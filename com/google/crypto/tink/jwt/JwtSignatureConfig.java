package com.google.crypto.tink.jwt;

import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class JwtSignatureConfig {
    public static final String JWT_ECDSA_PRIVATE_KEY_TYPE_URL;
    public static final String JWT_ECDSA_PUBLIC_KEY_TYPE_URL;
    public static final String JWT_RSA_PKCS1_PRIVATE_KEY_TYPE_URL;
    public static final String JWT_RSA_PKCS1_PUBLIC_KEY_TYPE_URL;
    public static final String JWT_RSA_PSS_PRIVATE_KEY_TYPE_URL;
    public static final String JWT_RSA_PSS_PUBLIC_KEY_TYPE_URL;
    public static final RegistryConfig LATEST;

    static {
        JwtSignatureConfig.JWT_ECDSA_PUBLIC_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey";
        JwtSignatureConfig.JWT_ECDSA_PRIVATE_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.JwtEcdsaPrivateKey";
        JwtSignatureConfig.JWT_RSA_PKCS1_PRIVATE_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PrivateKey";
        JwtSignatureConfig.JWT_RSA_PKCS1_PUBLIC_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey";
        JwtSignatureConfig.JWT_RSA_PSS_PRIVATE_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPrivateKey";
        JwtSignatureConfig.JWT_RSA_PSS_PUBLIC_KEY_TYPE_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey";
        JwtSignatureConfig.LATEST = RegistryConfig.getDefaultInstance();
    }

    public static void register() throws GeneralSecurityException {
        JwtEcdsaSignKeyManager.registerPair(true);
        JwtRsaSsaPkcs1SignKeyManager.registerPair(true);
        JwtRsaSsaPssSignKeyManager.registerPair(true);
        JwtPublicKeySignWrapper.register();
        JwtPublicKeyVerifyWrapper.register();
    }
}

