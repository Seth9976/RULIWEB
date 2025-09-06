package okhttp3.internal.concurrent;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0011\u001A\u00020\u0012H\u0000¢\u0006\u0002\b\u0019J\b\u0010\u001A\u001A\u00020\fH&J\b\u0010\u001B\u001A\u00020\u0003H\u0016R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u001A\u0010\u000B\u001A\u00020\fX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\r\u0010\u000E\"\u0004\b\u000F\u0010\u0010R\u001C\u0010\u0011\u001A\u0004\u0018\u00010\u0012X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001C"}, d2 = {"Lokhttp3/internal/concurrent/Task;", "", "name", "", "cancelable", "", "(Ljava/lang/String;Z)V", "getCancelable", "()Z", "getName", "()Ljava/lang/String;", "nextExecuteNanoTime", "", "getNextExecuteNanoTime$okhttp", "()J", "setNextExecuteNanoTime$okhttp", "(J)V", "queue", "Lokhttp3/internal/concurrent/TaskQueue;", "getQueue$okhttp", "()Lokhttp3/internal/concurrent/TaskQueue;", "setQueue$okhttp", "(Lokhttp3/internal/concurrent/TaskQueue;)V", "initQueue", "", "initQueue$okhttp", "runOnce", "toString", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public abstract class Task {
    private final boolean cancelable;
    private final String name;
    private long nextExecuteNanoTime;
    private TaskQueue queue;

    public Task(String s, boolean z) {
        Intrinsics.checkNotNullParameter(s, "name");
        super();
        this.name = s;
        this.cancelable = z;
        this.nextExecuteNanoTime = -1L;
    }

    public Task(String s, boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            z = true;
        }
        this(s, z);
    }

    public final boolean getCancelable() {
        return this.cancelable;
    }

    public final String getName() {
        return this.name;
    }

    public final long getNextExecuteNanoTime$okhttp() {
        return this.nextExecuteNanoTime;
    }

    public final TaskQueue getQueue$okhttp() {
        return this.queue;
    }

    public final void initQueue$okhttp(TaskQueue taskQueue0) {
        Intrinsics.checkNotNullParameter(taskQueue0, "queue");
        TaskQueue taskQueue1 = this.queue;
        if(taskQueue1 == taskQueue0) {
            return;
        }
        if(taskQueue1 != null) {
            throw new IllegalStateException("task is in multiple queues");
        }
        this.queue = taskQueue0;
    }

    public abstract long runOnce();

    public final void setNextExecuteNanoTime$okhttp(long v) {
        this.nextExecuteNanoTime = v;
    }

    public final void setQueue$okhttp(TaskQueue taskQueue0) {
        this.queue = taskQueue0;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

