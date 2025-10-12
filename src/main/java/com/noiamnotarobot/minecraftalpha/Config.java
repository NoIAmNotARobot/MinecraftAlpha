package com.noiamnotarobot.minecraftalpha;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static int dimensionID = 98;
    public static int dimensionProviderID = 98;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        dimensionID = configuration.getInt(
            "dimensionID",
            "dimension",
            dimensionID,
            0,
            255,
            "ID number for the Minecraft Alpha Dimension. Change if there are mod conflicts.");
        dimensionProviderID = configuration.getInt(
            "dimensionProviderID",
            "dimension",
            dimensionProviderID,
            0,
            255,
            "ID number for the Dimension provider.");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
