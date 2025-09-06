package com.google.crypto.tink.shaded.protobuf;

@CheckReturnValue
final class ExtensionSchemas {
    private static final ExtensionSchema FULL_SCHEMA;
    private static final ExtensionSchema LITE_SCHEMA;

    static {
        ExtensionSchemas.LITE_SCHEMA = new ExtensionSchemaLite();
        ExtensionSchemas.FULL_SCHEMA = ExtensionSchemas.loadSchemaForFullRuntime();
    }

    static ExtensionSchema full() {
        ExtensionSchema extensionSchema0 = ExtensionSchemas.FULL_SCHEMA;
        if(extensionSchema0 == null) {
            throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
        }
        return extensionSchema0;
    }

    static ExtensionSchema lite() {
        return ExtensionSchemas.LITE_SCHEMA;
    }

    private static ExtensionSchema loadSchemaForFullRuntime() {
        try {
            return (ExtensionSchema)Class.forName("com.google.crypto.tink.shaded.protobuf.ExtensionSchemaFull").getDeclaredConstructor(null).newInstance(null);
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

