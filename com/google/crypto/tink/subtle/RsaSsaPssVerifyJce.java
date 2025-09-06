package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.errorprone.annotations.Immutable;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

@Immutable
public final class RsaSsaPssVerifyJce implements PublicKeyVerify {
    public static final AlgorithmFipsCompatibility FIPS;
    private final HashType mgf1Hash;
    private final RSAPublicKey publicKey;
    private final int saltLength;
    private final HashType sigHash;

    static {
        RsaSsaPssVerifyJce.FIPS = AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public RsaSsaPssVerifyJce(RSAPublicKey rSAPublicKey0, HashType enums$HashType0, HashType enums$HashType1, int v) throws GeneralSecurityException {
        if(!RsaSsaPssVerifyJce.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use RSA PSS in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateSignatureHash(enums$HashType0);
        Validators.validateRsaModulusSize(rSAPublicKey0.getModulus().bitLength());
        Validators.validateRsaPublicExponent(rSAPublicKey0.getPublicExponent());
        this.publicKey = rSAPublicKey0;
        this.sigHash = enums$HashType0;
        this.mgf1Hash = enums$HashType1;
        this.saltLength = v;
    }

    private void emsaPssVerify(byte[] arr_b, byte[] arr_b1, int v) throws GeneralSecurityException {
        int v8;
        Validators.validateSignatureHash(this.sigHash);
        String s = SubtleUtil.toDigestAlgo(this.sigHash);
        MessageDigest messageDigest0 = (MessageDigest)EngineFactory.MESSAGE_DIGEST.getInstance(s);
        byte[] arr_b2 = messageDigest0.digest(arr_b);
        int v1 = messageDigest0.getDigestLength();
        if(arr_b1.length < this.saltLength + v1 + 2 || arr_b1[arr_b1.length - 1] != -68) {
            throw new GeneralSecurityException("inconsistent");
        }
        int v2 = arr_b1.length - v1;
        byte[] arr_b3 = Arrays.copyOf(arr_b1, v2 - 1);
        byte[] arr_b4 = Arrays.copyOfRange(arr_b1, arr_b3.length, arr_b3.length + v1);
        for(int v3 = 0; true; ++v3) {
            long v4 = ((long)arr_b1.length) * 8L - ((long)v);
            if(((long)v3) >= v4) {
                break;
            }
            if((arr_b3[v3 / 8] >> 7 - v3 % 8 & 1) != 0) {
                throw new GeneralSecurityException("inconsistent");
            }
        }
        byte[] arr_b5 = SubtleUtil.mgf1(arr_b4, v2 - 1, this.mgf1Hash);
        byte[] arr_b6 = new byte[arr_b5.length];
        for(int v5 = 0; v5 < arr_b5.length; ++v5) {
            arr_b6[v5] = (byte)(arr_b5[v5] ^ arr_b3[v5]);
        }
        for(int v6 = 0; ((long)v6) <= v4; ++v6) {
            arr_b6[v6 / 8] = (byte)(~(1 << 7 - v6 % 8) & arr_b6[v6 / 8]);
        }
        for(int v7 = 0; true; ++v7) {
            v8 = this.saltLength;
            if(v7 >= v2 - v8 - 2) {
                break;
            }
            if(arr_b6[v7] != 0) {
                throw new GeneralSecurityException("inconsistent");
            }
        }
        if(arr_b6[v2 - v8 - 2] != 1) {
            throw new GeneralSecurityException("inconsistent");
        }
        byte[] arr_b7 = Arrays.copyOfRange(arr_b6, arr_b5.length - v8, arr_b5.length);
        byte[] arr_b8 = new byte[this.saltLength + (v1 + 8)];
        System.arraycopy(arr_b2, 0, arr_b8, 8, arr_b2.length);
        System.arraycopy(arr_b7, 0, arr_b8, v1 + 8, arr_b7.length);
        if(!Bytes.equal(messageDigest0.digest(arr_b8), arr_b4)) {
            throw new GeneralSecurityException("inconsistent");
        }
    }

    @Override  // com.google.crypto.tink.PublicKeyVerify
    public void verify(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        BigInteger bigInteger0 = this.publicKey.getPublicExponent();
        BigInteger bigInteger1 = this.publicKey.getModulus();
        int v = bigInteger1.bitLength();
        int v1 = bigInteger1.bitLength();
        if((v + 7) / 8 != arr_b.length) {
            throw new GeneralSecurityException("invalid signature\'s length");
        }
        BigInteger bigInteger2 = SubtleUtil.bytes2Integer(arr_b);
        if(bigInteger2.compareTo(bigInteger1) >= 0) {
            throw new GeneralSecurityException("signature out of range");
        }
        this.emsaPssVerify(arr_b1, SubtleUtil.integer2Bytes(bigInteger2.modPow(bigInteger0, bigInteger1), (v1 + 6) / 8), bigInteger1.bitLength() - 1);
    }
}

