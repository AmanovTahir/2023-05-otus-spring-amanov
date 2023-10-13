package ru.otus.dungeons.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Monster {
    private String name;

    private int level;

    private int health;

    private int hitPower;
}
