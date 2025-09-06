package com.google.crypto.tink.aead;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction;
import com.google.crypto.tink.internal.Serialization;

public final class XChaCha20Poly1305ProtoSerialization..ExternalSyntheticLambda2 implements KeySerializationFunction {
    @Override  // com.google.crypto.tink.internal.KeySerializer$KeySerializationFunction
    public final Serialization serializeKey(Key key0, SecretKeyAccess secretKeyAccess0) {
        return XChaCha20Poly1305ProtoSerialization.$r8$lambda$bEkdnXQxuYKf9bXoZfJjPvsAnmw(((XChaCha20Poly1305Key)key0), secretKeyAccess0);
    }
}

