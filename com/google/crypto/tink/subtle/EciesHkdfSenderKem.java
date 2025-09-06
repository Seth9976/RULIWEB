package com.google.crypto.tink.subtle;

import com.google.crypto.tink.util.Bytes;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public final class EciesHkdfSenderKem {
    public static final class KemKey {
        private final Bytes kemBytes;
        private final Bytes symmetricKey;

        public KemKey(byte[] arr_b, byte[] arr_b1) {
            if(arr_b == null) {
                throw new NullPointerException("KemBytes must be non-null");
            }
            if(arr_b1 == null) {
                throw new NullPointerException("symmetricKey must be non-null");
            }
            this.kemBytes = Bytes.copyFrom(arr_b);
            this.symmetricKey = Bytes.copyFrom(arr_b1);
        }

        public byte[] getKemBytes() {
            return this.kemBytes.toByteArray();
        }

        public byte[] getSymmetricKey() {
            return this.symmetricKey.toByteArray();
        }
    }

    private final ECPublicKey recipientPublicKey;

    public EciesHkdfSenderKem(ECPublicKey eCPublicKey0) {
        this.recipientPublicKey = eCPublicKey0;
    }

    public KemKey generateKey(String s, byte[] arr_b, byte[] arr_b1, int v, PointFormatType ellipticCurves$PointFormatType0) throws GeneralSecurityException {
        KeyPair keyPair0 = EllipticCurves.generateKeyPair(this.recipientPublicKey.getParams());
        ECPublicKey eCPublicKey0 = (ECPublicKey)keyPair0.getPublic();
        byte[] arr_b2 = EllipticCurves.computeSharedSecret(((ECPrivateKey)keyPair0.getPrivate()), this.recipientPublicKey);
        byte[] arr_b3 = EllipticCurves.pointEncode(eCPublicKey0.getParams().getCurve(), ellipticCurves$PointFormatType0, eCPublicKey0.getW());
        return new KemKey(arr_b3, Hkdf.computeEciesHkdfSymmetricKey(arr_b3, arr_b2, s, arr_b, arr_b1, v));
    }
}

