package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;

public final class ReflectJavaWildcardType extends ReflectJavaType implements JavaWildcardType {
    private final Collection annotations;
    private final boolean isDeprecatedInJavaDoc;
    private final WildcardType reflectType;

    public ReflectJavaWildcardType(WildcardType wildcardType0) {
        Intrinsics.checkNotNullParameter(wildcardType0, "reflectType");
        super();
        this.reflectType = wildcardType0;
        this.annotations = CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public Collection getAnnotations() {
        return this.annotations;
    }

    public ReflectJavaType getBound() {
        Type[] arr_type = this.getReflectType().getUpperBounds();
        Type[] arr_type1 = this.getReflectType().getLowerBounds();
        if(arr_type.length > 1 || arr_type1.length > 1) {
            throw new UnsupportedOperationException("Wildcard types with many bounds are not yet supported: " + this.getReflectType());
        }
        if(arr_type1.length == 1) {
            Intrinsics.checkNotNullExpressionValue(arr_type1, "lowerBounds");
            Object object0 = ArraysKt.single(arr_type1);
            Intrinsics.checkNotNullExpressionValue(object0, "lowerBounds.single()");
            return ReflectJavaType.Factory.create(((Type)object0));
        }
        if(arr_type.length == 1) {
            Intrinsics.checkNotNullExpressionValue(arr_type, "upperBounds");
            Type type0 = (Type)ArraysKt.single(arr_type);
            if(!Intrinsics.areEqual(type0, Object.class)) {
                Intrinsics.checkNotNullExpressionValue(type0, "ub");
                return ReflectJavaType.Factory.create(type0);
            }
        }
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType
    public JavaType getBound() {
        return this.getBound();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType
    public Type getReflectType() {
        return this.getReflectType();
    }

    protected WildcardType getReflectType() {
        return this.reflectType;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return this.isDeprecatedInJavaDoc;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType
    public boolean isExtends() {
        Type[] arr_type = this.getReflectType().getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(arr_type, "reflectType.upperBounds");
        return !Intrinsics.areEqual(ArraysKt.firstOrNull(arr_type), Object.class);
    }
}

