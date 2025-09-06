package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

public final class StorageKt {
    public static final Object getValue(NotNullLazyValue notNullLazyValue0, Object object0, KProperty kProperty0) {
        Intrinsics.checkNotNullParameter(notNullLazyValue0, "<this>");
        Intrinsics.checkNotNullParameter(kProperty0, "p");
        return notNullLazyValue0.invoke();
    }

    public static final Object getValue(NullableLazyValue nullableLazyValue0, Object object0, KProperty kProperty0) {
        Intrinsics.checkNotNullParameter(nullableLazyValue0, "<this>");
        Intrinsics.checkNotNullParameter(kProperty0, "p");
        return nullableLazyValue0.invoke();
    }
}

