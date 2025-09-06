package com.google.crypto.tink.shaded.protobuf;

@CheckReturnValue
final class MapFieldSchemas {
    private static final MapFieldSchema FULL_SCHEMA;
    private static final MapFieldSchema LITE_SCHEMA;

    static {
        MapFieldSchemas.FULL_SCHEMA = MapFieldSchemas.loadSchemaForFullRuntime();
        MapFieldSchemas.LITE_SCHEMA = new MapFieldSchemaLite();
    }

    static MapFieldSchema full() {
        return MapFieldSchemas.FULL_SCHEMA;
    }

    static MapFieldSchema lite() {
        return MapFieldSchemas.LITE_SCHEMA;
    }

    private static MapFieldSchema loadSchemaForFullRuntime() {
        try {
            return (MapFieldSchema)Class.forName("com.google.crypto.tink.shaded.protobuf.MapFieldSchemaFull").getDeclaredConstructor(null).newInstance(null);
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

