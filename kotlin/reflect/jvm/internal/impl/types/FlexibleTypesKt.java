package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

public final class FlexibleTypesKt {
    public static final FlexibleType asFlexibleType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        Intrinsics.checkNotNull(unwrappedType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.FlexibleType");
        return (FlexibleType)unwrappedType0;
    }

    public static final boolean isFlexible(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return kotlinType0.unwrap() instanceof FlexibleType;
    }

    public static final SimpleType lowerIfFlexible(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        if(unwrappedType0 instanceof FlexibleType) {
            return ((FlexibleType)unwrappedType0).getLowerBound();
        }
        if(!(unwrappedType0 instanceof SimpleType)) {
            throw new NoWhenBranchMatchedException();
        }
        return (SimpleType)unwrappedType0;
    }

    public static final SimpleType upperIfFlexible(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        if(unwrappedType0 instanceof FlexibleType) {
            return ((FlexibleType)unwrappedType0).getUpperBound();
        }
        if(!(unwrappedType0 instanceof SimpleType)) {
            throw new NoWhenBranchMatchedException();
        }
        return (SimpleType)unwrappedType0;
    }
}

