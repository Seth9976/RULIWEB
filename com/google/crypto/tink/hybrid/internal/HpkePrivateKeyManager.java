package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.HpkeAead;
import com.google.crypto.tink.proto.HpkeKdf;
import com.google.crypto.tink.proto.HpkeKem;
import com.google.crypto.tink.proto.HpkeKeyFormat;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.proto.HpkePrivateKey;
import com.google.crypto.tink.proto.HpkePublicKey;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.EllipticCurves.CurveType;
import com.google.crypto.tink.subtle.EllipticCurves.PointFormatType;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import com.google.crypto.tink.subtle.X25519;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class HpkePrivateKeyManager extends PrivateKeyTypeManager {
    public HpkePrivateKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public HybridDecrypt getPrimitive(HpkePrivateKey hpkePrivateKey0) throws GeneralSecurityException {
                return HpkeDecrypt.createHpkeDecrypt(hpkePrivateKey0);
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((HpkePrivateKey)messageLite0));
            }
        }};
        super(HpkePrivateKey.class, HpkePublicKey.class, arr_primitiveFactory);
    }

    private static KeyFormat createKeyFormat(HpkeKem hpkeKem0, HpkeKdf hpkeKdf0, HpkeAead hpkeAead0, OutputPrefixType keyTemplate$OutputPrefixType0) {
        HpkeParams hpkeParams0 = (HpkeParams)HpkeParams.newBuilder().setKem(hpkeKem0).setKdf(hpkeKdf0).setAead(hpkeAead0).build();
        return new KeyFormat(((HpkeKeyFormat)HpkeKeyFormat.newBuilder().setParams(hpkeParams0).build()), keyTemplate$OutputPrefixType0);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HpkePrivateKey";
    }

    public HpkePublicKey getPublicKey(HpkePrivateKey hpkePrivateKey0) {
        return hpkePrivateKey0.getPublicKey();
    }

    @Override  // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public MessageLite getPublicKey(MessageLite messageLite0) throws GeneralSecurityException {
        return this.getPublicKey(((HpkePrivateKey)messageLite0));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(HpkeKeyFormat.class) {
            public HpkePrivateKey createKey(HpkeKeyFormat hpkeKeyFormat0) throws GeneralSecurityException {
                byte[] arr_b1;
                byte[] arr_b;
                switch(com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[hpkeKeyFormat0.getParams().getKem().ordinal()]) {
                    case 1: {
                        arr_b = new byte[]{(byte)0x87, 20, -78, -59, 77, -37, 43, 2, 0x7A, 67, -46, -37, -55, -74, 4, 72, (byte)0x81, -26, (byte)0x8D, -20, 8, (byte)0x82, 9, 0x75, -10, 2, -25, -90, -67, 45, (byte)0x8C, -72};
                        arr_b1 = X25519.publicFromPrivate(arr_b);
                        break;
                    }
                    case 2: 
                    case 3: 
                    case 4: {
                        CurveType ellipticCurves$CurveType0 = HpkeUtil.nistHpkeKemToCurve(hpkeKeyFormat0.getParams().getKem());
                        KeyPair keyPair0 = EllipticCurves.generateKeyPair(ellipticCurves$CurveType0);
                        ECPoint eCPoint0 = ((ECPublicKey)keyPair0.getPublic()).getW();
                        arr_b1 = EllipticCurves.pointEncode(ellipticCurves$CurveType0, PointFormatType.UNCOMPRESSED, eCPoint0);
                        arr_b = ((ECPrivateKey)keyPair0.getPrivate()).getS().toByteArray();
                        break;
                    }
                    default: {
                        throw new GeneralSecurityException("Invalid KEM");
                    }
                }
                HpkePublicKey hpkePublicKey0 = (HpkePublicKey)HpkePublicKey.newBuilder().setVersion(0).setParams(hpkeKeyFormat0.getParams()).setPublicKey(ByteString.copyFrom(arr_b1)).build();
                return (HpkePrivateKey)HpkePrivateKey.newBuilder().setVersion(0).setPublicKey(hpkePublicKey0).setPrivateKey(ByteString.copyFrom(arr_b)).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((HpkeKeyFormat)messageLite0));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_X25519_HKDF_SHA256, HpkeKdf.HKDF_SHA256, HpkeAead.AES_128_GCM, OutputPrefixType.TINK));
                hashMap0.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_X25519_HKDF_SHA256, HpkeKdf.HKDF_SHA256, HpkeAead.AES_128_GCM, OutputPrefixType.RAW));
                hashMap0.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_X25519_HKDF_SHA256, HpkeKdf.HKDF_SHA256, HpkeAead.AES_256_GCM, OutputPrefixType.TINK));
                hashMap0.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_X25519_HKDF_SHA256, HpkeKdf.HKDF_SHA256, HpkeAead.AES_256_GCM, OutputPrefixType.RAW));
                hashMap0.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_X25519_HKDF_SHA256, HpkeKdf.HKDF_SHA256, HpkeAead.CHACHA20_POLY1305, OutputPrefixType.TINK));
                hashMap0.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305_RAW", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_X25519_HKDF_SHA256, HpkeKdf.HKDF_SHA256, HpkeAead.CHACHA20_POLY1305, OutputPrefixType.RAW));
                hashMap0.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_P256_HKDF_SHA256, HpkeKdf.HKDF_SHA256, HpkeAead.AES_128_GCM, OutputPrefixType.TINK));
                hashMap0.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_P256_HKDF_SHA256, HpkeKdf.HKDF_SHA256, HpkeAead.AES_128_GCM, OutputPrefixType.RAW));
                hashMap0.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_P256_HKDF_SHA256, HpkeKdf.HKDF_SHA256, HpkeAead.AES_256_GCM, OutputPrefixType.TINK));
                hashMap0.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_P256_HKDF_SHA256, HpkeKdf.HKDF_SHA256, HpkeAead.AES_256_GCM, OutputPrefixType.RAW));
                hashMap0.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_P384_HKDF_SHA384, HpkeKdf.HKDF_SHA384, HpkeAead.AES_128_GCM, OutputPrefixType.TINK));
                hashMap0.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_P384_HKDF_SHA384, HpkeKdf.HKDF_SHA384, HpkeAead.AES_128_GCM, OutputPrefixType.RAW));
                hashMap0.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_P384_HKDF_SHA384, HpkeKdf.HKDF_SHA384, HpkeAead.AES_256_GCM, OutputPrefixType.TINK));
                hashMap0.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_P384_HKDF_SHA384, HpkeKdf.HKDF_SHA384, HpkeAead.AES_256_GCM, OutputPrefixType.RAW));
                hashMap0.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_P521_HKDF_SHA512, HpkeKdf.HKDF_SHA512, HpkeAead.AES_128_GCM, OutputPrefixType.TINK));
                hashMap0.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_P521_HKDF_SHA512, HpkeKdf.HKDF_SHA512, HpkeAead.AES_128_GCM, OutputPrefixType.RAW));
                hashMap0.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_P521_HKDF_SHA512, HpkeKdf.HKDF_SHA512, HpkeAead.AES_256_GCM, OutputPrefixType.TINK));
                hashMap0.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(HpkeKem.DHKEM_P521_HKDF_SHA512, HpkeKdf.HKDF_SHA512, HpkeAead.AES_256_GCM, OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public HpkeKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return HpkeKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(HpkeKeyFormat hpkeKeyFormat0) throws GeneralSecurityException {
                HpkeUtil.validateParams(hpkeKeyFormat0.getParams());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((HpkeKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public HpkePrivateKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return HpkePrivateKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void registerPair(boolean z) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new HpkePrivateKeyManager(), new HpkePublicKeyManager(), z);
    }

    public void validateKey(HpkePrivateKey hpkePrivateKey0) throws GeneralSecurityException {
        if(hpkePrivateKey0.getPrivateKey().isEmpty()) {
            throw new GeneralSecurityException("Private key is empty.");
        }
        if(!hpkePrivateKey0.hasPublicKey()) {
            throw new GeneralSecurityException("Missing public key.");
        }
        Validators.validateVersion(hpkePrivateKey0.getVersion(), 0);
        HpkeUtil.validateParams(hpkePrivateKey0.getPublicKey().getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((HpkePrivateKey)messageLite0));
    }

    class com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager.3 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$HpkeKem;

        static {
            int[] arr_v = new int[HpkeKem.values().length];
            com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HpkeKem = arr_v;
            try {
                arr_v[HpkeKem.DHKEM_X25519_HKDF_SHA256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[HpkeKem.DHKEM_P256_HKDF_SHA256.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[HpkeKem.DHKEM_P384_HKDF_SHA384.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[HpkeKem.DHKEM_P521_HKDF_SHA512.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

