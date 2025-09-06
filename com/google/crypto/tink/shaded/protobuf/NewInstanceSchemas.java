package com.google.crypto.tink.shaded.protobuf;

@CheckReturnValue
final class NewInstanceSchemas {
    private static final NewInstanceSchema FULL_SCHEMA;
    private static final NewInstanceSchema LITE_SCHEMA;

    static {
        NewInstanceSchemas.FULL_SCHEMA = NewInstanceSchemas.loadSchemaForFullRuntime();
        NewInstanceSchemas.LITE_SCHEMA = new NewInstanceSchemaLite();
    }

    static NewInstanceSchema full() {
        return NewInstanceSchemas.FULL_SCHEMA;
    }

    static NewInstanceSchema lite() {
        return NewInstanceSchemas.LITE_SCHEMA;
    }

    private static NewInstanceSchema loadSchemaForFullRuntime() {
        try {
            return (NewInstanceSchema)Class.forName("com.google.crypto.tink.shaded.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

