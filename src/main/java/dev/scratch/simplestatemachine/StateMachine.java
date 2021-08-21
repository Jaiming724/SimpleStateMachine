package dev.scratch.simplestatemachine;

import dev.scratch.simplestatemachine.conditions.TimedCondition;

import java.util.ArrayList;
import java.util.List;

public class StateMachine {

    private State currentState;
    private Transition currentTransition;
    private final List<Transition> transitions = new ArrayList<>();
    private boolean shouldExit = false;
    private int counter = 0;

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
        currentState = transitions.get(counter).getFrom();
        currentTransition = transitions.get(counter);
        currentTransition.getConditions().forEach((condition, state) -> {
            if (condition instanceof TimedCondition) {
                ((TimedCondition) condition).startTimer();
            }
        });
        if (currentTransition.shouldTransition()) {
//            System.out.println("Current state "+currentState.getName());
//            System.out.println("To State "+currentTransition.getTo().getName());
            currentState.getOnExit().run();
            if(counter+1<transitions.size()){
                transitions.get(counter+1).setFrom(currentTransition.getTo());
            }
            currentState = currentTransition.getTo();
            currentState.getOnEntry().run();
            System.out.println("Removing transition " + currentTransition.getName());
            counter++;
        }
        currentState.getLoop().run();

        if (counter < transitions.size()) {
            shouldExit = false;
        } else {
            currentState.getOnExit().run();
            shouldExit = true;
        }

    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }


}