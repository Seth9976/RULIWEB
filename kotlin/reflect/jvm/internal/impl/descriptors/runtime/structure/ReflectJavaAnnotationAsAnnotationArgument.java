package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationAsAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class ReflectJavaAnnotationAsAnnotationArgument extends ReflectJavaAnnotationArgument implements JavaAnnotationAsAnnotationArgument {
    private final Annotation annotation;

    public ReflectJavaAnnotationAsAnnotationArgument(Name name0, Annotation annotation0) {
        Intrinsics.checkNotNullParameter(annotation0, "annotation");
        super(name0, null);
        this.annotation = annotation0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationAsAnnotationArgument
    public JavaAnnotation getAnnotation() {
        return new ReflectJavaAnnotation(this.annotation);
    }
}

