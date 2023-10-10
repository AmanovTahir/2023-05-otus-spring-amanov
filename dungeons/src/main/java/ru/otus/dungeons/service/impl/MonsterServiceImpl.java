package ru.otus.dungeons.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Dungeon;
import ru.otus.dungeons.domain.Monster;
import ru.otus.dungeons.service.MonsterService;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class MonsterServiceImpl implements MonsterService {
    private final Random random = new Random();

    @Override
    public Monster getRandomMonster(Dungeon dungeon) {
        if (!dungeon.getMonsters().isEmpty()) {
            int randomIndex = random.nextInt(dungeon.getMonsters().size());
            return dungeon.getMonsters().get(randomIndex);
        }
        return null;
    }

    @Override
    public void killMonster(Dungeon dungeon, Monster monster) {
        log.info("Монстер {} умер", monster.getName());
        dungeon.getMonsters().remove(monster);
    }

    @Override
    public void hit(Monster monster, int damage) {
        if (Math.random() > 0.15) {
            monster.setHealth(monster.getHealth() - damage);
            log.info("Монстер {} получил урон: {}, осталось жизней: {}",
                    monster.getName(), damage, monster.getHealth());
        } else {
            log.info("Монстер {} уклонился от удара", monster.getName());
        }
    }

    @Override
    public boolean isAlive(Monster monster) {
        return monster.getHealth() > 0;
    }
}
