package com.seailz.atom.workers;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.seailz.atom.Atom;
import com.seailz.atom.events.model.message.ChannelMessageEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 * This worker is responsible for listening to the BungeeCord channel and dispatching messages to relevant listeners.
 * This is an internal class and should not be used by the developer.
 * @author Seailz
 * @since 1.0.0
 */
public class BungeeChannelListener implements PluginMessageListener {

    private Atom atom;

    public BungeeChannelListener(Atom atom) {
        this.atom = atom;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        ByteArrayDataInput inCopy = ByteStreams.newDataInput(message);
        String subChannel = in.readUTF();

        // Dispatch
        ChannelMessageEvent event = new ChannelMessageEvent(atom, subChannel, in.readUTF(), player, inCopy);
        atom.getDispatcher().dispatch(event);
    }
}
