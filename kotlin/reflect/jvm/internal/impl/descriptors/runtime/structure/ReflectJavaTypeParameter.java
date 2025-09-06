package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class ReflectJavaTypeParameter extends ReflectJavaElement implements ReflectJavaAnnotationOwner, JavaTypeParameter {
    private final TypeVariable typeVariable;

    public ReflectJavaTypeParameter(TypeVariable typeVariable0) {
        Intrinsics.checkNotNullParameter(typeVariable0, "typeVariable");
        super();
        this.typeVariable = typeVariable0;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ReflectJavaTypeParameter && Intrinsics.areEqual(this.typeVariable, ((ReflectJavaTypeParameter)object0).typeVariable);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    public ReflectJavaAnnotation findAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        AnnotatedElement annotatedElement0 = this.getElement();
        if(annotatedElement0 != null) {
            Annotation[] arr_annotation = annotatedElement0.getDeclaredAnnotations();
            return arr_annotation == null ? null : ReflectJavaAnnotationOwnerKt.findAnnotation(arr_annotation, fqName0);
        }
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public JavaAnnotation findAnnotation(FqName fqName0) {
        return this.findAnnotation(fqName0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public Collection getAnnotations() {
        return this.getAnnotations();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    public List getAnnotations() {
        AnnotatedElement annotatedElement0 = this.getElement();
        if(annotatedElement0 != null) {
            Annotation[] arr_annotation = annotatedElement0.getDeclaredAnnotations();
            if(arr_annotation != null) {
                List list0 = ReflectJavaAnnotationOwnerKt.getAnnotations(arr_annotation);
                return list0 == null ? CollectionsKt.emptyList() : list0;
            }
        }
        return CollectionsKt.emptyList();
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    public AnnotatedElement getElement() {
        return this.typeVariable instanceof AnnotatedElement ? ((AnnotatedElement)this.typeVariable) : null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaNamedElement
    public Name getName() {
        Name name0 = Name.identifier(this.typeVariable.getName());
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(typeVariable.name)");
        return name0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter
    public Collection getUpperBounds() {
        return this.getUpperBounds();
    }

    public List getUpperBounds() {
        Type[] arr_type = this.typeVariable.getBounds();
        Intrinsics.checkNotNullExpressionValue(arr_type, "typeVariable.bounds");
        ArrayList arrayList0 = new ArrayList(arr_type.length);
        for(int v = 0; v < arr_type.length; ++v) {
            arrayList0.add(new ReflectJavaClassifierType(arr_type[v]));
        }
        ReflectJavaClassifierType reflectJavaClassifierType0 = (ReflectJavaClassifierType)CollectionsKt.singleOrNull(arrayList0);
        return Intrinsics.areEqual((reflectJavaClassifierType0 == null ? null : reflectJavaClassifierType0.getReflectType()), Object.class) ? CollectionsKt.emptyList() : arrayList0;
    }

    @Override
    public int hashCode() {
        return this.typeVariable.hashCode();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ": " + this.typeVariable;
    }
}

