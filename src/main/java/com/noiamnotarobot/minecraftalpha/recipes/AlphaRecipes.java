package com.noiamnotarobot.minecraftalpha.recipes;

import net.minecraft.item.ItemStack;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;
import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

import cpw.mods.fml.common.registry.GameRegistry;

public class AlphaRecipes {

    public static void registerRecipes() {
        // tools
        // weapons
        new AlphaRecipesIngots().addRecipes();
        new AlphaRecipesFood().addRecipes();
        new AlphaRecipesCrafting().addRecipes();
        // armor

        GameRegistry.addRecipe(new ItemStack(AlphaItem.paper, 3), "###", '#', AlphaItem.reed);
        GameRegistry.addRecipe(new ItemStack(AlphaItem.book, 1), "#", "#", "#", '#', AlphaItem.paper);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.fence, 2), "###", "###", '#', AlphaItem.stick);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.jukebox, 1), "###", "#X#", "###", '#', AlphaBlock.planks,
        // 'X', AlphaItem.diamond);
        GameRegistry.addRecipe(
            new ItemStack(AlphaBlock.bookshelf, 1),
            "###",
            "XXX",
            "###",
            '#',
            AlphaBlock.planks,
            'X',
            AlphaItem.book);
        GameRegistry
            .addRecipe(new ItemStack(AlphaBlock.blockSnow, 1), new Object[] { "##", "##", '#', AlphaItem.snowball });
        GameRegistry.addRecipe(new ItemStack(AlphaBlock.blockClay, 1), "##", "##", '#', AlphaItem.clay);
        GameRegistry.addRecipe(new ItemStack(AlphaBlock.brick, 1), "##", "##", '#', AlphaItem.brick);
        GameRegistry.addRecipe(new ItemStack(AlphaBlock.cloth, 1), "###", "###", "###", '#', AlphaItem.silk);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.tnt, 1), "X#X", "#X#", "X#X", 'X', AlphaItem.gunpowder, '#',
        // AlphaBlock.sand);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.stairSingle, 3), "###", '#', AlphaBlock.cobblestone);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.ladder, 1), "# #", "###", "# #", '#', AlphaItem.stick);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.doorWood, 1), "##", "##", "##", '#', AlphaBlock.planks);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.doorSteel, 1), "##", "##", "##", '#', AlphaItem.ingotIron);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.sign, 1), "###", "###", " X ", '#', AlphaBlock.planks, 'X',
        // AlphaItem.stick);
        GameRegistry.addRecipe(new ItemStack(AlphaBlock.planks, 4), "#", '#', AlphaBlock.wood);
        GameRegistry.addRecipe(new ItemStack(AlphaItem.stick, 4), "#", "#", '#', AlphaBlock.planks);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.torch, 4), "X", "#", 'X', AlphaItem.coal, '#',
        // AlphaItem.stick);
        GameRegistry.addRecipe(new ItemStack(AlphaItem.bowlEmpty, 4), "# #", " # ", '#', AlphaBlock.planks);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.minecartTrack, 16), "X X", "X#X", "X X", 'X',
        // AlphaItem.ingotIron, '#', AlphaItem.stick);
        // GameRegistry.addRecipe(new ItemStack(AlphaItem.minecartEmpty, 1), "# #", "###", '#', AlphaItem.ingotIron);
        // GameRegistry.addRecipe(new ItemStack(AlphaItem.minecartBox, 1), new Object[]{"A", "B", 'A', AlphaBlock.chest,
        // 'B', AlphaItem.minecartEmpty});
        // GameRegistry.addRecipe(new ItemStack(AlphaItem.minecartEngine, 1), new Object[]{"A", "B", 'A',
        // AlphaBlock.stoneOvenIdle, 'B', AlphaItem.minecartEmpty});
        // GameRegistry.addRecipe(new ItemStack(AlphaItem.boat, 1), "# #", "###", '#', AlphaBlock.planks);
        // GameRegistry.addRecipe(new ItemStack(AlphaItem.bucketEmpty, 1), "# #", " # ", '#', AlphaItem.ingotIron);
        GameRegistry
            .addRecipe(new ItemStack(AlphaItem.striker, 1), "A ", " B", 'A', AlphaItem.ingotIron, 'B', AlphaItem.flint);
        GameRegistry.addRecipe(new ItemStack(AlphaItem.bread, 1), "###", '#', AlphaItem.wheat);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.stairCompactWood, 4), "# ", "## ", "###", '#',
        // AlphaBlock.planks);
        // GameRegistry.addRecipe(new ItemStack(AlphaItem.fishingRod, 1), " #", " #X", "# X", '#', AlphaItem.stick, 'X',
        // AlphaItem.silk);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.stairCompactStone, 4), "# ", "## ", "###", '#',
        // AlphaBlock.cobblestone);
        // GameRegistry.addRecipe(new ItemStack(AlphaItem.painting, 1), "###", "#X#", "###", '#', AlphaItem.stick, 'X',
        // AlphaBlock.cloth);
        GameRegistry.addRecipe(
            new ItemStack(AlphaItem.appleGold, 1),
            "###",
            "#X#",
            "###",
            '#',
            AlphaBlock.blockGold,
            'X',
            AlphaItem.appleRed);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.lever, 1), "X", "#", '#', AlphaBlock.cobblestone, 'X',
        // AlphaItem.stick);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.torchRedstoneActive, 1), new Object[]{"X", "#", '#',
        // AlphaItem.stick, 'X', AlphaItem.redstone});
        // GameRegistry.addRecipe(new ItemStack(AlphaItem.compass, 1), new Object[]{" # ", "#X#", " # ", '#',
        // AlphaItem.ingotIron, 'X', AlphaItem.redstone});
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.button, 1), "#", "#", '#', AlphaBlock.stone);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.pressurePlateStone, 1), "###", '#', AlphaBlock.stone);
        // GameRegistry.addRecipe(new ItemStack(AlphaBlock.pressurePlateWood, 1), "###", '#', AlphaBlock.planks);
    }
}
