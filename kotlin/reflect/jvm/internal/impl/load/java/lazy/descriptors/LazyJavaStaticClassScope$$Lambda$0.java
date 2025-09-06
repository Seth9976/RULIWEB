package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;

class LazyJavaStaticClassScope..Lambda.0 implements Neighbors {
    public static final LazyJavaStaticClassScope..Lambda.0 INSTANCE;

    static {
        LazyJavaStaticClassScope..Lambda.0.INSTANCE = new LazyJavaStaticClassScope..Lambda.0();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$Neighbors
    public Iterable getNeighbors(Object object0) {
        return LazyJavaStaticClassScope.accessor$LazyJavaStaticClassScope$lambda0(((ClassDescriptor)object0));
    }
}

