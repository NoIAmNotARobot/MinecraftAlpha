package com.noiamnotarobot.minecraftalpha.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;

public class AlphaBlockRedstoneTorch extends AlphaBlockTorch {

    private final boolean torchActive;
    private static final ArrayList<RedstoneUpdateInfo> torchUpdates = new ArrayList<>();

    public IIcon getIcon(int var1, int var2) {
        return var1 == 1 ? AlphaBlock.redstoneWire.getIcon(var1, var2) : super.getIcon(var1, var2);
    }

    private boolean checkForBurnout(World var1, int var2, int var3, int var4, boolean var5) {
        if (var5) {
            torchUpdates.add(new RedstoneUpdateInfo(var2, var3, var4, var1.getWorldTime()));
        }

        int var6 = 0;

        for (RedstoneUpdateInfo var8 : torchUpdates) {
            if (var8.x == var2 && var8.y == var3 && var8.z == var4) {
                ++var6;
                if (var6 >= 8) {
                    return true;
                }
            }
        }

        return false;
    }

    protected AlphaBlockRedstoneTorch(String name, boolean var3) {
        super(name);
        this.torchActive = var3;
        this.setTickRandomly(true);
    }

    public int tickRate(World world) {
        return 2;
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4) {
        if (var1.getBlockMetadata(var2, var3, var4) == 0) {
            super.onBlockAdded(var1, var2, var3, var4);
        }

        if (this.torchActive) {
            var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
            var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
            var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
            var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
        }

    }

    public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int meta) {
        if (this.torchActive) {
            var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
            var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
            var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
            var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
        }
    }

    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion explosionIn) {
        if (this.torchActive) {
            var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
            var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
            var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
            var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
        }
    }

    public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        if (!this.torchActive) {
            return 0;
        } else {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            return var6 == 5 && var5 == 1 ? 0
                : (var6 == 3 && var5 == 3 ? 0
                    : (var6 == 4 && var5 == 2 ? 0 : (var6 == 1 && var5 == 5 ? 0 : (var6 != 2 || var5 != 4) ? 15 : 0)));
        }
    }

    private boolean isIndirectlyPowered(World var1, int var2, int var3, int var4) {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 5 && var1.getIndirectPowerOutput(var2, var3, var4, 0)
            || (var5 == 3 && var1.getIndirectPowerOutput(var2, var3, var4 - 1, 2)
                || (var5 == 4 && var1.getIndirectPowerOutput(var2, var3, var4 + 1, 3)
                    || (var5 == 1 && var1.getIndirectPowerOutput(var2 - 1, var3, var4, 4)
                        || var5 == 2 && var1.getIndirectPowerOutput(var2 + 1, var3, var4, 5))));
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        boolean var6 = this.isIndirectlyPowered(var1, var2, var3, var4);

        while (!torchUpdates.isEmpty() && var1.getWorldTime() - torchUpdates.get(0).updateTime > 100L) {
            torchUpdates.remove(0);
        }

        if (this.torchActive) {
            if (var6) {
                var1.setBlock(
                    var2,
                    var3,
                    var4,
                    AlphaBlock.torchRedstoneIdle,
                    var1.getBlockMetadata(var2, var3, var4),
                    3);
                if (this.checkForBurnout(var1, var2, var3, var4, true)) {
                    var1.playSoundEffect(
                        (float) var2 + 0.5F,
                        (float) var3 + 0.5F,
                        (float) var4 + 0.5F,
                        MinecraftAlpha.MODID + ":random.fizz",
                        0.5F,
                        2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);

                    for (int var7 = 0; var7 < 5; ++var7) {
                        double var8 = (double) var2 + var5.nextDouble() * 0.6D + 0.2D;
                        double var10 = (double) var3 + var5.nextDouble() * 0.6D + 0.2D;
                        double var12 = (double) var4 + var5.nextDouble() * 0.6D + 0.2D;
                        var1.spawnParticle("smoke", var8, var10, var12, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        } else if (!var6 && !this.checkForBurnout(var1, var2, var3, var4, false)) {
            var1.setBlock(var2, var3, var4, AlphaBlock.torchRedstoneActive, var1.getBlockMetadata(var2, var3, var4), 3);
        }

    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
        var1.scheduleBlockUpdate(var2, var3, var4, this, tickRate(var1));
    }

    public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        return var5 == 0 ? this.isProvidingWeakPower(var1, var2, var3, var4, var5) : 0;
    }

    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(AlphaBlock.torchRedstoneActive);
    }

    public boolean canProvidePower() {
        return true;
    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
        if (this.torchActive) {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            double var7 = (double) ((float) var2 + 0.5F) + (double) (var5.nextFloat() - 0.5F) * 0.2D;
            double var9 = (double) ((float) var3 + 0.7F) + (double) (var5.nextFloat() - 0.5F) * 0.2D;
            double var11 = (double) ((float) var4 + 0.5F) + (double) (var5.nextFloat() - 0.5F) * 0.2D;
            double var13 = 0.22F;
            double var15 = 0.27F;
            if (var6 == 1) {
                var1.spawnParticle("reddust", var7 - var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
            } else if (var6 == 2) {
                var1.spawnParticle("reddust", var7 + var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
            } else if (var6 == 3) {
                var1.spawnParticle("reddust", var7, var9 + var13, var11 - var15, 0.0D, 0.0D, 0.0D);
            } else if (var6 == 4) {
                var1.spawnParticle("reddust", var7, var9 + var13, var11 + var15, 0.0D, 0.0D, 0.0D);
            } else {
                var1.spawnParticle("reddust", var7, var9, var11, 0.0D, 0.0D, 0.0D);
            }

        }
    }

    static class RedstoneUpdateInfo {

        int x;
        int y;
        int z;
        long updateTime;

        public RedstoneUpdateInfo(int var1, int var2, int var3, long var4) {
            this.x = var1;
            this.y = var2;
            this.z = var3;
            this.updateTime = var4;
        }
    }
}
