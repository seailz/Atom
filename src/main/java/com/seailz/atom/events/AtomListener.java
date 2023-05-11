package com.seailz.atom.events;

import com.seailz.atom.events.model.message.ChannelMessageEvent;

/**
 * You must extend this class in your listener classes to create listeners.
 * @author Seailz
 * @since 1.0.0
 */
public abstract class AtomListener {

    public void onMessageReceived(ChannelMessageEvent event) {}

}
