package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class XChaCha20Poly1305 implements Aead {
    private final InsecureNonceXChaCha20Poly1305 cipher;

    public XChaCha20Poly1305(byte[] arr_b) throws GeneralSecurityException {
        this.cipher = new InsecureNonceXChaCha20Poly1305(arr_b);
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(arr_b.length < 40) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] arr_b2 = Arrays.copyOf(arr_b, 24);
        ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b, 24, arr_b.length - 24);
        return this.cipher.decrypt(byteBuffer0, arr_b2, arr_b1);
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(arr_b.length + 40);
        byte[] arr_b2 = {22, -37, 52, -21, 43, -105, -78, -23, -80, 35, -35, -101, -40, 0x76, -44, 105, -84, -28, -44, -46, 17, 14, 0x7F, (byte)0x80};
        byteBuffer0.put(arr_b2);
        this.cipher.encrypt(byteBuffer0, arr_b2, arr_b, arr_b1);
        return byteBuffer0.array();
    }
}

