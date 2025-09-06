package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@CheckReturnValue
final class Protobuf {
    private static final Protobuf INSTANCE;
    private final ConcurrentMap schemaCache;
    private final SchemaFactory schemaFactory;

    static {
        Protobuf.INSTANCE = new Protobuf();
    }

    private Protobuf() {
        this.schemaCache = new ConcurrentHashMap();
        this.schemaFactory = new ManifestSchemaFactory();
    }

    public static Protobuf getInstance() {
        return Protobuf.INSTANCE;
    }

    int getTotalSchemaSize() {
        int v = 0;
        for(Object object0: this.schemaCache.values()) {
            Schema schema0 = (Schema)object0;
            if(schema0 instanceof MessageSchema) {
                v += ((MessageSchema)schema0).getSchemaSize();
            }
        }
        return v;
    }

    boolean isInitialized(Object object0) {
        return this.schemaFor(object0).isInitialized(object0);
    }

    public void makeImmutable(Object object0) {
        this.schemaFor(object0).makeImmutable(object0);
    }

    public void mergeFrom(Object object0, Reader reader0) throws IOException {
        this.mergeFrom(object0, reader0, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void mergeFrom(Object object0, Reader reader0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        this.schemaFor(object0).mergeFrom(object0, reader0, extensionRegistryLite0);
    }

    public Schema registerSchema(Class class0, Schema schema0) {
        Internal.checkNotNull(class0, "messageType");
        Internal.checkNotNull(schema0, "schema");
        return (Schema)this.schemaCache.putIfAbsent(class0, schema0);
    }

    public Schema registerSchemaOverride(Class class0, Schema schema0) {
        Internal.checkNotNull(class0, "messageType");
        Internal.checkNotNull(schema0, "schema");
        return (Schema)this.schemaCache.put(class0, schema0);
    }

    public Schema schemaFor(Class class0) {
        Internal.checkNotNull(class0, "messageType");
        Schema schema0 = (Schema)this.schemaCache.get(class0);
        if(schema0 == null) {
            schema0 = this.schemaFactory.createSchema(class0);
            Schema schema1 = this.registerSchema(class0, schema0);
            return schema1 == null ? schema0 : schema1;
        }
        return schema0;
    }

    public Schema schemaFor(Object object0) {
        return this.schemaFor(object0.getClass());
    }

    public void writeTo(Object object0, Writer writer0) throws IOException {
        this.schemaFor(object0).writeTo(object0, writer0);
    }
}

