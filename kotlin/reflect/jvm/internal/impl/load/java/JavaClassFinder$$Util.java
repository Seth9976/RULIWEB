package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public class JavaClassFinder..Util {
    public static JavaPackage findPackage$default(JavaClassFinder javaClassFinder0, FqName fqName0, boolean z, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: findPackage");
        }
        if((v & 2) != 0) {
            z = true;
        }
        return javaClassFinder0.findPackage(fqName0, z);
    }
}

