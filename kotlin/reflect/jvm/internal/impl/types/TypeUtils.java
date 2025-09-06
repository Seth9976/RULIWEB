package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

public class TypeUtils {
    public static class SpecialType extends DelegatingSimpleType {
        private final String name;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[(v == 1 || v == 4 ? 2 : 3)];
            switch(v) {
                case 2: {
                    arr_object[0] = "delegate";
                    break;
                }
                case 3: {
                    arr_object[0] = "kotlinTypeRefiner";
                    break;
                }
                case 1: 
                case 4: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils$SpecialType";
                    break;
                }
                default: {
                    arr_object[0] = "newAttributes";
                }
            }
            switch(v) {
                case 1: {
                    arr_object[1] = "toString";
                    break;
                }
                case 4: {
                    arr_object[1] = "refine";
                    break;
                }
                default: {
                    arr_object[1] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils$SpecialType";
                }
            }
            switch(v) {
                case 2: {
                    arr_object[2] = "replaceDelegate";
                    break;
                }
                case 3: {
                    arr_object[2] = "refine";
                    break;
                }
                case 1: 
                case 4: {
                    break;
                }
                default: {
                    arr_object[2] = "replaceAttributes";
                }
            }
            String s = String.format((v == 1 || v == 4 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
            IllegalStateException illegalStateException0 = v == 1 || v == 4 ? new IllegalStateException(s) : new IllegalArgumentException(s);
            throw illegalStateException0;
        }

        public SpecialType(String s) {
            this.name = s;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
        protected SimpleType getDelegate() {
            throw new IllegalStateException(this.name);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
        public SimpleType makeNullableAsSpecified(boolean z) {
            throw new IllegalStateException(this.name);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
        public UnwrappedType makeNullableAsSpecified(boolean z) {
            return this.makeNullableAsSpecified(z);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
        public KotlinType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
            return this.refine(kotlinTypeRefiner0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
        public SimpleType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
            return this.refine(kotlinTypeRefiner0);
        }

        public SpecialType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
            if(kotlinTypeRefiner0 == null) {
                SpecialType.$$$reportNull$$$0(3);
            }
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
        public UnwrappedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
            return this.refine(kotlinTypeRefiner0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
        public SimpleType replaceAttributes(TypeAttributes typeAttributes0) {
            if(typeAttributes0 == null) {
                SpecialType.$$$reportNull$$$0(0);
            }
            throw new IllegalStateException(this.name);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
        public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
            return this.replaceAttributes(typeAttributes0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
        public DelegatingSimpleType replaceDelegate(SimpleType simpleType0) {
            if(simpleType0 == null) {
                SpecialType.$$$reportNull$$$0(2);
            }
            throw new IllegalStateException(this.name);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
        public String toString() {
            String s = this.name;
            if(s == null) {
                SpecialType.$$$reportNull$$$0(1);
            }
            return s;
        }
    }

    static final boolean $assertionsDisabled;
    public static final SimpleType CANNOT_INFER_FUNCTION_PARAM_TYPE;
    public static final SimpleType DONT_CARE;
    public static final SimpleType NO_EXPECTED_TYPE;
    public static final SimpleType UNIT_EXPECTED_TYPE;

    private static void $$$reportNull$$$0(int v) {
        IllegalStateException illegalStateException0;
        int v1;
        String s;
        switch(v) {
            case 4: 
            case 6: 
            case 7: 
            case 9: 
            case 11: 
            case 15: 
            case 17: 
            case 19: 
            case 26: 
            case 35: 
            case 0x30: 
            case 53: {
                s = "@NotNull method %s.%s must not return null";
                break;
            }
            default: {
                s = v == 56 || v == 57 || v == 58 || v == 59 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
            }
        }
        switch(v) {
            case 4: 
            case 6: 
            case 7: 
            case 9: 
            case 11: 
            case 15: 
            case 17: 
            case 19: 
            case 26: 
            case 35: 
            case 0x30: 
            case 53: {
                v1 = 2;
                break;
            }
            default: {
                v1 = v == 56 || v == 57 || v == 58 || v == 59 ? 2 : 3;
            }
        }
        Object[] arr_object = new Object[v1];
        switch(v) {
            case 12: {
                arr_object[0] = "typeConstructor";
                break;
            }
            case 13: {
                arr_object[0] = "unsubstitutedMemberScope";
                break;
            }
            case 14: {
                arr_object[0] = "refinedTypeFactory";
                break;
            }
            case 16: {
                arr_object[0] = "parameters";
                break;
            }
            case 20: {
                arr_object[0] = "subType";
                break;
            }
            case 21: {
                arr_object[0] = "superType";
                break;
            }
            case 22: {
                arr_object[0] = "substitutor";
                break;
            }
            case 24: {
                arr_object[0] = "result";
                break;
            }
            case 0x20: {
                arr_object[0] = "typeArguments";
                break;
            }
            case 0x1F: 
            case 33: {
                arr_object[0] = "clazz";
                break;
            }
            case 34: {
                arr_object[0] = "projections";
                break;
            }
            case 36: {
                arr_object[0] = "a";
                break;
            }
            case 37: {
                arr_object[0] = "b";
                break;
            }
            case 39: {
                arr_object[0] = "typeParameters";
                break;
            }
            case 41: {
                arr_object[0] = "typeParameterConstructors";
                break;
            }
            case 42: {
                arr_object[0] = "specialType";
                break;
            }
            case 43: 
            case 44: {
                arr_object[0] = "isSpecialType";
                break;
            }
            case 45: 
            case 46: {
                arr_object[0] = "parameterDescriptor";
                break;
            }
            case 49: 
            case 50: {
                arr_object[0] = "supertypes";
                break;
            }
            case 0x2F: 
            case 51: {
                arr_object[0] = "numberValueTypeConstructor";
                break;
            }
            case 54: {
                arr_object[0] = "literalTypeConstructor";
                break;
            }
            case 52: 
            case 55: {
                arr_object[0] = "expectedType";
                break;
            }
            case 4: 
            case 6: 
            case 7: 
            case 9: 
            case 11: 
            case 15: 
            case 17: 
            case 19: 
            case 26: 
            case 35: 
            case 0x30: 
            case 53: 
            case 56: 
            case 57: 
            case 58: 
            case 59: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/types/TypeUtils";
                break;
            }
            default: {
                arr_object[0] = "type";
            }
        }
        switch(v) {
            case 4: {
                arr_object[1] = "makeNullableAsSpecified";
                break;
            }
            case 6: 
            case 7: 
            case 9: {
                arr_object[1] = "makeNullableIfNeeded";
                break;
            }
            case 11: 
            case 15: {
                arr_object[1] = "makeUnsubstitutedType";
                break;
            }
            case 17: {
                arr_object[1] = "getDefaultTypeProjections";
                break;
            }
            case 19: {
                arr_object[1] = "getImmediateSupertypes";
                break;
            }
            case 26: {
                arr_object[1] = "getAllSupertypes";
                break;
            }
            case 35: {
                arr_object[1] = "substituteProjectionsForParameters";
                break;
            }
            case 0x30: {
                arr_object[1] = "getDefaultPrimitiveNumberType";
                break;
            }
            case 53: {
                arr_object[1] = "getPrimitiveNumberType";
                break;
            }
            default: {
                arr_object[1] = v == 56 || v == 57 || v == 58 || v == 59 ? "getPrimitiveNumberType" : "kotlin/reflect/jvm/internal/impl/types/TypeUtils";
            }
        }
        switch(v) {
            case 1: {
                arr_object[2] = "makeNullable";
                break;
            }
            case 2: {
                arr_object[2] = "makeNotNullable";
                break;
            }
            case 3: {
                arr_object[2] = "makeNullableAsSpecified";
                break;
            }
            case 5: 
            case 8: {
                arr_object[2] = "makeNullableIfNeeded";
                break;
            }
            case 10: {
                arr_object[2] = "canHaveSubtypes";
                break;
            }
            case 12: 
            case 13: 
            case 14: {
                arr_object[2] = "makeUnsubstitutedType";
                break;
            }
            case 16: {
                arr_object[2] = "getDefaultTypeProjections";
                break;
            }
            case 18: {
                arr_object[2] = "getImmediateSupertypes";
                break;
            }
            case 20: 
            case 21: 
            case 22: {
                arr_object[2] = "createSubstitutedSupertype";
                break;
            }
            case 23: 
            case 24: {
                arr_object[2] = "collectAllSupertypes";
                break;
            }
            case 25: {
                arr_object[2] = "getAllSupertypes";
                break;
            }
            case 27: {
                arr_object[2] = "isNullableType";
                break;
            }
            case 28: {
                arr_object[2] = "acceptsNullable";
                break;
            }
            case 29: {
                arr_object[2] = "hasNullableSuperType";
                break;
            }
            case 30: {
                arr_object[2] = "getClassDescriptor";
                break;
            }
            case 0x1F: 
            case 0x20: {
                arr_object[2] = "substituteParameters";
                break;
            }
            case 33: 
            case 34: {
                arr_object[2] = "substituteProjectionsForParameters";
                break;
            }
            case 36: 
            case 37: {
                arr_object[2] = "equalTypes";
                break;
            }
            case 38: 
            case 39: {
                arr_object[2] = "dependsOnTypeParameters";
                break;
            }
            case 40: 
            case 41: {
                arr_object[2] = "dependsOnTypeConstructors";
                break;
            }
            case 42: 
            case 43: 
            case 44: {
                arr_object[2] = "contains";
                break;
            }
            case 45: 
            case 46: {
                arr_object[2] = "makeStarProjection";
                break;
            }
            case 0x2F: 
            case 49: {
                arr_object[2] = "getDefaultPrimitiveNumberType";
                break;
            }
            case 50: {
                arr_object[2] = "findByFqName";
                break;
            }
            case 51: 
            case 52: 
            case 54: 
            case 55: {
                arr_object[2] = "getPrimitiveNumberType";
                break;
            }
            case 4: 
            case 6: 
            case 7: 
            case 9: 
            case 11: 
            case 15: 
            case 17: 
            case 19: 
            case 26: 
            case 35: 
            case 0x30: 
            case 53: 
            case 56: 
            case 57: 
            case 58: 
            case 59: {
                break;
            }
            case 60: {
                arr_object[2] = "isTypeParameter";
                break;
            }
            case 61: {
                arr_object[2] = "isReifiedTypeParameter";
                break;
            }
            case 62: {
                arr_object[2] = "isNonReifiedTypeParameter";
                break;
            }
            case 0x3F: {
                arr_object[2] = "getTypeParameterDescriptorOrNull";
                break;
            }
            default: {
                arr_object[2] = "noExpectedType";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 4: 
            case 6: 
            case 7: 
            case 9: 
            case 11: 
            case 15: 
            case 17: 
            case 19: 
            case 26: 
            case 35: 
            case 0x30: 
            case 53: {
                illegalStateException0 = new IllegalStateException(s1);
                break;
            }
            default: {
                illegalStateException0 = v == 56 || v == 57 || v == 58 || v == 59 ? new IllegalStateException(s1) : new IllegalArgumentException(s1);
            }
        }
        throw illegalStateException0;
    }

    static {
        TypeUtils.DONT_CARE = ErrorUtils.createErrorType(ErrorTypeKind.DONT_CARE, new String[0]);
        TypeUtils.CANNOT_INFER_FUNCTION_PARAM_TYPE = ErrorUtils.createErrorType(ErrorTypeKind.UNINFERRED_LAMBDA_PARAMETER_TYPE, new String[0]);
        TypeUtils.NO_EXPECTED_TYPE = new SpecialType("NO_EXPECTED_TYPE");
        TypeUtils.UNIT_EXPECTED_TYPE = new SpecialType("UNIT_EXPECTED_TYPE");
    }

    public static boolean acceptsNullable(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(28);
        }
        return kotlinType0.isMarkedNullable() ? true : FlexibleTypesKt.isFlexible(kotlinType0) && TypeUtils.acceptsNullable(FlexibleTypesKt.asFlexibleType(kotlinType0).getUpperBound());
    }

    public static boolean contains(KotlinType kotlinType0, Function1 function10) {
        if(function10 == null) {
            TypeUtils.$$$reportNull$$$0(43);
        }
        return TypeUtils.contains(kotlinType0, function10, null);
    }

    private static boolean contains(KotlinType kotlinType0, Function1 function10, SmartSet smartSet0) {
        if(function10 == null) {
            TypeUtils.$$$reportNull$$$0(44);
        }
        if(kotlinType0 == null) {
            return false;
        }
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        if(TypeUtils.noExpectedType(kotlinType0)) {
            return ((Boolean)function10.invoke(unwrappedType0)).booleanValue();
        }
        if(smartSet0 != null && smartSet0.contains(kotlinType0)) {
            return false;
        }
        if(((Boolean)function10.invoke(unwrappedType0)).booleanValue()) {
            return true;
        }
        if(smartSet0 == null) {
            smartSet0 = SmartSet.create();
        }
        smartSet0.add(kotlinType0);
        FlexibleType flexibleType0 = unwrappedType0 instanceof FlexibleType ? ((FlexibleType)unwrappedType0) : null;
        if(flexibleType0 != null && (TypeUtils.contains(flexibleType0.getLowerBound(), function10, smartSet0) || TypeUtils.contains(flexibleType0.getUpperBound(), function10, smartSet0))) {
            return true;
        }
        if(unwrappedType0 instanceof DefinitelyNotNullType && TypeUtils.contains(((DefinitelyNotNullType)unwrappedType0).getOriginal(), function10, smartSet0)) {
            return true;
        }
        TypeConstructor typeConstructor0 = kotlinType0.getConstructor();
        if(typeConstructor0 instanceof IntersectionTypeConstructor) {
            for(Object object0: ((IntersectionTypeConstructor)typeConstructor0).getSupertypes()) {
                if(TypeUtils.contains(((KotlinType)object0), function10, smartSet0)) {
                    return true;
                }
                if(false) {
                    break;
                }
            }
            return false;
        }
        for(Object object1: kotlinType0.getArguments()) {
            if(!((TypeProjection)object1).isStarProjection() && TypeUtils.contains(((TypeProjection)object1).getType(), function10, smartSet0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public static KotlinType createSubstitutedSupertype(KotlinType kotlinType0, KotlinType kotlinType1, TypeSubstitutor typeSubstitutor0) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(20);
        }
        if(kotlinType1 == null) {
            TypeUtils.$$$reportNull$$$0(21);
        }
        if(typeSubstitutor0 == null) {
            TypeUtils.$$$reportNull$$$0(22);
        }
        KotlinType kotlinType2 = typeSubstitutor0.substitute(kotlinType1, Variance.INVARIANT);
        return kotlinType2 == null ? null : TypeUtils.makeNullableIfNeeded(kotlinType2, kotlinType0.isMarkedNullable());
    }

    public static ClassDescriptor getClassDescriptor(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(30);
        }
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        return classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
    }

    public static List getDefaultTypeProjections(List list0) {
        if(list0 == null) {
            TypeUtils.$$$reportNull$$$0(16);
        }
        ArrayList arrayList0 = new ArrayList(list0.size());
        for(Object object0: list0) {
            arrayList0.add(new TypeProjectionImpl(((TypeParameterDescriptor)object0).getDefaultType()));
        }
        List list1 = CollectionsKt.toList(arrayList0);
        if(list1 == null) {
            TypeUtils.$$$reportNull$$$0(17);
        }
        return list1;
    }

    public static List getImmediateSupertypes(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(18);
        }
        TypeSubstitutor typeSubstitutor0 = TypeSubstitutor.create(kotlinType0);
        Collection collection0 = kotlinType0.getConstructor().getSupertypes();
        List list0 = new ArrayList(collection0.size());
        for(Object object0: collection0) {
            KotlinType kotlinType1 = TypeUtils.createSubstitutedSupertype(kotlinType0, ((KotlinType)object0), typeSubstitutor0);
            if(kotlinType1 != null) {
                list0.add(kotlinType1);
            }
        }
        return list0;
    }

    public static TypeParameterDescriptor getTypeParameterDescriptorOrNull(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(0x3F);
        }
        return kotlinType0.getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor ? ((TypeParameterDescriptor)kotlinType0.getConstructor().getDeclarationDescriptor()) : null;
    }

    public static boolean hasNullableSuperType(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(29);
        }
        if(kotlinType0.getConstructor().getDeclarationDescriptor() instanceof ClassDescriptor) {
            return false;
        }
        for(Object object0: TypeUtils.getImmediateSupertypes(kotlinType0)) {
            if(TypeUtils.isNullableType(((KotlinType)object0))) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public static boolean isDontCarePlaceholder(KotlinType kotlinType0) {
        return kotlinType0 != null && kotlinType0.getConstructor() == TypeUtils.DONT_CARE.getConstructor();
    }

    public static boolean isNullableType(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(27);
        }
        if(kotlinType0.isMarkedNullable()) {
            return true;
        }
        if(FlexibleTypesKt.isFlexible(kotlinType0) && TypeUtils.isNullableType(FlexibleTypesKt.asFlexibleType(kotlinType0).getUpperBound())) {
            return true;
        }
        if(SpecialTypesKt.isDefinitelyNotNullType(kotlinType0)) {
            return false;
        }
        if(TypeUtils.isTypeParameter(kotlinType0)) {
            return TypeUtils.hasNullableSuperType(kotlinType0);
        }
        if(kotlinType0 instanceof AbstractStubType) {
            TypeParameterDescriptor typeParameterDescriptor0 = ((AbstractStubType)kotlinType0).getOriginalTypeVariable().getOriginalTypeParameter();
            return typeParameterDescriptor0 == null || TypeUtils.hasNullableSuperType(typeParameterDescriptor0.getDefaultType());
        }
        TypeConstructor typeConstructor0 = kotlinType0.getConstructor();
        if(typeConstructor0 instanceof IntersectionTypeConstructor) {
            for(Object object0: typeConstructor0.getSupertypes()) {
                if(TypeUtils.isNullableType(((KotlinType)object0))) {
                    return true;
                }
                if(false) {
                    break;
                }
            }
        }
        return false;
    }

    public static boolean isTypeParameter(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(60);
        }
        return TypeUtils.getTypeParameterDescriptorOrNull(kotlinType0) != null || kotlinType0.getConstructor() instanceof NewTypeVariableConstructor;
    }

    public static KotlinType makeNotNullable(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(2);
        }
        return TypeUtils.makeNullableAsSpecified(kotlinType0, false);
    }

    public static KotlinType makeNullable(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(1);
        }
        return TypeUtils.makeNullableAsSpecified(kotlinType0, true);
    }

    public static KotlinType makeNullableAsSpecified(KotlinType kotlinType0, boolean z) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(3);
        }
        KotlinType kotlinType1 = kotlinType0.unwrap().makeNullableAsSpecified(z);
        if(kotlinType1 == null) {
            TypeUtils.$$$reportNull$$$0(4);
        }
        return kotlinType1;
    }

    public static KotlinType makeNullableIfNeeded(KotlinType kotlinType0, boolean z) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(8);
        }
        if(z) {
            return TypeUtils.makeNullable(kotlinType0);
        }
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(9);
        }
        return kotlinType0;
    }

    public static SimpleType makeNullableIfNeeded(SimpleType simpleType0, boolean z) {
        if(simpleType0 == null) {
            TypeUtils.$$$reportNull$$$0(5);
        }
        if(z) {
            SimpleType simpleType1 = simpleType0.makeNullableAsSpecified(true);
            if(simpleType1 == null) {
                TypeUtils.$$$reportNull$$$0(6);
            }
            return simpleType1;
        }
        if(simpleType0 == null) {
            TypeUtils.$$$reportNull$$$0(7);
        }
        return simpleType0;
    }

    public static TypeProjection makeStarProjection(TypeParameterDescriptor typeParameterDescriptor0) {
        if(typeParameterDescriptor0 == null) {
            TypeUtils.$$$reportNull$$$0(45);
        }
        return new StarProjectionImpl(typeParameterDescriptor0);
    }

    public static TypeProjection makeStarProjection(TypeParameterDescriptor typeParameterDescriptor0, ErasureTypeAttributes erasureTypeAttributes0) {
        if(typeParameterDescriptor0 == null) {
            TypeUtils.$$$reportNull$$$0(46);
        }
        return erasureTypeAttributes0.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE ? new TypeProjectionImpl(StarProjectionImplKt.starProjectionType(typeParameterDescriptor0)) : new StarProjectionImpl(typeParameterDescriptor0);
    }

    public static SimpleType makeUnsubstitutedType(ClassifierDescriptor classifierDescriptor0, MemberScope memberScope0, Function1 function10) {
        if(ErrorUtils.isError(classifierDescriptor0)) {
            SimpleType simpleType0 = ErrorUtils.createErrorType(ErrorTypeKind.UNABLE_TO_SUBSTITUTE_TYPE, new String[]{classifierDescriptor0.toString()});
            if(simpleType0 == null) {
                TypeUtils.$$$reportNull$$$0(11);
            }
            return simpleType0;
        }
        return TypeUtils.makeUnsubstitutedType(classifierDescriptor0.getTypeConstructor(), memberScope0, function10);
    }

    public static SimpleType makeUnsubstitutedType(TypeConstructor typeConstructor0, MemberScope memberScope0, Function1 function10) {
        if(typeConstructor0 == null) {
            TypeUtils.$$$reportNull$$$0(12);
        }
        if(memberScope0 == null) {
            TypeUtils.$$$reportNull$$$0(13);
        }
        if(function10 == null) {
            TypeUtils.$$$reportNull$$$0(14);
        }
        List list0 = TypeUtils.getDefaultTypeProjections(typeConstructor0.getParameters());
        SimpleType simpleType0 = KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(TypeAttributes.Companion.getEmpty(), typeConstructor0, list0, false, memberScope0, function10);
        if(simpleType0 == null) {
            TypeUtils.$$$reportNull$$$0(15);
        }
        return simpleType0;
    }

    public static boolean noExpectedType(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeUtils.$$$reportNull$$$0(0);
        }
        return kotlinType0 == TypeUtils.NO_EXPECTED_TYPE || kotlinType0 == TypeUtils.UNIT_EXPECTED_TYPE;
    }
}

