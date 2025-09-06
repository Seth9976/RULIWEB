package androidx.work.impl.utils;

import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;

public class SerialExecutorImpl implements SerialExecutor {
    static class Task implements Runnable {
        final Runnable mRunnable;
        final SerialExecutorImpl mSerialExecutor;

        Task(SerialExecutorImpl serialExecutorImpl0, Runnable runnable0) {
            this.mSerialExecutor = serialExecutorImpl0;
            this.mRunnable = runnable0;
        }

        @Override
        public void run() {
            try {
                this.mRunnable.run();
            }
            finally {
                synchronized(this.mSerialExecutor.mLock) {
                    this.mSerialExecutor.scheduleNext();
                }
            }
        }
    }

    private Runnable mActive;
    private final Executor mExecutor;
    final Object mLock;
    private final ArrayDeque mTasks;

    public SerialExecutorImpl(Executor executor0) {
        this.mExecutor = executor0;
        this.mTasks = new ArrayDeque();
        this.mLock = new Object();
    }

    @Override
    public void execute(Runnable runnable0) {
        synchronized(this.mLock) {
            Task serialExecutorImpl$Task0 = new Task(this, runnable0);
            this.mTasks.add(serialExecutorImpl$Task0);
            if(this.mActive == null) {
                this.scheduleNext();
            }
        }
    }

    public Executor getDelegatedExecutor() {
        return this.mExecutor;
    }

    @Override  // androidx.work.impl.utils.taskexecutor.SerialExecutor
    public boolean hasPendingTasks() {
        synchronized(this.mLock) {
        }
        return !this.mTasks.isEmpty();
    }

    void scheduleNext() {
        Runnable runnable0 = (Runnable)this.mTasks.poll();
        this.mActive = runnable0;
        if(runnable0 != null) {
            this.mExecutor.execute(runnable0);
        }
    }
}

