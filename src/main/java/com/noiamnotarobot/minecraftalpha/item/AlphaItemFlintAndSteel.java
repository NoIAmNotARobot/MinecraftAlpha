package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;

public class AlphaItemFlintAndSteel extends AlphaItem {

    public AlphaItemFlintAndSteel() {
        super("striker");
        setMaxStackSize(1);
        setMaxDamage(64);
    }

    @Override
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

        Block var11 = var3.getBlock(var4, var5, var6);
        if (var11 == Blocks.air) {
            var3.playSoundEffect(
                (double) var4 + 0.5D,
                (double) var5 + 0.5D,
                (double) var6 + 0.5D,
                MinecraftAlpha.MODID + ":fire.ignite",
                1.0F,
                rand.nextFloat() * 0.4F + 0.8F);
            var3.setBlock(var4, var5, var6, Blocks.fire);
        }

        if (!var2.capabilities.isCreativeMode) var1.damageItem(1, var2);
        return true;
    }
}
