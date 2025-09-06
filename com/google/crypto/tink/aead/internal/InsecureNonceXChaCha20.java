package com.google.crypto.tink.aead.internal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;

public class InsecureNonceXChaCha20 extends InsecureNonceChaCha20Base {
    public static final int NONCE_SIZE_IN_BYTES = 24;

    public InsecureNonceXChaCha20(byte[] arr_b, int v) throws InvalidKeyException {
        super(arr_b, v);
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    int[] createInitialState(int[] arr_v, int v) {
        if(arr_v.length != 6) {
            throw new IllegalArgumentException(String.format("XChaCha20 uses 192-bit nonces, but got a %d-bit nonce", ((int)(arr_v.length * 0x20))));
        }
        int[] arr_v1 = new int[16];
        ChaCha20Util.setSigmaAndKey(arr_v1, InsecureNonceXChaCha20.hChaCha20(this.key, arr_v));
        arr_v1[12] = v;
        arr_v1[13] = 0;
        arr_v1[14] = arr_v[4];
        arr_v1[15] = arr_v[5];
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

    static int[] hChaCha20(int[] arr_v, int[] arr_v1) {
        int[] arr_v2 = new int[16];
        ChaCha20Util.setSigmaAndKey(arr_v2, arr_v);
        arr_v2[12] = arr_v1[0];
        arr_v2[13] = arr_v1[1];
        arr_v2[14] = arr_v1[2];
        arr_v2[15] = arr_v1[3];
        ChaCha20Util.shuffleState(arr_v2);
        arr_v2[4] = arr_v2[12];
        arr_v2[5] = arr_v2[13];
        arr_v2[6] = arr_v2[14];
        arr_v2[7] = arr_v2[15];
        return Arrays.copyOf(arr_v2, 8);
    }

    @Override  // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    int nonceSizeInBytes() [...] // Inlined contents
}

