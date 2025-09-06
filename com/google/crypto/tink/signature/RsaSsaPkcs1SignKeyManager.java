package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormat;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKey;
import com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPkcs1SignJce;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class RsaSsaPkcs1SignKeyManager extends PrivateKeyTypeManager {
    RsaSsaPkcs1SignKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public PublicKeySign getPrimitive(RsaSsaPkcs1PrivateKey rsaSsaPkcs1PrivateKey0) throws GeneralSecurityException {
                KeyFactory keyFactory0 = (KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA");
                RSAPrivateCrtKey rSAPrivateCrtKey0 = (RSAPrivateCrtKey)keyFactory0.generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, rsaSsaPkcs1PrivateKey0.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey0.getPublicKey().getE().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey0.getD().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey0.getP().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey0.getQ().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey0.getDp().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey0.getDq().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey0.getCrt().toByteArray())));
                RsaSsaPkcs1Params rsaSsaPkcs1Params0 = rsaSsaPkcs1PrivateKey0.getPublicKey().getParams();
                SelfKeyTestValidators.validateRsaSsaPkcs1(rSAPrivateCrtKey0, ((RSAPublicKey)keyFactory0.generatePublic(new RSAPublicKeySpec(new BigInteger(1, rsaSsaPkcs1PrivateKey0.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey0.getPublicKey().getE().toByteArray())))), SigUtil.toHashType(rsaSsaPkcs1Params0.getHashType()));
                return new RsaSsaPkcs1SignJce(rSAPrivateCrtKey0, SigUtil.toHashType(rsaSsaPkcs1Params0.getHashType()));
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((RsaSsaPkcs1PrivateKey)messageLite0));
            }
        }};
        super(RsaSsaPkcs1PrivateKey.class, RsaSsaPkcs1PublicKey.class, arr_primitiveFactory);
    }

    private static RsaSsaPkcs1KeyFormat createKeyFormat(HashType hashType0, int v, BigInteger bigInteger0) {
        RsaSsaPkcs1Params rsaSsaPkcs1Params0 = (RsaSsaPkcs1Params)RsaSsaPkcs1Params.newBuilder().setHashType(hashType0).build();
        return (RsaSsaPkcs1KeyFormat)RsaSsaPkcs1KeyFormat.newBuilder().setParams(rsaSsaPkcs1Params0).setModulusSizeInBits(v).setPublicExponent(ByteString.copyFrom(bigInteger0.toByteArray())).build();
    }

    // 去混淆评级： 低(20)
    private static KeyTemplate createKeyTemplate(HashType hashType0, int v, BigInteger bigInteger0, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey", RsaSsaPkcs1SignKeyManager.createKeyFormat(hashType0, v, bigInteger0).toByteArray(), keyTemplate$OutputPrefixType0);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public AlgorithmFipsCompatibility fipsStatus() {
        return AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    public RsaSsaPkcs1PublicKey getPublicKey(RsaSsaPkcs1PrivateKey rsaSsaPkcs1PrivateKey0) throws GeneralSecurityException {
        return rsaSsaPkcs1PrivateKey0.getPublicKey();
    }

    @Override  // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public MessageLite getPublicKey(MessageLite messageLite0) throws GeneralSecurityException {
        return this.getPublicKey(((RsaSsaPkcs1PrivateKey)messageLite0));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.internal.KeyTypeManager.KeyFactory keyFactory() {
        return new com.google.crypto.tink.internal.KeyTypeManager.KeyFactory(RsaSsaPkcs1KeyFormat.class) {
            public RsaSsaPkcs1PrivateKey createKey(RsaSsaPkcs1KeyFormat rsaSsaPkcs1KeyFormat0) throws GeneralSecurityException {
                RsaSsaPkcs1Params rsaSsaPkcs1Params0 = rsaSsaPkcs1KeyFormat0.getParams();
                KeyPairGenerator keyPairGenerator0 = (KeyPairGenerator)EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                keyPairGenerator0.initialize(new RSAKeyGenParameterSpec(rsaSsaPkcs1KeyFormat0.getModulusSizeInBits(), new BigInteger(1, rsaSsaPkcs1KeyFormat0.getPublicExponent().toByteArray())));
                KeyPair keyPair0 = keyPairGenerator0.generateKeyPair();
                RSAPublicKey rSAPublicKey0 = (RSAPublicKey)keyPair0.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey0 = (RSAPrivateCrtKey)keyPair0.getPrivate();
                RsaSsaPkcs1PublicKey rsaSsaPkcs1PublicKey0 = (RsaSsaPkcs1PublicKey)RsaSsaPkcs1PublicKey.newBuilder().setVersion(0).setParams(rsaSsaPkcs1Params0).setE(ByteString.copyFrom(rSAPublicKey0.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey0.getModulus().toByteArray())).build();
                return (RsaSsaPkcs1PrivateKey)RsaSsaPkcs1PrivateKey.newBuilder().setVersion(0).setPublicKey(rsaSsaPkcs1PublicKey0).setD(ByteString.copyFrom(rSAPrivateCrtKey0.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey0.getCrtCoefficient().toByteArray())).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((RsaSsaPkcs1KeyFormat)messageLite0));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("RSA_SSA_PKCS1_3072_SHA256_F4", new KeyFormat(RsaSsaPkcs1SignKeyManager.createKeyFormat(HashType.SHA256, 0xC00, RSAKeyGenParameterSpec.F4), OutputPrefixType.TINK));
                hashMap0.put("RSA_SSA_PKCS1_3072_SHA256_F4_RAW", new KeyFormat(RsaSsaPkcs1SignKeyManager.createKeyFormat(HashType.SHA256, 0xC00, RSAKeyGenParameterSpec.F4), OutputPrefixType.RAW));
                hashMap0.put("RSA_SSA_PKCS1_3072_SHA256_F4_WITHOUT_PREFIX", new KeyFormat(RsaSsaPkcs1SignKeyManager.createKeyFormat(HashType.SHA256, 0xC00, RSAKeyGenParameterSpec.F4), OutputPrefixType.RAW));
                hashMap0.put("RSA_SSA_PKCS1_4096_SHA512_F4", new KeyFormat(RsaSsaPkcs1SignKeyManager.createKeyFormat(HashType.SHA512, 0x1000, RSAKeyGenParameterSpec.F4), OutputPrefixType.TINK));
                hashMap0.put("RSA_SSA_PKCS1_4096_SHA512_F4_RAW", new KeyFormat(RsaSsaPkcs1SignKeyManager.createKeyFormat(HashType.SHA512, 0x1000, RSAKeyGenParameterSpec.F4), OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public RsaSsaPkcs1KeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return RsaSsaPkcs1KeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(RsaSsaPkcs1KeyFormat rsaSsaPkcs1KeyFormat0) throws GeneralSecurityException {
                SigUtil.validateRsaSsaPkcs1Params(rsaSsaPkcs1KeyFormat0.getParams());
                Validators.validateRsaModulusSize(rsaSsaPkcs1KeyFormat0.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPkcs1KeyFormat0.getPublicExponent().toByteArray()));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((RsaSsaPkcs1KeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public RsaSsaPkcs1PrivateKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return RsaSsaPkcs1PrivateKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static final KeyTemplate rawRsa3072SsaPkcs1Sha256F4Template() {
        return RsaSsaPkcs1SignKeyManager.createKeyTemplate(HashType.SHA256, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawRsa4096SsaPkcs1Sha512F4Template() {
        return RsaSsaPkcs1SignKeyManager.createKeyTemplate(HashType.SHA512, 0x1000, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW);
    }

    public static void registerPair(boolean z) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new RsaSsaPkcs1SignKeyManager(), new RsaSsaPkcs1VerifyKeyManager(), z);
    }

    public static final KeyTemplate rsa3072SsaPkcs1Sha256F4Template() {
        return RsaSsaPkcs1SignKeyManager.createKeyTemplate(HashType.SHA256, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK);
    }

    public static final KeyTemplate rsa4096SsaPkcs1Sha512F4Template() {
        return RsaSsaPkcs1SignKeyManager.createKeyTemplate(HashType.SHA512, 0x1000, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK);
    }

    public void validateKey(RsaSsaPkcs1PrivateKey rsaSsaPkcs1PrivateKey0) throws GeneralSecurityException {
        Validators.validateVersion(rsaSsaPkcs1PrivateKey0.getVersion(), 0);
        Validators.validateRsaModulusSize(new BigInteger(1, rsaSsaPkcs1PrivateKey0.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPkcs1PrivateKey0.getPublicKey().getE().toByteArray()));
        SigUtil.validateRsaSsaPkcs1Params(rsaSsaPkcs1PrivateKey0.getPublicKey().getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((RsaSsaPkcs1PrivateKey)messageLite0));
    }
}

