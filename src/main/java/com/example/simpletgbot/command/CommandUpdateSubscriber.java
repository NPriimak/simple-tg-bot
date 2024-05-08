package com.example.simpletgbot.command;

import com.example.simpletgbot.update.UpdateSubscriber;
import com.example.simpletgbot.update.UpdateType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CommandUpdateSubscriber implements UpdateSubscriber {

    private final CommandDispatcher dispatcher;

    public CommandUpdateSubscriber(CommandDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void notify(Update update) {
        dispatcher.dispatch(update);
    }

    @Override
    public UpdateType getType() {
        return UpdateType.COMMAND;
    }
}
