package com.noiamnotarobot.minecraftalpha.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate.Sensitivity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AlphaBlockPressurePlate extends AlphaBlock {

    private final Sensitivity triggerMobType;

    protected AlphaBlockPressurePlate(String name, Sensitivity var3) {
        super(Material.rock, name);
        this.triggerMobType = var3;
        this.setTickRandomly(true);
        float var4 = 1.0F / 16.0F;
        this.setBlockBounds(var4, 0.0F, var4, 1.0F - var4, 0.03125F, 1.0F - var4);
    }

    public int tickRate(World worldIn) {
        return 20;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
        return var1.isBlockNormalCubeDefault(var2, var3 - 1, var4, false);
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        if (!var1.isBlockNormalCubeDefault(var2, var3 - 1, var4, false)) {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlock(var2, var3, var4, Blocks.air);
        }

    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        if (var1.getBlockMetadata(var2, var3, var4) != 0) {
            this.setStateIfMobInteractsWithPlate(var1, var2, var3, var4);
        }
    }

    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
        if (var1.getBlockMetadata(var2, var3, var4) != 1) {
            this.setStateIfMobInteractsWithPlate(var1, var2, var3, var4);
        }
    }

    private void setStateIfMobInteractsWithPlate(World var1, int var2, int var3, int var4) {
        boolean var5 = var1.getBlockMetadata(var2, var3, var4) == 1;
        boolean var6 = false;
        float var7 = 2.0F / 16.0F;
        List var8 = switch (this.triggerMobType) {
            case everything -> var1.getEntitiesWithinAABBExcludingEntity(
                null,
                AxisAlignedBB.getBoundingBox(
                    (float) var2 + var7,
                    var3,
                    (float) var4 + var7,
                    (float) (var2 + 1) - var7,
                    (double) var3 + 0.25D,
                    (float) (var4 + 1) - var7));
            case mobs -> var1.getEntitiesWithinAABB(
                EntityLivingBase.class,
                AxisAlignedBB.getBoundingBox(
                    (float) var2 + var7,
                    var3,
                    (float) var4 + var7,
                    (float) (var2 + 1) - var7,
                    (double) var3 + 0.25D,
                    (float) (var4 + 1) - var7));
            case players -> var1.getEntitiesWithinAABB(
                EntityPlayer.class,
                AxisAlignedBB.getBoundingBox(
                    (float) var2 + var7,
                    var3,
                    (float) var4 + var7,
                    (float) (var2 + 1) - var7,
                    (double) var3 + 0.25D,
                    (float) (var4 + 1) - var7));
        };

        if (!var8.isEmpty()) {
            var6 = true;
        }

        if (var6 && !var5) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, 1, 3);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
            var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
            var1.markBlockForUpdate(var2, var3, var4);
            var1.playSoundEffect(
                (double) var2 + 0.5D,
                (double) var3 + 0.1D,
                (double) var4 + 0.5D,
                "random.click",
                0.3F,
                0.6F);
        }

        if (!var6 && var5) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, 0, 3);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
            var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
            var1.markBlockForUpdate(var2, var3, var4);
            var1.playSoundEffect(
                (double) var2 + 0.5D,
                (double) var3 + 0.1D,
                (double) var4 + 0.5D,
                "random.click",
                0.3F,
                0.5F);
        }

        if (var6) {
            var1.scheduleBlockUpdate(var2, var3, var4, this, tickRate(var1));
        }

    }

    public void onBlockPreDestroy(World var1, int var2, int var3, int var4, int meta) {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        if (var5 > 0) {
            var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
            var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
        }

        super.onBlockPreDestroy(var1, var2, var3, var4, meta);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
        boolean var5 = var1.getBlockMetadata(var2, var3, var4) == 1;
        float var6 = 1.0F / 16.0F;
        if (var5) {
            this.setBlockBounds(var6, 0.0F, var6, 1.0F - var6, 0.03125F, 1.0F - var6);
        } else {
            this.setBlockBounds(var6, 0.0F, var6, 1.0F - var6, 1.0F / 16.0F, 1.0F - var6);
        }
    }

    public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        return var1.getBlockMetadata(var2, var3, var4) > 0 ? 15 : 0;
    }

    public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        return (var1.getBlockMetadata(var2, var3, var4) != 0 && var5 == 1) ? 15 : 0;
    }

    public boolean canProvidePower() {
        return true;
    }

    public void setBlockBoundsForItemRender() {
        float var1 = 0.5F;
        float var2 = 2.0F / 16.0F;
        float var3 = 0.5F;
        this.setBlockBounds(0.5F - var1, 0.5F - var2, 0.5F - var3, 0.5F + var1, 0.5F + var2, 0.5F + var3);
    }
}
