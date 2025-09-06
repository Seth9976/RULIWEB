package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

public interface KeyDataOrBuilder extends MessageLiteOrBuilder {
    KeyMaterialType getKeyMaterialType();

    int getKeyMaterialTypeValue();

    String getTypeUrl();

    ByteString getTypeUrlBytes();

    ByteString getValue();
}

