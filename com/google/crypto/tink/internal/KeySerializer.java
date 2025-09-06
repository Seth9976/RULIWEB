package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

public abstract class KeySerializer {
    public interface KeySerializationFunction {
        Serialization serializeKey(Key arg1, @Nullable SecretKeyAccess arg2) throws GeneralSecurityException;
    }

    private final Class keyClass;
    private final Class serializationClass;

    private KeySerializer(Class class0, Class class1) {
        this.keyClass = class0;
        this.serializationClass = class1;
    }

    KeySerializer(Class class0, Class class1, com.google.crypto.tink.internal.KeySerializer.1 keySerializer$10) {
        this(class0, class1);
    }

    public static KeySerializer create(KeySerializationFunction keySerializer$KeySerializationFunction0, Class class0, Class class1) {
        return new KeySerializer(class0, class1, keySerializer$KeySerializationFunction0) {
            final KeySerializationFunction val$function;

            {
                this.val$function = keySerializer$KeySerializationFunction0;
                super(class0, class1, null);
            }

            @Override  // com.google.crypto.tink.internal.KeySerializer
            public Serialization serializeKey(Key key0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
                return this.val$function.serializeKey(key0, secretKeyAccess0);
            }
        };
    }

    public Class getKeyClass() {
        return this.keyClass;
    }

    public Class getSerializationClass() {
        return this.serializationClass;
    }

    public abstract Serialization serializeKey(Key arg1, @Nullable SecretKeyAccess arg2) throws GeneralSecurityException;
}

