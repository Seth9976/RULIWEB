package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DefaultExecutorKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0001(B\u0015\u0012\u0006\u0010\u0003\u001A\u00020\u0001\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006J\u0019\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0013H\u0097A\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0014J\u001C\u0010\u0015\u001A\u00020\u00112\u0006\u0010\u0016\u001A\u00020\u00172\n\u0010\u0018\u001A\u00060\tj\u0002`\nH\u0016J-\u0010\u0019\u001A\u00020\u00112\n\u0010\u0018\u001A\u00060\tj\u0002`\n2\u0016\u0010\u001A\u001A\u0012\u0012\b\u0012\u00060\u001CR\u00020\u0000\u0012\u0004\u0012\u00020\u00110\u001BH\u0082\bJ\u001C\u0010\u001D\u001A\u00020\u00112\u0006\u0010\u0016\u001A\u00020\u00172\n\u0010\u0018\u001A\u00060\tj\u0002`\nH\u0017J%\u0010\u001E\u001A\u00020\u001F2\u0006\u0010 \u001A\u00020\u00132\n\u0010\u0018\u001A\u00060\tj\u0002`\n2\u0006\u0010\u0016\u001A\u00020\u0017H\u0096\u0001J\u0010\u0010!\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u0005H\u0017J\u0010\u0010\"\u001A\n\u0018\u00010\tj\u0004\u0018\u0001`\nH\u0002J\u001F\u0010#\u001A\u00020\u00112\u0006\u0010 \u001A\u00020\u00132\f\u0010$\u001A\b\u0012\u0004\u0012\u00020\u00110%H\u0096\u0001J\b\u0010&\u001A\u00020\'H\u0002R\u000E\u0010\u0003\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001A\f\u0012\b\u0012\u00060\tj\u0002`\n0\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\t\u0010\u000B\u001A\u00020\fX\u0082\u0004R\u0012\u0010\r\u001A\u00060\u000Ej\u0002`\u000FX\u0082\u0004\u00A2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006)"}, d2 = {"Lkotlinx/coroutines/internal/LimitedDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "dispatcher", "parallelism", "", "(Lkotlinx/coroutines/CoroutineDispatcher;I)V", "queue", "Lkotlinx/coroutines/internal/LockFreeTaskQueue;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "runningWorkers", "Lkotlinx/atomicfu/AtomicInt;", "workerAllocationLock", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "delay", "", "time", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "dispatchInternal", "startWorker", "Lkotlin/Function1;", "Lkotlinx/coroutines/internal/LimitedDispatcher$Worker;", "dispatchYield", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "limitedParallelism", "obtainTaskOrDeallocateWorker", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "tryAllocateWorker", "", "Worker", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class LimitedDispatcher extends CoroutineDispatcher implements Delay {
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0011\u0012\n\u0010\u0003\u001A\u00060\u0001j\u0002`\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001A\u00020\u0006H\u0016R\u0012\u0010\u0003\u001A\u00060\u0001j\u0002`\u0002X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/internal/LimitedDispatcher$Worker;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "currentTask", "(Lkotlinx/coroutines/internal/LimitedDispatcher;Ljava/lang/Runnable;)V", "run", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class Worker implements Runnable {
        private Runnable currentTask;

        public Worker(Runnable runnable0) {
            this.currentTask = runnable0;
        }

        @Override
        public void run() {
            int v = 0;
            do {
                try {
                    this.currentTask.run();
                }
                catch(Throwable throwable0) {
                    CoroutineExceptionHandlerKt.handleCoroutineException(EmptyCoroutineContext.INSTANCE, throwable0);
                }
                Runnable runnable0 = LimitedDispatcher.this.obtainTaskOrDeallocateWorker();
                if(runnable0 == null) {
                    return;
                }
                this.currentTask = runnable0;
                ++v;
            }
            while(v < 16 || !LimitedDispatcher.this.dispatcher.isDispatchNeeded(LimitedDispatcher.this));
            LimitedDispatcher.this.dispatcher.dispatch(LimitedDispatcher.this, this);
        }
    }

    private final Delay $$delegate_0;
    private final CoroutineDispatcher dispatcher;
    private final int parallelism;
    private final LockFreeTaskQueue queue;
    @Volatile
    private volatile int runningWorkers;
    private static final AtomicIntegerFieldUpdater runningWorkers$FU;
    private final Object workerAllocationLock;

    static {
        LimitedDispatcher.runningWorkers$FU = AtomicIntegerFieldUpdater.newUpdater(LimitedDispatcher.class, "runningWorkers");
    }

    public LimitedDispatcher(CoroutineDispatcher coroutineDispatcher0, int v) {
        this.dispatcher = coroutineDispatcher0;
        this.parallelism = v;
        Delay delay0 = coroutineDispatcher0 instanceof Delay ? ((Delay)coroutineDispatcher0) : null;
        if(delay0 == null) {
            delay0 = DefaultExecutorKt.getDefaultDelay();
        }
        this.$$delegate_0 = delay0;
        this.queue = new LockFreeTaskQueue(false);
        this.workerAllocationLock = new Object();
    }

    @Override  // kotlinx.coroutines.Delay
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    public Object delay(long v, Continuation continuation0) {
        return this.$$delegate_0.delay(v, continuation0);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext0, Runnable runnable0) {
        this.queue.addLast(runnable0);
        if(LimitedDispatcher.runningWorkers$FU.get(this) < this.parallelism && this.tryAllocateWorker()) {
            Runnable runnable1 = this.obtainTaskOrDeallocateWorker();
            if(runnable1 != null) {
                Worker limitedDispatcher$Worker0 = new Worker(this, runnable1);
                this.dispatcher.dispatch(this, limitedDispatcher$Worker0);
            }
        }
    }

    private final void dispatchInternal(Runnable runnable0, Function1 function10) {
        this.queue.addLast(runnable0);
        if(LimitedDispatcher.runningWorkers$FU.get(this) < this.parallelism && this.tryAllocateWorker()) {
            Runnable runnable1 = this.obtainTaskOrDeallocateWorker();
            if(runnable1 != null) {
                function10.invoke(new Worker(this, runnable1));
            }
        }
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(CoroutineContext coroutineContext0, Runnable runnable0) {
        this.queue.addLast(runnable0);
        if(LimitedDispatcher.runningWorkers$FU.get(this) < this.parallelism && this.tryAllocateWorker()) {
            Runnable runnable1 = this.obtainTaskOrDeallocateWorker();
            if(runnable1 != null) {
                Worker limitedDispatcher$Worker0 = new Worker(this, runnable1);
                this.dispatcher.dispatchYield(this, limitedDispatcher$Worker0);
            }
        }
    }

    @Override  // kotlinx.coroutines.Delay
    public DisposableHandle invokeOnTimeout(long v, Runnable runnable0, CoroutineContext coroutineContext0) {
        return this.$$delegate_0.invokeOnTimeout(v, runnable0, coroutineContext0);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public CoroutineDispatcher limitedParallelism(int v) {
        LimitedDispatcherKt.checkParallelism(v);
        return v >= this.parallelism ? this : super.limitedParallelism(v);
    }

    private final Runnable obtainTaskOrDeallocateWorker() {
        Runnable runnable0;
        while((runnable0 = (Runnable)this.queue.removeFirstOrNull()) == null) {
            synchronized(this.workerAllocationLock) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = LimitedDispatcher.runningWorkers$FU;
                atomicIntegerFieldUpdater0.decrementAndGet(this);
                if(this.queue.getSize() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater0.incrementAndGet(this);
            }
        }
        return runnable0;
    }

    @Override  // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long v, CancellableContinuation cancellableContinuation0) {
        this.$$delegate_0.scheduleResumeAfterDelay(v, cancellableContinuation0);
    }

    private final boolean tryAllocateWorker() {
        synchronized(this.workerAllocationLock) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = LimitedDispatcher.runningWorkers$FU;
            if(atomicIntegerFieldUpdater0.get(this) >= this.parallelism) {
                return false;
            }
            atomicIntegerFieldUpdater0.incrementAndGet(this);
            return true;
        }
    }
}

