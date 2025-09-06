package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public abstract class LazyJavaStaticScope extends LazyJavaScope {
    public LazyJavaStaticScope(LazyJavaResolverContext lazyJavaResolverContext0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        super(lazyJavaResolverContext0, null, 2, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredProperties(Name name0, Collection collection0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(collection0, "result");
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected MethodSignatureData resolveMethodSignature(JavaMethod javaMethod0, List list0, KotlinType kotlinType0, List list1) {
        Intrinsics.checkNotNullParameter(javaMethod0, "method");
        Intrinsics.checkNotNullParameter(list0, "methodTypeParameters");
        Intrinsics.checkNotNullParameter(kotlinType0, "returnType");
        Intrinsics.checkNotNullParameter(list1, "valueParameters");
        return new MethodSignatureData(kotlinType0, null, list1, list0, false, CollectionsKt.emptyList());
    }
}

