package dev.scratch.simplestatemachine;

public class State {

    private String name;
    private Runnable onEntry;
    private Runnable onExit;
    private Runnable loop;


    private Transition transition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Runnable getOnEntry() {
        return onEntry;
    }

    public void setOnEntry(Runnable onEntry) {
        this.onEntry = onEntry;
    }

    public Runnable getOnExit() {
        return onExit;
    }

    public void setOnExit(Runnable onExit) {
        this.onExit = onExit;
    }

    public Runnable getLoop() {
        return loop;
    }

    public void setLoop(Runnable loop) {
        this.loop = loop;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }
}