package com.noiamnotarobot.minecraftalpha.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockWorkbench extends AlphaBlock {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;

    public AlphaBlockWorkbench() {
        super(Material.wood, "workbench");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? topIcon
            : (side == 0 ? AlphaBlock.planks.getBlockTextureFromSide(0)
                : (side != 2 && side != 4 ? sideIcon : blockIcon));
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        player.displayGUIWorkbench(x, y, z);
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTextureName() + "_front");
        this.topIcon = reg.registerIcon(this.getTextureName() + "_top");
        this.sideIcon = reg.registerIcon(this.getTextureName() + "_side");
    }
}
