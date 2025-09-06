package com.google.crypto.tink.signature;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser.KeyParsingFunction;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.Serialization;

public final class EcdsaProtoSerialization..ExternalSyntheticLambda3 implements KeyParsingFunction {
    @Override  // com.google.crypto.tink.internal.KeyParser$KeyParsingFunction
    public final Key parseKey(Serialization serialization0, SecretKeyAccess secretKeyAccess0) {
        return EcdsaProtoSerialization.$r8$lambda$yS9Hwh6FSrUpXOxo6QSA4zOibRQ(((ProtoKeySerialization)serialization0), secretKeyAccess0);
    }
}

