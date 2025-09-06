package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ThreadContextElement;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001C\u0010\u000E\u001A\u00020\u000F2\n\u0010\u0010\u001A\u0006\u0012\u0002\b\u00030\t2\b\u0010\u0011\u001A\u0004\u0018\u00010\u0001J\u000E\u0010\u0012\u001A\u00020\u000F2\u0006\u0010\u0002\u001A\u00020\u0003R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R \u0010\u0007\u001A\u0012\u0012\u000E\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000E\u0010\u000B\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/internal/ThreadState;", "", "context", "Lkotlin/coroutines/CoroutineContext;", "n", "", "(Lkotlin/coroutines/CoroutineContext;I)V", "elements", "", "Lkotlinx/coroutines/ThreadContextElement;", "[Lkotlinx/coroutines/ThreadContextElement;", "i", "values", "[Ljava/lang/Object;", "append", "", "element", "value", "restore", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ThreadState {
    public final CoroutineContext context;
    private final ThreadContextElement[] elements;
    private int i;
    private final Object[] values;

    public ThreadState(CoroutineContext coroutineContext0, int v) {
        this.context = coroutineContext0;
        this.values = new Object[v];
        this.elements = new ThreadContextElement[v];
    }

    public final void append(ThreadContextElement threadContextElement0, Object object0) {
        int v = this.i;
        this.values[v] = object0;
        this.i = v + 1;
        Intrinsics.checkNotNull(threadContextElement0, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        this.elements[v] = threadContextElement0;
    }

    public final void restore(CoroutineContext coroutineContext0) {
        int v = this.elements.length - 1;
        if(v >= 0) {
            while(true) {
                ThreadContextElement threadContextElement0 = this.elements[v];
                Intrinsics.checkNotNull(threadContextElement0);
                threadContextElement0.restoreThreadContext(coroutineContext0, this.values[v]);
                if(v - 1 < 0) {
                    break;
                }
                --v;
            }
        }
    }
}

