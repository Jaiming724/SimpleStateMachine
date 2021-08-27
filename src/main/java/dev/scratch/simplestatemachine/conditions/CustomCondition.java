package dev.scratch.simplestatemachine.conditions;

import dev.scratch.simplestatemachine.util.Callback;

public class CustomCondition extends Condition {
    private Callback callback;

    public CustomCondition(Callback callback, String name) {
        super(name);
        this.callback = callback;
    }

    @Override
    public boolean shouldTransition() {
        return callback.call();
    }
}
