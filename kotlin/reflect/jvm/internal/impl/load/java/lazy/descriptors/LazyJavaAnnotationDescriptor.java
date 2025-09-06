package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMapper;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationAsAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassObjectAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaEnumValueAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaLiteralAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeUsage;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public final class LazyJavaAnnotationDescriptor implements AnnotationDescriptor, PossiblyExternalAnnotationDescriptor {
    static final KProperty[] $$delegatedProperties;
    private final NotNullLazyValue allValueArguments$delegate;
    private final LazyJavaResolverContext c;
    private final NullableLazyValue fqName$delegate;
    private final boolean isFreshlySupportedTypeUseAnnotation;
    private final boolean isIdeExternalAnnotation;
    private final JavaAnnotation javaAnnotation;
    private final JavaSourceElement source;
    private final NotNullLazyValue type$delegate;

    static {
        LazyJavaAnnotationDescriptor.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaAnnotationDescriptor.class), "fqName", "getFqName()Lorg/jetbrains/kotlin/name/FqName;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaAnnotationDescriptor.class), "type", "getType()Lorg/jetbrains/kotlin/types/SimpleType;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaAnnotationDescriptor.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    }

    public LazyJavaAnnotationDescriptor(LazyJavaResolverContext lazyJavaResolverContext0, JavaAnnotation javaAnnotation0, boolean z) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        Intrinsics.checkNotNullParameter(javaAnnotation0, "javaAnnotation");
        super();
        this.c = lazyJavaResolverContext0;
        this.javaAnnotation = javaAnnotation0;
        this.fqName$delegate = lazyJavaResolverContext0.getStorageManager().createNullableLazyValue(new Function0() {
            {
                LazyJavaAnnotationDescriptor.this = lazyJavaAnnotationDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final FqName invoke() {
                ClassId classId0 = LazyJavaAnnotationDescriptor.access$getJavaAnnotation$p(LazyJavaAnnotationDescriptor.this).getClassId();
                return classId0 == null ? null : classId0.asSingleFqName();
            }
        });
        this.type$delegate = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0() {
            {
                LazyJavaAnnotationDescriptor.this = lazyJavaAnnotationDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final SimpleType invoke() {
                FqName fqName0 = LazyJavaAnnotationDescriptor.this.getFqName();
                if(fqName0 == null) {
                    return ErrorUtils.createErrorType(ErrorTypeKind.NOT_FOUND_FQNAME_FOR_JAVA_ANNOTATION, new String[]{LazyJavaAnnotationDescriptor.this.javaAnnotation.toString()});
                }
                KotlinBuiltIns kotlinBuiltIns0 = LazyJavaAnnotationDescriptor.this.c.getModule().getBuiltIns();
                ClassDescriptor classDescriptor0 = JavaToKotlinClassMapper.mapJavaToKotlin$default(JavaToKotlinClassMapper.INSTANCE, fqName0, kotlinBuiltIns0, null, 4, null);
                if(classDescriptor0 == null) {
                    JavaClass javaClass0 = LazyJavaAnnotationDescriptor.this.javaAnnotation.resolve();
                    classDescriptor0 = javaClass0 == null ? null : LazyJavaAnnotationDescriptor.this.c.getComponents().getModuleClassResolver().resolveClass(javaClass0);
                    if(classDescriptor0 == null) {
                        classDescriptor0 = LazyJavaAnnotationDescriptor.this.createTypeForMissingDependencies(fqName0);
                    }
                }
                return classDescriptor0.getDefaultType();
            }
        });
        this.source = lazyJavaResolverContext0.getComponents().getSourceElementFactory().source(javaAnnotation0);
        this.allValueArguments$delegate = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0() {
            {
                LazyJavaAnnotationDescriptor.this = lazyJavaAnnotationDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Map invoke() {
                Iterable iterable0 = LazyJavaAnnotationDescriptor.access$getJavaAnnotation$p(LazyJavaAnnotationDescriptor.this).getArguments();
                LazyJavaAnnotationDescriptor lazyJavaAnnotationDescriptor0 = LazyJavaAnnotationDescriptor.this;
                Collection collection0 = new ArrayList();
                for(Object object0: iterable0) {
                    Name name0 = ((JavaAnnotationArgument)object0).getName();
                    if(name0 == null) {
                        name0 = JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME;
                    }
                    ConstantValue constantValue0 = LazyJavaAnnotationDescriptor.access$resolveAnnotationArgument(lazyJavaAnnotationDescriptor0, ((JavaAnnotationArgument)object0));
                    Pair pair0 = constantValue0 == null ? null : TuplesKt.to(name0, constantValue0);
                    if(pair0 != null) {
                        collection0.add(pair0);
                    }
                }
                return MapsKt.toMap(((List)collection0));
            }
        });
        this.isIdeExternalAnnotation = javaAnnotation0.isIdeExternalAnnotation();
        this.isFreshlySupportedTypeUseAnnotation = javaAnnotation0.isFreshlySupportedTypeUseAnnotation() || z;
    }

    public LazyJavaAnnotationDescriptor(LazyJavaResolverContext lazyJavaResolverContext0, JavaAnnotation javaAnnotation0, boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 4) != 0) {
            z = false;
        }
        this(lazyJavaResolverContext0, javaAnnotation0, z);
    }

    public static final ConstantValue access$resolveAnnotationArgument(LazyJavaAnnotationDescriptor lazyJavaAnnotationDescriptor0, JavaAnnotationArgument javaAnnotationArgument0) {
        return lazyJavaAnnotationDescriptor0.resolveAnnotationArgument(javaAnnotationArgument0);
    }

    private final ClassDescriptor createTypeForMissingDependencies(FqName fqName0) {
        ModuleDescriptor moduleDescriptor0 = this.c.getModule();
        ClassId classId0 = ClassId.topLevel(fqName0);
        Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(fqName)");
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(moduleDescriptor0, classId0, this.c.getComponents().getDeserializedDescriptorResolver().getComponents().getNotFoundClasses());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public Map getAllValueArguments() {
        return (Map)StorageKt.getValue(this.allValueArguments$delegate, this, LazyJavaAnnotationDescriptor.$$delegatedProperties[2]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public FqName getFqName() {
        return (FqName)StorageKt.getValue(this.fqName$delegate, this, LazyJavaAnnotationDescriptor.$$delegatedProperties[0]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public SourceElement getSource() {
        return this.getSource();
    }

    public JavaSourceElement getSource() {
        return this.source;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public KotlinType getType() {
        return this.getType();
    }

    public SimpleType getType() {
        return (SimpleType)StorageKt.getValue(this.type$delegate, this, LazyJavaAnnotationDescriptor.$$delegatedProperties[1]);
    }

    public final boolean isFreshlySupportedTypeUseAnnotation() {
        return this.isFreshlySupportedTypeUseAnnotation;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor
    public boolean isIdeExternalAnnotation() {
        return this.isIdeExternalAnnotation;
    }

    private final ConstantValue resolveAnnotationArgument(JavaAnnotationArgument javaAnnotationArgument0) {
        if(javaAnnotationArgument0 instanceof JavaLiteralAnnotationArgument) {
            Object object0 = ((JavaLiteralAnnotationArgument)javaAnnotationArgument0).getValue();
            return ConstantValueFactory.createConstantValue$default(ConstantValueFactory.INSTANCE, object0, null, 2, null);
        }
        if(javaAnnotationArgument0 instanceof JavaEnumValueAnnotationArgument) {
            return this.resolveFromEnumValue(((JavaEnumValueAnnotationArgument)javaAnnotationArgument0).getEnumClassId(), ((JavaEnumValueAnnotationArgument)javaAnnotationArgument0).getEntryName());
        }
        if(javaAnnotationArgument0 instanceof JavaArrayAnnotationArgument) {
            Name name0 = ((JavaArrayAnnotationArgument)javaAnnotationArgument0).getName();
            if(name0 == null) {
                name0 = JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME;
            }
            Intrinsics.checkNotNullExpressionValue(name0, "argument.name ?: DEFAULT_ANNOTATION_MEMBER_NAME");
            return this.resolveFromArray(name0, ((JavaArrayAnnotationArgument)javaAnnotationArgument0).getElements());
        }
        if(javaAnnotationArgument0 instanceof JavaAnnotationAsAnnotationArgument) {
            return this.resolveFromAnnotation(((JavaAnnotationAsAnnotationArgument)javaAnnotationArgument0).getAnnotation());
        }
        return javaAnnotationArgument0 instanceof JavaClassObjectAnnotationArgument ? this.resolveFromJavaClassObjectType(((JavaClassObjectAnnotationArgument)javaAnnotationArgument0).getReferencedType()) : null;
    }

    private final ConstantValue resolveFromAnnotation(JavaAnnotation javaAnnotation0) {
        return new AnnotationValue(new LazyJavaAnnotationDescriptor(this.c, javaAnnotation0, false, 4, null));
    }

    private final ConstantValue resolveFromArray(Name name0, List list0) {
        KotlinType kotlinType0;
        SimpleType simpleType0 = this.getType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "type");
        if(KotlinTypeKt.isError(simpleType0)) {
            return null;
        }
        ClassDescriptor classDescriptor0 = DescriptorUtilsKt.getAnnotationClass(this);
        Intrinsics.checkNotNull(classDescriptor0);
        ValueParameterDescriptor valueParameterDescriptor0 = DescriptorResolverUtils.getAnnotationParameterByName(name0, classDescriptor0);
        if(valueParameterDescriptor0 == null) {
        label_10:
            KotlinBuiltIns kotlinBuiltIns0 = this.c.getComponents().getModule().getBuiltIns();
            KotlinType kotlinType1 = ErrorUtils.createErrorType(ErrorTypeKind.UNKNOWN_ARRAY_ELEMENT_TYPE_OF_ANNOTATION_ARGUMENT, new String[0]);
            kotlinType0 = kotlinBuiltIns0.getArrayType(Variance.INVARIANT, kotlinType1);
        }
        else {
            kotlinType0 = valueParameterDescriptor0.getType();
            if(kotlinType0 == null) {
                goto label_10;
            }
        }
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "DescriptorResolverUtils.…GUMENT)\n                )");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            ConstantValue constantValue0 = this.resolveAnnotationArgument(((JavaAnnotationArgument)object0));
            if(constantValue0 == null) {
                constantValue0 = new NullValue();
            }
            arrayList0.add(constantValue0);
        }
        return ConstantValueFactory.INSTANCE.createArrayValue(arrayList0, kotlinType0);
    }

    private final ConstantValue resolveFromEnumValue(ClassId classId0, Name name0) {
        return classId0 != null && name0 != null ? new EnumValue(classId0, name0) : null;
    }

    private final ConstantValue resolveFromJavaClassObjectType(JavaType javaType0) {
        KotlinType kotlinType0 = this.c.getTypeResolver().transformJavaType(javaType0, JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, false, null, 7, null));
        return KClassValue.Companion.create(kotlinType0);
    }

    @Override
    public String toString() {
        return DescriptorRenderer.renderAnnotation$default(DescriptorRenderer.FQ_NAMES_IN_TYPES, this, null, 2, null);
    }
}

