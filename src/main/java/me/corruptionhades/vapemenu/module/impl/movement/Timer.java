package me.corruptionhades.vapemenu.module.impl.movement;

import me.corruptionhades.vapemenu.module.Category;
import me.corruptionhades.vapemenu.module.Module;
import me.corruptionhades.vapemenu.setting.NumberSetting;

public class Timer extends Module {

    public static final NumberSetting timerSpeed = new NumberSetting("Speed", 0.01, 2, 1, 0.01);

    public Timer() {
        super("Timer", "Change the speed of the game", Category.MOVEMENT);
        addSettings(timerSpeed);
    }
}
