package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface KeysetInfoOrBuilder extends MessageLiteOrBuilder {
    KeyInfo getKeyInfo(int arg1);

    int getKeyInfoCount();

    List getKeyInfoList();

    int getPrimaryKeyId();
}

