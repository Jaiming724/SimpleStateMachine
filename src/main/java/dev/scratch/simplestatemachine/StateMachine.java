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
    }

    public void start() {
        if (currentState == null)
            throw new IllegalStateException("State machine has not been initialized");
        currentState.getOnEntry().run();
    }

    public boolean isActive() {
        return !shouldExit;
    }

    public void loop() {
        if (currentState == null)
            throw new IllegalStateException("State machine is not yet initialized!");
        if (shouldExit)
            return;
        currentTransition = currentState.getTransition();

        currentTransition.getConditions().forEach((condition, state) -> {
            if (condition.getName().equals("TimedCondition")) {
                ((TimedCondition) condition).startTimer();
            }
        });
        if (currentTransition.shouldTransition()) {
            currentState.getOnExit().run();
            if (currentTransition.shouldExit() || currentTransition.getTo() == null) {
                shouldExit = true;
                return;
            } else {
                currentState = currentTransition.getTo();
                currentState.getOnEntry().run();
            }

        }
        currentState.getLoop().run();

    }

    public void shutdown() {
        if (currentState == null)
            throw new IllegalStateException("State machine is not initialized and cannot be shut down!");
        currentState = null;
    }

}