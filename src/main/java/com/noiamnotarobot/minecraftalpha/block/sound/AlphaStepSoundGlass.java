package com.noiamnotarobot.minecraftalpha.block.sound;

public class AlphaStepSoundGlass extends AlphaStepSound {

    public AlphaStepSoundGlass(String name, float volume, float frequency) {
        super(name, volume, frequency);
    }

    @Override
    public String getBreakSound() {
        return "random.glass";
    }
}
