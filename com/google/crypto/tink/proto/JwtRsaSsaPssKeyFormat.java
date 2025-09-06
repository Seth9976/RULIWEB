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

public final class JwtRsaSsaPssKeyFormat extends GeneratedMessageLite implements JwtRsaSsaPssKeyFormatOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements JwtRsaSsaPssKeyFormatOrBuilder {
        private Builder() {
            super(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormat.1 jwtRsaSsaPssKeyFormat$10) {
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
            ((JwtRsaSsaPssKeyFormat)this.instance).clearAlgorithm();
            return this;
        }

        public Builder clearModulusSizeInBits() {
            this.copyOnWrite();
            ((JwtRsaSsaPssKeyFormat)this.instance).clearModulusSizeInBits();
            return this;
        }

        public Builder clearPublicExponent() {
            this.copyOnWrite();
            ((JwtRsaSsaPssKeyFormat)this.instance).clearPublicExponent();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((JwtRsaSsaPssKeyFormat)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormatOrBuilder
        public JwtRsaSsaPssAlgorithm getAlgorithm() {
            return ((JwtRsaSsaPssKeyFormat)this.instance).getAlgorithm();
        }

        @Override  // com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormatOrBuilder
        public int getAlgorithmValue() {
            return ((JwtRsaSsaPssKeyFormat)this.instance).getAlgorithmValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormatOrBuilder
        public int getModulusSizeInBits() {
            return ((JwtRsaSsaPssKeyFormat)this.instance).getModulusSizeInBits();
        }

        @Override  // com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormatOrBuilder
        public ByteString getPublicExponent() {
            return ((JwtRsaSsaPssKeyFormat)this.instance).getPublicExponent();
        }

        @Override  // com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormatOrBuilder
        public int getVersion() {
            return ((JwtRsaSsaPssKeyFormat)this.instance).getVersion();
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

        public Builder setAlgorithm(JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm0) {
            this.copyOnWrite();
            ((JwtRsaSsaPssKeyFormat)this.instance).setAlgorithm(jwtRsaSsaPssAlgorithm0);
            return this;
        }

        public Builder setAlgorithmValue(int v) {
            this.copyOnWrite();
            ((JwtRsaSsaPssKeyFormat)this.instance).setAlgorithmValue(v);
            return this;
        }

        public Builder setModulusSizeInBits(int v) {
            this.copyOnWrite();
            ((JwtRsaSsaPssKeyFormat)this.instance).setModulusSizeInBits(v);
            return this;
        }

        public Builder setPublicExponent(ByteString byteString0) {
            this.copyOnWrite();
            ((JwtRsaSsaPssKeyFormat)this.instance).setPublicExponent(byteString0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((JwtRsaSsaPssKeyFormat)this.instance).setVersion(v);
            return this;
        }
    }

    public static final int ALGORITHM_FIELD_NUMBER = 2;
    private static final JwtRsaSsaPssKeyFormat DEFAULT_INSTANCE = null;
    public static final int MODULUS_SIZE_IN_BITS_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int PUBLIC_EXPONENT_FIELD_NUMBER = 4;
    public static final int VERSION_FIELD_NUMBER = 1;
    private int algorithm_;
    private int modulusSizeInBits_;
    private ByteString publicExponent_;
    private int version_;

    static {
        JwtRsaSsaPssKeyFormat jwtRsaSsaPssKeyFormat0 = new JwtRsaSsaPssKeyFormat();
        JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE = jwtRsaSsaPssKeyFormat0;
        GeneratedMessageLite.registerDefaultInstance(JwtRsaSsaPssKeyFormat.class, jwtRsaSsaPssKeyFormat0);
    }

    private JwtRsaSsaPssKeyFormat() {
        this.publicExponent_ = ByteString.EMPTY;
    }

    private void clearAlgorithm() {
        this.algorithm_ = 0;
    }

    private void clearModulusSizeInBits() {
        this.modulusSizeInBits_ = 0;
    }

    private void clearPublicExponent() {
        this.publicExponent_ = JwtRsaSsaPssKeyFormat.getDefaultInstance().getPublicExponent();
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormat.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new JwtRsaSsaPssKeyFormat();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return JwtRsaSsaPssKeyFormat.newMessageInfo(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000B\u0002\f\u0003\u000B\u0004\n", new Object[]{"version_", "algorithm_", "modulusSizeInBits_", "publicExponent_"});
            }
            case 4: {
                return JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = JwtRsaSsaPssKeyFormat.PARSER;
                if(parser0 == null) {
                    Class class0 = JwtRsaSsaPssKeyFormat.class;
                    synchronized(class0) {
                        Parser parser1 = JwtRsaSsaPssKeyFormat.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE);
                            JwtRsaSsaPssKeyFormat.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormatOrBuilder
    public JwtRsaSsaPssAlgorithm getAlgorithm() {
        JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm0 = JwtRsaSsaPssAlgorithm.forNumber(this.algorithm_);
        return jwtRsaSsaPssAlgorithm0 == null ? JwtRsaSsaPssAlgorithm.UNRECOGNIZED : jwtRsaSsaPssAlgorithm0;
    }

    @Override  // com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormatOrBuilder
    public int getAlgorithmValue() {
        return this.algorithm_;
    }

    public static JwtRsaSsaPssKeyFormat getDefaultInstance() {
        return JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormatOrBuilder
    public int getModulusSizeInBits() {
        return this.modulusSizeInBits_;
    }

    @Override  // com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormatOrBuilder
    public ByteString getPublicExponent() {
        return this.publicExponent_;
    }

    @Override  // com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormatOrBuilder
    public int getVersion() {
        return this.version_;
    }

    public static Builder newBuilder() {
        return (Builder)JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(JwtRsaSsaPssKeyFormat jwtRsaSsaPssKeyFormat0) {
        return (Builder)JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE.createBuilder(jwtRsaSsaPssKeyFormat0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static JwtRsaSsaPssKeyFormat parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (JwtRsaSsaPssKeyFormat)JwtRsaSsaPssKeyFormat.parseDelimitedFrom(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtRsaSsaPssKeyFormat parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtRsaSsaPssKeyFormat)JwtRsaSsaPssKeyFormat.parseDelimitedFrom(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtRsaSsaPssKeyFormat parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (JwtRsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, byteString0);
    }

    public static JwtRsaSsaPssKeyFormat parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtRsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static JwtRsaSsaPssKeyFormat parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (JwtRsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static JwtRsaSsaPssKeyFormat parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtRsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static JwtRsaSsaPssKeyFormat parseFrom(InputStream inputStream0) throws IOException {
        return (JwtRsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, inputStream0);
    }

    public static JwtRsaSsaPssKeyFormat parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (JwtRsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static JwtRsaSsaPssKeyFormat parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (JwtRsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static JwtRsaSsaPssKeyFormat parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtRsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static JwtRsaSsaPssKeyFormat parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (JwtRsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, arr_b);
    }

    public static JwtRsaSsaPssKeyFormat parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (JwtRsaSsaPssKeyFormat)GeneratedMessageLite.parseFrom(JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return JwtRsaSsaPssKeyFormat.DEFAULT_INSTANCE.getParserForType();
    }

    private void setAlgorithm(JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm0) {
        this.algorithm_ = jwtRsaSsaPssAlgorithm0.getNumber();
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

