package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A4\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001A\u0004\u0018\u00010\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001AI\u0010\b\u001A\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012%\b\u0002\u0010\t\u001A\u001F\u0012\u0013\u0012\u00110\u000B¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000E\u0012\u0004\u0012\u00020\u000F\u0018\u00010\nH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001A.\u0010\b\u001A\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\n\u0010\u0011\u001A\u0006\u0012\u0002\b\u00030\u0012H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"recoverResult", "Lkotlin/Result;", "T", "state", "", "uCont", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toState", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "caller", "Lkotlinx/coroutines/CancellableContinuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CompletionStateKt {
    // 去混淆评级： 中等(60)
    public static final Object recoverResult(Object object0, Continuation continuation0) {
        return object0 instanceof CompletedExceptionally ? Result.constructor-impl(ResultKt.createFailure(((CompletedExceptionally)object0).cause)) : object0;
    }

    public static final Object toState(Object object0, Function1 function10) {
        Throwable throwable0 = Result.exceptionOrNull-impl(object0);
        if(throwable0 == null) {
            return function10 != null ? new CompletedWithCancellation(object0, function10) : object0;
        }
        return new CompletedExceptionally(throwable0, false, 2, null);
    }

    public static final Object toState(Object object0, CancellableContinuation cancellableContinuation0) {
        Throwable throwable0 = Result.exceptionOrNull-impl(object0);
        return throwable0 == null ? object0 : new CompletedExceptionally(throwable0, false, 2, null);
    }

    public static Object toState$default(Object object0, Function1 function10, int v, Object object1) {
        if((v & 1) != 0) {
            function10 = null;
        }
        return CompletionStateKt.toState(object0, function10);
    }
}

