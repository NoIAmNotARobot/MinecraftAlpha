package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

public class AlphaItemHoe extends AlphaItem {

    public AlphaItemHoe(String name, int harvestLevel) {
        super(name);
        this.maxStackSize = 1;
        this.setMaxDamage(32 << harvestLevel);
    }

    @Override
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7,
        float var8, float var9, float var10) {
        Block var21 = var3.getBlock(var4, var5, var6);
        if ((var3.getBlock(var4, var5 + 1, var6)
            .getMaterial()
            .isSolid() || var21 != AlphaBlock.grass) && var21 != AlphaBlock.dirt) {
            return false;
        } else {
            var3.playSoundEffect(
                (float) var4 + 0.5F,
                (float) var5 + 0.5F,
                (float) var6 + 0.5F,
                AlphaBlock.tilledField.stepSound.getStepResourcePath(),
                (AlphaBlock.tilledField.stepSound.getVolume() + 1.0F) / 2.0F,
                AlphaBlock.tilledField.stepSound.getPitch() * 0.8F);
            var3.setBlock(var4, var5, var6, AlphaBlock.tilledField);
            var1.damageItem(1, var2);
            if (!var3.isRemote && var3.rand.nextInt(8) == 0 && var21 == AlphaBlock.grass) {
                byte var11 = 1;

                for (int var12 = 0; var12 < var11; ++var12) {
                    float var13 = 0.7F;
                    float var14 = var3.rand.nextFloat() * var13 + (1.0F - var13) * 0.5F;
                    float var15 = 1.2F;
                    float var16 = var3.rand.nextFloat() * var13 + (1.0F - var13) * 0.5F;
                    EntityItem var17 = new EntityItem(
                        var3,
                        (float) var4 + var14,
                        (float) var5 + var15,
                        (float) var6 + var16,
                        new ItemStack(AlphaItem.seeds));
                    var17.delayBeforeCanPickup = 10;
                    var3.spawnEntityInWorld(var17);
                }
            }

            return true;
        }
    }

    public boolean isFull3D() {
        return true;
    }
}
