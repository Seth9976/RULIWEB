package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class ReflectJavaPackage extends ReflectJavaElement implements JavaPackage {
    private final FqName fqName;

    public ReflectJavaPackage(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        super();
        this.fqName = fqName0;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ReflectJavaPackage && Intrinsics.areEqual(this.getFqName(), ((ReflectJavaPackage)object0).getFqName());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public JavaAnnotation findAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public Collection getAnnotations() {
        return this.getAnnotations();
    }

    public List getAnnotations() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage
    public Collection getClasses(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage
    public FqName getFqName() {
        return this.fqName;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage
    public Collection getSubPackages() {
        return CollectionsKt.emptyList();
    }

    @Override
    public int hashCode() {
        return this.getFqName().hashCode();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ": " + this.getFqName();
    }
}

