package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AlphaBlockIce extends AlphaBlockBreakable {

    public AlphaBlockIce() {
        super(Material.ice, "ice", false);
        this.slipperiness = 0.98F;
        this.setTickRandomly(true);
    }

    public int getRenderBlockPass() {
        return 1;
    }

    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        return super.shouldSideBeRendered(var1, var2, var3, var4, 1 - var5);
    }

    @Override
    public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5) {
        Material var6 = var1.getBlock(var2, var3 - 1, var4)
            .getMaterial();
        if (var6.isSolid() || var6.isLiquid()) {
            var1.setBlock(var2, var3, var4, Blocks.flowing_water);
        }

    }

    public int quantityDropped(Random var1) {
        return 0;
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        if (var1.getSavedLightValue(EnumSkyBlock.Block, var2, var3, var4) > 11 - this.lightOpacity) {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlock(var2, var3, var4, Blocks.water);
        }

    }
}
