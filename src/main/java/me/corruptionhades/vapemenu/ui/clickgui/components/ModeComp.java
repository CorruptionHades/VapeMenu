package me.corruptionhades.vapemenu.ui.clickgui.components;

import me.corruptionhades.vapemenu.VapeMenu;
import me.corruptionhades.vapemenu.ui.clickgui.VapeClickGui;
import me.corruptionhades.vapemenu.ui.clickgui.components.*;
import me.corruptionhades.vapemenu.setting.ModeSetting;
import me.corruptionhades.vapemenu.utils.RenderUtils;
import me.corruptionhades.vapemenu.utils.Theme;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class ModeComp extends Component {

    private final ModeSetting setting;
    private final VapeClickGui parent;
    private float x, y;

    public ModeComp(ModeSetting setting, VapeClickGui parent, float x, float y) {
        this.setting = setting;
        this.parent = parent;
        this.x = x;
        this.y = y;
    }

    @Override
    public void drawScreen(DrawContext context, int mouseX, int mouseY, float delta) {
        MatrixStack matrices = context.getMatrices();
        RenderUtils.renderRoundedQuad(context, x + parent.windowX + 445 + parent.settingsFieldX, y + 2, x + parent.windowX + parent.width - 5, y + 22, 2, 20, Theme.MODE_SETTING_BG);
        RenderUtils.renderRoundedQuad(context, x + parent.windowX + 446 + parent.settingsFieldX, y + 3, x + parent.windowX + parent.width - 6, y + 21, 2, 20, Theme.MODE_SETTING_FILL);
        VapeMenu.getFontRenderer().drawString(matrices, setting.getName() + ":" + setting.getMode(), x + parent.windowX + 455 + parent.settingsFieldX, y + 10, Theme.NORMAL_TEXT_COLOR.getRGB());
        VapeMenu.getFontRenderer().drawString(matrices, ">", x + parent.windowX + parent.width - 15, y + 9, Theme.MODULE_TEXT.getRGB());
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {

        if(isHovered(x + parent.windowX + 445 + parent.settingsFieldX, y + 2, x + parent.windowX + parent.width - 5, y + 22, mouseX, mouseY) && button == 0) {
            setting.cycle();
        }
    }

    public void setY(float y) {
        this.y = y;
    }
}
