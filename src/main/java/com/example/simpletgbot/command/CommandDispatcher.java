package com.example.simpletgbot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandDispatcher {

    void dispatch(Update update);
}
