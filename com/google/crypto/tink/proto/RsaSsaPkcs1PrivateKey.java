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

public final class RsaSsaPkcs1PrivateKey extends GeneratedMessageLite implements RsaSsaPkcs1PrivateKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements RsaSsaPkcs1PrivateKeyOrBuilder {
        private Builder() {
            super(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKey.1 rsaSsaPkcs1PrivateKey$10) {
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

        public Builder clearCrt() {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).clearCrt();
            return this;
        }

        public Builder clearD() {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).clearD();
            return this;
        }

        public Builder clearDp() {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).clearDp();
            return this;
        }

        public Builder clearDq() {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).clearDq();
            return this;
        }

        public Builder clearP() {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).clearP();
            return this;
        }

        public Builder clearPublicKey() {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).clearPublicKey();
            return this;
        }

        public Builder clearQ() {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).clearQ();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).clearVersion();
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

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
        public ByteString getCrt() {
            return ((RsaSsaPkcs1PrivateKey)this.instance).getCrt();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
        public ByteString getD() {
            return ((RsaSsaPkcs1PrivateKey)this.instance).getD();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
        public ByteString getDp() {
            return ((RsaSsaPkcs1PrivateKey)this.instance).getDp();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
        public ByteString getDq() {
            return ((RsaSsaPkcs1PrivateKey)this.instance).getDq();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
        public ByteString getP() {
            return ((RsaSsaPkcs1PrivateKey)this.instance).getP();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
        public RsaSsaPkcs1PublicKey getPublicKey() {
            return ((RsaSsaPkcs1PrivateKey)this.instance).getPublicKey();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
        public ByteString getQ() {
            return ((RsaSsaPkcs1PrivateKey)this.instance).getQ();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
        public int getVersion() {
            return ((RsaSsaPkcs1PrivateKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
        public boolean hasPublicKey() {
            return ((RsaSsaPkcs1PrivateKey)this.instance).hasPublicKey();
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

        public Builder mergePublicKey(RsaSsaPkcs1PublicKey rsaSsaPkcs1PublicKey0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).mergePublicKey(rsaSsaPkcs1PublicKey0);
            return this;
        }

        public Builder setCrt(ByteString byteString0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).setCrt(byteString0);
            return this;
        }

        public Builder setD(ByteString byteString0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).setD(byteString0);
            return this;
        }

        public Builder setDp(ByteString byteString0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).setDp(byteString0);
            return this;
        }

        public Builder setDq(ByteString byteString0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).setDq(byteString0);
            return this;
        }

        public Builder setP(ByteString byteString0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).setP(byteString0);
            return this;
        }

        public Builder setPublicKey(com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey.Builder rsaSsaPkcs1PublicKey$Builder0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).setPublicKey(((RsaSsaPkcs1PublicKey)rsaSsaPkcs1PublicKey$Builder0.build()));
            return this;
        }

        public Builder setPublicKey(RsaSsaPkcs1PublicKey rsaSsaPkcs1PublicKey0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).setPublicKey(rsaSsaPkcs1PublicKey0);
            return this;
        }

        public Builder setQ(ByteString byteString0) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).setQ(byteString0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((RsaSsaPkcs1PrivateKey)this.instance).setVersion(v);
            return this;
        }
    }

    public static final int CRT_FIELD_NUMBER = 8;
    private static final RsaSsaPkcs1PrivateKey DEFAULT_INSTANCE = null;
    public static final int DP_FIELD_NUMBER = 6;
    public static final int DQ_FIELD_NUMBER = 7;
    public static final int D_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int PUBLIC_KEY_FIELD_NUMBER = 2;
    public static final int P_FIELD_NUMBER = 4;
    public static final int Q_FIELD_NUMBER = 5;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString crt_;
    private ByteString d_;
    private ByteString dp_;
    private ByteString dq_;
    private ByteString p_;
    private RsaSsaPkcs1PublicKey publicKey_;
    private ByteString q_;
    private int version_;

    static {
        RsaSsaPkcs1PrivateKey rsaSsaPkcs1PrivateKey0 = new RsaSsaPkcs1PrivateKey();
        RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE = rsaSsaPkcs1PrivateKey0;
        GeneratedMessageLite.registerDefaultInstance(RsaSsaPkcs1PrivateKey.class, rsaSsaPkcs1PrivateKey0);
    }

    private RsaSsaPkcs1PrivateKey() {
        this.d_ = ByteString.EMPTY;
        this.p_ = ByteString.EMPTY;
        this.q_ = ByteString.EMPTY;
        this.dp_ = ByteString.EMPTY;
        this.dq_ = ByteString.EMPTY;
        this.crt_ = ByteString.EMPTY;
    }

    private void clearCrt() {
        this.crt_ = RsaSsaPkcs1PrivateKey.getDefaultInstance().getCrt();
    }

    private void clearD() {
        this.d_ = RsaSsaPkcs1PrivateKey.getDefaultInstance().getD();
    }

    private void clearDp() {
        this.dp_ = RsaSsaPkcs1PrivateKey.getDefaultInstance().getDp();
    }

    private void clearDq() {
        this.dq_ = RsaSsaPkcs1PrivateKey.getDefaultInstance().getDq();
    }

    private void clearP() {
        this.p_ = RsaSsaPkcs1PrivateKey.getDefaultInstance().getP();
    }

    private void clearPublicKey() {
        this.publicKey_ = null;
    }

    private void clearQ() {
        this.q_ = RsaSsaPkcs1PrivateKey.getDefaultInstance().getQ();
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new RsaSsaPkcs1PrivateKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return RsaSsaPkcs1PrivateKey.newMessageInfo(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n\u0004\n\u0005\n\u0006\n\u0007\n\b\n", new Object[]{"version_", "publicKey_", "d_", "p_", "q_", "dp_", "dq_", "crt_"});
            }
            case 4: {
                return RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = RsaSsaPkcs1PrivateKey.PARSER;
                if(parser0 == null) {
                    Class class0 = RsaSsaPkcs1PrivateKey.class;
                    synchronized(class0) {
                        Parser parser1 = RsaSsaPkcs1PrivateKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE);
                            RsaSsaPkcs1PrivateKey.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
    public ByteString getCrt() {
        return this.crt_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
    public ByteString getD() {
        return this.d_;
    }

    public static RsaSsaPkcs1PrivateKey getDefaultInstance() {
        return RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
    public ByteString getDp() {
        return this.dp_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
    public ByteString getDq() {
        return this.dq_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
    public ByteString getP() {
        return this.p_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
    public RsaSsaPkcs1PublicKey getPublicKey() {
        return this.publicKey_ == null ? RsaSsaPkcs1PublicKey.getDefaultInstance() : this.publicKey_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
    public ByteString getQ() {
        return this.q_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKeyOrBuilder
    public boolean hasPublicKey() {
        return this.publicKey_ != null;
    }

    private void mergePublicKey(RsaSsaPkcs1PublicKey rsaSsaPkcs1PublicKey0) {
        rsaSsaPkcs1PublicKey0.getClass();
        if(this.publicKey_ != null && this.publicKey_ != RsaSsaPkcs1PublicKey.getDefaultInstance()) {
            this.publicKey_ = (RsaSsaPkcs1PublicKey)((com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey.Builder)RsaSsaPkcs1PublicKey.newBuilder(this.publicKey_).mergeFrom(rsaSsaPkcs1PublicKey0)).buildPartial();
            return;
        }
        this.publicKey_ = rsaSsaPkcs1PublicKey0;
    }

    public static Builder newBuilder() {
        return (Builder)RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RsaSsaPkcs1PrivateKey rsaSsaPkcs1PrivateKey0) {
        return (Builder)RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE.createBuilder(rsaSsaPkcs1PrivateKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static RsaSsaPkcs1PrivateKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (RsaSsaPkcs1PrivateKey)RsaSsaPkcs1PrivateKey.parseDelimitedFrom(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static RsaSsaPkcs1PrivateKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPkcs1PrivateKey)RsaSsaPkcs1PrivateKey.parseDelimitedFrom(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1PrivateKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1PrivateKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, byteString0);
    }

    public static RsaSsaPkcs1PrivateKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1PrivateKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1PrivateKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (RsaSsaPkcs1PrivateKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static RsaSsaPkcs1PrivateKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPkcs1PrivateKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1PrivateKey parseFrom(InputStream inputStream0) throws IOException {
        return (RsaSsaPkcs1PrivateKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static RsaSsaPkcs1PrivateKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RsaSsaPkcs1PrivateKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1PrivateKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1PrivateKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static RsaSsaPkcs1PrivateKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1PrivateKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static RsaSsaPkcs1PrivateKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1PrivateKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, arr_b);
    }

    public static RsaSsaPkcs1PrivateKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1PrivateKey)GeneratedMessageLite.parseFrom(RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return RsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setCrt(ByteString byteString0) {
        byteString0.getClass();
        this.crt_ = byteString0;
    }

    private void setD(ByteString byteString0) {
        byteString0.getClass();
        this.d_ = byteString0;
    }

    private void setDp(ByteString byteString0) {
        byteString0.getClass();
        this.dp_ = byteString0;
    }

    private void setDq(ByteString byteString0) {
        byteString0.getClass();
        this.dq_ = byteString0;
    }

    private void setP(ByteString byteString0) {
        byteString0.getClass();
        this.p_ = byteString0;
    }

    private void setPublicKey(RsaSsaPkcs1PublicKey rsaSsaPkcs1PublicKey0) {
        rsaSsaPkcs1PublicKey0.getClass();
        this.publicKey_ = rsaSsaPkcs1PublicKey0;
    }

    private void setQ(ByteString byteString0) {
        byteString0.getClass();
        this.q_ = byteString0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

