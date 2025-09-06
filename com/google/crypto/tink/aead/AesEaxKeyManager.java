package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesEaxKey;
import com.google.crypto.tink.proto.AesEaxKeyFormat;
import com.google.crypto.tink.proto.AesEaxParams;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.AesEaxJce;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesEaxKeyManager extends KeyTypeManager {
    AesEaxKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public Aead getPrimitive(AesEaxKey aesEaxKey0) throws GeneralSecurityException {
                return new AesEaxJce(aesEaxKey0.getKeyValue().toByteArray(), aesEaxKey0.getParams().getIvSize());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((AesEaxKey)messageLite0));
            }
        }};
        super(AesEaxKey.class, arr_primitiveFactory);
    }

    public static final KeyTemplate aes128EaxTemplate() {
        return AesEaxKeyManager.createKeyTemplate(16, 16, OutputPrefixType.TINK);
    }

    public static final KeyTemplate aes256EaxTemplate() {
        return AesEaxKeyManager.createKeyTemplate(0x20, 16, OutputPrefixType.TINK);
    }

    private static KeyFormat createKeyFormat(int v, int v1, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return new KeyFormat(((AesEaxKeyFormat)AesEaxKeyFormat.newBuilder().setKeySize(v).setParams(((AesEaxParams)AesEaxParams.newBuilder().setIvSize(v1).build())).build()), keyTemplate$OutputPrefixType0);
    }

    // 去混淆评级： 低(20)
    private static KeyTemplate createKeyTemplate(int v, int v1, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.AesEaxKey", ((AesEaxKeyFormat)AesEaxKeyFormat.newBuilder().setKeySize(v).setParams(((AesEaxParams)AesEaxParams.newBuilder().setIvSize(v1).build())).build()).toByteArray(), keyTemplate$OutputPrefixType0);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(AesEaxKeyFormat.class) {
            public AesEaxKey createKey(AesEaxKeyFormat aesEaxKeyFormat0) throws GeneralSecurityException {
                return (AesEaxKey)AesEaxKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesEaxKeyFormat0.getKeySize()))).setParams(aesEaxKeyFormat0.getParams()).setVersion(0).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((AesEaxKeyFormat)messageLite0));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("AES128_EAX", AesEaxKeyManager.createKeyFormat(16, 16, OutputPrefixType.TINK));
                hashMap0.put("AES128_EAX_RAW", AesEaxKeyManager.createKeyFormat(16, 16, OutputPrefixType.RAW));
                hashMap0.put("AES256_EAX", AesEaxKeyManager.createKeyFormat(0x20, 16, OutputPrefixType.TINK));
                hashMap0.put("AES256_EAX_RAW", AesEaxKeyManager.createKeyFormat(0x20, 16, OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public AesEaxKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return AesEaxKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(AesEaxKeyFormat aesEaxKeyFormat0) throws GeneralSecurityException {
                Validators.validateAesKeySize(aesEaxKeyFormat0.getKeySize());
                switch(aesEaxKeyFormat0.getParams().getIvSize()) {
                    case 12: 
                    case 16: {
                        return;
                    }
                    default: {
                        throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
                    }
                }
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((AesEaxKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public AesEaxKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return AesEaxKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static final KeyTemplate rawAes128EaxTemplate() {
        return AesEaxKeyManager.createKeyTemplate(16, 16, OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawAes256EaxTemplate() {
        return AesEaxKeyManager.createKeyTemplate(0x20, 16, OutputPrefixType.RAW);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesEaxKeyManager(), z);
        AesEaxProtoSerialization.register();
    }

    public void validateKey(AesEaxKey aesEaxKey0) throws GeneralSecurityException {
        Validators.validateVersion(aesEaxKey0.getVersion(), 0);
        Validators.validateAesKeySize(aesEaxKey0.getKeyValue().size());
        switch(aesEaxKey0.getParams().getIvSize()) {
            case 12: 
            case 16: {
                return;
            }
            default: {
                throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
            }
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((AesEaxKey)messageLite0));
    }
}

