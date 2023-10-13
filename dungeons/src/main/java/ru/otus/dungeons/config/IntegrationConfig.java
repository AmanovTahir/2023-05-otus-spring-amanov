package ru.otus.dungeons.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.PollerSpec;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.dungeons.handler.DungeonExploreHandler;

@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {
    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2);
    }

    @Bean
    public MessageChannelSpec<?, ?> characterChannel() {
        return MessageChannels.queue(10);
    }

    @Bean
    public MessageChannelSpec<?, ?> dungeonChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean
    public IntegrationFlow dungeonExplorationFlow(DungeonExploreHandler exploreHandler) {
        return IntegrationFlow.from(characterChannel())
                .split()
                .handle(exploreHandler, "exploreDungeon")
                .aggregate()
                .channel(dungeonChannel())
                .get();
    }
}
