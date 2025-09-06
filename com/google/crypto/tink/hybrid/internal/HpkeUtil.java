package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.HpkeAead;
import com.google.crypto.tink.proto.HpkeKdf;
import com.google.crypto.tink.proto.HpkeKem;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EllipticCurves.CurveType;
import java.security.GeneralSecurityException;

public final class HpkeUtil {
    public static final byte[] AES_128_GCM_AEAD_ID;
    public static final byte[] AES_256_GCM_AEAD_ID;
    public static final byte[] BASE_MODE;
    public static final byte[] CHACHA20_POLY1305_AEAD_ID;
    public static final byte[] EMPTY_SALT;
    public static final byte[] HKDF_SHA256_KDF_ID;
    public static final byte[] HKDF_SHA384_KDF_ID;
    public static final byte[] HKDF_SHA512_KDF_ID;
    private static final byte[] HPKE;
    private static final byte[] HPKE_V1;
    private static final byte[] KEM;
    public static final byte[] P256_HKDF_SHA256_KEM_ID;
    public static final byte[] P384_HKDF_SHA384_KEM_ID;
    public static final byte[] P521_HKDF_SHA512_KEM_ID;
    public static final byte[] X25519_HKDF_SHA256_KEM_ID;

    static {
        HpkeUtil.BASE_MODE = new byte[]{0};
        HpkeUtil.X25519_HKDF_SHA256_KEM_ID = new byte[]{0, 0x20};
        HpkeUtil.P256_HKDF_SHA256_KEM_ID = new byte[]{0, 16};
        HpkeUtil.P384_HKDF_SHA384_KEM_ID = new byte[]{0, 17};
        HpkeUtil.P521_HKDF_SHA512_KEM_ID = new byte[]{0, 18};
        HpkeUtil.HKDF_SHA256_KDF_ID = new byte[]{0, 1};
        HpkeUtil.HKDF_SHA384_KDF_ID = new byte[]{0, 2};
        HpkeUtil.HKDF_SHA512_KDF_ID = new byte[]{0, 3};
        HpkeUtil.AES_128_GCM_AEAD_ID = new byte[]{0, 1};
        HpkeUtil.AES_256_GCM_AEAD_ID = new byte[]{0, 2};
        HpkeUtil.CHACHA20_POLY1305_AEAD_ID = new byte[]{0, 3};
        HpkeUtil.EMPTY_SALT = new byte[0];
        HpkeUtil.KEM = new byte[]{75, 69, 77};
        HpkeUtil.HPKE = new byte[]{72, 80, 75, 69};
        HpkeUtil.HPKE_V1 = new byte[]{72, 80, 75, 69, 45, 0x76, 49};
    }

    static byte[] hpkeSuiteId(byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        return Bytes.concat(new byte[][]{HpkeUtil.HPKE, arr_b, arr_b1, arr_b2});
    }

    public static byte[] intToByteArray(int v, int v1) {
        byte[] arr_b = new byte[v];
        for(int v2 = 0; v2 < v; ++v2) {
            arr_b[v2] = (byte)(v1 >> (v - v2 - 1) * 8 & 0xFF);
        }
        return arr_b;
    }

    static byte[] kemSuiteId(byte[] arr_b) throws GeneralSecurityException [...] // 潜在的解密器

    static byte[] labelIkm(String s, byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        return Bytes.concat(new byte[][]{HpkeUtil.HPKE_V1, arr_b1, s.getBytes(Util.UTF_8), arr_b});
    }

    static byte[] labelInfo(String s, byte[] arr_b, byte[] arr_b1, int v) throws GeneralSecurityException {
        return Bytes.concat(new byte[][]{HpkeUtil.intToByteArray(2, v), HpkeUtil.HPKE_V1, arr_b1, s.getBytes(Util.UTF_8), arr_b});
    }

    static CurveType nistHpkeKemToCurve(HpkeKem hpkeKem0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.hybrid.internal.HpkeUtil.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[hpkeKem0.ordinal()]) {
            case 1: {
                return CurveType.NIST_P256;
            }
            case 2: {
                return CurveType.NIST_P384;
            }
            case 3: {
                return CurveType.NIST_P521;
            }
            default: {
                throw new GeneralSecurityException("Unrecognized NIST HPKE KEM identifier");
            }
        }
    }

    static void validateParams(HpkeParams hpkeParams0) throws GeneralSecurityException {
        if(hpkeParams0.getKem() == HpkeKem.KEM_UNKNOWN || hpkeParams0.getKem() == HpkeKem.UNRECOGNIZED) {
            throw new GeneralSecurityException("Invalid KEM param: " + hpkeParams0.getKem().name());
        }
        if(hpkeParams0.getKdf() == HpkeKdf.KDF_UNKNOWN || hpkeParams0.getKdf() == HpkeKdf.UNRECOGNIZED) {
            throw new GeneralSecurityException("Invalid KDF param: " + hpkeParams0.getKdf().name());
        }
        if(hpkeParams0.getAead() == HpkeAead.AEAD_UNKNOWN || hpkeParams0.getAead() == HpkeAead.UNRECOGNIZED) {
            throw new GeneralSecurityException("Invalid AEAD param: " + hpkeParams0.getAead().name());
        }
    }

    class com.google.crypto.tink.hybrid.internal.HpkeUtil.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$HpkeKem;

        static {
            int[] arr_v = new int[HpkeKem.values().length];
            com.google.crypto.tink.hybrid.internal.HpkeUtil.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem = arr_v;
            try {
                arr_v[HpkeKem.DHKEM_P256_HKDF_SHA256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.HpkeUtil.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[HpkeKem.DHKEM_P384_HKDF_SHA384.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.HpkeUtil.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[HpkeKem.DHKEM_P521_HKDF_SHA512.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

