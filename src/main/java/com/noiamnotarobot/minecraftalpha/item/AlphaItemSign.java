package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

public class AlphaItemSign extends AlphaItem {

    public AlphaItemSign() {
        super("sign", 1);
        this.setMaxDamage(64);
    }

    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7,
        float var8, float var9, float var10) {
        if (var7 == 0) {
            return false;
        } else if (!var3.getBlock(var4, var5, var6)
            .getMaterial()
            .isSolid()) {
                return false;
            } else {
                if (var7 == 1) {
                    ++var5;
                }

                if (var7 == 2) {
                    --var6;
                }

                if (var7 == 3) {
                    ++var6;
                }

                if (var7 == 4) {
                    --var4;
                }

                if (var7 == 5) {
                    ++var4;
                }

                if (!AlphaBlock.signStanding.canPlaceBlockAt(var3, var4, var5, var6)) {
                    return false;
                } else {
                    if (var7 == 1) {
                        var3.setBlock(
                            var4,
                            var5,
                            var6,
                            AlphaBlock.signStanding,
                            MathHelper.floor_double((double) ((var2.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D)
                                & 15,
                            3);
                    } else {
                        var3.setBlock(var4, var5, var6, AlphaBlock.signWall, var7, 3);
                    }

                    --var1.stackSize;
                    var2.func_146100_a(var3.getTileEntity(var4, var5, var6));
                    return true;
                }
            }
    }
}
