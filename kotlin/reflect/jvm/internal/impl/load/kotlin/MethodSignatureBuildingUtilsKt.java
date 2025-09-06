package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;

public final class MethodSignatureBuildingUtilsKt {
    public static final String signature(SignatureBuildingComponents signatureBuildingComponents0, ClassDescriptor classDescriptor0, String s) {
        Intrinsics.checkNotNullParameter(signatureBuildingComponents0, "<this>");
        Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
        Intrinsics.checkNotNullParameter(s, "jvmDescriptor");
        return signatureBuildingComponents0.signature(MethodSignatureMappingKt.getInternalName(classDescriptor0), s);
    }
}

