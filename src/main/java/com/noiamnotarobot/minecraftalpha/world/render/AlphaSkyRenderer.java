package com.noiamnotarobot.minecraftalpha.world.render;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IRenderHandler;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AlphaSkyRenderer extends IRenderHandler {

    private final int starGLCallList;
    private final int glSkyList;
    private final int glSkyList2;

    @SideOnly(Side.CLIENT)
    public AlphaSkyRenderer() {
        this.starGLCallList = GLAllocation.generateDisplayLists(3);
        GL11.glPushMatrix();
        GL11.glNewList(this.starGLCallList, GL11.GL_COMPILE);
        this.renderStars();
        GL11.glEndList();
        GL11.glPopMatrix();
        Tessellator var4 = Tessellator.instance;
        this.glSkyList = this.starGLCallList + 1;
        GL11.glNewList(this.glSkyList, GL11.GL_COMPILE);
        byte var6 = 64;
        int var7 = 256 / var6 + 2;
        float var5 = 16.0F;

        int var8;
        int var9;
        for (var8 = -var6 * var7; var8 <= var6 * var7; var8 += var6) {
            for (var9 = -var6 * var7; var9 <= var6 * var7; var9 += var6) {
                var4.startDrawingQuads();
                var4.addVertex((double) (var8 + 0), (double) var5, (double) (var9 + 0));
                var4.addVertex((double) (var8 + var6), (double) var5, (double) (var9 + 0));
                var4.addVertex((double) (var8 + var6), (double) var5, (double) (var9 + var6));
                var4.addVertex((double) (var8 + 0), (double) var5, (double) (var9 + var6));
                var4.draw();
            }
        }

        GL11.glEndList();
        this.glSkyList2 = this.starGLCallList + 2;
        GL11.glNewList(this.glSkyList2, GL11.GL_COMPILE);
        var5 = -16.0F;
        var4.startDrawingQuads();

        for (var8 = -var6 * var7; var8 <= var6 * var7; var8 += var6) {
            for (var9 = -var6 * var7; var9 <= var6 * var7; var9 += var6) {
                var4.addVertex((var8 + var6), var5, (var9));
                var4.addVertex((var8), var5, (var9));
                var4.addVertex((var8), var5, (var9 + var6));
                var4.addVertex((var8 + var6), var5, (var9 + var6));
            }
        }

        var4.draw();
        GL11.glEndList();
    }

    private void renderStars() {
        Random var1 = new Random(10842L);
        Tessellator var2 = Tessellator.instance;
        var2.startDrawingQuads();

        for (int var3 = 0; var3 < 1500; ++var3) {
            double var4 = (var1.nextFloat() * 2.0F - 1.0F);
            double var6 = (var1.nextFloat() * 2.0F - 1.0F);
            double var8 = (var1.nextFloat() * 2.0F - 1.0F);
            double var10 = (0.25F + var1.nextFloat() * 0.25F);
            double var12 = var4 * var4 + var6 * var6 + var8 * var8;
            if (var12 < 1.0D && var12 > 0.01D) {
                var12 = 1.0D / Math.sqrt(var12);
                var4 *= var12;
                var6 *= var12;
                var8 *= var12;
                double var14 = var4 * 100.0D;
                double var16 = var6 * 100.0D;
                double var18 = var8 * 100.0D;
                double var20 = Math.atan2(var4, var8);
                double var22 = Math.sin(var20);
                double var24 = Math.cos(var20);
                double var26 = Math.atan2(Math.sqrt(var4 * var4 + var8 * var8), var6);
                double var28 = Math.sin(var26);
                double var30 = Math.cos(var26);
                double var32 = var1.nextDouble() * Math.PI * 2.0D;
                double var34 = Math.sin(var32);
                double var36 = Math.cos(var32);

                for (int var38 = 0; var38 < 4; ++var38) {
                    double var39 = 0.0D;
                    double var41 = (double) ((var38 & 2) - 1) * var10;
                    double var43 = (double) ((var38 + 1 & 2) - 1) * var10;
                    double var47 = var41 * var36 - var43 * var34;
                    double var49 = var43 * var36 + var41 * var34;
                    double var53 = var47 * var28 + var39 * var30;
                    double var55 = var39 * var28 - var47 * var30;
                    double var57 = var55 * var22 - var49 * var24;
                    double var61 = var49 * var22 + var55 * var24;
                    var2.addVertex(var14 + var57, var16 + var53, var18 + var61);
                }
            }
        }

        var2.draw();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(float var1, WorldClient world, Minecraft mc) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Vec3 var2 = world.getSkyColor(mc.thePlayer, var1);
        float var3 = (float) var2.xCoord;
        float var4 = (float) var2.yCoord;
        float var5 = (float) var2.zCoord;
        float var7;
        float var8;
        if (mc.gameSettings.anaglyph) {
            float var6 = (var3 * 30.0F + var4 * 59.0F + var5 * 11.0F) / 100.0F;
            var7 = (var3 * 30.0F + var4 * 70.0F) / 100.0F;
            var8 = (var3 * 30.0F + var5 * 70.0F) / 100.0F;
            var3 = var6;
            var4 = var7;
            var5 = var8;
        }

        GL11.glColor3f(var3, var4, var5);
        Tessellator var12 = Tessellator.instance;
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glColor3f(var3, var4, var5);
        GL11.glCallList(glSkyList);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        GL11.glPushMatrix();
        var7 = 0.0F;
        var8 = 0.0F;
        float var9 = 0.0F;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef(var7, var8, var9);
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(world.getCelestialAngle(var1) * 360.0F, 1.0F, 0.0F, 0.0F);
        float var10 = 30.0F;
        mc.renderEngine.bindTexture(new ResourceLocation("textures/environment/sun.png"));
        var12.startDrawingQuads();
        var12.addVertexWithUV((-var10), 100.0D, (-var10), 0.0D, 0.0D);
        var12.addVertexWithUV(var10, 100.0D, (-var10), 1.0D, 0.0D);
        var12.addVertexWithUV(var10, 100.0D, var10, 1.0D, 1.0D);
        var12.addVertexWithUV((-var10), 100.0D, var10, 0.0D, 1.0D);
        var12.draw();
        var10 = 20.0F;
        mc.renderEngine.bindTexture(new ResourceLocation("textures/environment/moon_phases.png"));
        var12.startDrawingQuads();
        var12.addVertexWithUV((-var10), -100.0D, var10, 0.25D, 0.5D);
        var12.addVertexWithUV(var10, -100.0D, var10, 0.0D, 0.5D);
        var12.addVertexWithUV(var10, -100.0D, (-var10), 0.0D, 0.0D);
        var12.addVertexWithUV((-var10), -100.0D, (-var10), 0.25D, 0.0D);
        var12.draw();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        float var11 = world.getStarBrightness(var1);
        if (var11 > 0.0F) {
            GL11.glColor4f(var11, var11, var11, var11);
            GL11.glCallList(starGLCallList);
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glPopMatrix();
        GL11.glColor3f(var3 * 0.2F + 0.04F, var4 * 0.2F + 0.04F, var5 * 0.6F + 0.1F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glCallList(glSkyList2);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
    }
}
