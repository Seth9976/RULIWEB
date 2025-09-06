package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

public final class TypeAttributesKt {
    public static final TypeAttributes replaceAnnotations(TypeAttributes typeAttributes0, Annotations annotations0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "<this>");
        Intrinsics.checkNotNullParameter(annotations0, "newAnnotations");
        if(AnnotationsTypeAttributeKt.getAnnotations(typeAttributes0) == annotations0) {
            return typeAttributes0;
        }
        AnnotationsTypeAttribute annotationsTypeAttribute0 = AnnotationsTypeAttributeKt.getAnnotationsAttribute(typeAttributes0);
        if(annotationsTypeAttribute0 != null) {
            TypeAttributes typeAttributes1 = typeAttributes0.remove(annotationsTypeAttribute0);
            if(typeAttributes1 != null) {
                typeAttributes0 = typeAttributes1;
            }
        }
        return annotations0.iterator().hasNext() || !annotations0.isEmpty() ? typeAttributes0.plus(new AnnotationsTypeAttribute(annotations0)) : typeAttributes0;
    }

    public static final TypeAttributes toDefaultAttributes(Annotations annotations0) {
        Intrinsics.checkNotNullParameter(annotations0, "<this>");
        return DefaultImpls.toAttributes$default(DefaultTypeAttributeTranslator.INSTANCE, annotations0, null, null, 6, null);
    }
}

