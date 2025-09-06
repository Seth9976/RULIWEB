package kotlinx.coroutines.flow;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0007\u001A\u00020\b2\n\u0010\t\u001A\u0006\u0012\u0002\b\u00030\u0002H\u0016J\u0011\u0010\n\u001A\u00020\u000BH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\'\u0010\r\u001A\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000B\u0018\u00010\u000F0\u000E2\n\u0010\t\u001A\u0006\u0012\u0002\b\u00030\u0002H\u0016¢\u0006\u0002\u0010\u0010J\u0006\u0010\u0011\u001A\u00020\u000BJ\u0006\u0010\u0012\u001A\u00020\bR\u0011\u0010\u0004\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/flow/StateFlowSlot;", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "Lkotlinx/coroutines/flow/StateFlowImpl;", "()V", "_state", "Lkotlinx/atomicfu/AtomicRef;", "", "allocateLocked", "", "flow", "awaitPending", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "freeLocked", "", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/flow/StateFlowImpl;)[Lkotlin/coroutines/Continuation;", "makePending", "takePending", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class StateFlowSlot extends AbstractSharedFlowSlot {
    @Volatile
    private volatile Object _state;
    private static final AtomicReferenceFieldUpdater _state$FU;

    static {
        StateFlowSlot._state$FU = AtomicReferenceFieldUpdater.newUpdater(StateFlowSlot.class, Object.class, "_state");
    }

    @Override  // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public boolean allocateLocked(Object object0) {
        return this.allocateLocked(((StateFlowImpl)object0));
    }

    public boolean allocateLocked(StateFlowImpl stateFlowImpl0) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = StateFlowSlot._state$FU;
        if(atomicReferenceFieldUpdater0.get(this) != null) {
            return false;
        }
        atomicReferenceFieldUpdater0.set(this, StateFlowKt.NONE);
        return true;
    }

    public final Object awaitPending(Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        if(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(StateFlowSlot._state$FU, this, StateFlowKt.NONE, cancellableContinuationImpl0)) {
            cancellableContinuationImpl0.resumeWith(Unit.INSTANCE);
        }
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public Continuation[] freeLocked(Object object0) {
        return this.freeLocked(((StateFlowImpl)object0));
    }

    public Continuation[] freeLocked(StateFlowImpl stateFlowImpl0) {
        StateFlowSlot._state$FU.set(this, null);
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }

    private final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicReferenceFieldUpdater0.get(object0));
        }
    }

    public final void makePending() {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = StateFlowSlot._state$FU;
    alab1:
        while(true) {
            do {
                object0 = atomicReferenceFieldUpdater0.get(this);
                if(object0 == null || object0 == StateFlowKt.PENDING) {
                    break alab1;
                }
                if(object0 != StateFlowKt.NONE) {
                    goto label_6;
                }
            }
            while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(StateFlowSlot._state$FU, this, object0, StateFlowKt.PENDING));
            return;
        label_6:
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(StateFlowSlot._state$FU, this, object0, StateFlowKt.NONE)) {
                ((CancellableContinuationImpl)object0).resumeWith(Unit.INSTANCE);
                return;
            }
        }
    }

    public final boolean takePending() {
        Object object0 = StateFlowSlot._state$FU.getAndSet(this, StateFlowKt.NONE);
        Intrinsics.checkNotNull(object0);
        return object0 == StateFlowKt.PENDING;
    }
}

