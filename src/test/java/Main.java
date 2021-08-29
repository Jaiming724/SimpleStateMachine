import dev.scratch.simplestatemachine.*;

public class Main {
    public static void main(String[] args) {
        Thermometer thermometer = new Thermometer();
        State solid = new StateBuilder()
                .name("solid")
                .onEntry(() -> System.out.println("Entering solid state"))
                .loop(() -> System.out.println("Looping in solid state"))
                .onExit(() -> System.out.println("Exiting solid state"))
                .build();
        State liquid = new StateBuilder()
                .name("liquid")
                .onEntry(() -> System.out.println("Entering liquid state"))
                .loop(() -> System.out.println("Looping in liquid state"))
                .onExit(() -> System.out.println("Exiting liquid state"))
                .build();

        State gas = new StateBuilder()
                .name("gas")
                .onEntry(() -> System.out.println("Entering gas state"))
                .loop(() -> System.out.println("Looping in gas state"))
                .onExit(() -> System.out.println("Exiting gas state"))
                .build();
        Transition solidTransition = new TransitionBuilder()
                .name("Solid Transition")
                .exitTransition(() -> thermometer.getTemperature() == -999)
                .customTransition(() -> thermometer.getTemperature() > 0 && thermometer.getTemperature() < 100, liquid)
                .customTransition(() -> thermometer.getTemperature() > 100, gas)
                .build();
        Transition gasTransition = new TransitionBuilder()
                .name("Gas Transition")
                .exitTransition(() -> thermometer.getTemperature() == -999)
                .customTransition(() -> thermometer.getTemperature() < 0, solid)
                .customTransition(() -> thermometer.getTemperature() < 100, liquid)
                .build();

        Transition liquidTransition = new TransitionBuilder()
                .name("Liquid Transition")
                .exitTransition(() -> thermometer.getTemperature() == -999)
                .customTransition(() -> thermometer.getTemperature() < 0, solid)
                .customTransition(() -> thermometer.getTemperature() > 100, gas)
                .build();
        solid.setTransition(solidTransition);
        gas.setTransition(gasTransition);
        liquid.setTransition(liquidTransition);
        StateMachine stateMachine = new StateMachineBuilder()
                .init(solid)
                .addLoopStartEvent(event -> System.out.println("Start looping with event name: " + event.getState().getName()))
                .addLoopStartEvent(event -> System.out.println("End looping with event name: " + event.getState().getName()))
                .addTransitionEvent(event -> {
                    if (event.getFinalState() == null) {
                        System.out.printf("Transitioning from %s to exiting with transition %s %n", event.getInitialState().getName(), event.getTransition().getName());
                    } else {
                        System.out.printf("Transitioning from %s to %s with transition %s %n", event.getInitialState().getName(), event.getFinalState().getName(), event.getTransition().getName());
                    }
                })
                .build();
        stateMachine.start();
        while (stateMachine.isActive()) {
            thermometer.setTemperature();
            stateMachine.loop();
        }
        stateMachine.shutdown();
    }

}
