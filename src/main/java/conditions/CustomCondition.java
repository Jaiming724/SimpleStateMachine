package conditions;

import util.Callback;

public class CustomCondition extends Condition {
    private Callback callback;

    public CustomCondition(Callback callback) {
        this.callback = callback;
    }

    @Override
    public boolean shouldTransition() {
        return callback.call();
    }
}
