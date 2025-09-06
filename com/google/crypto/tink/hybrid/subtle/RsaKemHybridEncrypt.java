package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.aead.subtle.AeadFactory;
import com.google.crypto.tink.subtle.Hkdf;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;

public final class RsaKemHybridEncrypt implements HybridEncrypt {
    private final AeadFactory aeadFactory;
    private final String hkdfHmacAlgo;
    private final byte[] hkdfSalt;
    private final RSAPublicKey recipientPublicKey;

    public RsaKemHybridEncrypt(RSAPublicKey rSAPublicKey0, String s, byte[] arr_b, AeadFactory aeadFactory0) throws GeneralSecurityException {
        RsaKem.validateRsaModulus(rSAPublicKey0.getModulus());
        this.recipientPublicKey = rSAPublicKey0;
        this.hkdfHmacAlgo = s;
        this.hkdfSalt = arr_b;
        this.aeadFactory = aeadFactory0;
    }

    @Override  // com.google.crypto.tink.HybridEncrypt
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        byte[] arr_b2 = RsaKem.generateSecret(this.recipientPublicKey.getModulus());
        Cipher cipher0 = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher0.init(1, this.recipientPublicKey);
        byte[] arr_b3 = cipher0.doFinal(arr_b2);
        int v = this.aeadFactory.getKeySizeInBytes();
        byte[] arr_b4 = Hkdf.computeHkdf(this.hkdfHmacAlgo, arr_b2, this.hkdfSalt, arr_b1, v);
        byte[] arr_b5 = this.aeadFactory.createAead(arr_b4).encrypt(arr_b, RsaKem.EMPTY_AAD);
        return ByteBuffer.allocate(arr_b3.length + arr_b5.length).put(arr_b3).put(arr_b5).array();
    }
}

