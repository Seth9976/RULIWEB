package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\ba\u0018\u00002\u00020\u0001J\u001D\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0002\u000B\n\u0005\b¡\u001E0\u0001\n\u0002\b\u0019¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/DelayWithTimeoutDiagnostics;", "Lkotlinx/coroutines/Delay;", "timeoutMessage", "", "timeout", "Lkotlin/time/Duration;", "timeoutMessage-LRDsOJo", "(J)Ljava/lang/String;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface DelayWithTimeoutDiagnostics extends Delay {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
        public static Object delay(DelayWithTimeoutDiagnostics delayWithTimeoutDiagnostics0, long v, Continuation continuation0) {
            Object object0 = kotlinx.coroutines.Delay.DefaultImpls.delay(delayWithTimeoutDiagnostics0, v, continuation0);
            return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
        }

        public static DisposableHandle invokeOnTimeout(DelayWithTimeoutDiagnostics delayWithTimeoutDiagnostics0, long v, Runnable runnable0, CoroutineContext coroutineContext0) {
            return kotlinx.coroutines.Delay.DefaultImpls.invokeOnTimeout(delayWithTimeoutDiagnostics0, v, runnable0, coroutineContext0);
        }
    }

    String timeoutMessage-LRDsOJo(long arg1);
}

