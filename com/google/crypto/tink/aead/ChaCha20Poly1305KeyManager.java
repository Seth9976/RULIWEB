package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.ChaCha20Poly1305Key;
import com.google.crypto.tink.proto.ChaCha20Poly1305KeyFormat;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.ChaCha20Poly1305;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChaCha20Poly1305KeyManager extends KeyTypeManager {
    private static final int KEY_SIZE_IN_BYTES = 0x20;

    ChaCha20Poly1305KeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public Aead getPrimitive(ChaCha20Poly1305Key chaCha20Poly1305Key0) throws GeneralSecurityException {
                return new ChaCha20Poly1305(chaCha20Poly1305Key0.getKeyValue().toByteArray());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((ChaCha20Poly1305Key)messageLite0));
            }
        }};
        super(ChaCha20Poly1305Key.class, arr_primitiveFactory);
    }

    // 去混淆评级： 低(20)
    public static final KeyTemplate chaCha20Poly1305Template() {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key", ChaCha20Poly1305KeyFormat.getDefaultInstance().toByteArray(), OutputPrefixType.TINK);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(ChaCha20Poly1305KeyFormat.class) {
            // 去混淆评级： 低(20)
            public ChaCha20Poly1305Key createKey(ChaCha20Poly1305KeyFormat chaCha20Poly1305KeyFormat0) throws GeneralSecurityException {
                return (ChaCha20Poly1305Key)ChaCha20Poly1305Key.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(new byte[]{-55, 80, 94, -57, -99, -97, 51, 0x60, (byte)0x91, 0x7C, 53, 20, 0x70, -51, (byte)0xC0, -5, -26, 51, (byte)0x8D, -75, 35, -70, (byte)0x8F, 6, -82, -80, 39, 15, (byte)0x90, 35, 58, -97})).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((ChaCha20Poly1305KeyFormat)messageLite0));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("CHACHA20_POLY1305", new KeyFormat(ChaCha20Poly1305KeyFormat.getDefaultInstance(), OutputPrefixType.TINK));
                hashMap0.put("CHACHA20_POLY1305_RAW", new KeyFormat(ChaCha20Poly1305KeyFormat.getDefaultInstance(), OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public ChaCha20Poly1305KeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return ChaCha20Poly1305KeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(ChaCha20Poly1305KeyFormat format) throws GeneralSecurityException {
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public ChaCha20Poly1305Key parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return ChaCha20Poly1305Key.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    // 去混淆评级： 低(20)
    public static final KeyTemplate rawChaCha20Poly1305Template() {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key", ChaCha20Poly1305KeyFormat.getDefaultInstance().toByteArray(), OutputPrefixType.RAW);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new ChaCha20Poly1305KeyManager(), z);
        ChaCha20Poly1305ProtoSerialization.register();
    }

    public void validateKey(ChaCha20Poly1305Key chaCha20Poly1305Key0) throws GeneralSecurityException {
        Validators.validateVersion(chaCha20Poly1305Key0.getVersion(), 0);
        if(chaCha20Poly1305Key0.getKeyValue().size() != 0x20) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((ChaCha20Poly1305Key)messageLite0));
    }
}

