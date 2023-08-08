package me.corruptionhades.vapemenu.module.impl.render;

import me.corruptionhades.vapemenu.VapeMenu;
import me.corruptionhades.vapemenu.module.Category;
import me.corruptionhades.vapemenu.module.HudModule;
import me.corruptionhades.vapemenu.module.Module;
import me.corruptionhades.vapemenu.module.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

import java.util.List;

public class Arraylist extends HudModule {

    public Arraylist() {
        super("Arraylist", "Shows a list of enabled Modules", Category.RENDER, 0, 0, 1, 1);
    }

    @Override
    public void draw(DrawContext context) {

        MatrixStack matrices = context.getMatrices();

        List<Module> enabledModules = ModuleManager.INSTANCE.getEnabledModules();
        // Sort
        enabledModules.sort((m1, m2) -> mc.textRenderer.getWidth(m2.getDisplayName()) - mc.textRenderer.getWidth(m1.getDisplayName()));

        boolean leftHalf = getX() < (mc.getWindow().getScaledWidth() / 2);
        boolean rightHalf = getX() > (mc.getWindow().getScaledWidth() / 2);

        setWidth(mc.textRenderer.getWidth(enabledModules.get(0).getDisplayName()));

        if((getX() + getWidth()) > mc.getWindow().getScaledWidth()) {
            setX(mc.getWindow().getScaledWidth() - (getWidth()));
        }

        int offset = 0;
        for(Module module : enabledModules) {
            if(leftHalf) {
                VapeMenu.getFontRenderer().drawString(matrices, module.getDisplayName(), getX(), getY() + offset, -1);
            }
            else if(rightHalf) {
                VapeMenu.getFontRenderer().drawString(matrices, module.getDisplayName(), getX() + getWidth() - mc.textRenderer.getWidth(module.getDisplayName()), getY() + offset, -1);
            }
            offset += mc.textRenderer.fontHeight + 2;
        }

        setHeight(offset);
    }
}
