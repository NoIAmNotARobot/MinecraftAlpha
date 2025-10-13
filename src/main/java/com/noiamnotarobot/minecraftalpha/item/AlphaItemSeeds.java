package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

public class AlphaItemSeeds extends AlphaItem {

    private final Block blockType;

    public AlphaItemSeeds(Block blockType) {
        super("seeds");
        this.blockType = blockType;
    }

    @Override
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7,
        float var8, float var9, float var10) {
        if (var7 != 1) {
            return false;
        } else {
            Block var11 = var3.getBlock(var4, var5, var6);
            if (var11 == AlphaBlock.tilledField) {
                var3.setBlock(var4, var5 + 1, var6, this.blockType, 0, 3);
                --var1.stackSize;
                return true;
            } else {
                return false;
            }
        }
    }
}
