package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.entity.AlphaEntitySnowball;

public class AlphaItemSnowball extends AlphaItem {

    public AlphaItemSnowball() {
        super("snowball", 16);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) --itemStack.stackSize;
        world.playSoundAtEntity(
            player,
            MinecraftAlpha.MODID + ":random.bow",
            0.5F,
            0.4F / (rand.nextFloat() * 0.4F + 0.8F));
        world.spawnEntityInWorld(new AlphaEntitySnowball(world, player));
        return itemStack;
    }
}
