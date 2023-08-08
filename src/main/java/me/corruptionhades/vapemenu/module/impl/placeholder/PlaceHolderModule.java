package me.corruptionhades.vapemenu.module.impl.placeholder;

import me.corruptionhades.vapemenu.module.Category;
import me.corruptionhades.vapemenu.module.Module;
import me.corruptionhades.vapemenu.setting.BooleanSetting;
import me.corruptionhades.vapemenu.setting.ModeSetting;
import me.corruptionhades.vapemenu.setting.NumberSetting;

public class PlaceHolderModule extends Module {

    BooleanSetting booleanSetting = new BooleanSetting("Boolean", false);
    NumberSetting numberSetting = new NumberSetting("Number", 1, 10, 1, 1);
    ModeSetting modeSetting = new ModeSetting("Mode", "Mode1", "Mode1", "Mode2", "Mode3");

    public PlaceHolderModule(String name, Category category) {
        super(name, "Description ", category);
        addSettings(booleanSetting, numberSetting, modeSetting);
    }
}
