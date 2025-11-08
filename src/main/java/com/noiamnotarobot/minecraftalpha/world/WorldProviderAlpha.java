package com.noiamnotarobot.minecraftalpha.world;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

import com.noiamnotarobot.minecraftalpha.Config;
import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;
import com.noiamnotarobot.minecraftalpha.world.gen.ChunkProviderAlpha;
import com.noiamnotarobot.minecraftalpha.world.render.AlphaSkyRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
    public boolean canDoLightning(Chunk chunk) {
        return false;
    }

    @Override
    public void calculateInitialWeather() {
        if (Config.snowCovered) {
            worldObj.setRainStrength(1.0F);
        } else {
            worldObj.setRainStrength(0.0F);
        }
    }

    @Override
    public void updateWeather() {}

    public boolean canCoordinateBeSpawn(int var1, int var2) {
        return this.worldObj.getTopBlock(var1, var2) == AlphaBlock.grass;
    }

    @Override
    public boolean canSnowAt(int x, int y, int z, boolean checkLight) {
        if (Config.snowCovered) {
            if (!checkLight) {
                return true;
            } else {
                if (y >= 0 && y < 256 && worldObj.getSavedLightValue(EnumSkyBlock.Block, x, y, z) < 10) {
                    return worldObj.getBlock(x, y, z)
                        .getMaterial() == Material.air && AlphaBlock.snow.canPlaceBlockAt(worldObj, x, y, z);
                }
                return false;
            }
        } else return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        if (super.getSkyRenderer() == null) {
            this.setSkyRenderer(new AlphaSkyRenderer());
        }
        return super.getSkyRenderer();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        float var2 = worldObj.getCelestialAngle(partialTicks);
        float var3 = MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;
        if (var3 < 0.0F) {
            var3 = 0.0F;
        }

        if (var3 > 1.0F) {
            var3 = 1.0F;
        }

        long skyColor = 8961023L;
        float var4 = (float) (skyColor >> 16 & 255L) / 255.0F;
        float var5 = (float) (skyColor >> 8 & 255L) / 255.0F;
        float var6 = (float) (skyColor & 255L) / 255.0F;
        var4 *= var3;
        var5 *= var3;
        var6 *= var3;
        return Vec3.createVectorHelper(var4, var5, var6);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float var1) {
        float var2 = worldObj.getCelestialAngle(var1);
        float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 12.0F / 16.0F);
        if (var3 < 0.0F) {
            var3 = 0.0F;
        }

        if (var3 > 1.0F) {
            var3 = 1.0F;
        }

        return var3 * var3 * 0.5F;
    }

    @Override
    public int getMoonPhase(long p_76559_1_) {
        return 0;
    }

    public Vec3 getFogColor(float var1, float var2) {
        float var3 = MathHelper.cos(var1 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;
        if (var3 < 0.0F) {
            var3 = 0.0F;
        }

        if (var3 > 1.0F) {
            var3 = 1.0F;
        }

        long fogColor = 12638463L;
        float var4 = (float) (fogColor >> 16 & 255L) / 255.0F;
        float var5 = (float) (fogColor >> 8 & 255L) / 255.0F;
        float var6 = (float) (fogColor & 255L) / 255.0F;
        var4 *= var3 * 0.94F + 0.06F;
        var5 *= var3 * 0.94F + 0.06F;
        var6 *= var3 * 0.91F + 0.09F;
        return Vec3.createVectorHelper(var4, var5, var6);
    }

    @Override
    public float getCloudHeight() {
        return 108;
    }
}
