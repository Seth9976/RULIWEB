package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;
import java.util.List;

@Immutable
public final class EcdsaVerifyJce implements PublicKeyVerify {
    public static final AlgorithmFipsCompatibility FIPS;
    private final EcdsaEncoding encoding;
    private final ECPublicKey publicKey;
    private final String signatureAlgorithm;

    static {
        EcdsaVerifyJce.FIPS = AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public EcdsaVerifyJce(ECPublicKey eCPublicKey0, HashType enums$HashType0, EcdsaEncoding ellipticCurves$EcdsaEncoding0) throws GeneralSecurityException {
        if(!EcdsaVerifyJce.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use ECDSA in FIPS-mode, as BoringCrypto is not available.");
        }
        EllipticCurves.checkPublicKey(eCPublicKey0);
        this.signatureAlgorithm = SubtleUtil.toEcdsaAlgo(enums$HashType0);
        this.publicKey = eCPublicKey0;
        this.encoding = ellipticCurves$EcdsaEncoding0;
    }

    @Override  // com.google.crypto.tink.PublicKeyVerify
    public void verify(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(this.encoding == EcdsaEncoding.IEEE_P1363) {
            int v = EllipticCurves.fieldSizeInBytes(this.publicKey.getParams().getCurve());
            if(arr_b.length != v * 2) {
                throw new GeneralSecurityException("Invalid signature");
            }
            arr_b = EllipticCurves.ecdsaIeee2Der(arr_b);
        }
        if(!EllipticCurves.isValidDerEncoding(arr_b)) {
            throw new GeneralSecurityException("Invalid signature");
        }
        List list0 = EngineFactory.toProviderList(new String[]{"GmsCore_OpenSSL", "AndroidOpenSSL", "Conscrypt"});
        Signature signature0 = (Signature)EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm, list0);
        signature0.initVerify(this.publicKey);
        signature0.update(arr_b1);
        try {
            boolean z = false;
            z = signature0.verify(arr_b);
        }
        catch(RuntimeException unused_ex) {
        }
        if(!z) {
            throw new GeneralSecurityException("Invalid signature");
        }
    }
}

