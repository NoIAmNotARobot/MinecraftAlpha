package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;

public class AlphaBlockButton extends AlphaBlock {

    protected AlphaBlockButton() {
        super(Material.circuits, "button");
        this.setTickRandomly(true);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        return null;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return AlphaBlock.stone.getIcon(side, meta);
    }

    public int tickRate(World world) {
        return 20;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
        return var1.isBlockNormalCubeDefault(var2 - 1, var3, var4, false)
            || (var1.isBlockNormalCubeDefault(var2 + 1, var3, var4, false)
                || (var1.isBlockNormalCubeDefault(var2, var3, var4 - 1, false)
                    || var1.isBlockNormalCubeDefault(var2, var3, var4 + 1, false)));
    }

    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float subX, float subY, float subZ,
        int meta) {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        int var7 = var6 & 8;
        var6 &= 7;
        if (var5 == 2 && var1.isBlockNormalCubeDefault(var2, var3, var4 + 1, false)) {
            var6 = 4;
        }

        if (var5 == 3 && var1.isBlockNormalCubeDefault(var2, var3, var4 - 1, false)) {
            var6 = 3;
        }

        if (var5 == 4 && var1.isBlockNormalCubeDefault(var2 + 1, var3, var4, false)) {
            var6 = 2;
        }

        if (var5 == 5 && var1.isBlockNormalCubeDefault(var2 - 1, var3, var4, false)) {
            var6 = 1;
        }

        return var6 + var7;
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4) {
        if (var1.isBlockNormalCubeDefault(var2 - 1, var3, var4, false)) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, 1, 3);
        } else if (var1.isBlockNormalCubeDefault(var2 + 1, var3, var4, false)) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, 2, 3);
        } else if (var1.isBlockNormalCubeDefault(var2, var3, var4 - 1, false)) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, 3, 3);
        } else if (var1.isBlockNormalCubeDefault(var2, var3, var4 + 1, false)) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, 4, 3);
        }

        this.checkIfAttachedToBlock(var1, var2, var3, var4);
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        if (this.checkIfAttachedToBlock(var1, var2, var3, var4)) {
            int var6 = var1.getBlockMetadata(var2, var3, var4) & 7;
            boolean var7 = !var1.isBlockNormalCubeDefault(var2 - 1, var3, var4, false) && var6 == 1;

            if (!var1.isBlockNormalCubeDefault(var2 + 1, var3, var4, false) && var6 == 2) {
                var7 = true;
            }

            if (!var1.isBlockNormalCubeDefault(var2, var3, var4 - 1, false) && var6 == 3) {
                var7 = true;
            }

            if (!var1.isBlockNormalCubeDefault(var2, var3, var4 + 1, false) && var6 == 4) {
                var7 = true;
            }

            if (var7) {
                this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
                var1.setBlock(var2, var3, var4, Blocks.air);
            }
        }

    }

    private boolean checkIfAttachedToBlock(World var1, int var2, int var3, int var4) {
        if (!this.canPlaceBlockAt(var1, var2, var3, var4)) {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlock(var2, var3, var4, Blocks.air);
            return false;
        } else {
            return true;
        }
    }

    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        int var6 = var5 & 7;
        boolean var7 = (var5 & 8) > 0;
        float var8 = 6.0F / 16.0F;
        float var9 = 10.0F / 16.0F;
        float var10 = 3.0F / 16.0F;
        float var11 = 2.0F / 16.0F;
        if (var7) {
            var11 = 1.0F / 16.0F;
        }

        if (var6 == 1) {
            this.setBlockBounds(0.0F, var8, 0.5F - var10, var11, var9, 0.5F + var10);
        } else if (var6 == 2) {
            this.setBlockBounds(1.0F - var11, var8, 0.5F - var10, 1.0F, var9, 0.5F + var10);
        } else if (var6 == 3) {
            this.setBlockBounds(0.5F - var10, var8, 0.0F, 0.5F + var10, var9, var11);
        } else if (var6 == 4) {
            this.setBlockBounds(0.5F - var10, var8, 1.0F - var11, 0.5F + var10, var9, 1.0F);
        }

    }

    public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {
        this.onBlockActivated(var1, var2, var3, var4, var5, 0, 0, 0, 0);
    }

    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int side, float subX,
        float subY, float subZ) {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        int var7 = var6 & 7;
        int var8 = 8 - (var6 & 8);
        if (var8 != 0) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var7 + var8, 3);
            var1.markBlockForUpdate(var2, var3, var4);
            var1.playSoundEffect(
                (double) var2 + 0.5D,
                (double) var3 + 0.5D,
                (double) var4 + 0.5D,
                MinecraftAlpha.MODID + ":random.click",
                0.3F,
                0.6F);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
            if (var7 == 1) {
                var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
            } else if (var7 == 2) {
                var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
            } else if (var7 == 3) {
                var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
            } else if (var7 == 4) {
                var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
            } else {
                var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
            }

            var1.scheduleBlockUpdate(var2, var3, var4, this, tickRate(var1));
        }
        return true;
    }

    public void onBlockPreDestroy(World var1, int var2, int var3, int var4, int var5) {
        if ((var5 & 8) > 0) {
            var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
            int var6 = var5 & 7;
            if (var6 == 1) {
                var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
            } else if (var6 == 2) {
                var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
            } else if (var6 == 3) {
                var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
            } else if (var6 == 4) {
                var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
            } else {
                var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
            }
        }

        super.onBlockPreDestroy(var1, var2, var3, var4, var5);
    }

    public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        return ((var1.getBlockMetadata(var2, var3, var4) & 8) > 0) ? 15 : 0;
    }

    public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        if ((var6 & 8) == 0) {
            return 0;
        } else {
            int var7 = var6 & 7;
            return var7 == 5 && var5 == 1 ? 15
                : (var7 == 4 && var5 == 2 ? 15
                    : (var7 == 3 && var5 == 3 ? 15
                        : (var7 == 2 && var5 == 4 ? 15 : (var7 == 1 && var5 == 5) ? 15 : 0)));
        }
    }

    public boolean canProvidePower() {
        return true;
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        if ((var6 & 8) != 0) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6 & 7, 3);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4, this);
            int var7 = var6 & 7;
            if (var7 == 1) {
                var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
            } else if (var7 == 2) {
                var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
            } else if (var7 == 3) {
                var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
            } else if (var7 == 4) {
                var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
            } else {
                var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
            }

            var1.playSoundEffect(
                (double) var2 + 0.5D,
                (double) var3 + 0.5D,
                (double) var4 + 0.5D,
                MinecraftAlpha.MODID + ":random.click",
                0.3F,
                0.5F);
            var1.markBlockForUpdate(var2, var3, var4);
        }
    }

    public void setBlockBoundsForItemRender() {
        float var1 = 3.0F / 16.0F;
        float var2 = 2.0F / 16.0F;
        float var3 = 2.0F / 16.0F;
        this.setBlockBounds(0.5F - var1, 0.5F - var2, 0.5F - var3, 0.5F + var1, 0.5F + var2, 0.5F + var3);
    }
}
