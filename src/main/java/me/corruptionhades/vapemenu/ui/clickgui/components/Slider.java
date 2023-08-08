package me.corruptionhades.vapemenu.ui.clickgui.components;

import me.corruptionhades.vapemenu.VapeMenu;
import me.corruptionhades.vapemenu.ui.clickgui.VapeClickGui;
import me.corruptionhades.vapemenu.ui.clickgui.components.*;
import me.corruptionhades.vapemenu.setting.NumberSetting;
import me.corruptionhades.vapemenu.utils.RenderUtils;
import me.corruptionhades.vapemenu.utils.Theme;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Slider extends Component {

    private final NumberSetting setting;
    private final VapeClickGui parent;
    private float x, y;
    private boolean dragging = false;

    public Slider(NumberSetting setting, float x, float y, VapeClickGui parent) {
        this.setting = setting;
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    @Override
    public void drawScreen(DrawContext context, int mouseX, int mouseY, float delta) {

        MatrixStack matrices = context.getMatrices();

        float present = (float) (((x + parent.windowX + parent.width - 11) - (x + parent.windowX + 450 + parent.settingsFieldX))
                * (((Number) setting.getValue()).floatValue() - setting.getMin())
                / (setting.getMax() - setting.getMin()));

        // Setting Name
        if(dragging) {
            VapeMenu.getFontRenderer().drawString(matrices, setting.getName(), parent.windowX + 445 + parent.settingsFieldX, y + 5, -1);
        }
        else {
            VapeMenu.getFontRenderer().drawString(matrices, setting.getName(), parent.windowX + 445 + parent.settingsFieldX, y + 5, Theme.MODULE_TEXT.getRGB());
        }

        // Value
        VapeMenu.getFontRenderer().drawString(matrices, "" + setting.getValue(), parent.windowX + parent.width - 20, y + 5, Theme.NORMAL_TEXT_COLOR.getRGB());
        // Bg
        RenderUtils.renderRoundedQuad(context, x + parent.windowX + 450 + parent.settingsFieldX, y + 20, x + parent.windowX + parent.width - 11, y + 21.5f, 1, 20, Theme.SLIDER_SETTING_BG);
        // Slider itself
        RenderUtils.renderRoundedQuad(context, x + parent.windowX + 450 + parent.settingsFieldX, y + 20, x + parent.windowX + 450 + parent.settingsFieldX + present, y + 21.5f, 1, 20, Theme.ENABLED);

        if(dragging) {
            float render2 = (float) setting.getMin();
            double max = setting.getMax();
            double inc = 0.1;
            double valAbs = (double) mouseX - ((double) (x + parent.windowX + 450 + parent.settingsFieldX));
            double perc = valAbs / (((x + parent.windowX + parent.width - 11) - (x + parent.windowX + 450 + parent.settingsFieldX)));
            perc = Math.min(Math.max(0.0D, perc), 1.0D);
            double valRel = (max - render2) * perc;
            double val = render2 + valRel;
            val = (double) Math.round(val * (1.0D / inc)) / (1.0D / inc);
            setting.setValue(val);
        }
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isHovered(x + parent.windowX + 450 + parent.settingsFieldX, y + 18, x + parent.windowX + parent.width - 11, y + 23.5f, mouseX, mouseY)) {
            dragging = true;
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        dragging = false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
