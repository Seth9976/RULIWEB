package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.name.Name;

public interface SyntheticJavaPartsProvider {
    public static final class Companion {
        static final Companion $$INSTANCE;
        private static final CompositeSyntheticJavaPartsProvider EMPTY;

        static {
            Companion.$$INSTANCE = new Companion();
            Companion.EMPTY = new CompositeSyntheticJavaPartsProvider(CollectionsKt.emptyList());
        }

        public final CompositeSyntheticJavaPartsProvider getEMPTY() {
            return Companion.EMPTY;
        }
    }

    public static final Companion Companion;

    static {
        SyntheticJavaPartsProvider.Companion = Companion.$$INSTANCE;
    }

    void generateConstructors(LazyJavaResolverContext arg1, ClassDescriptor arg2, List arg3);

    void generateMethods(LazyJavaResolverContext arg1, ClassDescriptor arg2, Name arg3, Collection arg4);

    void generateNestedClass(LazyJavaResolverContext arg1, ClassDescriptor arg2, Name arg3, List arg4);

    void generateStaticFunctions(LazyJavaResolverContext arg1, ClassDescriptor arg2, Name arg3, Collection arg4);

    List getMethodNames(LazyJavaResolverContext arg1, ClassDescriptor arg2);

    List getNestedClassNames(LazyJavaResolverContext arg1, ClassDescriptor arg2);

    List getStaticFunctionNames(LazyJavaResolverContext arg1, ClassDescriptor arg2);
}

