package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;

public interface TypeAliasExpansionReportStrategy {
    public static final class DO_NOTHING implements TypeAliasExpansionReportStrategy {
        public static final DO_NOTHING INSTANCE;

        static {
            DO_NOTHING.INSTANCE = new DO_NOTHING();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy
        public void boundsViolationInSubstitution(TypeSubstitutor typeSubstitutor0, KotlinType kotlinType0, KotlinType kotlinType1, TypeParameterDescriptor typeParameterDescriptor0) {
            Intrinsics.checkNotNullParameter(typeSubstitutor0, "substitutor");
            Intrinsics.checkNotNullParameter(kotlinType0, "unsubstitutedArgument");
            Intrinsics.checkNotNullParameter(kotlinType1, "argument");
            Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "typeParameter");
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy
        public void conflictingProjection(TypeAliasDescriptor typeAliasDescriptor0, TypeParameterDescriptor typeParameterDescriptor0, KotlinType kotlinType0) {
            Intrinsics.checkNotNullParameter(typeAliasDescriptor0, "typeAlias");
            Intrinsics.checkNotNullParameter(kotlinType0, "substitutedArgument");
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy
        public void recursiveTypeAlias(TypeAliasDescriptor typeAliasDescriptor0) {
            Intrinsics.checkNotNullParameter(typeAliasDescriptor0, "typeAlias");
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy
        public void repeatedAnnotation(AnnotationDescriptor annotationDescriptor0) {
            Intrinsics.checkNotNullParameter(annotationDescriptor0, "annotation");
        }
    }

    void boundsViolationInSubstitution(TypeSubstitutor arg1, KotlinType arg2, KotlinType arg3, TypeParameterDescriptor arg4);

    void conflictingProjection(TypeAliasDescriptor arg1, TypeParameterDescriptor arg2, KotlinType arg3);

    void recursiveTypeAlias(TypeAliasDescriptor arg1);

    void repeatedAnnotation(AnnotationDescriptor arg1);
}

