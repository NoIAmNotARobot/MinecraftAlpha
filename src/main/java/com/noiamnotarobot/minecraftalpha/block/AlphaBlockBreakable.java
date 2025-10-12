package com.noiamnotarobot.minecraftalpha.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class AlphaBlockBreakable extends AlphaBlock {

    private boolean localFlag;

    public AlphaBlockBreakable(Material materialIn, String name, boolean localFlag) {
        super(materialIn, name);
        this.localFlag = localFlag;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        Block var6 = world.getBlock(x, y, z);
        return (this.localFlag || var6 != this) && super.shouldSideBeRendered(world, x, y, z, side);
    }
}
