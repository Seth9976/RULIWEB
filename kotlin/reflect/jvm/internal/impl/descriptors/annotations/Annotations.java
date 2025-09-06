package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public interface Annotations extends Iterable, KMappedMarker {
    public static final class Companion {
        static final Companion $$INSTANCE;
        private static final Annotations EMPTY;

        static {
            Companion.$$INSTANCE = new Companion();
            Companion.EMPTY = new Annotations.Companion.EMPTY.1();
        }

        public final Annotations create(List list0) {
            Intrinsics.checkNotNullParameter(list0, "annotations");
            return list0.isEmpty() ? Companion.EMPTY : new AnnotationsImpl(list0);
        }

        public final Annotations getEMPTY() {
            return Companion.EMPTY;
        }
    }

    public static final class DefaultImpls {
        public static AnnotationDescriptor findAnnotation(Annotations annotations0, FqName fqName0) {
            Intrinsics.checkNotNullParameter(fqName0, "fqName");
            for(Object object0: annotations0) {
                if(Intrinsics.areEqual(((AnnotationDescriptor)object0).getFqName(), fqName0)) {
                    return (AnnotationDescriptor)object0;
                }
                if(false) {
                    break;
                }
            }
            return null;
        }

        public static boolean hasAnnotation(Annotations annotations0, FqName fqName0) {
            Intrinsics.checkNotNullParameter(fqName0, "fqName");
            return annotations0.findAnnotation(fqName0) != null;
        }
    }

    public static final Companion Companion;

    static {
        Annotations.Companion = Companion.$$INSTANCE;
    }

    AnnotationDescriptor findAnnotation(FqName arg1);

    boolean hasAnnotation(FqName arg1);

    boolean isEmpty();
}

