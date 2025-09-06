package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J-\u0010\u0007\u001A\u00020\b\"\u000E\b\u0000\u0010\t\u0018\u0001*\u00060\u0001j\u0002`\n2\u0012\u0010\u000B\u001A\u000E\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\b0\fH\u0086\bJ\u0010\u0010\r\u001A\n\u0018\u00010\u0001j\u0004\u0018\u0001`\nH\u0014J\u0006\u0010\u000E\u001A\u00020\u000FJ\r\u0010\u0010\u001A\u00020\bH\u0000¢\u0006\u0002\b\u0011R\u0011\u0010\u0003\u001A\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0005R\u0014\u0010\u0006\u001A\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0005¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "()V", "isEmpty", "", "()Z", "isRemoved", "forEach", "", "T", "Lkotlinx/coroutines/internal/Node;", "block", "Lkotlin/Function1;", "nextIfRemoved", "remove", "", "validate", "validate$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class LockFreeLinkedListHead extends LockFreeLinkedListNode {
    public final void forEach(Function1 function10) {
        Object object0 = this.getNext();
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        for(LockFreeLinkedListNode lockFreeLinkedListNode0 = (LockFreeLinkedListNode)object0; !Intrinsics.areEqual(lockFreeLinkedListNode0, this); lockFreeLinkedListNode0 = lockFreeLinkedListNode0.getNextNode()) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if(lockFreeLinkedListNode0 instanceof LockFreeLinkedListNode) {
                function10.invoke(lockFreeLinkedListNode0);
            }
        }
    }

    public final boolean isEmpty() {
        return this.getNext() == this;
    }

    @Override  // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public boolean isRemoved() {
        return false;
    }

    @Override  // kotlinx.coroutines.internal.LockFreeLinkedListNode
    protected LockFreeLinkedListNode nextIfRemoved() {
        return null;
    }

    // 去混淆评级： 低(20)
    public final Void remove() {
        throw new IllegalStateException("head cannot be removed");
    }

    @Override  // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public boolean remove() {
        return ((Boolean)this.remove()).booleanValue();
    }

    public final void validate$kotlinx_coroutines_core() {
        Object object0 = this.getNext();
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        LockFreeLinkedListNode lockFreeLinkedListNode0 = this;
        for(LockFreeLinkedListNode lockFreeLinkedListNode1 = (LockFreeLinkedListNode)object0; !Intrinsics.areEqual(lockFreeLinkedListNode1, this); lockFreeLinkedListNode1 = lockFreeLinkedListNode2) {
            LockFreeLinkedListNode lockFreeLinkedListNode2 = lockFreeLinkedListNode1.getNextNode();
            lockFreeLinkedListNode1.validateNode$kotlinx_coroutines_core(lockFreeLinkedListNode0, lockFreeLinkedListNode2);
            lockFreeLinkedListNode0 = lockFreeLinkedListNode1;
        }
        Object object1 = this.getNext();
        Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        this.validateNode$kotlinx_coroutines_core(lockFreeLinkedListNode0, ((LockFreeLinkedListNode)object1));
    }
}

