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

public final class AesCmacParams extends GeneratedMessageLite implements AesCmacParamsOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements AesCmacParamsOrBuilder {
        private Builder() {
            super(AesCmacParams.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.AesCmacParams.1 aesCmacParams$10) {
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

        public Builder clearTagSize() {
            this.copyOnWrite();
            ((AesCmacParams)this.instance).clearTagSize();
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

        @Override  // com.google.crypto.tink.proto.AesCmacParamsOrBuilder
        public int getTagSize() {
            return ((AesCmacParams)this.instance).getTagSize();
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

        public Builder setTagSize(int v) {
            this.copyOnWrite();
            ((AesCmacParams)this.instance).setTagSize(v);
            return this;
        }
    }

    private static final AesCmacParams DEFAULT_INSTANCE = null;
    private static volatile Parser PARSER = null;
    public static final int TAG_SIZE_FIELD_NUMBER = 1;
    private int tagSize_;

    static {
        AesCmacParams aesCmacParams0 = new AesCmacParams();
        AesCmacParams.DEFAULT_INSTANCE = aesCmacParams0;
        GeneratedMessageLite.registerDefaultInstance(AesCmacParams.class, aesCmacParams0);
    }

    private void clearTagSize() {
        this.tagSize_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.AesCmacParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new AesCmacParams();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return AesCmacParams.newMessageInfo(AesCmacParams.DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000B", new Object[]{"tagSize_"});
            }
            case 4: {
                return AesCmacParams.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = AesCmacParams.PARSER;
                if(parser0 == null) {
                    Class class0 = AesCmacParams.class;
                    synchronized(class0) {
                        Parser parser1 = AesCmacParams.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(AesCmacParams.DEFAULT_INSTANCE);
                            AesCmacParams.PARSER = parser1;
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

    public static AesCmacParams getDefaultInstance() {
        return AesCmacParams.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.AesCmacParamsOrBuilder
    public int getTagSize() {
        return this.tagSize_;
    }

    public static Builder newBuilder() {
        return (Builder)AesCmacParams.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesCmacParams aesCmacParams0) {
        return (Builder)AesCmacParams.DEFAULT_INSTANCE.createBuilder(aesCmacParams0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static AesCmacParams parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (AesCmacParams)AesCmacParams.parseDelimitedFrom(AesCmacParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCmacParams parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCmacParams)AesCmacParams.parseDelimitedFrom(AesCmacParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCmacParams parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (AesCmacParams)GeneratedMessageLite.parseFrom(AesCmacParams.DEFAULT_INSTANCE, byteString0);
    }

    public static AesCmacParams parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCmacParams)GeneratedMessageLite.parseFrom(AesCmacParams.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static AesCmacParams parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (AesCmacParams)GeneratedMessageLite.parseFrom(AesCmacParams.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static AesCmacParams parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCmacParams)GeneratedMessageLite.parseFrom(AesCmacParams.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static AesCmacParams parseFrom(InputStream inputStream0) throws IOException {
        return (AesCmacParams)GeneratedMessageLite.parseFrom(AesCmacParams.DEFAULT_INSTANCE, inputStream0);
    }

    public static AesCmacParams parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (AesCmacParams)GeneratedMessageLite.parseFrom(AesCmacParams.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static AesCmacParams parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (AesCmacParams)GeneratedMessageLite.parseFrom(AesCmacParams.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static AesCmacParams parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCmacParams)GeneratedMessageLite.parseFrom(AesCmacParams.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static AesCmacParams parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (AesCmacParams)GeneratedMessageLite.parseFrom(AesCmacParams.DEFAULT_INSTANCE, arr_b);
    }

    public static AesCmacParams parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (AesCmacParams)GeneratedMessageLite.parseFrom(AesCmacParams.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return AesCmacParams.DEFAULT_INSTANCE.getParserForType();
    }

    private void setTagSize(int v) {
        this.tagSize_ = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }

    class com.google.crypto.tink.proto.AesCmacParams.1 {
        static final int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] arr_v = new int[MethodToInvoke.values().length];
            com.google.crypto.tink.proto.AesCmacParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = arr_v;
            try {
                arr_v[MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.proto.AesCmacParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.proto.AesCmacParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.proto.AesCmacParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.proto.AesCmacParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[MethodToInvoke.GET_PARSER.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.proto.AesCmacParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.proto.AesCmacParams.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

