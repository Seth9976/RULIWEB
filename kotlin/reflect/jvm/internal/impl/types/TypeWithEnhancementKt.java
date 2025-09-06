package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class TypeWithEnhancementKt {
    public static final KotlinType getEnhancement(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return kotlinType0 instanceof TypeWithEnhancement ? ((TypeWithEnhancement)kotlinType0).getEnhancement() : null;
    }

    public static final UnwrappedType inheritEnhancement(UnwrappedType unwrappedType0, KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(unwrappedType0, "<this>");
        Intrinsics.checkNotNullParameter(kotlinType0, "origin");
        return TypeWithEnhancementKt.wrapEnhancement(unwrappedType0, TypeWithEnhancementKt.getEnhancement(kotlinType0));
    }

    public static final UnwrappedType inheritEnhancement(UnwrappedType unwrappedType0, KotlinType kotlinType0, Function1 function10) {
        Intrinsics.checkNotNullParameter(unwrappedType0, "<this>");
        Intrinsics.checkNotNullParameter(kotlinType0, "origin");
        Intrinsics.checkNotNullParameter(function10, "transform");
        KotlinType kotlinType1 = TypeWithEnhancementKt.getEnhancement(kotlinType0);
        return kotlinType1 == null ? TypeWithEnhancementKt.wrapEnhancement(unwrappedType0, null) : TypeWithEnhancementKt.wrapEnhancement(unwrappedType0, ((KotlinType)function10.invoke(kotlinType1)));
    }

    public static final UnwrappedType wrapEnhancement(UnwrappedType unwrappedType0, KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(unwrappedType0, "<this>");
        if(unwrappedType0 instanceof TypeWithEnhancement) {
            return TypeWithEnhancementKt.wrapEnhancement(((TypeWithEnhancement)unwrappedType0).getOrigin(), kotlinType0);
        }
        if(kotlinType0 != null && !Intrinsics.areEqual(kotlinType0, unwrappedType0)) {
            if(unwrappedType0 instanceof SimpleType) {
                return new SimpleTypeWithEnhancement(((SimpleType)unwrappedType0), kotlinType0);
            }
            if(!(unwrappedType0 instanceof FlexibleType)) {
                throw new NoWhenBranchMatchedException();
            }
            return new FlexibleTypeWithEnhancement(((FlexibleType)unwrappedType0), kotlinType0);
        }
        return unwrappedType0;
    }
}

