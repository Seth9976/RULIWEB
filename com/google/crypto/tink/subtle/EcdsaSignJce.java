package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.util.List;

@Immutable
public final class EcdsaSignJce implements PublicKeySign {
    public static final AlgorithmFipsCompatibility FIPS;
    private final EcdsaEncoding encoding;
    private final ECPrivateKey privateKey;
    private final String signatureAlgorithm;

    static {
        EcdsaSignJce.FIPS = AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public EcdsaSignJce(ECPrivateKey eCPrivateKey0, HashType enums$HashType0, EcdsaEncoding ellipticCurves$EcdsaEncoding0) throws GeneralSecurityException {
        if(!EcdsaSignJce.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use ECDSA in FIPS-mode, as BoringCrypto is not available.");
        }
        this.privateKey = eCPrivateKey0;
        this.signatureAlgorithm = SubtleUtil.toEcdsaAlgo(enums$HashType0);
        this.encoding = ellipticCurves$EcdsaEncoding0;
    }

    @Override  // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] arr_b) throws GeneralSecurityException {
        List list0 = EngineFactory.toProviderList(new String[]{"GmsCore_OpenSSL", "AndroidOpenSSL", "Conscrypt"});
        Signature signature0 = (Signature)EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm, list0);
        signature0.initSign(this.privateKey);
        signature0.update(arr_b);
        byte[] arr_b1 = signature0.sign();
        return this.encoding == EcdsaEncoding.IEEE_P1363 ? EllipticCurves.ecdsaDer2Ieee(arr_b1, EllipticCurves.fieldSizeInBytes(this.privateKey.getParams().getCurve()) * 2) : arr_b1;
    }
}

