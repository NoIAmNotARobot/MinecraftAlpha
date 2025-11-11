package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockStep extends AlphaBlock {

    private final boolean blockType;
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;

    public AlphaBlockStep(String name, boolean var2, boolean isInCreativeMenu) {
        super(Material.rock, name, isInCreativeMenu);
        this.blockType = var2;
        if (!var2) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }

        this.setLightOpacity(255);
    }

    @Override
    public IIcon getIcon(int var1, int var2) {
        return var1 <= 1 ? blockIcon : sideIcon;
    }

    public boolean isOpaqueCube() {
        return this.blockType;
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        if (this == AlphaBlock.stairSingle) {}
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4) {
        if (this != AlphaBlock.stairSingle) {
            super.onBlockAdded(var1, var2, var3, var4);
        }

        Block var5 = var1.getBlock(var2, var3 - 1, var4);
        if (var5 == AlphaBlock.stairSingle) {
            var1.setBlockToAir(var2, var3, var4);
            var1.setBlock(var2, var3 - 1, var4, AlphaBlock.stairDouble);
        }

    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(AlphaBlock.stairSingle);
    }

    public boolean renderAsNormalBlock() {
        return this.blockType;
    }

    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        if (this != AlphaBlock.stairSingle) {
            super.shouldSideBeRendered(var1, var2, var3, var4, var5);
        }

        return var5 == 1 || (super.shouldSideBeRendered(var1, var2, var3, var4, var5)
            && (var5 == 0 || var1.getBlock(var2, var3, var4) != this));
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(MinecraftAlpha.MODID + ":stair_top");
        this.sideIcon = reg.registerIcon(MinecraftAlpha.MODID + ":stair_side");
    }
}
