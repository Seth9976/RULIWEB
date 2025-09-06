package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlinx.coroutines.internal.ThreadLocalElement;
import kotlinx.coroutines.internal.ThreadLocalKey;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\u001A+\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u0002H\u0002¢\u0006\u0002\u0010\u0005\u001A\u0019\u0010\u0006\u001A\u00020\u0007*\u0006\u0012\u0002\b\u00030\u0003H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\b\u001A\u0019\u0010\t\u001A\u00020\n*\u0006\u0012\u0002\b\u00030\u0003H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000B"}, d2 = {"asContextElement", "Lkotlinx/coroutines/ThreadContextElement;", "T", "Ljava/lang/ThreadLocal;", "value", "(Ljava/lang/ThreadLocal;Ljava/lang/Object;)Lkotlinx/coroutines/ThreadContextElement;", "ensurePresent", "", "(Ljava/lang/ThreadLocal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isPresent", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ThreadContextElementKt {
    public static final ThreadContextElement asContextElement(ThreadLocal threadLocal0, Object object0) {
        return new ThreadLocalElement(object0, threadLocal0);
    }

    public static ThreadContextElement asContextElement$default(ThreadLocal threadLocal0, Object object0, int v, Object object1) {
        if((v & 1) != 0) {
            object0 = threadLocal0.get();
        }
        return ThreadContextElementKt.asContextElement(threadLocal0, object0);
    }

    public static final Object ensurePresent(ThreadLocal threadLocal0, Continuation continuation0) {
        if(continuation0.getContext().get(new ThreadLocalKey(threadLocal0)) == null) {
            throw new IllegalStateException(("ThreadLocal " + threadLocal0 + " is missing from context " + continuation0.getContext()).toString());
        }
        return Unit.INSTANCE;
    }

    private static final Object ensurePresent$$forInline(ThreadLocal threadLocal0, Continuation continuation0) {
        throw new NullPointerException();
    }

    public static final Object isPresent(ThreadLocal threadLocal0, Continuation continuation0) {
        return continuation0.getContext().get(new ThreadLocalKey(threadLocal0)) == null ? Boxing.boxBoolean(false) : Boxing.boxBoolean(true);
    }

    private static final Object isPresent$$forInline(ThreadLocal threadLocal0, Continuation continuation0) {
        throw new NullPointerException();
    }
}

