package com.google.crypto.tink;

import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;

class KeyManagerImpl implements KeyManager {
    static class KeyFactoryHelper {
        final KeyFactory keyFactory;

        KeyFactoryHelper(KeyFactory keyTypeManager$KeyFactory0) {
            this.keyFactory = keyTypeManager$KeyFactory0;
        }

        MessageLite castValidateCreate(MessageLite messageLite0) throws GeneralSecurityException {
            return this.validateCreate(((MessageLite)KeyManagerImpl.castOrThrowSecurityException(messageLite0, "Expected proto of type " + this.keyFactory.getKeyFormatClass().getName(), this.keyFactory.getKeyFormatClass())));
        }

        MessageLite parseValidateCreate(ByteString byteString0) throws GeneralSecurityException, InvalidProtocolBufferException {
            return this.validateCreate(this.keyFactory.parseKeyFormat(byteString0));
        }

        private MessageLite validateCreate(MessageLite messageLite0) throws GeneralSecurityException {
            this.keyFactory.validateKeyFormat(messageLite0);
            return this.keyFactory.createKey(messageLite0);
        }
    }

    private final KeyTypeManager keyTypeManager;
    private final Class primitiveClass;

    public KeyManagerImpl(KeyTypeManager keyTypeManager0, Class class0) {
        if(!keyTypeManager0.supportedPrimitives().contains(class0) && !Void.class.equals(class0)) {
            throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", keyTypeManager0.toString(), class0.getName()));
        }
        this.keyTypeManager = keyTypeManager0;
        this.primitiveClass = class0;
    }

    private static Object castOrThrowSecurityException(Object object0, String s, Class class0) throws GeneralSecurityException {
        if(!class0.isInstance(object0)) {
            throw new GeneralSecurityException(s);
        }
        return object0;
    }

    @Override  // com.google.crypto.tink.KeyManager
    public final boolean doesSupport(String s) {
        return s.equals(this.getKeyType());
    }

    @Override  // com.google.crypto.tink.KeyManager
    public final String getKeyType() {
        return this.keyTypeManager.getKeyType();
    }

    @Override  // com.google.crypto.tink.KeyManager
    public final Object getPrimitive(ByteString byteString0) throws GeneralSecurityException {
        try {
            return this.validateKeyAndGetPrimitive(this.keyTypeManager.parseKey(byteString0));
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw new GeneralSecurityException("Failures parsing proto of type " + this.keyTypeManager.getKeyClass().getName(), invalidProtocolBufferException0);
        }
    }

    @Override  // com.google.crypto.tink.KeyManager
    public final Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
        return this.validateKeyAndGetPrimitive(((MessageLite)KeyManagerImpl.castOrThrowSecurityException(messageLite0, "Expected proto of type " + this.keyTypeManager.getKeyClass().getName(), this.keyTypeManager.getKeyClass())));
    }

    @Override  // com.google.crypto.tink.KeyManager
    public final Class getPrimitiveClass() {
        return this.primitiveClass;
    }

    @Override  // com.google.crypto.tink.KeyManager
    public int getVersion() {
        return this.keyTypeManager.getVersion();
    }

    private KeyFactoryHelper keyFactoryHelper() {
        return new KeyFactoryHelper(this.keyTypeManager.keyFactory());
    }

    @Override  // com.google.crypto.tink.KeyManager
    public final MessageLite newKey(ByteString byteString0) throws GeneralSecurityException {
        try {
            return this.keyFactoryHelper().parseValidateCreate(byteString0);
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw new GeneralSecurityException("Failures parsing proto of type " + this.keyTypeManager.keyFactory().getKeyFormatClass().getName(), invalidProtocolBufferException0);
        }
    }

    @Override  // com.google.crypto.tink.KeyManager
    public final MessageLite newKey(MessageLite messageLite0) throws GeneralSecurityException {
        return this.keyFactoryHelper().castValidateCreate(messageLite0);
    }

    @Override  // com.google.crypto.tink.KeyManager
    public final KeyData newKeyData(ByteString byteString0) throws GeneralSecurityException {
        try {
            MessageLite messageLite0 = this.keyFactoryHelper().parseValidateCreate(byteString0);
            return (KeyData)KeyData.newBuilder().setTypeUrl(this.getKeyType()).setValue(messageLite0.toByteString()).setKeyMaterialType(this.keyTypeManager.keyMaterialType()).build();
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw new GeneralSecurityException("Unexpected proto", invalidProtocolBufferException0);
        }
    }

    private Object validateKeyAndGetPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
        if(Void.class.equals(this.primitiveClass)) {
            throw new GeneralSecurityException("Cannot create a primitive for Void");
        }
        this.keyTypeManager.validateKey(messageLite0);
        return this.keyTypeManager.getPrimitive(messageLite0, this.primitiveClass);
    }
}

