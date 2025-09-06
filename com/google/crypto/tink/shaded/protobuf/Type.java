package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class Type extends GeneratedMessageLite implements TypeOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements TypeOrBuilder {
        private Builder() {
            super(Type.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.Type.1 type$10) {
        }

        public Builder addAllFields(Iterable iterable0) {
            this.copyOnWrite();
            ((Type)this.instance).addAllFields(iterable0);
            return this;
        }

        public Builder addAllOneofs(Iterable iterable0) {
            this.copyOnWrite();
            ((Type)this.instance).addAllOneofs(iterable0);
            return this;
        }

        public Builder addAllOptions(Iterable iterable0) {
            this.copyOnWrite();
            ((Type)this.instance).addAllOptions(iterable0);
            return this;
        }

        public Builder addFields(int v, com.google.crypto.tink.shaded.protobuf.Field.Builder field$Builder0) {
            this.copyOnWrite();
            ((Type)this.instance).addFields(v, ((Field)field$Builder0.build()));
            return this;
        }

        public Builder addFields(int v, Field field0) {
            this.copyOnWrite();
            ((Type)this.instance).addFields(v, field0);
            return this;
        }

        public Builder addFields(com.google.crypto.tink.shaded.protobuf.Field.Builder field$Builder0) {
            this.copyOnWrite();
            ((Type)this.instance).addFields(((Field)field$Builder0.build()));
            return this;
        }

        public Builder addFields(Field field0) {
            this.copyOnWrite();
            ((Type)this.instance).addFields(field0);
            return this;
        }

        public Builder addOneofs(String s) {
            this.copyOnWrite();
            ((Type)this.instance).addOneofs(s);
            return this;
        }

        public Builder addOneofsBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Type)this.instance).addOneofsBytes(byteString0);
            return this;
        }

        public Builder addOptions(int v, com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((Type)this.instance).addOptions(v, ((Option)option$Builder0.build()));
            return this;
        }

        public Builder addOptions(int v, Option option0) {
            this.copyOnWrite();
            ((Type)this.instance).addOptions(v, option0);
            return this;
        }

        public Builder addOptions(com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((Type)this.instance).addOptions(((Option)option$Builder0.build()));
            return this;
        }

        public Builder addOptions(Option option0) {
            this.copyOnWrite();
            ((Type)this.instance).addOptions(option0);
            return this;
        }

        public Builder clearFields() {
            this.copyOnWrite();
            ((Type)this.instance).clearFields();
            return this;
        }

        public Builder clearName() {
            this.copyOnWrite();
            ((Type)this.instance).clearName();
            return this;
        }

        public Builder clearOneofs() {
            this.copyOnWrite();
            ((Type)this.instance).clearOneofs();
            return this;
        }

        public Builder clearOptions() {
            this.copyOnWrite();
            ((Type)this.instance).clearOptions();
            return this;
        }

        public Builder clearSourceContext() {
            this.copyOnWrite();
            ((Type)this.instance).clearSourceContext();
            return this;
        }

        public Builder clearSyntax() {
            this.copyOnWrite();
            ((Type)this.instance).clearSyntax();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public Field getFields(int v) {
            return ((Type)this.instance).getFields(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public int getFieldsCount() {
            return ((Type)this.instance).getFieldsCount();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public List getFieldsList() {
            return Collections.unmodifiableList(((Type)this.instance).getFieldsList());
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public String getName() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public ByteString getNameBytes() {
            return ((Type)this.instance).getNameBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public String getOneofs(int v) {
            return ((Type)this.instance).getOneofs(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public ByteString getOneofsBytes(int v) {
            return ((Type)this.instance).getOneofsBytes(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public int getOneofsCount() {
            return ((Type)this.instance).getOneofsCount();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public List getOneofsList() {
            return Collections.unmodifiableList(((Type)this.instance).getOneofsList());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public Option getOptions(int v) {
            return ((Type)this.instance).getOptions(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public int getOptionsCount() {
            return ((Type)this.instance).getOptionsCount();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public List getOptionsList() {
            return Collections.unmodifiableList(((Type)this.instance).getOptionsList());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public SourceContext getSourceContext() {
            return ((Type)this.instance).getSourceContext();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public Syntax getSyntax() {
            return ((Type)this.instance).getSyntax();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public int getSyntaxValue() {
            return ((Type)this.instance).getSyntaxValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
        public boolean hasSourceContext() {
            return ((Type)this.instance).hasSourceContext();
        }

        public Builder mergeSourceContext(SourceContext sourceContext0) {
            this.copyOnWrite();
            ((Type)this.instance).mergeSourceContext(sourceContext0);
            return this;
        }

        public Builder removeFields(int v) {
            this.copyOnWrite();
            ((Type)this.instance).removeFields(v);
            return this;
        }

        public Builder removeOptions(int v) {
            this.copyOnWrite();
            ((Type)this.instance).removeOptions(v);
            return this;
        }

        public Builder setFields(int v, com.google.crypto.tink.shaded.protobuf.Field.Builder field$Builder0) {
            this.copyOnWrite();
            ((Type)this.instance).setFields(v, ((Field)field$Builder0.build()));
            return this;
        }

        public Builder setFields(int v, Field field0) {
            this.copyOnWrite();
            ((Type)this.instance).setFields(v, field0);
            return this;
        }

        public Builder setName(String s) {
            this.copyOnWrite();
            ((Type)this.instance).setName(s);
            return this;
        }

        public Builder setNameBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Type)this.instance).setNameBytes(byteString0);
            return this;
        }

        public Builder setOneofs(int v, String s) {
            this.copyOnWrite();
            ((Type)this.instance).setOneofs(v, s);
            return this;
        }

        public Builder setOptions(int v, com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((Type)this.instance).setOptions(v, ((Option)option$Builder0.build()));
            return this;
        }

        public Builder setOptions(int v, Option option0) {
            this.copyOnWrite();
            ((Type)this.instance).setOptions(v, option0);
            return this;
        }

        public Builder setSourceContext(com.google.crypto.tink.shaded.protobuf.SourceContext.Builder sourceContext$Builder0) {
            this.copyOnWrite();
            ((Type)this.instance).setSourceContext(((SourceContext)sourceContext$Builder0.build()));
            return this;
        }

        public Builder setSourceContext(SourceContext sourceContext0) {
            this.copyOnWrite();
            ((Type)this.instance).setSourceContext(sourceContext0);
            return this;
        }

        public Builder setSyntax(Syntax syntax0) {
            this.copyOnWrite();
            ((Type)this.instance).setSyntax(syntax0);
            return this;
        }

        public Builder setSyntaxValue(int v) {
            this.copyOnWrite();
            ((Type)this.instance).setSyntaxValue(v);
            return this;
        }
    }

    private static final Type DEFAULT_INSTANCE = null;
    public static final int FIELDS_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int ONEOFS_FIELD_NUMBER = 3;
    public static final int OPTIONS_FIELD_NUMBER = 4;
    private static volatile Parser PARSER = null;
    public static final int SOURCE_CONTEXT_FIELD_NUMBER = 5;
    public static final int SYNTAX_FIELD_NUMBER = 6;
    private ProtobufList fields_;
    private String name_;
    private ProtobufList oneofs_;
    private ProtobufList options_;
    private SourceContext sourceContext_;
    private int syntax_;

    static {
        Type type0 = new Type();
        Type.DEFAULT_INSTANCE = type0;
        GeneratedMessageLite.registerDefaultInstance(Type.class, type0);
    }

    private Type() {
        this.name_ = "";
        this.fields_ = Type.emptyProtobufList();
        this.oneofs_ = GeneratedMessageLite.emptyProtobufList();
        this.options_ = Type.emptyProtobufList();
    }

    private void addAllFields(Iterable iterable0) {
        this.ensureFieldsIsMutable();
        AbstractMessageLite.addAll(iterable0, this.fields_);
    }

    private void addAllOneofs(Iterable iterable0) {
        this.ensureOneofsIsMutable();
        AbstractMessageLite.addAll(iterable0, this.oneofs_);
    }

    private void addAllOptions(Iterable iterable0) {
        this.ensureOptionsIsMutable();
        AbstractMessageLite.addAll(iterable0, this.options_);
    }

    private void addFields(int v, Field field0) {
        field0.getClass();
        this.ensureFieldsIsMutable();
        this.fields_.add(v, field0);
    }

    private void addFields(Field field0) {
        field0.getClass();
        this.ensureFieldsIsMutable();
        this.fields_.add(field0);
    }

    private void addOneofs(String s) {
        s.getClass();
        this.ensureOneofsIsMutable();
        this.oneofs_.add(s);
    }

    private void addOneofsBytes(ByteString byteString0) {
        Type.checkByteStringIsUtf8(byteString0);
        this.ensureOneofsIsMutable();
        this.oneofs_.add(byteString0.toStringUtf8());
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

    private void clearFields() {
        this.fields_ = Type.emptyProtobufList();
    }

    private void clearName() {
        this.name_ = "";
    }

    private void clearOneofs() {
        this.oneofs_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearOptions() {
        this.options_ = Type.emptyProtobufList();
    }

    private void clearSourceContext() {
        this.sourceContext_ = null;
    }

    private void clearSyntax() {
        this.syntax_ = 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.Type.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Type();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Type.newMessageInfo(Type.DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0003\u0000\u0001Ȉ\u0002\u001B\u0003Ț\u0004\u001B\u0005\t\u0006\f", new Object[]{"name_", "fields_", Field.class, "oneofs_", "options_", Option.class, "sourceContext_", "syntax_"});
            }
            case 4: {
                return Type.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Type.PARSER;
                if(parser0 == null) {
                    Class class0 = Type.class;
                    synchronized(class0) {
                        Parser parser1 = Type.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Type.DEFAULT_INSTANCE);
                            Type.PARSER = parser1;
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

    private void ensureFieldsIsMutable() {
        ProtobufList internal$ProtobufList0 = this.fields_;
        if(!internal$ProtobufList0.isModifiable()) {
            this.fields_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList0);
        }
    }

    private void ensureOneofsIsMutable() {
        ProtobufList internal$ProtobufList0 = this.oneofs_;
        if(!internal$ProtobufList0.isModifiable()) {
            this.oneofs_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList0);
        }
    }

    private void ensureOptionsIsMutable() {
        ProtobufList internal$ProtobufList0 = this.options_;
        if(!internal$ProtobufList0.isModifiable()) {
            this.options_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList0);
        }
    }

    public static Type getDefaultInstance() {
        return Type.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public Field getFields(int v) {
        return (Field)this.fields_.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public int getFieldsCount() {
        return this.fields_.size();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public List getFieldsList() {
        return this.fields_;
    }

    public FieldOrBuilder getFieldsOrBuilder(int v) {
        return (FieldOrBuilder)this.fields_.get(v);
    }

    public List getFieldsOrBuilderList() {
        return this.fields_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public String getName() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public String getOneofs(int v) {
        return (String)this.oneofs_.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public ByteString getOneofsBytes(int v) {
        return ByteString.copyFromUtf8(((String)this.oneofs_.get(v)));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public int getOneofsCount() {
        return this.oneofs_.size();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public List getOneofsList() {
        return this.oneofs_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public Option getOptions(int v) {
        return (Option)this.options_.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public int getOptionsCount() {
        return this.options_.size();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public List getOptionsList() {
        return this.options_;
    }

    public OptionOrBuilder getOptionsOrBuilder(int v) {
        return (OptionOrBuilder)this.options_.get(v);
    }

    public List getOptionsOrBuilderList() {
        return this.options_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public SourceContext getSourceContext() {
        return this.sourceContext_ == null ? SourceContext.getDefaultInstance() : this.sourceContext_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public Syntax getSyntax() {
        Syntax syntax0 = Syntax.forNumber(this.syntax_);
        return syntax0 == null ? Syntax.UNRECOGNIZED : syntax0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
    public int getSyntaxValue() {
        return this.syntax_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.TypeOrBuilder
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
        return (Builder)Type.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Type type0) {
        return (Builder)Type.DEFAULT_INSTANCE.createBuilder(type0);
    }

    public static Type parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Type)Type.parseDelimitedFrom(Type.DEFAULT_INSTANCE, inputStream0);
    }

    public static Type parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Type)Type.parseDelimitedFrom(Type.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Type parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Type)GeneratedMessageLite.parseFrom(Type.DEFAULT_INSTANCE, byteString0);
    }

    public static Type parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Type)GeneratedMessageLite.parseFrom(Type.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Type parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Type)GeneratedMessageLite.parseFrom(Type.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Type parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Type)GeneratedMessageLite.parseFrom(Type.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Type parseFrom(InputStream inputStream0) throws IOException {
        return (Type)GeneratedMessageLite.parseFrom(Type.DEFAULT_INSTANCE, inputStream0);
    }

    public static Type parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Type)GeneratedMessageLite.parseFrom(Type.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Type parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Type)GeneratedMessageLite.parseFrom(Type.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Type parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Type)GeneratedMessageLite.parseFrom(Type.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Type parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Type)GeneratedMessageLite.parseFrom(Type.DEFAULT_INSTANCE, arr_b);
    }

    public static Type parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Type)GeneratedMessageLite.parseFrom(Type.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Type.DEFAULT_INSTANCE.getParserForType();
    }

    private void removeFields(int v) {
        this.ensureFieldsIsMutable();
        this.fields_.remove(v);
    }

    private void removeOptions(int v) {
        this.ensureOptionsIsMutable();
        this.options_.remove(v);
    }

    private void setFields(int v, Field field0) {
        field0.getClass();
        this.ensureFieldsIsMutable();
        this.fields_.set(v, field0);
    }

    private void setName(String s) {
        s.getClass();
        this.name_ = s;
    }

    private void setNameBytes(ByteString byteString0) {
        Type.checkByteStringIsUtf8(byteString0);
        this.name_ = byteString0.toStringUtf8();
    }

    private void setOneofs(int v, String s) {
        s.getClass();
        this.ensureOneofsIsMutable();
        this.oneofs_.set(v, s);
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
}

