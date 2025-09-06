package com.google.crypto.tink.shaded.protobuf;

import java.util.Map;

@CheckReturnValue
interface MapFieldSchema {
    Map forMapData(Object arg1);

    Metadata forMapMetadata(Object arg1);

    Map forMutableMapData(Object arg1);

    int getSerializedSize(int arg1, Object arg2, Object arg3);

    boolean isImmutable(Object arg1);

    Object mergeFrom(Object arg1, Object arg2);

    Object newMapField(Object arg1);

    Object toImmutable(Object arg1);
}

