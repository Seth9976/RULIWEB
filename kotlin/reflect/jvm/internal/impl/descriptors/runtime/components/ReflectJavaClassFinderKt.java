package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.Intrinsics;

public final class ReflectJavaClassFinderKt {
    public static final Class tryLoadClass(ClassLoader classLoader0, String s) {
        Intrinsics.checkNotNullParameter(classLoader0, "<this>");
        Intrinsics.checkNotNullParameter(s, "fqName");
        try {
            return Class.forName(s, false, classLoader0);
        }
        catch(ClassNotFoundException unused_ex) {
            return null;
        }
    }
}

