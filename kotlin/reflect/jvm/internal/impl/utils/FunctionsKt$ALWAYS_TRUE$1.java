package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

final class FunctionsKt.ALWAYS_TRUE.1 extends Lambda implements Function1 {
    public static final FunctionsKt.ALWAYS_TRUE.1 INSTANCE;

    static {
        FunctionsKt.ALWAYS_TRUE.1.INSTANCE = new FunctionsKt.ALWAYS_TRUE.1();
    }

    FunctionsKt.ALWAYS_TRUE.1() {
        super(1);
    }

    public final Boolean invoke(Object object0) {
        return true;
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return this.invoke(object0);
    }
}

