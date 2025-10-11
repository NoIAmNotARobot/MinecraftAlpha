package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class AlphaBlockGravel extends AlphaBlockSand {

    public AlphaBlockGravel() {
        super("gravel");
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune) {
        return rand.nextInt(10) == 0 ? Items.flint : Item.getItemFromBlock(this);
    }
}
