package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemTeleporter extends AlphaItem {

    public ItemTeleporter() {
        super("teleporter", 1);
        this.setMaxDamage(4);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        switch (player.dimension) {
            case 0 -> {
                stack.damageItem(1, player);
                player.travelToDimension(98);
            }
            case 98 -> {
                stack.damageItem(1, player);
                player.travelToDimension(0);
            }
            default -> player.addChatComponentMessage(
                new ChatComponentText("Must be in either the Overworld or Minecraft Alpha 1.1.2_01 to use this item."));
        }

        return stack;
    }
}
