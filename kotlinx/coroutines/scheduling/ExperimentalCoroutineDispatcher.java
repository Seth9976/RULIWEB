package kotlinx.coroutines.scheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DefaultExecutor;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0005\b\u0011\u0018\u00002\u00020\u0001B%\b\u0016\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001B\b\u0017\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0003¢\u0006\u0002\u0010\bB\'\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\t\u001A\u00020\n\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u000BJ\u0010\u0010\u0012\u001A\u00020\u00132\b\b\u0002\u0010\u0014\u001A\u00020\u0003J\b\u0010\u0015\u001A\u00020\u0016H\u0016J\b\u0010\u0017\u001A\u00020\rH\u0002J\u001C\u0010\u0018\u001A\u00020\u00162\u0006\u0010\u0019\u001A\u00020\u001A2\n\u0010\u001B\u001A\u00060\u001Cj\u0002`\u001DH\u0016J)\u0010\u001E\u001A\u00020\u00162\n\u0010\u001B\u001A\u00060\u001Cj\u0002`\u001D2\u0006\u0010\u0019\u001A\u00020\u001F2\u0006\u0010 \u001A\u00020!H\u0000¢\u0006\u0002\b\"J\u001C\u0010#\u001A\u00020\u00162\u0006\u0010\u0019\u001A\u00020\u001A2\n\u0010\u001B\u001A\u00060\u001Cj\u0002`\u001DH\u0016J\u000E\u0010$\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00020\u0003J\b\u0010%\u001A\u00020\u0006H\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\rX\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u000E\u001A\u00020\u000F8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "corePoolSize", "", "maxPoolSize", "schedulerName", "", "(IILjava/lang/String;)V", "(II)V", "idleWorkerKeepAliveNs", "", "(IIJLjava/lang/String;)V", "coroutineScheduler", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "blocking", "Lkotlinx/coroutines/CoroutineDispatcher;", "parallelism", "close", "", "createScheduler", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchWithContext", "Lkotlinx/coroutines/scheduling/TaskContext;", "tailDispatch", "", "dispatchWithContext$kotlinx_coroutines_core", "dispatchYield", "limited", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class ExperimentalCoroutineDispatcher extends ExecutorCoroutineDispatcher {
    private final int corePoolSize;
    private CoroutineScheduler coroutineScheduler;
    private final long idleWorkerKeepAliveNs;
    private final int maxPoolSize;
    private final String schedulerName;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility for Ktor 1.0-beta")
    public ExperimentalCoroutineDispatcher(int v, int v1) {
        this(v, v1, TasksKt.IDLE_WORKER_KEEP_ALIVE_NS, null, 8, null);
    }

    public ExperimentalCoroutineDispatcher(int v, int v1, int v2, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v2 & 1) != 0) {
            v = TasksKt.CORE_POOL_SIZE;
        }
        if((v2 & 2) != 0) {
            v1 = TasksKt.MAX_POOL_SIZE;
        }
        this(v, v1);
    }

    public ExperimentalCoroutineDispatcher(int v, int v1, long v2, String s) {
        this.corePoolSize = v;
        this.maxPoolSize = v1;
        this.idleWorkerKeepAliveNs = v2;
        this.schedulerName = s;
        this.coroutineScheduler = this.createScheduler();
    }

    public ExperimentalCoroutineDispatcher(int v, int v1, long v2, String s, int v3, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v3 & 8) != 0) {
            s = "CoroutineScheduler";
        }
        this(v, v1, v2, s);
    }

    public ExperimentalCoroutineDispatcher(int v, int v1, String s) {
        this(v, v1, TasksKt.IDLE_WORKER_KEEP_ALIVE_NS, s);
    }

    public ExperimentalCoroutineDispatcher(int v, int v1, String s, int v2, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v2 & 1) != 0) {
            v = TasksKt.CORE_POOL_SIZE;
        }
        if((v2 & 2) != 0) {
            v1 = TasksKt.MAX_POOL_SIZE;
        }
        if((v2 & 4) != 0) {
            s = "DefaultDispatcher";
        }
        this(v, v1, s);
    }

    public final CoroutineDispatcher blocking(int v) {
        if(v <= 0) {
            throw new IllegalArgumentException(("Expected positive parallelism level, but have " + v).toString());
        }
        return new LimitingDispatcher(this, v, null, 1);
    }

    public static CoroutineDispatcher blocking$default(ExperimentalCoroutineDispatcher experimentalCoroutineDispatcher0, int v, int v1, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: blocking");
        }
        if((v1 & 1) != 0) {
            v = 16;
        }
        return experimentalCoroutineDispatcher0.blocking(v);
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
        try {
            CoroutineScheduler.dispatch$default(this.coroutineScheduler, runnable0, null, false, 6, null);
        }
        catch(RejectedExecutionException unused_ex) {
            DefaultExecutor.INSTANCE.dispatch(coroutineContext0, runnable0);
        }
    }

    public final void dispatchWithContext$kotlinx_coroutines_core(Runnable runnable0, TaskContext taskContext0, boolean z) {
        try {
            this.coroutineScheduler.dispatch(runnable0, taskContext0, z);
        }
        catch(RejectedExecutionException unused_ex) {
            Runnable runnable1 = this.coroutineScheduler.createTask(runnable0, taskContext0);
            DefaultExecutor.INSTANCE.enqueue(runnable1);
        }
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(CoroutineContext coroutineContext0, Runnable runnable0) {
        try {
            CoroutineScheduler.dispatch$default(this.coroutineScheduler, runnable0, null, true, 2, null);
        }
        catch(RejectedExecutionException unused_ex) {
            DefaultExecutor.INSTANCE.dispatchYield(coroutineContext0, runnable0);
        }
    }

    @Override  // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public Executor getExecutor() {
        return this.coroutineScheduler;
    }

    public final CoroutineDispatcher limited(int v) {
        if(v <= 0) {
            throw new IllegalArgumentException(("Expected positive parallelism level, but have " + v).toString());
        }
        if(v > this.corePoolSize) {
            throw new IllegalArgumentException(("Expected parallelism level lesser than core pool size (" + this.corePoolSize + "), but have " + v).toString());
        }
        return new LimitingDispatcher(this, v, null, 0);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return super.toString() + "[scheduler = " + this.coroutineScheduler + ']';
    }
}

