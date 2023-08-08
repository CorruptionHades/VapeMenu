package me.corruptionhades.vapemenu.ui.clickgui.components;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Component {

    protected final MinecraftClient mc = MinecraftClient.getInstance();

    public void drawScreen(DrawContext context, int mouseX, int mouseY, float delta) {}

    public void mouseReleased(double mouseX, double mouseY, int button) {}

    public void mouseClicked(double mouseX, double mouseY, int button) {}

    public void keyPressed(int keyCode, int scanCode, int modifiers) {}

    public void charTyped(char chr, int modifiers) {}

    protected static boolean isHovered(float x, float y, float x2, float y2, double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }
}
