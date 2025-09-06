package com.google.crypto.tink.mac;

import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class MacConfig {
    public static final String HMAC_TYPE_URL;
    @Deprecated
    public static final RegistryConfig LATEST;
    @Deprecated
    public static final RegistryConfig TINK_1_0_0;
    @Deprecated
    public static final RegistryConfig TINK_1_1_0;

    static {
        try {
            MacConfig.HMAC_TYPE_URL = "type.googleapis.com/google.crypto.tink.HmacKey";
            RegistryConfig registryConfig0 = RegistryConfig.getDefaultInstance();
            MacConfig.TINK_1_0_0 = registryConfig0;
            MacConfig.TINK_1_1_0 = registryConfig0;
            MacConfig.LATEST = registryConfig0;
            MacConfig.init();
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new ExceptionInInitializerError(generalSecurityException0);
        }
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        MacConfig.register();
    }

    public static void register() throws GeneralSecurityException {
        MacWrapper.register();
        ChunkedMacWrapper.register();
        HmacKeyManager.register(true);
        if(TinkFips.useOnlyFips()) {
            return;
        }
        AesCmacKeyManager.register(true);
    }

    @Deprecated
    public static void registerStandardKeyTypes() throws GeneralSecurityException {
        MacConfig.register();
    }
}

