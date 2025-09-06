package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.aead.subtle.AeadFactory;
import com.google.crypto.tink.subtle.Hkdf;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;

public final class RsaKemHybridDecrypt implements HybridDecrypt {
    private final AeadFactory aeadFactory;
    private final String hkdfHmacAlgo;
    private final byte[] hkdfSalt;
    private final RSAPrivateKey recipientPrivateKey;

    public RsaKemHybridDecrypt(RSAPrivateKey rSAPrivateKey0, String s, byte[] arr_b, AeadFactory aeadFactory0) throws GeneralSecurityException {
        RsaKem.validateRsaModulus(rSAPrivateKey0.getModulus());
        this.recipientPrivateKey = rSAPrivateKey0;
        this.hkdfSalt = arr_b;
        this.hkdfHmacAlgo = s;
        this.aeadFactory = aeadFactory0;
    }

    @Override  // com.google.crypto.tink.HybridDecrypt
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        int v = RsaKem.bigIntSizeInBytes(this.recipientPrivateKey.getModulus());
        if(arr_b.length < v) {
            throw new GeneralSecurityException(String.format("Ciphertext must be of at least size %d bytes, but got %d", v, ((int)arr_b.length)));
        }
        ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b);
        byte[] arr_b2 = new byte[v];
        byteBuffer0.get(arr_b2);
        Cipher cipher0 = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher0.init(2, this.recipientPrivateKey);
        byte[] arr_b3 = cipher0.doFinal(arr_b2);
        int v1 = this.aeadFactory.getKeySizeInBytes();
        byte[] arr_b4 = Hkdf.computeHkdf(this.hkdfHmacAlgo, arr_b3, this.hkdfSalt, arr_b1, v1);
        Aead aead0 = this.aeadFactory.createAead(arr_b4);
        byte[] arr_b5 = new byte[byteBuffer0.remaining()];
        byteBuffer0.get(arr_b5);
        return aead0.decrypt(arr_b5, RsaKem.EMPTY_AAD);
    }
}

