package com.noiamnotarobot.minecraftalpha.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.AlphaEnumArt;
import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

public class AlphaEntityPainting extends Entity {

    private int tickCounter;
    public int direction;
    private int xPosition;
    private int yPosition;
    private int zPosition;
    public AlphaEnumArt art;

    public AlphaEntityPainting(World var1) {
        super(var1);
        this.tickCounter = 0;
        this.direction = 0;
        this.yOffset = 0.0F;
        this.setSize(0.5F, 0.5F);
        this.art = AlphaEnumArt.KEBAB;
    }

    public AlphaEntityPainting(World var1, int var2, int var3, int var4, int var5) {
        this(var1);
        this.xPosition = var2;
        this.yPosition = var3;
        this.zPosition = var4;
        ArrayList<AlphaEnumArt> var6 = new ArrayList<>();
        AlphaEnumArt[] var7 = AlphaEnumArt.values();

        for (AlphaEnumArt var10 : var7) {
            this.art = var10;
            this.setDirection(var5);
            if (this.onValidSurface()) {
                var6.add(var10);
            }
        }

        if (!var6.isEmpty()) {
            this.art = var6.get(this.rand.nextInt(var6.size()));
        }

        this.setDirection(var5);
    }

    protected void entityInit() {}

    public void setDirection(int var1) {
        this.direction = var1;
        this.prevRotationYaw = this.rotationYaw = (float) (var1 * 90);
        float var2 = (float) this.art.sizeX;
        float var3 = (float) this.art.sizeY;
        float var4 = (float) this.art.sizeX;
        if (var1 != 0 && var1 != 2) {
            var2 = 0.5F;
        } else {
            var4 = 0.5F;
        }

        var2 /= 32.0F;
        var3 /= 32.0F;
        var4 /= 32.0F;
        float var5 = (float) this.xPosition + 0.5F;
        float var6 = (float) this.yPosition + 0.5F;
        float var7 = (float) this.zPosition + 0.5F;
        float var8 = 9.0F / 16.0F;
        if (var1 == 0) {
            var7 -= var8;
        }

        if (var1 == 1) {
            var5 -= var8;
        }

        if (var1 == 2) {
            var7 += var8;
        }

        if (var1 == 3) {
            var5 += var8;
        }

        if (var1 == 0) {
            var5 -= this.getArtSize(this.art.sizeX);
        }

        if (var1 == 1) {
            var7 += this.getArtSize(this.art.sizeX);
        }

        if (var1 == 2) {
            var5 += this.getArtSize(this.art.sizeX);
        }

        if (var1 == 3) {
            var7 -= this.getArtSize(this.art.sizeX);
        }

        var6 += this.getArtSize(this.art.sizeY);
        this.setPosition(var5, var6, var7);
        float var9 = -(0.1F / 16.0F);
        this.boundingBox.setBounds(
            (var5 - var2 - var9),
            (var6 - var3 - var9),
            (var7 - var4 - var9),
            (var5 + var2 + var9),
            (var6 + var3 + var9),
            (var7 + var4 + var9));
    }

    private float getArtSize(int var1) {
        return var1 == 32 ? 0.5F : (var1 == 64 ? 0.5F : 0.0F);
    }

    public void onUpdate() {
        if (this.tickCounter++ == 100 && !this.onValidSurface()) {
            this.tickCounter = 0;
            this.setDead();
            this.worldObj.spawnEntityInWorld(
                new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(AlphaItem.painting)));
        }

    }

    public boolean onValidSurface() {
        if (!this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox)
            .isEmpty()) {
            return false;
        } else {
            int var1 = this.art.sizeX / 16;
            int var2 = this.art.sizeY / 16;
            int var3 = this.xPosition;
            int var4;
            int var5 = this.zPosition;
            if (this.direction == 0) {
                var3 = MathHelper.floor_double(this.posX - (double) ((float) this.art.sizeX / 32.0F));
            }

            if (this.direction == 1) {
                var5 = MathHelper.floor_double(this.posZ - (double) ((float) this.art.sizeX / 32.0F));
            }

            if (this.direction == 2) {
                var3 = MathHelper.floor_double(this.posX - (double) ((float) this.art.sizeX / 32.0F));
            }

            if (this.direction == 3) {
                var5 = MathHelper.floor_double(this.posZ - (double) ((float) this.art.sizeX / 32.0F));
            }

            var4 = MathHelper.floor_double(this.posY - (double) ((float) this.art.sizeY / 32.0F));

            int var7;
            for (int var6 = 0; var6 < var1; ++var6) {
                for (var7 = 0; var7 < var2; ++var7) {
                    Material var8;
                    if (this.direction != 0 && this.direction != 2) {
                        var8 = this.worldObj.getBlock(this.xPosition, var4 + var7, var5 + var6)
                            .getMaterial();
                    } else {
                        var8 = this.worldObj.getBlock(var3 + var6, var4 + var7, this.zPosition)
                            .getMaterial();
                    }

                    if (!var8.isSolid()) {
                        return false;
                    }
                }
            }

            List<Entity> var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox);

            for (var7 = 0; var7 < var9.size(); ++var7) {
                if (var9.get(var7) instanceof AlphaEntityPainting) {
                    return false;
                }
            }

            return true;
        }
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean attackEntityFrom(DamageSource var1, float var2) {
        this.setDead();
        this.worldObj.spawnEntityInWorld(
            new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(AlphaItem.painting)));
        return true;
    }

    public void writeEntityToNBT(NBTTagCompound var1) {
        var1.setByte("Dir", (byte) this.direction);
        var1.setString("Motive", this.art.title);
        var1.setInteger("TileX", this.xPosition);
        var1.setInteger("TileY", this.yPosition);
        var1.setInteger("TileZ", this.zPosition);
    }

    public void readEntityFromNBT(NBTTagCompound var1) {
        this.direction = var1.getByte("Dir");
        this.xPosition = var1.getInteger("TileX");
        this.yPosition = var1.getInteger("TileY");
        this.zPosition = var1.getInteger("TileZ");
        String var2 = var1.getString("Motive");
        AlphaEnumArt[] var3 = AlphaEnumArt.values();

        for (AlphaEnumArt var6 : var3) {
            if (var6.title.equals(var2)) {
                this.art = var6;
            }
        }

        if (this.art == null) {
            this.art = AlphaEnumArt.KEBAB;
        }

        this.setDirection(this.direction);
    }
}
