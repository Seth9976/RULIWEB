package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ServiceLoader;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class BuiltInsLoader.Companion.Instance.2 extends Lambda implements Function0 {
    public static final BuiltInsLoader.Companion.Instance.2 INSTANCE;

    static {
        BuiltInsLoader.Companion.Instance.2.INSTANCE = new BuiltInsLoader.Companion.Instance.2();
    }

    BuiltInsLoader.Companion.Instance.2() {
        super(0);
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }

    public final BuiltInsLoader invoke() {
        ServiceLoader serviceLoader0 = ServiceLoader.load(BuiltInsLoader.class, BuiltInsLoader.class.getClassLoader());
        Intrinsics.checkNotNullExpressionValue(serviceLoader0, "implementations");
        BuiltInsLoader builtInsLoader0 = (BuiltInsLoader)CollectionsKt.firstOrNull(serviceLoader0);
        if(builtInsLoader0 == null) {
            throw new IllegalStateException("No BuiltInsLoader implementation was found. Please ensure that the META-INF/services/ is not stripped from your application and that the Java virtual machine is not running under a security manager");
        }
        return builtInsLoader0;
    }
}

