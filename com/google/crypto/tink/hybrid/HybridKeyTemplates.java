package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadDemParams;
import com.google.crypto.tink.proto.EciesAeadHkdfKeyFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EciesHkdfKemParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;

public final class HybridKeyTemplates {
    public static final KeyTemplate ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256;
    public static final KeyTemplate ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM;
    public static final KeyTemplate ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX;
    private static final byte[] EMPTY_SALT;

    static {
        byte[] arr_b = new byte[0];
        HybridKeyTemplates.EMPTY_SALT = arr_b;
        HybridKeyTemplates.ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM = HybridKeyTemplates.createEciesAeadHkdfKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, AeadKeyTemplates.AES128_GCM, OutputPrefixType.TINK, arr_b);
        HybridKeyTemplates.ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX = HybridKeyTemplates.createEciesAeadHkdfKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, AeadKeyTemplates.AES128_GCM, OutputPrefixType.RAW, arr_b);
        HybridKeyTemplates.ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256 = HybridKeyTemplates.createEciesAeadHkdfKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, AeadKeyTemplates.AES128_CTR_HMAC_SHA256, OutputPrefixType.TINK, arr_b);
    }

    public static KeyTemplate createEciesAeadHkdfKeyTemplate(EllipticCurveType ellipticCurveType0, HashType hashType0, EcPointFormat ecPointFormat0, KeyTemplate keyTemplate0, OutputPrefixType outputPrefixType0, byte[] arr_b) {
        EciesAeadHkdfKeyFormat eciesAeadHkdfKeyFormat0 = (EciesAeadHkdfKeyFormat)EciesAeadHkdfKeyFormat.newBuilder().setParams(HybridKeyTemplates.createEciesAeadHkdfParams(ellipticCurveType0, hashType0, ecPointFormat0, keyTemplate0, arr_b)).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey").setOutputPrefixType(outputPrefixType0).setValue(eciesAeadHkdfKeyFormat0.toByteString()).build();
    }

    public static EciesAeadHkdfParams createEciesAeadHkdfParams(EllipticCurveType ellipticCurveType0, HashType hashType0, EcPointFormat ecPointFormat0, KeyTemplate keyTemplate0, byte[] arr_b) {
        EciesHkdfKemParams eciesHkdfKemParams0 = (EciesHkdfKemParams)EciesHkdfKemParams.newBuilder().setCurveType(ellipticCurveType0).setHkdfHashType(hashType0).setHkdfSalt(ByteString.copyFrom(arr_b)).build();
        EciesAeadDemParams eciesAeadDemParams0 = (EciesAeadDemParams)EciesAeadDemParams.newBuilder().setAeadDem(keyTemplate0).build();
        return (EciesAeadHkdfParams)EciesAeadHkdfParams.newBuilder().setKemParams(eciesHkdfKemParams0).setDemParams(eciesAeadDemParams0).setEcPointFormat(ecPointFormat0).build();
    }
}

