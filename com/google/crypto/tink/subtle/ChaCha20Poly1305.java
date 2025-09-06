package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Poly1305;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class ChaCha20Poly1305 implements Aead {
    private final InsecureNonceChaCha20Poly1305 cipher;

    public ChaCha20Poly1305(byte[] arr_b) throws GeneralSecurityException {
        this.cipher = new InsecureNonceChaCha20Poly1305(arr_b);
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(arr_b.length < 28) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] arr_b2 = Arrays.copyOf(arr_b, 12);
        ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b, 12, arr_b.length - 12);
        return this.cipher.decrypt(byteBuffer0, arr_b2, arr_b1);
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(arr_b.length + 28);
        byte[] arr_b2 = {62, -29, (byte)0x87, 72, -15, -30, 44, -41, 84, 101, -24, (byte)0x89};
        byteBuffer0.put(arr_b2);
        this.cipher.encrypt(byteBuffer0, arr_b2, arr_b, arr_b1);
        return byteBuffer0.array();
    }
}

