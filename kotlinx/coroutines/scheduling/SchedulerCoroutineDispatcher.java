package kotlinx.coroutines.scheduling;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\n\b\u0010\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0010\u001A\u00020\u0011H\u0016J\b\u0010\u0012\u001A\u00020\u000BH\u0002J\u001C\u0010\u0013\u001A\u00020\u00112\u0006\u0010\u0014\u001A\u00020\u00152\n\u0010\u0016\u001A\u00060\u0017j\u0002`\u0018H\u0016J)\u0010\u0019\u001A\u00020\u00112\n\u0010\u0016\u001A\u00060\u0017j\u0002`\u00182\u0006\u0010\u0014\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\u001CH\u0000¢\u0006\u0002\b\u001DJ\u001C\u0010\u001E\u001A\u00020\u00112\u0006\u0010\u0014\u001A\u00020\u00152\n\u0010\u0016\u001A\u00060\u0017j\u0002`\u0018H\u0016J\r\u0010\u001F\u001A\u00020\u0011H\u0000¢\u0006\u0002\b J\u0015\u0010!\u001A\u00020\u00112\u0006\u0010\"\u001A\u00020\u0006H\u0000¢\u0006\u0002\b#J\r\u0010$\u001A\u00020\u0011H\u0000¢\u0006\u0002\b%R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001A\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u000FR\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lkotlinx/coroutines/scheduling/SchedulerCoroutineDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "corePoolSize", "", "maxPoolSize", "idleWorkerKeepAliveNs", "", "schedulerName", "", "(IIJLjava/lang/String;)V", "coroutineScheduler", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "close", "", "createScheduler", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchWithContext", "Lkotlinx/coroutines/scheduling/TaskContext;", "tailDispatch", "", "dispatchWithContext$kotlinx_coroutines_core", "dispatchYield", "restore", "restore$kotlinx_coroutines_core", "shutdown", "timeout", "shutdown$kotlinx_coroutines_core", "usePrivateScheduler", "usePrivateScheduler$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class SchedulerCoroutineDispatcher extends ExecutorCoroutineDispatcher {
    private final int corePoolSize;
    private CoroutineScheduler coroutineScheduler;
    private final long idleWorkerKeepAliveNs;
    private final int maxPoolSize;
    private final String schedulerName;

    public SchedulerCoroutineDispatcher() {
        this(0, 0, 0L, null, 15, null);
    }

    public SchedulerCoroutineDispatcher(int v, int v1, long v2, String s) {
        this.corePoolSize = v;
        this.maxPoolSize = v1;
        this.idleWorkerKeepAliveNs = v2;
        this.schedulerName = s;
        this.coroutineScheduler = this.createScheduler();
    }

    public SchedulerCoroutineDispatcher(int v, int v1, long v2, String s, int v3, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v3 & 1) != 0) {
            v = TasksKt.CORE_POOL_SIZE;
        }
        if((v3 & 2) != 0) {
            v1 = TasksKt.MAX_POOL_SIZE;
        }
        if((v3 & 4) != 0) {
            v2 = TasksKt.IDLE_WORKER_KEEP_ALIVE_NS;
        }
        if((v3 & 8) != 0) {
            s = "CoroutineScheduler";
        }
        this(v, v1, v2, s);
    }

    @Override  // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public void close() {
        this.coroutineScheduler.close();
    }

    private final CoroutineScheduler createScheduler() {
        return new CoroutineScheduler(this.corePoolSize, this.maxPoolSize, this.idleWorkerKeepAliveNs, this.schedulerName);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext0, Runnable runnable0) {
        CoroutineScheduler.dispatch$default(this.coroutineScheduler, runnable0, null, false, 6, null);
    }

    public final void dispatchWithContext$kotlinx_coroutines_core(Runnable runnable0, TaskContext taskContext0, boolean z) {
        this.coroutineScheduler.dispatch(runnable0, taskContext0, z);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(CoroutineContext coroutineContext0, Runnable runnable0) {
        CoroutineScheduler.dispatch$default(this.coroutineScheduler, runnable0, null, true, 2, null);
    }

    @Override  // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public Executor getExecutor() {
        return this.coroutineScheduler;
    }

    public final void restore$kotlinx_coroutines_core() {
        this.usePrivateScheduler$kotlinx_coroutines_core();
    }

    public final void shutdown$kotlinx_coroutines_core(long v) {
        synchronized(this) {
            this.coroutineScheduler.shutdown(v);
        }
    }

    public final void usePrivateScheduler$kotlinx_coroutines_core() {
        synchronized(this) {
            this.coroutineScheduler.shutdown(1000L);
            this.coroutineScheduler = this.createScheduler();
        }
    }
}

