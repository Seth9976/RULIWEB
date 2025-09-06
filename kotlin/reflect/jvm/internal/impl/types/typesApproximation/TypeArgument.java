package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;

final class TypeArgument {
    private final KotlinType inProjection;
    private final KotlinType outProjection;
    private final TypeParameterDescriptor typeParameter;

    public TypeArgument(TypeParameterDescriptor typeParameterDescriptor0, KotlinType kotlinType0, KotlinType kotlinType1) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "typeParameter");
        Intrinsics.checkNotNullParameter(kotlinType0, "inProjection");
        Intrinsics.checkNotNullParameter(kotlinType1, "outProjection");
        super();
        this.typeParameter = typeParameterDescriptor0;
        this.inProjection = kotlinType0;
        this.outProjection = kotlinType1;
    }

    public final KotlinType getInProjection() {
        return this.inProjection;
    }

    public final KotlinType getOutProjection() {
        return this.outProjection;
    }

    public final TypeParameterDescriptor getTypeParameter() {
        return this.typeParameter;
    }

    public final boolean isConsistent() {
        return KotlinTypeChecker.DEFAULT.isSubtypeOf(this.inProjection, this.outProjection);
    }
}

