package com.google.crypto.tink.internal;

import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;

public abstract class PrimitiveFactory {
    private final Class clazz;

    public PrimitiveFactory(Class class0) {
        this.clazz = class0;
    }

    public abstract Object getPrimitive(MessageLite arg1) throws GeneralSecurityException;

    final Class getPrimitiveClass() {
        return this.clazz;
    }
}

