package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class CompositeSyntheticJavaPartsProvider implements SyntheticJavaPartsProvider {
    private final List inner;

    public CompositeSyntheticJavaPartsProvider(List list0) {
        Intrinsics.checkNotNullParameter(list0, "inner");
        super();
        this.inner = list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider
    public void generateConstructors(LazyJavaResolverContext lazyJavaResolverContext0, ClassDescriptor classDescriptor0, List list0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "_context_receiver_0");
        Intrinsics.checkNotNullParameter(classDescriptor0, "thisDescriptor");
        Intrinsics.checkNotNullParameter(list0, "result");
        for(Object object0: this.inner) {
            ((SyntheticJavaPartsProvider)object0).generateConstructors(lazyJavaResolverContext0, classDescriptor0, list0);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider
    public void generateMethods(LazyJavaResolverContext lazyJavaResolverContext0, ClassDescriptor classDescriptor0, Name name0, Collection collection0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "_context_receiver_0");
        Intrinsics.checkNotNullParameter(classDescriptor0, "thisDescriptor");
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(collection0, "result");
        for(Object object0: this.inner) {
            ((SyntheticJavaPartsProvider)object0).generateMethods(lazyJavaResolverContext0, classDescriptor0, name0, collection0);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider
    public void generateNestedClass(LazyJavaResolverContext lazyJavaResolverContext0, ClassDescriptor classDescriptor0, Name name0, List list0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "_context_receiver_0");
        Intrinsics.checkNotNullParameter(classDescriptor0, "thisDescriptor");
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(list0, "result");
        for(Object object0: this.inner) {
            ((SyntheticJavaPartsProvider)object0).generateNestedClass(lazyJavaResolverContext0, classDescriptor0, name0, list0);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider
    public void generateStaticFunctions(LazyJavaResolverContext lazyJavaResolverContext0, ClassDescriptor classDescriptor0, Name name0, Collection collection0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "_context_receiver_0");
        Intrinsics.checkNotNullParameter(classDescriptor0, "thisDescriptor");
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(collection0, "result");
        for(Object object0: this.inner) {
            ((SyntheticJavaPartsProvider)object0).generateStaticFunctions(lazyJavaResolverContext0, classDescriptor0, name0, collection0);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider
    public List getMethodNames(LazyJavaResolverContext lazyJavaResolverContext0, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "_context_receiver_0");
        Intrinsics.checkNotNullParameter(classDescriptor0, "thisDescriptor");
        Collection collection0 = new ArrayList();
        for(Object object0: this.inner) {
            CollectionsKt.addAll(collection0, ((SyntheticJavaPartsProvider)object0).getMethodNames(lazyJavaResolverContext0, classDescriptor0));
        }
        return (List)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider
    public List getNestedClassNames(LazyJavaResolverContext lazyJavaResolverContext0, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "_context_receiver_0");
        Intrinsics.checkNotNullParameter(classDescriptor0, "thisDescriptor");
        Collection collection0 = new ArrayList();
        for(Object object0: this.inner) {
            CollectionsKt.addAll(collection0, ((SyntheticJavaPartsProvider)object0).getNestedClassNames(lazyJavaResolverContext0, classDescriptor0));
        }
        return (List)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider
    public List getStaticFunctionNames(LazyJavaResolverContext lazyJavaResolverContext0, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "_context_receiver_0");
        Intrinsics.checkNotNullParameter(classDescriptor0, "thisDescriptor");
        Collection collection0 = new ArrayList();
        for(Object object0: this.inner) {
            CollectionsKt.addAll(collection0, ((SyntheticJavaPartsProvider)object0).getStaticFunctionNames(lazyJavaResolverContext0, classDescriptor0));
        }
        return (List)collection0;
    }
}

