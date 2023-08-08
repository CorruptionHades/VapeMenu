package me.corruptionhades.vapemenu.mixins;

import me.corruptionhades.vapemenu.event.impl.HandSwingEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerMixin {

    @Inject(at = @At("HEAD"), method = "swingHand", cancellable = true)
    public void swingHand(Hand hand, CallbackInfo ci) {
        // Call the Hand Swing event
        HandSwingEvent event = new HandSwingEvent(hand);
        if(event.isCancelled()) ci.cancel();
    }
}
