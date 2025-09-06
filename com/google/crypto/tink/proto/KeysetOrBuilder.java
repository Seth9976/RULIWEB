package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface KeysetOrBuilder extends MessageLiteOrBuilder {
    Key getKey(int arg1);

    int getKeyCount();

    List getKeyList();

    int getPrimaryKeyId();
}

