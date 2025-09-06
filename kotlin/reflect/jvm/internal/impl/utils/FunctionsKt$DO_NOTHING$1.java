package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

final class FunctionsKt.DO_NOTHING.1 extends Lambda implements Function1 {
    public static final FunctionsKt.DO_NOTHING.1 INSTANCE;

    static {
        FunctionsKt.DO_NOTHING.1.INSTANCE = new FunctionsKt.DO_NOTHING.1();
    }

    FunctionsKt.DO_NOTHING.1() {
        super(1);
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return Unit.INSTANCE;
    }

    public final void invoke(Object object0) {
    }
}

