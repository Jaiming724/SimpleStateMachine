package dev.scratch.simplestatemachine.conditions;

import dev.scratch.simplestatemachine.util.Callback;

public class ExitCondition extends Condition{
    private Callback callback;

    public ExitCondition(Callback callback) {
        this.callback = callback;
    }
    @Override
    public boolean shouldTransition() {
        return callback.call();
    }
}
