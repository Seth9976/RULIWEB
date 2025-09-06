package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

public final class DynamicTypesKt {
    public static final boolean isDynamic(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return kotlinType0.unwrap() instanceof DynamicType;
    }
}

