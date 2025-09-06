package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Method;
import kotlin.jvm.internal.Intrinsics;

final class Java16SealedRecordLoader {
    public static final class Cache {
        private final Method getPermittedSubclasses;
        private final Method getRecordComponents;
        private final Method isRecord;
        private final Method isSealed;

        public Cache(Method method0, Method method1, Method method2, Method method3) {
            this.isSealed = method0;
            this.getPermittedSubclasses = method1;
            this.isRecord = method2;
            this.getRecordComponents = method3;
        }

        public final Method getGetPermittedSubclasses() {
            return this.getPermittedSubclasses;
        }

        public final Method getGetRecordComponents() {
            return this.getRecordComponents;
        }

        public final Method isRecord() {
            return this.isRecord;
        }

        public final Method isSealed() {
            return this.isSealed;
        }
    }

    public static final Java16SealedRecordLoader INSTANCE;
    private static Cache _cache;

    static {
        Java16SealedRecordLoader.INSTANCE = new Java16SealedRecordLoader();
    }

    private final Cache buildCache() {
        try {
            return new Cache(Class.class.getMethod("isSealed", null), Class.class.getMethod("getPermittedSubclasses", null), Class.class.getMethod("isRecord", null), Class.class.getMethod("getRecordComponents", null));
        }
        catch(NoSuchMethodException unused_ex) {
            return new Cache(null, null, null, null);
        }
    }

    private final Cache initCache() {
        Cache java16SealedRecordLoader$Cache0 = Java16SealedRecordLoader._cache;
        if(java16SealedRecordLoader$Cache0 == null) {
            java16SealedRecordLoader$Cache0 = this.buildCache();
            Java16SealedRecordLoader._cache = java16SealedRecordLoader$Cache0;
        }
        return java16SealedRecordLoader$Cache0;
    }

    public final Class[] loadGetPermittedSubclasses(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "clazz");
        Method method0 = this.initCache().getGetPermittedSubclasses();
        if(method0 == null) {
            return null;
        }
        Object object0 = method0.invoke(class0, null);
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<java.lang.Class<*>>");
        return (Class[])object0;
    }

    public final Object[] loadGetRecordComponents(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "clazz");
        Method method0 = this.initCache().getGetRecordComponents();
        return method0 == null ? null : ((Object[])method0.invoke(class0, null));
    }

    public final Boolean loadIsRecord(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "clazz");
        Method method0 = this.initCache().isRecord();
        if(method0 == null) {
            return null;
        }
        Object object0 = method0.invoke(class0, null);
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Boolean");
        return (Boolean)object0;
    }

    public final Boolean loadIsSealed(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "clazz");
        Method method0 = this.initCache().isSealed();
        if(method0 == null) {
            return null;
        }
        Object object0 = method0.invoke(class0, null);
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Boolean");
        return (Boolean)object0;
    }
}

