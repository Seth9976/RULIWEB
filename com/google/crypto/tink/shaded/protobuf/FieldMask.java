package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class FieldMask extends GeneratedMessageLite implements FieldMaskOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements FieldMaskOrBuilder {
        private Builder() {
            super(FieldMask.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.FieldMask.1 fieldMask$10) {
        }

        public Builder addAllPaths(Iterable iterable0) {
            this.copyOnWrite();
            ((FieldMask)this.instance).addAllPaths(iterable0);
            return this;
        }

        public Builder addPaths(String s) {
            this.copyOnWrite();
            ((FieldMask)this.instance).addPaths(s);
            return this;
        }

        public Builder addPathsBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((FieldMask)this.instance).addPathsBytes(byteString0);
            return this;
        }

        public Builder clearPaths() {
            this.copyOnWrite();
            ((FieldMask)this.instance).clearPaths();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldMaskOrBuilder
        public String getPaths(int v) {
            return ((FieldMask)this.instance).getPaths(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldMaskOrBuilder
        public ByteString getPathsBytes(int v) {
            return ((FieldMask)this.instance).getPathsBytes(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldMaskOrBuilder
        public int getPathsCount() {
            return ((FieldMask)this.instance).getPathsCount();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldMaskOrBuilder
        public List getPathsList() {
            return Collections.unmodifiableList(((FieldMask)this.instance).getPathsList());
        }

        public Builder setPaths(int v, String s) {
            this.copyOnWrite();
            ((FieldMask)this.instance).setPaths(v, s);
            return this;
        }
    }

    private static final FieldMask DEFAULT_INSTANCE = null;
    private static volatile Parser PARSER = null;
    public static final int PATHS_FIELD_NUMBER = 1;
    private ProtobufList paths_;

    static {
        FieldMask fieldMask0 = new FieldMask();
        FieldMask.DEFAULT_INSTANCE = fieldMask0;
        GeneratedMessageLite.registerDefaultInstance(FieldMask.class, fieldMask0);
    }

    private FieldMask() {
        this.paths_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void addAllPaths(Iterable iterable0) {
        this.ensurePathsIsMutable();
        AbstractMessageLite.addAll(iterable0, this.paths_);
    }

    private void addPaths(String s) {
        s.getClass();
        this.ensurePathsIsMutable();
        this.paths_.add(s);
    }

    private void addPathsBytes(ByteString byteString0) {
        FieldMask.checkByteStringIsUtf8(byteString0);
        this.ensurePathsIsMutable();
        this.paths_.add(byteString0.toStringUtf8());
    }

    private void clearPaths() {
        this.paths_ = GeneratedMessageLite.emptyProtobufList();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.FieldMask.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new FieldMask();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return FieldMask.newMessageInfo(FieldMask.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001Ț", new Object[]{"paths_"});
            }
            case 4: {
                return FieldMask.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = FieldMask.PARSER;
                if(parser0 == null) {
                    Class class0 = FieldMask.class;
                    synchronized(class0) {
                        Parser parser1 = FieldMask.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(FieldMask.DEFAULT_INSTANCE);
                            FieldMask.PARSER = parser1;
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

    private void ensurePathsIsMutable() {
        ProtobufList internal$ProtobufList0 = this.paths_;
        if(!internal$ProtobufList0.isModifiable()) {
            this.paths_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList0);
        }
    }

    public static FieldMask getDefaultInstance() {
        return FieldMask.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldMaskOrBuilder
    public String getPaths(int v) {
        return (String)this.paths_.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldMaskOrBuilder
    public ByteString getPathsBytes(int v) {
        return ByteString.copyFromUtf8(((String)this.paths_.get(v)));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldMaskOrBuilder
    public int getPathsCount() {
        return this.paths_.size();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldMaskOrBuilder
    public List getPathsList() {
        return this.paths_;
    }

    public static Builder newBuilder() {
        return (Builder)FieldMask.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(FieldMask fieldMask0) {
        return (Builder)FieldMask.DEFAULT_INSTANCE.createBuilder(fieldMask0);
    }

    public static FieldMask parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (FieldMask)FieldMask.parseDelimitedFrom(FieldMask.DEFAULT_INSTANCE, inputStream0);
    }

    public static FieldMask parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (FieldMask)FieldMask.parseDelimitedFrom(FieldMask.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static FieldMask parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (FieldMask)GeneratedMessageLite.parseFrom(FieldMask.DEFAULT_INSTANCE, byteString0);
    }

    public static FieldMask parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (FieldMask)GeneratedMessageLite.parseFrom(FieldMask.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static FieldMask parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (FieldMask)GeneratedMessageLite.parseFrom(FieldMask.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static FieldMask parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (FieldMask)GeneratedMessageLite.parseFrom(FieldMask.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static FieldMask parseFrom(InputStream inputStream0) throws IOException {
        return (FieldMask)GeneratedMessageLite.parseFrom(FieldMask.DEFAULT_INSTANCE, inputStream0);
    }

    public static FieldMask parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (FieldMask)GeneratedMessageLite.parseFrom(FieldMask.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static FieldMask parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (FieldMask)GeneratedMessageLite.parseFrom(FieldMask.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static FieldMask parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (FieldMask)GeneratedMessageLite.parseFrom(FieldMask.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static FieldMask parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (FieldMask)GeneratedMessageLite.parseFrom(FieldMask.DEFAULT_INSTANCE, arr_b);
    }

    public static FieldMask parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (FieldMask)GeneratedMessageLite.parseFrom(FieldMask.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return FieldMask.DEFAULT_INSTANCE.getParserForType();
    }

    private void setPaths(int v, String s) {
        s.getClass();
        this.ensurePathsIsMutable();
        this.paths_.set(v, s);
    }
}

