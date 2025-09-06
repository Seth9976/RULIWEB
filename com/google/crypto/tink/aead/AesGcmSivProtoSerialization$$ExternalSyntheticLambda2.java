package com.google.crypto.tink.aead;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction;
import com.google.crypto.tink.internal.Serialization;

public final class AesGcmSivProtoSerialization..ExternalSyntheticLambda2 implements KeySerializationFunction {
    @Override  // com.google.crypto.tink.internal.KeySerializer$KeySerializationFunction
    public final Serialization serializeKey(Key key0, SecretKeyAccess secretKeyAccess0) {
        return AesGcmSivProtoSerialization.$r8$lambda$ZVix_XPd6SkCZney6dJhi30uqRo(((AesGcmSivKey)key0), secretKeyAccess0);
    }
}

