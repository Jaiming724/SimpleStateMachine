package dev.scratch.simplestatemachine;

public class StateBuilder {

    private State state;

    public StateBuilder() {
        state = new State();
    }

    public StateBuilder name(String name) {
        state.setName(name);
        return this;
    }

    public StateBuilder onEntry(Runnable runnable) {
        state.setOnEntry(runnable);
        return this;
    }

    public StateBuilder onExit(Runnable runnable) {
        state.setOnExit(runnable);
        return this;
    }

    public StateBuilder loop(Runnable runnable) {
        state.setLoop(runnable);
        return this;
    }

    public StateBuilder transition(Transition transition) {
        state.setTransition(transition);
        return this;
    }

    public State build() {
        return state;
    }
}
