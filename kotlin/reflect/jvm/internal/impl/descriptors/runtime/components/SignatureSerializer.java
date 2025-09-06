package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;

final class SignatureSerializer {
    public static final SignatureSerializer INSTANCE;

    static {
        SignatureSerializer.INSTANCE = new SignatureSerializer();
    }

    public final String constructorDesc(Constructor constructor0) {
        Intrinsics.checkNotNullParameter(constructor0, "constructor");
        StringBuilder stringBuilder0 = new StringBuilder("(");
        Class[] arr_class = constructor0.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(arr_class, "constructor.parameterTypes");
        for(int v = 0; v < arr_class.length; ++v) {
            Class class0 = arr_class[v];
            Intrinsics.checkNotNullExpressionValue(class0, "parameterType");
            stringBuilder0.append(ReflectClassUtilKt.getDesc(class0));
        }
        stringBuilder0.append(")V");
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "sb.toString()");
        return s;
    }

    public final String fieldDesc(Field field0) {
        Intrinsics.checkNotNullParameter(field0, "field");
        Class class0 = field0.getType();
        Intrinsics.checkNotNullExpressionValue(class0, "field.type");
        return ReflectClassUtilKt.getDesc(class0);
    }

    public final String methodDesc(Method method0) {
        Intrinsics.checkNotNullParameter(method0, "method");
        StringBuilder stringBuilder0 = new StringBuilder("(");
        Class[] arr_class = method0.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(arr_class, "method.parameterTypes");
        for(int v = 0; v < arr_class.length; ++v) {
            Class class0 = arr_class[v];
            Intrinsics.checkNotNullExpressionValue(class0, "parameterType");
            stringBuilder0.append(ReflectClassUtilKt.getDesc(class0));
        }
        stringBuilder0.append(")");
        Class class1 = method0.getReturnType();
        Intrinsics.checkNotNullExpressionValue(class1, "method.returnType");
        stringBuilder0.append(ReflectClassUtilKt.getDesc(class1));
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "sb.toString()");
        return s;
    }
}

