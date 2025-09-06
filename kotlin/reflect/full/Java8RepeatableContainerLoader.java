package kotlin.reflect.full;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001:\u0001\u000EB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001A\u00020\u0004H\u0002J \u0010\n\u001A\f\u0012\u0006\b\u0001\u0012\u00020\f\u0018\u00010\u000B2\u000E\u0010\r\u001A\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000BR\u001C\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000F"}, d2 = {"Lkotlin/reflect/full/Java8RepeatableContainerLoader;", "", "()V", "cache", "Lkotlin/reflect/full/Java8RepeatableContainerLoader$Cache;", "getCache", "()Lkotlin/reflect/full/Java8RepeatableContainerLoader$Cache;", "setCache", "(Lkotlin/reflect/full/Java8RepeatableContainerLoader$Cache;)V", "buildCache", "loadRepeatableContainer", "Ljava/lang/Class;", "", "klass", "Cache", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class Java8RepeatableContainerLoader {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B!\u0012\u0010\u0010\u0002\u001A\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u001B\u0010\u0002\u001A\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\f"}, d2 = {"Lkotlin/reflect/full/Java8RepeatableContainerLoader$Cache;", "", "repeatableClass", "Ljava/lang/Class;", "", "valueMethod", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/lang/reflect/Method;)V", "getRepeatableClass", "()Ljava/lang/Class;", "getValueMethod", "()Ljava/lang/reflect/Method;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Cache {
        private final Class repeatableClass;
        private final Method valueMethod;

        public Cache(Class class0, Method method0) {
            this.repeatableClass = class0;
            this.valueMethod = method0;
        }

        public final Class getRepeatableClass() {
            return this.repeatableClass;
        }

        public final Method getValueMethod() {
            return this.valueMethod;
        }
    }

    public static final Java8RepeatableContainerLoader INSTANCE;
    private static Cache cache;

    static {
        Java8RepeatableContainerLoader.INSTANCE = new Java8RepeatableContainerLoader();
    }

    private final Cache buildCache() {
        try {
            Intrinsics.checkNotNull(Repeatable.class, "null cannot be cast to non-null type java.lang.Class<out kotlin.Annotation>");
            return new Cache(Repeatable.class, Repeatable.class.getMethod("value", null));
        }
        catch(ClassNotFoundException unused_ex) {
            return new Cache(null, null);
        }
    }

    public final Class loadRepeatableContainer(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "klass");
        Cache java8RepeatableContainerLoader$Cache0 = Java8RepeatableContainerLoader.cache;
        if(java8RepeatableContainerLoader$Cache0 == null) {
            synchronized(this) {
                Java8RepeatableContainerLoader java8RepeatableContainerLoader0 = Java8RepeatableContainerLoader.INSTANCE;
                Cache java8RepeatableContainerLoader$Cache1 = Java8RepeatableContainerLoader.cache;
                if(java8RepeatableContainerLoader$Cache1 == null) {
                    java8RepeatableContainerLoader$Cache0 = java8RepeatableContainerLoader0.buildCache();
                    Java8RepeatableContainerLoader.cache = java8RepeatableContainerLoader$Cache0;
                }
                else {
                    java8RepeatableContainerLoader$Cache0 = java8RepeatableContainerLoader$Cache1;
                }
            }
        }
        Class class1 = java8RepeatableContainerLoader$Cache0.getRepeatableClass();
        if(class1 == null) {
            return null;
        }
        Annotation annotation0 = class0.getAnnotation(class1);
        if(annotation0 == null) {
            return null;
        }
        Method method0 = java8RepeatableContainerLoader$Cache0.getValueMethod();
        if(method0 == null) {
            return null;
        }
        Object object0 = method0.invoke(annotation0, null);
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type java.lang.Class<out kotlin.Annotation>");
        return (Class)object0;
    }
}

