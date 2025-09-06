package kotlin.reflect.jvm.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001A\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"signature", "", "Ljava/lang/reflect/Method;", "getSignature", "(Ljava/lang/reflect/Method;)Ljava/lang/String;", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class RuntimeTypeMapperKt {
    public static final String access$getSignature(Method method0) {
        return RuntimeTypeMapperKt.getSignature(method0);
    }

    private static final String getSignature(Method method0) {
        Class[] arr_class = method0.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(arr_class, "parameterTypes");
        Class class0 = method0.getReturnType();
        Intrinsics.checkNotNullExpressionValue(class0, "returnType");
        return method0.getName() + ArraysKt.joinToString$default(arr_class, "", "(", ")", 0, null, kotlin.reflect.jvm.internal.RuntimeTypeMapperKt.signature.1.INSTANCE, 24, null) + ReflectClassUtilKt.getDesc(class0);

        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0016\u0010\u0002\u001A\u0012\u0012\u0002\b\u0003 \u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Ljava/lang/Class;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlin.reflect.jvm.internal.RuntimeTypeMapperKt.signature.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.RuntimeTypeMapperKt.signature.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.RuntimeTypeMapperKt.signature.1.INSTANCE = new kotlin.reflect.jvm.internal.RuntimeTypeMapperKt.signature.1();
            }

            kotlin.reflect.jvm.internal.RuntimeTypeMapperKt.signature.1() {
                super(1);
            }

            public final CharSequence invoke(Class class0) {
                Intrinsics.checkNotNullExpressionValue(class0, "it");
                return ReflectClassUtilKt.getDesc(class0);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Class)object0));
            }
        }

    }
}

