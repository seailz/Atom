package com.seailz.atom.events.model.message;

import com.google.common.io.ByteArrayDataInput;
import com.seailz.atom.Atom;
import org.bukkit.entity.Player;

public class ChannelMessageEvent {

    private Atom atomInstance;
    // The subChannel that this message was sent on
    private String channel;
    // The message that was sent
    private String message;
    // The player that was attached to this message.
    private Player player;
    private ByteArrayDataInput in;

    public ChannelMessageEvent(Atom atomInstance, String channel, String message, Player player, ByteArrayDataInput in) {
        this.atomInstance = atomInstance;
        this.channel = channel;
        this.message = message;
        this.player = player;
        this.in = in;
    }

    public Player getPlayer() {
        return player;
    }

    public ByteArrayDataInput getIn() {
        return in;
    }

    public Atom getAtom() {
        return atomInstance;
    }

    public String getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }
}
