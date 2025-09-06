package androidx.work.impl.utils;

import java.util.concurrent.Executor;

public class SynchronousExecutor implements Executor {
    @Override
    public void execute(Runnable runnable0) {
        runnable0.run();
    }
}

