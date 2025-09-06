package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class AnnotationsImpl implements Annotations {
    private final List annotations;

    public AnnotationsImpl(List list0) {
        Intrinsics.checkNotNullParameter(list0, "annotations");
        super();
        this.annotations = list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public AnnotationDescriptor findAnnotation(FqName fqName0) {
        return DefaultImpls.findAnnotation(this, fqName0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean hasAnnotation(FqName fqName0) {
        return DefaultImpls.hasAnnotation(this, fqName0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean isEmpty() {
        return this.annotations.isEmpty();
    }

    @Override
    public Iterator iterator() {
        return this.annotations.iterator();
    }

    @Override
    public String toString() {
        return this.annotations.toString();
    }
}

