package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.mac.HmacKeyManager;
import com.google.crypto.tink.proto.AesCtrHmacAeadKey;
import com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormat;
import com.google.crypto.tink.proto.AesCtrKey;
import com.google.crypto.tink.proto.AesCtrKeyFormat;
import com.google.crypto.tink.proto.AesCtrParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKey;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.EncryptThenAuthenticate;
import com.google.crypto.tink.subtle.IndCpaCipher;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesCtrHmacAeadKeyManager extends KeyTypeManager {
    AesCtrHmacAeadKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public Aead getPrimitive(AesCtrHmacAeadKey aesCtrHmacAeadKey0) throws GeneralSecurityException {
                return new EncryptThenAuthenticate(((IndCpaCipher)new AesCtrKeyManager().getPrimitive(aesCtrHmacAeadKey0.getAesCtrKey(), IndCpaCipher.class)), ((Mac)new HmacKeyManager().getPrimitive(aesCtrHmacAeadKey0.getHmacKey(), Mac.class)), aesCtrHmacAeadKey0.getHmacKey().getParams().getTagSize());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((AesCtrHmacAeadKey)messageLite0));
            }
        }};
        super(AesCtrHmacAeadKey.class, arr_primitiveFactory);
    }

    public static final KeyTemplate aes128CtrHmacSha256Template() {
        return AesCtrHmacAeadKeyManager.createKeyTemplate(16, 16, 0x20, 16, HashType.SHA256);
    }

    public static final KeyTemplate aes256CtrHmacSha256Template() {
        return AesCtrHmacAeadKeyManager.createKeyTemplate(0x20, 16, 0x20, 0x20, HashType.SHA256);
    }

    private static KeyFormat createKeyFormat(int v, int v1, int v2, int v3, HashType hashType0, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return new KeyFormat(AesCtrHmacAeadKeyManager.createKeyFormat(v, v1, v2, v3, hashType0), keyTemplate$OutputPrefixType0);
    }

    private static AesCtrHmacAeadKeyFormat createKeyFormat(int v, int v1, int v2, int v3, HashType hashType0) {
        AesCtrKeyFormat aesCtrKeyFormat0 = (AesCtrKeyFormat)AesCtrKeyFormat.newBuilder().setParams(((AesCtrParams)AesCtrParams.newBuilder().setIvSize(v1).build())).setKeySize(v).build();
        HmacKeyFormat hmacKeyFormat0 = (HmacKeyFormat)HmacKeyFormat.newBuilder().setParams(((HmacParams)HmacParams.newBuilder().setHash(hashType0).setTagSize(v3).build())).setKeySize(v2).build();
        return (AesCtrHmacAeadKeyFormat)AesCtrHmacAeadKeyFormat.newBuilder().setAesCtrKeyFormat(aesCtrKeyFormat0).setHmacKeyFormat(hmacKeyFormat0).build();
    }

    // 去混淆评级： 低(20)
    private static KeyTemplate createKeyTemplate(int v, int v1, int v2, int v3, HashType hashType0) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey", AesCtrHmacAeadKeyManager.createKeyFormat(v, v1, v2, v3, hashType0).toByteArray(), OutputPrefixType.TINK);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public AlgorithmFipsCompatibility fipsStatus() {
        return AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(AesCtrHmacAeadKeyFormat.class) {
            public AesCtrHmacAeadKey createKey(AesCtrHmacAeadKeyFormat aesCtrHmacAeadKeyFormat0) throws GeneralSecurityException {
                AesCtrKey aesCtrKey0 = (AesCtrKey)new AesCtrKeyManager().keyFactory().createKey(aesCtrHmacAeadKeyFormat0.getAesCtrKeyFormat());
                HmacKey hmacKey0 = (HmacKey)new HmacKeyManager().keyFactory().createKey(aesCtrHmacAeadKeyFormat0.getHmacKeyFormat());
                return (AesCtrHmacAeadKey)AesCtrHmacAeadKey.newBuilder().setAesCtrKey(aesCtrKey0).setHmacKey(hmacKey0).setVersion(0).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((AesCtrHmacAeadKeyFormat)messageLite0));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("AES128_CTR_HMAC_SHA256", AesCtrHmacAeadKeyManager.createKeyFormat(16, 16, 0x20, 16, HashType.SHA256, OutputPrefixType.TINK));
                hashMap0.put("AES128_CTR_HMAC_SHA256_RAW", AesCtrHmacAeadKeyManager.createKeyFormat(16, 16, 0x20, 16, HashType.SHA256, OutputPrefixType.RAW));
                hashMap0.put("AES256_CTR_HMAC_SHA256", AesCtrHmacAeadKeyManager.createKeyFormat(0x20, 16, 0x20, 0x20, HashType.SHA256, OutputPrefixType.TINK));
                hashMap0.put("AES256_CTR_HMAC_SHA256_RAW", AesCtrHmacAeadKeyManager.createKeyFormat(0x20, 16, 0x20, 0x20, HashType.SHA256, OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public AesCtrHmacAeadKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return AesCtrHmacAeadKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(AesCtrHmacAeadKeyFormat aesCtrHmacAeadKeyFormat0) throws GeneralSecurityException {
                new AesCtrKeyManager().keyFactory().validateKeyFormat(aesCtrHmacAeadKeyFormat0.getAesCtrKeyFormat());
                new HmacKeyManager().keyFactory().validateKeyFormat(aesCtrHmacAeadKeyFormat0.getHmacKeyFormat());
                Validators.validateAesKeySize(aesCtrHmacAeadKeyFormat0.getAesCtrKeyFormat().getKeySize());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((AesCtrHmacAeadKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public AesCtrHmacAeadKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return AesCtrHmacAeadKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCtrHmacAeadKeyManager(), z);
    }

    public void validateKey(AesCtrHmacAeadKey aesCtrHmacAeadKey0) throws GeneralSecurityException {
        Validators.validateVersion(aesCtrHmacAeadKey0.getVersion(), 0);
        new AesCtrKeyManager().validateKey(aesCtrHmacAeadKey0.getAesCtrKey());
        new HmacKeyManager().validateKey(aesCtrHmacAeadKey0.getHmacKey());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((AesCtrHmacAeadKey)messageLite0));
    }
}

