package ru.otus.dungeons.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.dungeons.domain.Character;
import ru.otus.dungeons.domain.Item;
import ru.otus.dungeons.service.CharacterService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CharacterServiceImpl implements CharacterService {
    private final Map<String, Character> characters = new HashMap<>();

    public CharacterServiceImpl() {
        characters.put("Warrior", new Character("Warrior", 150, 30, 5, 0.07));
        characters.put("Mage", new Character("Mage", 130, 25, 3, 0.10));
        characters.put("Rogue", new Character("Rogue", 100, 15, 4, 0.15));
    }

    @Override
    public void win(Character character) {
        log.info("Герой {} выбрался из подземелья! результат: {}", character.getName(), character);
    }

    @Override
    public void lost(Character character) {
        log.info("Герой {} не смог покинуть подземелье...", character.getName());
    }

    @Override
    public List<Character> getAll() {
        return characters.values().stream().toList();
    }

    @Override
    public void levelUp(Character character) {
        character.setLevel(character.getLevel() + 1);
        character.setHitPower(character.getHitPower() + 10);
        log.info("Герой {} повысел уровень: {}", character.getName(), character.getLevel());
    }

    @Override
    public void hit(Character character, int damage) {
        if (Math.random() > 0.15 + character.getAgility()) {
            int result = damage - character.getShield();
            character.setHealth(character.getHealth() - result);
            log.info("Герой {} получил урон: {}, осталось жизней: {}",
                    character.getName(), damage, character.getHealth());
        } else {
            log.info("Герой {} уклонился от удара", character.getName());
        }
    }

    @Override
    public boolean isAlive(Character character) {
        return character.getHealth() > 0;
    }

    @Override
    public void addInventory(Character character, Item item) {
        log.info("Герой {} нашел предмет: {}", character.getName(), item.getName());
        character.getInventory().add(item);
        executeItemBuff(character, item);
    }

    private void executeItemBuff(Character character, Item item) {
        switch (item.getName()) {
            case "Зелье лечения" -> character.setHealth(character.getHealth() + 25);
            case "Меч" -> character.setHitPower(character.getHitPower() + 15);
            case "Щит" -> character.setShield(character.getShield() + 5);
            default -> throw new IllegalStateException("Unexpected value: " + item.getName());
        }
    }
}
