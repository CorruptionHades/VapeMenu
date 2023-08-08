package me.corruptionhades.vapemenu.utils;

import me.corruptionhades.vapemenu.VapeMenu;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatUtils {

    private final MinecraftClient mc = MinecraftClient.getInstance();

    // Unicode character for '§'
    public static final String paragraph = "\u00A7";

    public void sendMsg(String text) {
        if(mc.player != null) mc.player.sendMessage(Text.of(translate(text)));
    }

    public void sendPrefixMsg(String text) {
        if(mc.player != null) mc.player.sendMessage(Text.of(translate(VapeMenu.getInstance().getChatPrefix()) + translate(text)));
    }

    public String translate(String text) {
        return text.replace("&", paragraph);
    }
}
