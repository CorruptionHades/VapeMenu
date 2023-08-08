package me.corruptionhades.vapemenu.module.impl.combat;

import me.corruptionhades.vapemenu.event.EventTarget;
import me.corruptionhades.vapemenu.event.impl.EventUpdate;
import me.corruptionhades.vapemenu.event.impl.HandSwingEvent;
import me.corruptionhades.vapemenu.module.Category;
import me.corruptionhades.vapemenu.module.Module;
import me.corruptionhades.vapemenu.setting.NumberSetting;
import me.corruptionhades.vapemenu.utils.PacketHelper;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.function.Predicate;

public class Reach extends Module {

    private final NumberSetting maxDistance = new NumberSetting("Amount", 1, 100, 10, 1);

    public Reach() {
        super("Reach", "Up to 100 blocks of reach", Category.COMBAT);
        addSettings(maxDistance);
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(mc.options.useKey.wasPressed()) {

            BlockHitResult hit = getTargetBlock((int) maxDistance.getValue());
            BlockPos block = hit.getBlockPos();

            Block target = mc.world.getBlockState(block).getBlock();

            if(!(target instanceof ChestBlock || target instanceof EnderChestBlock || target instanceof BarrelBlock || target instanceof ShulkerBoxBlock)) {
                return;
            }

            setDisplayName(getName() + "[" + maxDistance.getValue() + "]" + " [" + target.getName().getString() + "]");

            Vec3d block_pos = new Vec3d(block.getX(), block.getY(), block.getZ());
            Vec3d playerPos = mc.player.getPos();
            double targetDist = targetDistance(playerPos, block_pos);

            if(targetDist > 5) {
                tpTo(playerPos, block_pos);
                PlayerInteractBlockC2SPacket packet = new PlayerInteractBlockC2SPacket(mc.player.preferredHand, hit, 1);
                PacketHelper.sendPacket(packet);

                mc.player.setPosition(playerPos);
                mc.player.swingHand(mc.player.preferredHand);
            }
        }
    }

    @EventTarget
    public void onSwing(HandSwingEvent e) {
        if(mc.options.attackKey.isPressed()) {
            Entity target = getTarget((int) maxDistance.getValue());

            if(target == null || target.getType().equals(EntityType.ITEM) || target.getType().equals(EntityType.EXPERIENCE_ORB)) {
                return;
            }
            else {
                setDisplayName(getName() + "[" + maxDistance.getValue() + "]" + " [" + target.getName().getString() + "]");
            }

            Vec3d playerPos = mc.player.getPos();
            Vec3d entityPos = target.getPos();

            tpTo(playerPos, entityPos);

            PlayerInteractEntityC2SPacket attackPacket = PlayerInteractEntityC2SPacket.attack(target, false);
            PacketHelper.sendPacket(attackPacket);

            tpTo(entityPos, playerPos);

            mc.player.setPosition(playerPos);
        }
    }

    private void tpTo(Vec3d from, Vec3d to) {
        double distancePerBlink = 8.0;
        double targetDistance = Math.ceil(from.distanceTo(to) / distancePerBlink);
        for(int i = 1; i <= targetDistance; i++) {
            Vec3d tempPos = from.lerp(to, i / targetDistance);
            PacketHelper.sendPosition(tempPos);
        }
    }

    private double targetDistance(Vec3d from, Vec3d to) {
        return Math.ceil(from.distanceTo(to));
    }

    public Entity getTarget(int max) {
        Entity entity2 = mc.getCameraEntity();
        Predicate<Entity> predicate = entity -> true; // Do entity checking here(ex: preventing specific entities from triggering)
        Vec3d eyePos = entity2.getEyePos();
        Vec3d vec3d2 = entity2.getRotationVec(mc.getTickDelta()).multiply(max);
        Vec3d vec3d3 = eyePos.add(vec3d2);
        Box box = entity2.getBoundingBox().stretch(vec3d2).expand(1.0);
        EntityHitResult entityHitResult = ProjectileUtil.raycast(entity2, eyePos, vec3d3, box, predicate, max * max);

        if(entityHitResult == null) return null;

        Entity res = entityHitResult.getEntity();

        return entityHitResult.getEntity();
    }

    public BlockHitResult getTargetBlock(int max) {
        HitResult hitResult = mc.cameraEntity.raycast(max, mc.getTickDelta(), false);
        if(hitResult instanceof BlockHitResult hit) {
            return hit;
        }

        return null;
    }
}
