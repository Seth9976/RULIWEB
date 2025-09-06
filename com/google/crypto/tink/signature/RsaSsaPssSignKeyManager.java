package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.RsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.proto.RsaSsaPssPrivateKey;
import com.google.crypto.tink.proto.RsaSsaPssPublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPssSignJce;
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

public final class RsaSsaPssSignKeyManager extends PrivateKeyTypeManager {
    RsaSsaPssSignKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public PublicKeySign getPrimitive(RsaSsaPssPrivateKey rsaSsaPssPrivateKey0) throws GeneralSecurityException {
                KeyFactory keyFactory0 = (KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA");
                RSAPrivateCrtKey rSAPrivateCrtKey0 = (RSAPrivateCrtKey)keyFactory0.generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, rsaSsaPssPrivateKey0.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey0.getPublicKey().getE().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey0.getD().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey0.getP().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey0.getQ().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey0.getDp().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey0.getDq().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey0.getCrt().toByteArray())));
                RsaSsaPssParams rsaSsaPssParams0 = rsaSsaPssPrivateKey0.getPublicKey().getParams();
                SelfKeyTestValidators.validateRsaSsaPss(rSAPrivateCrtKey0, ((RSAPublicKey)keyFactory0.generatePublic(new RSAPublicKeySpec(new BigInteger(1, rsaSsaPssPrivateKey0.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey0.getPublicKey().getE().toByteArray())))), SigUtil.toHashType(rsaSsaPssParams0.getSigHash()), SigUtil.toHashType(rsaSsaPssParams0.getMgf1Hash()), rsaSsaPssParams0.getSaltLength());
                return new RsaSsaPssSignJce(rSAPrivateCrtKey0, SigUtil.toHashType(rsaSsaPssParams0.getSigHash()), SigUtil.toHashType(rsaSsaPssParams0.getMgf1Hash()), rsaSsaPssParams0.getSaltLength());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((RsaSsaPssPrivateKey)messageLite0));
            }
        }};
        super(RsaSsaPssPrivateKey.class, RsaSsaPssPublicKey.class, arr_primitiveFactory);
    }

    private static RsaSsaPssKeyFormat createKeyFormat(HashType hashType0, HashType hashType1, int v, int v1, BigInteger bigInteger0) {
        RsaSsaPssParams rsaSsaPssParams0 = (RsaSsaPssParams)RsaSsaPssParams.newBuilder().setSigHash(hashType0).setMgf1Hash(hashType1).setSaltLength(v).build();
        return (RsaSsaPssKeyFormat)RsaSsaPssKeyFormat.newBuilder().setParams(rsaSsaPssParams0).setModulusSizeInBits(v1).setPublicExponent(ByteString.copyFrom(bigInteger0.toByteArray())).build();
    }

    // 去混淆评级： 低(20)
    private static KeyTemplate createKeyTemplate(HashType hashType0, HashType hashType1, int v, int v1, BigInteger bigInteger0, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey", RsaSsaPssSignKeyManager.createKeyFormat(hashType0, hashType1, v, v1, bigInteger0).toByteArray(), keyTemplate$OutputPrefixType0);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    public RsaSsaPssPublicKey getPublicKey(RsaSsaPssPrivateKey rsaSsaPssPrivateKey0) throws GeneralSecurityException {
        return rsaSsaPssPrivateKey0.getPublicKey();
    }

    @Override  // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public MessageLite getPublicKey(MessageLite messageLite0) throws GeneralSecurityException {
        return this.getPublicKey(((RsaSsaPssPrivateKey)messageLite0));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.internal.KeyTypeManager.KeyFactory keyFactory() {
        return new com.google.crypto.tink.internal.KeyTypeManager.KeyFactory(RsaSsaPssKeyFormat.class) {
            public RsaSsaPssPrivateKey createKey(RsaSsaPssKeyFormat rsaSsaPssKeyFormat0) throws GeneralSecurityException {
                RsaSsaPssParams rsaSsaPssParams0 = rsaSsaPssKeyFormat0.getParams();
                Validators.validateRsaModulusSize(rsaSsaPssKeyFormat0.getModulusSizeInBits());
                Validators.validateSignatureHash(SigUtil.toHashType(rsaSsaPssParams0.getSigHash()));
                KeyPairGenerator keyPairGenerator0 = (KeyPairGenerator)EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                keyPairGenerator0.initialize(new RSAKeyGenParameterSpec(rsaSsaPssKeyFormat0.getModulusSizeInBits(), new BigInteger(1, rsaSsaPssKeyFormat0.getPublicExponent().toByteArray())));
                KeyPair keyPair0 = keyPairGenerator0.generateKeyPair();
                RSAPublicKey rSAPublicKey0 = (RSAPublicKey)keyPair0.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey0 = (RSAPrivateCrtKey)keyPair0.getPrivate();
                RsaSsaPssPublicKey rsaSsaPssPublicKey0 = (RsaSsaPssPublicKey)RsaSsaPssPublicKey.newBuilder().setVersion(0).setParams(rsaSsaPssParams0).setE(ByteString.copyFrom(rSAPublicKey0.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey0.getModulus().toByteArray())).build();
                return (RsaSsaPssPrivateKey)RsaSsaPssPrivateKey.newBuilder().setVersion(0).setPublicKey(rsaSsaPssPublicKey0).setD(ByteString.copyFrom(rSAPrivateCrtKey0.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey0.getCrtCoefficient().toByteArray())).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((RsaSsaPssKeyFormat)messageLite0));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("RSA_SSA_PSS_3072_SHA256_F4", new KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(HashType.SHA256, HashType.SHA256, 0x20, 0xC00, RSAKeyGenParameterSpec.F4), OutputPrefixType.TINK));
                hashMap0.put("RSA_SSA_PSS_3072_SHA256_F4_RAW", new KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(HashType.SHA256, HashType.SHA256, 0x20, 0xC00, RSAKeyGenParameterSpec.F4), OutputPrefixType.RAW));
                hashMap0.put("RSA_SSA_PSS_3072_SHA256_SHA256_32_F4", new KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(HashType.SHA256, HashType.SHA256, 0x20, 0xC00, RSAKeyGenParameterSpec.F4), OutputPrefixType.TINK));
                hashMap0.put("RSA_SSA_PSS_4096_SHA512_F4", new KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(HashType.SHA512, HashType.SHA512, 0x40, 0x1000, RSAKeyGenParameterSpec.F4), OutputPrefixType.TINK));
                hashMap0.put("RSA_SSA_PSS_4096_SHA512_F4_RAW", new KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(HashType.SHA512, HashType.SHA512, 0x40, 0x1000, RSAKeyGenParameterSpec.F4), OutputPrefixType.RAW));
                hashMap0.put("RSA_SSA_PSS_4096_SHA512_SHA512_64_F4", new KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(HashType.SHA512, HashType.SHA512, 0x40, 0x1000, RSAKeyGenParameterSpec.F4), OutputPrefixType.TINK));
                return Collections.unmodifiableMap(hashMap0);
            }

            public RsaSsaPssKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return RsaSsaPssKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(RsaSsaPssKeyFormat rsaSsaPssKeyFormat0) throws GeneralSecurityException {
                SigUtil.validateRsaSsaPssParams(rsaSsaPssKeyFormat0.getParams());
                Validators.validateRsaModulusSize(rsaSsaPssKeyFormat0.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPssKeyFormat0.getPublicExponent().toByteArray()));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((RsaSsaPssKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public RsaSsaPssPrivateKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return RsaSsaPssPrivateKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static final KeyTemplate rawRsa3072PssSha256F4Template() {
        return RsaSsaPssSignKeyManager.createKeyTemplate(HashType.SHA256, HashType.SHA256, 0x20, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawRsa4096PssSha512F4Template() {
        return RsaSsaPssSignKeyManager.createKeyTemplate(HashType.SHA512, HashType.SHA512, 0x40, 0x1000, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW);
    }

    public static void registerPair(boolean z) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new RsaSsaPssSignKeyManager(), new RsaSsaPssVerifyKeyManager(), z);
    }

    public static final KeyTemplate rsa3072PssSha256F4Template() {
        return RsaSsaPssSignKeyManager.createKeyTemplate(HashType.SHA256, HashType.SHA256, 0x20, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK);
    }

    public static final KeyTemplate rsa4096PssSha512F4Template() {
        return RsaSsaPssSignKeyManager.createKeyTemplate(HashType.SHA512, HashType.SHA512, 0x40, 0x1000, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK);
    }

    public void validateKey(RsaSsaPssPrivateKey rsaSsaPssPrivateKey0) throws GeneralSecurityException {
        Validators.validateVersion(rsaSsaPssPrivateKey0.getVersion(), 0);
        Validators.validateRsaModulusSize(new BigInteger(1, rsaSsaPssPrivateKey0.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPssPrivateKey0.getPublicKey().getE().toByteArray()));
        SigUtil.validateRsaSsaPssParams(rsaSsaPssPrivateKey0.getPublicKey().getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((RsaSsaPssPrivateKey)messageLite0));
    }
}

