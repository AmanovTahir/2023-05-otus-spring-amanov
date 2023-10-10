package ru.otus.dungeons.gateway;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.dungeons.domain.Character;

import java.util.List;

@MessagingGateway
public interface DungeonGateway {

    @Gateway(requestChannel = "characterChannel", replyChannel = "dungeonChannel")
    List<Character> explore(List<Character> characters);
}
