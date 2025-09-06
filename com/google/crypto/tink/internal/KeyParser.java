package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.util.Bytes;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

public abstract class KeyParser {
    public interface KeyParsingFunction {
        Key parseKey(Serialization arg1, @Nullable SecretKeyAccess arg2) throws GeneralSecurityException;
    }

    private final Bytes objectIdentifier;
    private final Class serializationClass;

    private KeyParser(Bytes bytes0, Class class0) {
        this.objectIdentifier = bytes0;
        this.serializationClass = class0;
    }

    KeyParser(Bytes bytes0, Class class0, com.google.crypto.tink.internal.KeyParser.1 keyParser$10) {
        this(bytes0, class0);
    }

    public static KeyParser create(KeyParsingFunction keyParser$KeyParsingFunction0, Bytes bytes0, Class class0) {
        return new KeyParser(bytes0, class0, keyParser$KeyParsingFunction0) {
            final KeyParsingFunction val$function;

            {
                this.val$function = keyParser$KeyParsingFunction0;
                super(bytes0, class0, null);
            }

            @Override  // com.google.crypto.tink.internal.KeyParser
            public Key parseKey(Serialization serialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
                return this.val$function.parseKey(serialization0, secretKeyAccess0);
            }
        };
    }

    public final Bytes getObjectIdentifier() {
        return this.objectIdentifier;
    }

    public final Class getSerializationClass() {
        return this.serializationClass;
    }

    public abstract Key parseKey(Serialization arg1, @Nullable SecretKeyAccess arg2) throws GeneralSecurityException;
}

