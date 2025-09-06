package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0006\b \u0018\u0000*\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002B\u000F\u0012\b\u0010\u0003\u001A\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0017\u001A\u00020\u0018J\u0006\u0010\u0019\u001A\u00020\u000EJ\u001E\u0010\u001A\u001A\u0004\u0018\u00018\u00002\f\u0010\u001B\u001A\b\u0012\u0004\u0012\u00020\u001D0\u001CH\u0086\b¢\u0006\u0002\u0010\u001EJ\u0006\u0010\u001F\u001A\u00020\u0018J\u0013\u0010 \u001A\u00020\u000E2\u0006\u0010!\u001A\u00028\u0000¢\u0006\u0002\u0010\"R\u0011\u0010\u0005\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006X\u0082\u0004R\u0011\u0010\u0007\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0006X\u0082\u0004R\u0016\u0010\b\u001A\u0004\u0018\u00018\u00008BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\nR\u0014\u0010\u000B\u001A\u00028\u00008BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\nR\u0012\u0010\r\u001A\u00020\u000EX¦\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000FR\u0011\u0010\u0010\u001A\u00020\u000E8F¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u000FR\u0013\u0010\u0011\u001A\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001A\u0004\b\u0012\u0010\nR\u0016\u0010\u0013\u001A\u0004\u0018\u00010\u00028BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0003\u001A\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001A\u0004\b\u0016\u0010\n¨\u0006#"}, d2 = {"Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "N", "", "prev", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)V", "_next", "Lkotlinx/atomicfu/AtomicRef;", "_prev", "aliveSegmentLeft", "getAliveSegmentLeft", "()Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "aliveSegmentRight", "getAliveSegmentRight", "isRemoved", "", "()Z", "isTail", "next", "getNext", "nextOrClosed", "getNextOrClosed", "()Ljava/lang/Object;", "getPrev", "cleanPrev", "", "markAsClosed", "nextOrIfClosed", "onClosedAction", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "remove", "trySetNext", "value", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Z", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ConcurrentLinkedListNode {
    @Volatile
    private volatile Object _next;
    private static final AtomicReferenceFieldUpdater _next$FU;
    @Volatile
    private volatile Object _prev;
    private static final AtomicReferenceFieldUpdater _prev$FU;

    static {
        ConcurrentLinkedListNode._next$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next");
        ConcurrentLinkedListNode._prev$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev");
    }

    public ConcurrentLinkedListNode(ConcurrentLinkedListNode concurrentLinkedListNode0) {
        this._prev = concurrentLinkedListNode0;
    }

    public final void cleanPrev() {
        ConcurrentLinkedListNode._prev$FU.lazySet(this, null);
    }

    private final ConcurrentLinkedListNode getAliveSegmentLeft() {
        ConcurrentLinkedListNode concurrentLinkedListNode0;
        for(concurrentLinkedListNode0 = this.getPrev(); concurrentLinkedListNode0 != null && concurrentLinkedListNode0.isRemoved(); concurrentLinkedListNode0 = (ConcurrentLinkedListNode)ConcurrentLinkedListNode._prev$FU.get(concurrentLinkedListNode0)) {
        }
        return concurrentLinkedListNode0;
    }

    private final ConcurrentLinkedListNode getAliveSegmentRight() {
        ConcurrentLinkedListNode concurrentLinkedListNode0 = this.getNext();
        Intrinsics.checkNotNull(concurrentLinkedListNode0);
        while(concurrentLinkedListNode0.isRemoved()) {
            ConcurrentLinkedListNode concurrentLinkedListNode1 = concurrentLinkedListNode0.getNext();
            if(concurrentLinkedListNode1 == null) {
                break;
            }
            concurrentLinkedListNode0 = concurrentLinkedListNode1;
        }
        return concurrentLinkedListNode0;
    }

    public final ConcurrentLinkedListNode getNext() {
        Object object0 = this.getNextOrClosed();
        return object0 == ConcurrentLinkedListKt.access$getCLOSED$p() ? null : ((ConcurrentLinkedListNode)object0);
    }

    private final Object getNextOrClosed() {
        return ConcurrentLinkedListNode._next$FU.get(this);
    }

    public final ConcurrentLinkedListNode getPrev() {
        return (ConcurrentLinkedListNode)ConcurrentLinkedListNode._prev$FU.get(this);
    }

    public abstract boolean isRemoved();

    public final boolean isTail() {
        return this.getNext() == null;
    }

    public final boolean markAsClosed() {
        return AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(ConcurrentLinkedListNode._next$FU, this, null, ConcurrentLinkedListKt.access$getCLOSED$p());
    }

    public final ConcurrentLinkedListNode nextOrIfClosed(Function0 function00) {
        Object object0 = this.getNextOrClosed();
        if(object0 != ConcurrentLinkedListKt.access$getCLOSED$p()) {
            return (ConcurrentLinkedListNode)object0;
        }
        function00.invoke();
        throw new KotlinNothingValueException();
    }

    public final void remove() {
        if(!this.isTail()) {
            while(true) {
                ConcurrentLinkedListNode concurrentLinkedListNode0 = this.getAliveSegmentLeft();
                ConcurrentLinkedListNode concurrentLinkedListNode1 = this.getAliveSegmentRight();
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = ConcurrentLinkedListNode._prev$FU;
                do {
                    Object object0 = atomicReferenceFieldUpdater0.get(concurrentLinkedListNode1);
                }
                while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, concurrentLinkedListNode1, object0, (((ConcurrentLinkedListNode)object0) == null ? null : concurrentLinkedListNode0)));
                if(concurrentLinkedListNode0 != null) {
                    ConcurrentLinkedListNode._next$FU.set(concurrentLinkedListNode0, concurrentLinkedListNode1);
                }
                if((!concurrentLinkedListNode1.isRemoved() || concurrentLinkedListNode1.isTail()) && (concurrentLinkedListNode0 == null || !concurrentLinkedListNode0.isRemoved())) {
                    break;
                }
            }
        }
    }

    public final boolean trySetNext(ConcurrentLinkedListNode concurrentLinkedListNode0) {
        return AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(ConcurrentLinkedListNode._next$FU, this, null, concurrentLinkedListNode0);
    }

    private final void update$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        do {
            Object object1 = atomicReferenceFieldUpdater0.get(object0);
        }
        while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, object0, object1, function10.invoke(object1)));
    }
}

