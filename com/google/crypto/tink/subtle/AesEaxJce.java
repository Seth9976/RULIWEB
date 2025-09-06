package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesEaxJce implements Aead {
    static final boolean $assertionsDisabled = false;
    static final int BLOCK_SIZE_IN_BYTES = 16;
    public static final AlgorithmFipsCompatibility FIPS = null;
    static final int TAG_SIZE_IN_BYTES = 16;
    private final byte[] b;
    private final int ivSizeInBytes;
    private final SecretKeySpec keySpec;
    private static final ThreadLocal localCtrCipher;
    private static final ThreadLocal localEcbCipher;
    private final byte[] p;

    static {
        AesEaxJce.FIPS = AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
        AesEaxJce.localEcbCipher = new ThreadLocal() {
            @Override
            protected Object initialValue() {
                return this.initialValue();
            }

            protected Cipher initialValue() {
                try {
                    return (Cipher)EngineFactory.CIPHER.getInstance("AES/ECB/NOPADDING");
                }
                catch(GeneralSecurityException generalSecurityException0) {
                    throw new IllegalStateException(generalSecurityException0);
                }
            }
        };
        AesEaxJce.localCtrCipher = new ThreadLocal() {
            @Override
            protected Object initialValue() {
                return this.initialValue();
            }

            protected Cipher initialValue() {
                try {
                    return (Cipher)EngineFactory.CIPHER.getInstance("AES/CTR/NOPADDING");
                }
                catch(GeneralSecurityException generalSecurityException0) {
                    throw new IllegalStateException(generalSecurityException0);
                }
            }
        };
    }

    public AesEaxJce(byte[] arr_b, int v) throws GeneralSecurityException {
        if(!AesEaxJce.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
        }
        if(v != 12 && v != 16) {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
        this.ivSizeInBytes = v;
        Validators.validateAesKeySize(arr_b.length);
        SecretKeySpec secretKeySpec0 = new SecretKeySpec(arr_b, "AES");
        this.keySpec = secretKeySpec0;
        Cipher cipher0 = (Cipher)AesEaxJce.localEcbCipher.get();
        cipher0.init(1, secretKeySpec0);
        byte[] arr_b1 = AesEaxJce.multiplyByX(cipher0.doFinal(new byte[16]));
        this.b = arr_b1;
        this.p = AesEaxJce.multiplyByX(arr_b1);
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        int v = arr_b.length - this.ivSizeInBytes - 16;
        if(v < 0) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        Cipher cipher0 = (Cipher)AesEaxJce.localEcbCipher.get();
        cipher0.init(1, this.keySpec);
        byte[] arr_b2 = this.omac(cipher0, 0, arr_b, 0, this.ivSizeInBytes);
        byte[] arr_b3 = arr_b1 == null ? new byte[0] : arr_b1;
        byte[] arr_b4 = this.omac(cipher0, 1, arr_b3, 0, arr_b3.length);
        byte[] arr_b5 = this.omac(cipher0, 2, arr_b, this.ivSizeInBytes, v);
        int v1 = arr_b.length - 16;
        int v2 = 0;
        for(int v3 = 0; v3 < 16; ++v3) {
            v2 = (byte)(v2 | arr_b[v1 + v3] ^ arr_b4[v3] ^ arr_b2[v3] ^ arr_b5[v3]);
        }
        if(v2 != 0) {
            throw new AEADBadTagException("tag mismatch");
        }
        Cipher cipher1 = (Cipher)AesEaxJce.localCtrCipher.get();
        IvParameterSpec ivParameterSpec0 = new IvParameterSpec(arr_b2);
        cipher1.init(1, this.keySpec, ivParameterSpec0);
        return cipher1.doFinal(arr_b, this.ivSizeInBytes, v);
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        int v = this.ivSizeInBytes;
        if(arr_b.length > 0x7FFFFFEF - v) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] arr_b2 = new byte[arr_b.length + v + 16];
        byte[] arr_b3 = Random.randBytes(v);
        System.arraycopy(arr_b3, 0, arr_b2, 0, this.ivSizeInBytes);
        Cipher cipher0 = (Cipher)AesEaxJce.localEcbCipher.get();
        cipher0.init(1, this.keySpec);
        byte[] arr_b4 = this.omac(cipher0, 0, arr_b3, 0, arr_b3.length);
        byte[] arr_b5 = arr_b1 == null ? new byte[0] : arr_b1;
        byte[] arr_b6 = this.omac(cipher0, 1, arr_b5, 0, arr_b5.length);
        Cipher cipher1 = (Cipher)AesEaxJce.localCtrCipher.get();
        IvParameterSpec ivParameterSpec0 = new IvParameterSpec(arr_b4);
        cipher1.init(1, this.keySpec, ivParameterSpec0);
        cipher1.doFinal(arr_b, 0, arr_b.length, arr_b2, this.ivSizeInBytes);
        byte[] arr_b7 = this.omac(cipher0, 2, arr_b2, this.ivSizeInBytes, arr_b.length);
        int v1 = arr_b.length + this.ivSizeInBytes;
        for(int v2 = 0; v2 < 16; ++v2) {
            arr_b2[v1 + v2] = (byte)(arr_b6[v2] ^ arr_b4[v2] ^ arr_b7[v2]);
        }
        return arr_b2;
    }

    private static byte[] multiplyByX(byte[] arr_b) {
        byte[] arr_b1 = new byte[16];
        for(int v = 0; v < 15; ++v) {
            arr_b1[v] = (byte)((arr_b[v] << 1 ^ (arr_b[v + 1] & 0xFF) >>> 7) & 0xFF);
        }
        arr_b1[15] = (byte)(arr_b[0] >> 7 & 0x87 ^ arr_b[15] << 1);
        return arr_b1;
    }

    private byte[] omac(Cipher cipher0, int v, byte[] arr_b, int v1, int v2) throws IllegalBlockSizeException, BadPaddingException {
        byte[] arr_b1 = new byte[16];
        arr_b1[15] = (byte)v;
        if(v2 == 0) {
            return cipher0.doFinal(AesEaxJce.xor(arr_b1, this.b));
        }
        byte[] arr_b2 = cipher0.doFinal(arr_b1);
        int v3;
        for(v3 = 0; v2 - v3 > 16; v3 += 16) {
            for(int v4 = 0; v4 < 16; ++v4) {
                arr_b2[v4] = (byte)(arr_b2[v4] ^ arr_b[v1 + v3 + v4]);
            }
            arr_b2 = cipher0.doFinal(arr_b2);
        }
        return cipher0.doFinal(AesEaxJce.xor(arr_b2, this.pad(Arrays.copyOfRange(arr_b, v3 + v1, v1 + v2))));
    }

    private byte[] pad(byte[] arr_b) {
        if(arr_b.length == 16) {
            return AesEaxJce.xor(arr_b, this.b);
        }
        byte[] arr_b1 = Arrays.copyOf(this.p, 16);
        for(int v = 0; v < arr_b.length; ++v) {
            arr_b1[v] = (byte)(arr_b1[v] ^ arr_b[v]);
        }
        arr_b1[arr_b.length] = (byte)(arr_b1[arr_b.length] ^ 0x80);
        return arr_b1;
    }

    private static byte[] xor(byte[] arr_b, byte[] arr_b1) {
        byte[] arr_b2 = new byte[arr_b.length];
        for(int v = 0; v < arr_b.length; ++v) {
            arr_b2[v] = (byte)(arr_b[v] ^ arr_b1[v]);
        }
        return arr_b2;
    }
}

