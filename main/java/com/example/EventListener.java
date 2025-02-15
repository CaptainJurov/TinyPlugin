package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    private final Plugin plugin;
    public Map<Player, Integer> playerTasks = new HashMap<Player, Integer>();
    EventListener(Plugin plug) {this.plugin = plug;}
    @EventHandler
    public void login(PlayerJoinEvent e) {
        String nickname = e.getPlayer().getName();
        Players playerData = plugin.players.get(nickname);
        if (playerData == null) {
            playerData = new Players(true);
            plugin.players.put(nickname, playerData);
            e.setJoinMessage("§2"+nickname+" впервые у нас!\nПри рождении ему перепали следующие характеристики:\n\nЗнак зодиака - "+playerData.getZodiac()+"\nЦвет кожи - "+playerData.getSkin()+"\nIQ - "+playerData.iq+"\nПипиндр - "+String.format("%.1f",playerData.dicksize)+" см.\nСписок заболеваний:\n"+playerData.getDiseases());
        }
        else {
            e.setJoinMessage("§2+"+nickname);
        }
        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runner(e.getPlayer(), playerData), 0L, 150L);
        playerTasks.put(e.getPlayer(), taskId);
        plugin.saveDataM();
    }
    @EventHandler
    public void exit(PlayerQuitEvent e) {
        String nickname = e.getPlayer().getName();
        e.setQuitMessage("§4-"+nickname);
        Integer task = playerTasks.remove(e.getPlayer());
        if (task != null) {
            Bukkit.getScheduler().cancelTask(task);
        }
    }
    @EventHandler
    public void death(PlayerDeathEvent e) {
        e.setDeathMessage("§8Некролог > "+e.getDeathMessage());
    }
    @EventHandler
    public void message(PlayerChatEvent e) {
        String nickname = e.getPlayer().getName();
        Players playerData = plugin.players.get(nickname);
        e.setFormat(playerData.getSkinColor()+nickname+"§f > "+filteraze(e.getMessage()));
    }
    public String filteraze(String say) {
        String modifiedMessage = say.replaceAll("%", "%%");
        for (String key : plugin.words.keySet()) {
            modifiedMessage = Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)
                                      .matcher(modifiedMessage)
                                      .replaceAll("§7"+plugin.words.get(key)+"§f");
        }
        return modifiedMessage;
    } 
    
}