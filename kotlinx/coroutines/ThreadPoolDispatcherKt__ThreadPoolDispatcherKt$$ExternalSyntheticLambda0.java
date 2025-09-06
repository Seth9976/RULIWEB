package kotlinx.coroutines;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class ThreadPoolDispatcherKt__ThreadPoolDispatcherKt..ExternalSyntheticLambda0 implements ThreadFactory {
    public final int f$0;
    public final String f$1;
    public final AtomicInteger f$2;

    public ThreadPoolDispatcherKt__ThreadPoolDispatcherKt..ExternalSyntheticLambda0(int v, String s, AtomicInteger atomicInteger0) {
        this.f$0 = v;
        this.f$1 = s;
        this.f$2 = atomicInteger0;
    }

    @Override
    public final Thread newThread(Runnable runnable0) {
        return ThreadPoolDispatcherKt__ThreadPoolDispatcherKt.$r8$lambda$XFVhsA28fmYUFDEvkT7eVQ1JeFw(this.f$0, this.f$1, this.f$2, runnable0);
    }
}

