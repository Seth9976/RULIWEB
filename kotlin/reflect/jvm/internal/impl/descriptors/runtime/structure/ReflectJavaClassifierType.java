package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifier;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class ReflectJavaClassifierType extends ReflectJavaType implements JavaClassifierType {
    private final JavaClassifier classifier;
    private final Type reflectType;

    public ReflectJavaClassifierType(Type type0) {
        Intrinsics.checkNotNullParameter(type0, "reflectType");
        JavaClassifier javaClassifier0;
        super();
        this.reflectType = type0;
        Type type1 = this.getReflectType();
        if(type1 instanceof Class) {
            javaClassifier0 = new ReflectJavaClass(((Class)type1));
        }
        else if(type1 instanceof TypeVariable) {
            javaClassifier0 = new ReflectJavaTypeParameter(((TypeVariable)type1));
        }
        else if(type1 instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType)type1).getRawType();
            Intrinsics.checkNotNull(type2, "null cannot be cast to non-null type java.lang.Class<*>");
            javaClassifier0 = new ReflectJavaClass(((Class)type2));
        }
        else {
            throw new IllegalStateException("Not a classifier type (" + type1.getClass() + "): " + type1);
        }
        this.classifier = javaClassifier0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType, kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public JavaAnnotation findAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public Collection getAnnotations() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType
    public JavaClassifier getClassifier() {
        return this.classifier;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType
    public String getClassifierQualifiedName() {
        throw new UnsupportedOperationException("Type not found: " + this.getReflectType());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType
    public String getPresentableText() {
        return this.getReflectType().toString();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType
    public Type getReflectType() {
        return this.reflectType;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType
    public List getTypeArguments() {
        Iterable iterable0 = ReflectClassUtilKt.getParameterizedTypeArguments(this.getReflectType());
        Factory reflectJavaType$Factory0 = ReflectJavaType.Factory;
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(reflectJavaType$Factory0.create(((Type)object0)));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType
    public boolean isRaw() {
        Type type0 = this.getReflectType();
        if(type0 instanceof Class) {
            TypeVariable[] arr_typeVariable = ((Class)type0).getTypeParameters();
            Intrinsics.checkNotNullExpressionValue(arr_typeVariable, "getTypeParameters()");
            return arr_typeVariable.length != 0;
        }
        return false;
    }
}

