package com.example.simpletgbot.update;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static java.util.function.Predicate.not;

public interface UpdatePublisher {

    Map<UpdateType, List<UpdateSubscriber>> subscribers = new ConcurrentHashMap<>();
    default void notifySubscribers(UpdateWithType update) {
        ofNullable(subscribers.get(update.type()))
                .filter(not(Collection::isEmpty))
                .ifPresent(subs -> subs.forEach(el -> el.notify(update.update())));
    }

    default void subscribe(UpdateSubscriber subscriber) {
        subscribers.merge(
                subscriber.getType(),
                List.of(subscriber),
                (prev, next) -> Stream.concat(prev.stream(), next.stream()).toList()
        );
    }

    default void unsubscribe(UpdateType type, UpdateSubscriber subscriber) {
        subscribers.get(type)
                .remove(subscriber);
    }

}
