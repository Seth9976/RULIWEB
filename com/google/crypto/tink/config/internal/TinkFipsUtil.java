package com.google.crypto.tink.config.internal;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public final class TinkFipsUtil {
    // 部分失败：枚举糖化
    // 枚举按原样呈现，而不是糖化为Java 5枚举。
    public static abstract class AlgorithmFipsCompatibility extends Enum {
        private static final AlgorithmFipsCompatibility[] $VALUES;
        public static final enum AlgorithmFipsCompatibility ALGORITHM_NOT_FIPS;
        public static final enum AlgorithmFipsCompatibility ALGORITHM_REQUIRES_BORINGCRYPTO;

        static {
            com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility.1 tinkFipsUtil$AlgorithmFipsCompatibility$10 = new AlgorithmFipsCompatibility("ALGORITHM_NOT_FIPS", 0) {
                @Override  // com.google.crypto.tink.config.internal.TinkFipsUtil$AlgorithmFipsCompatibility
                public boolean isCompatible() {
                    return !TinkFipsUtil.useOnlyFips();
                }
            };
            AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS = tinkFipsUtil$AlgorithmFipsCompatibility$10;
            com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility.2 tinkFipsUtil$AlgorithmFipsCompatibility$20 = new AlgorithmFipsCompatibility("ALGORITHM_REQUIRES_BORINGCRYPTO", 1) {
                // 去混淆评级： 低(20)
                @Override  // com.google.crypto.tink.config.internal.TinkFipsUtil$AlgorithmFipsCompatibility
                public boolean isCompatible() {
                    return !TinkFipsUtil.useOnlyFips() || TinkFipsUtil.fipsModuleAvailable();
                }
            };
            AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO = tinkFipsUtil$AlgorithmFipsCompatibility$20;
            AlgorithmFipsCompatibility.$VALUES = new AlgorithmFipsCompatibility[]{tinkFipsUtil$AlgorithmFipsCompatibility$10, tinkFipsUtil$AlgorithmFipsCompatibility$20};
        }

        private AlgorithmFipsCompatibility(String s, int v) {
            super(s, v);
        }

        AlgorithmFipsCompatibility(String s, int v, com.google.crypto.tink.config.internal.TinkFipsUtil.1 tinkFipsUtil$10) {
            this(s, v);
        }

        public abstract boolean isCompatible();

        public static AlgorithmFipsCompatibility valueOf(String s) {
            return (AlgorithmFipsCompatibility)Enum.valueOf(AlgorithmFipsCompatibility.class, s);
        }

        public static AlgorithmFipsCompatibility[] values() {
            return (AlgorithmFipsCompatibility[])AlgorithmFipsCompatibility.$VALUES.clone();
        }
    }

    private static final AtomicBoolean isRestrictedToFips;
    private static final Logger logger;

    static {
        TinkFipsUtil.logger = Logger.getLogger("com.google.crypto.tink.config.internal.TinkFipsUtil");
        TinkFipsUtil.isRestrictedToFips = new AtomicBoolean(false);
    }

    static Boolean checkConscryptIsAvailableAndUsesFipsBoringSsl() {
        try {
            return (Boolean)Class.forName("org.conscrypt.Conscrypt").getMethod("isBoringSslFIPSBuild", null).invoke(null, null);
        }
        catch(Exception unused_ex) {
            TinkFipsUtil.logger.info("Conscrypt is not available or does not support checking for FIPS build.");
            return false;
        }
    }

    public static boolean fipsModuleAvailable() {
        return TinkFipsUtil.checkConscryptIsAvailableAndUsesFipsBoringSsl().booleanValue();
    }

    public static void setFipsRestricted() {
        TinkFipsUtil.isRestrictedToFips.set(true);
    }

    public static void unsetFipsRestricted() {
        TinkFipsUtil.isRestrictedToFips.set(false);
    }

    public static boolean useOnlyFips() {
        return TinkFipsUtil.isRestrictedToFips.get();
    }

    class com.google.crypto.tink.config.internal.TinkFipsUtil.1 {
    }

}

