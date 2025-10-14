package com.noiamnotarobot.minecraftalpha;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.noiamnotarobot.minecraftalpha.biome.BiomeGenAlpha;
import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;
import com.noiamnotarobot.minecraftalpha.entity.AlphaEntity;
import com.noiamnotarobot.minecraftalpha.item.AlphaItem;
import com.noiamnotarobot.minecraftalpha.recipes.AlphaRecipes;
import com.noiamnotarobot.minecraftalpha.world.WorldProviderAlpha;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(
    modid = MinecraftAlpha.MODID,
    version = Tags.VERSION,
    name = "MinecraftAlpha",
    acceptedMinecraftVersions = "[1.7.10]")
public class MinecraftAlpha {

    public static final String MODID = "minecraftalpha";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @Mod.Instance
    public static MinecraftAlpha instance;

    public static CreativeTabs tabAlpha = new CreativeTabs("minecraftalpha") {

        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(AlphaBlock.grass);
        }
    };
    // Note: This biome should not be allowed to generate outside the Minecraft Alpha dimension, as doing so will cause
    // undocumented behavior.
    public static BiomeGenBase biomeAlpha;

    @SidedProxy(
        clientSide = "com.noiamnotarobot.minecraftalpha.ClientProxy",
        serverSide = "com.noiamnotarobot.minecraftalpha.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc., and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        MinecraftAlpha.LOG.info("I am MinecraftAlpha, mod version " + Tags.VERSION + ".");

        biomeAlpha = new BiomeGenAlpha(Config.biomeID).setBiomeName("Minecraft Alpha 1.1.2_01")
            .setTemperatureRainfall(1.0F, 0.0F);

        AlphaBlock.preInit();
        AlphaItem.preInit();
        AlphaEntity.preInit();

        proxy.preInit(event);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        AlphaRecipes.registerRecipes();

        BiomeDictionary.registerBiomeType(biomeAlpha, BiomeDictionary.Type.PLAINS);
        BiomeManager.addSpawnBiome(biomeAlpha);

        DimensionManager.registerProviderType(Config.dimensionProviderID, WorldProviderAlpha.class, false);

        proxy.init(event);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) throws RuntimeException {
        if (!DimensionManager.isDimensionRegistered(Config.dimensionID)) {
            DimensionManager.registerDimension(Config.dimensionID, Config.dimensionProviderID);
        } else {
            throw new RuntimeException(
                "The dimension id {} is already in use by another mod, please pick another id for MinecraftAlpha.");
        }

        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
