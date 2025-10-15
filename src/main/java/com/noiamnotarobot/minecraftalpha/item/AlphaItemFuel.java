package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.IFuelHandler;

public class AlphaItemFuel extends AlphaItem implements IFuelHandler {

    private final int burnTime;

    public AlphaItemFuel(String name, int burnTime) {
        this(name, 64, burnTime);
    }

    public AlphaItemFuel(String name, int maxStackSize, int burnTime) {
        super(name, maxStackSize);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        return fuel.getItem() == this ? burnTime : 0;
    }
}
