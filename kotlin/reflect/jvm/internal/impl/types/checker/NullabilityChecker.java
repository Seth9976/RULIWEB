package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.AbstractNullabilityChecker;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState.SupertypesPolicy.LowerIfFlexible;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;

public final class NullabilityChecker {
    public static final NullabilityChecker INSTANCE;

    static {
        NullabilityChecker.INSTANCE = new NullabilityChecker();
    }

    public final boolean isSubtypeOfAny(UnwrappedType unwrappedType0) {
        Intrinsics.checkNotNullParameter(unwrappedType0, "type");
        TypeCheckerState typeCheckerState0 = SimpleClassicTypeSystemContext.INSTANCE.newTypeCheckerState(false, true);
        SimpleTypeMarker simpleTypeMarker0 = FlexibleTypesKt.lowerIfFlexible(unwrappedType0);
        return AbstractNullabilityChecker.INSTANCE.hasNotNullSupertype(typeCheckerState0, simpleTypeMarker0, LowerIfFlexible.INSTANCE);
    }
}

