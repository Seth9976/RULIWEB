package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Value extends GeneratedMessageLite implements ValueOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements ValueOrBuilder {
        private Builder() {
            super(Value.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.Value.1 value$10) {
        }

        public Builder clearBoolValue() {
            this.copyOnWrite();
            ((Value)this.instance).clearBoolValue();
            return this;
        }

        public Builder clearKind() {
            this.copyOnWrite();
            ((Value)this.instance).clearKind();
            return this;
        }

        public Builder clearListValue() {
            this.copyOnWrite();
            ((Value)this.instance).clearListValue();
            return this;
        }

        public Builder clearNullValue() {
            this.copyOnWrite();
            ((Value)this.instance).clearNullValue();
            return this;
        }

        public Builder clearNumberValue() {
            this.copyOnWrite();
            ((Value)this.instance).clearNumberValue();
            return this;
        }

        public Builder clearStringValue() {
            this.copyOnWrite();
            ((Value)this.instance).clearStringValue();
            return this;
        }

        public Builder clearStructValue() {
            this.copyOnWrite();
            ((Value)this.instance).clearStructValue();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public boolean getBoolValue() {
            return ((Value)this.instance).getBoolValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public KindCase getKindCase() {
            return ((Value)this.instance).getKindCase();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public ListValue getListValue() {
            return ((Value)this.instance).getListValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public NullValue getNullValue() {
            return ((Value)this.instance).getNullValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public int getNullValueValue() {
            return ((Value)this.instance).getNullValueValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public double getNumberValue() {
            return ((Value)this.instance).getNumberValue();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public String getStringValue() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public ByteString getStringValueBytes() {
            return ((Value)this.instance).getStringValueBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public Struct getStructValue() {
            return ((Value)this.instance).getStructValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public boolean hasBoolValue() {
            return ((Value)this.instance).hasBoolValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public boolean hasListValue() {
            return ((Value)this.instance).hasListValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public boolean hasNullValue() {
            return ((Value)this.instance).hasNullValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public boolean hasNumberValue() {
            return ((Value)this.instance).hasNumberValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public boolean hasStringValue() {
            return ((Value)this.instance).hasStringValue();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
        public boolean hasStructValue() {
            return ((Value)this.instance).hasStructValue();
        }

        public Builder mergeListValue(ListValue listValue0) {
            this.copyOnWrite();
            ((Value)this.instance).mergeListValue(listValue0);
            return this;
        }

        public Builder mergeStructValue(Struct struct0) {
            this.copyOnWrite();
            ((Value)this.instance).mergeStructValue(struct0);
            return this;
        }

        public Builder setBoolValue(boolean z) {
            this.copyOnWrite();
            ((Value)this.instance).setBoolValue(z);
            return this;
        }

        public Builder setListValue(com.google.crypto.tink.shaded.protobuf.ListValue.Builder listValue$Builder0) {
            this.copyOnWrite();
            ((Value)this.instance).setListValue(((ListValue)listValue$Builder0.build()));
            return this;
        }

        public Builder setListValue(ListValue listValue0) {
            this.copyOnWrite();
            ((Value)this.instance).setListValue(listValue0);
            return this;
        }

        public Builder setNullValue(NullValue nullValue0) {
            this.copyOnWrite();
            ((Value)this.instance).setNullValue(nullValue0);
            return this;
        }

        public Builder setNullValueValue(int v) {
            this.copyOnWrite();
            ((Value)this.instance).setNullValueValue(v);
            return this;
        }

        public Builder setNumberValue(double f) {
            this.copyOnWrite();
            ((Value)this.instance).setNumberValue(f);
            return this;
        }

        public Builder setStringValue(String s) {
            this.copyOnWrite();
            ((Value)this.instance).setStringValue(s);
            return this;
        }

        public Builder setStringValueBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Value)this.instance).setStringValueBytes(byteString0);
            return this;
        }

        public Builder setStructValue(com.google.crypto.tink.shaded.protobuf.Struct.Builder struct$Builder0) {
            this.copyOnWrite();
            ((Value)this.instance).setStructValue(((Struct)struct$Builder0.build()));
            return this;
        }

        public Builder setStructValue(Struct struct0) {
            this.copyOnWrite();
            ((Value)this.instance).setStructValue(struct0);
            return this;
        }
    }

    public static enum KindCase {
        NULL_VALUE(1),
        NUMBER_VALUE(2),
        STRING_VALUE(3),
        BOOL_VALUE(4),
        STRUCT_VALUE(5),
        LIST_VALUE(6),
        KIND_NOT_SET(0);

        private final int value;

        private KindCase(int v1) {
            this.value = v1;
        }

        public static KindCase forNumber(int v) {
            switch(v) {
                case 0: {
                    return KindCase.KIND_NOT_SET;
                }
                case 1: {
                    return KindCase.NULL_VALUE;
                }
                case 2: {
                    return KindCase.NUMBER_VALUE;
                }
                case 3: {
                    return KindCase.STRING_VALUE;
                }
                case 4: {
                    return KindCase.BOOL_VALUE;
                }
                case 5: {
                    return KindCase.STRUCT_VALUE;
                }
                case 6: {
                    return KindCase.LIST_VALUE;
                }
                default: {
                    return null;
                }
            }
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static KindCase valueOf(int v) {
            return KindCase.forNumber(v);
        }
    }

    public static final int BOOL_VALUE_FIELD_NUMBER = 4;
    private static final Value DEFAULT_INSTANCE = null;
    public static final int LIST_VALUE_FIELD_NUMBER = 6;
    public static final int NULL_VALUE_FIELD_NUMBER = 1;
    public static final int NUMBER_VALUE_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int STRING_VALUE_FIELD_NUMBER = 3;
    public static final int STRUCT_VALUE_FIELD_NUMBER = 5;
    private int kindCase_;
    private Object kind_;

    static {
        Value value0 = new Value();
        Value.DEFAULT_INSTANCE = value0;
        GeneratedMessageLite.registerDefaultInstance(Value.class, value0);
    }

    private Value() {
        this.kindCase_ = 0;
    }

    private void clearBoolValue() {
        if(this.kindCase_ == 4) {
            this.kindCase_ = 0;
            this.kind_ = null;
        }
    }

    private void clearKind() {
        this.kindCase_ = 0;
        this.kind_ = null;
    }

    private void clearListValue() {
        if(this.kindCase_ == 6) {
            this.kindCase_ = 0;
            this.kind_ = null;
        }
    }

    private void clearNullValue() {
        if(this.kindCase_ == 1) {
            this.kindCase_ = 0;
            this.kind_ = null;
        }
    }

    private void clearNumberValue() {
        if(this.kindCase_ == 2) {
            this.kindCase_ = 0;
            this.kind_ = null;
        }
    }

    private void clearStringValue() {
        if(this.kindCase_ == 3) {
            this.kindCase_ = 0;
            this.kind_ = null;
        }
    }

    private void clearStructValue() {
        if(this.kindCase_ == 5) {
            this.kindCase_ = 0;
            this.kind_ = null;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.Value.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Value();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Value.newMessageInfo(Value.DEFAULT_INSTANCE, "\u0000\u0006\u0001\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001?\u0000\u00023\u0000\u0003Ȼ\u0000\u0004:\u0000\u0005<\u0000\u0006<\u0000", new Object[]{"kind_", "kindCase_", Struct.class, ListValue.class});
            }
            case 4: {
                return Value.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Value.PARSER;
                if(parser0 == null) {
                    Class class0 = Value.class;
                    synchronized(class0) {
                        Parser parser1 = Value.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Value.DEFAULT_INSTANCE);
                            Value.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public boolean getBoolValue() {
        return this.kindCase_ == 4 ? ((Boolean)this.kind_).booleanValue() : false;
    }

    public static Value getDefaultInstance() {
        return Value.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public KindCase getKindCase() {
        return KindCase.forNumber(this.kindCase_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public ListValue getListValue() {
        return this.kindCase_ == 6 ? ((ListValue)this.kind_) : ListValue.getDefaultInstance();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public NullValue getNullValue() {
        if(this.kindCase_ == 1) {
            NullValue nullValue0 = NullValue.forNumber(((int)(((Integer)this.kind_))));
            return nullValue0 == null ? NullValue.UNRECOGNIZED : nullValue0;
        }
        return NullValue.NULL_VALUE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public int getNullValueValue() {
        return this.kindCase_ == 1 ? ((int)(((Integer)this.kind_))) : 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public double getNumberValue() {
        return this.kindCase_ == 2 ? ((double)(((Double)this.kind_))) : 0.0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public String getStringValue() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public ByteString getStringValueBytes() {
        return this.kindCase_ == 3 ? ByteString.copyFromUtf8(((String)this.kind_)) : ByteString.copyFromUtf8("");
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public Struct getStructValue() {
        return this.kindCase_ == 5 ? ((Struct)this.kind_) : Struct.getDefaultInstance();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public boolean hasBoolValue() {
        return this.kindCase_ == 4;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public boolean hasListValue() {
        return this.kindCase_ == 6;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public boolean hasNullValue() {
        return this.kindCase_ == 1;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public boolean hasNumberValue() {
        return this.kindCase_ == 2;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public boolean hasStringValue() {
        return this.kindCase_ == 3;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ValueOrBuilder
    public boolean hasStructValue() {
        return this.kindCase_ == 5;
    }

    private void mergeListValue(ListValue listValue0) {
        listValue0.getClass();
        this.kind_ = this.kindCase_ != 6 || this.kind_ == ListValue.getDefaultInstance() ? listValue0 : ((com.google.crypto.tink.shaded.protobuf.ListValue.Builder)ListValue.newBuilder(((ListValue)this.kind_)).mergeFrom(listValue0)).buildPartial();
        this.kindCase_ = 6;
    }

    private void mergeStructValue(Struct struct0) {
        struct0.getClass();
        this.kind_ = this.kindCase_ != 5 || this.kind_ == Struct.getDefaultInstance() ? struct0 : ((com.google.crypto.tink.shaded.protobuf.Struct.Builder)Struct.newBuilder(((Struct)this.kind_)).mergeFrom(struct0)).buildPartial();
        this.kindCase_ = 5;
    }

    public static Builder newBuilder() {
        return (Builder)Value.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Value value0) {
        return (Builder)Value.DEFAULT_INSTANCE.createBuilder(value0);
    }

    public static Value parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Value)Value.parseDelimitedFrom(Value.DEFAULT_INSTANCE, inputStream0);
    }

    public static Value parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Value)Value.parseDelimitedFrom(Value.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Value parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Value)GeneratedMessageLite.parseFrom(Value.DEFAULT_INSTANCE, byteString0);
    }

    public static Value parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Value)GeneratedMessageLite.parseFrom(Value.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Value parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Value)GeneratedMessageLite.parseFrom(Value.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Value parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Value)GeneratedMessageLite.parseFrom(Value.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Value parseFrom(InputStream inputStream0) throws IOException {
        return (Value)GeneratedMessageLite.parseFrom(Value.DEFAULT_INSTANCE, inputStream0);
    }

    public static Value parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Value)GeneratedMessageLite.parseFrom(Value.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Value parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Value)GeneratedMessageLite.parseFrom(Value.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Value parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Value)GeneratedMessageLite.parseFrom(Value.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Value parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Value)GeneratedMessageLite.parseFrom(Value.DEFAULT_INSTANCE, arr_b);
    }

    public static Value parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Value)GeneratedMessageLite.parseFrom(Value.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Value.DEFAULT_INSTANCE.getParserForType();
    }

    private void setBoolValue(boolean z) {
        this.kindCase_ = 4;
        this.kind_ = Boolean.valueOf(z);
    }

    private void setListValue(ListValue listValue0) {
        listValue0.getClass();
        this.kind_ = listValue0;
        this.kindCase_ = 6;
    }

    private void setNullValue(NullValue nullValue0) {
        this.kind_ = nullValue0.getNumber();
        this.kindCase_ = 1;
    }

    private void setNullValueValue(int v) {
        this.kindCase_ = 1;
        this.kind_ = v;
    }

    private void setNumberValue(double f) {
        this.kindCase_ = 2;
        this.kind_ = f;
    }

    private void setStringValue(String s) {
        s.getClass();
        this.kindCase_ = 3;
        this.kind_ = s;
    }

    private void setStringValueBytes(ByteString byteString0) {
        Value.checkByteStringIsUtf8(byteString0);
        this.kind_ = byteString0.toStringUtf8();
        this.kindCase_ = 3;
    }

    private void setStructValue(Struct struct0) {
        struct0.getClass();
        this.kind_ = struct0;
        this.kindCase_ = 5;
    }
}

