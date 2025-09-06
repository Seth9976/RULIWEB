package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.AbstractMessageLite;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedInputStream;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

@Deprecated
public final class RegistryConfig extends GeneratedMessageLite implements RegistryConfigOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements RegistryConfigOrBuilder {
        private Builder() {
            super(RegistryConfig.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.proto.RegistryConfig.1 registryConfig$10) {
        }

        public Builder addAllEntry(Iterable iterable0) {
            this.copyOnWrite();
            ((RegistryConfig)this.instance).addAllEntry(iterable0);
            return this;
        }

        public Builder addEntry(int v, com.google.crypto.tink.proto.KeyTypeEntry.Builder keyTypeEntry$Builder0) {
            this.copyOnWrite();
            ((RegistryConfig)this.instance).addEntry(v, ((KeyTypeEntry)keyTypeEntry$Builder0.build()));
            return this;
        }

        public Builder addEntry(int v, KeyTypeEntry keyTypeEntry0) {
            this.copyOnWrite();
            ((RegistryConfig)this.instance).addEntry(v, keyTypeEntry0);
            return this;
        }

        public Builder addEntry(com.google.crypto.tink.proto.KeyTypeEntry.Builder keyTypeEntry$Builder0) {
            this.copyOnWrite();
            ((RegistryConfig)this.instance).addEntry(((KeyTypeEntry)keyTypeEntry$Builder0.build()));
            return this;
        }

        public Builder addEntry(KeyTypeEntry keyTypeEntry0) {
            this.copyOnWrite();
            ((RegistryConfig)this.instance).addEntry(keyTypeEntry0);
            return this;
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

        public Builder clearConfigName() {
            this.copyOnWrite();
            ((RegistryConfig)this.instance).clearConfigName();
            return this;
        }

        public Builder clearEntry() {
            this.copyOnWrite();
            ((RegistryConfig)this.instance).clearEntry();
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

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.proto.RegistryConfigOrBuilder
        public String getConfigName() {
            return "";
        }

        @Override  // com.google.crypto.tink.proto.RegistryConfigOrBuilder
        public ByteString getConfigNameBytes() {
            return ((RegistryConfig)this.instance).getConfigNameBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override  // com.google.crypto.tink.proto.RegistryConfigOrBuilder
        public KeyTypeEntry getEntry(int v) {
            return ((RegistryConfig)this.instance).getEntry(v);
        }

        @Override  // com.google.crypto.tink.proto.RegistryConfigOrBuilder
        public int getEntryCount() {
            return ((RegistryConfig)this.instance).getEntryCount();
        }

        @Override  // com.google.crypto.tink.proto.RegistryConfigOrBuilder
        public List getEntryList() {
            return Collections.unmodifiableList(((RegistryConfig)this.instance).getEntryList());
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

        public Builder removeEntry(int v) {
            this.copyOnWrite();
            ((RegistryConfig)this.instance).removeEntry(v);
            return this;
        }

        public Builder setConfigName(String s) {
            this.copyOnWrite();
            ((RegistryConfig)this.instance).setConfigName(s);
            return this;
        }

        public Builder setConfigNameBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((RegistryConfig)this.instance).setConfigNameBytes(byteString0);
            return this;
        }

        public Builder setEntry(int v, com.google.crypto.tink.proto.KeyTypeEntry.Builder keyTypeEntry$Builder0) {
            this.copyOnWrite();
            ((RegistryConfig)this.instance).setEntry(v, ((KeyTypeEntry)keyTypeEntry$Builder0.build()));
            return this;
        }

        public Builder setEntry(int v, KeyTypeEntry keyTypeEntry0) {
            this.copyOnWrite();
            ((RegistryConfig)this.instance).setEntry(v, keyTypeEntry0);
            return this;
        }
    }

    public static final int CONFIG_NAME_FIELD_NUMBER = 1;
    private static final RegistryConfig DEFAULT_INSTANCE = null;
    public static final int ENTRY_FIELD_NUMBER = 2;
    private static volatile Parser PARSER;
    private String configName_;
    private ProtobufList entry_;

    static {
        RegistryConfig registryConfig0 = new RegistryConfig();
        RegistryConfig.DEFAULT_INSTANCE = registryConfig0;
        GeneratedMessageLite.registerDefaultInstance(RegistryConfig.class, registryConfig0);
    }

    private RegistryConfig() {
        this.configName_ = "";
        this.entry_ = RegistryConfig.emptyProtobufList();
    }

    private void addAllEntry(Iterable iterable0) {
        this.ensureEntryIsMutable();
        AbstractMessageLite.addAll(iterable0, this.entry_);
    }

    private void addEntry(int v, KeyTypeEntry keyTypeEntry0) {
        keyTypeEntry0.getClass();
        this.ensureEntryIsMutable();
        this.entry_.add(v, keyTypeEntry0);
    }

    private void addEntry(KeyTypeEntry keyTypeEntry0) {
        keyTypeEntry0.getClass();
        this.ensureEntryIsMutable();
        this.entry_.add(keyTypeEntry0);
    }

    private void clearConfigName() {
        this.configName_ = "";
    }

    private void clearEntry() {
        this.entry_ = RegistryConfig.emptyProtobufList();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.proto.RegistryConfig.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new RegistryConfig();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return RegistryConfig.newMessageInfo(RegistryConfig.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001B", new Object[]{"configName_", "entry_", KeyTypeEntry.class});
            }
            case 4: {
                return RegistryConfig.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = RegistryConfig.PARSER;
                if(parser0 == null) {
                    Class class0 = RegistryConfig.class;
                    synchronized(class0) {
                        Parser parser1 = RegistryConfig.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(RegistryConfig.DEFAULT_INSTANCE);
                            RegistryConfig.PARSER = parser1;
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

    private void ensureEntryIsMutable() {
        ProtobufList internal$ProtobufList0 = this.entry_;
        if(!internal$ProtobufList0.isModifiable()) {
            this.entry_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList0);
        }
    }

    @Override  // com.google.crypto.tink.proto.RegistryConfigOrBuilder
    public String getConfigName() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.proto.RegistryConfigOrBuilder
    public ByteString getConfigNameBytes() {
        return ByteString.copyFromUtf8(this.configName_);
    }

    public static RegistryConfig getDefaultInstance() {
        return RegistryConfig.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override  // com.google.crypto.tink.proto.RegistryConfigOrBuilder
    public KeyTypeEntry getEntry(int v) {
        return (KeyTypeEntry)this.entry_.get(v);
    }

    @Override  // com.google.crypto.tink.proto.RegistryConfigOrBuilder
    public int getEntryCount() {
        return this.entry_.size();
    }

    @Override  // com.google.crypto.tink.proto.RegistryConfigOrBuilder
    public List getEntryList() {
        return this.entry_;
    }

    public KeyTypeEntryOrBuilder getEntryOrBuilder(int v) {
        return (KeyTypeEntryOrBuilder)this.entry_.get(v);
    }

    public List getEntryOrBuilderList() {
        return this.entry_;
    }

    public static Builder newBuilder() {
        return (Builder)RegistryConfig.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RegistryConfig registryConfig0) {
        return (Builder)RegistryConfig.DEFAULT_INSTANCE.createBuilder(registryConfig0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    public static RegistryConfig parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (RegistryConfig)RegistryConfig.parseDelimitedFrom(RegistryConfig.DEFAULT_INSTANCE, inputStream0);
    }

    public static RegistryConfig parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RegistryConfig)RegistryConfig.parseDelimitedFrom(RegistryConfig.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RegistryConfig parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (RegistryConfig)GeneratedMessageLite.parseFrom(RegistryConfig.DEFAULT_INSTANCE, byteString0);
    }

    public static RegistryConfig parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RegistryConfig)GeneratedMessageLite.parseFrom(RegistryConfig.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static RegistryConfig parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (RegistryConfig)GeneratedMessageLite.parseFrom(RegistryConfig.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static RegistryConfig parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RegistryConfig)GeneratedMessageLite.parseFrom(RegistryConfig.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static RegistryConfig parseFrom(InputStream inputStream0) throws IOException {
        return (RegistryConfig)GeneratedMessageLite.parseFrom(RegistryConfig.DEFAULT_INSTANCE, inputStream0);
    }

    public static RegistryConfig parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (RegistryConfig)GeneratedMessageLite.parseFrom(RegistryConfig.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static RegistryConfig parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (RegistryConfig)GeneratedMessageLite.parseFrom(RegistryConfig.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static RegistryConfig parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RegistryConfig)GeneratedMessageLite.parseFrom(RegistryConfig.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static RegistryConfig parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (RegistryConfig)GeneratedMessageLite.parseFrom(RegistryConfig.DEFAULT_INSTANCE, arr_b);
    }

    public static RegistryConfig parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (RegistryConfig)GeneratedMessageLite.parseFrom(RegistryConfig.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return RegistryConfig.DEFAULT_INSTANCE.getParserForType();
    }

    private void removeEntry(int v) {
        this.ensureEntryIsMutable();
        this.entry_.remove(v);
    }

    private void setConfigName(String s) {
        s.getClass();
        this.configName_ = s;
    }

    private void setConfigNameBytes(ByteString byteString0) {
        RegistryConfig.checkByteStringIsUtf8(byteString0);
        this.configName_ = byteString0.toStringUtf8();
    }

    private void setEntry(int v, KeyTypeEntry keyTypeEntry0) {
        keyTypeEntry0.getClass();
        this.ensureEntryIsMutable();
        this.entry_.set(v, keyTypeEntry0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }
}

