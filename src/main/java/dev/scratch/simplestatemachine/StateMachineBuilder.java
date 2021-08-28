package dev.scratch.simplestatemachine;

import dev.scratch.simplestatemachine.events.EventHandler;
import dev.scratch.simplestatemachine.events.LoopEvent;
import dev.scratch.simplestatemachine.events.StateTransitionEvent;

public class StateMachineBuilder {
    private final StateMachine stateMachine;

    public StateMachineBuilder() {
        this.stateMachine = new StateMachine();
    }

    public StateMachineBuilder init(State state) {
        stateMachine.init(state);
        return this;
    }

    public StateMachineBuilder addLoopEndEvent(EventHandler<LoopEvent> e) {
        stateMachine.addLoopEndEvent(e);
        return this;
    }

    public StateMachineBuilder addLoopStartEvent(EventHandler<LoopEvent> e) {
        stateMachine.addLoopStartEvent(e);
        return this;
    }
    public StateMachineBuilder addTransitionEvent(EventHandler<StateTransitionEvent> e) {
        stateMachine.addTransitionEvent(e);
        return this;
    }

    public StateMachine build() {
        return stateMachine;
    }
}
