package dev.scratch.simplestatemachine.conditions;

public class TimedCondition extends Condition {
    private final double time;
    private long startTime;
    private boolean started = false;

    public TimedCondition(double time, String name) {
        super(name);
        this.time = time;
    }

    public void startTimer() {
        if (!started) {
            startTime = System.nanoTime();
        }
        started = true;
    }

    @Override
    public boolean shouldTransition() {
        return (System.nanoTime() - startTime) / 1e6 >= time;
    }
}
