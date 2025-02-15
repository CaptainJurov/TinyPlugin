package com.example;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin
{
  public Map<String, Players> players;
  public Map<String, String> words;
  @Override
  public void onEnable()
  {
    Players.diseases = getConfig().getStringList("diseases");
    players = new HashMap<String, Players>();
    loadData();
    words = new HashMap<>();
    ConfigurationSection section = getConfig().getConfigurationSection("words");
    if (section != null) {
        for (String key : section.getKeys(false)) {
                String value = section.getString(key);
                this.words.put(key, value);
        }
    }
    getServer().getPluginManager().registerEvents(new com.example.EventListener(this), this);
    getServer().getPluginCommand("stats").setExecutor(new com.example.ComandHuila(this));
}
  @Override
  public void onDisable()
  {
    getServer().savePlayers();
    saveDataM();
  }


  public void saveDataM() {
    File file = new File(getDataFolder(), "players.yml");
    YamlConfiguration yaml = new YamlConfiguration();

    // Сохраняем данные
    for (Map.Entry<String, Players> entry : players.entrySet()) {
        Players player = entry.getValue();
        yaml.set("players." + entry.getKey() + ".zodiac", player.zodiac);
        yaml.set("players." + entry.getKey() + ".skintype", player.skintype);
        yaml.set("players." + entry.getKey() + ".iq", player.iq);
        yaml.set("players." + entry.getKey() + ".dicksize", player.dicksize);
        yaml.set("players." + entry.getKey() + ".diseases", player.disease);
    }

    try {
        yaml.save(file);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


public void loadData() {
    File file = new File(getDataFolder(), "players.yml");
    if (!file.exists()) {
        return;
    }

    YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

    // Загружаем данные
    if (yaml.contains("players")) {
    for (String key : yaml.getConfigurationSection("players").getKeys(false)) {
        ConfigurationSection playerConfig = yaml.getConfigurationSection("players." + key);
        
        // Создаем объект Players
        Players player = new Players();
        
        // Заполняем его данные
        player.dicksize = (float) playerConfig.getDouble("dicksize");
        player.skintype = playerConfig.getInt("skintype");
        player.zodiac = playerConfig.getInt("zodiac");
        player.iq = playerConfig.getInt("iq");
        player.disease = playerConfig.getStringList("diseases");
        
        // Добавляем игрока в список
        players.put(key, player);
    }
}
}
}

