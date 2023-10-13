package ru.otus.dungeons.service;

import ru.otus.dungeons.domain.Dungeon;
import ru.otus.dungeons.domain.Item;

public interface ItemService {
    Item getRandomItem(Dungeon dungeon);
}
