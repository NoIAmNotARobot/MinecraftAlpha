package com.noiamnotarobot.minecraftalpha.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.block.entity.AlphaTileEntities;
import com.noiamnotarobot.minecraftalpha.block.sound.AlphaStepSound;
import com.noiamnotarobot.minecraftalpha.block.sound.AlphaStepSoundGlass;
import com.noiamnotarobot.minecraftalpha.block.sound.AlphaStepSoundSand;

import cpw.mods.fml.common.registry.GameRegistry;

public class AlphaBlock extends Block {

    public static final AlphaStepSound soundPowderFootstep = new AlphaStepSound("stone", 1.0F, 1.0F);
    public static final AlphaStepSound soundWoodFootstep = new AlphaStepSound("wood", 1.0F, 1.0F);
    public static final AlphaStepSound soundGravelFootstep = new AlphaStepSound("gravel", 1.0F, 1.0F);
    public static final AlphaStepSound soundGrassFootstep = new AlphaStepSound("grass", 1.0F, 1.0F);
    public static final AlphaStepSound soundStoneFootstep = new AlphaStepSound("stone", 1.0F, 1.0F);
    public static final AlphaStepSound soundMetalFootstep = new AlphaStepSound("stone", 1.0F, 1.5F);
    public static final AlphaStepSound soundGlassFootstep = new AlphaStepSoundGlass("stone", 1.0F, 1.0F);
    public static final AlphaStepSound soundClothFootstep = new AlphaStepSound("cloth", 1.0F, 1.0F);
    public static final AlphaStepSound soundSandFootstep = new AlphaStepSoundSand("sand", 1.0F, 1.0F);

    public static List<Block> blocksList = new ArrayList<>();
    public static final AlphaBlock stone = (AlphaBlock) new AlphaBlockStone().setHardness(1.5F)
        .setResistance(10.0F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlockGrass grass = (AlphaBlockGrass) new AlphaBlockGrass().setHardness(0.6F)
        .setStepSound(soundGrassFootstep);
    public static final AlphaBlock dirt = (AlphaBlock) new AlphaBlockDirt().setHardness(0.5F)
        .setStepSound(soundGravelFootstep);
    public static final AlphaBlock cobblestone = (AlphaBlock) new AlphaBlock(Material.rock, "cobblestone")
        .setHardness(2.0F)
        .setResistance(10.0F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock planks = (AlphaBlock) (new AlphaBlock(Material.wood, "planks")).setHardness(2.0F)
        .setResistance(5.0F)
        .setStepSound(soundWoodFootstep);
    public static final AlphaBlock sapling = (AlphaBlock) new AlphaBlockSapling().setHardness(0.0F)
        .setStepSound(soundGrassFootstep);
    public static final AlphaBlock bedrock = (AlphaBlock) new AlphaBlock(Material.rock, "bedrock").setHardness(-1.0F)
        .setResistance(6000000.0F)
        .setStepSound(soundStoneFootstep);
    public static final Block waterMoving = Blocks.flowing_water;
    public static final Block waterStill = Blocks.water;
    public static final Block lavaMoving = Blocks.flowing_lava;
    public static final Block lavaStill = Blocks.lava;
    public static final AlphaBlock sand = (AlphaBlock) new AlphaBlockSand().setHardness(0.5F)
        .setStepSound(soundSandFootstep);
    public static final AlphaBlock gravel = (AlphaBlock) new AlphaBlockGravel().setHardness(0.6F)
        .setStepSound(soundGravelFootstep);
    public static final AlphaBlock oreGold = (AlphaBlock) new AlphaBlockOre("oreGold").setHardness(3.0F)
        .setResistance(5.0F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock oreIron = (AlphaBlock) new AlphaBlockOre("oreIron").setHardness(3.0F)
        .setResistance(5.0F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock oreCoal = (AlphaBlock) new AlphaBlockOre("oreCoal").setHardness(3.0F)
        .setResistance(5.0F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock wood = (AlphaBlock) new AlphaBlockLog().setHardness(2.0F)
        .setStepSound(soundWoodFootstep);
    public static final AlphaBlockLeaves leaves = (AlphaBlockLeaves) new AlphaBlockLeaves().setHardness(0.2F)
        .setLightOpacity(1)
        .setStepSound(soundTypeGrass);
    public static final AlphaBlock sponge = (AlphaBlock) new AlphaBlock(Material.sponge, "sponge").setHardness(0.6F)
        .setStepSound(soundTypeGrass);
    public static final AlphaBlock glass = (AlphaBlock) new AlphaBlockGlass("glass", false).setHardness(0.3F)
        .setStepSound(soundGlassFootstep);
    public static final AlphaBlock clothRed = null;
    public static final AlphaBlock clothOrange = null;
    public static final AlphaBlock clothYellow = null;
    public static final AlphaBlock clothChartreuse = null;
    public static final AlphaBlock clothGreen = null;
    public static final AlphaBlock clothSpringGreen = null;
    public static final AlphaBlock clothCyan = null;
    public static final AlphaBlock clothCapri = null;
    public static final AlphaBlock clothUltramarine = null;
    public static final AlphaBlock clothViolet = null;
    public static final AlphaBlock clothPurple = null;
    public static final AlphaBlock clothMagenta = null;
    public static final AlphaBlock clothRose = null;
    public static final AlphaBlock clothDarkGray = null;
    public static final AlphaBlock cloth = (AlphaBlock) new AlphaBlock(Material.cloth, "cloth").setHardness(0.8F)
        .setStepSound(soundClothFootstep);
    public static final AlphaBlock clothWhite = null;
    public static final AlphaBlockFlower plantYellow = (AlphaBlockFlower) new AlphaBlockFlower("plantYellow")
        .setHardness(0.0F)
        .setStepSound(soundTypeGrass);
    public static final AlphaBlockFlower plantRed = (AlphaBlockFlower) new AlphaBlockFlower("plantRed")
        .setHardness(0.0F)
        .setStepSound(soundTypeGrass);
    public static final AlphaBlockFlower mushroomBrown = (AlphaBlockFlower) new AlphaBlockMushroom("mushroomBrown")
        .setHardness(0.0F)
        .setStepSound(soundTypeGrass)
        .setLightLevel(0.125F);
    public static final AlphaBlockFlower mushroomRed = (AlphaBlockFlower) new AlphaBlockMushroom("mushroomRed")
        .setHardness(0.0F)
        .setStepSound(soundTypeGrass);
    public static final AlphaBlock blockGold = (AlphaBlock) new AlphaBlockOreBlock("blockGold").setHardness(3.0F)
        .setResistance(10.0F)
        .setStepSound(soundMetalFootstep);
    public static final AlphaBlock blockIron = (AlphaBlock) new AlphaBlockOreBlock("blockIron").setHardness(5.0F)
        .setResistance(10.0F)
        .setStepSound(soundMetalFootstep);
    public static final AlphaBlock stairDouble = (AlphaBlock) new AlphaBlockStep("stairDouble", true, false)
        .setHardness(2.0F)
        .setResistance(10.0F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock stairSingle = (AlphaBlock) new AlphaBlockStep("stairSingle", false, true)
        .setHardness(2.0F)
        .setResistance(10.0F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock brick = (AlphaBlock) new AlphaBlock(Material.rock, "brick").setHardness(2.0F)
        .setResistance(10.0F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock tnt = (AlphaBlock) new AlphaBlockTNT().setHardness(0.0F)
        .setStepSound(soundGrassFootstep);
    public static final AlphaBlock bookshelf = (AlphaBlock) new AlphaBlockBookshelf().setHardness(1.5F)
        .setStepSound(soundWoodFootstep);
    public static final AlphaBlock cobblestoneMossy = (AlphaBlock) new AlphaBlock(Material.rock, "cobblestoneMossy")
        .setHardness(2.0F)
        .setResistance(10.0F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock obsidian = (AlphaBlock) new AlphaBlock(Material.rock, "obsidian").setHardness(10.0F)
        .setResistance(2000.0F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock torch = (AlphaBlock) new AlphaBlockTorch("torch").setHardness(0.0F)
        .setLightLevel(15.0F / 16.0F)
        .setStepSound(soundWoodFootstep);
    public static final AlphaBlockFire fire = (AlphaBlockFire) new AlphaBlockFire().setHardness(0.0F)
        .setLightLevel(1.0F)
        .setStepSound(soundWoodFootstep);
    public static final AlphaBlock mobSpawner = (AlphaBlock) new AlphaBlockMobSpawner().setHardness(5.0F)
        .setStepSound(soundMetalFootstep);
    // stairCompactWood
    public static final AlphaBlock chest = (AlphaBlock) new AlphaBlockChest().setHardness(2.5F)
        .setStepSound(soundWoodFootstep);
    public static final AlphaBlock redstoneWire = (AlphaBlock) new AlphaBlockRedstoneWire().setHardness(0.0F)
        .setStepSound(soundPowderFootstep);
    public static final AlphaBlock oreDiamond = (AlphaBlock) new AlphaBlockOre("oreDiamond").setHardness(3.0F)
        .setResistance(5.0F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock blockDiamond = (AlphaBlock) new AlphaBlockOreBlock("blockDiamond").setHardness(5.0F)
        .setResistance(10.0F)
        .setStepSound(soundMetalFootstep);
    public static final AlphaBlock workbench = (AlphaBlock) new AlphaBlockWorkbench().setHardness(2.5F)
        .setStepSound(soundWoodFootstep);
    public static final AlphaBlock crops = (AlphaBlock) new AlphaBlockCrops().setHardness(0.0F)
        .setStepSound(soundTypeGrass);
    public static final AlphaBlock tilledField = (AlphaBlock) new AlphaBlockFarmland().setHardness(0.6F)
        .setStepSound(soundTypeGravel);
    public static final AlphaBlock stoneOvenIdle = (AlphaBlock) new AlphaBlockFurnace("stoneOvenIdle", false, true)
        .setHardness(3.5F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock stoneOvenActive = (AlphaBlock) new AlphaBlockFurnace("stoneOvenActive", true, false)
        .setHardness(3.5F)
        .setStepSound(soundStoneFootstep)
        .setLightLevel(14.0F / 16.0F);
    // signStanding
    public static final AlphaBlock doorWood = (AlphaBlock) new AlphaBlockDoor(Material.wood, "doorWood")
        .setHardness(3.0F)
        .setStepSound(soundWoodFootstep);
    public static final AlphaBlock ladder = (AlphaBlock) new AlphaBlockLadder().setHardness(0.4F)
        .setStepSound(soundWoodFootstep);
    // minecartTrack
    // stairCompactStone
    // signWall
    public static final AlphaBlock lever = (AlphaBlock) (new AlphaBlockLever()).setHardness(0.5F)
        .setStepSound(soundWoodFootstep);
    public static final AlphaBlock pressurePlateStone = (AlphaBlock) (new AlphaBlockPressurePlate(
        "pressurePlateStone",
        BlockPressurePlate.Sensitivity.mobs,
        stone.getTextureName())).setHardness(0.5F)
            .setStepSound(soundStoneFootstep);
    public static final AlphaBlock doorIron = new AlphaBlockDoor(Material.iron, "doorIron");
    public static final AlphaBlock pressurePlateWood = (AlphaBlock) (new AlphaBlockPressurePlate(
        "pressurePlateWood",
        BlockPressurePlate.Sensitivity.mobs,
        planks.getTextureName())).setHardness(0.5F)
            .setStepSound(soundWoodFootstep);
    public static final AlphaBlock oreRedstone = (AlphaBlock) new AlphaBlockRedstoneOre("oreRedstone", false)
        .setHardness(3.0F)
        .setResistance(5.0F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock oreRedstoneGlowing = (AlphaBlock) new AlphaBlockRedstoneOre(
        "oreRedstoneGlowing",
        true).setLightLevel(10F / 16F)
            .setHardness(3.0F)
            .setResistance(5.0F)
            .setStepSound(soundStoneFootstep);
    public static final AlphaBlock torchRedstoneIdle = (AlphaBlock) (new AlphaBlockRedstoneTorch(
        "torchRedstoneIdle",
        false)).setHardness(0.0F)
            .setStepSound(soundWoodFootstep);
    public static final AlphaBlock torchRedstoneActive = (AlphaBlock) (new AlphaBlockRedstoneTorch(
        "torchRedstoneActive",
        true)).setHardness(0.0F)
            .setLightLevel(0.5F)
            .setStepSound(soundWoodFootstep);
    public static final AlphaBlock button = (AlphaBlock) (new AlphaBlockButton()).setHardness(0.5F)
        .setStepSound(soundStoneFootstep);
    public static final AlphaBlock snow = (AlphaBlock) new AlphaBlockSnow().setHardness(0.1F)
        .setStepSound(soundClothFootstep);
    public static final AlphaBlock ice = (AlphaBlock) new AlphaBlockIce().setHardness(0.5F)
        .setLightOpacity(3)
        .setStepSound(soundGlassFootstep);
    public static final AlphaBlock blockSnow = (AlphaBlock) new AlphaBlockSnowBlock().setHardness(0.2F)
        .setStepSound(soundClothFootstep);
    public static final AlphaBlock cactus = (AlphaBlock) new AlphaBlockCactus().setHardness(0.4F)
        .setStepSound(soundClothFootstep);
    public static final AlphaBlock blockClay = (AlphaBlock) new AlphaBlockClay().setHardness(0.6F)
        .setStepSound(soundGravelFootstep);
    public static final AlphaBlock reed = (AlphaBlock) new AlphaBlockReed().setHardness(0.0F)
        .setStepSound(soundGrassFootstep);
    public static final AlphaBlock jukebox = (AlphaBlock) new AlphaBlockJukeBox().setHardness(2.0F)
        .setResistance(5.0F)
        .setStepSound(soundWoodFootstep);
    public static final Block fence = new AlphaBlockFence().setHardness(2.0F)
        .setResistance(5.0F)
        .setStepSound(soundWoodFootstep);

    protected String unlocalizedName;

    public AlphaBlock(Material materialIn, String name) {
        this(materialIn, name, true);
    }

    public AlphaBlock(Material materialIn, String name, boolean addToCreativeMenu) {
        super(materialIn);
        unlocalizedName = name;
        this.setBlockName(unlocalizedName);
        this.setBlockTextureName(MinecraftAlpha.MODID + ":" + name);
        if (addToCreativeMenu) this.setCreativeTab(MinecraftAlpha.tabAlpha);
    }

    public static void preInit() {
        blocksList.add(stone);
        blocksList.add(grass);
        blocksList.add(dirt);
        blocksList.add(cobblestone);
        blocksList.add(planks);
        blocksList.add(sapling);
        blocksList.add(bedrock);
        blocksList.add(sand);
        blocksList.add(gravel);
        blocksList.add(oreGold);
        blocksList.add(oreIron);
        blocksList.add(oreCoal);
        blocksList.add(wood);
        blocksList.add(leaves);
        blocksList.add(sponge);
        blocksList.add(glass);
        blocksList.add(cloth);
        blocksList.add(plantYellow);
        blocksList.add(plantRed);
        blocksList.add(mushroomBrown);
        blocksList.add(mushroomRed);
        blocksList.add(blockGold);
        blocksList.add(blockIron);
        blocksList.add(stairDouble);
        blocksList.add(stairSingle);
        blocksList.add(brick);
        blocksList.add(tnt);
        blocksList.add(bookshelf);
        blocksList.add(cobblestoneMossy);
        blocksList.add(obsidian);
        blocksList.add(torch);
        blocksList.add(fire);
        blocksList.add(mobSpawner);
        blocksList.add(chest);
        blocksList.add(redstoneWire);
        blocksList.add(oreDiamond);
        blocksList.add(blockDiamond);
        blocksList.add(workbench);
        blocksList.add(crops);
        blocksList.add(tilledField);
        blocksList.add(stoneOvenIdle);
        blocksList.add(stoneOvenActive);
        blocksList.add(doorWood);
        blocksList.add(ladder);
        blocksList.add(lever);
        blocksList.add(pressurePlateStone);
        blocksList.add(doorIron);
        blocksList.add(pressurePlateWood);
        blocksList.add(oreRedstone);
        blocksList.add(oreRedstoneGlowing);
        blocksList.add(torchRedstoneIdle);
        blocksList.add(torchRedstoneActive);
        blocksList.add(button);
        blocksList.add(snow);
        blocksList.add(ice);
        blocksList.add(blockSnow);
        blocksList.add(cactus);
        blocksList.add(blockClay);
        blocksList.add(reed);
        blocksList.add(jukebox);
        blocksList.add(fence);

        MinecraftAlpha.LOG.info("Registering blocks...");
        int blocksRegistered = 0;
        for (Block block : blocksList) {
            GameRegistry.registerBlock(block, block.getUnlocalizedName());
            blocksRegistered++;
        }
        MinecraftAlpha.LOG.info("Registered {} blocks!", blocksRegistered);
        MinecraftAlpha.LOG.info(
            "Of these, {} had tile entities that were successfully registered!",
            AlphaTileEntities.registerTileEntities());
    }

    @Override
    public String getUnlocalizedName() {
        return "tile." + MinecraftAlpha.MODID + "." + unlocalizedName;
    }
}
