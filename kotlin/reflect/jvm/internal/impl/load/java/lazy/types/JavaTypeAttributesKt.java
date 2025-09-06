package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeUsage;

public final class JavaTypeAttributesKt {
    public static final JavaTypeAttributes toAttributes(TypeUsage typeUsage0, boolean z, boolean z1, TypeParameterDescriptor typeParameterDescriptor0) {
        Intrinsics.checkNotNullParameter(typeUsage0, "<this>");
        return typeParameterDescriptor0 == null ? new JavaTypeAttributes(typeUsage0, null, z1, z, null, null, 34, null) : new JavaTypeAttributes(typeUsage0, null, z1, z, SetsKt.setOf(typeParameterDescriptor0), null, 34, null);
    }

    public static JavaTypeAttributes toAttributes$default(TypeUsage typeUsage0, boolean z, boolean z1, TypeParameterDescriptor typeParameterDescriptor0, int v, Object object0) {
        if((v & 1) != 0) {
            z = false;
        }
        if((v & 2) != 0) {
            z1 = false;
        }
        if((v & 4) != 0) {
            typeParameterDescriptor0 = null;
        }
        return JavaTypeAttributesKt.toAttributes(typeUsage0, z, z1, typeParameterDescriptor0);
    }
}

