package androidx.concurrent.futures;

import java.util.concurrent.Executor;

public enum DirectExecutor implements Executor {
    INSTANCE;

    @Override
    public void execute(Runnable runnable0) {
        runnable0.run();
    }

    @Override
    public String toString() {
        return "DirectExecutor";
    }
}

