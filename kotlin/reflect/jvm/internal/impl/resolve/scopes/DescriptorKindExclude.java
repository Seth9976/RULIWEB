package kotlin.reflect.jvm.internal.impl.resolve.scopes;

public abstract class DescriptorKindExclude {
    public static final class NonExtensions extends DescriptorKindExclude {
        public static final NonExtensions INSTANCE;
        private static final int fullyExcludedDescriptorKinds;

        // 去混淆评级： 低(20)
        static {
            NonExtensions.INSTANCE = new NonExtensions();
            NonExtensions.fullyExcludedDescriptorKinds = 15;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindExclude
        public int getFullyExcludedDescriptorKinds() {
            return NonExtensions.fullyExcludedDescriptorKinds;
        }
    }

    public static final class TopLevelPackages extends DescriptorKindExclude {
        public static final TopLevelPackages INSTANCE;

        static {
            TopLevelPackages.INSTANCE = new TopLevelPackages();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindExclude
        public int getFullyExcludedDescriptorKinds() {
            return 0;
        }
    }

    public abstract int getFullyExcludedDescriptorKinds();

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

