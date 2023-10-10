package ru.otus.dungeons.service;

import ru.otus.dungeons.domain.Dungeon;
import ru.otus.dungeons.domain.Monster;

public interface MonsterService {

    Monster getRandomMonster(Dungeon dungeon);

    void killMonster(Dungeon dungeon, Monster monster);

    void hit(Monster monster, int damage);

    boolean isAlive(Monster monster);
}
