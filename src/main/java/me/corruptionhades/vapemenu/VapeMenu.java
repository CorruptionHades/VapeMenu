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
    private static String name;

    // The Config that is currently used
    public static Config selectedConfig;

    @Override
    public void onInitialize() {
        name = "Client_Name";
        try {
            ConfigLoader.loadConfigs();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set Theme to dark
        Theme.darkTheme();

        System.out.println("Loaded " + name + "!");
    }

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

    // Get the Mods name
    public static String getName() {
        return name;
    }
}
