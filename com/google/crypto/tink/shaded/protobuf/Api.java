package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class Api extends GeneratedMessageLite implements ApiOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements ApiOrBuilder {
        private Builder() {
            super(Api.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.Api.1 api$10) {
        }

        public Builder addAllMethods(Iterable iterable0) {
            this.copyOnWrite();
            ((Api)this.instance).addAllMethods(iterable0);
            return this;
        }

        public Builder addAllMixins(Iterable iterable0) {
            this.copyOnWrite();
            ((Api)this.instance).addAllMixins(iterable0);
            return this;
        }

        public Builder addAllOptions(Iterable iterable0) {
            this.copyOnWrite();
            ((Api)this.instance).addAllOptions(iterable0);
            return this;
        }

        public Builder addMethods(int v, com.google.crypto.tink.shaded.protobuf.Method.Builder method$Builder0) {
            this.copyOnWrite();
            ((Api)this.instance).addMethods(v, ((Method)method$Builder0.build()));
            return this;
        }

        public Builder addMethods(int v, Method method0) {
            this.copyOnWrite();
            ((Api)this.instance).addMethods(v, method0);
            return this;
        }

        public Builder addMethods(com.google.crypto.tink.shaded.protobuf.Method.Builder method$Builder0) {
            this.copyOnWrite();
            ((Api)this.instance).addMethods(((Method)method$Builder0.build()));
            return this;
        }

        public Builder addMethods(Method method0) {
            this.copyOnWrite();
            ((Api)this.instance).addMethods(method0);
            return this;
        }

        public Builder addMixins(int v, com.google.crypto.tink.shaded.protobuf.Mixin.Builder mixin$Builder0) {
            this.copyOnWrite();
            ((Api)this.instance).addMixins(v, ((Mixin)mixin$Builder0.build()));
            return this;
        }

        public Builder addMixins(int v, Mixin mixin0) {
            this.copyOnWrite();
            ((Api)this.instance).addMixins(v, mixin0);
            return this;
        }

        public Builder addMixins(com.google.crypto.tink.shaded.protobuf.Mixin.Builder mixin$Builder0) {
            this.copyOnWrite();
            ((Api)this.instance).addMixins(((Mixin)mixin$Builder0.build()));
            return this;
        }

        public Builder addMixins(Mixin mixin0) {
            this.copyOnWrite();
            ((Api)this.instance).addMixins(mixin0);
            return this;
        }

        public Builder addOptions(int v, com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((Api)this.instance).addOptions(v, ((Option)option$Builder0.build()));
            return this;
        }

        public Builder addOptions(int v, Option option0) {
            this.copyOnWrite();
            ((Api)this.instance).addOptions(v, option0);
            return this;
        }

        public Builder addOptions(com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((Api)this.instance).addOptions(((Option)option$Builder0.build()));
            return this;
        }

        public Builder addOptions(Option option0) {
            this.copyOnWrite();
            ((Api)this.instance).addOptions(option0);
            return this;
        }

        public Builder clearMethods() {
            this.copyOnWrite();
            ((Api)this.instance).clearMethods();
            return this;
        }

        public Builder clearMixins() {
            this.copyOnWrite();
            ((Api)this.instance).clearMixins();
            return this;
        }

        public Builder clearName() {
            this.copyOnWrite();
            ((Api)this.instance).clearName();
            return this;
        }

        public Builder clearOptions() {
            this.copyOnWrite();
            ((Api)this.instance).clearOptions();
            return this;
        }

        public Builder clearSourceContext() {
            this.copyOnWrite();
            ((Api)this.instance).clearSourceContext();
            return this;
        }

        public Builder clearSyntax() {
            this.copyOnWrite();
            ((Api)this.instance).clearSyntax();
            return this;
        }

        public Builder clearVersion() {
            this.copyOnWrite();
            ((Api)this.instance).clearVersion();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public Method getMethods(int v) {
            return ((Api)this.instance).getMethods(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public int getMethodsCount() {
            return ((Api)this.instance).getMethodsCount();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public List getMethodsList() {
            return Collections.unmodifiableList(((Api)this.instance).getMethodsList());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public Mixin getMixins(int v) {
            return ((Api)this.instance).getMixins(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public int getMixinsCount() {
            return ((Api)this.instance).getMixinsCount();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public List getMixinsList() {
            return Collections.unmodifiableList(((Api)this.instance).getMixinsList());
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public String getName() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public ByteString getNameBytes() {
            return ((Api)this.instance).getNameBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public Option getOptions(int v) {
            return ((Api)this.instance).getOptions(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public int getOptionsCount() {
            return ((Api)this.instance).getOptionsCount();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public List getOptionsList() {
            return Collections.unmodifiableList(((Api)this.instance).getOptionsList());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public SourceContext getSourceContext() {
            return ((Api)this.instance).getSourceContext();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public Syntax getSyntax() {
            return ((Api)this.instance).getSyntax();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public int getSyntaxValue() {
            return ((Api)this.instance).getSyntaxValue();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public String getVersion() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public ByteString getVersionBytes() {
            return ((Api)this.instance).getVersionBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
        public boolean hasSourceContext() {
            return ((Api)this.instance).hasSourceContext();
        }

        public Builder mergeSourceContext(SourceContext sourceContext0) {
            this.copyOnWrite();
            ((Api)this.instance).mergeSourceContext(sourceContext0);
            return this;
        }

        public Builder removeMethods(int v) {
            this.copyOnWrite();
            ((Api)this.instance).removeMethods(v);
            return this;
        }

        public Builder removeMixins(int v) {
            this.copyOnWrite();
            ((Api)this.instance).removeMixins(v);
            return this;
        }

        public Builder removeOptions(int v) {
            this.copyOnWrite();
            ((Api)this.instance).removeOptions(v);
            return this;
        }

        public Builder setMethods(int v, com.google.crypto.tink.shaded.protobuf.Method.Builder method$Builder0) {
            this.copyOnWrite();
            ((Api)this.instance).setMethods(v, ((Method)method$Builder0.build()));
            return this;
        }

        public Builder setMethods(int v, Method method0) {
            this.copyOnWrite();
            ((Api)this.instance).setMethods(v, method0);
            return this;
        }

        public Builder setMixins(int v, com.google.crypto.tink.shaded.protobuf.Mixin.Builder mixin$Builder0) {
            this.copyOnWrite();
            ((Api)this.instance).setMixins(v, ((Mixin)mixin$Builder0.build()));
            return this;
        }

        public Builder setMixins(int v, Mixin mixin0) {
            this.copyOnWrite();
            ((Api)this.instance).setMixins(v, mixin0);
            return this;
        }

        public Builder setName(String s) {
            this.copyOnWrite();
            ((Api)this.instance).setName(s);
            return this;
        }

        public Builder setNameBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Api)this.instance).setNameBytes(byteString0);
            return this;
        }

        public Builder setOptions(int v, com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((Api)this.instance).setOptions(v, ((Option)option$Builder0.build()));
            return this;
        }

        public Builder setOptions(int v, Option option0) {
            this.copyOnWrite();
            ((Api)this.instance).setOptions(v, option0);
            return this;
        }

        public Builder setSourceContext(com.google.crypto.tink.shaded.protobuf.SourceContext.Builder sourceContext$Builder0) {
            this.copyOnWrite();
            ((Api)this.instance).setSourceContext(((SourceContext)sourceContext$Builder0.build()));
            return this;
        }

        public Builder setSourceContext(SourceContext sourceContext0) {
            this.copyOnWrite();
            ((Api)this.instance).setSourceContext(sourceContext0);
            return this;
        }

        public Builder setSyntax(Syntax syntax0) {
            this.copyOnWrite();
            ((Api)this.instance).setSyntax(syntax0);
            return this;
        }

        public Builder setSyntaxValue(int v) {
            this.copyOnWrite();
            ((Api)this.instance).setSyntaxValue(v);
            return this;
        }

        public Builder setVersion(String s) {
            this.copyOnWrite();
            ((Api)this.instance).setVersion(s);
            return this;
        }

        public Builder setVersionBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Api)this.instance).setVersionBytes(byteString0);
            return this;
        }
    }

    private static final Api DEFAULT_INSTANCE = null;
    public static final int METHODS_FIELD_NUMBER = 2;
    public static final int MIXINS_FIELD_NUMBER = 6;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int SOURCE_CONTEXT_FIELD_NUMBER = 5;
    public static final int SYNTAX_FIELD_NUMBER = 7;
    public static final int VERSION_FIELD_NUMBER = 4;
    private ProtobufList methods_;
    private ProtobufList mixins_;
    private String name_;
    private ProtobufList options_;
    private SourceContext sourceContext_;
    private int syntax_;
    private String version_;

    static {
        Api api0 = new Api();
        Api.DEFAULT_INSTANCE = api0;
        GeneratedMessageLite.registerDefaultInstance(Api.class, api0);
    }

    private Api() {
        this.name_ = "";
        this.methods_ = Api.emptyProtobufList();
        this.options_ = Api.emptyProtobufList();
        this.version_ = "";
        this.mixins_ = Api.emptyProtobufList();
    }

    private void addAllMethods(Iterable iterable0) {
        this.ensureMethodsIsMutable();
        AbstractMessageLite.addAll(iterable0, this.methods_);
    }

    private void addAllMixins(Iterable iterable0) {
        this.ensureMixinsIsMutable();
        AbstractMessageLite.addAll(iterable0, this.mixins_);
    }

    private void addAllOptions(Iterable iterable0) {
        this.ensureOptionsIsMutable();
        AbstractMessageLite.addAll(iterable0, this.options_);
    }

    private void addMethods(int v, Method method0) {
        method0.getClass();
        this.ensureMethodsIsMutable();
        this.methods_.add(v, method0);
    }

    private void addMethods(Method method0) {
        method0.getClass();
        this.ensureMethodsIsMutable();
        this.methods_.add(method0);
    }

    private void addMixins(int v, Mixin mixin0) {
        mixin0.getClass();
        this.ensureMixinsIsMutable();
        this.mixins_.add(v, mixin0);
    }

    private void addMixins(Mixin mixin0) {
        mixin0.getClass();
        this.ensureMixinsIsMutable();
        this.mixins_.add(mixin0);
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

    private void clearMethods() {
        this.methods_ = Api.emptyProtobufList();
    }

    private void clearMixins() {
        this.mixins_ = Api.emptyProtobufList();
    }

    private void clearName() {
        this.name_ = "";
    }

    private void clearOptions() {
        this.options_ = Api.emptyProtobufList();
    }

    private void clearSourceContext() {
        this.sourceContext_ = null;
    }

    private void clearSyntax() {
        this.syntax_ = 0;
    }

    private void clearVersion() {
        this.version_ = "";
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.Api.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Api();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Api.newMessageInfo(Api.DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0003\u0000\u0001Ȉ\u0002\u001B\u0003\u001B\u0004Ȉ\u0005\t\u0006\u001B\u0007\f", new Object[]{"name_", "methods_", Method.class, "options_", Option.class, "version_", "sourceContext_", "mixins_", Mixin.class, "syntax_"});
            }
            case 4: {
                return Api.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Api.PARSER;
                if(parser0 == null) {
                    Class class0 = Api.class;
                    synchronized(class0) {
                        Parser parser1 = Api.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Api.DEFAULT_INSTANCE);
                            Api.PARSER = parser1;
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

    private void ensureMethodsIsMutable() {
        ProtobufList internal$ProtobufList0 = this.methods_;
        if(!internal$ProtobufList0.isModifiable()) {
            this.methods_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList0);
        }
    }

    private void ensureMixinsIsMutable() {
        ProtobufList internal$ProtobufList0 = this.mixins_;
        if(!internal$ProtobufList0.isModifiable()) {
            this.mixins_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList0);
        }
    }

    private void ensureOptionsIsMutable() {
        ProtobufList internal$ProtobufList0 = this.options_;
        if(!internal$ProtobufList0.isModifiable()) {
            this.options_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList0);
        }
    }

    public static Api getDefaultInstance() {
        return Api.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public Method getMethods(int v) {
        return (Method)this.methods_.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public int getMethodsCount() {
        return this.methods_.size();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public List getMethodsList() {
        return this.methods_;
    }

    public MethodOrBuilder getMethodsOrBuilder(int v) {
        return (MethodOrBuilder)this.methods_.get(v);
    }

    public List getMethodsOrBuilderList() {
        return this.methods_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public Mixin getMixins(int v) {
        return (Mixin)this.mixins_.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public int getMixinsCount() {
        return this.mixins_.size();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public List getMixinsList() {
        return this.mixins_;
    }

    public MixinOrBuilder getMixinsOrBuilder(int v) {
        return (MixinOrBuilder)this.mixins_.get(v);
    }

    public List getMixinsOrBuilderList() {
        return this.mixins_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public String getName() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public Option getOptions(int v) {
        return (Option)this.options_.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public int getOptionsCount() {
        return this.options_.size();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public List getOptionsList() {
        return this.options_;
    }

    public OptionOrBuilder getOptionsOrBuilder(int v) {
        return (OptionOrBuilder)this.options_.get(v);
    }

    public List getOptionsOrBuilderList() {
        return this.options_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public SourceContext getSourceContext() {
        return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public Syntax getSyntax() {
        Syntax syntax0 = Syntax.forNumber(this.syntax_);
        return syntax0 == null ? Syntax.UNRECOGNIZED : syntax0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public int getSyntaxValue() {
        return this.syntax_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public String getVersion() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public ByteString getVersionBytes() {
        return ByteString.copyFromUtf8(this.version_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ApiOrBuilder
    public boolean hasSourceContext() {
        return this.sourceContext_ != null;
    }

    private void mergeSourceContext(SourceContext sourceContext0) {
        sourceContext0.getClass();
        if(this.sourceContext_ != null && this.sourceContext_ != SourceContext.getDefaultInstance()) {
            this.sourceContext_ = (SourceContext)((com.google.crypto.tink.shaded.protobuf.SourceContext.Builder)SourceContext.newBuilder(this.sourceContext_).mergeFrom(sourceContext0)).buildPartial();
            return;
        }
        this.sourceContext_ = sourceContext0;
    }

    public static Builder newBuilder() {
        return (Builder)Api.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Api api0) {
        return (Builder)Api.DEFAULT_INSTANCE.createBuilder(api0);
    }

    public static Api parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Api)Api.parseDelimitedFrom(Api.DEFAULT_INSTANCE, inputStream0);
    }

    public static Api parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Api)Api.parseDelimitedFrom(Api.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Api parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Api)GeneratedMessageLite.parseFrom(Api.DEFAULT_INSTANCE, byteString0);
    }

    public static Api parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Api)GeneratedMessageLite.parseFrom(Api.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Api parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Api)GeneratedMessageLite.parseFrom(Api.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Api parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Api)GeneratedMessageLite.parseFrom(Api.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Api parseFrom(InputStream inputStream0) throws IOException {
        return (Api)GeneratedMessageLite.parseFrom(Api.DEFAULT_INSTANCE, inputStream0);
    }

    public static Api parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Api)GeneratedMessageLite.parseFrom(Api.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Api parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Api)GeneratedMessageLite.parseFrom(Api.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Api parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Api)GeneratedMessageLite.parseFrom(Api.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Api parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Api)GeneratedMessageLite.parseFrom(Api.DEFAULT_INSTANCE, arr_b);
    }

    public static Api parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Api)GeneratedMessageLite.parseFrom(Api.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Api.DEFAULT_INSTANCE.getParserForType();
    }

    private void removeMethods(int v) {
        this.ensureMethodsIsMutable();
        this.methods_.remove(v);
    }

    private void removeMixins(int v) {
        this.ensureMixinsIsMutable();
        this.mixins_.remove(v);
    }

    private void removeOptions(int v) {
        this.ensureOptionsIsMutable();
        this.options_.remove(v);
    }

    private void setMethods(int v, Method method0) {
        method0.getClass();
        this.ensureMethodsIsMutable();
        this.methods_.set(v, method0);
    }

    private void setMixins(int v, Mixin mixin0) {
        mixin0.getClass();
        this.ensureMixinsIsMutable();
        this.mixins_.set(v, mixin0);
    }

    private void setName(String s) {
        s.getClass();
        this.name_ = s;
    }

    private void setNameBytes(ByteString byteString0) {
        Api.checkByteStringIsUtf8(byteString0);
        this.name_ = byteString0.toStringUtf8();
    }

    private void setOptions(int v, Option option0) {
        option0.getClass();
        this.ensureOptionsIsMutable();
        this.options_.set(v, option0);
    }

    private void setSourceContext(SourceContext sourceContext0) {
        sourceContext0.getClass();
        this.sourceContext_ = sourceContext0;
    }

    private void setSyntax(Syntax syntax0) {
        this.syntax_ = syntax0.getNumber();
    }

    private void setSyntaxValue(int v) {
        this.syntax_ = v;
    }

    private void setVersion(String s) {
        s.getClass();
        this.version_ = s;
    }

    private void setVersionBytes(ByteString byteString0) {
        Api.checkByteStringIsUtf8(byteString0);
        this.version_ = byteString0.toStringUtf8();
    }
}

