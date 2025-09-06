package com.google.crypto.tink.aead.internal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

public class InsecureNonceChaCha20 extends InsecureNonceChaCha20Base {
    public InsecureNonceChaCha20(byte[] arr_b, int v) throws InvalidKeyException {
        super(arr_b, v);
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public int[] createInitialState(int[] arr_v, int v) {
        if(arr_v.length != 3) {
            throw new IllegalArgumentException(String.format("ChaCha20 uses 96-bit nonces, but got a %d-bit nonce", ((int)(arr_v.length * 0x20))));
        }
        int[] arr_v1 = new int[16];
        ChaCha20Util.setSigmaAndKey(arr_v1, this.key);
        arr_v1[12] = v;
        System.arraycopy(arr_v, 0, arr_v1, 13, arr_v.length);
        return arr_v1;
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public byte[] decrypt(byte[] arr_b, ByteBuffer byteBuffer0) throws GeneralSecurityException {
        return super.decrypt(arr_b, byteBuffer0);
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        return super.decrypt(arr_b, arr_b1);
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public void encrypt(ByteBuffer byteBuffer0, byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        super.encrypt(byteBuffer0, arr_b, arr_b1);
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        return super.encrypt(arr_b, arr_b1);
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public int nonceSizeInBytes() [...] // Inlined contents
}

