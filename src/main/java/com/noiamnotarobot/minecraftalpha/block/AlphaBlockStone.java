package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class AlphaBlockStone extends AlphaBlock {

    public AlphaBlockStone() {
        super(Material.rock, "stone");
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(AlphaBlock.cobblestone);
    }
}
