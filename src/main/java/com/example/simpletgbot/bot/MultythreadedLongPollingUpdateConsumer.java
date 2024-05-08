package com.example.simpletgbot.bot;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface MultythreadedLongPollingUpdateConsumer extends LongPollingUpdateConsumer {
    ExecutorService executor = Executors.newFixedThreadPool(
            10,
            new CustomizableThreadFactory("bot-pool-")
    );

    @Override
    default void consume(List<Update> updates) {
        updates.forEach(el -> executor.execute(() -> consume(el)));
    }

    void consume(Update update);
}
