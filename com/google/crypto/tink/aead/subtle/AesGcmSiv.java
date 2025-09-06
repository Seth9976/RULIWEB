package com.google.crypto.tink.aead.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.SubtleUtil;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesGcmSiv implements Aead {
    private static final int IV_SIZE_IN_BYTES = 12;
    private static final int TAG_SIZE_IN_BYTES = 16;
    private final SecretKey keySpec;
    private static final ThreadLocal localCipher;

    static {
        AesGcmSiv.localCipher = new ThreadLocal() {
            @Override
            protected Object initialValue() {
                return this.initialValue();
            }

            protected Cipher initialValue() {
                try {
                    return (Cipher)EngineFactory.CIPHER.getInstance("AES/GCM-SIV/NoPadding");
                }
                catch(GeneralSecurityException generalSecurityException0) {
                    throw new IllegalStateException(generalSecurityException0);
                }
            }
        };
    }

    public AesGcmSiv(byte[] arr_b) throws GeneralSecurityException {
        Validators.validateAesKeySize(arr_b.length);
        this.keySpec = new SecretKeySpec(arr_b, "AES");
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(arr_b.length < 28) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        AlgorithmParameterSpec algorithmParameterSpec0 = AesGcmSiv.getParams(arr_b, 0, 12);
        ThreadLocal threadLocal0 = AesGcmSiv.localCipher;
        ((Cipher)threadLocal0.get()).init(2, this.keySpec, algorithmParameterSpec0);
        if(arr_b1 != null && arr_b1.length != 0) {
            ((Cipher)threadLocal0.get()).updateAAD(arr_b1);
        }
        return ((Cipher)threadLocal0.get()).doFinal(arr_b, 12, arr_b.length - 12);
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(arr_b.length > 0x7FFFFFE3) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] arr_b2 = new byte[arr_b.length + 28];
        byte[] arr_b3 = {-97, 69, -87, -49, -83, (byte)0xB1, 105, -103, -25, 22, 6, 91};
        System.arraycopy(arr_b3, 0, arr_b2, 0, 12);
        AlgorithmParameterSpec algorithmParameterSpec0 = AesGcmSiv.getParams(arr_b3);
        ThreadLocal threadLocal0 = AesGcmSiv.localCipher;
        ((Cipher)threadLocal0.get()).init(1, this.keySpec, algorithmParameterSpec0);
        if(arr_b1 != null && arr_b1.length != 0) {
            ((Cipher)threadLocal0.get()).updateAAD(arr_b1);
        }
        int v = ((Cipher)threadLocal0.get()).doFinal(arr_b, 0, arr_b.length, arr_b2, 12);
        if(v != arr_b.length + 16) {
            throw new GeneralSecurityException(String.format("encryption failed; GCM tag must be %s bytes, but got only %s bytes", 16, ((int)(v - arr_b.length))));
        }
        return arr_b2;
    }

    private static AlgorithmParameterSpec getParams(byte[] arr_b) throws GeneralSecurityException {
        return AesGcmSiv.getParams(arr_b, 0, arr_b.length);
    }

    private static AlgorithmParameterSpec getParams(byte[] arr_b, int v, int v1) throws GeneralSecurityException {
        try {
            Class.forName("javax.crypto.spec.GCMParameterSpec");
            return new GCMParameterSpec(0x80, arr_b, v, v1);
        }
        catch(ClassNotFoundException unused_ex) {
            if(!SubtleUtil.isAndroid()) {
                throw new GeneralSecurityException("cannot use AES-GCM: javax.crypto.spec.GCMParameterSpec not found");
            }
            return new IvParameterSpec(arr_b, v, v1);
        }
    }
}

