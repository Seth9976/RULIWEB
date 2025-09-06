package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;

public interface KeyManager {
    boolean doesSupport(String arg1);

    String getKeyType();

    Object getPrimitive(ByteString arg1) throws GeneralSecurityException;

    Object getPrimitive(MessageLite arg1) throws GeneralSecurityException;

    Class getPrimitiveClass();

    int getVersion();

    MessageLite newKey(ByteString arg1) throws GeneralSecurityException;

    MessageLite newKey(MessageLite arg1) throws GeneralSecurityException;

    KeyData newKeyData(ByteString arg1) throws GeneralSecurityException;
}

