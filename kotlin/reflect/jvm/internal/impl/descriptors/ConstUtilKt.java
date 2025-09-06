package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.UnsignedTypes;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

public final class ConstUtilKt {
    // 去混淆评级： 低(20)
    public static final boolean canBeUsedForConstVal(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return (KotlinBuiltIns.isPrimitiveType(kotlinType0) || UnsignedTypes.isUnsignedType(kotlinType0)) && !TypeUtils.isNullableType(kotlinType0) || KotlinBuiltIns.isString(kotlinType0);
    }
}

