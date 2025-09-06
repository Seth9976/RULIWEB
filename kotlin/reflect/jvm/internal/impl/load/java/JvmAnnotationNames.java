package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class JvmAnnotationNames {
    public static final Name DEFAULT_ANNOTATION_MEMBER_NAME;
    public static final FqName DEPRECATED_ANNOTATION;
    public static final FqName DOCUMENTED_ANNOTATION;
    public static final FqName ELEMENT_TYPE_ENUM;
    public static final FqName ENHANCED_MUTABILITY_ANNOTATION;
    public static final FqName ENHANCED_NULLABILITY_ANNOTATION;
    public static final FqName JETBRAINS_MUTABLE_ANNOTATION;
    public static final FqName JETBRAINS_NOT_NULL_ANNOTATION;
    public static final FqName JETBRAINS_NULLABLE_ANNOTATION;
    public static final FqName JETBRAINS_READONLY_ANNOTATION;
    public static final FqName KOTLIN_JVM_INTERNAL;
    public static final String METADATA_DESC;
    public static final FqName METADATA_FQ_NAME;
    public static final FqName MUTABLE_ANNOTATION;
    public static final FqName PURELY_IMPLEMENTS_ANNOTATION;
    public static final FqName READONLY_ANNOTATION;
    public static final FqName REPEATABLE_ANNOTATION;
    public static final FqName RETENTION_ANNOTATION;
    public static final FqName RETENTION_POLICY_ENUM;
    public static final String SERIALIZED_IR_DESC;
    public static final FqName SERIALIZED_IR_FQ_NAME;
    public static final FqName TARGET_ANNOTATION;

    static {
        JvmAnnotationNames.METADATA_FQ_NAME = new FqName("kotlin.Metadata");
        JvmAnnotationNames.METADATA_DESC = "Lkotlin/Metadata;";
        JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME = Name.identifier("value");
        JvmAnnotationNames.TARGET_ANNOTATION = new FqName("java.lang.annotation.Target");
        JvmAnnotationNames.ELEMENT_TYPE_ENUM = new FqName("java.lang.annotation.ElementType");
        JvmAnnotationNames.RETENTION_ANNOTATION = new FqName("java.lang.annotation.Retention");
        JvmAnnotationNames.RETENTION_POLICY_ENUM = new FqName("java.lang.annotation.RetentionPolicy");
        JvmAnnotationNames.DEPRECATED_ANNOTATION = new FqName("java.lang.Deprecated");
        JvmAnnotationNames.DOCUMENTED_ANNOTATION = new FqName("java.lang.annotation.Documented");
        JvmAnnotationNames.REPEATABLE_ANNOTATION = new FqName("java.lang.annotation.Repeatable");
        JvmAnnotationNames.JETBRAINS_NOT_NULL_ANNOTATION = new FqName("org.jetbrains.annotations.NotNull");
        JvmAnnotationNames.JETBRAINS_NULLABLE_ANNOTATION = new FqName("org.jetbrains.annotations.Nullable");
        JvmAnnotationNames.JETBRAINS_MUTABLE_ANNOTATION = new FqName("org.jetbrains.annotations.Mutable");
        JvmAnnotationNames.JETBRAINS_READONLY_ANNOTATION = new FqName("org.jetbrains.annotations.ReadOnly");
        JvmAnnotationNames.READONLY_ANNOTATION = new FqName("kotlin.annotations.jvm.ReadOnly");
        JvmAnnotationNames.MUTABLE_ANNOTATION = new FqName("kotlin.annotations.jvm.Mutable");
        JvmAnnotationNames.PURELY_IMPLEMENTS_ANNOTATION = new FqName("kotlin.jvm.PurelyImplements");
        JvmAnnotationNames.KOTLIN_JVM_INTERNAL = new FqName("kotlin.jvm.internal");
        JvmAnnotationNames.SERIALIZED_IR_FQ_NAME = new FqName("kotlin.jvm.internal.SerializedIr");
        JvmAnnotationNames.SERIALIZED_IR_DESC = "Lkotlin/jvm/internal/SerializedIr;";
        JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION = new FqName("kotlin.jvm.internal.EnhancedNullability");
        JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION = new FqName("kotlin.jvm.internal.EnhancedMutability");
    }
}

