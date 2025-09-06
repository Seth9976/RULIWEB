package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.jvm.internal.Intrinsics;

public final class AnnotationsKt {
    public static final Annotations composeAnnotations(Annotations annotations0, Annotations annotations1) {
        Intrinsics.checkNotNullParameter(annotations0, "first");
        Intrinsics.checkNotNullParameter(annotations1, "second");
        if(annotations0.isEmpty()) {
            return annotations1;
        }
        return annotations1.isEmpty() ? annotations0 : new CompositeAnnotations(new Annotations[]{annotations0, annotations1});
    }
}

