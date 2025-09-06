package kotlin.reflect.jvm.internal.impl.load.java.structure;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class JavaLoadingKt {
    private static final boolean isMethodWithOneObjectParameter(JavaMethod javaMethod0) {
        JavaValueParameter javaValueParameter0 = (JavaValueParameter)CollectionsKt.singleOrNull(javaMethod0.getValueParameters());
        JavaClassifierType javaClassifierType0 = null;
        JavaType javaType0 = javaValueParameter0 == null ? null : javaValueParameter0.getType();
        if(javaType0 instanceof JavaClassifierType) {
            javaClassifierType0 = (JavaClassifierType)javaType0;
        }
        if(javaClassifierType0 == null) {
            return false;
        }
        JavaClassifier javaClassifier0 = javaClassifierType0.getClassifier();
        if(javaClassifier0 instanceof JavaClass) {
            FqName fqName0 = ((JavaClass)javaClassifier0).getFqName();
            return fqName0 != null && Intrinsics.areEqual(fqName0.asString(), "java.lang.Object");
        }
        return false;
    }

    private static final boolean isObjectMethod(JavaMethod javaMethod0) {
        switch(javaMethod0.getName().asString()) {
            case "equals": {
                return JavaLoadingKt.isMethodWithOneObjectParameter(javaMethod0);
            }
            case "hashCode": 
            case "toString": {
                return javaMethod0.getValueParameters().isEmpty();
            }
            default: {
                return false;
            }
        }
    }

    public static final boolean isObjectMethodInInterface(JavaMember javaMember0) {
        Intrinsics.checkNotNullParameter(javaMember0, "<this>");
        return javaMember0.getContainingClass().isInterface() && javaMember0 instanceof JavaMethod && JavaLoadingKt.isObjectMethod(((JavaMethod)javaMember0));
    }
}

