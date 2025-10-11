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
    public static final Block sand = (new AlphaBlockSand()).setHardness(0.5F)
        .setStepSound(soundSandFootstep);
    public static final Block gravel = (new AlphaBlockGravel()).setHardness(0.6F)
        .setStepSound(soundGravelFootstep);
    // gravel
    // oreGold
    // oreIron
    // oreCoal
    // wood
    // leaves
    // sponge
    // glass
    // clothRed
    // clothOrange
    // clothYellow
    // clothChartreuse
    // clothGreen
    // clothSpringGreen
    // clothCyan
    // clothCapri
    // clothUltramarine
    // clothViolet
    // clothPurple
    // clothMagneta
    // clothRose
    // clothDarkGray
    // cloth
    // clockWhite
    // plantYellow
    // plantRed
    // mushroomBrown
    // muchroomRed
    // blockGold
    // blockSteel
    // stairDouble
    // stairSingle
    // brick
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
    // oreDiamond
    // blockDiamond
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

    public AlphaBlock(Material materialIn, String name) {
        super(materialIn);
        this.setBlockName(name);
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

        MinecraftAlpha.LOG.info("Registering blocks...");
        int blocksRegistered = 0;
        for (Block block : blocksList) {
            GameRegistry.registerBlock(block, block.getUnlocalizedName());
            blocksRegistered++;
        }
        MinecraftAlpha.LOG.info("Registered {} blocks!", blocksRegistered);
    }
}
