package dev.scratch.simplestatemachine.events;

import dev.scratch.simplestatemachine.State;

public class LoopEvent {

    private final State state;

    public LoopEvent(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
