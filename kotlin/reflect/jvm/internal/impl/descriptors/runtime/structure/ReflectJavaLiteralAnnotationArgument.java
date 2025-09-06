package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaLiteralAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class ReflectJavaLiteralAnnotationArgument extends ReflectJavaAnnotationArgument implements JavaLiteralAnnotationArgument {
    private final Object value;

    public ReflectJavaLiteralAnnotationArgument(Name name0, Object object0) {
        Intrinsics.checkNotNullParameter(object0, "value");
        super(name0, null);
        this.value = object0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaLiteralAnnotationArgument
    public Object getValue() {
        return this.value;
    }
}

