package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;

public final class ModalityUtilsKt {
    public static final boolean isFinalClass(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "<this>");
        return classDescriptor0.getModality() == Modality.FINAL && classDescriptor0.getKind() != ClassKind.ENUM_CLASS;
    }
}

