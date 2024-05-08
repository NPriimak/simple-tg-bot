package com.example.simpletgbot.update;

import org.telegram.telegrambots.meta.api.objects.Update;

public record UpdateWithType(UpdateType type, Update update){}
