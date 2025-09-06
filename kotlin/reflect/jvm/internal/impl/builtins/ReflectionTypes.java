package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;

public final class ReflectionTypes {
    static final class ClassLookup {
        private final int numberOfTypeParameters;

        public ClassLookup(int v) {
            this.numberOfTypeParameters = v;
        }

        public final ClassDescriptor getValue(ReflectionTypes reflectionTypes0, KProperty kProperty0) {
            Intrinsics.checkNotNullParameter(reflectionTypes0, "types");
            Intrinsics.checkNotNullParameter(kProperty0, "property");
            return reflectionTypes0.find(CapitalizeDecapitalizeKt.capitalizeAsciiOnly(kProperty0.getName()), this.numberOfTypeParameters);
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final KotlinType createKPropertyStarType(ModuleDescriptor moduleDescriptor0) {
            Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
            ClassDescriptor classDescriptor0 = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor0, FqNames.kProperty);
            if(classDescriptor0 == null) {
                return null;
            }
            TypeAttributes typeAttributes0 = TypeAttributes.Companion.getEmpty();
            List list0 = classDescriptor0.getTypeConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "kPropertyClass.typeConstructor.parameters");
            Object object0 = CollectionsKt.single(list0);
            Intrinsics.checkNotNullExpressionValue(object0, "kPropertyClass.typeConstructor.parameters.single()");
            return KotlinTypeFactory.simpleNotNullType(typeAttributes0, classDescriptor0, CollectionsKt.listOf(new StarProjectionImpl(((TypeParameterDescriptor)object0))));
        }
    }

    static final KProperty[] $$delegatedProperties;
    public static final Companion Companion;
    private final ClassLookup kClass$delegate;
    private final ClassLookup kMutableProperty0$delegate;
    private final ClassLookup kMutableProperty1$delegate;
    private final ClassLookup kMutableProperty2$delegate;
    private final ClassLookup kProperty$delegate;
    private final ClassLookup kProperty0$delegate;
    private final ClassLookup kProperty1$delegate;
    private final ClassLookup kProperty2$delegate;
    private final Lazy kotlinReflectScope$delegate;
    private final NotFoundClasses notFoundClasses;

    static {
        ReflectionTypes.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kClass", "getKClass()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kProperty", "getKProperty()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kProperty0", "getKProperty0()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kProperty1", "getKProperty1()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kProperty2", "getKProperty2()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kMutableProperty0", "getKMutableProperty0()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kMutableProperty1", "getKMutableProperty1()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kMutableProperty2", "getKMutableProperty2()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;"))};
        ReflectionTypes.Companion = new Companion(null);
    }

    public ReflectionTypes(ModuleDescriptor moduleDescriptor0, NotFoundClasses notFoundClasses0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        Intrinsics.checkNotNullParameter(notFoundClasses0, "notFoundClasses");
        super();
        this.notFoundClasses = notFoundClasses0;
        Function0 function00 = new Function0() {
            final ModuleDescriptor $module;

            {
                this.$module = moduleDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final MemberScope invoke() {
                return this.$module.getPackage(StandardNames.KOTLIN_REFLECT_FQ_NAME).getMemberScope();
            }
        };
        this.kotlinReflectScope$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        this.kClass$delegate = new ClassLookup(1);
        this.kProperty$delegate = new ClassLookup(1);
        this.kProperty0$delegate = new ClassLookup(1);
        this.kProperty1$delegate = new ClassLookup(2);
        this.kProperty2$delegate = new ClassLookup(3);
        this.kMutableProperty0$delegate = new ClassLookup(1);
        this.kMutableProperty1$delegate = new ClassLookup(2);
        this.kMutableProperty2$delegate = new ClassLookup(3);
    }

    private final ClassDescriptor find(String s, int v) {
        Name name0 = Name.identifier(s);
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(className)");
        ClassifierDescriptor classifierDescriptor0 = this.getKotlinReflectScope().getContributedClassifier(name0, NoLookupLocation.FROM_REFLECTION);
        ClassDescriptor classDescriptor0 = classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
        if(classDescriptor0 == null) {
            ClassId classId0 = new ClassId(StandardNames.KOTLIN_REFLECT_FQ_NAME, name0);
            List list0 = CollectionsKt.listOf(v);
            return this.notFoundClasses.getClass(classId0, list0);
        }
        return classDescriptor0;
    }

    public final ClassDescriptor getKClass() {
        return this.kClass$delegate.getValue(this, ReflectionTypes.$$delegatedProperties[0]);
    }

    private final MemberScope getKotlinReflectScope() {
        return (MemberScope)this.kotlinReflectScope$delegate.getValue();
    }
}

