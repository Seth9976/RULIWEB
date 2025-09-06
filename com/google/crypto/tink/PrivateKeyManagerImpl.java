package com.google.crypto.tink;

import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;

class PrivateKeyManagerImpl extends KeyManagerImpl implements PrivateKeyManager {
    private final PrivateKeyTypeManager privateKeyManager;
    private final KeyTypeManager publicKeyManager;

    public PrivateKeyManagerImpl(PrivateKeyTypeManager privateKeyTypeManager0, KeyTypeManager keyTypeManager0, Class class0) {
        super(privateKeyTypeManager0, class0);
        this.privateKeyManager = privateKeyTypeManager0;
        this.publicKeyManager = keyTypeManager0;
    }

    @Override  // com.google.crypto.tink.PrivateKeyManager
    public KeyData getPublicKeyData(ByteString byteString0) throws GeneralSecurityException {
        try {
            MessageLite messageLite0 = this.privateKeyManager.parseKey(byteString0);
            this.privateKeyManager.validateKey(messageLite0);
            MessageLite messageLite1 = this.privateKeyManager.getPublicKey(messageLite0);
            this.publicKeyManager.validateKey(messageLite1);
            return (KeyData)KeyData.newBuilder().setTypeUrl(this.publicKeyManager.getKeyType()).setValue(messageLite1.toByteString()).setKeyMaterialType(this.publicKeyManager.keyMaterialType()).build();
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw new GeneralSecurityException("expected serialized proto of type ", invalidProtocolBufferException0);
        }
    }
}

