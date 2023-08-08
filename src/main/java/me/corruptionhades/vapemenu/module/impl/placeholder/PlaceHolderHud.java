package me.corruptionhades.vapemenu.module.impl.placeholder;

import me.corruptionhades.vapemenu.VapeMenu;
import me.corruptionhades.vapemenu.module.Category;
import me.corruptionhades.vapemenu.module.HudModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class PlaceHolderHud extends HudModule {
    public PlaceHolderHud(String name, Category category) {
        super(name, "Hud description", category, 0, 0, 10, 10);
    }

    @Override
    public void draw(DrawContext context) {
        VapeMenu.getFontRenderer().drawString(context.getMatrices(), getDisplayName(), getX(), getY(), -1);
        setWidth(mc.textRenderer.getWidth(getDisplayName()));
        setHeight(mc.textRenderer.fontHeight);
    }
}
