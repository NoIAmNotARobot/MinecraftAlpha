package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockGrass extends AlphaBlock {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon snowIcon;

    public AlphaBlockGrass() {
        super(Material.grass, "grass");
        this.setTickRandomly(true);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? this.topIcon : (side == 0 ? AlphaBlock.dirt.getBlockTextureFromSide(side) : this.blockIcon);
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        if (side == 1) {
            return this.topIcon;
        } else if (side == 0) {
            return AlphaBlock.dirt.getBlockTextureFromSide(side);
        } else {
            Material var6 = world.getBlock(x, y + 1, z)
                .getMaterial();
            return var6 != Material.snow && var6 != Material.craftedSnow ? this.blockIcon : this.snowIcon;
        }
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(AlphaBlock.dirt);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTextureName() + "_side");
        this.topIcon = reg.registerIcon(this.getTextureName() + "_top");
        this.snowIcon = reg.registerIcon(this.getTextureName() + "_side_snow");
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlock(x, y + 1, z)
            .getMaterial()
            .getCanBlockGrass()) {
            if (rand.nextInt(4) != 0) {
                return;
            }

            world.setBlock(x, y, z, AlphaBlock.dirt);
        } else if (world.getBlockLightValue(x, y + 1, z) >= 9) {
            int var6 = x + rand.nextInt(3) - 1;
            int var7 = y + rand.nextInt(5) - 3;
            int var8 = z + rand.nextInt(3) - 1;
            if (world.getBlock(var6, var7, var8) == AlphaBlock.dirt
                && world.getBlockLightValue(var6, var7 + 1, var8) >= 4
                && !world.getBlock(var6, var7 + 1, var8)
                    .getMaterial()
                    .getCanBlockGrass()) {
                world.setBlock(var6, var7, var8, AlphaBlock.grass);
            }
        }

    }
}
