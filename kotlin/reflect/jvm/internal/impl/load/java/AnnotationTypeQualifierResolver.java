package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

public final class AnnotationTypeQualifierResolver extends AbstractAnnotationTypeQualifierResolver {
    public AnnotationTypeQualifierResolver(JavaTypeEnhancementState javaTypeEnhancementState0) {
        Intrinsics.checkNotNullParameter(javaTypeEnhancementState0, "javaTypeEnhancementState");
        super(javaTypeEnhancementState0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver
    public Iterable enumArguments(Object object0, boolean z) {
        return this.enumArguments(((AnnotationDescriptor)object0), z);
    }

    protected Iterable enumArguments(AnnotationDescriptor annotationDescriptor0, boolean z) {
        Intrinsics.checkNotNullParameter(annotationDescriptor0, "<this>");
        Map map0 = annotationDescriptor0.getAllValueArguments();
        Collection collection0 = new ArrayList();
        for(Object object0: map0.entrySet()) {
            Name name0 = (Name)((Map.Entry)object0).getKey();
            ConstantValue constantValue0 = (ConstantValue)((Map.Entry)object0).getValue();
            CollectionsKt.addAll(collection0, (!z || Intrinsics.areEqual(name0, JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME) ? this.toEnumNames(constantValue0) : CollectionsKt.emptyList()));
        }
        return (List)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver
    public FqName getFqName(Object object0) {
        return this.getFqName(((AnnotationDescriptor)object0));
    }

    protected FqName getFqName(AnnotationDescriptor annotationDescriptor0) {
        Intrinsics.checkNotNullParameter(annotationDescriptor0, "<this>");
        return annotationDescriptor0.getFqName();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver
    public Object getKey(Object object0) {
        return this.getKey(((AnnotationDescriptor)object0));
    }

    protected Object getKey(AnnotationDescriptor annotationDescriptor0) {
        Intrinsics.checkNotNullParameter(annotationDescriptor0, "<this>");
        ClassDescriptor classDescriptor0 = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor0);
        Intrinsics.checkNotNull(classDescriptor0);
        return classDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver
    public Iterable getMetaAnnotations(Object object0) {
        return this.getMetaAnnotations(((AnnotationDescriptor)object0));
    }

    protected Iterable getMetaAnnotations(AnnotationDescriptor annotationDescriptor0) {
        Intrinsics.checkNotNullParameter(annotationDescriptor0, "<this>");
        ClassDescriptor classDescriptor0 = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor0);
        if(classDescriptor0 != null) {
            Annotations annotations0 = classDescriptor0.getAnnotations();
            if(annotations0 != null) {
                return annotations0;
            }
        }
        return CollectionsKt.emptyList();
    }

    private final List toEnumNames(ConstantValue constantValue0) {
        if(constantValue0 instanceof ArrayValue) {
            Iterable iterable0 = (Iterable)((ArrayValue)constantValue0).getValue();
            Collection collection0 = new ArrayList();
            for(Object object0: iterable0) {
                CollectionsKt.addAll(collection0, this.toEnumNames(((ConstantValue)object0)));
            }
            return (List)collection0;
        }
        return constantValue0 instanceof EnumValue ? CollectionsKt.listOf(((EnumValue)constantValue0).getEnumEntryName().getIdentifier()) : CollectionsKt.emptyList();
    }
}

