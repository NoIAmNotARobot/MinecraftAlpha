package com.noiamnotarobot.minecraftalpha.block.entity;

import cpw.mods.fml.common.registry.GameRegistry;

public class AlphaTileEntities {

    public static int registerTileEntities() {
        GameRegistry.registerTileEntity(AlphaTileEntityChest.class, "tileentity.minecraftalpha.chest");
        GameRegistry.registerTileEntity(AlphaTileEntityFurnace.class, "tileentity.minecraftalpha.furnace");
        return 2;
    }
}
