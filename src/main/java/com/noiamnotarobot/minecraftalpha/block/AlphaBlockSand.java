package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class AlphaBlockSand extends AlphaBlock {

    public static boolean fallInstantly = false;

    public AlphaBlockSand() {
        super(Material.sand, "sand");
    }

    public AlphaBlockSand(String name) {
        super(Material.sand, name);
    }

    @Override
    public void onBlockAdded(World var1, int var2, int var3, int var4) {
        var1.scheduleBlockUpdate(var2, var3, var4, this, tickRate());
    }

    @Override
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        var1.scheduleBlockUpdate(var2, var3, var4, this, tickRate());
    }

    @Override
    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        this.tryToFall(var1, var2, var3, var4);
    }

    private void tryToFall(World var1, int var2, int var3, int var4) {
        if (canFallBelow(var1, var2, var3 - 1, var4) && var3 >= 0) {
            EntityFallingBlock var8 = new EntityFallingBlock(
                var1,
                (float) var2 + 0.5F,
                (float) var3 + 0.5F,
                (float) var4 + 0.5F,
                this);
            if (fallInstantly) {
                while (!var8.isDead) {
                    var8.onUpdate();
                }
            } else {
                var1.spawnEntityInWorld(var8);
            }
        }

    }

    public int tickRate() {
        return 3;
    }

    public static boolean canFallBelow(World var0, int var1, int var2, int var3) {
        Block var4 = var0.getBlock(var1, var2, var3);
        if (var4 == Blocks.air) {
            return true;
        } else if (var4 == Blocks.fire) {
            return true;
        } else {
            Material var5 = var4.getMaterial();
            return var5 == Material.water || var5 == Material.lava;
        }
    }
}
