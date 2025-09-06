package com.google.crypto.tink.signature;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction;
import com.google.crypto.tink.internal.Serialization;

public final class EcdsaProtoSerialization..ExternalSyntheticLambda4 implements KeySerializationFunction {
    @Override  // com.google.crypto.tink.internal.KeySerializer$KeySerializationFunction
    public final Serialization serializeKey(Key key0, SecretKeyAccess secretKeyAccess0) {
        return EcdsaProtoSerialization.$r8$lambda$QL64PlliBOEO_yvpgc5iADKWtsE(((EcdsaPrivateKey)key0), secretKeyAccess0);
    }
}

