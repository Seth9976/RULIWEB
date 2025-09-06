package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState.SupertypesPolicy;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.model.ArgumentList;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DynamicTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.RawTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;

public final class SimpleClassicTypeSystemContext implements ClassicTypeSystemContext {
    public static final SimpleClassicTypeSystemContext INSTANCE;

    static {
        SimpleClassicTypeSystemContext.INSTANCE = new SimpleClassicTypeSystemContext();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean areEqualTypeConstructors(TypeConstructorMarker typeConstructorMarker0, TypeConstructorMarker typeConstructorMarker1) {
        return DefaultImpls.areEqualTypeConstructors(this, typeConstructorMarker0, typeConstructorMarker1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int argumentsCount(KotlinTypeMarker kotlinTypeMarker0) {
        return DefaultImpls.argumentsCount(this, kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentListMarker asArgumentList(SimpleTypeMarker simpleTypeMarker0) {
        return DefaultImpls.asArgumentList(this, simpleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    public CapturedTypeMarker asCapturedType(SimpleTypeMarker simpleTypeMarker0) {
        return DefaultImpls.asCapturedType(this, simpleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public DefinitelyNotNullTypeMarker asDefinitelyNotNullType(SimpleTypeMarker simpleTypeMarker0) {
        return DefaultImpls.asDefinitelyNotNullType(this, simpleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public DynamicTypeMarker asDynamicType(FlexibleTypeMarker flexibleTypeMarker0) {
        return DefaultImpls.asDynamicType(this, flexibleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public FlexibleTypeMarker asFlexibleType(KotlinTypeMarker kotlinTypeMarker0) {
        return DefaultImpls.asFlexibleType(this, kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public RawTypeMarker asRawType(FlexibleTypeMarker flexibleTypeMarker0) {
        return DefaultImpls.asRawType(this, flexibleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    public SimpleTypeMarker asSimpleType(KotlinTypeMarker kotlinTypeMarker0) {
        return DefaultImpls.asSimpleType(this, kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentMarker asTypeArgument(KotlinTypeMarker kotlinTypeMarker0) {
        return DefaultImpls.asTypeArgument(this, kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker captureFromArguments(SimpleTypeMarker simpleTypeMarker0, CaptureStatus captureStatus0) {
        return DefaultImpls.captureFromArguments(this, simpleTypeMarker0, captureStatus0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public CaptureStatus captureStatus(CapturedTypeMarker capturedTypeMarker0) {
        return DefaultImpls.captureStatus(this, capturedTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    public KotlinTypeMarker createFlexibleType(SimpleTypeMarker simpleTypeMarker0, SimpleTypeMarker simpleTypeMarker1) {
        return DefaultImpls.createFlexibleType(this, simpleTypeMarker0, simpleTypeMarker1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public List fastCorrespondingSupertypes(SimpleTypeMarker simpleTypeMarker0, TypeConstructorMarker typeConstructorMarker0) {
        Intrinsics.checkNotNullParameter(simpleTypeMarker0, "<this>");
        Intrinsics.checkNotNullParameter(typeConstructorMarker0, "constructor");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentMarker get(TypeArgumentListMarker typeArgumentListMarker0, int v) {
        Intrinsics.checkNotNullParameter(typeArgumentListMarker0, "<this>");
        if(typeArgumentListMarker0 instanceof SimpleTypeMarker) {
            return this.getArgument(((KotlinTypeMarker)typeArgumentListMarker0), v);
        }
        if(!(typeArgumentListMarker0 instanceof ArgumentList)) {
            throw new IllegalStateException(("unknown type argument list type: " + typeArgumentListMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeArgumentListMarker0.getClass())).toString());
        }
        Object object0 = ((ArgumentList)typeArgumentListMarker0).get(v);
        Intrinsics.checkNotNullExpressionValue(object0, "get(index)");
        return (TypeArgumentMarker)object0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentMarker getArgument(KotlinTypeMarker kotlinTypeMarker0, int v) {
        return DefaultImpls.getArgument(this, kotlinTypeMarker0, v);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentMarker getArgumentOrNull(SimpleTypeMarker simpleTypeMarker0, int v) {
        Intrinsics.checkNotNullParameter(simpleTypeMarker0, "<this>");
        return v < 0 || v >= this.argumentsCount(simpleTypeMarker0) ? null : this.getArgument(simpleTypeMarker0, v);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public List getArguments(KotlinTypeMarker kotlinTypeMarker0) {
        return DefaultImpls.getArguments(this, kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public FqNameUnsafe getClassFqNameUnsafe(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.getClassFqNameUnsafe(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeParameterMarker getParameter(TypeConstructorMarker typeConstructorMarker0, int v) {
        return DefaultImpls.getParameter(this, typeConstructorMarker0, v);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public List getParameters(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.getParameters(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public PrimitiveType getPrimitiveArrayType(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.getPrimitiveArrayType(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public PrimitiveType getPrimitiveType(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.getPrimitiveType(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public KotlinTypeMarker getRepresentativeUpperBound(TypeParameterMarker typeParameterMarker0) {
        return DefaultImpls.getRepresentativeUpperBound(this, typeParameterMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker getType(TypeArgumentMarker typeArgumentMarker0) {
        return DefaultImpls.getType(this, typeArgumentMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeParameterMarker getTypeParameter(TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker0) {
        return DefaultImpls.getTypeParameter(this, typeVariableTypeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeParameterMarker getTypeParameterClassifier(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.getTypeParameterClassifier(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public KotlinTypeMarker getUnsubstitutedUnderlyingType(KotlinTypeMarker kotlinTypeMarker0) {
        return DefaultImpls.getUnsubstitutedUnderlyingType(this, kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public List getUpperBounds(TypeParameterMarker typeParameterMarker0) {
        return DefaultImpls.getUpperBounds(this, typeParameterMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeVariance getVariance(TypeArgumentMarker typeArgumentMarker0) {
        return DefaultImpls.getVariance(this, typeArgumentMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeVariance getVariance(TypeParameterMarker typeParameterMarker0) {
        return DefaultImpls.getVariance(this, typeParameterMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean hasAnnotation(KotlinTypeMarker kotlinTypeMarker0, FqName fqName0) {
        return DefaultImpls.hasAnnotation(this, kotlinTypeMarker0, fqName0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean hasFlexibleNullability(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        return this.isMarkedNullable(this.lowerBoundIfFlexible(kotlinTypeMarker0)) != this.isMarkedNullable(this.upperBoundIfFlexible(kotlinTypeMarker0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean hasRecursiveBounds(TypeParameterMarker typeParameterMarker0, TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.hasRecursiveBounds(this, typeParameterMarker0, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemOptimizationContext
    public boolean identicalArguments(SimpleTypeMarker simpleTypeMarker0, SimpleTypeMarker simpleTypeMarker1) {
        return DefaultImpls.identicalArguments(this, simpleTypeMarker0, simpleTypeMarker1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker intersectTypes(List list0) {
        return DefaultImpls.intersectTypes(this, list0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isAnyConstructor(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.isAnyConstructor(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isCapturedType(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        SimpleTypeMarker simpleTypeMarker0 = this.asSimpleType(kotlinTypeMarker0);
        return (simpleTypeMarker0 == null ? null : this.asCapturedType(simpleTypeMarker0)) != null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isClassType(SimpleTypeMarker simpleTypeMarker0) {
        Intrinsics.checkNotNullParameter(simpleTypeMarker0, "<this>");
        return this.isClassTypeConstructor(this.typeConstructor(simpleTypeMarker0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isClassTypeConstructor(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.isClassTypeConstructor(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isCommonFinalClassConstructor(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.isCommonFinalClassConstructor(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isDefinitelyNotNullType(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        SimpleTypeMarker simpleTypeMarker0 = this.asSimpleType(kotlinTypeMarker0);
        return (simpleTypeMarker0 == null ? null : this.asDefinitelyNotNullType(simpleTypeMarker0)) != null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isDenotable(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.isDenotable(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isDynamic(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        FlexibleTypeMarker flexibleTypeMarker0 = this.asFlexibleType(kotlinTypeMarker0);
        return (flexibleTypeMarker0 == null ? null : this.asDynamicType(flexibleTypeMarker0)) != null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isError(KotlinTypeMarker kotlinTypeMarker0) {
        return DefaultImpls.isError(this, kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean isInlineClass(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.isInlineClass(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isIntegerLiteralType(SimpleTypeMarker simpleTypeMarker0) {
        Intrinsics.checkNotNullParameter(simpleTypeMarker0, "<this>");
        return this.isIntegerLiteralTypeConstructor(this.typeConstructor(simpleTypeMarker0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isIntegerLiteralTypeConstructor(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.isIntegerLiteralTypeConstructor(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isIntersection(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.isIntersection(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isMarkedNullable(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        return kotlinTypeMarker0 instanceof SimpleTypeMarker && this.isMarkedNullable(((SimpleTypeMarker)kotlinTypeMarker0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isMarkedNullable(SimpleTypeMarker simpleTypeMarker0) {
        return DefaultImpls.isMarkedNullable(this, simpleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNotNullTypeParameter(KotlinTypeMarker kotlinTypeMarker0) {
        return DefaultImpls.isNotNullTypeParameter(this, kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNothing(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        return this.isNothingConstructor(this.typeConstructor(kotlinTypeMarker0)) && !this.isNullableType(kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNothingConstructor(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.isNothingConstructor(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isNullableType(KotlinTypeMarker kotlinTypeMarker0) {
        return DefaultImpls.isNullableType(this, kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isOldCapturedType(CapturedTypeMarker capturedTypeMarker0) {
        return DefaultImpls.isOldCapturedType(this, capturedTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isPrimitiveType(SimpleTypeMarker simpleTypeMarker0) {
        return DefaultImpls.isPrimitiveType(this, simpleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isProjectionNotNull(CapturedTypeMarker capturedTypeMarker0) {
        return DefaultImpls.isProjectionNotNull(this, capturedTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    public boolean isSingleClassifierType(SimpleTypeMarker simpleTypeMarker0) {
        return DefaultImpls.isSingleClassifierType(this, simpleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isStarProjection(TypeArgumentMarker typeArgumentMarker0) {
        return DefaultImpls.isStarProjection(this, typeArgumentMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isStubType(SimpleTypeMarker simpleTypeMarker0) {
        return DefaultImpls.isStubType(this, simpleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isStubTypeForBuilderInference(SimpleTypeMarker simpleTypeMarker0) {
        return DefaultImpls.isStubTypeForBuilderInference(this, simpleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public boolean isTypeVariableType(KotlinTypeMarker kotlinTypeMarker0) {
        return DefaultImpls.isTypeVariableType(this, kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public boolean isUnderKotlinPackage(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.isUnderKotlinPackage(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    public SimpleTypeMarker lowerBound(FlexibleTypeMarker flexibleTypeMarker0) {
        return DefaultImpls.lowerBound(this, flexibleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker lowerBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        FlexibleTypeMarker flexibleTypeMarker0 = this.asFlexibleType(kotlinTypeMarker0);
        if(flexibleTypeMarker0 != null) {
            SimpleTypeMarker simpleTypeMarker0 = this.lowerBound(flexibleTypeMarker0);
            if(simpleTypeMarker0 != null) {
                return simpleTypeMarker0;
            }
        }
        SimpleTypeMarker simpleTypeMarker1 = this.asSimpleType(kotlinTypeMarker0);
        Intrinsics.checkNotNull(simpleTypeMarker1);
        return simpleTypeMarker1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker lowerType(CapturedTypeMarker capturedTypeMarker0) {
        return DefaultImpls.lowerType(this, capturedTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker makeDefinitelyNotNullOrNotNull(KotlinTypeMarker kotlinTypeMarker0) {
        return DefaultImpls.makeDefinitelyNotNullOrNotNull(this, kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext
    public KotlinTypeMarker makeNullable(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        SimpleTypeMarker simpleTypeMarker0 = this.asSimpleType(kotlinTypeMarker0);
        if(simpleTypeMarker0 != null) {
            SimpleTypeMarker simpleTypeMarker1 = this.withNullability(simpleTypeMarker0, true);
            if(simpleTypeMarker1 != null) {
                return simpleTypeMarker1;
            }
        }
        return kotlinTypeMarker0;
    }

    public TypeCheckerState newTypeCheckerState(boolean z, boolean z1) {
        return DefaultImpls.newTypeCheckerState(this, z, z1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker original(DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker0) {
        return DefaultImpls.original(this, definitelyNotNullTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker originalIfDefinitelyNotNullable(SimpleTypeMarker simpleTypeMarker0) {
        Intrinsics.checkNotNullParameter(simpleTypeMarker0, "<this>");
        DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker0 = this.asDefinitelyNotNullType(simpleTypeMarker0);
        if(definitelyNotNullTypeMarker0 != null) {
            SimpleTypeMarker simpleTypeMarker1 = this.original(definitelyNotNullTypeMarker0);
            return simpleTypeMarker1 == null ? simpleTypeMarker0 : simpleTypeMarker1;
        }
        return simpleTypeMarker0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int parametersCount(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.parametersCount(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public Collection possibleIntegerTypes(SimpleTypeMarker simpleTypeMarker0) {
        return DefaultImpls.possibleIntegerTypes(this, simpleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeArgumentMarker projection(CapturedTypeConstructorMarker capturedTypeConstructorMarker0) {
        return DefaultImpls.projection(this, capturedTypeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public int size(TypeArgumentListMarker typeArgumentListMarker0) {
        Intrinsics.checkNotNullParameter(typeArgumentListMarker0, "<this>");
        if(typeArgumentListMarker0 instanceof SimpleTypeMarker) {
            return this.argumentsCount(((KotlinTypeMarker)typeArgumentListMarker0));
        }
        if(!(typeArgumentListMarker0 instanceof ArgumentList)) {
            throw new IllegalStateException(("unknown type argument list type: " + typeArgumentListMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeArgumentListMarker0.getClass())).toString());
        }
        return ((ArgumentList)typeArgumentListMarker0).size();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SupertypesPolicy substitutionSupertypePolicy(SimpleTypeMarker simpleTypeMarker0) {
        return DefaultImpls.substitutionSupertypePolicy(this, simpleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public Collection supertypes(TypeConstructorMarker typeConstructorMarker0) {
        return DefaultImpls.supertypes(this, typeConstructorMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public CapturedTypeConstructorMarker typeConstructor(CapturedTypeMarker capturedTypeMarker0) {
        return DefaultImpls.typeConstructor(this, capturedTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public TypeConstructorMarker typeConstructor(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        SimpleTypeMarker simpleTypeMarker0 = this.asSimpleType(kotlinTypeMarker0);
        if(simpleTypeMarker0 == null) {
            simpleTypeMarker0 = this.lowerBoundIfFlexible(kotlinTypeMarker0);
        }
        return this.typeConstructor(simpleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    public TypeConstructorMarker typeConstructor(SimpleTypeMarker simpleTypeMarker0) {
        return DefaultImpls.typeConstructor(this, simpleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    public SimpleTypeMarker upperBound(FlexibleTypeMarker flexibleTypeMarker0) {
        return DefaultImpls.upperBound(this, flexibleTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public SimpleTypeMarker upperBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        FlexibleTypeMarker flexibleTypeMarker0 = this.asFlexibleType(kotlinTypeMarker0);
        if(flexibleTypeMarker0 != null) {
            SimpleTypeMarker simpleTypeMarker0 = this.upperBound(flexibleTypeMarker0);
            if(simpleTypeMarker0 != null) {
                return simpleTypeMarker0;
            }
        }
        SimpleTypeMarker simpleTypeMarker1 = this.asSimpleType(kotlinTypeMarker0);
        Intrinsics.checkNotNull(simpleTypeMarker1);
        return simpleTypeMarker1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    public KotlinTypeMarker withNullability(KotlinTypeMarker kotlinTypeMarker0, boolean z) {
        return DefaultImpls.withNullability(this, kotlinTypeMarker0, z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext
    public SimpleTypeMarker withNullability(SimpleTypeMarker simpleTypeMarker0, boolean z) {
        return DefaultImpls.withNullability(this, simpleTypeMarker0, z);
    }
}

