package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Type;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPrimitiveType;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

public final class ReflectJavaPrimitiveType extends ReflectJavaType implements JavaPrimitiveType {
    private final Collection annotations;
    private final boolean isDeprecatedInJavaDoc;
    private final Class reflectType;

    public ReflectJavaPrimitiveType(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "reflectType");
        super();
        this.reflectType = class0;
        this.annotations = CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public Collection getAnnotations() {
        return this.annotations;
    }

    protected Class getReflectType() {
        return this.reflectType;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType
    public Type getReflectType() {
        return this.getReflectType();
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPrimitiveType
    public PrimitiveType getType() {
        return Intrinsics.areEqual(this.getReflectType(), Void.TYPE) ? null : JvmPrimitiveType.get(this.getReflectType().getName()).getPrimitiveType();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return this.isDeprecatedInJavaDoc;
    }
}

