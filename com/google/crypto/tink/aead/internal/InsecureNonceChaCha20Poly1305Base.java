package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import javax.crypto.AEADBadTagException;

abstract class InsecureNonceChaCha20Poly1305Base {
    public static final AlgorithmFipsCompatibility FIPS;
    private final InsecureNonceChaCha20Base chacha20;
    private final InsecureNonceChaCha20Base macKeyChaCha20;

    static {
        InsecureNonceChaCha20Poly1305Base.FIPS = AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    }

    public InsecureNonceChaCha20Poly1305Base(byte[] arr_b) throws GeneralSecurityException {
        if(!InsecureNonceChaCha20Poly1305Base.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
        }
        this.chacha20 = this.newChaCha20Instance(arr_b, 1);
        this.macKeyChaCha20 = this.newChaCha20Instance(arr_b, 0);
    }

    public byte[] decrypt(ByteBuffer byteBuffer0, byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(byteBuffer0.remaining() < 16) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        int v = byteBuffer0.position();
        byte[] arr_b2 = new byte[16];
        byteBuffer0.position(byteBuffer0.limit() - 16);
        byteBuffer0.get(arr_b2);
        byteBuffer0.position(v);
        byteBuffer0.limit(byteBuffer0.limit() - 16);
        if(arr_b1 == null) {
            arr_b1 = new byte[0];
        }
        try {
            Poly1305.verifyMac(this.getMacKey(arr_b), InsecureNonceChaCha20Poly1305Base.macDataRfc8439(arr_b1, byteBuffer0), arr_b2);
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new AEADBadTagException(generalSecurityException0.toString());
        }
        byteBuffer0.position(v);
        return this.chacha20.decrypt(arr_b, byteBuffer0);
    }

    public byte[] decrypt(byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        return this.decrypt(ByteBuffer.wrap(arr_b1), arr_b, arr_b2);
    }

    public void encrypt(ByteBuffer byteBuffer0, byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        if(byteBuffer0.remaining() < arr_b1.length + 16) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        this.chacha20.encrypt(byteBuffer0, arr_b, arr_b1);
        byteBuffer0.position(byteBuffer0.position());
        byteBuffer0.limit(byteBuffer0.limit() - 16);
        if(arr_b2 == null) {
            arr_b2 = new byte[0];
        }
        byte[] arr_b3 = Poly1305.computeMac(this.getMacKey(arr_b), InsecureNonceChaCha20Poly1305Base.macDataRfc8439(arr_b2, byteBuffer0));
        byteBuffer0.limit(byteBuffer0.limit() + 16);
        byteBuffer0.put(arr_b3);
    }

    public byte[] encrypt(byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        if(arr_b1.length > 0x7FFFFFEF) {
            throw new GeneralSecurityException("plaintext too long");
        }
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(arr_b1.length + 16);
        this.encrypt(byteBuffer0, arr_b, arr_b1, arr_b2);
        return byteBuffer0.array();
    }

    private byte[] getMacKey(byte[] arr_b) throws GeneralSecurityException {
        ByteBuffer byteBuffer0 = this.macKeyChaCha20.chacha20Block(arr_b, 0);
        byte[] arr_b1 = new byte[0x20];
        byteBuffer0.get(arr_b1);
        return arr_b1;
    }

    private static byte[] macDataRfc8439(byte[] arr_b, ByteBuffer byteBuffer0) {
        int v = arr_b.length % 16 == 0 ? arr_b.length : arr_b.length + 16 - arr_b.length % 16;
        int v1 = byteBuffer0.remaining();
        int v2 = (v1 % 16 == 0 ? v1 : v1 + 16 - v1 % 16) + v;
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(v2 + 16).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer1.put(arr_b);
        byteBuffer1.position(v);
        byteBuffer1.put(byteBuffer0);
        byteBuffer1.position(v2);
        byteBuffer1.putLong(((long)arr_b.length));
        byteBuffer1.putLong(((long)v1));
        return byteBuffer1.array();
    }

    abstract InsecureNonceChaCha20Base newChaCha20Instance(byte[] arg1, int arg2) throws InvalidKeyException;
}

