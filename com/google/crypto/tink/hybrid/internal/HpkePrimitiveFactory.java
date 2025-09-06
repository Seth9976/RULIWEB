package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.subtle.EllipticCurves.CurveType;
import java.security.GeneralSecurityException;
import java.util.Arrays;

final class HpkePrimitiveFactory {
    static HpkeAead createAead(HpkeParams hpkeParams0) throws GeneralSecurityException {
        if(hpkeParams0.getAead() == com.google.crypto.tink.proto.HpkeAead.AES_128_GCM) {
            return new AesGcmHpkeAead(16);
        }
        if(hpkeParams0.getAead() == com.google.crypto.tink.proto.HpkeAead.AES_256_GCM) {
            return new AesGcmHpkeAead(0x20);
        }
        if(hpkeParams0.getAead() != com.google.crypto.tink.proto.HpkeAead.CHACHA20_POLY1305) {
            throw new IllegalArgumentException("Unrecognized HPKE AEAD identifier");
        }
        return new ChaCha20Poly1305HpkeAead();
    }

    static HpkeAead createAead(byte[] arr_b) throws GeneralSecurityException {
        if(Arrays.equals(arr_b, HpkeUtil.AES_128_GCM_AEAD_ID)) {
            return new AesGcmHpkeAead(16);
        }
        if(Arrays.equals(arr_b, HpkeUtil.AES_256_GCM_AEAD_ID)) {
            return new AesGcmHpkeAead(0x20);
        }
        if(!Arrays.equals(arr_b, HpkeUtil.CHACHA20_POLY1305_AEAD_ID)) {
            throw new IllegalArgumentException("Unrecognized HPKE AEAD identifier");
        }
        return new ChaCha20Poly1305HpkeAead();
    }

    static HpkeKdf createKdf(HpkeParams hpkeParams0) {
        if(hpkeParams0.getKdf() == com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256) {
            return new HkdfHpkeKdf("HmacSha256");
        }
        if(hpkeParams0.getKdf() == com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA384) {
            return new HkdfHpkeKdf("HmacSha384");
        }
        if(hpkeParams0.getKdf() != com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA512) {
            throw new IllegalArgumentException("Unrecognized HPKE KDF identifier");
        }
        return new HkdfHpkeKdf("HmacSha512");
    }

    static HpkeKdf createKdf(byte[] arr_b) {
        if(Arrays.equals(arr_b, HpkeUtil.HKDF_SHA256_KDF_ID)) {
            return new HkdfHpkeKdf("HmacSha256");
        }
        if(Arrays.equals(arr_b, HpkeUtil.HKDF_SHA384_KDF_ID)) {
            return new HkdfHpkeKdf("HmacSha384");
        }
        if(!Arrays.equals(arr_b, HpkeUtil.HKDF_SHA512_KDF_ID)) {
            throw new IllegalArgumentException("Unrecognized HPKE KDF identifier");
        }
        return new HkdfHpkeKdf("HmacSha512");
    }

    static HpkeKem createKem(HpkeParams hpkeParams0) throws GeneralSecurityException {
        if(hpkeParams0.getKem() == com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256) {
            return new X25519HpkeKem(new HkdfHpkeKdf("HmacSha256"));
        }
        if(hpkeParams0.getKem() == com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256) {
            return NistCurvesHpkeKem.fromCurve(CurveType.NIST_P256);
        }
        if(hpkeParams0.getKem() == com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384) {
            return NistCurvesHpkeKem.fromCurve(CurveType.NIST_P384);
        }
        if(hpkeParams0.getKem() != com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512) {
            throw new IllegalArgumentException("Unrecognized HPKE KEM identifier");
        }
        return NistCurvesHpkeKem.fromCurve(CurveType.NIST_P521);
    }

    static HpkeKem createKem(byte[] arr_b) throws GeneralSecurityException {
        if(Arrays.equals(arr_b, HpkeUtil.X25519_HKDF_SHA256_KEM_ID)) {
            return new X25519HpkeKem(new HkdfHpkeKdf("HmacSha256"));
        }
        if(Arrays.equals(arr_b, HpkeUtil.P256_HKDF_SHA256_KEM_ID)) {
            return NistCurvesHpkeKem.fromCurve(CurveType.NIST_P256);
        }
        if(Arrays.equals(arr_b, HpkeUtil.P384_HKDF_SHA384_KEM_ID)) {
            return NistCurvesHpkeKem.fromCurve(CurveType.NIST_P384);
        }
        if(!Arrays.equals(arr_b, HpkeUtil.P521_HKDF_SHA512_KEM_ID)) {
            throw new IllegalArgumentException("Unrecognized HPKE KEM identifier");
        }
        return NistCurvesHpkeKem.fromCurve(CurveType.NIST_P521);
    }
}

