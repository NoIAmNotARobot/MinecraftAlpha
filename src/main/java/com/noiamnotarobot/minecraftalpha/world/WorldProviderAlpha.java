package com.noiamnotarobot.minecraftalpha.world;

import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;

import com.noiamnotarobot.minecraftalpha.Config;
import com.noiamnotarobot.minecraftalpha.world.gen.ChunkProviderAlpha;

public class WorldProviderAlpha extends WorldProviderSurface {

    public final String saveFolder;
    public ChunkProviderAlpha chunkProvider;

    public WorldProviderAlpha() {
        setDimension(Config.dimensionID);
        saveFolder = "DIM" + Config.dimensionID;
    }

    @Override
    public String getSaveFolder() {
        return saveFolder;
    }

    @Override
    public String getDimensionName() {
        return "Minecraft Alpha 1.1.2_01";
    }

    @Override
    public String getWelcomeMessage() {
        return "Entering Minecraft Alpha 1.1.2_01";
    }

    @Override
    public String getDepartMessage() {
        return "Leaving Minecraft Alpha 1.1.2_01";
    }

    public ChunkProviderAlpha getChunkProvider() {
        return chunkProvider;
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderAlpha(worldObj, worldObj.getSeed());
    }
}
