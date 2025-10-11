package com.noiamnotarobot.minecraftalpha.block.sound;

import net.minecraft.block.Block;

public class AlphaStepSound extends Block.SoundType {

    public AlphaStepSound(String name, float volume, float frequency) {
        super(name, volume, frequency);
    }

    @Override
    public String getBreakSound() {
        return "step." + this.soundName;
    }
}
