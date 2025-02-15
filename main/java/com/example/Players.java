package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

@SerializableAs("Players")
public class Players implements ConfigurationSerializable {
    public float dicksize;
    public int skintype;
    public int zodiac;
    public int iq;
    public List<String> disease;

    // Конструктор по умолчанию (обязателен для десериализации)
    Players() {
        this.disease = new ArrayList<>();
    }

    // Конструктор для создания нового игрока
    Players(boolean s) {
        this();
        this.dicksize = (float) Math.random() * 40;
        this.skintype = (int) (Math.random() * skintypes.length);
        this.zodiac = (int) (Math.random() * zodiac_arr.length);
        this.iq = (int) (Math.random() * 200);
        int[] counter = new int[diseases.size()];
        for (int i = 0; i < (int) (diseases.size()/2); i++) {
            int num = (int) (Math.random() * diseases.size() * 2);
            if (num < diseases.size()) {
                counter[num]++;
            }
        }
        boolean normis = true;
        for (int i = 0; i < diseases.size(); i++) {
            if (counter[i] > 1) {
                normis = false;
                disease.add(diseases.get(i) + " " + (counter[i] - 1) + " степени.");
            }
        }
        if (normis) {
            disease.add("В норме.");
        }
    }

    // Сериализация
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        data.put("dicksize", dicksize);
        data.put("skintype", skintype);
        data.put("zodiac", zodiac);
        data.put("iq", iq);
        data.put("disease", disease);
        return data;
    }
    public String getDiseases() {
        return String.join("\n", disease);
    }

    public String getSkin() {
        return skintypes[this.skintype];
    }

    public String getSkinColor() {
        switch (skintype) {
            case 0: return "§0";
            case 1: return "§8";
            case 2: return "§6";
            case 3: return "§7";
            case 4: return "§f";
            default: return "§f";
        }
    }

    public String getZodiac() {
        return zodiac_arr[this.zodiac];
    }
    public static String[] skintypes = {"Негр", "Смуглый", "Загорелый", "Белый", "Снежок"};
    public static String[] zodiac_arr = {
            "Овен", "Телец", "Близнецы", "Рак", "Лев", "Дева",
            "Весы", "Скорпион", "Стрелец", "Козерог", "Водолей", "Рыбы"
    };
    public static List<String> diseases;
}