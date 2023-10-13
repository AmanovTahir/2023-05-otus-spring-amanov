package ru.otus.dungeons.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Dungeon;
import ru.otus.dungeons.domain.Item;
import ru.otus.dungeons.service.ItemService;

import java.util.Random;

@Service
public class ItemServiceImpl implements ItemService {
    private final Random random = new Random();

    @Override
    public Item getRandomItem(Dungeon dungeon) {
        if (!dungeon.getItems().isEmpty()) {
            int randomIndex = random.nextInt(dungeon.getItems().size());
            return dungeon.getItems().get(randomIndex);
        }
        return null;
    }
}
