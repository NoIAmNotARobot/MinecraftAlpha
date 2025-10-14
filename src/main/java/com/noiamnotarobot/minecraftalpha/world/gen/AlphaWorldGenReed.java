package com.noiamnotarobot.minecraftalpha.world.gen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

public class AlphaWorldGenReed extends AlphaWorldGenerator {

    public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
        for (int var6 = 0; var6 < 20; ++var6) {
            int var7 = var3 + var2.nextInt(4) - var2.nextInt(4);
            int var8 = var4;
            int var9 = var5 + var2.nextInt(4) - var2.nextInt(4);
            if (var1.getBlock(var7, var4, var9) == Blocks.air && (var1.getBlock(var7 - 1, var4 - 1, var9)
                .getMaterial() == Material.water
                || var1.getBlock(var7 + 1, var4 - 1, var9)
                    .getMaterial() == Material.water
                || var1.getBlock(var7, var4 - 1, var9 - 1)
                    .getMaterial() == Material.water
                || var1.getBlock(var7, var4 - 1, var9 + 1)
                    .getMaterial() == Material.water)) {
                int var10 = 2 + var2.nextInt(var2.nextInt(3) + 1);

                for (int var11 = 0; var11 < var10; ++var11) {
                    if (AlphaBlock.reed.canBlockStay(var1, var7, var8 + var11, var9)) {
                        var1.setBlock(var7, var8 + var11, var9, AlphaBlock.reed);
                    }
                }
            }
        }

        return true;
    }
}
