package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

public class AlphaBlockReed extends AlphaBlock {

    protected AlphaBlockReed() {
        super(Material.plants, "reed", false);
        float var3 = 6.0F / 16.0F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 1.0F, 0.5F + var3);
        this.setTickRandomly(true);
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        if (var1.getBlock(var2, var3 + 1, var4) == Blocks.air) {
            int var6;
            for (var6 = 1; var1.getBlock(var2, var3 - var6, var4) == this; ++var6) {}

            if (var6 < 3) {
                int var7 = var1.getBlockMetadata(var2, var3, var4);
                if (var7 == 15) {
                    var1.setBlock(var2, var3 + 1, var4, this, 0, 3);
                    var1.setBlockMetadataWithNotify(var2, var3, var4, 0, 3);
                } else {
                    var1.setBlockMetadataWithNotify(var2, var3, var4, var7 + 1, 3);
                }
            }
        }

    }

    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
        Block var5 = var1.getBlock(var2, var3 - 1, var4);
        return var5 == this
            || ((var5 == AlphaBlock.grass || var5 == AlphaBlock.dirt) && (var1.getBlock(var2 - 1, var3 - 1, var4)
                .getMaterial() == Material.water
                || (var1.getBlock(var2 + 1, var3 - 1, var4)
                    .getMaterial() == Material.water
                    || (var1.getBlock(var2, var3 - 1, var4 - 1)
                        .getMaterial() == Material.water
                        || var1.getBlock(var2, var3 - 1, var4 + 1)
                            .getMaterial() == Material.water))));
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        this.checkBlockCoordValid(world, x, y, z);
    }

    protected final void checkBlockCoordValid(World var1, int var2, int var3, int var4) {
        if (!this.canBlockStay(var1, var2, var3, var4)) {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlock(var2, var3, var4, Blocks.air, 0, 3);
        }

    }

    public boolean canBlockStay(World var1, int var2, int var3, int var4) {
        return this.canPlaceBlockAt(var1, var2, var3, var4);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        return null;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return AlphaItem.reed;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 1;
    }
}
