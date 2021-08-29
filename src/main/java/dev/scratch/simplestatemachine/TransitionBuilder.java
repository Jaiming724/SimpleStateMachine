package dev.scratch.simplestatemachine;

import dev.scratch.simplestatemachine.conditions.CustomCondition;
import dev.scratch.simplestatemachine.conditions.TimedCondition;

import java.util.function.BooleanSupplier;

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

    public Transition build() {
        return transition;
    }

    public TransitionBuilder timedTransition(double time, State state) {
        transition.addCondition(new TimedCondition(time, "TimedCondition"), state);
        return this;
    }

    public TransitionBuilder customTransition(BooleanSupplier booleanSupplier, State state) {
        transition.addCondition(new CustomCondition(booleanSupplier, "CustomTransition"), state);
        return this;
    }

    public TransitionBuilder exitTransition(BooleanSupplier booleanSupplier) {
        transition.addCondition(new CustomCondition(booleanSupplier, "ExitTransition"), null);
        return this;
    }

}
