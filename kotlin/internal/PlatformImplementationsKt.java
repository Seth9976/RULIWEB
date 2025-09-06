package kotlin.internal;

import kotlin.Metadata;
import kotlin.internal.jdk8.JDK8PlatformImplementations;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\u001A \u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\u0005H\u0001\u001A\"\u0010\b\u001A\u0002H\t\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n2\u0006\u0010\u000B\u001A\u00020\nH\u0083\b¢\u0006\u0002\u0010\f\"\u0010\u0010\u0000\u001A\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "apiVersionIsAtLeast", "", "major", "", "minor", "patch", "castToBaseType", "T", "", "instance", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class PlatformImplementationsKt {
    public static final PlatformImplementations IMPLEMENTATIONS;

    static {
        PlatformImplementationsKt.IMPLEMENTATIONS = new JDK8PlatformImplementations();
    }

    public static final boolean apiVersionIsAtLeast(int v, int v1, int v2) [...] // 潜在的解密器

    private static final Object castToBaseType(Object object0) {
        try {
            Intrinsics.reifiedOperationMarker(1, "T");
            return object0;
        }
        catch(ClassCastException classCastException0) {
            ClassLoader classLoader0 = object0.getClass().getClassLoader();
            Intrinsics.reifiedOperationMarker(4, "T");
            ClassLoader classLoader1 = Object.class.getClassLoader();
            if(!Intrinsics.areEqual(classLoader0, classLoader1)) {
                throw new ClassNotFoundException("Instance class was loaded from a different classloader: " + classLoader0 + ", base type classloader: " + classLoader1, classCastException0);
            }
            throw classCastException0;
        }
    }
}

