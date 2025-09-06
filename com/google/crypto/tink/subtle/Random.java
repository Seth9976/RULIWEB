package com.google.crypto.tink.subtle;

import java.security.SecureRandom;

public final class Random {
    private static final ThreadLocal localRandom;

    static {
        Random.localRandom = new ThreadLocal() {
            @Override
            protected Object initialValue() {
                return this.initialValue();
            }

            protected SecureRandom initialValue() {
                return Random.newDefaultSecureRandom();
            }
        };
    }

    private static SecureRandom newDefaultSecureRandom() {
        SecureRandom secureRandom0 = new SecureRandom();
        secureRandom0.nextLong();
        return secureRandom0;
    }

    public static byte[] randBytes(int v) [...] // 潜在的解密器

    public static final int randInt() {
        return ((SecureRandom)Random.localRandom.get()).nextInt();
    }

    public static final int randInt(int v) {
        return ((SecureRandom)Random.localRandom.get()).nextInt(v);
    }
}

