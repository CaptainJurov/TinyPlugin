package com.example;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Runner implements Runnable {
    Player p;
    Players playerData;
    Runner(Player pl, Players playee) {
        this.p = pl;
        this.playerData = playee;
    }
    @Override
    public void run() {
            if (this.p.isOnline()) {
                giveEffectOnRace();
            }
            return;
    }
    private void giveEffectOnRace() {
        if (this.playerData==null) {
            return;
        }
        PotionEffectType pett = null;
        if (this.playerData.dicksize>25) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999, 0));
        }
        if (this.playerData.dicksize<10) {
            pett = PotionEffectType.JUMP;
        }
        else if (this.playerData.dicksize>35) {
            pett = PotionEffectType.INCREASE_DAMAGE;
        }
        if (pett!=null) {
            this.p.addPotionEffect(new PotionEffect(pett, 999, 0));
        }
        if (playerData.iq>150) {
            this.p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999, 0));
        }
        PotionEffectType pet = null;
        switch (this.playerData.skintype) {
            case 0: pet = PotionEffectType.FAST_DIGGING; break;
            case 1: pet = PotionEffectType.INCREASE_DAMAGE; break;
            case 2: pet = PotionEffectType.SPEED; break;
            case 3: pet = PotionEffectType.DAMAGE_RESISTANCE; break;
            case 4: pet = PotionEffectType.REGENERATION; break;
            default:
                break;
        }
        if (pet!=null) {
            this.p.addPotionEffect(new PotionEffect(pet, 999, 0));
        }
        
    }
}
