package com.noiamnotarobot.minecraftalpha.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockOreBlock extends AlphaBlock {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon bottomIcon;

    public AlphaBlockOreBlock(String name) {
        super(Material.iron, name);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? this.topIcon : (side == 0 ? this.bottomIcon : this.blockIcon);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess worldIn, int x, int y, int z, int side) {
        if (side == 1) {
            return this.topIcon;
        } else if (side == 0) {
            return this.bottomIcon;
        } else {
            return this.blockIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTextureName() + "_side");
        this.topIcon = reg.registerIcon(this.getTextureName() + "_top");
        this.bottomIcon = reg.registerIcon(this.getTextureName() + "_bottom");
    }
}
