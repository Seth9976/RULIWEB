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

public final class JwtRsaSsaPkcs1KeyFormat extends GeneratedMessageLite implements JwtRsaSsaPkcs1KeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements JwtRsaSsaPkcs1KeyFormatOrBuilder {
        private Builder() {
            super(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormat.1 jwtRsaSsaPkcs1KeyFormat$10) {
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
            ((JwtRsaSsaPkcs1KeyFormat)this.instance).clearAlgorithm();
            return this;
        }

        public Builder clearModulusSizeInBits() {
            this.copyOnWrite();
            ((JwtRsaSsaPkcs1KeyFormat)this.instance).clearModulusSizeInBits();
            return this;
        }

        public Builder clearPublicExponent() {
            this.copyOnWrite();
            ((JwtRsaSsaPkcs1KeyFormat)this.instance).clearPublicExponent();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((JwtRsaSsaPkcs1KeyFormat)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormatOrBuilder
        public JwtRsaSsaPkcs1Algorithm getAlgorithm() {
            return ((JwtRsaSsaPkcs1KeyFormat)this.instance).getAlgorithm();
        }

        @Override  // com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormatOrBuilder
        public int getAlgorithmValue() {
            return ((JwtRsaSsaPkcs1KeyFormat)this.instance).getAlgorithmValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormatOrBuilder
        public int getModulusSizeInBits() {
            return ((JwtRsaSsaPkcs1KeyFormat)this.instance).getModulusSizeInBits();
        }

        @Override  // com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormatOrBuilder
        public ByteString getPublicExponent() {
            return ((JwtRsaSsaPkcs1KeyFormat)this.instance).getPublicExponent();
        }

        @Override  // com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormatOrBuilder
        public int getVersion() {
            return ((JwtRsaSsaPkcs1KeyFormat)this.instance).getVersion();
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

        public Builder setAlgorithm(JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm0) {
            this.copyOnWrite();
            ((JwtRsaSsaPkcs1KeyFormat)this.instance).setAlgorithm(jwtRsaSsaPkcs1Algorithm0);
            return this;
        }

        public Builder setAlgorithmValue(int v) {
            this.copyOnWrite();
            ((JwtRsaSsaPkcs1KeyFormat)this.instance).setAlgorithmValue(v);
            return this;
        }

        public Builder setModulusSizeInBits(int v) {
            this.copyOnWrite();
            ((JwtRsaSsaPkcs1KeyFormat)this.instance).setModulusSizeInBits(v);
            return this;
        }

        public Builder setPublicExponent(ByteString byteString0) {
            this.copyOnWrite();
            ((JwtRsaSsaPkcs1KeyFormat)this.instance).setPublicExponent(byteString0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((JwtRsaSsaPkcs1KeyFormat)this.instance).setVersion(v);
            return this;
        }
    }

    public static final int ALGORITHM_FIELD_NUMBER = 2;
    private static final JwtRsaSsaPkcs1KeyFormat DEFAULT_INSTANCE = null;
    public static final int MODULUS_SIZE_IN_BITS_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int PUBLIC_EXPONENT_FIELD_NUMBER = 4;
    public static final int VERSION_FIELD_NUMBER = 1;
    private int algorithm_;
    private int modulusSizeInBits_;
    private ByteString publicExponent_;
    private int version_;

    static {
        JwtRsaSsaPkcs1KeyFormat jwtRsaSsaPkcs1KeyFormat0 = new JwtRsaSsaPkcs1KeyFormat();
        JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE = jwtRsaSsaPkcs1KeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(JwtRsaSsaPkcs1KeyFormat.class, jwtRsaSsaPkcs1KeyFormat0);
    }

    private JwtRsaSsaPkcs1KeyFormat() {
        this.publicExponent_ = ByteString.EMPTY;
    }

    private void clearAlgorithm() {
        this.algorithm_ = 0;
    }

    private void clearModulusSizeInBits() {
        this.modulusSizeInBits_ = 0;
    }

    private void clearPublicExponent() {
        this.publicExponent_ = JwtRsaSsaPkcs1KeyFormat.getDefaultInstance().getPublicExponent();
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new JwtRsaSsaPkcs1KeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return JwtRsaSsaPkcs1KeyFormat.newMessageInfo(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000B\u0002\f\u0003\u000B\u0004\n", new Object[]{"version_", "algorithm_", "modulusSizeInBits_", "publicExponent_"});
            }
            case 4: {
                return JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = JwtRsaSsaPkcs1KeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = JwtRsaSsaPkcs1KeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = JwtRsaSsaPkcs1KeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE);
                            JwtRsaSsaPkcs1KeyFormat.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormatOrBuilder
    public JwtRsaSsaPkcs1Algorithm getAlgorithm() {
        JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm0 = JwtRsaSsaPkcs1Algorithm.forNumber(this.algorithm_);
        return jwtRsaSsaPkcs1Algorithm0 == null ? JwtRsaSsaPkcs1Algorithm.UNRECOGNIZED : jwtRsaSsaPkcs1Algorithm0;
    }

    @Override  // com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormatOrBuilder
    public int getAlgorithmValue() {
        return this.algorithm_;
    }

    public static JwtRsaSsaPkcs1KeyFormat getDefaultInstance() {
        return JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormatOrBuilder
    public int getModulusSizeInBits() {
        return this.modulusSizeInBits_;
    }

    @Override  // com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormatOrBuilder
    public ByteString getPublicExponent() {
        return this.publicExponent_;
    }

    @Override  // com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormatOrBuilder
    public int getVersion() {
        return this.version_;
    }

    public static Builder newBuilder() {
        return (Builder)JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(JwtRsaSsaPkcs1KeyFormat jwtRsaSsaPkcs1KeyFormat0) {
        return (Builder)JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE.createBuilder(jwtRsaSsaPkcs1KeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static JwtRsaSsaPkcs1KeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (JwtRsaSsaPkcs1KeyFormat)JwtRsaSsaPkcs1KeyFormat.parseDelimitedFrom(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtRsaSsaPkcs1KeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtRsaSsaPkcs1KeyFormat)JwtRsaSsaPkcs1KeyFormat.parseDelimitedFrom(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtRsaSsaPkcs1KeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (JwtRsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static JwtRsaSsaPkcs1KeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtRsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static JwtRsaSsaPkcs1KeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (JwtRsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static JwtRsaSsaPkcs1KeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtRsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static JwtRsaSsaPkcs1KeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (JwtRsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtRsaSsaPkcs1KeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtRsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtRsaSsaPkcs1KeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (JwtRsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static JwtRsaSsaPkcs1KeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtRsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static JwtRsaSsaPkcs1KeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (JwtRsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static JwtRsaSsaPkcs1KeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtRsaSsaPkcs1KeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return JwtRsaSsaPkcs1KeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setAlgorithm(JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm0) {
        this.algorithm_ = jwtRsaSsaPkcs1Algorithm0.getNumber();
    }

    private void setAlgorithmValue(int v) {
        this.algorithm_ = v;
    }

    private void setModulusSizeInBits(int v) {
        this.modulusSizeInBits_ = v;
    }

    private void setPublicExponent(ByteString byteString0) {
        byteString0.getClass();
        this.publicExponent_ = byteString0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

