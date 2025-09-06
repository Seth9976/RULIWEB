package kotlin.reflect.jvm.internal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KTypeBase;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KProperty;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.jvm.KTypesJvm;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u001B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001F\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u0012\u0010 \u001A\u0004\u0018\u00010\u00132\u0006\u0010\u0002\u001A\u00020\u0003H\u0002J\u0013\u0010!\u001A\u00020\u00192\b\u0010\"\u001A\u0004\u0018\u00010#H\u0096\u0002J\b\u0010$\u001A\u00020%H\u0016J\u0015\u0010&\u001A\u00020\u00002\u0006\u0010\'\u001A\u00020\u0019H\u0000¢\u0006\u0002\b(J\b\u0010)\u001A\u00020*H\u0016R\u001A\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\t8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR!\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u000E0\t8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001A\u0004\b\u000F\u0010\fR\u001D\u0010\u0012\u001A\u0004\u0018\u00010\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001A\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0004\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001A\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0018\u0010\u001AR\u0016\u0010\u001B\u001A\u0004\u0018\u00010\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u001C\u0010\u001DR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u001E\u0010\u001F¨\u0006+²\u0006\u0010\u0010,\u001A\b\u0012\u0004\u0012\u00020\u00060\tX\u008A\u0084\u0002"}, d2 = {"Lkotlin/reflect/jvm/internal/KTypeImpl;", "Lkotlin/jvm/internal/KTypeBase;", "type", "Lkotlin/reflect/jvm/internal/impl/types/KotlinType;", "computeJavaType", "Lkotlin/Function0;", "Ljava/lang/reflect/Type;", "(Lorg/jetbrains/kotlin/types/KotlinType;Lkotlin/jvm/functions/Function0;)V", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "arguments", "Lkotlin/reflect/KTypeProjection;", "getArguments", "arguments$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "classifier", "Lkotlin/reflect/KClassifier;", "getClassifier", "()Lkotlin/reflect/KClassifier;", "classifier$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "isMarkedNullable", "", "()Z", "javaType", "getJavaType", "()Ljava/lang/reflect/Type;", "getType", "()Lorg/jetbrains/kotlin/types/KotlinType;", "convert", "equals", "other", "", "hashCode", "", "makeNullableAsSpecified", "nullable", "makeNullableAsSpecified$kotlin_reflection", "toString", "", "kotlin-reflection", "parameterizedTypeArguments"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class KTypeImpl implements KTypeBase {
    static final KProperty[] $$delegatedProperties;
    private final LazySoftVal arguments$delegate;
    private final LazySoftVal classifier$delegate;
    private final LazySoftVal computeJavaType;
    private final KotlinType type;

    static {
        KTypeImpl.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KTypeImpl.class), "classifier", "getClassifier()Lkotlin/reflect/KClassifier;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KTypeImpl.class), "arguments", "getArguments()Ljava/util/List;"))};
    }

    public KTypeImpl(KotlinType kotlinType0, Function0 function00) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        super();
        this.type = kotlinType0;
        LazySoftVal reflectProperties$LazySoftVal0 = null;
        LazySoftVal reflectProperties$LazySoftVal1 = function00 instanceof LazySoftVal ? ((LazySoftVal)function00) : null;
        if(reflectProperties$LazySoftVal1 != null) {
            reflectProperties$LazySoftVal0 = reflectProperties$LazySoftVal1;
        }
        else if(function00 != null) {
            reflectProperties$LazySoftVal0 = ReflectProperties.lazySoft(function00);
        }
        this.computeJavaType = reflectProperties$LazySoftVal0;
        this.classifier$delegate = ReflectProperties.lazySoft(new Function0() {
            {
                KTypeImpl.this = kTypeImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final KClassifier invoke() {
                return KTypeImpl.access$convert(KTypeImpl.this, KTypeImpl.this.getType());
            }
        });
        this.arguments$delegate = ReflectProperties.lazySoft(new Function0(function00) {
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
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

            final Function0 $computeJavaType;

            {
                KTypeImpl.this = kTypeImpl0;
                this.$computeJavaType = function00;
                super(0);
            }

            public static final List access$invoke$lambda$0(Lazy lazy0) {
                return kotlin.reflect.jvm.internal.KTypeImpl.arguments.2.invoke$lambda$0(lazy0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                KTypeProjection kTypeProjection0;
                List list0 = KTypeImpl.this.getType().getArguments();
                if(list0.isEmpty()) {
                    return CollectionsKt.emptyList();
                }
                kotlin.reflect.jvm.internal.KTypeImpl.arguments.2.parameterizedTypeArguments.2 kTypeImpl$arguments$2$parameterizedTypeArguments$20 = new Function0() {
                    {
                        KTypeImpl.this = kTypeImpl0;
                        super(0);
                    }

                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return this.invoke();
                    }

                    public final List invoke() {
                        Type type0 = KTypeImpl.this.getJavaType();
                        Intrinsics.checkNotNull(type0);
                        return ReflectClassUtilKt.getParameterizedTypeArguments(type0);
                    }
                };
                Lazy lazy0 = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, kTypeImpl$arguments$2$parameterizedTypeArguments$20);
                Function0 function00 = this.$computeJavaType;
                KTypeImpl kTypeImpl0 = KTypeImpl.this;
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                int v = 0;
                for(Object object0: list0) {
                    if(v < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if(((TypeProjection)object0).isStarProjection()) {
                        kTypeProjection0 = KTypeProjection.Companion.getSTAR();
                    }
                    else {
                        KotlinType kotlinType0 = ((TypeProjection)object0).getType();
                        Intrinsics.checkNotNullExpressionValue(kotlinType0, "typeProjection.type");
                        Function0 function01 = function00 == null ? null : new Function0(v, lazy0) {
                            final int $i;
                            final Lazy $parameterizedTypeArguments$delegate;

                            {
                                KTypeImpl.this = kTypeImpl0;
                                this.$i = v;
                                this.$parameterizedTypeArguments$delegate = lazy0;
                                super(0);
                            }

                            @Override  // kotlin.jvm.functions.Function0
                            public Object invoke() {
                                return this.invoke();
                            }

                            public final Type invoke() {
                                Type type0 = KTypeImpl.this.getJavaType();
                                if(type0 instanceof Class) {
                                    Class class0 = ((Class)type0).isArray() ? ((Class)type0).getComponentType() : Object.class;
                                    Intrinsics.checkNotNullExpressionValue(class0, "{\n                      …                        }");
                                    return class0;
                                }
                                if(type0 instanceof GenericArrayType) {
                                    if(this.$i != 0) {
                                        throw new KotlinReflectionInternalError("Array type has been queried for a non-0th argument: " + KTypeImpl.this);
                                    }
                                    Type type1 = ((GenericArrayType)type0).getGenericComponentType();
                                    Intrinsics.checkNotNullExpressionValue(type1, "{\n                      …                        }");
                                    return type1;
                                }
                                if(!(type0 instanceof ParameterizedType)) {
                                    throw new KotlinReflectionInternalError("Non-generic type has been queried for arguments: " + KTypeImpl.this);
                                }
                                Type type2 = (Type)kotlin.reflect.jvm.internal.KTypeImpl.arguments.2.access$invoke$lambda$0(this.$parameterizedTypeArguments$delegate).get(this.$i);
                                if(type2 instanceof WildcardType) {
                                    Type[] arr_type = ((WildcardType)type2).getLowerBounds();
                                    Intrinsics.checkNotNullExpressionValue(arr_type, "argument.lowerBounds");
                                    Type type3 = (Type)ArraysKt.firstOrNull(arr_type);
                                    if(type3 == null) {
                                        Type[] arr_type1 = ((WildcardType)type2).getUpperBounds();
                                        Intrinsics.checkNotNullExpressionValue(arr_type1, "argument.upperBounds");
                                        type2 = (Type)ArraysKt.first(arr_type1);
                                    }
                                    else {
                                        type2 = type3;
                                    }
                                }
                                Intrinsics.checkNotNullExpressionValue(type2, "{\n                      …                        }");
                                return type2;
                            }
                        };
                        KTypeImpl kTypeImpl1 = new KTypeImpl(kotlinType0, function01);
                        switch(WhenMappings.$EnumSwitchMapping$0[((TypeProjection)object0).getProjectionKind().ordinal()]) {
                            case 1: {
                                kTypeProjection0 = KTypeProjection.Companion.invariant(kTypeImpl1);
                                break;
                            }
                            case 2: {
                                kTypeProjection0 = KTypeProjection.Companion.contravariant(kTypeImpl1);
                                break;
                            }
                            case 3: {
                                kTypeProjection0 = KTypeProjection.Companion.covariant(kTypeImpl1);
                                break;
                            }
                            default: {
                                throw new NoWhenBranchMatchedException();
                            }
                        }
                    }
                    arrayList0.add(kTypeProjection0);
                    ++v;
                }
                return arrayList0;
            }

            private static final List invoke$lambda$0(Lazy lazy0) {
                return (List)lazy0.getValue();
            }
        });
    }

    public KTypeImpl(KotlinType kotlinType0, Function0 function00, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            function00 = null;
        }
        this(kotlinType0, function00);
    }

    public static final KClassifier access$convert(KTypeImpl kTypeImpl0, KotlinType kotlinType0) {
        return kTypeImpl0.convert(kotlinType0);
    }

    private final KClassifier convert(KotlinType kotlinType0) {
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        if(classifierDescriptor0 instanceof ClassDescriptor) {
            Class class0 = UtilKt.toJavaClass(((ClassDescriptor)classifierDescriptor0));
            if(class0 == null) {
                return null;
            }
            if(class0.isArray()) {
                TypeProjection typeProjection0 = (TypeProjection)CollectionsKt.singleOrNull(kotlinType0.getArguments());
                if(typeProjection0 != null) {
                    KotlinType kotlinType1 = typeProjection0.getType();
                    if(kotlinType1 != null) {
                        KClassifier kClassifier0 = this.convert(kotlinType1);
                        if(kClassifier0 == null) {
                            throw new KotlinReflectionInternalError("Cannot determine classifier for array element type: " + this);
                        }
                        return new KClassImpl(UtilKt.createArrayType(JvmClassMappingKt.getJavaClass(KTypesJvm.getJvmErasure(kClassifier0))));
                    }
                }
                return new KClassImpl(class0);
            }
            if(!TypeUtils.isNullableType(kotlinType0)) {
                Class class1 = ReflectClassUtilKt.getPrimitiveByWrapper(class0);
                if(class1 != null) {
                    class0 = class1;
                }
                return new KClassImpl(class0);
            }
            return new KClassImpl(class0);
        }
        if(classifierDescriptor0 instanceof TypeParameterDescriptor) {
            return new KTypeParameterImpl(null, ((TypeParameterDescriptor)classifierDescriptor0));
        }
        if(classifierDescriptor0 instanceof TypeAliasDescriptor) {
            throw new NotImplementedError("An operation is not implemented: Type alias classifiers are not yet supported");
        }
        return null;
    }

    // 去混淆评级： 低(40)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof KTypeImpl && Intrinsics.areEqual(this.type, ((KTypeImpl)object0).type) && Intrinsics.areEqual(this.getClassifier(), ((KTypeImpl)object0).getClassifier()) && Intrinsics.areEqual(this.getArguments(), ((KTypeImpl)object0).getArguments());
    }

    @Override  // kotlin.reflect.KAnnotatedElement
    public List getAnnotations() {
        return UtilKt.computeAnnotations(this.type);
    }

    @Override  // kotlin.reflect.KType
    public List getArguments() {
        Object object0 = this.arguments$delegate.getValue(this, KTypeImpl.$$delegatedProperties[1]);
        Intrinsics.checkNotNullExpressionValue(object0, "<get-arguments>(...)");
        return (List)object0;
    }

    @Override  // kotlin.reflect.KType
    public KClassifier getClassifier() {
        return (KClassifier)this.classifier$delegate.getValue(this, KTypeImpl.$$delegatedProperties[0]);
    }

    @Override  // kotlin.jvm.internal.KTypeBase
    public Type getJavaType() {
        return this.computeJavaType == null ? null : ((Type)this.computeJavaType.invoke());
    }

    public final KotlinType getType() {
        return this.type;
    }

    @Override
    public int hashCode() {
        int v = this.type.hashCode();
        KClassifier kClassifier0 = this.getClassifier();
        return kClassifier0 == null ? v * 961 + this.getArguments().hashCode() : (v * 0x1F + kClassifier0.hashCode()) * 0x1F + this.getArguments().hashCode();
    }

    @Override  // kotlin.reflect.KType
    public boolean isMarkedNullable() {
        return this.type.isMarkedNullable();
    }

    public final KTypeImpl makeNullableAsSpecified$kotlin_reflection(boolean z) {
        if(!FlexibleTypesKt.isFlexible(this.type) && this.isMarkedNullable() == z) {
            return this;
        }
        KotlinType kotlinType0 = TypeUtils.makeNullableAsSpecified(this.type, z);
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "makeNullableAsSpecified(type, nullable)");
        return new KTypeImpl(kotlinType0, this.computeJavaType);
    }

    @Override
    public String toString() {
        return ReflectionObjectRenderer.INSTANCE.renderType(this.type);
    }
}

