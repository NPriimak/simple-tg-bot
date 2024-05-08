package com.example.simpletgbot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.function.Predicate;

@Component
public class HelloCommandHandler implements CommandHandler {

    private final TelegramClient telegramClient;

    public HelloCommandHandler(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    @Override
    public void handle(Update update) {
        try {
            telegramClient.execute(
                    SendMessage.builder()
                            .chatId(update.getMessage().getChatId())
                            .text("Hello there")
                            .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCommand() {
        return "/hello";
    }
}
