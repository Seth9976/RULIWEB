package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Method;
import kotlin.jvm.internal.Intrinsics;

final class Java16RecordComponentsLoader {
    public static final class Cache {
        private final Method getAccessor;
        private final Method getType;

        public Cache(Method method0, Method method1) {
            this.getType = method0;
            this.getAccessor = method1;
        }

        public final Method getGetAccessor() {
            return this.getAccessor;
        }

        public final Method getGetType() {
            return this.getType;
        }
    }

    public static final Java16RecordComponentsLoader INSTANCE;
    private static Cache _cache;

    static {
        Java16RecordComponentsLoader.INSTANCE = new Java16RecordComponentsLoader();
    }

    private final Cache buildCache(Object object0) {
        Class class0 = object0.getClass();
        try {
            return new Cache(class0.getMethod("getType", null), class0.getMethod("getAccessor", null));
        }
        catch(NoSuchMethodException unused_ex) {
            return new Cache(null, null);
        }
    }

    private final Cache initCache(Object object0) {
        Cache java16RecordComponentsLoader$Cache0 = Java16RecordComponentsLoader._cache;
        if(java16RecordComponentsLoader$Cache0 == null) {
            Cache java16RecordComponentsLoader$Cache1 = this.buildCache(object0);
            Java16RecordComponentsLoader._cache = java16RecordComponentsLoader$Cache1;
            return java16RecordComponentsLoader$Cache1;
        }
        return java16RecordComponentsLoader$Cache0;
    }

    public final Method loadGetAccessor(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "recordComponent");
        Method method0 = this.initCache(object0).getGetAccessor();
        if(method0 == null) {
            return null;
        }
        Object object1 = method0.invoke(object0, null);
        Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type java.lang.reflect.Method");
        return (Method)object1;
    }

    public final Class loadGetType(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "recordComponent");
        Method method0 = this.initCache(object0).getGetType();
        if(method0 == null) {
            return null;
        }
        Object object1 = method0.invoke(object0, null);
        Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type java.lang.Class<*>");
        return (Class)object1;
    }
}

