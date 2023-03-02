package me.corruptionhades.vapemenu.module;

import me.corruptionhades.vapemenu.event.EventManager;
import me.corruptionhades.vapemenu.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Module {

    private String name, description, displayName;
    private int key;
    private boolean enabled;
    private Category category;

    private boolean expanded;

    protected MinecraftClient mc = MinecraftClient.getInstance();

    private final List<Setting> settings = new ArrayList<>();

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.displayName = name;
    }

    public void toggle() {
       this.enabled = !this.enabled;

       if(enabled) {
           onEnable();
           mc.player.sendMessage(Text.of(name + " was enabled!"));
       }
       else {
           onDisable();
           mc.player.sendMessage(Text.of(name + " was disabled!"));
       }
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void addSettings(Setting...settingz) {
        settings.addAll(Arrays.asList(settingz));
    }

    public void onEnable() {EventManager.register(this);}
    public void onDisable() {EventManager.unregister(this);}

    public void setEnabled(boolean toggled) {
        this.enabled = toggled;
        if(enabled) {
            onEnable();
        }
        else {
            onDisable();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    protected void sendMsg(String message) {
        if(mc.player == null) return;
        mc.player.sendMessage(Text.of(message.replace("&", "§")));
    }
}
