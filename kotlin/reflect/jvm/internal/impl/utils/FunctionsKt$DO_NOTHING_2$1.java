package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

final class FunctionsKt.DO_NOTHING_2.1 extends Lambda implements Function2 {
    public static final FunctionsKt.DO_NOTHING_2.1 INSTANCE;

    static {
        FunctionsKt.DO_NOTHING_2.1.INSTANCE = new FunctionsKt.DO_NOTHING_2.1();
    }

    FunctionsKt.DO_NOTHING_2.1() {
        super(2);
    }

    @Override  // kotlin.jvm.functions.Function2
    public Object invoke(Object object0, Object object1) {
        return Unit.INSTANCE;
    }

    public final void invoke(Object object0, Object object1) {
    }
}

