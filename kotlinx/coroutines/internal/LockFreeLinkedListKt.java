package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0010\u0010\r\u001A\u00060\u000Ej\u0002`\u000F*\u00020\u0001H\u0001\"\u001C\u0010\u0000\u001A\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001A\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0006\u001A\u00020\u00078\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003\"\u0016\u0010\t\u001A\u00020\u00078\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003\"\u0016\u0010\u000B\u001A\u00020\u00078\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0003*\f\b\u0002\u0010\u0010\"\u00020\u000E2\u00020\u000E¨\u0006\u0011"}, d2 = {"CONDITION_FALSE", "", "getCONDITION_FALSE$annotations", "()V", "getCONDITION_FALSE", "()Ljava/lang/Object;", "FAILURE", "", "getFAILURE$annotations", "SUCCESS", "getSUCCESS$annotations", "UNDECIDED", "getUNDECIDED$annotations", "unwrap", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "Node", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class LockFreeLinkedListKt {
    private static final Object CONDITION_FALSE = null;
    public static final int FAILURE = 2;
    public static final int SUCCESS = 1;
    public static final int UNDECIDED;

    static {
        LockFreeLinkedListKt.CONDITION_FALSE = new Symbol("CONDITION_FALSE");
    }

    public static final Object getCONDITION_FALSE() {
        return LockFreeLinkedListKt.CONDITION_FALSE;
    }

    public static void getCONDITION_FALSE$annotations() {
    }

    public static void getFAILURE$annotations() {
    }

    public static void getSUCCESS$annotations() {
    }

    public static void getUNDECIDED$annotations() {
    }

    public static final LockFreeLinkedListNode unwrap(Object object0) {
        Removed removed0 = object0 instanceof Removed ? ((Removed)object0) : null;
        if(removed0 != null) {
            LockFreeLinkedListNode lockFreeLinkedListNode0 = removed0.ref;
            if(lockFreeLinkedListNode0 != null) {
                return lockFreeLinkedListNode0;
            }
        }
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        return (LockFreeLinkedListNode)object0;
    }
}

