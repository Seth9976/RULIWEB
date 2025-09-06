package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKey;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormat;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.AesGcmHkdfStreaming;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesGcmHkdfStreamingKeyManager extends KeyTypeManager {
    private static final int NONCE_PREFIX_IN_BYTES = 7;
    private static final int TAG_SIZE_IN_BYTES = 16;

    AesGcmHkdfStreamingKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public StreamingAead getPrimitive(AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey0) throws GeneralSecurityException {
                return new AesGcmHkdfStreaming(aesGcmHkdfStreamingKey0.getKeyValue().toByteArray(), StreamingAeadUtil.toHmacAlgo(aesGcmHkdfStreamingKey0.getParams().getHkdfHashType()), aesGcmHkdfStreamingKey0.getParams().getDerivedKeySize(), aesGcmHkdfStreamingKey0.getParams().getCiphertextSegmentSize(), 0);
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((AesGcmHkdfStreamingKey)messageLite0));
            }
        }};
        super(AesGcmHkdfStreamingKey.class, arr_primitiveFactory);
    }

    public static final KeyTemplate aes128GcmHkdf1MBTemplate() {
        return AesGcmHkdfStreamingKeyManager.createKeyTemplate(16, HashType.SHA256, 16, 0x100000);
    }

    public static final KeyTemplate aes128GcmHkdf4KBTemplate() {
        return AesGcmHkdfStreamingKeyManager.createKeyTemplate(16, HashType.SHA256, 16, 0x1000);
    }

    public static final KeyTemplate aes256GcmHkdf1MBTemplate() {
        return AesGcmHkdfStreamingKeyManager.createKeyTemplate(0x20, HashType.SHA256, 0x20, 0x100000);
    }

    public static final KeyTemplate aes256GcmHkdf4KBTemplate() {
        return AesGcmHkdfStreamingKeyManager.createKeyTemplate(0x20, HashType.SHA256, 0x20, 0x1000);
    }

    private static AesGcmHkdfStreamingKeyFormat createKeyFormat(int v, HashType hashType0, int v1, int v2) {
        AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams0 = (AesGcmHkdfStreamingParams)AesGcmHkdfStreamingParams.newBuilder().setCiphertextSegmentSize(v2).setDerivedKeySize(v1).setHkdfHashType(hashType0).build();
        return (AesGcmHkdfStreamingKeyFormat)AesGcmHkdfStreamingKeyFormat.newBuilder().setKeySize(v).setParams(aesGcmHkdfStreamingParams0).build();
    }

    // 去混淆评级： 低(20)
    private static KeyTemplate createKeyTemplate(int v, HashType hashType0, int v1, int v2) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.AesGcmHkdfStreamingKey", AesGcmHkdfStreamingKeyManager.createKeyFormat(v, hashType0, v1, v2).toByteArray(), OutputPrefixType.RAW);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(AesGcmHkdfStreamingKeyFormat.class) {
            public AesGcmHkdfStreamingKey createKey(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat0) throws GeneralSecurityException {
                return (AesGcmHkdfStreamingKey)AesGcmHkdfStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesGcmHkdfStreamingKeyFormat0.getKeySize()))).setParams(aesGcmHkdfStreamingKeyFormat0.getParams()).setVersion(0).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((AesGcmHkdfStreamingKeyFormat)messageLite0));
            }

            public AesGcmHkdfStreamingKey deriveKey(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat0, InputStream inputStream0) throws GeneralSecurityException {
                Validators.validateVersion(aesGcmHkdfStreamingKeyFormat0.getVersion(), 0);
                byte[] arr_b = new byte[aesGcmHkdfStreamingKeyFormat0.getKeySize()];
                try {
                    com.google.crypto.tink.streamingaead.AesGcmHkdfStreamingKeyManager.2.readFully(inputStream0, arr_b);
                    return (AesGcmHkdfStreamingKey)AesGcmHkdfStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(arr_b)).setParams(aesGcmHkdfStreamingKeyFormat0.getParams()).setVersion(0).build();
                }
                catch(IOException iOException0) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", iOException0);
                }
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
                return this.deriveKey(((AesGcmHkdfStreamingKeyFormat)messageLite0), inputStream0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("AES128_GCM_HKDF_4KB", new KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(16, HashType.SHA256, 16, 0x1000), OutputPrefixType.RAW));
                hashMap0.put("AES128_GCM_HKDF_1MB", new KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(16, HashType.SHA256, 16, 0x100000), OutputPrefixType.RAW));
                hashMap0.put("AES256_GCM_HKDF_4KB", new KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(0x20, HashType.SHA256, 0x20, 0x1000), OutputPrefixType.RAW));
                hashMap0.put("AES256_GCM_HKDF_1MB", new KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(0x20, HashType.SHA256, 0x20, 0x100000), OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public AesGcmHkdfStreamingKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return AesGcmHkdfStreamingKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat0) throws GeneralSecurityException {
                if(aesGcmHkdfStreamingKeyFormat0.getKeySize() < 16) {
                    throw new GeneralSecurityException("key_size must be at least 16 bytes");
                }
                AesGcmHkdfStreamingKeyManager.validateParams(aesGcmHkdfStreamingKeyFormat0.getParams());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((AesGcmHkdfStreamingKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public AesGcmHkdfStreamingKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return AesGcmHkdfStreamingKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesGcmHkdfStreamingKeyManager(), z);
    }

    public void validateKey(AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey0) throws GeneralSecurityException {
        Validators.validateVersion(aesGcmHkdfStreamingKey0.getVersion(), 0);
        AesGcmHkdfStreamingKeyManager.validateParams(aesGcmHkdfStreamingKey0.getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((AesGcmHkdfStreamingKey)messageLite0));
    }

    private static void validateParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams0) throws GeneralSecurityException {
        Validators.validateAesKeySize(aesGcmHkdfStreamingParams0.getDerivedKeySize());
        if(aesGcmHkdfStreamingParams0.getHkdfHashType() == HashType.UNKNOWN_HASH) {
            throw new GeneralSecurityException("unknown HKDF hash type");
        }
        if(aesGcmHkdfStreamingParams0.getCiphertextSegmentSize() < aesGcmHkdfStreamingParams0.getDerivedKeySize() + 25) {
            throw new GeneralSecurityException("ciphertext_segment_size must be at least (derived_key_size + NONCE_PREFIX_IN_BYTES + TAG_SIZE_IN_BYTES + 2)");
        }
    }
}

