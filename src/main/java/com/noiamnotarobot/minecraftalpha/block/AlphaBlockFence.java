package com.noiamnotarobot.minecraftalpha.block;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;

public class AlphaBlockFence extends BlockFence {

    protected String unlocalizedName = "fence";

    public AlphaBlockFence() {
        super(MinecraftAlpha.MODID + ":planks", Material.wood);
        this.setBlockName(unlocalizedName);
        this.setBlockTextureName(MinecraftAlpha.MODID + ":" + unlocalizedName);
        this.setCreativeTab(MinecraftAlpha.tabAlpha);
    }

    public IIcon getIcon(int side, int meta) {
        return AlphaBlock.planks.getIcon(side, meta);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        return AxisAlignedBB.getBoundingBox(var2, var3, var4, var2 + 1, (double) var3 + 1.5D, var4 + 1);
    }

    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
        return var1.getBlock(var2, var3 - 1, var4) != this && (var1.getBlock(var2, var3 - 1, var4)
            .getMaterial()
            .isSolid() && super.canPlaceBlockAt(var1, var2, var3, var4));
    }

    public String getUnlocalizedName() {
        return "tile." + MinecraftAlpha.MODID + "." + unlocalizedName;
    }

    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        return false;
    }
}
