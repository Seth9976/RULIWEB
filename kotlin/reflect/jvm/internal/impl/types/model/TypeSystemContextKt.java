package kotlin.reflect.jvm.internal.impl.types.model;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public final class TypeSystemContextKt {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Variance.values().length];
            try {
                arr_v[Variance.INVARIANT.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Variance.IN_VARIANCE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Variance.OUT_VARIANCE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final TypeVariance convertVariance(Variance variance0) {
        Intrinsics.checkNotNullParameter(variance0, "<this>");
        switch(WhenMappings.$EnumSwitchMapping$0[variance0.ordinal()]) {
            case 1: {
                return TypeVariance.INV;
            }
            case 2: {
                return TypeVariance.IN;
            }
            case 3: {
                return TypeVariance.OUT;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }
}

