package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlinx.coroutines.DebugStringsKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0017\u0018\u00002\u00020\u0001:\u00010B\u0005\u00A2\u0006\u0002\u0010\u0002J\u0012\u0010\u0014\u001A\u00020\u00152\n\u0010\u0016\u001A\u00060\u0000j\u0002`\u000FJ%\u0010\u0017\u001A\u00020\t2\n\u0010\u0016\u001A\u00060\u0000j\u0002`\u000F2\u000E\b\u0004\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\t0\u0019H\u0086\bJ \u0010\u001A\u001A\u00020\t2\n\u0010\u0016\u001A\u00060\u0000j\u0002`\u000F2\n\u0010\u000B\u001A\u00060\u0000j\u0002`\u000FH\u0001J\u0012\u0010\u001B\u001A\u00020\t2\n\u0010\u0016\u001A\u00060\u0000j\u0002`\u000FJ\u001B\u0010\u001C\u001A\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u000F2\b\u0010\u001D\u001A\u0004\u0018\u00010\u001EH\u0082\u0010J\u0019\u0010\u001F\u001A\u00060\u0000j\u0002`\u000F2\n\u0010 \u001A\u00060\u0000j\u0002`\u000FH\u0082\u0010J\u0014\u0010!\u001A\u00020\u00152\n\u0010\u000B\u001A\u00060\u0000j\u0002`\u000FH\u0002J%\u0010\"\u001A\u00020#2\n\u0010\u0016\u001A\u00060\u0000j\u0002`\u000F2\u000E\b\u0004\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\t0\u0019H\u0081\bJ\u0010\u0010$\u001A\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u000FH\u0014J\b\u0010%\u001A\u00020\tH\u0016J\u0010\u0010&\u001A\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u000FH\u0001J\b\u0010\'\u001A\u00020\u0007H\u0002J\b\u0010(\u001A\u00020)H\u0016J(\u0010*\u001A\u00020+2\n\u0010\u0016\u001A\u00060\u0000j\u0002`\u000F2\n\u0010\u000B\u001A\u00060\u0000j\u0002`\u000F2\u0006\u0010,\u001A\u00020#H\u0001J%\u0010-\u001A\u00020\u00152\n\u0010.\u001A\u00060\u0000j\u0002`\u000F2\n\u0010\u000B\u001A\u00060\u0000j\u0002`\u000FH\u0000\u00A2\u0006\u0002\b/R\u000F\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004R\u000F\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00000\u0004X\u0082\u0004R\u0011\u0010\u0006\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004X\u0082\u0004R\u0014\u0010\b\u001A\u00020\t8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\b\u0010\nR\u0011\u0010\u000B\u001A\u00020\u00018F\u00A2\u0006\u0006\u001A\u0004\b\f\u0010\rR\u0015\u0010\u000E\u001A\u00060\u0000j\u0002`\u000F8F\u00A2\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0012\u001A\u00060\u0000j\u0002`\u000F8F\u00A2\u0006\u0006\u001A\u0004\b\u0013\u0010\u0011\u00A8\u00061"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "", "()V", "_next", "Lkotlinx/atomicfu/AtomicRef;", "_prev", "_removedRef", "Lkotlinx/coroutines/internal/Removed;", "isRemoved", "", "()Z", "next", "getNext", "()Ljava/lang/Object;", "nextNode", "Lkotlinx/coroutines/internal/Node;", "getNextNode", "()Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "prevNode", "getPrevNode", "addLast", "", "node", "addLastIf", "condition", "Lkotlin/Function0;", "addNext", "addOneIfEmpty", "correctPrev", "op", "Lkotlinx/coroutines/internal/OpDescriptor;", "findPrevNonRemoved", "current", "finishAdd", "makeCondAddOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "nextIfRemoved", "remove", "removeOrNext", "removed", "toString", "", "tryCondAddNext", "", "condAdd", "validateNode", "prev", "validateNode$kotlinx_coroutines_core", "CondAddOp", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class LockFreeLinkedListNode {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\b!\u0018\u00002\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B\u0011\u0012\n\u0010\u0004\u001A\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0005J\u001E\u0010\u0007\u001A\u00020\b2\n\u0010\t\u001A\u00060\u0002j\u0002`\u00032\b\u0010\n\u001A\u0004\u0018\u00010\u000BH\u0016R\u0014\u0010\u0004\u001A\u00060\u0002j\u0002`\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u0006\u001A\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00038\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "Lkotlinx/coroutines/internal/AtomicOp;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "newNode", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "oldNext", "complete", "", "affected", "failure", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static abstract class CondAddOp extends AtomicOp {
        public final LockFreeLinkedListNode newNode;
        public LockFreeLinkedListNode oldNext;

        public CondAddOp(LockFreeLinkedListNode lockFreeLinkedListNode0) {
            this.newNode = lockFreeLinkedListNode0;
        }

        @Override  // kotlinx.coroutines.internal.AtomicOp
        public void complete(Object object0, Object object1) {
            this.complete(((LockFreeLinkedListNode)object0), object1);
        }

        public void complete(LockFreeLinkedListNode lockFreeLinkedListNode0, Object object0) {
            LockFreeLinkedListNode lockFreeLinkedListNode1 = object0 == null ? this.oldNext : this.newNode;
            if(lockFreeLinkedListNode1 != null && AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._next$FU, lockFreeLinkedListNode0, this, lockFreeLinkedListNode1) && object0 == null) {
                LockFreeLinkedListNode lockFreeLinkedListNode2 = this.oldNext;
                Intrinsics.checkNotNull(lockFreeLinkedListNode2);
                this.newNode.finishAdd(lockFreeLinkedListNode2);
            }
        }
    }

    @Volatile
    private volatile Object _next;
    private static final AtomicReferenceFieldUpdater _next$FU;
    @Volatile
    private volatile Object _prev;
    private static final AtomicReferenceFieldUpdater _prev$FU;
    @Volatile
    private volatile Object _removedRef;
    private static final AtomicReferenceFieldUpdater _removedRef$FU;

    static {
        LockFreeLinkedListNode._next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_next");
        LockFreeLinkedListNode._prev$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_prev");
        LockFreeLinkedListNode._removedRef$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_removedRef");
    }

    public LockFreeLinkedListNode() {
        this._next = this;
        this._prev = this;
    }

    public final void addLast(LockFreeLinkedListNode lockFreeLinkedListNode0) {
        while(!this.getPrevNode().addNext(lockFreeLinkedListNode0, this)) {
        }
    }

    public final boolean addLastIf(LockFreeLinkedListNode lockFreeLinkedListNode0, Function0 function00) {
        CondAddOp lockFreeLinkedListNode$CondAddOp0 = new kotlinx.coroutines.internal.LockFreeLinkedListNode.makeCondAddOp.1(lockFreeLinkedListNode0, function00);
    alab1:
        while(true) {
            switch(this.getPrevNode().tryCondAddNext(lockFreeLinkedListNode0, this, lockFreeLinkedListNode$CondAddOp0)) {
                case 1: {
                    return true;
                }
                case 2: {
                    break alab1;
                }
            }
        }
        return false;
    }

    public final boolean addNext(LockFreeLinkedListNode lockFreeLinkedListNode0, LockFreeLinkedListNode lockFreeLinkedListNode1) {
        LockFreeLinkedListNode._prev$FU.lazySet(lockFreeLinkedListNode0, this);
        LockFreeLinkedListNode._next$FU.lazySet(lockFreeLinkedListNode0, lockFreeLinkedListNode1);
        if(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._next$FU, this, lockFreeLinkedListNode1, lockFreeLinkedListNode0)) {
            return false;
        }
        lockFreeLinkedListNode0.finishAdd(lockFreeLinkedListNode1);
        return true;
    }

    public final boolean addOneIfEmpty(LockFreeLinkedListNode lockFreeLinkedListNode0) {
        LockFreeLinkedListNode._prev$FU.lazySet(lockFreeLinkedListNode0, this);
        LockFreeLinkedListNode._next$FU.lazySet(lockFreeLinkedListNode0, this);
        do {
            if(this.getNext() != this) {
                return false;
            }
        }
        while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._next$FU, this, this, lockFreeLinkedListNode0));
        lockFreeLinkedListNode0.finishAdd(this);
        return true;
    }

    private final LockFreeLinkedListNode correctPrev(OpDescriptor opDescriptor0) {
        LockFreeLinkedListNode lockFreeLinkedListNode1;
        while(true) {
            LockFreeLinkedListNode lockFreeLinkedListNode0 = (LockFreeLinkedListNode)LockFreeLinkedListNode._prev$FU.get(this);
            lockFreeLinkedListNode1 = lockFreeLinkedListNode0;
        label_2:
            LockFreeLinkedListNode lockFreeLinkedListNode2 = null;
        label_3:
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = LockFreeLinkedListNode._next$FU;
            Object object0 = atomicReferenceFieldUpdater0.get(lockFreeLinkedListNode1);
            if(object0 != this) {
                if(this.isRemoved()) {
                    return null;
                }
                if(object0 == opDescriptor0) {
                    return lockFreeLinkedListNode1;
                }
            }
            else if(lockFreeLinkedListNode0 != lockFreeLinkedListNode1 && !AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._prev$FU, this, lockFreeLinkedListNode0, lockFreeLinkedListNode1)) {
                continue;
            }
            else {
                return lockFreeLinkedListNode1;
            }
            if(object0 instanceof OpDescriptor) {
                ((OpDescriptor)object0).perform(lockFreeLinkedListNode1);
                continue;
            }
            if(!(object0 instanceof Removed)) {
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
                lockFreeLinkedListNode2 = lockFreeLinkedListNode1;
                lockFreeLinkedListNode1 = (LockFreeLinkedListNode)object0;
                goto label_3;
            }
            if(lockFreeLinkedListNode2 == null) {
                break;
            }
            if(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, lockFreeLinkedListNode2, lockFreeLinkedListNode1, ((Removed)object0).ref)) {
                continue;
            }
            lockFreeLinkedListNode1 = lockFreeLinkedListNode2;
            goto label_2;
        }
        lockFreeLinkedListNode1 = (LockFreeLinkedListNode)LockFreeLinkedListNode._prev$FU.get(lockFreeLinkedListNode1);
        goto label_3;
    }

    private final LockFreeLinkedListNode findPrevNonRemoved(LockFreeLinkedListNode lockFreeLinkedListNode0) {
        while(lockFreeLinkedListNode0.isRemoved()) {
            lockFreeLinkedListNode0 = (LockFreeLinkedListNode)LockFreeLinkedListNode._prev$FU.get(lockFreeLinkedListNode0);
        }
        return lockFreeLinkedListNode0;
    }

    private final void finishAdd(LockFreeLinkedListNode lockFreeLinkedListNode0) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = LockFreeLinkedListNode._prev$FU;
        while(true) {
            LockFreeLinkedListNode lockFreeLinkedListNode1 = (LockFreeLinkedListNode)atomicReferenceFieldUpdater0.get(lockFreeLinkedListNode0);
            if(this.getNext() != lockFreeLinkedListNode0) {
                break;
            }
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._prev$FU, lockFreeLinkedListNode0, lockFreeLinkedListNode1, this)) {
                if(!this.isRemoved()) {
                    break;
                }
                lockFreeLinkedListNode0.correctPrev(null);
                return;
            }
        }
    }

    public final Object getNext() {
        Object object0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = LockFreeLinkedListNode._next$FU;
        while(true) {
            object0 = atomicReferenceFieldUpdater0.get(this);
            if(!(object0 instanceof OpDescriptor)) {
                break;
            }
            ((OpDescriptor)object0).perform(this);
        }
        return object0;
    }

    public final LockFreeLinkedListNode getNextNode() {
        return LockFreeLinkedListKt.unwrap(this.getNext());
    }

    public final LockFreeLinkedListNode getPrevNode() {
        LockFreeLinkedListNode lockFreeLinkedListNode0 = this.correctPrev(null);
        return lockFreeLinkedListNode0 == null ? this.findPrevNonRemoved(((LockFreeLinkedListNode)LockFreeLinkedListNode._prev$FU.get(this))) : lockFreeLinkedListNode0;
    }

    public boolean isRemoved() {
        return this.getNext() instanceof Removed;
    }

    private final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicReferenceFieldUpdater0.get(object0));
        }
    }

    public final CondAddOp makeCondAddOp(LockFreeLinkedListNode lockFreeLinkedListNode0, Function0 function00) {
        return new CondAddOp(function00) {
            @Override  // kotlinx.coroutines.internal.AtomicOp
            public Object prepare(Object object0) {
                return this.prepare(((LockFreeLinkedListNode)object0));
            }

            // 去混淆评级： 低(20)
            public Object prepare(LockFreeLinkedListNode lockFreeLinkedListNode0) {
                return ((Boolean)this.$condition.invoke()).booleanValue() ? null : LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
    }

    protected LockFreeLinkedListNode nextIfRemoved() {
        Object object0 = this.getNext();
        Removed removed0 = object0 instanceof Removed ? ((Removed)object0) : null;
        return removed0 == null ? null : removed0.ref;
    }

    public boolean remove() {
        return this.removeOrNext() == null;
    }

    public final LockFreeLinkedListNode removeOrNext() {
        Object object0;
        do {
            object0 = this.getNext();
            if(object0 instanceof Removed) {
                return ((Removed)object0).ref;
            }
            if(object0 == this) {
                return (LockFreeLinkedListNode)object0;
            }
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            Removed removed0 = ((LockFreeLinkedListNode)object0).removed();
        }
        while(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._next$FU, this, object0, removed0));
        ((LockFreeLinkedListNode)object0).correctPrev(null);
        return null;
    }

    private final Removed removed() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = LockFreeLinkedListNode._removedRef$FU;
        Removed removed0 = (Removed)atomicReferenceFieldUpdater0.get(this);
        if(removed0 == null) {
            removed0 = new Removed(this);
            atomicReferenceFieldUpdater0.lazySet(this, removed0);
        }
        return removed0;
    }

    @Override
    public String toString() {
        return new PropertyReference0Impl() {
            {
                super(object0, DebugStringsKt.class, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", 1);
            }

            @Override  // kotlin.jvm.internal.PropertyReference0Impl
            public Object get() {
                return DebugStringsKt.getClassSimpleName(this.receiver);
            }
        } + '@' + DebugStringsKt.getHexAddress(this);
    }

    public final int tryCondAddNext(LockFreeLinkedListNode lockFreeLinkedListNode0, LockFreeLinkedListNode lockFreeLinkedListNode1, CondAddOp lockFreeLinkedListNode$CondAddOp0) {
        LockFreeLinkedListNode._prev$FU.lazySet(lockFreeLinkedListNode0, this);
        LockFreeLinkedListNode._next$FU.lazySet(lockFreeLinkedListNode0, lockFreeLinkedListNode1);
        lockFreeLinkedListNode$CondAddOp0.oldNext = lockFreeLinkedListNode1;
        if(!AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(LockFreeLinkedListNode._next$FU, this, lockFreeLinkedListNode1, lockFreeLinkedListNode$CondAddOp0)) {
            return 0;
        }
        return lockFreeLinkedListNode$CondAddOp0.perform(this) == null ? 1 : 2;
    }

    // 去混淆评级： 中等(60)
    public final void validateNode$kotlinx_coroutines_core(LockFreeLinkedListNode lockFreeLinkedListNode0, LockFreeLinkedListNode lockFreeLinkedListNode1) {
    }
}

