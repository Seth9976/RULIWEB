package com.google.crypto.tink.subtle;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

public final class SelfKeyTestValidators {
    private static final ByteString TEST_MESSAGE;

    static {
        SelfKeyTestValidators.TEST_MESSAGE = ByteString.copyFromUtf8("Tink and Wycheproof.");
    }

    public static final void validateEcdsa(ECPrivateKey eCPrivateKey0, ECPublicKey eCPublicKey0, HashType enums$HashType0, EcdsaEncoding ellipticCurves$EcdsaEncoding0) throws GeneralSecurityException {
        EcdsaSignJce ecdsaSignJce0 = new EcdsaSignJce(eCPrivateKey0, enums$HashType0, ellipticCurves$EcdsaEncoding0);
        EcdsaVerifyJce ecdsaVerifyJce0 = new EcdsaVerifyJce(eCPublicKey0, enums$HashType0, ellipticCurves$EcdsaEncoding0);
        try {
            ecdsaVerifyJce0.verify(ecdsaSignJce0.sign(SelfKeyTestValidators.TEST_MESSAGE.toByteArray()), SelfKeyTestValidators.TEST_MESSAGE.toByteArray());
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new GeneralSecurityException("ECDSA signing with private key followed by verifying with public key failed. The key may be corrupted.", generalSecurityException0);
        }
    }

    public static final void validateRsaSsaPkcs1(RSAPrivateCrtKey rSAPrivateCrtKey0, RSAPublicKey rSAPublicKey0, HashType enums$HashType0) throws GeneralSecurityException {
        RsaSsaPkcs1SignJce rsaSsaPkcs1SignJce0 = new RsaSsaPkcs1SignJce(rSAPrivateCrtKey0, enums$HashType0);
        RsaSsaPkcs1VerifyJce rsaSsaPkcs1VerifyJce0 = new RsaSsaPkcs1VerifyJce(rSAPublicKey0, enums$HashType0);
        try {
            rsaSsaPkcs1VerifyJce0.verify(rsaSsaPkcs1SignJce0.sign(SelfKeyTestValidators.TEST_MESSAGE.toByteArray()), SelfKeyTestValidators.TEST_MESSAGE.toByteArray());
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new GeneralSecurityException("RSA PKCS1 signing with private key followed by verifying with public key failed. The key may be corrupted.", generalSecurityException0);
        }
    }

    public static final void validateRsaSsaPss(RSAPrivateCrtKey rSAPrivateCrtKey0, RSAPublicKey rSAPublicKey0, HashType enums$HashType0, HashType enums$HashType1, int v) throws GeneralSecurityException {
        RsaSsaPssSignJce rsaSsaPssSignJce0 = new RsaSsaPssSignJce(rSAPrivateCrtKey0, enums$HashType0, enums$HashType1, v);
        RsaSsaPssVerifyJce rsaSsaPssVerifyJce0 = new RsaSsaPssVerifyJce(rSAPublicKey0, enums$HashType0, enums$HashType1, v);
        try {
            rsaSsaPssVerifyJce0.verify(rsaSsaPssSignJce0.sign(SelfKeyTestValidators.TEST_MESSAGE.toByteArray()), SelfKeyTestValidators.TEST_MESSAGE.toByteArray());
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new GeneralSecurityException("RSA PSS signing with private key followed by verifying with public key failed. The key may be corrupted.", generalSecurityException0);
        }
    }
}

