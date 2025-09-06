package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001AU\u0010\u0004\u001A\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0006\u001A\u00020\u00072\'\u0010\b\u001A#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000B\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rH\u0086@ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u000E\u001A[\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0010\"\u0004\b\u0000\u0010\u0005*\u00020\n2\b\b\u0002\u0010\u0006\u001A\u00020\u00072\b\b\u0002\u0010\u0011\u001A\u00020\u00122\'\u0010\b\u001A#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000B\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001AF\u0010\u0014\u001A\u0002H\u0005\"\u0004\b\u0000\u0010\u0005*\u00020\u00152)\b\b\u0010\b\u001A#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000B\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rH\u0086Jø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001AO\u0010\u0017\u001A\u00020\u0018*\u00020\n2\b\b\u0002\u0010\u0006\u001A\u00020\u00072\b\b\u0002\u0010\u0011\u001A\u00020\u00122\'\u0010\b\u001A#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u000B\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u001A\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0003\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001B"}, d2 = {"RESUMED", "", "SUSPENDED", "UNDECIDED", "withContext", "T", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "async", "Lkotlinx/coroutines/Deferred;", "start", "Lkotlinx/coroutines/CoroutineStart;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Deferred;", "invoke", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launch", "Lkotlinx/coroutines/Job;", "", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/BuildersKt")
final class BuildersKt__Builders_commonKt {
    private static final int RESUMED = 2;
    private static final int SUSPENDED = 1;
    private static final int UNDECIDED;

    public static final Deferred async(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20) {
        DeferredCoroutine deferredCoroutine0 = new DeferredCoroutine(CoroutineContextKt.newCoroutineContext(coroutineScope0, coroutineContext0), true);
        deferredCoroutine0.start(coroutineStart0, deferredCoroutine0, function20);
        return deferredCoroutine0;
    }

    public static Deferred async$default(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v & 2) != 0) {
            coroutineStart0 = CoroutineStart.DEFAULT;
        }
        return BuildersKt.async(coroutineScope0, coroutineContext0, coroutineStart0, function20);
    }

    public static final Object invoke(CoroutineDispatcher coroutineDispatcher0, Function2 function20, Continuation continuation0) {
        return BuildersKt.withContext(coroutineDispatcher0, function20, continuation0);
    }

    private static final Object invoke$$forInline(CoroutineDispatcher coroutineDispatcher0, Function2 function20, Continuation continuation0) {
        return BuildersKt.withContext(coroutineDispatcher0, function20, continuation0);
    }

    public static final Job launch(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20) {
        StandaloneCoroutine standaloneCoroutine0 = new StandaloneCoroutine(CoroutineContextKt.newCoroutineContext(coroutineScope0, coroutineContext0), true);
        standaloneCoroutine0.start(coroutineStart0, standaloneCoroutine0, function20);
        return standaloneCoroutine0;
    }

    public static Job launch$default(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        if((v & 2) != 0) {
            coroutineStart0 = CoroutineStart.DEFAULT;
        }
        return BuildersKt.launch(coroutineScope0, coroutineContext0, coroutineStart0, function20);
    }

    public static final Object withContext(CoroutineContext coroutineContext0, Function2 function20, Continuation continuation0) {
        Object object2;
        Object object0;
        CoroutineContext coroutineContext1 = continuation0.getContext();
        CoroutineContext coroutineContext2 = CoroutineContextKt.newCoroutineContext(coroutineContext1, coroutineContext0);
        JobKt.ensureActive(coroutineContext2);
        if(coroutineContext2 == coroutineContext1) {
            ScopeCoroutine scopeCoroutine0 = new ScopeCoroutine(coroutineContext2, continuation0);
            object0 = UndispatchedKt.startUndispatchedOrReturn(scopeCoroutine0, scopeCoroutine0, function20);
        }
        else if(Intrinsics.areEqual(coroutineContext2.get(ContinuationInterceptor.Key), coroutineContext1.get(ContinuationInterceptor.Key))) {
            UndispatchedCoroutine undispatchedCoroutine0 = new UndispatchedCoroutine(coroutineContext2, continuation0);
            CoroutineContext coroutineContext3 = undispatchedCoroutine0.getContext();
            Object object1 = ThreadContextKt.updateThreadContext(coroutineContext3, null);
            try {
                object2 = UndispatchedKt.startUndispatchedOrReturn(undispatchedCoroutine0, undispatchedCoroutine0, function20);
            }
            finally {
                ThreadContextKt.restoreThreadContext(coroutineContext3, object1);
            }
            object0 = object2;
        }
        else {
            DispatchedCoroutine dispatchedCoroutine0 = new DispatchedCoroutine(coroutineContext2, continuation0);
            CancellableKt.startCoroutineCancellable$default(function20, dispatchedCoroutine0, dispatchedCoroutine0, null, 4, null);
            object0 = dispatchedCoroutine0.getResult$kotlinx_coroutines_core();
        }
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }
}

