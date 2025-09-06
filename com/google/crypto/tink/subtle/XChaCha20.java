package com.google.crypto.tink.subtle;

import com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;

class XChaCha20 implements IndCpaCipher {
    static final int NONCE_LENGTH_IN_BYTES = 24;
    private final InsecureNonceXChaCha20 cipher;

    XChaCha20(byte[] arr_b, int v) throws InvalidKeyException {
        this.cipher = new InsecureNonceXChaCha20(arr_b, v);
    }

    @Override  // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(byte[] arr_b) throws GeneralSecurityException {
        if(arr_b.length < 24) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] arr_b1 = Arrays.copyOf(arr_b, 24);
        ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b, 24, arr_b.length - 24);
        return this.cipher.decrypt(arr_b1, byteBuffer0);
    }

    @Override  // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(byte[] arr_b) throws GeneralSecurityException {
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(arr_b.length + 24);
        byte[] arr_b1 = {27, 93, (byte)0xC0, -76, -39, -71, -66, -103, 77, (byte)0x80, 5, (byte)0xC0, 68, -41, -49, 24, 120, -30, 0x70, 0x6F, 12, -49, 83, -10};
        byteBuffer0.put(arr_b1);
        this.cipher.encrypt(byteBuffer0, arr_b1, arr_b);
        return byteBuffer0.array();
    }
}

