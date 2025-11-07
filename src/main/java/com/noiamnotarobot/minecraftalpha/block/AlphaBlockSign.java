package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockSign extends AlphaBlockContainer {

    private final Class signEntityClass;
    private final boolean isFreestanding;

    protected AlphaBlockSign(String name, Class var2, boolean var3) {
        super(Material.wood, name, false);
        this.isFreestanding = var3;
        this.signEntityClass = var2;
        float var4 = 0.25F;
        float var5 = 1.0F;
        this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var5, 0.5F + var4);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return AlphaBlock.planks.getBlockTextureFromSide(side);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        return null;
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
        return super.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
        if (!this.isFreestanding) {
            int var5 = var1.getBlockMetadata(var2, var3, var4);
            float var6 = 9.0F / 32.0F;
            float var7 = 25.0F / 32.0F;
            float var8 = 0.0F;
            float var9 = 1.0F;
            float var10 = 2.0F / 16.0F;
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            if (var5 == 2) {
                this.setBlockBounds(var8, var6, 1.0F - var10, var9, var7, 1.0F);
            }

            if (var5 == 3) {
                this.setBlockBounds(var8, var6, 0.0F, var9, var7, var10);
            }

            if (var5 == 4) {
                this.setBlockBounds(1.0F - var10, var6, var8, 1.0F, var7, var9);
            }

            if (var5 == 5) {
                this.setBlockBounds(0.0F, var6, var8, var10, var7, var9);
            }

        }
    }

    public int getRenderType() {
        return -1;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        try {
            return (TileEntity) this.signEntityClass.newInstance();
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public Item getItemDropped(int meta, Random random, int fortune) {
        return AlphaItem.sign;
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        boolean var6 = false;
        if (this.isFreestanding) {
            if (!var1.getBlock(var2, var3 - 1, var4)
                .getMaterial()
                .isSolid()) {
                var6 = true;
            }
        } else {
            int var7 = var1.getBlockMetadata(var2, var3, var4);
            var6 = true;
            if (var7 == 2 && var1.getBlock(var2, var3, var4 + 1)
                .getMaterial()
                .isSolid()) {
                var6 = false;
            }

            if (var7 == 3 && var1.getBlock(var2, var3, var4 - 1)
                .getMaterial()
                .isSolid()) {
                var6 = false;
            }

            if (var7 == 4 && var1.getBlock(var2 + 1, var3, var4)
                .getMaterial()
                .isSolid()) {
                var6 = false;
            }

            if (var7 == 5 && var1.getBlock(var2 - 1, var3, var4)
                .getMaterial()
                .isSolid()) {
                var6 = false;
            }
        }

        if (var6) {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlock(var2, var3, var4, Blocks.air);
        }

        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
    }

    @Override
    public Item getItem(World worldIn, int x, int y, int z) {
        return AlphaItem.sign;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {}
}
