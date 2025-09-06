package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesCtrHmacStreamingKey;
import com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormat;
import com.google.crypto.tink.proto.AesCtrHmacStreamingParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.AesCtrHmacStreaming;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesCtrHmacStreamingKeyManager extends KeyTypeManager {
    private static final int MIN_TAG_SIZE_IN_BYTES = 10;
    private static final int NONCE_PREFIX_IN_BYTES = 7;

    AesCtrHmacStreamingKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public StreamingAead getPrimitive(AesCtrHmacStreamingKey aesCtrHmacStreamingKey0) throws GeneralSecurityException {
                return new AesCtrHmacStreaming(aesCtrHmacStreamingKey0.getKeyValue().toByteArray(), StreamingAeadUtil.toHmacAlgo(aesCtrHmacStreamingKey0.getParams().getHkdfHashType()), aesCtrHmacStreamingKey0.getParams().getDerivedKeySize(), StreamingAeadUtil.toHmacAlgo(aesCtrHmacStreamingKey0.getParams().getHmacParams().getHash()), aesCtrHmacStreamingKey0.getParams().getHmacParams().getTagSize(), aesCtrHmacStreamingKey0.getParams().getCiphertextSegmentSize(), 0);
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((AesCtrHmacStreamingKey)messageLite0));
            }
        }};
        super(AesCtrHmacStreamingKey.class, arr_primitiveFactory);
    }

    public static final KeyTemplate aes128CtrHmacSha2561MBTemplate() {
        return AesCtrHmacStreamingKeyManager.createKeyTemplate(16, HashType.SHA256, 16, HashType.SHA256, 0x20, 0x100000);
    }

    public static final KeyTemplate aes128CtrHmacSha2564KBTemplate() {
        return AesCtrHmacStreamingKeyManager.createKeyTemplate(16, HashType.SHA256, 16, HashType.SHA256, 0x20, 0x1000);
    }

    public static final KeyTemplate aes256CtrHmacSha2561MBTemplate() {
        return AesCtrHmacStreamingKeyManager.createKeyTemplate(0x20, HashType.SHA256, 0x20, HashType.SHA256, 0x20, 0x100000);
    }

    public static final KeyTemplate aes256CtrHmacSha2564KBTemplate() {
        return AesCtrHmacStreamingKeyManager.createKeyTemplate(0x20, HashType.SHA256, 0x20, HashType.SHA256, 0x20, 0x1000);
    }

    private static AesCtrHmacStreamingKeyFormat createKeyFormat(int v, HashType hashType0, int v1, HashType hashType1, int v2, int v3) {
        HmacParams hmacParams0 = (HmacParams)HmacParams.newBuilder().setHash(hashType1).setTagSize(v2).build();
        AesCtrHmacStreamingParams aesCtrHmacStreamingParams0 = (AesCtrHmacStreamingParams)AesCtrHmacStreamingParams.newBuilder().setCiphertextSegmentSize(v3).setDerivedKeySize(v1).setHkdfHashType(hashType0).setHmacParams(hmacParams0).build();
        return (AesCtrHmacStreamingKeyFormat)AesCtrHmacStreamingKeyFormat.newBuilder().setParams(aesCtrHmacStreamingParams0).setKeySize(v).build();
    }

    // 去混淆评级： 低(20)
    private static KeyTemplate createKeyTemplate(int v, HashType hashType0, int v1, HashType hashType1, int v2, int v3) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.AesCtrHmacStreamingKey", AesCtrHmacStreamingKeyManager.createKeyFormat(v, hashType0, v1, hashType1, v2, v3).toByteArray(), OutputPrefixType.RAW);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(AesCtrHmacStreamingKeyFormat.class) {
            public AesCtrHmacStreamingKey createKey(AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat0) throws GeneralSecurityException {
                return (AesCtrHmacStreamingKey)AesCtrHmacStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesCtrHmacStreamingKeyFormat0.getKeySize()))).setParams(aesCtrHmacStreamingKeyFormat0.getParams()).setVersion(0).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((AesCtrHmacStreamingKeyFormat)messageLite0));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("AES128_CTR_HMAC_SHA256_4KB", new KeyFormat(AesCtrHmacStreamingKeyManager.createKeyFormat(16, HashType.SHA256, 16, HashType.SHA256, 0x20, 0x1000), OutputPrefixType.RAW));
                hashMap0.put("AES128_CTR_HMAC_SHA256_1MB", new KeyFormat(AesCtrHmacStreamingKeyManager.createKeyFormat(16, HashType.SHA256, 16, HashType.SHA256, 0x20, 0x100000), OutputPrefixType.RAW));
                hashMap0.put("AES256_CTR_HMAC_SHA256_4KB", new KeyFormat(AesCtrHmacStreamingKeyManager.createKeyFormat(0x20, HashType.SHA256, 0x20, HashType.SHA256, 0x20, 0x1000), OutputPrefixType.RAW));
                hashMap0.put("AES256_CTR_HMAC_SHA256_1MB", new KeyFormat(AesCtrHmacStreamingKeyManager.createKeyFormat(0x20, HashType.SHA256, 0x20, HashType.SHA256, 0x20, 0x100000), OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public AesCtrHmacStreamingKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return AesCtrHmacStreamingKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat0) throws GeneralSecurityException {
                if(aesCtrHmacStreamingKeyFormat0.getKeySize() < 16) {
                    throw new GeneralSecurityException("key_size must be at least 16 bytes");
                }
                AesCtrHmacStreamingKeyManager.validateParams(aesCtrHmacStreamingKeyFormat0.getParams());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((AesCtrHmacStreamingKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public AesCtrHmacStreamingKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return AesCtrHmacStreamingKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCtrHmacStreamingKeyManager(), z);
    }

    private static void validateHmacParams(HmacParams hmacParams0) throws GeneralSecurityException {
        if(hmacParams0.getTagSize() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        switch(com.google.crypto.tink.streamingaead.AesCtrHmacStreamingKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType[hmacParams0.getHash().ordinal()]) {
            case 1: {
                if(hmacParams0.getTagSize() > 20) {
                    throw new GeneralSecurityException("tag size too big");
                }
                break;
            }
            case 2: {
                if(hmacParams0.getTagSize() > 0x20) {
                    throw new GeneralSecurityException("tag size too big");
                }
                break;
            }
            case 3: {
                if(hmacParams0.getTagSize() > 0x40) {
                    throw new GeneralSecurityException("tag size too big");
                }
                break;
            }
            default: {
                throw new GeneralSecurityException("unknown hash type");
            }
        }
    }

    public void validateKey(AesCtrHmacStreamingKey aesCtrHmacStreamingKey0) throws GeneralSecurityException {
        Validators.validateVersion(aesCtrHmacStreamingKey0.getVersion(), 0);
        if(aesCtrHmacStreamingKey0.getKeyValue().size() < 16) {
            throw new GeneralSecurityException("key_value must have at least 16 bytes");
        }
        int v = aesCtrHmacStreamingKey0.getParams().getDerivedKeySize();
        if(aesCtrHmacStreamingKey0.getKeyValue().size() < v) {
            throw new GeneralSecurityException("key_value must have at least as many bits as derived keys");
        }
        AesCtrHmacStreamingKeyManager.validateParams(aesCtrHmacStreamingKey0.getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((AesCtrHmacStreamingKey)messageLite0));
    }

    private static void validateParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams0) throws GeneralSecurityException {
        Validators.validateAesKeySize(aesCtrHmacStreamingParams0.getDerivedKeySize());
        if(aesCtrHmacStreamingParams0.getHkdfHashType() != HashType.SHA1 && aesCtrHmacStreamingParams0.getHkdfHashType() != HashType.SHA256 && aesCtrHmacStreamingParams0.getHkdfHashType() != HashType.SHA512) {
            throw new GeneralSecurityException("Invalid HKDF hash type: " + aesCtrHmacStreamingParams0.getHkdfHashType().getNumber());
        }
        if(aesCtrHmacStreamingParams0.getHmacParams().getHash() == HashType.UNKNOWN_HASH) {
            throw new GeneralSecurityException("unknown HMAC hash type");
        }
        AesCtrHmacStreamingKeyManager.validateHmacParams(aesCtrHmacStreamingParams0.getHmacParams());
        if(aesCtrHmacStreamingParams0.getCiphertextSegmentSize() < aesCtrHmacStreamingParams0.getDerivedKeySize() + aesCtrHmacStreamingParams0.getHmacParams().getTagSize() + 9) {
            throw new GeneralSecurityException("ciphertext_segment_size must be at least (derived_key_size + tag_size + NONCE_PREFIX_IN_BYTES + 2)");
        }
    }
}

