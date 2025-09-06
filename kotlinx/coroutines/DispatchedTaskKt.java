package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A \u0010\f\u001A\u00020\r\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\u000F2\u0006\u0010\u0010\u001A\u00020\u0001H\u0000\u001A.\u0010\u0011\u001A\u00020\r\"\u0004\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\u000F2\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00132\u0006\u0010\u0014\u001A\u00020\tH\u0000\u001A\u0010\u0010\u0015\u001A\u00020\r*\u0006\u0012\u0002\b\u00030\u000FH\u0002\u001A\u0019\u0010\u0016\u001A\u00020\r*\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0017\u001A\u00020\u0018H\u0080\b\u001A\'\u0010\u0019\u001A\u00020\r*\u0006\u0012\u0002\b\u00030\u000F2\u0006\u0010\u001A\u001A\u00020\u001B2\f\u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\r0\u001DH\u0080\b\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001A\u00020\u00018\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0003\u0010\u0004\"\u000E\u0010\u0005\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0006\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0007\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0018\u0010\b\u001A\u00020\t*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\n\"\u0018\u0010\u000B\u001A\u00020\t*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\n¨\u0006\u001E"}, d2 = {"MODE_ATOMIC", "", "MODE_CANCELLABLE", "getMODE_CANCELLABLE$annotations", "()V", "MODE_CANCELLABLE_REUSABLE", "MODE_UNDISPATCHED", "MODE_UNINITIALIZED", "isCancellableMode", "", "(I)Z", "isReusableMode", "dispatch", "", "T", "Lkotlinx/coroutines/DispatchedTask;", "mode", "resume", "delegate", "Lkotlin/coroutines/Continuation;", "undispatched", "resumeUnconfined", "resumeWithStackTrace", "exception", "", "runUnconfinedEventLoop", "eventLoop", "Lkotlinx/coroutines/EventLoop;", "block", "Lkotlin/Function0;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DispatchedTaskKt {
    public static final int MODE_ATOMIC = 0;
    public static final int MODE_CANCELLABLE = 1;
    public static final int MODE_CANCELLABLE_REUSABLE = 2;
    public static final int MODE_UNDISPATCHED = 4;
    public static final int MODE_UNINITIALIZED = -1;

    public static final void dispatch(DispatchedTask dispatchedTask0, int v) {
        Continuation continuation0 = dispatchedTask0.getDelegate$kotlinx_coroutines_core();
        if(v != 4 && continuation0 instanceof DispatchedContinuation && DispatchedTaskKt.isCancellableMode(v) == DispatchedTaskKt.isCancellableMode(dispatchedTask0.resumeMode)) {
            CoroutineDispatcher coroutineDispatcher0 = ((DispatchedContinuation)continuation0).dispatcher;
            CoroutineContext coroutineContext0 = continuation0.getContext();
            if(coroutineDispatcher0.isDispatchNeeded(coroutineContext0)) {
                coroutineDispatcher0.dispatch(coroutineContext0, dispatchedTask0);
                return;
            }
            DispatchedTaskKt.resumeUnconfined(dispatchedTask0);
            return;
        }
        DispatchedTaskKt.resume(dispatchedTask0, continuation0, v == 4);
    }

    public static void getMODE_CANCELLABLE$annotations() {
    }

    public static final boolean isCancellableMode(int v) {
        return v == 1 || v == 2;
    }

    public static final boolean isReusableMode(int v) {
        return v == 2;
    }

    public static final void resume(DispatchedTask dispatchedTask0, Continuation continuation0, boolean z) {
        Object object0 = dispatchedTask0.takeState$kotlinx_coroutines_core();
        Throwable throwable0 = dispatchedTask0.getExceptionalResult$kotlinx_coroutines_core(object0);
        Object object1 = throwable0 == null ? dispatchedTask0.getSuccessfulResult$kotlinx_coroutines_core(object0) : ResultKt.createFailure(throwable0);
        if(z) {
            Intrinsics.checkNotNull(continuation0, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>");
            DispatchedContinuation dispatchedContinuation0 = (DispatchedContinuation)continuation0;
            CoroutineContext coroutineContext0 = dispatchedContinuation0.continuation.getContext();
            Object object2 = ThreadContextKt.updateThreadContext(coroutineContext0, dispatchedContinuation0.countOrElement);
            UndispatchedCoroutine undispatchedCoroutine0 = object2 == ThreadContextKt.NO_THREAD_ELEMENTS ? null : CoroutineContextKt.updateUndispatchedCompletion(dispatchedContinuation0.continuation, coroutineContext0, object2);
            try {
                dispatchedContinuation0.continuation.resumeWith(object1);
            }
            catch(Throwable throwable1) {
                if(undispatchedCoroutine0 == null || undispatchedCoroutine0.clearThreadContext()) {
                    ThreadContextKt.restoreThreadContext(coroutineContext0, object2);
                }
                throw throwable1;
            }
            if(undispatchedCoroutine0 != null && !undispatchedCoroutine0.clearThreadContext()) {
                return;
            }
            ThreadContextKt.restoreThreadContext(coroutineContext0, object2);
            return;
        }
        continuation0.resumeWith(object1);
    }

    private static final void resumeUnconfined(DispatchedTask dispatchedTask0) {
        EventLoop eventLoop0 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if(eventLoop0.isUnconfinedLoopActive()) {
            eventLoop0.dispatchUnconfined(dispatchedTask0);
            return;
        }
        eventLoop0.incrementUseCount(true);
        try {
            DispatchedTaskKt.resume(dispatchedTask0, dispatchedTask0.getDelegate$kotlinx_coroutines_core(), true);
            while(eventLoop0.processUnconfinedEvent()) {
            }
        }
        catch(Throwable throwable0) {
            dispatchedTask0.handleFatalException$kotlinx_coroutines_core(throwable0, null);
        }
        finally {
            eventLoop0.decrementUseCount(true);
        }
    }

    // 去混淆评级： 低(20)
    public static final void resumeWithStackTrace(Continuation continuation0, Throwable throwable0) {
        continuation0.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
    }

    public static final void runUnconfinedEventLoop(DispatchedTask dispatchedTask0, EventLoop eventLoop0, Function0 function00) {
        eventLoop0.incrementUseCount(true);
        try {
            function00.invoke();
            while(eventLoop0.processUnconfinedEvent()) {
            }
        }
        catch(Throwable throwable0) {
            dispatchedTask0.handleFatalException$kotlinx_coroutines_core(throwable0, null);
        }
        finally {
            eventLoop0.decrementUseCount(true);
        }
    }
}

