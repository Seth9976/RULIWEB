package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;

public final class LazyJavaAnnotationsKt {
    public static final Annotations resolveAnnotations(LazyJavaResolverContext lazyJavaResolverContext0, JavaAnnotationOwner javaAnnotationOwner0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "<this>");
        Intrinsics.checkNotNullParameter(javaAnnotationOwner0, "annotationsOwner");
        return new LazyJavaAnnotations(lazyJavaResolverContext0, javaAnnotationOwner0, false, 4, null);
    }
}

