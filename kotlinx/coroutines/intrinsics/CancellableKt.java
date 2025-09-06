package kotlinx.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001A\u001C\u0010\u0000\u001A\u00020\u00012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0002\u001A#\u0010\u0006\u001A\u00020\u00012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u00032\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00010\bH\u0082\b\u001A\u001E\u0010\t\u001A\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00032\n\u0010\n\u001A\u0006\u0012\u0002\b\u00030\u0003H\u0000\u001A>\u0010\t\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u000B*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000B0\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\f2\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0003H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000E\u001Ay\u0010\t\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u000F\"\u0004\b\u0001\u0010\u000B*\u001E\b\u0001\u0012\u0004\u0012\u0002H\u000F\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000B0\u0003\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00102\u0006\u0010\u0011\u001A\u0002H\u000F2\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u00032%\b\u0002\u0010\u0012\u001A\u001F\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0001\u0018\u00010\fH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"dispatcherFailure", "", "completion", "Lkotlin/coroutines/Continuation;", "e", "", "runSafely", "block", "Lkotlin/Function0;", "startCoroutineCancellable", "fatalCompletion", "T", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "R", "Lkotlin/Function2;", "receiver", "onCancellation", "Lkotlin/ParameterName;", "name", "cause", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;Lkotlin/jvm/functions/Function1;)V", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CancellableKt {
    private static final void dispatcherFailure(Continuation continuation0, Throwable throwable0) {
        continuation0.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
        throw throwable0;
    }

    private static final void runSafely(Continuation continuation0, Function0 function00) {
        try {
            function00.invoke();
        }
        catch(Throwable throwable0) {
            CancellableKt.dispatcherFailure(continuation0, throwable0);
        }
    }

    public static final void startCoroutineCancellable(Continuation continuation0, Continuation continuation1) {
        try {
            DispatchedContinuationKt.resumeCancellableWith$default(IntrinsicsKt.intercepted(continuation0), Unit.INSTANCE, null, 2, null);
        }
        catch(Throwable throwable0) {
            CancellableKt.dispatcherFailure(continuation1, throwable0);
        }
    }

    public static final void startCoroutineCancellable(Function1 function10, Continuation continuation0) {
        try {
            DispatchedContinuationKt.resumeCancellableWith$default(IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function10, continuation0)), Unit.INSTANCE, null, 2, null);
        }
        catch(Throwable throwable0) {
            CancellableKt.dispatcherFailure(continuation0, throwable0);
        }
    }

    public static final void startCoroutineCancellable(Function2 function20, Object object0, Continuation continuation0, Function1 function10) {
        try {
            DispatchedContinuationKt.resumeCancellableWith(IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function20, object0, continuation0)), Unit.INSTANCE, function10);
        }
        catch(Throwable throwable0) {
            CancellableKt.dispatcherFailure(continuation0, throwable0);
        }
    }

    public static void startCoroutineCancellable$default(Function2 function20, Object object0, Continuation continuation0, Function1 function10, int v, Object object1) {
        if((v & 4) != 0) {
            function10 = null;
        }
        CancellableKt.startCoroutineCancellable(function20, object0, continuation0, function10);
    }
}

