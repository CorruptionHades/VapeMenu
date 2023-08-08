package me.corruptionhades.vapemenu.event.impl;

import me.corruptionhades.vapemenu.event.Event;
import net.minecraft.network.packet.s2c.play.CommandSuggestionsS2CPacket;

public class CommandSuggestEvent extends Event {

    private final CommandSuggestionsS2CPacket packet;

    public CommandSuggestEvent(CommandSuggestionsS2CPacket packet) {
        this.packet = packet;
    }

    public CommandSuggestionsS2CPacket getPacket() {
        return packet;
    }
}
