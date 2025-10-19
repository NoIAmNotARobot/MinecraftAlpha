package com.noiamnotarobot.minecraftalpha.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class AlphaBlockLadder extends AlphaBlock {

    protected AlphaBlockLadder() {
        super(Material.circuits, "ladder");
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        float var6 = 2.0F / 16.0F;
        if (var5 == 2) {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - var6, 1.0F, 1.0F, 1.0F);
        }

        if (var5 == 3) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var6);
        }

        if (var5 == 4) {
            this.setBlockBounds(1.0F - var6, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (var5 == 5) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, var6, 1.0F, 1.0F);
        }

        return super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        float var6 = 2.0F / 16.0F;
        if (var5 == 2) {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - var6, 1.0F, 1.0F, 1.0F);
        }

        if (var5 == 3) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var6);
        }

        if (var5 == 4) {
            this.setBlockBounds(1.0F - var6, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (var5 == 5) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, var6, 1.0F, 1.0F);
        }

        return super.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 8;
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
        if ((var6 == 0 || var5 == 2) && var1.isBlockNormalCubeDefault(var2, var3, var4 + 1, false)) {
            var6 = 2;
        }

        if ((var6 == 0 || var5 == 3) && var1.isBlockNormalCubeDefault(var2, var3, var4 - 1, false)) {
            var6 = 3;
        }

        if ((var6 == 0 || var5 == 4) && var1.isBlockNormalCubeDefault(var2 + 1, var3, var4, false)) {
            var6 = 4;
        }

        if ((var6 == 0 || var5 == 5) && var1.isBlockNormalCubeDefault(var2 - 1, var3, var4, false)) {
            var6 = 5;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 3);
        return var6;
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        boolean var7 = false;
        if (var6 == 2 && var1.isBlockNormalCubeDefault(var2, var3, var4 + 1, false)) {
            var7 = true;
        }

        if (var6 == 3 && var1.isBlockNormalCubeDefault(var2, var3, var4 - 1, false)) {
            var7 = true;
        }

        if (var6 == 4 && var1.isBlockNormalCubeDefault(var2 + 1, var3, var4, false)) {
            var7 = true;
        }

        if (var6 == 5 && var1.isBlockNormalCubeDefault(var2 - 1, var3, var4, false)) {
            var7 = true;
        }

        if (!var7) {
            this.dropBlockAsItem(var1, var2, var3, var4, var6, 0);
            var1.setBlock(var2, var3, var4, Blocks.air);
        }

        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
    }
}
