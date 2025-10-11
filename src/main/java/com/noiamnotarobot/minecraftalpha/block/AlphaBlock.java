package com.noiamnotarobot.minecraftalpha.block;

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

    public static final Block stone = new AlphaBlockStone().setHardness(1.5F)
        .setResistance(10.0F)
        .setStepSound(soundStoneFootstep)
        .setBlockName("stone");
    // grass
    // dirt
    // cobblestone
    // planks
    // sapling
    // bedrock
    // water
    // lava
    // sand
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

    public AlphaBlock(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(MinecraftAlpha.tabAlpha);
    }

    public static void preInit() {
        GameRegistry.registerBlock(stone, "stone");
    }
}
