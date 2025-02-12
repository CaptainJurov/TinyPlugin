package com.example;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin
{
  public Map<String, String> words;
  @Override
  public void onEnable()
  {
    words = new HashMap<>();
    ConfigurationSection section = getConfig().getConfigurationSection("words");
        if (section != null) {
            for (String key : section.getKeys(false)) {
                String value = section.getString(key);
                this.words.put(key, value);
            }
        }
    getServer().getPluginManager().registerEvents(new com.example.EventListener(this), this);
  }
  @Override
  public void onDisable()
  {
    for (Player player : getServer().getOnlinePlayers()) {
      player.saveData();
    }
    getServer().savePlayers();
  }
}

