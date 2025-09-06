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
import com.google.crypto.tink.proto.KmsEnvelopeAeadKey;
import com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

public class KmsEnvelopeAeadKeyManager extends KeyTypeManager {
    KmsEnvelopeAeadKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            // 去混淆评级： 中等(60)
            public Aead getPrimitive(KmsEnvelopeAeadKey kmsEnvelopeAeadKey0) throws GeneralSecurityException {
                return new KmsEnvelopeAead(kmsEnvelopeAeadKey0.getParams().getDekTemplate(), KmsClients.get("").getAead(""));
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((KmsEnvelopeAeadKey)messageLite0));
            }
        }};
        super(KmsEnvelopeAeadKey.class, arr_primitiveFactory);
    }

    static KmsEnvelopeAeadKeyFormat createKeyFormat(String s, KeyTemplate keyTemplate0) {
        return (KmsEnvelopeAeadKeyFormat)KmsEnvelopeAeadKeyFormat.newBuilder().setDekTemplate(((com.google.crypto.tink.proto.KeyTemplate)com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(keyTemplate0.getTypeUrl()).setValue(ByteString.copyFrom(keyTemplate0.getValue())).build())).setKekUri(s).build();
    }

    // 去混淆评级： 低(20)
    public static KeyTemplate createKeyTemplate(String s, KeyTemplate keyTemplate0) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey", KmsEnvelopeAeadKeyManager.createKeyFormat(s, keyTemplate0).toByteArray(), OutputPrefixType.RAW);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(KmsEnvelopeAeadKeyFormat.class) {
            public KmsEnvelopeAeadKey createKey(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat0) throws GeneralSecurityException {
                return (KmsEnvelopeAeadKey)KmsEnvelopeAeadKey.newBuilder().setParams(kmsEnvelopeAeadKeyFormat0).setVersion(0).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((KmsEnvelopeAeadKeyFormat)messageLite0));
            }

            public KmsEnvelopeAeadKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return KmsEnvelopeAeadKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            // 去混淆评级： 中等(50)
            public void validateKeyFormat(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat0) throws GeneralSecurityException {
                throw new GeneralSecurityException("invalid key format: missing KEK URI or DEK template");
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((KmsEnvelopeAeadKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.REMOTE;
    }

    public KmsEnvelopeAeadKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return KmsEnvelopeAeadKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new KmsEnvelopeAeadKeyManager(), z);
    }

    public void validateKey(KmsEnvelopeAeadKey kmsEnvelopeAeadKey0) throws GeneralSecurityException {
        Validators.validateVersion(kmsEnvelopeAeadKey0.getVersion(), 0);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((KmsEnvelopeAeadKey)messageLite0));
    }
}

