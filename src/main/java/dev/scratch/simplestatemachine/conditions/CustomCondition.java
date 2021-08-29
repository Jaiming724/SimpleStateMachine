package dev.scratch.simplestatemachine.conditions;

import java.util.function.BooleanSupplier;

public class CustomCondition extends Condition {
    private BooleanSupplier booleanSupplier;

    public CustomCondition(BooleanSupplier booleanSupplier, String name) {
        super(name);
        this.booleanSupplier = booleanSupplier;
    }

    @Override
    public boolean shouldTransition() {
        return booleanSupplier.getAsBoolean();
    }
}
