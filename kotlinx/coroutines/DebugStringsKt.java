package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.internal.DispatchedContinuation;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000E\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001A\u0010\u0010\u0007\u001A\u00020\u0001*\u0006\u0012\u0002\b\u00030\bH\u0000\"\u0018\u0010\u0000\u001A\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001A\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0004¨\u0006\t"}, d2 = {"classSimpleName", "", "", "getClassSimpleName", "(Ljava/lang/Object;)Ljava/lang/String;", "hexAddress", "getHexAddress", "toDebugString", "Lkotlin/coroutines/Continuation;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DebugStringsKt {
    public static final String getClassSimpleName(Object object0) {
        return object0.getClass().getSimpleName();
    }

    public static final String getHexAddress(Object object0) {
        return Integer.toHexString(System.identityHashCode(object0));
    }

    public static final String toDebugString(Continuation continuation0) {
        if(continuation0 instanceof DispatchedContinuation) {
            return continuation0.toString();
        }
        String s = continuation0 + '@' + DebugStringsKt.getHexAddress(continuation0);
        return Result.exceptionOrNull-impl(s) == null ? s : continuation0.getClass().getName() + '@' + DebugStringsKt.getHexAddress(continuation0);
    }
}

