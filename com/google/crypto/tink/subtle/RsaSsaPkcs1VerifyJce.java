package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.errorprone.annotations.Immutable;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;

@Immutable
public final class RsaSsaPkcs1VerifyJce implements PublicKeyVerify {
    private static final String ASN_PREFIX_SHA256 = "3031300d060960864801650304020105000420";
    private static final String ASN_PREFIX_SHA384 = "3041300d060960864801650304020205000430";
    private static final String ASN_PREFIX_SHA512 = "3051300d060960864801650304020305000440";
    public static final AlgorithmFipsCompatibility FIPS;
    private final HashType hash;
    private final RSAPublicKey publicKey;

    static {
        RsaSsaPkcs1VerifyJce.FIPS = AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public RsaSsaPkcs1VerifyJce(RSAPublicKey rSAPublicKey0, HashType enums$HashType0) throws GeneralSecurityException {
        if(!RsaSsaPkcs1VerifyJce.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use RSA-PKCS1.5 in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateSignatureHash(enums$HashType0);
        Validators.validateRsaModulusSize(rSAPublicKey0.getModulus().bitLength());
        Validators.validateRsaPublicExponent(rSAPublicKey0.getPublicExponent());
        this.publicKey = rSAPublicKey0;
        this.hash = enums$HashType0;
    }

    private byte[] emsaPkcs1(byte[] arr_b, int v, HashType enums$HashType0) throws GeneralSecurityException {
        Validators.validateSignatureHash(enums$HashType0);
        String s = SubtleUtil.toDigestAlgo(this.hash);
        MessageDigest messageDigest0 = (MessageDigest)EngineFactory.MESSAGE_DIGEST.getInstance(s);
        messageDigest0.update(arr_b);
        byte[] arr_b1 = messageDigest0.digest();
        byte[] arr_b2 = this.toAsnPrefix(enums$HashType0);
        int v1 = arr_b2.length + arr_b1.length;
        if(v < v1 + 11) {
            throw new GeneralSecurityException("intended encoded message length too short");
        }
        byte[] arr_b3 = new byte[v];
        arr_b3[0] = 0;
        arr_b3[1] = 1;
        int v2 = 2;
        int v3 = 0;
        while(v3 < v - v1 - 3) {
            arr_b3[v2] = -1;
            ++v3;
            ++v2;
        }
        arr_b3[v2] = 0;
        System.arraycopy(arr_b2, 0, arr_b3, v2 + 1, arr_b2.length);
        System.arraycopy(arr_b1, 0, arr_b3, v2 + 1 + arr_b2.length, arr_b1.length);
        return arr_b3;
    }

    private byte[] toAsnPrefix(HashType enums$HashType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.subtle.RsaSsaPkcs1VerifyJce.1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[enums$HashType0.ordinal()]) {
            case 1: {
                return new byte[]{0x30, 49, 0x30, 13, 6, 9, 0x60, (byte)0x86, 72, 1, 101, 3, 4, 2, 1, 5, 0, 4, 0x20};
            }
            case 2: {
                return new byte[]{0x30, 65, 0x30, 13, 6, 9, 0x60, (byte)0x86, 72, 1, 101, 3, 4, 2, 2, 5, 0, 4, 0x30};
            }
            case 3: {
                return new byte[]{0x30, 81, 0x30, 13, 6, 9, 0x60, (byte)0x86, 72, 1, 101, 3, 4, 2, 3, 5, 0, 4, 0x40};
            }
            default: {
                throw new GeneralSecurityException("Unsupported hash " + enums$HashType0);
            }
        }
    }

    @Override  // com.google.crypto.tink.PublicKeyVerify
    public void verify(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        BigInteger bigInteger0 = this.publicKey.getPublicExponent();
        BigInteger bigInteger1 = this.publicKey.getModulus();
        int v = (bigInteger1.bitLength() + 7) / 8;
        if(v != arr_b.length) {
            throw new GeneralSecurityException("invalid signature\'s length");
        }
        BigInteger bigInteger2 = SubtleUtil.bytes2Integer(arr_b);
        if(bigInteger2.compareTo(bigInteger1) >= 0) {
            throw new GeneralSecurityException("signature out of range");
        }
        if(!Bytes.equal(SubtleUtil.integer2Bytes(bigInteger2.modPow(bigInteger0, bigInteger1), v), this.emsaPkcs1(arr_b1, v, this.hash))) {
            throw new GeneralSecurityException("invalid signature");
        }
    }
}

