package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesCmacKeyFormat;
import com.google.crypto.tink.proto.AesCmacParams;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.PrfAesCmac;
import com.google.crypto.tink.subtle.PrfMac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesCmacKeyManager extends KeyTypeManager {
    private static final PrimitiveConstructor CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR = null;
    private static final int KEY_SIZE_IN_BYTES = 0x20;
    private static final int MAX_TAG_SIZE_IN_BYTES = 16;
    private static final int MIN_TAG_SIZE_IN_BYTES = 10;
    private static final int VERSION;

    static {
        AesCmacKeyManager.CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(new AesCmacKeyManager..ExternalSyntheticLambda0(), AesCmacKey.class, ChunkedMac.class);
    }

    AesCmacKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public Mac getPrimitive(com.google.crypto.tink.proto.AesCmacKey aesCmacKey0) throws GeneralSecurityException {
                return new PrfMac(new PrfAesCmac(aesCmacKey0.getKeyValue().toByteArray()), aesCmacKey0.getParams().getTagSize());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((com.google.crypto.tink.proto.AesCmacKey)messageLite0));
            }
        }};
        super(com.google.crypto.tink.proto.AesCmacKey.class, arr_primitiveFactory);
    }

    // 去混淆评级： 低(20)
    public static final KeyTemplate aes256CmacTemplate() {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.AesCmacKey", ((AesCmacKeyFormat)AesCmacKeyFormat.newBuilder().setKeySize(0x20).setParams(((AesCmacParams)AesCmacParams.newBuilder().setTagSize(16).build())).build()).toByteArray(), OutputPrefixType.TINK);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(AesCmacKeyFormat.class) {
            public com.google.crypto.tink.proto.AesCmacKey createKey(AesCmacKeyFormat aesCmacKeyFormat0) throws GeneralSecurityException {
                return (com.google.crypto.tink.proto.AesCmacKey)com.google.crypto.tink.proto.AesCmacKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(Random.randBytes(aesCmacKeyFormat0.getKeySize()))).setParams(aesCmacKeyFormat0.getParams()).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((AesCmacKeyFormat)messageLite0));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("AES_CMAC", new KeyFormat(((AesCmacKeyFormat)AesCmacKeyFormat.newBuilder().setKeySize(0x20).setParams(((AesCmacParams)AesCmacParams.newBuilder().setTagSize(16).build())).build()), OutputPrefixType.TINK));
                hashMap0.put("AES256_CMAC", new KeyFormat(((AesCmacKeyFormat)AesCmacKeyFormat.newBuilder().setKeySize(0x20).setParams(((AesCmacParams)AesCmacParams.newBuilder().setTagSize(16).build())).build()), OutputPrefixType.TINK));
                hashMap0.put("AES256_CMAC_RAW", new KeyFormat(((AesCmacKeyFormat)AesCmacKeyFormat.newBuilder().setKeySize(0x20).setParams(((AesCmacParams)AesCmacParams.newBuilder().setTagSize(16).build())).build()), OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public AesCmacKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return AesCmacKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(AesCmacKeyFormat aesCmacKeyFormat0) throws GeneralSecurityException {
                AesCmacKeyManager.validateParams(aesCmacKeyFormat0.getParams());
                AesCmacKeyManager.validateSize(aesCmacKeyFormat0.getKeySize());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((AesCmacKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public com.google.crypto.tink.proto.AesCmacKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return com.google.crypto.tink.proto.AesCmacKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    // 去混淆评级： 低(20)
    public static final KeyTemplate rawAes256CmacTemplate() {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.AesCmacKey", ((AesCmacKeyFormat)AesCmacKeyFormat.newBuilder().setKeySize(0x20).setParams(((AesCmacParams)AesCmacParams.newBuilder().setTagSize(16).build())).build()).toByteArray(), OutputPrefixType.RAW);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCmacKeyManager(), z);
        AesCmacProtoSerialization.register();
        MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(AesCmacKeyManager.CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR);
    }

    public void validateKey(com.google.crypto.tink.proto.AesCmacKey aesCmacKey0) throws GeneralSecurityException {
        Validators.validateVersion(aesCmacKey0.getVersion(), 0);
        AesCmacKeyManager.validateSize(aesCmacKey0.getKeyValue().size());
        AesCmacKeyManager.validateParams(aesCmacKey0.getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((com.google.crypto.tink.proto.AesCmacKey)messageLite0));
    }

    private static void validateParams(AesCmacParams aesCmacParams0) throws GeneralSecurityException {
        if(aesCmacParams0.getTagSize() < 10) {
            throw new GeneralSecurityException("tag size too short");
        }
        if(aesCmacParams0.getTagSize() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    private static void validateSize(int v) throws GeneralSecurityException {
        if(v != 0x20) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }
}

