package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class StreamingAeadConfig {
    public static final String AES_CTR_HMAC_STREAMINGAEAD_TYPE_URL;
    public static final String AES_GCM_HKDF_STREAMINGAEAD_TYPE_URL;
    public static final RegistryConfig LATEST;
    @Deprecated
    public static final RegistryConfig TINK_1_1_0;

    static {
        try {
            StreamingAeadConfig.AES_CTR_HMAC_STREAMINGAEAD_TYPE_URL = "type.googleapis.com/google.crypto.tink.AesCtrHmacStreamingKey";
            StreamingAeadConfig.AES_GCM_HKDF_STREAMINGAEAD_TYPE_URL = "type.googleapis.com/google.crypto.tink.AesGcmHkdfStreamingKey";
            StreamingAeadConfig.TINK_1_1_0 = RegistryConfig.getDefaultInstance();
            StreamingAeadConfig.LATEST = RegistryConfig.getDefaultInstance();
            StreamingAeadConfig.init();
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new ExceptionInInitializerError(generalSecurityException0);
        }
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        StreamingAeadConfig.register();
    }

    public static void register() throws GeneralSecurityException {
        StreamingAeadWrapper.register();
        if(TinkFips.useOnlyFips()) {
            return;
        }
        AesCtrHmacStreamingKeyManager.register(true);
        AesGcmHkdfStreamingKeyManager.register(true);
    }
}

