package com.noiamnotarobot.minecraftalpha.block;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class AlphaBlockMushroom extends AlphaBlockFlower {

    protected AlphaBlockMushroom(String name) {
        super(name);
        float var3 = 0.2F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
    }

    protected boolean canThisPlantGrowOnThisBlock(Block var1) {
        return var1.isOpaqueCube();
    }

    public boolean canBlockStay(World var1, int var2, int var3, int var4) {
        return var1.getBlockLightValue(var2, var3, var4) <= 13
            && this.canThisPlantGrowOnThisBlock(var1.getBlock(var2, var3 - 1, var4));
    }
}
