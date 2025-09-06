package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

final class KotlinTypeFactory.EMPTY_REFINED_TYPE_FACTORY.1 extends Lambda implements Function1 {
    public static final KotlinTypeFactory.EMPTY_REFINED_TYPE_FACTORY.1 INSTANCE;

    static {
        KotlinTypeFactory.EMPTY_REFINED_TYPE_FACTORY.1.INSTANCE = new KotlinTypeFactory.EMPTY_REFINED_TYPE_FACTORY.1();
    }

    KotlinTypeFactory.EMPTY_REFINED_TYPE_FACTORY.1() {
        super(1);
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return this.invoke(((KotlinTypeRefiner)object0));
    }

    public final Void invoke(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "<anonymous parameter 0>");
        return null;
    }
}

