package com.google.crypto.tink;

import com.google.crypto.tink.subtle.Base64;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums.HashType;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.annotation.Nullable;

public enum PemKeyType {
    RSA_PSS_2048_SHA256("RSA", "RSASSA-PSS", 0x800, HashType.SHA256),
    RSA_PSS_3072_SHA256("RSA", "RSASSA-PSS", 0xC00, HashType.SHA256),
    RSA_PSS_4096_SHA256("RSA", "RSASSA-PSS", 0x1000, HashType.SHA256),
    RSA_PSS_4096_SHA512("RSA", "RSASSA-PSS", 0x1000, HashType.SHA512),
    RSA_SIGN_PKCS1_2048_SHA256("RSA", "RSASSA-PKCS1-v1_5", 0x800, HashType.SHA256),
    RSA_SIGN_PKCS1_3072_SHA256("RSA", "RSASSA-PKCS1-v1_5", 0xC00, HashType.SHA256),
    RSA_SIGN_PKCS1_4096_SHA256("RSA", "RSASSA-PKCS1-v1_5", 0x1000, HashType.SHA256),
    RSA_SIGN_PKCS1_4096_SHA512("RSA", "RSASSA-PKCS1-v1_5", 0x1000, HashType.SHA512),
    ECDSA_P256_SHA256("EC", "ECDSA", 0x100, HashType.SHA256),
    ECDSA_P384_SHA384("EC", "ECDSA", 0x180, HashType.SHA384),
    ECDSA_P521_SHA512("EC", "ECDSA", 0x209, HashType.SHA512);

    private static final String BEGIN = "-----BEGIN ";
    private static final String END = "-----END ";
    private static final String MARKER = "-----";
    private static final String PRIVATE_KEY = "PRIVATE KEY";
    private static final String PUBLIC_KEY = "PUBLIC KEY";
    public final String algorithm;
    public final HashType hash;
    public final int keySizeInBits;
    public final String keyType;

    private PemKeyType(String s1, String s2, int v1, HashType enums$HashType0) {
        this.keyType = s1;
        this.algorithm = s2;
        this.keySizeInBits = v1;
        this.hash = enums$HashType0;
    }

    private Key getPrivateKey(byte[] arr_b) throws GeneralSecurityException {
        return this.validate(((KeyFactory)EngineFactory.KEY_FACTORY.getInstance(this.keyType)).generatePrivate(new PKCS8EncodedKeySpec(arr_b)));
    }

    private Key getPublicKey(byte[] arr_b) throws GeneralSecurityException {
        return this.validate(((KeyFactory)EngineFactory.KEY_FACTORY.getInstance(this.keyType)).generatePublic(new X509EncodedKeySpec(arr_b)));
    }

    @Nullable
    public Key readKey(BufferedReader bufferedReader0) throws IOException {
        String s;
        for(s = bufferedReader0.readLine(); s != null && !s.startsWith("-----BEGIN "); s = bufferedReader0.readLine()) {
        }
        if(s == null) {
            return null;
        }
        String s1 = s.trim().substring(11);
        int v = s1.indexOf("-----");
        if(v < 0) {
            return null;
        }
        String s2 = s1.substring(0, v);
        StringBuilder stringBuilder0 = new StringBuilder();
        String s3;
        while((s3 = bufferedReader0.readLine()) != null) {
            if(s3.indexOf(":") <= 0) {
                if(s3.contains("-----END " + s2 + "-----")) {
                    break;
                }
                stringBuilder0.append(s3);
            }
        }
        try {
            byte[] arr_b = Base64.decode(stringBuilder0.toString(), 0);
            if(s2.contains("PUBLIC KEY")) {
                return this.getPublicKey(arr_b);
            }
            return s2.contains("PRIVATE KEY") ? this.getPrivateKey(arr_b) : null;
        }
        catch(GeneralSecurityException | IllegalArgumentException unused_ex) {
        }
        return null;
    }

    private Key validate(Key key0) throws GeneralSecurityException {
        if(this.keyType.equals("RSA")) {
            int v = ((RSAKey)key0).getModulus().bitLength();
            if(v != this.keySizeInBits) {
                throw new GeneralSecurityException(String.format("invalid RSA key size, want %d got %d", this.keySizeInBits, v));
            }
            return key0;
        }
        ECParameterSpec eCParameterSpec0 = ((ECKey)key0).getParams();
        if(!EllipticCurves.isNistEcParameterSpec(eCParameterSpec0)) {
            throw new GeneralSecurityException("unsupport EC spec: " + eCParameterSpec0.toString());
        }
        int v1 = EllipticCurves.fieldSizeInBits(eCParameterSpec0.getCurve());
        if(v1 != this.keySizeInBits) {
            throw new GeneralSecurityException(String.format("invalid EC key size, want %d got %d", this.keySizeInBits, v1));
        }
        return key0;
    }
}

