package ru.otus.dungeons.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Character;
import ru.otus.dungeons.gateway.DungeonGateway;
import ru.otus.dungeons.service.CharacterService;
import ru.otus.dungeons.service.Runner;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RunnerService implements Runner {
    private final DungeonGateway gateway;

    private final CharacterService characterService;

    @Override
    public void run() {
        List<Character> result = gateway.explore(characterService.getAll());
        result.forEach(character -> {
            if (characterService.isAlive(character)) {
                characterService.win(character);
            } else {
                characterService.lost(character);
            }
        });
    }
}
