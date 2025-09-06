package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class FilteredAnnotations implements Annotations {
    private final Annotations delegate;
    private final Function1 fqNameFilter;
    private final boolean isDefinitelyNewInference;

    public FilteredAnnotations(Annotations annotations0, Function1 function10) {
        Intrinsics.checkNotNullParameter(annotations0, "delegate");
        Intrinsics.checkNotNullParameter(function10, "fqNameFilter");
        this(annotations0, false, function10);
    }

    public FilteredAnnotations(Annotations annotations0, boolean z, Function1 function10) {
        Intrinsics.checkNotNullParameter(annotations0, "delegate");
        Intrinsics.checkNotNullParameter(function10, "fqNameFilter");
        super();
        this.delegate = annotations0;
        this.isDefinitelyNewInference = z;
        this.fqNameFilter = function10;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public AnnotationDescriptor findAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return ((Boolean)this.fqNameFilter.invoke(fqName0)).booleanValue() ? this.delegate.findAnnotation(fqName0) : null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean hasAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return ((Boolean)this.fqNameFilter.invoke(fqName0)).booleanValue() ? this.delegate.hasAnnotation(fqName0) : false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean isEmpty() {
        Iterable iterable0 = this.delegate;
        if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
            for(Object object0: iterable0) {
                if(this.shouldBeReturned(((AnnotationDescriptor)object0))) {
                    return !this.isDefinitelyNewInference;
                }
                if(false) {
                    break;
                }
            }
        }
        return this.isDefinitelyNewInference;
    }

    @Override
    public Iterator iterator() {
        Collection collection0 = new ArrayList();
        for(Object object0: this.delegate) {
            if(this.shouldBeReturned(((AnnotationDescriptor)object0))) {
                collection0.add(object0);
            }
        }
        return ((List)collection0).iterator();
    }

    private final boolean shouldBeReturned(AnnotationDescriptor annotationDescriptor0) {
        FqName fqName0 = annotationDescriptor0.getFqName();
        return fqName0 != null && ((Boolean)this.fqNameFilter.invoke(fqName0)).booleanValue();
    }
}

