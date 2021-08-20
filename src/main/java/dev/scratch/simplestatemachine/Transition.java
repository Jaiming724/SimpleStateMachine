package dev.scratch.simplestatemachine;

import dev.scratch.simplestatemachine.conditions.Condition;

public class Transition {
    private String name;
    private State from;
    private State to;
    private Condition condition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getFrom() {
        return from;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    public State getTo() {
        return to;
    }

    public void setTo(State to) {
        this.to = to;
    }

    public Condition getCondition() {
        return condition;
    }

    public boolean shouldTransition() {
        return condition.shouldTransition();
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
