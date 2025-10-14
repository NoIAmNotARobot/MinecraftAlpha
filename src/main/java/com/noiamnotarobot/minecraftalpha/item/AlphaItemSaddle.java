package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class AlphaItemSaddle extends AlphaItem {

    public AlphaItemSaddle() {
        super("saddle", 1);
        this.setMaxDamage(64);
    }

    public boolean saddleEntity(ItemStack var1, EntityLivingBase var2) {
        /*
         * if(var2 instanceof AlphaEntityPig) {
         * AlphaEntityPig var3 = (AlphaEntityPig)var2;
         * if(!var3.saddled) {
         * var3.saddled = true;
         * --var1.stackSize;
         * return true;
         * }
         * }
         */
        return false;
    }

    @Override
    public boolean hitEntity(ItemStack var1, EntityLivingBase var2, EntityLivingBase var3) {
        return this.saddleEntity(var1, var2);
    }
}
