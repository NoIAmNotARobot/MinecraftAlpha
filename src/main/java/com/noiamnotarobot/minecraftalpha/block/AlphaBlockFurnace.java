package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.block.entity.AlphaTileEntityFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockFurnace extends AlphaBlockContainer {

    private final boolean isActive;
    @SideOnly(Side.CLIENT)
    private IIcon frontIcon;

    protected AlphaBlockFurnace(String name, boolean var2, boolean addToCreativeMenu) {
        super(Material.rock, name, addToCreativeMenu);
        this.isActive = var2;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(AlphaBlock.stoneOvenIdle);
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4) {
        super.onBlockAdded(var1, var2, var3, var4);
        this.setDefaultDirection(var1, var2, var3, var4);
    }

    private void setDefaultDirection(World var1, int var2, int var3, int var4) {
        Block var5 = var1.getBlock(var2, var3, var4 - 1);
        Block var6 = var1.getBlock(var2, var3, var4 + 1);
        Block var7 = var1.getBlock(var2 - 1, var3, var4);
        Block var8 = var1.getBlock(var2 + 1, var3, var4);
        byte var9 = 3;
        if (var5.isOpaqueCube() && !var6.isOpaqueCube()) {
            var9 = 3;
        }

        if (var6.isOpaqueCube() && !var5.isOpaqueCube()) {
            var9 = 2;
        }

        if (var7.isOpaqueCube() && !var8.isOpaqueCube()) {
            var9 = 5;
        }

        if (var8.isOpaqueCube() && !var7.isOpaqueCube()) {
            var9 = 4;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var9, 3);
    }

    public IIcon getIcon(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        if (var5 == 1) {
            return AlphaBlock.stone.getBlockTextureFromSide(var5);
        } else if (var5 == 0) {
            return AlphaBlock.stone.getBlockTextureFromSide(var5);
        } else {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            return var5 != var6 ? this.blockIcon : this.frontIcon;
        }
    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
        if (this.isActive) {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            float var7 = (float) var2 + 0.5F;
            float var8 = (float) var3 + 0.0F + var5.nextFloat() * 6.0F / 16.0F;
            float var9 = (float) var4 + 0.5F;
            float var10 = 0.52F;
            float var11 = var5.nextFloat() * 0.6F - 0.3F;
            if (var6 == 4) {
                var1.spawnParticle("smoke", var7 - var10, var8, (var9 + var11), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("flame", var7 - var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
            } else if (var6 == 5) {
                var1.spawnParticle("smoke", (var7 + var10), var8, (var9 + var11), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("flame", (var7 + var10), var8, (var9 + var11), 0.0D, 0.0D, 0.0D);
            } else if (var6 == 2) {
                var1.spawnParticle("smoke", (var7 + var11), var8, (var9 - var10), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("flame", (var7 + var11), var8, (var9 - var10), 0.0D, 0.0D, 0.0D);
            } else if (var6 == 3) {
                var1.spawnParticle("smoke", (var7 + var11), var8, (var9 + var10), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("flame", (var7 + var11), var8, (var9 + var10), 0.0D, 0.0D, 0.0D);
            }

        }
    }

    public IIcon getIcon(int var1, int var2) {
        return var1 == 1 ? AlphaBlock.stone.getBlockTextureFromSide(var1)
            : (var1 == 0 ? AlphaBlock.stone.getBlockTextureFromSide(var1)
                : (var1 == 3 ? this.frontIcon : this.blockIcon));
    }

    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int side, float subX,
        float subY, float subZ) {
        AlphaTileEntityFurnace var6 = (AlphaTileEntityFurnace) var1.getTileEntity(var2, var3, var4);
        var5.func_146101_a(var6);
        return true;
    }

    public static void updateFurnaceBlockState(boolean var0, World var1, int var2, int var3, int var4) {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        TileEntity var6 = var1.getTileEntity(var2, var3, var4);
        if (var0) {
            var1.setBlock(var2, var3, var4, AlphaBlock.stoneOvenActive);
        } else {
            var1.setBlock(var2, var3, var4, AlphaBlock.stoneOvenIdle);
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var5, 3);
        var1.setTileEntity(var2, var3, var4, var6);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new AlphaTileEntityFurnace();
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTextureName() + "_side");
        this.frontIcon = reg.registerIcon(this.getTextureName() + "_front");
    }
}
