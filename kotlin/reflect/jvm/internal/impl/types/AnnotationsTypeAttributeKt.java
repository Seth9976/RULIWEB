package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.util.NullableArrayMapAccessor;

public final class AnnotationsTypeAttributeKt {
    static final KProperty[] $$delegatedProperties;
    private static final ReadOnlyProperty annotationsAttribute$delegate;

    static {
        AnnotationsTypeAttributeKt.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinPackage(AnnotationsTypeAttributeKt.class, "descriptors"), "annotationsAttribute", "getAnnotationsAttribute(Lorg/jetbrains/kotlin/types/TypeAttributes;)Lorg/jetbrains/kotlin/types/AnnotationsTypeAttribute;"))};
        KClass kClass0 = Reflection.getOrCreateKotlinClass(AnnotationsTypeAttribute.class);
        NullableArrayMapAccessor nullableArrayMapAccessor0 = TypeAttributes.Companion.generateNullableAccessor(kClass0);
        Intrinsics.checkNotNull(nullableArrayMapAccessor0, "null cannot be cast to non-null type kotlin.properties.ReadOnlyProperty<org.jetbrains.kotlin.types.TypeAttributes, T of org.jetbrains.kotlin.types.TypeAttributes.Companion.attributeAccessor?>");
        AnnotationsTypeAttributeKt.annotationsAttribute$delegate = nullableArrayMapAccessor0;
    }

    public static final Annotations getAnnotations(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "<this>");
        AnnotationsTypeAttribute annotationsTypeAttribute0 = AnnotationsTypeAttributeKt.getAnnotationsAttribute(typeAttributes0);
        if(annotationsTypeAttribute0 != null) {
            Annotations annotations0 = annotationsTypeAttribute0.getAnnotations();
            return annotations0 == null ? Annotations.Companion.getEMPTY() : annotations0;
        }
        return Annotations.Companion.getEMPTY();
    }

    public static final AnnotationsTypeAttribute getAnnotationsAttribute(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "<this>");
        return (AnnotationsTypeAttribute)AnnotationsTypeAttributeKt.annotationsAttribute$delegate.getValue(typeAttributes0, AnnotationsTypeAttributeKt.$$delegatedProperties[0]);
    }
}

