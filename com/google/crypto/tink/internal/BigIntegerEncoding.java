package com.google.crypto.tink.internal;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class BigIntegerEncoding {
    public static BigInteger fromUnsignedBigEndianBytes(byte[] arr_b) {
        return new BigInteger(1, arr_b);
    }

    public static byte[] toBigEndianBytes(BigInteger bigInteger0) {
        if(bigInteger0.signum() == -1) {
            throw new IllegalArgumentException("n must not be negative");
        }
        return bigInteger0.toByteArray();
    }

    public static byte[] toBigEndianBytesOfFixedLength(BigInteger bigInteger0, int v) throws GeneralSecurityException {
        if(bigInteger0.signum() == -1) {
            throw new IllegalArgumentException("integer must be nonnegative");
        }
        byte[] arr_b = bigInteger0.toByteArray();
        if(arr_b.length == v) {
            return arr_b;
        }
        if(arr_b.length > v + 1) {
            throw new GeneralSecurityException("integer too large");
        }
        if(arr_b.length == v + 1) {
            if(arr_b[0] != 0) {
                throw new GeneralSecurityException("integer too large");
            }
            return Arrays.copyOfRange(arr_b, 1, arr_b.length);
        }
        byte[] arr_b1 = new byte[v];
        System.arraycopy(arr_b, 0, arr_b1, v - arr_b.length, arr_b.length);
        return arr_b1;
    }
}

