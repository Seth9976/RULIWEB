package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public final class EciesHkdfRecipientKem {
    private ECPrivateKey recipientPrivateKey;

    public EciesHkdfRecipientKem(ECPrivateKey eCPrivateKey0) {
        this.recipientPrivateKey = eCPrivateKey0;
    }

    public byte[] generateKey(byte[] arr_b, String s, byte[] arr_b1, byte[] arr_b2, int v, PointFormatType ellipticCurves$PointFormatType0) throws GeneralSecurityException {
        ECPublicKey eCPublicKey0 = EllipticCurves.getEcPublicKey(this.recipientPrivateKey.getParams(), ellipticCurves$PointFormatType0, arr_b);
        return Hkdf.computeEciesHkdfSymmetricKey(arr_b, EllipticCurves.computeSharedSecret(this.recipientPrivateKey, eCPublicKey0), s, arr_b1, arr_b2, v);
    }
}

