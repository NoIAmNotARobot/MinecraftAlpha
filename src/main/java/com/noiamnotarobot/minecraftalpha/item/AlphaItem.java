package com.noiamnotarobot.minecraftalpha.item;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;

import cpw.mods.fml.common.registry.GameRegistry;

public class AlphaItem extends Item {

    public static ArrayList<Item> itemsList = new ArrayList<>();
    // shovel
    // pickaxeIron
    // axeIron
    // striker
    public static Item appleRed = new AlphaItemFood("appleRed", 4);
    // bow
    public static Item arrow = new AlphaItem("arrow");
    public static Item coal = new AlphaItem("coal");
    public static Item diamond = new AlphaItem("diamond");
    public static Item ingotIron = new AlphaItem("ingotIron");
    public static Item ingotGold = new AlphaItem("ingotGold");
    // swordIron
    // swordWood
    // shovelWood
    // pickaxeWood
    // axeWood
    // swordStone
    // shovelStone
    // pickaxeStone
    // axeStone
    // swordDiamond
    // shovelDiamond
    // pickaxeDiamond
    // axeDiamond
    public static Item stick = new AlphaItem("stick");
    public static Item bowlEmpty = new AlphaItem("bowlEmpty");
    public static Item bowlSoup = new AlphaItemFood("bowlSoup", 10);
    // swordGold
    // shovelGold
    // pickaxeGold
    // axeGold
    public static Item silk = new AlphaItem("silk");
    public static Item feather = new AlphaItem("feather");
    public static Item gunpowder = new AlphaItem("gunpowder");
    // hoeWood
    // hoeStone
    // hoeIron
    // hoeDiamond
    // hoeGold
    // seeds
    public static Item wheat = new AlphaItem("wheat");
    public static Item bread = new AlphaItemFood("bread", 5);
    // helmetLeather
    // plateLeather
    // legsLeather
    // bootsLeather
    // helmetChain
    // plateChain
    // legsChain
    // bootsChain
    // helmetIron
    // plateIron
    // legsIron
    // bootsIron
    // helmetDiamond
    // plateDiamond
    // legsDiamond
    // bootsDiamond
    // helmetGold
    // plateGold
    // legsGold
    // bootsGold
    public static Item flint = new AlphaItem("flint");
    public static Item porkRaw = new AlphaItemFood("porkRaw", 3);
    public static Item porkCooked = new AlphaItemFood("porkCooked", 8);
    // painting
    public static Item appleGold = new AlphaItemFood("appleGold", 42);
    // sign
    // doorWood
    // bucketEmpty
    // bucketWater
    // bucketLava
    // minecartEmpty
    // saddle
    // doorIron
    // redstone
    // snowball
    // boat
    public static Item leather = new AlphaItem("leather");
    // bucketMilk
    public static Item brick = new AlphaItem("brick");
    public static Item clay = new AlphaItem("clay");
    // reed
    public static Item paper = new AlphaItem("paper");
    public static Item book = new AlphaItem("book");
    public static Item slimeBall = new AlphaItem("slimeBall");
    // minecartBox
    // minecartEngine
    // egg
    // compass
    // fishingRod
    // record13
    // recordCat

    protected String unlocalizedName;

    public AlphaItem(String name) {
        this.setCreativeTab(MinecraftAlpha.tabAlpha);
        unlocalizedName = name;
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(MinecraftAlpha.MODID + ":" + name);
    }

    public AlphaItem(String name, int maxStackSize) {
        this(name);
        this.setMaxStackSize(maxStackSize);
    }

    public static void preInit() {
        itemsList.add(arrow);
        itemsList.add(coal);
        itemsList.add(diamond);
        itemsList.add(ingotIron);
        itemsList.add(ingotGold);
        itemsList.add(stick);
        itemsList.add(bowlEmpty);
        itemsList.add(bowlSoup);
        itemsList.add(silk);
        itemsList.add(feather);
        itemsList.add(gunpowder);
        itemsList.add(wheat);
        itemsList.add(bread);
        itemsList.add(flint);
        itemsList.add(porkRaw);
        itemsList.add(porkCooked);
        itemsList.add(appleGold);
        itemsList.add(leather);
        itemsList.add(brick);
        itemsList.add(clay);
        itemsList.add(paper);
        itemsList.add(book);
        itemsList.add(slimeBall);

        MinecraftAlpha.LOG.info("Registering items...");
        int itemsRegistered = 0;
        for (Item item : itemsList) {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
            itemsRegistered++;
        }
        MinecraftAlpha.LOG.info("Registered {} items!", itemsRegistered);
    }

    @Override
    public String getUnlocalizedName() {
        return "item." + MinecraftAlpha.MODID + "." + unlocalizedName;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName();
    }

    @Override
    public String getUnlocalizedNameInefficiently(ItemStack stack) {
        String s = getUnlocalizedName();
        return s == null ? "" : StatCollector.translateToLocal(s);
    }
}
