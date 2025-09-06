package com.google.crypto.tink.aead;

import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesCtrKey;
import com.google.crypto.tink.proto.AesCtrKeyFormat;
import com.google.crypto.tink.proto.AesCtrParams;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.AesCtrJceCipher;
import com.google.crypto.tink.subtle.IndCpaCipher;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

public class AesCtrKeyManager extends KeyTypeManager {
    private static final int MIN_IV_SIZE_IN_BYTES = 12;

    AesCtrKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public IndCpaCipher getPrimitive(AesCtrKey aesCtrKey0) throws GeneralSecurityException {
                return new AesCtrJceCipher(aesCtrKey0.getKeyValue().toByteArray(), aesCtrKey0.getParams().getIvSize());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((AesCtrKey)messageLite0));
            }
        }};
        super(AesCtrKey.class, arr_primitiveFactory);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrKey";
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(AesCtrKeyFormat.class) {
            public AesCtrKey createKey(AesCtrKeyFormat aesCtrKeyFormat0) throws GeneralSecurityException {
                return (AesCtrKey)AesCtrKey.newBuilder().setParams(aesCtrKeyFormat0.getParams()).setKeyValue(ByteString.copyFrom(Random.randBytes(aesCtrKeyFormat0.getKeySize()))).setVersion(0).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((AesCtrKeyFormat)messageLite0));
            }

            public AesCtrKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return AesCtrKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(AesCtrKeyFormat aesCtrKeyFormat0) throws GeneralSecurityException {
                Validators.validateAesKeySize(aesCtrKeyFormat0.getKeySize());
                AesCtrParams aesCtrParams0 = aesCtrKeyFormat0.getParams();
                AesCtrKeyManager.this.validateParams(aesCtrParams0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((AesCtrKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public AesCtrKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return AesCtrKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCtrKeyManager(), z);
    }

    public void validateKey(AesCtrKey aesCtrKey0) throws GeneralSecurityException {
        Validators.validateVersion(aesCtrKey0.getVersion(), 0);
        Validators.validateAesKeySize(aesCtrKey0.getKeyValue().size());
        this.validateParams(aesCtrKey0.getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((AesCtrKey)messageLite0));
    }

    private void validateParams(AesCtrParams aesCtrParams0) throws GeneralSecurityException {
        if(aesCtrParams0.getIvSize() < 12 || aesCtrParams0.getIvSize() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }
}

