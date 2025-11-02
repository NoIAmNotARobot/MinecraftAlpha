package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

public class AlphaBlockRedstoneWire extends AlphaBlock {

    private boolean wiresProvidePower = true;

    public AlphaBlockRedstoneWire() {
        super(Material.circuits, "redstoneWire", false);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F / 16.0F, 1.0F);
    }

    /*
     * public int getBlockTextureFromSideAndMetadata(int var1, int var2) {
     * return this.blockIndexInTexture + (var2 > 0 ? 16 : 0);
     * }
     */

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 5;
    }

    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
        return var1.isBlockNormalCubeDefault(var2, var3 - 1, var4, false);
    }

    private void updateAndPropagateCurrentStrength(World var1, int var2, int var3, int var4) {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        int var6 = 0;
        this.wiresProvidePower = false;
        boolean var7 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4);
        this.wiresProvidePower = true;
        int var8;
        int var9;
        int var10;
        if (var7) {
            var6 = 15;
        } else {
            for (var8 = 0; var8 < 4; ++var8) {
                var9 = var2;
                var10 = var4;
                if (var8 == 0) {
                    var9 = var2 - 1;
                }

                if (var8 == 1) {
                    ++var9;
                }

                if (var8 == 2) {
                    var10 = var4 - 1;
                }

                if (var8 == 3) {
                    ++var10;
                }

                var6 = this.getMaxCurrentStrength(var1, var9, var3, var10, var6);
                if (var1.isBlockNormalCubeDefault(var9, var3, var10, false)
                    && !var1.isBlockNormalCubeDefault(var2, var3 + 1, var4, false)) {
                    var6 = this.getMaxCurrentStrength(var1, var9, var3 + 1, var10, var6);
                } else if (!var1.isBlockNormalCubeDefault(var9, var3, var10, false)) {
                    var6 = this.getMaxCurrentStrength(var1, var9, var3 - 1, var10, var6);
                }
            }

            if (var6 > 0) {
                --var6;
            } else {
                var6 = 0;
            }
        }

        if (var5 != var6) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 3);
            var1.markBlockForUpdate(var2, var3, var4);
            if (var6 > 0) {
                --var6;
            }

            for (var8 = 0; var8 < 4; ++var8) {
                var9 = var2;
                var10 = var4;
                int var11 = var3 - 1;
                if (var8 == 0) {
                    var9 = var2 - 1;
                }

                if (var8 == 1) {
                    ++var9;
                }

                if (var8 == 2) {
                    var10 = var4 - 1;
                }

                if (var8 == 3) {
                    ++var10;
                }

                if (var1.isBlockNormalCubeDefault(var9, var3, var10, false)) {
                    var11 += 2;
                }

                int var12 = this.getMaxCurrentStrength(var1, var9, var3, var10, -1);
                if (var12 >= 0 && var12 != var6) {
                    this.updateAndPropagateCurrentStrength(var1, var9, var3, var10);
                }

                var12 = this.getMaxCurrentStrength(var1, var9, var11, var10, -1);
                if (var12 >= 0 && var12 != var6) {
                    this.updateAndPropagateCurrentStrength(var1, var9, var11, var10);
                }
            }

            if (var5 == 0 || var6 == 0) {
                var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
                var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
                var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
                var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
                var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
                var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
                var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
            }
        }

    }

    private void notifyWireNeighborsOfNeighborChange(World var1, int var2, int var3, int var4) {
        if (var1.getBlock(var2, var3, var4) == this) {
            var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
            var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
            var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
            var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
            var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
        }
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4) {
        super.onBlockAdded(var1, var2, var3, var4);
        this.updateAndPropagateCurrentStrength(var1, var2, var3, var4);
        var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
        var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
        this.notifyWireNeighborsOfNeighborChange(var1, var2 - 1, var3, var4);
        this.notifyWireNeighborsOfNeighborChange(var1, var2 + 1, var3, var4);
        this.notifyWireNeighborsOfNeighborChange(var1, var2, var3, var4 - 1);
        this.notifyWireNeighborsOfNeighborChange(var1, var2, var3, var4 + 1);
        if (var1.isBlockNormalCubeDefault(var2 - 1, var3, var4, false)) {
            this.notifyWireNeighborsOfNeighborChange(var1, var2 - 1, var3 + 1, var4);
        } else {
            this.notifyWireNeighborsOfNeighborChange(var1, var2 - 1, var3 - 1, var4);
        }

        if (var1.isBlockNormalCubeDefault(var2 + 1, var3, var4, false)) {
            this.notifyWireNeighborsOfNeighborChange(var1, var2 + 1, var3 + 1, var4);
        } else {
            this.notifyWireNeighborsOfNeighborChange(var1, var2 + 1, var3 - 1, var4);
        }

        if (var1.isBlockNormalCubeDefault(var2, var3, var4 - 1, false)) {
            this.notifyWireNeighborsOfNeighborChange(var1, var2, var3 + 1, var4 - 1);
        } else {
            this.notifyWireNeighborsOfNeighborChange(var1, var2, var3 - 1, var4 - 1);
        }

        if (var1.isBlockNormalCubeDefault(var2, var3, var4 + 1, false)) {
            this.notifyWireNeighborsOfNeighborChange(var1, var2, var3 + 1, var4 + 1);
        } else {
            this.notifyWireNeighborsOfNeighborChange(var1, var2, var3 - 1, var4 + 1);
        }

    }

    public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int meta) {
        super.onBlockDestroyedByPlayer(var1, var2, var3, var4, meta);
        var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
        var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
        this.updateAndPropagateCurrentStrength(var1, var2, var3, var4);
        this.notifyWireNeighborsOfNeighborChange(var1, var2 - 1, var3, var4);
        this.notifyWireNeighborsOfNeighborChange(var1, var2 + 1, var3, var4);
        this.notifyWireNeighborsOfNeighborChange(var1, var2, var3, var4 - 1);
        this.notifyWireNeighborsOfNeighborChange(var1, var2, var3, var4 + 1);
        if (var1.isBlockNormalCubeDefault(var2 - 1, var3, var4, false)) {
            this.notifyWireNeighborsOfNeighborChange(var1, var2 - 1, var3 + 1, var4);
        } else {
            this.notifyWireNeighborsOfNeighborChange(var1, var2 - 1, var3 - 1, var4);
        }

        if (var1.isBlockNormalCubeDefault(var2 + 1, var3, var4, false)) {
            this.notifyWireNeighborsOfNeighborChange(var1, var2 + 1, var3 + 1, var4);
        } else {
            this.notifyWireNeighborsOfNeighborChange(var1, var2 + 1, var3 - 1, var4);
        }

        if (var1.isBlockNormalCubeDefault(var2, var3, var4 - 1, false)) {
            this.notifyWireNeighborsOfNeighborChange(var1, var2, var3 + 1, var4 - 1);
        } else {
            this.notifyWireNeighborsOfNeighborChange(var1, var2, var3 - 1, var4 - 1);
        }

        if (var1.isBlockNormalCubeDefault(var2, var3, var4 + 1, false)) {
            this.notifyWireNeighborsOfNeighborChange(var1, var2, var3 + 1, var4 + 1);
        } else {
            this.notifyWireNeighborsOfNeighborChange(var1, var2, var3 - 1, var4 + 1);
        }

    }

    private int getMaxCurrentStrength(World var1, int var2, int var3, int var4, int var5) {
        if (var1.getBlock(var2, var3, var4) != this) {
            return var5;
        } else {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            return Math.max(var6, var5);
        }
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        boolean var7 = this.canPlaceBlockAt(var1, var2, var3, var4);
        if (!var7) {
            this.dropBlockAsItem(var1, var2, var3, var4, var6, 0);
            var1.setBlock(var2, var3, var4, Blocks.air);
        } else {
            this.updateAndPropagateCurrentStrength(var1, var2, var3, var4);
        }

        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
    }

    public Item getItemDropped(int meta, Random random, int fortune) {
        return AlphaItem.redstone;
    }

    public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        return (this.wiresProvidePower && this.isProvidingWeakPower(var1, var2, var3, var4, var5) != 0) ? 15 : 0;
    }

    public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        if (!this.wiresProvidePower) {
            return 0;
        } else if (var1.getBlockMetadata(var2, var3, var4) == 0) {
            return 0;
        } else if (var5 == 1) {
            return 0;
        } else {
            boolean var6 = isPowerProviderOrWire(var1, var2 - 1, var3, var4) || !var1.getBlock(var2 - 1, var3, var4)
                .isBlockNormalCube() && isPowerProviderOrWire(var1, var2 - 1, var3 - 1, var4);
            boolean var7 = isPowerProviderOrWire(var1, var2 + 1, var3, var4) || !var1.getBlock(var2 + 1, var3, var4)
                .isBlockNormalCube() && isPowerProviderOrWire(var1, var2 + 1, var3 - 1, var4);
            boolean var8 = isPowerProviderOrWire(var1, var2, var3, var4 - 1) || !var1.getBlock(var2, var3, var4 - 1)
                .isBlockNormalCube() && isPowerProviderOrWire(var1, var2, var3 - 1, var4 - 1);
            boolean var9 = isPowerProviderOrWire(var1, var2, var3, var4 + 1) || !var1.getBlock(var2, var3, var4 + 1)
                .isBlockNormalCube() && isPowerProviderOrWire(var1, var2, var3 - 1, var4 + 1);
            if (!var1.getBlock(var2, var3 + 1, var4)
                .isBlockNormalCube()) {
                if (var1.getBlock(var2 - 1, var3, var4)
                    .isBlockNormalCube() && isPowerProviderOrWire(var1, var2 - 1, var3 + 1, var4)) {
                    var6 = true;
                }

                if (var1.getBlock(var2 + 1, var3, var4)
                    .isBlockNormalCube() && isPowerProviderOrWire(var1, var2 + 1, var3 + 1, var4)) {
                    var7 = true;
                }

                if (var1.getBlock(var2, var3, var4 - 1)
                    .isBlockNormalCube() && isPowerProviderOrWire(var1, var2, var3 + 1, var4 - 1)) {
                    var8 = true;
                }

                if (var1.getBlock(var2, var3, var4 + 1)
                    .isBlockNormalCube() && isPowerProviderOrWire(var1, var2, var3 + 1, var4 + 1)) {
                    var9 = true;
                }
            }

            return (!var8 && !var7 && !var6 && !var9 && var5 >= 2 && var5 <= 5
                || (var5 == 2 && var8 && !var6 && !var7 || (var5 == 3 && var9 && !var6 && !var7
                    || (var5 == 4 && var6 && !var8 && !var9 || var5 == 5 && var7 && !var8 && !var9)))) ? 15 : 0;
        }
    }

    public boolean canProvidePower() {
        return this.wiresProvidePower;
    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
        if (var1.getBlockMetadata(var2, var3, var4) > 0) {
            double var6 = (double) var2 + 0.5D + ((double) var5.nextFloat() - 0.5D) * 0.2D;
            double var8 = (float) var3 + 1.0F / 16.0F;
            double var10 = (double) var4 + 0.5D + ((double) var5.nextFloat() - 0.5D) * 0.2D;
            var1.spawnParticle("reddust", var6, var8, var10, 0.0D, 0.0D, 0.0D);
        }

    }

    public static boolean isPowerProviderOrWire(IBlockAccess var0, int var1, int var2, int var3) {
        Block var4 = var0.getBlock(var1, var2, var3);
        return var4 == AlphaBlock.redstoneWire || (var4 != Blocks.air && var4.canProvidePower());
    }
}
