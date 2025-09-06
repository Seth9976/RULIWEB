package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.types.checker.IntersectionTypeKt;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class TypeParameterUpperBoundEraser {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final KotlinType replaceArgumentsOfUpperBound(KotlinType kotlinType0, TypeSubstitutor typeSubstitutor0, Set set0, boolean z) {
            UnwrappedType unwrappedType1;
            Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
            Intrinsics.checkNotNullParameter(typeSubstitutor0, "substitutor");
            UnwrappedType unwrappedType0 = kotlinType0.unwrap();
            if(unwrappedType0 instanceof FlexibleType) {
                SimpleType simpleType0 = ((FlexibleType)unwrappedType0).getLowerBound();
                if(!simpleType0.getConstructor().getParameters().isEmpty() && simpleType0.getConstructor().getDeclarationDescriptor() != null) {
                    List list0 = simpleType0.getConstructor().getParameters();
                    Intrinsics.checkNotNullExpressionValue(list0, "constructor.parameters");
                    ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                    for(Object object0: list0) {
                        TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)object0;
                        TypeProjection typeProjection0 = (TypeProjection)CollectionsKt.getOrNull(kotlinType0.getArguments(), typeParameterDescriptor0.getIndex());
                        if(!z || typeProjection0 == null) {
                        label_19:
                            if(typeProjection0 == null || set0 != null && set0.contains(typeParameterDescriptor0)) {
                                typeProjection0 = new StarProjectionImpl(typeParameterDescriptor0);
                            }
                            else {
                                TypeSubstitution typeSubstitution0 = typeSubstitutor0.getSubstitution();
                                KotlinType kotlinType2 = typeProjection0.getType();
                                Intrinsics.checkNotNullExpressionValue(kotlinType2, "argument.type");
                                if(typeSubstitution0.get(kotlinType2) == null) {
                                    typeProjection0 = new StarProjectionImpl(typeParameterDescriptor0);
                                }
                            }
                        }
                        else {
                            KotlinType kotlinType1 = typeProjection0.getType();
                            if(kotlinType1 == null) {
                                goto label_19;
                            }
                            else {
                                Intrinsics.checkNotNullExpressionValue(kotlinType1, "type");
                                if(TypeUtilsKt.containsTypeParameter(kotlinType1)) {
                                    goto label_19;
                                }
                            }
                        }
                        arrayList0.add(typeProjection0);
                    }
                    simpleType0 = TypeSubstitutionKt.replace$default(simpleType0, arrayList0, null, 2, null);
                }
                SimpleType simpleType1 = ((FlexibleType)unwrappedType0).getUpperBound();
                if(!simpleType1.getConstructor().getParameters().isEmpty() && simpleType1.getConstructor().getDeclarationDescriptor() != null) {
                    List list1 = simpleType1.getConstructor().getParameters();
                    Intrinsics.checkNotNullExpressionValue(list1, "constructor.parameters");
                    ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
                    for(Object object1: list1) {
                        TypeParameterDescriptor typeParameterDescriptor1 = (TypeParameterDescriptor)object1;
                        TypeProjection typeProjection1 = (TypeProjection)CollectionsKt.getOrNull(kotlinType0.getArguments(), typeParameterDescriptor1.getIndex());
                        if(!z || typeProjection1 == null) {
                        label_45:
                            if(typeProjection1 == null || set0 != null && set0.contains(typeParameterDescriptor1)) {
                                typeProjection1 = new StarProjectionImpl(typeParameterDescriptor1);
                            }
                            else {
                                TypeSubstitution typeSubstitution1 = typeSubstitutor0.getSubstitution();
                                KotlinType kotlinType4 = typeProjection1.getType();
                                Intrinsics.checkNotNullExpressionValue(kotlinType4, "argument.type");
                                if(typeSubstitution1.get(kotlinType4) == null) {
                                    typeProjection1 = new StarProjectionImpl(typeParameterDescriptor1);
                                }
                            }
                        }
                        else {
                            KotlinType kotlinType3 = typeProjection1.getType();
                            if(kotlinType3 == null) {
                                goto label_45;
                            }
                            else {
                                Intrinsics.checkNotNullExpressionValue(kotlinType3, "type");
                                if(TypeUtilsKt.containsTypeParameter(kotlinType3)) {
                                    goto label_45;
                                }
                            }
                        }
                        arrayList1.add(typeProjection1);
                    }
                    simpleType1 = TypeSubstitutionKt.replace$default(simpleType1, arrayList1, null, 2, null);
                }
                unwrappedType1 = KotlinTypeFactory.flexibleType(simpleType0, simpleType1);
            }
            else if(unwrappedType0 instanceof SimpleType) {
                SimpleType simpleType2 = (SimpleType)unwrappedType0;
                if(!simpleType2.getConstructor().getParameters().isEmpty() && simpleType2.getConstructor().getDeclarationDescriptor() != null) {
                    List list2 = simpleType2.getConstructor().getParameters();
                    Intrinsics.checkNotNullExpressionValue(list2, "constructor.parameters");
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    for(Object object2: list2) {
                        TypeParameterDescriptor typeParameterDescriptor2 = (TypeParameterDescriptor)object2;
                        TypeProjection typeProjection2 = (TypeProjection)CollectionsKt.getOrNull(kotlinType0.getArguments(), typeParameterDescriptor2.getIndex());
                        if(!z || typeProjection2 == null) {
                        label_74:
                            if(typeProjection2 == null || set0 != null && set0.contains(typeParameterDescriptor2)) {
                                typeProjection2 = new StarProjectionImpl(typeParameterDescriptor2);
                            }
                            else {
                                TypeSubstitution typeSubstitution2 = typeSubstitutor0.getSubstitution();
                                KotlinType kotlinType6 = typeProjection2.getType();
                                Intrinsics.checkNotNullExpressionValue(kotlinType6, "argument.type");
                                if(typeSubstitution2.get(kotlinType6) == null) {
                                    typeProjection2 = new StarProjectionImpl(typeParameterDescriptor2);
                                }
                            }
                        }
                        else {
                            KotlinType kotlinType5 = typeProjection2.getType();
                            if(kotlinType5 == null) {
                                goto label_74;
                            }
                            else {
                                Intrinsics.checkNotNullExpressionValue(kotlinType5, "type");
                                if(TypeUtilsKt.containsTypeParameter(kotlinType5)) {
                                    goto label_74;
                                }
                            }
                        }
                        arrayList2.add(typeProjection2);
                    }
                    simpleType2 = TypeSubstitutionKt.replace$default(simpleType2, arrayList2, null, 2, null);
                }
                unwrappedType1 = simpleType2;
            }
            else {
                throw new NoWhenBranchMatchedException();
            }
            KotlinType kotlinType7 = typeSubstitutor0.safeSubstitute(TypeWithEnhancementKt.inheritEnhancement(unwrappedType1, unwrappedType0), Variance.OUT_VARIANCE);
            Intrinsics.checkNotNullExpressionValue(kotlinType7, "substitutor.safeSubstitu…s, Variance.OUT_VARIANCE)");
            return kotlinType7;
        }
    }

    static final class DataToEraseUpperBound {
        private final ErasureTypeAttributes typeAttr;
        private final TypeParameterDescriptor typeParameter;

        public DataToEraseUpperBound(TypeParameterDescriptor typeParameterDescriptor0, ErasureTypeAttributes erasureTypeAttributes0) {
            Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "typeParameter");
            Intrinsics.checkNotNullParameter(erasureTypeAttributes0, "typeAttr");
            super();
            this.typeParameter = typeParameterDescriptor0;
            this.typeAttr = erasureTypeAttributes0;
        }

        // 去混淆评级： 低(30)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof DataToEraseUpperBound ? Intrinsics.areEqual(((DataToEraseUpperBound)object0).typeParameter, this.typeParameter) && Intrinsics.areEqual(((DataToEraseUpperBound)object0).typeAttr, this.typeAttr) : false;
        }

        public final ErasureTypeAttributes getTypeAttr() {
            return this.typeAttr;
        }

        public final TypeParameterDescriptor getTypeParameter() {
            return this.typeParameter;
        }

        @Override
        public int hashCode() {
            int v = this.typeParameter.hashCode();
            return v + (v * 0x1F + this.typeAttr.hashCode());
        }

        @Override
        public String toString() {
            return "DataToEraseUpperBound(typeParameter=" + this.typeParameter + ", typeAttr=" + this.typeAttr + ')';
        }
    }

    public static final Companion Companion;
    private final Lazy erroneousErasedBound$delegate;
    private final MemoizedFunctionToNotNull getErasedUpperBound;
    private final TypeParameterErasureOptions options;
    private final ErasureProjectionComputer projectionComputer;
    private final LockBasedStorageManager storage;

    static {
        TypeParameterUpperBoundEraser.Companion = new Companion(null);
    }

    public TypeParameterUpperBoundEraser(ErasureProjectionComputer erasureProjectionComputer0, TypeParameterErasureOptions typeParameterErasureOptions0) {
        Intrinsics.checkNotNullParameter(erasureProjectionComputer0, "projectionComputer");
        Intrinsics.checkNotNullParameter(typeParameterErasureOptions0, "options");
        super();
        this.projectionComputer = erasureProjectionComputer0;
        this.options = typeParameterErasureOptions0;
        LockBasedStorageManager lockBasedStorageManager0 = new LockBasedStorageManager("Type parameter upper bound erasure results");
        this.storage = lockBasedStorageManager0;
        this.erroneousErasedBound$delegate = LazyKt.lazy(new Function0() {
            {
                TypeParameterUpperBoundEraser.this = typeParameterUpperBoundEraser0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final ErrorType invoke() {
                return ErrorUtils.createErrorType(ErrorTypeKind.CANNOT_COMPUTE_ERASED_BOUND, new String[]{TypeParameterUpperBoundEraser.this.toString()});
            }
        });
        MemoizedFunctionToNotNull memoizedFunctionToNotNull0 = lockBasedStorageManager0.createMemoizedFunction(new Function1() {
            {
                TypeParameterUpperBoundEraser.this = typeParameterUpperBoundEraser0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((DataToEraseUpperBound)object0));
            }

            public final KotlinType invoke(DataToEraseUpperBound typeParameterUpperBoundEraser$DataToEraseUpperBound0) {
                return TypeParameterUpperBoundEraser.this.getErasedUpperBoundInternal(typeParameterUpperBoundEraser$DataToEraseUpperBound0.getTypeParameter(), typeParameterUpperBoundEraser$DataToEraseUpperBound0.getTypeAttr());
            }
        });
        Intrinsics.checkNotNullExpressionValue(memoizedFunctionToNotNull0, "storage.createMemoizedFu…ameter, typeAttr) }\n    }");
        this.getErasedUpperBound = memoizedFunctionToNotNull0;
    }

    public TypeParameterUpperBoundEraser(ErasureProjectionComputer erasureProjectionComputer0, TypeParameterErasureOptions typeParameterErasureOptions0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            typeParameterErasureOptions0 = new TypeParameterErasureOptions(false, false);
        }
        this(erasureProjectionComputer0, typeParameterErasureOptions0);
    }

    private final KotlinType getDefaultType(ErasureTypeAttributes erasureTypeAttributes0) {
        SimpleType simpleType0 = erasureTypeAttributes0.getDefaultType();
        if(simpleType0 != null) {
            KotlinType kotlinType0 = TypeUtilsKt.replaceArgumentsWithStarProjections(simpleType0);
            if(kotlinType0 != null) {
                return kotlinType0;
            }
        }
        return this.getErroneousErasedBound();
    }

    public final KotlinType getErasedUpperBound(TypeParameterDescriptor typeParameterDescriptor0, ErasureTypeAttributes erasureTypeAttributes0) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "typeParameter");
        Intrinsics.checkNotNullParameter(erasureTypeAttributes0, "typeAttr");
        DataToEraseUpperBound typeParameterUpperBoundEraser$DataToEraseUpperBound0 = new DataToEraseUpperBound(typeParameterDescriptor0, erasureTypeAttributes0);
        Object object0 = this.getErasedUpperBound.invoke(typeParameterUpperBoundEraser$DataToEraseUpperBound0);
        Intrinsics.checkNotNullExpressionValue(object0, "getErasedUpperBound(Data…typeParameter, typeAttr))");
        return (KotlinType)object0;
    }

    private final KotlinType getErasedUpperBoundInternal(TypeParameterDescriptor typeParameterDescriptor0, ErasureTypeAttributes erasureTypeAttributes0) {
        TypeProjection typeProjection0;
        Set set0 = erasureTypeAttributes0.getVisitedTypeParameters();
        if(set0 != null && set0.contains(typeParameterDescriptor0.getOriginal())) {
            return this.getDefaultType(erasureTypeAttributes0);
        }
        SimpleType simpleType0 = typeParameterDescriptor0.getDefaultType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "typeParameter.defaultType");
        Iterable iterable0 = TypeUtilsKt.extractTypeParametersFromUpperBounds(simpleType0, set0);
        Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable0, 10)), 16));
        for(Object object0: iterable0) {
            TypeParameterDescriptor typeParameterDescriptor1 = (TypeParameterDescriptor)object0;
            if(set0 == null || !set0.contains(typeParameterDescriptor1)) {
                KotlinType kotlinType0 = this.getErasedUpperBound(typeParameterDescriptor1, erasureTypeAttributes0.withNewVisitedTypeParameter(typeParameterDescriptor0));
                typeProjection0 = this.projectionComputer.computeProjection(typeParameterDescriptor1, erasureTypeAttributes0, this, kotlinType0);
            }
            else {
                typeProjection0 = TypeUtils.makeStarProjection(typeParameterDescriptor1, erasureTypeAttributes0);
                Intrinsics.checkNotNullExpressionValue(typeProjection0, "makeStarProjection(it, typeAttr)");
            }
            Pair pair0 = TuplesKt.to(typeParameterDescriptor1.getTypeConstructor(), typeProjection0);
            map0.put(pair0.getFirst(), pair0.getSecond());
        }
        TypeSubstitutor typeSubstitutor0 = TypeSubstitutor.create(kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution.Companion.createByConstructorsMap$default(TypeConstructorSubstitution.Companion, map0, false, 2, null));
        Intrinsics.checkNotNullExpressionValue(typeSubstitutor0, "create(TypeConstructorSu…ap(erasedTypeParameters))");
        List list0 = typeParameterDescriptor0.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(list0, "typeParameter.upperBounds");
        Set set1 = this.substituteErasedUpperBounds(typeSubstitutor0, list0, erasureTypeAttributes0);
        if(!set1.isEmpty()) {
            if(!this.options.getIntersectUpperBounds()) {
                if(set1.size() != 1) {
                    throw new IllegalArgumentException("Should only be one computed upper bound if no need to intersect all bounds");
                }
                return (KotlinType)CollectionsKt.single(set1);
            }
            Iterable iterable1 = CollectionsKt.toList(set1);
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable1, 10));
            for(Object object1: iterable1) {
                arrayList0.add(((KotlinType)object1).unwrap());
            }
            return IntersectionTypeKt.intersectTypes(arrayList0);
        }
        return this.getDefaultType(erasureTypeAttributes0);
    }

    private final ErrorType getErroneousErasedBound() {
        return (ErrorType)this.erroneousErasedBound$delegate.getValue();
    }

    private final Set substituteErasedUpperBounds(TypeSubstitutor typeSubstitutor0, List list0, ErasureTypeAttributes erasureTypeAttributes0) {
        Set set0 = SetsKt.createSetBuilder();
        for(Object object0: list0) {
            KotlinType kotlinType0 = (KotlinType)object0;
            ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
            if(classifierDescriptor0 instanceof ClassDescriptor) {
                Set set1 = erasureTypeAttributes0.getVisitedTypeParameters();
                set0.add(TypeParameterUpperBoundEraser.Companion.replaceArgumentsOfUpperBound(kotlinType0, typeSubstitutor0, set1, this.options.getLeaveNonTypeParameterTypes()));
            }
            else if(classifierDescriptor0 instanceof TypeParameterDescriptor) {
                Set set2 = erasureTypeAttributes0.getVisitedTypeParameters();
                if(set2 == null || !set2.contains(classifierDescriptor0)) {
                    List list1 = ((TypeParameterDescriptor)classifierDescriptor0).getUpperBounds();
                    Intrinsics.checkNotNullExpressionValue(list1, "declaration.upperBounds");
                    set0.addAll(this.substituteErasedUpperBounds(typeSubstitutor0, list1, erasureTypeAttributes0));
                }
                else {
                    set0.add(this.getDefaultType(erasureTypeAttributes0));
                }
            }
            if(!this.options.getIntersectUpperBounds()) {
                break;
            }
        }
        return SetsKt.build(set0);
    }
}

