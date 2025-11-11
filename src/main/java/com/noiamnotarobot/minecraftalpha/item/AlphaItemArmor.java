package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.EnumHelper;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;

public class AlphaItemArmor extends ItemArmor {

    private static final int[] maxDamageArray = new int[] { 11, 16, 15, 13 };
    private static final int[] damageReduceAmountArray = new int[] { 3, 8, 6, 3 };

    public static final ItemArmor.ArmorMaterial MATERIAL = EnumHelper
        .addArmorMaterial("alpha_armor", 0, damageReduceAmountArray, 0);

    private final String unlocalizedName;
    public final int armorLevel;

    public AlphaItemArmor(String name, int armorLevel, int renderIndex, int armorType) {
        super(MATERIAL, renderIndex, armorType);
        this.setCreativeTab(MinecraftAlpha.tabAlpha);
        this.unlocalizedName = name;
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(MinecraftAlpha.MODID + ":" + name);

        this.armorLevel = armorLevel;
        this.setMaxDamage(maxDamageArray[armorType] * 3 << armorLevel);
    }

    @Override
    public String getUnlocalizedName() {
        return "item." + MinecraftAlpha.MODID + "." + unlocalizedName;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName();
    }

    @Override
    public String getUnlocalizedNameInefficiently(ItemStack stack) {
        String s = getUnlocalizedName();
        return s == null ? "" : StatCollector.translateToLocal(s);
    }
}
