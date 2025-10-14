package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

public class AlphaItemRecord extends AlphaItem {

    private String recordName;

    protected AlphaItemRecord(String name, String recordName) {
        super(name);
        this.recordName = recordName;
        this.maxStackSize = 1;
    }

    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7,
        float var8, float var9, float var10) {
        if (var3.getBlock(var4, var5, var6) == AlphaBlock.jukebox && var3.getBlockMetadata(var4, var5, var6) == 0) {
            var3.setBlockMetadataWithNotify(var4, var5, var6, this == AlphaItem.record13 ? 1 : 2, 3);
            var3.playRecord(this.recordName, var4, var5, var6);
            --var1.stackSize;
            return true;
        } else {
            return false;
        }
    }
}
