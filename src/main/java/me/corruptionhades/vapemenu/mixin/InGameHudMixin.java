package me.corruptionhades.vapemenu.mixin;

import me.corruptionhades.vapemenu.module.HudModule;
import me.corruptionhades.vapemenu.module.Module;
import me.corruptionhades.vapemenu.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "render", at = @At("RETURN"), cancellable = true)
    public void renderHud(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.player != null) {
            for(Module module : ModuleManager.INSTANCE.getEnabledModules()) {
                if(module instanceof HudModule hudModule) {
                    // Draws the Hud Modules to the screen
                    hudModule.draw(matrices);
                }
            }
        }
    }
}
