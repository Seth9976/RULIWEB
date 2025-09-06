package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class ConstUtil {
    public static final ConstUtil INSTANCE;

    static {
        ConstUtil.INSTANCE = new ConstUtil();
    }

    @JvmStatic
    public static final boolean canBeUsedForConstVal(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        return ConstUtilKt.canBeUsedForConstVal(kotlinType0);
    }
}

