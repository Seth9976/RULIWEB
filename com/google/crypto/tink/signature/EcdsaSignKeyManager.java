package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.EcdsaKeyFormat;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaPrivateKey;
import com.google.crypto.tink.proto.EcdsaPublicKey;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EcdsaSignJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class EcdsaSignKeyManager extends PrivateKeyTypeManager {
    EcdsaSignKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public PublicKeySign getPrimitive(EcdsaPrivateKey ecdsaPrivateKey0) throws GeneralSecurityException {
                ECPrivateKey eCPrivateKey0 = EllipticCurves.getEcPrivateKey(SigUtil.toCurveType(ecdsaPrivateKey0.getPublicKey().getParams().getCurve()), ecdsaPrivateKey0.getKeyValue().toByteArray());
                SelfKeyTestValidators.validateEcdsa(eCPrivateKey0, EllipticCurves.getEcPublicKey(SigUtil.toCurveType(ecdsaPrivateKey0.getPublicKey().getParams().getCurve()), ecdsaPrivateKey0.getPublicKey().getX().toByteArray(), ecdsaPrivateKey0.getPublicKey().getY().toByteArray()), SigUtil.toHashType(ecdsaPrivateKey0.getPublicKey().getParams().getHashType()), SigUtil.toEcdsaEncoding(ecdsaPrivateKey0.getPublicKey().getParams().getEncoding()));
                return new EcdsaSignJce(eCPrivateKey0, SigUtil.toHashType(ecdsaPrivateKey0.getPublicKey().getParams().getHashType()), SigUtil.toEcdsaEncoding(ecdsaPrivateKey0.getPublicKey().getParams().getEncoding()));
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((EcdsaPrivateKey)messageLite0));
            }
        }};
        super(EcdsaPrivateKey.class, EcdsaPublicKey.class, arr_primitiveFactory);
    }

    private static KeyFormat createKeyFormat(HashType hashType0, EllipticCurveType ellipticCurveType0, EcdsaSignatureEncoding ecdsaSignatureEncoding0, OutputPrefixType keyTemplate$OutputPrefixType0) {
        EcdsaParams ecdsaParams0 = (EcdsaParams)EcdsaParams.newBuilder().setHashType(hashType0).setCurve(ellipticCurveType0).setEncoding(ecdsaSignatureEncoding0).build();
        return new KeyFormat(((EcdsaKeyFormat)EcdsaKeyFormat.newBuilder().setParams(ecdsaParams0).build()), keyTemplate$OutputPrefixType0);
    }

    public static KeyTemplate createKeyTemplate(HashType hashType0, EllipticCurveType ellipticCurveType0, EcdsaSignatureEncoding ecdsaSignatureEncoding0, OutputPrefixType keyTemplate$OutputPrefixType0) {
        EcdsaParams ecdsaParams0 = (EcdsaParams)EcdsaParams.newBuilder().setHashType(hashType0).setCurve(ellipticCurveType0).setEncoding(ecdsaSignatureEncoding0).build();
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey", ((EcdsaKeyFormat)EcdsaKeyFormat.newBuilder().setParams(ecdsaParams0).build()).toByteArray(), keyTemplate$OutputPrefixType0);
    }

    public static final KeyTemplate ecdsaP256Template() {
        return EcdsaSignKeyManager.createKeyTemplate(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.DER, OutputPrefixType.TINK);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public AlgorithmFipsCompatibility fipsStatus() {
        return AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    public EcdsaPublicKey getPublicKey(EcdsaPrivateKey ecdsaPrivateKey0) throws GeneralSecurityException {
        return ecdsaPrivateKey0.getPublicKey();
    }

    @Override  // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public MessageLite getPublicKey(MessageLite messageLite0) throws GeneralSecurityException {
        return this.getPublicKey(((EcdsaPrivateKey)messageLite0));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(EcdsaKeyFormat.class) {
            public EcdsaPrivateKey createKey(EcdsaKeyFormat ecdsaKeyFormat0) throws GeneralSecurityException {
                EcdsaParams ecdsaParams0 = ecdsaKeyFormat0.getParams();
                KeyPair keyPair0 = EllipticCurves.generateKeyPair(SigUtil.toCurveType(ecdsaParams0.getCurve()));
                ECPublicKey eCPublicKey0 = (ECPublicKey)keyPair0.getPublic();
                ECPrivateKey eCPrivateKey0 = (ECPrivateKey)keyPair0.getPrivate();
                ECPoint eCPoint0 = eCPublicKey0.getW();
                EcdsaPublicKey ecdsaPublicKey0 = (EcdsaPublicKey)EcdsaPublicKey.newBuilder().setVersion(0).setParams(ecdsaParams0).setX(ByteString.copyFrom(eCPoint0.getAffineX().toByteArray())).setY(ByteString.copyFrom(eCPoint0.getAffineY().toByteArray())).build();
                return (EcdsaPrivateKey)EcdsaPrivateKey.newBuilder().setVersion(0).setPublicKey(ecdsaPublicKey0).setKeyValue(ByteString.copyFrom(eCPrivateKey0.getS().toByteArray())).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((EcdsaKeyFormat)messageLite0));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("ECDSA_P256", EcdsaSignKeyManager.createKeyFormat(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.DER, OutputPrefixType.TINK));
                hashMap0.put("ECDSA_P256_IEEE_P1363", EcdsaSignKeyManager.createKeyFormat(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.IEEE_P1363, OutputPrefixType.TINK));
                hashMap0.put("ECDSA_P256_RAW", EcdsaSignKeyManager.createKeyFormat(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.IEEE_P1363, OutputPrefixType.RAW));
                hashMap0.put("ECDSA_P256_IEEE_P1363_WITHOUT_PREFIX", EcdsaSignKeyManager.createKeyFormat(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.IEEE_P1363, OutputPrefixType.RAW));
                hashMap0.put("ECDSA_P384", EcdsaSignKeyManager.createKeyFormat(HashType.SHA512, EllipticCurveType.NIST_P384, EcdsaSignatureEncoding.DER, OutputPrefixType.TINK));
                hashMap0.put("ECDSA_P384_IEEE_P1363", EcdsaSignKeyManager.createKeyFormat(HashType.SHA512, EllipticCurveType.NIST_P384, EcdsaSignatureEncoding.IEEE_P1363, OutputPrefixType.TINK));
                hashMap0.put("ECDSA_P384_SHA512", EcdsaSignKeyManager.createKeyFormat(HashType.SHA512, EllipticCurveType.NIST_P384, EcdsaSignatureEncoding.DER, OutputPrefixType.TINK));
                hashMap0.put("ECDSA_P384_SHA384", EcdsaSignKeyManager.createKeyFormat(HashType.SHA384, EllipticCurveType.NIST_P384, EcdsaSignatureEncoding.DER, OutputPrefixType.TINK));
                hashMap0.put("ECDSA_P521", EcdsaSignKeyManager.createKeyFormat(HashType.SHA512, EllipticCurveType.NIST_P521, EcdsaSignatureEncoding.DER, OutputPrefixType.TINK));
                hashMap0.put("ECDSA_P521_IEEE_P1363", EcdsaSignKeyManager.createKeyFormat(HashType.SHA512, EllipticCurveType.NIST_P521, EcdsaSignatureEncoding.IEEE_P1363, OutputPrefixType.TINK));
                return Collections.unmodifiableMap(hashMap0);
            }

            public EcdsaKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return EcdsaKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(EcdsaKeyFormat ecdsaKeyFormat0) throws GeneralSecurityException {
                SigUtil.validateEcdsaParams(ecdsaKeyFormat0.getParams());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((EcdsaKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public EcdsaPrivateKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return EcdsaPrivateKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static final KeyTemplate rawEcdsaP256Template() {
        return EcdsaSignKeyManager.createKeyTemplate(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.IEEE_P1363, OutputPrefixType.RAW);
    }

    public static void registerPair(boolean z) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new EcdsaSignKeyManager(), new EcdsaVerifyKeyManager(), z);
        EcdsaProtoSerialization.register();
    }

    public void validateKey(EcdsaPrivateKey ecdsaPrivateKey0) throws GeneralSecurityException {
        Validators.validateVersion(ecdsaPrivateKey0.getVersion(), 0);
        SigUtil.validateEcdsaParams(ecdsaPrivateKey0.getPublicKey().getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((EcdsaPrivateKey)messageLite0));
    }
}

