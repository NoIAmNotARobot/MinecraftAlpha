package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AlphaBlockStairs extends AlphaBlock {

    private final Block modelBlock;

    protected AlphaBlockStairs(String name, Block var2) {
        super(var2.getMaterial(), name);
        this.modelBlock = var2;
        this.setHardness(var2.getBlockHardness(null, 0, 0, 0));
        this.setResistance(var2.getExplosionResistance(null) / 3.0F);
        this.setStepSound(var2.stepSound);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return modelBlock.getIcon(side, meta);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 10;
    }

    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        return super.shouldSideBeRendered(var1, var2, var3, var4, var5);
    }

    /*
     * public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
     * int var7 = var1.getBlockMetadata(var2, var3, var4);
     * if (var7 == 0) {
     * this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
     * super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
     * this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
     * super.getCollidingBoundingBoxes(var1, var2, var3, var4);
     * } else if (var7 == 1) {
     * this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
     * super.getCollidingBoundingBoxes(var1, var2, var3, var4);
     * this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
     * super.getCollidingBoundingBoxes(var1, var2, var3, var4);
     * } else if (var7 == 2) {
     * this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F);
     * super.getCollidingBoundingBoxes(var1, var2, var3, var4);
     * this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
     * super.getCollidingBoundingBoxes(var1, var2, var3, var4);
     * } else if (var7 == 3) {
     * this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
     * super.getCollidingBoundingBoxes(var1, var2, var3, var4);
     * this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
     * super.getCollidingBoundingBoxes(var1, var2, var3, var4);
     * }
     * this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
     * }
     */

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        if (!var1.isRemote) {
            if (var1.getBlock(var2, var3 + 1, var4)
                .getMaterial()
                .isSolid()) {
                var1.setBlock(var2, var3, var4, this.modelBlock);
            } else {
                this.updateState(var1, var2, var3, var4);
                this.updateState(var1, var2 + 1, var3 - 1, var4);
                this.updateState(var1, var2 - 1, var3 - 1, var4);
                this.updateState(var1, var2, var3 - 1, var4 - 1);
                this.updateState(var1, var2, var3 - 1, var4 + 1);
                this.updateState(var1, var2 + 1, var3 + 1, var4);
                this.updateState(var1, var2 - 1, var3 + 1, var4);
                this.updateState(var1, var2, var3 + 1, var4 - 1);
                this.updateState(var1, var2, var3 + 1, var4 + 1);
            }
            this.modelBlock.onNeighborBlockChange(var1, var2, var3, var4, var5);
        }
    }

    private void updateState(World var1, int var2, int var3, int var4) {
        if (this.isBlockStair(var1, var2, var3, var4)) {
            byte var5 = -1;
            if (this.isBlockStair(var1, var2 + 1, var3 + 1, var4)) {
                var5 = 0;
            }
            if (this.isBlockStair(var1, var2 - 1, var3 + 1, var4)) {
                var5 = 1;
            }
            if (this.isBlockStair(var1, var2, var3 + 1, var4 + 1)) {
                var5 = 2;
            }
            if (this.isBlockStair(var1, var2, var3 + 1, var4 - 1)) {
                var5 = 3;
            }
            if (var5 < 0) {
                if (this.isBlockSolid(var1, var2 + 1, var3, var4) && !this.isBlockSolid(var1, var2 - 1, var3, var4)) {
                    var5 = 0;
                }
                if (this.isBlockSolid(var1, var2 - 1, var3, var4) && !this.isBlockSolid(var1, var2 + 1, var3, var4)) {
                    var5 = 1;
                }
                if (this.isBlockSolid(var1, var2, var3, var4 + 1) && !this.isBlockSolid(var1, var2, var3, var4 - 1)) {
                    var5 = 2;
                }
                if (this.isBlockSolid(var1, var2, var3, var4 - 1) && !this.isBlockSolid(var1, var2, var3, var4 + 1)) {
                    var5 = 3;
                }
            }
            if (var5 < 0) {
                if (this.isBlockStair(var1, var2 - 1, var3 - 1, var4)) {
                    var5 = 0;
                }
                if (this.isBlockStair(var1, var2 + 1, var3 - 1, var4)) {
                    var5 = 1;
                }
                if (this.isBlockStair(var1, var2, var3 - 1, var4 - 1)) {
                    var5 = 2;
                }
                if (this.isBlockStair(var1, var2, var3 - 1, var4 + 1)) {
                    var5 = 3;
                }
            }
            if (var5 >= 0) {
                var1.setBlockMetadataWithNotify(var2, var3, var4, var5, 3);
            }
        }
    }

    private boolean isBlockSolid(World var1, int var2, int var3, int var4) {
        return var1.getBlock(var2, var3, var4)
            .getMaterial()
            .isSolid();
    }

    private boolean isBlockStair(World var1, int var2, int var3, int var4) {
        Block var5 = var1.getBlock(var2, var3, var4);
        return var5 != Blocks.air && var5.getRenderType() == 10;
    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
        this.modelBlock.randomDisplayTick(var1, var2, var3, var4, var5);
    }

    public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {
        this.modelBlock.onBlockClicked(var1, var2, var3, var4, var5);
    }

    public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5) {
        this.modelBlock.onBlockDestroyedByPlayer(var1, var2, var3, var4, var5);
    }

    public int getMixedBrightnessForBlock(IBlockAccess var1, int var2, int var3, int var4) {
        return this.modelBlock.getMixedBrightnessForBlock(var1, var2, var3, var4);
    }

    public float getExplosionResistance(Entity var1) {
        return this.modelBlock.getExplosionResistance(var1);
    }

    public int getRenderBlockPass() {
        return this.modelBlock.getRenderBlockPass();
    }

    public Item getItemDropped(int meta, Random random, int fortune) {
        return this.modelBlock.getItemDropped(meta, random, fortune);
    }

    public int quantityDropped(Random var1) {
        return this.modelBlock.quantityDropped(var1);
    }

    public int tickRate(World world) {
        return this.modelBlock.tickRate(world);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        return this.modelBlock.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
    }

    public void velocityToAddToEntity(World var1, int var2, int var3, int var4, Entity var5, Vec3 var6) {
        this.modelBlock.velocityToAddToEntity(var1, var2, var3, var4, var5, var6);
    }

    public boolean isCollidable() {
        return this.modelBlock.isCollidable();
    }

    public boolean canCollideCheck(int var1, boolean var2) {
        return this.modelBlock.canCollideCheck(var1, var2);
    }

    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
        return this.modelBlock.canPlaceBlockAt(var1, var2, var3, var4);
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4) {
        this.onNeighborBlockChange(var1, var2, var3, var4, Blocks.air);
        this.modelBlock.onBlockAdded(var1, var2, var3, var4);
    }

    public void onBlockPreDestroy(World var1, int var2, int var3, int var4, int meta) {
        this.modelBlock.onBlockPreDestroy(var1, var2, var3, var4, meta);
    }

    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int fortune) {
        this.modelBlock.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, fortune);
    }

    public void onEntityWalking(World var1, int var2, int var3, int var4, Entity var5) {
        this.modelBlock.onEntityWalking(var1, var2, var3, var4, var5);
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        this.modelBlock.updateTick(var1, var2, var3, var4, var5);
    }

    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int side, float subX,
        float subY, float subZ) {
        return this.modelBlock.onBlockActivated(var1, var2, var3, var4, var5, side, subX, subY, subZ);
    }

    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion explosionIn) {
        this.modelBlock.onBlockDestroyedByExplosion(var1, var2, var3, var4, explosionIn);
    }
}
