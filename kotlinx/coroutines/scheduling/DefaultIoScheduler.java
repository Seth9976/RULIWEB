package kotlinx.coroutines.scheduling;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\bÀ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\t\u001A\u00020\nH\u0016J\u001C\u0010\u000B\u001A\u00020\n2\u0006\u0010\f\u001A\u00020\r2\n\u0010\u000E\u001A\u00060\u000Fj\u0002`\u0010H\u0016J\u001C\u0010\u0011\u001A\u00020\n2\u0006\u0010\f\u001A\u00020\r2\n\u0010\u000E\u001A\u00060\u000Fj\u0002`\u0010H\u0017J\u0010\u0010\u0012\u001A\u00020\n2\u0006\u0010\u0013\u001A\u00020\u000FH\u0016J\u0010\u0010\u0014\u001A\u00020\u00052\u0006\u0010\u0015\u001A\u00020\u0016H\u0017J\b\u0010\u0017\u001A\u00020\u0018H\u0016R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/scheduling/DefaultIoScheduler;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Ljava/util/concurrent/Executor;", "()V", "default", "Lkotlinx/coroutines/CoroutineDispatcher;", "executor", "getExecutor", "()Ljava/util/concurrent/Executor;", "close", "", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchYield", "execute", "command", "limitedParallelism", "parallelism", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DefaultIoScheduler extends ExecutorCoroutineDispatcher implements Executor {
    public static final DefaultIoScheduler INSTANCE;
    private static final CoroutineDispatcher default;

    static {
        DefaultIoScheduler.INSTANCE = new DefaultIoScheduler();
        int v = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.io.parallelism", RangesKt.coerceAtLeast(0x40, 8), 0, 0, 12, null);
        DefaultIoScheduler.default = UnlimitedIoScheduler.INSTANCE.limitedParallelism(v);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO");
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext0, Runnable runnable0) {
        DefaultIoScheduler.default.dispatch(coroutineContext0, runnable0);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(CoroutineContext coroutineContext0, Runnable runnable0) {
        DefaultIoScheduler.default.dispatchYield(coroutineContext0, runnable0);
    }

    @Override
    public void execute(Runnable runnable0) {
        this.dispatch(EmptyCoroutineContext.INSTANCE, runnable0);
    }

    @Override  // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public Executor getExecutor() {
        return this;
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public CoroutineDispatcher limitedParallelism(int v) {
        return UnlimitedIoScheduler.INSTANCE.limitedParallelism(v);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return "Dispatchers.IO";
    }
}

