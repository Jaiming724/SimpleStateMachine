import dev.scratch.simplestatemachine.*;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        State state1 = new StateBuilder()
                .name("State1")
                .onEntry(() -> System.out.println("Entering state 1"))
                .loop(() -> System.out.println("Looping state 1"))
                .onExit(() -> System.out.println("Exiting state 1"))
                .build();

        State state2 = new StateBuilder()
                .name("State2")
                .onEntry(() -> System.out.println("Entering state 2"))
                .loop(() -> System.out.println("Looping state 2"))
                .onExit(() -> System.out.println("Exiting state 2"))
                .build();
        State state3 = new StateBuilder()
                .name("State3")
                .onEntry(() -> System.out.println("Entering state 3"))
                .loop(() -> System.out.println("Looping state 3"))
                .onExit(() -> System.out.println("Exiting state 3"))
                .build();
        State state4 = new StateBuilder()
                .name("State4")
                .onEntry(() -> System.out.println("Entering state 4"))
                .loop(() -> System.out.println("Looping state 4"))
                .onExit(() -> System.out.println("Exiting state 4"))
                .build();
        Transition transition1 = new TransitionBuilder()
                .name("state1To2")
                .from(state1)
                .customTransition(() -> count == 2, state2)
                .customTransition(() -> count == 1, state3)
                .timedTransition(50,state2)
                .build();
        Transition transition2 = new TransitionBuilder()
                .name("state2To3")
                .customTransition(() -> 3 < 6, state4)
                 .build();


        StateMachine stateMachine = new StateMachineBuilder()
                .init(state1)
                .addTransition(transition1)
                .addTransition(transition2)
                .build();
        while (!stateMachine.isShouldExit()) {
            stateMachine.loop();
        }
    }
}
