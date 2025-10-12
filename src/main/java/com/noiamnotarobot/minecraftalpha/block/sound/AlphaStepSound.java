package com.noiamnotarobot.minecraftalpha.block.sound;

import net.minecraft.block.Block;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;

public class AlphaStepSound extends Block.SoundType {

    public AlphaStepSound(String name, float volume, float frequency) {
        super(name, volume, frequency);
    }

    @Override
    public String getBreakSound() {
        return MinecraftAlpha.MODID + ":step." + this.soundName;
    }

    @Override
    public String getStepResourcePath() {
        return MinecraftAlpha.MODID + ":step." + this.soundName;
    }
}
