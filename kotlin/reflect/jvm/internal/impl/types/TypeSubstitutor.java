package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.FilteredAnnotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;
import kotlin.reflect.jvm.internal.impl.utils.ExceptionUtilsKt;

public class TypeSubstitutor {
    static final class SubstitutionException extends Exception {
        public SubstitutionException(String s) {
            super(s);
        }
    }

    static enum VarianceConflictType {
        NO_CONFLICT,
        IN_IN_OUT_POSITION,
        OUT_IN_IN_POSITION;

    }

    static final boolean $assertionsDisabled;
    public static final TypeSubstitutor EMPTY;
    private final TypeSubstitution substitution;

    private static void $$$reportNull$$$0(int v) {
        IllegalStateException illegalStateException0;
        int v1;
        String s;
        if(v == 1 || v == 2 || v == 8 || v == 34 || v == 37 || (v == 11 || v == 12 || v == 13)) {
            s = "@NotNull method %s.%s must not return null";
        }
        else {
            switch(v) {
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: {
                    s = "@NotNull method %s.%s must not return null";
                    break;
                }
                default: {
                    s = v == 29 || v == 30 || v == 0x1F || v == 0x20 || (v == 40 || v == 41 || v == 42) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
                }
            }
        }
        if(v == 1 || v == 2 || v == 8 || v == 34 || v == 37 || (v == 11 || v == 12 || v == 13)) {
            v1 = 2;
        }
        else {
            switch(v) {
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: {
                    v1 = 2;
                    break;
                }
                default: {
                    v1 = v == 29 || v == 30 || v == 0x1F || v == 0x20 || (v == 40 || v == 41 || v == 42) ? 2 : 3;
                }
            }
        }
        Object[] arr_object = new Object[v1];
        switch(v) {
            case 3: {
                arr_object[0] = "first";
                break;
            }
            case 4: {
                arr_object[0] = "second";
                break;
            }
            case 5: {
                arr_object[0] = "substitutionContext";
                break;
            }
            case 6: {
                arr_object[0] = "context";
                break;
            }
            case 9: 
            case 14: {
                arr_object[0] = "type";
                break;
            }
            case 10: 
            case 15: {
                arr_object[0] = "howThisTypeIsUsed";
                break;
            }
            case 26: {
                arr_object[0] = "originalType";
                break;
            }
            case 27: {
                arr_object[0] = "substituted";
                break;
            }
            case 18: 
            case 28: {
                arr_object[0] = "originalProjection";
                break;
            }
            case 33: {
                arr_object[0] = "annotations";
                break;
            }
            case 16: 
            case 17: 
            case 36: {
                arr_object[0] = "typeProjection";
                break;
            }
            case 35: 
            case 38: {
                arr_object[0] = "typeParameterVariance";
                break;
            }
            case 39: {
                arr_object[0] = "projectionKind";
                break;
            }
            case 1: 
            case 2: 
            case 8: 
            case 11: 
            case 12: 
            case 13: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: 
            case 29: 
            case 30: 
            case 0x1F: 
            case 0x20: 
            case 34: 
            case 37: 
            case 40: 
            case 41: 
            case 42: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor";
                break;
            }
            default: {
                arr_object[0] = "substitution";
            }
        }
        switch(v) {
            case 1: {
                arr_object[1] = "replaceWithNonApproximatingSubstitution";
                break;
            }
            case 2: {
                arr_object[1] = "replaceWithContravariantApproximatingSubstitution";
                break;
            }
            case 8: {
                arr_object[1] = "getSubstitution";
                break;
            }
            case 34: {
                arr_object[1] = "filterOutUnsafeVariance";
                break;
            }
            case 37: {
                arr_object[1] = "combine";
                break;
            }
            default: {
                if(v == 11 || v == 12 || v == 13) {
                    arr_object[1] = "safeSubstitute";
                }
                else {
                    switch(v) {
                        case 19: 
                        case 20: 
                        case 21: 
                        case 22: 
                        case 23: 
                        case 24: 
                        case 25: {
                            arr_object[1] = "unsafeSubstitute";
                            break;
                        }
                        default: {
                            if(v == 29 || v == 30 || v == 0x1F || v == 0x20) {
                                arr_object[1] = "projectedTypeForConflictedTypeWithUnsafeVariance";
                            }
                            else if(v != 40 && v != 41 && v != 42) {
                                arr_object[1] = "kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor";
                            }
                            else {
                                arr_object[1] = "combine";
                            }
                        }
                    }
                }
            }
        }
        switch(v) {
            case 3: 
            case 4: {
                arr_object[2] = "createChainedSubstitutor";
                break;
            }
            case 7: {
                arr_object[2] = "<init>";
                break;
            }
            case 9: 
            case 10: {
                arr_object[2] = "safeSubstitute";
                break;
            }
            case 14: 
            case 15: 
            case 16: {
                arr_object[2] = "substitute";
                break;
            }
            case 17: {
                arr_object[2] = "substituteWithoutApproximation";
                break;
            }
            case 18: {
                arr_object[2] = "unsafeSubstitute";
                break;
            }
            case 26: 
            case 27: 
            case 28: {
                arr_object[2] = "projectedTypeForConflictedTypeWithUnsafeVariance";
                break;
            }
            case 33: {
                arr_object[2] = "filterOutUnsafeVariance";
                break;
            }
            case 35: 
            case 36: 
            case 38: 
            case 39: {
                arr_object[2] = "combine";
                break;
            }
            case 1: 
            case 2: 
            case 8: 
            case 11: 
            case 12: 
            case 13: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: 
            case 29: 
            case 30: 
            case 0x1F: 
            case 0x20: 
            case 34: 
            case 37: 
            case 40: 
            case 41: 
            case 42: {
                break;
            }
            default: {
                arr_object[2] = "create";
            }
        }
        String s1 = String.format(s, arr_object);
        if(v == 1 || v == 2 || v == 8 || v == 34 || v == 37 || (v == 11 || v == 12 || v == 13)) {
            illegalStateException0 = new IllegalStateException(s1);
        }
        else {
            switch(v) {
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: {
                    illegalStateException0 = new IllegalStateException(s1);
                    break;
                }
                default: {
                    illegalStateException0 = v == 29 || v == 30 || v == 0x1F || v == 0x20 || (v == 40 || v == 41 || v == 42) ? new IllegalStateException(s1) : new IllegalArgumentException(s1);
                }
            }
        }
        throw illegalStateException0;
    }

    static {
        TypeSubstitutor.EMPTY = TypeSubstitutor.create(TypeSubstitution.EMPTY);
    }

    protected TypeSubstitutor(TypeSubstitution typeSubstitution0) {
        if(typeSubstitution0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(7);
        }
        super();
        this.substitution = typeSubstitution0;
    }

    private static void assertRecursionDepth(int v, TypeProjection typeProjection0, TypeSubstitution typeSubstitution0) {
        if(v > 100) {
            throw new IllegalStateException("Recursion too deep. Most likely infinite loop while substituting " + TypeSubstitutor.safeToString(typeProjection0) + "; substitution: " + TypeSubstitutor.safeToString(typeSubstitution0));
        }
    }

    public static Variance combine(Variance variance0, TypeProjection typeProjection0) {
        if(variance0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(35);
        }
        if(typeProjection0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(36);
        }
        if(typeProjection0.isStarProjection()) {
            Variance variance1 = Variance.OUT_VARIANCE;
            if(variance1 == null) {
                TypeSubstitutor.$$$reportNull$$$0(37);
            }
            return variance1;
        }
        return TypeSubstitutor.combine(variance0, typeProjection0.getProjectionKind());
    }

    public static Variance combine(Variance variance0, Variance variance1) {
        if(variance0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(38);
        }
        if(variance1 == null) {
            TypeSubstitutor.$$$reportNull$$$0(39);
        }
        if(variance0 == Variance.INVARIANT) {
            if(variance1 != null) {
                return variance1;
            }
            TypeSubstitutor.$$$reportNull$$$0(40);
            return null;
        }
        if(variance1 == Variance.INVARIANT) {
            if(variance0 == null) {
                TypeSubstitutor.$$$reportNull$$$0(41);
            }
            return variance0;
        }
        if(variance0 != variance1) {
            throw new AssertionError("Variance conflict: type parameter variance \'" + variance0 + "\' and projection kind \'" + variance1 + "\' cannot be combined");
        }
        if(variance1 == null) {
            TypeSubstitutor.$$$reportNull$$$0(42);
        }
        return variance1;
    }

    private static VarianceConflictType conflictType(Variance variance0, Variance variance1) {
        if(variance0 == Variance.IN_VARIANCE && variance1 == Variance.OUT_VARIANCE) {
            return VarianceConflictType.OUT_IN_IN_POSITION;
        }
        return variance0 != Variance.OUT_VARIANCE || variance1 != Variance.IN_VARIANCE ? VarianceConflictType.NO_CONFLICT : VarianceConflictType.IN_IN_OUT_POSITION;
    }

    public static TypeSubstitutor create(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(6);
        }
        return TypeSubstitutor.create(TypeConstructorSubstitution.create(kotlinType0.getConstructor(), kotlinType0.getArguments()));
    }

    public static TypeSubstitutor create(TypeSubstitution typeSubstitution0) {
        if(typeSubstitution0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(0);
        }
        return new TypeSubstitutor(typeSubstitution0);
    }

    public static TypeSubstitutor createChainedSubstitutor(TypeSubstitution typeSubstitution0, TypeSubstitution typeSubstitution1) {
        if(typeSubstitution0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(3);
        }
        if(typeSubstitution1 == null) {
            TypeSubstitutor.$$$reportNull$$$0(4);
        }
        return TypeSubstitutor.create(DisjointKeysUnionTypeSubstitution.create(typeSubstitution0, typeSubstitution1));
    }

    private static Annotations filterOutUnsafeVariance(Annotations annotations0) {
        if(annotations0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(33);
        }
        return !annotations0.hasAnnotation(FqNames.unsafeVariance) ? annotations0 : new FilteredAnnotations(annotations0, new Function1() {
            // 去混淆评级： 低(20)
            private static void $$$reportNull$$$0(int v) {
                throw new IllegalArgumentException("Argument for @NotNull parameter \'name\' of kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor$1.invoke must not be null");
            }

            public Boolean invoke(FqName fqName0) {
                if(fqName0 == null) {
                    kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.1.$$$reportNull$$$0(0);
                }
                return Boolean.valueOf(!fqName0.equals(FqNames.unsafeVariance));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((FqName)object0));
            }
        });
    }

    public TypeSubstitution getSubstitution() {
        TypeSubstitution typeSubstitution0 = this.substitution;
        if(typeSubstitution0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(8);
        }
        return typeSubstitution0;
    }

    public boolean isEmpty() {
        return this.substitution.isEmpty();
    }

    private static TypeProjection projectedTypeForConflictedTypeWithUnsafeVariance(KotlinType kotlinType0, TypeProjection typeProjection0, TypeParameterDescriptor typeParameterDescriptor0, TypeProjection typeProjection1) {
        if(kotlinType0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(26);
        }
        if(typeProjection0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(27);
        }
        if(typeProjection1 == null) {
            TypeSubstitutor.$$$reportNull$$$0(28);
        }
        if(kotlinType0.getAnnotations().hasAnnotation(FqNames.unsafeVariance)) {
            TypeConstructor typeConstructor0 = typeProjection0.getType().getConstructor();
            if(typeConstructor0 instanceof NewCapturedTypeConstructor) {
                TypeProjection typeProjection2 = ((NewCapturedTypeConstructor)typeConstructor0).getProjection();
                Variance variance0 = typeProjection2.getProjectionKind();
                if(TypeSubstitutor.conflictType(typeProjection1.getProjectionKind(), variance0) == VarianceConflictType.OUT_IN_IN_POSITION) {
                    return new TypeProjectionImpl(typeProjection2.getType());
                }
                if(typeParameterDescriptor0 != null && TypeSubstitutor.conflictType(typeParameterDescriptor0.getVariance(), variance0) == VarianceConflictType.OUT_IN_IN_POSITION) {
                    return new TypeProjectionImpl(typeProjection2.getType());
                }
            }
        }
        else if(typeProjection0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(29);
            return null;
        }
        return typeProjection0;
    }

    // 去混淆评级： 低(20)
    public TypeSubstitutor replaceWithNonApproximatingSubstitution() {
        return !(this.substitution instanceof IndexedParametersSubstitution) || !this.substitution.approximateContravariantCapturedTypes() ? this : new TypeSubstitutor(new IndexedParametersSubstitution(((IndexedParametersSubstitution)this.substitution).getParameters(), ((IndexedParametersSubstitution)this.substitution).getArguments(), false));
    }

    public KotlinType safeSubstitute(KotlinType kotlinType0, Variance variance0) {
        KotlinType kotlinType1;
        if(kotlinType0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(9);
        }
        if(variance0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(10);
        }
        if(this.isEmpty()) {
            if(kotlinType0 == null) {
                TypeSubstitutor.$$$reportNull$$$0(11);
            }
            return kotlinType0;
        }
        try {
            kotlinType1 = this.unsafeSubstitute(new TypeProjectionImpl(variance0, kotlinType0), null, 0).getType();
        }
        catch(SubstitutionException typeSubstitutor$SubstitutionException0) {
            KotlinType kotlinType2 = ErrorUtils.createErrorType(ErrorTypeKind.UNABLE_TO_SUBSTITUTE_TYPE, new String[]{typeSubstitutor$SubstitutionException0.getMessage()});
            if(kotlinType2 == null) {
                TypeSubstitutor.$$$reportNull$$$0(13);
            }
            return kotlinType2;
        }
        if(kotlinType1 == null) {
            TypeSubstitutor.$$$reportNull$$$0(12);
        }
        return kotlinType1;
    }

    private static String safeToString(Object object0) {
        try {
            return object0.toString();
        }
        catch(Throwable throwable0) {
            if(ExceptionUtilsKt.isProcessCanceledException(throwable0)) {
                throw (RuntimeException)throwable0;
            }
            return "[Exception while computing toString(): " + throwable0 + "]";
        }
    }

    public KotlinType substitute(KotlinType kotlinType0, Variance variance0) {
        if(kotlinType0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(14);
        }
        if(variance0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(15);
        }
        TypeProjection typeProjection0 = this.substitute(new TypeProjectionImpl(variance0, this.getSubstitution().prepareTopLevelType(kotlinType0, variance0)));
        return typeProjection0 == null ? null : typeProjection0.getType();
    }

    public TypeProjection substitute(TypeProjection typeProjection0) {
        if(typeProjection0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(16);
        }
        TypeProjection typeProjection1 = this.substituteWithoutApproximation(typeProjection0);
        return this.substitution.approximateCapturedTypes() || this.substitution.approximateContravariantCapturedTypes() ? CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary(typeProjection1, this.substitution.approximateContravariantCapturedTypes()) : typeProjection1;
    }

    private TypeProjection substituteCompoundType(TypeProjection typeProjection0, int v) throws SubstitutionException {
        KotlinType kotlinType0 = typeProjection0.getType();
        Variance variance0 = typeProjection0.getProjectionKind();
        if(kotlinType0.getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor) {
            return typeProjection0;
        }
        SimpleType simpleType0 = SpecialTypesKt.getAbbreviation(kotlinType0);
        KotlinType kotlinType1 = simpleType0 == null ? null : this.replaceWithNonApproximatingSubstitution().substitute(simpleType0, Variance.INVARIANT);
        List list0 = this.substituteTypeArguments(kotlinType0.getConstructor().getParameters(), kotlinType0.getArguments(), v);
        Annotations annotations0 = kotlinType0.getAnnotations();
        KotlinType kotlinType2 = TypeSubstitutionKt.replace(kotlinType0, list0, this.substitution.filterAnnotations(annotations0));
        if(kotlinType2 instanceof SimpleType && kotlinType1 instanceof SimpleType) {
            kotlinType2 = SpecialTypesKt.withAbbreviation(((SimpleType)kotlinType2), ((SimpleType)kotlinType1));
        }
        return new TypeProjectionImpl(variance0, kotlinType2);
    }

    private List substituteTypeArguments(List list0, List list1, int v) throws SubstitutionException {
        List list2 = new ArrayList(list0.size());
        boolean z = false;
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)list0.get(v1);
            TypeProjection typeProjection0 = (TypeProjection)list1.get(v1);
            TypeProjection typeProjection1 = this.unsafeSubstitute(typeProjection0, typeParameterDescriptor0, v + 1);
            switch(kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.2.$SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType[TypeSubstitutor.conflictType(typeParameterDescriptor0.getVariance(), typeProjection1.getProjectionKind()).ordinal()]) {
                case 1: 
                case 2: {
                    typeProjection1 = TypeUtils.makeStarProjection(typeParameterDescriptor0);
                    break;
                }
                case 3: {
                    if(typeParameterDescriptor0.getVariance() != Variance.INVARIANT && !typeProjection1.isStarProjection()) {
                        KotlinType kotlinType0 = typeProjection1.getType();
                        typeProjection1 = new TypeProjectionImpl(Variance.INVARIANT, kotlinType0);
                    }
                }
            }
            if(typeProjection1 != typeProjection0) {
                z = true;
            }
            list2.add(typeProjection1);
        }
        return z ? list2 : list1;
    }

    public TypeProjection substituteWithoutApproximation(TypeProjection typeProjection0) {
        if(typeProjection0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(17);
        }
        if(this.isEmpty()) {
            return typeProjection0;
        }
        try {
            return this.unsafeSubstitute(typeProjection0, null, 0);
        }
        catch(SubstitutionException unused_ex) {
            return null;
        }
    }

    private TypeProjection unsafeSubstitute(TypeProjection typeProjection0, TypeParameterDescriptor typeParameterDescriptor0, int v) throws SubstitutionException {
        if(typeProjection0 == null) {
            TypeSubstitutor.$$$reportNull$$$0(18);
        }
        TypeSubstitutor.assertRecursionDepth(v, typeProjection0, this.substitution);
        if(!typeProjection0.isStarProjection()) {
            KotlinType kotlinType0 = typeProjection0.getType();
            if(kotlinType0 instanceof TypeWithEnhancement) {
                UnwrappedType unwrappedType0 = ((TypeWithEnhancement)kotlinType0).getOrigin();
                KotlinType kotlinType1 = ((TypeWithEnhancement)kotlinType0).getEnhancement();
                TypeProjection typeProjection1 = this.unsafeSubstitute(new TypeProjectionImpl(typeProjection0.getProjectionKind(), unwrappedType0), typeParameterDescriptor0, v + 1);
                if(typeProjection1.isStarProjection()) {
                    return typeProjection1;
                }
                KotlinType kotlinType2 = this.substitute(kotlinType1, typeProjection0.getProjectionKind());
                UnwrappedType unwrappedType1 = TypeWithEnhancementKt.wrapEnhancement(typeProjection1.getType().unwrap(), kotlinType2);
                return new TypeProjectionImpl(typeProjection1.getProjectionKind(), unwrappedType1);
            }
            if(!DynamicTypesKt.isDynamic(kotlinType0) && !(kotlinType0.unwrap() instanceof RawType)) {
                TypeProjection typeProjection2 = this.substitution.get(kotlinType0);
                TypeProjection typeProjection3 = typeProjection2 == null ? null : TypeSubstitutor.projectedTypeForConflictedTypeWithUnsafeVariance(kotlinType0, typeProjection2, typeParameterDescriptor0, typeProjection0);
                Variance variance0 = typeProjection0.getProjectionKind();
                if(typeProjection3 == null && FlexibleTypesKt.isFlexible(kotlinType0) && !TypeCapabilitiesKt.isCustomTypeParameter(kotlinType0)) {
                    FlexibleType flexibleType0 = FlexibleTypesKt.asFlexibleType(kotlinType0);
                    TypeProjection typeProjection4 = this.unsafeSubstitute(new TypeProjectionImpl(variance0, flexibleType0.getLowerBound()), typeParameterDescriptor0, v + 1);
                    TypeProjection typeProjection5 = this.unsafeSubstitute(new TypeProjectionImpl(variance0, flexibleType0.getUpperBound()), typeParameterDescriptor0, v + 1);
                    Variance variance1 = typeProjection4.getProjectionKind();
                    if(typeProjection4.getType() != flexibleType0.getLowerBound() || typeProjection5.getType() != flexibleType0.getUpperBound()) {
                        return new TypeProjectionImpl(variance1, KotlinTypeFactory.flexibleType(TypeSubstitutionKt.asSimpleType(typeProjection4.getType()), TypeSubstitutionKt.asSimpleType(typeProjection5.getType())));
                    }
                }
                else if(!KotlinBuiltIns.isNothing(kotlinType0) && !KotlinTypeKt.isError(kotlinType0)) {
                    if(typeProjection3 != null) {
                        VarianceConflictType typeSubstitutor$VarianceConflictType0 = TypeSubstitutor.conflictType(variance0, typeProjection3.getProjectionKind());
                        if(!CapturedTypeConstructorKt.isCaptured(kotlinType0)) {
                            switch(kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.2.$SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType[typeSubstitutor$VarianceConflictType0.ordinal()]) {
                                case 1: {
                                    throw new SubstitutionException("Out-projection in in-position");
                                }
                                case 2: {
                                    SimpleType simpleType0 = kotlinType0.getConstructor().getBuiltIns().getNullableAnyType();
                                    return new TypeProjectionImpl(Variance.OUT_VARIANCE, simpleType0);
                                }
                            }
                        }
                        CustomTypeParameter customTypeParameter0 = TypeCapabilitiesKt.getCustomTypeParameter(kotlinType0);
                        if(typeProjection3.isStarProjection()) {
                            return typeProjection3;
                        }
                        KotlinType kotlinType3 = customTypeParameter0 == null ? TypeUtils.makeNullableIfNeeded(typeProjection3.getType(), kotlinType0.isMarkedNullable()) : customTypeParameter0.substitutionResult(typeProjection3.getType());
                        if(!kotlinType0.getAnnotations().isEmpty()) {
                            Annotations annotations0 = kotlinType0.getAnnotations();
                            Annotations annotations1 = TypeSubstitutor.filterOutUnsafeVariance(this.substitution.filterAnnotations(annotations0));
                            kotlinType3 = TypeUtilsKt.replaceAnnotations(kotlinType3, new CompositeAnnotations(new Annotations[]{kotlinType3.getAnnotations(), annotations1}));
                        }
                        if(typeSubstitutor$VarianceConflictType0 == VarianceConflictType.NO_CONFLICT) {
                            variance0 = TypeSubstitutor.combine(variance0, typeProjection3.getProjectionKind());
                        }
                        return new TypeProjectionImpl(variance0, kotlinType3);
                    }
                    TypeProjection typeProjection6 = this.substituteCompoundType(typeProjection0, v);
                    if(typeProjection6 == null) {
                        TypeSubstitutor.$$$reportNull$$$0(25);
                    }
                    return typeProjection6;
                }
            }
        }
        return typeProjection0;
    }

    class kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.2 {
        static final int[] $SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType;

        static {
            int[] arr_v = new int[VarianceConflictType.values().length];
            kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.2.$SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType = arr_v;
            try {
                arr_v[VarianceConflictType.OUT_IN_IN_POSITION.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.2.$SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType[VarianceConflictType.IN_IN_OUT_POSITION.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor.2.$SwitchMap$org$jetbrains$kotlin$types$TypeSubstitutor$VarianceConflictType[VarianceConflictType.NO_CONFLICT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

