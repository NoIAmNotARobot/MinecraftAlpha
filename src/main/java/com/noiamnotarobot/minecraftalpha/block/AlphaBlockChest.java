package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.block.entity.AlphaTileEntityChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockChest extends AlphaBlockContainer {

    private final Random random = new Random();
    @SideOnly(Side.CLIENT)
    private IIcon endIcon;
    @SideOnly(Side.CLIENT)
    private IIcon frontIcon;
    @SideOnly(Side.CLIENT)
    private IIcon frontLeftIcon;
    @SideOnly(Side.CLIENT)
    private IIcon frontRightIcon;
    @SideOnly(Side.CLIENT)
    private IIcon sideLeftIcon;
    @SideOnly(Side.CLIENT)
    private IIcon sideRightIcon;

    protected AlphaBlockChest() {
        super(Material.wood, "chest");
    }

    @Override
    public IIcon getIcon(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        if (var5 == 1) {
            return this.endIcon;
        } else if (var5 == 0) {
            return this.endIcon;
        } else {
            Block var6 = var1.getBlock(var2, var3, var4 - 1);
            Block var7 = var1.getBlock(var2, var3, var4 + 1);
            Block var8 = var1.getBlock(var2 - 1, var3, var4);
            Block var9 = var1.getBlock(var2 + 1, var3, var4);
            int var10;
            Block var11;
            Block var12;
            byte var13;
            if (var6 != this && var7 != this) {
                if (var8 != this && var9 != this) {
                    byte var14 = 3;
                    if (var6.isOpaqueCube() && !var7.isOpaqueCube()) {
                        var14 = 3;
                    }

                    if (var7.isOpaqueCube() && !var6.isOpaqueCube()) {
                        var14 = 2;
                    }

                    if (var8.isOpaqueCube() && !var9.isOpaqueCube()) {
                        var14 = 5;
                    }

                    if (var9.isOpaqueCube() && !var8.isOpaqueCube()) {
                        var14 = 4;
                    }

                    return var5 == var14 ? this.frontIcon : this.blockIcon;
                } else if (var5 != 4 && var5 != 5) {
                    var10 = 0;
                    if (var8 == this) {
                        var10 = -1;
                    }

                    var11 = var1.getBlock(var8 == this ? var2 - 1 : var2 + 1, var3, var4 - 1);
                    var12 = var1.getBlock(var8 == this ? var2 - 1 : var2 + 1, var3, var4 + 1);
                    if (var5 == 3) {
                        var10 = -1 - var10;
                    }

                    var13 = 3;
                    if ((var6.isOpaqueCube() || var11.isOpaqueCube()) && !var7.isOpaqueCube()
                        && !var12.isOpaqueCube()) {
                        var13 = 3;
                    }

                    if ((var7.isOpaqueCube() || var12.isOpaqueCube()) && !var6.isOpaqueCube()
                        && !var11.isOpaqueCube()) {
                        var13 = 2;
                    }

                    if (var10 == 0) return var5 == var13 ? this.frontRightIcon : this.sideRightIcon;
                    else return var5 == var13 ? this.frontLeftIcon : this.sideLeftIcon;
                } else {
                    return this.blockIcon;
                }
            } else if (var5 != 2 && var5 != 3) {
                var10 = 0;
                if (var6 == this) {
                    var10 = -1;
                }

                var11 = var1.getBlock(var2 - 1, var3, var6 == this ? var4 - 1 : var4 + 1);
                var12 = var1.getBlock(var2 + 1, var3, var6 == this ? var4 - 1 : var4 + 1);
                if (var5 == 4) {
                    var10 = -1 - var10;
                }

                var13 = 5;
                if ((var8.isOpaqueCube() || var11.isOpaqueCube()) && !var9.isOpaqueCube() && !var12.isOpaqueCube()) {
                    var13 = 5;
                }

                if ((var9.isOpaqueCube() || var12.isOpaqueCube()) && !var8.isOpaqueCube() && !var11.isOpaqueCube()) {
                    var13 = 4;
                }

                if (var10 == 0) return var5 == var13 ? this.frontRightIcon : this.sideRightIcon;
                else return var5 == var13 ? this.frontLeftIcon : this.sideLeftIcon;
            } else {
                return this.blockIcon;
            }
        }
    }

    public IIcon getIcon(int var1, int var2) {
        return var1 == 1 ? this.endIcon : (var1 == 0 ? this.endIcon : (var1 == 3 ? this.frontIcon : this.blockIcon));
    }

    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
        int var5 = 0;
        if (var1.getBlock(var2 - 1, var3, var4) == this) {
            ++var5;
        }

        if (var1.getBlock(var2 + 1, var3, var4) == this) {
            ++var5;
        }

        if (var1.getBlock(var2, var3, var4 - 1) == this) {
            ++var5;
        }

        if (var1.getBlock(var2, var3, var4 + 1) == this) {
            ++var5;
        }

        return var5 <= 1 && (!this.isThereANeighborChest(var1, var2 - 1, var3, var4)
            && (!this.isThereANeighborChest(var1, var2 + 1, var3, var4)
                && (!this.isThereANeighborChest(var1, var2, var3, var4 - 1)
                    && !this.isThereANeighborChest(var1, var2, var3, var4 + 1))));
    }

    private boolean isThereANeighborChest(World var1, int var2, int var3, int var4) {
        return var1.getBlock(var2, var3, var4) == this
            && (var1.getBlock(var2 - 1, var3, var4) == this || (var1.getBlock(var2 + 1, var3, var4) == this
                || (var1.getBlock(var2, var3, var4 - 1) == this || var1.getBlock(var2, var3, var4 + 1) == this)));
    }

    public void onBlockPreDestroy(World var1, int var2, int var3, int var4, int meta) {
        AlphaTileEntityChest var5 = (AlphaTileEntityChest) var1.getTileEntity(var2, var3, var4);

        for (int var6 = 0; var6 < var5.getSizeInventory(); ++var6) {
            ItemStack var7 = var5.getStackInSlot(var6);
            if (var7 != null) {
                float var8 = this.random.nextFloat() * 0.8F + 0.1F;
                float var9 = this.random.nextFloat() * 0.8F + 0.1F;
                float var10 = this.random.nextFloat() * 0.8F + 0.1F;

                while (var7.stackSize > 0) {
                    int var11 = this.random.nextInt(21) + 10;
                    if (var11 > var7.stackSize) {
                        var11 = var7.stackSize;
                    }

                    var7.stackSize -= var11;
                    EntityItem var12 = new EntityItem(
                        var1,
                        (float) var2 + var8,
                        (float) var3 + var9,
                        (float) var4 + var10,
                        new ItemStack(var7.getItem(), var11, var7.getItemDamage()));
                    float var13 = 0.05F;
                    var12.motionX = (float) this.random.nextGaussian() * var13;
                    var12.motionY = (float) this.random.nextGaussian() * var13 + 0.2F;
                    var12.motionZ = (float) this.random.nextGaussian() * var13;
                    var1.spawnEntityInWorld(var12);
                }
            }
        }

        super.onBlockPreDestroy(var1, var2, var3, var4, meta);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        Object var6 = world.getTileEntity(x, y, z);
        if (world.isBlockNormalCubeDefault(x, y + 1, z, true)) {
            return true;
        } else if (world.getBlock(x - 1, y, z) == this && world.isBlockNormalCubeDefault(x - 1, y + 1, z, true)) {
            return true;
        } else if (world.getBlock(x + 1, y, z) == this && world.isBlockNormalCubeDefault(x + 1, y + 1, z, true)) {
            return true;
        } else if (world.getBlock(x, y, z - 1) == this && world.isBlockNormalCubeDefault(x, y + 1, z - 1, true)) {
            return true;
        } else if (world.getBlock(x, y, z + 1) == this && world.isBlockNormalCubeDefault(x, y + 1, z + 1, true)) {
            return true;
        } else {
            if (world.getBlock(x - 1, y, z) == this) {
                var6 = new InventoryLargeChest(
                    "Large chest",
                    (AlphaTileEntityChest) world.getTileEntity(x - 1, y, z),
                    (IInventory) var6);
            }

            if (world.getBlock(x + 1, y, z) == this) {
                var6 = new InventoryLargeChest(
                    "Large chest",
                    (IInventory) var6,
                    (AlphaTileEntityChest) world.getTileEntity(x + 1, y, z));
            }

            if (world.getBlock(x, y, z - 1) == this) {
                var6 = new InventoryLargeChest(
                    "Large chest",
                    (AlphaTileEntityChest) world.getTileEntity(x, y, z - 1),
                    (IInventory) var6);
            }

            if (world.getBlock(x, y, z + 1) == this) {
                var6 = new InventoryLargeChest(
                    "Large chest",
                    (IInventory) var6,
                    (AlphaTileEntityChest) world.getTileEntity(x, y, z + 1));
            }

            player.displayGUIChest((IInventory) var6);
            return true;
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        endIcon = reg.registerIcon(this.getTextureName() + "_end");
        blockIcon = reg.registerIcon(this.getTextureName() + "_side");
        frontIcon = reg.registerIcon(this.getTextureName() + "_front");
        frontLeftIcon = reg.registerIcon(this.getTextureName() + "_front_left");
        frontRightIcon = reg.registerIcon(this.getTextureName() + "_front_right");
        sideLeftIcon = reg.registerIcon(this.getTextureName() + "_side_left");
        sideRightIcon = reg.registerIcon(this.getTextureName() + "_side_right");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new AlphaTileEntityChest();
    }
}
