package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A\u000E\u0010\u0006\u001A\u00020\u00022\u0006\u0010\u0007\u001A\u00020\b\u001A\u0006\u0010\t\u001A\u00020\u0002\u001AM\u0010\n\u001A\u0002H\u000B\"\u0004\b\u0000\u0010\u000B2\'\u0010\f\u001A#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000B0\u000E\u0012\u0006\u0012\u0004\u0018\u00010\u000F0\r¢\u0006\u0002\b\u0010H\u0086@ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0011\u001A\u0011\u0010\u0012\u001A\u00020\bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001A\u001E\u0010\u0014\u001A\u00020\u0015*\u00020\u00022\u0006\u0010\u0016\u001A\u00020\u00172\n\b\u0002\u0010\u0018\u001A\u0004\u0018\u00010\u0019\u001A\u001C\u0010\u0014\u001A\u00020\u0015*\u00020\u00022\u0010\b\u0002\u0010\u0018\u001A\n\u0018\u00010\u001Aj\u0004\u0018\u0001`\u001B\u001A\n\u0010\u001C\u001A\u00020\u0015*\u00020\u0002\u001A\u0015\u0010\u001D\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\bH\u0086\u0002\"\u001B\u0010\u0000\u001A\u00020\u0001*\u00020\u00028F¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001A\u0004\b\u0000\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001E"}, d2 = {"isActive", "", "Lkotlinx/coroutines/CoroutineScope;", "isActive$annotations", "(Lkotlinx/coroutines/CoroutineScope;)V", "(Lkotlinx/coroutines/CoroutineScope;)Z", "CoroutineScope", "context", "Lkotlin/coroutines/CoroutineContext;", "MainScope", "coroutineScope", "R", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "currentCoroutineContext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancel", "", "message", "", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "ensureActive", "plus", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CoroutineScopeKt {
    public static final CoroutineScope CoroutineScope(CoroutineContext coroutineContext0) {
        if(coroutineContext0.get(Job.Key) == null) {
            coroutineContext0 = coroutineContext0.plus(JobKt.Job$default(null, 1, null));
        }
        return new ContextScope(coroutineContext0);
    }

    public static final CoroutineScope MainScope() {
        return new ContextScope(SupervisorKt.SupervisorJob$default(null, 1, null).plus(Dispatchers.getMain()));
    }

    public static final void cancel(CoroutineScope coroutineScope0, String s, Throwable throwable0) {
        CoroutineScopeKt.cancel(coroutineScope0, ExceptionsKt.CancellationException(s, throwable0));
    }

    public static final void cancel(CoroutineScope coroutineScope0, CancellationException cancellationException0) {
        Job job0 = (Job)coroutineScope0.getCoroutineContext().get(Job.Key);
        if(job0 == null) {
            throw new IllegalStateException(("Scope cannot be cancelled because it does not have a job: " + coroutineScope0).toString());
        }
        job0.cancel(cancellationException0);
    }

    public static void cancel$default(CoroutineScope coroutineScope0, String s, Throwable throwable0, int v, Object object0) {
        if((v & 2) != 0) {
            throwable0 = null;
        }
        CoroutineScopeKt.cancel(coroutineScope0, s, throwable0);
    }

    public static void cancel$default(CoroutineScope coroutineScope0, CancellationException cancellationException0, int v, Object object0) {
        if((v & 1) != 0) {
            cancellationException0 = null;
        }
        CoroutineScopeKt.cancel(coroutineScope0, cancellationException0);
    }

    public static final Object coroutineScope(Function2 function20, Continuation continuation0) {
        ScopeCoroutine scopeCoroutine0 = new ScopeCoroutine(continuation0.getContext(), continuation0);
        Object object0 = UndispatchedKt.startUndispatchedOrReturn(scopeCoroutine0, scopeCoroutine0, function20);
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    public static final Object currentCoroutineContext(Continuation continuation0) {
        return continuation0.getContext();
    }

    private static final Object currentCoroutineContext$$forInline(Continuation continuation0) {
        throw new NullPointerException();
    }

    public static final void ensureActive(CoroutineScope coroutineScope0) {
        JobKt.ensureActive(coroutineScope0.getCoroutineContext());
    }

    public static final boolean isActive(CoroutineScope coroutineScope0) {
        Job job0 = (Job)coroutineScope0.getCoroutineContext().get(Job.Key);
        return job0 == null ? true : job0.isActive();
    }

    public static void isActive$annotations(CoroutineScope coroutineScope0) {
    }

    public static final CoroutineScope plus(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0) {
        return new ContextScope(coroutineScope0.getCoroutineContext().plus(coroutineContext0));
    }
}

