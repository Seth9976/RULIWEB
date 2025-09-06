package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class Annotations.Companion.EMPTY.1 implements Annotations {
    public Void findAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public AnnotationDescriptor findAnnotation(FqName fqName0) {
        return (AnnotationDescriptor)this.findAnnotation(fqName0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean hasAnnotation(FqName fqName0) {
        return DefaultImpls.hasAnnotation(this, fqName0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Iterator iterator() {
        return CollectionsKt.emptyList().iterator();
    }

    @Override
    public String toString() {
        return "EMPTY";
    }
}

