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

public final class EciesAeadHkdfPublicKey extends GeneratedMessageLite implements EciesAeadHkdfPublicKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements EciesAeadHkdfPublicKeyOrBuilder {
        private Builder() {
            super(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.EciesAeadHkdfPublicKey.1 eciesAeadHkdfPublicKey$10) {
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

        public Builder clearParams() {
            this.copyOnWrite();
            ((EciesAeadHkdfPublicKey)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((EciesAeadHkdfPublicKey)this.instance).clearVersion();
            return this;
        }

        public Builder clearX() {
            this.copyOnWrite();
            ((EciesAeadHkdfPublicKey)this.instance).clearX();
            return this;
        }

        public Builder clearY() {
            this.copyOnWrite();
            ((EciesAeadHkdfPublicKey)this.instance).clearY();
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

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPublicKeyOrBuilder
        public EciesAeadHkdfParams getParams() {
            return ((EciesAeadHkdfPublicKey)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPublicKeyOrBuilder
        public int getVersion() {
            return ((EciesAeadHkdfPublicKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPublicKeyOrBuilder
        public ByteString getX() {
            return ((EciesAeadHkdfPublicKey)this.instance).getX();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPublicKeyOrBuilder
        public ByteString getY() {
            return ((EciesAeadHkdfPublicKey)this.instance).getY();
        }

        @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPublicKeyOrBuilder
        public boolean hasParams() {
            return ((EciesAeadHkdfPublicKey)this.instance).hasParams();
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

        public Builder mergeParams(EciesAeadHkdfParams eciesAeadHkdfParams0) {
            this.copyOnWrite();
            ((EciesAeadHkdfPublicKey)this.instance).mergeParams(eciesAeadHkdfParams0);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.EciesAeadHkdfParams.Builder eciesAeadHkdfParams$Builder0) {
            this.copyOnWrite();
            ((EciesAeadHkdfPublicKey)this.instance).setParams(((EciesAeadHkdfParams)eciesAeadHkdfParams$Builder0.build()));
            return this;
        }

        public Builder setParams(EciesAeadHkdfParams eciesAeadHkdfParams0) {
            this.copyOnWrite();
            ((EciesAeadHkdfPublicKey)this.instance).setParams(eciesAeadHkdfParams0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((EciesAeadHkdfPublicKey)this.instance).setVersion(v);
            return this;
        }

        public Builder setX(ByteString byteString0) {
            this.copyOnWrite();
            ((EciesAeadHkdfPublicKey)this.instance).setX(byteString0);
            return this;
        }

        public Builder setY(ByteString byteString0) {
            this.copyOnWrite();
            ((EciesAeadHkdfPublicKey)this.instance).setY(byteString0);
            return this;
        }
    }

    private static final EciesAeadHkdfPublicKey DEFAULT_INSTANCE = null;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    public static final int X_FIELD_NUMBER = 3;
    public static final int Y_FIELD_NUMBER = 4;
    private EciesAeadHkdfParams params_;
    private int version_;
    private ByteString x_;
    private ByteString y_;

    static {
        EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey0 = new EciesAeadHkdfPublicKey();
        EciesAeadHkdfPublicKey.DEFAULT_INSTANCE = eciesAeadHkdfPublicKey0;
        GeneratedMessageLite.registerDefaultInstance(EciesAeadHkdfPublicKey.class, eciesAeadHkdfPublicKey0);
    }

    private EciesAeadHkdfPublicKey() {
        this.x_ = ByteString.EMPTY;
        this.y_ = ByteString.EMPTY;
    }

    private void clearParams() {
        this.params_ = null;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    private void clearX() {
        this.x_ = EciesAeadHkdfPublicKey.getDefaultInstance().getX();
    }

    private void clearY() {
        this.y_ = EciesAeadHkdfPublicKey.getDefaultInstance().getY();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.EciesAeadHkdfPublicKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new EciesAeadHkdfPublicKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return EciesAeadHkdfPublicKey.newMessageInfo(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n\u0004\n", new Object[]{"version_", "params_", "x_", "y_"});
            }
            case 4: {
                return EciesAeadHkdfPublicKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = EciesAeadHkdfPublicKey.PARSER;
                if(parser0 == null) {
                    Class class0 = EciesAeadHkdfPublicKey.class;
                    synchronized(class0) {
                        Parser parser1 = EciesAeadHkdfPublicKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE);
                            EciesAeadHkdfPublicKey.PARSER = parser1;
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

    public static EciesAeadHkdfPublicKey getDefaultInstance() {
        return EciesAeadHkdfPublicKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPublicKeyOrBuilder
    public EciesAeadHkdfParams getParams() {
        return this.params_ == null ? EciesAeadHkdfParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPublicKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPublicKeyOrBuilder
    public ByteString getX() {
        return this.x_;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPublicKeyOrBuilder
    public ByteString getY() {
        return this.y_;
    }

    @Override  // com.google.crypto.tink.proto.EciesAeadHkdfPublicKeyOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(EciesAeadHkdfParams eciesAeadHkdfParams0) {
        eciesAeadHkdfParams0.getClass();
        if(this.params_ != null && this.params_ != EciesAeadHkdfParams.getDefaultInstance()) {
            this.params_ = (EciesAeadHkdfParams)((com.google.crypto.tink.proto.EciesAeadHkdfParams.Builder)EciesAeadHkdfParams.newBuilder(this.params_).mergeFrom(eciesAeadHkdfParams0)).buildPartial();
            return;
        }
        this.params_ = eciesAeadHkdfParams0;
    }

    public static Builder newBuilder() {
        return (Builder)EciesAeadHkdfPublicKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey0) {
        return (Builder)EciesAeadHkdfPublicKey.DEFAULT_INSTANCE.createBuilder(eciesAeadHkdfPublicKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static EciesAeadHkdfPublicKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (EciesAeadHkdfPublicKey)EciesAeadHkdfPublicKey.parseDelimitedFrom(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static EciesAeadHkdfPublicKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadHkdfPublicKey)EciesAeadHkdfPublicKey.parseDelimitedFrom(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfPublicKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPublicKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, byteString0);
    }

    public static EciesAeadHkdfPublicKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPublicKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfPublicKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (EciesAeadHkdfPublicKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static EciesAeadHkdfPublicKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadHkdfPublicKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfPublicKey parseFrom(InputStream inputStream0) throws IOException {
        return (EciesAeadHkdfPublicKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static EciesAeadHkdfPublicKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EciesAeadHkdfPublicKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfPublicKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPublicKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static EciesAeadHkdfPublicKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPublicKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static EciesAeadHkdfPublicKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPublicKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, arr_b);
    }

    public static EciesAeadHkdfPublicKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPublicKey)GeneratedMessageLite.parseFrom(EciesAeadHkdfPublicKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return EciesAeadHkdfPublicKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setParams(EciesAeadHkdfParams eciesAeadHkdfParams0) {
        eciesAeadHkdfParams0.getClass();
        this.params_ = eciesAeadHkdfParams0;
    }

    private void setVersion(int v) {
        this.version_ = v;
    }

    private void setX(ByteString byteString0) {
        byteString0.getClass();
        this.x_ = byteString0;
    }

    private void setY(ByteString byteString0) {
        byteString0.getClass();
        this.y_ = byteString0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

