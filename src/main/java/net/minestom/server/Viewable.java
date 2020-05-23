package net.minestom.server;

import net.minestom.server.entity.Player;
import net.minestom.server.network.PacketWriterUtils;
import net.minestom.server.network.packet.server.ServerPacket;

import java.util.HashSet;
import java.util.Set;

public interface Viewable {

    /**
     * @param player the viewer to add
     * @return true if the player has been added, false otherwise (could be because he is already a viewer)
     */
    boolean addViewer(Player player);

    /**
     * @param player the viewer to remove
     * @return true if the player has been removed, false otherwise (could be because he was not a viewer)
     */
    boolean removeViewer(Player player);

    Set<Player> getViewers();

    default boolean isViewer(Player player) {
        return getViewers().contains(player);
    }

    default void sendPacketToViewers(ServerPacket packet) {
        PacketWriterUtils.writeAndSend(getViewers(), packet);
    }

    default void sendPacketsToViewers(ServerPacket... packets) {
        for (ServerPacket packet : packets) {
            PacketWriterUtils.writeAndSend(getViewers(), packet);
        }
    }

    default void sendPacketToViewersAndSelf(ServerPacket packet) {
        if (this instanceof Player) {
            if (getViewers().isEmpty()) {
                ((Player) this).getPlayerConnection().sendPacket(packet);
            } else {
                UNSAFE_sendPacketToViewersAndSelf(packet);
            }
        } else {
            sendPacketToViewers(packet);
        }
    }

    private void UNSAFE_sendPacketToViewersAndSelf(ServerPacket packet) {
        Set<Player> recipients = new HashSet<>(getViewers());
        recipients.add((Player) this);
        PacketWriterUtils.writeAndSend(recipients, packet);
    }

}
