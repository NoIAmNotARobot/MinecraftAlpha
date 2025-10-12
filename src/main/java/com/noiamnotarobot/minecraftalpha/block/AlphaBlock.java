package com.noiamnotarobot.minecraftalpha.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
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
    public static final Block stone = new AlphaBlockStone().setHardness(1.5F)
        .setResistance(10.0F)
        .setStepSound(soundStoneFootstep);
    public static final Block grass = new AlphaBlockGrass().setHardness(0.6F)
        .setStepSound(soundGrassFootstep);
    public static final Block dirt = new AlphaBlockDirt().setHardness(0.5F)
        .setStepSound(soundGravelFootstep);
    public static final Block cobblestone = new AlphaBlock(Material.rock, "cobblestone").setHardness(2.0F)
        .setResistance(10.0F)
        .setStepSound(soundStoneFootstep);
    public static final Block planks = (new AlphaBlock(Material.wood, "planks")).setHardness(2.0F)
        .setResistance(5.0F)
        .setStepSound(soundWoodFootstep);
    // sapling
    public static final Block bedrock = new AlphaBlock(Material.rock, "bedrock").setHardness(-1.0F)
        .setResistance(6000000.0F)
        .setStepSound(soundStoneFootstep);
    // water
    // lava
    public static final Block sand = new AlphaBlockSand().setHardness(0.5F)
        .setStepSound(soundSandFootstep);
    public static final Block gravel = new AlphaBlockGravel().setHardness(0.6F)
        .setStepSound(soundGravelFootstep);
    public static final Block oreGold = new AlphaBlockOre("oreGold").setHardness(3.0F)
        .setResistance(5.0F)
        .setStepSound(soundStoneFootstep);
    public static final Block oreIron = new AlphaBlockOre("oreIron").setHardness(3.0F)
        .setResistance(5.0F)
        .setStepSound(soundStoneFootstep);
    public static final Block oreCoal = new AlphaBlockOre("oreCoal").setHardness(3.0F)
        .setResistance(5.0F)
        .setStepSound(soundStoneFootstep);
    // wood
    // leaves
    public static final Block sponge = new AlphaBlock(Material.sponge, "sponge").setHardness(0.6F)
        .setStepSound(soundTypeGrass);
    public static final Block glass = new AlphaBlockGlass(Material.glass, "glass", false).setHardness(0.3F)
        .setStepSound(soundGlassFootstep);
    public static final Block clothRed = null;
    public static final Block clothOrange = null;
    public static final Block clothYellow = null;
    public static final Block clothChartreuse = null;
    public static final Block clothGreen = null;
    public static final Block clothSpringGreen = null;
    public static final Block clothCyan = null;
    public static final Block clothCapri = null;
    public static final Block clothUltramarine = null;
    public static final Block clothViolet = null;
    public static final Block clothPurple = null;
    public static final Block clothMagenta = null;
    public static final Block clothRose = null;
    public static final Block clothDarkGray = null;
    public static final Block cloth = new AlphaBlock(Material.cloth, "cloth").setHardness(0.8F)
        .setStepSound(soundClothFootstep);
    public static final Block clothWhite = null;
    // plantYellow
    // plantRed
    // mushroomBrown
    // muchroomRed
    public static final Block blockGold = new AlphaBlockOreBlock("blockGold").setHardness(3.0F)
        .setResistance(10.0F)
        .setStepSound(soundMetalFootstep);
    public static final Block blockIron = new AlphaBlockOreBlock("blockIron").setHardness(5.0F)
        .setResistance(10.0F)
        .setStepSound(soundMetalFootstep);
    // stairDouble
    // stairSingle
    public static final Block brick = new AlphaBlock(Material.rock, "brick").setHardness(2.0F)
        .setResistance(10.0F)
        .setStepSound(soundStoneFootstep);
    // tnt
    // bookshelf
    // cobblestoneMossy
    // obsidian
    // torch
    // fire
    // mobSpawner
    // stairCompactWood
    // chest
    // redstoneWire
    public static final Block oreDiamond = new AlphaBlockOre("oreDiamond").setHardness(3.0F)
        .setResistance(5.0F)
        .setStepSound(soundStoneFootstep);
    public static final Block blockDiamond = new AlphaBlockOreBlock("blockDiamond").setHardness(5.0F)
        .setResistance(10.0F)
        .setStepSound(soundMetalFootstep);
    // workbench
    // crops
    // tilledField
    // stoneOvenIdle
    // stoneOvenActive
    // signStanding
    // doorWood
    // ladder
    // minecartTrack
    // stairCompactStone
    // signWall
    // lever
    // pressurePlateStone
    // doorSteel
    // pressurePlateWood
    // oreRedstone
    // oreRedstoneGlowing
    // torchRedstoneIdle
    // torchRedstoneActive
    // button
    // snow
    // ice
    // blockSnow
    // cactus
    // blockClay
    // reed
    // jukebox
    // fence

    protected String unlocalizedName;

    public AlphaBlock(Material materialIn, String name) {
        super(materialIn);
        unlocalizedName = name;
        this.setBlockName(unlocalizedName);
        this.setBlockTextureName(MinecraftAlpha.MODID + ":" + name);
        this.setCreativeTab(MinecraftAlpha.tabAlpha);
    }

    public static void preInit() {
        blocksList.add(stone);
        blocksList.add(grass);
        blocksList.add(dirt);
        blocksList.add(cobblestone);
        blocksList.add(planks);
        blocksList.add(bedrock);
        blocksList.add(sand);
        blocksList.add(gravel);
        blocksList.add(oreGold);
        blocksList.add(oreIron);
        blocksList.add(oreCoal);
        blocksList.add(sponge);
        blocksList.add(glass);
        blocksList.add(cloth);
        blocksList.add(blockGold);
        blocksList.add(blockIron);
        blocksList.add(brick);
        blocksList.add(oreDiamond);
        blocksList.add(blockDiamond);

        MinecraftAlpha.LOG.info("Registering blocks...");
        int blocksRegistered = 0;
        for (Block block : blocksList) {
            GameRegistry.registerBlock(block, block.getUnlocalizedName());
            blocksRegistered++;
        }
        MinecraftAlpha.LOG.info("Registered {} blocks!", blocksRegistered);
    }

    @Override
    public String getUnlocalizedName() {
        return "tile." + MinecraftAlpha.MODID + "." + unlocalizedName;
    }
}
