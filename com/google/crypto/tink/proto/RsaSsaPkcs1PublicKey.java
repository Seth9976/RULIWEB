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

public final class RsaSsaPkcs1PublicKey extends GeneratedMessageLite implements RsaSsaPkcs1PublicKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements RsaSsaPkcs1PublicKeyOrBuilder {
        private Builder() {
            super(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey.1 rsaSsaPkcs1PublicKey$10) {
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

        public Builder clearE() {
            this.copyOnWrite();
            ((RsaSsaPkcs1PublicKey)this.instance).clearE();
            return this;
        }

        public Builder clearN() {
            this.copyOnWrite();
            ((RsaSsaPkcs1PublicKey)this.instance).clearN();
            return this;
        }

        public Builder clearParams() {
            this.copyOnWrite();
            ((RsaSsaPkcs1PublicKey)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((RsaSsaPkcs1PublicKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PublicKeyOrBuilder
        public ByteString getE() {
            return ((RsaSsaPkcs1PublicKey)this.instance).getE();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PublicKeyOrBuilder
        public ByteString getN() {
            return ((RsaSsaPkcs1PublicKey)this.instance).getN();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PublicKeyOrBuilder
        public RsaSsaPkcs1Params getParams() {
            return ((RsaSsaPkcs1PublicKey)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PublicKeyOrBuilder
        public int getVersion() {
            return ((RsaSsaPkcs1PublicKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PublicKeyOrBuilder
        public boolean hasParams() {
            return ((RsaSsaPkcs1PublicKey)this.instance).hasParams();
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

        public Builder mergeParams(RsaSsaPkcs1Params rsaSsaPkcs1Params0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PublicKey)this.instance).mergeParams(rsaSsaPkcs1Params0);
            return this;
        }

        public Builder setE(ByteString byteString0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PublicKey)this.instance).setE(byteString0);
            return this;
        }

        public Builder setN(ByteString byteString0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PublicKey)this.instance).setN(byteString0);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.RsaSsaPkcs1Params.Builder rsaSsaPkcs1Params$Builder0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PublicKey)this.instance).setParams(((RsaSsaPkcs1Params)rsaSsaPkcs1Params$Builder0.build()));
            return this;
        }

        public Builder setParams(RsaSsaPkcs1Params rsaSsaPkcs1Params0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PublicKey)this.instance).setParams(rsaSsaPkcs1Params0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PublicKey)this.instance).setVersion(v);
            return this;
        }
    }

    private static final RsaSsaPkcs1PublicKey DEFAULT_INSTANCE = null;
    public static final int E_FIELD_NUMBER = 4;
    public static final int N_FIELD_NUMBER = 3;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString e_;
    private ByteString n_;
    private RsaSsaPkcs1Params params_;
    private int version_;

    static {
        RsaSsaPkcs1PublicKey rsaSsaPkcs1PublicKey0 = new RsaSsaPkcs1PublicKey();
        RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE = rsaSsaPkcs1PublicKey0;
        GeneratedMessageLite.registerDefaultInstance(RsaSsaPkcs1PublicKey.class, rsaSsaPkcs1PublicKey0);
    }

    private RsaSsaPkcs1PublicKey() {
        this.n_ = ByteString.EMPTY;
        this.e_ = ByteString.EMPTY;
    }

    private void clearE() {
        this.e_ = RsaSsaPkcs1PublicKey.getDefaultInstance().getE();
    }

    private void clearN() {
        this.n_ = RsaSsaPkcs1PublicKey.getDefaultInstance().getN();
    }

    private void clearParams() {
        this.params_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new RsaSsaPkcs1PublicKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return RsaSsaPkcs1PublicKey.newMessageInfo(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n\u0004\n", new Object[]{"version_", "params_", "n_", "e_"});
            }
            case 4: {
                return RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = RsaSsaPkcs1PublicKey.PARSER;
                if(parser0 == null) {
                    Class class0 = RsaSsaPkcs1PublicKey.class;
                    synchronized(class0) {
                        Parser parser1 = RsaSsaPkcs1PublicKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE);
                            RsaSsaPkcs1PublicKey.PARSER = parser1;
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

    public static RsaSsaPkcs1PublicKey getDefaultInstance() {
        return RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PublicKeyOrBuilder
    public ByteString getE() {
        return this.e_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PublicKeyOrBuilder
    public ByteString getN() {
        return this.n_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PublicKeyOrBuilder
    public RsaSsaPkcs1Params getParams() {
        return this.params_ == null ? RsaSsaPkcs1Params.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PublicKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PublicKeyOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(RsaSsaPkcs1Params rsaSsaPkcs1Params0) {
        rsaSsaPkcs1Params0.getClass();
        if(this.params_ != null && this.params_ != RsaSsaPkcs1Params.getDefaultInstance()) {
            this.params_ = (RsaSsaPkcs1Params)((com.google.crypto.tink.proto.RsaSsaPkcs1Params.Builder)RsaSsaPkcs1Params.newBuilder(this.params_).mergeFrom(rsaSsaPkcs1Params0)).buildPartial();
            return;
        }
        this.params_ = rsaSsaPkcs1Params0;
    }

    public static Builder newBuilder() {
        return (Builder)RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RsaSsaPkcs1PublicKey rsaSsaPkcs1PublicKey0) {
        return (Builder)RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE.createBuilder(rsaSsaPkcs1PublicKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static RsaSsaPkcs1PublicKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (RsaSsaPkcs1PublicKey)RsaSsaPkcs1PublicKey.parseDelimitedFrom(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static RsaSsaPkcs1PublicKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPkcs1PublicKey)RsaSsaPkcs1PublicKey.parseDelimitedFrom(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1PublicKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1PublicKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, byteString0);
    }

    public static RsaSsaPkcs1PublicKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1PublicKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1PublicKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (RsaSsaPkcs1PublicKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static RsaSsaPkcs1PublicKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPkcs1PublicKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1PublicKey parseFrom(InputStream inputStream0) throws IOException {
        return (RsaSsaPkcs1PublicKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static RsaSsaPkcs1PublicKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPkcs1PublicKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1PublicKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1PublicKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static RsaSsaPkcs1PublicKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1PublicKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1PublicKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1PublicKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, arr_b);
    }

    public static RsaSsaPkcs1PublicKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1PublicKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return RsaSsaPkcs1PublicKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setE(ByteString byteString0) {
        byteString0.getClass();
        this.e_ = byteString0;
    }

    private void setN(ByteString byteString0) {
        byteString0.getClass();
        this.n_ = byteString0;
    }

    private void setParams(RsaSsaPkcs1Params rsaSsaPkcs1Params0) {
        rsaSsaPkcs1Params0.getClass();
        this.params_ = rsaSsaPkcs1Params0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

