package androidx.work;

public interface RunnableScheduler {
    void cancel(Runnable arg1);

    void scheduleWithDelay(long arg1, Runnable arg2);
}

