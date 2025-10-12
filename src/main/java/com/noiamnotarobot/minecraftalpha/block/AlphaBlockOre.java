package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

public class AlphaBlockOre extends AlphaBlock {

    public AlphaBlockOre(String name) {
        super(Material.rock, name);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return this == AlphaBlock.oreCoal ? AlphaItem.coal
            : (this == AlphaBlock.oreDiamond ? AlphaItem.diamond : Item.getItemFromBlock(this));
    }
}
