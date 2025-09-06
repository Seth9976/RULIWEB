package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u00020\u0004H&J\b\u0010\u0005\u001A\u00020\u0004H&J\u0018\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\u00012\u0006\u0010\t\u001A\u00020\u0004H&J\b\u0010\n\u001A\u00020\u0007H&J\b\u0010\u000B\u001A\u00020\u0007H&J\b\u0010\f\u001A\u00020\u0007H&J\u0010\u0010\r\u001A\u00020\u00072\u0006\u0010\u000E\u001A\u00020\u000FH&J\b\u0010\u0010\u001A\u00020\u0007H&J\u0018\u0010\u0011\u001A\u00060\u0012j\u0002`\u00132\n\u0010\u0014\u001A\u00060\u0012j\u0002`\u0013H&¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/AbstractTimeSource;", "", "()V", "currentTimeMillis", "", "nanoTime", "parkNanos", "", "blocker", "nanos", "registerTimeLoopThread", "trackTask", "unTrackTask", "unpark", "thread", "Ljava/lang/Thread;", "unregisterTimeLoopThread", "wrapTask", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "block", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class AbstractTimeSource {
    public abstract long currentTimeMillis();

    public abstract long nanoTime();

    public abstract void parkNanos(Object arg1, long arg2);

    public abstract void registerTimeLoopThread();

    public abstract void trackTask();

    public abstract void unTrackTask();

    public abstract void unpark(Thread arg1);

    public abstract void unregisterTimeLoopThread();

    public abstract Runnable wrapTask(Runnable arg1);
}

