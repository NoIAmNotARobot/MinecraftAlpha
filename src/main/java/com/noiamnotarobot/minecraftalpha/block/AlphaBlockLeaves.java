package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

// Note to self: if rendering is weird between graphics levels, mess around with icons for a bit until it happens to
// work.
public class AlphaBlockLeaves extends AlphaBlockLeavesBase {

    private int decayCounter = 0;

    public AlphaBlockLeaves() {
        super(Material.leaves, "leaves", false);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        this.decayCounter = 0;
        this.updateCurrentLeaves(world, x, y, z);
        super.onNeighborBlockChange(world, x, y, z, neighbor);
    }

    public void updateConnectedLeaves(World var1, int var2, int var3, int var4, int var5) {
        if (var1.getBlock(var2, var3, var4) == this) {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            if (var6 != 0 && var6 == var5 - 1) {
                this.updateCurrentLeaves(var1, var2, var3, var4);
            }
        }
    }

    public void updateCurrentLeaves(World var1, int var2, int var3, int var4) {
        if (this.decayCounter++ < 100) {
            int var5 = var1.getBlock(var2, var3 - 1, var4)
                .getMaterial()
                .isSolid() ? 16 : 0;
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            if (var6 == 0) {
                var6 = 1;
                var1.setBlockMetadataWithNotify(var2, var3, var4, 1, 3);
            }

            var5 = this.getConnectionStrength(var1, var2, var3 - 1, var4, var5);
            var5 = this.getConnectionStrength(var1, var2, var3, var4 - 1, var5);
            var5 = this.getConnectionStrength(var1, var2, var3, var4 + 1, var5);
            var5 = this.getConnectionStrength(var1, var2 - 1, var3, var4, var5);
            var5 = this.getConnectionStrength(var1, var2 + 1, var3, var4, var5);
            int var7 = var5 - 1;
            if (var7 < 10) {
                var7 = 1;
            }

            if (var7 != var6) {
                var1.setBlockMetadataWithNotify(var2, var3, var4, var7, 3);
                this.updateConnectedLeaves(var1, var2, var3 - 1, var4, var6);
                this.updateConnectedLeaves(var1, var2, var3 + 1, var4, var6);
                this.updateConnectedLeaves(var1, var2, var3, var4 - 1, var6);
                this.updateConnectedLeaves(var1, var2, var3, var4 + 1, var6);
                this.updateConnectedLeaves(var1, var2 - 1, var3, var4, var6);
                this.updateConnectedLeaves(var1, var2 + 1, var3, var4, var6);
            }

        }
    }

    private int getConnectionStrength(World var1, int var2, int var3, int var4, int var5) {
        Block var6 = var1.getBlock(var2, var3, var4);
        if (var6 == AlphaBlock.wood) {
            return 16;
        } else {
            if (var6 == this) {
                int var7 = var1.getBlockMetadata(var2, var3, var4);
                if (var7 != 0 && var7 > var5) {
                    return var7;
                }
            }

            return var5;
        }
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        if (var6 == 0) {
            this.decayCounter = 0;
            this.updateCurrentLeaves(var1, var2, var3, var4);
        } else if (var6 == 1) {
            this.removeLeaves(var1, var2, var3, var4);
        } else if (var5.nextInt(10) == 0) {
            this.updateCurrentLeaves(var1, var2, var3, var4);
        }

    }

    private void removeLeaves(World var1, int var2, int var3, int var4) {
        this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
        var1.setBlock(var2, var3, var4, Blocks.air);
    }

    public int quantityDropped(Random var1) {
        return var1.nextInt(20) == 0 ? 1 : 0;
    }

    /*
     * @Override
     * public Item getItemDropped(int meta, Random random, int fortune) {
     * return Item.getItemFromBlock(AlphaBlock.sapling);
     * }
     */

    public boolean isOpaqueCube() {
        return !this.graphicsLevel;
    }

    public void setGraphicsLevel(boolean var1) {
        this.graphicsLevel = var1;
    }

    public void onEntityWalking(World var1, int var2, int var3, int var4, Entity var5) {
        super.onEntityWalking(var1, var2, var3, var4, var5);
    }
}
