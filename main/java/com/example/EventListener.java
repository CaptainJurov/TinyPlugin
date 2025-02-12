package com.example;

import java.util.regex.Pattern;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    private final Plugin plugin;
    EventListener(Plugin plug) {this.plugin = plug;}
    @EventHandler
    public void login(PlayerJoinEvent e) {
        e.setJoinMessage("§2+"+e.getPlayer().getName());
    }
    @EventHandler
    public void leave(PlayerQuitEvent e) {
        e.setQuitMessage("§4-"+e.getPlayer().getName());
    }
    @EventHandler
    public void message(PlayerChatEvent e) {
        e.setMessage(filteraze("§f"+e.getMessage()));
        e.setFormat(e.getPlayer().getName()+" > "+e.getMessage());
    }
    @EventHandler
    public void death(PlayerDeathEvent e) {
        e.setDeathMessage("§8Некролог > "+e.getDeathMessage());
    }
    public String filteraze(String say) {
        String modifiedMessage = say;
        for (String key : plugin.words.keySet()) {
            modifiedMessage = Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)
                                      .matcher(modifiedMessage)
                                      .replaceAll("§7"+plugin.words.get(key)+"§f");
        }
        return modifiedMessage;
    } 
}