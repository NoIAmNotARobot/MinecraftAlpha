package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockCactus extends AlphaBlock {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon bottomIcon;

    public AlphaBlockCactus() {
        super(Material.cactus, "cactus");
        setTickRandomly(true);
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        if (var1.getBlock(var2, var3 + 1, var4) == Blocks.air) {
            int var6;
            for (var6 = 1; var1.getBlock(var2, var3 - var6, var4) == this; ++var6) {}

            if (var6 < 3) {
                int var7 = var1.getBlockMetadata(var2, var3, var4);
                if (var7 == 15) {
                    var1.setBlock(var2, var3 + 1, var4, this);
                    var1.setBlockMetadataWithNotify(var2, var3, var4, 0, 3);
                } else {
                    var1.setBlockMetadataWithNotify(var2, var3, var4, var7 + 1, 3);
                }
            }
        }

    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        float var5 = 1.0F / 16.0F;
        return AxisAlignedBB.getBoundingBox(
            (float) var2 + var5,
            var3,
            (float) var4 + var5,
            (float) (var2 + 1) - var5,
            (float) (var3 + 1) - var5,
            (float) (var4 + 1) - var5);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        float var5 = 1.0F / 16.0F;
        return AxisAlignedBB.getBoundingBox(
            (float) var2 + var5,
            var3,
            (float) var4 + var5,
            (float) (var2 + 1) - var5,
            var3 + 1,
            (float) (var4 + 1) - var5);
    }

    public IIcon getIcon(int var1, int var2) {
        return var1 == 1 ? topIcon : (var1 == 0 ? bottomIcon : blockIcon);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public int getRenderType() {
        return 13;
    }

    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
        return super.canPlaceBlockAt(var1, var2, var3, var4) && this.canBlockStay(var1, var2, var3, var4);
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        if (!this.canBlockStay(var1, var2, var3, var4)) {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlock(var2, var3, var4, Blocks.air);
        }

    }

    public boolean canBlockStay(World var1, int var2, int var3, int var4) {
        if (var1.getBlock(var2 - 1, var3, var4)
            .getMaterial()
            .isSolid()) {
            return false;
        } else if (var1.getBlock(var2 + 1, var3, var4)
            .getMaterial()
            .isSolid()) {
                return false;
            } else if (var1.getBlock(var2, var3, var4 - 1)
                .getMaterial()
                .isSolid()) {
                    return false;
                } else if (var1.getBlock(var2, var3, var4 + 1)
                    .getMaterial()
                    .isSolid()) {
                        return false;
                    } else {
                        Block var5 = var1.getBlock(var2, var3 - 1, var4);
                        return var5 == AlphaBlock.cactus || var5 == AlphaBlock.sand;
                    }
    }

    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
        var5.attackEntityFrom(DamageSource.cactus, 1);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon(this.getTextureName() + "_side");
        topIcon = reg.registerIcon(this.getTextureName() + "_top");
        bottomIcon = reg.registerIcon(this.getTextureName() + "_bottom");
    }
}
