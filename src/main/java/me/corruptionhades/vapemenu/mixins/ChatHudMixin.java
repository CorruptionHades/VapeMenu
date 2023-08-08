package me.corruptionhades.vapemenu.mixins;

import me.corruptionhades.vapemenu.event.impl.ChatMessageReceivedEvent;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatHudMixin {

    @Inject(at = @At("HEAD"), method = "addMessage*", cancellable = true)
    private void onMessage(Text message, CallbackInfo ci) {
        ChatMessageReceivedEvent event = new ChatMessageReceivedEvent(message);
        if(event.isCancelled()) ci.cancel();
    }
}
