package com.example.simpletgbot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class DefaultCommandDispatcher implements CommandDispatcher {

    private final Map<String, CommandHandler> handlerMap;

    public DefaultCommandDispatcher(List<CommandHandler> handlers) {
        this.handlerMap = handlers.stream()
                .collect(Collectors.toMap(
                        CommandHandler::getCommand,
                        Function.identity()
                ));
    }

    @Override
    public void dispatch(Update update) {
        final var command = getCommand(update);
        ofNullable(handlerMap.get(command))
                .ifPresentOrElse(
                        handler -> handler.handle(update),
                        () -> {
                            throw new IllegalArgumentException("Обработчика для команды %s мы еще не придумали".formatted(command));
                        }
                );
    }

    private String getCommand(Update update) {
        return update.getMessage()
                .getText()
                .split(" ")[0];
    }
}
