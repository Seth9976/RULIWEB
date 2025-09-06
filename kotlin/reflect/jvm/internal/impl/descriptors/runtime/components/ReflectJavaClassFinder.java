package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaPackage;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder.Request;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.text.StringsKt;

public final class ReflectJavaClassFinder implements JavaClassFinder {
    private final ClassLoader classLoader;

    public ReflectJavaClassFinder(ClassLoader classLoader0) {
        Intrinsics.checkNotNullParameter(classLoader0, "classLoader");
        super();
        this.classLoader = classLoader0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder
    public JavaClass findClass(Request javaClassFinder$Request0) {
        Intrinsics.checkNotNullParameter(javaClassFinder$Request0, "request");
        ClassId classId0 = javaClassFinder$Request0.getClassId();
        FqName fqName0 = classId0.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName0, "classId.packageFqName");
        String s = classId0.getRelativeClassName().asString();
        Intrinsics.checkNotNullExpressionValue(s, "classId.relativeClassName.asString()");
        String s1 = StringsKt.replace$default(s, '.', '$', false, 4, null);
        if(!fqName0.isRoot()) {
            s1 = fqName0.asString() + '.' + s1;
        }
        Class class0 = ReflectJavaClassFinderKt.tryLoadClass(this.classLoader, s1);
        return class0 != null ? new ReflectJavaClass(class0) : null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder
    public JavaPackage findPackage(FqName fqName0, boolean z) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return new ReflectJavaPackage(fqName0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder
    public Set knownClassNamesInPackage(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "packageFqName");
        return null;
    }
}

