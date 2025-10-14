package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.block.Block;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

public class AlphaItemSpade extends AlphaItemTool {

    public AlphaItemSpade(String name, int material) {
        super(
            name,
            1,
            material,
            new Block[] { AlphaBlock.grass, AlphaBlock.dirt, AlphaBlock.sand, AlphaBlock.gravel, AlphaBlock.snow,
                AlphaBlock.blockSnow, AlphaBlock.blockClay });
    }
}
