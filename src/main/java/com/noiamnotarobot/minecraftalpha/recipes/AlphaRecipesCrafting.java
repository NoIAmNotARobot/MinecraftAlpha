package com.noiamnotarobot.minecraftalpha.recipes;

import net.minecraft.item.ItemStack;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

import cpw.mods.fml.common.registry.GameRegistry;

public class AlphaRecipesCrafting {

    public void addRecipes() {
        GameRegistry.addRecipe(new ItemStack(AlphaBlock.chest), "###", "# #", "###", '#', AlphaBlock.planks);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.stoneOvenIdle), "###", "# #", "###", '#',
        // AlphaBlock.cobblestone);
        GameRegistry.addRecipe(new ItemStack(AlphaBlock.workbench), "##", "##", '#', AlphaBlock.planks);
    }
}
