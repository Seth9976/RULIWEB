package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A0\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\b\b\u0000\u0010\u0004*\u00020\u00052\u0016\u0010\u0006\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u0002H\u00040\u0007H\u0000\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"useClassValue", "", "createCache", "Lkotlin/reflect/jvm/internal/CacheByClass;", "V", "", "compute", "Lkotlin/Function1;", "Ljava/lang/Class;", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CacheByClassKt {
    private static final boolean useClassValue;

    static {
        Boolean boolean0 = ClassValue.class;
        if(Result.isSuccess-impl(boolean0)) {
            Class class0 = (Class)boolean0;
            boolean0 = Boolean.TRUE;
        }
        CacheByClassKt.useClassValue = (Result.isFailure-impl(boolean0) ? Boolean.FALSE : boolean0).booleanValue();
    }

    public static final CacheByClass createCache(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "compute");
        return CacheByClassKt.useClassValue ? new ClassValueCache(function10) : new ConcurrentHashMapCache(function10);
    }
}

