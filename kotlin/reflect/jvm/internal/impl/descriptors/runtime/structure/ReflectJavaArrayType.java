package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;

public final class ReflectJavaArrayType extends ReflectJavaType implements JavaArrayType {
    private final Collection annotations;
    private final ReflectJavaType componentType;
    private final boolean isDeprecatedInJavaDoc;
    private final Type reflectType;

    public ReflectJavaArrayType(Type type0) {
        Intrinsics.checkNotNullParameter(type0, "reflectType");
        ReflectJavaType reflectJavaType0;
        super();
        this.reflectType = type0;
        Type type1 = this.getReflectType();
        if(type1 instanceof GenericArrayType) {
            Type type2 = ((GenericArrayType)type1).getGenericComponentType();
            Intrinsics.checkNotNullExpressionValue(type2, "genericComponentType");
            reflectJavaType0 = ReflectJavaType.Factory.create(type2);
        }
        else if(type1 instanceof Class && ((Class)type1).isArray()) {
            Class class0 = ((Class)type1).getComponentType();
            Intrinsics.checkNotNullExpressionValue(class0, "getComponentType()");
            reflectJavaType0 = ReflectJavaType.Factory.create(class0);
        }
        else {
            throw new IllegalArgumentException("Not an array type (" + this.getReflectType().getClass() + "): " + this.getReflectType());
        }
        this.componentType = reflectJavaType0;
        this.annotations = CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public Collection getAnnotations() {
        return this.annotations;
    }

    public ReflectJavaType getComponentType() {
        return this.componentType;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType
    public JavaType getComponentType() {
        return this.getComponentType();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType
    protected Type getReflectType() {
        return this.reflectType;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return this.isDeprecatedInJavaDoc;
    }
}

