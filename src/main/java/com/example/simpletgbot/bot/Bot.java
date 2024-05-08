package com.example.simpletgbot.bot;

import com.example.simpletgbot.update.UpdatePublisher;
import com.example.simpletgbot.update.UpdateTypeResolver;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class Bot implements SpringLongPollingBot, MultythreadedLongPollingUpdateConsumer {

    private final String token;
    private final UpdatePublisher updatePublisher;
    private final UpdateTypeResolver typeResolver;

    public Bot(@Value("${application.bot.token}") String token,
               UpdatePublisher updatePublisher,
               UpdateTypeResolver typeResolver) {
        this.token = token;
        this.updatePublisher = updatePublisher;
        this.typeResolver = typeResolver;
    }

    @Override
    public void consume(Update update) {
        Try.of(() -> typeResolver.resolve(update))
                .andThen(updatePublisher::notifySubscribers)
                .onFailure(t -> log.error(t.getMessage()));
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }
}
