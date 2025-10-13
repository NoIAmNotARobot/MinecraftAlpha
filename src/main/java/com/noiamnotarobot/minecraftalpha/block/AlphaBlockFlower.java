package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class AlphaBlockFlower extends AlphaBlock {

    protected AlphaBlockFlower(String name) {
        this(name, true);
    }

    protected AlphaBlockFlower(String name, boolean addToCreativeMenu) {
        super(Material.plants, name, addToCreativeMenu);
        // this.setTickOnLoad(true);
        float var3 = 0.2F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 3.0F, 0.5F + var3);
    }

    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
        return this.canThisPlantGrowOnThisBlock(var1.getBlock(var2, var3 - 1, var4));
    }

    protected boolean canThisPlantGrowOnThisBlock(Block var1) {
        return var1 == AlphaBlock.grass || var1 == AlphaBlock.dirt || var1 == AlphaBlock.tilledField;
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
        this.checkFlowerChange(var1, var2, var3, var4);
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        this.checkFlowerChange(var1, var2, var3, var4);
    }

    protected final void checkFlowerChange(World var1, int var2, int var3, int var4) {
        if (!this.canBlockStay(var1, var2, var3, var4)) {
            int meta = var1.getBlockMetadata(var2, var3, var4);
            this.dropBlockAsItem(
                var1,
                var2,
                var3,
                var4,
                new ItemStack(getItemDropped(meta, var1.rand, 0), quantityDropped(meta, 0, var1.rand)));
            var1.setBlock(var2, var3, var4, Blocks.air);
        }
    }

    public boolean canBlockStay(World var1, int var2, int var3, int var4) {
        return (var1.getBlockLightValue(var2, var3, var4) >= 8 || var1.canBlockSeeTheSky(var2, var3, var4))
            && this.canThisPlantGrowOnThisBlock(var1.getBlock(var2, var3 - 1, var4));
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

    public int getRenderType() {
        return 1;
    }
}
