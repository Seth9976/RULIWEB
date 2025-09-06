package kotlin.reflect.jvm.internal.impl.load.java.lazy;

public interface JavaResolverSettings {
    public static final class Companion {
        static final Companion $$INSTANCE;

        static {
            Companion.$$INSTANCE = new Companion();
        }
    }

    public static final class Default implements JavaResolverSettings {
        public static final Default INSTANCE;

        static {
            Default.INSTANCE = new Default();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings
        public boolean getCorrectNullabilityForNotNullTypeParameter() {
            return false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings
        public boolean getEnhancePrimitiveArrays() {
            return false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings
        public boolean getIgnoreNullabilityForErasedValueParameters() {
            return false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings
        public boolean getTypeEnhancementImprovementsInStrictMode() {
            return false;
        }
    }

    public static final Companion Companion;

    static {
        JavaResolverSettings.Companion = Companion.$$INSTANCE;
    }

    boolean getCorrectNullabilityForNotNullTypeParameter();

    boolean getEnhancePrimitiveArrays();

    boolean getIgnoreNullabilityForErasedValueParameters();

    boolean getTypeEnhancementImprovementsInStrictMode();
}

