package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

public final class ClassicTypeSystemContextKt {
    public static final UnwrappedType access$makeDefinitelyNotNullOrNotNullInternal(UnwrappedType unwrappedType0) {
        return ClassicTypeSystemContextKt.makeDefinitelyNotNullOrNotNullInternal(unwrappedType0);
    }

    private static final UnwrappedType makeDefinitelyNotNullOrNotNullInternal(UnwrappedType unwrappedType0) {
        return SpecialTypesKt.makeDefinitelyNotNullOrNotNull$default(unwrappedType0, false, 1, null);
    }
}

