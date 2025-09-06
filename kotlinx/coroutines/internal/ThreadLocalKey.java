package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0081\b\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0011\u0012\n\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\u0006\u001A\u0006\u0012\u0002\b\u00030\u0004HÂ\u0003J\u0017\u0010\u0007\u001A\u00020\u00002\f\b\u0002\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004HÆ\u0001J\u0013\u0010\b\u001A\u00020\t2\b\u0010\n\u001A\u0004\u0018\u00010\u000BHÖ\u0003J\t\u0010\f\u001A\u00020\rHÖ\u0001J\t\u0010\u000E\u001A\u00020\u000FHÖ\u0001R\u0012\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/internal/ThreadLocalKey;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/internal/ThreadLocalElement;", "threadLocal", "Ljava/lang/ThreadLocal;", "(Ljava/lang/ThreadLocal;)V", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ThreadLocalKey implements Key {
    private final ThreadLocal threadLocal;

    public ThreadLocalKey(ThreadLocal threadLocal0) {
        this.threadLocal = threadLocal0;
    }

    private final ThreadLocal component1() {
        return this.threadLocal;
    }

    public final ThreadLocalKey copy(ThreadLocal threadLocal0) {
        return new ThreadLocalKey(threadLocal0);
    }

    public static ThreadLocalKey copy$default(ThreadLocalKey threadLocalKey0, ThreadLocal threadLocal0, int v, Object object0) {
        if((v & 1) != 0) {
            threadLocal0 = threadLocalKey0.threadLocal;
        }
        return threadLocalKey0.copy(threadLocal0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof ThreadLocalKey ? Intrinsics.areEqual(this.threadLocal, ((ThreadLocalKey)object0).threadLocal) : false;
    }

    @Override
    public int hashCode() {
        return this.threadLocal.hashCode();
    }

    @Override
    public String toString() {
        return "ThreadLocalKey(threadLocal=" + this.threadLocal + ')';
    }
}

