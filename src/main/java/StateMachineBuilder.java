public class StateMachineBuilder {
    private final StateMachine stateMachine;

    public StateMachineBuilder() {
        this.stateMachine = new StateMachine();
    }

    public StateMachineBuilder init(State state) {
        stateMachine.init(state);
        return this;
    }

    public StateMachineBuilder addTransition(Transition transition) {
        stateMachine.addTransition(transition);
        return this;
    }

    public StateMachine build(){
        return stateMachine;
    }
}
