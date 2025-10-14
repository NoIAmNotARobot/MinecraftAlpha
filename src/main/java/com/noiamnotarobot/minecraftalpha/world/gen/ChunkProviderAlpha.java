package com.noiamnotarobot.minecraftalpha.world.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

import com.noiamnotarobot.minecraftalpha.Config;
import com.noiamnotarobot.minecraftalpha.block.AlphaBlock;
import com.noiamnotarobot.minecraftalpha.block.AlphaBlockSand;
import com.noiamnotarobot.minecraftalpha.world.gen.noise.NoiseGeneratorOctaves;

// Note to self: many blocks use vanilla versions for now.
// Make sure to fix this once the alpha versions are implemented.
// (Of course with the exception of air)
// Also that includes MapGenCaves.
public class ChunkProviderAlpha implements IChunkProvider {

    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen4;
    private NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves noiseGen7;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private double[] noiseArray;
    private double[] sandNoise = new double[256];
    private double[] gravelNoise = new double[256];
    private double[] stoneNoise = new double[256];
    private AlphaMapGenBase caveGenerator = new AlphaMapGenCaves();
    double[] noise3;
    double[] noise1;
    double[] noise2;
    double[] noise6;
    double[] noise7;
    int[][] unused = new int[32][32];

    public ChunkProviderAlpha(World world, long seed) {
        this.worldObj = world;
        this.rand = new Random(seed);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen7 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
    }

    public void generateTerrain(int chunkX, int chunkZ, Block[] chunk) {
        byte var4 = 4;
        byte var5 = 64;
        int var6 = var4 + 1;
        byte var7 = 17;
        int var8 = var4 + 1;
        this.noiseArray = this.initializeNoiseField(this.noiseArray, chunkX * var4, 0, chunkZ * var4, var6, var7, var8);

        for (int i = 0; i < var4; ++i) {
            for (int var10 = 0; var10 < var4; ++var10) {
                for (int var11 = 0; var11 < 16; ++var11) {
                    double var12 = 0.125D;
                    double var14 = this.noiseArray[((i + 0) * var8 + var10 + 0) * var7 + var11 + 0];
                    double var16 = this.noiseArray[((i + 0) * var8 + var10 + 1) * var7 + var11 + 0];
                    double var18 = this.noiseArray[((i + 1) * var8 + var10 + 0) * var7 + var11 + 0];
                    double var20 = this.noiseArray[((i + 1) * var8 + var10 + 1) * var7 + var11 + 0];
                    double var22 = (this.noiseArray[((i + 0) * var8 + var10 + 0) * var7 + var11 + 1] - var14) * var12;
                    double var24 = (this.noiseArray[((i + 0) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
                    double var26 = (this.noiseArray[((i + 1) * var8 + var10 + 0) * var7 + var11 + 1] - var18) * var12;
                    double var28 = (this.noiseArray[((i + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;

                    for (int var30 = 0; var30 < 8; ++var30) {
                        double var31 = 0.25D;
                        double var33 = var14;
                        double var35 = var16;
                        double var37 = (var18 - var14) * var31;
                        double var39 = (var20 - var16) * var31;

                        for (int var41 = 0; var41 < 4; ++var41) {
                            int var42 = var41 + i * 4 << 11 | var10 * 4 << 7 | var11 * 8 + var30;
                            short var43 = 128;
                            double var44 = 0.25D;
                            double var46 = var33;
                            double var48 = (var35 - var33) * var44;

                            for (int var50 = 0; var50 < 4; ++var50) {
                                Block var51 = Blocks.air;
                                if (var11 * 8 + var30 < var5) {
                                    if (Config.snowCovered && var11 * 8 + var30 >= var5 - 1) {
                                        var51 = AlphaBlock.ice;
                                    } else {
                                        var51 = Blocks.water;
                                    }
                                }

                                if (var46 > 0.0D) {
                                    var51 = AlphaBlock.stone;
                                }

                                chunk[var42] = var51;
                                var42 += var43;
                                var46 += var48;
                            }

                            var33 += var37;
                            var35 += var39;
                        }

                        var14 += var22;
                        var16 += var24;
                        var18 += var26;
                        var20 += var28;
                    }
                }
            }
        }

    }

    public void replaceSurfaceBlocks(int chunkX, int chunkZ, Block[] var3) {
        byte var4 = 64;
        double var5 = 1.0D / 32.0D;
        this.sandNoise = this.noiseGen4
            .generateNoiseOctaves(this.sandNoise, chunkX * 16, chunkZ * 16, 0.0D, 16, 16, 1, var5, var5, 1.0D);
        this.gravelNoise = this.noiseGen4
            .generateNoiseOctaves(this.gravelNoise, chunkZ * 16, 109.0134D, chunkX * 16, 16, 1, 16, var5, 1.0D, var5);
        this.stoneNoise = this.noiseGen5.generateNoiseOctaves(
            this.stoneNoise,
            chunkX * 16,
            chunkZ * 16,
            0.0D,
            16,
            16,
            1,
            var5 * 2.0D,
            var5 * 2.0D,
            var5 * 2.0D);

        for (int var7 = 0; var7 < 16; ++var7) {
            for (int var8 = 0; var8 < 16; ++var8) {
                boolean var9 = this.sandNoise[var7 + var8 * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
                boolean var10 = this.gravelNoise[var7 + var8 * 16] + this.rand.nextDouble() * 0.2D > 3.0D;
                int var11 = (int) (this.stoneNoise[var7 + var8 * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var12 = -1;
                Block var13 = AlphaBlock.grass;
                Block var14 = AlphaBlock.dirt;

                for (int var15 = 127; var15 >= 0; --var15) {
                    int var16 = (var7 * 16 + var8) * 128 + var15;
                    if (var15 <= this.rand.nextInt(6) - 1) {
                        var3[var16] = AlphaBlock.bedrock;
                    } else {
                        Block var17 = var3[var16];
                        if (var17 == Blocks.air) {
                            var12 = -1;
                        } else if (var17 == AlphaBlock.stone) {
                            if (var12 == -1) {
                                if (var11 <= 0) {
                                    var13 = Blocks.air;
                                    var14 = AlphaBlock.stone;
                                } else if (var15 >= var4 - 4 && var15 <= var4 + 1) {
                                    var13 = AlphaBlock.grass;
                                    var14 = AlphaBlock.dirt;
                                    if (var10) {
                                        var13 = Blocks.air;
                                    }

                                    if (var10) {
                                        var14 = AlphaBlock.gravel;
                                    }

                                    if (var9) {
                                        var13 = AlphaBlock.sand;
                                    }

                                    if (var9) {
                                        var14 = AlphaBlock.sand;
                                    }
                                }

                                if (var15 < var4 && var13 == Blocks.air) {
                                    var13 = Blocks.water;
                                }

                                var12 = var11;
                                if (var15 >= var4 - 1) {
                                    var3[var16] = var13;
                                } else {
                                    var3[var16] = var14;
                                }
                            } else if (var12 > 0) {
                                --var12;
                                var3[var16] = var14;
                            }
                        }
                    }
                }
            }
        }

    }

    private double[] initializeNoiseField(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        if (var1 == null) {
            var1 = new double[var5 * var6 * var7];
        }

        double var8 = 684.412D;
        double var10 = 684.412D;
        this.noise6 = this.noiseGen6
            .generateNoiseOctaves(this.noise6, var2, var3, var4, var5, 1, var7, 1.0D, 0.0D, 1.0D);
        this.noise7 = this.noiseGen7
            .generateNoiseOctaves(this.noise7, var2, var3, var4, var5, 1, var7, 100.0D, 0.0D, 100.0D);
        this.noise3 = this.noiseGen3.generateNoiseOctaves(
            this.noise3,
            var2,
            var3,
            var4,
            var5,
            var6,
            var7,
            var8 / 80.0D,
            var10 / 160.0D,
            var8 / 80.0D);
        this.noise1 = this.noiseGen1
            .generateNoiseOctaves(this.noise1, var2, var3, var4, var5, var6, var7, var8, var10, var8);
        this.noise2 = this.noiseGen2
            .generateNoiseOctaves(this.noise2, var2, var3, var4, var5, var6, var7, var8, var10, var8);
        int var12 = 0;
        int var13 = 0;

        for (int var14 = 0; var14 < var5; ++var14) {
            for (int var15 = 0; var15 < var7; ++var15) {
                double var16 = (this.noise6[var13] + 256.0D) / 512.0D;
                if (var16 > 1.0D) {
                    var16 = 1.0D;
                }

                double var18 = 0.0D;
                double var20 = this.noise7[var13] / 8000.0D;
                if (var20 < 0.0D) {
                    var20 = -var20;
                }

                var20 = var20 * 3.0D - 3.0D;
                if (var20 < 0.0D) {
                    var20 /= 2.0D;
                    if (var20 < -1.0D) {
                        var20 = -1.0D;
                    }

                    var20 /= 1.4D;
                    var20 /= 2.0D;
                    var16 = 0.0D;
                } else {
                    if (var20 > 1.0D) {
                        var20 = 1.0D;
                    }

                    var20 /= 6.0D;
                }

                var16 += 0.5D;
                var20 = var20 * (double) var6 / 16.0D;
                double var22 = (double) var6 / 2.0D + var20 * 4.0D;
                ++var13;

                for (int var24 = 0; var24 < var6; ++var24) {
                    double var25;
                    double var27 = ((double) var24 - var22) * 12.0D / var16;
                    if (var27 < 0.0D) {
                        var27 *= 4.0D;
                    }

                    double var29 = this.noise1[var12] / 512.0D;
                    double var31 = this.noise2[var12] / 512.0D;
                    double var33 = (this.noise3[var12] / 10.0D + 1.0D) / 2.0D;
                    if (var33 < 0.0D) {
                        var25 = var29;
                    } else if (var33 > 1.0D) {
                        var25 = var31;
                    } else {
                        var25 = var29 + (var31 - var29) * var33;
                    }

                    var25 -= var27;
                    double var35;
                    if (var24 > var6 - 4) {
                        var35 = (float) (var24 - (var6 - 4)) / 3.0F;
                        var25 = var25 * (1.0D - var35) + -10.0D * var35;
                    }

                    if ((double) var24 < var18) {
                        var35 = (var18 - (double) var24) / 4.0D;
                        if (var35 < 0.0D) {
                            var35 = 0.0D;
                        }

                        if (var35 > 1.0D) {
                            var35 = 1.0D;
                        }

                        var25 = var25 * (1.0D - var35) + -10.0D * var35;
                    }

                    var1[var12] = var25;
                    ++var12;
                }
            }
        }

        return var1;
    }

    @Override
    public boolean chunkExists(int chunkX, int chunkZ) {
        return true;
    }

    @Override
    public Chunk provideChunk(int chunkX, int chunkZ) {
        this.rand.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);
        Block[] blocks = new Block[-Short.MIN_VALUE];
        this.generateTerrain(chunkX, chunkZ, blocks);
        this.replaceSurfaceBlocks(chunkX, chunkZ, blocks);
        this.caveGenerator.generate(this, this.worldObj, chunkX, chunkZ, blocks);
        Chunk chunk = new Chunk(this.worldObj, blocks, chunkX, chunkZ);
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public Chunk loadChunk(int chunkX, int chunkZ) {
        return provideChunk(chunkX, chunkZ);
    }

    @Override
    public void populate(IChunkProvider provider, int chunkX, int chunkZ) {
        AlphaBlockSand.fallInstantly = true;
        int var4 = chunkX * 16;
        int var5 = chunkZ * 16;
        this.rand.setSeed(this.worldObj.getSeed());
        long var6 = this.rand.nextLong() / 2L * 2L + 1L;
        long var8 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) chunkX * var6 + (long) chunkZ * var8 ^ this.worldObj.getSeed());
        double var10;

        int var12;
        int var13;
        int var14;
        int var15;

        for (var12 = 0; var12 < 8; ++var12) {
            var13 = var4 + this.rand.nextInt(16) + 8;
            var14 = this.rand.nextInt(128);
            var15 = var5 + this.rand.nextInt(16) + 8;
            (new AlphaWorldGenDungeons()).generate(this.worldObj, this.rand, var13, var14, var15);
        }

        for (var12 = 0; var12 < 10; ++var12) {
            var13 = var4 + this.rand.nextInt(16);
            var14 = this.rand.nextInt(128);
            var15 = var5 + this.rand.nextInt(16);
            (new AlphaWorldGenClay(32)).generate(this.worldObj, this.rand, var13, var14, var15);
        }

        for (var12 = 0; var12 < 20; ++var12) {
            var13 = var4 + this.rand.nextInt(16);
            var14 = this.rand.nextInt(128);
            var15 = var5 + this.rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlock.dirt, 32)).generate(this.worldObj, this.rand, var13, var14, var15);
        }

        for (var12 = 0; var12 < 10; ++var12) {
            var13 = var4 + this.rand.nextInt(16);
            var14 = this.rand.nextInt(128);
            var15 = var5 + this.rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlock.gravel, 32)).generate(this.worldObj, this.rand, var13, var14, var15);
        }

        for (var12 = 0; var12 < 20; ++var12) {
            var13 = var4 + this.rand.nextInt(16);
            var14 = this.rand.nextInt(128);
            var15 = var5 + this.rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlock.oreCoal, 16)).generate(this.worldObj, this.rand, var13, var14, var15);
        }

        for (var12 = 0; var12 < 20; ++var12) {
            var13 = var4 + this.rand.nextInt(16);
            var14 = this.rand.nextInt(64);
            var15 = var5 + this.rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlock.oreIron, 8)).generate(this.worldObj, this.rand, var13, var14, var15);
        }

        for (var12 = 0; var12 < 2; ++var12) {
            var13 = var4 + this.rand.nextInt(16);
            var14 = this.rand.nextInt(32);
            var15 = var5 + this.rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlock.oreGold, 8)).generate(this.worldObj, this.rand, var13, var14, var15);
        }

        for (var12 = 0; var12 < 8; ++var12) {
            var13 = var4 + this.rand.nextInt(16);
            var14 = this.rand.nextInt(16);
            var15 = var5 + this.rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlock.oreRedstone, 7))
                .generate(this.worldObj, this.rand, var13, var14, var15);
        }

        for (var12 = 0; var12 < 1; ++var12) {
            var13 = var4 + this.rand.nextInt(16);
            var14 = this.rand.nextInt(16);
            var15 = var5 + this.rand.nextInt(16);
            (new AlphaWorldGenMinable(AlphaBlock.oreDiamond, 7))
                .generate(this.worldObj, this.rand, var13, var14, var15);
        }

        var10 = 0.5D;
        var12 = (int) ((this.mobSpawnerNoise.generateNoiseOctaves((double) var4 * var10, (double) var5 * var10) / 8.0D
            + this.rand.nextDouble() * 4.0D
            + 4.0D) / 3.0D);
        if (var12 < 0) {
            var12 = 0;
        }

        if (this.rand.nextInt(10) == 0) {
            ++var12;
        }

        AlphaWorldGenerator var18 = new AlphaWorldGenTrees();

        if (this.rand.nextInt(10) == 0) {
            var18 = new AlphaWorldGenBigTree();
        }

        int var16;
        for (var14 = 0; var14 < var12; ++var14) {
            var15 = var4 + this.rand.nextInt(16) + 8;
            var16 = var5 + this.rand.nextInt(16) + 8;
            var18.setScale(1.0D, 1.0D, 1.0D);
            var18.generate(this.worldObj, this.rand, var15, this.worldObj.getHeightValue(var15, var16), var16);
        }

        int var17;

        for (var14 = 0; var14 < 2; ++var14) {
            var15 = var4 + this.rand.nextInt(16) + 8;
            var16 = this.rand.nextInt(128);
            var17 = var5 + this.rand.nextInt(16) + 8;
            (new AlphaWorldGenFlowers(AlphaBlock.plantYellow)).generate(this.worldObj, this.rand, var15, var16, var17);
        }
        if (this.rand.nextInt(2) == 0) {
            var14 = var4 + this.rand.nextInt(16) + 8;
            var15 = this.rand.nextInt(128);
            var16 = var5 + this.rand.nextInt(16) + 8;
            (new AlphaWorldGenFlowers(AlphaBlock.plantRed)).generate(this.worldObj, this.rand, var14, var15, var16);
        }
        if (this.rand.nextInt(4) == 0) {
            var14 = var4 + this.rand.nextInt(16) + 8;
            var15 = this.rand.nextInt(128);
            var16 = var5 + this.rand.nextInt(16) + 8;
            (new AlphaWorldGenFlowers(AlphaBlock.mushroomBrown))
                .generate(this.worldObj, this.rand, var14, var15, var16);
        }
        if (this.rand.nextInt(8) == 0) {
            var14 = var4 + this.rand.nextInt(16) + 8;
            var15 = this.rand.nextInt(128);
            var16 = var5 + this.rand.nextInt(16) + 8;
            (new AlphaWorldGenFlowers(AlphaBlock.mushroomRed)).generate(this.worldObj, this.rand, var14, var15, var16);
        }

        for (var14 = 0; var14 < 10; ++var14) {
            var15 = var4 + this.rand.nextInt(16) + 8;
            var16 = this.rand.nextInt(128);
            var17 = var5 + this.rand.nextInt(16) + 8;
            (new AlphaWorldGenReed()).generate(this.worldObj, this.rand, var15, var16, var17);
        }
        for (var14 = 0; var14 < 1; ++var14) {
            var15 = var4 + this.rand.nextInt(16) + 8;
            var16 = this.rand.nextInt(128);
            var17 = var5 + this.rand.nextInt(16) + 8;
            (new AlphaWorldGenCactus()).generate(this.worldObj, this.rand, var15, var16, var17);
        }

        for (var14 = 0; var14 < 50; ++var14) {
            var15 = var4 + this.rand.nextInt(16) + 8;
            var16 = this.rand.nextInt(this.rand.nextInt(120) + 8);
            var17 = var5 + this.rand.nextInt(16) + 8;
            (new AlphaWorldGenLiquids(Blocks.flowing_water)).generate(this.worldObj, this.rand, var15, var16, var17);
        }
        for (var14 = 0; var14 < 20; ++var14) {
            var15 = var4 + this.rand.nextInt(16) + 8;
            var16 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(112) + 8) + 8);
            var17 = var5 + this.rand.nextInt(16) + 8;
            (new AlphaWorldGenLiquids(Blocks.flowing_lava)).generate(this.worldObj, this.rand, var15, var16, var17);
        }

        for (var14 = var4 + 8; var14 < var4 + 8 + 16; ++var14) {
            for (var15 = var5 + 8; var15 < var5 + 8 + 16; ++var15) {
                var16 = this.worldObj.getTopSolidOrLiquidBlock(var14, var15);
                if (Config.snowCovered && var16 > 0
                    && var16 < 128
                    && this.worldObj.getBlock(var14, var16, var15) == Blocks.air
                    && this.worldObj.getBlock(var14, var16 - 1, var15)
                        .getMaterial()
                        .isSolid()
                    && this.worldObj.getBlock(var14, var16 - 1, var15)
                        .getMaterial() != Material.ice) {
                    this.worldObj.setBlock(var14, var16, var15, AlphaBlock.snow);
                }
            }
        }

        AlphaBlockSand.fallInstantly = false;
    }

    @Override
    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public String makeString() {
        return "RandomLevelSource";
    }

    @Override
    public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_,
        int p_73155_3_, int p_73155_4_) {
        return new ArrayList<>();
    }

    @Override
    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_,
        int p_147416_5_) {
        return null;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void recreateStructures(int p_82695_1_, int p_82695_2_) {

    }

    @Override
    public void saveExtraData() {}
}
