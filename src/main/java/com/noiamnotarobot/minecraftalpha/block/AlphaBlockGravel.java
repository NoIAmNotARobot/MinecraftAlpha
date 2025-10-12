package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import com.noiamnotarobot.minecraftalpha.item.AlphaItem;
import net.minecraft.item.Item;

public class AlphaBlockGravel extends AlphaBlockSand {

    public AlphaBlockGravel() {
        super("gravel");
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune) {
        return rand.nextInt(10) == 0 ? AlphaItem.flint : Item.getItemFromBlock(this);
    }
}
