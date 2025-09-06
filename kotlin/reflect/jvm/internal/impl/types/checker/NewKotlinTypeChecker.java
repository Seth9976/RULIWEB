package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;

public interface NewKotlinTypeChecker extends KotlinTypeChecker {
    public static final class Companion {
        static final Companion $$INSTANCE;
        private static final NewKotlinTypeCheckerImpl Default;

        static {
            Companion.$$INSTANCE = new Companion();
            Companion.Default = new NewKotlinTypeCheckerImpl(Default.INSTANCE, null, 2, null);
        }

        public final NewKotlinTypeCheckerImpl getDefault() {
            return Companion.Default;
        }
    }

    public static final Companion Companion;

    static {
        NewKotlinTypeChecker.Companion = Companion.$$INSTANCE;
    }

    KotlinTypeRefiner getKotlinTypeRefiner();

    OverridingUtil getOverridingUtil();
}

