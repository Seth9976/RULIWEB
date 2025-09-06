package com.google.crypto.tink.subtle;

import com.google.crypto.tink.HybridEncrypt;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

public final class EciesAeadHkdfHybridEncrypt implements HybridEncrypt {
    private static final byte[] EMPTY_AAD;
    private final EciesAeadHkdfDemHelper demHelper;
    private final PointFormatType ecPointFormat;
    private final String hkdfHmacAlgo;
    private final byte[] hkdfSalt;
    private final EciesHkdfSenderKem senderKem;

    static {
        EciesAeadHkdfHybridEncrypt.EMPTY_AAD = new byte[0];
    }

    public EciesAeadHkdfHybridEncrypt(ECPublicKey eCPublicKey0, byte[] arr_b, String s, PointFormatType ellipticCurves$PointFormatType0, EciesAeadHkdfDemHelper eciesAeadHkdfDemHelper0) throws GeneralSecurityException {
        EllipticCurves.checkPublicKey(eCPublicKey0);
        this.senderKem = new EciesHkdfSenderKem(eCPublicKey0);
        this.hkdfSalt = arr_b;
        this.hkdfHmacAlgo = s;
        this.ecPointFormat = ellipticCurves$PointFormatType0;
        this.demHelper = eciesAeadHkdfDemHelper0;
    }

    @Override  // com.google.crypto.tink.HybridEncrypt
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        int v = this.demHelper.getSymmetricKeySizeInBytes();
        KemKey eciesHkdfSenderKem$KemKey0 = this.senderKem.generateKey(this.hkdfHmacAlgo, this.hkdfSalt, arr_b1, v, this.ecPointFormat);
        byte[] arr_b2 = eciesHkdfSenderKem$KemKey0.getSymmetricKey();
        byte[] arr_b3 = this.demHelper.getAeadOrDaead(arr_b2).encrypt(arr_b, EciesAeadHkdfHybridEncrypt.EMPTY_AAD);
        byte[] arr_b4 = eciesHkdfSenderKem$KemKey0.getKemBytes();
        return ByteBuffer.allocate(arr_b4.length + arr_b3.length).put(arr_b4).put(arr_b3).array();
    }
}

