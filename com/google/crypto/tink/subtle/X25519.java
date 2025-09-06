package com.google.crypto.tink.subtle;

import java.security.InvalidKeyException;
import java.util.Arrays;

public final class X25519 {
    public static byte[] computeSharedSecret(byte[] arr_b, byte[] arr_b1) throws InvalidKeyException {
        if(arr_b.length != 0x20) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        long[] arr_v = new long[11];
        byte[] arr_b2 = Arrays.copyOf(arr_b, 0x20);
        arr_b2[0] = (byte)(arr_b2[0] & 0xF8);
        byte b = (byte)(arr_b2[0x1F] & 0x7F);
        arr_b2[0x1F] = b;
        arr_b2[0x1F] = (byte)(b | 0x40);
        Curve25519.curveMult(arr_v, arr_b2, arr_b1);
        return Field25519.contract(arr_v);
    }

    public static byte[] generatePrivateKey() [...] // 潜在的解密器

    public static byte[] publicFromPrivate(byte[] arr_b) throws InvalidKeyException {
        if(arr_b.length != 0x20) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        byte[] arr_b1 = new byte[0x20];
        arr_b1[0] = 9;
        return X25519.computeSharedSecret(arr_b, arr_b1);
    }
}

