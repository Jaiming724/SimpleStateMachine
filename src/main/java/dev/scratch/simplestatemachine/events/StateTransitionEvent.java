package dev.scratch.simplestatemachine.events;


import dev.scratch.simplestatemachine.State;
import dev.scratch.simplestatemachine.Transition;

public class StateTransitionEvent {

    private final State initialState;
    private State finalState;
    private Transition transition;


    public StateTransitionEvent(State initialState, State finalState, Transition transition) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.transition = transition;
    }

    public State getInitialState() {
        return initialState;
    }

    public State getFinalState() {
        return finalState;
    }

    public Transition getTransition() {
        return transition;
    }
}
