package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.errorprone.annotations.Immutable;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

@Immutable
public final class RsaSsaPssSignJce implements PublicKeySign {
    public static final AlgorithmFipsCompatibility FIPS = null;
    private static final String RAW_RSA_ALGORITHM = "RSA/ECB/NOPADDING";
    private final HashType mgf1Hash;
    private final RSAPrivateCrtKey privateKey;
    private final RSAPublicKey publicKey;
    private final int saltLength;
    private final HashType sigHash;

    static {
        RsaSsaPssSignJce.FIPS = AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public RsaSsaPssSignJce(RSAPrivateCrtKey rSAPrivateCrtKey0, HashType enums$HashType0, HashType enums$HashType1, int v) throws GeneralSecurityException {
        if(!RsaSsaPssSignJce.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use RSA PSS in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateSignatureHash(enums$HashType0);
        Validators.validateRsaModulusSize(rSAPrivateCrtKey0.getModulus().bitLength());
        Validators.validateRsaPublicExponent(rSAPrivateCrtKey0.getPublicExponent());
        this.privateKey = rSAPrivateCrtKey0;
        this.publicKey = (RSAPublicKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA")).generatePublic(new RSAPublicKeySpec(rSAPrivateCrtKey0.getModulus(), rSAPrivateCrtKey0.getPublicExponent()));
        this.sigHash = enums$HashType0;
        this.mgf1Hash = enums$HashType1;
        this.saltLength = v;
    }

    private byte[] emsaPssEncode(byte[] arr_b, int v) throws GeneralSecurityException {
        Validators.validateSignatureHash(this.sigHash);
        String s = SubtleUtil.toDigestAlgo(this.sigHash);
        MessageDigest messageDigest0 = (MessageDigest)EngineFactory.MESSAGE_DIGEST.getInstance(s);
        byte[] arr_b1 = messageDigest0.digest(arr_b);
        int v1 = messageDigest0.getDigestLength();
        int v2 = (v - 1) / 8 + 1;
        int v3 = this.saltLength;
        if(v2 < v1 + v3 + 2) {
            throw new GeneralSecurityException("encoding error");
        }
        byte[] arr_b2 = Random.randBytes(v3);
        byte[] arr_b3 = new byte[this.saltLength + (v1 + 8)];
        System.arraycopy(arr_b1, 0, arr_b3, 8, v1);
        System.arraycopy(arr_b2, 0, arr_b3, v1 + 8, arr_b2.length);
        byte[] arr_b4 = messageDigest0.digest(arr_b3);
        int v4 = v2 - v1 - 1;
        byte[] arr_b5 = new byte[v4];
        arr_b5[v2 - this.saltLength - v1 - 2] = 1;
        System.arraycopy(arr_b2, 0, arr_b5, v2 - this.saltLength - v1 - 1, arr_b2.length);
        byte[] arr_b6 = SubtleUtil.mgf1(arr_b4, v4, this.mgf1Hash);
        byte[] arr_b7 = new byte[v4];
        for(int v5 = 0; v5 < v4; ++v5) {
            arr_b7[v5] = (byte)(arr_b5[v5] ^ arr_b6[v5]);
        }
        for(int v6 = 0; ((long)v6) < ((long)v2) * 8L - ((long)v); ++v6) {
            arr_b7[v6 / 8] = (byte)(~(1 << 7 - v6 % 8) & arr_b7[v6 / 8]);
        }
        int v7 = v1 + v4;
        byte[] arr_b8 = new byte[v7 + 1];
        System.arraycopy(arr_b7, 0, arr_b8, 0, v4);
        System.arraycopy(arr_b4, 0, arr_b8, v4, arr_b4.length);
        arr_b8[v7] = -68;
        return arr_b8;
    }

    private byte[] rsasp1(byte[] arr_b) throws GeneralSecurityException {
        Cipher cipher0 = (Cipher)EngineFactory.CIPHER.getInstance("RSA/ECB/NOPADDING");
        cipher0.init(2, this.privateKey);
        byte[] arr_b1 = cipher0.doFinal(arr_b);
        Cipher cipher1 = (Cipher)EngineFactory.CIPHER.getInstance("RSA/ECB/NOPADDING");
        cipher1.init(1, this.publicKey);
        byte[] arr_b2 = cipher1.doFinal(arr_b1);
        if(!new BigInteger(1, arr_b).equals(new BigInteger(1, arr_b2))) {
            throw new RuntimeException("Security bug: RSA signature computation error");
        }
        return arr_b1;
    }

    @Override  // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] arr_b) throws GeneralSecurityException {
        return this.rsasp1(this.emsaPssEncode(arr_b, this.publicKey.getModulus().bitLength() - 1));
    }
}

