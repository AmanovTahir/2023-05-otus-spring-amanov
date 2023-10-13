package ru.otus.dungeons.handler;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Character;
import ru.otus.dungeons.domain.Dungeon;
import ru.otus.dungeons.domain.Monster;
import ru.otus.dungeons.service.CharacterService;
import ru.otus.dungeons.service.DungeonService;
import ru.otus.dungeons.service.ItemService;
import ru.otus.dungeons.service.MonsterService;

@Service
@RequiredArgsConstructor
@Slf4j
public class DungeonExploreHandler {
    private final CharacterService characterService;

    private final MonsterService monsterService;

    private final DungeonService dungeonService;

    private final ItemService itemService;

    public Character exploreDungeon(Character character) {
        return descendInDungeon(character);
    }

    private Character descendInDungeon(Character character) {
        Dungeon dungeon = dungeonService.getDungeon();
        log.info("{} начал спуск в подземелье.", character.getName());
        for (int i = 0; i < dungeon.getLevels(); i++) {
            if (!characterService.isAlive(character)) {
                return character;
            }
            Monster monster = monsterService.getRandomMonster(dungeon);
            if (monster != null) {
                log.info("{} встретил монстра {} на уровне {}.", character.getName(), monster.getName(), i + 1);
                fighting(character, monster, dungeon);
            } else {
                log.info("На уровне {} {} не встретил монстров.", i + 1, character.getName());
            }
        }
        return character;
    }

    private void fighting(Character character, Monster monster, Dungeon dungeon) {
        delay();
        while (monsterService.isAlive(monster) && characterService.isAlive(character)) {
            log.info("Монстер {} атакует", monster.getName());
            characterService.hit(character, monster.getHitPower());
            if (!characterService.isAlive(character)) {
                break;
            }
            log.info("Герой {} атакует", character.getName());
            monsterService.hit(monster, character.getHitPower());
        }
        if (characterService.isAlive(character)) {
            monsterService.killMonster(dungeon, monster);
            characterService.levelUp(character);
            characterService.addInventory(character, itemService.getRandomItem(dungeon));
        }
    }

    @SneakyThrows
    private void delay() {
        Thread.sleep(500);
    }
}
