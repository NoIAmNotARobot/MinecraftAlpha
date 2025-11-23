package com.noiamnotarobot.minecraftalpha.entity;

import net.minecraft.entity.Entity;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.entity.render.AlphaRenderArrow;
import com.noiamnotarobot.minecraftalpha.entity.render.AlphaRenderBoat;
import com.noiamnotarobot.minecraftalpha.entity.render.AlphaRenderPainting;
import com.noiamnotarobot.minecraftalpha.entity.render.AlphaRenderSnowball;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class AlphaEntities {

    public static void preInit() {
        createEntity(AlphaEntitySnowball.class, "AlphaSnowball", 64);
        createEntity(AlphaEntityArrow.class, "AlphaArrow", 64);
        createEntity(AlphaEntityTNTPrimed.class, "AlphaTNTPrimed", 64);
        createEntity(AlphaEntityPainting.class, "AlphaPainting", 64);
        createEntity(AlphaEntityBoat.class, "AlphaBoat", 64);
    }

    @SideOnly(Side.CLIENT)
    public static void preInitClient() {
        RenderingRegistry.registerEntityRenderingHandler(AlphaEntitySnowball.class, new AlphaRenderSnowball());
        RenderingRegistry.registerEntityRenderingHandler(AlphaEntityArrow.class, new AlphaRenderArrow());
        // tnt
        RenderingRegistry.registerEntityRenderingHandler(AlphaEntityPainting.class, new AlphaRenderPainting());
        RenderingRegistry.registerEntityRenderingHandler(AlphaEntityBoat.class, new AlphaRenderBoat());
    }

    public static void createEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange) {
        int randomId = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId);
        EntityRegistry
            .registerModEntity(entityClass, entityName, randomId, MinecraftAlpha.instance, trackingRange, 1, true);
    }
}
