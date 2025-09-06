package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolver;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class LazyJavaResolverContext {
    private final JavaResolverComponents components;
    private final Lazy defaultTypeQualifiers$delegate;
    private final Lazy delegateForDefaultTypeQualifiers;
    private final TypeParameterResolver typeParameterResolver;
    private final JavaTypeResolver typeResolver;

    public LazyJavaResolverContext(JavaResolverComponents javaResolverComponents0, TypeParameterResolver typeParameterResolver0, Lazy lazy0) {
        Intrinsics.checkNotNullParameter(javaResolverComponents0, "components");
        Intrinsics.checkNotNullParameter(typeParameterResolver0, "typeParameterResolver");
        Intrinsics.checkNotNullParameter(lazy0, "delegateForDefaultTypeQualifiers");
        super();
        this.components = javaResolverComponents0;
        this.typeParameterResolver = typeParameterResolver0;
        this.delegateForDefaultTypeQualifiers = lazy0;
        this.defaultTypeQualifiers$delegate = lazy0;
        this.typeResolver = new JavaTypeResolver(this, typeParameterResolver0);
    }

    public final JavaResolverComponents getComponents() {
        return this.components;
    }

    public final JavaTypeQualifiersByElementType getDefaultTypeQualifiers() {
        return (JavaTypeQualifiersByElementType)this.defaultTypeQualifiers$delegate.getValue();
    }

    public final Lazy getDelegateForDefaultTypeQualifiers$descriptors_jvm() {
        return this.delegateForDefaultTypeQualifiers;
    }

    public final ModuleDescriptor getModule() {
        return this.components.getModule();
    }

    public final StorageManager getStorageManager() {
        return this.components.getStorageManager();
    }

    public final TypeParameterResolver getTypeParameterResolver() {
        return this.typeParameterResolver;
    }

    public final JavaTypeResolver getTypeResolver() {
        return this.typeResolver;
    }
}

