package com.noiamnotarobot.minecraftalpha.item;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

import cpw.mods.fml.common.registry.GameRegistry;

public class AlphaItem extends Item {

    public static Random rand = new Random();
    public static ArrayList<AlphaItem> itemsList = new ArrayList<>();
    public static AlphaItem shovel = new AlphaItemSpade("shovel", 2);
    public static AlphaItem pickaxeIron = new AlphaItemPickaxe("pickaxeIron", 2);
    public static AlphaItem axeIron = new AlphaItemAxe("axeIron", 2);
    public static AlphaItem striker = new AlphaItemFlintAndSteel();
    public static AlphaItem appleRed = new AlphaItemFood("appleRed", 4);
    // bow
    public static AlphaItem arrow = new AlphaItem("arrow");
    public static AlphaItem coal = new AlphaItem("coal");
    public static AlphaItem diamond = new AlphaItem("diamond");
    public static AlphaItem ingotIron = new AlphaItem("ingotIron");
    public static AlphaItem ingotGold = new AlphaItem("ingotGold");
    public static AlphaItem swordIron = new AlphaItemSword("swordIron", 2);
    public static AlphaItem swordWood = new AlphaItemSword("swordWood", 0);
    public static AlphaItem shovelWood = new AlphaItemSpade("shovelWood", 0);
    public static AlphaItem pickaxeWood = new AlphaItemPickaxe("pickaxeWood", 0);
    public static AlphaItem axeWood = new AlphaItemAxe("axeWood", 0);
    public static AlphaItem swordStone = new AlphaItemSword("swordStone", 1);
    public static AlphaItem shovelStone = new AlphaItemSpade("shovelStone", 1);
    public static AlphaItem pickaxeStone = new AlphaItemPickaxe("pickaxeStone", 1);
    public static AlphaItem axeStone = new AlphaItemAxe("axeStone", 1);
    public static AlphaItem swordDiamond = new AlphaItemSword("swordDiamond", 3);
    public static AlphaItem shovelDiamond = new AlphaItemSpade("shovelDiamond", 3);
    public static AlphaItem pickaxeDiamond = new AlphaItemPickaxe("pickaxeDiamond", 3);
    public static AlphaItem axeDiamond = new AlphaItemAxe("axeDiamond", 3);
    public static AlphaItem stick = new AlphaItem("stick");
    public static AlphaItem bowlEmpty = new AlphaItem("bowlEmpty");
    public static AlphaItem bowlSoup = new AlphaItemFood("bowlSoup", 10);
    public static AlphaItem swordGold = new AlphaItemSword("swordGold", 0);
    public static AlphaItem shovelGold = new AlphaItemSpade("shovelGold", 0);
    public static AlphaItem pickaxeGold = new AlphaItemPickaxe("pickaxeGold", 0);
    public static AlphaItem axeGold = new AlphaItemAxe("axeGold", 0);
    public static AlphaItem silk = new AlphaItem("silk");
    public static AlphaItem feather = new AlphaItem("feather");
    public static AlphaItem gunpowder = new AlphaItem("gunpowder");
    public static AlphaItem hoeWood = new AlphaItemHoe("hoeWood", 0);
    public static AlphaItem hoeStone = new AlphaItemHoe("hoeStone", 1);
    public static AlphaItem hoeIron = new AlphaItemHoe("hoeIron", 2);
    public static AlphaItem hoeDiamond = new AlphaItemHoe("hoeDiamond", 3);
    public static AlphaItem hoeGold = new AlphaItemHoe("hoeGold", 1);
    public static AlphaItem seeds = new AlphaItemSeeds(AlphaBlock.crops);
    public static AlphaItem wheat = new AlphaItem("wheat");
    public static AlphaItem bread = new AlphaItemFood("bread", 5);
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
    public static AlphaItem flint = new AlphaItem("flint");
    public static AlphaItem porkRaw = new AlphaItemFood("porkRaw", 3);
    public static AlphaItem porkCooked = new AlphaItemFood("porkCooked", 8);
    // painting
    public static AlphaItem appleGold = new AlphaItemFood("appleGold", 42);
    // sign
    // doorWood
    public static Item bucketEmpty = Items.bucket;
    // bucketWater
    // bucketLava
    // minecartEmpty
    public static AlphaItem saddle = new AlphaItemSaddle();
    // doorIron
    public static AlphaItem redstone = new AlphaItemRedstone();
    public static AlphaItem snowball = new AlphaItemSnowball();
    // boat
    public static AlphaItem leather = new AlphaItem("leather");
    // bucketMilk
    public static AlphaItem brick = new AlphaItem("brick");
    public static AlphaItem clay = new AlphaItem("clay");
    public static AlphaItem reed = new AlphaItemReed(AlphaBlock.reed);
    public static AlphaItem paper = new AlphaItem("paper");
    public static AlphaItem book = new AlphaItem("book");
    public static AlphaItem slimeBall = new AlphaItem("slimeBall");
    // minecartBox
    // minecartEngine
    // egg
    // compass
    // fishingRod
    public static AlphaItem record13 = new AlphaItemRecord("record13", "records.13");
    public static AlphaItem recordCat = new AlphaItemRecord("recordCat", "records.cat");

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
        itemsList.add(shovel);
        itemsList.add(pickaxeIron);
        itemsList.add(axeIron);
        itemsList.add(striker);
        itemsList.add(appleRed);
        itemsList.add(arrow);
        itemsList.add(coal);
        itemsList.add(diamond);
        itemsList.add(ingotIron);
        itemsList.add(ingotGold);
        itemsList.add(swordIron);
        itemsList.add(swordWood);
        itemsList.add(shovelWood);
        itemsList.add(pickaxeWood);
        itemsList.add(axeWood);
        itemsList.add(swordStone);
        itemsList.add(shovelStone);
        itemsList.add(pickaxeStone);
        itemsList.add(axeStone);
        itemsList.add(swordDiamond);
        itemsList.add(shovelDiamond);
        itemsList.add(pickaxeDiamond);
        itemsList.add(axeDiamond);
        itemsList.add(stick);
        itemsList.add(bowlEmpty);
        itemsList.add(bowlSoup);
        itemsList.add(swordGold);
        itemsList.add(shovelGold);
        itemsList.add(pickaxeGold);
        itemsList.add(axeGold);
        itemsList.add(silk);
        itemsList.add(feather);
        itemsList.add(gunpowder);
        itemsList.add(hoeWood);
        itemsList.add(hoeStone);
        itemsList.add(hoeIron);
        itemsList.add(hoeDiamond);
        itemsList.add(hoeGold);
        itemsList.add(seeds);
        itemsList.add(wheat);
        itemsList.add(bread);
        itemsList.add(flint);
        itemsList.add(porkRaw);
        itemsList.add(porkCooked);
        itemsList.add(appleGold);
        itemsList.add(saddle);
        itemsList.add(redstone);
        itemsList.add(snowball);
        itemsList.add(leather);
        itemsList.add(brick);
        itemsList.add(clay);
        itemsList.add(reed);
        itemsList.add(paper);
        itemsList.add(book);
        itemsList.add(slimeBall);
        itemsList.add(record13);
        itemsList.add(recordCat);

        MinecraftAlpha.LOG.info("Registering items...");
        int itemsRegistered = 0;
        for (AlphaItem item : itemsList) {
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
