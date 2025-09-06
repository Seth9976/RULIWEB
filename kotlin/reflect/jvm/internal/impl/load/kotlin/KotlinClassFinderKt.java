package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

public final class KotlinClassFinderKt {
    public static final KotlinJvmBinaryClass findKotlinClass(KotlinClassFinder kotlinClassFinder0, JavaClass javaClass0, JvmMetadataVersion jvmMetadataVersion0) {
        Intrinsics.checkNotNullParameter(kotlinClassFinder0, "<this>");
        Intrinsics.checkNotNullParameter(javaClass0, "javaClass");
        Intrinsics.checkNotNullParameter(jvmMetadataVersion0, "jvmMetadataVersion");
        Result kotlinClassFinder$Result0 = kotlinClassFinder0.findKotlinClassOrContent(javaClass0, jvmMetadataVersion0);
        return kotlinClassFinder$Result0 == null ? null : kotlinClassFinder$Result0.toKotlinJvmBinaryClass();
    }

    public static final KotlinJvmBinaryClass findKotlinClass(KotlinClassFinder kotlinClassFinder0, ClassId classId0, JvmMetadataVersion jvmMetadataVersion0) {
        Intrinsics.checkNotNullParameter(kotlinClassFinder0, "<this>");
        Intrinsics.checkNotNullParameter(classId0, "classId");
        Intrinsics.checkNotNullParameter(jvmMetadataVersion0, "jvmMetadataVersion");
        Result kotlinClassFinder$Result0 = kotlinClassFinder0.findKotlinClassOrContent(classId0, jvmMetadataVersion0);
        return kotlinClassFinder$Result0 == null ? null : kotlinClassFinder$Result0.toKotlinJvmBinaryClass();
    }
}

