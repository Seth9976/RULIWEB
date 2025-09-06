package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Public;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.java.JavaVisibilities.PackageVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.java.JavaVisibilities.ProtectedAndPackage;
import kotlin.reflect.jvm.internal.impl.descriptors.java.JavaVisibilities.ProtectedStaticVisibility;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;

public abstract class ReflectJavaMember extends ReflectJavaElement implements ReflectJavaAnnotationOwner, ReflectJavaModifierListOwner, JavaMember {
    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ReflectJavaMember && Intrinsics.areEqual(this.getMember(), ((ReflectJavaMember)object0).getMember());
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

    public ReflectJavaClass getContainingClass() {
        Class class0 = this.getMember().getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(class0, "member.declaringClass");
        return new ReflectJavaClass(class0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember
    public JavaClass getContainingClass() {
        return this.getContainingClass();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    public AnnotatedElement getElement() {
        Member member0 = this.getMember();
        Intrinsics.checkNotNull(member0, "null cannot be cast to non-null type java.lang.reflect.AnnotatedElement");
        return (AnnotatedElement)member0;
    }

    public abstract Member getMember();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaModifierListOwner
    public int getModifiers() {
        return this.getMember().getModifiers();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaNamedElement
    public Name getName() {
        String s = this.getMember().getName();
        Name name0 = s == null ? null : Name.identifier(s);
        return name0 == null ? SpecialNames.NO_NAME_PROVIDED : name0;
    }

    protected final List getValueParameters(Type[] arr_type, Annotation[][] arr2_annotation, boolean z) {
        String s;
        Intrinsics.checkNotNullParameter(arr_type, "parameterTypes");
        Intrinsics.checkNotNullParameter(arr2_annotation, "parameterAnnotations");
        ArrayList arrayList0 = new ArrayList(arr_type.length);
        Member member0 = this.getMember();
        List list0 = Java8ParameterNamesLoader.INSTANCE.loadParameterNames(member0);
        int v = list0 == null ? 0 : list0.size() - arr_type.length;
        for(int v1 = 0; v1 < arr_type.length; ++v1) {
            ReflectJavaType reflectJavaType0 = ReflectJavaType.Factory.create(arr_type[v1]);
            if(list0 == null) {
                s = null;
            }
            else {
                s = (String)CollectionsKt.getOrNull(list0, v1 + v);
                if(s == null) {
                    throw new IllegalStateException(("No parameter with index " + v1 + '+' + v + " (name=" + this.getName() + " type=" + reflectJavaType0 + ") in " + this).toString());
                }
            }
            boolean z1 = z && v1 == ArraysKt.getLastIndex(arr_type);
            arrayList0.add(new ReflectJavaValueParameter(reflectJavaType0, arr2_annotation[v1], s, z1));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public Visibility getVisibility() {
        int v = this.getModifiers();
        if(Modifier.isPublic(v)) {
            return Public.INSTANCE;
        }
        if(Modifier.isPrivate(v)) {
            return Private.INSTANCE;
        }
        if(Modifier.isProtected(v)) {
            return Modifier.isStatic(v) ? ProtectedStaticVisibility.INSTANCE : ProtectedAndPackage.INSTANCE;
        }
        return PackageVisibility.INSTANCE;
    }

    @Override
    public int hashCode() {
        return this.getMember().hashCode();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isAbstract() {
        return Modifier.isAbstract(this.getModifiers());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isFinal() {
        return Modifier.isFinal(this.getModifiers());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isStatic() {
        return Modifier.isStatic(this.getModifiers());
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ": " + this.getMember();
    }
}

