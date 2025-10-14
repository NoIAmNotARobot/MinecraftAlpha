package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class AlphaBlockBookshelf extends AlphaBlock {

    public AlphaBlockBookshelf() {
        super(Material.wood, "bookshelf");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return side <= 1 ? AlphaBlock.planks.getBlockTextureFromSide(side) : this.blockIcon;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        return 0;
    }
}
