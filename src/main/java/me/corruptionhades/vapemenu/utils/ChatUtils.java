package me.corruptionhades.vapemenu.utils;

import me.corruptionhades.vapemenu.VapeMenu;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatUtils {

    private MinecraftClient mc = MinecraftClient.getInstance();

    // Unicode character for §
    private final String paragraph = "\u00A7";

    public void sendMsg(String text) {
        if(mc.player != null) mc.player.sendMessage(Text.of(translate(text)));
    }

    public void sendMsg(Text text) {
        if(mc.player != null) mc.player.sendMessage(text);
    }

    public void sendPrefixMsg(String text) {
        if(mc.player != null) mc.player.sendMessage(Text.of(VapeMenu.getPrefix() + translate(text)));
    }

    public String translate(String text) {
        return text.replace("&", paragraph);
    }
}
