package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;

public interface JvmTypeFactory {
    Object boxType(Object arg1);

    Object createFromString(String arg1);

    Object createObjectType(String arg1);

    Object createPrimitiveType(PrimitiveType arg1);

    Object getJavaLangClassType();

    String toString(Object arg1);
}

