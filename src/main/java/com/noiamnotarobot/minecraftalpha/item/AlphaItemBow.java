package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.entity.AlphaEntityArrow;

public class AlphaItemBow extends AlphaItem {

    public AlphaItemBow() {
        super("bow", 1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
        if (var3.capabilities.isCreativeMode || var3.inventory.consumeInventoryItem(AlphaItem.arrow)) {
            if (!var2.isRemote) {
                var2.playSoundAtEntity(
                    var3,
                    MinecraftAlpha.MODID + ":random.bow",
                    1.0F,
                    1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                var2.spawnEntityInWorld(new AlphaEntityArrow(var2, var3));
            }
        }

        return var1;
    }
}
