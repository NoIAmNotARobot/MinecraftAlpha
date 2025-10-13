package com.noiamnotarobot.minecraftalpha.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class AlphaBlockLeavesBase extends AlphaBlock {

    protected boolean graphicsLevel;

    public AlphaBlockLeavesBase(Material materialIn, String name, boolean graphicsLevel) {
        super(materialIn, name);
        this.graphicsLevel = graphicsLevel;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        Block var6 = world.getBlock(x, y, z);
        return (this.graphicsLevel || var6 != this) && super.shouldSideBeRendered(world, x, y, z, side);
    }
}
