package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\u001A\u0014\u0010\u0000\u001A\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"newSingleThreadContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/CloseableCoroutineDispatcher;", "name", "", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/ThreadPoolDispatcherKt")
final class ThreadPoolDispatcherKt__MultithreadedDispatchers_commonKt {
    public static final ExecutorCoroutineDispatcher newSingleThreadContext(String s) {
        return ThreadPoolDispatcherKt.newFixedThreadPoolContext(1, s);
    }
}

