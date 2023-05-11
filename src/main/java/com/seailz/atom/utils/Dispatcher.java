package com.seailz.atom.utils;

import com.seailz.atom.Atom;
import com.seailz.atom.events.AtomListener;
import com.seailz.atom.events.annotation.AtomMethod;
import com.seailz.atom.events.model.message.ChannelMessageEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for dispatching messages to relevant listeners.
 * This is an internal class and should not be used by the developer.
 * @since 1.0.0
 * @author Seailz
 * @see com.seailz.atom.Atom
 */
public class Dispatcher {

    private final List<AtomListener> listeners = new ArrayList<>();
    private static Dispatcher instance;
    private Atom atom;

    private Dispatcher(Atom atom) {
        instance = this;
        this.atom = atom;
    }

    public void registerListener(AtomListener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(AtomListener listener) {
        listeners.remove(listener);
    }

    /**
     * Dispatches a message to all relevant listeners.
     * @param event The event to dispatch.
     */
    public void dispatch(ChannelMessageEvent event) {
        listeners.stream().filter(listener -> listener.getClass().isAnnotationPresent(AtomMethod.class)).forEach(listener -> {
            AtomMethod method = listener.getClass().getAnnotation(AtomMethod.class);
            boolean matches = match(event.getChannel(), method.channel(), method.ignoreWildcard());
            if (matches) listener.onMessageReceived(event);
        });
    }

    private boolean match(String input, String regex, boolean ignoreWildcard) {
        if (!ignoreWildcard) input = input.replaceAll("\\*", ".*");
        return input.matches(regex);
    }

    public static Dispatcher getInstance(Atom atom) {
        return instance == null ? new Dispatcher(atom) : instance;
    }

}
