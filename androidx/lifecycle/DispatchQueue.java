package androidx.lifecycle;

import java.util.ArrayDeque;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001A\u00020\u0004H\u0007J\u0018\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\tH\u0007J\b\u0010\u0010\u001A\u00020\fH\u0007J\u0010\u0010\u0011\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\tH\u0003J\b\u0010\u0012\u001A\u00020\fH\u0007J\b\u0010\u0013\u001A\u00020\fH\u0007J\b\u0010\u0014\u001A\u00020\fH\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/lifecycle/DispatchQueue;", "", "()V", "finished", "", "isDraining", "paused", "queue", "Ljava/util/Queue;", "Ljava/lang/Runnable;", "canRun", "dispatchAndEnqueue", "", "context", "Lkotlin/coroutines/CoroutineContext;", "runnable", "drainQueue", "enqueue", "finish", "pause", "resume", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DispatchQueue {
    private boolean finished;
    private boolean isDraining;
    private boolean paused;
    private final Queue queue;

    public DispatchQueue() {
        this.paused = true;
        this.queue = new ArrayDeque();
    }

    // 去混淆评级： 低(20)
    public final boolean canRun() {
        return this.finished || !this.paused;
    }

    public final void dispatchAndEnqueue(CoroutineContext coroutineContext0, Runnable runnable0) {
        Intrinsics.checkNotNullParameter(coroutineContext0, "context");
        Intrinsics.checkNotNullParameter(runnable0, "runnable");
        MainCoroutineDispatcher mainCoroutineDispatcher0 = Dispatchers.getMain().getImmediate();
        if(!mainCoroutineDispatcher0.isDispatchNeeded(coroutineContext0) && !this.canRun()) {
            this.enqueue(runnable0);
            return;
        }
        mainCoroutineDispatcher0.dispatch(coroutineContext0, () -> {
            Intrinsics.checkNotNullParameter(this, "this$0");
            Intrinsics.checkNotNullParameter(runnable0, "$runnable");
            this.enqueue(runnable0);
        });
    }

    // 检测为 Lambda 实现
    private static final void dispatchAndEnqueue$lambda$2$lambda$1(DispatchQueue dispatchQueue0, Runnable runnable0) [...]

    public final void drainQueue() {
        if(this.isDraining) {
            return;
        }
        try {
            this.isDraining = true;
            while(!this.queue.isEmpty() && this.canRun()) {
                Runnable runnable0 = (Runnable)this.queue.poll();
                if(runnable0 != null) {
                    runnable0.run();
                }
            }
        }
        finally {
            this.isDraining = false;
        }
    }

    private final void enqueue(Runnable runnable0) {
        if(!this.queue.offer(runnable0)) {
            throw new IllegalStateException("cannot enqueue any more runnables");
        }
        this.drainQueue();
    }

    public final void finish() {
        this.finished = true;
        this.drainQueue();
    }

    public final void pause() {
        this.paused = true;
    }

    public final void resume() {
        if(!this.paused) {
            return;
        }
        if(this.finished) {
            throw new IllegalStateException("Cannot resume a finished dispatcher");
        }
        this.paused = false;
        this.drainQueue();
    }
}

