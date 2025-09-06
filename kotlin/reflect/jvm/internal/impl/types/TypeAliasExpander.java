package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class TypeAliasExpander {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public static final void access$assertRecursionDepth(Companion typeAliasExpander$Companion0, int v, TypeAliasDescriptor typeAliasDescriptor0) {
            typeAliasExpander$Companion0.assertRecursionDepth(v, typeAliasDescriptor0);
        }

        private final void assertRecursionDepth(int v, TypeAliasDescriptor typeAliasDescriptor0) {
            if(v > 100) {
                throw new AssertionError("Too deep recursion while expanding type alias " + typeAliasDescriptor0.getName());
            }
        }
    }

    public static final Companion Companion;
    private static final TypeAliasExpander NON_REPORTING;
    private final TypeAliasExpansionReportStrategy reportStrategy;
    private final boolean shouldCheckBounds;

    static {
        TypeAliasExpander.Companion = new Companion(null);
        TypeAliasExpander.NON_REPORTING = new TypeAliasExpander(DO_NOTHING.INSTANCE, false);
    }

    public TypeAliasExpander(TypeAliasExpansionReportStrategy typeAliasExpansionReportStrategy0, boolean z) {
        Intrinsics.checkNotNullParameter(typeAliasExpansionReportStrategy0, "reportStrategy");
        super();
        this.reportStrategy = typeAliasExpansionReportStrategy0;
        this.shouldCheckBounds = z;
    }

    private final void checkRepeatedAnnotations(Annotations annotations0, Annotations annotations1) {
        Collection collection0 = new HashSet();
        for(Object object0: annotations0) {
            collection0.add(((AnnotationDescriptor)object0).getFqName());
        }
        for(Object object1: annotations1) {
            AnnotationDescriptor annotationDescriptor0 = (AnnotationDescriptor)object1;
            if(((HashSet)collection0).contains(annotationDescriptor0.getFqName())) {
                this.reportStrategy.repeatedAnnotation(annotationDescriptor0);
            }
        }
    }

    private final void checkTypeArgumentsSubstitution(KotlinType kotlinType0, KotlinType kotlinType1) {
        TypeSubstitutor typeSubstitutor0 = TypeSubstitutor.create(kotlinType1);
        Intrinsics.checkNotNullExpressionValue(typeSubstitutor0, "create(substitutedType)");
        int v = 0;
        for(Object object0: kotlinType1.getArguments()) {
            if(v < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if(!((TypeProjection)object0).isStarProjection()) {
                KotlinType kotlinType2 = ((TypeProjection)object0).getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType2, "substitutedArgument.type");
                if(!TypeUtilsKt.containsTypeAliasParameters(kotlinType2)) {
                    TypeProjection typeProjection0 = (TypeProjection)kotlinType0.getArguments().get(v);
                    TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)kotlinType0.getConstructor().getParameters().get(v);
                    if(this.shouldCheckBounds) {
                        KotlinType kotlinType3 = typeProjection0.getType();
                        Intrinsics.checkNotNullExpressionValue(kotlinType3, "unsubstitutedArgument.type");
                        KotlinType kotlinType4 = ((TypeProjection)object0).getType();
                        Intrinsics.checkNotNullExpressionValue(kotlinType4, "substitutedArgument.type");
                        Intrinsics.checkNotNullExpressionValue(typeParameterDescriptor0, "typeParameter");
                        this.reportStrategy.boundsViolationInSubstitution(typeSubstitutor0, kotlinType3, kotlinType4, typeParameterDescriptor0);
                    }
                }
            }
            ++v;
        }
    }

    private final DynamicType combineAttributes(DynamicType dynamicType0, TypeAttributes typeAttributes0) {
        return dynamicType0.replaceAttributes(this.createdCombinedAttributes(dynamicType0, typeAttributes0));
    }

    // 去混淆评级： 低(20)
    private final SimpleType combineAttributes(SimpleType simpleType0, TypeAttributes typeAttributes0) {
        return KotlinTypeKt.isError(simpleType0) ? simpleType0 : TypeSubstitutionKt.replace$default(simpleType0, null, this.createdCombinedAttributes(simpleType0, typeAttributes0), 1, null);
    }

    private final SimpleType combineNullability(SimpleType simpleType0, KotlinType kotlinType0) {
        SimpleType simpleType1 = TypeUtils.makeNullableIfNeeded(simpleType0, kotlinType0.isMarkedNullable());
        Intrinsics.checkNotNullExpressionValue(simpleType1, "makeNullableIfNeeded(thi…romType.isMarkedNullable)");
        return simpleType1;
    }

    private final SimpleType combineNullabilityAndAnnotations(SimpleType simpleType0, KotlinType kotlinType0) {
        return this.combineAttributes(this.combineNullability(simpleType0, kotlinType0), kotlinType0.getAttributes());
    }

    private final SimpleType createAbbreviation(TypeAliasExpansion typeAliasExpansion0, TypeAttributes typeAttributes0, boolean z) {
        TypeConstructor typeConstructor0 = typeAliasExpansion0.getDescriptor().getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor0, "descriptor.typeConstructor");
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(typeAttributes0, typeConstructor0, typeAliasExpansion0.getArguments(), z, Empty.INSTANCE);
    }

    // 去混淆评级： 低(20)
    private final TypeAttributes createdCombinedAttributes(KotlinType kotlinType0, TypeAttributes typeAttributes0) {
        return KotlinTypeKt.isError(kotlinType0) ? kotlinType0.getAttributes() : typeAttributes0.add(kotlinType0.getAttributes());
    }

    public final SimpleType expand(TypeAliasExpansion typeAliasExpansion0, TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAliasExpansion0, "typeAliasExpansion");
        Intrinsics.checkNotNullParameter(typeAttributes0, "attributes");
        return this.expandRecursively(typeAliasExpansion0, typeAttributes0, false, 0, true);
    }

    private final TypeProjection expandNonArgumentTypeProjection(TypeProjection typeProjection0, TypeAliasExpansion typeAliasExpansion0, int v) {
        KotlinType kotlinType0 = typeProjection0.getType().unwrap();
        if(!DynamicTypesKt.isDynamic(kotlinType0)) {
            SimpleType simpleType0 = TypeSubstitutionKt.asSimpleType(kotlinType0);
            if(!KotlinTypeKt.isError(simpleType0) && TypeUtilsKt.requiresTypeAliasExpansion(simpleType0)) {
                TypeConstructor typeConstructor0 = simpleType0.getConstructor();
                ClassifierDescriptor classifierDescriptor0 = typeConstructor0.getDeclarationDescriptor();
                typeConstructor0.getParameters().size();
                simpleType0.getArguments().size();
                if(!(classifierDescriptor0 instanceof TypeParameterDescriptor)) {
                    if(classifierDescriptor0 instanceof TypeAliasDescriptor) {
                        if(typeAliasExpansion0.isRecursion(((TypeAliasDescriptor)classifierDescriptor0))) {
                            this.reportStrategy.recursiveTypeAlias(((TypeAliasDescriptor)classifierDescriptor0));
                            String s = ((TypeAliasDescriptor)classifierDescriptor0).getName().toString();
                            Intrinsics.checkNotNullExpressionValue(s, "typeDescriptor.name.toString()");
                            KotlinType kotlinType1 = ErrorUtils.createErrorType(ErrorTypeKind.RECURSIVE_TYPE_ALIAS, new String[]{s});
                            return new TypeProjectionImpl(Variance.INVARIANT, kotlinType1);
                        }
                        Iterable iterable0 = simpleType0.getArguments();
                        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                        int v1 = 0;
                        for(Object object0: iterable0) {
                            if(v1 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            arrayList0.add(this.expandTypeProjection(((TypeProjection)object0), typeAliasExpansion0, ((TypeParameterDescriptor)typeConstructor0.getParameters().get(v1)), v + 1));
                            ++v1;
                        }
                        SimpleType simpleType1 = this.expandRecursively(TypeAliasExpansion.Companion.create(typeAliasExpansion0, ((TypeAliasDescriptor)classifierDescriptor0), arrayList0), simpleType0.getAttributes(), simpleType0.isMarkedNullable(), v + 1, false);
                        SimpleType simpleType2 = this.substituteArguments(simpleType0, typeAliasExpansion0, v);
                        if(!DynamicTypesKt.isDynamic(simpleType1)) {
                            simpleType1 = SpecialTypesKt.withAbbreviation(simpleType1, simpleType2);
                        }
                        return new TypeProjectionImpl(typeProjection0.getProjectionKind(), simpleType1);
                    }
                    KotlinType kotlinType2 = this.substituteArguments(simpleType0, typeAliasExpansion0, v);
                    this.checkTypeArgumentsSubstitution(simpleType0, kotlinType2);
                    return new TypeProjectionImpl(typeProjection0.getProjectionKind(), kotlinType2);
                }
            }
        }
        return typeProjection0;
    }

    private final SimpleType expandRecursively(TypeAliasExpansion typeAliasExpansion0, TypeAttributes typeAttributes0, boolean z, int v, boolean z1) {
        KotlinType kotlinType0 = typeAliasExpansion0.getDescriptor().getUnderlyingType();
        TypeProjection typeProjection0 = this.expandTypeProjection(new TypeProjectionImpl(Variance.INVARIANT, kotlinType0), typeAliasExpansion0, null, v);
        KotlinType kotlinType1 = typeProjection0.getType();
        Intrinsics.checkNotNullExpressionValue(kotlinType1, "expandedProjection.type");
        SimpleType simpleType0 = TypeSubstitutionKt.asSimpleType(kotlinType1);
        if(KotlinTypeKt.isError(simpleType0)) {
            return simpleType0;
        }
        typeProjection0.getProjectionKind();
        this.checkRepeatedAnnotations(simpleType0.getAnnotations(), AnnotationsTypeAttributeKt.getAnnotations(typeAttributes0));
        SimpleType simpleType1 = TypeUtils.makeNullableIfNeeded(this.combineAttributes(simpleType0, typeAttributes0), z);
        Intrinsics.checkNotNullExpressionValue(simpleType1, "expandedType.combineAttr…fNeeded(it, isNullable) }");
        return z1 ? SpecialTypesKt.withAbbreviation(simpleType1, this.createAbbreviation(typeAliasExpansion0, typeAttributes0, z)) : simpleType1;
    }

    private final TypeProjection expandTypeProjection(TypeProjection typeProjection0, TypeAliasExpansion typeAliasExpansion0, TypeParameterDescriptor typeParameterDescriptor0, int v) {
        Variance variance2;
        Companion.access$assertRecursionDepth(TypeAliasExpander.Companion, v, typeAliasExpansion0.getDescriptor());
        if(typeProjection0.isStarProjection()) {
            Intrinsics.checkNotNull(typeParameterDescriptor0);
            TypeProjection typeProjection1 = TypeUtils.makeStarProjection(typeParameterDescriptor0);
            Intrinsics.checkNotNullExpressionValue(typeProjection1, "makeStarProjection(typeParameterDescriptor!!)");
            return typeProjection1;
        }
        KotlinType kotlinType0 = typeProjection0.getType();
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "underlyingProjection.type");
        TypeProjection typeProjection2 = typeAliasExpansion0.getReplacement(kotlinType0.getConstructor());
        if(typeProjection2 == null) {
            return this.expandNonArgumentTypeProjection(typeProjection0, typeAliasExpansion0, v);
        }
        if(typeProjection2.isStarProjection()) {
            Intrinsics.checkNotNull(typeParameterDescriptor0);
            TypeProjection typeProjection3 = TypeUtils.makeStarProjection(typeParameterDescriptor0);
            Intrinsics.checkNotNullExpressionValue(typeProjection3, "makeStarProjection(typeParameterDescriptor!!)");
            return typeProjection3;
        }
        UnwrappedType unwrappedType0 = typeProjection2.getType().unwrap();
        Variance variance0 = typeProjection2.getProjectionKind();
        Intrinsics.checkNotNullExpressionValue(variance0, "argument.projectionKind");
        Variance variance1 = typeProjection0.getProjectionKind();
        Intrinsics.checkNotNullExpressionValue(variance1, "underlyingProjection.projectionKind");
        if(variance1 != variance0 && variance1 != Variance.INVARIANT) {
            if(variance0 == Variance.INVARIANT) {
                variance0 = variance1;
            }
            else {
                this.reportStrategy.conflictingProjection(typeAliasExpansion0.getDescriptor(), typeParameterDescriptor0, unwrappedType0);
            }
        }
        if(typeParameterDescriptor0 == null) {
            variance2 = Variance.INVARIANT;
        }
        else {
            variance2 = typeParameterDescriptor0.getVariance();
            if(variance2 == null) {
                variance2 = Variance.INVARIANT;
            }
        }
        Intrinsics.checkNotNullExpressionValue(variance2, "typeParameterDescriptor?…nce ?: Variance.INVARIANT");
        if(variance2 != variance0 && variance2 != Variance.INVARIANT) {
            if(variance0 == Variance.INVARIANT) {
                variance0 = Variance.INVARIANT;
            }
            else {
                this.reportStrategy.conflictingProjection(typeAliasExpansion0.getDescriptor(), typeParameterDescriptor0, unwrappedType0);
            }
        }
        this.checkRepeatedAnnotations(kotlinType0.getAnnotations(), unwrappedType0.getAnnotations());
        return unwrappedType0 instanceof DynamicType ? new TypeProjectionImpl(variance0, this.combineAttributes(((DynamicType)unwrappedType0), kotlinType0.getAttributes())) : new TypeProjectionImpl(variance0, this.combineNullabilityAndAnnotations(TypeSubstitutionKt.asSimpleType(unwrappedType0), kotlinType0));
    }

    private final SimpleType substituteArguments(SimpleType simpleType0, TypeAliasExpansion typeAliasExpansion0, int v) {
        TypeConstructor typeConstructor0 = simpleType0.getConstructor();
        Iterable iterable0 = simpleType0.getArguments();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        int v1 = 0;
        for(Object object0: iterable0) {
            if(v1 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TypeProjection typeProjection0 = this.expandTypeProjection(((TypeProjection)object0), typeAliasExpansion0, ((TypeParameterDescriptor)typeConstructor0.getParameters().get(v1)), v + 1);
            if(!typeProjection0.isStarProjection()) {
                typeProjection0 = new TypeProjectionImpl(typeProjection0.getProjectionKind(), TypeUtils.makeNullableIfNeeded(typeProjection0.getType(), ((TypeProjection)object0).getType().isMarkedNullable()));
            }
            arrayList0.add(typeProjection0);
            ++v1;
        }
        return TypeSubstitutionKt.replace$default(simpleType0, arrayList0, null, 2, null);
    }
}

