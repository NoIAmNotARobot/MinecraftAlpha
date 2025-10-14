package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import com.google.common.collect.Multimap;

public class AlphaItemTool extends AlphaItem {

    private final Block[] blocksEffectiveAgainst;
    private float efficiencyOnProperMaterial = 4.0F;
    private final int damageVsEntity;
    protected int toolMaterial;

    public AlphaItemTool(String var1, int var2, int var3, Block[] var4) {
        super(var1);
        this.toolMaterial = var3;
        this.blocksEffectiveAgainst = var4;
        this.maxStackSize = 1;
        this.setMaxDamage(32 << var3);
        if (var3 == 3) {
            this.setMaxDamage(getMaxDamage() * 4);
        }

        this.efficiencyOnProperMaterial = (float) ((var3 + 1) * 2);
        this.damageVsEntity = var2 + var3;
    }

    public float getDigSpeed(ItemStack var1, Block var2, int var3) {
        for (Block block : this.getBlocksEffectiveAgainst()) {
            if (block == var2 || ForgeHooks.isToolEffective(var1, var2, var3)) {
                return this.efficiencyOnProperMaterial;
            }
        }

        return 1.0F;
    }

    @Override
    public boolean canHarvestBlock(Block var1, ItemStack var2) {
        return ForgeHooks.canToolHarvestBlock(var1, 0, var2);
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass) {
        return toolMaterial;
    }

    public Block[] getBlocksEffectiveAgainst() {
        return blocksEffectiveAgainst;
    }

    public boolean hitEntity(ItemStack var1, EntityLivingBase var2, EntityLivingBase var3) {
        var1.damageItem(2, var3);
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
            new AttributeModifier(field_111210_e, "Tool modifier", this.damageVsEntity, 0));
        return multimap;
    }

    @Override
    public int getDamage(ItemStack stack) {
        return super.getDamage(stack);
    }

    public boolean isFull3D() {
        return true;
    }
}
