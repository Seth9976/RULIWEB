package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JavaDescriptorResolver;

public final class SingleModuleClassResolver implements ModuleClassResolver {
    public JavaDescriptorResolver resolver;

    public final JavaDescriptorResolver getResolver() {
        JavaDescriptorResolver javaDescriptorResolver0 = this.resolver;
        if(javaDescriptorResolver0 != null) {
            return javaDescriptorResolver0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("resolver");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.ModuleClassResolver
    public ClassDescriptor resolveClass(JavaClass javaClass0) {
        Intrinsics.checkNotNullParameter(javaClass0, "javaClass");
        return this.getResolver().resolveClass(javaClass0);
    }

    public final void setResolver(JavaDescriptorResolver javaDescriptorResolver0) {
        Intrinsics.checkNotNullParameter(javaDescriptorResolver0, "<set-?>");
        this.resolver = javaDescriptorResolver0;
    }
}

