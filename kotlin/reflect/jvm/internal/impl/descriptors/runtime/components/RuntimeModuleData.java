package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializationComponentsForJava.Companion.ModuleData;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializationComponentsForJava;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;

public final class RuntimeModuleData {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final RuntimeModuleData create(ClassLoader classLoader0) {
            Intrinsics.checkNotNullParameter(classLoader0, "classLoader");
            ReflectKotlinClassFinder reflectKotlinClassFinder0 = new ReflectKotlinClassFinder(classLoader0);
            ClassLoader classLoader1 = Unit.class.getClassLoader();
            Intrinsics.checkNotNullExpressionValue(classLoader1, "Unit::class.java.classLoader");
            ReflectKotlinClassFinder reflectKotlinClassFinder1 = new ReflectKotlinClassFinder(classLoader1);
            JavaClassFinder javaClassFinder0 = new ReflectJavaClassFinder(classLoader0);
            ModuleData deserializationComponentsForJava$Companion$ModuleData0 = DeserializationComponentsForJava.Companion.createModuleData(reflectKotlinClassFinder0, reflectKotlinClassFinder1, javaClassFinder0, "runtime module for " + classLoader0, RuntimeErrorReporter.INSTANCE, RuntimeSourceElementFactory.INSTANCE);
            return new RuntimeModuleData(deserializationComponentsForJava$Companion$ModuleData0.getDeserializationComponentsForJava().getComponents(), new PackagePartScopeCache(deserializationComponentsForJava$Companion$ModuleData0.getDeserializedDescriptorResolver(), reflectKotlinClassFinder0), null);
        }
    }

    public static final Companion Companion;
    private final DeserializationComponents deserialization;
    private final PackagePartScopeCache packagePartScopeCache;

    static {
        RuntimeModuleData.Companion = new Companion(null);
    }

    private RuntimeModuleData(DeserializationComponents deserializationComponents0, PackagePartScopeCache packagePartScopeCache0) {
        this.deserialization = deserializationComponents0;
        this.packagePartScopeCache = packagePartScopeCache0;
    }

    public RuntimeModuleData(DeserializationComponents deserializationComponents0, PackagePartScopeCache packagePartScopeCache0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(deserializationComponents0, packagePartScopeCache0);
    }

    public final DeserializationComponents getDeserialization() {
        return this.deserialization;
    }

    public final ModuleDescriptor getModule() {
        return this.deserialization.getModuleDescriptor();
    }

    public final PackagePartScopeCache getPackagePartScopeCache() {
        return this.packagePartScopeCache;
    }
}

