package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockDoor extends AlphaBlock {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;

    protected AlphaBlockDoor(Material material, String name) {
        super(material, name, false);
        float var3 = 0.5F;
        float var4 = 1.0F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side != 0 && side != 1) {
            int var3 = this.getState(meta);
            if ((var3 == 0 || var3 == 2) ^ side <= 3) {
                return this.blockIcon;
            } else {
                int var4 = var3 / 2 + (side & 1 ^ var3);
                var4 += (meta & 4) / 4;
                int var5 = 256 - (meta & 8) * 2;
                if ((var4 & 1) != 0) {
                    var5 = -var5;
                }

                return switch (var5) {
                    case 240, -240 -> this.topIcon;
                    default -> this.blockIcon;
                };
            }
        } else {
            return this.blockIcon;
        }
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 7;
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
        return super.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
        return super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
        this.setDoorRotation(this.getState(var1.getBlockMetadata(var2, var3, var4)));
    }

    public void setDoorRotation(int var1) {
        float var2 = 3.0F / 16.0F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
        if (var1 == 0) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
        }

        if (var1 == 1) {
            this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (var1 == 2) {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
        }

        if (var1 == 3) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
        }

    }

    public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {
        this.onBlockActivated(var1, var2, var3, var4, var5, 0, 0, 0, 0);
    }

    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int side, float subX,
        float subY, float subZ) {
        if (this.getMaterial() == Material.iron) {
            return true;
        } else {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            if ((var6 & 8) != 0) {
                if (var1.getBlock(var2, var3 - 1, var4) == this) {
                    this.onBlockClicked(var1, var2, var3 - 1, var4, var5);
                }

                return true;
            } else {
                if (var1.getBlock(var2, var3 + 1, var4) == this) {
                    var1.setBlockMetadataWithNotify(var2, var3 + 1, var4, (var6 ^ 4) + 8, 3);
                }

                var1.setBlockMetadataWithNotify(var2, var3, var4, var6 ^ 4, 3);
                var1.markBlockForUpdate(var2, var3, var4);
                var1.markBlockForUpdate(var2, var3 - 1, var4);
                if (Math.random() < 0.5D) {
                    var1.playSoundEffect(
                        (double) var2 + 0.5D,
                        (double) var3 + 0.5D,
                        (double) var4 + 0.5D,
                        MinecraftAlpha.MODID + ":random.door_open",
                        1.0F,
                        var1.rand.nextFloat() * 0.1F + 0.9F);
                } else {
                    var1.playSoundEffect(
                        (double) var2 + 0.5D,
                        (double) var3 + 0.5D,
                        (double) var4 + 0.5D,
                        MinecraftAlpha.MODID + ":random.door_close",
                        1.0F,
                        var1.rand.nextFloat() * 0.1F + 0.9F);
                }

                return true;
            }
        }
    }

    public void onPoweredBlockChange(World var1, int var2, int var3, int var4, boolean var5) {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        if ((var6 & 8) != 0) {
            if (var1.getBlock(var2, var3 - 1, var4) == this) {
                this.onPoweredBlockChange(var1, var2, var3 - 1, var4, var5);
            }

        } else {
            boolean var7 = (var1.getBlockMetadata(var2, var3, var4) & 4) > 0;
            if (var7 != var5) {
                if (var1.getBlock(var2, var3 + 1, var4) == this) {
                    var1.setBlockMetadataWithNotify(var2, var3 + 1, var4, (var6 ^ 4) + 8, 3);
                }

                var1.setBlockMetadataWithNotify(var2, var3, var4, var6 ^ 4, 3);
                var1.markBlockForUpdate(var2, var3, var4);
                var1.markBlockForUpdate(var2, var3 - 1, var4);
                if (Math.random() < 0.5D) {
                    var1.playSoundEffect(
                        (double) var2 + 0.5D,
                        (double) var3 + 0.5D,
                        (double) var4 + 0.5D,
                        MinecraftAlpha.MODID + ":random.door_open",
                        1.0F,
                        var1.rand.nextFloat() * 0.1F + 0.9F);
                } else {
                    var1.playSoundEffect(
                        (double) var2 + 0.5D,
                        (double) var3 + 0.5D,
                        (double) var4 + 0.5D,
                        MinecraftAlpha.MODID + ":random.door_close",
                        1.0F,
                        var1.rand.nextFloat() * 0.1F + 0.9F);
                }

            }
        }
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        if ((var6 & 8) != 0) {
            if (var1.getBlock(var2, var3 - 1, var4) != this) {
                var1.setBlock(var2, var3, var4, Blocks.air);
            }

            if (var5 != Blocks.air && var5.canProvidePower()) {
                this.onNeighborBlockChange(var1, var2, var3 - 1, var4, var5);
            }
        } else {
            boolean var7 = false;
            if (var1.getBlock(var2, var3 + 1, var4) != this) {
                var1.setBlock(var2, var3, var4, Blocks.air);
                var7 = true;
            }

            if (!var1.isBlockNormalCubeDefault(var2, var3 - 1, var4, false)) {
                var1.setBlock(var2, var3, var4, Blocks.air);
                var7 = true;
                if (var1.getBlock(var2, var3 + 1, var4) == this) {
                    var1.setBlock(var2, var3 + 1, var4, Blocks.air);
                }
            }

            if (var7) {
                this.dropBlockAsItem(var1, var2, var3, var4, var6, 0);
            } else if (var5 != Blocks.air && var5.canProvidePower()) {
                boolean var8 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4)
                    || var1.isBlockIndirectlyGettingPowered(var2, var3 + 1, var4);
                this.onPoweredBlockChange(var1, var2, var3, var4, var8);
            }
        }

    }

    @Override
    public int quantityDropped(int var1, int var2, Random var3) {
        return (var1 & 8) != 0 ? 0 : 1;
    }

    @Override
    public Item getItemDropped(int var1, Random var2, int var3) {
        return this.getMaterial() == Material.iron ? AlphaItem.doorIron : AlphaItem.doorWood;
    }

    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6) {
        this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
        return super.collisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    public int getState(int var1) {
        return (var1 & 4) == 0 ? var1 - 1 & 3 : var1 & 3;
    }

    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
        return var3 < 127 && var1.isBlockNormalCubeDefault(var2, var3 - 1, var4, false)
            && super.canPlaceBlockAt(var1, var2, var3, var4)
            && super.canPlaceBlockAt(var1, var2, var3 + 1, var4);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTextureName() + "_bottom");
        this.topIcon = reg.registerIcon(this.getTextureName() + "_top");
    }
}
