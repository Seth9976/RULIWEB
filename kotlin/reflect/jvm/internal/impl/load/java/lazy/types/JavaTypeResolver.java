package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMapper;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifier;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPrimitiveType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypesKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.ErasureTypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeParameterUpperBoundEraser;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeUsage;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class JavaTypeResolver {
    private final LazyJavaResolverContext c;
    private final RawProjectionComputer projectionComputer;
    private final TypeParameterResolver typeParameterResolver;
    private final TypeParameterUpperBoundEraser typeParameterUpperBoundEraser;

    public JavaTypeResolver(LazyJavaResolverContext lazyJavaResolverContext0, TypeParameterResolver typeParameterResolver0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        Intrinsics.checkNotNullParameter(typeParameterResolver0, "typeParameterResolver");
        super();
        this.c = lazyJavaResolverContext0;
        this.typeParameterResolver = typeParameterResolver0;
        RawProjectionComputer rawProjectionComputer0 = new RawProjectionComputer();
        this.projectionComputer = rawProjectionComputer0;
        this.typeParameterUpperBoundEraser = new TypeParameterUpperBoundEraser(rawProjectionComputer0, null, 2, null);
    }

    private final boolean argumentsMakeSenseOnlyForMutableContainer(JavaClassifierType javaClassifierType0, ClassDescriptor classDescriptor0) {
        if(!JavaTypesKt.isSuperWildcard(((JavaType)CollectionsKt.lastOrNull(javaClassifierType0.getTypeArguments())))) {
            return false;
        }
        List list0 = JavaToKotlinClassMapper.INSTANCE.convertReadOnlyToMutable(classDescriptor0).getTypeConstructor().getParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "JavaToKotlinClassMapper.…ypeConstructor.parameters");
        TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)CollectionsKt.lastOrNull(list0);
        if(typeParameterDescriptor0 != null) {
            Variance variance0 = typeParameterDescriptor0.getVariance();
            return variance0 != null && variance0 != Variance.OUT_VARIANCE;
        }
        return false;
    }

    private final List computeArguments(JavaClassifierType javaClassifierType0, JavaTypeAttributes javaTypeAttributes0, TypeConstructor typeConstructor0) {
        boolean z;
        if(javaClassifierType0.isRaw()) {
            z = true;
        }
        else if(javaClassifierType0.getTypeArguments().isEmpty()) {
            List list0 = typeConstructor0.getParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "constructor.parameters");
            z = list0.isEmpty() ? false : true;
        }
        else {
            z = false;
        }
        List list1 = typeConstructor0.getParameters();
        Intrinsics.checkNotNullExpressionValue(list1, "constructor.parameters");
        if(z) {
            return this.computeRawTypeArguments(javaClassifierType0, list1, typeConstructor0, javaTypeAttributes0);
        }
        if(list1.size() != javaClassifierType0.getTypeArguments().size()) {
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
            for(Object object0: list1) {
                String s = ((TypeParameterDescriptor)object0).getName().asString();
                Intrinsics.checkNotNullExpressionValue(s, "p.name.asString()");
                arrayList0.add(new TypeProjectionImpl(ErrorUtils.createErrorType(ErrorTypeKind.MISSED_TYPE_ARGUMENT_FOR_TYPE_PARAMETER, new String[]{s})));
            }
            return CollectionsKt.toList(arrayList0);
        }
        Iterable iterable0 = CollectionsKt.withIndex(javaClassifierType0.getTypeArguments());
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object1: iterable0) {
            int v = ((IndexedValue)object1).component1();
            JavaType javaType0 = (JavaType)((IndexedValue)object1).component2();
            TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)list1.get(v);
            JavaTypeAttributes javaTypeAttributes1 = JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, false, null, 7, null);
            Intrinsics.checkNotNullExpressionValue(typeParameterDescriptor0, "parameter");
            arrayList1.add(this.transformToTypeProjection(javaType0, javaTypeAttributes1, typeParameterDescriptor0));
        }
        return CollectionsKt.toList(arrayList1);
    }

    private final List computeRawTypeArguments(JavaClassifierType javaClassifierType0, List list0, TypeConstructor typeConstructor0, JavaTypeAttributes javaTypeAttributes0) {
        TypeProjection typeProjection0;
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)object0;
            if(TypeUtilsKt.hasTypeParameterRecursiveBounds(typeParameterDescriptor0, null, javaTypeAttributes0.getVisitedTypeParameters())) {
                typeProjection0 = TypeUtils.makeStarProjection(typeParameterDescriptor0, javaTypeAttributes0);
            }
            else {
                LazyWrappedType lazyWrappedType0 = new LazyWrappedType(this.c.getStorageManager(), new Function0(typeParameterDescriptor0, javaTypeAttributes0, typeConstructor0, javaClassifierType0) {
                    final JavaTypeAttributes $attr;
                    final TypeConstructor $constructor;
                    final JavaClassifierType $javaType;
                    final TypeParameterDescriptor $parameter;

                    {
                        JavaTypeResolver.this = javaTypeResolver0;
                        this.$parameter = typeParameterDescriptor0;
                        this.$attr = javaTypeAttributes0;
                        this.$constructor = typeConstructor0;
                        this.$javaType = javaClassifierType0;
                        super(0);
                    }

                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return this.invoke();
                    }

                    public final KotlinType invoke() {
                        TypeParameterUpperBoundEraser typeParameterUpperBoundEraser0 = JavaTypeResolver.this.typeParameterUpperBoundEraser;
                        ClassifierDescriptor classifierDescriptor0 = this.$constructor.getDeclarationDescriptor();
                        return classifierDescriptor0 == null ? typeParameterUpperBoundEraser0.getErasedUpperBound(this.$parameter, this.$attr.withDefaultType(null).markIsRaw(this.$javaType.isRaw())) : typeParameterUpperBoundEraser0.getErasedUpperBound(this.$parameter, this.$attr.withDefaultType(classifierDescriptor0.getDefaultType()).markIsRaw(this.$javaType.isRaw()));
                    }
                });
                ErasureTypeAttributes erasureTypeAttributes0 = javaTypeAttributes0.markIsRaw(javaClassifierType0.isRaw());
                typeProjection0 = this.projectionComputer.computeProjection(typeParameterDescriptor0, erasureTypeAttributes0, this.typeParameterUpperBoundEraser, lazyWrappedType0);
            }
            arrayList0.add(typeProjection0);
        }
        return arrayList0;
    }

    private final SimpleType computeSimpleJavaClassifierType(JavaClassifierType javaClassifierType0, JavaTypeAttributes javaTypeAttributes0, SimpleType simpleType0) {
        TypeAttributes typeAttributes0;
        if(simpleType0 == null) {
            typeAttributes0 = TypeAttributesKt.toDefaultAttributes(new LazyJavaAnnotations(this.c, javaClassifierType0, false, 4, null));
        }
        else {
            typeAttributes0 = simpleType0.getAttributes();
            if(typeAttributes0 == null) {
                typeAttributes0 = TypeAttributesKt.toDefaultAttributes(new LazyJavaAnnotations(this.c, javaClassifierType0, false, 4, null));
            }
        }
        TypeConstructor typeConstructor0 = this.computeTypeConstructor(javaClassifierType0, javaTypeAttributes0);
        TypeConstructor typeConstructor1 = null;
        if(typeConstructor0 == null) {
            return null;
        }
        boolean z = this.isNullable(javaTypeAttributes0);
        if(simpleType0 != null) {
            typeConstructor1 = simpleType0.getConstructor();
        }
        return !Intrinsics.areEqual(typeConstructor1, typeConstructor0) || javaClassifierType0.isRaw() || !z ? KotlinTypeFactory.simpleType$default(typeAttributes0, typeConstructor0, this.computeArguments(javaClassifierType0, javaTypeAttributes0, typeConstructor0), z, null, 16, null) : simpleType0.makeNullableAsSpecified(true);
    }

    private final TypeConstructor computeTypeConstructor(JavaClassifierType javaClassifierType0, JavaTypeAttributes javaTypeAttributes0) {
        JavaClassifier javaClassifier0 = javaClassifierType0.getClassifier();
        if(javaClassifier0 == null) {
            return this.createNotFoundClass(javaClassifierType0);
        }
        if(javaClassifier0 instanceof JavaClass) {
            FqName fqName0 = ((JavaClass)javaClassifier0).getFqName();
            if(fqName0 == null) {
                throw new AssertionError("Class type should have a FQ name: " + javaClassifier0);
            }
            ClassDescriptor classDescriptor0 = this.mapKotlinClass(javaClassifierType0, javaTypeAttributes0, fqName0);
            if(classDescriptor0 == null) {
                classDescriptor0 = this.c.getComponents().getModuleClassResolver().resolveClass(((JavaClass)javaClassifier0));
            }
            if(classDescriptor0 != null) {
                TypeConstructor typeConstructor0 = classDescriptor0.getTypeConstructor();
                return typeConstructor0 == null ? this.createNotFoundClass(javaClassifierType0) : typeConstructor0;
            }
            return this.createNotFoundClass(javaClassifierType0);
        }
        if(!(javaClassifier0 instanceof JavaTypeParameter)) {
            throw new IllegalStateException("Unknown classifier kind: " + javaClassifier0);
        }
        TypeParameterDescriptor typeParameterDescriptor0 = this.typeParameterResolver.resolveTypeParameter(((JavaTypeParameter)javaClassifier0));
        return typeParameterDescriptor0 == null ? null : typeParameterDescriptor0.getTypeConstructor();
    }

    private final TypeConstructor createNotFoundClass(JavaClassifierType javaClassifierType0) {
        ClassId classId0 = ClassId.topLevel(new FqName(javaClassifierType0.getClassifierQualifiedName()));
        Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(FqName(javaType.classifierQualifiedName))");
        TypeConstructor typeConstructor0 = this.c.getComponents().getDeserializedDescriptorResolver().getComponents().getNotFoundClasses().getClass(classId0, CollectionsKt.listOf(0)).getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor0, "c.components.deserialize…istOf(0)).typeConstructor");
        return typeConstructor0;
    }

    private final boolean isConflictingArgumentFor(Variance variance0, TypeParameterDescriptor typeParameterDescriptor0) {
        return typeParameterDescriptor0.getVariance() == Variance.INVARIANT ? false : variance0 != typeParameterDescriptor0.getVariance();
    }

    // 去混淆评级： 低(20)
    private final boolean isNullable(JavaTypeAttributes javaTypeAttributes0) {
        return javaTypeAttributes0.getFlexibility() == JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND ? false : !javaTypeAttributes0.isForAnnotationParameter() && javaTypeAttributes0.getHowThisTypeIsUsed() != TypeUsage.SUPERTYPE;
    }

    private final ClassDescriptor mapKotlinClass(JavaClassifierType javaClassifierType0, JavaTypeAttributes javaTypeAttributes0, FqName fqName0) {
        if(javaTypeAttributes0.isForAnnotationParameter() && Intrinsics.areEqual(fqName0, JavaTypeResolverKt.JAVA_LANG_CLASS_FQ_NAME)) {
            return this.c.getComponents().getReflectionTypes().getKClass();
        }
        JavaToKotlinClassMapper javaToKotlinClassMapper0 = JavaToKotlinClassMapper.INSTANCE;
        ClassDescriptor classDescriptor0 = JavaToKotlinClassMapper.mapJavaToKotlin$default(javaToKotlinClassMapper0, fqName0, this.c.getModule().getBuiltIns(), null, 4, null);
        if(classDescriptor0 == null) {
            return null;
        }
        return !javaToKotlinClassMapper0.isReadOnly(classDescriptor0) || javaTypeAttributes0.getFlexibility() != JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND && javaTypeAttributes0.getHowThisTypeIsUsed() != TypeUsage.SUPERTYPE && !this.argumentsMakeSenseOnlyForMutableContainer(javaClassifierType0, classDescriptor0) ? classDescriptor0 : javaToKotlinClassMapper0.convertReadOnlyToMutable(classDescriptor0);
    }

    public final KotlinType transformArrayType(JavaArrayType javaArrayType0, JavaTypeAttributes javaTypeAttributes0, boolean z) {
        Intrinsics.checkNotNullParameter(javaArrayType0, "arrayType");
        Intrinsics.checkNotNullParameter(javaTypeAttributes0, "attr");
        JavaType javaType0 = javaArrayType0.getComponentType();
        PrimitiveType primitiveType0 = null;
        JavaPrimitiveType javaPrimitiveType0 = javaType0 instanceof JavaPrimitiveType ? ((JavaPrimitiveType)javaType0) : null;
        if(javaPrimitiveType0 != null) {
            primitiveType0 = javaPrimitiveType0.getType();
        }
        LazyJavaAnnotations lazyJavaAnnotations0 = new LazyJavaAnnotations(this.c, javaArrayType0, true);
        if(primitiveType0 != null) {
            SimpleType simpleType0 = this.c.getModule().getBuiltIns().getPrimitiveArrayKotlinType(primitiveType0);
            Intrinsics.checkNotNullExpressionValue(simpleType0, "it");
            KotlinType kotlinType0 = TypeUtilsKt.replaceAnnotations(simpleType0, new CompositeAnnotations(new Annotations[]{simpleType0.getAnnotations(), lazyJavaAnnotations0}));
            Intrinsics.checkNotNull(kotlinType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
            return javaTypeAttributes0.isForAnnotationParameter() ? ((SimpleType)kotlinType0) : KotlinTypeFactory.flexibleType(((SimpleType)kotlinType0), ((SimpleType)kotlinType0).makeNullableAsSpecified(true));
        }
        KotlinType kotlinType1 = this.transformJavaType(javaType0, JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, javaTypeAttributes0.isForAnnotationParameter(), false, null, 6, null));
        if(javaTypeAttributes0.isForAnnotationParameter()) {
            SimpleType simpleType1 = this.c.getModule().getBuiltIns().getArrayType((z ? Variance.OUT_VARIANCE : Variance.INVARIANT), kotlinType1, lazyJavaAnnotations0);
            Intrinsics.checkNotNullExpressionValue(simpleType1, "c.module.builtIns.getArr…mponentType, annotations)");
            return simpleType1;
        }
        SimpleType simpleType2 = this.c.getModule().getBuiltIns().getArrayType(Variance.INVARIANT, kotlinType1, lazyJavaAnnotations0);
        Intrinsics.checkNotNullExpressionValue(simpleType2, "c.module.builtIns.getArr…mponentType, annotations)");
        return KotlinTypeFactory.flexibleType(simpleType2, this.c.getModule().getBuiltIns().getArrayType(Variance.OUT_VARIANCE, kotlinType1, lazyJavaAnnotations0).makeNullableAsSpecified(true));
    }

    public static KotlinType transformArrayType$default(JavaTypeResolver javaTypeResolver0, JavaArrayType javaArrayType0, JavaTypeAttributes javaTypeAttributes0, boolean z, int v, Object object0) {
        if((v & 4) != 0) {
            z = false;
        }
        return javaTypeResolver0.transformArrayType(javaArrayType0, javaTypeAttributes0, z);
    }

    private final KotlinType transformJavaClassifierType(JavaClassifierType javaClassifierType0, JavaTypeAttributes javaTypeAttributes0) {
        boolean z = javaClassifierType0.isRaw();
        if(!z && (javaTypeAttributes0.isForAnnotationParameter() || javaTypeAttributes0.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE)) {
            SimpleType simpleType0 = this.computeSimpleJavaClassifierType(javaClassifierType0, javaTypeAttributes0, null);
            return simpleType0 != null ? simpleType0 : JavaTypeResolver.transformJavaClassifierType$errorType(javaClassifierType0);
        }
        SimpleType simpleType1 = this.computeSimpleJavaClassifierType(javaClassifierType0, javaTypeAttributes0.withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND), null);
        if(simpleType1 == null) {
            return JavaTypeResolver.transformJavaClassifierType$errorType(javaClassifierType0);
        }
        SimpleType simpleType2 = this.computeSimpleJavaClassifierType(javaClassifierType0, javaTypeAttributes0.withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND), simpleType1);
        if(simpleType2 == null) {
            return JavaTypeResolver.transformJavaClassifierType$errorType(javaClassifierType0);
        }
        return z ? new RawTypeImpl(simpleType1, simpleType2) : KotlinTypeFactory.flexibleType(simpleType1, simpleType2);
    }

    private static final ErrorType transformJavaClassifierType$errorType(JavaClassifierType javaClassifierType0) {
        String[] arr_s = {javaClassifierType0.getPresentableText()};
        return ErrorUtils.createErrorType(ErrorTypeKind.UNRESOLVED_JAVA_CLASS, arr_s);
    }

    public final KotlinType transformJavaType(JavaType javaType0, JavaTypeAttributes javaTypeAttributes0) {
        Intrinsics.checkNotNullParameter(javaTypeAttributes0, "attr");
        if(javaType0 instanceof JavaPrimitiveType) {
            PrimitiveType primitiveType0 = ((JavaPrimitiveType)javaType0).getType();
            SimpleType simpleType0 = primitiveType0 == null ? this.c.getModule().getBuiltIns().getUnitType() : this.c.getModule().getBuiltIns().getPrimitiveKotlinType(primitiveType0);
            Intrinsics.checkNotNullExpressionValue(simpleType0, "{\n                val pr…ns.unitType\n            }");
            return simpleType0;
        }
        if(javaType0 instanceof JavaClassifierType) {
            return this.transformJavaClassifierType(((JavaClassifierType)javaType0), javaTypeAttributes0);
        }
        if(javaType0 instanceof JavaArrayType) {
            return JavaTypeResolver.transformArrayType$default(this, ((JavaArrayType)javaType0), javaTypeAttributes0, false, 4, null);
        }
        if(javaType0 instanceof JavaWildcardType) {
            JavaType javaType1 = ((JavaWildcardType)javaType0).getBound();
            if(javaType1 != null) {
                KotlinType kotlinType0 = this.transformJavaType(javaType1, javaTypeAttributes0);
                if(kotlinType0 != null) {
                    return kotlinType0;
                }
            }
            SimpleType simpleType1 = this.c.getModule().getBuiltIns().getDefaultBound();
            Intrinsics.checkNotNullExpressionValue(simpleType1, "c.module.builtIns.defaultBound");
            return simpleType1;
        }
        if(javaType0 != null) {
            throw new UnsupportedOperationException("Unsupported type: " + javaType0);
        }
        SimpleType simpleType2 = this.c.getModule().getBuiltIns().getDefaultBound();
        Intrinsics.checkNotNullExpressionValue(simpleType2, "c.module.builtIns.defaultBound");
        return simpleType2;
    }

    private final TypeProjection transformToTypeProjection(JavaType javaType0, JavaTypeAttributes javaTypeAttributes0, TypeParameterDescriptor typeParameterDescriptor0) {
        TypeProjection typeProjection0;
        if(javaType0 instanceof JavaWildcardType) {
            JavaType javaType1 = ((JavaWildcardType)javaType0).getBound();
            Variance variance0 = ((JavaWildcardType)javaType0).isExtends() ? Variance.OUT_VARIANCE : Variance.IN_VARIANCE;
            if(javaType1 == null || this.isConflictingArgumentFor(variance0, typeParameterDescriptor0)) {
                typeProjection0 = TypeUtils.makeStarProjection(typeParameterDescriptor0, javaTypeAttributes0);
            }
            else {
                AnnotationDescriptor annotationDescriptor0 = UtilsKt.extractNullabilityAnnotationOnBoundedWildcard(this.c, ((JavaWildcardType)javaType0));
                KotlinType kotlinType0 = this.transformJavaType(javaType1, JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, false, null, 7, null));
                if(annotationDescriptor0 != null) {
                    List list0 = CollectionsKt.plus(kotlinType0.getAnnotations(), annotationDescriptor0);
                    kotlinType0 = TypeUtilsKt.replaceAnnotations(kotlinType0, Annotations.Companion.create(list0));
                }
                typeProjection0 = TypeUtilsKt.createProjection(kotlinType0, variance0, typeParameterDescriptor0);
            }
            Intrinsics.checkNotNullExpressionValue(typeProjection0, "{\n                val bo…          }\n            }");
            return typeProjection0;
        }
        KotlinType kotlinType1 = this.transformJavaType(javaType0, javaTypeAttributes0);
        return new TypeProjectionImpl(Variance.INVARIANT, kotlinType1);
    }
}

