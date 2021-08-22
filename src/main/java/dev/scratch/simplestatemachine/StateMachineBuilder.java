package dev.scratch.simplestatemachine;

public class StateMachineBuilder {
    private final StateMachine stateMachine;

    public StateMachineBuilder() {
        this.stateMachine = new StateMachine();
    }

    public StateMachineBuilder init(State state) {
        stateMachine.init(state);
        return this;
    }


    public StateMachine build(){
        return stateMachine;
    }
}
