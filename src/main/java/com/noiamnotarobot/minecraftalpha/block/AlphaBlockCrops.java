package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockCrops extends AlphaBlockFlower {

    @SideOnly(Side.CLIENT)
    private final IIcon[] icons = new IIcon[8];

    protected AlphaBlockCrops() {
        super("crops", false);
        setTickRandomly(true);
        float var3 = 0.5F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.25F, 0.5F + var3);
    }

    protected boolean canThisPlantGrowOnThisBlock(Block var1) {
        return var1 == AlphaBlock.tilledField;
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        super.updateTick(var1, var2, var3, var4, var5);
        if (var1.getBlockLightValue(var2, var3 + 1, var4) >= 9) {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            if (var6 < 7) {
                float var7 = this.getGrowthRate(var1, var2, var3, var4);
                if (var5.nextInt((int) (100.0F / var7)) == 0) {
                    ++var6;
                    var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 3);
                }
            }
        }

    }

    private float getGrowthRate(World var1, int var2, int var3, int var4) {
        float var5 = 1.0F;
        Block var6 = var1.getBlock(var2, var3, var4 - 1);
        Block var7 = var1.getBlock(var2, var3, var4 + 1);
        Block var8 = var1.getBlock(var2 - 1, var3, var4);
        Block var9 = var1.getBlock(var2 + 1, var3, var4);
        Block var10 = var1.getBlock(var2 - 1, var3, var4 - 1);
        Block var11 = var1.getBlock(var2 + 1, var3, var4 - 1);
        Block var12 = var1.getBlock(var2 + 1, var3, var4 + 1);
        Block var13 = var1.getBlock(var2 - 1, var3, var4 + 1);
        boolean var14 = var8 == this || var9 == this;
        boolean var15 = var6 == this || var7 == this;
        boolean var16 = var10 == this || var11 == this || var12 == this || var13 == this;

        for (int var17 = var2 - 1; var17 <= var2 + 1; ++var17) {
            for (int var18 = var4 - 1; var18 <= var4 + 1; ++var18) {
                Block var19 = var1.getBlock(var17, var3 - 1, var18);
                float var20 = 0.0F;
                if (var19 == AlphaBlock.tilledField) {
                    var20 = 1.0F;
                    if (var1.getBlockMetadata(var17, var3 - 1, var18) > 0) {
                        var20 = 3.0F;
                    }
                }

                if (var17 != var2 || var18 != var4) {
                    var20 /= 4.0F;
                }

                var5 += var20;
            }
        }

        if (var16 || var14 && var15) {
            var5 /= 2.0F;
        }

        return var5;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (meta < 0) {
            meta = 7;
        }

        return icons[meta];
    }

    public int getRenderType() {
        return 6;
    }

    public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5) {
        super.onBlockDestroyedByPlayer(var1, var2, var3, var4, var5);

        for (int var6 = 0; var6 < 3; ++var6) {
            if (var1.rand.nextInt(15) <= var5) {
                float var7 = 0.7F;
                float var8 = var1.rand.nextFloat() * var7 + (1.0F - var7) * 0.5F;
                float var9 = var1.rand.nextFloat() * var7 + (1.0F - var7) * 0.5F;
                float var10 = var1.rand.nextFloat() * var7 + (1.0F - var7) * 0.5F;
                EntityItem var11 = new EntityItem(
                    var1,
                    (float) var2 + var8,
                    (float) var3 + var9,
                    (float) var4 + var10,
                    new ItemStack(AlphaItem.seeds));
                var11.delayBeforeCanPickup = 10;
                var1.spawnEntityInWorld(var11);
            }
        }

    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return AlphaItem.wheat;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        return meta == 7 ? 1 : 0;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        for (int i = 0; i < 8; i++) {
            icons[i] = reg.registerIcon(this.getTextureName() + "_" + i);
        }
        blockIcon = icons[0];
    }
}
