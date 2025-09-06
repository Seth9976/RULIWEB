package com.google.crypto.tink.shaded.protobuf;

import java.util.Map.Entry;
import java.util.Map;

@CheckReturnValue
class MapFieldSchemaLite implements MapFieldSchema {
    @Override  // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Map forMapData(Object object0) {
        return (MapFieldLite)object0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Metadata forMapMetadata(Object object0) {
        return ((MapEntryLite)object0).getMetadata();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Map forMutableMapData(Object object0) {
        return (MapFieldLite)object0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public int getSerializedSize(int v, Object object0, Object object1) {
        return MapFieldSchemaLite.getSerializedSizeLite(v, object0, object1);
    }

    private static int getSerializedSizeLite(int v, Object object0, Object object1) {
        int v1 = 0;
        if(((MapFieldLite)object0).isEmpty()) {
            return 0;
        }
        for(Object object2: ((MapFieldLite)object0).entrySet()) {
            v1 += ((MapEntryLite)object1).computeMessageSize(v, ((Map.Entry)object2).getKey(), ((Map.Entry)object2).getValue());
        }
        return v1;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public boolean isImmutable(Object object0) {
        return !((MapFieldLite)object0).isMutable();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Object mergeFrom(Object object0, Object object1) {
        return MapFieldSchemaLite.mergeFromLite(object0, object1);
    }

    private static MapFieldLite mergeFromLite(Object object0, Object object1) {
        MapFieldLite mapFieldLite0 = (MapFieldLite)object0;
        if(!((MapFieldLite)object1).isEmpty()) {
            if(!mapFieldLite0.isMutable()) {
                mapFieldLite0 = mapFieldLite0.mutableCopy();
            }
            mapFieldLite0.mergeFrom(((MapFieldLite)object1));
        }
        return mapFieldLite0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Object newMapField(Object object0) {
        return MapFieldLite.emptyMapField().mutableCopy();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Object toImmutable(Object object0) {
        ((MapFieldLite)object0).makeImmutable();
        return object0;
    }
}

