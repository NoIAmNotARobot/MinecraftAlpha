package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

import cpw.mods.fml.common.IFuelHandler;

public class AlphaItemBucket extends AlphaItem implements IFuelHandler {

    private final Block isFull;
    private final int burnTime;

    public AlphaItemBucket(String name, Block isFull) {
        this(name, isFull, 0);
    }

    public AlphaItemBucket(String name, Block isFull, int burnTime) {
        super(name, 1);
        this.setMaxDamage(64);
        this.isFull = isFull;
        this.burnTime = burnTime;
    }

    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
        float var4 = 1.0F;
        float var5 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * var4;
        float var6 = var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * var4;
        double var7 = var3.prevPosX + (var3.posX - var3.prevPosX) * (double) var4;
        double var9 = var3.prevPosY + (var3.posY - var3.prevPosY) * (double) var4
            + (double) (var2.isRemote ? var3.getEyeHeight() - var3.getDefaultEyeHeight() : var3.getEyeHeight());
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
        MovingObjectPosition var24 = var2.rayTraceBlocks(var13, var23, this.isFull == Blocks.air);
        if (var24 != null) {
            if (var24.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int var25 = var24.blockX;
                int var26 = var24.blockY;
                int var27 = var24.blockZ;
                if (this.isFull == Blocks.air) {
                    if ((var2.getBlock(var25, var26, var27) == AlphaBlock.waterStill
                        || var2.getBlock(var25, var26, var27) == AlphaBlock.waterMoving)
                        && var2.getBlockMetadata(var25, var26, var27) == 0) {
                        var2.setBlockToAir(var25, var26, var27);
                        return new ItemStack(AlphaItem.bucketWater);
                    }

                    if ((var2.getBlock(var25, var26, var27) == AlphaBlock.lavaStill
                        || var2.getBlock(var25, var26, var27) == AlphaBlock.lavaMoving)
                        && var2.getBlockMetadata(var25, var26, var27) == 0) {
                        var2.setBlockToAir(var25, var26, var27);
                        return new ItemStack(AlphaItem.bucketLava);
                    }
                } else {
                    if (this.isFull == null) {
                        return new ItemStack(AlphaItem.bucketEmpty);
                    }

                    if (var24.sideHit == 0) {
                        --var26;
                    }

                    if (var24.sideHit == 1) {
                        ++var26;
                    }

                    if (var24.sideHit == 2) {
                        --var27;
                    }

                    if (var24.sideHit == 3) {
                        ++var27;
                    }

                    if (var24.sideHit == 4) {
                        --var25;
                    }

                    if (var24.sideHit == 5) {
                        ++var25;
                    }

                    if (var2.getBlock(var25, var26, var27) == Blocks.air || !var2.getBlock(var25, var26, var27)
                        .getMaterial()
                        .isSolid()) {
                        var2.setBlock(var25, var26, var27, this.isFull, 0, 3);
                        return new ItemStack(AlphaItem.bucketEmpty);
                    }
                }
            } /*
               * else if(this.isFull == 0 && var24.entityHit instanceof AlphaEntityCow) {
               * return new ItemStack(AlphaItem.bucketMilk);
               * }
               */

        }
        return var1;
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        return fuel.getItem() == this ? burnTime : 0;
    }
}
