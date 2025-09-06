package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.InputStream;
import java.security.GeneralSecurityException;

public class PrivilegedRegistry {
    public static KeyData deriveKey(KeyTemplate keyTemplate0, InputStream inputStream0) throws GeneralSecurityException {
        return Registry.deriveKey(keyTemplate0, inputStream0);
    }

    public static MessageLite parseKeyData(KeyData keyData0) throws GeneralSecurityException, InvalidProtocolBufferException {
        return Registry.parseKeyData(keyData0);
    }
}

