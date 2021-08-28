package dev.scratch.simplestatemachine.events;

public interface EventHandler<T> {

    void onEvent(T event);

    default void execute(T event) {
        try {
            onEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}