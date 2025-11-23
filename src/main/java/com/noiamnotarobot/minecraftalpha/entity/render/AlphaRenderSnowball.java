package com.noiamnotarobot.minecraftalpha.entity.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

public class AlphaRenderSnowball extends Render {

    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) var2, (float) var4, (float) var6);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        this.bindEntityTexture(var1);
        Tessellator var11 = Tessellator.instance;
        float var16 = 1.0F;
        float var17 = 0.5F;
        float var18 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        IIcon icon = AlphaItem.snowball.getIconFromDamage(0);
        var11.startDrawingQuads();
        var11.setNormal(0.0F, 1.0F, 0.0F);
        var11.addVertexWithUV((0.0F - var17), (0.0F - var18), 0.0D, icon.getMinU(), icon.getMaxV());
        var11.addVertexWithUV((var16 - var17), (0.0F - var18), 0.0D, icon.getMaxU(), icon.getMaxV());
        var11.addVertexWithUV((var16 - var17), (1.0F - var18), 0.0D, icon.getMaxU(), icon.getMinV());
        var11.addVertexWithUV((0.0F - var17), (1.0F - var18), 0.0D, icon.getMinU(), icon.getMinV());
        var11.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(Entity var1) {
        return TextureMap.locationItemsTexture;
    }
}
