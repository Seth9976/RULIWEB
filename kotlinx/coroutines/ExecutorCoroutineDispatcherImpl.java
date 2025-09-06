package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ConcurrentKt;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0002J\b\u0010\u000E\u001A\u00020\tH\u0016J\u001C\u0010\u000F\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\n\u0010\u0010\u001A\u00060\u0011j\u0002`\u0012H\u0016J\u0013\u0010\u0013\u001A\u00020\u00142\b\u0010\u0015\u001A\u0004\u0018\u00010\u0016H\u0096\u0002J\b\u0010\u0017\u001A\u00020\u0018H\u0016J$\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\u001C2\n\u0010\u0010\u001A\u00060\u0011j\u0002`\u00122\u0006\u0010\n\u001A\u00020\u000BH\u0016J\u001E\u0010\u001D\u001A\u00020\t2\u0006\u0010\u001B\u001A\u00020\u001C2\f\u0010\u001E\u001A\b\u0012\u0004\u0012\u00020\t0\u001FH\u0016J\b\u0010 \u001A\u00020!H\u0016J.\u0010\"\u001A\b\u0012\u0002\b\u0003\u0018\u00010#*\u00020$2\n\u0010\u0010\u001A\u00060\u0011j\u0002`\u00122\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\u001B\u001A\u00020\u001CH\u0002R\u0014\u0010\u0003\u001A\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007¨\u0006%"}, d2 = {"Lkotlinx/coroutines/ExecutorCoroutineDispatcherImpl;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "executor", "Ljava/util/concurrent/Executor;", "(Ljava/util/concurrent/Executor;)V", "getExecutor", "()Ljava/util/concurrent/Executor;", "cancelJobOnRejection", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "Ljava/util/concurrent/RejectedExecutionException;", "close", "dispatch", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "equals", "", "other", "", "hashCode", "", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "toString", "", "scheduleBlock", "Ljava/util/concurrent/ScheduledFuture;", "Ljava/util/concurrent/ScheduledExecutorService;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ExecutorCoroutineDispatcherImpl extends ExecutorCoroutineDispatcher implements Delay {
    private final Executor executor;

    public ExecutorCoroutineDispatcherImpl(Executor executor0) {
        this.executor = executor0;
        ConcurrentKt.removeFutureOnCancel(this.getExecutor());
    }

    private final void cancelJobOnRejection(CoroutineContext coroutineContext0, RejectedExecutionException rejectedExecutionException0) {
        JobKt.cancel(coroutineContext0, ExceptionsKt.CancellationException("The task was rejected", rejectedExecutionException0));
    }

    @Override  // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public void close() {
        Executor executor0 = this.getExecutor();
        ExecutorService executorService0 = executor0 instanceof ExecutorService ? ((ExecutorService)executor0) : null;
        if(executorService0 != null) {
            executorService0.shutdown();
        }
    }

    @Override  // kotlinx.coroutines.Delay
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    public Object delay(long v, Continuation continuation0) {
        return DefaultImpls.delay(this, v, continuation0);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext0, Runnable runnable0) {
        Runnable runnable1;
        try {
            Executor executor0 = this.getExecutor();
            AbstractTimeSource abstractTimeSource0 = AbstractTimeSourceKt.getTimeSource();
            if(abstractTimeSource0 == null) {
                runnable1 = runnable0;
            }
            else {
                runnable1 = abstractTimeSource0.wrapTask(runnable0);
                if(runnable1 == null) {
                    runnable1 = runnable0;
                }
            }
            executor0.execute(runnable1);
        }
        catch(RejectedExecutionException rejectedExecutionException0) {
            AbstractTimeSource abstractTimeSource1 = AbstractTimeSourceKt.getTimeSource();
            if(abstractTimeSource1 != null) {
                abstractTimeSource1.unTrackTask();
            }
            this.cancelJobOnRejection(coroutineContext0, rejectedExecutionException0);
            Dispatchers.getIO().dispatch(coroutineContext0, runnable0);
        }
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ExecutorCoroutineDispatcherImpl && ((ExecutorCoroutineDispatcherImpl)object0).getExecutor() == this.getExecutor();
    }

    @Override  // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public Executor getExecutor() {
        return this.executor;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this.getExecutor());
    }

    @Override  // kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long v, Runnable runnable0, CoroutineContext coroutineContext0) {
        long v1;
        Executor executor0 = this.getExecutor();
        ScheduledFuture scheduledFuture0 = null;
        ScheduledExecutorService scheduledExecutorService0 = executor0 instanceof ScheduledExecutorService ? ((ScheduledExecutorService)executor0) : null;
        if(scheduledExecutorService0 == null) {
            v1 = v;
        }
        else {
            v1 = v;
            scheduledFuture0 = this.scheduleBlock(scheduledExecutorService0, runnable0, coroutineContext0, v1);
        }
        return scheduledFuture0 != null ? new DisposableFutureHandle(scheduledFuture0) : DefaultExecutor.INSTANCE.invokeOnTimeout(v1, runnable0, coroutineContext0);
    }

    private final ScheduledFuture scheduleBlock(ScheduledExecutorService scheduledExecutorService0, Runnable runnable0, CoroutineContext coroutineContext0, long v) {
        try {
            return scheduledExecutorService0.schedule(runnable0, v, TimeUnit.MILLISECONDS);
        }
        catch(RejectedExecutionException rejectedExecutionException0) {
            this.cancelJobOnRejection(coroutineContext0, rejectedExecutionException0);
            return null;
        }
    }

    @Override  // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long v, CancellableContinuation cancellableContinuation0) {
        long v1;
        Executor executor0 = this.getExecutor();
        ScheduledFuture scheduledFuture0 = null;
        ScheduledExecutorService scheduledExecutorService0 = executor0 instanceof ScheduledExecutorService ? ((ScheduledExecutorService)executor0) : null;
        if(scheduledExecutorService0 == null) {
            v1 = v;
        }
        else {
            v1 = v;
            scheduledFuture0 = this.scheduleBlock(scheduledExecutorService0, new ResumeUndispatchedRunnable(this, cancellableContinuation0), cancellableContinuation0.getContext(), v1);
        }
        if(scheduledFuture0 != null) {
            JobKt.cancelFutureOnCancellation(cancellableContinuation0, scheduledFuture0);
            return;
        }
        DefaultExecutor.INSTANCE.scheduleResumeAfterDelay(v1, cancellableContinuation0);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return this.getExecutor().toString();
    }
}

