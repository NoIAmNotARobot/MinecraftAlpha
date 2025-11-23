package com.noiamnotarobot.minecraftalpha;

public enum AlphaEnumArt {

    KEBAB("Kebab", 16, 16, 0, 0),
    AZTEC("Aztec", 16, 16, 16, 0),
    ALBAN("Alban", 16, 16, 32, 0),
    AZTEC_2("Aztec2", 16, 16, 48, 0),
    BOMB("Bomb", 16, 16, 64, 0),
    PLANT("Plant", 16, 16, 80, 0),
    WASTELAND("Wasteland", 16, 16, 96, 0),
    POOL("Pool", 32, 16, 0, 32),
    COURBET("Courbet", 32, 16, 32, 32),
    SEA("Sea", 32, 16, 64, 32),
    SUNSET("Sunset", 32, 16, 96, 32),
    CREEBET("Creebet", 32, 16, 128, 32),
    WANDERER("Wanderer", 16, 32, 0, 64),
    GRAHAM("Graham", 16, 32, 16, 64),
    MATCH("Match", 32, 32, 0, 128),
    BUST("Bust", 32, 32, 32, 128),
    STAGE("Stage", 32, 32, 64, 128),
    VOID("Void", 32, 32, 96, 128),
    SKULL_AND_ROSES("SkullAndRoses", 32, 32, 128, 128),
    FIGHTERS("Fighters", 64, 32, 0, 96),
    POINTER("Pointer", 64, 64, 0, 192),
    PIGSCENE("Pigscene", 64, 64, 64, 192),
    SKELETON("Skeleton", 64, 48, 192, 64),
    DONKEY_KONG("DonkeyKong", 64, 48, 192, 112);

    public final String title;
    public final int sizeX;
    public final int sizeY;
    public final int offsetX;
    public final int offsetY;

    AlphaEnumArt(String var3, int var4, int var5, int var6, int var7) {
        this.title = var3;
        this.sizeX = var4;
        this.sizeY = var5;
        this.offsetX = var6;
        this.offsetY = var7;
    }
}
