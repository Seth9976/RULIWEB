package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.subtle.Bytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

abstract class InsecureNonceChaCha20Base {
    private final int initialCounter;
    int[] key;

    public InsecureNonceChaCha20Base(byte[] arr_b, int v) throws InvalidKeyException {
        if(arr_b.length != 0x20) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.key = ChaCha20Util.toIntArray(arr_b);
        this.initialCounter = v;
    }

    ByteBuffer chacha20Block(byte[] arr_b, int v) {
        int[] arr_v = this.createInitialState(ChaCha20Util.toIntArray(arr_b), v);
        int[] arr_v1 = (int[])arr_v.clone();
        ChaCha20Util.shuffleState(arr_v1);
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            arr_v[v1] += arr_v1[v1];
        }
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(0x40).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer0.asIntBuffer().put(arr_v, 0, 16);
        return byteBuffer0;
    }

    abstract int[] createInitialState(int[] arg1, int arg2);

    public byte[] decrypt(byte[] arr_b, ByteBuffer byteBuffer0) throws GeneralSecurityException {
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(byteBuffer0.remaining());
        this.process(arr_b, byteBuffer1, byteBuffer0);
        return byteBuffer1.array();
    }

    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        return this.decrypt(arr_b, ByteBuffer.wrap(arr_b1));
    }

    public void encrypt(ByteBuffer byteBuffer0, byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(byteBuffer0.remaining() < arr_b1.length) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        this.process(arr_b, byteBuffer0, ByteBuffer.wrap(arr_b1));
    }

    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(arr_b1.length);
        this.encrypt(byteBuffer0, arr_b, arr_b1);
        return byteBuffer0.array();
    }

    abstract int nonceSizeInBytes();

    private void process(byte[] arr_b, ByteBuffer byteBuffer0, ByteBuffer byteBuffer1) throws GeneralSecurityException {
        int v = this.nonceSizeInBytes();
        if(arr_b.length != v) {
            throw new GeneralSecurityException("The nonce length (in bytes) must be " + this.nonceSizeInBytes());
        }
        int v1 = byteBuffer1.remaining();
        for(int v2 = 0; v2 < v1 / 0x40 + 1; ++v2) {
            ByteBuffer byteBuffer2 = this.chacha20Block(arr_b, this.initialCounter + v2);
            if(v2 == v1 / 0x40) {
                Bytes.xor(byteBuffer0, byteBuffer1, byteBuffer2, v1 % 0x40);
            }
            else {
                Bytes.xor(byteBuffer0, byteBuffer1, byteBuffer2, 0x40);
            }
        }
    }
}

