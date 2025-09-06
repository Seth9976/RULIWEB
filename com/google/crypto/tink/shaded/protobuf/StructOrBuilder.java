package com.google.crypto.tink.shaded.protobuf;

import java.util.Map;

public interface StructOrBuilder extends MessageLiteOrBuilder {
    boolean containsFields(String arg1);

    @Deprecated
    Map getFields();

    int getFieldsCount();

    Map getFieldsMap();

    Value getFieldsOrDefault(String arg1, Value arg2);

    Value getFieldsOrThrow(String arg1);
}

