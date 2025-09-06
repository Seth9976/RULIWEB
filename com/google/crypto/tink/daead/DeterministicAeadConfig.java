package com.google.crypto.tink.daead;

import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class DeterministicAeadConfig {
    public static final String AES_SIV_TYPE_URL;
    @Deprecated
    public static final RegistryConfig LATEST;
    @Deprecated
    public static final RegistryConfig TINK_1_1_0;

    static {
        try {
            DeterministicAeadConfig.AES_SIV_TYPE_URL = "type.googleapis.com/google.crypto.tink.AesSivKey";
            DeterministicAeadConfig.TINK_1_1_0 = RegistryConfig.getDefaultInstance();
            DeterministicAeadConfig.LATEST = RegistryConfig.getDefaultInstance();
            DeterministicAeadConfig.register();
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new ExceptionInInitializerError(generalSecurityException0);
        }
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        DeterministicAeadConfig.register();
    }

    public static void register() throws GeneralSecurityException {
        DeterministicAeadWrapper.register();
        if(TinkFips.useOnlyFips()) {
            return;
        }
        AesSivKeyManager.register(true);
    }
}

