package com.seailz.atom;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.seailz.atom.utils.Dispatcher;
import com.seailz.atom.workers.BungeeChannelListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;

/**
 * The main class for the Atom project.
 * You can create a new instance of this class and start calling methods to begin using the Atom API.
 *
 * @author Seailz
 * @since 1.0.0
 */
public class Atom {

    private Dispatcher dispatcher;
    private BungeeChannelListener bungeeChannelListener;
    private JavaPlugin plugin;

    public Atom(JavaPlugin plugin) {
        this.dispatcher = Dispatcher.getInstance(this);
        this.bungeeChannelListener = new BungeeChannelListener(this);
        this.plugin = plugin;

        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", bungeeChannelListener);
    }

    /**
     * Sends a message to the specified sub channel.
     * @param channel The sub-channel to send the message to.
     * @param message The message to send.
     * @param player The player to send the message to.
     * @param justJoined Whether or not the player just joined the server. If so, required by spigot, a slight delay is added.
     */
    public void sendMessage(String channel, String message, Player player, boolean justJoined) {
        if (justJoined) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> sendBungeeMessage(channel, message, player), 20L);
            return;
        }

        sendBungeeMessage(channel, message, player);
    }

    public void sendMessage(String channel, String message, Player player) {
        sendMessage(channel, message, player, false);
    }

    public void sendMessage(String channel, String message) {
        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        sendBungeeMessage(channel, message, player);
    }

    private void sendBungeeMessage(String channel, String message, Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF(channel);
        out.writeUTF(message);

        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

}
