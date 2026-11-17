package com.islamozroko.ambience;

import net.fabricmc.api.ClientModInitializer;

public class AmbienceMod implements ClientModInitializer {
    private static AmbienceConfig config;

    @Override
    public void onInitializeClient() {
        config = AmbienceConfig.load();
    }

    public static AmbienceConfig getConfig() {
        return config;
    }
}
