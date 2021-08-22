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

        State off = new StateBuilder()
                .name("off")
                .onEntry(() -> System.out.println("Entering state off"))
                .loop(() -> System.out.println("Looping state off"))
                .onExit(() -> System.out.println("Exiting state off"))
                .build();

        Transition transition1 = new TransitionBuilder()
                .name("state1To2")
                .from(state1)
                .customTransition(() -> count == 0, state2)
                .build();
        Transition transition2 = new TransitionBuilder()
                .name("state2To3")
                .customTransition(() -> 3 < 6, off)
                .build();
        Transition transition3 = new TransitionBuilder()
                .exit()
                .build();
        state1.setTransition(transition1);
        state2.setTransition(transition2);
        off.setTransition(transition3);

        StateMachine stateMachine = new StateMachineBuilder()
                .init(state1)
                .build();
        while (!stateMachine.isShouldExit()) {
            stateMachine.loop();
        }
    }
}
