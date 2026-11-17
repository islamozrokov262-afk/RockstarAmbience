package com.islamozroko.ambience.mixin;

import com.islamozroko.ambience.AmbienceMod;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class AmbienceMixin {

    // Миксин на вложенный класс данных времени
    @Mixin(ClientLevel.ClientLevelData.class)
    public static class TimeMixin {
        @Inject(method = "getDayTime", at = @At("HEAD"), cancellable = true)
        private void onGetDayTime(CallbackInfoReturnable<Long> cir) {
            if (AmbienceMod.getConfig().enabled && AmbienceMod.getConfig().customTime) {
                cir.setReturnValue((long) AmbienceMod.getConfig().time);
            }
        }
    }

    // Миксин на туман (если он скомпилировался, оставляем)
    @Mixin(FogRenderer.class)
    public static class FogMixin {
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
}
