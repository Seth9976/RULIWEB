package com.google.crypto.tink.config;

import com.google.crypto.tink.daead.DeterministicAeadConfig;
import com.google.crypto.tink.hybrid.HybridConfig;
import com.google.crypto.tink.prf.PrfConfig;
import com.google.crypto.tink.proto.RegistryConfig;
import com.google.crypto.tink.signature.SignatureConfig;
import com.google.crypto.tink.streamingaead.StreamingAeadConfig;
import java.security.GeneralSecurityException;

public final class TinkConfig {
    @Deprecated
    public static final RegistryConfig LATEST;
    @Deprecated
    public static final RegistryConfig TINK_1_0_0;
    @Deprecated
    public static final RegistryConfig TINK_1_1_0;

    static {
        try {
            TinkConfig.TINK_1_0_0 = RegistryConfig.getDefaultInstance();
            TinkConfig.TINK_1_1_0 = RegistryConfig.getDefaultInstance();
            TinkConfig.LATEST = RegistryConfig.getDefaultInstance();
            TinkConfig.init();
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new ExceptionInInitializerError(generalSecurityException0);
        }
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        TinkConfig.register();
    }

    public static void register() throws GeneralSecurityException {
        DeterministicAeadConfig.register();
        HybridConfig.register();
        PrfConfig.register();
        SignatureConfig.register();
        StreamingAeadConfig.register();
    }
}

