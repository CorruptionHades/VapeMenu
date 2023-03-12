package me.corruptionhades.vapemenu.command.impl;

import me.corruptionhades.vapemenu.command.Command;
import me.corruptionhades.vapemenu.utils.PacketHelper;
import net.minecraft.util.math.Vec3d;

public class TeleportCommand extends Command {

    public TeleportCommand() {
        super("teleport", "Teleport", "tp");
    }

    @Override
    public void onCmd(String message, String[] args) {
        String x = args[1];
        String y = args[2];
        String z = args[3];

        double xCoord = getX(x);
        double yCoord = getX(y);
        double zCoord = getX(z);

        for(int i = 0; i < 8; i++) {
            PacketHelper.sendPosition(mc.player.getPos());
        }

        // TODO: Optimize coordinates
        Vec3d targetPos = new Vec3d(mc.player.getX() + xCoord, mc.player.getY() + yCoord, mc.player.getZ() + zCoord);
        PacketHelper.sendPosition(targetPos);
        mc.player.setPosition(targetPos);
        chat.sendPrefixMsg("&7Successfully teleported!");
    }
}
