package com.noiamnotarobot.minecraftalpha.recipes;

import net.minecraft.item.ItemStack;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;
import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

import cpw.mods.fml.common.registry.GameRegistry;

public class AlphaRecipesFood {

    protected void addRecipes() {
        GameRegistry.addRecipe(
            new ItemStack(AlphaItem.bowlSoup),
            "Y",
            "X",
            "#",
            'X',
            AlphaBlock.mushroomBrown,
            'Y',
            AlphaBlock.mushroomRed,
            '#',
            AlphaItem.bowlEmpty);
        GameRegistry.addRecipe(
            new ItemStack(AlphaItem.bowlSoup),
            "Y",
            "X",
            "#",
            'X',
            AlphaBlock.mushroomRed,
            'Y',
            AlphaBlock.mushroomBrown,
            '#',
            AlphaItem.bowlEmpty);
    }
}
