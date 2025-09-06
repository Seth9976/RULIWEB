package androidx.arch.core.executor;

public abstract class TaskExecutor {
    public abstract void executeOnDiskIO(Runnable arg1);

    public void executeOnMainThread(Runnable runnable0) {
        if(this.isMainThread()) {
            runnable0.run();
            return;
        }
        this.postToMainThread(runnable0);
    }

    public abstract boolean isMainThread();

    public abstract void postToMainThread(Runnable arg1);
}

