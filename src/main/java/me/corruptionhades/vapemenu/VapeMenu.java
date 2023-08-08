package me.corruptionhades.vapemenu;

import me.corruptionhades.vapemenu.config.Config;
import me.corruptionhades.vapemenu.config.ConfigLoader;
import me.corruptionhades.vapemenu.module.Module;
import me.corruptionhades.vapemenu.module.ModuleManager;
import me.corruptionhades.vapemenu.ui.HudConfigScreen;
import me.corruptionhades.vapemenu.ui.clickgui.VapeClickGui;
import me.corruptionhades.vapemenu.utils.FontRenderer;
import me.corruptionhades.vapemenu.utils.Theme;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class VapeMenu implements ClientModInitializer {

    // Instance of this class
    private static final VapeMenu INSTANCE = new VapeMenu();

    private static FontRenderer fontRenderer;

    private final String name = "Client_Name";
    private final String chatPrefix = "&7[&4" + name + "&7] &a";
    private final String commandPrefix = "#";

    public Config selectedConfig;

    @Override
    public void onInitializeClient() {
        // Loads configs if there are any
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
        // Check if the player is ingame
        if(mc.currentScreen == null) {
            if(action == GLFW.GLFW_PRESS) {
                // Open the Clickgui
                if(key == GLFW.GLFW_KEY_RIGHT_SHIFT) mc.setScreen(VapeClickGui.getINSTANCE());
                // Open the Hud Config Menu
                if(key == GLFW.GLFW_KEY_H) mc.setScreen(new HudConfigScreen());

                // For Module toggling
                for(Module module : ModuleManager.INSTANCE.getModules()) {
                    if(module.getKey() == key) {
                        module.toggle();
                    }
                }
            }
        }
    }

    public static FontRenderer getFontRenderer() {
        if(fontRenderer == null) {
            fontRenderer = new FontRenderer("BalooDa2-VariableFont_wght.ttf", new Identifier("vape_menu", "textures/font"), 20);
        }
        return fontRenderer;
    }

    // Getters
    public static VapeMenu getInstance() {
        return INSTANCE;
    }

    public String getName() {
        return name;
    }

    public String getCommandPrefix() {
        return commandPrefix;
    }

    public String getChatPrefix() {
        return chatPrefix;
    }
}
