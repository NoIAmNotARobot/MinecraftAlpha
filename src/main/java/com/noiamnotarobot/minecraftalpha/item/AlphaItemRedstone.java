package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

public class AlphaItemRedstone extends AlphaItem {

    public AlphaItemRedstone() {
        super("redstone");
    }

    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7,
        float var8, float var9, float var10) {
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

        if (var3.getBlock(var4, var5, var6) != Blocks.air) {
            return false;
        } else {
            if (AlphaBlock.redstoneWire.canPlaceBlockAt(var3, var4, var5, var6)) {
                --var1.stackSize;
                var3.setBlock(var4, var5, var6, AlphaBlock.redstoneWire);
            }

            return true;
        }
    }
}
