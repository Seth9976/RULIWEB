package kotlin.reflect.jvm.internal.impl.load.java.structure;

public final class JavaTypesKt {
    public static final boolean isSuperWildcard(JavaType javaType0) {
        JavaWildcardType javaWildcardType0 = javaType0 instanceof JavaWildcardType ? ((JavaWildcardType)javaType0) : null;
        return javaWildcardType0 != null && javaWildcardType0.getBound() != null && !javaWildcardType0.isExtends();
    }
}

