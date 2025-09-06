package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0004\u001A?\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001A\u00020\u00012\u001A\b\u0004\u0010\n\u001A\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\f\u0012\u0004\u0012\u00020\r0\u000BH\u0087\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u001A@\u0010\u000E\u001A\u0002H\b\"\u0004\b\u0000\u0010\b2\u001A\b\u0004\u0010\u000F\u001A\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0004\u0012\u00020\r0\u000BH\u0087H\u00F8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0002\u0010\u0010\u001AD\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\r0\u0007\"\u0004\b\u0000\u0010\b*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000B2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007\u00F8\u0001\u0001\u00A2\u0006\u0002\u0010\u0014\u001A]\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\r0\u0007\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\b*#\b\u0001\u0012\u0004\u0012\u0002H\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0016\u00A2\u0006\u0002\b\u00172\u0006\u0010\u0018\u001A\u0002H\u00152\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007\u00F8\u0001\u0001\u00A2\u0006\u0002\u0010\u0019\u001A&\u0010\u001A\u001A\u00020\r\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0006\u0010\u001B\u001A\u0002H\bH\u0087\b\u00A2\u0006\u0002\u0010\u001C\u001A!\u0010\u001D\u001A\u00020\r\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0006\u0010\u001E\u001A\u00020\u001FH\u0087\b\u001A>\u0010 \u001A\u00020\r\"\u0004\b\u0000\u0010\b*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000B2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007\u00F8\u0001\u0001\u00A2\u0006\u0002\u0010!\u001AW\u0010 \u001A\u00020\r\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\b*#\b\u0001\u0012\u0004\u0012\u0002H\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0016\u00A2\u0006\u0002\b\u00172\u0006\u0010\u0018\u001A\u0002H\u00152\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007\u00F8\u0001\u0001\u00A2\u0006\u0002\u0010\"\"\u001B\u0010\u0000\u001A\u00020\u00018\u00C6\u0002X\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001A\u0004\b\u0004\u0010\u0005\u0082\u0002\u000B\n\u0005\b\u009920\u0001\n\u0002\b\u0019\u00A8\u0006#"}, d2 = {"coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext$annotations", "()V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "Continuation", "Lkotlin/coroutines/Continuation;", "T", "context", "resumeWith", "Lkotlin/Function1;", "Lkotlin/Result;", "", "suspendCoroutine", "block", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createCoroutine", "", "completion", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "resume", "value", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "resumeWithException", "exception", "", "startCoroutine", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class ContinuationKt {
    private static final Continuation Continuation(CoroutineContext coroutineContext0, Function1 function10) {
        Intrinsics.checkNotNullParameter(coroutineContext0, "context");
        Intrinsics.checkNotNullParameter(function10, "resumeWith");
        return new Continuation() {
            @Override  // kotlin.coroutines.Continuation
            public CoroutineContext getContext() {
                return coroutineContext0;
            }

            @Override  // kotlin.coroutines.Continuation
            public void resumeWith(Object object0) {
                Result result0 = Result.box-impl(object0);
                function10.invoke(result0);
            }
        };
    }

    public static final Continuation createCoroutine(Function1 function10, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(function10, "<this>");
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        return new SafeContinuation(IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function10, continuation0)), IntrinsicsKt.getCOROUTINE_SUSPENDED());
    }

    public static final Continuation createCoroutine(Function2 function20, Object object0, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(function20, "<this>");
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        return new SafeContinuation(IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function20, object0, continuation0)), IntrinsicsKt.getCOROUTINE_SUSPENDED());
    }

    private static final CoroutineContext getCoroutineContext() {
        throw new NotImplementedError("Implemented as intrinsic");
    }

    public static void getCoroutineContext$annotations() {
    }

    private static final void resume(Continuation continuation0, Object object0) {
        Intrinsics.checkNotNullParameter(continuation0, "<this>");
        continuation0.resumeWith(object0);
    }

    private static final void resumeWithException(Continuation continuation0, Throwable throwable0) {
        Intrinsics.checkNotNullParameter(continuation0, "<this>");
        Intrinsics.checkNotNullParameter(throwable0, "exception");
        continuation0.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
    }

    public static final void startCoroutine(Function1 function10, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(function10, "<this>");
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function10, continuation0)).resumeWith(Unit.INSTANCE);
    }

    public static final void startCoroutine(Function2 function20, Object object0, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(function20, "<this>");
        Intrinsics.checkNotNullParameter(continuation0, "completion");
        IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function20, object0, continuation0)).resumeWith(Result.constructor-impl(Unit.INSTANCE));
    }

    private static final Object suspendCoroutine(Function1 function10, Continuation continuation0) {
        SafeContinuation safeContinuation0 = new SafeContinuation(IntrinsicsKt.intercepted(continuation0));
        function10.invoke(safeContinuation0);
        Object object0 = safeContinuation0.getOrThrow();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }
}

