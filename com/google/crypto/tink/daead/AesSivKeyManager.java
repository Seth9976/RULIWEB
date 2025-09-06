package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesSivKey;
import com.google.crypto.tink.proto.AesSivKeyFormat;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.AesSiv;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AesSivKeyManager extends KeyTypeManager {
    private static final int KEY_SIZE_IN_BYTES = 0x40;

    AesSivKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public DeterministicAead getPrimitive(AesSivKey aesSivKey0) throws GeneralSecurityException {
                return new AesSiv(aesSivKey0.getKeyValue().toByteArray());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((AesSivKey)messageLite0));
            }
        }};
        super(AesSivKey.class, arr_primitiveFactory);
    }

    public static final KeyTemplate aes256SivTemplate() {
        return AesSivKeyManager.createKeyTemplate(0x40, OutputPrefixType.TINK);
    }

    // 去混淆评级： 低(20)
    private static KeyTemplate createKeyTemplate(int v, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.AesSivKey", ((AesSivKeyFormat)AesSivKeyFormat.newBuilder().setKeySize(v).build()).toByteArray(), keyTemplate$OutputPrefixType0);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(AesSivKeyFormat.class) {
            public AesSivKey createKey(AesSivKeyFormat aesSivKeyFormat0) throws GeneralSecurityException {
                return (AesSivKey)AesSivKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesSivKeyFormat0.getKeySize()))).setVersion(0).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((AesSivKeyFormat)messageLite0));
            }

            public AesSivKey deriveKey(AesSivKeyFormat aesSivKeyFormat0, InputStream inputStream0) throws GeneralSecurityException {
                Validators.validateVersion(aesSivKeyFormat0.getVersion(), 0);
                byte[] arr_b = new byte[0x40];
                try {
                    com.google.crypto.tink.daead.AesSivKeyManager.2.readFully(inputStream0, arr_b);
                    return (AesSivKey)AesSivKey.newBuilder().setKeyValue(ByteString.copyFrom(arr_b)).setVersion(0).build();
                }
                catch(IOException iOException0) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", iOException0);
                }
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
                return this.deriveKey(((AesSivKeyFormat)messageLite0), inputStream0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("AES256_SIV", new KeyFormat(((AesSivKeyFormat)AesSivKeyFormat.newBuilder().setKeySize(0x40).build()), OutputPrefixType.TINK));
                hashMap0.put("AES256_SIV_RAW", new KeyFormat(((AesSivKeyFormat)AesSivKeyFormat.newBuilder().setKeySize(0x40).build()), OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public AesSivKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return AesSivKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(AesSivKeyFormat aesSivKeyFormat0) throws GeneralSecurityException {
                if(aesSivKeyFormat0.getKeySize() != 0x40) {
                    throw new InvalidAlgorithmParameterException("invalid key size: " + aesSivKeyFormat0.getKeySize() + ". Valid keys must have 64 bytes.");
                }
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((AesSivKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public AesSivKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return AesSivKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static final KeyTemplate rawAes256SivTemplate() {
        return AesSivKeyManager.createKeyTemplate(0x40, OutputPrefixType.RAW);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesSivKeyManager(), z);
    }

    public void validateKey(AesSivKey aesSivKey0) throws GeneralSecurityException {
        Validators.validateVersion(aesSivKey0.getVersion(), 0);
        if(aesSivKey0.getKeyValue().size() != 0x40) {
            throw new InvalidKeyException("invalid key size: " + aesSivKey0.getKeyValue().size() + ". Valid keys must have 64 bytes.");
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((AesSivKey)messageLite0));
    }
}

