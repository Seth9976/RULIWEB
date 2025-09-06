package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.Ed25519KeyFormat;
import com.google.crypto.tink.proto.Ed25519PrivateKey;
import com.google.crypto.tink.proto.Ed25519PublicKey;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.Ed25519Sign.KeyPair;
import com.google.crypto.tink.subtle.Ed25519Sign;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Ed25519PrivateKeyManager extends PrivateKeyTypeManager {
    Ed25519PrivateKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public PublicKeySign getPrimitive(Ed25519PrivateKey ed25519PrivateKey0) throws GeneralSecurityException {
                return new Ed25519Sign(ed25519PrivateKey0.getKeyValue().toByteArray());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((Ed25519PrivateKey)messageLite0));
            }
        }};
        super(Ed25519PrivateKey.class, Ed25519PublicKey.class, arr_primitiveFactory);
    }

    // 去混淆评级： 低(20)
    public static final KeyTemplate ed25519Template() {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.Ed25519PrivateKey", new byte[0], OutputPrefixType.TINK);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    public Ed25519PublicKey getPublicKey(Ed25519PrivateKey ed25519PrivateKey0) throws GeneralSecurityException {
        return ed25519PrivateKey0.getPublicKey();
    }

    @Override  // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public MessageLite getPublicKey(MessageLite messageLite0) throws GeneralSecurityException {
        return this.getPublicKey(((Ed25519PrivateKey)messageLite0));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(Ed25519KeyFormat.class) {
            public Ed25519PrivateKey createKey(Ed25519KeyFormat ed25519KeyFormat0) throws GeneralSecurityException {
                KeyPair ed25519Sign$KeyPair0 = KeyPair.newKeyPair();
                Ed25519PublicKey ed25519PublicKey0 = (Ed25519PublicKey)Ed25519PublicKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(ed25519Sign$KeyPair0.getPublicKey())).build();
                return (Ed25519PrivateKey)Ed25519PrivateKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(ed25519Sign$KeyPair0.getPrivateKey())).setPublicKey(ed25519PublicKey0).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((Ed25519KeyFormat)messageLite0));
            }

            public Ed25519PrivateKey deriveKey(Ed25519KeyFormat ed25519KeyFormat0, InputStream inputStream0) throws GeneralSecurityException {
                Validators.validateVersion(ed25519KeyFormat0.getVersion(), 0);
                byte[] arr_b = new byte[0x20];
                try {
                    com.google.crypto.tink.signature.Ed25519PrivateKeyManager.2.readFully(inputStream0, arr_b);
                    KeyPair ed25519Sign$KeyPair0 = KeyPair.newKeyPairFromSeed(arr_b);
                    Ed25519PublicKey ed25519PublicKey0 = (Ed25519PublicKey)Ed25519PublicKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(ed25519Sign$KeyPair0.getPublicKey())).build();
                    return (Ed25519PrivateKey)Ed25519PrivateKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(ed25519Sign$KeyPair0.getPrivateKey())).setPublicKey(ed25519PublicKey0).build();
                }
                catch(IOException iOException0) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", iOException0);
                }
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
                return this.deriveKey(((Ed25519KeyFormat)messageLite0), inputStream0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("ED25519", new KeyFormat(Ed25519KeyFormat.getDefaultInstance(), OutputPrefixType.TINK));
                hashMap0.put("ED25519_RAW", new KeyFormat(Ed25519KeyFormat.getDefaultInstance(), OutputPrefixType.RAW));
                hashMap0.put("ED25519WithRawOutput", new KeyFormat(Ed25519KeyFormat.getDefaultInstance(), OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public Ed25519KeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return Ed25519KeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(Ed25519KeyFormat format) throws GeneralSecurityException {
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public Ed25519PrivateKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return Ed25519PrivateKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    // 去混淆评级： 低(20)
    public static final KeyTemplate rawEd25519Template() {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.Ed25519PrivateKey", new byte[0], OutputPrefixType.RAW);
    }

    public static void registerPair(boolean z) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new Ed25519PrivateKeyManager(), new Ed25519PublicKeyManager(), z);
    }

    public void validateKey(Ed25519PrivateKey ed25519PrivateKey0) throws GeneralSecurityException {
        Validators.validateVersion(ed25519PrivateKey0.getVersion(), 0);
        new Ed25519PublicKeyManager().validateKey(ed25519PrivateKey0.getPublicKey());
        if(ed25519PrivateKey0.getKeyValue().size() != 0x20) {
            throw new GeneralSecurityException("invalid Ed25519 private key: incorrect key length");
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((Ed25519PrivateKey)messageLite0));
    }
}

