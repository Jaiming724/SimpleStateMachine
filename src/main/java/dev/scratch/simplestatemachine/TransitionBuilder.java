package dev.scratch.simplestatemachine;

import dev.scratch.simplestatemachine.conditions.CustomCondition;
import dev.scratch.simplestatemachine.conditions.TimedCondition;
import dev.scratch.simplestatemachine.util.Callback;

public class TransitionBuilder {
    private Transition transition;

    public TransitionBuilder() {
        transition = new Transition();
    }

    public TransitionBuilder name(String name) {
        transition.setName(name);
        return this;
    }

    public TransitionBuilder from(State state) {
        transition.setFrom(state);
        return this;
    }

    public TransitionBuilder to(State state) {
        transition.setTo(state);
        return this;
    }

    public Transition build() {
        return transition;
    }

    public TransitionBuilder timedTransition(double time, State state) {
        transition.addCondition(new TimedCondition(time), state);
        return this;
    }

    public TransitionBuilder customTransition(Callback callback, State state) {
        transition.addCondition(new CustomCondition(callback), state);
        return this;
    }
}
