package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public interface LocalClassifierTypeSettings {
    public static final class Default implements LocalClassifierTypeSettings {
        public static final Default INSTANCE;

        static {
            Default.INSTANCE = new Default();
        }

        // 检测为 Lambda 实现
        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.LocalClassifierTypeSettings
        public SimpleType getReplacementTypeForLocalClassifiers() [...]
    }

    SimpleType getReplacementTypeForLocalClassifiers();
}

