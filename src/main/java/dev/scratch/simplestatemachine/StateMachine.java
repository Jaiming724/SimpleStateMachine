package dev.scratch.simplestatemachine;

import dev.scratch.simplestatemachine.conditions.TimedCondition;
import dev.scratch.simplestatemachine.events.EventHandler;
import dev.scratch.simplestatemachine.events.LoopEvent;
import dev.scratch.simplestatemachine.events.StateTransitionEvent;

import java.util.ArrayList;
import java.util.List;

public class StateMachine {

    private State currentState;
    private Transition currentTransition;
    private boolean shouldExit = false;
    private List<EventHandler<LoopEvent>> loopStartHandlerList = new ArrayList<>();
    private List<EventHandler<LoopEvent>> loopEndHandlerList = new ArrayList<>();
    private List<EventHandler<StateTransitionEvent>> transitionHandlerList = new ArrayList<>();

    public void init(State state) {
        if (currentState != null)
            throw new IllegalStateException("State machine has already been initialized!");
        currentState = state;
    }

    public void start() {
        if (currentState == null)
            throw new IllegalStateException("State machine has not been initialized");
        if (currentState.getOnEntry() != null) {
            currentState.getOnEntry().run();
        }
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
        loopStartHandlerList.forEach(h -> h.execute(new LoopEvent(currentState)));

        currentTransition.getConditions().forEach((condition, state) -> {
            if (condition.getName().equals("TimedCondition")) {
                ((TimedCondition) condition).startTimer();
            }
        });
        if (currentTransition.shouldTransition()) {
            StateTransitionEvent transitionEvent = new StateTransitionEvent(currentState, currentTransition.getTo(), currentTransition);
            transitionHandlerList.forEach(h -> h.execute(transitionEvent));

            if (currentState.getOnExit() != null) {
                currentState.getOnExit().run();
            }
            if (currentTransition.shouldExit() || currentTransition.getTo() == null) {
                shouldExit = true;
                return;
            } else {
                currentState = currentTransition.getTo();
                if (currentState.getOnEntry() != null) {
                    currentState.getOnEntry().run();
                }
            }

        }
        if (currentState.getLoop() != null) {
            currentState.getLoop().run();
        }
        loopEndHandlerList.forEach(h -> h.execute(new LoopEvent(currentState)));
    }

    public void shutdown() {
        if (currentState == null)
            throw new IllegalStateException("State machine is not initialized and cannot be shut down!");
        currentState = null;
    }

    public void addLoopEndEvent(EventHandler<LoopEvent> eventHandler) {
        loopEndHandlerList.add(eventHandler);
    }

    public void addLoopStartEvent(EventHandler<LoopEvent> eventHandler) {
        loopStartHandlerList.add(eventHandler);
    }

    public void addTransitionEvent(EventHandler<StateTransitionEvent> eventHandler) {
        transitionHandlerList.add(eventHandler);
    }

}