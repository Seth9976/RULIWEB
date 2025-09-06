package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

final class Java8ParameterNamesLoader {
    public static final class Cache {
        private final Method getName;
        private final Method getParameters;

        public Cache(Method method0, Method method1) {
            this.getParameters = method0;
            this.getName = method1;
        }

        public final Method getGetName() {
            return this.getName;
        }

        public final Method getGetParameters() {
            return this.getParameters;
        }
    }

    public static final Java8ParameterNamesLoader INSTANCE;
    private static Cache cache;

    static {
        Java8ParameterNamesLoader.INSTANCE = new Java8ParameterNamesLoader();
    }

    public final Cache buildCache(Member member0) {
        Intrinsics.checkNotNullParameter(member0, "member");
        Class class0 = member0.getClass();
        try {
            return new Cache(class0.getMethod("getParameters", null), ReflectClassUtilKt.getSafeClassLoader(class0).loadClass("java.lang.reflect.Parameter").getMethod("getName", null));
        }
        catch(NoSuchMethodException unused_ex) {
            return new Cache(null, null);
        }
    }

    public final List loadParameterNames(Member member0) {
        Intrinsics.checkNotNullParameter(member0, "member");
        Cache java8ParameterNamesLoader$Cache0 = Java8ParameterNamesLoader.cache;
        if(java8ParameterNamesLoader$Cache0 == null) {
            synchronized(this) {
                Java8ParameterNamesLoader java8ParameterNamesLoader0 = Java8ParameterNamesLoader.INSTANCE;
                Cache java8ParameterNamesLoader$Cache1 = Java8ParameterNamesLoader.cache;
                if(java8ParameterNamesLoader$Cache1 == null) {
                    java8ParameterNamesLoader$Cache0 = java8ParameterNamesLoader0.buildCache(member0);
                    Java8ParameterNamesLoader.cache = java8ParameterNamesLoader$Cache0;
                }
                else {
                    java8ParameterNamesLoader$Cache0 = java8ParameterNamesLoader$Cache1;
                }
            }
        }
        Method method0 = java8ParameterNamesLoader$Cache0.getGetParameters();
        if(method0 == null) {
            return null;
        }
        Method method1 = java8ParameterNamesLoader$Cache0.getGetName();
        if(method1 == null) {
            return null;
        }
        Object object0 = method0.invoke(member0, null);
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<*>");
        ArrayList arrayList0 = new ArrayList(((Object[])object0).length);
        for(int v1 = 0; v1 < ((Object[])object0).length; ++v1) {
            Object object1 = method1.invoke(((Object[])object0)[v1], null);
            Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.String");
            arrayList0.add(((String)object1));
        }
        return arrayList0;
    }
}

