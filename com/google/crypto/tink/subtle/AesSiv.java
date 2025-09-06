package com.google.crypto.tink.subtle;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.mac.internal.AesUtil;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesSiv implements DeterministicAead {
    private static final byte[] BLOCK_ONE;
    private static final byte[] BLOCK_ZERO;
    public static final AlgorithmFipsCompatibility FIPS;
    private static final Collection KEY_SIZES;
    private final byte[] aesCtrKey;
    private final PrfAesCmac cmacForS2V;

    static {
        AesSiv.FIPS = AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
        AesSiv.KEY_SIZES = Arrays.asList(new Integer[]{0x40});
        AesSiv.BLOCK_ZERO = new byte[16];
        AesSiv.BLOCK_ONE = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
    }

    public AesSiv(byte[] arr_b) throws GeneralSecurityException {
        if(!AesSiv.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-SIV in FIPS-mode.");
        }
        if(!AesSiv.KEY_SIZES.contains(((int)arr_b.length))) {
            throw new InvalidKeyException("invalid key size: " + arr_b.length + " bytes; key must have 64 bytes");
        }
        byte[] arr_b1 = Arrays.copyOfRange(arr_b, 0, arr_b.length / 2);
        this.aesCtrKey = Arrays.copyOfRange(arr_b, arr_b.length / 2, arr_b.length);
        this.cmacForS2V = new PrfAesCmac(arr_b1);
    }

    @Override  // com.google.crypto.tink.DeterministicAead
    public byte[] decryptDeterministically(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(arr_b.length < 16) {
            throw new GeneralSecurityException("Ciphertext too short.");
        }
        Cipher cipher0 = (Cipher)EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
        byte[] arr_b2 = Arrays.copyOfRange(arr_b, 0, 16);
        byte[] arr_b3 = (byte[])arr_b2.clone();
        arr_b3[8] = (byte)(arr_b3[8] & 0x7F);
        arr_b3[12] = (byte)(arr_b3[12] & 0x7F);
        cipher0.init(2, new SecretKeySpec(this.aesCtrKey, "AES"), new IvParameterSpec(arr_b3));
        byte[] arr_b4 = Arrays.copyOfRange(arr_b, 16, arr_b.length);
        byte[] arr_b5 = cipher0.doFinal(arr_b4);
        if(arr_b4.length == 0 && arr_b5 == null && SubtleUtil.isAndroid()) {
            arr_b5 = new byte[0];
        }
        if(!Bytes.equal(arr_b2, this.s2v(new byte[][]{arr_b1, arr_b5}))) {
            throw new AEADBadTagException("Integrity check failed.");
        }
        return arr_b5;
    }

    @Override  // com.google.crypto.tink.DeterministicAead
    public byte[] encryptDeterministically(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(arr_b.length > 0x7FFFFFEF) {
            throw new GeneralSecurityException("plaintext too long");
        }
        Cipher cipher0 = (Cipher)EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
        byte[] arr_b2 = this.s2v(new byte[][]{arr_b1, arr_b});
        byte[] arr_b3 = (byte[])arr_b2.clone();
        arr_b3[8] = (byte)(arr_b3[8] & 0x7F);
        arr_b3[12] = (byte)(arr_b3[12] & 0x7F);
        cipher0.init(1, new SecretKeySpec(this.aesCtrKey, "AES"), new IvParameterSpec(arr_b3));
        return Bytes.concat(new byte[][]{arr_b2, cipher0.doFinal(arr_b)});
    }

    private byte[] s2v(byte[][] arr2_b) throws GeneralSecurityException {
        byte[] arr_b3;
        if(arr2_b.length == 0) {
            return this.cmacForS2V.compute(AesSiv.BLOCK_ONE, 16);
        }
        byte[] arr_b = this.cmacForS2V.compute(AesSiv.BLOCK_ZERO, 16);
        for(int v = 0; v < arr2_b.length - 1; ++v) {
            byte[] arr_b1 = arr2_b[v];
            if(arr_b1 == null) {
                arr_b1 = new byte[0];
            }
            arr_b = Bytes.xor(AesUtil.dbl(arr_b), this.cmacForS2V.compute(arr_b1, 16));
        }
        byte[] arr_b2 = arr2_b[arr2_b.length - 1];
        if(arr_b2.length >= 16) {
            arr_b3 = Bytes.xorEnd(arr_b2, arr_b);
            return this.cmacForS2V.compute(arr_b3, 16);
        }
        arr_b3 = Bytes.xor(AesUtil.cmacPad(arr_b2), AesUtil.dbl(arr_b));
        return this.cmacForS2V.compute(arr_b3, 16);
    }
}

