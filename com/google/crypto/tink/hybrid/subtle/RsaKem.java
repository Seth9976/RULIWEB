package com.google.crypto.tink.hybrid.subtle;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

class RsaKem {
    static final byte[] EMPTY_AAD = null;
    static final int MIN_RSA_KEY_LENGTH_BITS = 0x800;

    static {
        RsaKem.EMPTY_AAD = new byte[0];
    }

    static int bigIntSizeInBytes(BigInteger bigInteger0) {
        return (bigInteger0.bitLength() + 7) / 8;
    }

    static byte[] bigIntToByteArray(BigInteger bigInteger0, int v) {
        byte[] arr_b = bigInteger0.toByteArray();
        if(arr_b.length == v) {
            return arr_b;
        }
        byte[] arr_b1 = new byte[v];
        if(arr_b.length == v + 1) {
            if(arr_b[0] != 0) {
                throw new IllegalArgumentException("Value is one-byte longer than the expected size, but its first byte is not 0");
            }
            System.arraycopy(arr_b, 1, arr_b1, 0, v);
            return arr_b1;
        }
        if(arr_b.length >= v) {
            throw new IllegalArgumentException(String.format("Value has invalid length, must be of length at most (%d + 1), but got %d", v, ((int)arr_b.length)));
        }
        System.arraycopy(arr_b, 0, arr_b1, v - arr_b.length, arr_b.length);
        return arr_b1;
    }

    static KeyPair generateRsaKeyPair(int v) {
        try {
            KeyPairGenerator keyPairGenerator0 = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator0.initialize(v);
            return keyPairGenerator0.generateKeyPair();
        }
        catch(NoSuchAlgorithmException noSuchAlgorithmException0) {
            throw new IllegalStateException("No support for RSA algorithm.", noSuchAlgorithmException0);
        }
    }

    static byte[] generateSecret(BigInteger bigInteger0) {
        BigInteger bigInteger1;
        int v = RsaKem.bigIntSizeInBytes(bigInteger0);
        SecureRandom secureRandom0 = new SecureRandom();
        do {
            bigInteger1 = new BigInteger(bigInteger0.bitLength(), secureRandom0);
        }
        while(bigInteger1.signum() <= 0 || bigInteger1.compareTo(bigInteger0) >= 0);
        return RsaKem.bigIntToByteArray(bigInteger1, v);
    }

    static void validateRsaModulus(BigInteger bigInteger0) throws GeneralSecurityException {
        if(bigInteger0.bitLength() < 0x800) {
            throw new GeneralSecurityException(String.format("RSA key must be of at least size %d bits, but got %d", 0x800, bigInteger0.bitLength()));
        }
    }
}

