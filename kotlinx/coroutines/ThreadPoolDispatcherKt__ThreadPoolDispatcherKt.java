package kotlinx.coroutines;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\u001A\u0018\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"newFixedThreadPoolContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "nThreads", "", "name", "", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/ThreadPoolDispatcherKt")
final class ThreadPoolDispatcherKt__ThreadPoolDispatcherKt {
    // 检测为 Lambda 实现
    public static Thread $r8$lambda$XFVhsA28fmYUFDEvkT7eVQ1JeFw(int v, String s, AtomicInteger atomicInteger0, Runnable runnable0) [...]

    public static final ExecutorCoroutineDispatcher newFixedThreadPoolContext(int v, String s) {
        if(v < 1) {
            throw new IllegalArgumentException(("Expected at least one thread, but " + v + " specified").toString());
        }
        return ExecutorsKt.from(Executors.newScheduledThreadPool(v, (Runnable runnable0) -> ThreadPoolDispatcherKt__ThreadPoolDispatcherKt.newFixedThreadPoolContext$lambda$1$ThreadPoolDispatcherKt__ThreadPoolDispatcherKt(v, s, new AtomicInteger(), runnable0)));
    }

    private static final Thread newFixedThreadPoolContext$lambda$1$ThreadPoolDispatcherKt__ThreadPoolDispatcherKt(int v, String s, AtomicInteger atomicInteger0, Runnable runnable0) {
        if(v != 1) {
            s = s + '-' + atomicInteger0.incrementAndGet();
        }
        Thread thread0 = new Thread(runnable0, s);
        thread0.setDaemon(true);
        return thread0;
    }
}

