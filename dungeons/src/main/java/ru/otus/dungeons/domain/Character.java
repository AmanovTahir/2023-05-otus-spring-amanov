package ru.otus.dungeons.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Character {
    private String name;

    private int level;

    private int health;

    private int hitPower;

    private int shield;

    private double agility;

    private List<Item> inventory;

    public Character(String name, int health, int hitPower, int shield, double agility) {
        this.name = name;
        this.health = health;
        this.hitPower = hitPower;
        this.shield = shield;
        this.agility = agility;
        this.level = 1;
        this.inventory = new ArrayList<>();
    }
}
