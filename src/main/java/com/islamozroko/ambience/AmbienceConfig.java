package com.islamozroko.ambience;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AmbienceConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("ambience.json").toFile();

    public boolean enabled = true;
    public boolean customTime = false;
    public int time = 18000; // Закат
    public boolean customFog = false;
    public String fogColor = "#FFD700"; // Золотой

    public static AmbienceConfig load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                AmbienceConfig config = GSON.fromJson(reader, AmbienceConfig.class);
                if (config != null) return config;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AmbienceConfig config = new AmbienceConfig();
        config.save();
        return config;
    }

    public void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
