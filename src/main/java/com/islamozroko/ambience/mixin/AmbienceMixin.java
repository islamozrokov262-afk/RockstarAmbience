package com.islamozroko.ambience.mixin;

import com.islamozroko.ambience.AmbienceMod;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ClientLevel.class, FogRenderer.class})
public class AmbienceMixin {

    // 1. Кастомное время
    @Inject(method = "getTimeOfDay", at = @At("HEAD"), cancellable = true)
    private void onGetTime(CallbackInfoReturnable<Long> cir) {
        if (AmbienceMod.getConfig().enabled && AmbienceMod.getConfig().customTime) {
            cir.setReturnValue((long) AmbienceMod.getConfig().time);
        }
    }

    // 2. Кастомный цвет тумана
    @Inject(method = "getFogColor", at = @At("HEAD"), cancellable = true)
    private static void onFogColor(FogType type, float partialTicks, CallbackInfoReturnable<Integer> cir) {
        if (AmbienceMod.getConfig().enabled && AmbienceMod.getConfig().customFog) {
            try {
                int color = Integer.parseInt(AmbienceMod.getConfig().fogColor.replace("#", ""), 16);
                cir.setReturnValue(color);
            } catch (NumberFormatException ignored) {}
        }
    }
}
