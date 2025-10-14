package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

public class AlphaItemSword extends AlphaItem {

    private final int weaponDamage;

    public AlphaItemSword(String name, int harvestLevel) {
        super(name);
        this.maxStackSize = 1;
        this.setMaxDamage(32 << harvestLevel);
        if (harvestLevel == 3) {
            this.setMaxDamage(getMaxDamage() * 4);
        }

        this.weaponDamage = 4 + harvestLevel * 2;
    }

    @Override
    public float getDigSpeed(ItemStack var1, Block var2, int var3) {
        return 1.5F;
    }

    @Override
    public boolean hitEntity(ItemStack var1, EntityLivingBase var2, EntityLivingBase var3) {
        var1.damageItem(1, var3);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack var1, World var2, Block var3, int var4, int var5, int var6,
        EntityLivingBase var7) {
        var1.damageItem(1, var7);
        return true;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(stack);
        multimap.put(
            SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
            new AttributeModifier(field_111210_e, "Weapon modifier", this.weaponDamage, 0));
        return multimap;
    }

    public boolean isFull3D() {
        return true;
    }
}
