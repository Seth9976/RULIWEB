package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.AbstractStrictEqualityTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

public final class StrictEqualityTypeChecker {
    public static final StrictEqualityTypeChecker INSTANCE;

    static {
        StrictEqualityTypeChecker.INSTANCE = new StrictEqualityTypeChecker();
    }

    public final boolean strictEqualTypes(UnwrappedType unwrappedType0, UnwrappedType unwrappedType1) {
        Intrinsics.checkNotNullParameter(unwrappedType0, "a");
        Intrinsics.checkNotNullParameter(unwrappedType1, "b");
        return AbstractStrictEqualityTypeChecker.INSTANCE.strictEqualTypes(SimpleClassicTypeSystemContext.INSTANCE, unwrappedType0, unwrappedType1);
    }
}

