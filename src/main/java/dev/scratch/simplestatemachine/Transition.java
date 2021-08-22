package dev.scratch.simplestatemachine;

import dev.scratch.simplestatemachine.conditions.Condition;

import java.util.HashMap;
import java.util.Map;

public class Transition {
    private String name;
    private State from;
    private State to;
    private Map<Condition, State> conditions;
    private boolean exit = false;

    public Transition() {
        conditions = new HashMap<>();
    }

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

    public Map<Condition, State> getConditions() {
        return conditions;
    }

    public boolean shouldExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public boolean shouldTransition() {
        for (Map.Entry<Condition, State> condition : conditions.entrySet()) {
            if (condition.getKey().shouldTransition()) {
                setTo(condition.getValue());
                return true;
            }
        }
        return false;
    }

    public void addCondition(Condition condition, State state) {
        conditions.put(condition, state);
    }


}
