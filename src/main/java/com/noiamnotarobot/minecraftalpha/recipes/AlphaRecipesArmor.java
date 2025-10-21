package com.noiamnotarobot.minecraftalpha.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;
import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

import cpw.mods.fml.common.registry.GameRegistry;

public class AlphaRecipesArmor {

    private final String[][] recipePatterns = new String[][] { { "XXX", "X X" }, { "X X", "XXX", "XXX" },
        { "XXX", "X X", "X X" }, { "X X", "X X" } };
    private final Object[][] recipeItems = new Object[][] {
        { AlphaItem.leather, AlphaBlock.fire, AlphaItem.ingotIron, AlphaItem.diamond, AlphaItem.ingotGold },
        { AlphaItem.helmetLeather, AlphaItem.helmetChain, AlphaItem.helmetIron, AlphaItem.helmetDiamond,
            AlphaItem.helmetGold },
        { AlphaItem.plateLeather, AlphaItem.plateChain, AlphaItem.plateIron, AlphaItem.plateDiamond,
            AlphaItem.plateGold },
        { AlphaItem.legsLeather, AlphaItem.legsChain, AlphaItem.legsIron, AlphaItem.legsDiamond, AlphaItem.legsGold },
        { AlphaItem.bootsLeather, AlphaItem.bootsChain, AlphaItem.bootsIron, AlphaItem.bootsDiamond,
            AlphaItem.bootsGold } };

    public void addRecipes() {
        for (int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
            Object var3 = this.recipeItems[0][var2];

            for (int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
                Item var5 = (Item) this.recipeItems[var4 + 1][var2];
                GameRegistry.addRecipe(new ItemStack(var5), this.recipePatterns[var4], 'X', var3);
            }
        }

    }
}
