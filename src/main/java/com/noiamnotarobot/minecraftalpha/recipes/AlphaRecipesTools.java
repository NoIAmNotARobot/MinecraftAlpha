package com.noiamnotarobot.minecraftalpha.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;
import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

import cpw.mods.fml.common.registry.GameRegistry;

public class AlphaRecipesTools {

    private final String[][] recipePatterns = new String[][] { { "XXX", " # ", " # " }, { "X", "#", "#" },
        { "XX", "X#", " #" }, { "XX", " #", " #" } };
    private final Object[][] recipeItems = new Object[][] {
        { AlphaBlock.planks, AlphaBlock.cobblestone, AlphaItem.ingotIron, AlphaItem.diamond, AlphaItem.ingotGold },
        { AlphaItem.pickaxeWood, AlphaItem.pickaxeStone, AlphaItem.pickaxeIron, AlphaItem.pickaxeDiamond,
            AlphaItem.pickaxeGold },
        { AlphaItem.shovelWood, AlphaItem.shovelStone, AlphaItem.shovel, AlphaItem.shovelDiamond,
            AlphaItem.shovelGold },
        { AlphaItem.axeWood, AlphaItem.axeStone, AlphaItem.axeIron, AlphaItem.axeDiamond, AlphaItem.axeGold },
        { AlphaItem.hoeWood, AlphaItem.hoeStone, AlphaItem.hoeIron, AlphaItem.hoeDiamond, AlphaItem.hoeGold } };

    public void addRecipes() {
        for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
            Object var3 = this.recipeItems[0][var2];

            for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
                Item var5 = (Item) this.recipeItems[var4 + 1][var2];
                GameRegistry.addRecipe(new ItemStack(var5), this.recipePatterns[var4], '#', AlphaItem.stick, 'X', var3);
            }
        }

    }
}
