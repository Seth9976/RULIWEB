package com.google.crypto.tink.internal;

import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;

public abstract class PrivateKeyTypeManager extends KeyTypeManager {
    private final Class publicKeyClazz;

    @SafeVarargs
    protected PrivateKeyTypeManager(Class class0, Class class1, PrimitiveFactory[] arr_primitiveFactory) {
        super(class0, arr_primitiveFactory);
        this.publicKeyClazz = class1;
    }

    public abstract MessageLite getPublicKey(MessageLite arg1) throws GeneralSecurityException;

    public final Class getPublicKeyClass() {
        return this.publicKeyClazz;
    }
}

