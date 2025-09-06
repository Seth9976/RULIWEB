package kotlin.reflect.jvm.internal.calls;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001C\u0010\u0000\u001A\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"arity", "", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getArity", "(Lkotlin/reflect/jvm/internal/calls/Caller;)I", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CallerKt {
    public static final int getArity(Caller caller0) {
        Intrinsics.checkNotNullParameter(caller0, "<this>");
        return caller0.getParameterTypes().size();
    }
}

