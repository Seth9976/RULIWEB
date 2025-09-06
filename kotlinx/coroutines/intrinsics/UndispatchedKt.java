package kotlinx.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A9\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u00042\u001A\u0010\u0005\u001A\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0082\b\u001A>\u0010\b\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\t\u001AR\u0010\b\u001A\u00020\u0001\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u0002*\u001E\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000B2\u0006\u0010\f\u001A\u0002H\n2\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\r\u001A>\u0010\u000E\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\t\u001AY\u0010\u000F\u001A\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00020\u00102\u0006\u0010\f\u001A\u0002H\n2\'\u0010\u0005\u001A#\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000B\u00A2\u0006\u0002\b\u0011H\u0000\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0012\u001AY\u0010\u0013\u001A\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00020\u00102\u0006\u0010\f\u001A\u0002H\n2\'\u0010\u0005\u001A#\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000B\u00A2\u0006\u0002\b\u0011H\u0000\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0012\u001A?\u0010\u0014\u001A\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00102\u0012\u0010\u0015\u001A\u000E\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00062\u000E\u0010\u0018\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0019H\u0082\b\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\u001A"}, d2 = {"startDirect", "", "T", "completion", "Lkotlin/coroutines/Continuation;", "block", "Lkotlin/Function1;", "", "startCoroutineUndispatched", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "R", "Lkotlin/Function2;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "startCoroutineUnintercepted", "startUndispatchedOrReturn", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/internal/ScopeCoroutine;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "startUndispatchedOrReturnIgnoreTimeout", "undispatchedResult", "shouldThrow", "", "", "startBlock", "Lkotlin/Function0;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class UndispatchedKt {
    public static final void startCoroutineUndispatched(Function1 function10, Continuation continuation0) {
        Object object1;
        Continuation continuation1 = DebugProbesKt.probeCoroutineCreated(continuation0);
        try {
            CoroutineContext coroutineContext0 = continuation0.getContext();
            Object object0 = ThreadContextKt.updateThreadContext(coroutineContext0, null);
            try {
                object1 = ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function10, 1)).invoke(continuation1);
            }
            finally {
                ThreadContextKt.restoreThreadContext(coroutineContext0, object0);
            }
        }
        catch(Throwable throwable0) {
            continuation1.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
            return;
        }
        if(object1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            continuation1.resumeWith(object1);
        }
    }

    public static final void startCoroutineUndispatched(Function2 function20, Object object0, Continuation continuation0) {
        Object object2;
        Continuation continuation1 = DebugProbesKt.probeCoroutineCreated(continuation0);
        try {
            CoroutineContext coroutineContext0 = continuation0.getContext();
            Object object1 = ThreadContextKt.updateThreadContext(coroutineContext0, null);
            try {
                object2 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function20, 2)).invoke(object0, continuation1);
            }
            finally {
                ThreadContextKt.restoreThreadContext(coroutineContext0, object1);
            }
        }
        catch(Throwable throwable0) {
            continuation1.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
            return;
        }
        if(object2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            continuation1.resumeWith(object2);
        }
    }

    public static final void startCoroutineUnintercepted(Function1 function10, Continuation continuation0) {
        Object object0;
        Continuation continuation1 = DebugProbesKt.probeCoroutineCreated(continuation0);
        try {
            object0 = ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function10, 1)).invoke(continuation1);
        }
        catch(Throwable throwable0) {
            continuation1.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
            return;
        }
        if(object0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            continuation1.resumeWith(object0);
        }
    }

    private static final void startDirect(Continuation continuation0, Function1 function10) {
        Object object0;
        Continuation continuation1 = DebugProbesKt.probeCoroutineCreated(continuation0);
        try {
            object0 = function10.invoke(continuation1);
        }
        catch(Throwable throwable0) {
            continuation1.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
            return;
        }
        if(object0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            continuation1.resumeWith(object0);
        }
    }

    public static final Object startUndispatchedOrReturn(ScopeCoroutine scopeCoroutine0, Object object0, Function2 function20) {
        CompletedExceptionally completedExceptionally0;
        try {
            completedExceptionally0 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function20, 2)).invoke(object0, scopeCoroutine0);
        }
        catch(Throwable throwable0) {
            completedExceptionally0 = new CompletedExceptionally(throwable0, false, 2, null);
        }
        if(completedExceptionally0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        Object object1 = scopeCoroutine0.makeCompletingOnce$kotlinx_coroutines_core(completedExceptionally0);
        if(object1 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if(object1 instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally)object1).cause;
        }
        return JobSupportKt.unboxState(object1);
    }

    public static final Object startUndispatchedOrReturnIgnoreTimeout(ScopeCoroutine scopeCoroutine0, Object object0, Function2 function20) {
        CompletedExceptionally completedExceptionally0;
        try {
            completedExceptionally0 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function20, 2)).invoke(object0, scopeCoroutine0);
        }
        catch(Throwable throwable0) {
            completedExceptionally0 = new CompletedExceptionally(throwable0, false, 2, null);
        }
        if(completedExceptionally0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        Object object1 = scopeCoroutine0.makeCompletingOnce$kotlinx_coroutines_core(completedExceptionally0);
        if(object1 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if(object1 instanceof CompletedExceptionally) {
            Throwable throwable1 = ((CompletedExceptionally)object1).cause;
            if(!(throwable1 instanceof TimeoutCancellationException) || ((TimeoutCancellationException)throwable1).coroutine != scopeCoroutine0) {
                throw ((CompletedExceptionally)object1).cause;
            }
            if(completedExceptionally0 instanceof CompletedExceptionally) {
                throw completedExceptionally0.cause;
            }
            return completedExceptionally0;
        }
        return JobSupportKt.unboxState(object1);
    }

    private static final Object undispatchedResult(ScopeCoroutine scopeCoroutine0, Function1 function10, Function0 function00) {
        CompletedExceptionally completedExceptionally0;
        try {
            completedExceptionally0 = function00.invoke();
        }
        catch(Throwable throwable0) {
            completedExceptionally0 = new CompletedExceptionally(throwable0, false, 2, null);
        }
        if(completedExceptionally0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        Object object0 = scopeCoroutine0.makeCompletingOnce$kotlinx_coroutines_core(completedExceptionally0);
        if(object0 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if(object0 instanceof CompletedExceptionally) {
            if(((Boolean)function10.invoke(((CompletedExceptionally)object0).cause)).booleanValue()) {
                throw ((CompletedExceptionally)object0).cause;
            }
            if(completedExceptionally0 instanceof CompletedExceptionally) {
                throw completedExceptionally0.cause;
            }
            return completedExceptionally0;
        }
        return JobSupportKt.unboxState(object0);
    }
}

