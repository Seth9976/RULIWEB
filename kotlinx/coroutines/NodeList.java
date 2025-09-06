package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000E\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u000BJ\b\u0010\r\u001A\u00020\u000BH\u0016R\u0014\u0010\u0004\u001A\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0006R\u0014\u0010\u0007\u001A\u00020\u00008VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\t¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/NodeList;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/Incomplete;", "()V", "isActive", "", "()Z", "list", "getList", "()Lkotlinx/coroutines/NodeList;", "getString", "", "state", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class NodeList extends LockFreeLinkedListHead implements Incomplete {
    @Override  // kotlinx.coroutines.Incomplete
    public NodeList getList() {
        return this;
    }

    public final String getString(String s) {
        StringBuilder stringBuilder0 = new StringBuilder("List{");
        stringBuilder0.append(s);
        stringBuilder0.append("}[");
        Object object0 = this.getNext();
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        LockFreeLinkedListNode lockFreeLinkedListNode0 = (LockFreeLinkedListNode)object0;
        boolean z = true;
        while(!Intrinsics.areEqual(lockFreeLinkedListNode0, this)) {
            if(lockFreeLinkedListNode0 instanceof JobNode) {
                if(z) {
                    z = false;
                }
                else {
                    stringBuilder0.append(", ");
                }
                stringBuilder0.append(((JobNode)lockFreeLinkedListNode0));
            }
            lockFreeLinkedListNode0 = lockFreeLinkedListNode0.getNextNode();
        }
        stringBuilder0.append("]");
        String s1 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s1, "StringBuilder().apply(builderAction).toString()");
        return s1;
    }

    @Override  // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return true;
    }

    // 去混淆评级： 低(30)
    @Override  // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return super.toString();
    }
}

