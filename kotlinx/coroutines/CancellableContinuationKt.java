package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.DispatchedContinuation;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001A\"\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000\u001A3\u0010\u0005\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u00022\u001A\b\u0004\u0010\u0006\u001A\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001A3\u0010\u000B\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u00022\u001A\b\u0004\u0010\u0006\u001A\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\t0\u0007H\u0080Hø\u0001\u0000¢\u0006\u0002\u0010\n\u001A\u0018\u0010\f\u001A\u00020\t*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\r\u001A\u00020\u000EH\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000F"}, d2 = {"getOrCreateCancellableContinuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "T", "delegate", "Lkotlin/coroutines/Continuation;", "suspendCancellableCoroutine", "block", "Lkotlin/Function1;", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "suspendCancellableCoroutineReusable", "disposeOnCancellation", "handle", "Lkotlinx/coroutines/DisposableHandle;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CancellableContinuationKt {
    public static final void disposeOnCancellation(CancellableContinuation cancellableContinuation0, DisposableHandle disposableHandle0) {
        cancellableContinuation0.invokeOnCancellation(new DisposeOnCancel(disposableHandle0));
    }

    public static final CancellableContinuationImpl getOrCreateCancellableContinuation(Continuation continuation0) {
        if(!(continuation0 instanceof DispatchedContinuation)) {
            return new CancellableContinuationImpl(continuation0, 1);
        }
        CancellableContinuationImpl cancellableContinuationImpl0 = ((DispatchedContinuation)continuation0).claimReusableCancellableContinuation$kotlinx_coroutines_core();
        if(cancellableContinuationImpl0 != null) {
            if(!cancellableContinuationImpl0.resetStateReusable()) {
                cancellableContinuationImpl0 = null;
            }
            return cancellableContinuationImpl0 == null ? new CancellableContinuationImpl(continuation0, 2) : cancellableContinuationImpl0;
        }
        return new CancellableContinuationImpl(continuation0, 2);
    }

    public static final Object suspendCancellableCoroutine(Function1 function10, Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        function10.invoke(cancellableContinuationImpl0);
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    private static final Object suspendCancellableCoroutine$$forInline(Function1 function10, Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        function10.invoke(cancellableContinuationImpl0);
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    public static final Object suspendCancellableCoroutineReusable(Function1 function10, Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation0));
        try {
            function10.invoke(cancellableContinuationImpl0);
        }
        catch(Throwable throwable0) {
            cancellableContinuationImpl0.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw throwable0;
        }
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    private static final Object suspendCancellableCoroutineReusable$$forInline(Function1 function10, Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation0));
        try {
            function10.invoke(cancellableContinuationImpl0);
        }
        catch(Throwable throwable0) {
            cancellableContinuationImpl0.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw throwable0;
        }
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }
}

