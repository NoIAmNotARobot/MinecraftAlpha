package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.material.Material;

public class AlphaBlockGlass extends AlphaBlockBreakable {

    public AlphaBlockGlass(String name, boolean localFlag) {
        super(Material.glass, name, localFlag);
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }
}
