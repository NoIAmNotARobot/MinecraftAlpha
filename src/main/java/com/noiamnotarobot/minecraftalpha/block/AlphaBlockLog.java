package com.noiamnotarobot.minecraftalpha.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockLog extends AlphaBlock {

    @SideOnly(Side.CLIENT)
    private IIcon endIcon;

    public AlphaBlockLog() {
        super(Material.wood, "wood");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? this.endIcon : (side == 0 ? this.endIcon : this.blockIcon);
    }

    @Override
    public IIcon getIcon(IBlockAccess worldIn, int x, int y, int z, int side) {
        return getIcon(side, 0);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTextureName() + "_side");
        this.endIcon = reg.registerIcon(this.getTextureName() + "_end");
    }
}
