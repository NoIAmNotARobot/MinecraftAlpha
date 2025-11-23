package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.entity.AlphaEntityBoat;

public class AlphaItemBoat extends AlphaItem {

    public AlphaItemBoat() {
        super("boat", 1);
    }

    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
        float var4 = 1.0F;
        float var5 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * var4;
        float var6 = var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * var4;
        double var7 = var3.prevPosX + (var3.posX - var3.prevPosX) * (double) var4;
        double var9 = var3.prevPosY + (var3.posY - var3.prevPosY) * (double) var4;
        double var11 = var3.prevPosZ + (var3.posZ - var3.prevPosZ) * (double) var4;
        Vec3 var13 = Vec3.createVectorHelper(var7, var9, var11);
        float var14 = MathHelper.cos(-var6 * ((float) Math.PI / 180.0F) - (float) Math.PI);
        float var15 = MathHelper.sin(-var6 * ((float) Math.PI / 180.0F) - (float) Math.PI);
        float var16 = -MathHelper.cos(-var5 * ((float) Math.PI / 180.0F));
        float var17 = MathHelper.sin(-var5 * ((float) Math.PI / 180.0F));
        float var18 = var15 * var16;
        float var20 = var14 * var16;
        double var21 = 5.0D;
        Vec3 var23 = var13.addVector((double) var18 * var21, (double) var17 * var21, (double) var20 * var21);
        MovingObjectPosition var24 = var2.rayTraceBlocks(var13, var23, true);
        if (var24 != null) {
            if (var24.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                if (!var2.isRemote) {
                    int var25 = var24.blockX;
                    int var26 = var24.blockY;
                    int var27 = var24.blockZ;
                    var2.spawnEntityInWorld(
                        new AlphaEntityBoat(
                            var2,
                            ((float) var25 + 0.5F),
                            ((float) var26 + 1.5F),
                            ((float) var27 + 0.5F)));
                }
                --var1.stackSize;
            }

        }
        return var1;
    }
}
