package com.google.crypto.tink.aead.internal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

public final class InsecureNonceChaCha20Poly1305 extends InsecureNonceChaCha20Poly1305Base {
    public InsecureNonceChaCha20Poly1305(byte[] arr_b) throws GeneralSecurityException {
        super(arr_b);
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Poly1305Base
    public byte[] decrypt(ByteBuffer byteBuffer0, byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        return super.decrypt(byteBuffer0, arr_b, arr_b1);
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Poly1305Base
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        return super.decrypt(arr_b, arr_b1, arr_b2);
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Poly1305Base
    public void encrypt(ByteBuffer byteBuffer0, byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        super.encrypt(byteBuffer0, arr_b, arr_b1, arr_b2);
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Poly1305Base
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        return super.encrypt(arr_b, arr_b1, arr_b2);
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Poly1305Base
    InsecureNonceChaCha20Base newChaCha20Instance(byte[] arr_b, int v) throws InvalidKeyException {
        return new InsecureNonceChaCha20(arr_b, v);
    }
}

