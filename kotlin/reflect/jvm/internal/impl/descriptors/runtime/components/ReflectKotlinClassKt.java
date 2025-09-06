package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.util.Set;
import kotlin.collections.SetsKt;

public final class ReflectKotlinClassKt {
    private static final Set TYPES_ELIGIBLE_FOR_SIMPLE_VISIT;

    static {
        ReflectKotlinClassKt.TYPES_ELIGIBLE_FOR_SIMPLE_VISIT = SetsKt.setOf(new Class[]{Integer.class, Character.class, Byte.class, Long.class, Short.class, Boolean.class, Double.class, Float.class, int[].class, char[].class, byte[].class, long[].class, short[].class, boolean[].class, double[].class, float[].class, Class.class, String.class});
    }

    public static final Set access$getTYPES_ELIGIBLE_FOR_SIMPLE_VISIT$p() {
        return ReflectKotlinClassKt.TYPES_ELIGIBLE_FOR_SIMPLE_VISIT;
    }
}

