package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;

public interface JavaClassesTracker {
    public static final class Default implements JavaClassesTracker {
        public static final Default INSTANCE;

        static {
            Default.INSTANCE = new Default();
        }

        // 检测为 Lambda 实现
        @Override  // kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker
        public void reportClass(JavaClassDescriptor javaClassDescriptor0) [...]
    }

    void reportClass(JavaClassDescriptor arg1);
}

