package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

public class AlphaBlockRedstoneOre extends AlphaBlock {

    private final boolean glowing;

    public AlphaBlockRedstoneOre(String name, boolean glowing) {
        super(Material.rock, name, !glowing);
        this.glowing = glowing;
        this.setTickRandomly(glowing);
        this.setBlockTextureName(MinecraftAlpha.MODID + ":oreRedstone");
    }

    public int tickRate(World world) {
        return 30;
    }

    public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {
        this.glow(var1, var2, var3, var4);
        super.onBlockClicked(var1, var2, var3, var4, var5);
    }

    public void onEntityWalking(World var1, int var2, int var3, int var4, Entity var5) {
        this.glow(var1, var2, var3, var4);
        super.onEntityWalking(var1, var2, var3, var4, var5);
    }

    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int side, float subX,
        float subY, float subZ) {
        this.glow(var1, var2, var3, var4);
        return super.onBlockActivated(var1, var2, var3, var4, var5, side, subX, subY, subZ);
    }

    private void glow(World var1, int var2, int var3, int var4) {
        this.sparkle(var1, var2, var3, var4);
        if (this == AlphaBlock.oreRedstone) {
            var1.setBlock(var2, var3, var4, AlphaBlock.oreRedstoneGlowing);
        }

    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        if (this == AlphaBlock.oreRedstoneGlowing) {
            var1.setBlock(var2, var3, var4, AlphaBlock.oreRedstone);
        }

    }

    public Item getItemDropped(int meta, Random random, int fortune) {
        return AlphaItem.redstone;
    }

    public int quantityDropped(Random var1) {
        return 4 + var1.nextInt(2);
    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
        if (this.glowing) this.sparkle(var1, var2, var3, var4);
    }

    private void sparkle(World var1, int var2, int var3, int var4) {
        Random var5 = var1.rand;
        double var6 = 1.0D / 16.0D;

        for (int var8 = 0; var8 < 6; ++var8) {
            double var9 = (float) var2 + var5.nextFloat();
            double var11 = (float) var3 + var5.nextFloat();
            double var13 = (float) var4 + var5.nextFloat();
            if (var8 == 0 && !var1.isBlockNormalCubeDefault(var2, var3 + 1, var4, true)) {
                var11 = (double) (var3 + 1) + var6;
            }

            if (var8 == 1 && !var1.isBlockNormalCubeDefault(var2, var3 - 1, var4, true)) {
                var11 = (double) (var3) - var6;
            }

            if (var8 == 2 && !var1.isBlockNormalCubeDefault(var2, var3, var4 + 1, true)) {
                var13 = (double) (var4 + 1) + var6;
            }

            if (var8 == 3 && !var1.isBlockNormalCubeDefault(var2, var3, var4 - 1, true)) {
                var13 = (double) (var4) - var6;
            }

            if (var8 == 4 && !var1.isBlockNormalCubeDefault(var2 + 1, var3, var4, true)) {
                var9 = (double) (var2 + 1) + var6;
            }

            if (var8 == 5 && !var1.isBlockNormalCubeDefault(var2 - 1, var3, var4, true)) {
                var9 = (double) (var2) - var6;
            }

            if (var9 < (double) var2 || var9 > (double) (var2 + 1)
                || var11 < 0.0D
                || var11 > (double) (var3 + 1)
                || var13 < (double) var4
                || var13 > (double) (var4 + 1)) {
                var1.spawnParticle("reddust", var9, var11, var13, 0.0D, 0.0D, 0.0D);
            }
        }

    }
}
