package com.noiamnotarobot.minecraftalpha.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class AlphaWorldGenFlowers extends AlphaWorldGenerator {

    private final Block plantBlock;

    public AlphaWorldGenFlowers(Block var1) {
        this.plantBlock = var1;
    }

    public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
        for (int var6 = 0; var6 < 64; ++var6) {
            int var7 = var3 + var2.nextInt(8) - var2.nextInt(8);
            int var8 = var4 + var2.nextInt(4) - var2.nextInt(4);
            int var9 = var5 + var2.nextInt(8) - var2.nextInt(8);
            if (var1.getBlock(var7, var8, var9) == Blocks.air && (plantBlock).canBlockStay(var1, var7, var8, var9)) {
                var1.setBlock(var7, var8, var9, this.plantBlock);
            }
        }

        return true;
    }
}
