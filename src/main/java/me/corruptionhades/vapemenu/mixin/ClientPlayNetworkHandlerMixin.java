package me.corruptionhades.vapemenu.mixin;

import me.corruptionhades.vapemenu.VapeMenu;
import me.corruptionhades.vapemenu.command.Command;
import me.corruptionhades.vapemenu.command.CommandManager;
import me.corruptionhades.vapemenu.event.impl.CommandSuggestEvent;
import me.corruptionhades.vapemenu.event.impl.PacketSendEvent;
import me.corruptionhades.vapemenu.utils.PacketHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import net.minecraft.network.packet.s2c.play.CommandSuggestionsS2CPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {

    @Inject(method = "onCommandSuggestions", at =  @At("TAIL"))
    public void onCmdSuggest(CommandSuggestionsS2CPacket packet, CallbackInfo ci) {
        new CommandSuggestEvent(packet).call();
    }

    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    public void onSend(Packet<?> packet, CallbackInfo ci) {
        PacketSendEvent pse = new PacketSendEvent(packet);
        if(pse.isCancelled()) ci.cancel();
    }
}
