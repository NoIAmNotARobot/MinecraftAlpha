package com.noiamnotarobot.minecraftalpha.block;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AlphaBlockFire extends AlphaBlock {

    private final HashMap<Block, Integer> chanceToEncourageFire = new HashMap<>();
    private final HashMap<Block, Integer> abilityToCatchFire = new HashMap<>();

    protected AlphaBlockFire() {
        super(Material.fire, "fire", false);
        this.initializeBlock(AlphaBlock.planks, 5, 20);
        this.initializeBlock(AlphaBlock.wood, 5, 5);
        this.initializeBlock(AlphaBlock.leaves, 30, 60);
        this.initializeBlock(AlphaBlock.bookshelf, 30, 20);
        this.initializeBlock(AlphaBlock.tnt, 15, 100);
        this.initializeBlock(AlphaBlock.cloth, 30, 60);
        this.setTickRandomly(true);
    }

    private void initializeBlock(Block var1, int var2, int var3) {
        this.chanceToEncourageFire.put(var1, var2);
        this.abilityToCatchFire.put(var1, var3);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 3;
    }

    public int quantityDropped(int var1, int var2, Random var3) {
        return 0;
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        if (var6 < 15) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6 + 1, 3);
            var1.scheduleBlockUpdate(var2, var3, var4, this, tickRate(var1));
        }

        if (!this.canNeighborBurn(var1, var2, var3, var4)) {
            if (!var1.isBlockNormalCubeDefault(var2, var3 - 1, var4, false) || var6 > 3) {
                var1.setBlock(var2, var3, var4, Blocks.air);
            }

        } else if (!this.canBlockCatchFire(var1, var2, var3 - 1, var4) && var6 == 15 && var5.nextInt(4) == 0) {
            var1.setBlock(var2, var3, var4, Blocks.air);
        } else {
            if (var6 % 2 == 0 && var6 > 2) {
                this.tryToCatchBlockOnFire(var1, var2 + 1, var3, var4, 300, var5);
                this.tryToCatchBlockOnFire(var1, var2 - 1, var3, var4, 300, var5);
                this.tryToCatchBlockOnFire(var1, var2, var3 - 1, var4, 200, var5);
                this.tryToCatchBlockOnFire(var1, var2, var3 + 1, var4, 250, var5);
                this.tryToCatchBlockOnFire(var1, var2, var3, var4 - 1, 300, var5);
                this.tryToCatchBlockOnFire(var1, var2, var3, var4 + 1, 300, var5);

                for (int var7 = var2 - 1; var7 <= var2 + 1; ++var7) {
                    for (int var8 = var4 - 1; var8 <= var4 + 1; ++var8) {
                        for (int var9 = var3 - 1; var9 <= var3 + 4; ++var9) {
                            if (var7 != var2 || var9 != var3 || var8 != var4) {
                                int var10 = 100;
                                if (var9 > var3 + 1) {
                                    var10 += (var9 - (var3 + 1)) * 100;
                                }

                                int var11 = this.getChanceOfNeighborsEncouragingFire(var1, var7, var9, var8);
                                if (var11 > 0 && var5.nextInt(var10) <= var11) {
                                    var1.setBlock(var7, var9, var8, this);
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    private void tryToCatchBlockOnFire(World var1, int var2, int var3, int var4, int var5, Random var6) {
        int var7 = this.abilityToCatchFire.getOrDefault(var1.getBlock(var2, var3, var4), 0);
        if (var6.nextInt(var5) < var7) {
            boolean var8 = var1.getBlock(var2, var3, var4) == AlphaBlock.tnt;
            if (var6.nextInt(2) == 0) {
                var1.setBlock(var2, var3, var4, this);
            } else {
                var1.setBlock(var2, var3, var4, Blocks.air);
            }

            if (var8) {
                AlphaBlock.tnt.onBlockDestroyedByPlayer(var1, var2, var3, var4, 0);
            }
        }

    }

    private boolean canNeighborBurn(World var1, int var2, int var3, int var4) {
        return this.canBlockCatchFire(var1, var2 + 1, var3, var4) || (this.canBlockCatchFire(var1, var2 - 1, var3, var4)
            || (this.canBlockCatchFire(var1, var2, var3 - 1, var4)
                || (this.canBlockCatchFire(var1, var2, var3 + 1, var4)
                    || (this.canBlockCatchFire(var1, var2, var3, var4 - 1)
                        || this.canBlockCatchFire(var1, var2, var3, var4 + 1)))));
    }

    private int getChanceOfNeighborsEncouragingFire(World var1, int var2, int var3, int var4) {
        byte var5 = 0;
        if (var1.getBlock(var2, var3, var4) != Blocks.air) {
            return 0;
        } else {
            int var6 = this.getChanceToEncourageFire(var1, var2 + 1, var3, var4, var5);
            var6 = this.getChanceToEncourageFire(var1, var2 - 1, var3, var4, var6);
            var6 = this.getChanceToEncourageFire(var1, var2, var3 - 1, var4, var6);
            var6 = this.getChanceToEncourageFire(var1, var2, var3 + 1, var4, var6);
            var6 = this.getChanceToEncourageFire(var1, var2, var3, var4 - 1, var6);
            var6 = this.getChanceToEncourageFire(var1, var2, var3, var4 + 1, var6);
            return var6;
        }
    }

    public boolean isCollidable() {
        return false;
    }

    public boolean canBlockCatchFire(IBlockAccess var1, int var2, int var3, int var4) {
        return this.chanceToEncourageFire.getOrDefault(var1.getBlock(var2, var3, var4), 0) > 0;
    }

    public int getChanceToEncourageFire(World var1, int var2, int var3, int var4, int var5) {
        int var6 = this.chanceToEncourageFire.getOrDefault(var1.getBlock(var2, var3, var4), 0);
        return Math.max(var6, var5);
    }

    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
        return var1.isBlockNormalCubeDefault(var2, var3 - 1, var4, false)
            || this.canNeighborBurn(var1, var2, var3, var4);
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        if (!var1.isBlockNormalCubeDefault(var2, var3 - 1, var4, false)
            && !this.canNeighborBurn(var1, var2, var3, var4)) {
            var1.setBlock(var2, var3, var4, Blocks.air);
        }
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4) {
        if (!var1.isBlockNormalCubeDefault(var2, var3 - 1, var4, false)
            && !this.canNeighborBurn(var1, var2, var3, var4)) {
            var1.setBlock(var2, var3, var4, Blocks.air);
        } else {
            var1.scheduleBlockUpdate(var2, var3, var4, this, tickRate(var1));
        }
    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
        if (var5.nextInt(24) == 0) {
            var1.playSoundEffect(
                (float) var2 + 0.5F,
                (float) var3 + 0.5F,
                (float) var4 + 0.5F,
                "fire.fire",
                1.0F + var5.nextFloat(),
                var5.nextFloat() * 0.7F + 0.3F);
        }

        int var6;
        float var7;
        float var8;
        float var9;
        if (!var1.isBlockNormalCubeDefault(var2, var3 - 1, var4, false)
            && !AlphaBlock.fire.canBlockCatchFire(var1, var2, var3 - 1, var4)) {
            if (AlphaBlock.fire.canBlockCatchFire(var1, var2 - 1, var3, var4)) {
                for (var6 = 0; var6 < 2; ++var6) {
                    var7 = (float) var2 + var5.nextFloat() * 0.1F;
                    var8 = (float) var3 + var5.nextFloat();
                    var9 = (float) var4 + var5.nextFloat();
                    var1.spawnParticle("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
                }
            }

            if (AlphaBlock.fire.canBlockCatchFire(var1, var2 + 1, var3, var4)) {
                for (var6 = 0; var6 < 2; ++var6) {
                    var7 = (float) (var2 + 1) - var5.nextFloat() * 0.1F;
                    var8 = (float) var3 + var5.nextFloat();
                    var9 = (float) var4 + var5.nextFloat();
                    var1.spawnParticle("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
                }
            }

            if (AlphaBlock.fire.canBlockCatchFire(var1, var2, var3, var4 - 1)) {
                for (var6 = 0; var6 < 2; ++var6) {
                    var7 = (float) var2 + var5.nextFloat();
                    var8 = (float) var3 + var5.nextFloat();
                    var9 = (float) var4 + var5.nextFloat() * 0.1F;
                    var1.spawnParticle("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
                }
            }

            if (AlphaBlock.fire.canBlockCatchFire(var1, var2, var3, var4 + 1)) {
                for (var6 = 0; var6 < 2; ++var6) {
                    var7 = (float) var2 + var5.nextFloat();
                    var8 = (float) var3 + var5.nextFloat();
                    var9 = (float) (var4 + 1) - var5.nextFloat() * 0.1F;
                    var1.spawnParticle("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
                }
            }

            if (AlphaBlock.fire.canBlockCatchFire(var1, var2, var3 + 1, var4)) {
                for (var6 = 0; var6 < 2; ++var6) {
                    var7 = (float) var2 + var5.nextFloat();
                    var8 = (float) (var3 + 1) - var5.nextFloat() * 0.1F;
                    var9 = (float) var4 + var5.nextFloat();
                    var1.spawnParticle("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
                }
            }
        } else {
            for (var6 = 0; var6 < 3; ++var6) {
                var7 = (float) var2 + var5.nextFloat();
                var8 = (float) var3 + var5.nextFloat() * 0.5F + 0.5F;
                var9 = (float) var4 + var5.nextFloat();
                var1.spawnParticle("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
            }
        }

    }
}
