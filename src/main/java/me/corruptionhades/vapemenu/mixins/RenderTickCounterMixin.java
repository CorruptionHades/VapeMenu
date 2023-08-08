package me.corruptionhades.vapemenu.mixins;

import me.corruptionhades.vapemenu.module.ModuleManager;
import me.corruptionhades.vapemenu.module.impl.movement.Timer;
import net.minecraft.client.render.RenderTickCounter;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderTickCounter.class)
public class RenderTickCounterMixin {

    @Shadow
    public float lastFrameDuration;

    @Inject(method = "beginRenderTick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/RenderTickCounter;prevTimeMillis:J", opcode = Opcodes.PUTFIELD))
    private void onBeingRenderTick(long a, CallbackInfoReturnable<Integer> info) {
        // For slowing down/speeding up the render ticks
        if(ModuleManager.INSTANCE.getModuleByClass(Timer.class).isEnabled()) lastFrameDuration *= Timer.timerSpeed.getFloatValue();
    }
}
