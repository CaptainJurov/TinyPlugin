package com.example;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ComandHuila implements CommandExecutor {
    Plugin plugin;
    ComandHuila(Plugin plug) {
        this.plugin = plug;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (args.length==1) {
            Players pl = plugin.players.get(args[0]);
            if (pl==null) {
                sender.sendMessage("§4Игрок не найден");
                return false;
            }
            else {
                sender.sendMessage("§2Понос-плагин > Инфа об игроке "+args[0]+"\n"+showStats(pl));
                return true;
            }
        }
        else if (args.length==0) {
            Players pl = plugin.players.get(sender.getName());
            if (pl==null) {
                sender.sendMessage("§4Ты не зареган блять, перезайди на сервер");
                return false;
            }
            sender.sendMessage("§2Понос-плагин > Инфа об игроке "+sender.getName()+"\n"+showStats(pl));
            return true;
        }
        else {
            sender.sendMessage("§4Еблан, чё ты ввёл вообще\nСмотри /help по плагину ебантяй сука");
            return false; 
        }
    }
    public String showStats(Players player) {
        return "§7Знак зодиака - "+player.getZodiac()+"\nЦвет кожи - "+player.getSkinColor()+player.getSkin()+"\n§7IQ - "+player.iq+"\nПипиндр - "+String.format("%.1f",player.dicksize)+" см.\nСписок заболеваний:\n"+player.getDiseases();
    }
}
