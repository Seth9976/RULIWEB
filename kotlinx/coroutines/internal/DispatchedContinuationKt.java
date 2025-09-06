package kotlinx.coroutines.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001A;\u0010\u0003\u001A\u00020\u0004*\u0006\u0012\u0002\b\u00030\u00052\b\u0010\u0006\u001A\u0004\u0018\u00010\u00072\u0006\u0010\b\u001A\u00020\t2\b\b\u0002\u0010\n\u001A\u00020\u00042\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\r0\fH\u0082\b\u001AU\u0010\u000E\u001A\u00020\r\"\u0004\b\u0000\u0010\u000F*\b\u0012\u0004\u0012\u0002H\u000F0\u00102\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u00122%\b\u0002\u0010\u0013\u001A\u001F\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\r\u0018\u00010\u0014H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001A\u0012\u0010\u001A\u001A\u00020\u0004*\b\u0012\u0004\u0012\u00020\r0\u0005H\u0000\"\u0010\u0010\u0000\u001A\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001B"}, d2 = {"REUSABLE_CLAIMED", "Lkotlinx/coroutines/internal/Symbol;", "UNDEFINED", "executeUnconfined", "", "Lkotlinx/coroutines/internal/DispatchedContinuation;", "contState", "", "mode", "", "doYield", "block", "Lkotlin/Function0;", "", "resumeCancellableWith", "T", "Lkotlin/coroutines/Continuation;", "result", "Lkotlin/Result;", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "yieldUndispatched", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DispatchedContinuationKt {
    public static final Symbol REUSABLE_CLAIMED;
    private static final Symbol UNDEFINED;

    static {
        DispatchedContinuationKt.UNDEFINED = new Symbol("UNDEFINED");
        DispatchedContinuationKt.REUSABLE_CLAIMED = new Symbol("REUSABLE_CLAIMED");
    }

    private static final boolean executeUnconfined(DispatchedContinuation dispatchedContinuation0, Object object0, int v, boolean z, Function0 function00) {
        EventLoop eventLoop0 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if(z && eventLoop0.isUnconfinedQueueEmpty()) {
            return false;
        }
        if(eventLoop0.isUnconfinedLoopActive()) {
            dispatchedContinuation0._state = object0;
            dispatchedContinuation0.resumeMode = v;
            eventLoop0.dispatchUnconfined(dispatchedContinuation0);
            return true;
        }
        eventLoop0.incrementUseCount(true);
        try {
            function00.invoke();
            while(eventLoop0.processUnconfinedEvent()) {
            }
        }
        catch(Throwable throwable0) {
            dispatchedContinuation0.handleFatalException$kotlinx_coroutines_core(throwable0, null);
        }
        finally {
            eventLoop0.decrementUseCount(true);
        }
        return false;
    }

    static boolean executeUnconfined$default(DispatchedContinuation dispatchedContinuation0, Object object0, int v, boolean z, Function0 function00, int v1, Object object1) {
        if((v1 & 4) != 0) {
            z = false;
        }
        EventLoop eventLoop0 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if(z && eventLoop0.isUnconfinedQueueEmpty()) {
            return false;
        }
        if(eventLoop0.isUnconfinedLoopActive()) {
            dispatchedContinuation0._state = object0;
            dispatchedContinuation0.resumeMode = v;
            eventLoop0.dispatchUnconfined(dispatchedContinuation0);
            return true;
        }
        eventLoop0.incrementUseCount(true);
        try {
            function00.invoke();
            while(eventLoop0.processUnconfinedEvent()) {
            }
        }
        catch(Throwable throwable0) {
            dispatchedContinuation0.handleFatalException$kotlinx_coroutines_core(throwable0, null);
        }
        finally {
            eventLoop0.decrementUseCount(true);
        }
        return false;
    }

    public static final void resumeCancellableWith(Continuation continuation0, Object object0, Function1 function10) {
        if(continuation0 instanceof DispatchedContinuation) {
            DispatchedContinuation dispatchedContinuation0 = (DispatchedContinuation)continuation0;
            Object object1 = CompletionStateKt.toState(object0, function10);
            CoroutineContext coroutineContext0 = dispatchedContinuation0.getContext();
            if(dispatchedContinuation0.dispatcher.isDispatchNeeded(coroutineContext0)) {
                dispatchedContinuation0._state = object1;
                dispatchedContinuation0.resumeMode = 1;
                CoroutineContext coroutineContext1 = dispatchedContinuation0.getContext();
                dispatchedContinuation0.dispatcher.dispatch(coroutineContext1, dispatchedContinuation0);
                return;
            }
            EventLoop eventLoop0 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
            if(eventLoop0.isUnconfinedLoopActive()) {
                dispatchedContinuation0._state = object1;
                dispatchedContinuation0.resumeMode = 1;
                eventLoop0.dispatchUnconfined(dispatchedContinuation0);
                return;
            }
            eventLoop0.incrementUseCount(true);
            try {
                Job job0 = (Job)dispatchedContinuation0.getContext().get(Job.Key);
                if(job0 == null || job0.isActive()) {
                    CoroutineContext coroutineContext2 = dispatchedContinuation0.continuation.getContext();
                    Object object2 = ThreadContextKt.updateThreadContext(coroutineContext2, dispatchedContinuation0.countOrElement);
                    UndispatchedCoroutine undispatchedCoroutine0 = object2 == ThreadContextKt.NO_THREAD_ELEMENTS ? null : CoroutineContextKt.updateUndispatchedCompletion(dispatchedContinuation0.continuation, coroutineContext2, object2);
                    try {
                        dispatchedContinuation0.continuation.resumeWith(object0);
                    }
                    catch(Throwable throwable1) {
                        if(undispatchedCoroutine0 == null || undispatchedCoroutine0.clearThreadContext()) {
                            ThreadContextKt.restoreThreadContext(coroutineContext2, object2);
                        }
                        throw throwable1;
                    }
                    if(undispatchedCoroutine0 == null) {
                        ThreadContextKt.restoreThreadContext(coroutineContext2, object2);
                    }
                    else if(undispatchedCoroutine0.clearThreadContext()) {
                        ThreadContextKt.restoreThreadContext(coroutineContext2, object2);
                    }
                }
                else {
                    CancellationException cancellationException0 = job0.getCancellationException();
                    dispatchedContinuation0.cancelCompletedResult$kotlinx_coroutines_core(object1, cancellationException0);
                    dispatchedContinuation0.resumeWith(Result.constructor-impl(ResultKt.createFailure(cancellationException0)));
                }
                while(eventLoop0.processUnconfinedEvent()) {
                }
            }
            catch(Throwable throwable0) {
                dispatchedContinuation0.handleFatalException$kotlinx_coroutines_core(throwable0, null);
            }
            finally {
                eventLoop0.decrementUseCount(true);
            }
            return;
        }
        continuation0.resumeWith(object0);
    }

    public static void resumeCancellableWith$default(Continuation continuation0, Object object0, Function1 function10, int v, Object object1) {
        if((v & 2) != 0) {
            function10 = null;
        }
        DispatchedContinuationKt.resumeCancellableWith(continuation0, object0, function10);
    }

    public static final boolean yieldUndispatched(DispatchedContinuation dispatchedContinuation0) {
        Unit unit0 = Unit.INSTANCE;
        EventLoop eventLoop0 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if(eventLoop0.isUnconfinedQueueEmpty()) {
            return false;
        }
        if(eventLoop0.isUnconfinedLoopActive()) {
            dispatchedContinuation0._state = unit0;
            dispatchedContinuation0.resumeMode = 1;
            eventLoop0.dispatchUnconfined(dispatchedContinuation0);
            return true;
        }
        eventLoop0.incrementUseCount(true);
        try {
            dispatchedContinuation0.run();
            while(eventLoop0.processUnconfinedEvent()) {
            }
        }
        catch(Throwable throwable0) {
            dispatchedContinuation0.handleFatalException$kotlinx_coroutines_core(throwable0, null);
        }
        finally {
            eventLoop0.decrementUseCount(true);
        }
        return false;
    }
}

