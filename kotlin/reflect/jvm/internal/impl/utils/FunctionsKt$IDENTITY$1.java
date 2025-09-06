package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

final class FunctionsKt.IDENTITY.1 extends Lambda implements Function1 {
    public static final FunctionsKt.IDENTITY.1 INSTANCE;

    static {
        FunctionsKt.IDENTITY.1.INSTANCE = new FunctionsKt.IDENTITY.1();
    }

    FunctionsKt.IDENTITY.1() {
        super(1);
    }

    @Override  // kotlin.jvm.functions.Function1
    public final Object invoke(Object object0) {
        return object0;
    }
}

