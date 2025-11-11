package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockFarmland extends AlphaBlock {

    @SideOnly(Side.CLIENT)
    private IIcon topWetIcon;

    protected AlphaBlockFarmland() {
        super(Material.grass, "tilledField", false);
        setTickRandomly(true);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 15.0F / 16.0F, 1.0F);
        this.setLightOpacity(255);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        return AxisAlignedBB.getBoundingBox(var2, var3, var4, var2 + 1, var3 + 1, var4 + 1);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return side == 1 && meta > 0 ? this.topWetIcon
            : (side == 1 ? this.blockIcon : AlphaBlock.dirt.getBlockTextureFromSide(side));
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        if (var5.nextInt(5) == 0) {
            if (this.isWaterNearby(var1, var2, var3, var4)) {
                var1.setBlockMetadataWithNotify(var2, var3, var4, 7, 3);
            } else {
                int var6 = var1.getBlockMetadata(var2, var3, var4);
                if (var6 > 0) {
                    var1.setBlockMetadataWithNotify(var2, var3, var4, var6 - 1, 3);
                } else if (!this.isCropsNearby(var1, var2, var3, var4)) {
                    var1.setBlock(var2, var3, var4, AlphaBlock.dirt, 0, 3);
                }
            }
        }

    }

    public void onEntityWalking(World var1, int var2, int var3, int var4, Entity var5) {
        if (!var1.isRemote && var1.rand.nextInt(4) == 0) {
            var1.setBlock(var2, var3, var4, AlphaBlock.dirt, 0, 3);
        }

    }

    private boolean isCropsNearby(World var1, int var2, int var3, int var4) {
        byte var5 = 0;

        for (int var6 = var2 - var5; var6 <= var2 + var5; ++var6) {
            for (int var7 = var4 - var5; var7 <= var4 + var5; ++var7) {
                if (var1.getBlock(var6, var3 + 1, var7) == AlphaBlock.crops) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isWaterNearby(World var1, int var2, int var3, int var4) {
        for (int var5 = var2 - 4; var5 <= var2 + 4; ++var5) {
            for (int var6 = var3; var6 <= var3 + 1; ++var6) {
                for (int var7 = var4 - 4; var7 <= var4 + 4; ++var7) {
                    if (var1.getBlock(var5, var6, var7)
                        .getMaterial() == Material.water) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
        Material var6 = var1.getBlock(var2, var3 + 1, var4)
            .getMaterial();
        if (var6.isSolid()) {
            var1.setBlock(var2, var3, var4, AlphaBlock.dirt, 0, 3);
        }

    }

    public Item getItemDropped(int var1, Random var2, int var3) {
        return AlphaBlock.dirt.getItemDropped(0, var2, 0);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTextureName() + "_top");
        this.topWetIcon = reg.registerIcon(this.getTextureName() + "_top_wet");
    }
}
