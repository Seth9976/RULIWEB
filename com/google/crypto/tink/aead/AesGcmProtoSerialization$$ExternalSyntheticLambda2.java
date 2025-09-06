package com.google.crypto.tink.aead;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction;
import com.google.crypto.tink.internal.Serialization;

public final class AesGcmProtoSerialization..ExternalSyntheticLambda2 implements KeySerializationFunction {
    @Override  // com.google.crypto.tink.internal.KeySerializer$KeySerializationFunction
    public final Serialization serializeKey(Key key0, SecretKeyAccess secretKeyAccess0) {
        return AesGcmProtoSerialization.$r8$lambda$EUz4ME7O9dcEVpDksD4G5pFlPQA(((AesGcmKey)key0), secretKeyAccess0);
    }
}

