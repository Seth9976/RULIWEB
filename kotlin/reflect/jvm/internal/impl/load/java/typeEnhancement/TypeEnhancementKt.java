package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMapper;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.checker.SimpleClassicTypeSystemContext;

public final class TypeEnhancementKt {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[NullabilityQualifier.values().length];
            try {
                arr_v[NullabilityQualifier.NULLABLE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[NullabilityQualifier.NOT_NULL.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private static final EnhancedTypeAnnotations ENHANCED_MUTABILITY_ANNOTATIONS;
    private static final Annotations ENHANCED_NULLABILITY_ANNOTATIONS;

    static {
        Intrinsics.checkNotNullExpressionValue(JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION, "ENHANCED_NULLABILITY_ANNOTATION");
        TypeEnhancementKt.ENHANCED_NULLABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION);
        Intrinsics.checkNotNullExpressionValue(JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION, "ENHANCED_MUTABILITY_ANNOTATION");
        TypeEnhancementKt.ENHANCED_MUTABILITY_ANNOTATIONS = new EnhancedTypeAnnotations(JvmAnnotationNames.ENHANCED_MUTABILITY_ANNOTATION);
    }

    public static final Annotations access$compositeAnnotationsOrSingle(List list0) {
        return TypeEnhancementKt.compositeAnnotationsOrSingle(list0);
    }

    public static final ClassifierDescriptor access$enhanceMutability(ClassifierDescriptor classifierDescriptor0, JavaTypeQualifiers javaTypeQualifiers0, TypeComponentPosition typeComponentPosition0) {
        return TypeEnhancementKt.enhanceMutability(classifierDescriptor0, javaTypeQualifiers0, typeComponentPosition0);
    }

    public static final EnhancedTypeAnnotations access$getENHANCED_MUTABILITY_ANNOTATIONS$p() {
        return TypeEnhancementKt.ENHANCED_MUTABILITY_ANNOTATIONS;
    }

    public static final Boolean access$getEnhancedNullability(JavaTypeQualifiers javaTypeQualifiers0, TypeComponentPosition typeComponentPosition0) {
        return TypeEnhancementKt.getEnhancedNullability(javaTypeQualifiers0, typeComponentPosition0);
    }

    private static final Annotations compositeAnnotationsOrSingle(List list0) {
        switch(list0.size()) {
            case 0: {
                throw new IllegalStateException("At least one Annotations object expected");
            }
            case 1: {
                return (Annotations)CollectionsKt.single(list0);
            }
            default: {
                return new CompositeAnnotations(CollectionsKt.toList(list0));
            }
        }
    }

    private static final ClassifierDescriptor enhanceMutability(ClassifierDescriptor classifierDescriptor0, JavaTypeQualifiers javaTypeQualifiers0, TypeComponentPosition typeComponentPosition0) {
        JavaToKotlinClassMapper javaToKotlinClassMapper0 = JavaToKotlinClassMapper.INSTANCE;
        if(!TypeComponentPositionKt.shouldEnhance(typeComponentPosition0)) {
            return null;
        }
        if(!(classifierDescriptor0 instanceof ClassDescriptor)) {
            return null;
        }
        if(javaTypeQualifiers0.getMutability() == MutabilityQualifier.READ_ONLY && typeComponentPosition0 == TypeComponentPosition.FLEXIBLE_LOWER && javaToKotlinClassMapper0.isMutable(((ClassDescriptor)classifierDescriptor0))) {
            return javaToKotlinClassMapper0.convertMutableToReadOnly(((ClassDescriptor)classifierDescriptor0));
        }
        return javaTypeQualifiers0.getMutability() == MutabilityQualifier.MUTABLE && typeComponentPosition0 == TypeComponentPosition.FLEXIBLE_UPPER && javaToKotlinClassMapper0.isReadOnly(((ClassDescriptor)classifierDescriptor0)) ? javaToKotlinClassMapper0.convertReadOnlyToMutable(((ClassDescriptor)classifierDescriptor0)) : null;
    }

    public static final Annotations getENHANCED_NULLABILITY_ANNOTATIONS() {
        return TypeEnhancementKt.ENHANCED_NULLABILITY_ANNOTATIONS;
    }

    private static final Boolean getEnhancedNullability(JavaTypeQualifiers javaTypeQualifiers0, TypeComponentPosition typeComponentPosition0) {
        if(!TypeComponentPositionKt.shouldEnhance(typeComponentPosition0)) {
            return null;
        }
        NullabilityQualifier nullabilityQualifier0 = javaTypeQualifiers0.getNullability();
        switch((nullabilityQualifier0 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[nullabilityQualifier0.ordinal()])) {
            case 1: {
                return true;
            }
            case 2: {
                return false;
            }
            default: {
                return null;
            }
        }
    }

    public static final boolean hasEnhancedNullability(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return TypeEnhancementUtilsKt.hasEnhancedNullability(SimpleClassicTypeSystemContext.INSTANCE, kotlinType0);
    }
}

