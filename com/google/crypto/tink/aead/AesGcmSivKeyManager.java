package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.aead.subtle.AesGcmSiv;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesGcmSivKey;
import com.google.crypto.tink.proto.AesGcmSivKeyFormat;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public final class AesGcmSivKeyManager extends KeyTypeManager {
    AesGcmSivKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public Aead getPrimitive(AesGcmSivKey aesGcmSivKey0) throws GeneralSecurityException {
                return new AesGcmSiv(aesGcmSivKey0.getKeyValue().toByteArray());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((AesGcmSivKey)messageLite0));
            }
        }};
        super(AesGcmSivKey.class, arr_primitiveFactory);
    }

    public static final KeyTemplate aes128GcmSivTemplate() {
        return AesGcmSivKeyManager.createKeyTemplate(16, OutputPrefixType.TINK);
    }

    public static final KeyTemplate aes256GcmSivTemplate() {
        return AesGcmSivKeyManager.createKeyTemplate(0x20, OutputPrefixType.TINK);
    }

    private static boolean canUseAesGcmSive() {
        try {
            Cipher.getInstance("AES/GCM-SIV/NoPadding");
            return true;
        }
        catch(NoSuchAlgorithmException | NoSuchPaddingException unused_ex) {
            return false;
        }
    }

    private static KeyFormat createKeyFormat(int v, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return new KeyFormat(((AesGcmSivKeyFormat)AesGcmSivKeyFormat.newBuilder().setKeySize(v).build()), keyTemplate$OutputPrefixType0);
    }

    // 去混淆评级： 低(20)
    private static KeyTemplate createKeyTemplate(int v, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.AesGcmSivKey", ((AesGcmSivKeyFormat)AesGcmSivKeyFormat.newBuilder().setKeySize(v).build()).toByteArray(), keyTemplate$OutputPrefixType0);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(AesGcmSivKeyFormat.class) {
            public AesGcmSivKey createKey(AesGcmSivKeyFormat aesGcmSivKeyFormat0) {
                return (AesGcmSivKey)AesGcmSivKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesGcmSivKeyFormat0.getKeySize()))).setVersion(0).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((AesGcmSivKeyFormat)messageLite0));
            }

            public AesGcmSivKey deriveKey(AesGcmSivKeyFormat aesGcmSivKeyFormat0, InputStream inputStream0) throws GeneralSecurityException {
                Validators.validateVersion(aesGcmSivKeyFormat0.getVersion(), 0);
                byte[] arr_b = new byte[aesGcmSivKeyFormat0.getKeySize()];
                try {
                    com.google.crypto.tink.aead.AesGcmSivKeyManager.2.readFully(inputStream0, arr_b);
                    return (AesGcmSivKey)AesGcmSivKey.newBuilder().setKeyValue(ByteString.copyFrom(arr_b)).setVersion(0).build();
                }
                catch(IOException iOException0) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", iOException0);
                }
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
                return this.deriveKey(((AesGcmSivKeyFormat)messageLite0), inputStream0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("AES128_GCM_SIV", AesGcmSivKeyManager.createKeyFormat(16, OutputPrefixType.TINK));
                hashMap0.put("AES128_GCM_SIV_RAW", AesGcmSivKeyManager.createKeyFormat(16, OutputPrefixType.RAW));
                hashMap0.put("AES256_GCM_SIV", AesGcmSivKeyManager.createKeyFormat(0x20, OutputPrefixType.TINK));
                hashMap0.put("AES256_GCM_SIV_RAW", AesGcmSivKeyManager.createKeyFormat(0x20, OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public AesGcmSivKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return AesGcmSivKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(AesGcmSivKeyFormat aesGcmSivKeyFormat0) throws GeneralSecurityException {
                Validators.validateAesKeySize(aesGcmSivKeyFormat0.getKeySize());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((AesGcmSivKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public AesGcmSivKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return AesGcmSivKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static final KeyTemplate rawAes128GcmSivTemplate() {
        return AesGcmSivKeyManager.createKeyTemplate(16, OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawAes256GcmSivTemplate() {
        return AesGcmSivKeyManager.createKeyTemplate(0x20, OutputPrefixType.RAW);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        if(AesGcmSivKeyManager.canUseAesGcmSive()) {
            Registry.registerKeyManager(new AesGcmSivKeyManager(), z);
            AesGcmSivProtoSerialization.register();
        }
    }

    public void validateKey(AesGcmSivKey aesGcmSivKey0) throws GeneralSecurityException {
        Validators.validateVersion(aesGcmSivKey0.getVersion(), 0);
        Validators.validateAesKeySize(aesGcmSivKey0.getKeyValue().size());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((AesGcmSivKey)messageLite0));
    }
}

