package dev.scratch.simplestatemachine.conditions;

public abstract class Condition {
    private String name;

    public Condition(String name) {
        this.name = name;
    }

    public abstract boolean shouldTransition();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
