package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.EnumMap;
import kotlin.jvm.internal.Intrinsics;

public final class JavaTypeQualifiersByElementType {
    private final EnumMap defaultQualifiers;

    public JavaTypeQualifiersByElementType(EnumMap enumMap0) {
        Intrinsics.checkNotNullParameter(enumMap0, "defaultQualifiers");
        super();
        this.defaultQualifiers = enumMap0;
    }

    public final JavaDefaultQualifiers get(AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType0) {
        return (JavaDefaultQualifiers)this.defaultQualifiers.get(annotationQualifierApplicabilityType0);
    }

    public final EnumMap getDefaultQualifiers() {
        return this.defaultQualifiers;
    }
}

