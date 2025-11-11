package com.noiamnotarobot.minecraftalpha.mixin;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.noiamnotarobot.minecraftalpha.MinecraftAlpha;

@Mixin(InventoryPlayer.class)
public abstract class MixinInventoryPlayer implements IInventory {

    @Inject(method = "getTotalArmorValue", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void addAlphaArmorProtection(CallbackInfoReturnable<Integer> cir, int i) {
        MinecraftAlpha.LOG.info("hhhh");
        cir.setReturnValue(1);
    }
}
