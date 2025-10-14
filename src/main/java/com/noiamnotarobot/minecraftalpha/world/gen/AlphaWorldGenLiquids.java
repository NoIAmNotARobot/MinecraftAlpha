package com.noiamnotarobot.minecraftalpha.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

public class AlphaWorldGenLiquids extends AlphaWorldGenerator {

    private final Block liquidBlock;

    public AlphaWorldGenLiquids(Block var1) {
        this.liquidBlock = var1;
    }

    public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
        if (var1.getBlock(var3, var4 + 1, var5) != AlphaBlock.stone) {
            return false;
        } else if (var1.getBlock(var3, var4 - 1, var5) != AlphaBlock.stone) {
            return false;
        } else
            if (var1.getBlock(var3, var4, var5) != Blocks.air && var1.getBlock(var3, var4, var5) != AlphaBlock.stone) {
                return false;
            } else {
                int var6 = 0;
                if (var1.getBlock(var3 - 1, var4, var5) == AlphaBlock.stone) {
                    ++var6;
                }

                if (var1.getBlock(var3 + 1, var4, var5) == AlphaBlock.stone) {
                    ++var6;
                }

                if (var1.getBlock(var3, var4, var5 - 1) == AlphaBlock.stone) {
                    ++var6;
                }

                if (var1.getBlock(var3, var4, var5 + 1) == AlphaBlock.stone) {
                    ++var6;
                }

                int var7 = 0;
                if (var1.getBlock(var3 - 1, var4, var5) == Blocks.air) {
                    ++var7;
                }

                if (var1.getBlock(var3 + 1, var4, var5) == Blocks.air) {
                    ++var7;
                }

                if (var1.getBlock(var3, var4, var5 - 1) == Blocks.air) {
                    ++var7;
                }

                if (var1.getBlock(var3, var4, var5 + 1) == Blocks.air) {
                    ++var7;
                }

                if (var6 == 3 && var7 == 1) {
                    var1.setBlock(var3, var4, var5, this.liquidBlock);
                }

                return true;
            }
    }
}
