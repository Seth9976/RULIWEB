package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.text.StringsKt;

public final class ReflectKotlinClassFinderKt {
    public static final String access$toRuntimeFqName(ClassId classId0) {
        return ReflectKotlinClassFinderKt.toRuntimeFqName(classId0);
    }

    private static final String toRuntimeFqName(ClassId classId0) {
        String s = classId0.getRelativeClassName().asString();
        Intrinsics.checkNotNullExpressionValue(s, "relativeClassName.asString()");
        String s1 = StringsKt.replace$default(s, '.', '$', false, 4, null);
        return classId0.getPackageFqName().isRoot() ? s1 : classId0.getPackageFqName() + '.' + s1;
    }
}

