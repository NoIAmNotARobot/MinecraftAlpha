package com.noiamnotarobot.minecraftalpha.block;

import com.noiamnotarobot.minecraftalpha.item.AlphaItem;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class AlphaBlockOre extends AlphaBlock {
    public AlphaBlockOre(String name) {
        super(Material.rock, name);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return this == AlphaBlock.oreCoal ? AlphaItem.coal : (this == AlphaBlock.oreDiamond ? AlphaItem.diamond : Item.getItemFromBlock(this));
    }
}
