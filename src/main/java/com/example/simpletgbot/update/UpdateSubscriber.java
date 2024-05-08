package com.example.simpletgbot.update;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateSubscriber {

    void notify(Update update);
    UpdateType getType();
}
