package kotlin.reflect.jvm.internal.impl.types.model;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState.SupertypesPolicy;

public interface TypeSystemContext extends TypeSystemOptimizationContext {
    boolean areEqualTypeConstructors(TypeConstructorMarker arg1, TypeConstructorMarker arg2);

    int argumentsCount(KotlinTypeMarker arg1);

    TypeArgumentListMarker asArgumentList(SimpleTypeMarker arg1);

    CapturedTypeMarker asCapturedType(SimpleTypeMarker arg1);

    DefinitelyNotNullTypeMarker asDefinitelyNotNullType(SimpleTypeMarker arg1);

    DynamicTypeMarker asDynamicType(FlexibleTypeMarker arg1);

    FlexibleTypeMarker asFlexibleType(KotlinTypeMarker arg1);

    RawTypeMarker asRawType(FlexibleTypeMarker arg1);

    SimpleTypeMarker asSimpleType(KotlinTypeMarker arg1);

    TypeArgumentMarker asTypeArgument(KotlinTypeMarker arg1);

    SimpleTypeMarker captureFromArguments(SimpleTypeMarker arg1, CaptureStatus arg2);

    CaptureStatus captureStatus(CapturedTypeMarker arg1);

    List fastCorrespondingSupertypes(SimpleTypeMarker arg1, TypeConstructorMarker arg2);

    TypeArgumentMarker get(TypeArgumentListMarker arg1, int arg2);

    TypeArgumentMarker getArgument(KotlinTypeMarker arg1, int arg2);

    TypeArgumentMarker getArgumentOrNull(SimpleTypeMarker arg1, int arg2);

    List getArguments(KotlinTypeMarker arg1);

    TypeParameterMarker getParameter(TypeConstructorMarker arg1, int arg2);

    List getParameters(TypeConstructorMarker arg1);

    KotlinTypeMarker getType(TypeArgumentMarker arg1);

    TypeParameterMarker getTypeParameter(TypeVariableTypeConstructorMarker arg1);

    TypeParameterMarker getTypeParameterClassifier(TypeConstructorMarker arg1);

    List getUpperBounds(TypeParameterMarker arg1);

    TypeVariance getVariance(TypeArgumentMarker arg1);

    TypeVariance getVariance(TypeParameterMarker arg1);

    boolean hasFlexibleNullability(KotlinTypeMarker arg1);

    boolean hasRecursiveBounds(TypeParameterMarker arg1, TypeConstructorMarker arg2);

    KotlinTypeMarker intersectTypes(List arg1);

    boolean isAnyConstructor(TypeConstructorMarker arg1);

    boolean isCapturedType(KotlinTypeMarker arg1);

    boolean isClassType(SimpleTypeMarker arg1);

    boolean isClassTypeConstructor(TypeConstructorMarker arg1);

    boolean isCommonFinalClassConstructor(TypeConstructorMarker arg1);

    boolean isDefinitelyNotNullType(KotlinTypeMarker arg1);

    boolean isDenotable(TypeConstructorMarker arg1);

    boolean isDynamic(KotlinTypeMarker arg1);

    boolean isError(KotlinTypeMarker arg1);

    boolean isIntegerLiteralType(SimpleTypeMarker arg1);

    boolean isIntegerLiteralTypeConstructor(TypeConstructorMarker arg1);

    boolean isIntersection(TypeConstructorMarker arg1);

    boolean isMarkedNullable(KotlinTypeMarker arg1);

    boolean isMarkedNullable(SimpleTypeMarker arg1);

    boolean isNotNullTypeParameter(KotlinTypeMarker arg1);

    boolean isNothing(KotlinTypeMarker arg1);

    boolean isNothingConstructor(TypeConstructorMarker arg1);

    boolean isNullableType(KotlinTypeMarker arg1);

    boolean isOldCapturedType(CapturedTypeMarker arg1);

    boolean isPrimitiveType(SimpleTypeMarker arg1);

    boolean isProjectionNotNull(CapturedTypeMarker arg1);

    boolean isSingleClassifierType(SimpleTypeMarker arg1);

    boolean isStarProjection(TypeArgumentMarker arg1);

    boolean isStubType(SimpleTypeMarker arg1);

    boolean isStubTypeForBuilderInference(SimpleTypeMarker arg1);

    boolean isTypeVariableType(KotlinTypeMarker arg1);

    SimpleTypeMarker lowerBound(FlexibleTypeMarker arg1);

    SimpleTypeMarker lowerBoundIfFlexible(KotlinTypeMarker arg1);

    KotlinTypeMarker lowerType(CapturedTypeMarker arg1);

    KotlinTypeMarker makeDefinitelyNotNullOrNotNull(KotlinTypeMarker arg1);

    SimpleTypeMarker original(DefinitelyNotNullTypeMarker arg1);

    SimpleTypeMarker originalIfDefinitelyNotNullable(SimpleTypeMarker arg1);

    int parametersCount(TypeConstructorMarker arg1);

    Collection possibleIntegerTypes(SimpleTypeMarker arg1);

    TypeArgumentMarker projection(CapturedTypeConstructorMarker arg1);

    int size(TypeArgumentListMarker arg1);

    SupertypesPolicy substitutionSupertypePolicy(SimpleTypeMarker arg1);

    Collection supertypes(TypeConstructorMarker arg1);

    CapturedTypeConstructorMarker typeConstructor(CapturedTypeMarker arg1);

    TypeConstructorMarker typeConstructor(KotlinTypeMarker arg1);

    TypeConstructorMarker typeConstructor(SimpleTypeMarker arg1);

    SimpleTypeMarker upperBound(FlexibleTypeMarker arg1);

    SimpleTypeMarker upperBoundIfFlexible(KotlinTypeMarker arg1);

    KotlinTypeMarker withNullability(KotlinTypeMarker arg1, boolean arg2);

    SimpleTypeMarker withNullability(SimpleTypeMarker arg1, boolean arg2);
}

