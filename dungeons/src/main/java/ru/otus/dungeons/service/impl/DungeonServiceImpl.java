package ru.otus.dungeons.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Dungeon;
import ru.otus.dungeons.service.DungeonService;

@Service
@Slf4j
@RequiredArgsConstructor
public class DungeonServiceImpl implements DungeonService {
    private final Dungeon dungeon = new Dungeon(4);

    @Override
    public Dungeon getDungeon() {
        return dungeon;
    }
}
