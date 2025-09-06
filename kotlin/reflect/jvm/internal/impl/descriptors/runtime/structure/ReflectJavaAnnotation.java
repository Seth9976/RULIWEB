package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class ReflectJavaAnnotation extends ReflectJavaElement implements JavaAnnotation {
    private final Annotation annotation;

    public ReflectJavaAnnotation(Annotation annotation0) {
        Intrinsics.checkNotNullParameter(annotation0, "annotation");
        super();
        this.annotation = annotation0;
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ReflectJavaAnnotation && this.annotation == ((ReflectJavaAnnotation)object0).annotation;
    }

    public final Annotation getAnnotation() {
        return this.annotation;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation
    public Collection getArguments() {
        Method[] arr_method = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(this.annotation)).getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(arr_method, "annotation.annotationClass.java.declaredMethods");
        ArrayList arrayList0 = new ArrayList(arr_method.length);
        for(int v = 0; v < arr_method.length; ++v) {
            Method method0 = arr_method[v];
            Object object0 = method0.invoke(this.annotation, null);
            Intrinsics.checkNotNullExpressionValue(object0, "method.invoke(annotation)");
            Name name0 = Name.identifier(method0.getName());
            arrayList0.add(ReflectJavaAnnotationArgument.Factory.create(object0, name0));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation
    public ClassId getClassId() {
        return ReflectClassUtilKt.getClassId(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(this.annotation)));
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this.annotation);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation
    public boolean isFreshlySupportedTypeUseAnnotation() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation
    public boolean isIdeExternalAnnotation() {
        return false;
    }

    public ReflectJavaClass resolve() {
        return new ReflectJavaClass(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(this.annotation)));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation
    public JavaClass resolve() {
        return this.resolve();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ": " + this.annotation;
    }
}

