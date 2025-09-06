package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.subtle.EllipticCurves.CurveType;
import com.google.crypto.tink.subtle.EllipticCurves.PointFormatType;
import com.google.crypto.tink.subtle.EllipticCurves;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

final class HybridUtil {
    public static CurveType toCurveType(EllipticCurveType ellipticCurveType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$EllipticCurveType[ellipticCurveType0.ordinal()]) {
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
                throw new GeneralSecurityException("unknown curve type: " + ellipticCurveType0);
            }
        }
    }

    public static String toHmacAlgo(HashType hashType0) throws NoSuchAlgorithmException {
        switch(com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType0.ordinal()]) {
            case 1: {
                return "HmacSha1";
            }
            case 2: {
                return "HmacSha224";
            }
            case 3: {
                return "HmacSha256";
            }
            case 4: {
                return "HmacSha384";
            }
            case 5: {
                return "HmacSha512";
            }
            default: {
                throw new NoSuchAlgorithmException("hash unsupported for HMAC: " + hashType0);
            }
        }
    }

    public static PointFormatType toPointFormatType(EcPointFormat ecPointFormat0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$EcPointFormat[ecPointFormat0.ordinal()]) {
            case 1: {
                return PointFormatType.UNCOMPRESSED;
            }
            case 2: {
                return PointFormatType.DO_NOT_USE_CRUNCHY_UNCOMPRESSED;
            }
            case 3: {
                return PointFormatType.COMPRESSED;
            }
            default: {
                throw new GeneralSecurityException("unknown point format: " + ecPointFormat0);
            }
        }
    }

    public static void validate(EciesAeadHkdfParams eciesAeadHkdfParams0) throws GeneralSecurityException {
        EllipticCurves.getCurveSpec(HybridUtil.toCurveType(eciesAeadHkdfParams0.getKemParams().getCurveType()));
        HybridUtil.toHmacAlgo(eciesAeadHkdfParams0.getKemParams().getHkdfHashType());
        if(eciesAeadHkdfParams0.getEcPointFormat() == EcPointFormat.UNKNOWN_FORMAT) {
            throw new GeneralSecurityException("unknown EC point format");
        }
        Registry.newKeyData(eciesAeadHkdfParams0.getDemParams().getAeadDem());
    }

    class com.google.crypto.tink.hybrid.HybridUtil.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$EcPointFormat;
        static final int[] $SwitchMap$com$google$crypto$tink$proto$EllipticCurveType;
        static final int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        static {
            int[] arr_v = new int[EcPointFormat.values().length];
            com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$EcPointFormat = arr_v;
            try {
                arr_v[EcPointFormat.UNCOMPRESSED.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$EcPointFormat[EcPointFormat.DO_NOT_USE_CRUNCHY_UNCOMPRESSED.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$EcPointFormat[EcPointFormat.COMPRESSED.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v1 = new int[EllipticCurveType.values().length];
            com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$EllipticCurveType = arr_v1;
            try {
                arr_v1[EllipticCurveType.NIST_P256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$EllipticCurveType[EllipticCurveType.NIST_P384.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$EllipticCurveType[EllipticCurveType.NIST_P521.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v2 = new int[HashType.values().length];
            com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$HashType = arr_v2;
            try {
                arr_v2[HashType.SHA1.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA224.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA256.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA384.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.HybridUtil.1.$SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

