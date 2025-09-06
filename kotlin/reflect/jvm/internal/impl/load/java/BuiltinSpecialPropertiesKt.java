package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class BuiltinSpecialPropertiesKt {
    public static final FqName access$child(FqName fqName0, String s) {
        return BuiltinSpecialPropertiesKt.child(fqName0, s);
    }

    public static final FqName access$childSafe(FqNameUnsafe fqNameUnsafe0, String s) {
        return BuiltinSpecialPropertiesKt.childSafe(fqNameUnsafe0, s);
    }

    private static final FqName child(FqName fqName0, String s) {
        FqName fqName1 = fqName0.child(Name.identifier(s));
        Intrinsics.checkNotNullExpressionValue(fqName1, "child(Name.identifier(name))");
        return fqName1;
    }

    private static final FqName childSafe(FqNameUnsafe fqNameUnsafe0, String s) {
        FqName fqName0 = fqNameUnsafe0.child(Name.identifier(s)).toSafe();
        Intrinsics.checkNotNullExpressionValue(fqName0, "child(Name.identifier(name)).toSafe()");
        return fqName0;
    }
}

