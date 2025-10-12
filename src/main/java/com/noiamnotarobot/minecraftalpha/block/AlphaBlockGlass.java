package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.material.Material;

public class AlphaBlockGlass extends AlphaBlockBreakable {

    public AlphaBlockGlass(Material materialIn, String name, boolean localFlag) {
        super(materialIn, name, localFlag);
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }
}
