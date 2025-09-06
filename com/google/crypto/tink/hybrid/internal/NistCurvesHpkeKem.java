package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EllipticCurves.CurveType;
import com.google.crypto.tink.subtle.EllipticCurves.PointFormatType;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;

@Immutable
final class NistCurvesHpkeKem implements HpkeKem {
    private final CurveType curve;
    private final HkdfHpkeKdf hkdf;

    private NistCurvesHpkeKem(HkdfHpkeKdf hkdfHpkeKdf0, CurveType ellipticCurves$CurveType0) {
        this.hkdf = hkdfHpkeKdf0;
        this.curve = ellipticCurves$CurveType0;
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKem
    public byte[] decapsulate(byte[] arr_b, HpkeKemPrivateKey hpkeKemPrivateKey0) throws GeneralSecurityException {
        byte[] arr_b1 = hpkeKemPrivateKey0.getSerializedPrivate().toByteArray();
        return this.deriveKemSharedSecret(EllipticCurves.computeSharedSecret(EllipticCurves.getEcPrivateKey(this.curve, arr_b1), EllipticCurves.getEcPublicKey(this.curve, PointFormatType.UNCOMPRESSED, arr_b)), arr_b, hpkeKemPrivateKey0.getSerializedPublic().toByteArray());
    }

    private byte[] deriveKemSharedSecret(byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        byte[] arr_b3 = Bytes.concat(new byte[][]{arr_b1, arr_b2});
        byte[] arr_b4 = HpkeUtil.kemSuiteId(this.getKemId());
        int v = this.hkdf.getMacLength();
        return this.hkdf.extractAndExpand(null, arr_b, "eae_prk", arr_b3, "shared_secret", arr_b4, v);
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKem
    public HpkeKemEncapOutput encapsulate(byte[] arr_b) throws GeneralSecurityException {
        return this.encapsulate(arr_b, EllipticCurves.generateKeyPair(this.curve));
    }

    HpkeKemEncapOutput encapsulate(byte[] arr_b, KeyPair keyPair0) throws GeneralSecurityException {
        ECPublicKey eCPublicKey0 = EllipticCurves.getEcPublicKey(this.curve, PointFormatType.UNCOMPRESSED, arr_b);
        byte[] arr_b1 = EllipticCurves.computeSharedSecret(((ECPrivateKey)keyPair0.getPrivate()), eCPublicKey0);
        ECPoint eCPoint0 = ((ECPublicKey)keyPair0.getPublic()).getW();
        byte[] arr_b2 = EllipticCurves.pointEncode(this.curve, PointFormatType.UNCOMPRESSED, eCPoint0);
        return new HpkeKemEncapOutput(this.deriveKemSharedSecret(arr_b1, arr_b2, arr_b), arr_b2);
    }

    static NistCurvesHpkeKem fromCurve(CurveType ellipticCurves$CurveType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.hybrid.internal.NistCurvesHpkeKem.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[ellipticCurves$CurveType0.ordinal()]) {
            case 1: {
                return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha256"), CurveType.NIST_P256);
            }
            case 2: {
                return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha384"), CurveType.NIST_P384);
            }
            case 3: {
                return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha512"), CurveType.NIST_P521);
            }
            default: {
                throw new GeneralSecurityException("invalid curve type: " + ellipticCurves$CurveType0);
            }
        }
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKem
    public byte[] getKemId() throws GeneralSecurityException {
        switch(com.google.crypto.tink.hybrid.internal.NistCurvesHpkeKem.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[this.curve.ordinal()]) {
            case 1: {
                return HpkeUtil.P256_HKDF_SHA256_KEM_ID;
            }
            case 2: {
                return HpkeUtil.P384_HKDF_SHA384_KEM_ID;
            }
            case 3: {
                return HpkeUtil.P521_HKDF_SHA512_KEM_ID;
            }
            default: {
                throw new GeneralSecurityException("Could not determine HPKE KEM ID");
            }
        }
    }

    class com.google.crypto.tink.hybrid.internal.NistCurvesHpkeKem.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType;

        static {
            int[] arr_v = new int[CurveType.values().length];
            com.google.crypto.tink.hybrid.internal.NistCurvesHpkeKem.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType = arr_v;
            try {
                arr_v[CurveType.NIST_P256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.NistCurvesHpkeKem.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[CurveType.NIST_P384.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.NistCurvesHpkeKem.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[CurveType.NIST_P521.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

