package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AlphaItemFood extends AlphaItem {

    private final int healAmount;

    public AlphaItemFood(String name, int healAmount) {
        super(name, 1);
        this.healAmount = healAmount;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int var4, int var5, int var6, int var7,
        float var8, float var9, float var10) {
        stack.stackSize--;
        player.heal(this.healAmount);
        return true;
    }
}
