package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.ArgumentList;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.IntersectionTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

public final class AbstractTypeChecker {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;

        static {
            int[] arr_v = new int[TypeVariance.values().length];
            try {
                arr_v[TypeVariance.INV.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[TypeVariance.OUT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[TypeVariance.IN.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
            int[] arr_v1 = new int[LowerCapturedTypePolicy.values().length];
            try {
                arr_v1[LowerCapturedTypePolicy.CHECK_ONLY_LOWER.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v1[LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v1[LowerCapturedTypePolicy.SKIP_LOWER.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$1 = arr_v1;
        }
    }

    public static final AbstractTypeChecker INSTANCE;
    public static boolean RUN_SLOW_ASSERTIONS;

    static {
        AbstractTypeChecker.INSTANCE = new AbstractTypeChecker();
    }

    private final Boolean checkSubtypeForIntegerLiteralType(TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0, SimpleTypeMarker simpleTypeMarker1) {
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        if(!typeSystemContext0.isIntegerLiteralType(simpleTypeMarker0) && !typeSystemContext0.isIntegerLiteralType(simpleTypeMarker1)) {
            return null;
        }
        if(AbstractTypeChecker.checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeOrCapturedOne(typeSystemContext0, simpleTypeMarker0) && AbstractTypeChecker.checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeOrCapturedOne(typeSystemContext0, simpleTypeMarker1)) {
            return true;
        }
        if(typeSystemContext0.isIntegerLiteralType(simpleTypeMarker0)) {
            return AbstractTypeChecker.checkSubtypeForIntegerLiteralType$lambda$7$isTypeInIntegerLiteralType(typeSystemContext0, typeCheckerState0, simpleTypeMarker0, simpleTypeMarker1, false) ? true : null;
        }
        return !typeSystemContext0.isIntegerLiteralType(simpleTypeMarker1) || !AbstractTypeChecker.checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeInIntersectionComponents(typeSystemContext0, simpleTypeMarker0) && !AbstractTypeChecker.checkSubtypeForIntegerLiteralType$lambda$7$isTypeInIntegerLiteralType(typeSystemContext0, typeCheckerState0, simpleTypeMarker1, simpleTypeMarker0, true) ? null : true;
    }

    private static final boolean checkSubtypeForIntegerLiteralType$lambda$7$isCapturedIntegerLiteralType(TypeSystemContext typeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
        if(!(simpleTypeMarker0 instanceof CapturedTypeMarker)) {
            return false;
        }
        TypeArgumentMarker typeArgumentMarker0 = typeSystemContext0.projection(typeSystemContext0.typeConstructor(((CapturedTypeMarker)simpleTypeMarker0)));
        return !typeSystemContext0.isStarProjection(typeArgumentMarker0) && typeSystemContext0.isIntegerLiteralType(typeSystemContext0.upperBoundIfFlexible(typeSystemContext0.getType(typeArgumentMarker0)));
    }

    private static final boolean checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeInIntersectionComponents(TypeSystemContext typeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
        TypeConstructorMarker typeConstructorMarker0 = typeSystemContext0.typeConstructor(simpleTypeMarker0);
        if(typeConstructorMarker0 instanceof IntersectionTypeConstructorMarker) {
            Iterable iterable0 = typeSystemContext0.supertypes(typeConstructorMarker0);
            if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
                for(Object object0: iterable0) {
                    SimpleTypeMarker simpleTypeMarker1 = typeSystemContext0.asSimpleType(((KotlinTypeMarker)object0));
                    if(simpleTypeMarker1 != null && typeSystemContext0.isIntegerLiteralType(simpleTypeMarker1)) {
                        return true;
                    }
                    if(false) {
                        break;
                    }
                }
            }
        }
        return false;
    }

    // 去混淆评级： 低(20)
    private static final boolean checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeOrCapturedOne(TypeSystemContext typeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
        return typeSystemContext0.isIntegerLiteralType(simpleTypeMarker0) || AbstractTypeChecker.checkSubtypeForIntegerLiteralType$lambda$7$isCapturedIntegerLiteralType(typeSystemContext0, simpleTypeMarker0);
    }

    private static final boolean checkSubtypeForIntegerLiteralType$lambda$7$isTypeInIntegerLiteralType(TypeSystemContext typeSystemContext0, TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0, SimpleTypeMarker simpleTypeMarker1, boolean z) {
        Iterable iterable0 = typeSystemContext0.possibleIntegerTypes(simpleTypeMarker0);
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return false;
        }
        for(Object object0: iterable0) {
            if(Intrinsics.areEqual(typeSystemContext0.typeConstructor(((KotlinTypeMarker)object0)), typeSystemContext0.typeConstructor(simpleTypeMarker1)) || z && AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, typeCheckerState0, simpleTypeMarker1, ((KotlinTypeMarker)object0), false, 8, null)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private final Boolean checkSubtypeForSpecialCases(TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0, SimpleTypeMarker simpleTypeMarker1) {
        SimpleTypeMarker simpleTypeMarker2;
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        boolean z = false;
        if(!typeSystemContext0.isError(simpleTypeMarker0) && !typeSystemContext0.isError(simpleTypeMarker1)) {
            if(typeSystemContext0.isStubTypeForBuilderInference(simpleTypeMarker0) && typeSystemContext0.isStubTypeForBuilderInference(simpleTypeMarker1)) {
                if(AbstractTypeChecker.INSTANCE.isStubTypeSubtypeOfAnother(typeSystemContext0, simpleTypeMarker0, simpleTypeMarker1) || typeCheckerState0.isStubTypeEqualsToAnything()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
            if(!typeSystemContext0.isStubType(simpleTypeMarker0) && !typeSystemContext0.isStubType(simpleTypeMarker1)) {
                DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker0 = typeSystemContext0.asDefinitelyNotNullType(simpleTypeMarker1);
                if(definitelyNotNullTypeMarker0 == null) {
                    simpleTypeMarker2 = simpleTypeMarker1;
                }
                else {
                    simpleTypeMarker2 = typeSystemContext0.original(definitelyNotNullTypeMarker0);
                    if(simpleTypeMarker2 == null) {
                        simpleTypeMarker2 = simpleTypeMarker1;
                    }
                }
                CapturedTypeMarker capturedTypeMarker0 = typeSystemContext0.asCapturedType(simpleTypeMarker2);
                KotlinTypeMarker kotlinTypeMarker0 = capturedTypeMarker0 == null ? null : typeSystemContext0.lowerType(capturedTypeMarker0);
                if(capturedTypeMarker0 != null && kotlinTypeMarker0 != null) {
                    if(typeSystemContext0.isMarkedNullable(simpleTypeMarker1)) {
                        kotlinTypeMarker0 = typeSystemContext0.withNullability(kotlinTypeMarker0, true);
                    }
                    else if(typeSystemContext0.isDefinitelyNotNullType(simpleTypeMarker1)) {
                        kotlinTypeMarker0 = typeSystemContext0.makeDefinitelyNotNullOrNotNull(kotlinTypeMarker0);
                    }
                    switch(WhenMappings.$EnumSwitchMapping$1[typeCheckerState0.getLowerCapturedTypePolicy(simpleTypeMarker0, capturedTypeMarker0).ordinal()]) {
                        case 1: {
                            return Boolean.valueOf(AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, typeCheckerState0, simpleTypeMarker0, kotlinTypeMarker0, false, 8, null));
                        }
                        case 2: {
                            if(AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, typeCheckerState0, simpleTypeMarker0, kotlinTypeMarker0, false, 8, null)) {
                                return true;
                            }
                        }
                    }
                }
                TypeConstructorMarker typeConstructorMarker0 = typeSystemContext0.typeConstructor(simpleTypeMarker1);
                if(typeSystemContext0.isIntersection(typeConstructorMarker0)) {
                    typeSystemContext0.isMarkedNullable(simpleTypeMarker1);
                    Iterable iterable0 = typeSystemContext0.supertypes(typeConstructorMarker0);
                    if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
                        for(Object object0: iterable0) {
                            if(!AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, typeCheckerState0, simpleTypeMarker0, ((KotlinTypeMarker)object0), false, 8, null)) {
                                return false;
                            }
                            if(false) {
                                break;
                            }
                        }
                    }
                    return true;
                }
                TypeConstructorMarker typeConstructorMarker1 = typeSystemContext0.typeConstructor(simpleTypeMarker0);
                if(!(simpleTypeMarker0 instanceof CapturedTypeMarker)) {
                    if(!typeSystemContext0.isIntersection(typeConstructorMarker1)) {
                        return null;
                    }
                    Iterable iterable1 = typeSystemContext0.supertypes(typeConstructorMarker1);
                    if(!(iterable1 instanceof Collection) || !((Collection)iterable1).isEmpty()) {
                        for(Object object1: iterable1) {
                            if(((KotlinTypeMarker)object1) instanceof CapturedTypeMarker) {
                                continue;
                            }
                            return null;
                        }
                    }
                }
                TypeParameterMarker typeParameterMarker0 = AbstractTypeChecker.INSTANCE.getTypeParameterForArgumentInBaseIfItEqualToTarget(typeCheckerState0.getTypeSystemContext(), simpleTypeMarker1, simpleTypeMarker0);
                return typeParameterMarker0 == null || !typeSystemContext0.hasRecursiveBounds(typeParameterMarker0, typeSystemContext0.typeConstructor(simpleTypeMarker1)) ? null : true;
            }
            return Boolean.valueOf(typeCheckerState0.isStubTypeEqualsToAnything());
        }
        if(typeCheckerState0.isErrorTypeEqualsToAnything()) {
            return true;
        }
        if(typeSystemContext0.isMarkedNullable(simpleTypeMarker0) && !typeSystemContext0.isMarkedNullable(simpleTypeMarker1)) {
            return false;
        }
        KotlinTypeMarker kotlinTypeMarker1 = typeSystemContext0.withNullability(simpleTypeMarker0, false);
        KotlinTypeMarker kotlinTypeMarker2 = typeSystemContext0.withNullability(simpleTypeMarker1, false);
        return Boolean.valueOf(AbstractStrictEqualityTypeChecker.INSTANCE.strictEqualTypes(typeSystemContext0, kotlinTypeMarker1, kotlinTypeMarker2));
    }

    private final List collectAllSupertypesWithGivenTypeConstructor(TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0, TypeConstructorMarker typeConstructorMarker0) {
        SupertypesPolicy typeCheckerState$SupertypesPolicy0;
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        List list0 = typeSystemContext0.fastCorrespondingSupertypes(simpleTypeMarker0, typeConstructorMarker0);
        if(list0 != null) {
            return list0;
        }
        if(!typeSystemContext0.isClassTypeConstructor(typeConstructorMarker0) && typeSystemContext0.isClassType(simpleTypeMarker0)) {
            return CollectionsKt.emptyList();
        }
        if(typeSystemContext0.isCommonFinalClassConstructor(typeConstructorMarker0)) {
            if(typeSystemContext0.areEqualTypeConstructors(typeSystemContext0.typeConstructor(simpleTypeMarker0), typeConstructorMarker0)) {
                SimpleTypeMarker simpleTypeMarker1 = typeSystemContext0.captureFromArguments(simpleTypeMarker0, CaptureStatus.FOR_SUBTYPING);
                if(simpleTypeMarker1 != null) {
                    simpleTypeMarker0 = simpleTypeMarker1;
                }
                return CollectionsKt.listOf(simpleTypeMarker0);
            }
            return CollectionsKt.emptyList();
        }
        List list1 = new SmartList();
        typeCheckerState0.initialize();
        ArrayDeque arrayDeque0 = typeCheckerState0.getSupertypesDeque();
        Intrinsics.checkNotNull(arrayDeque0);
        Set set0 = typeCheckerState0.getSupertypesSet();
        Intrinsics.checkNotNull(set0);
        arrayDeque0.push(simpleTypeMarker0);
        while(!arrayDeque0.isEmpty()) {
            if(set0.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker0 + ". Supertypes = " + CollectionsKt.joinToString$default(set0, null, null, null, 0, null, null, 0x3F, null)).toString());
            }
            SimpleTypeMarker simpleTypeMarker2 = (SimpleTypeMarker)arrayDeque0.pop();
            Intrinsics.checkNotNullExpressionValue(simpleTypeMarker2, "current");
            if(set0.add(simpleTypeMarker2)) {
                SimpleTypeMarker simpleTypeMarker3 = typeSystemContext0.captureFromArguments(simpleTypeMarker2, CaptureStatus.FOR_SUBTYPING);
                if(simpleTypeMarker3 == null) {
                    simpleTypeMarker3 = simpleTypeMarker2;
                }
                if(typeSystemContext0.areEqualTypeConstructors(typeSystemContext0.typeConstructor(simpleTypeMarker3), typeConstructorMarker0)) {
                    list1.add(simpleTypeMarker3);
                    typeCheckerState$SupertypesPolicy0 = None.INSTANCE;
                }
                else if(typeSystemContext0.argumentsCount(simpleTypeMarker3) == 0) {
                    typeCheckerState$SupertypesPolicy0 = LowerIfFlexible.INSTANCE;
                }
                else {
                    typeCheckerState$SupertypesPolicy0 = typeCheckerState0.getTypeSystemContext().substitutionSupertypePolicy(simpleTypeMarker3);
                }
                if(Intrinsics.areEqual(typeCheckerState$SupertypesPolicy0, None.INSTANCE)) {
                    typeCheckerState$SupertypesPolicy0 = null;
                }
                if(typeCheckerState$SupertypesPolicy0 != null) {
                    TypeSystemContext typeSystemContext1 = typeCheckerState0.getTypeSystemContext();
                    for(Object object0: typeSystemContext1.supertypes(typeSystemContext1.typeConstructor(simpleTypeMarker2))) {
                        arrayDeque0.add(typeCheckerState$SupertypesPolicy0.transformType(typeCheckerState0, ((KotlinTypeMarker)object0)));
                    }
                }
            }
        }
        typeCheckerState0.clear();
        return list1;
    }

    private final List collectAndFilter(TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0, TypeConstructorMarker typeConstructorMarker0) {
        return this.selectOnlyPureKotlinSupertypes(typeCheckerState0, this.collectAllSupertypesWithGivenTypeConstructor(typeCheckerState0, simpleTypeMarker0, typeConstructorMarker0));
    }

    private final boolean completeIsSubTypeOf(TypeCheckerState typeCheckerState0, KotlinTypeMarker kotlinTypeMarker0, KotlinTypeMarker kotlinTypeMarker1, boolean z) {
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        KotlinTypeMarker kotlinTypeMarker2 = typeCheckerState0.prepareType(typeCheckerState0.refineType(kotlinTypeMarker0));
        KotlinTypeMarker kotlinTypeMarker3 = typeCheckerState0.prepareType(typeCheckerState0.refineType(kotlinTypeMarker1));
        AbstractTypeChecker abstractTypeChecker0 = AbstractTypeChecker.INSTANCE;
        Boolean boolean0 = abstractTypeChecker0.checkSubtypeForSpecialCases(typeCheckerState0, typeSystemContext0.lowerBoundIfFlexible(kotlinTypeMarker2), typeSystemContext0.upperBoundIfFlexible(kotlinTypeMarker3));
        if(boolean0 != null) {
            typeCheckerState0.addSubtypeConstraint(kotlinTypeMarker2, kotlinTypeMarker3, z);
            return boolean0.booleanValue();
        }
        Boolean boolean1 = typeCheckerState0.addSubtypeConstraint(kotlinTypeMarker2, kotlinTypeMarker3, z);
        return boolean1 == null ? abstractTypeChecker0.isSubtypeOfForSingleClassifierType(typeCheckerState0, typeSystemContext0.lowerBoundIfFlexible(kotlinTypeMarker2), typeSystemContext0.upperBoundIfFlexible(kotlinTypeMarker3)) : boolean1.booleanValue();
    }

    public final TypeVariance effectiveVariance(TypeVariance typeVariance0, TypeVariance typeVariance1) {
        Intrinsics.checkNotNullParameter(typeVariance0, "declared");
        Intrinsics.checkNotNullParameter(typeVariance1, "useSite");
        if(typeVariance0 == TypeVariance.INV) {
            return typeVariance1;
        }
        return typeVariance1 != TypeVariance.INV && typeVariance0 != typeVariance1 ? null : typeVariance0;
    }

    public final boolean equalTypes(TypeCheckerState typeCheckerState0, KotlinTypeMarker kotlinTypeMarker0, KotlinTypeMarker kotlinTypeMarker1) {
        Intrinsics.checkNotNullParameter(typeCheckerState0, "state");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "a");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker1, "b");
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        if(kotlinTypeMarker0 == kotlinTypeMarker1) {
            return true;
        }
        AbstractTypeChecker abstractTypeChecker0 = AbstractTypeChecker.INSTANCE;
        if(abstractTypeChecker0.isCommonDenotableType(typeSystemContext0, kotlinTypeMarker0) && abstractTypeChecker0.isCommonDenotableType(typeSystemContext0, kotlinTypeMarker1)) {
            KotlinTypeMarker kotlinTypeMarker2 = typeCheckerState0.prepareType(typeCheckerState0.refineType(kotlinTypeMarker0));
            KotlinTypeMarker kotlinTypeMarker3 = typeCheckerState0.prepareType(typeCheckerState0.refineType(kotlinTypeMarker1));
            SimpleTypeMarker simpleTypeMarker0 = typeSystemContext0.lowerBoundIfFlexible(kotlinTypeMarker2);
            if(!typeSystemContext0.areEqualTypeConstructors(typeSystemContext0.typeConstructor(kotlinTypeMarker2), typeSystemContext0.typeConstructor(kotlinTypeMarker3))) {
                return false;
            }
            return typeSystemContext0.argumentsCount(simpleTypeMarker0) == 0 ? typeSystemContext0.hasFlexibleNullability(kotlinTypeMarker2) || typeSystemContext0.hasFlexibleNullability(kotlinTypeMarker3) || typeSystemContext0.isMarkedNullable(simpleTypeMarker0) == typeSystemContext0.isMarkedNullable(typeSystemContext0.lowerBoundIfFlexible(kotlinTypeMarker3)) : AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker0, typeCheckerState0, kotlinTypeMarker0, kotlinTypeMarker1, false, 8, null) && AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker0, typeCheckerState0, kotlinTypeMarker1, kotlinTypeMarker0, false, 8, null);
        }
        return AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker0, typeCheckerState0, kotlinTypeMarker0, kotlinTypeMarker1, false, 8, null) && AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker0, typeCheckerState0, kotlinTypeMarker1, kotlinTypeMarker0, false, 8, null);
    }

    public final List findCorrespondingSupertypes(TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0, TypeConstructorMarker typeConstructorMarker0) {
        SupertypesPolicy typeCheckerState$SupertypesPolicy0;
        Intrinsics.checkNotNullParameter(typeCheckerState0, "state");
        Intrinsics.checkNotNullParameter(simpleTypeMarker0, "subType");
        Intrinsics.checkNotNullParameter(typeConstructorMarker0, "superConstructor");
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        if(typeSystemContext0.isClassType(simpleTypeMarker0)) {
            return AbstractTypeChecker.INSTANCE.collectAndFilter(typeCheckerState0, simpleTypeMarker0, typeConstructorMarker0);
        }
        if(!typeSystemContext0.isClassTypeConstructor(typeConstructorMarker0) && !typeSystemContext0.isIntegerLiteralTypeConstructor(typeConstructorMarker0)) {
            return AbstractTypeChecker.INSTANCE.collectAllSupertypesWithGivenTypeConstructor(typeCheckerState0, simpleTypeMarker0, typeConstructorMarker0);
        }
        SmartList smartList0 = new SmartList();
        typeCheckerState0.initialize();
        ArrayDeque arrayDeque0 = typeCheckerState0.getSupertypesDeque();
        Intrinsics.checkNotNull(arrayDeque0);
        Set set0 = typeCheckerState0.getSupertypesSet();
        Intrinsics.checkNotNull(set0);
        arrayDeque0.push(simpleTypeMarker0);
        while(!arrayDeque0.isEmpty()) {
            if(set0.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker0 + ". Supertypes = " + CollectionsKt.joinToString$default(set0, null, null, null, 0, null, null, 0x3F, null)).toString());
            }
            SimpleTypeMarker simpleTypeMarker1 = (SimpleTypeMarker)arrayDeque0.pop();
            Intrinsics.checkNotNullExpressionValue(simpleTypeMarker1, "current");
            if(set0.add(simpleTypeMarker1)) {
                if(typeSystemContext0.isClassType(simpleTypeMarker1)) {
                    smartList0.add(simpleTypeMarker1);
                    typeCheckerState$SupertypesPolicy0 = None.INSTANCE;
                }
                else {
                    typeCheckerState$SupertypesPolicy0 = LowerIfFlexible.INSTANCE;
                }
                if(Intrinsics.areEqual(typeCheckerState$SupertypesPolicy0, None.INSTANCE)) {
                    typeCheckerState$SupertypesPolicy0 = null;
                }
                if(typeCheckerState$SupertypesPolicy0 != null) {
                    TypeSystemContext typeSystemContext1 = typeCheckerState0.getTypeSystemContext();
                    for(Object object0: typeSystemContext1.supertypes(typeSystemContext1.typeConstructor(simpleTypeMarker1))) {
                        arrayDeque0.add(typeCheckerState$SupertypesPolicy0.transformType(typeCheckerState0, ((KotlinTypeMarker)object0)));
                    }
                }
            }
        }
        typeCheckerState0.clear();
        Collection collection0 = new ArrayList();
        for(Object object1: smartList0) {
            Intrinsics.checkNotNullExpressionValue(((SimpleTypeMarker)object1), "it");
            CollectionsKt.addAll(collection0, AbstractTypeChecker.INSTANCE.collectAndFilter(typeCheckerState0, ((SimpleTypeMarker)object1), typeConstructorMarker0));
        }
        return (List)collection0;
    }

    private final TypeParameterMarker getTypeParameterForArgumentInBaseIfItEqualToTarget(TypeSystemContext typeSystemContext0, KotlinTypeMarker kotlinTypeMarker0, KotlinTypeMarker kotlinTypeMarker1) {
        int v = typeSystemContext0.argumentsCount(kotlinTypeMarker0);
        for(int v1 = 0; true; ++v1) {
            TypeArgumentMarker typeArgumentMarker0 = null;
            if(v1 >= v) {
                break;
            }
            TypeArgumentMarker typeArgumentMarker1 = typeSystemContext0.getArgument(kotlinTypeMarker0, v1);
            if(!typeSystemContext0.isStarProjection(typeArgumentMarker1)) {
                typeArgumentMarker0 = typeArgumentMarker1;
            }
            if(typeArgumentMarker0 != null) {
                KotlinTypeMarker kotlinTypeMarker2 = typeSystemContext0.getType(typeArgumentMarker0);
                if(kotlinTypeMarker2 != null) {
                    boolean z = typeSystemContext0.isCapturedType(typeSystemContext0.originalIfDefinitelyNotNullable(typeSystemContext0.lowerBoundIfFlexible(kotlinTypeMarker2))) && typeSystemContext0.isCapturedType(typeSystemContext0.originalIfDefinitelyNotNullable(typeSystemContext0.lowerBoundIfFlexible(kotlinTypeMarker1)));
                    if(Intrinsics.areEqual(kotlinTypeMarker2, kotlinTypeMarker1) || z && Intrinsics.areEqual(typeSystemContext0.typeConstructor(kotlinTypeMarker2), typeSystemContext0.typeConstructor(kotlinTypeMarker1))) {
                        return typeSystemContext0.getParameter(typeSystemContext0.typeConstructor(kotlinTypeMarker0), v1);
                    }
                    TypeParameterMarker typeParameterMarker0 = this.getTypeParameterForArgumentInBaseIfItEqualToTarget(typeSystemContext0, kotlinTypeMarker2, kotlinTypeMarker1);
                    if(typeParameterMarker0 != null) {
                        return typeParameterMarker0;
                    }
                }
            }
        }
        return null;
    }

    private final boolean hasNothingSupertype(TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0) {
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        TypeConstructorMarker typeConstructorMarker0 = typeSystemContext0.typeConstructor(simpleTypeMarker0);
        if(typeSystemContext0.isClassTypeConstructor(typeConstructorMarker0)) {
            return typeSystemContext0.isNothingConstructor(typeConstructorMarker0);
        }
        if(typeSystemContext0.isNothingConstructor(typeSystemContext0.typeConstructor(simpleTypeMarker0))) {
            return true;
        }
        typeCheckerState0.initialize();
        ArrayDeque arrayDeque0 = typeCheckerState0.getSupertypesDeque();
        Intrinsics.checkNotNull(arrayDeque0);
        Set set0 = typeCheckerState0.getSupertypesSet();
        Intrinsics.checkNotNull(set0);
        arrayDeque0.push(simpleTypeMarker0);
        while(!arrayDeque0.isEmpty()) {
            if(set0.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker0 + ". Supertypes = " + CollectionsKt.joinToString$default(set0, null, null, null, 0, null, null, 0x3F, null)).toString());
            }
            SimpleTypeMarker simpleTypeMarker1 = (SimpleTypeMarker)arrayDeque0.pop();
            Intrinsics.checkNotNullExpressionValue(simpleTypeMarker1, "current");
            if(set0.add(simpleTypeMarker1)) {
                SupertypesPolicy typeCheckerState$SupertypesPolicy0 = typeSystemContext0.isClassType(simpleTypeMarker1) ? None.INSTANCE : LowerIfFlexible.INSTANCE;
                if(Intrinsics.areEqual(typeCheckerState$SupertypesPolicy0, None.INSTANCE)) {
                    typeCheckerState$SupertypesPolicy0 = null;
                }
                if(typeCheckerState$SupertypesPolicy0 != null) {
                    TypeSystemContext typeSystemContext1 = typeCheckerState0.getTypeSystemContext();
                    for(Object object0: typeSystemContext1.supertypes(typeSystemContext1.typeConstructor(simpleTypeMarker1))) {
                        SimpleTypeMarker simpleTypeMarker2 = typeCheckerState$SupertypesPolicy0.transformType(typeCheckerState0, ((KotlinTypeMarker)object0));
                        if(typeSystemContext0.isNothingConstructor(typeSystemContext0.typeConstructor(simpleTypeMarker2))) {
                            typeCheckerState0.clear();
                            return true;
                        }
                        arrayDeque0.add(simpleTypeMarker2);
                    }
                }
            }
        }
        typeCheckerState0.clear();
        return false;
    }

    // 去混淆评级： 中等(50)
    private final boolean isCommonDenotableType(TypeSystemContext typeSystemContext0, KotlinTypeMarker kotlinTypeMarker0) {
        return typeSystemContext0.isDenotable(typeSystemContext0.typeConstructor(kotlinTypeMarker0)) && !typeSystemContext0.isDynamic(kotlinTypeMarker0) && !typeSystemContext0.isDefinitelyNotNullType(kotlinTypeMarker0) && !typeSystemContext0.isNotNullTypeParameter(kotlinTypeMarker0) && Intrinsics.areEqual(typeSystemContext0.typeConstructor(typeSystemContext0.lowerBoundIfFlexible(kotlinTypeMarker0)), typeSystemContext0.typeConstructor(typeSystemContext0.upperBoundIfFlexible(kotlinTypeMarker0)));
    }

    private final boolean isStubTypeSubtypeOfAnother(TypeSystemContext typeSystemContext0, SimpleTypeMarker simpleTypeMarker0, SimpleTypeMarker simpleTypeMarker1) {
        SimpleTypeMarker simpleTypeMarker3;
        SimpleTypeMarker simpleTypeMarker2;
        DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker0 = typeSystemContext0.asDefinitelyNotNullType(simpleTypeMarker0);
        if(definitelyNotNullTypeMarker0 == null) {
            simpleTypeMarker2 = simpleTypeMarker0;
        }
        else {
            simpleTypeMarker2 = typeSystemContext0.original(definitelyNotNullTypeMarker0);
            if(simpleTypeMarker2 == null) {
                simpleTypeMarker2 = simpleTypeMarker0;
            }
        }
        DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker1 = typeSystemContext0.asDefinitelyNotNullType(simpleTypeMarker1);
        if(definitelyNotNullTypeMarker1 == null) {
            simpleTypeMarker3 = simpleTypeMarker1;
        }
        else {
            simpleTypeMarker3 = typeSystemContext0.original(definitelyNotNullTypeMarker1);
            if(simpleTypeMarker3 == null) {
                simpleTypeMarker3 = simpleTypeMarker1;
            }
        }
        if(typeSystemContext0.typeConstructor(simpleTypeMarker2) != typeSystemContext0.typeConstructor(simpleTypeMarker3)) {
            return false;
        }
        return typeSystemContext0.isDefinitelyNotNullType(simpleTypeMarker0) || !typeSystemContext0.isDefinitelyNotNullType(simpleTypeMarker1) ? !typeSystemContext0.isMarkedNullable(simpleTypeMarker0) || typeSystemContext0.isMarkedNullable(simpleTypeMarker1) : false;
    }

    public final boolean isSubtypeForSameConstructor(TypeCheckerState typeCheckerState0, TypeArgumentListMarker typeArgumentListMarker0, SimpleTypeMarker simpleTypeMarker0) {
        boolean z;
        Intrinsics.checkNotNullParameter(typeCheckerState0, "<this>");
        Intrinsics.checkNotNullParameter(typeArgumentListMarker0, "capturedSubArguments");
        Intrinsics.checkNotNullParameter(simpleTypeMarker0, "superType");
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        TypeConstructorMarker typeConstructorMarker0 = typeSystemContext0.typeConstructor(simpleTypeMarker0);
        int v = typeSystemContext0.size(typeArgumentListMarker0);
        int v1 = typeSystemContext0.parametersCount(typeConstructorMarker0);
        if(v == v1 && v == typeSystemContext0.argumentsCount(simpleTypeMarker0)) {
            for(int v2 = 0; v2 < v1; ++v2) {
                TypeArgumentMarker typeArgumentMarker0 = typeSystemContext0.getArgument(simpleTypeMarker0, v2);
                if(!typeSystemContext0.isStarProjection(typeArgumentMarker0)) {
                    KotlinTypeMarker kotlinTypeMarker0 = typeSystemContext0.getType(typeArgumentMarker0);
                    TypeArgumentMarker typeArgumentMarker1 = typeSystemContext0.get(typeArgumentListMarker0, v2);
                    typeSystemContext0.getVariance(typeArgumentMarker1);
                    KotlinTypeMarker kotlinTypeMarker1 = typeSystemContext0.getType(typeArgumentMarker1);
                    AbstractTypeChecker abstractTypeChecker0 = AbstractTypeChecker.INSTANCE;
                    TypeVariance typeVariance0 = abstractTypeChecker0.effectiveVariance(typeSystemContext0.getVariance(typeSystemContext0.getParameter(typeConstructorMarker0, v2)), typeSystemContext0.getVariance(typeArgumentMarker0));
                    if(typeVariance0 == null) {
                        return typeCheckerState0.isErrorTypeEqualsToAnything();
                    }
                    if(typeVariance0 != TypeVariance.INV || !abstractTypeChecker0.isTypeVariableAgainstStarProjectionForSelfType(typeSystemContext0, kotlinTypeMarker1, kotlinTypeMarker0, typeConstructorMarker0) && !abstractTypeChecker0.isTypeVariableAgainstStarProjectionForSelfType(typeSystemContext0, kotlinTypeMarker0, kotlinTypeMarker1, typeConstructorMarker0)) {
                        if(typeCheckerState0.argumentsDepth > 100) {
                            throw new IllegalStateException(("Arguments depth is too high. Some related argument: " + kotlinTypeMarker1).toString());
                        }
                        ++typeCheckerState0.argumentsDepth;
                        switch(WhenMappings.$EnumSwitchMapping$0[typeVariance0.ordinal()]) {
                            case 1: {
                                z = abstractTypeChecker0.equalTypes(typeCheckerState0, kotlinTypeMarker1, kotlinTypeMarker0);
                                break;
                            }
                            case 2: {
                                z = AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker0, typeCheckerState0, kotlinTypeMarker1, kotlinTypeMarker0, false, 8, null);
                                break;
                            }
                            case 3: {
                                z = AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker0, typeCheckerState0, kotlinTypeMarker0, kotlinTypeMarker1, false, 8, null);
                                break;
                            }
                            default: {
                                throw new NoWhenBranchMatchedException();
                            }
                        }
                        --typeCheckerState0.argumentsDepth;
                        if(!z) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public final boolean isSubtypeOf(TypeCheckerState typeCheckerState0, KotlinTypeMarker kotlinTypeMarker0, KotlinTypeMarker kotlinTypeMarker1) {
        Intrinsics.checkNotNullParameter(typeCheckerState0, "state");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "subType");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker1, "superType");
        return AbstractTypeChecker.isSubtypeOf$default(this, typeCheckerState0, kotlinTypeMarker0, kotlinTypeMarker1, false, 8, null);
    }

    public final boolean isSubtypeOf(TypeCheckerState typeCheckerState0, KotlinTypeMarker kotlinTypeMarker0, KotlinTypeMarker kotlinTypeMarker1, boolean z) {
        Intrinsics.checkNotNullParameter(typeCheckerState0, "state");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "subType");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker1, "superType");
        if(kotlinTypeMarker0 == kotlinTypeMarker1) {
            return true;
        }
        return typeCheckerState0.customIsSubtypeOf(kotlinTypeMarker0, kotlinTypeMarker1) ? this.completeIsSubTypeOf(typeCheckerState0, kotlinTypeMarker0, kotlinTypeMarker1, z) : false;
    }

    public static boolean isSubtypeOf$default(AbstractTypeChecker abstractTypeChecker0, TypeCheckerState typeCheckerState0, KotlinTypeMarker kotlinTypeMarker0, KotlinTypeMarker kotlinTypeMarker1, boolean z, int v, Object object0) {
        if((v & 8) != 0) {
            z = false;
        }
        return abstractTypeChecker0.isSubtypeOf(typeCheckerState0, kotlinTypeMarker0, kotlinTypeMarker1, z);
    }

    private final boolean isSubtypeOfForSingleClassifierType(TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0, SimpleTypeMarker simpleTypeMarker1) {
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        if(AbstractTypeChecker.RUN_SLOW_ASSERTIONS) {
            if(!typeSystemContext0.isSingleClassifierType(simpleTypeMarker0) && !typeSystemContext0.isIntersection(typeSystemContext0.typeConstructor(simpleTypeMarker0))) {
                typeCheckerState0.isAllowedTypeVariable(simpleTypeMarker0);
            }
            if(!typeSystemContext0.isSingleClassifierType(simpleTypeMarker1)) {
                typeCheckerState0.isAllowedTypeVariable(simpleTypeMarker1);
            }
        }
        if(!AbstractNullabilityChecker.INSTANCE.isPossibleSubtype(typeCheckerState0, simpleTypeMarker0, simpleTypeMarker1)) {
            return false;
        }
        AbstractTypeChecker abstractTypeChecker0 = AbstractTypeChecker.INSTANCE;
        Boolean boolean0 = abstractTypeChecker0.checkSubtypeForIntegerLiteralType(typeCheckerState0, typeSystemContext0.lowerBoundIfFlexible(simpleTypeMarker0), typeSystemContext0.upperBoundIfFlexible(simpleTypeMarker1));
        if(boolean0 != null) {
            TypeCheckerState.addSubtypeConstraint$default(typeCheckerState0, simpleTypeMarker0, simpleTypeMarker1, false, 4, null);
            return boolean0.booleanValue();
        }
        TypeConstructorMarker typeConstructorMarker0 = typeSystemContext0.typeConstructor(simpleTypeMarker1);
        if(typeSystemContext0.areEqualTypeConstructors(typeSystemContext0.typeConstructor(simpleTypeMarker0), typeConstructorMarker0) && typeSystemContext0.parametersCount(typeConstructorMarker0) == 0) {
            return true;
        }
        if(typeSystemContext0.isAnyConstructor(typeSystemContext0.typeConstructor(simpleTypeMarker1))) {
            return true;
        }
        Iterable iterable0 = abstractTypeChecker0.findCorrespondingSupertypes(typeCheckerState0, simpleTypeMarker0, typeConstructorMarker0);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            SimpleTypeMarker simpleTypeMarker2 = (SimpleTypeMarker)object0;
            SimpleTypeMarker simpleTypeMarker3 = typeSystemContext0.asSimpleType(typeCheckerState0.prepareType(simpleTypeMarker2));
            if(simpleTypeMarker3 != null) {
                simpleTypeMarker2 = simpleTypeMarker3;
            }
            arrayList0.add(simpleTypeMarker2);
        }
        switch(arrayList0.size()) {
            case 0: {
                return AbstractTypeChecker.INSTANCE.hasNothingSupertype(typeCheckerState0, simpleTypeMarker0);
            }
            case 1: {
                TypeArgumentListMarker typeArgumentListMarker0 = typeSystemContext0.asArgumentList(((SimpleTypeMarker)CollectionsKt.first(arrayList0)));
                return AbstractTypeChecker.INSTANCE.isSubtypeForSameConstructor(typeCheckerState0, typeArgumentListMarker0, simpleTypeMarker1);
            }
            default: {
                ArgumentList argumentList0 = new ArgumentList(typeSystemContext0.parametersCount(typeConstructorMarker0));
                int v = typeSystemContext0.parametersCount(typeConstructorMarker0);
                boolean z = false;
                for(int v1 = 0; v1 < v; ++v1) {
                    z = z || typeSystemContext0.getVariance(typeSystemContext0.getParameter(typeConstructorMarker0, v1)) != TypeVariance.OUT;
                    if(!z) {
                        Collection collection0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList0, 10));
                        for(Object object1: arrayList0) {
                            SimpleTypeMarker simpleTypeMarker4 = (SimpleTypeMarker)object1;
                            TypeArgumentMarker typeArgumentMarker0 = typeSystemContext0.getArgumentOrNull(simpleTypeMarker4, v1);
                            if(typeArgumentMarker0 == null) {
                                throw new IllegalStateException(("Incorrect type: " + simpleTypeMarker4 + ", subType: " + simpleTypeMarker0 + ", superType: " + simpleTypeMarker1).toString());
                            }
                            if(typeSystemContext0.getVariance(typeArgumentMarker0) != TypeVariance.INV) {
                                typeArgumentMarker0 = null;
                            }
                            if(typeArgumentMarker0 == null) {
                                throw new IllegalStateException(("Incorrect type: " + simpleTypeMarker4 + ", subType: " + simpleTypeMarker0 + ", superType: " + simpleTypeMarker1).toString());
                            }
                            KotlinTypeMarker kotlinTypeMarker0 = typeSystemContext0.getType(typeArgumentMarker0);
                            if(kotlinTypeMarker0 == null) {
                                throw new IllegalStateException(("Incorrect type: " + simpleTypeMarker4 + ", subType: " + simpleTypeMarker0 + ", superType: " + simpleTypeMarker1).toString());
                            }
                            collection0.add(kotlinTypeMarker0);
                        }
                        argumentList0.add(typeSystemContext0.asTypeArgument(typeSystemContext0.intersectTypes(((List)collection0))));
                    }
                }
                return z || !AbstractTypeChecker.INSTANCE.isSubtypeForSameConstructor(typeCheckerState0, argumentList0, simpleTypeMarker1) ? typeCheckerState0.runForkingPoint(new Function1(typeCheckerState0, typeSystemContext0, simpleTypeMarker1) {
                    final TypeCheckerState $state;
                    final SimpleTypeMarker $superType;
                    final List $supertypesWithSameConstructor;
                    final TypeSystemContext $this_with;

                    {
                        this.$supertypesWithSameConstructor = list0;
                        this.$state = typeCheckerState0;
                        this.$this_with = typeSystemContext0;
                        this.$superType = simpleTypeMarker0;
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        this.invoke(((ForkPointContext)object0));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(ForkPointContext typeCheckerState$ForkPointContext0) {
                        Intrinsics.checkNotNullParameter(typeCheckerState$ForkPointContext0, "$this$runForkingPoint");
                        for(Object object0: this.$supertypesWithSameConstructor) {
                            typeCheckerState$ForkPointContext0.fork(new Function0(this.$this_with, ((SimpleTypeMarker)object0), this.$superType) {
                                final TypeCheckerState $state;
                                final SimpleTypeMarker $subTypeArguments;
                                final SimpleTypeMarker $superType;
                                final TypeSystemContext $this_with;

                                {
                                    this.$state = typeCheckerState0;
                                    this.$this_with = typeSystemContext0;
                                    this.$subTypeArguments = simpleTypeMarker0;
                                    this.$superType = simpleTypeMarker1;
                                    super(0);
                                }

                                public final Boolean invoke() {
                                    TypeArgumentListMarker typeArgumentListMarker0 = this.$this_with.asArgumentList(this.$subTypeArguments);
                                    return Boolean.valueOf(AbstractTypeChecker.INSTANCE.isSubtypeForSameConstructor(this.$state, typeArgumentListMarker0, this.$superType));
                                }

                                @Override  // kotlin.jvm.functions.Function0
                                public Object invoke() {
                                    return this.invoke();
                                }
                            });
                        }
                    }
                }) : true;
            }
        }
    }

    private final boolean isTypeVariableAgainstStarProjectionForSelfType(TypeSystemContext typeSystemContext0, KotlinTypeMarker kotlinTypeMarker0, KotlinTypeMarker kotlinTypeMarker1, TypeConstructorMarker typeConstructorMarker0) {
        SimpleTypeMarker simpleTypeMarker0 = typeSystemContext0.asSimpleType(kotlinTypeMarker0);
        if(!(simpleTypeMarker0 instanceof CapturedTypeMarker) || typeSystemContext0.isOldCapturedType(((CapturedTypeMarker)simpleTypeMarker0)) || !typeSystemContext0.isStarProjection(typeSystemContext0.projection(typeSystemContext0.typeConstructor(((CapturedTypeMarker)simpleTypeMarker0)))) || typeSystemContext0.captureStatus(((CapturedTypeMarker)simpleTypeMarker0)) != CaptureStatus.FOR_SUBTYPING) {
            return false;
        }
        TypeConstructorMarker typeConstructorMarker1 = typeSystemContext0.typeConstructor(kotlinTypeMarker1);
        TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker0 = typeConstructorMarker1 instanceof TypeVariableTypeConstructorMarker ? ((TypeVariableTypeConstructorMarker)typeConstructorMarker1) : null;
        if(typeVariableTypeConstructorMarker0 == null) {
            return false;
        }
        TypeParameterMarker typeParameterMarker0 = typeSystemContext0.getTypeParameter(typeVariableTypeConstructorMarker0);
        return typeParameterMarker0 != null && typeSystemContext0.hasRecursiveBounds(typeParameterMarker0, typeConstructorMarker0);
    }

    private final List selectOnlyPureKotlinSupertypes(TypeCheckerState typeCheckerState0, List list0) {
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        if(list0.size() >= 2) {
            Collection collection0 = new ArrayList();
            Iterator iterator0 = list0.iterator();
        label_4:
            while(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                TypeArgumentListMarker typeArgumentListMarker0 = typeSystemContext0.asArgumentList(((SimpleTypeMarker)object0));
                int v = typeSystemContext0.size(typeArgumentListMarker0);
                int v1 = 0;
                while(v1 < v) {
                    if(typeSystemContext0.asFlexibleType(typeSystemContext0.getType(typeSystemContext0.get(typeArgumentListMarker0, v1))) != null) {
                        continue label_4;
                    }
                    ++v1;
                }
                collection0.add(object0);
            }
            return ((List)collection0).isEmpty() ? list0 : ((List)collection0);
        }
        return list0;
    }
}

