package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

public final class Struct extends GeneratedMessageLite implements StructOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements StructOrBuilder {
        private Builder() {
            super(Struct.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.Struct.1 struct$10) {
        }

        public Builder clearFields() {
            this.copyOnWrite();
            ((Struct)this.instance).getMutableFieldsMap().clear();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.StructOrBuilder
        public boolean containsFields(String s) {
            s.getClass();
            return ((Struct)this.instance).getFieldsMap().containsKey(s);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.StructOrBuilder
        @Deprecated
        public Map getFields() {
            return this.getFieldsMap();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.StructOrBuilder
        public int getFieldsCount() {
            return ((Struct)this.instance).getFieldsMap().size();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.StructOrBuilder
        public Map getFieldsMap() {
            return Collections.unmodifiableMap(((Struct)this.instance).getFieldsMap());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.StructOrBuilder
        public Value getFieldsOrDefault(String s, Value value0) {
            s.getClass();
            Map map0 = ((Struct)this.instance).getFieldsMap();
            return map0.containsKey(s) ? ((Value)map0.get(s)) : value0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.StructOrBuilder
        public Value getFieldsOrThrow(String s) {
            s.getClass();
            Map map0 = ((Struct)this.instance).getFieldsMap();
            if(!map0.containsKey(s)) {
                throw new IllegalArgumentException();
            }
            return (Value)map0.get(s);
        }

        public Builder putAllFields(Map map0) {
            this.copyOnWrite();
            ((Struct)this.instance).getMutableFieldsMap().putAll(map0);
            return this;
        }

        public Builder putFields(String s, Value value0) {
            s.getClass();
            value0.getClass();
            this.copyOnWrite();
            ((Struct)this.instance).getMutableFieldsMap().put(s, value0);
            return this;
        }

        public Builder removeFields(String s) {
            s.getClass();
            this.copyOnWrite();
            ((Struct)this.instance).getMutableFieldsMap().remove(s);
            return this;
        }
    }

    static final class FieldsDefaultEntryHolder {
        static final MapEntryLite defaultEntry;

        static {
            Value value0 = Value.getDefaultInstance();
            FieldsDefaultEntryHolder.defaultEntry = MapEntryLite.newDefaultInstance(FieldType.STRING, "", FieldType.MESSAGE, value0);
        }
    }

    private static final Struct DEFAULT_INSTANCE = null;
    public static final int FIELDS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private MapFieldLite fields_;

    static {
        Struct struct0 = new Struct();
        Struct.DEFAULT_INSTANCE = struct0;
        GeneratedMessageLite.registerDefaultInstance(Struct.class, struct0);
    }

    private Struct() {
        this.fields_ = MapFieldLite.emptyMapField();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.StructOrBuilder
    public boolean containsFields(String s) {
        s.getClass();
        return this.internalGetFields().containsKey(s);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.Struct.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Struct();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Struct.newMessageInfo(Struct.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"fields_", FieldsDefaultEntryHolder.defaultEntry});
            }
            case 4: {
                return Struct.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Struct.PARSER;
                if(parser0 == null) {
                    Class class0 = Struct.class;
                    synchronized(class0) {
                        Parser parser1 = Struct.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Struct.DEFAULT_INSTANCE);
                            Struct.PARSER = parser1;
                        }
                        return parser1;
                    }
                }
                return parser0;
            }
            case 6: {
                return (byte)1;
            }
            case 7: {
                return null;
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }

    public static Struct getDefaultInstance() {
        return Struct.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.StructOrBuilder
    @Deprecated
    public Map getFields() {
        return this.getFieldsMap();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.StructOrBuilder
    public int getFieldsCount() {
        return this.internalGetFields().size();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.StructOrBuilder
    public Map getFieldsMap() {
        return Collections.unmodifiableMap(this.internalGetFields());
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.StructOrBuilder
    public Value getFieldsOrDefault(String s, Value value0) {
        s.getClass();
        MapFieldLite mapFieldLite0 = this.internalGetFields();
        return mapFieldLite0.containsKey(s) ? ((Value)mapFieldLite0.get(s)) : value0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.StructOrBuilder
    public Value getFieldsOrThrow(String s) {
        s.getClass();
        MapFieldLite mapFieldLite0 = this.internalGetFields();
        if(!mapFieldLite0.containsKey(s)) {
            throw new IllegalArgumentException();
        }
        return (Value)mapFieldLite0.get(s);
    }

    private Map getMutableFieldsMap() {
        return this.internalGetMutableFields();
    }

    private MapFieldLite internalGetFields() {
        return this.fields_;
    }

    private MapFieldLite internalGetMutableFields() {
        if(!this.fields_.isMutable()) {
            this.fields_ = this.fields_.mutableCopy();
        }
        return this.fields_;
    }

    public static Builder newBuilder() {
        return (Builder)Struct.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Struct struct0) {
        return (Builder)Struct.DEFAULT_INSTANCE.createBuilder(struct0);
    }

    public static Struct parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Struct)Struct.parseDelimitedFrom(Struct.DEFAULT_INSTANCE, inputStream0);
    }

    public static Struct parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Struct)Struct.parseDelimitedFrom(Struct.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Struct parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Struct)GeneratedMessageLite.parseFrom(Struct.DEFAULT_INSTANCE, byteString0);
    }

    public static Struct parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Struct)GeneratedMessageLite.parseFrom(Struct.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Struct parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Struct)GeneratedMessageLite.parseFrom(Struct.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Struct parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Struct)GeneratedMessageLite.parseFrom(Struct.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Struct parseFrom(InputStream inputStream0) throws IOException {
        return (Struct)GeneratedMessageLite.parseFrom(Struct.DEFAULT_INSTANCE, inputStream0);
    }

    public static Struct parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Struct)GeneratedMessageLite.parseFrom(Struct.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Struct parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Struct)GeneratedMessageLite.parseFrom(Struct.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Struct parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Struct)GeneratedMessageLite.parseFrom(Struct.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Struct parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Struct)GeneratedMessageLite.parseFrom(Struct.DEFAULT_INSTANCE, arr_b);
    }

    public static Struct parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Struct)GeneratedMessageLite.parseFrom(Struct.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Struct.DEFAULT_INSTANCE.getParserForType();
    }
}

