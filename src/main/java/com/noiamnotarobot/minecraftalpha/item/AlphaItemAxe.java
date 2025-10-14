package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.block.Block;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

public class AlphaItemAxe extends AlphaItemTool {

    public AlphaItemAxe(String name, int harvestLevel) {
        super(
            name,
            3,
            harvestLevel,
            new Block[] { AlphaBlock.planks, AlphaBlock.bookshelf, AlphaBlock.wood, AlphaBlock.chest });
    }
}
