package com.noiamnotarobot.minecraftalpha.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaBlockJukeBox extends AlphaBlock {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;

    protected AlphaBlockJukeBox() {
        super(Material.wood, "jukebox");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? this.topIcon : this.blockIcon;
    }

    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int side, float subX,
        float subY, float subZ) {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        if (var6 > 0) {
            this.ejectRecord(var1, var2, var3, var4, var6);
            return true;
        } else {
            return false;
        }
    }

    public void ejectRecord(World var1, int var2, int var3, int var4, int var5) {
        var1.playRecord(null, var2, var3, var4);
        var1.setBlockMetadataWithNotify(var2, var3, var4, 0, 3);
        if (var1 instanceof WorldServer) {
            Item var6 = var5 - 1 == 0 ? AlphaItem.record13 : AlphaItem.recordCat;
            float var7 = 0.7F;
            double var8 = (double) (var1.rand.nextFloat() * var7) + (double) (1.0F - var7) * 0.5D;
            double var10 = (double) (var1.rand.nextFloat() * var7) + (double) (1.0F - var7) * 0.2D + 0.6D;
            double var12 = (double) (var1.rand.nextFloat() * var7) + (double) (1.0F - var7) * 0.5D;
            EntityItem var14 = new EntityItem(
                var1,
                (double) var2 + var8,
                (double) var3 + var10,
                (double) var4 + var12,
                new ItemStack(var6));
            var14.delayBeforeCanPickup = 10;
            var1.spawnEntityInWorld(var14);
        }
    }

    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int fortune) {
        if (var5 > 0) this.ejectRecord(var1, var2, var3, var4, var5);
        super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, fortune);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTextureName() + "_side");
        this.topIcon = reg.registerIcon(this.getTextureName() + "_top");
    }
}
