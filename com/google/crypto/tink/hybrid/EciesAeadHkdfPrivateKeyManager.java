package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.aead.AesCtrHmacAeadKeyManager;
import com.google.crypto.tink.aead.AesGcmKeyManager;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadDemParams;
import com.google.crypto.tink.proto.EciesAeadHkdfKeyFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EciesAeadHkdfPrivateKey;
import com.google.crypto.tink.proto.EciesAeadHkdfPublicKey;
import com.google.crypto.tink.proto.EciesHkdfKemParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.EciesAeadHkdfHybridDecrypt;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class EciesAeadHkdfPrivateKeyManager extends PrivateKeyTypeManager {
    private static final byte[] EMPTY_SALT;

    static {
        EciesAeadHkdfPrivateKeyManager.EMPTY_SALT = new byte[0];
    }

    EciesAeadHkdfPrivateKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public HybridDecrypt getPrimitive(EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey0) throws GeneralSecurityException {
                EciesAeadHkdfParams eciesAeadHkdfParams0 = eciesAeadHkdfPrivateKey0.getPublicKey().getParams();
                EciesHkdfKemParams eciesHkdfKemParams0 = eciesAeadHkdfParams0.getKemParams();
                ECPrivateKey eCPrivateKey0 = EllipticCurves.getEcPrivateKey(HybridUtil.toCurveType(eciesHkdfKemParams0.getCurveType()), eciesAeadHkdfPrivateKey0.getKeyValue().toByteArray());
                RegistryEciesAeadHkdfDemHelper registryEciesAeadHkdfDemHelper0 = new RegistryEciesAeadHkdfDemHelper(eciesAeadHkdfParams0.getDemParams().getAeadDem());
                return new EciesAeadHkdfHybridDecrypt(eCPrivateKey0, eciesHkdfKemParams0.getHkdfSalt().toByteArray(), HybridUtil.toHmacAlgo(eciesHkdfKemParams0.getHkdfHashType()), HybridUtil.toPointFormatType(eciesAeadHkdfParams0.getEcPointFormat()), registryEciesAeadHkdfDemHelper0);
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((EciesAeadHkdfPrivateKey)messageLite0));
            }
        }};
        super(EciesAeadHkdfPrivateKey.class, EciesAeadHkdfPublicKey.class, arr_primitiveFactory);
    }

    static byte[] access$000() [...] // 潜在的解密器

    private static KeyFormat createKeyFormat(EllipticCurveType ellipticCurveType0, HashType hashType0, EcPointFormat ecPointFormat0, KeyTemplate keyTemplate0, byte[] arr_b, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return new KeyFormat(((EciesAeadHkdfKeyFormat)EciesAeadHkdfKeyFormat.newBuilder().setParams(EciesAeadHkdfPrivateKeyManager.createParams(ellipticCurveType0, hashType0, ecPointFormat0, keyTemplate0, arr_b)).build()), keyTemplate$OutputPrefixType0);
    }

    // 去混淆评级： 低(20)
    private static KeyTemplate createKeyTemplate(EllipticCurveType ellipticCurveType0, HashType hashType0, EcPointFormat ecPointFormat0, KeyTemplate keyTemplate0, OutputPrefixType keyTemplate$OutputPrefixType0, byte[] arr_b) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", ((EciesAeadHkdfKeyFormat)EciesAeadHkdfKeyFormat.newBuilder().setParams(EciesAeadHkdfPrivateKeyManager.createParams(ellipticCurveType0, hashType0, ecPointFormat0, keyTemplate0, arr_b)).build()).toByteArray(), keyTemplate$OutputPrefixType0);
    }

    static EciesAeadHkdfParams createParams(EllipticCurveType ellipticCurveType0, HashType hashType0, EcPointFormat ecPointFormat0, KeyTemplate keyTemplate0, byte[] arr_b) {
        EciesHkdfKemParams eciesHkdfKemParams0 = (EciesHkdfKemParams)EciesHkdfKemParams.newBuilder().setCurveType(ellipticCurveType0).setHkdfHashType(hashType0).setHkdfSalt(ByteString.copyFrom(arr_b)).build();
        com.google.crypto.tink.proto.KeyTemplate keyTemplate1 = (com.google.crypto.tink.proto.KeyTemplate)com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(keyTemplate0.getTypeUrl()).setValue(ByteString.copyFrom(keyTemplate0.getValue())).setOutputPrefixType(EciesAeadHkdfPrivateKeyManager.toProto(keyTemplate0.getOutputPrefixType())).build();
        EciesAeadDemParams eciesAeadDemParams0 = (EciesAeadDemParams)EciesAeadDemParams.newBuilder().setAeadDem(keyTemplate1).build();
        return (EciesAeadHkdfParams)EciesAeadHkdfParams.newBuilder().setKemParams(eciesHkdfKemParams0).setDemParams(eciesAeadDemParams0).setEcPointFormat(ecPointFormat0).build();
    }

    public static final KeyTemplate eciesP256HkdfHmacSha256Aes128CtrHmacSha256Template() {
        KeyTemplate keyTemplate0 = AesCtrHmacAeadKeyManager.aes128CtrHmacSha256Template();
        return EciesAeadHkdfPrivateKeyManager.createKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, keyTemplate0, OutputPrefixType.TINK, EciesAeadHkdfPrivateKeyManager.EMPTY_SALT);
    }

    public static final KeyTemplate eciesP256HkdfHmacSha256Aes128GcmTemplate() {
        KeyTemplate keyTemplate0 = AesGcmKeyManager.aes128GcmTemplate();
        return EciesAeadHkdfPrivateKeyManager.createKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, keyTemplate0, OutputPrefixType.TINK, EciesAeadHkdfPrivateKeyManager.EMPTY_SALT);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    public EciesAeadHkdfPublicKey getPublicKey(EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey0) throws GeneralSecurityException {
        return eciesAeadHkdfPrivateKey0.getPublicKey();
    }

    @Override  // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public MessageLite getPublicKey(MessageLite messageLite0) throws GeneralSecurityException {
        return this.getPublicKey(((EciesAeadHkdfPrivateKey)messageLite0));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(EciesAeadHkdfKeyFormat.class) {
            public EciesAeadHkdfPrivateKey createKey(EciesAeadHkdfKeyFormat eciesAeadHkdfKeyFormat0) throws GeneralSecurityException {
                KeyPair keyPair0 = EllipticCurves.generateKeyPair(HybridUtil.toCurveType(eciesAeadHkdfKeyFormat0.getParams().getKemParams().getCurveType()));
                ECPublicKey eCPublicKey0 = (ECPublicKey)keyPair0.getPublic();
                ECPrivateKey eCPrivateKey0 = (ECPrivateKey)keyPair0.getPrivate();
                ECPoint eCPoint0 = eCPublicKey0.getW();
                EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey0 = (EciesAeadHkdfPublicKey)EciesAeadHkdfPublicKey.newBuilder().setVersion(0).setParams(eciesAeadHkdfKeyFormat0.getParams()).setX(ByteString.copyFrom(eCPoint0.getAffineX().toByteArray())).setY(ByteString.copyFrom(eCPoint0.getAffineY().toByteArray())).build();
                return (EciesAeadHkdfPrivateKey)EciesAeadHkdfPrivateKey.newBuilder().setVersion(0).setPublicKey(eciesAeadHkdfPublicKey0).setKeyValue(ByteString.copyFrom(eCPrivateKey0.getS().toByteArray())).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((EciesAeadHkdfKeyFormat)messageLite0));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                KeyTemplate keyTemplate0 = KeyTemplates.get("AES128_GCM");
                hashMap0.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM", EciesAeadHkdfPrivateKeyManager.createKeyFormat(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, keyTemplate0, new byte[0], OutputPrefixType.TINK));
                KeyTemplate keyTemplate1 = KeyTemplates.get("AES128_GCM");
                hashMap0.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_RAW", EciesAeadHkdfPrivateKeyManager.createKeyFormat(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, keyTemplate1, new byte[0], OutputPrefixType.RAW));
                KeyTemplate keyTemplate2 = KeyTemplates.get("AES128_GCM");
                hashMap0.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM", EciesAeadHkdfPrivateKeyManager.createKeyFormat(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, keyTemplate2, new byte[0], OutputPrefixType.TINK));
                KeyTemplate keyTemplate3 = KeyTemplates.get("AES128_GCM");
                hashMap0.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM_RAW", EciesAeadHkdfPrivateKeyManager.createKeyFormat(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, keyTemplate3, new byte[0], OutputPrefixType.RAW));
                KeyTemplate keyTemplate4 = KeyTemplates.get("AES128_GCM");
                hashMap0.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX", EciesAeadHkdfPrivateKeyManager.createKeyFormat(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, keyTemplate4, new byte[0], OutputPrefixType.RAW));
                KeyTemplate keyTemplate5 = KeyTemplates.get("AES128_CTR_HMAC_SHA256");
                hashMap0.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", EciesAeadHkdfPrivateKeyManager.createKeyFormat(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, keyTemplate5, new byte[0], OutputPrefixType.TINK));
                KeyTemplate keyTemplate6 = KeyTemplates.get("AES128_CTR_HMAC_SHA256");
                hashMap0.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", EciesAeadHkdfPrivateKeyManager.createKeyFormat(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, keyTemplate6, new byte[0], OutputPrefixType.RAW));
                KeyTemplate keyTemplate7 = KeyTemplates.get("AES128_CTR_HMAC_SHA256");
                hashMap0.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", EciesAeadHkdfPrivateKeyManager.createKeyFormat(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, keyTemplate7, new byte[0], OutputPrefixType.TINK));
                KeyTemplate keyTemplate8 = KeyTemplates.get("AES128_CTR_HMAC_SHA256");
                hashMap0.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", EciesAeadHkdfPrivateKeyManager.createKeyFormat(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, keyTemplate8, new byte[0], OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public EciesAeadHkdfKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return EciesAeadHkdfKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(EciesAeadHkdfKeyFormat eciesAeadHkdfKeyFormat0) throws GeneralSecurityException {
                HybridUtil.validate(eciesAeadHkdfKeyFormat0.getParams());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((EciesAeadHkdfKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public EciesAeadHkdfPrivateKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return EciesAeadHkdfPrivateKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static final KeyTemplate rawEciesP256HkdfHmacSha256Aes128CtrHmacSha256CompressedTemplate() {
        KeyTemplate keyTemplate0 = AesCtrHmacAeadKeyManager.aes128CtrHmacSha256Template();
        return EciesAeadHkdfPrivateKeyManager.createKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, keyTemplate0, OutputPrefixType.RAW, EciesAeadHkdfPrivateKeyManager.EMPTY_SALT);
    }

    public static final KeyTemplate rawEciesP256HkdfHmacSha256Aes128GcmCompressedTemplate() {
        KeyTemplate keyTemplate0 = AesGcmKeyManager.aes128GcmTemplate();
        return EciesAeadHkdfPrivateKeyManager.createKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, keyTemplate0, OutputPrefixType.RAW, EciesAeadHkdfPrivateKeyManager.EMPTY_SALT);
    }

    public static void registerPair(boolean z) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new EciesAeadHkdfPrivateKeyManager(), new EciesAeadHkdfPublicKeyManager(), z);
    }

    private static com.google.crypto.tink.proto.OutputPrefixType toProto(OutputPrefixType keyTemplate$OutputPrefixType0) {
        switch(com.google.crypto.tink.hybrid.EciesAeadHkdfPrivateKeyManager.3.$SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[keyTemplate$OutputPrefixType0.ordinal()]) {
            case 1: {
                return com.google.crypto.tink.proto.OutputPrefixType.TINK;
            }
            case 2: {
                return com.google.crypto.tink.proto.OutputPrefixType.LEGACY;
            }
            case 3: {
                return com.google.crypto.tink.proto.OutputPrefixType.RAW;
            }
            case 4: {
                return com.google.crypto.tink.proto.OutputPrefixType.CRUNCHY;
            }
            default: {
                throw new IllegalArgumentException("Unknown output prefix type");
            }
        }
    }

    public void validateKey(EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey0) throws GeneralSecurityException {
        if(eciesAeadHkdfPrivateKey0.getKeyValue().isEmpty()) {
            throw new GeneralSecurityException("invalid ECIES private key");
        }
        Validators.validateVersion(eciesAeadHkdfPrivateKey0.getVersion(), 0);
        HybridUtil.validate(eciesAeadHkdfPrivateKey0.getPublicKey().getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((EciesAeadHkdfPrivateKey)messageLite0));
    }
}

