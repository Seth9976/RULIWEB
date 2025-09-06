package com.google.crypto.tink.signature.internal;

import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.subtle.EllipticCurves.CurveType;
import com.google.crypto.tink.subtle.EllipticCurves.EcdsaEncoding;
import com.google.crypto.tink.subtle.Enums.HashType;
import java.math.BigInteger;
import java.security.GeneralSecurityException;

public final class SigUtil {
    static final String INVALID_PARAMS = "Invalid ECDSA parameters";

    public static CurveType toCurveType(EllipticCurveType ellipticCurveType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.signature.internal.SigUtil.1.$SwitchMap$com$google$crypto$tink$proto$EllipticCurveType[ellipticCurveType0.ordinal()]) {
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
                throw new GeneralSecurityException("unknown curve type: " + ellipticCurveType0.name());
            }
        }
    }

    public static EcdsaEncoding toEcdsaEncoding(EcdsaSignatureEncoding ecdsaSignatureEncoding0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.signature.internal.SigUtil.1.$SwitchMap$com$google$crypto$tink$proto$EcdsaSignatureEncoding[ecdsaSignatureEncoding0.ordinal()]) {
            case 1: {
                return EcdsaEncoding.DER;
            }
            case 2: {
                return EcdsaEncoding.IEEE_P1363;
            }
            default: {
                throw new GeneralSecurityException("unknown ECDSA encoding: " + ecdsaSignatureEncoding0.name());
            }
        }
    }

    public static HashType toHashType(com.google.crypto.tink.proto.HashType hashType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.signature.internal.SigUtil.1.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType0.ordinal()]) {
            case 1: {
                return HashType.SHA256;
            }
            case 2: {
                return HashType.SHA384;
            }
            case 3: {
                return HashType.SHA512;
            }
            default: {
                throw new GeneralSecurityException("unsupported hash type: " + hashType0.name());
            }
        }
    }

    public static ByteString toUnsignedIntByteString(BigInteger bigInteger0) {
        byte[] arr_b = bigInteger0.toByteArray();
        return arr_b[0] == 0 ? ByteString.copyFrom(arr_b, 1, arr_b.length - 1) : ByteString.copyFrom(arr_b);
    }

    public static void validateEcdsaParams(EcdsaParams ecdsaParams0) throws GeneralSecurityException {
        EcdsaSignatureEncoding ecdsaSignatureEncoding0 = ecdsaParams0.getEncoding();
        com.google.crypto.tink.proto.HashType hashType0 = ecdsaParams0.getHashType();
        EllipticCurveType ellipticCurveType0 = ecdsaParams0.getCurve();
        switch(com.google.crypto.tink.signature.internal.SigUtil.1.$SwitchMap$com$google$crypto$tink$proto$EcdsaSignatureEncoding[ecdsaSignatureEncoding0.ordinal()]) {
            case 1: 
            case 2: {
                int v = com.google.crypto.tink.signature.internal.SigUtil.1.$SwitchMap$com$google$crypto$tink$proto$EllipticCurveType[ellipticCurveType0.ordinal()];
                switch(v) {
                    case 1: {
                        if(hashType0 != com.google.crypto.tink.proto.HashType.SHA256) {
                            throw new GeneralSecurityException("Invalid ECDSA parameters");
                        }
                        break;
                    }
                    case 2: {
                        if(hashType0 != com.google.crypto.tink.proto.HashType.SHA384 && hashType0 != com.google.crypto.tink.proto.HashType.SHA512) {
                            throw new GeneralSecurityException("Invalid ECDSA parameters");
                        }
                        break;
                    }
                    default: {
                        if(v != 3 || hashType0 != com.google.crypto.tink.proto.HashType.SHA512) {
                            throw new GeneralSecurityException("Invalid ECDSA parameters");
                        }
                    }
                }
                return;
            }
            default: {
                throw new GeneralSecurityException("unsupported signature encoding");
            }
        }
    }

    public static void validateRsaSsaPkcs1Params(RsaSsaPkcs1Params rsaSsaPkcs1Params0) throws GeneralSecurityException {
        SigUtil.toHashType(rsaSsaPkcs1Params0.getHashType());
    }

    public static void validateRsaSsaPssParams(RsaSsaPssParams rsaSsaPssParams0) throws GeneralSecurityException {
        SigUtil.toHashType(rsaSsaPssParams0.getSigHash());
        if(rsaSsaPssParams0.getSigHash() != rsaSsaPssParams0.getMgf1Hash()) {
            throw new GeneralSecurityException("MGF1 hash is different from signature hash");
        }
        if(rsaSsaPssParams0.getSaltLength() < 0) {
            throw new GeneralSecurityException("salt length is negative");
        }
    }
}

