package com.noiamnotarobot.minecraftalpha.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;
import com.noiamnotarobot.minecraftalpha.item.AlphaItem;

public class AlphaBlockSnowBlock extends AlphaBlock {

    public AlphaBlockSnowBlock() {
        super(Material.craftedSnow, "blockSnow");
        setTickRandomly(true);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return AlphaItem.snowball;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        return 4;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (world.getSavedLightValue(EnumSkyBlock.Block, x, y, z) > 11) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, Blocks.air, 0, 3);
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon(MinecraftAlpha.MODID + ":snow");
    }
}
