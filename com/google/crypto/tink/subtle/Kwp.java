package com.google.crypto.tink.subtle;

import com.google.crypto.tink.KeyWrap;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Kwp implements KeyWrap {
    static final boolean $assertionsDisabled = false;
    static final int MAX_WRAP_KEY_SIZE = 0x1000;
    static final int MIN_WRAP_KEY_SIZE = 16;
    static final byte[] PREFIX = null;
    static final int ROUNDS = 6;
    private final SecretKey aesKey;

    static {
        Kwp.PREFIX = new byte[]{-90, 89, 89, -90};
    }

    public Kwp(byte[] arr_b) throws GeneralSecurityException {
        if(arr_b.length != 16 && arr_b.length != 0x20) {
            throw new GeneralSecurityException("Unsupported key length");
        }
        this.aesKey = new SecretKeySpec(arr_b, "AES");
    }

    private byte[] computeW(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(arr_b1.length <= 8 || arr_b1.length > 0x7FFFFFEF || arr_b.length != 8) {
            throw new GeneralSecurityException("computeW called with invalid parameters");
        }
        int v = arr_b1.length - (arr_b1.length + 7) % 8 + 15;
        byte[] arr_b2 = new byte[v];
        System.arraycopy(arr_b, 0, arr_b2, 0, arr_b.length);
        System.arraycopy(arr_b1, 0, arr_b2, 8, arr_b1.length);
        int v1 = v / 8 - 1;
        Cipher cipher0 = (Cipher)EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
        cipher0.init(1, this.aesKey);
        byte[] arr_b3 = new byte[16];
        System.arraycopy(arr_b2, 0, arr_b3, 0, 8);
        for(int v2 = 0; v2 < 6; ++v2) {
            for(int v3 = 0; v3 < v1; ++v3) {
                int v4 = (v3 + 1) * 8;
                System.arraycopy(arr_b2, v4, arr_b3, 8, 8);
                cipher0.doFinal(arr_b3, 0, 16, arr_b3);
                int v5 = v2 * v1 + v3 + 1;
                for(int v6 = 0; v6 < 4; ++v6) {
                    arr_b3[7 - v6] = (byte)(((byte)(v5 & 0xFF)) ^ arr_b3[7 - v6]);
                    v5 >>>= 8;
                }
                System.arraycopy(arr_b3, 8, arr_b2, v4, 8);
            }
        }
        System.arraycopy(arr_b3, 0, arr_b2, 0, 8);
        return arr_b2;
    }

    private byte[] invertW(byte[] arr_b) throws GeneralSecurityException {
        if(arr_b.length < 24 || arr_b.length % 8 != 0) {
            throw new GeneralSecurityException("Incorrect data size");
        }
        byte[] arr_b1 = Arrays.copyOf(arr_b, arr_b.length);
        int v = arr_b1.length / 8;
        Cipher cipher0 = (Cipher)EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
        cipher0.init(2, this.aesKey);
        byte[] arr_b2 = new byte[16];
        System.arraycopy(arr_b1, 0, arr_b2, 0, 8);
        for(int v1 = 5; v1 >= 0; --v1) {
            for(int v2 = v - 2; v2 >= 0; --v2) {
                int v3 = (v2 + 1) * 8;
                System.arraycopy(arr_b1, v3, arr_b2, 8, 8);
                int v4 = v1 * (v - 1) + v2 + 1;
                for(int v5 = 0; v5 < 4; ++v5) {
                    arr_b2[7 - v5] = (byte)(arr_b2[7 - v5] ^ ((byte)(v4 & 0xFF)));
                    v4 >>>= 8;
                }
                cipher0.doFinal(arr_b2, 0, 16, arr_b2);
                System.arraycopy(arr_b2, 8, arr_b1, v3, 8);
            }
        }
        System.arraycopy(arr_b2, 0, arr_b1, 0, 8);
        return arr_b1;
    }

    @Override  // com.google.crypto.tink.KeyWrap
    public byte[] unwrap(byte[] arr_b) throws GeneralSecurityException {
        if(arr_b.length < 24) {
            throw new GeneralSecurityException("Wrapped key size is too small");
        }
        if(arr_b.length > 0x1008) {
            throw new GeneralSecurityException("Wrapped key size is too large");
        }
        if(arr_b.length % 8 != 0) {
            throw new GeneralSecurityException("Wrapped key size must be a multiple of 8 bytes");
        }
        byte[] arr_b1 = this.invertW(arr_b);
        int v = 1;
        int v1 = 0;
        for(int v2 = 0; v2 < 4; ++v2) {
            if(Kwp.PREFIX[v2] != arr_b1[v2]) {
                v = 0;
            }
        }
        int v4 = 0;
        for(int v3 = 4; v3 < 8; ++v3) {
            v4 = (v4 << 8) + (arr_b1[v3] & 0xFF);
        }
        if(v4 - (v4 + 7) % 8 + 15 == arr_b1.length) {
            for(int v5 = v4 + 8; v5 < arr_b1.length; ++v5) {
                if(arr_b1[v5] != 0) {
                    v = 0;
                }
            }
            v1 = v;
        }
        if(v1 == 0) {
            throw new BadPaddingException("Invalid padding");
        }
        return Arrays.copyOfRange(arr_b1, 8, v4 + 8);
    }

    @Override  // com.google.crypto.tink.KeyWrap
    public byte[] wrap(byte[] arr_b) throws GeneralSecurityException {
        if(arr_b.length < 16) {
            throw new GeneralSecurityException("Key size of key to wrap too small");
        }
        if(arr_b.length > 0x1000) {
            throw new GeneralSecurityException("Key size of key to wrap too large");
        }
        byte[] arr_b1 = new byte[8];
        System.arraycopy(Kwp.PREFIX, 0, arr_b1, 0, Kwp.PREFIX.length);
        for(int v = 0; v < 4; ++v) {
            arr_b1[v + 4] = (byte)(arr_b.length >> (3 - v) * 8 & 0xFF);
        }
        return this.computeW(arr_b1, arr_b);
    }

    private int wrappingSize(int v) [...] // Inlined contents
}

