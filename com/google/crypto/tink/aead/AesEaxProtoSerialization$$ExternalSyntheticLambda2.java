package com.google.crypto.tink.aead;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction;
import com.google.crypto.tink.internal.Serialization;

public final class AesEaxProtoSerialization..ExternalSyntheticLambda2 implements KeySerializationFunction {
    @Override  // com.google.crypto.tink.internal.KeySerializer$KeySerializationFunction
    public final Serialization serializeKey(Key key0, SecretKeyAccess secretKeyAccess0) {
        return AesEaxProtoSerialization.$r8$lambda$hCX1FKbCEXveJ2WRy7RTy10wxag(((AesEaxKey)key0), secretKeyAccess0);
    }
}

