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

public final class JwtHmacKeyFormat extends GeneratedMessageLite implements JwtHmacKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements JwtHmacKeyFormatOrBuilder {
        private Builder() {
            super(JwtHmacKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.JwtHmacKeyFormat.1 jwtHmacKeyFormat$10) {
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
            ((JwtHmacKeyFormat)this.instance).clearAlgorithm();
            return this;
        }

        public Builder clearKeySize() {
            this.copyOnWrite();
            ((JwtHmacKeyFormat)this.instance).clearKeySize();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((JwtHmacKeyFormat)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.JwtHmacKeyFormatOrBuilder
        public JwtHmacAlgorithm getAlgorithm() {
            return ((JwtHmacKeyFormat)this.instance).getAlgorithm();
        }

        @Override  // com.google.crypto.tink.proto.JwtHmacKeyFormatOrBuilder
        public int getAlgorithmValue() {
            return ((JwtHmacKeyFormat)this.instance).getAlgorithmValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.JwtHmacKeyFormatOrBuilder
        public int getKeySize() {
            return ((JwtHmacKeyFormat)this.instance).getKeySize();
        }

        @Override  // com.google.crypto.tink.proto.JwtHmacKeyFormatOrBuilder
        public int getVersion() {
            return ((JwtHmacKeyFormat)this.instance).getVersion();
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

        public Builder setAlgorithm(JwtHmacAlgorithm jwtHmacAlgorithm0) {
            this.copyOnWrite();
            ((JwtHmacKeyFormat)this.instance).setAlgorithm(jwtHmacAlgorithm0);
            return this;
        }

        public Builder setAlgorithmValue(int v) {
            this.copyOnWrite();
            ((JwtHmacKeyFormat)this.instance).setAlgorithmValue(v);
            return this;
        }

        public Builder setKeySize(int v) {
            this.copyOnWrite();
            ((JwtHmacKeyFormat)this.instance).setKeySize(v);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((JwtHmacKeyFormat)this.instance).setVersion(v);
            return this;
        }
    }

    public static final int ALGORITHM_FIELD_NUMBER = 2;
    private static final JwtHmacKeyFormat DEFAULT_INSTANCE = null;
    public static final int KEY_SIZE_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private int algorithm_;
    private int keySize_;
    private int version_;

    static {
        JwtHmacKeyFormat jwtHmacKeyFormat0 = new JwtHmacKeyFormat();
        JwtHmacKeyFormat.DEFAULT_INSTANCE = jwtHmacKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(JwtHmacKeyFormat.class, jwtHmacKeyFormat0);
    }

    private void clearAlgorithm() {
        this.algorithm_ = 0;
    }

    private void clearKeySize() {
        this.keySize_ = 0;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.JwtHmacKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new JwtHmacKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return JwtHmacKeyFormat.newMessageInfo(JwtHmacKeyFormat.DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\f\u0003\u000B", new Object[]{"version_", "algorithm_", "keySize_"});
            }
            case 4: {
                return JwtHmacKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = JwtHmacKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = JwtHmacKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = JwtHmacKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(JwtHmacKeyFormat.DEFAULT_INSTANCE);
                            JwtHmacKeyFormat.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.JwtHmacKeyFormatOrBuilder
    public JwtHmacAlgorithm getAlgorithm() {
        JwtHmacAlgorithm jwtHmacAlgorithm0 = JwtHmacAlgorithm.forNumber(this.algorithm_);
        return jwtHmacAlgorithm0 == null ? JwtHmacAlgorithm.UNRECOGNIZED : jwtHmacAlgorithm0;
    }

    @Override  // com.google.crypto.tink.proto.JwtHmacKeyFormatOrBuilder
    public int getAlgorithmValue() {
        return this.algorithm_;
    }

    public static JwtHmacKeyFormat getDefaultInstance() {
        return JwtHmacKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.JwtHmacKeyFormatOrBuilder
    public int getKeySize() {
        return this.keySize_;
    }

    @Override  // com.google.crypto.tink.proto.JwtHmacKeyFormatOrBuilder
    public int getVersion() {
        return this.version_;
    }

    public static Builder newBuilder() {
        return (Builder)JwtHmacKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(JwtHmacKeyFormat jwtHmacKeyFormat0) {
        return (Builder)JwtHmacKeyFormat.DEFAULT_INSTANCE.createBuilder(jwtHmacKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static JwtHmacKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (JwtHmacKeyFormat)JwtHmacKeyFormat.parseDelimitedFrom(JwtHmacKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtHmacKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtHmacKeyFormat)JwtHmacKeyFormat.parseDelimitedFrom(JwtHmacKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtHmacKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (JwtHmacKeyFormat)GeneratedMessageLite.parseFrom(JwtHmacKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static JwtHmacKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtHmacKeyFormat)GeneratedMessageLite.parseFrom(JwtHmacKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static JwtHmacKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (JwtHmacKeyFormat)GeneratedMessageLite.parseFrom(JwtHmacKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static JwtHmacKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtHmacKeyFormat)GeneratedMessageLite.parseFrom(JwtHmacKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static JwtHmacKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (JwtHmacKeyFormat)GeneratedMessageLite.parseFrom(JwtHmacKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtHmacKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtHmacKeyFormat)GeneratedMessageLite.parseFrom(JwtHmacKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtHmacKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (JwtHmacKeyFormat)GeneratedMessageLite.parseFrom(JwtHmacKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static JwtHmacKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtHmacKeyFormat)GeneratedMessageLite.parseFrom(JwtHmacKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static JwtHmacKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (JwtHmacKeyFormat)GeneratedMessageLite.parseFrom(JwtHmacKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static JwtHmacKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtHmacKeyFormat)GeneratedMessageLite.parseFrom(JwtHmacKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return JwtHmacKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setAlgorithm(JwtHmacAlgorithm jwtHmacAlgorithm0) {
        this.algorithm_ = jwtHmacAlgorithm0.getNumber();
    }

    private void setAlgorithmValue(int v) {
        this.algorithm_ = v;
    }

    private void setKeySize(int v) {
        this.keySize_ = v;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

