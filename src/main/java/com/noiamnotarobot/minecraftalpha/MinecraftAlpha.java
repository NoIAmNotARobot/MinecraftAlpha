package com.noiamnotarobot.minecraftalpha;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;

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

    @SidedProxy(
        clientSide = "com.noiamnotarobot.minecraftalpha.ClientProxy",
        serverSide = "com.noiamnotarobot.minecraftalpha.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        MinecraftAlpha.LOG.info(Config.greeting);
        MinecraftAlpha.LOG.info("I am MinecraftAlpha, mod version " + Tags.VERSION + ".");

        AlphaBlock.preInit();

        proxy.preInit(event);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
