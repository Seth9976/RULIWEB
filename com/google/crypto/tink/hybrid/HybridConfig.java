package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class HybridConfig {
    public static final String ECIES_AEAD_HKDF_PRIVATE_KEY_TYPE_URL;
    public static final String ECIES_AEAD_HKDF_PUBLIC_KEY_TYPE_URL;
    @Deprecated
    public static final RegistryConfig LATEST;
    @Deprecated
    public static final RegistryConfig TINK_1_0_0;
    @Deprecated
    public static final RegistryConfig TINK_1_1_0;

    static {
        try {
            HybridConfig.ECIES_AEAD_HKDF_PUBLIC_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
            HybridConfig.ECIES_AEAD_HKDF_PRIVATE_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
            HybridConfig.TINK_1_0_0 = RegistryConfig.getDefaultInstance();
            HybridConfig.TINK_1_1_0 = RegistryConfig.getDefaultInstance();
            HybridConfig.LATEST = RegistryConfig.getDefaultInstance();
            HybridConfig.init();
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new ExceptionInInitializerError(generalSecurityException0);
        }
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        HybridConfig.register();
    }

    public static void register() throws GeneralSecurityException {
        HybridDecryptWrapper.register();
        HybridEncryptWrapper.register();
        AeadConfig.register();
        if(TinkFips.useOnlyFips()) {
            return;
        }
        EciesAeadHkdfPrivateKeyManager.registerPair(true);
        HpkePrivateKeyManager.registerPair(true);
    }
}

