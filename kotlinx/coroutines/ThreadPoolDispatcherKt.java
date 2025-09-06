package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata(d1 = {"kotlinx/coroutines/ThreadPoolDispatcherKt__MultithreadedDispatchers_commonKt", "kotlinx/coroutines/ThreadPoolDispatcherKt__ThreadPoolDispatcherKt"}, k = 4, mv = {1, 8, 0}, xi = 0x30)
public final class ThreadPoolDispatcherKt {
    public static final ExecutorCoroutineDispatcher newFixedThreadPoolContext(int v, String s) {
        return ThreadPoolDispatcherKt__ThreadPoolDispatcherKt.newFixedThreadPoolContext(v, s);
    }

    public static final ExecutorCoroutineDispatcher newSingleThreadContext(String s) {
        return ThreadPoolDispatcherKt__MultithreadedDispatchers_commonKt.newSingleThreadContext(s);
    }
}

