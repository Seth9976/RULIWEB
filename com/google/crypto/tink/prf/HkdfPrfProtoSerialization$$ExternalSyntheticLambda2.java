package com.google.crypto.tink.prf;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction;
import com.google.crypto.tink.internal.Serialization;

public final class HkdfPrfProtoSerialization..ExternalSyntheticLambda2 implements KeySerializationFunction {
    @Override  // com.google.crypto.tink.internal.KeySerializer$KeySerializationFunction
    public final Serialization serializeKey(Key key0, SecretKeyAccess secretKeyAccess0) {
        return HkdfPrfProtoSerialization.$r8$lambda$Cd7BK-0UX4it_C-F5LEzj5wO9ak(((HkdfPrfKey)key0), secretKeyAccess0);
    }
}

