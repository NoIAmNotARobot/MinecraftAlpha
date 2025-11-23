package com.noiamnotarobot.minecraftalpha;

import com.noiamnotarobot.minecraftalpha.entity.AlphaEntities;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        AlphaEntities.preInitClient();
    }

    @Override
    public boolean isClient() {
        return true;
    }
}
