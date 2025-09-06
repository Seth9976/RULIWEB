package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Timestamp extends GeneratedMessageLite implements TimestampOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements TimestampOrBuilder {
        private Builder() {
            super(Timestamp.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.Timestamp.1 timestamp$10) {
        }

        public Builder clearNanos() {
            this.copyOnWrite();
            ((Timestamp)this.instance).clearNanos();
            return this;
        }

        public Builder clearSeconds() {
            this.copyOnWrite();
            ((Timestamp)this.instance).clearSeconds();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TimestampOrBuilder
        public int getNanos() {
            return ((Timestamp)this.instance).getNanos();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TimestampOrBuilder
        public long getSeconds() {
            return ((Timestamp)this.instance).getSeconds();
        }

        public Builder setNanos(int v) {
            this.copyOnWrite();
            ((Timestamp)this.instance).setNanos(v);
            return this;
        }

        public Builder setSeconds(long v) {
            this.copyOnWrite();
            ((Timestamp)this.instance).setSeconds(v);
            return this;
        }
    }

    private static final Timestamp DEFAULT_INSTANCE = null;
    public static final int NANOS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int SECONDS_FIELD_NUMBER = 1;
    private int nanos_;
    private long seconds_;

    static {
        Timestamp timestamp0 = new Timestamp();
        Timestamp.DEFAULT_INSTANCE = timestamp0;
        GeneratedMessageLite.registerDefaultInstance(Timestamp.class, timestamp0);
    }

    private void clearNanos() {
        this.nanos_ = 0;
    }

    private void clearSeconds() {
        this.seconds_ = 0L;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.Timestamp.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Timestamp();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Timestamp.newMessageInfo(Timestamp.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"seconds_", "nanos_"});
            }
            case 4: {
                return Timestamp.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Timestamp.PARSER;
                if(parser0 == null) {
                    Class class0 = Timestamp.class;
                    synchronized(class0) {
                        Parser parser1 = Timestamp.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Timestamp.DEFAULT_INSTANCE);
                            Timestamp.PARSER = parser1;
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

    public static Timestamp getDefaultInstance() {
        return Timestamp.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TimestampOrBuilder
    public int getNanos() {
        return this.nanos_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TimestampOrBuilder
    public long getSeconds() {
        return this.seconds_;
    }

    public static Builder newBuilder() {
        return (Builder)Timestamp.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Timestamp timestamp0) {
        return (Builder)Timestamp.DEFAULT_INSTANCE.createBuilder(timestamp0);
    }

    public static Timestamp parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Timestamp)Timestamp.parseDelimitedFrom(Timestamp.DEFAULT_INSTANCE, inputStream0);
    }

    public static Timestamp parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Timestamp)Timestamp.parseDelimitedFrom(Timestamp.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Timestamp parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Timestamp)GeneratedMessageLite.parseFrom(Timestamp.DEFAULT_INSTANCE, byteString0);
    }

    public static Timestamp parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Timestamp)GeneratedMessageLite.parseFrom(Timestamp.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Timestamp parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Timestamp)GeneratedMessageLite.parseFrom(Timestamp.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Timestamp parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Timestamp)GeneratedMessageLite.parseFrom(Timestamp.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Timestamp parseFrom(InputStream inputStream0) throws IOException {
        return (Timestamp)GeneratedMessageLite.parseFrom(Timestamp.DEFAULT_INSTANCE, inputStream0);
    }

    public static Timestamp parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Timestamp)GeneratedMessageLite.parseFrom(Timestamp.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Timestamp parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Timestamp)GeneratedMessageLite.parseFrom(Timestamp.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Timestamp parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Timestamp)GeneratedMessageLite.parseFrom(Timestamp.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Timestamp parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Timestamp)GeneratedMessageLite.parseFrom(Timestamp.DEFAULT_INSTANCE, arr_b);
    }

    public static Timestamp parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Timestamp)GeneratedMessageLite.parseFrom(Timestamp.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Timestamp.DEFAULT_INSTANCE.getParserForType();
    }

    private void setNanos(int v) {
        this.nanos_ = v;
    }

    private void setSeconds(long v) {
        this.seconds_ = v;
    }
}

