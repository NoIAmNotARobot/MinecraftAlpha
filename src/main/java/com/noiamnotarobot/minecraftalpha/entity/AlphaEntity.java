package com.noiamnotarobot.minecraftalpha.entity;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;

import cpw.mods.fml.common.registry.EntityRegistry;

public abstract class AlphaEntity {

    public static void preInit() {
        createEntity(AlphaEntitySnowball.class, "alpha_snowball", 64);
        createEntity(AlphaEntityArrow.class, "alpha_arrow", 64);
        createEntity(AlphaEntityTNTPrimed.class, "alpha_tnt_primed", 64);
    }

    public static void createEntity(Class entityClass, String entityName, int trackingRange) {
        int randomId = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId);
        EntityRegistry
            .registerModEntity(entityClass, entityName, randomId, MinecraftAlpha.instance, trackingRange, 1, true);
    }
}
