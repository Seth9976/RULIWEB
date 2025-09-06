package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001B\u0010\u000F\u001A\u0004\u0018\u00010\u00102\n\u0010\u0011\u001A\u0006\u0012\u0002\b\u00030\u0012H\u0016¢\u0006\u0002\u0010\u0013R\u0016\u0010\u0004\u001A\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006R\u001A\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\b8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0014\u0010\f\u001A\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000E¨\u0006\u0014"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/ThrowingCaller;", "Lkotlin/reflect/jvm/internal/calls/Caller;", "", "()V", "member", "getMember", "()Ljava/lang/Void;", "parameterTypes", "", "Ljava/lang/reflect/Type;", "getParameterTypes", "()Ljava/util/List;", "returnType", "getReturnType", "()Ljava/lang/reflect/Type;", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ThrowingCaller implements Caller {
    public static final ThrowingCaller INSTANCE;

    static {
        ThrowingCaller.INSTANCE = new ThrowingCaller();
    }

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public Object call(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "args");
        throw new UnsupportedOperationException("call/callBy are not supported for this declaration.");
    }

    public Void getMember() [...] // Inlined contents

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public Member getMember() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public List getParameterTypes() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public Type getReturnType() {
        Class class0 = Void.TYPE;
        Intrinsics.checkNotNullExpressionValue(class0, "TYPE");
        return class0;
    }
}

