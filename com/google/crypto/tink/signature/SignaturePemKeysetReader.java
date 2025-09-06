package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeysetReader;
import com.google.crypto.tink.PemKeyType;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaPublicKey;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset.Key;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.proto.RsaSsaPssPublicKey;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

public final class SignaturePemKeysetReader implements KeysetReader {
    public static final class Builder {
        private List pemKeys;

        Builder() {
            this.pemKeys = new ArrayList();
        }

        public Builder addPem(String s, PemKeyType pemKeyType0) {
            PemKey signaturePemKeysetReader$PemKey0 = new PemKey(null);
            signaturePemKeysetReader$PemKey0.reader = new BufferedReader(new StringReader(s));
            signaturePemKeysetReader$PemKey0.type = pemKeyType0;
            this.pemKeys.add(signaturePemKeysetReader$PemKey0);
            return this;
        }

        public KeysetReader build() {
            return new SignaturePemKeysetReader(this.pemKeys);
        }
    }

    static final class PemKey {
        BufferedReader reader;
        PemKeyType type;

        private PemKey() {
        }

        PemKey(com.google.crypto.tink.signature.SignaturePemKeysetReader.1 signaturePemKeysetReader$10) {
        }
    }

    private List pemKeys;

    SignaturePemKeysetReader(List list0) {
        this.pemKeys = list0;
    }

    private static KeyData convertEcPublicKey(PemKeyType pemKeyType0, ECPublicKey eCPublicKey0) throws IOException {
        if(!pemKeyType0.algorithm.equals("ECDSA")) {
            throw new IOException("unsupported EC signature algorithm: " + pemKeyType0.algorithm);
        }
        EcdsaParams ecdsaParams0 = (EcdsaParams)EcdsaParams.newBuilder().setHashType(SignaturePemKeysetReader.getHashType(pemKeyType0)).setCurve(SignaturePemKeysetReader.getCurveType(pemKeyType0)).setEncoding(EcdsaSignatureEncoding.DER).build();
        EcdsaPublicKey ecdsaPublicKey0 = (EcdsaPublicKey)EcdsaPublicKey.newBuilder().setVersion(new EcdsaVerifyKeyManager().getVersion()).setParams(ecdsaParams0).setX(SigUtil.toUnsignedIntByteString(eCPublicKey0.getW().getAffineX())).setY(SigUtil.toUnsignedIntByteString(eCPublicKey0.getW().getAffineY())).build();
        return (KeyData)KeyData.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.EcdsaPublicKey").setValue(ecdsaPublicKey0.toByteString()).setKeyMaterialType(KeyMaterialType.ASYMMETRIC_PUBLIC).build();
    }

    private static KeyData convertRsaPublicKey(PemKeyType pemKeyType0, RSAPublicKey rSAPublicKey0) throws IOException {
        if(pemKeyType0.algorithm.equals("RSASSA-PKCS1-v1_5")) {
            RsaSsaPkcs1Params rsaSsaPkcs1Params0 = (RsaSsaPkcs1Params)RsaSsaPkcs1Params.newBuilder().setHashType(SignaturePemKeysetReader.getHashType(pemKeyType0)).build();
            RsaSsaPkcs1PublicKey rsaSsaPkcs1PublicKey0 = (RsaSsaPkcs1PublicKey)RsaSsaPkcs1PublicKey.newBuilder().setVersion(new RsaSsaPkcs1VerifyKeyManager().getVersion()).setParams(rsaSsaPkcs1Params0).setE(SigUtil.toUnsignedIntByteString(rSAPublicKey0.getPublicExponent())).setN(SigUtil.toUnsignedIntByteString(rSAPublicKey0.getModulus())).build();
            return (KeyData)KeyData.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PublicKey").setValue(rsaSsaPkcs1PublicKey0.toByteString()).setKeyMaterialType(KeyMaterialType.ASYMMETRIC_PUBLIC).build();
        }
        if(!pemKeyType0.algorithm.equals("RSASSA-PSS")) {
            throw new IOException("unsupported RSA signature algorithm: " + pemKeyType0.algorithm);
        }
        RsaSsaPssParams rsaSsaPssParams0 = (RsaSsaPssParams)RsaSsaPssParams.newBuilder().setSigHash(SignaturePemKeysetReader.getHashType(pemKeyType0)).setMgf1Hash(SignaturePemKeysetReader.getHashType(pemKeyType0)).setSaltLength(SignaturePemKeysetReader.getDigestSizeInBytes(pemKeyType0)).build();
        RsaSsaPssPublicKey rsaSsaPssPublicKey0 = (RsaSsaPssPublicKey)RsaSsaPssPublicKey.newBuilder().setVersion(new RsaSsaPssVerifyKeyManager().getVersion()).setParams(rsaSsaPssParams0).setE(SigUtil.toUnsignedIntByteString(rSAPublicKey0.getPublicExponent())).setN(SigUtil.toUnsignedIntByteString(rSAPublicKey0.getModulus())).build();
        return (KeyData)KeyData.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey").setValue(rsaSsaPssPublicKey0.toByteString()).setKeyMaterialType(KeyMaterialType.ASYMMETRIC_PUBLIC).build();
    }

    private static EllipticCurveType getCurveType(PemKeyType pemKeyType0) {
        switch(pemKeyType0.keySizeInBits) {
            case 0x100: {
                return EllipticCurveType.NIST_P256;
            }
            case 0x180: {
                return EllipticCurveType.NIST_P384;
            }
            case 0x209: {
                return EllipticCurveType.NIST_P521;
            }
            default: {
                throw new IllegalArgumentException("unsupported curve for key size: " + pemKeyType0.keySizeInBits);
            }
        }
    }

    private static int getDigestSizeInBytes(PemKeyType pemKeyType0) {
        switch(com.google.crypto.tink.signature.SignaturePemKeysetReader.1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[pemKeyType0.hash.ordinal()]) {
            case 1: {
                return 0x20;
            }
            case 2: {
                return 0x30;
            }
            case 3: {
                return 0x40;
            }
            default: {
                throw new IllegalArgumentException("unsupported hash type: " + pemKeyType0.hash.name());
            }
        }
    }

    private static HashType getHashType(PemKeyType pemKeyType0) {
        switch(com.google.crypto.tink.signature.SignaturePemKeysetReader.1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[pemKeyType0.hash.ordinal()]) {
            case 1: {
                return HashType.SHA256;
            }
            case 2: {
                return HashType.SHA384;
            }
            case 3: {
                return HashType.SHA512;
            }
            default: {
                throw new IllegalArgumentException("unsupported hash type: " + pemKeyType0.hash.name());
            }
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override  // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        com.google.crypto.tink.proto.Keyset.Builder keyset$Builder0 = Keyset.newBuilder();
        for(Object object0: this.pemKeys) {
            PemKey signaturePemKeysetReader$PemKey0 = (PemKey)object0;
            for(Key keyset$Key0 = SignaturePemKeysetReader.readKey(signaturePemKeysetReader$PemKey0.reader, signaturePemKeysetReader$PemKey0.type); keyset$Key0 != null; keyset$Key0 = SignaturePemKeysetReader.readKey(signaturePemKeysetReader$PemKey0.reader, signaturePemKeysetReader$PemKey0.type)) {
                keyset$Builder0.addKey(keyset$Key0);
            }
        }
        if(keyset$Builder0.getKeyCount() == 0) {
            throw new IOException("cannot find any key");
        }
        keyset$Builder0.setPrimaryKeyId(keyset$Builder0.getKey(0).getKeyId());
        return (Keyset)keyset$Builder0.build();
    }

    @Override  // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        throw new UnsupportedOperationException();
    }

    private static Key readKey(BufferedReader bufferedReader0, PemKeyType pemKeyType0) throws IOException {
        KeyData keyData0;
        java.security.Key key0 = pemKeyType0.readKey(bufferedReader0);
        if(key0 == null) {
            return null;
        }
        if(key0 instanceof RSAPublicKey) {
            keyData0 = SignaturePemKeysetReader.convertRsaPublicKey(pemKeyType0, ((RSAPublicKey)key0));
            return (Key)Key.newBuilder().setKeyData(keyData0).setStatus(KeyStatusType.ENABLED).setOutputPrefixType(OutputPrefixType.RAW).setKeyId(Random.randInt()).build();
        }
        if(key0 instanceof ECPublicKey) {
            keyData0 = SignaturePemKeysetReader.convertEcPublicKey(pemKeyType0, ((ECPublicKey)key0));
            return (Key)Key.newBuilder().setKeyData(keyData0).setStatus(KeyStatusType.ENABLED).setOutputPrefixType(OutputPrefixType.RAW).setKeyId(Random.randInt()).build();
        }
        return null;
    }

    class com.google.crypto.tink.signature.SignaturePemKeysetReader.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType;

        static {
            int[] arr_v = new int[com.google.crypto.tink.subtle.Enums.HashType.values().length];
            com.google.crypto.tink.signature.SignaturePemKeysetReader.1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType = arr_v;
            try {
                arr_v[com.google.crypto.tink.subtle.Enums.HashType.SHA256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.signature.SignaturePemKeysetReader.1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[com.google.crypto.tink.subtle.Enums.HashType.SHA384.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.signature.SignaturePemKeysetReader.1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[com.google.crypto.tink.subtle.Enums.HashType.SHA512.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

