package com.google.crypto.tink.util;

import com.google.crypto.tink.SecretKeyAccess;
import com.google.errorprone.annotations.Immutable;
import java.math.BigInteger;
import java.security.MessageDigest;

@Immutable
public final class SecretBigInteger {
    private final BigInteger value;

    private SecretBigInteger(BigInteger bigInteger0) {
        this.value = bigInteger0;
    }

    public boolean equalsSecretBigInteger(SecretBigInteger secretBigInteger0) {
        return MessageDigest.isEqual(this.value.toByteArray(), secretBigInteger0.value.toByteArray());
    }

    public static SecretBigInteger fromBigInteger(BigInteger bigInteger0, SecretKeyAccess secretKeyAccess0) {
        if(secretKeyAccess0 == null) {
            throw new NullPointerException("SecretKeyAccess required");
        }
        return new SecretBigInteger(bigInteger0);
    }

    public BigInteger getBigInteger(SecretKeyAccess secretKeyAccess0) {
        if(secretKeyAccess0 == null) {
            throw new NullPointerException("SecretKeyAccess required");
        }
        return this.value;
    }
}

