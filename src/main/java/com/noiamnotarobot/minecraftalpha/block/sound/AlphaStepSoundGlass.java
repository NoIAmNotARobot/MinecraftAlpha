package com.noiamnotarobot.minecraftalpha.block.sound;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;

public class AlphaStepSoundGlass extends AlphaStepSound {

    public AlphaStepSoundGlass(String name, float volume, float frequency) {
        super(name, volume, frequency);
    }

    @Override
    public String getBreakSound() {
        return MinecraftAlpha.MODID + ":random.glass";
    }
}
