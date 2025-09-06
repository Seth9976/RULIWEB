package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

public interface NameResolver {
    String getQualifiedClassName(int arg1);

    String getString(int arg1);

    boolean isLocalClassName(int arg1);
}

