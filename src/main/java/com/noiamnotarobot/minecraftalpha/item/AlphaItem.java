package com.noiamnotarobot.minecraftalpha.item;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class AlphaItem extends Item {

    public static Random rand = new Random();
    public static ArrayList<Item> itemsList = new ArrayList<>();
    public static AlphaItem shovel = new AlphaItemSpade("shovel", 2);
    public static AlphaItem pickaxeIron = new AlphaItemPickaxe("pickaxeIron", 2);
    public static AlphaItem axeIron = new AlphaItemAxe("axeIron", 2);
    public static AlphaItem striker = new AlphaItemFlintAndSteel();
    public static AlphaItem appleRed = new AlphaItemFood("appleRed", 4);
    public static AlphaItem bow = new AlphaItemBow();
    public static AlphaItem arrow = new AlphaItem("arrow");
    public static AlphaItem coal = new AlphaItemFuel("coal", 1600);
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
    public static AlphaItem stick = new AlphaItemFuel("stick", 100);
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
    public static AlphaItem helmetLeather = new AlphaItemArmor("helmetLeather", 0, 0, 0);
    public static AlphaItem plateLeather = new AlphaItemArmor("plateLeather", 0, 0, 1);
    public static AlphaItem legsLeather = new AlphaItemArmor("legsLeather", 0, 0, 2);
    public static AlphaItem bootsLeather = new AlphaItemArmor("bootsLeather", 0, 0, 3);
    public static AlphaItem helmetChain = new AlphaItemArmor("helmetChain", 1, 1, 0);
    public static AlphaItem plateChain = new AlphaItemArmor("plateChain", 1, 1, 1);
    public static AlphaItem legsChain = new AlphaItemArmor("legsChain", 1, 1, 2);
    public static AlphaItem bootsChain = new AlphaItemArmor("bootsChain", 1, 1, 3);
    public static AlphaItem helmetIron = new AlphaItemArmor("helmetIron", 2, 2, 0);
    public static AlphaItem plateIron = new AlphaItemArmor("plateIron", 2, 2, 1);
    public static AlphaItem legsIron = new AlphaItemArmor("legsIron", 2, 2, 2);
    public static AlphaItem bootsIron = new AlphaItemArmor("bootsIron", 2, 2, 3);
    public static AlphaItem helmetDiamond = new AlphaItemArmor("helmetDiamond", 3, 3, 0);
    public static AlphaItem plateDiamond = new AlphaItemArmor("plateDiamond", 3, 3, 1);
    public static AlphaItem legsDiamond = new AlphaItemArmor("legsDiamond", 3, 3, 2);
    public static AlphaItem bootsDiamond = new AlphaItemArmor("bootsDiamond", 3, 3, 3);
    public static AlphaItem helmetGold = new AlphaItemArmor("helmetGold", 1, 4, 0);
    public static AlphaItem plateGold = new AlphaItemArmor("plateGold", 1, 4, 1);
    public static AlphaItem legsGold = new AlphaItemArmor("legsGold", 1, 4, 2);
    public static AlphaItem bootsGold = new AlphaItemArmor("bootsGold", 1, 4, 3);
    public static AlphaItem flint = new AlphaItem("flint");
    public static AlphaItem porkRaw = new AlphaItemFood("porkRaw", 3);
    public static AlphaItem porkCooked = new AlphaItemFood("porkCooked", 8);
    // painting
    public static AlphaItem appleGold = new AlphaItemFood("appleGold", 42);
    // sign
    public static AlphaItem doorWood = new AlphaItemDoor("doorWood", Material.wood);
    public static AlphaItem bucketEmpty = new AlphaItemBucket("bucketEmpty", Blocks.air);
    public static AlphaItem bucketWater = new AlphaItemBucket("bucketWater", AlphaBlock.waterStill);
    public static AlphaItem bucketLava = new AlphaItemBucket("bucketLava", AlphaBlock.lavaStill, 20000);
    // minecartEmpty
    public static AlphaItem saddle = new AlphaItemSaddle();
    public static AlphaItem doorIron = new AlphaItemDoor("doorIron", Material.iron);
    public static AlphaItem redstone = new AlphaItemRedstone();
    public static AlphaItem snowball = new AlphaItemSnowball();
    // boat
    public static AlphaItem leather = new AlphaItem("leather");
    public static AlphaItem bucketMilk = new AlphaItemBucket("bucketMilk", null);
    public static AlphaItem brick = new AlphaItem("brick");
    public static AlphaItem clay = new AlphaItem("clay");
    public static AlphaItem reed = new AlphaItemReed(AlphaBlock.reed);
    public static AlphaItem paper = new AlphaItem("paper");
    public static AlphaItem book = new AlphaItem("book");
    public static AlphaItem slimeBall = new AlphaItem("slimeBall");
    // minecartBox
    // minecartEngine
    // egg
    public static AlphaItem compass = new AlphaItem("compass");
    // fishingRod
    public static AlphaItem record13 = new AlphaItemRecord("record13", "records.13");
    public static AlphaItem recordCat = new AlphaItemRecord("recordCat", "records.cat");

    protected String unlocalizedName;

    public AlphaItem(String name) {
        this.setCreativeTab(MinecraftAlpha.tabAlpha);
        this.unlocalizedName = name;
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
        itemsList.add(bow);
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
        itemsList.add(helmetLeather);
        itemsList.add(plateLeather);
        itemsList.add(legsLeather);
        itemsList.add(bootsLeather);
        itemsList.add(helmetChain);
        itemsList.add(plateChain);
        itemsList.add(legsChain);
        itemsList.add(bootsChain);
        itemsList.add(helmetIron);
        itemsList.add(plateIron);
        itemsList.add(legsIron);
        itemsList.add(bootsIron);
        itemsList.add(helmetDiamond);
        itemsList.add(plateDiamond);
        itemsList.add(legsDiamond);
        itemsList.add(bootsDiamond);
        itemsList.add(helmetGold);
        itemsList.add(plateGold);
        itemsList.add(legsGold);
        itemsList.add(bootsGold);
        itemsList.add(flint);
        itemsList.add(porkRaw);
        itemsList.add(porkCooked);
        itemsList.add(appleGold);
        itemsList.add(doorWood);
        itemsList.add(bucketEmpty);
        itemsList.add(bucketWater);
        itemsList.add(bucketLava);
        itemsList.add(saddle);
        itemsList.add(doorIron);
        itemsList.add(redstone);
        itemsList.add(snowball);
        itemsList.add(leather);
        itemsList.add(bucketMilk);
        itemsList.add(brick);
        itemsList.add(clay);
        itemsList.add(reed);
        itemsList.add(paper);
        itemsList.add(book);
        itemsList.add(slimeBall);
        itemsList.add(compass);
        itemsList.add(record13);
        itemsList.add(recordCat);

        MinecraftAlpha.LOG.info("Registering items...");
        int itemsRegistered = 0;
        for (Item item : itemsList) {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
            if (item instanceof IFuelHandler) {
                GameRegistry.registerFuelHandler((IFuelHandler) item);
            }
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
