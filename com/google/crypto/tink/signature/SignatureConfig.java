package com.google.crypto.tink.signature;

import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class SignatureConfig {
    public static final String ECDSA_PRIVATE_KEY_TYPE_URL;
    public static final String ECDSA_PUBLIC_KEY_TYPE_URL;
    public static final String ED25519_PRIVATE_KEY_TYPE_URL;
    public static final String ED25519_PUBLIC_KEY_TYPE_URL;
    @Deprecated
    public static final RegistryConfig LATEST;
    public static final String RSA_PKCS1_PRIVATE_KEY_TYPE_URL;
    public static final String RSA_PKCS1_PUBLIC_KEY_TYPE_URL;
    public static final String RSA_PSS_PRIVATE_KEY_TYPE_URL;
    public static final String RSA_PSS_PUBLIC_KEY_TYPE_URL;
    @Deprecated
    public static final RegistryConfig TINK_1_0_0;
    @Deprecated
    public static final RegistryConfig TINK_1_1_0;

    static {
        try {
            SignatureConfig.ECDSA_PUBLIC_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.EcdsaPublicKey";
            SignatureConfig.ECDSA_PRIVATE_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey";
            SignatureConfig.ED25519_PUBLIC_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.Ed25519PublicKey";
            SignatureConfig.ED25519_PRIVATE_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.Ed25519PrivateKey";
            SignatureConfig.RSA_PKCS1_PRIVATE_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey";
            SignatureConfig.RSA_PKCS1_PUBLIC_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PublicKey";
            SignatureConfig.RSA_PSS_PRIVATE_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey";
            SignatureConfig.RSA_PSS_PUBLIC_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey";
            SignatureConfig.TINK_1_0_0 = RegistryConfig.getDefaultInstance();
            SignatureConfig.TINK_1_1_0 = RegistryConfig.getDefaultInstance();
            SignatureConfig.LATEST = RegistryConfig.getDefaultInstance();
            SignatureConfig.init();
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new ExceptionInInitializerError(generalSecurityException0);
        }
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        SignatureConfig.register();
    }

    public static void register() throws GeneralSecurityException {
        PublicKeySignWrapper.register();
        PublicKeyVerifyWrapper.register();
        EcdsaSignKeyManager.registerPair(true);
        RsaSsaPkcs1SignKeyManager.registerPair(true);
        if(TinkFips.useOnlyFips()) {
            return;
        }
        RsaSsaPssSignKeyManager.registerPair(true);
        Ed25519PrivateKeyManager.registerPair(true);
    }
}

