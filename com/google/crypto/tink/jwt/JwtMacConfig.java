package com.google.crypto.tink.jwt;

import java.security.GeneralSecurityException;

public final class JwtMacConfig {
    public static final String JWT_HMAC_TYPE_URL;

    static {
        JwtMacConfig.JWT_HMAC_TYPE_URL = "type.googleapis.com/google.crypto.tink.JwtHmacKey";
    }

    public static void register() throws GeneralSecurityException {
        JwtHmacKeyManager.register(true);
        JwtMacWrapper.register();
    }
}

