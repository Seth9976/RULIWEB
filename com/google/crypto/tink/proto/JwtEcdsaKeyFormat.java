package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.AbstractMessageLite;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedInputStream;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class JwtEcdsaKeyFormat extends GeneratedMessageLite implements JwtEcdsaKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements JwtEcdsaKeyFormatOrBuilder {
        private Builder() {
            super(JwtEcdsaKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.JwtEcdsaKeyFormat.1 jwtEcdsaKeyFormat$10) {
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public MessageLite build() {
            return super.build();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public MessageLite buildPartial() {
            return super.buildPartial();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder clear() {
            return super.clear();
        }

        public Builder clearAlgorithm() {
            this.copyOnWrite();
            ((JwtEcdsaKeyFormat)this.instance).clearAlgorithm();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((JwtEcdsaKeyFormat)this.instance).clearVersion();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder clone() {
            return super.clone();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder clone() {
            return super.clone();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override  // com.google.crypto.tink.proto.JwtEcdsaKeyFormatOrBuilder
        public JwtEcdsaAlgorithm getAlgorithm() {
            return ((JwtEcdsaKeyFormat)this.instance).getAlgorithm();
        }

        @Override  // com.google.crypto.tink.proto.JwtEcdsaKeyFormatOrBuilder
        public int getAlgorithmValue() {
            return ((JwtEcdsaKeyFormat)this.instance).getAlgorithmValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.JwtEcdsaKeyFormatOrBuilder
        public int getVersion() {
            return ((JwtEcdsaKeyFormat)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        protected com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite abstractMessageLite0) {
            return super.internalMergeFrom(((GeneratedMessageLite)abstractMessageLite0));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return super.mergeFrom(codedInputStream0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder mergeFrom(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
            return super.mergeFrom(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder mergeFrom(byte[] arr_b, int v, int v1, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return super.mergeFrom(arr_b, v, v1, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(ByteString byteString0) throws InvalidProtocolBufferException {
            return super.mergeFrom(byteString0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return super.mergeFrom(byteString0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0) throws IOException {
            return super.mergeFrom(codedInputStream0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return super.mergeFrom(codedInputStream0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(MessageLite messageLite0) {
            return super.mergeFrom(messageLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(InputStream inputStream0) throws IOException {
            return super.mergeFrom(inputStream0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return super.mergeFrom(inputStream0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(byte[] arr_b) throws InvalidProtocolBufferException {
            return super.mergeFrom(arr_b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
            return super.mergeFrom(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(byte[] arr_b, int v, int v1, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return super.mergeFrom(arr_b, v, v1, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return super.mergeFrom(arr_b, extensionRegistryLite0);
        }

        public Builder setAlgorithm(JwtEcdsaAlgorithm jwtEcdsaAlgorithm0) {
            this.copyOnWrite();
            ((JwtEcdsaKeyFormat)this.instance).setAlgorithm(jwtEcdsaAlgorithm0);
            return this;
        }

        public Builder setAlgorithmValue(int v) {
            this.copyOnWrite();
            ((JwtEcdsaKeyFormat)this.instance).setAlgorithmValue(v);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((JwtEcdsaKeyFormat)this.instance).setVersion(v);
            return this;
        }
    }

    public static final int ALGORITHM_FIELD_NUMBER = 2;
    private static final JwtEcdsaKeyFormat DEFAULT_INSTANCE = null;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private int algorithm_;
    private int version_;

    static {
        JwtEcdsaKeyFormat jwtEcdsaKeyFormat0 = new JwtEcdsaKeyFormat();
        JwtEcdsaKeyFormat.DEFAULT_INSTANCE = jwtEcdsaKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(JwtEcdsaKeyFormat.class, jwtEcdsaKeyFormat0);
    }

    private void clearAlgorithm() {
        this.algorithm_ = 0;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.JwtEcdsaKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new JwtEcdsaKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return JwtEcdsaKeyFormat.newMessageInfo(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000B\u0002\f", new Object[]{"version_", "algorithm_"});
            }
            case 4: {
                return JwtEcdsaKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = JwtEcdsaKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = JwtEcdsaKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = JwtEcdsaKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(JwtEcdsaKeyFormat.DEFAULT_INSTANCE);
                            JwtEcdsaKeyFormat.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.JwtEcdsaKeyFormatOrBuilder
    public JwtEcdsaAlgorithm getAlgorithm() {
        JwtEcdsaAlgorithm jwtEcdsaAlgorithm0 = JwtEcdsaAlgorithm.forNumber(this.algorithm_);
        return jwtEcdsaAlgorithm0 == null ? JwtEcdsaAlgorithm.UNRECOGNIZED : jwtEcdsaAlgorithm0;
    }

    @Override  // com.google.crypto.tink.proto.JwtEcdsaKeyFormatOrBuilder
    public int getAlgorithmValue() {
        return this.algorithm_;
    }

    public static JwtEcdsaKeyFormat getDefaultInstance() {
        return JwtEcdsaKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.JwtEcdsaKeyFormatOrBuilder
    public int getVersion() {
        return this.version_;
    }

    public static Builder newBuilder() {
        return (Builder)JwtEcdsaKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(JwtEcdsaKeyFormat jwtEcdsaKeyFormat0) {
        return (Builder)JwtEcdsaKeyFormat.DEFAULT_INSTANCE.createBuilder(jwtEcdsaKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static JwtEcdsaKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (JwtEcdsaKeyFormat)JwtEcdsaKeyFormat.parseDelimitedFrom(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtEcdsaKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtEcdsaKeyFormat)JwtEcdsaKeyFormat.parseDelimitedFrom(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtEcdsaKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (JwtEcdsaKeyFormat)GeneratedMessageLite.parseFrom(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static JwtEcdsaKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtEcdsaKeyFormat)GeneratedMessageLite.parseFrom(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static JwtEcdsaKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (JwtEcdsaKeyFormat)GeneratedMessageLite.parseFrom(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static JwtEcdsaKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtEcdsaKeyFormat)GeneratedMessageLite.parseFrom(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static JwtEcdsaKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (JwtEcdsaKeyFormat)GeneratedMessageLite.parseFrom(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtEcdsaKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtEcdsaKeyFormat)GeneratedMessageLite.parseFrom(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtEcdsaKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (JwtEcdsaKeyFormat)GeneratedMessageLite.parseFrom(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static JwtEcdsaKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtEcdsaKeyFormat)GeneratedMessageLite.parseFrom(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static JwtEcdsaKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (JwtEcdsaKeyFormat)GeneratedMessageLite.parseFrom(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static JwtEcdsaKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtEcdsaKeyFormat)GeneratedMessageLite.parseFrom(JwtEcdsaKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return JwtEcdsaKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setAlgorithm(JwtEcdsaAlgorithm jwtEcdsaAlgorithm0) {
        this.algorithm_ = jwtEcdsaAlgorithm0.getNumber();
    }

    private void setAlgorithmValue(int v) {
        this.algorithm_ = v;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

