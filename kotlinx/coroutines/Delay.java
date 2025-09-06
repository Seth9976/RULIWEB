package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0097@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J$\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00052\n\u0010\n\u001A\u00060\u000Bj\u0002`\f2\u0006\u0010\r\u001A\u00020\u000EH\u0016J\u001E\u0010\u000F\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\u00052\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00030\u0011H&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/Delay;", "", "delay", "", "time", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "context", "Lkotlin/coroutines/CoroutineContext;", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface Delay {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
        public static Object delay(Delay delay0, long v, Continuation continuation0) {
            if(v <= 0L) {
                return Unit.INSTANCE;
            }
            CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
            cancellableContinuationImpl0.initCancellability();
            delay0.scheduleResumeAfterDelay(v, cancellableContinuationImpl0);
            Object object0 = cancellableContinuationImpl0.getResult();
            if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation0);
            }
            return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
        }

        public static DisposableHandle invokeOnTimeout(Delay delay0, long v, Runnable runnable0, CoroutineContext coroutineContext0) {
            return DefaultExecutorKt.getDefaultDelay().invokeOnTimeout(v, runnable0, coroutineContext0);
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    Object delay(long arg1, Continuation arg2);

    DisposableHandle invokeOnTimeout(long arg1, Runnable arg2, CoroutineContext arg3);

    void scheduleResumeAfterDelay(long arg1, CancellableContinuation arg2);
}

