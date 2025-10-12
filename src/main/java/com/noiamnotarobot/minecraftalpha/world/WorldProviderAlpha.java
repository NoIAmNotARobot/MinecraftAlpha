package com.noiamnotarobot.minecraftalpha.world;

import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

import com.noiamnotarobot.minecraftalpha.Config;
import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
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

    @Override
    public long getSeed() {
        if (Config.customSeed == 0) return super.getSeed();
        else return Config.customSeed;
    }

    @Override
    protected void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(MinecraftAlpha.biomeAlpha, 0.0F);
        this.dimensionId = Config.dimensionID;
    }

    @Override
    public boolean canRespawnHere() {
        return worldObj.getWorldInfo()
            .isInitialized();
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        if (this.chunkProvider == null) {
            this.chunkProvider = new ChunkProviderAlpha(worldObj, worldObj.getSeed());
            return this.chunkProvider;
        } else {
            return new ChunkProviderAlpha(worldObj, worldObj.getSeed());
        }
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }

    @Override
    public boolean canDoLightning(Chunk chunk) {
        return false;
    }
}
