package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlinx.coroutines.internal.LimitedDispatcherKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001A\u00020\u00132\b\b\u0002\u0010\u0014\u001A\u00020\u0004J\u0010\u0010\u0015\u001A\u00020\n2\u0006\u0010\u0014\u001A\u00020\u0004H\u0002J\u0012\u0010\u0016\u001A\u00020\u00132\n\u0010\u0017\u001A\u0006\u0012\u0002\b\u00030\u0010J\u0010\u0010\u0018\u001A\u00020\u00132\b\b\u0002\u0010\u0014\u001A\u00020\u0004J\u000E\u0010\u0019\u001A\u00020\u00012\u0006\u0010\u001A\u001A\u00020\u001BJ\b\u0010\u001C\u001A\u00020\nH\u0016J\u0006\u0010\u001D\u001A\u00020\u0004J\b\u0010\u001E\u001A\u00020\u0004H\u0016J\b\u0010\u001F\u001A\u00020\u0013H\u0016R\u0011\u0010\u0003\u001A\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0005R\u0014\u0010\u0006\u001A\u00020\u00048TX\u0094\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0005R\u0011\u0010\u0007\u001A\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\u0007\u0010\u0005R\u0011\u0010\b\u001A\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\b\u0010\u0005R\u0014\u0010\t\u001A\u00020\n8TX\u0094\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u000E\u0010\r\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u001A\u0010\u000E\u001A\u000E\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010\u0018\u00010\u000FX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\nX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lkotlinx/coroutines/EventLoop;", "Lkotlinx/coroutines/CoroutineDispatcher;", "()V", "isActive", "", "()Z", "isEmpty", "isUnconfinedLoopActive", "isUnconfinedQueueEmpty", "nextTime", "", "getNextTime", "()J", "shared", "unconfinedQueue", "Lkotlin/collections/ArrayDeque;", "Lkotlinx/coroutines/DispatchedTask;", "useCount", "decrementUseCount", "", "unconfined", "delta", "dispatchUnconfined", "task", "incrementUseCount", "limitedParallelism", "parallelism", "", "processNextEvent", "processUnconfinedEvent", "shouldBeProcessedFromContext", "shutdown", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class EventLoop extends CoroutineDispatcher {
    private boolean shared;
    private ArrayDeque unconfinedQueue;
    private long useCount;

    public final void decrementUseCount(boolean z) {
        long v = this.useCount - this.delta(z);
        this.useCount = v;
        if(v <= 0L && this.shared) {
            this.shutdown();
        }
    }

    public static void decrementUseCount$default(EventLoop eventLoop0, boolean z, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decrementUseCount");
        }
        if((v & 1) != 0) {
            z = false;
        }
        eventLoop0.decrementUseCount(z);
    }

    // 去混淆评级： 低(20)
    private final long delta(boolean z) [...] // 潜在的解密器

    public final void dispatchUnconfined(DispatchedTask dispatchedTask0) {
        ArrayDeque arrayDeque0 = this.unconfinedQueue;
        if(arrayDeque0 == null) {
            arrayDeque0 = new ArrayDeque();
            this.unconfinedQueue = arrayDeque0;
        }
        arrayDeque0.addLast(dispatchedTask0);
    }

    protected long getNextTime() {
        ArrayDeque arrayDeque0 = this.unconfinedQueue;
        if(arrayDeque0 == null) {
            return 0x7FFFFFFFFFFFFFFFL;
        }
        return arrayDeque0.isEmpty() ? 0x7FFFFFFFFFFFFFFFL : 0L;
    }

    public final void incrementUseCount(boolean z) {
        this.useCount += this.delta(z);
        if(!z) {
            this.shared = true;
        }
    }

    public static void incrementUseCount$default(EventLoop eventLoop0, boolean z, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incrementUseCount");
        }
        if((v & 1) != 0) {
            z = false;
        }
        eventLoop0.incrementUseCount(z);
    }

    public final boolean isActive() {
        return this.useCount > 0L;
    }

    protected boolean isEmpty() {
        return this.isUnconfinedQueueEmpty();
    }

    // 去混淆评级： 低(20)
    public final boolean isUnconfinedLoopActive() {
        return this.useCount >= 0x100000000L;
    }

    public final boolean isUnconfinedQueueEmpty() {
        return this.unconfinedQueue == null ? true : this.unconfinedQueue.isEmpty();
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public final CoroutineDispatcher limitedParallelism(int v) {
        LimitedDispatcherKt.checkParallelism(v);
        return this;
    }

    public long processNextEvent() {
        return this.processUnconfinedEvent() ? 0L : 0x7FFFFFFFFFFFFFFFL;
    }

    public final boolean processUnconfinedEvent() {
        ArrayDeque arrayDeque0 = this.unconfinedQueue;
        if(arrayDeque0 == null) {
            return false;
        }
        DispatchedTask dispatchedTask0 = (DispatchedTask)arrayDeque0.removeFirstOrNull();
        if(dispatchedTask0 == null) {
            return false;
        }
        dispatchedTask0.run();
        return true;
    }

    public boolean shouldBeProcessedFromContext() [...] // Inlined contents

    public void shutdown() {
    }
}

