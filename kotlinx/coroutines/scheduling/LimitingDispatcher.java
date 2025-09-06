package kotlinx.coroutines.scheduling;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.Volatile;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\'\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\b\u0010\b\u001A\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001A\u00020\u0007¢\u0006\u0002\u0010\u000BJ\b\u0010\u0017\u001A\u00020\u0018H\u0016J\b\u0010\u0019\u001A\u00020\u0018H\u0016J\u001C\u0010\u001A\u001A\u00020\u00182\u0006\u0010\u001B\u001A\u00020\u001C2\n\u0010\u001D\u001A\u00060\u0013j\u0002`\u0014H\u0016J\u001C\u0010\u001A\u001A\u00020\u00182\n\u0010\u001D\u001A\u00060\u0013j\u0002`\u00142\u0006\u0010\u001E\u001A\u00020\u001FH\u0002J\u001C\u0010 \u001A\u00020\u00182\u0006\u0010\u001B\u001A\u00020\u001C2\n\u0010\u001D\u001A\u00060\u0013j\u0002`\u0014H\u0016J\u0014\u0010!\u001A\u00020\u00182\n\u0010\"\u001A\u00060\u0013j\u0002`\u0014H\u0016J\b\u0010#\u001A\u00020\tH\u0016R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001A\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000ER\t\u0010\u000F\u001A\u00020\u0010X\u0082\u0004R\u0010\u0010\b\u001A\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0011\u001A\f\u0012\b\u0012\u00060\u0013j\u0002`\u00140\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001A\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lkotlinx/coroutines/scheduling/LimitingDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/scheduling/TaskContext;", "Ljava/util/concurrent/Executor;", "dispatcher", "Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "parallelism", "", "name", "", "taskMode", "(Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;ILjava/lang/String;I)V", "executor", "getExecutor", "()Ljava/util/concurrent/Executor;", "inFlightTasks", "Lkotlinx/atomicfu/AtomicInt;", "queue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "getTaskMode", "()I", "afterTask", "", "close", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "tailDispatch", "", "dispatchYield", "execute", "command", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class LimitingDispatcher extends ExecutorCoroutineDispatcher implements Executor, TaskContext {
    private final ExperimentalCoroutineDispatcher dispatcher;
    @Volatile
    private volatile int inFlightTasks;
    private static final AtomicIntegerFieldUpdater inFlightTasks$FU;
    private final String name;
    private final int parallelism;
    private final ConcurrentLinkedQueue queue;
    private final int taskMode;

    static {
        LimitingDispatcher.inFlightTasks$FU = AtomicIntegerFieldUpdater.newUpdater(LimitingDispatcher.class, "inFlightTasks");
    }

    public LimitingDispatcher(ExperimentalCoroutineDispatcher experimentalCoroutineDispatcher0, int v, String s, int v1) {
        this.dispatcher = experimentalCoroutineDispatcher0;
        this.parallelism = v;
        this.name = s;
        this.taskMode = v1;
        this.queue = new ConcurrentLinkedQueue();
    }

    @Override  // kotlinx.coroutines.scheduling.TaskContext
    public void afterTask() {
        Runnable runnable0 = (Runnable)this.queue.poll();
        if(runnable0 != null) {
            this.dispatcher.dispatchWithContext$kotlinx_coroutines_core(runnable0, this, true);
            return;
        }
        LimitingDispatcher.inFlightTasks$FU.decrementAndGet(this);
        Runnable runnable1 = (Runnable)this.queue.poll();
        if(runnable1 == null) {
            return;
        }
        this.dispatch(runnable1, true);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public void close() {
        throw new IllegalStateException("Close cannot be invoked on LimitingBlockingDispatcher");
    }

    private final void dispatch(Runnable runnable0, boolean z) {
        do {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = LimitingDispatcher.inFlightTasks$FU;
            if(atomicIntegerFieldUpdater0.incrementAndGet(this) <= this.parallelism) {
                this.dispatcher.dispatchWithContext$kotlinx_coroutines_core(runnable0, this, z);
                return;
            }
            this.queue.add(runnable0);
            if(atomicIntegerFieldUpdater0.decrementAndGet(this) >= this.parallelism) {
                break;
            }
            runnable0 = (Runnable)this.queue.poll();
        }
        while(runnable0 != null);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext0, Runnable runnable0) {
        this.dispatch(runnable0, false);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(CoroutineContext coroutineContext0, Runnable runnable0) {
        this.dispatch(runnable0, true);
    }

    @Override
    public void execute(Runnable runnable0) {
        this.dispatch(runnable0, false);
    }

    @Override  // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public Executor getExecutor() {
        return this;
    }

    @Override  // kotlinx.coroutines.scheduling.TaskContext
    public int getTaskMode() {
        return this.taskMode;
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return this.name == null ? super.toString() + "[dispatcher = " + this.dispatcher + ']' : this.name;
    }
}

