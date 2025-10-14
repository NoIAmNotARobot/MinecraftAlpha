package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

public class AlphaItemReed extends AlphaItem {

    private final Block spawn;

    public AlphaItemReed(Block var2) {
        super("reed");
        this.spawn = var2;
    }

    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7,
        float var8, float var9, float var10) {
        if (var3.getBlock(var4, var5, var6) == AlphaBlock.snow) {
            var7 = 0;
        } else {
            if (var7 == 0) {
                --var5;
            }

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
        }

        if (var1.stackSize == 0) {
            return false;
        } else {
            if (spawn.canPlaceBlockAt(var3, var4, var5, var6)) {
                if (var3.setBlock(var4, var5, var6, spawn, 0, 3)) {
                    spawn.onBlockPlaced(var3, var4, var5, var6, var7, var8, var9, var10, 0);
                    var3.playSoundEffect(
                        (float) var4 + 0.5F,
                        (float) var5 + 0.5F,
                        (float) var6 + 0.5F,
                        spawn.stepSound.getStepResourcePath(),
                        (spawn.stepSound.getVolume() + 1.0F) / 2.0F,
                        spawn.stepSound.getPitch() * 0.8F);
                    --var1.stackSize;
                }
            }

            return true;
        }
    }
}
