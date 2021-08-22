package dev.scratch.simplestatemachine;

import dev.scratch.simplestatemachine.conditions.TimedCondition;

public class StateMachine {

    private State currentState;
    private Transition currentTransition;
    private boolean shouldExit = false;

    public void init(State state) {
        if (currentState != null)
            throw new IllegalStateException("State machine has already been initialized!");
        currentState = state;
        currentState.getOnEntry().run();
    }

    public boolean isShouldExit() {
        return shouldExit;
    }

    public void loop() {
        if (currentState == null)
            throw new IllegalStateException("State machine is not yet initialized!");
        currentTransition = currentState.getTransition();
        if (currentTransition.isExit()) {
            shouldExit = true;
            return;
        }

        currentTransition.getConditions().forEach((condition, state) -> {
            if (condition instanceof TimedCondition) {
                ((TimedCondition) condition).startTimer();
            }
        });
        if (currentTransition.shouldTransition()) {
            currentState.getOnExit().run();
            if (currentTransition.getTo() == null) {
                currentState.getOnExit().run();
                shouldExit = true;
            } else {
                currentState = currentTransition.getTo();
                currentState.getOnEntry().run();
            }

        }
        currentState.getLoop().run();

    }


}