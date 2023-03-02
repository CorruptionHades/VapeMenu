package me.corruptionhades.vapemenu;

import me.corruptionhades.vapemenu.gui.HudConfigScreen;
import me.corruptionhades.vapemenu.gui.clickgui.VapeClickGui;
import me.corruptionhades.vapemenu.config.Config;
import me.corruptionhades.vapemenu.config.ConfigLoader;
import me.corruptionhades.vapemenu.module.ModuleManager;
import me.corruptionhades.vapemenu.utils.Theme;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class VapeMenu implements ModInitializer {

    // Instance of this class
    private static final VapeMenu INSTANCE = new VapeMenu();

    // The Mod name
    private static final String name = "Client_Name";
    // Chat Prefix
    private static final String prefix = "&7[&4" + name + "&7] &a";
    // Command prefix
    private static final String commandPrefix = "#";

    // The Config that is currently used
    public static Config selectedConfig;

    @Override
    public void onInitialize() {

        try {
            ConfigLoader.loadConfigs();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set Theme to dark (no others so far)
        Theme.darkTheme();

        System.out.println("Loaded " + name + "!");
    }

    // Open menus
    public void onKeyPress(int key, int action) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.currentScreen == null) {
            if(action == GLFW.GLFW_PRESS) {
                if(key == GLFW.GLFW_KEY_RIGHT_SHIFT) mc.setScreen(VapeClickGui.getINSTANCE());
                if(key == GLFW.GLFW_KEY_H) mc.setScreen(new HudConfigScreen());
            }
        }
    }

    // Get the Instance
    public static VapeMenu getINSTANCE() {
        return INSTANCE;
    }

    // Getters
    public static String getName() {
        return name;
    }

    public static String getCommandPrefix() {
        return commandPrefix;
    }

    public static String getPrefix() {
        return prefix;
    }
}
