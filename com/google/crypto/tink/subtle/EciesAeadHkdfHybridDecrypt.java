package com.google.crypto.tink.subtle;

import com.google.crypto.tink.HybridDecrypt;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.util.Arrays;

public final class EciesAeadHkdfHybridDecrypt implements HybridDecrypt {
    private static final byte[] EMPTY_AAD;
    private final EciesAeadHkdfDemHelper demHelper;
    private final PointFormatType ecPointFormat;
    private final String hkdfHmacAlgo;
    private final byte[] hkdfSalt;
    private final EciesHkdfRecipientKem recipientKem;
    private final ECPrivateKey recipientPrivateKey;

    static {
        EciesAeadHkdfHybridDecrypt.EMPTY_AAD = new byte[0];
    }

    public EciesAeadHkdfHybridDecrypt(ECPrivateKey eCPrivateKey0, byte[] arr_b, String s, PointFormatType ellipticCurves$PointFormatType0, EciesAeadHkdfDemHelper eciesAeadHkdfDemHelper0) throws GeneralSecurityException {
        this.recipientPrivateKey = eCPrivateKey0;
        this.recipientKem = new EciesHkdfRecipientKem(eCPrivateKey0);
        this.hkdfSalt = arr_b;
        this.hkdfHmacAlgo = s;
        this.ecPointFormat = ellipticCurves$PointFormatType0;
        this.demHelper = eciesAeadHkdfDemHelper0;
    }

    @Override  // com.google.crypto.tink.HybridDecrypt
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        int v = EllipticCurves.encodingSizeInBytes(this.recipientPrivateKey.getParams().getCurve(), this.ecPointFormat);
        if(arr_b.length < v) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] arr_b2 = Arrays.copyOfRange(arr_b, 0, v);
        int v1 = this.demHelper.getSymmetricKeySizeInBytes();
        byte[] arr_b3 = this.recipientKem.generateKey(arr_b2, this.hkdfHmacAlgo, this.hkdfSalt, arr_b1, v1, this.ecPointFormat);
        return this.demHelper.getAeadOrDaead(arr_b3).decrypt(Arrays.copyOfRange(arr_b, v, arr_b.length), EciesAeadHkdfHybridDecrypt.EMPTY_AAD);
    }
}

