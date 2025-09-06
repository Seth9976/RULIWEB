package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.SubtleUtil;
import com.google.crypto.tink.subtle.Validators;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class InsecureNonceAesGcmJce {
    public static final AlgorithmFipsCompatibility FIPS = null;
    public static final int IV_SIZE_IN_BYTES = 12;
    public static final int TAG_SIZE_IN_BYTES = 16;
    private final SecretKey keySpec;
    private static final ThreadLocal localCipher;
    private final boolean prependIv;

    static {
        InsecureNonceAesGcmJce.FIPS = AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
        InsecureNonceAesGcmJce.localCipher = new ThreadLocal() {
            @Override
            protected Object initialValue() {
                return this.initialValue();
            }

            protected Cipher initialValue() {
                try {
                    return (Cipher)EngineFactory.CIPHER.getInstance("AES/GCM/NoPadding");
                }
                catch(GeneralSecurityException generalSecurityException0) {
                    throw new IllegalStateException(generalSecurityException0);
                }
            }
        };
    }

    public InsecureNonceAesGcmJce(byte[] arr_b, boolean z) throws GeneralSecurityException {
        if(!InsecureNonceAesGcmJce.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateAesKeySize(arr_b.length);
        this.keySpec = new SecretKeySpec(arr_b, "AES");
        this.prependIv = z;
    }

    public byte[] decrypt(byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        if(arr_b.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        }
        boolean z = this.prependIv;
        int v = 0;
        if(arr_b1.length < (z ? 28 : 16)) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        if(z && !ByteBuffer.wrap(arr_b).equals(ByteBuffer.wrap(arr_b1, 0, 12))) {
            throw new GeneralSecurityException("iv does not match prepended iv");
        }
        AlgorithmParameterSpec algorithmParameterSpec0 = InsecureNonceAesGcmJce.getParams(arr_b);
        ThreadLocal threadLocal0 = InsecureNonceAesGcmJce.localCipher;
        ((Cipher)threadLocal0.get()).init(2, this.keySpec, algorithmParameterSpec0);
        if(arr_b2 != null && arr_b2.length != 0) {
            ((Cipher)threadLocal0.get()).updateAAD(arr_b2);
        }
        boolean z1 = this.prependIv;
        if(z1) {
            v = 12;
        }
        return z1 ? ((Cipher)threadLocal0.get()).doFinal(arr_b1, v, arr_b1.length - 12) : ((Cipher)threadLocal0.get()).doFinal(arr_b1, v, arr_b1.length);
    }

    public byte[] encrypt(byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        if(arr_b.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        }
        if(arr_b1.length > 0x7FFFFFE3) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] arr_b3 = new byte[(this.prependIv ? arr_b1.length + 28 : arr_b1.length + 16)];
        if(this.prependIv) {
            System.arraycopy(arr_b, 0, arr_b3, 0, 12);
        }
        AlgorithmParameterSpec algorithmParameterSpec0 = InsecureNonceAesGcmJce.getParams(arr_b);
        ThreadLocal threadLocal0 = InsecureNonceAesGcmJce.localCipher;
        ((Cipher)threadLocal0.get()).init(1, this.keySpec, algorithmParameterSpec0);
        if(arr_b2 != null && arr_b2.length != 0) {
            ((Cipher)threadLocal0.get()).updateAAD(arr_b2);
        }
        int v = ((Cipher)threadLocal0.get()).doFinal(arr_b1, 0, arr_b1.length, arr_b3, (this.prependIv ? 12 : 0));
        if(v != arr_b1.length + 16) {
            throw new GeneralSecurityException(String.format("encryption failed; GCM tag must be %s bytes, but got only %s bytes", 16, ((int)(v - arr_b1.length))));
        }
        return arr_b3;
    }

    private static AlgorithmParameterSpec getParams(byte[] arr_b) throws GeneralSecurityException {
        return InsecureNonceAesGcmJce.getParams(arr_b, 0, arr_b.length);
    }

    private static AlgorithmParameterSpec getParams(byte[] arr_b, int v, int v1) throws GeneralSecurityException {
        return SubtleUtil.isAndroid() && SubtleUtil.androidApiLevel() <= 19 ? new IvParameterSpec(arr_b, v, v1) : new GCMParameterSpec(0x80, arr_b, v, v1);
    }
}

