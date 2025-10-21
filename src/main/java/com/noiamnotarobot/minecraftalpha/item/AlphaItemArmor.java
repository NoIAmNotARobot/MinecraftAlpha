package com.noiamnotarobot.minecraftalpha.item;

public class AlphaItemArmor extends AlphaItem {

    private static final int[] damageReduceAmountArray = new int[] { 3, 8, 6, 3 };
    private static final int[] maxDamageArray = new int[] { 11, 16, 15, 13 };
    public final int armorLevel;
    public final int armorType;
    public final int damageReduceAmount;
    public final int renderIndex;

    public AlphaItemArmor(String name, int var2, int var3, int var4) {
        super(name, 1);
        this.armorLevel = var2;
        this.armorType = var4;
        this.renderIndex = var3;
        this.damageReduceAmount = damageReduceAmountArray[var4];
        this.setMaxDamage(maxDamageArray[var4] * 3 << var2);
    }
}
