package ru.otus.dungeons.service;

import ru.otus.dungeons.domain.Character;
import ru.otus.dungeons.domain.Item;

import java.util.List;

public interface CharacterService {

    void win(Character character);

    void lost(Character character);

    List<Character> getAll();

    void levelUp(Character character);

    void hit(Character character, int damage);

    boolean isAlive(Character character);

    void addInventory(Character character, Item item);
}
