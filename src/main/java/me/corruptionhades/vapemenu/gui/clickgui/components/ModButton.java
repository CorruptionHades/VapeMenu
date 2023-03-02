package me.corruptionhades.vapemenu.gui.clickgui.components;

import me.corruptionhades.vapemenu.gui.clickgui.VapeClickGui;
import me.corruptionhades.vapemenu.module.Module;
import me.corruptionhades.vapemenu.module.ModuleManager;
import me.corruptionhades.vapemenu.setting.BooleanSetting;
import me.corruptionhades.vapemenu.setting.ModeSetting;
import me.corruptionhades.vapemenu.setting.NumberSetting;
import me.corruptionhades.vapemenu.setting.Setting;
import me.corruptionhades.vapemenu.utils.Animation;
import me.corruptionhades.vapemenu.utils.RenderUtils;
import me.corruptionhades.vapemenu.utils.Theme;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModButton {

    // Module the Mod button will use
    private final Module module;
    // Coords of the button
    private double x, y;
    // Parent access
    private final VapeClickGui parent;

    // Minecraft access
    private final MinecraftClient mc = MinecraftClient.getInstance();

    // List of all setting components
    private final List<Component> components = new ArrayList<>();

    // Toggle animation
    private final Animation toggleAnim;

    public ModButton(Module module, double x, double y, VapeClickGui parent) {
        this.module = module;
        this.y = y;
        this.x = x;
        this.parent = parent;
        toggleAnim = new Animation(0, 10);
    }

    // Drawing method
    public void drawScreen(MatrixStack matrices, int mouseX, int mouseY, float delta) {

        x = parent.settingsFieldX;

        // If the mouse is hovered on the module
        if (isHovered(parent.windowX + (float) x + 100 + parent.coordModX, (float) (parent.windowY + y - 10), parent.windowX + (float) x + 425 + parent.coordModX, (float) (parent.windowY + y + 25), mouseX, mouseY)) {
            if (module.isEnabled()) {
                RenderUtils.renderRoundedQuad(matrices, parent.windowX + (float) x + 100 + parent.coordModX, parent.windowY + y - 10, parent.windowX + (float) x + 425 + parent.coordModX, parent.windowY + y + 25, 5, 20, Theme.MODULE_ENABLED_BG_HOVER);
            } else {
                RenderUtils.renderRoundedQuad(matrices, parent.windowX + (float) x + 100 + parent.coordModX, parent.windowY + y - 10, parent.windowX + (float) x + 425 + parent.coordModX, parent.windowY + y + 25, 5, 20, Theme.MODULE_DISABLED_BG_HOVER);
            }
        }
        else {
            if (module.isEnabled()) {
                RenderUtils.renderRoundedQuad(matrices, parent.windowX + (float) x + 100 + parent.coordModX, parent.windowY + y - 10, parent.windowX + (float) x + 425 + parent.coordModX, parent.windowY + y + 25, 5, 20, Theme.MODULE_ENABLED_BG);
            } else {
                RenderUtils.renderRoundedQuad(matrices, parent.windowX + (float) x + 100 + parent.coordModX, parent.windowY + y - 10, parent.windowX + (float) x + 425 + parent.coordModX, parent.windowY + y + 25, 5, 20, Theme.MODULE_DISABLED_BG);
            }
        }

        RenderUtils.renderRoundedQuad(matrices, parent.windowX + (float) x + 100 + parent.coordModX, parent.windowY + y - 10, parent.windowX + (float) x + 125 + parent.coordModX, parent.windowY + y + 25, 5, 20, Theme.MODULE_COLOR);
        RenderUtils.renderRoundedQuad(matrices, parent.windowX + (float) x + 410 + parent.coordModX, parent.windowY + y - 10, parent.windowX + (float) x + 425 + parent.coordModX, parent.windowY + y + 25, 5, 20, Theme.SETTINGS_HEADER);
        mc.textRenderer.draw(matrices,".", parent.windowX + (float) x + 416 + parent.coordModX, (float) (parent.windowY + y - 5), Theme.MODULE_TEXT.getRGB());
        mc.textRenderer.draw(matrices,".", parent.windowX + (float) x + 416 + parent.coordModX, (float) (parent.windowY + y - 1), Theme.MODULE_TEXT.getRGB());
        mc.textRenderer.draw(matrices,".", parent.windowX + (float) x + 416 + parent.coordModX, (float) (parent.windowY + y + 3), Theme.MODULE_TEXT.getRGB());

        // Bob the description forth a bit TODO: align it to the Module Name's length
        if (isHovered(parent.windowX + (float) x + 100 + parent.coordModX, parent.windowY + (float) y - 10, parent.windowX + (float) x + 425 + parent.coordModX, (float) (parent.windowY + y + 25), mouseX, mouseY)) {
            mc.textRenderer.draw(matrices, module.getDescription() + ".", parent.windowX + (float) x + 225 + parent.coordModX, parent.windowY + (float) (y + 5), Theme.MODULE_TEXT.getRGB());
        } else {
            mc.textRenderer.draw(matrices, module.getDescription() + ".", parent.windowX + (float) x + 220 + parent.coordModX, parent.windowY + (float) (y + 5), Theme.MODULE_TEXT.getRGB());
        }

        // Update the animation value
        toggleAnim.update();

        if (module.isEnabled()) {
            // Draw the Module name
            mc.textRenderer.draw(matrices,module.getName(), parent.windowX + (float) x + 140 + parent.coordModX, (float) (parent.windowY + y + 5), Theme.NORMAL_TEXT_COLOR.getRGB());
            RenderUtils.renderRoundedQuad(matrices, parent.windowX + (float) x + 100 + parent.coordModX, parent.windowY + y - 10, parent.windowX + (float) x + 125 + parent.coordModX, parent.windowY + y + 25, 5, 20, new Color(41, 117, 221, (int) ((toggleAnim.getValue() * 10) / 100f * 255)));

            toggleAnim.setEnd(10);

            RenderUtils.renderRoundedQuad(matrices, parent.windowX + (float) x + 380 + parent.coordModX, parent.windowY + y + 2, parent.windowX + (float) x + 400 + parent.coordModX, parent.windowY + y + 12, 4, 5, new Color(33, 94, 181, (int) ((toggleAnim.getValue() * 10) / 100f * 255)));
            RenderUtils.drawCircle(matrices,parent.windowX + x + parent.coordModX + 385 + toggleAnim.getValue(), parent.windowY + y + 7, 3.5, 20, Theme.NORMAL_TEXT_COLOR.getRGB());
        }
        else {
            mc.textRenderer.draw(matrices, module.getName(), parent.windowX + (float) x + 140 + parent.coordModX, parent.windowY + (float) (y + 5), Theme.MODULE_TEXT.getRGB());

            toggleAnim.setEnd(0);

            RenderUtils.renderRoundedQuad(matrices, parent.windowX + (float) x + 380 + parent.coordModX, parent.windowY + y + 2, parent.windowX + (float) x + 400 + parent.coordModX, parent.windowY + y + 12, 4, 5, Theme.TOGGLE_BUTTON_BG);
            RenderUtils.renderRoundedQuad(matrices, parent.windowX + (float) x + 381 + parent.coordModX, parent.windowY + y + 3, parent.windowX + (float) x + 399 + parent.coordModX, parent.windowY + y + 11, 3, 20, Theme.TOGGLE_BUTTON_FILL);
            RenderUtils.drawCircle(matrices, parent.windowX + x + parent.coordModX + 385 + toggleAnim.getValue(), parent.windowY + y + 7, 3.5, 20, Theme.TOGGLE_BUTTON_BG);
        }

        // Update Y Component values
        float settingsY = parent.windowY + 100 + parent.settingsFNow;
        for(Component component : components) {
            if(component instanceof CheckBox cb) cb.setY(settingsY);
            if(component instanceof Slider s) s.setY(settingsY);
            if(component instanceof ModeComp m) m.setY(settingsY);
            settingsY += 25;
        }

        // Only draw components when the module is expanded
        if(module.isExpanded()) components.forEach(c -> c.drawScreen(matrices, mouseX, mouseY, delta));
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        // Toggle the module
        if (isHovered(parent.windowX + (float) x + 100 + parent.coordModX, parent.windowY + (float) y - 10, parent.windowX + (float) x + 425 + parent.coordModX, parent.windowY + (float) y + 25, mouseX, mouseY) && button == 0) {
            module.toggle();
        }
        // Show Settings
        else if(isHovered(parent.windowX + (float) x + 100 + parent.coordModX, parent.windowY + (float) y - 10, parent.windowX + (float) x + 425 + parent.coordModX, parent.windowY + (float) y + 25, mouseX, mouseY) && button == 1) {

            if(parent.selectedModule != null && parent.selectedModule != module) {
                parent.selectedModule = null;
                for(Module module : ModuleManager.INSTANCE.getModulesByCategory(parent.selectedCategory)) module.setExpanded(false);
            }
            if (parent.selectedModule != module) {
                parent.settingsFieldX = 0;
                parent.selectedModule = module;

                components.clear();
                for(Module module : ModuleManager.INSTANCE.getModulesByCategory(parent.selectedCategory)) if(module != this.module) module.setExpanded(false);

                module.setExpanded(true);

                float settingsY = parent.windowY + 100 + parent.settingsFNow;
                for(Setting setting : module.getSettings()) {
                    if(setting instanceof BooleanSetting booleanSetting) {
                        components.add(new CheckBox(booleanSetting, parent, 0, settingsY));
                    }
                    else if(setting instanceof NumberSetting numberSetting) {
                        components.add(new Slider(numberSetting, 0, settingsY, parent));
                    }
                    else if(setting instanceof ModeSetting modeSetting) {
                        components.add(new ModeComp(modeSetting, parent, 0, settingsY));
                    }
                    settingsY += 25;
                }
            }
            else if (parent.selectedModule == module) {
                parent.selectedModule = null;
                for(Module module : ModuleManager.INSTANCE.getModulesByCategory(parent.selectedCategory)) module.setExpanded(false);
            }
        }

        if(module.isExpanded()) components.forEach(c -> c.mouseClicked(mouseX, mouseY, button));
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if(module.isExpanded()) components.forEach(c -> c.mouseReleased(mouseX, mouseY, button));
    }

    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        if(module.isExpanded()) {
            components.forEach(c -> c.keyPressed(keyCode, scanCode, modifiers));
        }
    }

    public void charTyped(char chr, int modifiers) {
        if(module.isExpanded()) {
            components.forEach(c -> c.charTyped(chr, modifiers));
        }
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    private boolean isHovered(float x, float y, float x2, float y2, double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }

    public Module getModule() {
        return module;
    }
}
