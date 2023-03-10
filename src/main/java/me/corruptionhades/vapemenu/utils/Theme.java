package me.corruptionhades.vapemenu.utils;

import java.awt.*;

public class Theme {

    public static Color WINDOW_COLOR;
    public static Color ENABLED;

    public static Color UNFOCUSED_TEXT_COLOR;
    public static Color DISABLED_TEXT_COLOR;

    public static Color SETTINGS_BG;
    public static Color SETTINGS_HEADER;

    public static Color MODULE_ENABLED_BG_HOVER;
    public static Color MODULE_DISABLED_BG_HOVER;

    public static Color MODULE_ENABLED_BG;
    public static Color MODULE_DISABLED_BG;
    public static Color MODULE_COLOR;
    public static Color MODULE_TEXT;

    public static Color TOGGLE_BUTTON_BG;
    public static Color TOGGLE_BUTTON_FILL;

    public static Color NORMAL_TEXT_COLOR;

    public static Color MODE_SETTING_BG;
    public static Color MODE_SETTING_FILL;

    public static Color SLIDER_SETTING_BG;

    public static Color CONFIG_EDIT_BG;

    // This is to change the Click gui theme
    public static void darkTheme() {
        WINDOW_COLOR = new Color(21, 22, 25);
        ENABLED = new Color(51, 112, 203);

        UNFOCUSED_TEXT_COLOR = new Color(108, 109, 113);
        //DISABLED_TEXT_COLOR = ;

        SETTINGS_BG = new Color(32, 31, 35);
        SETTINGS_HEADER = new Color(39, 38, 42);

        MODULE_ENABLED_BG_HOVER = new Color(43, 41, 45);
        MODULE_DISABLED_BG_HOVER = new Color(35, 35, 35);

        MODULE_ENABLED_BG = new Color(36, 34, 38);
        MODULE_DISABLED_BG = new Color(32, 31, 33);
        MODULE_COLOR = new Color(37, 35, 39);
        MODULE_TEXT = new Color(94, 95, 98);

        TOGGLE_BUTTON_BG = new Color(59, 60, 65);
        TOGGLE_BUTTON_FILL = new Color(29, 27, 31);

        NORMAL_TEXT_COLOR = Color.white;

        MODE_SETTING_BG = new Color(46, 45, 48);
        MODE_SETTING_FILL = new Color(32, 31, 35);

        SLIDER_SETTING_BG = new Color(73, 72, 76);

        CONFIG_EDIT_BG = new Color(20, 20, 25);
    }

    public static void lightTheme() {

    }
}
