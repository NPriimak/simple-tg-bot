package com.example.simpletgbot.update;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateTypeResolver {

    UpdateWithType resolve(Update update);
}
