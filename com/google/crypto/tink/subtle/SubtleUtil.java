package com.google.crypto.tink.subtle;

import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.internal.Util;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

public final class SubtleUtil {
    @Deprecated
    public static int androidApiLevel() {
        Integer integer0 = Util.getAndroidApiLevel();
        return integer0 == null ? -1 : ((int)integer0);
    }

    public static BigInteger bytes2Integer(byte[] arr_b) {
        return BigIntegerEncoding.fromUnsignedBigEndianBytes(arr_b);
    }

    public static byte[] integer2Bytes(BigInteger bigInteger0, int v) throws GeneralSecurityException {
        return BigIntegerEncoding.toBigEndianBytesOfFixedLength(bigInteger0, v);
    }

    // 去混淆评级： 低(30)
    public static boolean isAndroid() {
        return true;
    }

    public static byte[] mgf1(byte[] arr_b, int v, HashType enums$HashType0) throws GeneralSecurityException {
        String s = SubtleUtil.toDigestAlgo(enums$HashType0);
        MessageDigest messageDigest0 = (MessageDigest)EngineFactory.MESSAGE_DIGEST.getInstance(s);
        int v1 = messageDigest0.getDigestLength();
        byte[] arr_b1 = new byte[v];
        int v3 = 0;
        for(int v2 = 0; v2 <= (v - 1) / v1; ++v2) {
            messageDigest0.reset();
            messageDigest0.update(arr_b);
            messageDigest0.update(SubtleUtil.integer2Bytes(BigInteger.valueOf(v2), 4));
            byte[] arr_b2 = messageDigest0.digest();
            System.arraycopy(arr_b2, 0, arr_b1, v3, Math.min(arr_b2.length, v - v3));
            v3 += arr_b2.length;
        }
        return arr_b1;
    }

    public static void putAsUnsigedInt(ByteBuffer byteBuffer0, long v) throws GeneralSecurityException {
        if(0L > v || v >= 0x100000000L) {
            throw new GeneralSecurityException("Index out of range");
        }
        byteBuffer0.putInt(((int)v));
    }

    public static String toDigestAlgo(HashType enums$HashType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.subtle.SubtleUtil.1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[enums$HashType0.ordinal()]) {
            case 1: {
                return "SHA-1";
            }
            case 2: {
                return "SHA-224";
            }
            case 3: {
                return "SHA-256";
            }
            case 4: {
                return "SHA-384";
            }
            case 5: {
                return "SHA-512";
            }
            default: {
                throw new GeneralSecurityException("Unsupported hash " + enums$HashType0);
            }
        }
    }

    public static String toEcdsaAlgo(HashType enums$HashType0) throws GeneralSecurityException {
        Validators.validateSignatureHash(enums$HashType0);
        return enums$HashType0 + "withECDSA";
    }

    public static String toRsaSsaPkcs1Algo(HashType enums$HashType0) throws GeneralSecurityException {
        Validators.validateSignatureHash(enums$HashType0);
        return enums$HashType0 + "withRSA";
    }
}

