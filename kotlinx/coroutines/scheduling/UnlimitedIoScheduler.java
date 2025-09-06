package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.internal.LimitedDispatcherKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001C\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\n\u0010\u0007\u001A\u00060\bj\u0002`\tH\u0016J\u001C\u0010\n\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\n\u0010\u0007\u001A\u00060\bj\u0002`\tH\u0017J\u0010\u0010\u000B\u001A\u00020\u00012\u0006\u0010\f\u001A\u00020\rH\u0017¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/scheduling/UnlimitedIoScheduler;", "Lkotlinx/coroutines/CoroutineDispatcher;", "()V", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchYield", "limitedParallelism", "parallelism", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class UnlimitedIoScheduler extends CoroutineDispatcher {
    public static final UnlimitedIoScheduler INSTANCE;

    static {
        UnlimitedIoScheduler.INSTANCE = new UnlimitedIoScheduler();
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext0, Runnable runnable0) {
        DefaultScheduler.INSTANCE.dispatchWithContext$kotlinx_coroutines_core(runnable0, TasksKt.BlockingContext, false);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(CoroutineContext coroutineContext0, Runnable runnable0) {
        DefaultScheduler.INSTANCE.dispatchWithContext$kotlinx_coroutines_core(runnable0, TasksKt.BlockingContext, true);
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public CoroutineDispatcher limitedParallelism(int v) {
        LimitedDispatcherKt.checkParallelism(v);
        return v >= TasksKt.MAX_POOL_SIZE ? this : super.limitedParallelism(v);
    }
}

