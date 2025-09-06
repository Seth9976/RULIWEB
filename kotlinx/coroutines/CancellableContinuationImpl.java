package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000\u00C8\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\n\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000E\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0011\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00060\u0004j\u0002`\u00052\u00020\u0006B\u001B\u0012\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0010\t\u001A\u00020\n\u00A2\u0006\u0002\u0010\u000BJ\u0012\u0010+\u001A\u00020,2\b\u0010-\u001A\u0004\u0018\u00010\u0012H\u0002J\u0018\u0010.\u001A\u00020/2\u0006\u00100\u001A\u0002012\b\u00102\u001A\u0004\u0018\u000103J;\u0010.\u001A\u00020/2\'\u00100\u001A#\u0012\u0015\u0012\u0013\u0018\u000103\u00A2\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020/04j\u0002`72\b\u00102\u001A\u0004\u0018\u000103H\u0002J\u0017\u00108\u001A\u00020/2\f\u00109\u001A\b\u0012\u0004\u0012\u00020/0:H\u0082\bJ1\u0010;\u001A\u00020/2!\u0010<\u001A\u001D\u0012\u0013\u0012\u001103\u00A2\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020/042\u0006\u00102\u001A\u000203J\u001E\u0010=\u001A\u00020/2\n\u0010>\u001A\u0006\u0012\u0002\b\u00030?2\b\u00102\u001A\u0004\u0018\u000103H\u0002J\u0012\u0010@\u001A\u00020\u001D2\b\u00102\u001A\u0004\u0018\u000103H\u0016J\u001F\u0010A\u001A\u00020/2\b\u0010B\u001A\u0004\u0018\u00010\u00122\u0006\u00102\u001A\u000203H\u0010\u00A2\u0006\u0002\bCJ\u0010\u0010D\u001A\u00020\u001D2\u0006\u00102\u001A\u000203H\u0002J\u0010\u0010E\u001A\u00020/2\u0006\u0010F\u001A\u00020\u0012H\u0016J\r\u0010G\u001A\u00020/H\u0000\u00A2\u0006\u0002\bHJ\b\u0010I\u001A\u00020/H\u0002J\u0010\u0010J\u001A\u00020/2\u0006\u0010K\u001A\u00020\nH\u0002J\u0010\u0010L\u001A\u0002032\u0006\u0010M\u001A\u00020NH\u0016J\u0019\u0010O\u001A\u0004\u0018\u0001032\b\u0010$\u001A\u0004\u0018\u00010\u0012H\u0010\u00A2\u0006\u0002\bPJ\n\u0010Q\u001A\u0004\u0018\u00010\u0012H\u0001J\u0010\u0010R\u001A\n\u0018\u00010Sj\u0004\u0018\u0001`TH\u0016J\u001F\u0010U\u001A\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010$\u001A\u0004\u0018\u00010\u0012H\u0010\u00A2\u0006\u0004\bV\u0010WJ\b\u0010X\u001A\u00020/H\u0016J\n\u0010Y\u001A\u0004\u0018\u00010\u0010H\u0002J1\u0010Z\u001A\u00020/2\'\u00100\u001A#\u0012\u0015\u0012\u0013\u0018\u000103\u00A2\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020/04j\u0002`7H\u0016J\u001C\u0010Z\u001A\u00020/2\n\u0010>\u001A\u0006\u0012\u0002\b\u00030?2\u0006\u0010[\u001A\u00020\nH\u0016J\u0010\u0010\\\u001A\u00020/2\u0006\u00100\u001A\u00020\u0012H\u0002J\b\u0010]\u001A\u00020\u001DH\u0002J1\u0010^\u001A\u0002012\'\u00100\u001A#\u0012\u0015\u0012\u0013\u0018\u000103\u00A2\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020/04j\u0002`7H\u0002J\u001A\u0010_\u001A\u00020/2\u0006\u00100\u001A\u00020\u00122\b\u0010$\u001A\u0004\u0018\u00010\u0012H\u0002J\b\u0010`\u001A\u00020(H\u0014J\u0015\u0010a\u001A\u00020/2\u0006\u00102\u001A\u000203H\u0000\u00A2\u0006\u0002\bbJ\r\u0010c\u001A\u00020/H\u0000\u00A2\u0006\u0002\bdJ\b\u0010e\u001A\u00020\u001DH\u0001J:\u0010f\u001A\u00020/2\u0006\u0010g\u001A\u00028\u00002#\u0010<\u001A\u001F\u0012\u0013\u0012\u001103\u00A2\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020/\u0018\u000104H\u0016\u00A2\u0006\u0002\u0010hJA\u0010i\u001A\u00020/2\b\u0010-\u001A\u0004\u0018\u00010\u00122\u0006\u0010\t\u001A\u00020\n2%\b\u0002\u0010<\u001A\u001F\u0012\u0013\u0012\u001103\u00A2\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020/\u0018\u000104H\u0002J\u001E\u0010j\u001A\u00020/2\f\u0010k\u001A\b\u0012\u0004\u0012\u00028\u00000lH\u0016\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010mJS\u0010n\u001A\u0004\u0018\u00010\u00122\u0006\u0010$\u001A\u00020o2\b\u0010-\u001A\u0004\u0018\u00010\u00122\u0006\u0010\t\u001A\u00020\n2#\u0010<\u001A\u001F\u0012\u0013\u0012\u001103\u00A2\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020/\u0018\u0001042\b\u0010p\u001A\u0004\u0018\u00010\u0012H\u0002J\u000F\u0010q\u001A\u0004\u0018\u00010\u0012H\u0010\u00A2\u0006\u0002\brJ\b\u0010s\u001A\u00020(H\u0016J\b\u0010t\u001A\u00020\u001DH\u0002J!\u0010t\u001A\u0004\u0018\u00010\u00122\u0006\u0010g\u001A\u00028\u00002\b\u0010p\u001A\u0004\u0018\u00010\u0012H\u0016\u00A2\u0006\u0002\u0010uJF\u0010t\u001A\u0004\u0018\u00010\u00122\u0006\u0010g\u001A\u00028\u00002\b\u0010p\u001A\u0004\u0018\u00010\u00122#\u0010<\u001A\u001F\u0012\u0013\u0012\u001103\u00A2\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020/\u0018\u000104H\u0016\u00A2\u0006\u0002\u0010vJC\u0010w\u001A\u0004\u0018\u00010x2\b\u0010-\u001A\u0004\u0018\u00010\u00122\b\u0010p\u001A\u0004\u0018\u00010\u00122#\u0010<\u001A\u001F\u0012\u0013\u0012\u001103\u00A2\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020/\u0018\u000104H\u0002J\u0012\u0010y\u001A\u0004\u0018\u00010\u00122\u0006\u0010z\u001A\u000203H\u0016J\b\u0010{\u001A\u00020\u001DH\u0002J\u0019\u0010|\u001A\u00020/*\u00020}2\u0006\u0010g\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010~J\u0014\u0010\u007F\u001A\u00020/*\u00020}2\u0006\u0010z\u001A\u000203H\u0016R\t\u0010\f\u001A\u00020\rX\u0082\u0004R\u0011\u0010\u000E\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000FX\u0082\u0004R\u0011\u0010\u0011\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000FX\u0082\u0004R\u001C\u0010\u0013\u001A\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001A\u00020\u0017X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0018\u0010\u0019R\u001A\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\bX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001A\u0010\u001BR\u0014\u0010\u001C\u001A\u00020\u001D8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001C\u0010\u001ER\u0014\u0010\u001F\u001A\u00020\u001D8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001F\u0010\u001ER\u0014\u0010 \u001A\u00020\u001D8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b \u0010\u001ER\u0016\u0010!\u001A\u0004\u0018\u00010\u00108BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\"\u0010#R\u0016\u0010$\u001A\u0004\u0018\u00010\u00128@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b%\u0010&R\u0014\u0010\'\u001A\u00020(8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b)\u0010*\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\u0080\u0001"}, d2 = {"Lkotlinx/coroutines/CancellableContinuationImpl;", "T", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlinx/coroutines/CancellableContinuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/Waiter;", "delegate", "Lkotlin/coroutines/Continuation;", "resumeMode", "", "(Lkotlin/coroutines/Continuation;I)V", "_decisionAndIndex", "Lkotlinx/atomicfu/AtomicInt;", "_parentHandle", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/DisposableHandle;", "_state", "", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "isActive", "", "()Z", "isCancelled", "isCompleted", "parentHandle", "getParentHandle", "()Lkotlinx/coroutines/DisposableHandle;", "state", "getState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "stateDebugRepresentation", "", "getStateDebugRepresentation", "()Ljava/lang/String;", "alreadyResumedError", "", "proposedUpdate", "callCancelHandler", "", "handler", "Lkotlinx/coroutines/CancelHandler;", "cause", "", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "callCancelHandlerSafely", "block", "Lkotlin/Function0;", "callOnCancellation", "onCancellation", "callSegmentOnCancellation", "segment", "Lkotlinx/coroutines/internal/Segment;", "cancel", "cancelCompletedResult", "takenState", "cancelCompletedResult$kotlinx_coroutines_core", "cancelLater", "completeResume", "token", "detachChild", "detachChild$kotlinx_coroutines_core", "detachChildIfNonResuable", "dispatchResume", "mode", "getContinuationCancellationCause", "parent", "Lkotlinx/coroutines/Job;", "getExceptionalResult", "getExceptionalResult$kotlinx_coroutines_core", "getResult", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getSuccessfulResult", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "initCancellability", "installParentHandle", "invokeOnCancellation", "index", "invokeOnCancellationImpl", "isReusable", "makeCancelHandler", "multipleHandlersError", "nameString", "parentCancelled", "parentCancelled$kotlinx_coroutines_core", "releaseClaimedReusableContinuation", "releaseClaimedReusableContinuation$kotlinx_coroutines_core", "resetStateReusable", "resume", "value", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "resumeImpl", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "resumedState", "Lkotlinx/coroutines/NotCompleted;", "idempotent", "takeState", "takeState$kotlinx_coroutines_core", "toString", "tryResume", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "tryResumeImpl", "Lkotlinx/coroutines/internal/Symbol;", "tryResumeWithException", "exception", "trySuspend", "resumeUndispatched", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class CancellableContinuationImpl extends DispatchedTask implements CoroutineStackFrame, CancellableContinuation, Waiter {
    @Volatile
    private volatile int _decisionAndIndex;
    private static final AtomicIntegerFieldUpdater _decisionAndIndex$FU;
    @Volatile
    private volatile Object _parentHandle;
    private static final AtomicReferenceFieldUpdater _parentHandle$FU;
    @Volatile
    private volatile Object _state;
    private static final AtomicReferenceFieldUpdater _state$FU;
    private final CoroutineContext context;
    private final Continuation delegate;

    static {
        CancellableContinuationImpl._decisionAndIndex$FU = AtomicIntegerFieldUpdater.newUpdater(CancellableContinuationImpl.class, "_decisionAndIndex");
        CancellableContinuationImpl._state$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_state");
        CancellableContinuationImpl._parentHandle$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_parentHandle");
    }

    public CancellableContinuationImpl(Continuation continuation0, int v) {
        super(v);
        this.delegate = continuation0;
        this.context = continuation0.getContext();
        this._decisionAndIndex = 0x1FFFFFFF;
        this._state = Active.INSTANCE;
    }

    private final Void alreadyResumedError(Object object0) {
        throw new IllegalStateException(("Already resumed, but proposed with update " + object0).toString());
    }

    private final void callCancelHandler(Function1 function10, Throwable throwable0) {
        try {
            function10.invoke(throwable0);
        }
        catch(Throwable throwable1) {
            CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, throwable1));
        }
    }

    public final void callCancelHandler(CancelHandler cancelHandler0, Throwable throwable0) {
        try {
            cancelHandler0.invoke(throwable0);
        }
        catch(Throwable throwable1) {
            CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, throwable1));
        }
    }

    private final void callCancelHandlerSafely(Function0 function00) {
        try {
            function00.invoke();
        }
        catch(Throwable throwable0) {
            CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, throwable0));
        }
    }

    public final void callOnCancellation(Function1 function10, Throwable throwable0) {
        try {
            function10.invoke(throwable0);
        }
        catch(Throwable throwable1) {
            CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), new CompletionHandlerException("Exception in resume onCancellation handler for " + this, throwable1));
        }
    }

    private final void callSegmentOnCancellation(Segment segment0, Throwable throwable0) {
        int v = CancellableContinuationImpl._decisionAndIndex$FU.get(this);
        if((v & 0x1FFFFFFF) == 0x1FFFFFFF) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
        }
        try {
            segment0.onCancellation(v & 0x1FFFFFFF, throwable0, this.getContext());
        }
        catch(Throwable throwable1) {
            CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, throwable1));
        }
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public boolean cancel(Throwable throwable0) {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = CancellableContinuationImpl._state$FU;
        do {
            object0 = atomicReferenceFieldUpdater0.get(this);
            boolean z = false;
            if(!(object0 instanceof NotCompleted)) {
                return false;
            }
            if(object0 instanceof CancelHandler || object0 instanceof Segment) {
                z = true;
            }
            CancelledContinuation cancelledContinuation0 = new CancelledContinuation(this, throwable0, z);
        }
        while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(CancellableContinuationImpl._state$FU, this, object0, cancelledContinuation0));
        if(((NotCompleted)object0) instanceof CancelHandler) {
            this.callCancelHandler(((CancelHandler)object0), throwable0);
        }
        else if(((NotCompleted)object0) instanceof Segment) {
            this.callSegmentOnCancellation(((Segment)object0), throwable0);
        }
        this.detachChildIfNonResuable();
        this.dispatchResume(this.resumeMode);
        return true;
    }

    @Override  // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$kotlinx_coroutines_core(Object object0, Throwable throwable0) {
        Object object1;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = CancellableContinuationImpl._state$FU;
    alab1:
        while(true) {
            do {
                object1 = atomicReferenceFieldUpdater0.get(this);
                if(object1 instanceof NotCompleted) {
                    break alab1;
                }
                if(object1 instanceof CompletedExceptionally) {
                    return;
                }
                if(!(object1 instanceof CompletedContinuation)) {
                    goto label_11;
                }
                if(((CompletedContinuation)object1).getCancelled()) {
                    throw new IllegalStateException("Must be called at most once");
                }
                CompletedContinuation completedContinuation0 = CompletedContinuation.copy$default(((CompletedContinuation)object1), null, null, null, null, throwable0, 15, null);
            }
            while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(CancellableContinuationImpl._state$FU, this, object1, completedContinuation0));
            ((CompletedContinuation)object1).invokeHandlers(this, throwable0);
            return;
        label_11:
            CompletedContinuation completedContinuation1 = new CompletedContinuation(object1, null, null, null, throwable0, 14, null);
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(CancellableContinuationImpl._state$FU, this, object1, completedContinuation1)) {
                return;
            }
        }
        throw new IllegalStateException("Not completed");
    }

    private final boolean cancelLater(Throwable throwable0) {
        if(!this.isReusable()) {
            return false;
        }
        Intrinsics.checkNotNull(this.delegate, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        return ((DispatchedContinuation)this.delegate).postponeCancellation$kotlinx_coroutines_core(throwable0);
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public void completeResume(Object object0) {
        this.dispatchResume(this.resumeMode);
    }

    public final void detachChild$kotlinx_coroutines_core() {
        DisposableHandle disposableHandle0 = this.getParentHandle();
        if(disposableHandle0 == null) {
            return;
        }
        disposableHandle0.dispose();
        CancellableContinuationImpl._parentHandle$FU.set(this, NonDisposableHandle.INSTANCE);
    }

    private final void detachChildIfNonResuable() {
        if(!this.isReusable()) {
            this.detachChild$kotlinx_coroutines_core();
        }
    }

    private final void dispatchResume(int v) {
        if(this.tryResume()) {
            return;
        }
        DispatchedTaskKt.dispatch(this, v);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        return this.delegate instanceof CoroutineStackFrame ? ((CoroutineStackFrame)this.delegate) : null;
    }

    @Override  // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.context;
    }

    public Throwable getContinuationCancellationCause(Job job0) {
        return job0.getCancellationException();
    }

    @Override  // kotlinx.coroutines.DispatchedTask
    public final Continuation getDelegate$kotlinx_coroutines_core() {
        return this.delegate;
    }

    // 去混淆评级： 低(20)
    @Override  // kotlinx.coroutines.DispatchedTask
    public Throwable getExceptionalResult$kotlinx_coroutines_core(Object object0) {
        Throwable throwable0 = super.getExceptionalResult$kotlinx_coroutines_core(object0);
        return throwable0 == null ? null : throwable0;
    }

    private final DisposableHandle getParentHandle() {
        return (DisposableHandle)CancellableContinuationImpl._parentHandle$FU.get(this);
    }

    public final Object getResult() {
        boolean z = this.isReusable();
        if(this.trySuspend()) {
            if(this.getParentHandle() == null) {
                this.installParentHandle();
            }
            if(z) {
                this.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            }
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if(z) {
            this.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
        }
        Object object0 = this.getState$kotlinx_coroutines_core();
        if(object0 instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally)object0).cause;
        }
        if(DispatchedTaskKt.isCancellableMode(this.resumeMode)) {
            Job job0 = (Job)this.getContext().get(Job.Key);
            if(job0 != null && !job0.isActive()) {
                Throwable throwable0 = job0.getCancellationException();
                this.cancelCompletedResult$kotlinx_coroutines_core(object0, throwable0);
                throw throwable0;
            }
        }
        return this.getSuccessfulResult$kotlinx_coroutines_core(object0);
    }

    @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    public final Object getState$kotlinx_coroutines_core() {
        return CancellableContinuationImpl._state$FU.get(this);
    }

    private final String getStateDebugRepresentation() {
        Object object0 = this.getState$kotlinx_coroutines_core();
        if(object0 instanceof NotCompleted) {
            return "Active";
        }
        return object0 instanceof CancelledContinuation ? "Cancelled" : "Completed";
    }

    // 去混淆评级： 低(20)
    @Override  // kotlinx.coroutines.DispatchedTask
    public Object getSuccessfulResult$kotlinx_coroutines_core(Object object0) {
        return object0 instanceof CompletedContinuation ? ((CompletedContinuation)object0).result : object0;
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public void initCancellability() {
        DisposableHandle disposableHandle0 = this.installParentHandle();
        if(disposableHandle0 != null && this.isCompleted()) {
            disposableHandle0.dispose();
            CancellableContinuationImpl._parentHandle$FU.set(this, NonDisposableHandle.INSTANCE);
        }
    }

    private final DisposableHandle installParentHandle() {
        Element coroutineContext$Element0 = this.getContext().get(Job.Key);
        if(((Job)coroutineContext$Element0) == null) {
            return null;
        }
        DisposableHandle disposableHandle0 = DefaultImpls.invokeOnCompletion$default(((Job)coroutineContext$Element0), true, false, new ChildContinuation(this), 2, null);
        AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(CancellableContinuationImpl._parentHandle$FU, this, null, disposableHandle0);
        return disposableHandle0;
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public void invokeOnCancellation(Function1 function10) {
        this.invokeOnCancellationImpl(this.makeCancelHandler(function10));
    }

    @Override  // kotlinx.coroutines.Waiter
    public void invokeOnCancellation(Segment segment0, int v) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = CancellableContinuationImpl._decisionAndIndex$FU;
        while(true) {
            int v1 = atomicIntegerFieldUpdater0.get(this);
            if((v1 & 0x1FFFFFFF) != 0x1FFFFFFF) {
                break;
            }
            if(atomicIntegerFieldUpdater0.compareAndSet(this, v1, (v1 >> 29 << 29) + v)) {
                this.invokeOnCancellationImpl(segment0);
                return;
            }
        }
        throw new IllegalStateException("invokeOnCancellation should be called at most once");
    }

    private final void invokeOnCancellationImpl(Object object0) {
        Object object1;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = CancellableContinuationImpl._state$FU;
    alab1:
        while(true) {
            while(true) {
                while(true) {
                label_1:
                    object1 = atomicReferenceFieldUpdater0.get(this);
                    if(!(object1 instanceof Active)) {
                        break;
                    }
                    if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(CancellableContinuationImpl._state$FU, this, object1, object0)) {
                        return;
                    }
                }
                if((object1 instanceof CancelHandler ? true : object1 instanceof Segment)) {
                    this.multipleHandlersError(object0, object1);
                    goto label_1;
                }
                if(object1 instanceof CompletedExceptionally) {
                    CompletedExceptionally completedExceptionally0 = (CompletedExceptionally)object1;
                    if(!completedExceptionally0.makeHandled()) {
                        this.multipleHandlersError(object0, object1);
                    }
                    Throwable throwable0 = null;
                    if(!(object1 instanceof CancelledContinuation)) {
                        break alab1;
                    }
                    if(!(object1 instanceof CompletedExceptionally)) {
                        completedExceptionally0 = null;
                    }
                    if(completedExceptionally0 != null) {
                        throwable0 = completedExceptionally0.cause;
                    }
                    if(object0 instanceof CancelHandler) {
                        this.callCancelHandler(((CancelHandler)object0), throwable0);
                        return;
                    }
                    Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>");
                    this.callSegmentOnCancellation(((Segment)object0), throwable0);
                    return;
                }
                if(!(object1 instanceof CompletedContinuation)) {
                    goto label_36;
                }
                if(((CompletedContinuation)object1).cancelHandler != null) {
                    this.multipleHandlersError(object0, object1);
                }
                if(object0 instanceof Segment) {
                    break alab1;
                }
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                if(((CompletedContinuation)object1).getCancelled()) {
                    this.callCancelHandler(((CancelHandler)object0), ((CompletedContinuation)object1).cancelCause);
                    return;
                }
                CompletedContinuation completedContinuation0 = CompletedContinuation.copy$default(((CompletedContinuation)object1), null, ((CancelHandler)object0), null, null, null, 29, null);
                if(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(CancellableContinuationImpl._state$FU, this, object1, completedContinuation0)) {
                    goto label_1;
                }
                break;
            }
            return;
        label_36:
            if(object0 instanceof Segment) {
                break;
            }
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
            CompletedContinuation completedContinuation1 = new CompletedContinuation(object1, ((CancelHandler)object0), null, null, null, 28, null);
            if(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(CancellableContinuationImpl._state$FU, this, object1, completedContinuation1)) {
                goto label_1;
            }
            break;
        }
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public boolean isActive() {
        return this.getState$kotlinx_coroutines_core() instanceof NotCompleted;
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public boolean isCancelled() {
        return this.getState$kotlinx_coroutines_core() instanceof CancelledContinuation;
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public boolean isCompleted() {
        return !(this.getState$kotlinx_coroutines_core() instanceof NotCompleted);
    }

    private final boolean isReusable() {
        if(DispatchedTaskKt.isReusableMode(this.resumeMode)) {
            Intrinsics.checkNotNull(this.delegate, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            return ((DispatchedContinuation)this.delegate).isReusable$kotlinx_coroutines_core();
        }
        return false;
    }

    private final void loop$atomicfu(AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicIntegerFieldUpdater0.get(object0));
        }
    }

    private final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicReferenceFieldUpdater0.get(object0));
        }
    }

    private final CancelHandler makeCancelHandler(Function1 function10) {
        return function10 instanceof CancelHandler ? ((CancelHandler)function10) : new InvokeOnCancel(function10);
    }

    private final void multipleHandlersError(Object object0, Object object1) {
        throw new IllegalStateException(("It\'s prohibited to register multiple handlers, tried to register " + object0 + ", already has " + object1).toString());
    }

    protected String nameString() [...] // 潜在的解密器

    public final void parentCancelled$kotlinx_coroutines_core(Throwable throwable0) {
        if(this.cancelLater(throwable0)) {
            return;
        }
        this.cancel(throwable0);
        this.detachChildIfNonResuable();
    }

    public final void releaseClaimedReusableContinuation$kotlinx_coroutines_core() {
        DispatchedContinuation dispatchedContinuation0 = this.delegate instanceof DispatchedContinuation ? ((DispatchedContinuation)this.delegate) : null;
        if(dispatchedContinuation0 != null) {
            Throwable throwable0 = dispatchedContinuation0.tryReleaseClaimedContinuation$kotlinx_coroutines_core(this);
            if(throwable0 != null) {
                this.detachChild$kotlinx_coroutines_core();
                this.cancel(throwable0);
            }
        }
    }

    public final boolean resetStateReusable() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = CancellableContinuationImpl._state$FU;
        Object object0 = atomicReferenceFieldUpdater0.get(this);
        if(object0 instanceof CompletedContinuation && ((CompletedContinuation)object0).idempotentResume != null) {
            this.detachChild$kotlinx_coroutines_core();
            return false;
        }
        CancellableContinuationImpl._decisionAndIndex$FU.set(this, 0x1FFFFFFF);
        atomicReferenceFieldUpdater0.set(this, Active.INSTANCE);
        return true;
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public void resume(Object object0, Function1 function10) {
        this.resumeImpl(object0, this.resumeMode, function10);
    }

    private final void resumeImpl(Object object0, int v, Function1 function10) {
        Object object1;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = CancellableContinuationImpl._state$FU;
        while(true) {
            object1 = atomicReferenceFieldUpdater0.get(this);
            if(!(object1 instanceof NotCompleted)) {
                break;
            }
            Object object2 = this.resumedState(((NotCompleted)object1), object0, v, function10, null);
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(CancellableContinuationImpl._state$FU, this, object1, object2)) {
                this.detachChildIfNonResuable();
                this.dispatchResume(v);
                return;
            }
        }
        if(object1 instanceof CancelledContinuation && ((CancelledContinuation)object1).makeResumed()) {
            if(function10 != null) {
                this.callOnCancellation(function10, ((CancelledContinuation)object1).cause);
            }
            return;
        }
        this.alreadyResumedError(object0);
        throw new KotlinNothingValueException();
    }

    static void resumeImpl$default(CancellableContinuationImpl cancellableContinuationImpl0, Object object0, int v, Function1 function10, int v1, Object object1) {
        if(object1 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
        }
        if((v1 & 4) != 0) {
            function10 = null;
        }
        cancellableContinuationImpl0.resumeImpl(object0, v, function10);
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatched(CoroutineDispatcher coroutineDispatcher0, Object object0) {
        CoroutineDispatcher coroutineDispatcher1 = null;
        DispatchedContinuation dispatchedContinuation0 = this.delegate instanceof DispatchedContinuation ? ((DispatchedContinuation)this.delegate) : null;
        if(dispatchedContinuation0 != null) {
            coroutineDispatcher1 = dispatchedContinuation0.dispatcher;
        }
        CancellableContinuationImpl.resumeImpl$default(this, object0, (coroutineDispatcher1 == coroutineDispatcher0 ? 4 : this.resumeMode), null, 4, null);
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatchedWithException(CoroutineDispatcher coroutineDispatcher0, Throwable throwable0) {
        CoroutineDispatcher coroutineDispatcher1 = null;
        DispatchedContinuation dispatchedContinuation0 = this.delegate instanceof DispatchedContinuation ? ((DispatchedContinuation)this.delegate) : null;
        CompletedExceptionally completedExceptionally0 = new CompletedExceptionally(throwable0, false, 2, null);
        if(dispatchedContinuation0 != null) {
            coroutineDispatcher1 = dispatchedContinuation0.dispatcher;
        }
        CancellableContinuationImpl.resumeImpl$default(this, completedExceptionally0, (coroutineDispatcher1 == coroutineDispatcher0 ? 4 : this.resumeMode), null, 4, null);
    }

    @Override  // kotlin.coroutines.Continuation
    public void resumeWith(Object object0) {
        CancellableContinuationImpl.resumeImpl$default(this, CompletionStateKt.toState(object0, this), this.resumeMode, null, 4, null);
    }

    // 去混淆评级： 低(22)
    private final Object resumedState(NotCompleted notCompleted0, Object object0, int v, Function1 function10, Object object1) {
        if(object0 instanceof CompletedExceptionally) {
            return object0;
        }
        if(!DispatchedTaskKt.isCancellableMode(v) && object1 == null || function10 == null && !(notCompleted0 instanceof CancelHandler) && object1 == null) {
            return object0;
        }
        return notCompleted0 instanceof CancelHandler ? new CompletedContinuation(object0, ((CancelHandler)notCompleted0), function10, object1, null, 16, null) : new CompletedContinuation(object0, null, function10, object1, null, 16, null);
    }

    @Override  // kotlinx.coroutines.DispatchedTask
    public Object takeState$kotlinx_coroutines_core() {
        return this.getState$kotlinx_coroutines_core();
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return "CancellableContinuation" + '(' + DebugStringsKt.toDebugString(this.delegate) + "){" + this.getStateDebugRepresentation() + "}@" + DebugStringsKt.getHexAddress(this);
    }

    private final boolean tryResume() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = CancellableContinuationImpl._decisionAndIndex$FU;
    alab1:
        while(true) {
            int v = atomicIntegerFieldUpdater0.get(this);
            switch(v >> 29) {
                case 0: {
                    if(!CancellableContinuationImpl._decisionAndIndex$FU.compareAndSet(this, v, 0x40000000 + (0x1FFFFFFF & v))) {
                        break;
                    }
                    break alab1;
                }
                case 1: {
                    return false;
                }
                default: {
                    throw new IllegalStateException("Already resumed");
                }
            }
        }
        return true;
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public Object tryResume(Object object0, Object object1) {
        return this.tryResumeImpl(object0, object1, null);
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public Object tryResume(Object object0, Object object1, Function1 function10) {
        return this.tryResumeImpl(object0, object1, function10);
    }

    private final Symbol tryResumeImpl(Object object0, Object object1, Function1 function10) {
        Object object2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = CancellableContinuationImpl._state$FU;
        while(true) {
            object2 = atomicReferenceFieldUpdater0.get(this);
            if(!(object2 instanceof NotCompleted)) {
                break;
            }
            Object object3 = this.resumedState(((NotCompleted)object2), object0, this.resumeMode, function10, object1);
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(CancellableContinuationImpl._state$FU, this, object2, object3)) {
                this.detachChildIfNonResuable();
                return CancellableContinuationImplKt.RESUME_TOKEN;
            }
        }
        return !(object2 instanceof CompletedContinuation) || object1 == null || ((CompletedContinuation)object2).idempotentResume != object1 ? null : CancellableContinuationImplKt.RESUME_TOKEN;
    }

    @Override  // kotlinx.coroutines.CancellableContinuation
    public Object tryResumeWithException(Throwable throwable0) {
        return this.tryResumeImpl(new CompletedExceptionally(throwable0, false, 2, null), null, null);
    }

    private final boolean trySuspend() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = CancellableContinuationImpl._decisionAndIndex$FU;
    alab1:
        while(true) {
            int v = atomicIntegerFieldUpdater0.get(this);
            switch(v >> 29) {
                case 0: {
                    if(!CancellableContinuationImpl._decisionAndIndex$FU.compareAndSet(this, v, 0x20000000 + (0x1FFFFFFF & v))) {
                        break;
                    }
                    break alab1;
                }
                case 2: {
                    return false;
                }
                default: {
                    throw new IllegalStateException("Already suspended");
                }
            }
        }
        return true;
    }

    private final void update$atomicfu(AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0, Function1 function10, Object object0) {
        do {
            int v = atomicIntegerFieldUpdater0.get(object0);
        }
        while(!atomicIntegerFieldUpdater0.compareAndSet(object0, v, ((Number)function10.invoke(v)).intValue()));
    }
}

