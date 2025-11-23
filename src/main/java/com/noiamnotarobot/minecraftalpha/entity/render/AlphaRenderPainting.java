package com.noiamnotarobot.minecraftalpha.entity.render;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.noiamnotarobot.minecraftalpha.AlphaEnumArt;
import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.entity.AlphaEntityPainting;

public class AlphaRenderPainting extends Render {

    private final Random rand = new Random();
    private final ResourceLocation texture = new ResourceLocation(
        "textures/painting/paintings_kristoffer_zetterstrand.png");

    public void renderPainting(AlphaEntityPainting var1, double var2, double var4, double var6, float var8,
        float var9) {
        this.rand.setSeed(187L);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) var2, (float) var4, (float) var6);
        GL11.glRotatef(var8, 0.0F, 1.0F, 0.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        this.bindEntityTexture(var1);
        AlphaEnumArt var10 = var1.art;
        float var11 = 1.0F / 16.0F;
        GL11.glScalef(var11, var11, var11);
        MinecraftAlpha.LOG.info(var10);
        this.setSizes(var1, var10.sizeX, var10.sizeY, var10.offsetX, var10.offsetY);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    private void setSizes(AlphaEntityPainting var1, int var2, int var3, int var4, int var5) {
        float var6 = (float) (-var2) / 2.0F;
        float var7 = (float) (-var3) / 2.0F;
        float var8 = -0.5F;
        float var9 = 0.5F;

        for (int var10 = 0; var10 < var2 / 16; ++var10) {
            for (int var11 = 0; var11 < var3 / 16; ++var11) {
                float var12 = var6 + (float) ((var10 + 1) * 16);
                float var13 = var6 + (float) (var10 * 16);
                float var14 = var7 + (float) ((var11 + 1) * 16);
                float var15 = var7 + (float) (var11 * 16);
                this.getOffset(var1, (var12 + var13) / 2.0F, (var14 + var15) / 2.0F);
                float var16 = (float) (var4 + var2 - var10 * 16) / 256.0F;
                float var17 = (float) (var4 + var2 - (var10 + 1) * 16) / 256.0F;
                float var18 = (float) (var5 + var3 - var11 * 16) / 256.0F;
                float var19 = (float) (var5 + var3 - (var11 + 1) * 16) / 256.0F;
                float var20 = 12.0F / 16.0F;
                float var21 = 13.0F / 16.0F;
                float var22 = 0.0F;
                float var23 = 1.0F / 16.0F;
                float var24 = 12.0F / 16.0F;
                float var25 = 13.0F / 16.0F;
                float var26 = 0.001953125F;
                float var27 = 0.001953125F;
                float var28 = 385.0F / 512.0F;
                float var29 = 385.0F / 512.0F;
                float var30 = 0.0F;
                float var31 = 1.0F / 16.0F;
                Tessellator var32 = Tessellator.instance;
                var32.startDrawingQuads();
                var32.setNormal(0.0F, 0.0F, -1.0F);
                var32.addVertexWithUV(var12, var15, var8, var17, var18);
                var32.addVertexWithUV(var13, var15, var8, var16, var18);
                var32.addVertexWithUV(var13, var14, var8, var16, var19);
                var32.addVertexWithUV(var12, var14, var8, var17, var19);
                var32.setNormal(0.0F, 0.0F, 1.0F);
                var32.addVertexWithUV(var12, var14, var9, var20, var22);
                var32.addVertexWithUV(var13, var14, var9, var21, var22);
                var32.addVertexWithUV(var13, var15, var9, var21, var23);
                var32.addVertexWithUV(var12, var15, var9, var20, var23);
                var32.setNormal(0.0F, -1.0F, 0.0F);
                var32.addVertexWithUV(var12, var14, var8, var24, var26);
                var32.addVertexWithUV(var13, var14, var8, var25, var26);
                var32.addVertexWithUV(var13, var14, var9, var25, var27);
                var32.addVertexWithUV(var12, var14, var9, var24, var27);
                var32.setNormal(0.0F, 1.0F, 0.0F);
                var32.addVertexWithUV(var12, var15, var9, var24, var26);
                var32.addVertexWithUV(var13, var15, var9, var25, var26);
                var32.addVertexWithUV(var13, var15, var8, var25, var27);
                var32.addVertexWithUV(var12, var15, var8, var24, var27);
                var32.setNormal(-1.0F, 0.0F, 0.0F);
                var32.addVertexWithUV(var12, var14, var9, var29, var30);
                var32.addVertexWithUV(var12, var15, var9, var29, var31);
                var32.addVertexWithUV(var12, var15, var8, var28, var31);
                var32.addVertexWithUV(var12, var14, var8, var28, var30);
                var32.setNormal(1.0F, 0.0F, 0.0F);
                var32.addVertexWithUV(var13, var14, var8, var29, var30);
                var32.addVertexWithUV(var13, var15, var8, var29, var31);
                var32.addVertexWithUV(var13, var15, var9, var28, var31);
                var32.addVertexWithUV(var13, var14, var9, var28, var30);
                var32.draw();
            }
        }

    }

    private void getOffset(AlphaEntityPainting var1, float var2, float var3) {
        int var4 = MathHelper.floor_double(var1.posX);
        int var5 = MathHelper.floor_double(var1.posY + (double) (var3 / 16.0F));
        int var6 = MathHelper.floor_double(var1.posZ);
        if (var1.direction == 0) {
            var4 = MathHelper.floor_double(var1.posX + (double) (var2 / 16.0F));
        }

        if (var1.direction == 1) {
            var6 = MathHelper.floor_double(var1.posZ - (double) (var2 / 16.0F));
        }

        if (var1.direction == 2) {
            var4 = MathHelper.floor_double(var1.posX - (double) (var2 / 16.0F));
        }

        if (var1.direction == 3) {
            var6 = MathHelper.floor_double(var1.posZ + (double) (var2 / 16.0F));
        }

        float var7 = this.renderManager.worldObj.getLightBrightness(var4, var5, var6);
        GL11.glColor3f(var7, var7, var7);
    }

    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
        this.renderPainting((AlphaEntityPainting) var1, var2, var4, var6, var8, var9);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return texture;
    }
}
