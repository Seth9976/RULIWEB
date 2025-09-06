package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesCtrJceCipher implements IndCpaCipher {
    private static final String CIPHER_ALGORITHM = "AES/CTR/NoPadding";
    public static final AlgorithmFipsCompatibility FIPS = null;
    private static final String KEY_ALGORITHM = "AES";
    private static final int MIN_IV_SIZE_IN_BYTES = 12;
    private final int blockSize;
    private final int ivSize;
    private final SecretKeySpec keySpec;
    private static final ThreadLocal localCipher;

    static {
        AesCtrJceCipher.FIPS = AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
        AesCtrJceCipher.localCipher = new ThreadLocal() {
            @Override
            protected Object initialValue() {
                return this.initialValue();
            }

            protected Cipher initialValue() {
                try {
                    return (Cipher)EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
                }
                catch(GeneralSecurityException generalSecurityException0) {
                    throw new IllegalStateException(generalSecurityException0);
                }
            }
        };
    }

    public AesCtrJceCipher(byte[] arr_b, int v) throws GeneralSecurityException {
        if(!AesCtrJceCipher.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-CTR in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateAesKeySize(arr_b.length);
        this.keySpec = new SecretKeySpec(arr_b, "AES");
        int v1 = ((Cipher)AesCtrJceCipher.localCipher.get()).getBlockSize();
        this.blockSize = v1;
        if(v < 12 || v > v1) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.ivSize = v;
    }

    @Override  // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(byte[] arr_b) throws GeneralSecurityException {
        int v = this.ivSize;
        if(arr_b.length < v) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] arr_b1 = new byte[v];
        System.arraycopy(arr_b, 0, arr_b1, 0, v);
        byte[] arr_b2 = new byte[arr_b.length - this.ivSize];
        this.doCtr(arr_b, this.ivSize, arr_b.length - this.ivSize, arr_b2, 0, arr_b1, false);
        return arr_b2;
    }

    private void doCtr(byte[] arr_b, int v, int v1, byte[] arr_b1, int v2, byte[] arr_b2, boolean z) throws GeneralSecurityException {
        Cipher cipher0 = (Cipher)AesCtrJceCipher.localCipher.get();
        byte[] arr_b3 = new byte[this.blockSize];
        System.arraycopy(arr_b2, 0, arr_b3, 0, this.ivSize);
        IvParameterSpec ivParameterSpec0 = new IvParameterSpec(arr_b3);
        if(z) {
            cipher0.init(1, this.keySpec, ivParameterSpec0);
        }
        else {
            cipher0.init(2, this.keySpec, ivParameterSpec0);
        }
        if(cipher0.doFinal(arr_b, v, v1, arr_b1, v2) != v1) {
            throw new GeneralSecurityException("stored output\'s length does not match input\'s length");
        }
    }

    @Override  // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(byte[] arr_b) throws GeneralSecurityException {
        int v = this.ivSize;
        if(arr_b.length > 0x7FFFFFFF - v) {
            throw new GeneralSecurityException("plaintext length can not exceed " + (0x7FFFFFFF - this.ivSize));
        }
        byte[] arr_b1 = new byte[arr_b.length + v];
        byte[] arr_b2 = Random.randBytes(v);
        System.arraycopy(arr_b2, 0, arr_b1, 0, this.ivSize);
        this.doCtr(arr_b, 0, arr_b.length, arr_b1, this.ivSize, arr_b2, true);
        return arr_b1;
    }
}

