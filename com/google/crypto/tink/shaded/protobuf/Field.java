package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class Field extends GeneratedMessageLite implements FieldOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements FieldOrBuilder {
        private Builder() {
            super(Field.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.Field.1 field$10) {
        }

        public Builder addAllOptions(Iterable iterable0) {
            this.copyOnWrite();
            ((Field)this.instance).addAllOptions(iterable0);
            return this;
        }

        public Builder addOptions(int v, com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((Field)this.instance).addOptions(v, ((Option)option$Builder0.build()));
            return this;
        }

        public Builder addOptions(int v, Option option0) {
            this.copyOnWrite();
            ((Field)this.instance).addOptions(v, option0);
            return this;
        }

        public Builder addOptions(com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((Field)this.instance).addOptions(((Option)option$Builder0.build()));
            return this;
        }

        public Builder addOptions(Option option0) {
            this.copyOnWrite();
            ((Field)this.instance).addOptions(option0);
            return this;
        }

        public Builder clearCardinality() {
            this.copyOnWrite();
            ((Field)this.instance).clearCardinality();
            return this;
        }

        public Builder clearDefaultValue() {
            this.copyOnWrite();
            ((Field)this.instance).clearDefaultValue();
            return this;
        }

        public Builder clearJsonName() {
            this.copyOnWrite();
            ((Field)this.instance).clearJsonName();
            return this;
        }

        public Builder clearKind() {
            this.copyOnWrite();
            ((Field)this.instance).clearKind();
            return this;
        }

        public Builder clearName() {
            this.copyOnWrite();
            ((Field)this.instance).clearName();
            return this;
        }

        public Builder clearNumber() {
            this.copyOnWrite();
            ((Field)this.instance).clearNumber();
            return this;
        }

        public Builder clearOneofIndex() {
            this.copyOnWrite();
            ((Field)this.instance).clearOneofIndex();
            return this;
        }

        public Builder clearOptions() {
            this.copyOnWrite();
            ((Field)this.instance).clearOptions();
            return this;
        }

        public Builder clearPacked() {
            this.copyOnWrite();
            ((Field)this.instance).clearPacked();
            return this;
        }

        public Builder clearTypeUrl() {
            this.copyOnWrite();
            ((Field)this.instance).clearTypeUrl();
            return this;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public Cardinality getCardinality() {
            return ((Field)this.instance).getCardinality();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public int getCardinalityValue() {
            return ((Field)this.instance).getCardinalityValue();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public String getDefaultValue() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public ByteString getDefaultValueBytes() {
            return ((Field)this.instance).getDefaultValueBytes();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public String getJsonName() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public ByteString getJsonNameBytes() {
            return ((Field)this.instance).getJsonNameBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public Kind getKind() {
            return ((Field)this.instance).getKind();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public int getKindValue() {
            return ((Field)this.instance).getKindValue();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public String getName() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public ByteString getNameBytes() {
            return ((Field)this.instance).getNameBytes();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public int getNumber() {
            return ((Field)this.instance).getNumber();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public int getOneofIndex() {
            return ((Field)this.instance).getOneofIndex();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public Option getOptions(int v) {
            return ((Field)this.instance).getOptions(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public int getOptionsCount() {
            return ((Field)this.instance).getOptionsCount();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public List getOptionsList() {
            return Collections.unmodifiableList(((Field)this.instance).getOptionsList());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public boolean getPacked() {
            return ((Field)this.instance).getPacked();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public String getTypeUrl() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
        public ByteString getTypeUrlBytes() {
            return ((Field)this.instance).getTypeUrlBytes();
        }

        public Builder removeOptions(int v) {
            this.copyOnWrite();
            ((Field)this.instance).removeOptions(v);
            return this;
        }

        public Builder setCardinality(Cardinality field$Cardinality0) {
            this.copyOnWrite();
            ((Field)this.instance).setCardinality(field$Cardinality0);
            return this;
        }

        public Builder setCardinalityValue(int v) {
            this.copyOnWrite();
            ((Field)this.instance).setCardinalityValue(v);
            return this;
        }

        public Builder setDefaultValue(String s) {
            this.copyOnWrite();
            ((Field)this.instance).setDefaultValue(s);
            return this;
        }

        public Builder setDefaultValueBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Field)this.instance).setDefaultValueBytes(byteString0);
            return this;
        }

        public Builder setJsonName(String s) {
            this.copyOnWrite();
            ((Field)this.instance).setJsonName(s);
            return this;
        }

        public Builder setJsonNameBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Field)this.instance).setJsonNameBytes(byteString0);
            return this;
        }

        public Builder setKind(Kind field$Kind0) {
            this.copyOnWrite();
            ((Field)this.instance).setKind(field$Kind0);
            return this;
        }

        public Builder setKindValue(int v) {
            this.copyOnWrite();
            ((Field)this.instance).setKindValue(v);
            return this;
        }

        public Builder setName(String s) {
            this.copyOnWrite();
            ((Field)this.instance).setName(s);
            return this;
        }

        public Builder setNameBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Field)this.instance).setNameBytes(byteString0);
            return this;
        }

        public Builder setNumber(int v) {
            this.copyOnWrite();
            ((Field)this.instance).setNumber(v);
            return this;
        }

        public Builder setOneofIndex(int v) {
            this.copyOnWrite();
            ((Field)this.instance).setOneofIndex(v);
            return this;
        }

        public Builder setOptions(int v, com.google.crypto.tink.shaded.protobuf.Option.Builder option$Builder0) {
            this.copyOnWrite();
            ((Field)this.instance).setOptions(v, ((Option)option$Builder0.build()));
            return this;
        }

        public Builder setOptions(int v, Option option0) {
            this.copyOnWrite();
            ((Field)this.instance).setOptions(v, option0);
            return this;
        }

        public Builder setPacked(boolean z) {
            this.copyOnWrite();
            ((Field)this.instance).setPacked(z);
            return this;
        }

        public Builder setTypeUrl(String s) {
            this.copyOnWrite();
            ((Field)this.instance).setTypeUrl(s);
            return this;
        }

        public Builder setTypeUrlBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Field)this.instance).setTypeUrlBytes(byteString0);
            return this;
        }
    }

    public static enum Cardinality implements EnumLite {
        static final class CardinalityVerifier implements EnumVerifier {
            static final EnumVerifier INSTANCE;

            static {
                CardinalityVerifier.INSTANCE = new CardinalityVerifier();
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
            public boolean isInRange(int v) {
                return Cardinality.forNumber(v) != null;
            }
        }

        CARDINALITY_UNKNOWN(0),
        CARDINALITY_OPTIONAL(1),
        CARDINALITY_REQUIRED(2),
        CARDINALITY_REPEATED(3),
        UNRECOGNIZED(-1);

        public static final int CARDINALITY_OPTIONAL_VALUE = 1;
        public static final int CARDINALITY_REPEATED_VALUE = 3;
        public static final int CARDINALITY_REQUIRED_VALUE = 2;
        public static final int CARDINALITY_UNKNOWN_VALUE;
        private static final EnumLiteMap internalValueMap;
        private final int value;

        static {
            Cardinality.internalValueMap = new EnumLiteMap() {
                public Cardinality findValueByNumber(int v) {
                    return Cardinality.forNumber(v);
                }

                @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
                public EnumLite findValueByNumber(int v) {
                    return this.findValueByNumber(v);
                }
            };
        }

        private Cardinality(int v1) {
            this.value = v1;
        }

        public static Cardinality forNumber(int v) {
            switch(v) {
                case 0: {
                    return Cardinality.CARDINALITY_UNKNOWN;
                }
                case 1: {
                    return Cardinality.CARDINALITY_OPTIONAL;
                }
                case 2: {
                    return Cardinality.CARDINALITY_REQUIRED;
                }
                case 3: {
                    return Cardinality.CARDINALITY_REPEATED;
                }
                default: {
                    return null;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
        public final int getNumber() {
            if(this == Cardinality.UNRECOGNIZED) {
                throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
            }
            return this.value;
        }

        public static EnumLiteMap internalGetValueMap() {
            return Cardinality.internalValueMap;
        }

        public static EnumVerifier internalGetVerifier() {
            return CardinalityVerifier.INSTANCE;
        }

        @Deprecated
        public static Cardinality valueOf(int v) {
            return Cardinality.forNumber(v);
        }
    }

    public static enum Kind implements EnumLite {
        static final class KindVerifier implements EnumVerifier {
            static final EnumVerifier INSTANCE;

            static {
                KindVerifier.INSTANCE = new KindVerifier();
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
            public boolean isInRange(int v) {
                return Kind.forNumber(v) != null;
            }
        }

        TYPE_UNKNOWN(0),
        TYPE_DOUBLE(1),
        TYPE_FLOAT(2),
        TYPE_INT64(3),
        TYPE_UINT64(4),
        TYPE_INT32(5),
        TYPE_FIXED64(6),
        TYPE_FIXED32(7),
        TYPE_BOOL(8),
        TYPE_STRING(9),
        TYPE_GROUP(10),
        TYPE_MESSAGE(11),
        TYPE_BYTES(12),
        TYPE_UINT32(13),
        TYPE_ENUM(14),
        TYPE_SFIXED32(15),
        TYPE_SFIXED64(16),
        TYPE_SINT32(17),
        TYPE_SINT64(18),
        UNRECOGNIZED(-1);

        public static final int TYPE_BOOL_VALUE = 8;
        public static final int TYPE_BYTES_VALUE = 12;
        public static final int TYPE_DOUBLE_VALUE = 1;
        public static final int TYPE_ENUM_VALUE = 14;
        public static final int TYPE_FIXED32_VALUE = 7;
        public static final int TYPE_FIXED64_VALUE = 6;
        public static final int TYPE_FLOAT_VALUE = 2;
        public static final int TYPE_GROUP_VALUE = 10;
        public static final int TYPE_INT32_VALUE = 5;
        public static final int TYPE_INT64_VALUE = 3;
        public static final int TYPE_MESSAGE_VALUE = 11;
        public static final int TYPE_SFIXED32_VALUE = 15;
        public static final int TYPE_SFIXED64_VALUE = 16;
        public static final int TYPE_SINT32_VALUE = 17;
        public static final int TYPE_SINT64_VALUE = 18;
        public static final int TYPE_STRING_VALUE = 9;
        public static final int TYPE_UINT32_VALUE = 13;
        public static final int TYPE_UINT64_VALUE = 4;
        public static final int TYPE_UNKNOWN_VALUE;
        private static final EnumLiteMap internalValueMap;
        private final int value;

        static {
            Kind.internalValueMap = new EnumLiteMap() {
                public Kind findValueByNumber(int v) {
                    return Kind.forNumber(v);
                }

                @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
                public EnumLite findValueByNumber(int v) {
                    return this.findValueByNumber(v);
                }
            };
        }

        private Kind(int v1) {
            this.value = v1;
        }

        public static Kind forNumber(int v) {
            switch(v) {
                case 0: {
                    return Kind.TYPE_UNKNOWN;
                }
                case 1: {
                    return Kind.TYPE_DOUBLE;
                }
                case 2: {
                    return Kind.TYPE_FLOAT;
                }
                case 3: {
                    return Kind.TYPE_INT64;
                }
                case 4: {
                    return Kind.TYPE_UINT64;
                }
                case 5: {
                    return Kind.TYPE_INT32;
                }
                case 6: {
                    return Kind.TYPE_FIXED64;
                }
                case 7: {
                    return Kind.TYPE_FIXED32;
                }
                case 8: {
                    return Kind.TYPE_BOOL;
                }
                case 9: {
                    return Kind.TYPE_STRING;
                }
                case 10: {
                    return Kind.TYPE_GROUP;
                }
                case 11: {
                    return Kind.TYPE_MESSAGE;
                }
                case 12: {
                    return Kind.TYPE_BYTES;
                }
                case 13: {
                    return Kind.TYPE_UINT32;
                }
                case 14: {
                    return Kind.TYPE_ENUM;
                }
                case 15: {
                    return Kind.TYPE_SFIXED32;
                }
                case 16: {
                    return Kind.TYPE_SFIXED64;
                }
                case 17: {
                    return Kind.TYPE_SINT32;
                }
                case 18: {
                    return Kind.TYPE_SINT64;
                }
                default: {
                    return null;
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
        public final int getNumber() {
            if(this == Kind.UNRECOGNIZED) {
                throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
            }
            return this.value;
        }

        public static EnumLiteMap internalGetValueMap() {
            return Kind.internalValueMap;
        }

        public static EnumVerifier internalGetVerifier() {
            return KindVerifier.INSTANCE;
        }

        @Deprecated
        public static Kind valueOf(int v) {
            return Kind.forNumber(v);
        }
    }

    public static final int CARDINALITY_FIELD_NUMBER = 2;
    private static final Field DEFAULT_INSTANCE = null;
    public static final int DEFAULT_VALUE_FIELD_NUMBER = 11;
    public static final int JSON_NAME_FIELD_NUMBER = 10;
    public static final int KIND_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 4;
    public static final int NUMBER_FIELD_NUMBER = 3;
    public static final int ONEOF_INDEX_FIELD_NUMBER = 7;
    public static final int OPTIONS_FIELD_NUMBER = 9;
    public static final int PACKED_FIELD_NUMBER = 8;
    private static volatile Parser PARSER = null;
    public static final int TYPE_URL_FIELD_NUMBER = 6;
    private int cardinality_;
    private String defaultValue_;
    private String jsonName_;
    private int kind_;
    private String name_;
    private int number_;
    private int oneofIndex_;
    private ProtobufList options_;
    private boolean packed_;
    private String typeUrl_;

    static {
        Field field0 = new Field();
        Field.DEFAULT_INSTANCE = field0;
        GeneratedMessageLite.registerDefaultInstance(Field.class, field0);
    }

    private Field() {
        this.name_ = "";
        this.typeUrl_ = "";
        this.options_ = Field.emptyProtobufList();
        this.jsonName_ = "";
        this.defaultValue_ = "";
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

    private void clearCardinality() {
        this.cardinality_ = 0;
    }

    private void clearDefaultValue() {
        this.defaultValue_ = "";
    }

    private void clearJsonName() {
        this.jsonName_ = "";
    }

    private void clearKind() {
        this.kind_ = 0;
    }

    private void clearName() {
        this.name_ = "";
    }

    private void clearNumber() {
        this.number_ = 0;
    }

    private void clearOneofIndex() {
        this.oneofIndex_ = 0;
    }

    private void clearOptions() {
        this.options_ = Field.emptyProtobufList();
    }

    private void clearPacked() {
        this.packed_ = false;
    }

    private void clearTypeUrl() {
        this.typeUrl_ = "";
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.Field.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Field();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Field.newMessageInfo(Field.DEFAULT_INSTANCE, "\u0000\n\u0000\u0000\u0001\u000B\n\u0000\u0001\u0000\u0001\f\u0002\f\u0003\u0004\u0004Ȉ\u0006Ȉ\u0007\u0004\b\u0007\t\u001B\nȈ\u000BȈ", new Object[]{"kind_", "cardinality_", "number_", "name_", "typeUrl_", "oneofIndex_", "packed_", "options_", Option.class, "jsonName_", "defaultValue_"});
            }
            case 4: {
                return Field.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Field.PARSER;
                if(parser0 == null) {
                    Class class0 = Field.class;
                    synchronized(class0) {
                        Parser parser1 = Field.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Field.DEFAULT_INSTANCE);
                            Field.PARSER = parser1;
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

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public Cardinality getCardinality() {
        Cardinality field$Cardinality0 = Cardinality.forNumber(this.cardinality_);
        return field$Cardinality0 == null ? Cardinality.UNRECOGNIZED : field$Cardinality0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public int getCardinalityValue() {
        return this.cardinality_;
    }

    public static Field getDefaultInstance() {
        return Field.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public String getDefaultValue() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public ByteString getDefaultValueBytes() {
        return ByteString.copyFromUtf8(this.defaultValue_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public String getJsonName() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public ByteString getJsonNameBytes() {
        return ByteString.copyFromUtf8(this.jsonName_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public Kind getKind() {
        Kind field$Kind0 = Kind.forNumber(this.kind_);
        return field$Kind0 == null ? Kind.UNRECOGNIZED : field$Kind0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public int getKindValue() {
        return this.kind_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public String getName() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public int getNumber() {
        return this.number_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public int getOneofIndex() {
        return this.oneofIndex_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public Option getOptions(int v) {
        return (Option)this.options_.get(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public int getOptionsCount() {
        return this.options_.size();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public List getOptionsList() {
        return this.options_;
    }

    public OptionOrBuilder getOptionsOrBuilder(int v) {
        return (OptionOrBuilder)this.options_.get(v);
    }

    public List getOptionsOrBuilderList() {
        return this.options_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public boolean getPacked() {
        return this.packed_;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public String getTypeUrl() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.FieldOrBuilder
    public ByteString getTypeUrlBytes() {
        return ByteString.copyFromUtf8(this.typeUrl_);
    }

    public static Builder newBuilder() {
        return (Builder)Field.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Field field0) {
        return (Builder)Field.DEFAULT_INSTANCE.createBuilder(field0);
    }

    public static Field parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Field)Field.parseDelimitedFrom(Field.DEFAULT_INSTANCE, inputStream0);
    }

    public static Field parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Field)Field.parseDelimitedFrom(Field.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Field parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Field)GeneratedMessageLite.parseFrom(Field.DEFAULT_INSTANCE, byteString0);
    }

    public static Field parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Field)GeneratedMessageLite.parseFrom(Field.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Field parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Field)GeneratedMessageLite.parseFrom(Field.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Field parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Field)GeneratedMessageLite.parseFrom(Field.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Field parseFrom(InputStream inputStream0) throws IOException {
        return (Field)GeneratedMessageLite.parseFrom(Field.DEFAULT_INSTANCE, inputStream0);
    }

    public static Field parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Field)GeneratedMessageLite.parseFrom(Field.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Field parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Field)GeneratedMessageLite.parseFrom(Field.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Field parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Field)GeneratedMessageLite.parseFrom(Field.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Field parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Field)GeneratedMessageLite.parseFrom(Field.DEFAULT_INSTANCE, arr_b);
    }

    public static Field parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Field)GeneratedMessageLite.parseFrom(Field.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Field.DEFAULT_INSTANCE.getParserForType();
    }

    private void removeOptions(int v) {
        this.ensureOptionsIsMutable();
        this.options_.remove(v);
    }

    private void setCardinality(Cardinality field$Cardinality0) {
        this.cardinality_ = field$Cardinality0.getNumber();
    }

    private void setCardinalityValue(int v) {
        this.cardinality_ = v;
    }

    private void setDefaultValue(String s) {
        s.getClass();
        this.defaultValue_ = s;
    }

    private void setDefaultValueBytes(ByteString byteString0) {
        Field.checkByteStringIsUtf8(byteString0);
        this.defaultValue_ = byteString0.toStringUtf8();
    }

    private void setJsonName(String s) {
        s.getClass();
        this.jsonName_ = s;
    }

    private void setJsonNameBytes(ByteString byteString0) {
        Field.checkByteStringIsUtf8(byteString0);
        this.jsonName_ = byteString0.toStringUtf8();
    }

    private void setKind(Kind field$Kind0) {
        this.kind_ = field$Kind0.getNumber();
    }

    private void setKindValue(int v) {
        this.kind_ = v;
    }

    private void setName(String s) {
        s.getClass();
        this.name_ = s;
    }

    private void setNameBytes(ByteString byteString0) {
        Field.checkByteStringIsUtf8(byteString0);
        this.name_ = byteString0.toStringUtf8();
    }

    private void setNumber(int v) {
        this.number_ = v;
    }

    private void setOneofIndex(int v) {
        this.oneofIndex_ = v;
    }

    private void setOptions(int v, Option option0) {
        option0.getClass();
        this.ensureOptionsIsMutable();
        this.options_.set(v, option0);
    }

    private void setPacked(boolean z) {
        this.packed_ = z;
    }

    private void setTypeUrl(String s) {
        s.getClass();
        this.typeUrl_ = s;
    }

    private void setTypeUrlBytes(ByteString byteString0) {
        Field.checkByteStringIsUtf8(byteString0);
        this.typeUrl_ = byteString0.toStringUtf8();
    }
}

