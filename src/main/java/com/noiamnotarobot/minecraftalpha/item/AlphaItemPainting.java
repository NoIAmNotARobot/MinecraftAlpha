package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.entity.AlphaEntityPainting;

public class AlphaItemPainting extends AlphaItem {

    public AlphaItemPainting() {
        super("painting");
        this.setMaxDamage(64);
    }

    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7,
        float var10, float var11, float var12) {
        if (var7 == 0) {
            return false;
        } else if (var7 == 1) {
            return false;
        } else {
            byte var8 = 0;
            if (var7 == 4) {
                var8 = 1;
            }

            if (var7 == 3) {
                var8 = 2;
            }

            if (var7 == 5) {
                var8 = 3;
            }

            AlphaEntityPainting var9 = new AlphaEntityPainting(var3, var4, var5, var6, var8);
            if (var9.onValidSurface()) {
                if (!var3.isRemote) var3.spawnEntityInWorld(var9);
                --var1.stackSize;
            }

            return true;
        }
    }
}
