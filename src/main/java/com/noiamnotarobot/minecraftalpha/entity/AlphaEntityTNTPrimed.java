package com.noiamnotarobot.minecraftalpha.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class AlphaEntityTNTPrimed extends Entity {

    public int fuse;
    private boolean canTriggerWalking = true;

    public AlphaEntityTNTPrimed(World var1) {
        super(var1);
        this.fuse = 0;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
    }

    protected void entityInit() {}

    public AlphaEntityTNTPrimed(World var1, float var2, float var3, float var4) {
        this(var1);
        this.setPosition(var2, var3, var4);
        float var5 = (float) (Math.random() * (double) ((float) Math.PI) * 2.0D);
        this.motionX = -MathHelper.sin(var5 * (float) Math.PI / 180.0F) * 0.02F;
        this.motionY = 0.2F;
        this.motionZ = -MathHelper.cos(var5 * (float) Math.PI / 180.0F) * 0.02F;
        this.canTriggerWalking = false;
        this.fuse = 80;
        this.prevPosX = var2;
        this.prevPosY = var3;
        this.prevPosZ = var4;
    }

    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.04F;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.98F;
        this.motionY *= 0.98F;
        this.motionZ *= 0.98F;
        if (this.onGround) {
            this.motionX *= 0.7F;
            this.motionZ *= 0.7F;
            this.motionY *= -0.5D;
        }

        if (this.fuse-- <= 0) {
            this.setDead();
            this.explode();
        } else {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }

    }

    private void explode() {
        float var1 = 4.0F;
        this.worldObj.createExplosion(null, this.posX, this.posY, this.posZ, var1, false);
    }

    protected void writeEntityToNBT(NBTTagCompound var1) {
        var1.setByte("Fuse", (byte) this.fuse);
    }

    protected void readEntityFromNBT(NBTTagCompound var1) {
        this.fuse = var1.getByte("Fuse");
    }

    public float getShadowSize() {
        return 0.0F;
    }

    @Override
    protected boolean canTriggerWalking() {
        return canTriggerWalking;
    }
}
