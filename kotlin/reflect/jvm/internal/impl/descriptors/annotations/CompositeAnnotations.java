package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

public final class CompositeAnnotations implements Annotations {
    private final List delegates;

    public CompositeAnnotations(List list0) {
        Intrinsics.checkNotNullParameter(list0, "delegates");
        super();
        this.delegates = list0;
    }

    public CompositeAnnotations(Annotations[] arr_annotations) {
        Intrinsics.checkNotNullParameter(arr_annotations, "delegates");
        this(ArraysKt.toList(arr_annotations));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public AnnotationDescriptor findAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return (AnnotationDescriptor)SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.delegates), new Function1() {
            final FqName $fqName;

            {
                this.$fqName = fqName0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Annotations)object0));
            }

            public final AnnotationDescriptor invoke(Annotations annotations0) {
                Intrinsics.checkNotNullParameter(annotations0, "it");
                return annotations0.findAnnotation(this.$fqName);
            }
        }));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean hasAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        for(Object object0: CollectionsKt.asSequence(this.delegates)) {
            if(((Annotations)object0).hasAnnotation(fqName0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean isEmpty() {
        Iterable iterable0 = this.delegates;
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return true;
        }
        for(Object object0: iterable0) {
            if(!((Annotations)object0).isEmpty()) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    @Override
    public Iterator iterator() {
        return SequencesKt.flatMap(CollectionsKt.asSequence(this.delegates), kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations.iterator.1.INSTANCE).iterator();

        final class kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations.iterator.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations.iterator.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations.iterator.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations.iterator.1();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.annotations.CompositeAnnotations.iterator.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Annotations)object0));
            }

            public final Sequence invoke(Annotations annotations0) {
                Intrinsics.checkNotNullParameter(annotations0, "it");
                return CollectionsKt.asSequence(annotations0);
            }
        }

    }
}

