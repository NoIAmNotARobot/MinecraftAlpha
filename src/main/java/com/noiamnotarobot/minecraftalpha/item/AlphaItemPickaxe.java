package com.noiamnotarobot.minecraftalpha.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

public class AlphaItemPickaxe extends AlphaItemTool {

    private final int harvestLevel;

    public AlphaItemPickaxe(String name, int harvestLevel) {
        super(
            name,
            2,
            harvestLevel,
            new Block[] { AlphaBlock.cobblestone,
                /* AlphaBlock.stairDouble, AlphaBlock.stairSingle, */ AlphaBlock.stone, AlphaBlock.cobblestoneMossy,
                AlphaBlock.oreIron, AlphaBlock.blockIron, AlphaBlock.oreCoal, AlphaBlock.blockGold, AlphaBlock.oreGold,
                AlphaBlock.oreDiamond, AlphaBlock.blockDiamond, AlphaBlock.ice });
        this.harvestLevel = harvestLevel;
    }

    @Override
    public boolean canHarvestBlock(Block var1, ItemStack var2) {
        return var1 == AlphaBlock.obsidian ? this.harvestLevel == 3
            : (var1 != AlphaBlock.blockDiamond && var1 != AlphaBlock.oreDiamond
                ? (var1 != AlphaBlock.blockGold && var1 != AlphaBlock.oreGold
                    ? (var1 != AlphaBlock.blockIron && var1 != AlphaBlock.oreIron
                        ? (var1 != AlphaBlock.oreRedstone && var1 != AlphaBlock.oreRedstoneGlowing
                            ? (var1.getMaterial() == Material.rock || var1.getMaterial() == Material.iron)
                            : this.harvestLevel >= 2)
                        : this.harvestLevel >= 1)
                    : this.harvestLevel >= 2)
                : this.harvestLevel >= 2);
    }
}
