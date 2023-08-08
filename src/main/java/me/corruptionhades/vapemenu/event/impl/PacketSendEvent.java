package me.corruptionhades.vapemenu.event.impl;

import me.corruptionhades.vapemenu.event.Event;
import net.minecraft.network.packet.Packet;

public class PacketSendEvent extends Event {

    private Packet<?> packet;

    public PacketSendEvent(Packet<?> packet) {
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return packet;
    }

    public void setPacket(Packet<?> packet) {
        this.packet = packet;
    }
}
