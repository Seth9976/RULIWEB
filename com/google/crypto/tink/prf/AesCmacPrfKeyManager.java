package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesCmacPrfKey;
import com.google.crypto.tink.proto.AesCmacPrfKeyFormat;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.PrfAesCmac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesCmacPrfKeyManager extends KeyTypeManager {
    private static final int KEY_SIZE_IN_BYTES = 0x20;
    private static final int VERSION;

    AesCmacPrfKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public Prf getPrimitive(AesCmacPrfKey aesCmacPrfKey0) throws GeneralSecurityException {
                return new PrfAesCmac(aesCmacPrfKey0.getKeyValue().toByteArray());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((AesCmacPrfKey)messageLite0));
            }
        }};
        super(AesCmacPrfKey.class, arr_primitiveFactory);
    }

    // 去混淆评级： 低(20)
    public static final KeyTemplate aes256CmacTemplate() {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.AesCmacPrfKey", ((AesCmacPrfKeyFormat)AesCmacPrfKeyFormat.newBuilder().setKeySize(0x20).build()).toByteArray(), OutputPrefixType.RAW);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(AesCmacPrfKeyFormat.class) {
            public AesCmacPrfKey createKey(AesCmacPrfKeyFormat aesCmacPrfKeyFormat0) {
                return (AesCmacPrfKey)AesCmacPrfKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(Random.randBytes(aesCmacPrfKeyFormat0.getKeySize()))).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((AesCmacPrfKeyFormat)messageLite0));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("AES256_CMAC_PRF", new KeyFormat(((AesCmacPrfKeyFormat)AesCmacPrfKeyFormat.newBuilder().setKeySize(0x20).build()), OutputPrefixType.RAW));
                hashMap0.put("AES_CMAC_PRF", new KeyFormat(((AesCmacPrfKeyFormat)AesCmacPrfKeyFormat.newBuilder().setKeySize(0x20).build()), OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public AesCmacPrfKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return AesCmacPrfKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(AesCmacPrfKeyFormat aesCmacPrfKeyFormat0) throws GeneralSecurityException {
                AesCmacPrfKeyManager.validateSize(aesCmacPrfKeyFormat0.getKeySize());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((AesCmacPrfKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public AesCmacPrfKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return AesCmacPrfKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCmacPrfKeyManager(), z);
    }

    public void validateKey(AesCmacPrfKey aesCmacPrfKey0) throws GeneralSecurityException {
        Validators.validateVersion(aesCmacPrfKey0.getVersion(), 0);
        AesCmacPrfKeyManager.validateSize(aesCmacPrfKey0.getKeyValue().size());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((AesCmacPrfKey)messageLite0));
    }

    private static void validateSize(int v) throws GeneralSecurityException {
        if(v != 0x20) {
            throw new GeneralSecurityException("AesCmacPrfKey size wrong, must be 32 bytes");
        }
    }
}

