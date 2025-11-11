package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.entity.AlphaEntityTNTPrimed;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockTNT extends AlphaBlock {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon bottomIcon;

    public AlphaBlockTNT() {
        super(Material.tnt, "tnt");
    }

    public IIcon getIcon(int var1, int var2) {
        return var1 == 0 ? this.bottomIcon : (var1 == 1 ? this.topIcon : this.blockIcon);
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        if (var5 != Blocks.air && var5.canProvidePower() && var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
            this.onBlockDestroyedByPlayer(var1, var2, var3, var4, 0);
            var1.setBlockToAir(var2, var3, var4);
        }

    }

    public int quantityDropped(Random var1) {
        return 0;
    }

    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion explosion) {
        AlphaEntityTNTPrimed var5 = new AlphaEntityTNTPrimed(
            var1,
            (float) var2 + 0.5F,
            (float) var3 + 0.5F,
            (float) var4 + 0.5F);
        var5.fuse = var1.rand.nextInt(var5.fuse / 4) + var5.fuse / 8;
        var1.spawnEntityInWorld(var5);
    }

    public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5) {
        AlphaEntityTNTPrimed var6 = new AlphaEntityTNTPrimed(
            var1,
            (float) var2 + 0.5F,
            (float) var3 + 0.5F,
            (float) var4 + 0.5F);
        var1.spawnEntityInWorld(var6);
        var1.playSoundAtEntity(var6, MinecraftAlpha.MODID + ":random.fuse", 1.0F, 1.0F);
    }

    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTextureName() + "_side");
        this.topIcon = reg.registerIcon(this.getTextureName() + "_top");
        this.bottomIcon = reg.registerIcon(this.getTextureName() + "_bottom");
    }
}
