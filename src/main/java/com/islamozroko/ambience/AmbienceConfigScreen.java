package com.islamozroko.ambience;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class AmbienceConfigScreen {
    public static Screen create(Screen parent) {
        AmbienceConfig config = AmbienceMod.getConfig();
        
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("Ambience"))
                .setSavingRunnable(config::save);
        
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory general = builder.getOrCreateCategory(Component.literal("Настройки"));
        
        general.addEntry(entryBuilder.startBooleanToggle(Component.literal("Включить"), config.enabled)
                .setDefaultValue(true).setSaveConsumer(v -> config.enabled = v).build());
                
        general.addEntry(entryBuilder.startBooleanToggle(Component.literal("Кастомное время"), config.customTime)
                .setDefaultValue(false).setSaveConsumer(v -> config.customTime = v).build());

        general.addEntry(entryBuilder.startIntSlider(Component.literal("Время"), config.time, 0, 24000)
                .setDefaultValue(18000).setSaveConsumer(v -> config.time = v).build());

        general.addEntry(entryBuilder.startBooleanToggle(Component.literal("Кастомный туман"), config.customFog)
                .setDefaultValue(false).setSaveConsumer(v -> config.customFog = v).build());

        general.addEntry(entryBuilder.startStrField(Component.literal("Цвет тумана (HEX)"), config.fogColor)
                .setDefaultValue("#FFD700").setSaveConsumer(v -> config.fogColor = v).build());
        
        return builder.build();
    }
}
