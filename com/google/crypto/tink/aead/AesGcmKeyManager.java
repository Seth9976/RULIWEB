package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesGcmKey;
import com.google.crypto.tink.proto.AesGcmKeyFormat;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.AesGcmJce;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesGcmKeyManager extends KeyTypeManager {
    AesGcmKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public Aead getPrimitive(AesGcmKey aesGcmKey0) throws GeneralSecurityException {
                return new AesGcmJce(aesGcmKey0.getKeyValue().toByteArray());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((AesGcmKey)messageLite0));
            }
        }};
        super(AesGcmKey.class, arr_primitiveFactory);
    }

    public static final KeyTemplate aes128GcmTemplate() {
        return AesGcmKeyManager.createKeyTemplate(16, OutputPrefixType.TINK);
    }

    public static final KeyTemplate aes256GcmTemplate() {
        return AesGcmKeyManager.createKeyTemplate(0x20, OutputPrefixType.TINK);
    }

    private static KeyFormat createKeyFormat(int v, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return new KeyFormat(((AesGcmKeyFormat)AesGcmKeyFormat.newBuilder().setKeySize(v).build()), keyTemplate$OutputPrefixType0);
    }

    // 去混淆评级： 低(20)
    private static KeyTemplate createKeyTemplate(int v, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.AesGcmKey", ((AesGcmKeyFormat)AesGcmKeyFormat.newBuilder().setKeySize(v).build()).toByteArray(), keyTemplate$OutputPrefixType0);
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
        return new KeyFactory(AesGcmKeyFormat.class) {
            public AesGcmKey createKey(AesGcmKeyFormat aesGcmKeyFormat0) throws GeneralSecurityException {
                return (AesGcmKey)AesGcmKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesGcmKeyFormat0.getKeySize()))).setVersion(0).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((AesGcmKeyFormat)messageLite0));
            }

            public AesGcmKey deriveKey(AesGcmKeyFormat aesGcmKeyFormat0, InputStream inputStream0) throws GeneralSecurityException {
                Validators.validateVersion(aesGcmKeyFormat0.getVersion(), 0);
                byte[] arr_b = new byte[aesGcmKeyFormat0.getKeySize()];
                try {
                    com.google.crypto.tink.aead.AesGcmKeyManager.2.readFully(inputStream0, arr_b);
                    return (AesGcmKey)AesGcmKey.newBuilder().setKeyValue(ByteString.copyFrom(arr_b)).setVersion(0).build();
                }
                catch(IOException iOException0) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", iOException0);
                }
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
                return this.deriveKey(((AesGcmKeyFormat)messageLite0), inputStream0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("AES128_GCM", AesGcmKeyManager.createKeyFormat(16, OutputPrefixType.TINK));
                hashMap0.put("AES128_GCM_RAW", AesGcmKeyManager.createKeyFormat(16, OutputPrefixType.RAW));
                hashMap0.put("AES256_GCM", AesGcmKeyManager.createKeyFormat(0x20, OutputPrefixType.TINK));
                hashMap0.put("AES256_GCM_RAW", AesGcmKeyManager.createKeyFormat(0x20, OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public AesGcmKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return AesGcmKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(AesGcmKeyFormat aesGcmKeyFormat0) throws GeneralSecurityException {
                Validators.validateAesKeySize(aesGcmKeyFormat0.getKeySize());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((AesGcmKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public AesGcmKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return AesGcmKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static final KeyTemplate rawAes128GcmTemplate() {
        return AesGcmKeyManager.createKeyTemplate(16, OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawAes256GcmTemplate() {
        return AesGcmKeyManager.createKeyTemplate(0x20, OutputPrefixType.RAW);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesGcmKeyManager(), z);
        AesGcmProtoSerialization.register();
    }

    public void validateKey(AesGcmKey aesGcmKey0) throws GeneralSecurityException {
        Validators.validateVersion(aesGcmKey0.getVersion(), 0);
        Validators.validateAesKeySize(aesGcmKey0.getKeyValue().size());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((AesGcmKey)messageLite0));
    }
}

