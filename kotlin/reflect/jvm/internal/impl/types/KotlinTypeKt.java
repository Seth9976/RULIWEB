package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorType;

public final class KotlinTypeKt {
    public static final boolean isError(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        return unwrappedType0 instanceof ErrorType || unwrappedType0 instanceof FlexibleType && ((FlexibleType)unwrappedType0).getDelegate() instanceof ErrorType;
    }

    public static final boolean isNullable(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return TypeUtils.isNullableType(kotlinType0);
    }
}

