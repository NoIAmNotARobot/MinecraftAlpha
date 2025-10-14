package com.noiamnotarobot.minecraftalpha.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;
import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

import cpw.mods.fml.common.registry.GameRegistry;

public class AlphaRecipesIngots {

    private final Object[][] recipeItems = new Object[][] { { AlphaBlock.blockGold, AlphaItem.ingotGold },
        { AlphaBlock.blockIron, AlphaItem.ingotIron }, { AlphaBlock.blockDiamond, AlphaItem.diamond } };

    protected void addRecipes() {
        for (Object[] recipeItem : this.recipeItems) {
            Block var3 = (Block) recipeItem[0];
            Item var4 = (Item) recipeItem[1];
            GameRegistry.addRecipe(new ItemStack(var3), "###", "###", "###", '#', var4);
            GameRegistry.addRecipe(new ItemStack(var4, 9), "#", '#', var3);
        }

    }
}
