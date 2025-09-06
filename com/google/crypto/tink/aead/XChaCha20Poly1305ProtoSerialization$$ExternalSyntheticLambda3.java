package com.google.crypto.tink.aead;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser.KeyParsingFunction;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.Serialization;

public final class XChaCha20Poly1305ProtoSerialization..ExternalSyntheticLambda3 implements KeyParsingFunction {
    @Override  // com.google.crypto.tink.internal.KeyParser$KeyParsingFunction
    public final Key parseKey(Serialization serialization0, SecretKeyAccess secretKeyAccess0) {
        return XChaCha20Poly1305ProtoSerialization.$r8$lambda$CshRoNazMt5NRtdRkSWAYsXM9uI(((ProtoKeySerialization)serialization0), secretKeyAccess0);
    }
}

