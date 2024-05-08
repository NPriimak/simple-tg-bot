package com.example.simpletgbot.update;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class DefaultUpdateTypeResolver implements UpdateTypeResolver {

    @Override
    public UpdateWithType resolve(Update update) {
         if(update.getMessage().isCommand()) {
             return new UpdateWithType(UpdateType.COMMAND, update);
         }

         throw new IllegalArgumentException("Шо-то непонятное и страшное");
    }
}
