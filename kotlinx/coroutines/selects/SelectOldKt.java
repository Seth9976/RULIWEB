package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;

@Metadata(d1 = {"\u0000&\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\u001A8\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u001F\b\u0004\u0010\u0002\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0081Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001A8\u0010\b\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u001F\b\u0004\u0010\u0002\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0081Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001A%\u0010\t\u001A\u00020\u0005\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000B2\u0006\u0010\f\u001A\u0002H\nH\u0002¢\u0006\u0002\u0010\r\u001A\u0018\u0010\u000E\u001A\u00020\u0005*\u0006\u0012\u0002\b\u00030\u000B2\u0006\u0010\u000F\u001A\u00020\u0010H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"selectOld", "R", "builder", "Lkotlin/Function1;", "Lkotlinx/coroutines/selects/SelectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectUnbiasedOld", "resumeUndispatched", "T", "Lkotlinx/coroutines/CancellableContinuation;", "result", "(Lkotlinx/coroutines/CancellableContinuation;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "exception", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SelectOldKt {
    private static final void resumeUndispatched(CancellableContinuation cancellableContinuation0, Object object0) {
        CoroutineDispatcher coroutineDispatcher0 = (CoroutineDispatcher)cancellableContinuation0.getContext().get(CoroutineDispatcher.Key);
        if(coroutineDispatcher0 != null) {
            cancellableContinuation0.resumeUndispatched(coroutineDispatcher0, object0);
            return;
        }
        cancellableContinuation0.resumeWith(object0);
    }

    private static final void resumeUndispatchedWithException(CancellableContinuation cancellableContinuation0, Throwable throwable0) {
        CoroutineDispatcher coroutineDispatcher0 = (CoroutineDispatcher)cancellableContinuation0.getContext().get(CoroutineDispatcher.Key);
        if(coroutineDispatcher0 != null) {
            cancellableContinuation0.resumeUndispatchedWithException(coroutineDispatcher0, throwable0);
            return;
        }
        cancellableContinuation0.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
    }

    public static final Object selectOld(Function1 function10, Continuation continuation0) {
        SelectBuilderImpl selectBuilderImpl0 = new SelectBuilderImpl(continuation0);
        try {
            function10.invoke(selectBuilderImpl0);
        }
        catch(Throwable throwable0) {
            selectBuilderImpl0.handleBuilderException(throwable0);
        }
        Object object0 = selectBuilderImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    private static final Object selectOld$$forInline(Function1 function10, Continuation continuation0) {
        SelectBuilderImpl selectBuilderImpl0 = new SelectBuilderImpl(continuation0);
        try {
            function10.invoke(selectBuilderImpl0);
        }
        catch(Throwable throwable0) {
            selectBuilderImpl0.handleBuilderException(throwable0);
        }
        Object object0 = selectBuilderImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    public static final Object selectUnbiasedOld(Function1 function10, Continuation continuation0) {
        UnbiasedSelectBuilderImpl unbiasedSelectBuilderImpl0 = new UnbiasedSelectBuilderImpl(continuation0);
        try {
            function10.invoke(unbiasedSelectBuilderImpl0);
        }
        catch(Throwable throwable0) {
            unbiasedSelectBuilderImpl0.handleBuilderException(throwable0);
        }
        Object object0 = unbiasedSelectBuilderImpl0.initSelectResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    private static final Object selectUnbiasedOld$$forInline(Function1 function10, Continuation continuation0) {
        UnbiasedSelectBuilderImpl unbiasedSelectBuilderImpl0 = new UnbiasedSelectBuilderImpl(continuation0);
        try {
            function10.invoke(unbiasedSelectBuilderImpl0);
        }
        catch(Throwable throwable0) {
            unbiasedSelectBuilderImpl0.handleBuilderException(throwable0);
        }
        Object object0 = unbiasedSelectBuilderImpl0.initSelectResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }
}

