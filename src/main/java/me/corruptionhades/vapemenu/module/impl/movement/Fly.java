package me.corruptionhades.vapemenu.module.impl.movement;

import me.corruptionhades.vapemenu.event.EventTarget;
import me.corruptionhades.vapemenu.event.impl.EventUpdate;
import me.corruptionhades.vapemenu.module.Category;
import me.corruptionhades.vapemenu.module.Module;
import me.corruptionhades.vapemenu.setting.BooleanSetting;
import me.corruptionhades.vapemenu.setting.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class Fly extends Module {

    //ModeSetting modeSetting = new ModeSetting("Mode", "Vanilla", "Vanilla", "Hypixel", "Vulcan");
    NumberSetting speed = new NumberSetting("Speed", 0.01, 1, 0.2, 0.01);
    BooleanSetting warn = new BooleanSetting("Warn", true);

    public Fly() {
        super("Fly", "Makes you fly!", Category.MOVEMENT);
        setKey(GLFW.GLFW_KEY_G);
        addSettings(/*modeSetting,*/ speed, warn);
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(mc.player == null) return;
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed(speed.getFloatValue());
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if(warn.isEnabled()) {
            sendMsg("&7Warning: You can still take fall damage!");
        }
        if(mc.player == null) return;
        mc.player.getAbilities().allowFlying = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();

        if(mc.player == null) return;
        if(!mc.player.getAbilities().creativeMode) {
            mc.player.getAbilities().allowFlying = false;
            mc.player.getAbilities().flying = false;
        }
        mc.player.getAbilities().setFlySpeed(0.1f);
    }
}
