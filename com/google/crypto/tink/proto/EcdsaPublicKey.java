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

public final class EcdsaPublicKey extends GeneratedMessageLite implements EcdsaPublicKeyOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements EcdsaPublicKeyOrBuilder {
        private Builder() {
            super(EcdsaPublicKey.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.EcdsaPublicKey.1 ecdsaPublicKey$10) {
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
            ((EcdsaPublicKey)this.instance).clearParams();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((EcdsaPublicKey)this.instance).clearVersion();
            return this;
        }

        public Builder clearX() {
            this.copyOnWrite();
            ((EcdsaPublicKey)this.instance).clearX();
            return this;
        }

        public Builder clearY() {
            this.copyOnWrite();
            ((EcdsaPublicKey)this.instance).clearY();
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

        @Override  // com.google.crypto.tink.proto.EcdsaPublicKeyOrBuilder
        public EcdsaParams getParams() {
            return ((EcdsaPublicKey)this.instance).getParams();
        }

        @Override  // com.google.crypto.tink.proto.EcdsaPublicKeyOrBuilder
        public int getVersion() {
            return ((EcdsaPublicKey)this.instance).getVersion();
        }

        @Override  // com.google.crypto.tink.proto.EcdsaPublicKeyOrBuilder
        public ByteString getX() {
            return ((EcdsaPublicKey)this.instance).getX();
        }

        @Override  // com.google.crypto.tink.proto.EcdsaPublicKeyOrBuilder
        public ByteString getY() {
            return ((EcdsaPublicKey)this.instance).getY();
        }

        @Override  // com.google.crypto.tink.proto.EcdsaPublicKeyOrBuilder
        public boolean hasParams() {
            return ((EcdsaPublicKey)this.instance).hasParams();
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

        public Builder mergeParams(EcdsaParams ecdsaParams0) {
            this.copyOnWrite();
            ((EcdsaPublicKey)this.instance).mergeParams(ecdsaParams0);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.EcdsaParams.Builder ecdsaParams$Builder0) {
            this.copyOnWrite();
            ((EcdsaPublicKey)this.instance).setParams(((EcdsaParams)ecdsaParams$Builder0.build()));
            return this;
        }

        public Builder setParams(EcdsaParams ecdsaParams0) {
            this.copyOnWrite();
            ((EcdsaPublicKey)this.instance).setParams(ecdsaParams0);
            return this;
        }

        public Builder setVersion(int v) {
            this.copyOnWrite();
            ((EcdsaPublicKey)this.instance).setVersion(v);
            return this;
        }

        public Builder setX(ByteString byteString0) {
            this.copyOnWrite();
            ((EcdsaPublicKey)this.instance).setX(byteString0);
            return this;
        }

        public Builder setY(ByteString byteString0) {
            this.copyOnWrite();
            ((EcdsaPublicKey)this.instance).setY(byteString0);
            return this;
        }
    }

    private static final EcdsaPublicKey DEFAULT_INSTANCE = null;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    public static final int X_FIELD_NUMBER = 3;
    public static final int Y_FIELD_NUMBER = 4;
    private EcdsaParams params_;
    private int version_;
    private ByteString x_;
    private ByteString y_;

    static {
        EcdsaPublicKey ecdsaPublicKey0 = new EcdsaPublicKey();
        EcdsaPublicKey.DEFAULT_INSTANCE = ecdsaPublicKey0;
        GeneratedMessageLite.registerDefaultInstance(EcdsaPublicKey.class, ecdsaPublicKey0);
    }

    private EcdsaPublicKey() {
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
        this.x_ = EcdsaPublicKey.getDefaultInstance().getX();
    }

    private void clearY() {
        this.y_ = EcdsaPublicKey.getDefaultInstance().getY();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.EcdsaPublicKey.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new EcdsaPublicKey();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return EcdsaPublicKey.newMessageInfo(EcdsaPublicKey.DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n\u0004\n", new Object[]{"version_", "params_", "x_", "y_"});
            }
            case 4: {
                return EcdsaPublicKey.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = EcdsaPublicKey.PARSER;
                if(parser0 == null) {
                    Class class0 = EcdsaPublicKey.class;
                    synchronized(class0) {
                        Parser parser1 = EcdsaPublicKey.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(EcdsaPublicKey.DEFAULT_INSTANCE);
                            EcdsaPublicKey.PARSER = parser1;
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

    public static EcdsaPublicKey getDefaultInstance() {
        return EcdsaPublicKey.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.EcdsaPublicKeyOrBuilder
    public EcdsaParams getParams() {
        return this.params_ == null ? EcdsaParams.getDefaultInstance() : this.params_;
    }

    @Override  // com.google.crypto.tink.proto.EcdsaPublicKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override  // com.google.crypto.tink.proto.EcdsaPublicKeyOrBuilder
    public ByteString getX() {
        return this.x_;
    }

    @Override  // com.google.crypto.tink.proto.EcdsaPublicKeyOrBuilder
    public ByteString getY() {
        return this.y_;
    }

    @Override  // com.google.crypto.tink.proto.EcdsaPublicKeyOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    private void mergeParams(EcdsaParams ecdsaParams0) {
        ecdsaParams0.getClass();
        if(this.params_ != null && this.params_ != EcdsaParams.getDefaultInstance()) {
            this.params_ = (EcdsaParams)((com.google.crypto.tink.proto.EcdsaParams.Builder)EcdsaParams.newBuilder(this.params_).mergeFrom(ecdsaParams0)).buildPartial();
            return;
        }
        this.params_ = ecdsaParams0;
    }

    public static Builder newBuilder() {
        return (Builder)EcdsaPublicKey.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(EcdsaPublicKey ecdsaPublicKey0) {
        return (Builder)EcdsaPublicKey.DEFAULT_INSTANCE.createBuilder(ecdsaPublicKey0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static EcdsaPublicKey parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (EcdsaPublicKey)EcdsaPublicKey.parseDelimitedFrom(EcdsaPublicKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static EcdsaPublicKey parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EcdsaPublicKey)EcdsaPublicKey.parseDelimitedFrom(EcdsaPublicKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EcdsaPublicKey parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (EcdsaPublicKey)GeneratedMessageLite.parseFrom(EcdsaPublicKey.DEFAULT_INSTANCE, byteString0);
    }

    public static EcdsaPublicKey parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EcdsaPublicKey)GeneratedMessageLite.parseFrom(EcdsaPublicKey.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static EcdsaPublicKey parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (EcdsaPublicKey)GeneratedMessageLite.parseFrom(EcdsaPublicKey.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static EcdsaPublicKey parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EcdsaPublicKey)GeneratedMessageLite.parseFrom(EcdsaPublicKey.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static EcdsaPublicKey parseFrom(InputStream inputStream0) throws IOException {
        return (EcdsaPublicKey)GeneratedMessageLite.parseFrom(EcdsaPublicKey.DEFAULT_INSTANCE, inputStream0);
    }

    public static EcdsaPublicKey parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (EcdsaPublicKey)GeneratedMessageLite.parseFrom(EcdsaPublicKey.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static EcdsaPublicKey parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (EcdsaPublicKey)GeneratedMessageLite.parseFrom(EcdsaPublicKey.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static EcdsaPublicKey parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EcdsaPublicKey)GeneratedMessageLite.parseFrom(EcdsaPublicKey.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static EcdsaPublicKey parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (EcdsaPublicKey)GeneratedMessageLite.parseFrom(EcdsaPublicKey.DEFAULT_INSTANCE, arr_b);
    }

    public static EcdsaPublicKey parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (EcdsaPublicKey)GeneratedMessageLite.parseFrom(EcdsaPublicKey.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return EcdsaPublicKey.DEFAULT_INSTANCE.getParserForType();
    }

    private void setParams(EcdsaParams ecdsaParams0) {
        ecdsaParams0.getClass();
        this.params_ = ecdsaParams0;
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

