package kotlin.reflect.jvm.internal.impl.types.typeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.AbstractStubType;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.StubTypeForBuilderInference;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorType;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;

public final class TypeUtilsKt {
    public static final TypeProjection asTypeProjection(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return new TypeProjectionImpl(kotlinType0);
    }

    public static final boolean contains(KotlinType kotlinType0, Function1 function10) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        return TypeUtils.contains(kotlinType0, function10);
    }

    private static final boolean containsSelfTypeParameter(KotlinType kotlinType0, TypeConstructor typeConstructor0, Set set0) {
        boolean z;
        if(Intrinsics.areEqual(kotlinType0.getConstructor(), typeConstructor0)) {
            return true;
        }
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters0 = classifierDescriptor0 instanceof ClassifierDescriptorWithTypeParameters ? ((ClassifierDescriptorWithTypeParameters)classifierDescriptor0) : null;
        List list0 = classifierDescriptorWithTypeParameters0 == null ? null : classifierDescriptorWithTypeParameters0.getDeclaredTypeParameters();
        Iterable iterable0 = CollectionsKt.withIndex(kotlinType0.getArguments());
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return false;
        }
        for(Object object0: iterable0) {
            int v = ((IndexedValue)object0).component1();
            TypeProjection typeProjection0 = (TypeProjection)((IndexedValue)object0).component2();
            TypeParameterDescriptor typeParameterDescriptor0 = list0 == null ? null : ((TypeParameterDescriptor)CollectionsKt.getOrNull(list0, v));
            if((typeParameterDescriptor0 == null || set0 == null || !set0.contains(typeParameterDescriptor0)) && !typeProjection0.isStarProjection()) {
                KotlinType kotlinType1 = typeProjection0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType1, "argument.type");
                z = TypeUtilsKt.containsSelfTypeParameter(kotlinType1, typeConstructor0, set0);
            }
            else {
                z = false;
            }
            if(z) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public static final boolean containsTypeAliasParameters(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return TypeUtilsKt.contains(kotlinType0, kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeAliasParameters.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeAliasParameters.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeAliasParameters.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeAliasParameters.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeAliasParameters.1();
            }

            kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeAliasParameters.1() {
                super(1);
            }

            public final Boolean invoke(UnwrappedType unwrappedType0) {
                Intrinsics.checkNotNullParameter(unwrappedType0, "it");
                ClassifierDescriptor classifierDescriptor0 = unwrappedType0.getConstructor().getDeclarationDescriptor();
                return classifierDescriptor0 == null ? false : Boolean.valueOf(TypeUtilsKt.isTypeAliasParameter(classifierDescriptor0));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((UnwrappedType)object0));
            }
        }

    }

    public static final boolean containsTypeParameter(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return TypeUtils.contains(kotlinType0, kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeParameter.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeParameter.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeParameter.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeParameter.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeParameter.1();
            }

            kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeParameter.1() {
                super(1);
            }

            public final Boolean invoke(UnwrappedType unwrappedType0) {
                return Boolean.valueOf(TypeUtils.isTypeParameter(unwrappedType0));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((UnwrappedType)object0));
            }
        }

    }

    public static final TypeProjection createProjection(KotlinType kotlinType0, Variance variance0, TypeParameterDescriptor typeParameterDescriptor0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        Intrinsics.checkNotNullParameter(variance0, "projectionKind");
        if((typeParameterDescriptor0 == null ? null : typeParameterDescriptor0.getVariance()) == variance0) {
            variance0 = Variance.INVARIANT;
        }
        return new TypeProjectionImpl(variance0, kotlinType0);
    }

    public static final Set extractTypeParametersFromUpperBounds(KotlinType kotlinType0, Set set0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        Set set1 = new LinkedHashSet();
        TypeUtilsKt.extractTypeParametersFromUpperBounds(kotlinType0, kotlinType0, set1, set0);
        return set1;
    }

    private static final void extractTypeParametersFromUpperBounds(KotlinType kotlinType0, KotlinType kotlinType1, Set set0, Set set1) {
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        if(classifierDescriptor0 instanceof TypeParameterDescriptor) {
            if(!Intrinsics.areEqual(kotlinType0.getConstructor(), kotlinType1.getConstructor())) {
                set0.add(classifierDescriptor0);
                return;
            }
            for(Object object0: ((TypeParameterDescriptor)classifierDescriptor0).getUpperBounds()) {
                Intrinsics.checkNotNullExpressionValue(((KotlinType)object0), "upperBound");
                TypeUtilsKt.extractTypeParametersFromUpperBounds(((KotlinType)object0), kotlinType1, set0, set1);
            }
            return;
        }
        ClassifierDescriptor classifierDescriptor1 = kotlinType0.getConstructor().getDeclarationDescriptor();
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters0 = classifierDescriptor1 instanceof ClassifierDescriptorWithTypeParameters ? ((ClassifierDescriptorWithTypeParameters)classifierDescriptor1) : null;
        List list0 = classifierDescriptorWithTypeParameters0 == null ? null : classifierDescriptorWithTypeParameters0.getDeclaredTypeParameters();
        int v = 0;
        for(Object object1: kotlinType0.getArguments()) {
            TypeProjection typeProjection0 = (TypeProjection)object1;
            TypeParameterDescriptor typeParameterDescriptor0 = list0 == null ? null : ((TypeParameterDescriptor)CollectionsKt.getOrNull(list0, v));
            if((typeParameterDescriptor0 == null || set1 == null || !set1.contains(typeParameterDescriptor0)) && !typeProjection0.isStarProjection() && !CollectionsKt.contains(set0, typeProjection0.getType().getConstructor().getDeclarationDescriptor()) && !Intrinsics.areEqual(typeProjection0.getType().getConstructor(), kotlinType1.getConstructor())) {
                KotlinType kotlinType2 = typeProjection0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType2, "argument.type");
                TypeUtilsKt.extractTypeParametersFromUpperBounds(kotlinType2, kotlinType1, set0, set1);
            }
            ++v;
        }
    }

    public static final KotlinBuiltIns getBuiltIns(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        KotlinBuiltIns kotlinBuiltIns0 = kotlinType0.getConstructor().getBuiltIns();
        Intrinsics.checkNotNullExpressionValue(kotlinBuiltIns0, "constructor.builtIns");
        return kotlinBuiltIns0;
    }

    public static final KotlinType getRepresentativeUpperBound(TypeParameterDescriptor typeParameterDescriptor0) {
        ClassDescriptor classDescriptor0;
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "<this>");
        List list0 = typeParameterDescriptor0.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(list0, "upperBounds");
        list0.isEmpty();
        List list1 = typeParameterDescriptor0.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(list1, "upperBounds");
        Iterator iterator0 = list1.iterator();
        while(true) {
            classDescriptor0 = null;
            if(!iterator0.hasNext()) {
                break;
            }
            Object object0 = iterator0.next();
            ClassifierDescriptor classifierDescriptor0 = ((KotlinType)object0).getConstructor().getDeclarationDescriptor();
            if(classifierDescriptor0 instanceof ClassDescriptor) {
                classDescriptor0 = (ClassDescriptor)classifierDescriptor0;
            }
            if(classDescriptor0 != null && classDescriptor0.getKind() != ClassKind.INTERFACE && classDescriptor0.getKind() != ClassKind.ANNOTATION_CLASS) {
                classDescriptor0 = object0;
                break;
            }
        }
        if(((KotlinType)classDescriptor0) == null) {
            List list2 = typeParameterDescriptor0.getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(list2, "upperBounds");
            Object object1 = CollectionsKt.first(list2);
            Intrinsics.checkNotNullExpressionValue(object1, "upperBounds.first()");
            return (KotlinType)object1;
        }
        return (KotlinType)classDescriptor0;
    }

    public static final boolean hasTypeParameterRecursiveBounds(TypeParameterDescriptor typeParameterDescriptor0) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "typeParameter");
        return TypeUtilsKt.hasTypeParameterRecursiveBounds$default(typeParameterDescriptor0, null, null, 6, null);
    }

    public static final boolean hasTypeParameterRecursiveBounds(TypeParameterDescriptor typeParameterDescriptor0, TypeConstructor typeConstructor0, Set set0) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "typeParameter");
        List list0 = typeParameterDescriptor0.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(list0, "typeParameter.upperBounds");
        if(list0 instanceof Collection && list0.isEmpty()) {
            return false;
        }
        for(Object object0: list0) {
            Intrinsics.checkNotNullExpressionValue(((KotlinType)object0), "upperBound");
            if(TypeUtilsKt.containsSelfTypeParameter(((KotlinType)object0), typeParameterDescriptor0.getDefaultType().getConstructor(), set0) && (typeConstructor0 == null || Intrinsics.areEqual(((KotlinType)object0).getConstructor(), typeConstructor0))) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public static boolean hasTypeParameterRecursiveBounds$default(TypeParameterDescriptor typeParameterDescriptor0, TypeConstructor typeConstructor0, Set set0, int v, Object object0) {
        if((v & 2) != 0) {
            typeConstructor0 = null;
        }
        if((v & 4) != 0) {
            set0 = null;
        }
        return TypeUtilsKt.hasTypeParameterRecursiveBounds(typeParameterDescriptor0, typeConstructor0, set0);
    }

    public static final boolean isBoolean(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return KotlinBuiltIns.isBoolean(kotlinType0);
    }

    public static final boolean isNothing(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return KotlinBuiltIns.isNothing(kotlinType0);
    }

    public static final boolean isStubType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return kotlinType0 instanceof AbstractStubType || kotlinType0 instanceof DefinitelyNotNullType && ((DefinitelyNotNullType)kotlinType0).getOriginal() instanceof AbstractStubType;
    }

    public static final boolean isStubTypeForBuilderInference(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return kotlinType0 instanceof StubTypeForBuilderInference || kotlinType0 instanceof DefinitelyNotNullType && ((DefinitelyNotNullType)kotlinType0).getOriginal() instanceof StubTypeForBuilderInference;
    }

    public static final boolean isSubtypeOf(KotlinType kotlinType0, KotlinType kotlinType1) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        Intrinsics.checkNotNullParameter(kotlinType1, "superType");
        return KotlinTypeChecker.DEFAULT.isSubtypeOf(kotlinType0, kotlinType1);
    }

    public static final boolean isTypeAliasParameter(ClassifierDescriptor classifierDescriptor0) {
        Intrinsics.checkNotNullParameter(classifierDescriptor0, "<this>");
        return classifierDescriptor0 instanceof TypeParameterDescriptor && ((TypeParameterDescriptor)classifierDescriptor0).getContainingDeclaration() instanceof TypeAliasDescriptor;
    }

    public static final boolean isTypeParameter(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return TypeUtils.isTypeParameter(kotlinType0);
    }

    public static final boolean isUnresolvedType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        return kotlinType0 instanceof ErrorType && ((ErrorType)kotlinType0).getKind().isUnresolved();
    }

    public static final KotlinType makeNotNullable(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        KotlinType kotlinType1 = TypeUtils.makeNotNullable(kotlinType0);
        Intrinsics.checkNotNullExpressionValue(kotlinType1, "makeNotNullable(this)");
        return kotlinType1;
    }

    public static final KotlinType makeNullable(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        KotlinType kotlinType1 = TypeUtils.makeNullable(kotlinType0);
        Intrinsics.checkNotNullExpressionValue(kotlinType1, "makeNullable(this)");
        return kotlinType1;
    }

    public static final KotlinType replaceAnnotations(KotlinType kotlinType0, Annotations annotations0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        Intrinsics.checkNotNullParameter(annotations0, "newAnnotations");
        return kotlinType0.getAnnotations().isEmpty() && annotations0.isEmpty() ? kotlinType0 : kotlinType0.unwrap().replaceAttributes(TypeAttributesKt.replaceAnnotations(kotlinType0.getAttributes(), annotations0));
    }

    public static final KotlinType replaceArgumentsWithStarProjections(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        if(unwrappedType0 instanceof FlexibleType) {
            SimpleType simpleType0 = ((FlexibleType)unwrappedType0).getLowerBound();
            if(!simpleType0.getConstructor().getParameters().isEmpty() && simpleType0.getConstructor().getDeclarationDescriptor() != null) {
                List list0 = simpleType0.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(list0, "constructor.parameters");
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                for(Object object0: list0) {
                    arrayList0.add(new StarProjectionImpl(((TypeParameterDescriptor)object0)));
                }
                simpleType0 = TypeSubstitutionKt.replace$default(simpleType0, arrayList0, null, 2, null);
            }
            SimpleType simpleType1 = ((FlexibleType)unwrappedType0).getUpperBound();
            if(!simpleType1.getConstructor().getParameters().isEmpty() && simpleType1.getConstructor().getDeclarationDescriptor() != null) {
                List list1 = simpleType1.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(list1, "constructor.parameters");
                ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
                for(Object object1: list1) {
                    arrayList1.add(new StarProjectionImpl(((TypeParameterDescriptor)object1)));
                }
                simpleType1 = TypeSubstitutionKt.replace$default(simpleType1, arrayList1, null, 2, null);
            }
            return TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(simpleType0, simpleType1), unwrappedType0);
        }
        if(!(unwrappedType0 instanceof SimpleType)) {
            throw new NoWhenBranchMatchedException();
        }
        SimpleType simpleType2 = (SimpleType)unwrappedType0;
        if(!simpleType2.getConstructor().getParameters().isEmpty() && simpleType2.getConstructor().getDeclarationDescriptor() != null) {
            List list2 = simpleType2.getConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(list2, "constructor.parameters");
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for(Object object2: list2) {
                arrayList2.add(new StarProjectionImpl(((TypeParameterDescriptor)object2)));
            }
            simpleType2 = TypeSubstitutionKt.replace$default(simpleType2, arrayList2, null, 2, null);
        }
        return TypeWithEnhancementKt.inheritEnhancement(simpleType2, unwrappedType0);
    }

    public static final boolean requiresTypeAliasExpansion(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return TypeUtilsKt.contains(kotlinType0, kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.requiresTypeAliasExpansion.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.requiresTypeAliasExpansion.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.requiresTypeAliasExpansion.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.requiresTypeAliasExpansion.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.requiresTypeAliasExpansion.1();
            }

            kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.requiresTypeAliasExpansion.1() {
                super(1);
            }

            public final Boolean invoke(UnwrappedType unwrappedType0) {
                Intrinsics.checkNotNullParameter(unwrappedType0, "it");
                ClassifierDescriptor classifierDescriptor0 = unwrappedType0.getConstructor().getDeclarationDescriptor();
                return Boolean.valueOf(classifierDescriptor0 != null && (classifierDescriptor0 instanceof TypeAliasDescriptor || classifierDescriptor0 instanceof TypeParameterDescriptor));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((UnwrappedType)object0));
            }
        }

    }

    public static final boolean shouldBeUpdated(KotlinType kotlinType0) {
        return kotlinType0 == null || TypeUtilsKt.contains(kotlinType0, kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.shouldBeUpdated.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.shouldBeUpdated.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.shouldBeUpdated.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.shouldBeUpdated.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.shouldBeUpdated.1();
            }

            kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.shouldBeUpdated.1() {
                super(1);
            }

            public final Boolean invoke(UnwrappedType unwrappedType0) {
                Intrinsics.checkNotNullParameter(unwrappedType0, "it");
                return unwrappedType0 instanceof StubTypeForBuilderInference || unwrappedType0.getConstructor() instanceof TypeVariableTypeConstructorMarker || KotlinTypeKt.isError(unwrappedType0);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((UnwrappedType)object0));
            }
        }

    }
}

