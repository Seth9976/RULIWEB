package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

public final class FunctionsKt {
    private static final Function1 ALWAYS_NULL;
    private static final Function1 ALWAYS_TRUE;
    private static final Function1 DO_NOTHING;
    private static final Function2 DO_NOTHING_2;
    private static final Function3 DO_NOTHING_3;
    private static final Function1 IDENTITY;

    static {
        FunctionsKt.IDENTITY = FunctionsKt.IDENTITY.1.INSTANCE;
        FunctionsKt.ALWAYS_TRUE = FunctionsKt.ALWAYS_TRUE.1.INSTANCE;
        FunctionsKt.ALWAYS_NULL = FunctionsKt.ALWAYS_NULL.1.INSTANCE;
        FunctionsKt.DO_NOTHING = FunctionsKt.DO_NOTHING.1.INSTANCE;
        FunctionsKt.DO_NOTHING_2 = FunctionsKt.DO_NOTHING_2.1.INSTANCE;
        FunctionsKt.DO_NOTHING_3 = FunctionsKt.DO_NOTHING_3.1.INSTANCE;
    }

    public static final Function1 alwaysTrue() {
        return FunctionsKt.ALWAYS_TRUE;
    }

    public static final Function3 getDO_NOTHING_3() {
        return FunctionsKt.DO_NOTHING_3;
    }
}

