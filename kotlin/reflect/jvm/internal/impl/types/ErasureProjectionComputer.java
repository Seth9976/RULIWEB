package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

public class ErasureProjectionComputer {
    public TypeProjection computeProjection(TypeParameterDescriptor typeParameterDescriptor0, ErasureTypeAttributes erasureTypeAttributes0, TypeParameterUpperBoundEraser typeParameterUpperBoundEraser0, KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "parameter");
        Intrinsics.checkNotNullParameter(erasureTypeAttributes0, "typeAttr");
        Intrinsics.checkNotNullParameter(typeParameterUpperBoundEraser0, "typeParameterUpperBoundEraser");
        Intrinsics.checkNotNullParameter(kotlinType0, "erasedUpperBound");
        return new TypeProjectionImpl(Variance.OUT_VARIANCE, kotlinType0);
    }

    public static TypeProjection computeProjection$default(ErasureProjectionComputer erasureProjectionComputer0, TypeParameterDescriptor typeParameterDescriptor0, ErasureTypeAttributes erasureTypeAttributes0, TypeParameterUpperBoundEraser typeParameterUpperBoundEraser0, KotlinType kotlinType0, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: computeProjection");
        }
        if((v & 8) != 0) {
            kotlinType0 = typeParameterUpperBoundEraser0.getErasedUpperBound(typeParameterDescriptor0, erasureTypeAttributes0);
        }
        return erasureProjectionComputer0.computeProjection(typeParameterDescriptor0, erasureTypeAttributes0, typeParameterUpperBoundEraser0, kotlinType0);
    }
}

