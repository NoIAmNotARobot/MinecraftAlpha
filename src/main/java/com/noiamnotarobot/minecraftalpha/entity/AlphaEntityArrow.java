package com.noiamnotarobot.minecraftalpha.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

public class AlphaEntityArrow extends Entity {

    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile = Blocks.air;
    private boolean inData = false;
    public int arrowShake = 0;
    private EntityLivingBase shootingEntity;
    private int ticksInGround;
    private int ticksInAir = 0;

    public AlphaEntityArrow(World var1) {
        super(var1);
        this.setSize(0.5F, 0.5F);
    }

    public AlphaEntityArrow(World var1, EntityLivingBase var2) {
        super(var1);
        this.shootingEntity = var2;
        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(var2.posX, var2.posY, var2.posZ, var2.rotationYaw, var2.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.posY += 0.1F;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI)
            * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI)
            * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
        this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI);
        this.setArrowHeading(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
    }

    protected void entityInit() {}

    public void setArrowHeading(double var1, double var3, double var5, float var7, float var8) {
        float var9 = MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5);
        var1 /= var9;
        var3 /= var9;
        var5 /= var9;
        var1 += this.rand.nextGaussian() * (double) 0.0075F * (double) var8;
        var3 += this.rand.nextGaussian() * (double) 0.0075F * (double) var8;
        var5 += this.rand.nextGaussian() * (double) 0.0075F * (double) var8;
        var1 *= var7;
        var3 *= var7;
        var5 *= var7;
        this.motionX = var1;
        this.motionY = var3;
        this.motionZ = var5;
        float var10 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(var1, var5) * 180.0D
            / (double) ((float) Math.PI));
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(var3, var10) * 180.0D
            / (double) ((float) Math.PI));
        this.ticksInGround = 0;
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.arrowShake > 0) {
            --this.arrowShake;
        }

        if (this.inData) {
            Block var1 = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
            if (var1 == this.inTile) {
                ++this.ticksInGround;
                if (this.ticksInGround == 1200) {
                    this.setDead();
                }

                return;
            }

            this.inData = false;
            this.motionX *= this.rand.nextFloat() * 0.2F;
            this.motionY *= this.rand.nextFloat() * 0.2F;
            this.motionZ *= this.rand.nextFloat() * 0.2F;
            this.ticksInGround = 0;
            this.ticksInAir = 0;
        } else {
            ++this.ticksInAir;
        }

        Vec3 var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 var2 = Vec3
            .createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var15, var2);
        var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        if (var3 != null) {
            var2 = Vec3.createVectorHelper(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
        }

        Entity var4 = null;
        List<Entity> var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(
            this,
            this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ)
                .expand(1.0D, 1.0D, 1.0D));
        double var6 = 0.0D;

        float var10;
        for (Entity var9 : var5) {
            if (var9.canBeCollidedWith() && (var9 != this.shootingEntity || this.ticksInAir >= 5)) {
                var10 = 0.3F;
                AxisAlignedBB var11 = var9.boundingBox.expand(var10, var10, var10);
                MovingObjectPosition var12 = var11.calculateIntercept(var15, var2);
                if (var12 != null) {
                    double var13 = var15.distanceTo(var12.hitVec);
                    if (var13 < var6 || var6 == 0.0D) {
                        var4 = var9;
                        var6 = var13;
                    }
                }
            }
        }

        if (var4 != null) {
            var3 = new MovingObjectPosition(var4);
        }

        float var16;
        if (var3 != null) {
            if (var3.entityHit != null) {
                if (var3.entityHit.attackEntityFrom(
                    (new EntityDamageSourceIndirect("arrow", this, this.shootingEntity)).setProjectile(),
                    4)) {
                    this.worldObj.playSoundAtEntity(
                        this,
                        MinecraftAlpha.MODID + ":random.drr",
                        1.0F,
                        1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                    this.setDead();
                } else {
                    this.motionX *= -0.1F;
                    this.motionY *= -0.1F;
                    this.motionZ *= -0.1F;
                    this.rotationYaw += 180.0F;
                    this.prevRotationYaw += 180.0F;
                    this.ticksInAir = 0;
                }
            } else {
                this.xTile = var3.blockX;
                this.yTile = var3.blockY;
                this.zTile = var3.blockZ;
                this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
                this.motionX = (float) (var3.hitVec.xCoord - this.posX);
                this.motionY = (float) (var3.hitVec.yCoord - this.posY);
                this.motionZ = (float) (var3.hitVec.zCoord - this.posZ);
                var16 = MathHelper.sqrt_double(
                    this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                this.posX -= this.motionX / (double) var16 * (double) 0.05F;
                this.posY -= this.motionY / (double) var16 * (double) 0.05F;
                this.posZ -= this.motionZ / (double) var16 * (double) 0.05F;
                this.worldObj.playSoundAtEntity(
                    this,
                    MinecraftAlpha.MODID + ":random.drr",
                    1.0F,
                    1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                this.inData = true;
                this.arrowShake = 7;
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / (double) ((float) Math.PI));

        for (this.rotationPitch = (float) (Math.atan2(this.motionY, var16) * 180.0D
            / (double) ((float) Math.PI)); this.rotationPitch - this.prevRotationPitch
                < -180.0F; this.prevRotationPitch -= 360.0F) {}

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        float var17 = 0.99F;
        var10 = 0.03F;
        if (this.handleWaterMovement()) {
            for (int var18 = 0; var18 < 4; ++var18) {
                float var19 = 0.25F;
                this.worldObj.spawnParticle(
                    "bubble",
                    this.posX - this.motionX * (double) var19,
                    this.posY - this.motionY * (double) var19,
                    this.posZ - this.motionZ * (double) var19,
                    this.motionX,
                    this.motionY,
                    this.motionZ);
            }

            var17 = 0.8F;
        }

        this.motionX *= var17;
        this.motionY *= var17;
        this.motionZ *= var17;
        this.motionY -= var10;
        this.setPosition(this.posX, this.posY, this.posZ);
    }

    public void writeEntityToNBT(NBTTagCompound var1) {
        var1.setShort("xTile", (short) this.xTile);
        var1.setShort("yTile", (short) this.yTile);
        var1.setShort("zTile", (short) this.zTile);
        var1.setString("inTile", this.inTile.getUnlocalizedName());
        var1.setByte("shake", (byte) this.arrowShake);
        var1.setByte("inGround", (byte) (this.inData ? 1 : 0));
    }

    public void readEntityFromNBT(NBTTagCompound var1) {
        this.xTile = var1.getShort("xTile");
        this.yTile = var1.getShort("yTile");
        this.zTile = var1.getShort("zTile");
        this.inTile = Block.getBlockFromName(var1.getString("inTile"));
        this.arrowShake = var1.getByte("shake") & 255;
        this.inData = var1.getByte("inGround") == 1;
    }

    public void onCollideWithPlayer(EntityPlayer var1) {
        if (this.inData && this.shootingEntity == var1
            && this.arrowShake <= 0
            && var1.inventory.addItemStackToInventory(new ItemStack(AlphaItem.arrow, 1))) {
            this.worldObj.playSoundAtEntity(
                this,
                MinecraftAlpha.MODID + ":random.pop",
                0.2F,
                ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            var1.onItemPickup(this, 1);
            this.setDead();
        }

    }

    public float getShadowSize() {
        return 0.0F;
    }
}
