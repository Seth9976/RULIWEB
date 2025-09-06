package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class StarProjectionForAbsentTypeParameter extends TypeProjectionBase {
    private final KotlinType nullableAnyType;

    public StarProjectionForAbsentTypeParameter(KotlinBuiltIns kotlinBuiltIns0) {
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "kotlinBuiltIns");
        super();
        SimpleType simpleType0 = kotlinBuiltIns0.getNullableAnyType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "kotlinBuiltIns.nullableAnyType");
        this.nullableAnyType = simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public Variance getProjectionKind() {
        return Variance.OUT_VARIANCE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public KotlinType getType() {
        return this.nullableAnyType;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public boolean isStarProjection() {
        return true;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public TypeProjection refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return this;
    }
}

