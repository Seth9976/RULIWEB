package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class Method extends GeneratedMessageLite implements MethodOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements MethodOrBuilder {
        private Builder() {
            super(Method.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.Method.1 method$10) {
        }

        public Builder addAllOptions(Iterable iterable0) {
            this.copyOnWrite();
            ((Method)this.instance).addAllOptions(iterable0);
            return this;
        }

        public Builder addOptions(int v, com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((Method)this.instance).addOptions(v, ((Option)option$Builder0.build()));
            return this;
        }

        public Builder addOptions(int v, Option option0) {
            this.copyOnWrite();
            ((Method)this.instance).addOptions(v, option0);
            return this;
        }

        public Builder addOptions(com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((Method)this.instance).addOptions(((Option)option$Builder0.build()));
            return this;
        }

        public Builder addOptions(Option option0) {
            this.copyOnWrite();
            ((Method)this.instance).addOptions(option0);
            return this;
        }

        public Builder clearName() {
            this.copyOnWrite();
            ((Method)this.instance).clearName();
            return this;
        }

        public Builder clearOptions() {
            this.copyOnWrite();
            ((Method)this.instance).clearOptions();
            return this;
        }

        public Builder clearRequestStreaming() {
            this.copyOnWrite();
            ((Method)this.instance).clearRequestStreaming();
            return this;
        }

        public Builder clearRequestTypeUrl() {
            this.copyOnWrite();
            ((Method)this.instance).clearRequestTypeUrl();
            return this;
        }

        public Builder clearResponseStreaming() {
            this.copyOnWrite();
            ((Method)this.instance).clearResponseStreaming();
            return this;
        }

        public Builder clearResponseTypeUrl() {
            this.copyOnWrite();
            ((Method)this.instance).clearResponseTypeUrl();
            return this;
        }

        public Builder clearSyntax() {
            this.copyOnWrite();
            ((Method)this.instance).clearSyntax();
            return this;
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public String getName() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public ByteString getNameBytes() {
            return ((Method)this.instance).getNameBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public Option getOptions(int v) {
            return ((Method)this.instance).getOptions(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public int getOptionsCount() {
            return ((Method)this.instance).getOptionsCount();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public List getOptionsList() {
            return Collections.unmodifiableList(((Method)this.instance).getOptionsList());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public boolean getRequestStreaming() {
            return ((Method)this.instance).getRequestStreaming();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public String getRequestTypeUrl() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public ByteString getRequestTypeUrlBytes() {
            return ((Method)this.instance).getRequestTypeUrlBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public boolean getResponseStreaming() {
            return ((Method)this.instance).getResponseStreaming();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public String getResponseTypeUrl() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public ByteString getResponseTypeUrlBytes() {
            return ((Method)this.instance).getResponseTypeUrlBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public Syntax getSyntax() {
            return ((Method)this.instance).getSyntax();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
        public int getSyntaxValue() {
            return ((Method)this.instance).getSyntaxValue();
        }

        public Builder removeOptions(int v) {
            this.copyOnWrite();
            ((Method)this.instance).removeOptions(v);
            return this;
        }

        public Builder setName(String s) {
            this.copyOnWrite();
            ((Method)this.instance).setName(s);
            return this;
        }

        public Builder setNameBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Method)this.instance).setNameBytes(byteString0);
            return this;
        }

        public Builder setOptions(int v, com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((Method)this.instance).setOptions(v, ((Option)option$Builder0.build()));
            return this;
        }

        public Builder setOptions(int v, Option option0) {
            this.copyOnWrite();
            ((Method)this.instance).setOptions(v, option0);
            return this;
        }

        public Builder setRequestStreaming(boolean z) {
            this.copyOnWrite();
            ((Method)this.instance).setRequestStreaming(z);
            return this;
        }

        public Builder setRequestTypeUrl(String s) {
            this.copyOnWrite();
            ((Method)this.instance).setRequestTypeUrl(s);
            return this;
        }

        public Builder setRequestTypeUrlBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Method)this.instance).setRequestTypeUrlBytes(byteString0);
            return this;
        }

        public Builder setResponseStreaming(boolean z) {
            this.copyOnWrite();
            ((Method)this.instance).setResponseStreaming(z);
            return this;
        }

        public Builder setResponseTypeUrl(String s) {
            this.copyOnWrite();
            ((Method)this.instance).setResponseTypeUrl(s);
            return this;
        }

        public Builder setResponseTypeUrlBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Method)this.instance).setResponseTypeUrlBytes(byteString0);
            return this;
        }

        public Builder setSyntax(Syntax syntax0) {
            this.copyOnWrite();
            ((Method)this.instance).setSyntax(syntax0);
            return this;
        }

        public Builder setSyntaxValue(int v) {
            this.copyOnWrite();
            ((Method)this.instance).setSyntaxValue(v);
            return this;
        }
    }

    private static final Method DEFAULT_INSTANCE = null;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int OPTIONS_FIELD_NUMBER = 6;
    private static volatile Parser PARSER = null;
    public static final int REQUEST_STREAMING_FIELD_NUMBER = 3;
    public static final int REQUEST_TYPE_URL_FIELD_NUMBER = 2;
    public static final int RESPONSE_STREAMING_FIELD_NUMBER = 5;
    public static final int RESPONSE_TYPE_URL_FIELD_NUMBER = 4;
    public static final int SYNTAX_FIELD_NUMBER = 7;
    private String name_;
    private ProtobufList options_;
    private boolean requestStreaming_;
    private String requestTypeUrl_;
    private boolean responseStreaming_;
    private String responseTypeUrl_;
    private int syntax_;

    static {
        Method method0 = new Method();
        Method.DEFAULT_INSTANCE = method0;
        GeneratedMessageLite.registerDefaultInstance(Method.class, method0);
    }

    private Method() {
        this.name_ = "";
        this.requestTypeUrl_ = "";
        this.responseTypeUrl_ = "";
        this.options_ = Method.emptyProtobufList();
    }

    private void addAllOptions(Iterable iterable0) {
        this.ensureOptionsIsMutable();
        AbstractMessageLite.addAll(iterable0, this.options_);
    }

    private void addOptions(int v, Option option0) {
        option0.getClass();
        this.ensureOptionsIsMutable();
        this.options_.add(v, option0);
    }

    private void addOptions(Option option0) {
        option0.getClass();
        this.ensureOptionsIsMutable();
        this.options_.add(option0);
    }

    private void clearName() {
        this.name_ = "";
    }

    private void clearOptions() {
        this.options_ = Method.emptyProtobufList();
    }

    private void clearRequestStreaming() {
        this.requestStreaming_ = false;
    }

    private void clearRequestTypeUrl() {
        this.requestTypeUrl_ = "";
    }

    private void clearResponseStreaming() {
        this.responseStreaming_ = false;
    }

    private void clearResponseTypeUrl() {
        this.responseTypeUrl_ = "";
    }

    private void clearSyntax() {
        this.syntax_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.Method.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Method();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Method.newMessageInfo(Method.DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003\u0007\u0004Ȉ\u0005\u0007\u0006\u001B\u0007\f", new Object[]{"name_", "requestTypeUrl_", "requestStreaming_", "responseTypeUrl_", "responseStreaming_", "options_", Option.class, "syntax_"});
            }
            case 4: {
                return Method.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Method.PARSER;
                if(parser0 == null) {
                    Class class0 = Method.class;
                    synchronized(class0) {
                        Parser parser1 = Method.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Method.DEFAULT_INSTANCE);
                            Method.PARSER = parser1;
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

    private void ensureOptionsIsMutable() {
        ProtobufList internal$ProtobufList0 = this.options_;
        if(!internal$ProtobufList0.isModifiable()) {
            this.options_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList0);
        }
    }

    public static Method getDefaultInstance() {
        return Method.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public String getName() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public Option getOptions(int v) {
        return (Option)this.options_.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public int getOptionsCount() {
        return this.options_.size();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public List getOptionsList() {
        return this.options_;
    }

    public OptionOrBuilder getOptionsOrBuilder(int v) {
        return (OptionOrBuilder)this.options_.get(v);
    }

    public List getOptionsOrBuilderList() {
        return this.options_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public boolean getRequestStreaming() {
        return this.requestStreaming_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public String getRequestTypeUrl() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public ByteString getRequestTypeUrlBytes() {
        return ByteString.copyFromUtf8(this.requestTypeUrl_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public boolean getResponseStreaming() {
        return this.responseStreaming_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public String getResponseTypeUrl() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public ByteString getResponseTypeUrlBytes() {
        return ByteString.copyFromUtf8(this.responseTypeUrl_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public Syntax getSyntax() {
        Syntax syntax0 = Syntax.forNumber(this.syntax_);
        return syntax0 == null ? Syntax.UNRECOGNIZED : syntax0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MethodOrBuilder
    public int getSyntaxValue() {
        return this.syntax_;
    }

    public static Builder newBuilder() {
        return (Builder)Method.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Method method0) {
        return (Builder)Method.DEFAULT_INSTANCE.createBuilder(method0);
    }

    public static Method parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Method)Method.parseDelimitedFrom(Method.DEFAULT_INSTANCE, inputStream0);
    }

    public static Method parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Method)Method.parseDelimitedFrom(Method.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Method parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Method)GeneratedMessageLite.parseFrom(Method.DEFAULT_INSTANCE, byteString0);
    }

    public static Method parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Method)GeneratedMessageLite.parseFrom(Method.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Method parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Method)GeneratedMessageLite.parseFrom(Method.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Method parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Method)GeneratedMessageLite.parseFrom(Method.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Method parseFrom(InputStream inputStream0) throws IOException {
        return (Method)GeneratedMessageLite.parseFrom(Method.DEFAULT_INSTANCE, inputStream0);
    }

    public static Method parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Method)GeneratedMessageLite.parseFrom(Method.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Method parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Method)GeneratedMessageLite.parseFrom(Method.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Method parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Method)GeneratedMessageLite.parseFrom(Method.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Method parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Method)GeneratedMessageLite.parseFrom(Method.DEFAULT_INSTANCE, arr_b);
    }

    public static Method parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Method)GeneratedMessageLite.parseFrom(Method.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Method.DEFAULT_INSTANCE.getParserForType();
    }

    private void removeOptions(int v) {
        this.ensureOptionsIsMutable();
        this.options_.remove(v);
    }

    private void setName(String s) {
        s.getClass();
        this.name_ = s;
    }

    private void setNameBytes(ByteString byteString0) {
        Method.checkByteStringIsUtf8(byteString0);
        this.name_ = byteString0.toStringUtf8();
    }

    private void setOptions(int v, Option option0) {
        option0.getClass();
        this.ensureOptionsIsMutable();
        this.options_.set(v, option0);
    }

    private void setRequestStreaming(boolean z) {
        this.requestStreaming_ = z;
    }

    private void setRequestTypeUrl(String s) {
        s.getClass();
        this.requestTypeUrl_ = s;
    }

    private void setRequestTypeUrlBytes(ByteString byteString0) {
        Method.checkByteStringIsUtf8(byteString0);
        this.requestTypeUrl_ = byteString0.toStringUtf8();
    }

    private void setResponseStreaming(boolean z) {
        this.responseStreaming_ = z;
    }

    private void setResponseTypeUrl(String s) {
        s.getClass();
        this.responseTypeUrl_ = s;
    }

    private void setResponseTypeUrlBytes(ByteString byteString0) {
        Method.checkByteStringIsUtf8(byteString0);
        this.responseTypeUrl_ = byteString0.toStringUtf8();
    }

    private void setSyntax(Syntax syntax0) {
        this.syntax_ = syntax0.getNumber();
    }

    private void setSyntaxValue(int v) {
        this.syntax_ = v;
    }
}

