package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.lang.annotation.Annotation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;

public final class ReflectAnnotationSource implements SourceElement {
    private final Annotation annotation;

    public ReflectAnnotationSource(Annotation annotation0) {
        Intrinsics.checkNotNullParameter(annotation0, "annotation");
        super();
        this.annotation = annotation0;
    }

    public final Annotation getAnnotation() {
        return this.annotation;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.SourceElement
    public SourceFile getContainingFile() {
        Intrinsics.checkNotNullExpressionValue(SourceFile.NO_SOURCE_FILE, "NO_SOURCE_FILE");
        return SourceFile.NO_SOURCE_FILE;
    }
}

