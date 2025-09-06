package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005B\u001B\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\f\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005\u00A2\u0006\u0002\u0010\tJ\r\u0010\u001F\u001A\u00020 H\u0000\u00A2\u0006\u0002\b!J\u001F\u0010\"\u001A\u00020 2\b\u0010#\u001A\u0004\u0018\u00010\f2\u0006\u0010$\u001A\u00020%H\u0010\u00A2\u0006\u0002\b&J\u0015\u0010\'\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001CH\u0000\u00A2\u0006\u0002\b(J\u001F\u0010)\u001A\u00020 2\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010*\u001A\u00028\u0000H\u0000\u00A2\u0006\u0004\b+\u0010,J\u0010\u0010-\u001A\n\u0018\u00010.j\u0004\u0018\u0001`/H\u0016J\r\u00100\u001A\u000201H\u0000\u00A2\u0006\u0002\b2J\u0015\u00103\u001A\u0002012\u0006\u0010$\u001A\u00020%H\u0000\u00A2\u0006\u0002\b4J\r\u00105\u001A\u00020 H\u0000\u00A2\u0006\u0002\b6JH\u00107\u001A\u00020 2\f\u00108\u001A\b\u0012\u0004\u0012\u00028\u0000092%\b\b\u0010:\u001A\u001F\u0012\u0013\u0012\u00110%\u00A2\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b($\u0012\u0004\u0012\u00020 \u0018\u00010;H\u0080\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b>\u0010?J\u0018\u0010@\u001A\u0002012\b\u0010A\u001A\u0004\u0018\u00010\fH\u0080\b\u00A2\u0006\u0002\bBJ!\u0010C\u001A\u00020 2\f\u00108\u001A\b\u0012\u0004\u0012\u00028\u000009H\u0080\b\u00F8\u0001\u0000\u00A2\u0006\u0004\bD\u0010EJ\u001E\u0010F\u001A\u00020 2\f\u00108\u001A\b\u0012\u0004\u0012\u00028\u000009H\u0016\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010EJ\u000F\u0010G\u001A\u0004\u0018\u00010\fH\u0010\u00A2\u0006\u0002\bHJ\b\u0010I\u001A\u00020JH\u0016J\u001B\u0010K\u001A\u0004\u0018\u00010%2\n\u0010\b\u001A\u0006\u0012\u0002\b\u00030LH\u0000\u00A2\u0006\u0002\bMR\u0011\u0010\n\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000BX\u0082\u0004R\u001A\u0010\r\u001A\u0004\u0018\u00010\f8\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\u000E\u0010\u000FR\u001C\u0010\u0010\u001A\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00048VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001A\u00020\u0014X\u0096\u0005\u00A2\u0006\u0006\u001A\u0004\b\u0015\u0010\u0016R\u0016\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\u00058\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001A\u00020\f8\u0000X\u0081\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u0018\u001A\b\u0012\u0004\u0012\u00028\u00000\u00058PX\u0090\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0019\u0010\u001AR\u0010\u0010\u0006\u001A\u00020\u00078\u0000X\u0081\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u001B\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u001C8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001D\u0010\u001E\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006N"}, d2 = {"Lkotlinx/coroutines/internal/DispatchedContinuation;", "T", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "Lkotlin/coroutines/Continuation;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "continuation", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/Continuation;)V", "_reusableCancellableContinuation", "Lkotlinx/atomicfu/AtomicRef;", "", "_state", "get_state$kotlinx_coroutines_core$annotations", "()V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "countOrElement", "delegate", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "reusableCancellableContinuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "getReusableCancellableContinuation", "()Lkotlinx/coroutines/CancellableContinuationImpl;", "awaitReusability", "", "awaitReusability$kotlinx_coroutines_core", "cancelCompletedResult", "takenState", "cause", "", "cancelCompletedResult$kotlinx_coroutines_core", "claimReusableCancellableContinuation", "claimReusableCancellableContinuation$kotlinx_coroutines_core", "dispatchYield", "value", "dispatchYield$kotlinx_coroutines_core", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "isReusable", "", "isReusable$kotlinx_coroutines_core", "postponeCancellation", "postponeCancellation$kotlinx_coroutines_core", "release", "release$kotlinx_coroutines_core", "resumeCancellableWith", "result", "Lkotlin/Result;", "onCancellation", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "resumeCancellableWith$kotlinx_coroutines_core", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "resumeCancelled", "state", "resumeCancelled$kotlinx_coroutines_core", "resumeUndispatchedWith", "resumeUndispatchedWith$kotlinx_coroutines_core", "(Ljava/lang/Object;)V", "resumeWith", "takeState", "takeState$kotlinx_coroutines_core", "toString", "", "tryReleaseClaimedContinuation", "Lkotlinx/coroutines/CancellableContinuation;", "tryReleaseClaimedContinuation$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DispatchedContinuation extends DispatchedTask implements Continuation, CoroutineStackFrame {
    @Volatile
    private volatile Object _reusableCancellableContinuation;
    private static final AtomicReferenceFieldUpdater _reusableCancellableContinuation$FU;
    public Object _state;
    public final Continuation continuation;
    public final Object countOrElement;
    public final CoroutineDispatcher dispatcher;

    static {
        DispatchedContinuation._reusableCancellableContinuation$FU = AtomicReferenceFieldUpdater.newUpdater(DispatchedContinuation.class, Object.class, "_reusableCancellableContinuation");
    }

    public DispatchedContinuation(CoroutineDispatcher coroutineDispatcher0, Continuation continuation0) {
        super(-1);
        this.dispatcher = coroutineDispatcher0;
        this.continuation = continuation0;
        this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
        this.countOrElement = ThreadContextKt.threadContextElements(this.getContext());
    }

    public final void awaitReusability$kotlinx_coroutines_core() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = DispatchedContinuation._reusableCancellableContinuation$FU;
        while(atomicReferenceFieldUpdater0.get(this) == DispatchedContinuationKt.REUSABLE_CLAIMED) {
        }
    }

    @Override  // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$kotlinx_coroutines_core(Object object0, Throwable throwable0) {
        if(object0 instanceof CompletedWithCancellation) {
            ((CompletedWithCancellation)object0).onCancellation.invoke(throwable0);
        }
    }

    public final CancellableContinuationImpl claimReusableCancellableContinuation$kotlinx_coroutines_core() {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = DispatchedContinuation._reusableCancellableContinuation$FU;
        do {
            while(true) {
                object0 = atomicReferenceFieldUpdater0.get(this);
                if(object0 == null) {
                    DispatchedContinuation._reusableCancellableContinuation$FU.set(this, DispatchedContinuationKt.REUSABLE_CLAIMED);
                    return null;
                }
                if(!(object0 instanceof CancellableContinuationImpl)) {
                    break;
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(DispatchedContinuation._reusableCancellableContinuation$FU, this, object0, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                    return (CancellableContinuationImpl)object0;
                }
            }
        }
        while(object0 == DispatchedContinuationKt.REUSABLE_CLAIMED || object0 instanceof Throwable);
        throw new IllegalStateException(("Inconsistent state " + object0).toString());
    }

    public final void dispatchYield$kotlinx_coroutines_core(CoroutineContext coroutineContext0, Object object0) {
        this._state = object0;
        this.resumeMode = 1;
        this.dispatcher.dispatchYield(coroutineContext0, this);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        return this.continuation instanceof CoroutineStackFrame ? ((CoroutineStackFrame)this.continuation) : null;
    }

    @Override  // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    @Override  // kotlinx.coroutines.DispatchedTask
    public Continuation getDelegate$kotlinx_coroutines_core() {
        return this;
    }

    private final CancellableContinuationImpl getReusableCancellableContinuation() {
        Object object0 = DispatchedContinuation._reusableCancellableContinuation$FU.get(this);
        return object0 instanceof CancellableContinuationImpl ? ((CancellableContinuationImpl)object0) : null;
    }

    @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    public static void get_state$kotlinx_coroutines_core$annotations() {
    }

    public final boolean isReusable$kotlinx_coroutines_core() {
        return DispatchedContinuation._reusableCancellableContinuation$FU.get(this) != null;
    }

    private final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicReferenceFieldUpdater0.get(object0));
        }
    }

    public final boolean postponeCancellation$kotlinx_coroutines_core(Throwable throwable0) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = DispatchedContinuation._reusableCancellableContinuation$FU;
        do {
            while(true) {
                Object object0 = atomicReferenceFieldUpdater0.get(this);
                if(!Intrinsics.areEqual(object0, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                    break;
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(DispatchedContinuation._reusableCancellableContinuation$FU, this, DispatchedContinuationKt.REUSABLE_CLAIMED, throwable0)) {
                    return true;
                }
            }
            if(object0 instanceof Throwable) {
                return true;
            }
        }
        while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(DispatchedContinuation._reusableCancellableContinuation$FU, this, object0, null));
        return false;
    }

    public final void release$kotlinx_coroutines_core() {
        this.awaitReusability$kotlinx_coroutines_core();
        CancellableContinuationImpl cancellableContinuationImpl0 = this.getReusableCancellableContinuation();
        if(cancellableContinuationImpl0 != null) {
            cancellableContinuationImpl0.detachChild$kotlinx_coroutines_core();
        }
    }

    public final void resumeCancellableWith$kotlinx_coroutines_core(Object object0, Function1 function10) {
        Object object1 = CompletionStateKt.toState(object0, function10);
        CoroutineContext coroutineContext0 = this.getContext();
        if(this.dispatcher.isDispatchNeeded(coroutineContext0)) {
            this._state = object1;
            this.resumeMode = 1;
            CoroutineContext coroutineContext1 = this.getContext();
            this.dispatcher.dispatch(coroutineContext1, this);
            return;
        }
        EventLoop eventLoop0 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if(eventLoop0.isUnconfinedLoopActive()) {
            this._state = object1;
            this.resumeMode = 1;
            eventLoop0.dispatchUnconfined(this);
            return;
        }
        eventLoop0.incrementUseCount(true);
        try {
            Job job0 = (Job)this.getContext().get(Job.Key);
            if(job0 == null || job0.isActive()) {
                CoroutineContext coroutineContext2 = this.continuation.getContext();
                Object object2 = ThreadContextKt.updateThreadContext(coroutineContext2, this.countOrElement);
                UndispatchedCoroutine undispatchedCoroutine0 = object2 == ThreadContextKt.NO_THREAD_ELEMENTS ? null : CoroutineContextKt.updateUndispatchedCompletion(this.continuation, coroutineContext2, object2);
                try {
                    this.continuation.resumeWith(object0);
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
                this.cancelCompletedResult$kotlinx_coroutines_core(object1, cancellationException0);
                this.resumeWith(Result.constructor-impl(ResultKt.createFailure(cancellationException0)));
            }
            while(eventLoop0.processUnconfinedEvent()) {
            }
        }
        catch(Throwable throwable0) {
            this.handleFatalException$kotlinx_coroutines_core(throwable0, null);
        }
        finally {
            eventLoop0.decrementUseCount(true);
        }
    }

    public final boolean resumeCancelled$kotlinx_coroutines_core(Object object0) {
        Job job0 = (Job)this.getContext().get(Job.Key);
        if(job0 != null && !job0.isActive()) {
            Throwable throwable0 = job0.getCancellationException();
            this.cancelCompletedResult$kotlinx_coroutines_core(object0, throwable0);
            this.resumeWith(Result.constructor-impl(ResultKt.createFailure(throwable0)));
            return true;
        }
        return false;
    }

    public final void resumeUndispatchedWith$kotlinx_coroutines_core(Object object0) {
        CoroutineContext coroutineContext0 = this.continuation.getContext();
        Object object1 = ThreadContextKt.updateThreadContext(coroutineContext0, this.countOrElement);
        UndispatchedCoroutine undispatchedCoroutine0 = object1 == ThreadContextKt.NO_THREAD_ELEMENTS ? null : CoroutineContextKt.updateUndispatchedCompletion(this.continuation, coroutineContext0, object1);
        try {
            this.continuation.resumeWith(object0);
        }
        finally {
            if(undispatchedCoroutine0 == null || undispatchedCoroutine0.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(coroutineContext0, object1);
            }
        }
    }

    @Override  // kotlin.coroutines.Continuation
    public void resumeWith(Object object0) {
        CoroutineContext coroutineContext0 = this.continuation.getContext();
        Object object1 = CompletionStateKt.toState$default(object0, null, 1, null);
        if(this.dispatcher.isDispatchNeeded(coroutineContext0)) {
            this._state = object1;
            this.resumeMode = 0;
            this.dispatcher.dispatch(coroutineContext0, this);
            return;
        }
        EventLoop eventLoop0 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if(eventLoop0.isUnconfinedLoopActive()) {
            this._state = object1;
            this.resumeMode = 0;
            eventLoop0.dispatchUnconfined(this);
            return;
        }
        eventLoop0.incrementUseCount(true);
        try {
            CoroutineContext coroutineContext1 = this.getContext();
            Object object2 = ThreadContextKt.updateThreadContext(coroutineContext1, this.countOrElement);
            try {
                this.continuation.resumeWith(object0);
            }
            finally {
                ThreadContextKt.restoreThreadContext(coroutineContext1, object2);
            }
            while(eventLoop0.processUnconfinedEvent()) {
            }
        }
        catch(Throwable throwable0) {
            this.handleFatalException$kotlinx_coroutines_core(throwable0, null);
        }
        finally {
            eventLoop0.decrementUseCount(true);
        }
    }

    @Override  // kotlinx.coroutines.DispatchedTask
    public Object takeState$kotlinx_coroutines_core() {
        Object object0 = this._state;
        this._state = DispatchedContinuationKt.UNDEFINED;
        return object0;
    }

    @Override
    public String toString() {
        return "DispatchedContinuation[" + this.dispatcher + ", " + DebugStringsKt.toDebugString(this.continuation) + ']';
    }

    public final Throwable tryReleaseClaimedContinuation$kotlinx_coroutines_core(CancellableContinuation cancellableContinuation0) {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = DispatchedContinuation._reusableCancellableContinuation$FU;
        while(true) {
            object0 = atomicReferenceFieldUpdater0.get(this);
            if(object0 != DispatchedContinuationKt.REUSABLE_CLAIMED) {
                break;
            }
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(DispatchedContinuation._reusableCancellableContinuation$FU, this, DispatchedContinuationKt.REUSABLE_CLAIMED, cancellableContinuation0)) {
                return null;
            }
        }
        if(!(object0 instanceof Throwable)) {
            throw new IllegalStateException(("Inconsistent state " + object0).toString());
        }
        if(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(DispatchedContinuation._reusableCancellableContinuation$FU, this, object0, null)) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        return (Throwable)object0;
    }
}

