package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class ReflectJavaValueParameter extends ReflectJavaElement implements JavaValueParameter {
    private final boolean isVararg;
    private final Annotation[] reflectAnnotations;
    private final String reflectName;
    private final ReflectJavaType type;

    public ReflectJavaValueParameter(ReflectJavaType reflectJavaType0, Annotation[] arr_annotation, String s, boolean z) {
        Intrinsics.checkNotNullParameter(reflectJavaType0, "type");
        Intrinsics.checkNotNullParameter(arr_annotation, "reflectAnnotations");
        super();
        this.type = reflectJavaType0;
        this.reflectAnnotations = arr_annotation;
        this.reflectName = s;
        this.isVararg = z;
    }

    public ReflectJavaAnnotation findAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return ReflectJavaAnnotationOwnerKt.findAnnotation(this.reflectAnnotations, fqName0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public JavaAnnotation findAnnotation(FqName fqName0) {
        return this.findAnnotation(fqName0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public Collection getAnnotations() {
        return this.getAnnotations();
    }

    public List getAnnotations() {
        return ReflectJavaAnnotationOwnerKt.getAnnotations(this.reflectAnnotations);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter
    public Name getName() {
        return this.reflectName == null ? null : Name.guessByFirstCharacter(this.reflectName);
    }

    public ReflectJavaType getType() {
        return this.type;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter
    public JavaType getType() {
        return this.getType();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter
    public boolean isVararg() {
        return this.isVararg;
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return this.getClass().getName() + ": " + (this.isVararg() ? "vararg " : "") + this.getName() + ": " + this.getType();
    }
}

