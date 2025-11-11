package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

public class AlphaBlockSnow extends AlphaBlock {

    public AlphaBlockSnow() {
        super(Material.snow, "snow", false);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F / 16.0F, 1.0F);
        this.setTickRandomly(true);
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
        Block var5 = var1.getBlock(var2, var3 - 1, var4);
        return var5 != Blocks.air && var5.isOpaqueCube()
            && var1.getBlock(var2, var3 - 1, var4)
                .getMaterial()
                .isSolid();
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        this.canSnowStay(var1, var2, var3, var4);
    }

    private boolean canSnowStay(World var1, int var2, int var3, int var4) {
        if (!this.canPlaceBlockAt(var1, var2, var3, var4)) {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockToAir(var2, var3, var4);
            return false;
        } else {
            return true;
        }
    }

    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
        Item var6 = AlphaItem.snowball;
        float var7 = 0.7F;
        double var8 = (double) (world.rand.nextFloat() * var7) + (double) (1.0F - var7) * 0.5D;
        double var10 = (double) (world.rand.nextFloat() * var7) + (double) (1.0F - var7) * 0.5D;
        double var12 = (double) (world.rand.nextFloat() * var7) + (double) (1.0F - var7) * 0.5D;
        EntityItem var14 = new EntityItem(
            world,
            (double) x + var8,
            (double) y + var10,
            (double) z + var12,
            new ItemStack(var6));
        var14.delayBeforeCanPickup = 10;
        world.spawnEntityInWorld(var14);
        world.setBlockToAir(x, y, z);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return AlphaItem.snowball;
    }

    public int quantityDropped(Random var1) {
        return 0;
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        if (var1.getSavedLightValue(EnumSkyBlock.Block, var2, var3, var4) > 11) {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockToAir(var2, var3, var4);
        }

    }

    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        Material var6 = var1.getBlock(var2, var3, var4)
            .getMaterial();
        return var5 == 1 || (var6 != this.blockMaterial && super.shouldSideBeRendered(var1, var2, var3, var4, var5));
    }
}
