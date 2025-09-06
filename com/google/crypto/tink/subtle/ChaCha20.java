package com.google.crypto.tink.subtle;

import com.google.crypto.tink.aead.internal.InsecureNonceChaCha20;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;

class ChaCha20 implements IndCpaCipher {
    static final int NONCE_LENGTH_IN_BYTES = 12;
    private final InsecureNonceChaCha20 cipher;

    ChaCha20(byte[] arr_b, int v) throws InvalidKeyException {
        this.cipher = new InsecureNonceChaCha20(arr_b, v);
    }

    @Override  // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(byte[] arr_b) throws GeneralSecurityException {
        if(arr_b.length < 12) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] arr_b1 = Arrays.copyOf(arr_b, 12);
        ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b, 12, arr_b.length - 12);
        return this.cipher.decrypt(arr_b1, byteBuffer0);
    }

    @Override  // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(byte[] arr_b) throws GeneralSecurityException {
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(arr_b.length + 12);
        byte[] arr_b1 = {-39, -68, 18, -18, 38, -103, 109, 0x7C, 60, -106, 0x79, 68};
        byteBuffer0.put(arr_b1);
        this.cipher.encrypt(byteBuffer0, arr_b1, arr_b);
        return byteBuffer0.array();
    }
}

