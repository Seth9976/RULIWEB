package com.google.crypto.tink.signature;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.Ed25519PublicKey;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.Ed25519Verify;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

class Ed25519PublicKeyManager extends KeyTypeManager {
    public Ed25519PublicKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public PublicKeyVerify getPrimitive(Ed25519PublicKey ed25519PublicKey0) {
                return new Ed25519Verify(ed25519PublicKey0.getKeyValue().toByteArray());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((Ed25519PublicKey)messageLite0));
            }
        }};
        super(Ed25519PublicKey.class, arr_primitiveFactory);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    public Ed25519PublicKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return Ed25519PublicKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public void validateKey(Ed25519PublicKey ed25519PublicKey0) throws GeneralSecurityException {
        Validators.validateVersion(ed25519PublicKey0.getVersion(), 0);
        if(ed25519PublicKey0.getKeyValue().size() != 0x20) {
            throw new GeneralSecurityException("invalid Ed25519 public key: incorrect key length");
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((Ed25519PublicKey)messageLite0));
    }
}

