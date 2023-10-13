package ru.otus.dungeons.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Dungeon {
    private int levels;

    private List<Monster> monsters = new ArrayList<>();

    private List<Item> items = new ArrayList<>();

    public Dungeon(int levels) {
        this.levels = levels;
        generateMonsters();
        generateItems();
    }

    private void generateItems() {
        items.addAll(List.of(
                new Item("Зелье лечения", "Восстанавливает здоровье персонажа."),
                new Item("Меч", "Оружие для сражений."),
                new Item("Щит", "Защищает от атак врагов.")
        ));
    }

    private void generateMonsters() {
        monsters.addAll(List.of(
                new Monster("Гоблин", 3, 50, 10),
                new Monster("Орк", 5, 100, 25),
                new Monster("Дракон", 10, 300, 45),
                new Monster("Живой мертвец", 3, 30, 15),
                new Monster("Каменный голем", 9, 200, 35),
                new Monster("Вампир", 8, 80, 50),
                new Monster("Эльф-лучник", 4, 30, 70),
                new Monster("Скелет-воин", 7, 80, 30),
                new Monster("Скелет-лучник", 3, 30, 30)
        ));
    }
}
