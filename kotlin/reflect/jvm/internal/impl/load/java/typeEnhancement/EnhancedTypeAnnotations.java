package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;

final class EnhancedTypeAnnotations implements Annotations {
    private final FqName fqNameToMatch;

    public EnhancedTypeAnnotations(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqNameToMatch");
        super();
        this.fqNameToMatch = fqName0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public AnnotationDescriptor findAnnotation(FqName fqName0) {
        return this.findAnnotation(fqName0);
    }

    public EnhancedTypeAnnotationDescriptor findAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return Intrinsics.areEqual(fqName0, this.fqNameToMatch) ? EnhancedTypeAnnotationDescriptor.INSTANCE : null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean hasAnnotation(FqName fqName0) {
        return DefaultImpls.hasAnnotation(this, fqName0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator iterator() {
        return CollectionsKt.emptyList().iterator();
    }
}

