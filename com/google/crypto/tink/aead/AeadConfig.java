package com.google.crypto.tink.aead;

import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.mac.MacConfig;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class AeadConfig {
    public static final String AES_CTR_HMAC_AEAD_TYPE_URL;
    public static final String AES_EAX_TYPE_URL;
    public static final String AES_GCM_SIV_TYPE_URL;
    public static final String AES_GCM_TYPE_URL;
    public static final String CHACHA20_POLY1305_TYPE_URL;
    public static final String KMS_AEAD_TYPE_URL;
    public static final String KMS_ENVELOPE_AEAD_TYPE_URL;
    @Deprecated
    public static final RegistryConfig LATEST;
    @Deprecated
    public static final RegistryConfig TINK_1_0_0;
    @Deprecated
    public static final RegistryConfig TINK_1_1_0;
    public static final String XCHACHA20_POLY1305_TYPE_URL;

    static {
        try {
            AeadConfig.AES_CTR_HMAC_AEAD_TYPE_URL = "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
            AeadConfig.AES_GCM_TYPE_URL = "type.googleapis.com/google.crypto.tink.AesGcmKey";
            AeadConfig.AES_GCM_SIV_TYPE_URL = "type.googleapis.com/google.crypto.tink.AesGcmSivKey";
            AeadConfig.AES_EAX_TYPE_URL = "type.googleapis.com/google.crypto.tink.AesEaxKey";
            AeadConfig.KMS_AEAD_TYPE_URL = "type.googleapis.com/google.crypto.tink.KmsAeadKey";
            AeadConfig.KMS_ENVELOPE_AEAD_TYPE_URL = "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
            AeadConfig.CHACHA20_POLY1305_TYPE_URL = "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
            AeadConfig.XCHACHA20_POLY1305_TYPE_URL = "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
            RegistryConfig registryConfig0 = RegistryConfig.getDefaultInstance();
            AeadConfig.TINK_1_0_0 = registryConfig0;
            AeadConfig.TINK_1_1_0 = registryConfig0;
            AeadConfig.LATEST = registryConfig0;
            AeadConfig.init();
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new ExceptionInInitializerError(generalSecurityException0);
        }
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        AeadConfig.register();
    }

    public static void register() throws GeneralSecurityException {
        AeadWrapper.register();
        MacConfig.register();
        AesCtrHmacAeadKeyManager.register(true);
        AesGcmKeyManager.register(true);
        if(TinkFips.useOnlyFips()) {
            return;
        }
        AesEaxKeyManager.register(true);
        AesGcmSivKeyManager.register(true);
        ChaCha20Poly1305KeyManager.register(true);
        KmsAeadKeyManager.register(true);
        KmsEnvelopeAeadKeyManager.register(true);
        XChaCha20Poly1305KeyManager.register(true);
    }

    @Deprecated
    public static void registerStandardKeyTypes() throws GeneralSecurityException {
        AeadConfig.register();
    }
}

