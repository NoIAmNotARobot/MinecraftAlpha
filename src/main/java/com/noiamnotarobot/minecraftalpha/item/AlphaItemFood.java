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
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        stack.stackSize--;
        player.heal(this.healAmount);
        return stack;
    }
}
