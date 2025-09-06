package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy.FULLY_QUALIFIED;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class CapturedTypeApproximationKt {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Variance.values().length];
            try {
                arr_v[Variance.INVARIANT.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Variance.IN_VARIANCE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Variance.OUT_VARIANCE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final ApproximationBounds approximateCapturedTypes(KotlinType kotlinType0) {
        boolean z;
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        if(FlexibleTypesKt.isFlexible(kotlinType0)) {
            ApproximationBounds approximationBounds0 = CapturedTypeApproximationKt.approximateCapturedTypes(FlexibleTypesKt.lowerIfFlexible(kotlinType0));
            ApproximationBounds approximationBounds1 = CapturedTypeApproximationKt.approximateCapturedTypes(FlexibleTypesKt.upperIfFlexible(kotlinType0));
            return new ApproximationBounds(TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible(((KotlinType)approximationBounds0.getLower())), FlexibleTypesKt.upperIfFlexible(((KotlinType)approximationBounds1.getLower()))), kotlinType0), TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible(((KotlinType)approximationBounds0.getUpper())), FlexibleTypesKt.upperIfFlexible(((KotlinType)approximationBounds1.getUpper()))), kotlinType0));
        }
        TypeConstructor typeConstructor0 = kotlinType0.getConstructor();
        if(CapturedTypeConstructorKt.isCaptured(kotlinType0)) {
            Intrinsics.checkNotNull(typeConstructor0, "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.calls.inference.CapturedTypeConstructor");
            TypeProjection typeProjection0 = ((CapturedTypeConstructor)typeConstructor0).getProjection();
            KotlinType kotlinType1 = typeProjection0.getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType1, "typeProjection.type");
            KotlinType kotlinType2 = CapturedTypeApproximationKt.approximateCapturedTypes$makeNullableIfNeeded(kotlinType1, kotlinType0);
            switch(WhenMappings.$EnumSwitchMapping$0[typeProjection0.getProjectionKind().ordinal()]) {
                case 2: {
                    SimpleType simpleType0 = TypeUtilsKt.getBuiltIns(kotlinType0).getNullableAnyType();
                    Intrinsics.checkNotNullExpressionValue(simpleType0, "type.builtIns.nullableAnyType");
                    return new ApproximationBounds(kotlinType2, simpleType0);
                }
                case 3: {
                    SimpleType simpleType1 = TypeUtilsKt.getBuiltIns(kotlinType0).getNothingType();
                    Intrinsics.checkNotNullExpressionValue(simpleType1, "type.builtIns.nothingType");
                    return new ApproximationBounds(CapturedTypeApproximationKt.approximateCapturedTypes$makeNullableIfNeeded(simpleType1, kotlinType0), kotlinType2);
                }
                default: {
                    throw new AssertionError("Only nontrivial projections should have been captured, not: " + typeProjection0);
                }
            }
        }
        if(!kotlinType0.getArguments().isEmpty() && kotlinType0.getArguments().size() == typeConstructor0.getParameters().size()) {
            ArrayList arrayList0 = new ArrayList();
            ArrayList arrayList1 = new ArrayList();
            Iterable iterable0 = kotlinType0.getArguments();
            List list0 = typeConstructor0.getParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "typeConstructor.parameters");
            Iterator iterator0 = CollectionsKt.zip(iterable0, list0).iterator();
            while(true) {
                z = false;
                if(!iterator0.hasNext()) {
                    break;
                }
                Object object0 = iterator0.next();
                TypeProjection typeProjection1 = (TypeProjection)((Pair)object0).component1();
                TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)((Pair)object0).component2();
                Intrinsics.checkNotNullExpressionValue(typeParameterDescriptor0, "typeParameter");
                TypeArgument typeArgument0 = CapturedTypeApproximationKt.toTypeArgument(typeProjection1, typeParameterDescriptor0);
                if(typeProjection1.isStarProjection()) {
                    arrayList0.add(typeArgument0);
                    arrayList1.add(typeArgument0);
                }
                else {
                    ApproximationBounds approximationBounds2 = CapturedTypeApproximationKt.approximateProjection(typeArgument0);
                    arrayList0.add(((TypeArgument)approximationBounds2.component1()));
                    arrayList1.add(((TypeArgument)approximationBounds2.component2()));
                }
            }
            if(!(arrayList0 instanceof Collection) || !arrayList0.isEmpty()) {
                for(Object object1: arrayList0) {
                    if(!((TypeArgument)object1).isConsistent()) {
                        z = true;
                        break;
                    }
                    if(false) {
                        break;
                    }
                }
            }
            if(z) {
                SimpleType simpleType2 = TypeUtilsKt.getBuiltIns(kotlinType0).getNothingType();
                Intrinsics.checkNotNullExpressionValue(simpleType2, "type.builtIns.nothingType");
                return new ApproximationBounds(simpleType2, CapturedTypeApproximationKt.replaceTypeArguments(kotlinType0, arrayList1));
            }
            return new ApproximationBounds(CapturedTypeApproximationKt.replaceTypeArguments(kotlinType0, arrayList0), CapturedTypeApproximationKt.replaceTypeArguments(kotlinType0, arrayList1));
        }
        return new ApproximationBounds(kotlinType0, kotlinType0);
    }

    private static final KotlinType approximateCapturedTypes$makeNullableIfNeeded(KotlinType kotlinType0, KotlinType kotlinType1) {
        KotlinType kotlinType2 = TypeUtils.makeNullableIfNeeded(kotlinType0, kotlinType1.isMarkedNullable());
        Intrinsics.checkNotNullExpressionValue(kotlinType2, "makeNullableIfNeeded(this, type.isMarkedNullable)");
        return kotlinType2;
    }

    public static final TypeProjection approximateCapturedTypesIfNecessary(TypeProjection typeProjection0, boolean z) {
        if(typeProjection0 == null) {
            return null;
        }
        if(!typeProjection0.isStarProjection()) {
            KotlinType kotlinType0 = typeProjection0.getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType0, "typeProjection.type");
            if(TypeUtils.contains(kotlinType0, kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary.1.INSTANCE)) {
                Variance variance0 = typeProjection0.getProjectionKind();
                Intrinsics.checkNotNullExpressionValue(variance0, "typeProjection.projectionKind");
                if(variance0 == Variance.OUT_VARIANCE) {
                    return new TypeProjectionImpl(variance0, ((KotlinType)CapturedTypeApproximationKt.approximateCapturedTypes(kotlinType0).getUpper()));
                }
                return z ? new TypeProjectionImpl(variance0, ((KotlinType)CapturedTypeApproximationKt.approximateCapturedTypes(kotlinType0).getLower())) : CapturedTypeApproximationKt.substituteCapturedTypesWithProjections(typeProjection0);
            }
        }
        return typeProjection0;

        final class kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary.1();
            }

            kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.approximateCapturedTypesIfNecessary.1() {
                super(1);
            }

            public final Boolean invoke(UnwrappedType unwrappedType0) {
                Intrinsics.checkNotNullExpressionValue(unwrappedType0, "it");
                return Boolean.valueOf(CapturedTypeConstructorKt.isCaptured(unwrappedType0));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((UnwrappedType)object0));
            }
        }

    }

    private static final ApproximationBounds approximateProjection(TypeArgument typeArgument0) {
        ApproximationBounds approximationBounds0 = CapturedTypeApproximationKt.approximateCapturedTypes(typeArgument0.getInProjection());
        ApproximationBounds approximationBounds1 = CapturedTypeApproximationKt.approximateCapturedTypes(typeArgument0.getOutProjection());
        return new ApproximationBounds(new TypeArgument(typeArgument0.getTypeParameter(), ((KotlinType)approximationBounds0.component2()), ((KotlinType)approximationBounds1.component1())), new TypeArgument(typeArgument0.getTypeParameter(), ((KotlinType)approximationBounds0.component1()), ((KotlinType)approximationBounds1.component2())));
    }

    private static final KotlinType replaceTypeArguments(KotlinType kotlinType0, List list0) {
        kotlinType0.getArguments().size();
        list0.size();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(CapturedTypeApproximationKt.toTypeProjection(((TypeArgument)object0)));
        }
        return TypeSubstitutionKt.replace$default(kotlinType0, arrayList0, null, null, 6, null);
    }

    private static final TypeProjection substituteCapturedTypesWithProjections(TypeProjection typeProjection0) {
        TypeSubstitutor typeSubstitutor0 = TypeSubstitutor.create(new TypeConstructorSubstitution() {
            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
            public TypeProjection get(TypeConstructor typeConstructor0) {
                Intrinsics.checkNotNullParameter(typeConstructor0, "key");
                CapturedTypeConstructor capturedTypeConstructor0 = typeConstructor0 instanceof CapturedTypeConstructor ? ((CapturedTypeConstructor)typeConstructor0) : null;
                if(capturedTypeConstructor0 == null) {
                    return null;
                }
                if(capturedTypeConstructor0.getProjection().isStarProjection()) {
                    KotlinType kotlinType0 = capturedTypeConstructor0.getProjection().getType();
                    return new TypeProjectionImpl(Variance.OUT_VARIANCE, kotlinType0);
                }
                return capturedTypeConstructor0.getProjection();
            }
        });
        Intrinsics.checkNotNullExpressionValue(typeSubstitutor0, "create(object : TypeCons…ojection\n        }\n    })");
        return typeSubstitutor0.substituteWithoutApproximation(typeProjection0);
    }

    private static final TypeArgument toTypeArgument(TypeProjection typeProjection0, TypeParameterDescriptor typeParameterDescriptor0) {
        switch(WhenMappings.$EnumSwitchMapping$0[TypeSubstitutor.combine(typeParameterDescriptor0.getVariance(), typeProjection0).ordinal()]) {
            case 1: {
                KotlinType kotlinType0 = typeProjection0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType0, "type");
                KotlinType kotlinType1 = typeProjection0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType1, "type");
                return new TypeArgument(typeParameterDescriptor0, kotlinType0, kotlinType1);
            }
            case 2: {
                KotlinType kotlinType2 = typeProjection0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType2, "type");
                SimpleType simpleType0 = DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor0).getNullableAnyType();
                Intrinsics.checkNotNullExpressionValue(simpleType0, "typeParameter.builtIns.nullableAnyType");
                return new TypeArgument(typeParameterDescriptor0, kotlinType2, simpleType0);
            }
            case 3: {
                SimpleType simpleType1 = DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor0).getNothingType();
                Intrinsics.checkNotNullExpressionValue(simpleType1, "typeParameter.builtIns.nothingType");
                KotlinType kotlinType3 = typeProjection0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType3, "type");
                return new TypeArgument(typeParameterDescriptor0, simpleType1, kotlinType3);
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    private static final TypeProjection toTypeProjection(TypeArgument typeArgument0) {
        typeArgument0.isConsistent();
        if(!Intrinsics.areEqual(typeArgument0.getInProjection(), typeArgument0.getOutProjection()) && typeArgument0.getTypeParameter().getVariance() != Variance.IN_VARIANCE) {
            if(KotlinBuiltIns.isNothing(typeArgument0.getInProjection()) && typeArgument0.getTypeParameter().getVariance() != Variance.IN_VARIANCE) {
                return new TypeProjectionImpl(CapturedTypeApproximationKt.toTypeProjection$removeProjectionIfRedundant(typeArgument0, Variance.OUT_VARIANCE), typeArgument0.getOutProjection());
            }
            return KotlinBuiltIns.isNullableAny(typeArgument0.getOutProjection()) ? new TypeProjectionImpl(CapturedTypeApproximationKt.toTypeProjection$removeProjectionIfRedundant(typeArgument0, Variance.IN_VARIANCE), typeArgument0.getInProjection()) : new TypeProjectionImpl(CapturedTypeApproximationKt.toTypeProjection$removeProjectionIfRedundant(typeArgument0, Variance.OUT_VARIANCE), typeArgument0.getOutProjection());
        }
        return new TypeProjectionImpl(typeArgument0.getInProjection());

        final class kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.toTypeProjection.1.descriptorRenderer.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.toTypeProjection.1.descriptorRenderer.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.toTypeProjection.1.descriptorRenderer.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.toTypeProjection.1.descriptorRenderer.1();
            }

            kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt.toTypeProjection.1.descriptorRenderer.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((DescriptorRendererOptions)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(DescriptorRendererOptions descriptorRendererOptions0) {
                Intrinsics.checkNotNullParameter(descriptorRendererOptions0, "$this$withOptions");
                descriptorRendererOptions0.setClassifierNamePolicy(FULLY_QUALIFIED.INSTANCE);
            }
        }

    }

    private static final Variance toTypeProjection$removeProjectionIfRedundant(TypeArgument typeArgument0, Variance variance0) {
        return variance0 == typeArgument0.getTypeParameter().getVariance() ? Variance.INVARIANT : variance0;
    }
}

