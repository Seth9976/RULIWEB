package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErasureProjectionComputer;
import kotlin.reflect.jvm.internal.impl.types.ErasureTypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeParameterUpperBoundEraser;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public final class RawProjectionComputer extends ErasureProjectionComputer {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[JavaTypeFlexibility.values().length];
            try {
                arr_v[JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[JavaTypeFlexibility.INFLEXIBLE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.ErasureProjectionComputer
    public TypeProjection computeProjection(TypeParameterDescriptor typeParameterDescriptor0, ErasureTypeAttributes erasureTypeAttributes0, TypeParameterUpperBoundEraser typeParameterUpperBoundEraser0, KotlinType kotlinType0) {
        TypeProjection typeProjection0;
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "parameter");
        Intrinsics.checkNotNullParameter(erasureTypeAttributes0, "typeAttr");
        Intrinsics.checkNotNullParameter(typeParameterUpperBoundEraser0, "typeParameterUpperBoundEraser");
        Intrinsics.checkNotNullParameter(kotlinType0, "erasedUpperBound");
        if(!(erasureTypeAttributes0 instanceof JavaTypeAttributes)) {
            return super.computeProjection(typeParameterDescriptor0, erasureTypeAttributes0, typeParameterUpperBoundEraser0, kotlinType0);
        }
        JavaTypeAttributes javaTypeAttributes0 = (JavaTypeAttributes)erasureTypeAttributes0;
        if(!javaTypeAttributes0.isRaw()) {
            javaTypeAttributes0 = javaTypeAttributes0.withFlexibility(JavaTypeFlexibility.INFLEXIBLE);
        }
        switch(WhenMappings.$EnumSwitchMapping$0[javaTypeAttributes0.getFlexibility().ordinal()]) {
            case 1: {
                return new TypeProjectionImpl(Variance.INVARIANT, kotlinType0);
            }
            case 2: 
            case 3: {
                if(typeParameterDescriptor0.getVariance().getAllowsOutPosition()) {
                    List list0 = kotlinType0.getConstructor().getParameters();
                    Intrinsics.checkNotNullExpressionValue(list0, "erasedUpperBound.constructor.parameters");
                    typeProjection0 = list0.isEmpty() ? TypeUtils.makeStarProjection(typeParameterDescriptor0, javaTypeAttributes0) : new TypeProjectionImpl(Variance.OUT_VARIANCE, kotlinType0);
                }
                else {
                    KotlinType kotlinType1 = DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor0).getNothingType();
                    typeProjection0 = new TypeProjectionImpl(Variance.INVARIANT, kotlinType1);
                }
                Intrinsics.checkNotNullExpressionValue(typeProjection0, "{\n                if (!p…          }\n            }");
                return typeProjection0;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }
}

