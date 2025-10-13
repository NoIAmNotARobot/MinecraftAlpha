package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

public class AlphaBlockClay extends AlphaBlock {

    public AlphaBlockClay() {
        super(Material.clay, "blockClay");
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return AlphaItem.clay;
    }

    @Override
    public int quantityDropped(Random random) {
        return 4;
    }
}
