package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

@Immutable
public final class RsaSsaPkcs1SignJce implements PublicKeySign {
    public static final AlgorithmFipsCompatibility FIPS;
    private final RSAPrivateCrtKey privateKey;
    private final RSAPublicKey publicKey;
    private final String signatureAlgorithm;

    static {
        RsaSsaPkcs1SignJce.FIPS = AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public RsaSsaPkcs1SignJce(RSAPrivateCrtKey rSAPrivateCrtKey0, HashType enums$HashType0) throws GeneralSecurityException {
        if(!RsaSsaPkcs1SignJce.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use RSA PKCS1.5 in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateSignatureHash(enums$HashType0);
        Validators.validateRsaModulusSize(rSAPrivateCrtKey0.getModulus().bitLength());
        Validators.validateRsaPublicExponent(rSAPrivateCrtKey0.getPublicExponent());
        this.privateKey = rSAPrivateCrtKey0;
        this.signatureAlgorithm = SubtleUtil.toRsaSsaPkcs1Algo(enums$HashType0);
        this.publicKey = (RSAPublicKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA")).generatePublic(new RSAPublicKeySpec(rSAPrivateCrtKey0.getModulus(), rSAPrivateCrtKey0.getPublicExponent()));
    }

    @Override  // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] arr_b) throws GeneralSecurityException {
        Signature signature0 = (Signature)EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm);
        signature0.initSign(this.privateKey);
        signature0.update(arr_b);
        byte[] arr_b1 = signature0.sign();
        Signature signature1 = (Signature)EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm);
        signature1.initVerify(this.publicKey);
        signature1.update(arr_b);
        if(!signature1.verify(arr_b1)) {
            throw new RuntimeException("Security bug: RSA signature computation error");
        }
        return arr_b1;
    }
}

