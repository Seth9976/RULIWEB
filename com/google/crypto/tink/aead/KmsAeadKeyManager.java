package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KmsClients;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.KmsAeadKey;
import com.google.crypto.tink.proto.KmsAeadKeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

public class KmsAeadKeyManager extends KeyTypeManager {
    KmsAeadKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            // 去混淆评级： 中等(60)
            public Aead getPrimitive(KmsAeadKey kmsAeadKey0) throws GeneralSecurityException {
                return KmsClients.get("").getAead("");
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((KmsAeadKey)messageLite0));
            }
        }};
        super(KmsAeadKey.class, arr_primitiveFactory);
    }

    static KmsAeadKeyFormat createKeyFormat(String s) {
        return (KmsAeadKeyFormat)KmsAeadKeyFormat.newBuilder().setKeyUri(s).build();
    }

    // 去混淆评级： 低(20)
    public static KeyTemplate createKeyTemplate(String s) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.KmsAeadKey", KmsAeadKeyManager.createKeyFormat(s).toByteArray(), OutputPrefixType.RAW);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(KmsAeadKeyFormat.class) {
            public KmsAeadKey createKey(KmsAeadKeyFormat kmsAeadKeyFormat0) throws GeneralSecurityException {
                return (KmsAeadKey)KmsAeadKey.newBuilder().setParams(kmsAeadKeyFormat0).setVersion(0).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((KmsAeadKeyFormat)messageLite0));
            }

            public KmsAeadKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return KmsAeadKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(KmsAeadKeyFormat format) throws GeneralSecurityException {
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.REMOTE;
    }

    public KmsAeadKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return KmsAeadKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new KmsAeadKeyManager(), z);
    }

    public void validateKey(KmsAeadKey kmsAeadKey0) throws GeneralSecurityException {
        Validators.validateVersion(kmsAeadKey0.getVersion(), 0);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((KmsAeadKey)messageLite0));
    }
}

