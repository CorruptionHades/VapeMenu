package me.corruptionhades.vapemenu.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatUtils {

    private MinecraftClient mc = MinecraftClient.getInstance();

    public void sendMsg(String text) {
        if(mc.player != null) mc.player.sendMessage(Text.of(text.replace('&', '§')));
    }

    public void sendMsg(Text text) {
        if(mc.player != null) mc.player.sendMessage(text);
    }

    public void sendPrefixMsg(String text) {
        if(mc.player != null) mc.player.sendMessage(Text.of("[PREFIX] " + text.replace('&', '§')));
    }

    public String translate(String text) {
        return text.replace('&', '§');
    }
}
