package me.corruptionhades.vapemenu.mixin;

import me.corruptionhades.vapemenu.VapeMenu;
import me.corruptionhades.vapemenu.command.Command;
import me.corruptionhades.vapemenu.command.CommandManager;
import me.corruptionhades.vapemenu.event.impl.ChatMessageSentEvent;
import me.corruptionhades.vapemenu.event.impl.HandSwingEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.message.ChatMessageSigner;
import net.minecraft.text.Text;
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

    @Inject(at = @At("HEAD"), method = "sendChatMessagePacket", cancellable = true)
    public void sendChatMessage(ChatMessageSigner signer, String msg, Text preview, CallbackInfo ci) {
        StringBuilder CMD = new StringBuilder();
        for (int i = 1; i < msg.toCharArray().length; ++i) {
            CMD.append(msg.toCharArray()[i]);
        }
        String[] args = CMD.toString().split(" ");

        if(msg.startsWith(VapeMenu.getCommandPrefix())) {
            for(Command command : CommandManager.INSTANCE.getCmds()) {
                if(args[0].equalsIgnoreCase(command.getName())) {
                    command.onCmd(msg, args);
                    ci.cancel();
                    break;
                }
            }
        }

        ChatMessageSentEvent chatMessageSentEvent = new ChatMessageSentEvent(signer, msg, preview);
        if(chatMessageSentEvent.isCancelled()) ci.cancel();
        signer = chatMessageSentEvent.getSigner();
        msg = chatMessageSentEvent.getMessage();
        preview = chatMessageSentEvent.getPreview();
    }
}
