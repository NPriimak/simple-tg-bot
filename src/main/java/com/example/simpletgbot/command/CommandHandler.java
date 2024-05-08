package com.example.simpletgbot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.Predicate;

public interface CommandHandler {

    void handle(Update update);

    String getCommand();
}
