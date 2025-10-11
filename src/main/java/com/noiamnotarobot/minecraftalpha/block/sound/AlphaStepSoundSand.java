package com.noiamnotarobot.minecraftalpha.block.sound;

public class AlphaStepSoundSand extends AlphaStepSound {

    public AlphaStepSoundSand(String name, float volume, float frequency) {
        super(name, volume, frequency);
    }

    @Override
    public String getBreakSound() {
        return "step.gravel";
    }
}
