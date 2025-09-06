package com.google.crypto.tink.util;

import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.subtle.Random;
import com.google.errorprone.annotations.Immutable;
import java.security.MessageDigest;

@Immutable
public final class SecretBytes {
    private final Bytes bytes;

    private SecretBytes(Bytes bytes0) {
        this.bytes = bytes0;
    }

    public static SecretBytes copyFrom(byte[] arr_b, SecretKeyAccess secretKeyAccess0) {
        if(secretKeyAccess0 == null) {
            throw new NullPointerException("SecretKeyAccess required");
        }
        return new SecretBytes(Bytes.copyFrom(arr_b));
    }

    public boolean equalsSecretBytes(SecretBytes secretBytes0) {
        return MessageDigest.isEqual(this.bytes.toByteArray(), secretBytes0.bytes.toByteArray());
    }

    public static SecretBytes randomBytes(int v) {
        return new SecretBytes(Bytes.copyFrom(Random.randBytes(v)));
    }

    public int size() {
        return this.bytes.size();
    }

    public byte[] toByteArray(SecretKeyAccess secretKeyAccess0) {
        if(secretKeyAccess0 == null) {
            throw new NullPointerException("SecretKeyAccess required");
        }
        return this.bytes.toByteArray();
    }
}

