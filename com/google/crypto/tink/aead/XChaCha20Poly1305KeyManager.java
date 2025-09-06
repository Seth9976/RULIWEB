package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.XChaCha20Poly1305Key;
import com.google.crypto.tink.proto.XChaCha20Poly1305KeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.Validators;
import com.google.crypto.tink.subtle.XChaCha20Poly1305;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class XChaCha20Poly1305KeyManager extends KeyTypeManager {
    private static final int KEY_SIZE_IN_BYTES = 0x20;

    XChaCha20Poly1305KeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public Aead getPrimitive(XChaCha20Poly1305Key xChaCha20Poly1305Key0) throws GeneralSecurityException {
                return new XChaCha20Poly1305(xChaCha20Poly1305Key0.getKeyValue().toByteArray());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((XChaCha20Poly1305Key)messageLite0));
            }
        }};
        super(XChaCha20Poly1305Key.class, arr_primitiveFactory);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(XChaCha20Poly1305KeyFormat.class) {
            // 去混淆评级： 低(20)
            public XChaCha20Poly1305Key createKey(XChaCha20Poly1305KeyFormat xChaCha20Poly1305KeyFormat0) throws GeneralSecurityException {
                return (XChaCha20Poly1305Key)XChaCha20Poly1305Key.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(new byte[]{(byte)0x91, 38, -54, 0, -10, 76, 0x60, -57, -91, -15, 6, 93, -7, -2, 0x75, (byte)0xA1, 84, -17, 13, -13, 29, 0x6F, -89, -6, (byte)0xA1, -15, 0x7B, 93, -42, -27, 76, 16})).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((XChaCha20Poly1305KeyFormat)messageLite0));
            }

            public XChaCha20Poly1305Key deriveKey(XChaCha20Poly1305KeyFormat xChaCha20Poly1305KeyFormat0, InputStream inputStream0) throws GeneralSecurityException {
                Validators.validateVersion(xChaCha20Poly1305KeyFormat0.getVersion(), 0);
                byte[] arr_b = new byte[0x20];
                try {
                    com.google.crypto.tink.aead.XChaCha20Poly1305KeyManager.2.readFully(inputStream0, arr_b);
                    return (XChaCha20Poly1305Key)XChaCha20Poly1305Key.newBuilder().setKeyValue(ByteString.copyFrom(arr_b)).setVersion(0).build();
                }
                catch(IOException iOException0) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", iOException0);
                }
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
                return this.deriveKey(((XChaCha20Poly1305KeyFormat)messageLite0), inputStream0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("XCHACHA20_POLY1305", new KeyFormat(XChaCha20Poly1305KeyFormat.getDefaultInstance(), OutputPrefixType.TINK));
                hashMap0.put("XCHACHA20_POLY1305_RAW", new KeyFormat(XChaCha20Poly1305KeyFormat.getDefaultInstance(), OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public XChaCha20Poly1305KeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return XChaCha20Poly1305KeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(XChaCha20Poly1305KeyFormat format) throws GeneralSecurityException {
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

    public XChaCha20Poly1305Key parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return XChaCha20Poly1305Key.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    // 去混淆评级： 低(20)
    public static final KeyTemplate rawXChaCha20Poly1305Template() {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key", XChaCha20Poly1305KeyFormat.getDefaultInstance().toByteArray(), OutputPrefixType.RAW);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new XChaCha20Poly1305KeyManager(), z);
        XChaCha20Poly1305ProtoSerialization.register();
    }

    public void validateKey(XChaCha20Poly1305Key xChaCha20Poly1305Key0) throws GeneralSecurityException {
        Validators.validateVersion(xChaCha20Poly1305Key0.getVersion(), 0);
        if(xChaCha20Poly1305Key0.getKeyValue().size() != 0x20) {
            throw new GeneralSecurityException("invalid XChaCha20Poly1305Key: incorrect key length");
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((XChaCha20Poly1305Key)messageLite0));
    }

    // 去混淆评级： 低(20)
    public static final KeyTemplate xChaCha20Poly1305Template() {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key", XChaCha20Poly1305KeyFormat.getDefaultInstance().toByteArray(), OutputPrefixType.TINK);
    }
}

