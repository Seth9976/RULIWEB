package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.logging.Logging;
import java.util.concurrent.Executor;

class SafeLoggingExecutor implements Executor {
    static class SafeLoggingRunnable implements Runnable {
        private final Runnable delegate;

        SafeLoggingRunnable(Runnable runnable0) {
            this.delegate = runnable0;
        }

        @Override
        public void run() {
            try {
                this.delegate.run();
            }
            catch(Exception exception0) {
                Logging.e("Executor", "Background execution failure.", exception0);
            }
        }
    }

    private final Executor delegate;

    SafeLoggingExecutor(Executor executor0) {
        this.delegate = executor0;
    }

    @Override
    public void execute(Runnable runnable0) {
        SafeLoggingRunnable safeLoggingExecutor$SafeLoggingRunnable0 = new SafeLoggingRunnable(runnable0);
        this.delegate.execute(safeLoggingExecutor$SafeLoggingRunnable0);
    }
}

