package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

final class FunctionsKt.DO_NOTHING_3.1 extends Lambda implements Function3 {
    public static final FunctionsKt.DO_NOTHING_3.1 INSTANCE;

    static {
        FunctionsKt.DO_NOTHING_3.1.INSTANCE = new FunctionsKt.DO_NOTHING_3.1();
    }

    FunctionsKt.DO_NOTHING_3.1() {
        super(3);
    }

    @Override  // kotlin.jvm.functions.Function3
    public Object invoke(Object object0, Object object1, Object object2) {
        return Unit.INSTANCE;
    }

    public final void invoke(Object object0, Object object1, Object object2) {
    }
}

