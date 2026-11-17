package com.islamozroko.ambience.mixin;

import com.islamozroko.ambience.AmbienceMod;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// ВОТ ЭТА СТРОКА БЫЛА ПОТЕРЯНА
@Mixin(ClientLevel.ClientLevelData.class)
public class AmbienceMixin {

    // 1. Кастомное время
    @Inject(method = "getDayTime", at = @At("HEAD"), cancellable = true)
    private void onGetTime(CallbackInfoReturnable<Long> cir) {
        if (AmbienceMod.getConfig().enabled && AmbienceMod.getConfig().customTime) {
            cir.setReturnValue((long) AmbienceMod.getConfig().time);
        }
    }

    // 2. Кастомный цвет тумана (если метод найдется)
    // ВАЖНО: если после этого вылезет ошибка про getFogColor, 
    // просто удали весь блок ниже (от @Mixin(FogRenderer.class) до конца).
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
