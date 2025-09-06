package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassObjectAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class ReflectJavaClassObjectAnnotationArgument extends ReflectJavaAnnotationArgument implements JavaClassObjectAnnotationArgument {
    private final Class klass;

    public ReflectJavaClassObjectAnnotationArgument(Name name0, Class class0) {
        Intrinsics.checkNotNullParameter(class0, "klass");
        super(name0, null);
        this.klass = class0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassObjectAnnotationArgument
    public JavaType getReferencedType() {
        return ReflectJavaType.Factory.create(this.klass);
    }
}

