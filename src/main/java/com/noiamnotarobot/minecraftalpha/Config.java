package com.noiamnotarobot.minecraftalpha;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static int dimensionID = 98;
    public static int dimensionProviderID = 98;
    public static int biomeID = 242;
    public static long customSeed = 0;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        dimensionID = configuration.getInt(
            "dimensionID",
            "dimension",
            dimensionID,
            0,
            255,
            "ID number for the Minecraft Alpha Dimension. Change if there are mod conflicts. WARNING: If changed, will break existing worlds.");
        dimensionProviderID = configuration.getInt(
            "dimensionProviderID",
            "dimension",
            dimensionProviderID,
            0,
            255,
            "ID number for the Dimension provider. WARNING: If changed, may break existing worlds.");
        biomeID = configuration.getInt(
            "biomeID",
            "dimension",
            biomeID,
            0,
            255,
            "ID number for the Minecraft Alpha Biome. Change if there are mod conflicts. WARNING: If changed, will break existing worlds.");
        customSeed = configuration.getInt(
            "customSeed",
            "dimension",
            (int) customSeed,
            Integer.MIN_VALUE,
            Integer.MAX_VALUE,
            "Seed for the custom dimension, which overrides world seed. When at zero, defaults to the world seed.");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
