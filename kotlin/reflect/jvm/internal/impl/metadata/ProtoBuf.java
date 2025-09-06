package kotlin.reflect.jvm.internal.impl.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractParser;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString.Output;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage.ExtensionWriter;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringArrayList;
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import kotlin.reflect.jvm.internal.impl.protobuf.ProtocolStringList;

public final class ProtoBuf {
    public static final class Annotation extends GeneratedMessageLite implements ProtoBuf.AnnotationOrBuilder {
        public static final class Argument extends GeneratedMessageLite implements ProtoBuf.Annotation.ArgumentOrBuilder {
            public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.Annotation.ArgumentOrBuilder {
                private int bitField0_;
                private int nameId_;
                private Value value_;

                private Builder() {
                    this.value_ = Value.getDefaultInstance();
                }

                static Builder access$3600() {
                    return Builder.create();
                }

                public Argument build() {
                    Argument protoBuf$Annotation$Argument0 = this.buildPartial();
                    if(!protoBuf$Annotation$Argument0.isInitialized()) {
                        throw Builder.newUninitializedMessageException(protoBuf$Annotation$Argument0);
                    }
                    return protoBuf$Annotation$Argument0;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
                public MessageLite build() {
                    return this.build();
                }

                public Argument buildPartial() {
                    Argument protoBuf$Annotation$Argument0 = new Argument(this, null);
                    int v = this.bitField0_;
                    int v1 = (v & 1) == 1 ? 1 : 0;
                    protoBuf$Annotation$Argument0.nameId_ = this.nameId_;
                    if((v & 2) == 2) {
                        v1 |= 2;
                    }
                    protoBuf$Annotation$Argument0.value_ = this.value_;
                    protoBuf$Annotation$Argument0.bitField0_ = v1;
                    return protoBuf$Annotation$Argument0;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public Object clone() throws CloneNotSupportedException {
                    return this.clone();
                }

                public Builder clone() {
                    return Builder.create().mergeFrom(this.buildPartial());
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                    return this.clone();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                    return this.clone();
                }

                private static Builder create() {
                    return new Builder();
                }

                public Argument getDefaultInstanceForType() {
                    return Argument.getDefaultInstance();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public GeneratedMessageLite getDefaultInstanceForType() {
                    return this.getDefaultInstanceForType();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return this.getDefaultInstanceForType();
                }

                public Value getValue() {
                    return this.value_;
                }

                public boolean hasNameId() {
                    return (this.bitField0_ & 1) == 1;
                }

                public boolean hasValue() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    if(!this.hasNameId()) {
                        return false;
                    }
                    return this.hasValue() ? this.getValue().isInitialized() : false;
                }

                private void maybeForceBuilderInitialization() {
                }

                public Builder mergeFrom(Argument protoBuf$Annotation$Argument0) {
                    if(protoBuf$Annotation$Argument0 == Argument.getDefaultInstance()) {
                        return this;
                    }
                    if(protoBuf$Annotation$Argument0.hasNameId()) {
                        this.setNameId(protoBuf$Annotation$Argument0.getNameId());
                    }
                    if(protoBuf$Annotation$Argument0.hasValue()) {
                        this.mergeValue(protoBuf$Annotation$Argument0.getValue());
                    }
                    this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Annotation$Argument0.unknownFields));
                    return this;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                    Argument protoBuf$Annotation$Argument1;
                    Argument protoBuf$Annotation$Argument0;
                    try {
                        try {
                            protoBuf$Annotation$Argument0 = null;
                            protoBuf$Annotation$Argument1 = (Argument)Argument.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                            goto label_13;
                        }
                        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                            Argument protoBuf$Annotation$Argument2 = (Argument)invalidProtocolBufferException0.getUnfinishedMessage();
                            try {
                                throw invalidProtocolBufferException0;
                            }
                            catch(Throwable throwable0) {
                                protoBuf$Annotation$Argument0 = protoBuf$Annotation$Argument2;
                            }
                        }
                    }
                    catch(Throwable throwable0) {
                    }
                    if(protoBuf$Annotation$Argument0 != null) {
                        this.mergeFrom(protoBuf$Annotation$Argument0);
                    }
                    throw throwable0;
                label_13:
                    if(protoBuf$Annotation$Argument1 != null) {
                        this.mergeFrom(protoBuf$Annotation$Argument1);
                    }
                    return this;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                    return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                    return this.mergeFrom(((Argument)generatedMessageLite0));
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                    return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
                }

                public Builder mergeValue(Value protoBuf$Annotation$Argument$Value0) {
                    this.value_ = (this.bitField0_ & 2) != 2 || this.value_ == Value.getDefaultInstance() ? protoBuf$Annotation$Argument$Value0 : Value.newBuilder(this.value_).mergeFrom(protoBuf$Annotation$Argument$Value0).buildPartial();
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder setNameId(int v) {
                    this.bitField0_ |= 1;
                    this.nameId_ = v;
                    return this;
                }
            }

            public static final class Value extends GeneratedMessageLite implements ProtoBuf.Annotation.Argument.ValueOrBuilder {
                public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.Annotation.Argument.ValueOrBuilder {
                    private Annotation annotation_;
                    private int arrayDimensionCount_;
                    private List arrayElement_;
                    private int bitField0_;
                    private int classId_;
                    private double doubleValue_;
                    private int enumValueId_;
                    private int flags_;
                    private float floatValue_;
                    private long intValue_;
                    private int stringValue_;
                    private Type type_;

                    private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder() {
                        this.type_ = Type.BYTE;
                        this.annotation_ = Annotation.getDefaultInstance();
                        this.arrayElement_ = Collections.EMPTY_LIST;
                    }

                    static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder access$2100() {
                        return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder.create();
                    }

                    public Value build() {
                        Value protoBuf$Annotation$Argument$Value0 = this.buildPartial();
                        if(!protoBuf$Annotation$Argument$Value0.isInitialized()) {
                            throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder.newUninitializedMessageException(protoBuf$Annotation$Argument$Value0);
                        }
                        return protoBuf$Annotation$Argument$Value0;
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
                    public MessageLite build() {
                        return this.build();
                    }

                    public Value buildPartial() {
                        Value protoBuf$Annotation$Argument$Value0 = new Value(this, null);
                        int v = this.bitField0_;
                        int v1 = (v & 1) == 1 ? 1 : 0;
                        protoBuf$Annotation$Argument$Value0.type_ = this.type_;
                        if((v & 2) == 2) {
                            v1 |= 2;
                        }
                        protoBuf$Annotation$Argument$Value0.intValue_ = this.intValue_;
                        if((v & 4) == 4) {
                            v1 |= 4;
                        }
                        protoBuf$Annotation$Argument$Value0.floatValue_ = this.floatValue_;
                        if((v & 8) == 8) {
                            v1 |= 8;
                        }
                        protoBuf$Annotation$Argument$Value0.doubleValue_ = this.doubleValue_;
                        if((v & 16) == 16) {
                            v1 |= 16;
                        }
                        protoBuf$Annotation$Argument$Value0.stringValue_ = this.stringValue_;
                        if((v & 0x20) == 0x20) {
                            v1 |= 0x20;
                        }
                        protoBuf$Annotation$Argument$Value0.classId_ = this.classId_;
                        if((v & 0x40) == 0x40) {
                            v1 |= 0x40;
                        }
                        protoBuf$Annotation$Argument$Value0.enumValueId_ = this.enumValueId_;
                        if((v & 0x80) == 0x80) {
                            v1 |= 0x80;
                        }
                        protoBuf$Annotation$Argument$Value0.annotation_ = this.annotation_;
                        if((this.bitField0_ & 0x100) == 0x100) {
                            this.arrayElement_ = Collections.unmodifiableList(this.arrayElement_);
                            this.bitField0_ &= 0xFFFFFEFF;
                        }
                        protoBuf$Annotation$Argument$Value0.arrayElement_ = this.arrayElement_;
                        if((v & 0x200) == 0x200) {
                            v1 |= 0x100;
                        }
                        protoBuf$Annotation$Argument$Value0.arrayDimensionCount_ = this.arrayDimensionCount_;
                        if((v & 0x400) == 0x400) {
                            v1 |= 0x200;
                        }
                        protoBuf$Annotation$Argument$Value0.flags_ = this.flags_;
                        protoBuf$Annotation$Argument$Value0.bitField0_ = v1;
                        return protoBuf$Annotation$Argument$Value0;
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                    public Object clone() throws CloneNotSupportedException {
                        return this.clone();
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder clone() {
                        return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder.create().mergeFrom(this.buildPartial());
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                    public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                        return this.clone();
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                    public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                        return this.clone();
                    }

                    private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder create() {
                        return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder();
                    }

                    private void ensureArrayElementIsMutable() {
                        if((this.bitField0_ & 0x100) != 0x100) {
                            this.arrayElement_ = new ArrayList(this.arrayElement_);
                            this.bitField0_ |= 0x100;
                        }
                    }

                    public Annotation getAnnotation() {
                        return this.annotation_;
                    }

                    public Value getArrayElement(int v) {
                        return (Value)this.arrayElement_.get(v);
                    }

                    public int getArrayElementCount() {
                        return this.arrayElement_.size();
                    }

                    public Value getDefaultInstanceForType() {
                        return Value.getDefaultInstance();
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                    public GeneratedMessageLite getDefaultInstanceForType() {
                        return this.getDefaultInstanceForType();
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                    public MessageLite getDefaultInstanceForType() {
                        return this.getDefaultInstanceForType();
                    }

                    public boolean hasAnnotation() {
                        return (this.bitField0_ & 0x80) == 0x80;
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                    public final boolean isInitialized() {
                        if(this.hasAnnotation() && !this.getAnnotation().isInitialized()) {
                            return false;
                        }
                        for(int v = 0; v < this.getArrayElementCount(); ++v) {
                            if(!this.getArrayElement(v).isInitialized()) {
                                return false;
                            }
                        }
                        return true;
                    }

                    private void maybeForceBuilderInitialization() {
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder mergeAnnotation(Annotation protoBuf$Annotation0) {
                        this.annotation_ = (this.bitField0_ & 0x80) != 0x80 || this.annotation_ == Annotation.getDefaultInstance() ? protoBuf$Annotation0 : Annotation.newBuilder(this.annotation_).mergeFrom(protoBuf$Annotation0).buildPartial();
                        this.bitField0_ |= 0x80;
                        return this;
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder mergeFrom(Value protoBuf$Annotation$Argument$Value0) {
                        if(protoBuf$Annotation$Argument$Value0 == Value.getDefaultInstance()) {
                            return this;
                        }
                        if(protoBuf$Annotation$Argument$Value0.hasType()) {
                            this.setType(protoBuf$Annotation$Argument$Value0.getType());
                        }
                        if(protoBuf$Annotation$Argument$Value0.hasIntValue()) {
                            this.setIntValue(protoBuf$Annotation$Argument$Value0.getIntValue());
                        }
                        if(protoBuf$Annotation$Argument$Value0.hasFloatValue()) {
                            this.setFloatValue(protoBuf$Annotation$Argument$Value0.getFloatValue());
                        }
                        if(protoBuf$Annotation$Argument$Value0.hasDoubleValue()) {
                            this.setDoubleValue(protoBuf$Annotation$Argument$Value0.getDoubleValue());
                        }
                        if(protoBuf$Annotation$Argument$Value0.hasStringValue()) {
                            this.setStringValue(protoBuf$Annotation$Argument$Value0.getStringValue());
                        }
                        if(protoBuf$Annotation$Argument$Value0.hasClassId()) {
                            this.setClassId(protoBuf$Annotation$Argument$Value0.getClassId());
                        }
                        if(protoBuf$Annotation$Argument$Value0.hasEnumValueId()) {
                            this.setEnumValueId(protoBuf$Annotation$Argument$Value0.getEnumValueId());
                        }
                        if(protoBuf$Annotation$Argument$Value0.hasAnnotation()) {
                            this.mergeAnnotation(protoBuf$Annotation$Argument$Value0.getAnnotation());
                        }
                        if(!protoBuf$Annotation$Argument$Value0.arrayElement_.isEmpty()) {
                            if(this.arrayElement_.isEmpty()) {
                                this.arrayElement_ = protoBuf$Annotation$Argument$Value0.arrayElement_;
                                this.bitField0_ &= 0xFFFFFEFF;
                            }
                            else {
                                this.ensureArrayElementIsMutable();
                                this.arrayElement_.addAll(protoBuf$Annotation$Argument$Value0.arrayElement_);
                            }
                        }
                        if(protoBuf$Annotation$Argument$Value0.hasArrayDimensionCount()) {
                            this.setArrayDimensionCount(protoBuf$Annotation$Argument$Value0.getArrayDimensionCount());
                        }
                        if(protoBuf$Annotation$Argument$Value0.hasFlags()) {
                            this.setFlags(protoBuf$Annotation$Argument$Value0.getFlags());
                        }
                        this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Annotation$Argument$Value0.unknownFields));
                        return this;
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                        Value protoBuf$Annotation$Argument$Value1;
                        Value protoBuf$Annotation$Argument$Value0;
                        try {
                            try {
                                protoBuf$Annotation$Argument$Value0 = null;
                                protoBuf$Annotation$Argument$Value1 = (Value)Value.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                                goto label_13;
                            }
                            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                                Value protoBuf$Annotation$Argument$Value2 = (Value)invalidProtocolBufferException0.getUnfinishedMessage();
                                try {
                                    throw invalidProtocolBufferException0;
                                }
                                catch(Throwable throwable0) {
                                    protoBuf$Annotation$Argument$Value0 = protoBuf$Annotation$Argument$Value2;
                                }
                            }
                        }
                        catch(Throwable throwable0) {
                        }
                        if(protoBuf$Annotation$Argument$Value0 != null) {
                            this.mergeFrom(protoBuf$Annotation$Argument$Value0);
                        }
                        throw throwable0;
                    label_13:
                        if(protoBuf$Annotation$Argument$Value1 != null) {
                            this.mergeFrom(protoBuf$Annotation$Argument$Value1);
                        }
                        return this;
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
                    public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                        return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                    public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                        return this.mergeFrom(((Value)generatedMessageLite0));
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
                    public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                        return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder setArrayDimensionCount(int v) {
                        this.bitField0_ |= 0x200;
                        this.arrayDimensionCount_ = v;
                        return this;
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder setClassId(int v) {
                        this.bitField0_ |= 0x20;
                        this.classId_ = v;
                        return this;
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder setDoubleValue(double f) {
                        this.bitField0_ |= 8;
                        this.doubleValue_ = f;
                        return this;
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder setEnumValueId(int v) {
                        this.bitField0_ |= 0x40;
                        this.enumValueId_ = v;
                        return this;
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder setFlags(int v) {
                        this.bitField0_ |= 0x400;
                        this.flags_ = v;
                        return this;
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder setFloatValue(float f) {
                        this.bitField0_ |= 4;
                        this.floatValue_ = f;
                        return this;
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder setIntValue(long v) {
                        this.bitField0_ |= 2;
                        this.intValue_ = v;
                        return this;
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder setStringValue(int v) {
                        this.bitField0_ |= 16;
                        this.stringValue_ = v;
                        return this;
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder setType(Type protoBuf$Annotation$Argument$Value$Type0) {
                        protoBuf$Annotation$Argument$Value$Type0.getClass();
                        this.bitField0_ |= 1;
                        this.type_ = protoBuf$Annotation$Argument$Value$Type0;
                        return this;
                    }
                }

                public static enum Type implements EnumLite {
                    BYTE(0, 0),
                    CHAR(1, 1),
                    SHORT(2, 2),
                    INT(3, 3),
                    LONG(4, 4),
                    FLOAT(5, 5),
                    DOUBLE(6, 6),
                    BOOLEAN(7, 7),
                    STRING(8, 8),
                    CLASS(9, 9),
                    ENUM(10, 10),
                    ANNOTATION(11, 11),
                    ARRAY(12, 12);

                    private static EnumLiteMap internalValueMap;
                    private final int value;

                    static {
                        Type.internalValueMap = new EnumLiteMap() {
                            public Type findValueByNumber(int v) {
                                return Type.valueOf(v);
                            }

                            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                            public EnumLite findValueByNumber(int v) {
                                return this.findValueByNumber(v);
                            }
                        };
                    }

                    private Type(int v1, int v2) {
                        this.value = v2;
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
                    public final int getNumber() {
                        return this.value;
                    }

                    public static Type valueOf(int v) {
                        switch(v) {
                            case 0: {
                                return Type.BYTE;
                            }
                            case 1: {
                                return Type.CHAR;
                            }
                            case 2: {
                                return Type.SHORT;
                            }
                            case 3: {
                                return Type.INT;
                            }
                            case 4: {
                                return Type.LONG;
                            }
                            case 5: {
                                return Type.FLOAT;
                            }
                            case 6: {
                                return Type.DOUBLE;
                            }
                            case 7: {
                                return Type.BOOLEAN;
                            }
                            case 8: {
                                return Type.STRING;
                            }
                            case 9: {
                                return Type.CLASS;
                            }
                            case 10: {
                                return Type.ENUM;
                            }
                            case 11: {
                                return Type.ANNOTATION;
                            }
                            case 12: {
                                return Type.ARRAY;
                            }
                            default: {
                                return null;
                            }
                        }
                    }
                }

                public static Parser PARSER;
                private Annotation annotation_;
                private int arrayDimensionCount_;
                private List arrayElement_;
                private int bitField0_;
                private int classId_;
                private static final Value defaultInstance;
                private double doubleValue_;
                private int enumValueId_;
                private int flags_;
                private float floatValue_;
                private long intValue_;
                private byte memoizedIsInitialized;
                private int memoizedSerializedSize;
                private int stringValue_;
                private Type type_;
                private final ByteString unknownFields;

                static {
                    Value.PARSER = new AbstractParser() {
                        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                        public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                            return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        }

                        public Value parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                            return new Value(codedInputStream0, extensionRegistryLite0, null);
                        }
                    };
                    Value protoBuf$Annotation$Argument$Value0 = new Value(true);
                    Value.defaultInstance = protoBuf$Annotation$Argument$Value0;
                    protoBuf$Annotation$Argument$Value0.initFields();
                }

                private Value(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    this.memoizedIsInitialized = -1;
                    this.memoizedSerializedSize = -1;
                    this.initFields();
                    Output byteString$Output0 = ByteString.newOutput();
                    CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
                    int v = 0;
                    boolean z = false;
                    while(!z) {
                        try {
                            try {
                                int v1 = codedInputStream0.readTag();
                                switch(v1) {
                                    case 0: {
                                        z = true;
                                        continue;
                                    }
                                    case 8: {
                                        int v2 = codedInputStream0.readEnum();
                                        Type protoBuf$Annotation$Argument$Value$Type0 = Type.valueOf(v2);
                                        if(protoBuf$Annotation$Argument$Value$Type0 == null) {
                                            codedOutputStream0.writeRawVarint32(8);
                                            codedOutputStream0.writeRawVarint32(v2);
                                        }
                                        else {
                                            this.bitField0_ |= 1;
                                            this.type_ = protoBuf$Annotation$Argument$Value$Type0;
                                        }
                                        continue;
                                    }
                                    case 16: {
                                        this.bitField0_ |= 2;
                                        this.intValue_ = codedInputStream0.readSInt64();
                                        continue;
                                    }
                                    case 29: {
                                        this.bitField0_ |= 4;
                                        this.floatValue_ = codedInputStream0.readFloat();
                                        continue;
                                    }
                                    case 33: {
                                        this.bitField0_ |= 8;
                                        this.doubleValue_ = codedInputStream0.readDouble();
                                        continue;
                                    }
                                    case 40: {
                                        this.bitField0_ |= 16;
                                        this.stringValue_ = codedInputStream0.readInt32();
                                        continue;
                                    }
                                    case 0x30: {
                                        this.bitField0_ |= 0x20;
                                        this.classId_ = codedInputStream0.readInt32();
                                        continue;
                                    }
                                    case 56: {
                                        this.bitField0_ |= 0x40;
                                        this.enumValueId_ = codedInputStream0.readInt32();
                                        continue;
                                    }
                                    case 66: {
                                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder protoBuf$Annotation$Builder0 = (this.bitField0_ & 0x80) == 0x80 ? this.annotation_.toBuilder() : null;
                                        Annotation protoBuf$Annotation0 = (Annotation)codedInputStream0.readMessage(Annotation.PARSER, extensionRegistryLite0);
                                        this.annotation_ = protoBuf$Annotation0;
                                        if(protoBuf$Annotation$Builder0 != null) {
                                            protoBuf$Annotation$Builder0.mergeFrom(protoBuf$Annotation0);
                                            this.annotation_ = protoBuf$Annotation$Builder0.buildPartial();
                                        }
                                        this.bitField0_ |= 0x80;
                                        continue;
                                    }
                                    case 74: {
                                        if((v & 0x100) != 0x100) {
                                            this.arrayElement_ = new ArrayList();
                                            v = 0x100;
                                        }
                                        this.arrayElement_.add(codedInputStream0.readMessage(Value.PARSER, extensionRegistryLite0));
                                        continue;
                                    }
                                    case 80: {
                                        this.bitField0_ |= 0x200;
                                        this.flags_ = codedInputStream0.readInt32();
                                        continue;
                                    }
                                    case 88: {
                                        this.bitField0_ |= 0x100;
                                        this.arrayDimensionCount_ = codedInputStream0.readInt32();
                                        break;
                                    }
                                    default: {
                                        if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                            z = true;
                                            continue;
                                        }
                                    }
                                }
                            }
                            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                                throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                            }
                            catch(IOException iOException0) {
                                throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                            }
                        }
                        catch(Throwable throwable0) {
                            if((v & 0x100) == 0x100) {
                                this.arrayElement_ = Collections.unmodifiableList(this.arrayElement_);
                            }
                            try {
                                codedOutputStream0.flush();
                            }
                            catch(IOException throwable1) {
                                this.unknownFields = byteString$Output0.toByteString();
                                throw throwable1;
                            }
                            catch(Throwable unused_ex) {
                            }
                            this.unknownFields = byteString$Output0.toByteString();
                            throw throwable0;
                        }
                    }
                    if((v & 0x100) == 0x100) {
                        this.arrayElement_ = Collections.unmodifiableList(this.arrayElement_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable2) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable2;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                }

                Value(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
                    this(codedInputStream0, extensionRegistryLite0);
                }

                private Value(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
                    super(generatedMessageLite$Builder0);
                    this.memoizedIsInitialized = -1;
                    this.memoizedSerializedSize = -1;
                    this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
                }

                Value(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
                    this(generatedMessageLite$Builder0);
                }

                private Value(boolean z) {
                    this.memoizedIsInitialized = -1;
                    this.memoizedSerializedSize = -1;
                    this.unknownFields = ByteString.EMPTY;
                }

                public Annotation getAnnotation() {
                    return this.annotation_;
                }

                public int getArrayDimensionCount() {
                    return this.arrayDimensionCount_;
                }

                public Value getArrayElement(int v) {
                    return (Value)this.arrayElement_.get(v);
                }

                public int getArrayElementCount() {
                    return this.arrayElement_.size();
                }

                public List getArrayElementList() {
                    return this.arrayElement_;
                }

                public int getClassId() {
                    return this.classId_;
                }

                public static Value getDefaultInstance() {
                    return Value.defaultInstance;
                }

                public Value getDefaultInstanceForType() {
                    return Value.defaultInstance;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return this.getDefaultInstanceForType();
                }

                public double getDoubleValue() {
                    return this.doubleValue_;
                }

                public int getEnumValueId() {
                    return this.enumValueId_;
                }

                public int getFlags() {
                    return this.flags_;
                }

                public float getFloatValue() {
                    return this.floatValue_;
                }

                public long getIntValue() {
                    return this.intValue_;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
                public Parser getParserForType() {
                    return Value.PARSER;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
                public int getSerializedSize() {
                    int v1 = this.memoizedSerializedSize;
                    if(v1 != -1) {
                        return v1;
                    }
                    int v2 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeEnumSize(1, this.type_.getNumber()) : 0;
                    if((this.bitField0_ & 2) == 2) {
                        v2 += CodedOutputStream.computeSInt64Size(2, this.intValue_);
                    }
                    if((this.bitField0_ & 4) == 4) {
                        v2 += CodedOutputStream.computeFloatSize(3, this.floatValue_);
                    }
                    if((this.bitField0_ & 8) == 8) {
                        v2 += CodedOutputStream.computeDoubleSize(4, this.doubleValue_);
                    }
                    if((this.bitField0_ & 16) == 16) {
                        v2 += CodedOutputStream.computeInt32Size(5, this.stringValue_);
                    }
                    if((this.bitField0_ & 0x20) == 0x20) {
                        v2 += CodedOutputStream.computeInt32Size(6, this.classId_);
                    }
                    if((this.bitField0_ & 0x40) == 0x40) {
                        v2 += CodedOutputStream.computeInt32Size(7, this.enumValueId_);
                    }
                    if((this.bitField0_ & 0x80) == 0x80) {
                        v2 += CodedOutputStream.computeMessageSize(8, this.annotation_);
                    }
                    for(int v = 0; v < this.arrayElement_.size(); ++v) {
                        v2 += CodedOutputStream.computeMessageSize(9, ((MessageLite)this.arrayElement_.get(v)));
                    }
                    if((this.bitField0_ & 0x200) == 0x200) {
                        v2 += CodedOutputStream.computeInt32Size(10, this.flags_);
                    }
                    if((this.bitField0_ & 0x100) == 0x100) {
                        v2 += CodedOutputStream.computeInt32Size(11, this.arrayDimensionCount_);
                    }
                    int v3 = v2 + this.unknownFields.size();
                    this.memoizedSerializedSize = v3;
                    return v3;
                }

                public int getStringValue() {
                    return this.stringValue_;
                }

                public Type getType() {
                    return this.type_;
                }

                public boolean hasAnnotation() {
                    return (this.bitField0_ & 0x80) == 0x80;
                }

                public boolean hasArrayDimensionCount() {
                    return (this.bitField0_ & 0x100) == 0x100;
                }

                public boolean hasClassId() {
                    return (this.bitField0_ & 0x20) == 0x20;
                }

                public boolean hasDoubleValue() {
                    return (this.bitField0_ & 8) == 8;
                }

                public boolean hasEnumValueId() {
                    return (this.bitField0_ & 0x40) == 0x40;
                }

                public boolean hasFlags() {
                    return (this.bitField0_ & 0x200) == 0x200;
                }

                public boolean hasFloatValue() {
                    return (this.bitField0_ & 4) == 4;
                }

                public boolean hasIntValue() {
                    return (this.bitField0_ & 2) == 2;
                }

                public boolean hasStringValue() {
                    return (this.bitField0_ & 16) == 16;
                }

                public boolean hasType() {
                    return (this.bitField0_ & 1) == 1;
                }

                private void initFields() {
                    this.type_ = Type.BYTE;
                    this.intValue_ = 0L;
                    this.floatValue_ = 0.0f;
                    this.doubleValue_ = 0.0;
                    this.stringValue_ = 0;
                    this.classId_ = 0;
                    this.enumValueId_ = 0;
                    this.annotation_ = Annotation.getDefaultInstance();
                    this.arrayElement_ = Collections.EMPTY_LIST;
                    this.arrayDimensionCount_ = 0;
                    this.flags_ = 0;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    int v = this.memoizedIsInitialized;
                    if(v == 1) {
                        return true;
                    }
                    if(v == 0) {
                        return false;
                    }
                    if(this.hasAnnotation() && !this.getAnnotation().isInitialized()) {
                        this.memoizedIsInitialized = 0;
                        return false;
                    }
                    for(int v1 = 0; v1 < this.getArrayElementCount(); ++v1) {
                        if(!this.getArrayElement(v1).isInitialized()) {
                            this.memoizedIsInitialized = 0;
                            return false;
                        }
                    }
                    this.memoizedIsInitialized = 1;
                    return true;
                }

                public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder newBuilder() {
                    return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder.access$2100();
                }

                public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder newBuilder(Value protoBuf$Annotation$Argument$Value0) {
                    return Value.newBuilder().mergeFrom(protoBuf$Annotation$Argument$Value0);
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder newBuilderForType() {
                    return Value.newBuilder();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
                public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
                    return this.newBuilderForType();
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder toBuilder() {
                    return Value.newBuilder(this);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
                public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
                    return this.toBuilder();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
                public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
                    this.getSerializedSize();
                    if((this.bitField0_ & 1) == 1) {
                        codedOutputStream0.writeEnum(1, this.type_.getNumber());
                    }
                    if((this.bitField0_ & 2) == 2) {
                        codedOutputStream0.writeSInt64(2, this.intValue_);
                    }
                    if((this.bitField0_ & 4) == 4) {
                        codedOutputStream0.writeFloat(3, this.floatValue_);
                    }
                    if((this.bitField0_ & 8) == 8) {
                        codedOutputStream0.writeDouble(4, this.doubleValue_);
                    }
                    if((this.bitField0_ & 16) == 16) {
                        codedOutputStream0.writeInt32(5, this.stringValue_);
                    }
                    if((this.bitField0_ & 0x20) == 0x20) {
                        codedOutputStream0.writeInt32(6, this.classId_);
                    }
                    if((this.bitField0_ & 0x40) == 0x40) {
                        codedOutputStream0.writeInt32(7, this.enumValueId_);
                    }
                    if((this.bitField0_ & 0x80) == 0x80) {
                        codedOutputStream0.writeMessage(8, this.annotation_);
                    }
                    for(int v = 0; v < this.arrayElement_.size(); ++v) {
                        codedOutputStream0.writeMessage(9, ((MessageLite)this.arrayElement_.get(v)));
                    }
                    if((this.bitField0_ & 0x200) == 0x200) {
                        codedOutputStream0.writeInt32(10, this.flags_);
                    }
                    if((this.bitField0_ & 0x100) == 0x100) {
                        codedOutputStream0.writeInt32(11, this.arrayDimensionCount_);
                    }
                    codedOutputStream0.writeRawBytes(this.unknownFields);
                }
            }

            public static Parser PARSER;
            private int bitField0_;
            private static final Argument defaultInstance;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            private int nameId_;
            private final ByteString unknownFields;
            private Value value_;

            static {
                Argument.PARSER = new AbstractParser() {
                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                    public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                        return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                    }

                    public Argument parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                        return new Argument(codedInputStream0, extensionRegistryLite0, null);
                    }
                };
                Argument protoBuf$Annotation$Argument0 = new Argument(true);
                Argument.defaultInstance = protoBuf$Annotation$Argument0;
                protoBuf$Annotation$Argument0.initFields();
            }

            private Argument(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                Output byteString$Output0 = ByteString.newOutput();
                CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
                try {
                    boolean z = false;
                    while(!z) {
                        try {
                            try {
                                int v1 = codedInputStream0.readTag();
                                switch(v1) {
                                    case 0: {
                                        z = true;
                                        continue;
                                    }
                                    case 8: {
                                        this.bitField0_ |= 1;
                                        this.nameId_ = codedInputStream0.readInt32();
                                        continue;
                                    }
                                    case 18: {
                                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Builder protoBuf$Annotation$Argument$Value$Builder0 = (this.bitField0_ & 2) == 2 ? this.value_.toBuilder() : null;
                                        Value protoBuf$Annotation$Argument$Value0 = (Value)codedInputStream0.readMessage(Value.PARSER, extensionRegistryLite0);
                                        this.value_ = protoBuf$Annotation$Argument$Value0;
                                        if(protoBuf$Annotation$Argument$Value$Builder0 != null) {
                                            protoBuf$Annotation$Argument$Value$Builder0.mergeFrom(protoBuf$Annotation$Argument$Value0);
                                            this.value_ = protoBuf$Annotation$Argument$Value$Builder0.buildPartial();
                                        }
                                        this.bitField0_ |= 2;
                                        continue;
                                    }
                                    default: {
                                        if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                            z = true;
                                            continue;
                                        }
                                        continue;
                                    }
                                }
                            }
                            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                            }
                            catch(IOException iOException0) {
                                throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                            }
                            throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                        }
                        catch(Throwable throwable0) {
                            try {
                                codedOutputStream0.flush();
                            }
                            catch(IOException unused_ex) {
                            }
                            throw throwable0;
                        }
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException unused_ex) {
                    }
                }
                finally {
                    this.unknownFields = byteString$Output0.toByteString();
                }
            }

            Argument(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
                this(codedInputStream0, extensionRegistryLite0);
            }

            private Argument(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
                super(generatedMessageLite$Builder0);
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
            }

            Argument(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
                this(generatedMessageLite$Builder0);
            }

            private Argument(boolean z) {
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = ByteString.EMPTY;
            }

            public static Argument getDefaultInstance() {
                return Argument.defaultInstance;
            }

            public Argument getDefaultInstanceForType() {
                return Argument.defaultInstance;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public int getNameId() {
                return this.nameId_;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
            public Parser getParserForType() {
                return Argument.PARSER;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public int getSerializedSize() {
                int v = this.memoizedSerializedSize;
                if(v != -1) {
                    return v;
                }
                int v1 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.nameId_) : 0;
                if((this.bitField0_ & 2) == 2) {
                    v1 += CodedOutputStream.computeMessageSize(2, this.value_);
                }
                int v2 = v1 + this.unknownFields.size();
                this.memoizedSerializedSize = v2;
                return v2;
            }

            public Value getValue() {
                return this.value_;
            }

            public boolean hasNameId() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasValue() {
                return (this.bitField0_ & 2) == 2;
            }

            private void initFields() {
                this.nameId_ = 0;
                this.value_ = Value.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                int v = this.memoizedIsInitialized;
                if(v == 1) {
                    return true;
                }
                if(v == 0) {
                    return false;
                }
                if(!this.hasNameId()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                if(!this.hasValue()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                if(!this.getValue().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            public static Builder newBuilder() {
                return Builder.access$3600();
            }

            public static Builder newBuilder(Argument protoBuf$Annotation$Argument0) {
                return Argument.newBuilder().mergeFrom(protoBuf$Annotation$Argument0);
            }

            public Builder newBuilderForType() {
                return Argument.newBuilder();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
                return this.newBuilderForType();
            }

            public Builder toBuilder() {
                return Argument.newBuilder(this);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
                return this.toBuilder();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
                this.getSerializedSize();
                if((this.bitField0_ & 1) == 1) {
                    codedOutputStream0.writeInt32(1, this.nameId_);
                }
                if((this.bitField0_ & 2) == 2) {
                    codedOutputStream0.writeMessage(2, this.value_);
                }
                codedOutputStream0.writeRawBytes(this.unknownFields);
            }
        }

        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.AnnotationOrBuilder {
            private List argument_;
            private int bitField0_;
            private int id_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder() {
                this.argument_ = Collections.EMPTY_LIST;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder access$4200() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder.create();
            }

            public Annotation build() {
                Annotation protoBuf$Annotation0 = this.buildPartial();
                if(!protoBuf$Annotation0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder.newUninitializedMessageException(protoBuf$Annotation0);
                }
                return protoBuf$Annotation0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public Annotation buildPartial() {
                Annotation protoBuf$Annotation0 = new Annotation(this, null);
                int v = (this.bitField0_ & 1) == 1 ? 1 : 0;
                protoBuf$Annotation0.id_ = this.id_;
                if((this.bitField0_ & 2) == 2) {
                    this.argument_ = Collections.unmodifiableList(this.argument_);
                    this.bitField0_ &= -3;
                }
                protoBuf$Annotation0.argument_ = this.argument_;
                protoBuf$Annotation0.bitField0_ = v;
                return protoBuf$Annotation0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder();
            }

            private void ensureArgumentIsMutable() {
                if((this.bitField0_ & 2) != 2) {
                    this.argument_ = new ArrayList(this.argument_);
                    this.bitField0_ |= 2;
                }
            }

            public Argument getArgument(int v) {
                return (Argument)this.argument_.get(v);
            }

            public int getArgumentCount() {
                return this.argument_.size();
            }

            public Annotation getDefaultInstanceForType() {
                return Annotation.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public boolean hasId() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if(!this.hasId()) {
                    return false;
                }
                for(int v = 0; v < this.getArgumentCount(); ++v) {
                    if(!this.getArgument(v).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder mergeFrom(Annotation protoBuf$Annotation0) {
                if(protoBuf$Annotation0 == Annotation.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$Annotation0.hasId()) {
                    this.setId(protoBuf$Annotation0.getId());
                }
                if(!protoBuf$Annotation0.argument_.isEmpty()) {
                    if(this.argument_.isEmpty()) {
                        this.argument_ = protoBuf$Annotation0.argument_;
                        this.bitField0_ &= -3;
                    }
                    else {
                        this.ensureArgumentIsMutable();
                        this.argument_.addAll(protoBuf$Annotation0.argument_);
                    }
                }
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Annotation0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                Annotation protoBuf$Annotation1;
                Annotation protoBuf$Annotation0;
                try {
                    try {
                        protoBuf$Annotation0 = null;
                        protoBuf$Annotation1 = (Annotation)Annotation.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        Annotation protoBuf$Annotation2 = (Annotation)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$Annotation0 = protoBuf$Annotation2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$Annotation0 != null) {
                    this.mergeFrom(protoBuf$Annotation0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$Annotation1 != null) {
                    this.mergeFrom(protoBuf$Annotation1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((Annotation)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder setId(int v) {
                this.bitField0_ |= 1;
                this.id_ = v;
                return this;
            }
        }

        public static Parser PARSER;
        private List argument_;
        private int bitField0_;
        private static final Annotation defaultInstance;
        private int id_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final ByteString unknownFields;

        static {
            Annotation.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public Annotation parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new Annotation(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            Annotation protoBuf$Annotation0 = new Annotation(true);
            Annotation.defaultInstance = protoBuf$Annotation0;
            protoBuf$Annotation0.initFields();
        }

        private Annotation(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            int v = 0;
            boolean z = false;
            while(!z) {
                try {
                    try {
                        int v1 = codedInputStream0.readTag();
                        switch(v1) {
                            case 0: {
                                z = true;
                                continue;
                            }
                            case 8: {
                                this.bitField0_ |= 1;
                                this.id_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 18: {
                                if((v & 2) != 2) {
                                    this.argument_ = new ArrayList();
                                    v = 2;
                                }
                                this.argument_.add(codedInputStream0.readMessage(Argument.PARSER, extensionRegistryLite0));
                                break;
                            }
                            default: {
                                if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                    z = true;
                                    continue;
                                }
                            }
                        }
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if((v & 2) == 2) {
                        this.argument_ = Collections.unmodifiableList(this.argument_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    throw throwable0;
                }
            }
            if((v & 2) == 2) {
                this.argument_ = Collections.unmodifiableList(this.argument_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
        }

        Annotation(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private Annotation(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        Annotation(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private Annotation(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public Argument getArgument(int v) {
            return (Argument)this.argument_.get(v);
        }

        public int getArgumentCount() {
            return this.argument_.size();
        }

        public List getArgumentList() {
            return this.argument_;
        }

        public static Annotation getDefaultInstance() {
            return Annotation.defaultInstance;
        }

        public Annotation getDefaultInstanceForType() {
            return Annotation.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getId() {
            return this.id_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return Annotation.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v1 = this.memoizedSerializedSize;
            if(v1 != -1) {
                return v1;
            }
            int v2 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.id_) : 0;
            for(int v = 0; v < this.argument_.size(); ++v) {
                v2 += CodedOutputStream.computeMessageSize(2, ((MessageLite)this.argument_.get(v)));
            }
            int v3 = v2 + this.unknownFields.size();
            this.memoizedSerializedSize = v3;
            return v3;
        }

        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        private void initFields() {
            this.id_ = 0;
            this.argument_ = Collections.EMPTY_LIST;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            if(!this.hasId()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for(int v1 = 0; v1 < this.getArgumentCount(); ++v1) {
                if(!this.getArgument(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder.access$4200();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder newBuilder(Annotation protoBuf$Annotation0) {
            return Annotation.newBuilder().mergeFrom(protoBuf$Annotation0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder newBuilderForType() {
            return Annotation.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Builder toBuilder() {
            return Annotation.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(1, this.id_);
            }
            for(int v = 0; v < this.argument_.size(); ++v) {
                codedOutputStream0.writeMessage(2, ((MessageLite)this.argument_.get(v)));
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class Class extends ExtendableMessage implements ProtoBuf.ClassOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder extends ExtendableBuilder implements ProtoBuf.ClassOrBuilder {
            private int bitField0_;
            private int companionObjectName_;
            private List constructor_;
            private List contextReceiverTypeId_;
            private List contextReceiverType_;
            private List enumEntry_;
            private int flags_;
            private int fqName_;
            private List function_;
            private int inlineClassUnderlyingPropertyName_;
            private int inlineClassUnderlyingTypeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type inlineClassUnderlyingType_;
            private List multiFieldValueClassUnderlyingName_;
            private List multiFieldValueClassUnderlyingTypeId_;
            private List multiFieldValueClassUnderlyingType_;
            private List nestedClassName_;
            private List property_;
            private List sealedSubclassFqName_;
            private List supertypeId_;
            private List supertype_;
            private List typeAlias_;
            private List typeParameter_;
            private TypeTable typeTable_;
            private VersionRequirementTable versionRequirementTable_;
            private List versionRequirement_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder() {
                this.flags_ = 6;
                this.typeParameter_ = Collections.EMPTY_LIST;
                this.supertype_ = Collections.EMPTY_LIST;
                this.supertypeId_ = Collections.EMPTY_LIST;
                this.nestedClassName_ = Collections.EMPTY_LIST;
                this.contextReceiverType_ = Collections.EMPTY_LIST;
                this.contextReceiverTypeId_ = Collections.EMPTY_LIST;
                this.constructor_ = Collections.EMPTY_LIST;
                this.function_ = Collections.EMPTY_LIST;
                this.property_ = Collections.EMPTY_LIST;
                this.typeAlias_ = Collections.EMPTY_LIST;
                this.enumEntry_ = Collections.EMPTY_LIST;
                this.sealedSubclassFqName_ = Collections.EMPTY_LIST;
                this.inlineClassUnderlyingType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                this.multiFieldValueClassUnderlyingName_ = Collections.EMPTY_LIST;
                this.multiFieldValueClassUnderlyingType_ = Collections.EMPTY_LIST;
                this.multiFieldValueClassUnderlyingTypeId_ = Collections.EMPTY_LIST;
                this.typeTable_ = TypeTable.getDefaultInstance();
                this.versionRequirement_ = Collections.EMPTY_LIST;
                this.versionRequirementTable_ = VersionRequirementTable.getDefaultInstance();
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder access$8700() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder.create();
            }

            public Class build() {
                Class protoBuf$Class0 = this.buildPartial();
                if(!protoBuf$Class0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder.newUninitializedMessageException(protoBuf$Class0);
                }
                return protoBuf$Class0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public Class buildPartial() {
                Class protoBuf$Class0 = new Class(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                protoBuf$Class0.flags_ = this.flags_;
                if((v & 2) == 2) {
                    v1 |= 2;
                }
                protoBuf$Class0.fqName_ = this.fqName_;
                if((v & 4) == 4) {
                    v1 |= 4;
                }
                protoBuf$Class0.companionObjectName_ = this.companionObjectName_;
                if((this.bitField0_ & 8) == 8) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    this.bitField0_ &= -9;
                }
                protoBuf$Class0.typeParameter_ = this.typeParameter_;
                if((this.bitField0_ & 16) == 16) {
                    this.supertype_ = Collections.unmodifiableList(this.supertype_);
                    this.bitField0_ &= -17;
                }
                protoBuf$Class0.supertype_ = this.supertype_;
                if((this.bitField0_ & 0x20) == 0x20) {
                    this.supertypeId_ = Collections.unmodifiableList(this.supertypeId_);
                    this.bitField0_ &= -33;
                }
                protoBuf$Class0.supertypeId_ = this.supertypeId_;
                if((this.bitField0_ & 0x40) == 0x40) {
                    this.nestedClassName_ = Collections.unmodifiableList(this.nestedClassName_);
                    this.bitField0_ &= -65;
                }
                protoBuf$Class0.nestedClassName_ = this.nestedClassName_;
                if((this.bitField0_ & 0x80) == 0x80) {
                    this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
                    this.bitField0_ &= 0xFFFFFF7F;
                }
                protoBuf$Class0.contextReceiverType_ = this.contextReceiverType_;
                if((this.bitField0_ & 0x100) == 0x100) {
                    this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
                    this.bitField0_ &= 0xFFFFFEFF;
                }
                protoBuf$Class0.contextReceiverTypeId_ = this.contextReceiverTypeId_;
                if((this.bitField0_ & 0x200) == 0x200) {
                    this.constructor_ = Collections.unmodifiableList(this.constructor_);
                    this.bitField0_ &= 0xFFFFFDFF;
                }
                protoBuf$Class0.constructor_ = this.constructor_;
                if((this.bitField0_ & 0x400) == 0x400) {
                    this.function_ = Collections.unmodifiableList(this.function_);
                    this.bitField0_ &= 0xFFFFFBFF;
                }
                protoBuf$Class0.function_ = this.function_;
                if((this.bitField0_ & 0x800) == 0x800) {
                    this.property_ = Collections.unmodifiableList(this.property_);
                    this.bitField0_ &= 0xFFFFF7FF;
                }
                protoBuf$Class0.property_ = this.property_;
                if((this.bitField0_ & 0x1000) == 0x1000) {
                    this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                    this.bitField0_ &= 0xFFFFEFFF;
                }
                protoBuf$Class0.typeAlias_ = this.typeAlias_;
                if((this.bitField0_ & 0x2000) == 0x2000) {
                    this.enumEntry_ = Collections.unmodifiableList(this.enumEntry_);
                    this.bitField0_ &= 0xFFFFDFFF;
                }
                protoBuf$Class0.enumEntry_ = this.enumEntry_;
                if((this.bitField0_ & 0x4000) == 0x4000) {
                    this.sealedSubclassFqName_ = Collections.unmodifiableList(this.sealedSubclassFqName_);
                    this.bitField0_ &= 0xFFFFBFFF;
                }
                protoBuf$Class0.sealedSubclassFqName_ = this.sealedSubclassFqName_;
                if((v & 0x8000) == 0x8000) {
                    v1 |= 8;
                }
                protoBuf$Class0.inlineClassUnderlyingPropertyName_ = this.inlineClassUnderlyingPropertyName_;
                if((v & 0x10000) == 0x10000) {
                    v1 |= 16;
                }
                protoBuf$Class0.inlineClassUnderlyingType_ = this.inlineClassUnderlyingType_;
                if((v & 0x20000) == 0x20000) {
                    v1 |= 0x20;
                }
                protoBuf$Class0.inlineClassUnderlyingTypeId_ = this.inlineClassUnderlyingTypeId_;
                if((this.bitField0_ & 0x40000) == 0x40000) {
                    this.multiFieldValueClassUnderlyingName_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingName_);
                    this.bitField0_ &= 0xFFFBFFFF;
                }
                protoBuf$Class0.multiFieldValueClassUnderlyingName_ = this.multiFieldValueClassUnderlyingName_;
                if((this.bitField0_ & 0x80000) == 0x80000) {
                    this.multiFieldValueClassUnderlyingType_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingType_);
                    this.bitField0_ &= 0xFFF7FFFF;
                }
                protoBuf$Class0.multiFieldValueClassUnderlyingType_ = this.multiFieldValueClassUnderlyingType_;
                if((this.bitField0_ & 0x100000) == 0x100000) {
                    this.multiFieldValueClassUnderlyingTypeId_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingTypeId_);
                    this.bitField0_ &= 0xFFEFFFFF;
                }
                protoBuf$Class0.multiFieldValueClassUnderlyingTypeId_ = this.multiFieldValueClassUnderlyingTypeId_;
                if((v & 0x200000) == 0x200000) {
                    v1 |= 0x40;
                }
                protoBuf$Class0.typeTable_ = this.typeTable_;
                if((this.bitField0_ & 0x400000) == 0x400000) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= 0xFFBFFFFF;
                }
                protoBuf$Class0.versionRequirement_ = this.versionRequirement_;
                if((v & 0x800000) == 0x800000) {
                    v1 |= 0x80;
                }
                protoBuf$Class0.versionRequirementTable_ = this.versionRequirementTable_;
                protoBuf$Class0.bitField0_ = v1;
                return protoBuf$Class0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public ExtendableBuilder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder();
            }

            private void ensureConstructorIsMutable() {
                if((this.bitField0_ & 0x200) != 0x200) {
                    this.constructor_ = new ArrayList(this.constructor_);
                    this.bitField0_ |= 0x200;
                }
            }

            private void ensureContextReceiverTypeIdIsMutable() {
                if((this.bitField0_ & 0x100) != 0x100) {
                    this.contextReceiverTypeId_ = new ArrayList(this.contextReceiverTypeId_);
                    this.bitField0_ |= 0x100;
                }
            }

            private void ensureContextReceiverTypeIsMutable() {
                if((this.bitField0_ & 0x80) != 0x80) {
                    this.contextReceiverType_ = new ArrayList(this.contextReceiverType_);
                    this.bitField0_ |= 0x80;
                }
            }

            private void ensureEnumEntryIsMutable() {
                if((this.bitField0_ & 0x2000) != 0x2000) {
                    this.enumEntry_ = new ArrayList(this.enumEntry_);
                    this.bitField0_ |= 0x2000;
                }
            }

            private void ensureFunctionIsMutable() {
                if((this.bitField0_ & 0x400) != 0x400) {
                    this.function_ = new ArrayList(this.function_);
                    this.bitField0_ |= 0x400;
                }
            }

            private void ensureMultiFieldValueClassUnderlyingNameIsMutable() {
                if((this.bitField0_ & 0x40000) != 0x40000) {
                    this.multiFieldValueClassUnderlyingName_ = new ArrayList(this.multiFieldValueClassUnderlyingName_);
                    this.bitField0_ |= 0x40000;
                }
            }

            private void ensureMultiFieldValueClassUnderlyingTypeIdIsMutable() {
                if((this.bitField0_ & 0x100000) != 0x100000) {
                    this.multiFieldValueClassUnderlyingTypeId_ = new ArrayList(this.multiFieldValueClassUnderlyingTypeId_);
                    this.bitField0_ |= 0x100000;
                }
            }

            private void ensureMultiFieldValueClassUnderlyingTypeIsMutable() {
                if((this.bitField0_ & 0x80000) != 0x80000) {
                    this.multiFieldValueClassUnderlyingType_ = new ArrayList(this.multiFieldValueClassUnderlyingType_);
                    this.bitField0_ |= 0x80000;
                }
            }

            private void ensureNestedClassNameIsMutable() {
                if((this.bitField0_ & 0x40) != 0x40) {
                    this.nestedClassName_ = new ArrayList(this.nestedClassName_);
                    this.bitField0_ |= 0x40;
                }
            }

            private void ensurePropertyIsMutable() {
                if((this.bitField0_ & 0x800) != 0x800) {
                    this.property_ = new ArrayList(this.property_);
                    this.bitField0_ |= 0x800;
                }
            }

            private void ensureSealedSubclassFqNameIsMutable() {
                if((this.bitField0_ & 0x4000) != 0x4000) {
                    this.sealedSubclassFqName_ = new ArrayList(this.sealedSubclassFqName_);
                    this.bitField0_ |= 0x4000;
                }
            }

            private void ensureSupertypeIdIsMutable() {
                if((this.bitField0_ & 0x20) != 0x20) {
                    this.supertypeId_ = new ArrayList(this.supertypeId_);
                    this.bitField0_ |= 0x20;
                }
            }

            private void ensureSupertypeIsMutable() {
                if((this.bitField0_ & 16) != 16) {
                    this.supertype_ = new ArrayList(this.supertype_);
                    this.bitField0_ |= 16;
                }
            }

            private void ensureTypeAliasIsMutable() {
                if((this.bitField0_ & 0x1000) != 0x1000) {
                    this.typeAlias_ = new ArrayList(this.typeAlias_);
                    this.bitField0_ |= 0x1000;
                }
            }

            private void ensureTypeParameterIsMutable() {
                if((this.bitField0_ & 8) != 8) {
                    this.typeParameter_ = new ArrayList(this.typeParameter_);
                    this.bitField0_ |= 8;
                }
            }

            private void ensureVersionRequirementIsMutable() {
                if((this.bitField0_ & 0x400000) != 0x400000) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 0x400000;
                }
            }

            public Constructor getConstructor(int v) {
                return (Constructor)this.constructor_.get(v);
            }

            public int getConstructorCount() {
                return this.constructor_.size();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getContextReceiverType(int v) {
                return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.contextReceiverType_.get(v);
            }

            public int getContextReceiverTypeCount() {
                return this.contextReceiverType_.size();
            }

            public Class getDefaultInstanceForType() {
                return Class.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public EnumEntry getEnumEntry(int v) {
                return (EnumEntry)this.enumEntry_.get(v);
            }

            public int getEnumEntryCount() {
                return this.enumEntry_.size();
            }

            public Function getFunction(int v) {
                return (Function)this.function_.get(v);
            }

            public int getFunctionCount() {
                return this.function_.size();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getInlineClassUnderlyingType() {
                return this.inlineClassUnderlyingType_;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getMultiFieldValueClassUnderlyingType(int v) {
                return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.multiFieldValueClassUnderlyingType_.get(v);
            }

            public int getMultiFieldValueClassUnderlyingTypeCount() {
                return this.multiFieldValueClassUnderlyingType_.size();
            }

            public Property getProperty(int v) {
                return (Property)this.property_.get(v);
            }

            public int getPropertyCount() {
                return this.property_.size();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getSupertype(int v) {
                return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.supertype_.get(v);
            }

            public int getSupertypeCount() {
                return this.supertype_.size();
            }

            public TypeAlias getTypeAlias(int v) {
                return (TypeAlias)this.typeAlias_.get(v);
            }

            public int getTypeAliasCount() {
                return this.typeAlias_.size();
            }

            public TypeParameter getTypeParameter(int v) {
                return (TypeParameter)this.typeParameter_.get(v);
            }

            public int getTypeParameterCount() {
                return this.typeParameter_.size();
            }

            public TypeTable getTypeTable() {
                return this.typeTable_;
            }

            public boolean hasFqName() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasInlineClassUnderlyingType() {
                return (this.bitField0_ & 0x10000) == 0x10000;
            }

            public boolean hasTypeTable() {
                return (this.bitField0_ & 0x200000) == 0x200000;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if(!this.hasFqName()) {
                    return false;
                }
                for(int v = 0; v < this.getTypeParameterCount(); ++v) {
                    if(!this.getTypeParameter(v).isInitialized()) {
                        return false;
                    }
                }
                for(int v1 = 0; v1 < this.getSupertypeCount(); ++v1) {
                    if(!this.getSupertype(v1).isInitialized()) {
                        return false;
                    }
                }
                for(int v2 = 0; v2 < this.getContextReceiverTypeCount(); ++v2) {
                    if(!this.getContextReceiverType(v2).isInitialized()) {
                        return false;
                    }
                }
                for(int v3 = 0; v3 < this.getConstructorCount(); ++v3) {
                    if(!this.getConstructor(v3).isInitialized()) {
                        return false;
                    }
                }
                for(int v4 = 0; v4 < this.getFunctionCount(); ++v4) {
                    if(!this.getFunction(v4).isInitialized()) {
                        return false;
                    }
                }
                for(int v5 = 0; v5 < this.getPropertyCount(); ++v5) {
                    if(!this.getProperty(v5).isInitialized()) {
                        return false;
                    }
                }
                for(int v6 = 0; v6 < this.getTypeAliasCount(); ++v6) {
                    if(!this.getTypeAlias(v6).isInitialized()) {
                        return false;
                    }
                }
                for(int v7 = 0; v7 < this.getEnumEntryCount(); ++v7) {
                    if(!this.getEnumEntry(v7).isInitialized()) {
                        return false;
                    }
                }
                if(this.hasInlineClassUnderlyingType() && !this.getInlineClassUnderlyingType().isInitialized()) {
                    return false;
                }
                for(int v8 = 0; v8 < this.getMultiFieldValueClassUnderlyingTypeCount(); ++v8) {
                    if(!this.getMultiFieldValueClassUnderlyingType(v8).isInitialized()) {
                        return false;
                    }
                }
                return !this.hasTypeTable() || this.getTypeTable().isInitialized() ? this.extensionsAreInitialized() : false;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder mergeFrom(Class protoBuf$Class0) {
                if(protoBuf$Class0 == Class.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$Class0.hasFlags()) {
                    this.setFlags(protoBuf$Class0.getFlags());
                }
                if(protoBuf$Class0.hasFqName()) {
                    this.setFqName(protoBuf$Class0.getFqName());
                }
                if(protoBuf$Class0.hasCompanionObjectName()) {
                    this.setCompanionObjectName(protoBuf$Class0.getCompanionObjectName());
                }
                if(!protoBuf$Class0.typeParameter_.isEmpty()) {
                    if(this.typeParameter_.isEmpty()) {
                        this.typeParameter_ = protoBuf$Class0.typeParameter_;
                        this.bitField0_ &= -9;
                    }
                    else {
                        this.ensureTypeParameterIsMutable();
                        this.typeParameter_.addAll(protoBuf$Class0.typeParameter_);
                    }
                }
                if(!protoBuf$Class0.supertype_.isEmpty()) {
                    if(this.supertype_.isEmpty()) {
                        this.supertype_ = protoBuf$Class0.supertype_;
                        this.bitField0_ &= -17;
                    }
                    else {
                        this.ensureSupertypeIsMutable();
                        this.supertype_.addAll(protoBuf$Class0.supertype_);
                    }
                }
                if(!protoBuf$Class0.supertypeId_.isEmpty()) {
                    if(this.supertypeId_.isEmpty()) {
                        this.supertypeId_ = protoBuf$Class0.supertypeId_;
                        this.bitField0_ &= -33;
                    }
                    else {
                        this.ensureSupertypeIdIsMutable();
                        this.supertypeId_.addAll(protoBuf$Class0.supertypeId_);
                    }
                }
                if(!protoBuf$Class0.nestedClassName_.isEmpty()) {
                    if(this.nestedClassName_.isEmpty()) {
                        this.nestedClassName_ = protoBuf$Class0.nestedClassName_;
                        this.bitField0_ &= -65;
                    }
                    else {
                        this.ensureNestedClassNameIsMutable();
                        this.nestedClassName_.addAll(protoBuf$Class0.nestedClassName_);
                    }
                }
                if(!protoBuf$Class0.contextReceiverType_.isEmpty()) {
                    if(this.contextReceiverType_.isEmpty()) {
                        this.contextReceiverType_ = protoBuf$Class0.contextReceiverType_;
                        this.bitField0_ &= 0xFFFFFF7F;
                    }
                    else {
                        this.ensureContextReceiverTypeIsMutable();
                        this.contextReceiverType_.addAll(protoBuf$Class0.contextReceiverType_);
                    }
                }
                if(!protoBuf$Class0.contextReceiverTypeId_.isEmpty()) {
                    if(this.contextReceiverTypeId_.isEmpty()) {
                        this.contextReceiverTypeId_ = protoBuf$Class0.contextReceiverTypeId_;
                        this.bitField0_ &= 0xFFFFFEFF;
                    }
                    else {
                        this.ensureContextReceiverTypeIdIsMutable();
                        this.contextReceiverTypeId_.addAll(protoBuf$Class0.contextReceiverTypeId_);
                    }
                }
                if(!protoBuf$Class0.constructor_.isEmpty()) {
                    if(this.constructor_.isEmpty()) {
                        this.constructor_ = protoBuf$Class0.constructor_;
                        this.bitField0_ &= 0xFFFFFDFF;
                    }
                    else {
                        this.ensureConstructorIsMutable();
                        this.constructor_.addAll(protoBuf$Class0.constructor_);
                    }
                }
                if(!protoBuf$Class0.function_.isEmpty()) {
                    if(this.function_.isEmpty()) {
                        this.function_ = protoBuf$Class0.function_;
                        this.bitField0_ &= 0xFFFFFBFF;
                    }
                    else {
                        this.ensureFunctionIsMutable();
                        this.function_.addAll(protoBuf$Class0.function_);
                    }
                }
                if(!protoBuf$Class0.property_.isEmpty()) {
                    if(this.property_.isEmpty()) {
                        this.property_ = protoBuf$Class0.property_;
                        this.bitField0_ &= 0xFFFFF7FF;
                    }
                    else {
                        this.ensurePropertyIsMutable();
                        this.property_.addAll(protoBuf$Class0.property_);
                    }
                }
                if(!protoBuf$Class0.typeAlias_.isEmpty()) {
                    if(this.typeAlias_.isEmpty()) {
                        this.typeAlias_ = protoBuf$Class0.typeAlias_;
                        this.bitField0_ &= 0xFFFFEFFF;
                    }
                    else {
                        this.ensureTypeAliasIsMutable();
                        this.typeAlias_.addAll(protoBuf$Class0.typeAlias_);
                    }
                }
                if(!protoBuf$Class0.enumEntry_.isEmpty()) {
                    if(this.enumEntry_.isEmpty()) {
                        this.enumEntry_ = protoBuf$Class0.enumEntry_;
                        this.bitField0_ &= 0xFFFFDFFF;
                    }
                    else {
                        this.ensureEnumEntryIsMutable();
                        this.enumEntry_.addAll(protoBuf$Class0.enumEntry_);
                    }
                }
                if(!protoBuf$Class0.sealedSubclassFqName_.isEmpty()) {
                    if(this.sealedSubclassFqName_.isEmpty()) {
                        this.sealedSubclassFqName_ = protoBuf$Class0.sealedSubclassFqName_;
                        this.bitField0_ &= 0xFFFFBFFF;
                    }
                    else {
                        this.ensureSealedSubclassFqNameIsMutable();
                        this.sealedSubclassFqName_.addAll(protoBuf$Class0.sealedSubclassFqName_);
                    }
                }
                if(protoBuf$Class0.hasInlineClassUnderlyingPropertyName()) {
                    this.setInlineClassUnderlyingPropertyName(protoBuf$Class0.getInlineClassUnderlyingPropertyName());
                }
                if(protoBuf$Class0.hasInlineClassUnderlyingType()) {
                    this.mergeInlineClassUnderlyingType(protoBuf$Class0.getInlineClassUnderlyingType());
                }
                if(protoBuf$Class0.hasInlineClassUnderlyingTypeId()) {
                    this.setInlineClassUnderlyingTypeId(protoBuf$Class0.getInlineClassUnderlyingTypeId());
                }
                if(!protoBuf$Class0.multiFieldValueClassUnderlyingName_.isEmpty()) {
                    if(this.multiFieldValueClassUnderlyingName_.isEmpty()) {
                        this.multiFieldValueClassUnderlyingName_ = protoBuf$Class0.multiFieldValueClassUnderlyingName_;
                        this.bitField0_ &= 0xFFFBFFFF;
                    }
                    else {
                        this.ensureMultiFieldValueClassUnderlyingNameIsMutable();
                        this.multiFieldValueClassUnderlyingName_.addAll(protoBuf$Class0.multiFieldValueClassUnderlyingName_);
                    }
                }
                if(!protoBuf$Class0.multiFieldValueClassUnderlyingType_.isEmpty()) {
                    if(this.multiFieldValueClassUnderlyingType_.isEmpty()) {
                        this.multiFieldValueClassUnderlyingType_ = protoBuf$Class0.multiFieldValueClassUnderlyingType_;
                        this.bitField0_ &= 0xFFF7FFFF;
                    }
                    else {
                        this.ensureMultiFieldValueClassUnderlyingTypeIsMutable();
                        this.multiFieldValueClassUnderlyingType_.addAll(protoBuf$Class0.multiFieldValueClassUnderlyingType_);
                    }
                }
                if(!protoBuf$Class0.multiFieldValueClassUnderlyingTypeId_.isEmpty()) {
                    if(this.multiFieldValueClassUnderlyingTypeId_.isEmpty()) {
                        this.multiFieldValueClassUnderlyingTypeId_ = protoBuf$Class0.multiFieldValueClassUnderlyingTypeId_;
                        this.bitField0_ &= 0xFFEFFFFF;
                    }
                    else {
                        this.ensureMultiFieldValueClassUnderlyingTypeIdIsMutable();
                        this.multiFieldValueClassUnderlyingTypeId_.addAll(protoBuf$Class0.multiFieldValueClassUnderlyingTypeId_);
                    }
                }
                if(protoBuf$Class0.hasTypeTable()) {
                    this.mergeTypeTable(protoBuf$Class0.getTypeTable());
                }
                if(!protoBuf$Class0.versionRequirement_.isEmpty()) {
                    if(this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = protoBuf$Class0.versionRequirement_;
                        this.bitField0_ &= 0xFFBFFFFF;
                    }
                    else {
                        this.ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(protoBuf$Class0.versionRequirement_);
                    }
                }
                if(protoBuf$Class0.hasVersionRequirementTable()) {
                    this.mergeVersionRequirementTable(protoBuf$Class0.getVersionRequirementTable());
                }
                this.mergeExtensionFields(protoBuf$Class0);
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Class0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                Class protoBuf$Class1;
                Class protoBuf$Class0;
                try {
                    try {
                        protoBuf$Class0 = null;
                        protoBuf$Class1 = (Class)Class.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        Class protoBuf$Class2 = (Class)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$Class0 = protoBuf$Class2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$Class0 != null) {
                    this.mergeFrom(protoBuf$Class0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$Class1 != null) {
                    this.mergeFrom(protoBuf$Class1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((Class)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder mergeInlineClassUnderlyingType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.inlineClassUnderlyingType_ = (this.bitField0_ & 0x10000) != 0x10000 || this.inlineClassUnderlyingType_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.inlineClassUnderlyingType_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 0x10000;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder mergeTypeTable(TypeTable protoBuf$TypeTable0) {
                this.typeTable_ = (this.bitField0_ & 0x200000) != 0x200000 || this.typeTable_ == TypeTable.getDefaultInstance() ? protoBuf$TypeTable0 : TypeTable.newBuilder(this.typeTable_).mergeFrom(protoBuf$TypeTable0).buildPartial();
                this.bitField0_ |= 0x200000;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder mergeVersionRequirementTable(VersionRequirementTable protoBuf$VersionRequirementTable0) {
                this.versionRequirementTable_ = (this.bitField0_ & 0x800000) != 0x800000 || this.versionRequirementTable_ == VersionRequirementTable.getDefaultInstance() ? protoBuf$VersionRequirementTable0 : VersionRequirementTable.newBuilder(this.versionRequirementTable_).mergeFrom(protoBuf$VersionRequirementTable0).buildPartial();
                this.bitField0_ |= 0x800000;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder setCompanionObjectName(int v) {
                this.bitField0_ |= 4;
                this.companionObjectName_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder setFlags(int v) {
                this.bitField0_ |= 1;
                this.flags_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder setFqName(int v) {
                this.bitField0_ |= 2;
                this.fqName_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder setInlineClassUnderlyingPropertyName(int v) {
                this.bitField0_ |= 0x8000;
                this.inlineClassUnderlyingPropertyName_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder setInlineClassUnderlyingTypeId(int v) {
                this.bitField0_ |= 0x20000;
                this.inlineClassUnderlyingTypeId_ = v;
                return this;
            }
        }

        public static enum Kind implements EnumLite {
            CLASS(0, 0),
            INTERFACE(1, 1),
            ENUM_CLASS(2, 2),
            ENUM_ENTRY(3, 3),
            ANNOTATION_CLASS(4, 4),
            OBJECT(5, 5),
            COMPANION_OBJECT(6, 6);

            private static EnumLiteMap internalValueMap;
            private final int value;

            static {
                Kind.internalValueMap = new EnumLiteMap() {
                    public Kind findValueByNumber(int v) {
                        return Kind.valueOf(v);
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                    public EnumLite findValueByNumber(int v) {
                        return this.findValueByNumber(v);
                    }
                };
            }

            private Kind(int v1, int v2) {
                this.value = v2;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static Kind valueOf(int v) {
                switch(v) {
                    case 0: {
                        return Kind.CLASS;
                    }
                    case 1: {
                        return Kind.INTERFACE;
                    }
                    case 2: {
                        return Kind.ENUM_CLASS;
                    }
                    case 3: {
                        return Kind.ENUM_ENTRY;
                    }
                    case 4: {
                        return Kind.ANNOTATION_CLASS;
                    }
                    case 5: {
                        return Kind.OBJECT;
                    }
                    case 6: {
                        return Kind.COMPANION_OBJECT;
                    }
                    default: {
                        return null;
                    }
                }
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private int companionObjectName_;
        private List constructor_;
        private int contextReceiverTypeIdMemoizedSerializedSize;
        private List contextReceiverTypeId_;
        private List contextReceiverType_;
        private static final Class defaultInstance;
        private List enumEntry_;
        private int flags_;
        private int fqName_;
        private List function_;
        private int inlineClassUnderlyingPropertyName_;
        private int inlineClassUnderlyingTypeId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type inlineClassUnderlyingType_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int multiFieldValueClassUnderlyingNameMemoizedSerializedSize;
        private List multiFieldValueClassUnderlyingName_;
        private int multiFieldValueClassUnderlyingTypeIdMemoizedSerializedSize;
        private List multiFieldValueClassUnderlyingTypeId_;
        private List multiFieldValueClassUnderlyingType_;
        private int nestedClassNameMemoizedSerializedSize;
        private List nestedClassName_;
        private List property_;
        private int sealedSubclassFqNameMemoizedSerializedSize;
        private List sealedSubclassFqName_;
        private int supertypeIdMemoizedSerializedSize;
        private List supertypeId_;
        private List supertype_;
        private List typeAlias_;
        private List typeParameter_;
        private TypeTable typeTable_;
        private final ByteString unknownFields;
        private VersionRequirementTable versionRequirementTable_;
        private List versionRequirement_;

        static {
            Class.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public Class parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new Class(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            Class protoBuf$Class0 = new Class(true);
            Class.defaultInstance = protoBuf$Class0;
            protoBuf$Class0.initFields();
        }

        private Class(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.supertypeIdMemoizedSerializedSize = -1;
            this.nestedClassNameMemoizedSerializedSize = -1;
            this.contextReceiverTypeIdMemoizedSerializedSize = -1;
            this.sealedSubclassFqNameMemoizedSerializedSize = -1;
            this.multiFieldValueClassUnderlyingNameMemoizedSerializedSize = -1;
            this.multiFieldValueClassUnderlyingTypeIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            int v = 0;
            boolean z = false;
            while(!z) {
                try {
                    try {
                        int v1 = codedInputStream0.readTag();
                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder protoBuf$Type$Builder0 = null;
                        switch(v1) {
                            case 0: {
                            label_19:
                                z = true;
                                continue;
                            }
                            case 8: {
                                this.bitField0_ |= 1;
                                this.flags_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 16: {
                                if((v & 0x20) != 0x20) {
                                    this.supertypeId_ = new ArrayList();
                                    v |= 0x20;
                                }
                                this.supertypeId_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 18: {
                                int v2 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 0x20) != 0x20 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.supertypeId_ = new ArrayList();
                                    v |= 0x20;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.supertypeId_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v2);
                                continue;
                            }
                            case 24: {
                                this.bitField0_ |= 2;
                                this.fqName_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 0x20: {
                                this.bitField0_ |= 4;
                                this.companionObjectName_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 42: {
                                if((v & 8) != 8) {
                                    this.typeParameter_ = new ArrayList();
                                    v |= 8;
                                }
                                this.typeParameter_.add(codedInputStream0.readMessage(TypeParameter.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 50: {
                                if((v & 16) != 16) {
                                    this.supertype_ = new ArrayList();
                                    v |= 16;
                                }
                                this.supertype_.add(codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 56: {
                                if((v & 0x40) != 0x40) {
                                    this.nestedClassName_ = new ArrayList();
                                    v |= 0x40;
                                }
                                this.nestedClassName_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 58: {
                                int v3 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 0x40) != 0x40 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.nestedClassName_ = new ArrayList();
                                    v |= 0x40;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.nestedClassName_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v3);
                                continue;
                            }
                            case 66: {
                                if((v & 0x200) != 0x200) {
                                    this.constructor_ = new ArrayList();
                                    v |= 0x200;
                                }
                                this.constructor_.add(codedInputStream0.readMessage(Constructor.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 74: {
                                if((v & 0x400) != 0x400) {
                                    this.function_ = new ArrayList();
                                    v |= 0x400;
                                }
                                this.function_.add(codedInputStream0.readMessage(Function.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 82: {
                                if((v & 0x800) != 0x800) {
                                    this.property_ = new ArrayList();
                                    v |= 0x800;
                                }
                                this.property_.add(codedInputStream0.readMessage(Property.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 90: {
                                if((v & 0x1000) != 0x1000) {
                                    this.typeAlias_ = new ArrayList();
                                    v |= 0x1000;
                                }
                                this.typeAlias_.add(codedInputStream0.readMessage(TypeAlias.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 106: {
                                if((v & 0x2000) != 0x2000) {
                                    this.enumEntry_ = new ArrayList();
                                    v |= 0x2000;
                                }
                                this.enumEntry_.add(codedInputStream0.readMessage(EnumEntry.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 0x80: {
                                if((v & 0x4000) != 0x4000) {
                                    this.sealedSubclassFqName_ = new ArrayList();
                                    v |= 0x4000;
                                }
                                this.sealedSubclassFqName_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 130: {
                                int v4 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 0x4000) != 0x4000 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.sealedSubclassFqName_ = new ArrayList();
                                    v |= 0x4000;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.sealedSubclassFqName_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v4);
                                continue;
                            }
                            case 0x88: {
                                this.bitField0_ |= 8;
                                this.inlineClassUnderlyingPropertyName_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 0x92: {
                                if((this.bitField0_ & 16) == 16) {
                                    protoBuf$Type$Builder0 = this.inlineClassUnderlyingType_.toBuilder();
                                }
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                this.inlineClassUnderlyingType_ = protoBuf$Type0;
                                if(protoBuf$Type$Builder0 != null) {
                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type0);
                                    this.inlineClassUnderlyingType_ = protoBuf$Type$Builder0.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                continue;
                            }
                            case 0x98: {
                                this.bitField0_ |= 0x20;
                                this.inlineClassUnderlyingTypeId_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 0xA2: {
                                if((v & 0x80) != 0x80) {
                                    this.contextReceiverType_ = new ArrayList();
                                    v |= 0x80;
                                }
                                this.contextReceiverType_.add(codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 0xA8: {
                                if((v & 0x100) != 0x100) {
                                    this.contextReceiverTypeId_ = new ArrayList();
                                    v |= 0x100;
                                }
                                this.contextReceiverTypeId_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 170: {
                                int v5 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 0x100) != 0x100 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.contextReceiverTypeId_ = new ArrayList();
                                    v |= 0x100;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.contextReceiverTypeId_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v5);
                                continue;
                            }
                            case 0xB0: {
                                if((v & 0x40000) != 0x40000) {
                                    this.multiFieldValueClassUnderlyingName_ = new ArrayList();
                                    v |= 0x40000;
                                }
                                this.multiFieldValueClassUnderlyingName_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 0xB2: {
                                int v6 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 0x40000) != 0x40000 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.multiFieldValueClassUnderlyingName_ = new ArrayList();
                                    v |= 0x40000;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.multiFieldValueClassUnderlyingName_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v6);
                                continue;
                            }
                            case 0xBA: {
                                if((v & 0x80000) != 0x80000) {
                                    this.multiFieldValueClassUnderlyingType_ = new ArrayList();
                                    v |= 0x80000;
                                }
                                this.multiFieldValueClassUnderlyingType_.add(codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 0xC0: {
                                if((v & 0x100000) != 0x100000) {
                                    this.multiFieldValueClassUnderlyingTypeId_ = new ArrayList();
                                    v |= 0x100000;
                                }
                                this.multiFieldValueClassUnderlyingTypeId_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 0xC2: {
                                int v7 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 0x100000) != 0x100000 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.multiFieldValueClassUnderlyingTypeId_ = new ArrayList();
                                    v |= 0x100000;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.multiFieldValueClassUnderlyingTypeId_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v7);
                                continue;
                            }
                            case 0xF2: {
                                if((this.bitField0_ & 0x40) == 0x40) {
                                    protoBuf$Type$Builder0 = this.typeTable_.toBuilder();
                                }
                                TypeTable protoBuf$TypeTable0 = (TypeTable)codedInputStream0.readMessage(TypeTable.PARSER, extensionRegistryLite0);
                                this.typeTable_ = protoBuf$TypeTable0;
                                if(protoBuf$Type$Builder0 != null) {
                                    ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder)protoBuf$Type$Builder0).mergeFrom(protoBuf$TypeTable0);
                                    this.typeTable_ = ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder)protoBuf$Type$Builder0).buildPartial();
                                }
                                this.bitField0_ |= 0x40;
                                continue;
                            }
                            case 0xF8: {
                                if((v & 0x400000) != 0x400000) {
                                    this.versionRequirement_ = new ArrayList();
                                    v |= 0x400000;
                                }
                                this.versionRequirement_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 0xFA: {
                                int v8 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 0x400000) != 0x400000 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_ = new ArrayList();
                                    v |= 0x400000;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v8);
                                continue;
                            }
                            case 0x102: {
                                if((this.bitField0_ & 0x80) == 0x80) {
                                    protoBuf$Type$Builder0 = this.versionRequirementTable_.toBuilder();
                                }
                                VersionRequirementTable protoBuf$VersionRequirementTable0 = (VersionRequirementTable)codedInputStream0.readMessage(VersionRequirementTable.PARSER, extensionRegistryLite0);
                                this.versionRequirementTable_ = protoBuf$VersionRequirementTable0;
                                if(protoBuf$Type$Builder0 != null) {
                                    ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder)protoBuf$Type$Builder0).mergeFrom(protoBuf$VersionRequirementTable0);
                                    this.versionRequirementTable_ = ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder)protoBuf$Type$Builder0).buildPartial();
                                }
                                this.bitField0_ |= 0x80;
                                break;
                            }
                            default: {
                                if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                    goto label_19;
                                }
                            }
                        }
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if((v & 0x20) == 0x20) {
                        this.supertypeId_ = Collections.unmodifiableList(this.supertypeId_);
                    }
                    if((v & 8) == 8) {
                        this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    }
                    if((v & 16) == 16) {
                        this.supertype_ = Collections.unmodifiableList(this.supertype_);
                    }
                    if((v & 0x40) == 0x40) {
                        this.nestedClassName_ = Collections.unmodifiableList(this.nestedClassName_);
                    }
                    if((v & 0x200) == 0x200) {
                        this.constructor_ = Collections.unmodifiableList(this.constructor_);
                    }
                    if((v & 0x400) == 0x400) {
                        this.function_ = Collections.unmodifiableList(this.function_);
                    }
                    if((v & 0x800) == 0x800) {
                        this.property_ = Collections.unmodifiableList(this.property_);
                    }
                    if((v & 0x1000) == 0x1000) {
                        this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                    }
                    if((v & 0x2000) == 0x2000) {
                        this.enumEntry_ = Collections.unmodifiableList(this.enumEntry_);
                    }
                    if((v & 0x4000) == 0x4000) {
                        this.sealedSubclassFqName_ = Collections.unmodifiableList(this.sealedSubclassFqName_);
                    }
                    if((v & 0x80) == 0x80) {
                        this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
                    }
                    if((v & 0x100) == 0x100) {
                        this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
                    }
                    if((v & 0x40000) == 0x40000) {
                        this.multiFieldValueClassUnderlyingName_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingName_);
                    }
                    if((v & 0x80000) == 0x80000) {
                        this.multiFieldValueClassUnderlyingType_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingType_);
                    }
                    if((v & 0x100000) == 0x100000) {
                        this.multiFieldValueClassUnderlyingTypeId_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingTypeId_);
                    }
                    if((v & 0x400000) == 0x400000) {
                        this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    this.makeExtensionsImmutable();
                    throw throwable0;
                }
            }
            if((v & 0x20) == 0x20) {
                this.supertypeId_ = Collections.unmodifiableList(this.supertypeId_);
            }
            if((v & 8) == 8) {
                this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
            }
            if((v & 16) == 16) {
                this.supertype_ = Collections.unmodifiableList(this.supertype_);
            }
            if((v & 0x40) == 0x40) {
                this.nestedClassName_ = Collections.unmodifiableList(this.nestedClassName_);
            }
            if((v & 0x200) == 0x200) {
                this.constructor_ = Collections.unmodifiableList(this.constructor_);
            }
            if((v & 0x400) == 0x400) {
                this.function_ = Collections.unmodifiableList(this.function_);
            }
            if((v & 0x800) == 0x800) {
                this.property_ = Collections.unmodifiableList(this.property_);
            }
            if((v & 0x1000) == 0x1000) {
                this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
            }
            if((v & 0x2000) == 0x2000) {
                this.enumEntry_ = Collections.unmodifiableList(this.enumEntry_);
            }
            if((v & 0x4000) == 0x4000) {
                this.sealedSubclassFqName_ = Collections.unmodifiableList(this.sealedSubclassFqName_);
            }
            if((v & 0x80) == 0x80) {
                this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
            }
            if((v & 0x100) == 0x100) {
                this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
            }
            if((v & 0x40000) == 0x40000) {
                this.multiFieldValueClassUnderlyingName_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingName_);
            }
            if((v & 0x80000) == 0x80000) {
                this.multiFieldValueClassUnderlyingType_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingType_);
            }
            if((v & 0x100000) == 0x100000) {
                this.multiFieldValueClassUnderlyingTypeId_ = Collections.unmodifiableList(this.multiFieldValueClassUnderlyingTypeId_);
            }
            if((v & 0x400000) == 0x400000) {
                this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
            this.makeExtensionsImmutable();
        }

        Class(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private Class(ExtendableBuilder generatedMessageLite$ExtendableBuilder0) {
            super(generatedMessageLite$ExtendableBuilder0);
            this.supertypeIdMemoizedSerializedSize = -1;
            this.nestedClassNameMemoizedSerializedSize = -1;
            this.contextReceiverTypeIdMemoizedSerializedSize = -1;
            this.sealedSubclassFqNameMemoizedSerializedSize = -1;
            this.multiFieldValueClassUnderlyingNameMemoizedSerializedSize = -1;
            this.multiFieldValueClassUnderlyingTypeIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$ExtendableBuilder0.getUnknownFields();
        }

        Class(ExtendableBuilder generatedMessageLite$ExtendableBuilder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$ExtendableBuilder0);
        }

        private Class(boolean z) {
            this.supertypeIdMemoizedSerializedSize = -1;
            this.nestedClassNameMemoizedSerializedSize = -1;
            this.contextReceiverTypeIdMemoizedSerializedSize = -1;
            this.sealedSubclassFqNameMemoizedSerializedSize = -1;
            this.multiFieldValueClassUnderlyingNameMemoizedSerializedSize = -1;
            this.multiFieldValueClassUnderlyingTypeIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public int getCompanionObjectName() {
            return this.companionObjectName_;
        }

        public Constructor getConstructor(int v) {
            return (Constructor)this.constructor_.get(v);
        }

        public int getConstructorCount() {
            return this.constructor_.size();
        }

        public List getConstructorList() {
            return this.constructor_;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getContextReceiverType(int v) {
            return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.contextReceiverType_.get(v);
        }

        public int getContextReceiverTypeCount() {
            return this.contextReceiverType_.size();
        }

        public List getContextReceiverTypeIdList() {
            return this.contextReceiverTypeId_;
        }

        public List getContextReceiverTypeList() {
            return this.contextReceiverType_;
        }

        public static Class getDefaultInstance() {
            return Class.defaultInstance;
        }

        public Class getDefaultInstanceForType() {
            return Class.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public EnumEntry getEnumEntry(int v) {
            return (EnumEntry)this.enumEntry_.get(v);
        }

        public int getEnumEntryCount() {
            return this.enumEntry_.size();
        }

        public List getEnumEntryList() {
            return this.enumEntry_;
        }

        public int getFlags() {
            return this.flags_;
        }

        public int getFqName() {
            return this.fqName_;
        }

        public Function getFunction(int v) {
            return (Function)this.function_.get(v);
        }

        public int getFunctionCount() {
            return this.function_.size();
        }

        public List getFunctionList() {
            return this.function_;
        }

        public int getInlineClassUnderlyingPropertyName() {
            return this.inlineClassUnderlyingPropertyName_;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getInlineClassUnderlyingType() {
            return this.inlineClassUnderlyingType_;
        }

        public int getInlineClassUnderlyingTypeId() {
            return this.inlineClassUnderlyingTypeId_;
        }

        public int getMultiFieldValueClassUnderlyingNameCount() {
            return this.multiFieldValueClassUnderlyingName_.size();
        }

        public List getMultiFieldValueClassUnderlyingNameList() {
            return this.multiFieldValueClassUnderlyingName_;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getMultiFieldValueClassUnderlyingType(int v) {
            return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.multiFieldValueClassUnderlyingType_.get(v);
        }

        public int getMultiFieldValueClassUnderlyingTypeCount() {
            return this.multiFieldValueClassUnderlyingType_.size();
        }

        public int getMultiFieldValueClassUnderlyingTypeIdCount() {
            return this.multiFieldValueClassUnderlyingTypeId_.size();
        }

        public List getMultiFieldValueClassUnderlyingTypeIdList() {
            return this.multiFieldValueClassUnderlyingTypeId_;
        }

        public List getMultiFieldValueClassUnderlyingTypeList() {
            return this.multiFieldValueClassUnderlyingType_;
        }

        public List getNestedClassNameList() {
            return this.nestedClassName_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return Class.PARSER;
        }

        public Property getProperty(int v) {
            return (Property)this.property_.get(v);
        }

        public int getPropertyCount() {
            return this.property_.size();
        }

        public List getPropertyList() {
            return this.property_;
        }

        public List getSealedSubclassFqNameList() {
            return this.sealedSubclassFqName_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v1 = this.memoizedSerializedSize;
            if(v1 != -1) {
                return v1;
            }
            int v2 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.flags_) : 0;
            int v4 = 0;
            for(int v3 = 0; v3 < this.supertypeId_.size(); ++v3) {
                v4 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.supertypeId_.get(v3)))));
            }
            int v5 = this.getSupertypeIdList().isEmpty() ? v2 + v4 : v2 + v4 + 1 + CodedOutputStream.computeInt32SizeNoTag(v4);
            this.supertypeIdMemoizedSerializedSize = v4;
            if((this.bitField0_ & 2) == 2) {
                v5 += CodedOutputStream.computeInt32Size(3, this.fqName_);
            }
            if((this.bitField0_ & 4) == 4) {
                v5 += CodedOutputStream.computeInt32Size(4, this.companionObjectName_);
            }
            for(int v6 = 0; v6 < this.typeParameter_.size(); ++v6) {
                v5 += CodedOutputStream.computeMessageSize(5, ((MessageLite)this.typeParameter_.get(v6)));
            }
            for(int v7 = 0; v7 < this.supertype_.size(); ++v7) {
                v5 += CodedOutputStream.computeMessageSize(6, ((MessageLite)this.supertype_.get(v7)));
            }
            int v9 = 0;
            for(int v8 = 0; v8 < this.nestedClassName_.size(); ++v8) {
                v9 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.nestedClassName_.get(v8)))));
            }
            int v10 = this.getNestedClassNameList().isEmpty() ? v5 + v9 : v5 + v9 + 1 + CodedOutputStream.computeInt32SizeNoTag(v9);
            this.nestedClassNameMemoizedSerializedSize = v9;
            for(int v11 = 0; v11 < this.constructor_.size(); ++v11) {
                v10 += CodedOutputStream.computeMessageSize(8, ((MessageLite)this.constructor_.get(v11)));
            }
            for(int v12 = 0; v12 < this.function_.size(); ++v12) {
                v10 += CodedOutputStream.computeMessageSize(9, ((MessageLite)this.function_.get(v12)));
            }
            for(int v13 = 0; v13 < this.property_.size(); ++v13) {
                v10 += CodedOutputStream.computeMessageSize(10, ((MessageLite)this.property_.get(v13)));
            }
            for(int v14 = 0; v14 < this.typeAlias_.size(); ++v14) {
                v10 += CodedOutputStream.computeMessageSize(11, ((MessageLite)this.typeAlias_.get(v14)));
            }
            for(int v15 = 0; v15 < this.enumEntry_.size(); ++v15) {
                v10 += CodedOutputStream.computeMessageSize(13, ((MessageLite)this.enumEntry_.get(v15)));
            }
            int v17 = 0;
            for(int v16 = 0; v16 < this.sealedSubclassFqName_.size(); ++v16) {
                v17 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.sealedSubclassFqName_.get(v16)))));
            }
            int v18 = this.getSealedSubclassFqNameList().isEmpty() ? v10 + v17 : v10 + v17 + 2 + CodedOutputStream.computeInt32SizeNoTag(v17);
            this.sealedSubclassFqNameMemoizedSerializedSize = v17;
            if((this.bitField0_ & 8) == 8) {
                v18 += CodedOutputStream.computeInt32Size(17, this.inlineClassUnderlyingPropertyName_);
            }
            if((this.bitField0_ & 16) == 16) {
                v18 += CodedOutputStream.computeMessageSize(18, this.inlineClassUnderlyingType_);
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                v18 += CodedOutputStream.computeInt32Size(19, this.inlineClassUnderlyingTypeId_);
            }
            for(int v19 = 0; v19 < this.contextReceiverType_.size(); ++v19) {
                v18 += CodedOutputStream.computeMessageSize(20, ((MessageLite)this.contextReceiverType_.get(v19)));
            }
            int v21 = 0;
            for(int v20 = 0; v20 < this.contextReceiverTypeId_.size(); ++v20) {
                v21 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.contextReceiverTypeId_.get(v20)))));
            }
            int v22 = this.getContextReceiverTypeIdList().isEmpty() ? v18 + v21 : v18 + v21 + 2 + CodedOutputStream.computeInt32SizeNoTag(v21);
            this.contextReceiverTypeIdMemoizedSerializedSize = v21;
            int v24 = 0;
            for(int v23 = 0; v23 < this.multiFieldValueClassUnderlyingName_.size(); ++v23) {
                v24 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.multiFieldValueClassUnderlyingName_.get(v23)))));
            }
            int v25 = this.getMultiFieldValueClassUnderlyingNameList().isEmpty() ? v22 + v24 : v22 + v24 + 2 + CodedOutputStream.computeInt32SizeNoTag(v24);
            this.multiFieldValueClassUnderlyingNameMemoizedSerializedSize = v24;
            for(int v26 = 0; v26 < this.multiFieldValueClassUnderlyingType_.size(); ++v26) {
                v25 += CodedOutputStream.computeMessageSize(23, ((MessageLite)this.multiFieldValueClassUnderlyingType_.get(v26)));
            }
            int v28 = 0;
            for(int v27 = 0; v27 < this.multiFieldValueClassUnderlyingTypeId_.size(); ++v27) {
                v28 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.multiFieldValueClassUnderlyingTypeId_.get(v27)))));
            }
            int v29 = this.getMultiFieldValueClassUnderlyingTypeIdList().isEmpty() ? v25 + v28 : v25 + v28 + 2 + CodedOutputStream.computeInt32SizeNoTag(v28);
            this.multiFieldValueClassUnderlyingTypeIdMemoizedSerializedSize = v28;
            if((this.bitField0_ & 0x40) == 0x40) {
                v29 += CodedOutputStream.computeMessageSize(30, this.typeTable_);
            }
            int v30 = 0;
            for(int v = 0; v < this.versionRequirement_.size(); ++v) {
                v30 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.versionRequirement_.get(v)))));
            }
            int v31 = v29 + v30 + this.getVersionRequirementList().size() * 2;
            if((this.bitField0_ & 0x80) == 0x80) {
                v31 += CodedOutputStream.computeMessageSize(0x20, this.versionRequirementTable_);
            }
            int v32 = v31 + this.extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = v32;
            return v32;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getSupertype(int v) {
            return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.supertype_.get(v);
        }

        public int getSupertypeCount() {
            return this.supertype_.size();
        }

        public List getSupertypeIdList() {
            return this.supertypeId_;
        }

        public List getSupertypeList() {
            return this.supertype_;
        }

        public TypeAlias getTypeAlias(int v) {
            return (TypeAlias)this.typeAlias_.get(v);
        }

        public int getTypeAliasCount() {
            return this.typeAlias_.size();
        }

        public List getTypeAliasList() {
            return this.typeAlias_;
        }

        public TypeParameter getTypeParameter(int v) {
            return (TypeParameter)this.typeParameter_.get(v);
        }

        public int getTypeParameterCount() {
            return this.typeParameter_.size();
        }

        public List getTypeParameterList() {
            return this.typeParameter_;
        }

        public TypeTable getTypeTable() {
            return this.typeTable_;
        }

        public List getVersionRequirementList() {
            return this.versionRequirement_;
        }

        public VersionRequirementTable getVersionRequirementTable() {
            return this.versionRequirementTable_;
        }

        public boolean hasCompanionObjectName() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasFqName() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasInlineClassUnderlyingPropertyName() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasInlineClassUnderlyingType() {
            return (this.bitField0_ & 16) == 16;
        }

        public boolean hasInlineClassUnderlyingTypeId() {
            return (this.bitField0_ & 0x20) == 0x20;
        }

        public boolean hasTypeTable() {
            return (this.bitField0_ & 0x40) == 0x40;
        }

        public boolean hasVersionRequirementTable() {
            return (this.bitField0_ & 0x80) == 0x80;
        }

        private void initFields() {
            this.flags_ = 6;
            this.fqName_ = 0;
            this.companionObjectName_ = 0;
            this.typeParameter_ = Collections.EMPTY_LIST;
            this.supertype_ = Collections.EMPTY_LIST;
            this.supertypeId_ = Collections.EMPTY_LIST;
            this.nestedClassName_ = Collections.EMPTY_LIST;
            this.contextReceiverType_ = Collections.EMPTY_LIST;
            this.contextReceiverTypeId_ = Collections.EMPTY_LIST;
            this.constructor_ = Collections.EMPTY_LIST;
            this.function_ = Collections.EMPTY_LIST;
            this.property_ = Collections.EMPTY_LIST;
            this.typeAlias_ = Collections.EMPTY_LIST;
            this.enumEntry_ = Collections.EMPTY_LIST;
            this.sealedSubclassFqName_ = Collections.EMPTY_LIST;
            this.inlineClassUnderlyingPropertyName_ = 0;
            this.inlineClassUnderlyingType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.inlineClassUnderlyingTypeId_ = 0;
            this.multiFieldValueClassUnderlyingName_ = Collections.EMPTY_LIST;
            this.multiFieldValueClassUnderlyingType_ = Collections.EMPTY_LIST;
            this.multiFieldValueClassUnderlyingTypeId_ = Collections.EMPTY_LIST;
            this.typeTable_ = TypeTable.getDefaultInstance();
            this.versionRequirement_ = Collections.EMPTY_LIST;
            this.versionRequirementTable_ = VersionRequirementTable.getDefaultInstance();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            if(!this.hasFqName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for(int v1 = 0; v1 < this.getTypeParameterCount(); ++v1) {
                if(!this.getTypeParameter(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for(int v2 = 0; v2 < this.getSupertypeCount(); ++v2) {
                if(!this.getSupertype(v2).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for(int v3 = 0; v3 < this.getContextReceiverTypeCount(); ++v3) {
                if(!this.getContextReceiverType(v3).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for(int v4 = 0; v4 < this.getConstructorCount(); ++v4) {
                if(!this.getConstructor(v4).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for(int v5 = 0; v5 < this.getFunctionCount(); ++v5) {
                if(!this.getFunction(v5).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for(int v6 = 0; v6 < this.getPropertyCount(); ++v6) {
                if(!this.getProperty(v6).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for(int v7 = 0; v7 < this.getTypeAliasCount(); ++v7) {
                if(!this.getTypeAlias(v7).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for(int v8 = 0; v8 < this.getEnumEntryCount(); ++v8) {
                if(!this.getEnumEntry(v8).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(this.hasInlineClassUnderlyingType() && !this.getInlineClassUnderlyingType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for(int v9 = 0; v9 < this.getMultiFieldValueClassUnderlyingTypeCount(); ++v9) {
                if(!this.getMultiFieldValueClassUnderlyingType(v9).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(this.hasTypeTable() && !this.getTypeTable().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder.access$8700();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder newBuilder(Class protoBuf$Class0) {
            return Class.newBuilder().mergeFrom(protoBuf$Class0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder newBuilderForType() {
            return Class.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public static Class parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (Class)Class.PARSER.parseFrom(inputStream0, extensionRegistryLite0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Builder toBuilder() {
            return Class.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            ExtensionWriter generatedMessageLite$ExtendableMessage$ExtensionWriter0 = this.newExtensionWriter();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(1, this.flags_);
            }
            if(this.getSupertypeIdList().size() > 0) {
                codedOutputStream0.writeRawVarint32(18);
                codedOutputStream0.writeRawVarint32(this.supertypeIdMemoizedSerializedSize);
            }
            for(int v1 = 0; v1 < this.supertypeId_.size(); ++v1) {
                codedOutputStream0.writeInt32NoTag(((int)(((Integer)this.supertypeId_.get(v1)))));
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeInt32(3, this.fqName_);
            }
            if((this.bitField0_ & 4) == 4) {
                codedOutputStream0.writeInt32(4, this.companionObjectName_);
            }
            for(int v2 = 0; v2 < this.typeParameter_.size(); ++v2) {
                codedOutputStream0.writeMessage(5, ((MessageLite)this.typeParameter_.get(v2)));
            }
            for(int v3 = 0; v3 < this.supertype_.size(); ++v3) {
                codedOutputStream0.writeMessage(6, ((MessageLite)this.supertype_.get(v3)));
            }
            if(this.getNestedClassNameList().size() > 0) {
                codedOutputStream0.writeRawVarint32(58);
                codedOutputStream0.writeRawVarint32(this.nestedClassNameMemoizedSerializedSize);
            }
            for(int v4 = 0; v4 < this.nestedClassName_.size(); ++v4) {
                codedOutputStream0.writeInt32NoTag(((int)(((Integer)this.nestedClassName_.get(v4)))));
            }
            for(int v5 = 0; v5 < this.constructor_.size(); ++v5) {
                codedOutputStream0.writeMessage(8, ((MessageLite)this.constructor_.get(v5)));
            }
            for(int v6 = 0; v6 < this.function_.size(); ++v6) {
                codedOutputStream0.writeMessage(9, ((MessageLite)this.function_.get(v6)));
            }
            for(int v7 = 0; v7 < this.property_.size(); ++v7) {
                codedOutputStream0.writeMessage(10, ((MessageLite)this.property_.get(v7)));
            }
            for(int v8 = 0; v8 < this.typeAlias_.size(); ++v8) {
                codedOutputStream0.writeMessage(11, ((MessageLite)this.typeAlias_.get(v8)));
            }
            for(int v9 = 0; v9 < this.enumEntry_.size(); ++v9) {
                codedOutputStream0.writeMessage(13, ((MessageLite)this.enumEntry_.get(v9)));
            }
            if(this.getSealedSubclassFqNameList().size() > 0) {
                codedOutputStream0.writeRawVarint32(130);
                codedOutputStream0.writeRawVarint32(this.sealedSubclassFqNameMemoizedSerializedSize);
            }
            for(int v10 = 0; v10 < this.sealedSubclassFqName_.size(); ++v10) {
                codedOutputStream0.writeInt32NoTag(((int)(((Integer)this.sealedSubclassFqName_.get(v10)))));
            }
            if((this.bitField0_ & 8) == 8) {
                codedOutputStream0.writeInt32(17, this.inlineClassUnderlyingPropertyName_);
            }
            if((this.bitField0_ & 16) == 16) {
                codedOutputStream0.writeMessage(18, this.inlineClassUnderlyingType_);
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                codedOutputStream0.writeInt32(19, this.inlineClassUnderlyingTypeId_);
            }
            for(int v11 = 0; v11 < this.contextReceiverType_.size(); ++v11) {
                codedOutputStream0.writeMessage(20, ((MessageLite)this.contextReceiverType_.get(v11)));
            }
            if(this.getContextReceiverTypeIdList().size() > 0) {
                codedOutputStream0.writeRawVarint32(170);
                codedOutputStream0.writeRawVarint32(this.contextReceiverTypeIdMemoizedSerializedSize);
            }
            for(int v12 = 0; v12 < this.contextReceiverTypeId_.size(); ++v12) {
                codedOutputStream0.writeInt32NoTag(((int)(((Integer)this.contextReceiverTypeId_.get(v12)))));
            }
            if(this.getMultiFieldValueClassUnderlyingNameList().size() > 0) {
                codedOutputStream0.writeRawVarint32(0xB2);
                codedOutputStream0.writeRawVarint32(this.multiFieldValueClassUnderlyingNameMemoizedSerializedSize);
            }
            for(int v13 = 0; v13 < this.multiFieldValueClassUnderlyingName_.size(); ++v13) {
                codedOutputStream0.writeInt32NoTag(((int)(((Integer)this.multiFieldValueClassUnderlyingName_.get(v13)))));
            }
            for(int v14 = 0; v14 < this.multiFieldValueClassUnderlyingType_.size(); ++v14) {
                codedOutputStream0.writeMessage(23, ((MessageLite)this.multiFieldValueClassUnderlyingType_.get(v14)));
            }
            if(this.getMultiFieldValueClassUnderlyingTypeIdList().size() > 0) {
                codedOutputStream0.writeRawVarint32(0xC2);
                codedOutputStream0.writeRawVarint32(this.multiFieldValueClassUnderlyingTypeIdMemoizedSerializedSize);
            }
            for(int v15 = 0; v15 < this.multiFieldValueClassUnderlyingTypeId_.size(); ++v15) {
                codedOutputStream0.writeInt32NoTag(((int)(((Integer)this.multiFieldValueClassUnderlyingTypeId_.get(v15)))));
            }
            if((this.bitField0_ & 0x40) == 0x40) {
                codedOutputStream0.writeMessage(30, this.typeTable_);
            }
            for(int v = 0; v < this.versionRequirement_.size(); ++v) {
                codedOutputStream0.writeInt32(0x1F, ((int)(((Integer)this.versionRequirement_.get(v)))));
            }
            if((this.bitField0_ & 0x80) == 0x80) {
                codedOutputStream0.writeMessage(0x20, this.versionRequirementTable_);
            }
            generatedMessageLite$ExtendableMessage$ExtensionWriter0.writeUntil(19000, codedOutputStream0);
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class Constructor extends ExtendableMessage implements ProtoBuf.ConstructorOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder extends ExtendableBuilder implements ProtoBuf.ConstructorOrBuilder {
            private int bitField0_;
            private int flags_;
            private List valueParameter_;
            private List versionRequirement_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder() {
                this.flags_ = 6;
                this.valueParameter_ = Collections.EMPTY_LIST;
                this.versionRequirement_ = Collections.EMPTY_LIST;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder access$13300() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder.create();
            }

            public Constructor build() {
                Constructor protoBuf$Constructor0 = this.buildPartial();
                if(!protoBuf$Constructor0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder.newUninitializedMessageException(protoBuf$Constructor0);
                }
                return protoBuf$Constructor0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public Constructor buildPartial() {
                Constructor protoBuf$Constructor0 = new Constructor(this, null);
                int v = (this.bitField0_ & 1) == 1 ? 1 : 0;
                protoBuf$Constructor0.flags_ = this.flags_;
                if((this.bitField0_ & 2) == 2) {
                    this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                    this.bitField0_ &= -3;
                }
                protoBuf$Constructor0.valueParameter_ = this.valueParameter_;
                if((this.bitField0_ & 4) == 4) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= -5;
                }
                protoBuf$Constructor0.versionRequirement_ = this.versionRequirement_;
                protoBuf$Constructor0.bitField0_ = v;
                return protoBuf$Constructor0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public ExtendableBuilder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder();
            }

            private void ensureValueParameterIsMutable() {
                if((this.bitField0_ & 2) != 2) {
                    this.valueParameter_ = new ArrayList(this.valueParameter_);
                    this.bitField0_ |= 2;
                }
            }

            private void ensureVersionRequirementIsMutable() {
                if((this.bitField0_ & 4) != 4) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 4;
                }
            }

            public Constructor getDefaultInstanceForType() {
                return Constructor.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public ValueParameter getValueParameter(int v) {
                return (ValueParameter)this.valueParameter_.get(v);
            }

            public int getValueParameterCount() {
                return this.valueParameter_.size();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for(int v = 0; v < this.getValueParameterCount(); ++v) {
                    if(!this.getValueParameter(v).isInitialized()) {
                        return false;
                    }
                }
                return this.extensionsAreInitialized();
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder mergeFrom(Constructor protoBuf$Constructor0) {
                if(protoBuf$Constructor0 == Constructor.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$Constructor0.hasFlags()) {
                    this.setFlags(protoBuf$Constructor0.getFlags());
                }
                if(!protoBuf$Constructor0.valueParameter_.isEmpty()) {
                    if(this.valueParameter_.isEmpty()) {
                        this.valueParameter_ = protoBuf$Constructor0.valueParameter_;
                        this.bitField0_ &= -3;
                    }
                    else {
                        this.ensureValueParameterIsMutable();
                        this.valueParameter_.addAll(protoBuf$Constructor0.valueParameter_);
                    }
                }
                if(!protoBuf$Constructor0.versionRequirement_.isEmpty()) {
                    if(this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = protoBuf$Constructor0.versionRequirement_;
                        this.bitField0_ &= -5;
                    }
                    else {
                        this.ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(protoBuf$Constructor0.versionRequirement_);
                    }
                }
                this.mergeExtensionFields(protoBuf$Constructor0);
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Constructor0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                Constructor protoBuf$Constructor1;
                Constructor protoBuf$Constructor0;
                try {
                    try {
                        protoBuf$Constructor0 = null;
                        protoBuf$Constructor1 = (Constructor)Constructor.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        Constructor protoBuf$Constructor2 = (Constructor)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$Constructor0 = protoBuf$Constructor2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$Constructor0 != null) {
                    this.mergeFrom(protoBuf$Constructor0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$Constructor1 != null) {
                    this.mergeFrom(protoBuf$Constructor1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((Constructor)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder setFlags(int v) {
                this.bitField0_ |= 1;
                this.flags_ = v;
                return this;
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private static final Constructor defaultInstance;
        private int flags_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final ByteString unknownFields;
        private List valueParameter_;
        private List versionRequirement_;

        static {
            Constructor.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public Constructor parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new Constructor(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            Constructor protoBuf$Constructor0 = new Constructor(true);
            Constructor.defaultInstance = protoBuf$Constructor0;
            protoBuf$Constructor0.initFields();
        }

        private Constructor(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            int v = 0;
            boolean z = false;
            while(!z) {
                try {
                    try {
                        int v1 = codedInputStream0.readTag();
                        switch(v1) {
                            case 0: {
                                break;
                            }
                            case 8: {
                                this.bitField0_ |= 1;
                                this.flags_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 18: {
                                if((v & 2) != 2) {
                                    this.valueParameter_ = new ArrayList();
                                    v |= 2;
                                }
                                this.valueParameter_.add(codedInputStream0.readMessage(ValueParameter.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 0xF8: {
                                if((v & 4) != 4) {
                                    this.versionRequirement_ = new ArrayList();
                                    v |= 4;
                                }
                                this.versionRequirement_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 0xFA: {
                                int v2 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 4) != 4 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_ = new ArrayList();
                                    v |= 4;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v2);
                                continue;
                            }
                            default: {
                                if(this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                    continue;
                                }
                            }
                        }
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if((v & 2) == 2) {
                        this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                    }
                    if((v & 4) == 4) {
                        this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    this.makeExtensionsImmutable();
                    throw throwable0;
                }
                z = true;
            }
            if((v & 2) == 2) {
                this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
            }
            if((v & 4) == 4) {
                this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
            this.makeExtensionsImmutable();
        }

        Constructor(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private Constructor(ExtendableBuilder generatedMessageLite$ExtendableBuilder0) {
            super(generatedMessageLite$ExtendableBuilder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$ExtendableBuilder0.getUnknownFields();
        }

        Constructor(ExtendableBuilder generatedMessageLite$ExtendableBuilder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$ExtendableBuilder0);
        }

        private Constructor(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Constructor getDefaultInstance() {
            return Constructor.defaultInstance;
        }

        public Constructor getDefaultInstanceForType() {
            return Constructor.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getFlags() {
            return this.flags_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return Constructor.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v1 = this.memoizedSerializedSize;
            if(v1 != -1) {
                return v1;
            }
            int v2 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.flags_) : 0;
            for(int v3 = 0; v3 < this.valueParameter_.size(); ++v3) {
                v2 += CodedOutputStream.computeMessageSize(2, ((MessageLite)this.valueParameter_.get(v3)));
            }
            int v4 = 0;
            for(int v = 0; v < this.versionRequirement_.size(); ++v) {
                v4 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.versionRequirement_.get(v)))));
            }
            int v5 = v2 + v4 + this.getVersionRequirementList().size() * 2 + this.extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = v5;
            return v5;
        }

        public ValueParameter getValueParameter(int v) {
            return (ValueParameter)this.valueParameter_.get(v);
        }

        public int getValueParameterCount() {
            return this.valueParameter_.size();
        }

        public List getValueParameterList() {
            return this.valueParameter_;
        }

        public List getVersionRequirementList() {
            return this.versionRequirement_;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        private void initFields() {
            this.flags_ = 6;
            this.valueParameter_ = Collections.EMPTY_LIST;
            this.versionRequirement_ = Collections.EMPTY_LIST;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            for(int v1 = 0; v1 < this.getValueParameterCount(); ++v1) {
                if(!this.getValueParameter(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder.access$13300();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder newBuilder(Constructor protoBuf$Constructor0) {
            return Constructor.newBuilder().mergeFrom(protoBuf$Constructor0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder newBuilderForType() {
            return Constructor.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.Builder toBuilder() {
            return Constructor.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            ExtensionWriter generatedMessageLite$ExtendableMessage$ExtensionWriter0 = this.newExtensionWriter();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(1, this.flags_);
            }
            for(int v1 = 0; v1 < this.valueParameter_.size(); ++v1) {
                codedOutputStream0.writeMessage(2, ((MessageLite)this.valueParameter_.get(v1)));
            }
            for(int v = 0; v < this.versionRequirement_.size(); ++v) {
                codedOutputStream0.writeInt32(0x1F, ((int)(((Integer)this.versionRequirement_.get(v)))));
            }
            generatedMessageLite$ExtendableMessage$ExtensionWriter0.writeUntil(19000, codedOutputStream0);
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class Contract extends GeneratedMessageLite implements ProtoBuf.ContractOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.ContractOrBuilder {
            private int bitField0_;
            private List effect_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder() {
                this.effect_ = Collections.EMPTY_LIST;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder access$23500() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder.create();
            }

            public Contract build() {
                Contract protoBuf$Contract0 = this.buildPartial();
                if(!protoBuf$Contract0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder.newUninitializedMessageException(protoBuf$Contract0);
                }
                return protoBuf$Contract0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public Contract buildPartial() {
                Contract protoBuf$Contract0 = new Contract(this, null);
                if((this.bitField0_ & 1) == 1) {
                    this.effect_ = Collections.unmodifiableList(this.effect_);
                    this.bitField0_ &= -2;
                }
                protoBuf$Contract0.effect_ = this.effect_;
                return protoBuf$Contract0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder();
            }

            private void ensureEffectIsMutable() {
                if((this.bitField0_ & 1) != 1) {
                    this.effect_ = new ArrayList(this.effect_);
                    this.bitField0_ |= 1;
                }
            }

            public Contract getDefaultInstanceForType() {
                return Contract.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public Effect getEffect(int v) {
                return (Effect)this.effect_.get(v);
            }

            public int getEffectCount() {
                return this.effect_.size();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for(int v = 0; v < this.getEffectCount(); ++v) {
                    if(!this.getEffect(v).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder mergeFrom(Contract protoBuf$Contract0) {
                if(protoBuf$Contract0 == Contract.getDefaultInstance()) {
                    return this;
                }
                if(!protoBuf$Contract0.effect_.isEmpty()) {
                    if(this.effect_.isEmpty()) {
                        this.effect_ = protoBuf$Contract0.effect_;
                        this.bitField0_ &= -2;
                    }
                    else {
                        this.ensureEffectIsMutable();
                        this.effect_.addAll(protoBuf$Contract0.effect_);
                    }
                }
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Contract0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                Contract protoBuf$Contract1;
                Contract protoBuf$Contract0;
                try {
                    try {
                        protoBuf$Contract0 = null;
                        protoBuf$Contract1 = (Contract)Contract.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        Contract protoBuf$Contract2 = (Contract)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$Contract0 = protoBuf$Contract2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$Contract0 != null) {
                    this.mergeFrom(protoBuf$Contract0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$Contract1 != null) {
                    this.mergeFrom(protoBuf$Contract1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((Contract)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }
        }

        public static Parser PARSER;
        private static final Contract defaultInstance;
        private List effect_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final ByteString unknownFields;

        static {
            Contract.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public Contract parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new Contract(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            Contract protoBuf$Contract0 = new Contract(true);
            Contract.defaultInstance = protoBuf$Contract0;
            protoBuf$Contract0.initFields();
        }

        private Contract(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            boolean z = false;
            boolean z1 = false;
            while(!z1) {
                try {
                    try {
                        int v = codedInputStream0.readTag();
                        switch(v) {
                            case 0: {
                                z1 = true;
                                continue;
                            }
                            case 10: {
                                break;
                            }
                            default: {
                                if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v)) {
                                    z1 = true;
                                    continue;
                                }
                                continue;
                            }
                        }
                        if(!z) {
                            this.effect_ = new ArrayList();
                            z = true;
                        }
                        this.effect_.add(codedInputStream0.readMessage(Effect.PARSER, extensionRegistryLite0));
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if(z) {
                        this.effect_ = Collections.unmodifiableList(this.effect_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    throw throwable0;
                }
            }
            if(z) {
                this.effect_ = Collections.unmodifiableList(this.effect_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
        }

        Contract(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private Contract(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        Contract(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private Contract(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Contract getDefaultInstance() {
            return Contract.defaultInstance;
        }

        public Contract getDefaultInstanceForType() {
            return Contract.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public Effect getEffect(int v) {
            return (Effect)this.effect_.get(v);
        }

        public int getEffectCount() {
            return this.effect_.size();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return Contract.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v2 = 0;
            for(int v1 = 0; v1 < this.effect_.size(); ++v1) {
                v2 += CodedOutputStream.computeMessageSize(1, ((MessageLite)this.effect_.get(v1)));
            }
            int v3 = v2 + this.unknownFields.size();
            this.memoizedSerializedSize = v3;
            return v3;
        }

        private void initFields() {
            this.effect_ = Collections.EMPTY_LIST;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            for(int v1 = 0; v1 < this.getEffectCount(); ++v1) {
                if(!this.getEffect(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder.access$23500();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder newBuilder(Contract protoBuf$Contract0) {
            return Contract.newBuilder().mergeFrom(protoBuf$Contract0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder newBuilderForType() {
            return Contract.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder toBuilder() {
            return Contract.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            for(int v = 0; v < this.effect_.size(); ++v) {
                codedOutputStream0.writeMessage(1, ((MessageLite)this.effect_.get(v)));
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class Effect extends GeneratedMessageLite implements ProtoBuf.EffectOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.EffectOrBuilder {
            private int bitField0_;
            private Expression conclusionOfConditionalEffect_;
            private List effectConstructorArgument_;
            private EffectType effectType_;
            private InvocationKind kind_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder() {
                this.effectType_ = EffectType.RETURNS_CONSTANT;
                this.effectConstructorArgument_ = Collections.EMPTY_LIST;
                this.conclusionOfConditionalEffect_ = Expression.getDefaultInstance();
                this.kind_ = InvocationKind.AT_MOST_ONCE;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder access$24000() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder.create();
            }

            public Effect build() {
                Effect protoBuf$Effect0 = this.buildPartial();
                if(!protoBuf$Effect0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder.newUninitializedMessageException(protoBuf$Effect0);
                }
                return protoBuf$Effect0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public Effect buildPartial() {
                Effect protoBuf$Effect0 = new Effect(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                protoBuf$Effect0.effectType_ = this.effectType_;
                if((this.bitField0_ & 2) == 2) {
                    this.effectConstructorArgument_ = Collections.unmodifiableList(this.effectConstructorArgument_);
                    this.bitField0_ &= -3;
                }
                protoBuf$Effect0.effectConstructorArgument_ = this.effectConstructorArgument_;
                if((v & 4) == 4) {
                    v1 |= 2;
                }
                protoBuf$Effect0.conclusionOfConditionalEffect_ = this.conclusionOfConditionalEffect_;
                if((v & 8) == 8) {
                    v1 |= 4;
                }
                protoBuf$Effect0.kind_ = this.kind_;
                protoBuf$Effect0.bitField0_ = v1;
                return protoBuf$Effect0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder();
            }

            private void ensureEffectConstructorArgumentIsMutable() {
                if((this.bitField0_ & 2) != 2) {
                    this.effectConstructorArgument_ = new ArrayList(this.effectConstructorArgument_);
                    this.bitField0_ |= 2;
                }
            }

            public Expression getConclusionOfConditionalEffect() {
                return this.conclusionOfConditionalEffect_;
            }

            public Effect getDefaultInstanceForType() {
                return Effect.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public Expression getEffectConstructorArgument(int v) {
                return (Expression)this.effectConstructorArgument_.get(v);
            }

            public int getEffectConstructorArgumentCount() {
                return this.effectConstructorArgument_.size();
            }

            public boolean hasConclusionOfConditionalEffect() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for(int v = 0; v < this.getEffectConstructorArgumentCount(); ++v) {
                    if(!this.getEffectConstructorArgument(v).isInitialized()) {
                        return false;
                    }
                }
                return !this.hasConclusionOfConditionalEffect() || this.getConclusionOfConditionalEffect().isInitialized();
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder mergeConclusionOfConditionalEffect(Expression protoBuf$Expression0) {
                this.conclusionOfConditionalEffect_ = (this.bitField0_ & 4) != 4 || this.conclusionOfConditionalEffect_ == Expression.getDefaultInstance() ? protoBuf$Expression0 : Expression.newBuilder(this.conclusionOfConditionalEffect_).mergeFrom(protoBuf$Expression0).buildPartial();
                this.bitField0_ |= 4;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder mergeFrom(Effect protoBuf$Effect0) {
                if(protoBuf$Effect0 == Effect.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$Effect0.hasEffectType()) {
                    this.setEffectType(protoBuf$Effect0.getEffectType());
                }
                if(!protoBuf$Effect0.effectConstructorArgument_.isEmpty()) {
                    if(this.effectConstructorArgument_.isEmpty()) {
                        this.effectConstructorArgument_ = protoBuf$Effect0.effectConstructorArgument_;
                        this.bitField0_ &= -3;
                    }
                    else {
                        this.ensureEffectConstructorArgumentIsMutable();
                        this.effectConstructorArgument_.addAll(protoBuf$Effect0.effectConstructorArgument_);
                    }
                }
                if(protoBuf$Effect0.hasConclusionOfConditionalEffect()) {
                    this.mergeConclusionOfConditionalEffect(protoBuf$Effect0.getConclusionOfConditionalEffect());
                }
                if(protoBuf$Effect0.hasKind()) {
                    this.setKind(protoBuf$Effect0.getKind());
                }
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Effect0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                Effect protoBuf$Effect1;
                Effect protoBuf$Effect0;
                try {
                    try {
                        protoBuf$Effect0 = null;
                        protoBuf$Effect1 = (Effect)Effect.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        Effect protoBuf$Effect2 = (Effect)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$Effect0 = protoBuf$Effect2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$Effect0 != null) {
                    this.mergeFrom(protoBuf$Effect0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$Effect1 != null) {
                    this.mergeFrom(protoBuf$Effect1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((Effect)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder setEffectType(EffectType protoBuf$Effect$EffectType0) {
                protoBuf$Effect$EffectType0.getClass();
                this.bitField0_ |= 1;
                this.effectType_ = protoBuf$Effect$EffectType0;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder setKind(InvocationKind protoBuf$Effect$InvocationKind0) {
                protoBuf$Effect$InvocationKind0.getClass();
                this.bitField0_ |= 8;
                this.kind_ = protoBuf$Effect$InvocationKind0;
                return this;
            }
        }

        public static enum EffectType implements EnumLite {
            RETURNS_CONSTANT(0, 0),
            CALLS(1, 1),
            RETURNS_NOT_NULL(2, 2);

            private static EnumLiteMap internalValueMap;
            private final int value;

            static {
                EffectType.internalValueMap = new EnumLiteMap() {
                    public EffectType findValueByNumber(int v) {
                        return EffectType.valueOf(v);
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                    public EnumLite findValueByNumber(int v) {
                        return this.findValueByNumber(v);
                    }
                };
            }

            private EffectType(int v1, int v2) {
                this.value = v2;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static EffectType valueOf(int v) {
                switch(v) {
                    case 0: {
                        return EffectType.RETURNS_CONSTANT;
                    }
                    case 1: {
                        return EffectType.CALLS;
                    }
                    case 2: {
                        return EffectType.RETURNS_NOT_NULL;
                    }
                    default: {
                        return null;
                    }
                }
            }
        }

        public static enum InvocationKind implements EnumLite {
            AT_MOST_ONCE(0, 0),
            EXACTLY_ONCE(1, 1),
            AT_LEAST_ONCE(2, 2);

            private static EnumLiteMap internalValueMap;
            private final int value;

            static {
                InvocationKind.internalValueMap = new EnumLiteMap() {
                    public InvocationKind findValueByNumber(int v) {
                        return InvocationKind.valueOf(v);
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                    public EnumLite findValueByNumber(int v) {
                        return this.findValueByNumber(v);
                    }
                };
            }

            private InvocationKind(int v1, int v2) {
                this.value = v2;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static InvocationKind valueOf(int v) {
                switch(v) {
                    case 0: {
                        return InvocationKind.AT_MOST_ONCE;
                    }
                    case 1: {
                        return InvocationKind.EXACTLY_ONCE;
                    }
                    case 2: {
                        return InvocationKind.AT_LEAST_ONCE;
                    }
                    default: {
                        return null;
                    }
                }
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private Expression conclusionOfConditionalEffect_;
        private static final Effect defaultInstance;
        private List effectConstructorArgument_;
        private EffectType effectType_;
        private InvocationKind kind_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final ByteString unknownFields;

        static {
            Effect.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public Effect parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new Effect(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            Effect protoBuf$Effect0 = new Effect(true);
            Effect.defaultInstance = protoBuf$Effect0;
            protoBuf$Effect0.initFields();
        }

        private Effect(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            int v = 0;
            boolean z = false;
            while(!z) {
                try {
                    try {
                        int v1 = codedInputStream0.readTag();
                        switch(v1) {
                            case 0: {
                                break;
                            }
                            case 8: {
                                int v3 = codedInputStream0.readEnum();
                                EffectType protoBuf$Effect$EffectType0 = EffectType.valueOf(v3);
                                if(protoBuf$Effect$EffectType0 == null) {
                                    codedOutputStream0.writeRawVarint32(8);
                                    codedOutputStream0.writeRawVarint32(v3);
                                }
                                else {
                                    this.bitField0_ |= 1;
                                    this.effectType_ = protoBuf$Effect$EffectType0;
                                }
                                continue;
                            }
                            case 18: {
                                if((v & 2) != 2) {
                                    this.effectConstructorArgument_ = new ArrayList();
                                    v = 2;
                                }
                                this.effectConstructorArgument_.add(codedInputStream0.readMessage(Expression.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 26: {
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder protoBuf$Expression$Builder0 = (this.bitField0_ & 2) == 2 ? this.conclusionOfConditionalEffect_.toBuilder() : null;
                                Expression protoBuf$Expression0 = (Expression)codedInputStream0.readMessage(Expression.PARSER, extensionRegistryLite0);
                                this.conclusionOfConditionalEffect_ = protoBuf$Expression0;
                                if(protoBuf$Expression$Builder0 != null) {
                                    protoBuf$Expression$Builder0.mergeFrom(protoBuf$Expression0);
                                    this.conclusionOfConditionalEffect_ = protoBuf$Expression$Builder0.buildPartial();
                                }
                                this.bitField0_ |= 2;
                                continue;
                            }
                            case 0x20: {
                                int v2 = codedInputStream0.readEnum();
                                InvocationKind protoBuf$Effect$InvocationKind0 = InvocationKind.valueOf(v2);
                                if(protoBuf$Effect$InvocationKind0 == null) {
                                    codedOutputStream0.writeRawVarint32(0x20);
                                    codedOutputStream0.writeRawVarint32(v2);
                                }
                                else {
                                    this.bitField0_ |= 4;
                                    this.kind_ = protoBuf$Effect$InvocationKind0;
                                }
                                continue;
                            }
                            default: {
                                if(this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                    continue;
                                }
                            }
                        }
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if((v & 2) == 2) {
                        this.effectConstructorArgument_ = Collections.unmodifiableList(this.effectConstructorArgument_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    throw throwable0;
                }
                z = true;
            }
            if((v & 2) == 2) {
                this.effectConstructorArgument_ = Collections.unmodifiableList(this.effectConstructorArgument_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
        }

        Effect(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private Effect(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        Effect(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private Effect(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public Expression getConclusionOfConditionalEffect() {
            return this.conclusionOfConditionalEffect_;
        }

        public static Effect getDefaultInstance() {
            return Effect.defaultInstance;
        }

        public Effect getDefaultInstanceForType() {
            return Effect.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public Expression getEffectConstructorArgument(int v) {
            return (Expression)this.effectConstructorArgument_.get(v);
        }

        public int getEffectConstructorArgumentCount() {
            return this.effectConstructorArgument_.size();
        }

        public EffectType getEffectType() {
            return this.effectType_;
        }

        public InvocationKind getKind() {
            return this.kind_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return Effect.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v1 = this.memoizedSerializedSize;
            if(v1 != -1) {
                return v1;
            }
            int v2 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeEnumSize(1, this.effectType_.getNumber()) : 0;
            for(int v = 0; v < this.effectConstructorArgument_.size(); ++v) {
                v2 += CodedOutputStream.computeMessageSize(2, ((MessageLite)this.effectConstructorArgument_.get(v)));
            }
            if((this.bitField0_ & 2) == 2) {
                v2 += CodedOutputStream.computeMessageSize(3, this.conclusionOfConditionalEffect_);
            }
            if((this.bitField0_ & 4) == 4) {
                v2 += CodedOutputStream.computeEnumSize(4, this.kind_.getNumber());
            }
            int v3 = v2 + this.unknownFields.size();
            this.memoizedSerializedSize = v3;
            return v3;
        }

        public boolean hasConclusionOfConditionalEffect() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasEffectType() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasKind() {
            return (this.bitField0_ & 4) == 4;
        }

        private void initFields() {
            this.effectType_ = EffectType.RETURNS_CONSTANT;
            this.effectConstructorArgument_ = Collections.EMPTY_LIST;
            this.conclusionOfConditionalEffect_ = Expression.getDefaultInstance();
            this.kind_ = InvocationKind.AT_MOST_ONCE;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            for(int v1 = 0; v1 < this.getEffectConstructorArgumentCount(); ++v1) {
                if(!this.getEffectConstructorArgument(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(this.hasConclusionOfConditionalEffect() && !this.getConclusionOfConditionalEffect().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder.access$24000();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder newBuilder(Effect protoBuf$Effect0) {
            return Effect.newBuilder().mergeFrom(protoBuf$Effect0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder newBuilderForType() {
            return Effect.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.Builder toBuilder() {
            return Effect.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeEnum(1, this.effectType_.getNumber());
            }
            for(int v = 0; v < this.effectConstructorArgument_.size(); ++v) {
                codedOutputStream0.writeMessage(2, ((MessageLite)this.effectConstructorArgument_.get(v)));
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeMessage(3, this.conclusionOfConditionalEffect_);
            }
            if((this.bitField0_ & 4) == 4) {
                codedOutputStream0.writeEnum(4, this.kind_.getNumber());
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class EnumEntry extends ExtendableMessage implements ProtoBuf.EnumEntryOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder extends ExtendableBuilder implements ProtoBuf.EnumEntryOrBuilder {
            private int bitField0_;
            private int name_;

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder access$20400() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder.create();
            }

            public EnumEntry build() {
                EnumEntry protoBuf$EnumEntry0 = this.buildPartial();
                if(!protoBuf$EnumEntry0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder.newUninitializedMessageException(protoBuf$EnumEntry0);
                }
                return protoBuf$EnumEntry0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public EnumEntry buildPartial() {
                EnumEntry protoBuf$EnumEntry0 = new EnumEntry(this, null);
                int v = (this.bitField0_ & 1) == 1 ? 1 : 0;
                protoBuf$EnumEntry0.name_ = this.name_;
                protoBuf$EnumEntry0.bitField0_ = v;
                return protoBuf$EnumEntry0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public ExtendableBuilder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder();
            }

            public EnumEntry getDefaultInstanceForType() {
                return EnumEntry.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return this.extensionsAreInitialized();
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder mergeFrom(EnumEntry protoBuf$EnumEntry0) {
                if(protoBuf$EnumEntry0 == EnumEntry.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$EnumEntry0.hasName()) {
                    this.setName(protoBuf$EnumEntry0.getName());
                }
                this.mergeExtensionFields(protoBuf$EnumEntry0);
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$EnumEntry0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                EnumEntry protoBuf$EnumEntry1;
                EnumEntry protoBuf$EnumEntry0;
                try {
                    try {
                        protoBuf$EnumEntry0 = null;
                        protoBuf$EnumEntry1 = (EnumEntry)EnumEntry.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        EnumEntry protoBuf$EnumEntry2 = (EnumEntry)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$EnumEntry0 = protoBuf$EnumEntry2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$EnumEntry0 != null) {
                    this.mergeFrom(protoBuf$EnumEntry0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$EnumEntry1 != null) {
                    this.mergeFrom(protoBuf$EnumEntry1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((EnumEntry)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder setName(int v) {
                this.bitField0_ |= 1;
                this.name_ = v;
                return this;
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private static final EnumEntry defaultInstance;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int name_;
        private final ByteString unknownFields;

        static {
            EnumEntry.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public EnumEntry parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new EnumEntry(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            EnumEntry protoBuf$EnumEntry0 = new EnumEntry(true);
            EnumEntry.defaultInstance = protoBuf$EnumEntry0;
            protoBuf$EnumEntry0.initFields();
        }

        private EnumEntry(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            try {
                boolean z = false;
                while(!z) {
                    try {
                        try {
                            int v1 = codedInputStream0.readTag();
                            switch(v1) {
                                case 0: {
                                    z = true;
                                    continue;
                                }
                                case 8: {
                                    break;
                                }
                                default: {
                                    if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                        z = true;
                                        continue;
                                    }
                                    continue;
                                }
                            }
                            this.bitField0_ |= 1;
                            this.name_ = codedInputStream0.readInt32();
                            continue;
                        }
                        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        }
                        catch(IOException iOException0) {
                            throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                        }
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(Throwable throwable0) {
                        try {
                            codedOutputStream0.flush();
                        }
                        catch(IOException throwable1) {
                            throw throwable1;
                        }
                        catch(Throwable unused_ex) {
                        }
                        this.makeExtensionsImmutable();
                        throw throwable0;
                    }
                }
                try {
                    codedOutputStream0.flush();
                }
                catch(IOException unused_ex) {
                }
            }
            finally {
                this.unknownFields = byteString$Output0.toByteString();
            }
            this.makeExtensionsImmutable();
        }

        EnumEntry(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private EnumEntry(ExtendableBuilder generatedMessageLite$ExtendableBuilder0) {
            super(generatedMessageLite$ExtendableBuilder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$ExtendableBuilder0.getUnknownFields();
        }

        EnumEntry(ExtendableBuilder generatedMessageLite$ExtendableBuilder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$ExtendableBuilder0);
        }

        private EnumEntry(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static EnumEntry getDefaultInstance() {
            return EnumEntry.defaultInstance;
        }

        public EnumEntry getDefaultInstanceForType() {
            return EnumEntry.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getName() {
            return this.name_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return EnumEntry.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v1 = ((this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.name_) : 0) + this.extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = v1;
            return v1;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        private void initFields() {
            this.name_ = 0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            if(!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder.access$20400();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder newBuilder(EnumEntry protoBuf$EnumEntry0) {
            return EnumEntry.newBuilder().mergeFrom(protoBuf$EnumEntry0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder newBuilderForType() {
            return EnumEntry.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.Builder toBuilder() {
            return EnumEntry.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            ExtensionWriter generatedMessageLite$ExtendableMessage$ExtensionWriter0 = this.newExtensionWriter();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(1, this.name_);
            }
            generatedMessageLite$ExtendableMessage$ExtensionWriter0.writeUntil(200, codedOutputStream0);
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class Expression extends GeneratedMessageLite implements ProtoBuf.ExpressionOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.ExpressionOrBuilder {
            private List andArgument_;
            private int bitField0_;
            private ConstantValue constantValue_;
            private int flags_;
            private int isInstanceTypeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type isInstanceType_;
            private List orArgument_;
            private int valueParameterReference_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder() {
                this.constantValue_ = ConstantValue.TRUE;
                this.isInstanceType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                this.andArgument_ = Collections.EMPTY_LIST;
                this.orArgument_ = Collections.EMPTY_LIST;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder access$24900() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder.create();
            }

            public Expression build() {
                Expression protoBuf$Expression0 = this.buildPartial();
                if(!protoBuf$Expression0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder.newUninitializedMessageException(protoBuf$Expression0);
                }
                return protoBuf$Expression0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public Expression buildPartial() {
                Expression protoBuf$Expression0 = new Expression(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                protoBuf$Expression0.flags_ = this.flags_;
                if((v & 2) == 2) {
                    v1 |= 2;
                }
                protoBuf$Expression0.valueParameterReference_ = this.valueParameterReference_;
                if((v & 4) == 4) {
                    v1 |= 4;
                }
                protoBuf$Expression0.constantValue_ = this.constantValue_;
                if((v & 8) == 8) {
                    v1 |= 8;
                }
                protoBuf$Expression0.isInstanceType_ = this.isInstanceType_;
                if((v & 16) == 16) {
                    v1 |= 16;
                }
                protoBuf$Expression0.isInstanceTypeId_ = this.isInstanceTypeId_;
                if((this.bitField0_ & 0x20) == 0x20) {
                    this.andArgument_ = Collections.unmodifiableList(this.andArgument_);
                    this.bitField0_ &= -33;
                }
                protoBuf$Expression0.andArgument_ = this.andArgument_;
                if((this.bitField0_ & 0x40) == 0x40) {
                    this.orArgument_ = Collections.unmodifiableList(this.orArgument_);
                    this.bitField0_ &= -65;
                }
                protoBuf$Expression0.orArgument_ = this.orArgument_;
                protoBuf$Expression0.bitField0_ = v1;
                return protoBuf$Expression0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder();
            }

            private void ensureAndArgumentIsMutable() {
                if((this.bitField0_ & 0x20) != 0x20) {
                    this.andArgument_ = new ArrayList(this.andArgument_);
                    this.bitField0_ |= 0x20;
                }
            }

            private void ensureOrArgumentIsMutable() {
                if((this.bitField0_ & 0x40) != 0x40) {
                    this.orArgument_ = new ArrayList(this.orArgument_);
                    this.bitField0_ |= 0x40;
                }
            }

            public Expression getAndArgument(int v) {
                return (Expression)this.andArgument_.get(v);
            }

            public int getAndArgumentCount() {
                return this.andArgument_.size();
            }

            public Expression getDefaultInstanceForType() {
                return Expression.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getIsInstanceType() {
                return this.isInstanceType_;
            }

            public Expression getOrArgument(int v) {
                return (Expression)this.orArgument_.get(v);
            }

            public int getOrArgumentCount() {
                return this.orArgument_.size();
            }

            public boolean hasIsInstanceType() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if(this.hasIsInstanceType() && !this.getIsInstanceType().isInitialized()) {
                    return false;
                }
                for(int v = 0; v < this.getAndArgumentCount(); ++v) {
                    if(!this.getAndArgument(v).isInitialized()) {
                        return false;
                    }
                }
                for(int v1 = 0; v1 < this.getOrArgumentCount(); ++v1) {
                    if(!this.getOrArgument(v1).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder mergeFrom(Expression protoBuf$Expression0) {
                if(protoBuf$Expression0 == Expression.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$Expression0.hasFlags()) {
                    this.setFlags(protoBuf$Expression0.getFlags());
                }
                if(protoBuf$Expression0.hasValueParameterReference()) {
                    this.setValueParameterReference(protoBuf$Expression0.getValueParameterReference());
                }
                if(protoBuf$Expression0.hasConstantValue()) {
                    this.setConstantValue(protoBuf$Expression0.getConstantValue());
                }
                if(protoBuf$Expression0.hasIsInstanceType()) {
                    this.mergeIsInstanceType(protoBuf$Expression0.getIsInstanceType());
                }
                if(protoBuf$Expression0.hasIsInstanceTypeId()) {
                    this.setIsInstanceTypeId(protoBuf$Expression0.getIsInstanceTypeId());
                }
                if(!protoBuf$Expression0.andArgument_.isEmpty()) {
                    if(this.andArgument_.isEmpty()) {
                        this.andArgument_ = protoBuf$Expression0.andArgument_;
                        this.bitField0_ &= -33;
                    }
                    else {
                        this.ensureAndArgumentIsMutable();
                        this.andArgument_.addAll(protoBuf$Expression0.andArgument_);
                    }
                }
                if(!protoBuf$Expression0.orArgument_.isEmpty()) {
                    if(this.orArgument_.isEmpty()) {
                        this.orArgument_ = protoBuf$Expression0.orArgument_;
                        this.bitField0_ &= -65;
                    }
                    else {
                        this.ensureOrArgumentIsMutable();
                        this.orArgument_.addAll(protoBuf$Expression0.orArgument_);
                    }
                }
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Expression0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                Expression protoBuf$Expression1;
                Expression protoBuf$Expression0;
                try {
                    try {
                        protoBuf$Expression0 = null;
                        protoBuf$Expression1 = (Expression)Expression.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        Expression protoBuf$Expression2 = (Expression)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$Expression0 = protoBuf$Expression2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$Expression0 != null) {
                    this.mergeFrom(protoBuf$Expression0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$Expression1 != null) {
                    this.mergeFrom(protoBuf$Expression1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((Expression)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder mergeIsInstanceType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.isInstanceType_ = (this.bitField0_ & 8) != 8 || this.isInstanceType_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.isInstanceType_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 8;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder setConstantValue(ConstantValue protoBuf$Expression$ConstantValue0) {
                protoBuf$Expression$ConstantValue0.getClass();
                this.bitField0_ |= 4;
                this.constantValue_ = protoBuf$Expression$ConstantValue0;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder setFlags(int v) {
                this.bitField0_ |= 1;
                this.flags_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder setIsInstanceTypeId(int v) {
                this.bitField0_ |= 16;
                this.isInstanceTypeId_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder setValueParameterReference(int v) {
                this.bitField0_ |= 2;
                this.valueParameterReference_ = v;
                return this;
            }
        }

        public static enum ConstantValue implements EnumLite {
            TRUE(0, 0),
            FALSE(1, 1),
            NULL(2, 2);

            private static EnumLiteMap internalValueMap;
            private final int value;

            static {
                ConstantValue.internalValueMap = new EnumLiteMap() {
                    public ConstantValue findValueByNumber(int v) {
                        return ConstantValue.valueOf(v);
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                    public EnumLite findValueByNumber(int v) {
                        return this.findValueByNumber(v);
                    }
                };
            }

            private ConstantValue(int v1, int v2) {
                this.value = v2;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static ConstantValue valueOf(int v) {
                switch(v) {
                    case 0: {
                        return ConstantValue.TRUE;
                    }
                    case 1: {
                        return ConstantValue.FALSE;
                    }
                    case 2: {
                        return ConstantValue.NULL;
                    }
                    default: {
                        return null;
                    }
                }
            }
        }

        public static Parser PARSER;
        private List andArgument_;
        private int bitField0_;
        private ConstantValue constantValue_;
        private static final Expression defaultInstance;
        private int flags_;
        private int isInstanceTypeId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type isInstanceType_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List orArgument_;
        private final ByteString unknownFields;
        private int valueParameterReference_;

        static {
            Expression.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public Expression parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new Expression(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            Expression protoBuf$Expression0 = new Expression(true);
            Expression.defaultInstance = protoBuf$Expression0;
            protoBuf$Expression0.initFields();
        }

        private Expression(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            int v = 0;
            boolean z = false;
            while(!z) {
                try {
                    try {
                        int v1 = codedInputStream0.readTag();
                        switch(v1) {
                            case 0: {
                                break;
                            }
                            case 8: {
                                this.bitField0_ |= 1;
                                this.flags_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 16: {
                                this.bitField0_ |= 2;
                                this.valueParameterReference_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 24: {
                                int v2 = codedInputStream0.readEnum();
                                ConstantValue protoBuf$Expression$ConstantValue0 = ConstantValue.valueOf(v2);
                                if(protoBuf$Expression$ConstantValue0 == null) {
                                    codedOutputStream0.writeRawVarint32(24);
                                    codedOutputStream0.writeRawVarint32(v2);
                                }
                                else {
                                    this.bitField0_ |= 4;
                                    this.constantValue_ = protoBuf$Expression$ConstantValue0;
                                }
                                continue;
                            }
                            case 34: {
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder protoBuf$Type$Builder0 = (this.bitField0_ & 8) == 8 ? this.isInstanceType_.toBuilder() : null;
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                this.isInstanceType_ = protoBuf$Type0;
                                if(protoBuf$Type$Builder0 != null) {
                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type0);
                                    this.isInstanceType_ = protoBuf$Type$Builder0.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                continue;
                            }
                            case 40: {
                                this.bitField0_ |= 16;
                                this.isInstanceTypeId_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 50: {
                                if((v & 0x20) != 0x20) {
                                    this.andArgument_ = new ArrayList();
                                    v |= 0x20;
                                }
                                this.andArgument_.add(codedInputStream0.readMessage(Expression.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 58: {
                                if((v & 0x40) != 0x40) {
                                    this.orArgument_ = new ArrayList();
                                    v |= 0x40;
                                }
                                this.orArgument_.add(codedInputStream0.readMessage(Expression.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            default: {
                                if(this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                    continue;
                                }
                            }
                        }
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if((v & 0x20) == 0x20) {
                        this.andArgument_ = Collections.unmodifiableList(this.andArgument_);
                    }
                    if((v & 0x40) == 0x40) {
                        this.orArgument_ = Collections.unmodifiableList(this.orArgument_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    throw throwable0;
                }
                z = true;
            }
            if((v & 0x20) == 0x20) {
                this.andArgument_ = Collections.unmodifiableList(this.andArgument_);
            }
            if((v & 0x40) == 0x40) {
                this.orArgument_ = Collections.unmodifiableList(this.orArgument_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
        }

        Expression(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private Expression(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        Expression(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private Expression(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public Expression getAndArgument(int v) {
            return (Expression)this.andArgument_.get(v);
        }

        public int getAndArgumentCount() {
            return this.andArgument_.size();
        }

        public ConstantValue getConstantValue() {
            return this.constantValue_;
        }

        public static Expression getDefaultInstance() {
            return Expression.defaultInstance;
        }

        public Expression getDefaultInstanceForType() {
            return Expression.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getFlags() {
            return this.flags_;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getIsInstanceType() {
            return this.isInstanceType_;
        }

        public int getIsInstanceTypeId() {
            return this.isInstanceTypeId_;
        }

        public Expression getOrArgument(int v) {
            return (Expression)this.orArgument_.get(v);
        }

        public int getOrArgumentCount() {
            return this.orArgument_.size();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return Expression.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v1 = this.memoizedSerializedSize;
            if(v1 != -1) {
                return v1;
            }
            int v2 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.flags_) : 0;
            if((this.bitField0_ & 2) == 2) {
                v2 += CodedOutputStream.computeInt32Size(2, this.valueParameterReference_);
            }
            if((this.bitField0_ & 4) == 4) {
                v2 += CodedOutputStream.computeEnumSize(3, this.constantValue_.getNumber());
            }
            if((this.bitField0_ & 8) == 8) {
                v2 += CodedOutputStream.computeMessageSize(4, this.isInstanceType_);
            }
            if((this.bitField0_ & 16) == 16) {
                v2 += CodedOutputStream.computeInt32Size(5, this.isInstanceTypeId_);
            }
            for(int v3 = 0; v3 < this.andArgument_.size(); ++v3) {
                v2 += CodedOutputStream.computeMessageSize(6, ((MessageLite)this.andArgument_.get(v3)));
            }
            for(int v = 0; v < this.orArgument_.size(); ++v) {
                v2 += CodedOutputStream.computeMessageSize(7, ((MessageLite)this.orArgument_.get(v)));
            }
            int v4 = v2 + this.unknownFields.size();
            this.memoizedSerializedSize = v4;
            return v4;
        }

        public int getValueParameterReference() {
            return this.valueParameterReference_;
        }

        public boolean hasConstantValue() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasIsInstanceType() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasIsInstanceTypeId() {
            return (this.bitField0_ & 16) == 16;
        }

        public boolean hasValueParameterReference() {
            return (this.bitField0_ & 2) == 2;
        }

        private void initFields() {
            this.flags_ = 0;
            this.valueParameterReference_ = 0;
            this.constantValue_ = ConstantValue.TRUE;
            this.isInstanceType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.isInstanceTypeId_ = 0;
            this.andArgument_ = Collections.EMPTY_LIST;
            this.orArgument_ = Collections.EMPTY_LIST;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            if(this.hasIsInstanceType() && !this.getIsInstanceType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for(int v1 = 0; v1 < this.getAndArgumentCount(); ++v1) {
                if(!this.getAndArgument(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for(int v2 = 0; v2 < this.getOrArgumentCount(); ++v2) {
                if(!this.getOrArgument(v2).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder.access$24900();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder newBuilder(Expression protoBuf$Expression0) {
            return Expression.newBuilder().mergeFrom(protoBuf$Expression0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder newBuilderForType() {
            return Expression.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.Builder toBuilder() {
            return Expression.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(1, this.flags_);
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeInt32(2, this.valueParameterReference_);
            }
            if((this.bitField0_ & 4) == 4) {
                codedOutputStream0.writeEnum(3, this.constantValue_.getNumber());
            }
            if((this.bitField0_ & 8) == 8) {
                codedOutputStream0.writeMessage(4, this.isInstanceType_);
            }
            if((this.bitField0_ & 16) == 16) {
                codedOutputStream0.writeInt32(5, this.isInstanceTypeId_);
            }
            for(int v1 = 0; v1 < this.andArgument_.size(); ++v1) {
                codedOutputStream0.writeMessage(6, ((MessageLite)this.andArgument_.get(v1)));
            }
            for(int v = 0; v < this.orArgument_.size(); ++v) {
                codedOutputStream0.writeMessage(7, ((MessageLite)this.orArgument_.get(v)));
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class Function extends ExtendableMessage implements ProtoBuf.FunctionOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder extends ExtendableBuilder implements ProtoBuf.FunctionOrBuilder {
            private int bitField0_;
            private List contextReceiverTypeId_;
            private List contextReceiverType_;
            private Contract contract_;
            private int flags_;
            private int name_;
            private int oldFlags_;
            private int receiverTypeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type receiverType_;
            private int returnTypeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type returnType_;
            private List typeParameter_;
            private TypeTable typeTable_;
            private List valueParameter_;
            private List versionRequirement_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder() {
                this.flags_ = 6;
                this.oldFlags_ = 6;
                this.returnType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                this.typeParameter_ = Collections.EMPTY_LIST;
                this.receiverType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                this.contextReceiverType_ = Collections.EMPTY_LIST;
                this.contextReceiverTypeId_ = Collections.EMPTY_LIST;
                this.valueParameter_ = Collections.EMPTY_LIST;
                this.typeTable_ = TypeTable.getDefaultInstance();
                this.versionRequirement_ = Collections.EMPTY_LIST;
                this.contract_ = Contract.getDefaultInstance();
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder access$14100() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder.create();
            }

            public Function build() {
                Function protoBuf$Function0 = this.buildPartial();
                if(!protoBuf$Function0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder.newUninitializedMessageException(protoBuf$Function0);
                }
                return protoBuf$Function0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public Function buildPartial() {
                Function protoBuf$Function0 = new Function(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                protoBuf$Function0.flags_ = this.flags_;
                if((v & 2) == 2) {
                    v1 |= 2;
                }
                protoBuf$Function0.oldFlags_ = this.oldFlags_;
                if((v & 4) == 4) {
                    v1 |= 4;
                }
                protoBuf$Function0.name_ = this.name_;
                if((v & 8) == 8) {
                    v1 |= 8;
                }
                protoBuf$Function0.returnType_ = this.returnType_;
                if((v & 16) == 16) {
                    v1 |= 16;
                }
                protoBuf$Function0.returnTypeId_ = this.returnTypeId_;
                if((this.bitField0_ & 0x20) == 0x20) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    this.bitField0_ &= -33;
                }
                protoBuf$Function0.typeParameter_ = this.typeParameter_;
                if((v & 0x40) == 0x40) {
                    v1 |= 0x20;
                }
                protoBuf$Function0.receiverType_ = this.receiverType_;
                if((v & 0x80) == 0x80) {
                    v1 |= 0x40;
                }
                protoBuf$Function0.receiverTypeId_ = this.receiverTypeId_;
                if((this.bitField0_ & 0x100) == 0x100) {
                    this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
                    this.bitField0_ &= 0xFFFFFEFF;
                }
                protoBuf$Function0.contextReceiverType_ = this.contextReceiverType_;
                if((this.bitField0_ & 0x200) == 0x200) {
                    this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
                    this.bitField0_ &= 0xFFFFFDFF;
                }
                protoBuf$Function0.contextReceiverTypeId_ = this.contextReceiverTypeId_;
                if((this.bitField0_ & 0x400) == 0x400) {
                    this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                    this.bitField0_ &= 0xFFFFFBFF;
                }
                protoBuf$Function0.valueParameter_ = this.valueParameter_;
                if((v & 0x800) == 0x800) {
                    v1 |= 0x80;
                }
                protoBuf$Function0.typeTable_ = this.typeTable_;
                if((this.bitField0_ & 0x1000) == 0x1000) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= 0xFFFFEFFF;
                }
                protoBuf$Function0.versionRequirement_ = this.versionRequirement_;
                if((v & 0x2000) == 0x2000) {
                    v1 |= 0x100;
                }
                protoBuf$Function0.contract_ = this.contract_;
                protoBuf$Function0.bitField0_ = v1;
                return protoBuf$Function0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public ExtendableBuilder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder();
            }

            private void ensureContextReceiverTypeIdIsMutable() {
                if((this.bitField0_ & 0x200) != 0x200) {
                    this.contextReceiverTypeId_ = new ArrayList(this.contextReceiverTypeId_);
                    this.bitField0_ |= 0x200;
                }
            }

            private void ensureContextReceiverTypeIsMutable() {
                if((this.bitField0_ & 0x100) != 0x100) {
                    this.contextReceiverType_ = new ArrayList(this.contextReceiverType_);
                    this.bitField0_ |= 0x100;
                }
            }

            private void ensureTypeParameterIsMutable() {
                if((this.bitField0_ & 0x20) != 0x20) {
                    this.typeParameter_ = new ArrayList(this.typeParameter_);
                    this.bitField0_ |= 0x20;
                }
            }

            private void ensureValueParameterIsMutable() {
                if((this.bitField0_ & 0x400) != 0x400) {
                    this.valueParameter_ = new ArrayList(this.valueParameter_);
                    this.bitField0_ |= 0x400;
                }
            }

            private void ensureVersionRequirementIsMutable() {
                if((this.bitField0_ & 0x1000) != 0x1000) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 0x1000;
                }
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getContextReceiverType(int v) {
                return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.contextReceiverType_.get(v);
            }

            public int getContextReceiverTypeCount() {
                return this.contextReceiverType_.size();
            }

            public Contract getContract() {
                return this.contract_;
            }

            public Function getDefaultInstanceForType() {
                return Function.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getReceiverType() {
                return this.receiverType_;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getReturnType() {
                return this.returnType_;
            }

            public TypeParameter getTypeParameter(int v) {
                return (TypeParameter)this.typeParameter_.get(v);
            }

            public int getTypeParameterCount() {
                return this.typeParameter_.size();
            }

            public TypeTable getTypeTable() {
                return this.typeTable_;
            }

            public ValueParameter getValueParameter(int v) {
                return (ValueParameter)this.valueParameter_.get(v);
            }

            public int getValueParameterCount() {
                return this.valueParameter_.size();
            }

            public boolean hasContract() {
                return (this.bitField0_ & 0x2000) == 0x2000;
            }

            public boolean hasName() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean hasReceiverType() {
                return (this.bitField0_ & 0x40) == 0x40;
            }

            public boolean hasReturnType() {
                return (this.bitField0_ & 8) == 8;
            }

            public boolean hasTypeTable() {
                return (this.bitField0_ & 0x800) == 0x800;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if(!this.hasName()) {
                    return false;
                }
                if(this.hasReturnType() && !this.getReturnType().isInitialized()) {
                    return false;
                }
                for(int v = 0; v < this.getTypeParameterCount(); ++v) {
                    if(!this.getTypeParameter(v).isInitialized()) {
                        return false;
                    }
                }
                if(this.hasReceiverType() && !this.getReceiverType().isInitialized()) {
                    return false;
                }
                for(int v1 = 0; v1 < this.getContextReceiverTypeCount(); ++v1) {
                    if(!this.getContextReceiverType(v1).isInitialized()) {
                        return false;
                    }
                }
                for(int v2 = 0; v2 < this.getValueParameterCount(); ++v2) {
                    if(!this.getValueParameter(v2).isInitialized()) {
                        return false;
                    }
                }
                if(this.hasTypeTable() && !this.getTypeTable().isInitialized()) {
                    return false;
                }
                return !this.hasContract() || this.getContract().isInitialized() ? this.extensionsAreInitialized() : false;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder mergeContract(Contract protoBuf$Contract0) {
                this.contract_ = (this.bitField0_ & 0x2000) != 0x2000 || this.contract_ == Contract.getDefaultInstance() ? protoBuf$Contract0 : Contract.newBuilder(this.contract_).mergeFrom(protoBuf$Contract0).buildPartial();
                this.bitField0_ |= 0x2000;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder mergeFrom(Function protoBuf$Function0) {
                if(protoBuf$Function0 == Function.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$Function0.hasFlags()) {
                    this.setFlags(protoBuf$Function0.getFlags());
                }
                if(protoBuf$Function0.hasOldFlags()) {
                    this.setOldFlags(protoBuf$Function0.getOldFlags());
                }
                if(protoBuf$Function0.hasName()) {
                    this.setName(protoBuf$Function0.getName());
                }
                if(protoBuf$Function0.hasReturnType()) {
                    this.mergeReturnType(protoBuf$Function0.getReturnType());
                }
                if(protoBuf$Function0.hasReturnTypeId()) {
                    this.setReturnTypeId(protoBuf$Function0.getReturnTypeId());
                }
                if(!protoBuf$Function0.typeParameter_.isEmpty()) {
                    if(this.typeParameter_.isEmpty()) {
                        this.typeParameter_ = protoBuf$Function0.typeParameter_;
                        this.bitField0_ &= -33;
                    }
                    else {
                        this.ensureTypeParameterIsMutable();
                        this.typeParameter_.addAll(protoBuf$Function0.typeParameter_);
                    }
                }
                if(protoBuf$Function0.hasReceiverType()) {
                    this.mergeReceiverType(protoBuf$Function0.getReceiverType());
                }
                if(protoBuf$Function0.hasReceiverTypeId()) {
                    this.setReceiverTypeId(protoBuf$Function0.getReceiverTypeId());
                }
                if(!protoBuf$Function0.contextReceiverType_.isEmpty()) {
                    if(this.contextReceiverType_.isEmpty()) {
                        this.contextReceiverType_ = protoBuf$Function0.contextReceiverType_;
                        this.bitField0_ &= 0xFFFFFEFF;
                    }
                    else {
                        this.ensureContextReceiverTypeIsMutable();
                        this.contextReceiverType_.addAll(protoBuf$Function0.contextReceiverType_);
                    }
                }
                if(!protoBuf$Function0.contextReceiverTypeId_.isEmpty()) {
                    if(this.contextReceiverTypeId_.isEmpty()) {
                        this.contextReceiverTypeId_ = protoBuf$Function0.contextReceiverTypeId_;
                        this.bitField0_ &= 0xFFFFFDFF;
                    }
                    else {
                        this.ensureContextReceiverTypeIdIsMutable();
                        this.contextReceiverTypeId_.addAll(protoBuf$Function0.contextReceiverTypeId_);
                    }
                }
                if(!protoBuf$Function0.valueParameter_.isEmpty()) {
                    if(this.valueParameter_.isEmpty()) {
                        this.valueParameter_ = protoBuf$Function0.valueParameter_;
                        this.bitField0_ &= 0xFFFFFBFF;
                    }
                    else {
                        this.ensureValueParameterIsMutable();
                        this.valueParameter_.addAll(protoBuf$Function0.valueParameter_);
                    }
                }
                if(protoBuf$Function0.hasTypeTable()) {
                    this.mergeTypeTable(protoBuf$Function0.getTypeTable());
                }
                if(!protoBuf$Function0.versionRequirement_.isEmpty()) {
                    if(this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = protoBuf$Function0.versionRequirement_;
                        this.bitField0_ &= 0xFFFFEFFF;
                    }
                    else {
                        this.ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(protoBuf$Function0.versionRequirement_);
                    }
                }
                if(protoBuf$Function0.hasContract()) {
                    this.mergeContract(protoBuf$Function0.getContract());
                }
                this.mergeExtensionFields(protoBuf$Function0);
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Function0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                Function protoBuf$Function1;
                Function protoBuf$Function0;
                try {
                    try {
                        protoBuf$Function0 = null;
                        protoBuf$Function1 = (Function)Function.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        Function protoBuf$Function2 = (Function)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$Function0 = protoBuf$Function2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$Function0 != null) {
                    this.mergeFrom(protoBuf$Function0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$Function1 != null) {
                    this.mergeFrom(protoBuf$Function1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((Function)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder mergeReceiverType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.receiverType_ = (this.bitField0_ & 0x40) != 0x40 || this.receiverType_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.receiverType_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 0x40;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder mergeReturnType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.returnType_ = (this.bitField0_ & 8) != 8 || this.returnType_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.returnType_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 8;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder mergeTypeTable(TypeTable protoBuf$TypeTable0) {
                this.typeTable_ = (this.bitField0_ & 0x800) != 0x800 || this.typeTable_ == TypeTable.getDefaultInstance() ? protoBuf$TypeTable0 : TypeTable.newBuilder(this.typeTable_).mergeFrom(protoBuf$TypeTable0).buildPartial();
                this.bitField0_ |= 0x800;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder setFlags(int v) {
                this.bitField0_ |= 1;
                this.flags_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder setName(int v) {
                this.bitField0_ |= 4;
                this.name_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder setOldFlags(int v) {
                this.bitField0_ |= 2;
                this.oldFlags_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder setReceiverTypeId(int v) {
                this.bitField0_ |= 0x80;
                this.receiverTypeId_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder setReturnTypeId(int v) {
                this.bitField0_ |= 16;
                this.returnTypeId_ = v;
                return this;
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private int contextReceiverTypeIdMemoizedSerializedSize;
        private List contextReceiverTypeId_;
        private List contextReceiverType_;
        private Contract contract_;
        private static final Function defaultInstance;
        private int flags_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int name_;
        private int oldFlags_;
        private int receiverTypeId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type receiverType_;
        private int returnTypeId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type returnType_;
        private List typeParameter_;
        private TypeTable typeTable_;
        private final ByteString unknownFields;
        private List valueParameter_;
        private List versionRequirement_;

        static {
            Function.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public Function parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new Function(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            Function protoBuf$Function0 = new Function(true);
            Function.defaultInstance = protoBuf$Function0;
            protoBuf$Function0.initFields();
        }

        private Function(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.contextReceiverTypeIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            int v = 0;
            boolean z = false;
            while(!z) {
                try {
                    try {
                        int v1 = codedInputStream0.readTag();
                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder protoBuf$Type$Builder0 = null;
                        switch(v1) {
                            case 0: {
                            label_14:
                                z = true;
                                continue;
                            }
                            case 8: {
                                this.bitField0_ |= 2;
                                this.oldFlags_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 16: {
                                this.bitField0_ |= 4;
                                this.name_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 26: {
                                if((this.bitField0_ & 8) == 8) {
                                    protoBuf$Type$Builder0 = this.returnType_.toBuilder();
                                }
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                this.returnType_ = protoBuf$Type0;
                                if(protoBuf$Type$Builder0 != null) {
                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type0);
                                    this.returnType_ = protoBuf$Type$Builder0.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                continue;
                            }
                            case 34: {
                                if((v & 0x20) != 0x20) {
                                    this.typeParameter_ = new ArrayList();
                                    v |= 0x20;
                                }
                                this.typeParameter_.add(codedInputStream0.readMessage(TypeParameter.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 42: {
                                if((this.bitField0_ & 0x20) == 0x20) {
                                    protoBuf$Type$Builder0 = this.receiverType_.toBuilder();
                                }
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                this.receiverType_ = protoBuf$Type1;
                                if(protoBuf$Type$Builder0 != null) {
                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type1);
                                    this.receiverType_ = protoBuf$Type$Builder0.buildPartial();
                                }
                                this.bitField0_ |= 0x20;
                                continue;
                            }
                            case 50: {
                                if((v & 0x400) != 0x400) {
                                    this.valueParameter_ = new ArrayList();
                                    v |= 0x400;
                                }
                                this.valueParameter_.add(codedInputStream0.readMessage(ValueParameter.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 56: {
                                this.bitField0_ |= 16;
                                this.returnTypeId_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 0x40: {
                                this.bitField0_ |= 0x40;
                                this.receiverTypeId_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 72: {
                                this.bitField0_ |= 1;
                                this.flags_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 82: {
                                if((v & 0x100) != 0x100) {
                                    this.contextReceiverType_ = new ArrayList();
                                    v |= 0x100;
                                }
                                this.contextReceiverType_.add(codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 88: {
                                if((v & 0x200) != 0x200) {
                                    this.contextReceiverTypeId_ = new ArrayList();
                                    v |= 0x200;
                                }
                                this.contextReceiverTypeId_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 90: {
                                int v2 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 0x200) != 0x200 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.contextReceiverTypeId_ = new ArrayList();
                                    v |= 0x200;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.contextReceiverTypeId_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v2);
                                continue;
                            }
                            case 0xF2: {
                                if((this.bitField0_ & 0x80) == 0x80) {
                                    protoBuf$Type$Builder0 = this.typeTable_.toBuilder();
                                }
                                TypeTable protoBuf$TypeTable0 = (TypeTable)codedInputStream0.readMessage(TypeTable.PARSER, extensionRegistryLite0);
                                this.typeTable_ = protoBuf$TypeTable0;
                                if(protoBuf$Type$Builder0 != null) {
                                    ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder)protoBuf$Type$Builder0).mergeFrom(protoBuf$TypeTable0);
                                    this.typeTable_ = ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder)protoBuf$Type$Builder0).buildPartial();
                                }
                                this.bitField0_ |= 0x80;
                                continue;
                            }
                            case 0xF8: {
                                if((v & 0x1000) != 0x1000) {
                                    this.versionRequirement_ = new ArrayList();
                                    v |= 0x1000;
                                }
                                this.versionRequirement_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 0xFA: {
                                int v3 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 0x1000) != 0x1000 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_ = new ArrayList();
                                    v |= 0x1000;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v3);
                                continue;
                            }
                            case 0x102: {
                                if((this.bitField0_ & 0x100) == 0x100) {
                                    protoBuf$Type$Builder0 = this.contract_.toBuilder();
                                }
                                Contract protoBuf$Contract0 = (Contract)codedInputStream0.readMessage(Contract.PARSER, extensionRegistryLite0);
                                this.contract_ = protoBuf$Contract0;
                                if(protoBuf$Type$Builder0 != null) {
                                    ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder)protoBuf$Type$Builder0).mergeFrom(protoBuf$Contract0);
                                    this.contract_ = ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.Builder)protoBuf$Type$Builder0).buildPartial();
                                }
                                this.bitField0_ |= 0x100;
                                break;
                            }
                            default: {
                                if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                    goto label_14;
                                }
                            }
                        }
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if((v & 0x20) == 0x20) {
                        this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    }
                    if((v & 0x400) == 0x400) {
                        this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                    }
                    if((v & 0x100) == 0x100) {
                        this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
                    }
                    if((v & 0x200) == 0x200) {
                        this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
                    }
                    if((v & 0x1000) == 0x1000) {
                        this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    this.makeExtensionsImmutable();
                    throw throwable0;
                }
            }
            if((v & 0x20) == 0x20) {
                this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
            }
            if((v & 0x400) == 0x400) {
                this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
            }
            if((v & 0x100) == 0x100) {
                this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
            }
            if((v & 0x200) == 0x200) {
                this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
            }
            if((v & 0x1000) == 0x1000) {
                this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
            this.makeExtensionsImmutable();
        }

        Function(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private Function(ExtendableBuilder generatedMessageLite$ExtendableBuilder0) {
            super(generatedMessageLite$ExtendableBuilder0);
            this.contextReceiverTypeIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$ExtendableBuilder0.getUnknownFields();
        }

        Function(ExtendableBuilder generatedMessageLite$ExtendableBuilder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$ExtendableBuilder0);
        }

        private Function(boolean z) {
            this.contextReceiverTypeIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getContextReceiverType(int v) {
            return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.contextReceiverType_.get(v);
        }

        public int getContextReceiverTypeCount() {
            return this.contextReceiverType_.size();
        }

        public List getContextReceiverTypeIdList() {
            return this.contextReceiverTypeId_;
        }

        public List getContextReceiverTypeList() {
            return this.contextReceiverType_;
        }

        public Contract getContract() {
            return this.contract_;
        }

        public static Function getDefaultInstance() {
            return Function.defaultInstance;
        }

        public Function getDefaultInstanceForType() {
            return Function.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getFlags() {
            return this.flags_;
        }

        public int getName() {
            return this.name_;
        }

        public int getOldFlags() {
            return this.oldFlags_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return Function.PARSER;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getReceiverType() {
            return this.receiverType_;
        }

        public int getReceiverTypeId() {
            return this.receiverTypeId_;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getReturnType() {
            return this.returnType_;
        }

        public int getReturnTypeId() {
            return this.returnTypeId_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v1 = this.memoizedSerializedSize;
            if(v1 != -1) {
                return v1;
            }
            int v2 = (this.bitField0_ & 2) == 2 ? CodedOutputStream.computeInt32Size(1, this.oldFlags_) : 0;
            if((this.bitField0_ & 4) == 4) {
                v2 += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            if((this.bitField0_ & 8) == 8) {
                v2 += CodedOutputStream.computeMessageSize(3, this.returnType_);
            }
            for(int v3 = 0; v3 < this.typeParameter_.size(); ++v3) {
                v2 += CodedOutputStream.computeMessageSize(4, ((MessageLite)this.typeParameter_.get(v3)));
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                v2 += CodedOutputStream.computeMessageSize(5, this.receiverType_);
            }
            for(int v4 = 0; v4 < this.valueParameter_.size(); ++v4) {
                v2 += CodedOutputStream.computeMessageSize(6, ((MessageLite)this.valueParameter_.get(v4)));
            }
            if((this.bitField0_ & 16) == 16) {
                v2 += CodedOutputStream.computeInt32Size(7, this.returnTypeId_);
            }
            if((this.bitField0_ & 0x40) == 0x40) {
                v2 += CodedOutputStream.computeInt32Size(8, this.receiverTypeId_);
            }
            if((this.bitField0_ & 1) == 1) {
                v2 += CodedOutputStream.computeInt32Size(9, this.flags_);
            }
            for(int v5 = 0; v5 < this.contextReceiverType_.size(); ++v5) {
                v2 += CodedOutputStream.computeMessageSize(10, ((MessageLite)this.contextReceiverType_.get(v5)));
            }
            int v7 = 0;
            for(int v6 = 0; v6 < this.contextReceiverTypeId_.size(); ++v6) {
                v7 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.contextReceiverTypeId_.get(v6)))));
            }
            int v8 = this.getContextReceiverTypeIdList().isEmpty() ? v2 + v7 : v2 + v7 + 1 + CodedOutputStream.computeInt32SizeNoTag(v7);
            this.contextReceiverTypeIdMemoizedSerializedSize = v7;
            if((this.bitField0_ & 0x80) == 0x80) {
                v8 += CodedOutputStream.computeMessageSize(30, this.typeTable_);
            }
            int v9 = 0;
            for(int v = 0; v < this.versionRequirement_.size(); ++v) {
                v9 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.versionRequirement_.get(v)))));
            }
            int v10 = v8 + v9 + this.getVersionRequirementList().size() * 2;
            if((this.bitField0_ & 0x100) == 0x100) {
                v10 += CodedOutputStream.computeMessageSize(0x20, this.contract_);
            }
            int v11 = v10 + this.extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = v11;
            return v11;
        }

        public TypeParameter getTypeParameter(int v) {
            return (TypeParameter)this.typeParameter_.get(v);
        }

        public int getTypeParameterCount() {
            return this.typeParameter_.size();
        }

        public List getTypeParameterList() {
            return this.typeParameter_;
        }

        public TypeTable getTypeTable() {
            return this.typeTable_;
        }

        public ValueParameter getValueParameter(int v) {
            return (ValueParameter)this.valueParameter_.get(v);
        }

        public int getValueParameterCount() {
            return this.valueParameter_.size();
        }

        public List getValueParameterList() {
            return this.valueParameter_;
        }

        public List getVersionRequirementList() {
            return this.versionRequirement_;
        }

        public boolean hasContract() {
            return (this.bitField0_ & 0x100) == 0x100;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasName() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasOldFlags() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasReceiverType() {
            return (this.bitField0_ & 0x20) == 0x20;
        }

        public boolean hasReceiverTypeId() {
            return (this.bitField0_ & 0x40) == 0x40;
        }

        public boolean hasReturnType() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasReturnTypeId() {
            return (this.bitField0_ & 16) == 16;
        }

        public boolean hasTypeTable() {
            return (this.bitField0_ & 0x80) == 0x80;
        }

        private void initFields() {
            this.flags_ = 6;
            this.oldFlags_ = 6;
            this.name_ = 0;
            this.returnType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.returnTypeId_ = 0;
            this.typeParameter_ = Collections.EMPTY_LIST;
            this.receiverType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.receiverTypeId_ = 0;
            this.contextReceiverType_ = Collections.EMPTY_LIST;
            this.contextReceiverTypeId_ = Collections.EMPTY_LIST;
            this.valueParameter_ = Collections.EMPTY_LIST;
            this.typeTable_ = TypeTable.getDefaultInstance();
            this.versionRequirement_ = Collections.EMPTY_LIST;
            this.contract_ = Contract.getDefaultInstance();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            if(!this.hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(this.hasReturnType() && !this.getReturnType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for(int v1 = 0; v1 < this.getTypeParameterCount(); ++v1) {
                if(!this.getTypeParameter(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(this.hasReceiverType() && !this.getReceiverType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for(int v2 = 0; v2 < this.getContextReceiverTypeCount(); ++v2) {
                if(!this.getContextReceiverType(v2).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for(int v3 = 0; v3 < this.getValueParameterCount(); ++v3) {
                if(!this.getValueParameter(v3).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(this.hasTypeTable() && !this.getTypeTable().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(this.hasContract() && !this.getContract().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder.access$14100();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder newBuilder(Function protoBuf$Function0) {
            return Function.newBuilder().mergeFrom(protoBuf$Function0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder newBuilderForType() {
            return Function.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public static Function parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (Function)Function.PARSER.parseFrom(inputStream0, extensionRegistryLite0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.Builder toBuilder() {
            return Function.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            ExtensionWriter generatedMessageLite$ExtendableMessage$ExtensionWriter0 = this.newExtensionWriter();
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeInt32(1, this.oldFlags_);
            }
            if((this.bitField0_ & 4) == 4) {
                codedOutputStream0.writeInt32(2, this.name_);
            }
            if((this.bitField0_ & 8) == 8) {
                codedOutputStream0.writeMessage(3, this.returnType_);
            }
            for(int v1 = 0; v1 < this.typeParameter_.size(); ++v1) {
                codedOutputStream0.writeMessage(4, ((MessageLite)this.typeParameter_.get(v1)));
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                codedOutputStream0.writeMessage(5, this.receiverType_);
            }
            for(int v2 = 0; v2 < this.valueParameter_.size(); ++v2) {
                codedOutputStream0.writeMessage(6, ((MessageLite)this.valueParameter_.get(v2)));
            }
            if((this.bitField0_ & 16) == 16) {
                codedOutputStream0.writeInt32(7, this.returnTypeId_);
            }
            if((this.bitField0_ & 0x40) == 0x40) {
                codedOutputStream0.writeInt32(8, this.receiverTypeId_);
            }
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(9, this.flags_);
            }
            for(int v3 = 0; v3 < this.contextReceiverType_.size(); ++v3) {
                codedOutputStream0.writeMessage(10, ((MessageLite)this.contextReceiverType_.get(v3)));
            }
            if(this.getContextReceiverTypeIdList().size() > 0) {
                codedOutputStream0.writeRawVarint32(90);
                codedOutputStream0.writeRawVarint32(this.contextReceiverTypeIdMemoizedSerializedSize);
            }
            for(int v4 = 0; v4 < this.contextReceiverTypeId_.size(); ++v4) {
                codedOutputStream0.writeInt32NoTag(((int)(((Integer)this.contextReceiverTypeId_.get(v4)))));
            }
            if((this.bitField0_ & 0x80) == 0x80) {
                codedOutputStream0.writeMessage(30, this.typeTable_);
            }
            for(int v = 0; v < this.versionRequirement_.size(); ++v) {
                codedOutputStream0.writeInt32(0x1F, ((int)(((Integer)this.versionRequirement_.get(v)))));
            }
            if((this.bitField0_ & 0x100) == 0x100) {
                codedOutputStream0.writeMessage(0x20, this.contract_);
            }
            generatedMessageLite$ExtendableMessage$ExtensionWriter0.writeUntil(19000, codedOutputStream0);
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static enum MemberKind implements EnumLite {
        DECLARATION(0, 0),
        FAKE_OVERRIDE(1, 1),
        DELEGATION(2, 2),
        SYNTHESIZED(3, 3);

        private static EnumLiteMap internalValueMap;
        private final int value;

        static {
            MemberKind.internalValueMap = new EnumLiteMap() {
                public MemberKind findValueByNumber(int v) {
                    return MemberKind.valueOf(v);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                public EnumLite findValueByNumber(int v) {
                    return this.findValueByNumber(v);
                }
            };
        }

        private MemberKind(int v1, int v2) {
            this.value = v2;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static MemberKind valueOf(int v) {
            switch(v) {
                case 0: {
                    return MemberKind.DECLARATION;
                }
                case 1: {
                    return MemberKind.FAKE_OVERRIDE;
                }
                case 2: {
                    return MemberKind.DELEGATION;
                }
                case 3: {
                    return MemberKind.SYNTHESIZED;
                }
                default: {
                    return null;
                }
            }
        }
    }

    public static enum Modality implements EnumLite {
        FINAL(0, 0),
        OPEN(1, 1),
        ABSTRACT(2, 2),
        SEALED(3, 3);

        private static EnumLiteMap internalValueMap;
        private final int value;

        static {
            Modality.internalValueMap = new EnumLiteMap() {
                public Modality findValueByNumber(int v) {
                    return Modality.valueOf(v);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                public EnumLite findValueByNumber(int v) {
                    return this.findValueByNumber(v);
                }
            };
        }

        private Modality(int v1, int v2) {
            this.value = v2;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static Modality valueOf(int v) {
            switch(v) {
                case 0: {
                    return Modality.FINAL;
                }
                case 1: {
                    return Modality.OPEN;
                }
                case 2: {
                    return Modality.ABSTRACT;
                }
                case 3: {
                    return Modality.SEALED;
                }
                default: {
                    return null;
                }
            }
        }
    }

    public static final class Package extends ExtendableMessage implements ProtoBuf.PackageOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder extends ExtendableBuilder implements ProtoBuf.PackageOrBuilder {
            private int bitField0_;
            private List function_;
            private List property_;
            private List typeAlias_;
            private TypeTable typeTable_;
            private VersionRequirementTable versionRequirementTable_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder() {
                this.function_ = Collections.EMPTY_LIST;
                this.property_ = Collections.EMPTY_LIST;
                this.typeAlias_ = Collections.EMPTY_LIST;
                this.typeTable_ = TypeTable.getDefaultInstance();
                this.versionRequirementTable_ = VersionRequirementTable.getDefaultInstance();
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder access$11600() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder.create();
            }

            public Package build() {
                Package protoBuf$Package0 = this.buildPartial();
                if(!protoBuf$Package0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder.newUninitializedMessageException(protoBuf$Package0);
                }
                return protoBuf$Package0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public Package buildPartial() {
                Package protoBuf$Package0 = new Package(this, null);
                int v = this.bitField0_;
                int v1 = 1;
                if((v & 1) == 1) {
                    this.function_ = Collections.unmodifiableList(this.function_);
                    this.bitField0_ &= -2;
                }
                protoBuf$Package0.function_ = this.function_;
                if((this.bitField0_ & 2) == 2) {
                    this.property_ = Collections.unmodifiableList(this.property_);
                    this.bitField0_ &= -3;
                }
                protoBuf$Package0.property_ = this.property_;
                if((this.bitField0_ & 4) == 4) {
                    this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                    this.bitField0_ &= -5;
                }
                protoBuf$Package0.typeAlias_ = this.typeAlias_;
                if((v & 8) != 8) {
                    v1 = 0;
                }
                protoBuf$Package0.typeTable_ = this.typeTable_;
                if((v & 16) == 16) {
                    v1 |= 2;
                }
                protoBuf$Package0.versionRequirementTable_ = this.versionRequirementTable_;
                protoBuf$Package0.bitField0_ = v1;
                return protoBuf$Package0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public ExtendableBuilder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder();
            }

            private void ensureFunctionIsMutable() {
                if((this.bitField0_ & 1) != 1) {
                    this.function_ = new ArrayList(this.function_);
                    this.bitField0_ |= 1;
                }
            }

            private void ensurePropertyIsMutable() {
                if((this.bitField0_ & 2) != 2) {
                    this.property_ = new ArrayList(this.property_);
                    this.bitField0_ |= 2;
                }
            }

            private void ensureTypeAliasIsMutable() {
                if((this.bitField0_ & 4) != 4) {
                    this.typeAlias_ = new ArrayList(this.typeAlias_);
                    this.bitField0_ |= 4;
                }
            }

            public Package getDefaultInstanceForType() {
                return Package.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public Function getFunction(int v) {
                return (Function)this.function_.get(v);
            }

            public int getFunctionCount() {
                return this.function_.size();
            }

            public Property getProperty(int v) {
                return (Property)this.property_.get(v);
            }

            public int getPropertyCount() {
                return this.property_.size();
            }

            public TypeAlias getTypeAlias(int v) {
                return (TypeAlias)this.typeAlias_.get(v);
            }

            public int getTypeAliasCount() {
                return this.typeAlias_.size();
            }

            public TypeTable getTypeTable() {
                return this.typeTable_;
            }

            public boolean hasTypeTable() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for(int v = 0; v < this.getFunctionCount(); ++v) {
                    if(!this.getFunction(v).isInitialized()) {
                        return false;
                    }
                }
                for(int v1 = 0; v1 < this.getPropertyCount(); ++v1) {
                    if(!this.getProperty(v1).isInitialized()) {
                        return false;
                    }
                }
                for(int v2 = 0; v2 < this.getTypeAliasCount(); ++v2) {
                    if(!this.getTypeAlias(v2).isInitialized()) {
                        return false;
                    }
                }
                return !this.hasTypeTable() || this.getTypeTable().isInitialized() ? this.extensionsAreInitialized() : false;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder mergeFrom(Package protoBuf$Package0) {
                if(protoBuf$Package0 == Package.getDefaultInstance()) {
                    return this;
                }
                if(!protoBuf$Package0.function_.isEmpty()) {
                    if(this.function_.isEmpty()) {
                        this.function_ = protoBuf$Package0.function_;
                        this.bitField0_ &= -2;
                    }
                    else {
                        this.ensureFunctionIsMutable();
                        this.function_.addAll(protoBuf$Package0.function_);
                    }
                }
                if(!protoBuf$Package0.property_.isEmpty()) {
                    if(this.property_.isEmpty()) {
                        this.property_ = protoBuf$Package0.property_;
                        this.bitField0_ &= -3;
                    }
                    else {
                        this.ensurePropertyIsMutable();
                        this.property_.addAll(protoBuf$Package0.property_);
                    }
                }
                if(!protoBuf$Package0.typeAlias_.isEmpty()) {
                    if(this.typeAlias_.isEmpty()) {
                        this.typeAlias_ = protoBuf$Package0.typeAlias_;
                        this.bitField0_ &= -5;
                    }
                    else {
                        this.ensureTypeAliasIsMutable();
                        this.typeAlias_.addAll(protoBuf$Package0.typeAlias_);
                    }
                }
                if(protoBuf$Package0.hasTypeTable()) {
                    this.mergeTypeTable(protoBuf$Package0.getTypeTable());
                }
                if(protoBuf$Package0.hasVersionRequirementTable()) {
                    this.mergeVersionRequirementTable(protoBuf$Package0.getVersionRequirementTable());
                }
                this.mergeExtensionFields(protoBuf$Package0);
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Package0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                Package protoBuf$Package1;
                Package protoBuf$Package0;
                try {
                    try {
                        protoBuf$Package0 = null;
                        protoBuf$Package1 = (Package)Package.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        Package protoBuf$Package2 = (Package)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$Package0 = protoBuf$Package2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$Package0 != null) {
                    this.mergeFrom(protoBuf$Package0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$Package1 != null) {
                    this.mergeFrom(protoBuf$Package1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((Package)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder mergeTypeTable(TypeTable protoBuf$TypeTable0) {
                this.typeTable_ = (this.bitField0_ & 8) != 8 || this.typeTable_ == TypeTable.getDefaultInstance() ? protoBuf$TypeTable0 : TypeTable.newBuilder(this.typeTable_).mergeFrom(protoBuf$TypeTable0).buildPartial();
                this.bitField0_ |= 8;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder mergeVersionRequirementTable(VersionRequirementTable protoBuf$VersionRequirementTable0) {
                this.versionRequirementTable_ = (this.bitField0_ & 16) != 16 || this.versionRequirementTable_ == VersionRequirementTable.getDefaultInstance() ? protoBuf$VersionRequirementTable0 : VersionRequirementTable.newBuilder(this.versionRequirementTable_).mergeFrom(protoBuf$VersionRequirementTable0).buildPartial();
                this.bitField0_ |= 16;
                return this;
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private static final Package defaultInstance;
        private List function_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List property_;
        private List typeAlias_;
        private TypeTable typeTable_;
        private final ByteString unknownFields;
        private VersionRequirementTable versionRequirementTable_;

        static {
            Package.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public Package parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new Package(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            Package protoBuf$Package0 = new Package(true);
            Package.defaultInstance = protoBuf$Package0;
            protoBuf$Package0.initFields();
        }

        private Package(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            int v = 0;
            boolean z = false;
            while(!z) {
                try {
                    try {
                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder protoBuf$TypeTable$Builder0 = null;
                        int v1 = codedInputStream0.readTag();
                        switch(v1) {
                            case 0: {
                                z = true;
                                continue;
                            }
                            case 26: {
                                if((v & 1) != 1) {
                                    this.function_ = new ArrayList();
                                    v |= 1;
                                }
                                this.function_.add(codedInputStream0.readMessage(Function.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 34: {
                                if((v & 2) != 2) {
                                    this.property_ = new ArrayList();
                                    v |= 2;
                                }
                                this.property_.add(codedInputStream0.readMessage(Property.PARSER, extensionRegistryLite0));
                                break;
                            }
                            default: {
                                if(v1 == 42) {
                                    if((v & 4) != 4) {
                                        this.typeAlias_ = new ArrayList();
                                        v |= 4;
                                    }
                                    this.typeAlias_.add(codedInputStream0.readMessage(TypeAlias.PARSER, extensionRegistryLite0));
                                    continue;
                                }
                                else {
                                    switch(v1) {
                                        case 0xF2: {
                                            if((this.bitField0_ & 1) == 1) {
                                                protoBuf$TypeTable$Builder0 = this.typeTable_.toBuilder();
                                            }
                                            TypeTable protoBuf$TypeTable0 = (TypeTable)codedInputStream0.readMessage(TypeTable.PARSER, extensionRegistryLite0);
                                            this.typeTable_ = protoBuf$TypeTable0;
                                            if(protoBuf$TypeTable$Builder0 != null) {
                                                protoBuf$TypeTable$Builder0.mergeFrom(protoBuf$TypeTable0);
                                                this.typeTable_ = protoBuf$TypeTable$Builder0.buildPartial();
                                            }
                                            this.bitField0_ |= 1;
                                            continue;
                                        }
                                        case 0x102: {
                                            if((this.bitField0_ & 2) == 2) {
                                                protoBuf$TypeTable$Builder0 = this.versionRequirementTable_.toBuilder();
                                            }
                                            VersionRequirementTable protoBuf$VersionRequirementTable0 = (VersionRequirementTable)codedInputStream0.readMessage(VersionRequirementTable.PARSER, extensionRegistryLite0);
                                            this.versionRequirementTable_ = protoBuf$VersionRequirementTable0;
                                            if(protoBuf$TypeTable$Builder0 != null) {
                                                ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder)protoBuf$TypeTable$Builder0).mergeFrom(protoBuf$VersionRequirementTable0);
                                                this.versionRequirementTable_ = ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder)protoBuf$TypeTable$Builder0).buildPartial();
                                            }
                                            this.bitField0_ |= 2;
                                            continue;
                                        }
                                        default: {
                                            if(this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                                continue;
                                            }
                                        }
                                    }
                                }
                                z = true;
                                continue;
                            }
                        }
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if((v & 1) == 1) {
                        this.function_ = Collections.unmodifiableList(this.function_);
                    }
                    if((v & 2) == 2) {
                        this.property_ = Collections.unmodifiableList(this.property_);
                    }
                    if((v & 4) == 4) {
                        this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    this.makeExtensionsImmutable();
                    throw throwable0;
                }
            }
            if((v & 1) == 1) {
                this.function_ = Collections.unmodifiableList(this.function_);
            }
            if((v & 2) == 2) {
                this.property_ = Collections.unmodifiableList(this.property_);
            }
            if((v & 4) == 4) {
                this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
            this.makeExtensionsImmutable();
        }

        Package(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private Package(ExtendableBuilder generatedMessageLite$ExtendableBuilder0) {
            super(generatedMessageLite$ExtendableBuilder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$ExtendableBuilder0.getUnknownFields();
        }

        Package(ExtendableBuilder generatedMessageLite$ExtendableBuilder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$ExtendableBuilder0);
        }

        private Package(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Package getDefaultInstance() {
            return Package.defaultInstance;
        }

        public Package getDefaultInstanceForType() {
            return Package.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public Function getFunction(int v) {
            return (Function)this.function_.get(v);
        }

        public int getFunctionCount() {
            return this.function_.size();
        }

        public List getFunctionList() {
            return this.function_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return Package.PARSER;
        }

        public Property getProperty(int v) {
            return (Property)this.property_.get(v);
        }

        public int getPropertyCount() {
            return this.property_.size();
        }

        public List getPropertyList() {
            return this.property_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v3 = 0;
            for(int v2 = 0; v2 < this.function_.size(); ++v2) {
                v3 += CodedOutputStream.computeMessageSize(3, ((MessageLite)this.function_.get(v2)));
            }
            for(int v4 = 0; v4 < this.property_.size(); ++v4) {
                v3 += CodedOutputStream.computeMessageSize(4, ((MessageLite)this.property_.get(v4)));
            }
            for(int v1 = 0; v1 < this.typeAlias_.size(); ++v1) {
                v3 += CodedOutputStream.computeMessageSize(5, ((MessageLite)this.typeAlias_.get(v1)));
            }
            if((this.bitField0_ & 1) == 1) {
                v3 += CodedOutputStream.computeMessageSize(30, this.typeTable_);
            }
            if((this.bitField0_ & 2) == 2) {
                v3 += CodedOutputStream.computeMessageSize(0x20, this.versionRequirementTable_);
            }
            int v5 = v3 + this.extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = v5;
            return v5;
        }

        public TypeAlias getTypeAlias(int v) {
            return (TypeAlias)this.typeAlias_.get(v);
        }

        public int getTypeAliasCount() {
            return this.typeAlias_.size();
        }

        public List getTypeAliasList() {
            return this.typeAlias_;
        }

        public TypeTable getTypeTable() {
            return this.typeTable_;
        }

        public VersionRequirementTable getVersionRequirementTable() {
            return this.versionRequirementTable_;
        }

        public boolean hasTypeTable() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasVersionRequirementTable() {
            return (this.bitField0_ & 2) == 2;
        }

        private void initFields() {
            this.function_ = Collections.EMPTY_LIST;
            this.property_ = Collections.EMPTY_LIST;
            this.typeAlias_ = Collections.EMPTY_LIST;
            this.typeTable_ = TypeTable.getDefaultInstance();
            this.versionRequirementTable_ = VersionRequirementTable.getDefaultInstance();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            for(int v1 = 0; v1 < this.getFunctionCount(); ++v1) {
                if(!this.getFunction(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for(int v2 = 0; v2 < this.getPropertyCount(); ++v2) {
                if(!this.getProperty(v2).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            for(int v3 = 0; v3 < this.getTypeAliasCount(); ++v3) {
                if(!this.getTypeAlias(v3).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(this.hasTypeTable() && !this.getTypeTable().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder.access$11600();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder newBuilder(Package protoBuf$Package0) {
            return Package.newBuilder().mergeFrom(protoBuf$Package0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder newBuilderForType() {
            return Package.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public static Package parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (Package)Package.PARSER.parseFrom(inputStream0, extensionRegistryLite0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder toBuilder() {
            return Package.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            ExtensionWriter generatedMessageLite$ExtendableMessage$ExtensionWriter0 = this.newExtensionWriter();
            for(int v1 = 0; v1 < this.function_.size(); ++v1) {
                codedOutputStream0.writeMessage(3, ((MessageLite)this.function_.get(v1)));
            }
            for(int v2 = 0; v2 < this.property_.size(); ++v2) {
                codedOutputStream0.writeMessage(4, ((MessageLite)this.property_.get(v2)));
            }
            for(int v = 0; v < this.typeAlias_.size(); ++v) {
                codedOutputStream0.writeMessage(5, ((MessageLite)this.typeAlias_.get(v)));
            }
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeMessage(30, this.typeTable_);
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeMessage(0x20, this.versionRequirementTable_);
            }
            generatedMessageLite$ExtendableMessage$ExtensionWriter0.writeUntil(200, codedOutputStream0);
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class PackageFragment extends ExtendableMessage implements ProtoBuf.PackageFragmentOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder extends ExtendableBuilder implements ProtoBuf.PackageFragmentOrBuilder {
            private int bitField0_;
            private List class__;
            private Package package_;
            private QualifiedNameTable qualifiedNames_;
            private StringTable strings_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder() {
                this.strings_ = StringTable.getDefaultInstance();
                this.qualifiedNames_ = QualifiedNameTable.getDefaultInstance();
                this.package_ = Package.getDefaultInstance();
                this.class__ = Collections.EMPTY_LIST;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder access$22600() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder.create();
            }

            public PackageFragment build() {
                PackageFragment protoBuf$PackageFragment0 = this.buildPartial();
                if(!protoBuf$PackageFragment0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder.newUninitializedMessageException(protoBuf$PackageFragment0);
                }
                return protoBuf$PackageFragment0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public PackageFragment buildPartial() {
                PackageFragment protoBuf$PackageFragment0 = new PackageFragment(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                protoBuf$PackageFragment0.strings_ = this.strings_;
                if((v & 2) == 2) {
                    v1 |= 2;
                }
                protoBuf$PackageFragment0.qualifiedNames_ = this.qualifiedNames_;
                if((v & 4) == 4) {
                    v1 |= 4;
                }
                protoBuf$PackageFragment0.package_ = this.package_;
                if((this.bitField0_ & 8) == 8) {
                    this.class__ = Collections.unmodifiableList(this.class__);
                    this.bitField0_ &= -9;
                }
                protoBuf$PackageFragment0.class__ = this.class__;
                protoBuf$PackageFragment0.bitField0_ = v1;
                return protoBuf$PackageFragment0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public ExtendableBuilder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder();
            }

            private void ensureClass_IsMutable() {
                if((this.bitField0_ & 8) != 8) {
                    this.class__ = new ArrayList(this.class__);
                    this.bitField0_ |= 8;
                }
            }

            public Class getClass_(int v) {
                return (Class)this.class__.get(v);
            }

            public int getClass_Count() {
                return this.class__.size();
            }

            public PackageFragment getDefaultInstanceForType() {
                return PackageFragment.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public Package getPackage() {
                return this.package_;
            }

            public QualifiedNameTable getQualifiedNames() {
                return this.qualifiedNames_;
            }

            public boolean hasPackage() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean hasQualifiedNames() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if(this.hasQualifiedNames() && !this.getQualifiedNames().isInitialized()) {
                    return false;
                }
                if(this.hasPackage() && !this.getPackage().isInitialized()) {
                    return false;
                }
                for(int v = 0; v < this.getClass_Count(); ++v) {
                    if(!this.getClass_(v).isInitialized()) {
                        return false;
                    }
                }
                return this.extensionsAreInitialized();
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder mergeFrom(PackageFragment protoBuf$PackageFragment0) {
                if(protoBuf$PackageFragment0 == PackageFragment.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$PackageFragment0.hasStrings()) {
                    this.mergeStrings(protoBuf$PackageFragment0.getStrings());
                }
                if(protoBuf$PackageFragment0.hasQualifiedNames()) {
                    this.mergeQualifiedNames(protoBuf$PackageFragment0.getQualifiedNames());
                }
                if(protoBuf$PackageFragment0.hasPackage()) {
                    this.mergePackage(protoBuf$PackageFragment0.getPackage());
                }
                if(!protoBuf$PackageFragment0.class__.isEmpty()) {
                    if(this.class__.isEmpty()) {
                        this.class__ = protoBuf$PackageFragment0.class__;
                        this.bitField0_ &= -9;
                    }
                    else {
                        this.ensureClass_IsMutable();
                        this.class__.addAll(protoBuf$PackageFragment0.class__);
                    }
                }
                this.mergeExtensionFields(protoBuf$PackageFragment0);
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$PackageFragment0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                PackageFragment protoBuf$PackageFragment1;
                PackageFragment protoBuf$PackageFragment0;
                try {
                    try {
                        protoBuf$PackageFragment0 = null;
                        protoBuf$PackageFragment1 = (PackageFragment)PackageFragment.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        PackageFragment protoBuf$PackageFragment2 = (PackageFragment)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$PackageFragment0 = protoBuf$PackageFragment2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$PackageFragment0 != null) {
                    this.mergeFrom(protoBuf$PackageFragment0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$PackageFragment1 != null) {
                    this.mergeFrom(protoBuf$PackageFragment1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((PackageFragment)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder mergePackage(Package protoBuf$Package0) {
                this.package_ = (this.bitField0_ & 4) != 4 || this.package_ == Package.getDefaultInstance() ? protoBuf$Package0 : Package.newBuilder(this.package_).mergeFrom(protoBuf$Package0).buildPartial();
                this.bitField0_ |= 4;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder mergeQualifiedNames(QualifiedNameTable protoBuf$QualifiedNameTable0) {
                this.qualifiedNames_ = (this.bitField0_ & 2) != 2 || this.qualifiedNames_ == QualifiedNameTable.getDefaultInstance() ? protoBuf$QualifiedNameTable0 : QualifiedNameTable.newBuilder(this.qualifiedNames_).mergeFrom(protoBuf$QualifiedNameTable0).buildPartial();
                this.bitField0_ |= 2;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder mergeStrings(StringTable protoBuf$StringTable0) {
                this.strings_ = (this.bitField0_ & 1) != 1 || this.strings_ == StringTable.getDefaultInstance() ? protoBuf$StringTable0 : StringTable.newBuilder(this.strings_).mergeFrom(protoBuf$StringTable0).buildPartial();
                this.bitField0_ |= 1;
                return this;
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private List class__;
        private static final PackageFragment defaultInstance;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Package package_;
        private QualifiedNameTable qualifiedNames_;
        private StringTable strings_;
        private final ByteString unknownFields;

        static {
            PackageFragment.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public PackageFragment parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new PackageFragment(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            PackageFragment protoBuf$PackageFragment0 = new PackageFragment(true);
            PackageFragment.defaultInstance = protoBuf$PackageFragment0;
            protoBuf$PackageFragment0.initFields();
        }

        private PackageFragment(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            int v = 0;
            boolean z = false;
            while(!z) {
                try {
                    try {
                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder protoBuf$StringTable$Builder0 = null;
                        int v1 = codedInputStream0.readTag();
                        if(v1 != 0) {
                            switch(v1) {
                                case 10: {
                                    if((this.bitField0_ & 1) == 1) {
                                        protoBuf$StringTable$Builder0 = this.strings_.toBuilder();
                                    }
                                    StringTable protoBuf$StringTable0 = (StringTable)codedInputStream0.readMessage(StringTable.PARSER, extensionRegistryLite0);
                                    this.strings_ = protoBuf$StringTable0;
                                    if(protoBuf$StringTable$Builder0 != null) {
                                        protoBuf$StringTable$Builder0.mergeFrom(protoBuf$StringTable0);
                                        this.strings_ = protoBuf$StringTable$Builder0.buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                    continue;
                                }
                                case 18: {
                                    if((this.bitField0_ & 2) == 2) {
                                        protoBuf$StringTable$Builder0 = this.qualifiedNames_.toBuilder();
                                    }
                                    QualifiedNameTable protoBuf$QualifiedNameTable0 = (QualifiedNameTable)codedInputStream0.readMessage(QualifiedNameTable.PARSER, extensionRegistryLite0);
                                    this.qualifiedNames_ = protoBuf$QualifiedNameTable0;
                                    if(protoBuf$StringTable$Builder0 != null) {
                                        ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder)protoBuf$StringTable$Builder0).mergeFrom(protoBuf$QualifiedNameTable0);
                                        this.qualifiedNames_ = ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder)protoBuf$StringTable$Builder0).buildPartial();
                                    }
                                    this.bitField0_ |= 2;
                                    continue;
                                }
                                case 26: {
                                    if((this.bitField0_ & 4) == 4) {
                                        protoBuf$StringTable$Builder0 = this.package_.toBuilder();
                                    }
                                    Package protoBuf$Package0 = (Package)codedInputStream0.readMessage(Package.PARSER, extensionRegistryLite0);
                                    this.package_ = protoBuf$Package0;
                                    if(protoBuf$StringTable$Builder0 != null) {
                                        ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder)protoBuf$StringTable$Builder0).mergeFrom(protoBuf$Package0);
                                        this.package_ = ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.Builder)protoBuf$StringTable$Builder0).buildPartial();
                                    }
                                    this.bitField0_ |= 4;
                                    continue;
                                }
                                case 34: {
                                    if((v & 8) != 8) {
                                        this.class__ = new ArrayList();
                                        v = 8;
                                    }
                                    this.class__.add(codedInputStream0.readMessage(Class.PARSER, extensionRegistryLite0));
                                    continue;
                                }
                                default: {
                                    if(this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                        continue;
                                    }
                                }
                            }
                        }
                        z = true;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if((v & 8) == 8) {
                        this.class__ = Collections.unmodifiableList(this.class__);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    this.makeExtensionsImmutable();
                    throw throwable0;
                }
            }
            if((v & 8) == 8) {
                this.class__ = Collections.unmodifiableList(this.class__);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
            this.makeExtensionsImmutable();
        }

        PackageFragment(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private PackageFragment(ExtendableBuilder generatedMessageLite$ExtendableBuilder0) {
            super(generatedMessageLite$ExtendableBuilder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$ExtendableBuilder0.getUnknownFields();
        }

        PackageFragment(ExtendableBuilder generatedMessageLite$ExtendableBuilder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$ExtendableBuilder0);
        }

        private PackageFragment(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public Class getClass_(int v) {
            return (Class)this.class__.get(v);
        }

        public int getClass_Count() {
            return this.class__.size();
        }

        public List getClass_List() {
            return this.class__;
        }

        public static PackageFragment getDefaultInstance() {
            return PackageFragment.defaultInstance;
        }

        public PackageFragment getDefaultInstanceForType() {
            return PackageFragment.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public Package getPackage() {
            return this.package_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return PackageFragment.PARSER;
        }

        public QualifiedNameTable getQualifiedNames() {
            return this.qualifiedNames_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v1 = this.memoizedSerializedSize;
            if(v1 != -1) {
                return v1;
            }
            int v2 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeMessageSize(1, this.strings_) : 0;
            if((this.bitField0_ & 2) == 2) {
                v2 += CodedOutputStream.computeMessageSize(2, this.qualifiedNames_);
            }
            if((this.bitField0_ & 4) == 4) {
                v2 += CodedOutputStream.computeMessageSize(3, this.package_);
            }
            for(int v = 0; v < this.class__.size(); ++v) {
                v2 += CodedOutputStream.computeMessageSize(4, ((MessageLite)this.class__.get(v)));
            }
            int v3 = v2 + this.extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = v3;
            return v3;
        }

        public StringTable getStrings() {
            return this.strings_;
        }

        public boolean hasPackage() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasQualifiedNames() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasStrings() {
            return (this.bitField0_ & 1) == 1;
        }

        private void initFields() {
            this.strings_ = StringTable.getDefaultInstance();
            this.qualifiedNames_ = QualifiedNameTable.getDefaultInstance();
            this.package_ = Package.getDefaultInstance();
            this.class__ = Collections.EMPTY_LIST;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            if(this.hasQualifiedNames() && !this.getQualifiedNames().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(this.hasPackage() && !this.getPackage().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for(int v1 = 0; v1 < this.getClass_Count(); ++v1) {
                if(!this.getClass_(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder.access$22600();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder newBuilder(PackageFragment protoBuf$PackageFragment0) {
            return PackageFragment.newBuilder().mergeFrom(protoBuf$PackageFragment0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder newBuilderForType() {
            return PackageFragment.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public static PackageFragment parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (PackageFragment)PackageFragment.PARSER.parseFrom(inputStream0, extensionRegistryLite0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.Builder toBuilder() {
            return PackageFragment.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            ExtensionWriter generatedMessageLite$ExtendableMessage$ExtensionWriter0 = this.newExtensionWriter();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeMessage(1, this.strings_);
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeMessage(2, this.qualifiedNames_);
            }
            if((this.bitField0_ & 4) == 4) {
                codedOutputStream0.writeMessage(3, this.package_);
            }
            for(int v = 0; v < this.class__.size(); ++v) {
                codedOutputStream0.writeMessage(4, ((MessageLite)this.class__.get(v)));
            }
            generatedMessageLite$ExtendableMessage$ExtensionWriter0.writeUntil(200, codedOutputStream0);
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class Property extends ExtendableMessage implements ProtoBuf.PropertyOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder extends ExtendableBuilder implements ProtoBuf.PropertyOrBuilder {
            private int bitField0_;
            private List contextReceiverTypeId_;
            private List contextReceiverType_;
            private int flags_;
            private int getterFlags_;
            private int name_;
            private int oldFlags_;
            private int receiverTypeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type receiverType_;
            private int returnTypeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type returnType_;
            private int setterFlags_;
            private ValueParameter setterValueParameter_;
            private List typeParameter_;
            private List versionRequirement_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder() {
                this.flags_ = 0x206;
                this.oldFlags_ = 0x806;
                this.returnType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                this.typeParameter_ = Collections.EMPTY_LIST;
                this.receiverType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                this.contextReceiverType_ = Collections.EMPTY_LIST;
                this.contextReceiverTypeId_ = Collections.EMPTY_LIST;
                this.setterValueParameter_ = ValueParameter.getDefaultInstance();
                this.versionRequirement_ = Collections.EMPTY_LIST;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder access$16000() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder.create();
            }

            public Property build() {
                Property protoBuf$Property0 = this.buildPartial();
                if(!protoBuf$Property0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder.newUninitializedMessageException(protoBuf$Property0);
                }
                return protoBuf$Property0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public Property buildPartial() {
                Property protoBuf$Property0 = new Property(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                protoBuf$Property0.flags_ = this.flags_;
                if((v & 2) == 2) {
                    v1 |= 2;
                }
                protoBuf$Property0.oldFlags_ = this.oldFlags_;
                if((v & 4) == 4) {
                    v1 |= 4;
                }
                protoBuf$Property0.name_ = this.name_;
                if((v & 8) == 8) {
                    v1 |= 8;
                }
                protoBuf$Property0.returnType_ = this.returnType_;
                if((v & 16) == 16) {
                    v1 |= 16;
                }
                protoBuf$Property0.returnTypeId_ = this.returnTypeId_;
                if((this.bitField0_ & 0x20) == 0x20) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    this.bitField0_ &= -33;
                }
                protoBuf$Property0.typeParameter_ = this.typeParameter_;
                if((v & 0x40) == 0x40) {
                    v1 |= 0x20;
                }
                protoBuf$Property0.receiverType_ = this.receiverType_;
                if((v & 0x80) == 0x80) {
                    v1 |= 0x40;
                }
                protoBuf$Property0.receiverTypeId_ = this.receiverTypeId_;
                if((this.bitField0_ & 0x100) == 0x100) {
                    this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
                    this.bitField0_ &= 0xFFFFFEFF;
                }
                protoBuf$Property0.contextReceiverType_ = this.contextReceiverType_;
                if((this.bitField0_ & 0x200) == 0x200) {
                    this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
                    this.bitField0_ &= 0xFFFFFDFF;
                }
                protoBuf$Property0.contextReceiverTypeId_ = this.contextReceiverTypeId_;
                if((v & 0x400) == 0x400) {
                    v1 |= 0x80;
                }
                protoBuf$Property0.setterValueParameter_ = this.setterValueParameter_;
                if((v & 0x800) == 0x800) {
                    v1 |= 0x100;
                }
                protoBuf$Property0.getterFlags_ = this.getterFlags_;
                if((v & 0x1000) == 0x1000) {
                    v1 |= 0x200;
                }
                protoBuf$Property0.setterFlags_ = this.setterFlags_;
                if((this.bitField0_ & 0x2000) == 0x2000) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= 0xFFFFDFFF;
                }
                protoBuf$Property0.versionRequirement_ = this.versionRequirement_;
                protoBuf$Property0.bitField0_ = v1;
                return protoBuf$Property0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public ExtendableBuilder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder();
            }

            private void ensureContextReceiverTypeIdIsMutable() {
                if((this.bitField0_ & 0x200) != 0x200) {
                    this.contextReceiverTypeId_ = new ArrayList(this.contextReceiverTypeId_);
                    this.bitField0_ |= 0x200;
                }
            }

            private void ensureContextReceiverTypeIsMutable() {
                if((this.bitField0_ & 0x100) != 0x100) {
                    this.contextReceiverType_ = new ArrayList(this.contextReceiverType_);
                    this.bitField0_ |= 0x100;
                }
            }

            private void ensureTypeParameterIsMutable() {
                if((this.bitField0_ & 0x20) != 0x20) {
                    this.typeParameter_ = new ArrayList(this.typeParameter_);
                    this.bitField0_ |= 0x20;
                }
            }

            private void ensureVersionRequirementIsMutable() {
                if((this.bitField0_ & 0x2000) != 0x2000) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 0x2000;
                }
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getContextReceiverType(int v) {
                return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.contextReceiverType_.get(v);
            }

            public int getContextReceiverTypeCount() {
                return this.contextReceiverType_.size();
            }

            public Property getDefaultInstanceForType() {
                return Property.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getReceiverType() {
                return this.receiverType_;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getReturnType() {
                return this.returnType_;
            }

            public ValueParameter getSetterValueParameter() {
                return this.setterValueParameter_;
            }

            public TypeParameter getTypeParameter(int v) {
                return (TypeParameter)this.typeParameter_.get(v);
            }

            public int getTypeParameterCount() {
                return this.typeParameter_.size();
            }

            public boolean hasName() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean hasReceiverType() {
                return (this.bitField0_ & 0x40) == 0x40;
            }

            public boolean hasReturnType() {
                return (this.bitField0_ & 8) == 8;
            }

            public boolean hasSetterValueParameter() {
                return (this.bitField0_ & 0x400) == 0x400;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if(!this.hasName()) {
                    return false;
                }
                if(this.hasReturnType() && !this.getReturnType().isInitialized()) {
                    return false;
                }
                for(int v = 0; v < this.getTypeParameterCount(); ++v) {
                    if(!this.getTypeParameter(v).isInitialized()) {
                        return false;
                    }
                }
                if(this.hasReceiverType() && !this.getReceiverType().isInitialized()) {
                    return false;
                }
                for(int v1 = 0; v1 < this.getContextReceiverTypeCount(); ++v1) {
                    if(!this.getContextReceiverType(v1).isInitialized()) {
                        return false;
                    }
                }
                return !this.hasSetterValueParameter() || this.getSetterValueParameter().isInitialized() ? this.extensionsAreInitialized() : false;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder mergeFrom(Property protoBuf$Property0) {
                if(protoBuf$Property0 == Property.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$Property0.hasFlags()) {
                    this.setFlags(protoBuf$Property0.getFlags());
                }
                if(protoBuf$Property0.hasOldFlags()) {
                    this.setOldFlags(protoBuf$Property0.getOldFlags());
                }
                if(protoBuf$Property0.hasName()) {
                    this.setName(protoBuf$Property0.getName());
                }
                if(protoBuf$Property0.hasReturnType()) {
                    this.mergeReturnType(protoBuf$Property0.getReturnType());
                }
                if(protoBuf$Property0.hasReturnTypeId()) {
                    this.setReturnTypeId(protoBuf$Property0.getReturnTypeId());
                }
                if(!protoBuf$Property0.typeParameter_.isEmpty()) {
                    if(this.typeParameter_.isEmpty()) {
                        this.typeParameter_ = protoBuf$Property0.typeParameter_;
                        this.bitField0_ &= -33;
                    }
                    else {
                        this.ensureTypeParameterIsMutable();
                        this.typeParameter_.addAll(protoBuf$Property0.typeParameter_);
                    }
                }
                if(protoBuf$Property0.hasReceiverType()) {
                    this.mergeReceiverType(protoBuf$Property0.getReceiverType());
                }
                if(protoBuf$Property0.hasReceiverTypeId()) {
                    this.setReceiverTypeId(protoBuf$Property0.getReceiverTypeId());
                }
                if(!protoBuf$Property0.contextReceiverType_.isEmpty()) {
                    if(this.contextReceiverType_.isEmpty()) {
                        this.contextReceiverType_ = protoBuf$Property0.contextReceiverType_;
                        this.bitField0_ &= 0xFFFFFEFF;
                    }
                    else {
                        this.ensureContextReceiverTypeIsMutable();
                        this.contextReceiverType_.addAll(protoBuf$Property0.contextReceiverType_);
                    }
                }
                if(!protoBuf$Property0.contextReceiverTypeId_.isEmpty()) {
                    if(this.contextReceiverTypeId_.isEmpty()) {
                        this.contextReceiverTypeId_ = protoBuf$Property0.contextReceiverTypeId_;
                        this.bitField0_ &= 0xFFFFFDFF;
                    }
                    else {
                        this.ensureContextReceiverTypeIdIsMutable();
                        this.contextReceiverTypeId_.addAll(protoBuf$Property0.contextReceiverTypeId_);
                    }
                }
                if(protoBuf$Property0.hasSetterValueParameter()) {
                    this.mergeSetterValueParameter(protoBuf$Property0.getSetterValueParameter());
                }
                if(protoBuf$Property0.hasGetterFlags()) {
                    this.setGetterFlags(protoBuf$Property0.getGetterFlags());
                }
                if(protoBuf$Property0.hasSetterFlags()) {
                    this.setSetterFlags(protoBuf$Property0.getSetterFlags());
                }
                if(!protoBuf$Property0.versionRequirement_.isEmpty()) {
                    if(this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = protoBuf$Property0.versionRequirement_;
                        this.bitField0_ &= 0xFFFFDFFF;
                    }
                    else {
                        this.ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(protoBuf$Property0.versionRequirement_);
                    }
                }
                this.mergeExtensionFields(protoBuf$Property0);
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Property0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                Property protoBuf$Property1;
                Property protoBuf$Property0;
                try {
                    try {
                        protoBuf$Property0 = null;
                        protoBuf$Property1 = (Property)Property.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        Property protoBuf$Property2 = (Property)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$Property0 = protoBuf$Property2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$Property0 != null) {
                    this.mergeFrom(protoBuf$Property0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$Property1 != null) {
                    this.mergeFrom(protoBuf$Property1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((Property)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder mergeReceiverType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.receiverType_ = (this.bitField0_ & 0x40) != 0x40 || this.receiverType_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.receiverType_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 0x40;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder mergeReturnType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.returnType_ = (this.bitField0_ & 8) != 8 || this.returnType_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.returnType_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 8;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder mergeSetterValueParameter(ValueParameter protoBuf$ValueParameter0) {
                this.setterValueParameter_ = (this.bitField0_ & 0x400) != 0x400 || this.setterValueParameter_ == ValueParameter.getDefaultInstance() ? protoBuf$ValueParameter0 : ValueParameter.newBuilder(this.setterValueParameter_).mergeFrom(protoBuf$ValueParameter0).buildPartial();
                this.bitField0_ |= 0x400;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder setFlags(int v) {
                this.bitField0_ |= 1;
                this.flags_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder setGetterFlags(int v) {
                this.bitField0_ |= 0x800;
                this.getterFlags_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder setName(int v) {
                this.bitField0_ |= 4;
                this.name_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder setOldFlags(int v) {
                this.bitField0_ |= 2;
                this.oldFlags_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder setReceiverTypeId(int v) {
                this.bitField0_ |= 0x80;
                this.receiverTypeId_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder setReturnTypeId(int v) {
                this.bitField0_ |= 16;
                this.returnTypeId_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder setSetterFlags(int v) {
                this.bitField0_ |= 0x1000;
                this.setterFlags_ = v;
                return this;
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private int contextReceiverTypeIdMemoizedSerializedSize;
        private List contextReceiverTypeId_;
        private List contextReceiverType_;
        private static final Property defaultInstance;
        private int flags_;
        private int getterFlags_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int name_;
        private int oldFlags_;
        private int receiverTypeId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type receiverType_;
        private int returnTypeId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type returnType_;
        private int setterFlags_;
        private ValueParameter setterValueParameter_;
        private List typeParameter_;
        private final ByteString unknownFields;
        private List versionRequirement_;

        static {
            Property.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public Property parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new Property(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            Property protoBuf$Property0 = new Property(true);
            Property.defaultInstance = protoBuf$Property0;
            protoBuf$Property0.initFields();
        }

        private Property(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.contextReceiverTypeIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            int v = 0;
            boolean z = false;
            while(!z) {
                try {
                    try {
                        int v1 = codedInputStream0.readTag();
                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder protoBuf$Type$Builder0 = null;
                        switch(v1) {
                            case 0: {
                            label_14:
                                z = true;
                                continue;
                            }
                            case 8: {
                                this.bitField0_ |= 2;
                                this.oldFlags_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 16: {
                                this.bitField0_ |= 4;
                                this.name_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 26: {
                                if((this.bitField0_ & 8) == 8) {
                                    protoBuf$Type$Builder0 = this.returnType_.toBuilder();
                                }
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                this.returnType_ = protoBuf$Type0;
                                if(protoBuf$Type$Builder0 != null) {
                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type0);
                                    this.returnType_ = protoBuf$Type$Builder0.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                continue;
                            }
                            case 34: {
                                if((v & 0x20) != 0x20) {
                                    this.typeParameter_ = new ArrayList();
                                    v |= 0x20;
                                }
                                this.typeParameter_.add(codedInputStream0.readMessage(TypeParameter.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 42: {
                                if((this.bitField0_ & 0x20) == 0x20) {
                                    protoBuf$Type$Builder0 = this.receiverType_.toBuilder();
                                }
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                this.receiverType_ = protoBuf$Type1;
                                if(protoBuf$Type$Builder0 != null) {
                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type1);
                                    this.receiverType_ = protoBuf$Type$Builder0.buildPartial();
                                }
                                this.bitField0_ |= 0x20;
                                continue;
                            }
                            case 50: {
                                if((this.bitField0_ & 0x80) == 0x80) {
                                    protoBuf$Type$Builder0 = this.setterValueParameter_.toBuilder();
                                }
                                ValueParameter protoBuf$ValueParameter0 = (ValueParameter)codedInputStream0.readMessage(ValueParameter.PARSER, extensionRegistryLite0);
                                this.setterValueParameter_ = protoBuf$ValueParameter0;
                                if(protoBuf$Type$Builder0 != null) {
                                    ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder)protoBuf$Type$Builder0).mergeFrom(protoBuf$ValueParameter0);
                                    this.setterValueParameter_ = ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder)protoBuf$Type$Builder0).buildPartial();
                                }
                                this.bitField0_ |= 0x80;
                                continue;
                            }
                            case 56: {
                                this.bitField0_ |= 0x100;
                                this.getterFlags_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 0x40: {
                                this.bitField0_ |= 0x200;
                                this.setterFlags_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 72: {
                                this.bitField0_ |= 16;
                                this.returnTypeId_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 80: {
                                this.bitField0_ |= 0x40;
                                this.receiverTypeId_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 88: {
                                this.bitField0_ |= 1;
                                this.flags_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 98: {
                                if((v & 0x100) != 0x100) {
                                    this.contextReceiverType_ = new ArrayList();
                                    v |= 0x100;
                                }
                                this.contextReceiverType_.add(codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 104: {
                                if((v & 0x200) != 0x200) {
                                    this.contextReceiverTypeId_ = new ArrayList();
                                    v |= 0x200;
                                }
                                this.contextReceiverTypeId_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 106: {
                                int v2 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 0x200) != 0x200 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.contextReceiverTypeId_ = new ArrayList();
                                    v |= 0x200;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.contextReceiverTypeId_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v2);
                                continue;
                            }
                            case 0xF8: {
                                if((v & 0x2000) != 0x2000) {
                                    this.versionRequirement_ = new ArrayList();
                                    v |= 0x2000;
                                }
                                this.versionRequirement_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 0xFA: {
                                int v3 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 0x2000) != 0x2000 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_ = new ArrayList();
                                    v |= 0x2000;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v3);
                                break;
                            }
                            default: {
                                if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                    goto label_14;
                                }
                            }
                        }
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if((v & 0x20) == 0x20) {
                        this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    }
                    if((v & 0x100) == 0x100) {
                        this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
                    }
                    if((v & 0x200) == 0x200) {
                        this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
                    }
                    if((v & 0x2000) == 0x2000) {
                        this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    this.makeExtensionsImmutable();
                    throw throwable0;
                }
            }
            if((v & 0x20) == 0x20) {
                this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
            }
            if((v & 0x100) == 0x100) {
                this.contextReceiverType_ = Collections.unmodifiableList(this.contextReceiverType_);
            }
            if((v & 0x200) == 0x200) {
                this.contextReceiverTypeId_ = Collections.unmodifiableList(this.contextReceiverTypeId_);
            }
            if((v & 0x2000) == 0x2000) {
                this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
            this.makeExtensionsImmutable();
        }

        Property(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private Property(ExtendableBuilder generatedMessageLite$ExtendableBuilder0) {
            super(generatedMessageLite$ExtendableBuilder0);
            this.contextReceiverTypeIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$ExtendableBuilder0.getUnknownFields();
        }

        Property(ExtendableBuilder generatedMessageLite$ExtendableBuilder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$ExtendableBuilder0);
        }

        private Property(boolean z) {
            this.contextReceiverTypeIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getContextReceiverType(int v) {
            return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.contextReceiverType_.get(v);
        }

        public int getContextReceiverTypeCount() {
            return this.contextReceiverType_.size();
        }

        public List getContextReceiverTypeIdList() {
            return this.contextReceiverTypeId_;
        }

        public List getContextReceiverTypeList() {
            return this.contextReceiverType_;
        }

        public static Property getDefaultInstance() {
            return Property.defaultInstance;
        }

        public Property getDefaultInstanceForType() {
            return Property.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getFlags() {
            return this.flags_;
        }

        public int getGetterFlags() {
            return this.getterFlags_;
        }

        public int getName() {
            return this.name_;
        }

        public int getOldFlags() {
            return this.oldFlags_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return Property.PARSER;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getReceiverType() {
            return this.receiverType_;
        }

        public int getReceiverTypeId() {
            return this.receiverTypeId_;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getReturnType() {
            return this.returnType_;
        }

        public int getReturnTypeId() {
            return this.returnTypeId_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v1 = this.memoizedSerializedSize;
            if(v1 != -1) {
                return v1;
            }
            int v2 = (this.bitField0_ & 2) == 2 ? CodedOutputStream.computeInt32Size(1, this.oldFlags_) : 0;
            if((this.bitField0_ & 4) == 4) {
                v2 += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            if((this.bitField0_ & 8) == 8) {
                v2 += CodedOutputStream.computeMessageSize(3, this.returnType_);
            }
            for(int v3 = 0; v3 < this.typeParameter_.size(); ++v3) {
                v2 += CodedOutputStream.computeMessageSize(4, ((MessageLite)this.typeParameter_.get(v3)));
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                v2 += CodedOutputStream.computeMessageSize(5, this.receiverType_);
            }
            if((this.bitField0_ & 0x80) == 0x80) {
                v2 += CodedOutputStream.computeMessageSize(6, this.setterValueParameter_);
            }
            if((this.bitField0_ & 0x100) == 0x100) {
                v2 += CodedOutputStream.computeInt32Size(7, this.getterFlags_);
            }
            if((this.bitField0_ & 0x200) == 0x200) {
                v2 += CodedOutputStream.computeInt32Size(8, this.setterFlags_);
            }
            if((this.bitField0_ & 16) == 16) {
                v2 += CodedOutputStream.computeInt32Size(9, this.returnTypeId_);
            }
            if((this.bitField0_ & 0x40) == 0x40) {
                v2 += CodedOutputStream.computeInt32Size(10, this.receiverTypeId_);
            }
            if((this.bitField0_ & 1) == 1) {
                v2 += CodedOutputStream.computeInt32Size(11, this.flags_);
            }
            for(int v4 = 0; v4 < this.contextReceiverType_.size(); ++v4) {
                v2 += CodedOutputStream.computeMessageSize(12, ((MessageLite)this.contextReceiverType_.get(v4)));
            }
            int v6 = 0;
            for(int v5 = 0; v5 < this.contextReceiverTypeId_.size(); ++v5) {
                v6 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.contextReceiverTypeId_.get(v5)))));
            }
            int v7 = this.getContextReceiverTypeIdList().isEmpty() ? v2 + v6 : v2 + v6 + 1 + CodedOutputStream.computeInt32SizeNoTag(v6);
            this.contextReceiverTypeIdMemoizedSerializedSize = v6;
            int v8 = 0;
            for(int v = 0; v < this.versionRequirement_.size(); ++v) {
                v8 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.versionRequirement_.get(v)))));
            }
            int v9 = v7 + v8 + this.getVersionRequirementList().size() * 2 + this.extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = v9;
            return v9;
        }

        public int getSetterFlags() {
            return this.setterFlags_;
        }

        public ValueParameter getSetterValueParameter() {
            return this.setterValueParameter_;
        }

        public TypeParameter getTypeParameter(int v) {
            return (TypeParameter)this.typeParameter_.get(v);
        }

        public int getTypeParameterCount() {
            return this.typeParameter_.size();
        }

        public List getTypeParameterList() {
            return this.typeParameter_;
        }

        public List getVersionRequirementList() {
            return this.versionRequirement_;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasGetterFlags() {
            return (this.bitField0_ & 0x100) == 0x100;
        }

        public boolean hasName() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasOldFlags() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasReceiverType() {
            return (this.bitField0_ & 0x20) == 0x20;
        }

        public boolean hasReceiverTypeId() {
            return (this.bitField0_ & 0x40) == 0x40;
        }

        public boolean hasReturnType() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasReturnTypeId() {
            return (this.bitField0_ & 16) == 16;
        }

        public boolean hasSetterFlags() {
            return (this.bitField0_ & 0x200) == 0x200;
        }

        public boolean hasSetterValueParameter() {
            return (this.bitField0_ & 0x80) == 0x80;
        }

        private void initFields() {
            this.flags_ = 0x206;
            this.oldFlags_ = 0x806;
            this.name_ = 0;
            this.returnType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.returnTypeId_ = 0;
            this.typeParameter_ = Collections.EMPTY_LIST;
            this.receiverType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.receiverTypeId_ = 0;
            this.contextReceiverType_ = Collections.EMPTY_LIST;
            this.contextReceiverTypeId_ = Collections.EMPTY_LIST;
            this.setterValueParameter_ = ValueParameter.getDefaultInstance();
            this.getterFlags_ = 0;
            this.setterFlags_ = 0;
            this.versionRequirement_ = Collections.EMPTY_LIST;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            if(!this.hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(this.hasReturnType() && !this.getReturnType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for(int v1 = 0; v1 < this.getTypeParameterCount(); ++v1) {
                if(!this.getTypeParameter(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(this.hasReceiverType() && !this.getReceiverType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for(int v2 = 0; v2 < this.getContextReceiverTypeCount(); ++v2) {
                if(!this.getContextReceiverType(v2).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(this.hasSetterValueParameter() && !this.getSetterValueParameter().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder.access$16000();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder newBuilder(Property protoBuf$Property0) {
            return Property.newBuilder().mergeFrom(protoBuf$Property0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder newBuilderForType() {
            return Property.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.Builder toBuilder() {
            return Property.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            ExtensionWriter generatedMessageLite$ExtendableMessage$ExtensionWriter0 = this.newExtensionWriter();
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeInt32(1, this.oldFlags_);
            }
            if((this.bitField0_ & 4) == 4) {
                codedOutputStream0.writeInt32(2, this.name_);
            }
            if((this.bitField0_ & 8) == 8) {
                codedOutputStream0.writeMessage(3, this.returnType_);
            }
            for(int v1 = 0; v1 < this.typeParameter_.size(); ++v1) {
                codedOutputStream0.writeMessage(4, ((MessageLite)this.typeParameter_.get(v1)));
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                codedOutputStream0.writeMessage(5, this.receiverType_);
            }
            if((this.bitField0_ & 0x80) == 0x80) {
                codedOutputStream0.writeMessage(6, this.setterValueParameter_);
            }
            if((this.bitField0_ & 0x100) == 0x100) {
                codedOutputStream0.writeInt32(7, this.getterFlags_);
            }
            if((this.bitField0_ & 0x200) == 0x200) {
                codedOutputStream0.writeInt32(8, this.setterFlags_);
            }
            if((this.bitField0_ & 16) == 16) {
                codedOutputStream0.writeInt32(9, this.returnTypeId_);
            }
            if((this.bitField0_ & 0x40) == 0x40) {
                codedOutputStream0.writeInt32(10, this.receiverTypeId_);
            }
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(11, this.flags_);
            }
            for(int v2 = 0; v2 < this.contextReceiverType_.size(); ++v2) {
                codedOutputStream0.writeMessage(12, ((MessageLite)this.contextReceiverType_.get(v2)));
            }
            if(this.getContextReceiverTypeIdList().size() > 0) {
                codedOutputStream0.writeRawVarint32(106);
                codedOutputStream0.writeRawVarint32(this.contextReceiverTypeIdMemoizedSerializedSize);
            }
            for(int v3 = 0; v3 < this.contextReceiverTypeId_.size(); ++v3) {
                codedOutputStream0.writeInt32NoTag(((int)(((Integer)this.contextReceiverTypeId_.get(v3)))));
            }
            for(int v = 0; v < this.versionRequirement_.size(); ++v) {
                codedOutputStream0.writeInt32(0x1F, ((int)(((Integer)this.versionRequirement_.get(v)))));
            }
            generatedMessageLite$ExtendableMessage$ExtensionWriter0.writeUntil(19000, codedOutputStream0);
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class QualifiedNameTable extends GeneratedMessageLite implements ProtoBuf.QualifiedNameTableOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.QualifiedNameTableOrBuilder {
            private int bitField0_;
            private List qualifiedName_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder() {
                this.qualifiedName_ = Collections.EMPTY_LIST;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder access$1400() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder.create();
            }

            public QualifiedNameTable build() {
                QualifiedNameTable protoBuf$QualifiedNameTable0 = this.buildPartial();
                if(!protoBuf$QualifiedNameTable0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder.newUninitializedMessageException(protoBuf$QualifiedNameTable0);
                }
                return protoBuf$QualifiedNameTable0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public QualifiedNameTable buildPartial() {
                QualifiedNameTable protoBuf$QualifiedNameTable0 = new QualifiedNameTable(this, null);
                if((this.bitField0_ & 1) == 1) {
                    this.qualifiedName_ = Collections.unmodifiableList(this.qualifiedName_);
                    this.bitField0_ &= -2;
                }
                protoBuf$QualifiedNameTable0.qualifiedName_ = this.qualifiedName_;
                return protoBuf$QualifiedNameTable0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder();
            }

            private void ensureQualifiedNameIsMutable() {
                if((this.bitField0_ & 1) != 1) {
                    this.qualifiedName_ = new ArrayList(this.qualifiedName_);
                    this.bitField0_ |= 1;
                }
            }

            public QualifiedNameTable getDefaultInstanceForType() {
                return QualifiedNameTable.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public QualifiedName getQualifiedName(int v) {
                return (QualifiedName)this.qualifiedName_.get(v);
            }

            public int getQualifiedNameCount() {
                return this.qualifiedName_.size();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for(int v = 0; v < this.getQualifiedNameCount(); ++v) {
                    if(!this.getQualifiedName(v).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder mergeFrom(QualifiedNameTable protoBuf$QualifiedNameTable0) {
                if(protoBuf$QualifiedNameTable0 == QualifiedNameTable.getDefaultInstance()) {
                    return this;
                }
                if(!protoBuf$QualifiedNameTable0.qualifiedName_.isEmpty()) {
                    if(this.qualifiedName_.isEmpty()) {
                        this.qualifiedName_ = protoBuf$QualifiedNameTable0.qualifiedName_;
                        this.bitField0_ &= -2;
                    }
                    else {
                        this.ensureQualifiedNameIsMutable();
                        this.qualifiedName_.addAll(protoBuf$QualifiedNameTable0.qualifiedName_);
                    }
                }
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$QualifiedNameTable0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                QualifiedNameTable protoBuf$QualifiedNameTable1;
                QualifiedNameTable protoBuf$QualifiedNameTable0;
                try {
                    try {
                        protoBuf$QualifiedNameTable0 = null;
                        protoBuf$QualifiedNameTable1 = (QualifiedNameTable)QualifiedNameTable.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        QualifiedNameTable protoBuf$QualifiedNameTable2 = (QualifiedNameTable)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$QualifiedNameTable0 = protoBuf$QualifiedNameTable2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$QualifiedNameTable0 != null) {
                    this.mergeFrom(protoBuf$QualifiedNameTable0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$QualifiedNameTable1 != null) {
                    this.mergeFrom(protoBuf$QualifiedNameTable1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((QualifiedNameTable)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }
        }

        public static final class QualifiedName extends GeneratedMessageLite implements ProtoBuf.QualifiedNameTable.QualifiedNameOrBuilder {
            public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.QualifiedNameTable.QualifiedNameOrBuilder {
                private int bitField0_;
                private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind kind_;
                private int parentQualifiedName_;
                private int shortName_;

                private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder() {
                    this.parentQualifiedName_ = -1;
                    this.kind_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind.PACKAGE;
                }

                static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder access$700() {
                    return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder.create();
                }

                public QualifiedName build() {
                    QualifiedName protoBuf$QualifiedNameTable$QualifiedName0 = this.buildPartial();
                    if(!protoBuf$QualifiedNameTable$QualifiedName0.isInitialized()) {
                        throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder.newUninitializedMessageException(protoBuf$QualifiedNameTable$QualifiedName0);
                    }
                    return protoBuf$QualifiedNameTable$QualifiedName0;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
                public MessageLite build() {
                    return this.build();
                }

                public QualifiedName buildPartial() {
                    QualifiedName protoBuf$QualifiedNameTable$QualifiedName0 = new QualifiedName(this, null);
                    int v = this.bitField0_;
                    int v1 = (v & 1) == 1 ? 1 : 0;
                    protoBuf$QualifiedNameTable$QualifiedName0.parentQualifiedName_ = this.parentQualifiedName_;
                    if((v & 2) == 2) {
                        v1 |= 2;
                    }
                    protoBuf$QualifiedNameTable$QualifiedName0.shortName_ = this.shortName_;
                    if((v & 4) == 4) {
                        v1 |= 4;
                    }
                    protoBuf$QualifiedNameTable$QualifiedName0.kind_ = this.kind_;
                    protoBuf$QualifiedNameTable$QualifiedName0.bitField0_ = v1;
                    return protoBuf$QualifiedNameTable$QualifiedName0;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public Object clone() throws CloneNotSupportedException {
                    return this.clone();
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder clone() {
                    return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder.create().mergeFrom(this.buildPartial());
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                    return this.clone();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                    return this.clone();
                }

                private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder create() {
                    return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder();
                }

                public QualifiedName getDefaultInstanceForType() {
                    return QualifiedName.getDefaultInstance();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public GeneratedMessageLite getDefaultInstanceForType() {
                    return this.getDefaultInstanceForType();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return this.getDefaultInstanceForType();
                }

                public boolean hasShortName() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return this.hasShortName();
                }

                private void maybeForceBuilderInitialization() {
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder mergeFrom(QualifiedName protoBuf$QualifiedNameTable$QualifiedName0) {
                    if(protoBuf$QualifiedNameTable$QualifiedName0 == QualifiedName.getDefaultInstance()) {
                        return this;
                    }
                    if(protoBuf$QualifiedNameTable$QualifiedName0.hasParentQualifiedName()) {
                        this.setParentQualifiedName(protoBuf$QualifiedNameTable$QualifiedName0.getParentQualifiedName());
                    }
                    if(protoBuf$QualifiedNameTable$QualifiedName0.hasShortName()) {
                        this.setShortName(protoBuf$QualifiedNameTable$QualifiedName0.getShortName());
                    }
                    if(protoBuf$QualifiedNameTable$QualifiedName0.hasKind()) {
                        this.setKind(protoBuf$QualifiedNameTable$QualifiedName0.getKind());
                    }
                    this.setUnknownFields(this.getUnknownFields().concat(protoBuf$QualifiedNameTable$QualifiedName0.unknownFields));
                    return this;
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                    QualifiedName protoBuf$QualifiedNameTable$QualifiedName1;
                    QualifiedName protoBuf$QualifiedNameTable$QualifiedName0;
                    try {
                        try {
                            protoBuf$QualifiedNameTable$QualifiedName0 = null;
                            protoBuf$QualifiedNameTable$QualifiedName1 = (QualifiedName)QualifiedName.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                            goto label_13;
                        }
                        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                            QualifiedName protoBuf$QualifiedNameTable$QualifiedName2 = (QualifiedName)invalidProtocolBufferException0.getUnfinishedMessage();
                            try {
                                throw invalidProtocolBufferException0;
                            }
                            catch(Throwable throwable0) {
                                protoBuf$QualifiedNameTable$QualifiedName0 = protoBuf$QualifiedNameTable$QualifiedName2;
                            }
                        }
                    }
                    catch(Throwable throwable0) {
                    }
                    if(protoBuf$QualifiedNameTable$QualifiedName0 != null) {
                        this.mergeFrom(protoBuf$QualifiedNameTable$QualifiedName0);
                    }
                    throw throwable0;
                label_13:
                    if(protoBuf$QualifiedNameTable$QualifiedName1 != null) {
                        this.mergeFrom(protoBuf$QualifiedNameTable$QualifiedName1);
                    }
                    return this;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                    return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                    return this.mergeFrom(((QualifiedName)generatedMessageLite0));
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                    return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder setKind(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind protoBuf$QualifiedNameTable$QualifiedName$Kind0) {
                    protoBuf$QualifiedNameTable$QualifiedName$Kind0.getClass();
                    this.bitField0_ |= 4;
                    this.kind_ = protoBuf$QualifiedNameTable$QualifiedName$Kind0;
                    return this;
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder setParentQualifiedName(int v) {
                    this.bitField0_ |= 1;
                    this.parentQualifiedName_ = v;
                    return this;
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder setShortName(int v) {
                    this.bitField0_ |= 2;
                    this.shortName_ = v;
                    return this;
                }
            }

            public static enum kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind implements EnumLite {
                CLASS(0, 0),
                PACKAGE(1, 1),
                LOCAL(2, 2);

                private static EnumLiteMap internalValueMap;
                private final int value;

                static {
                    kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind.internalValueMap = new EnumLiteMap() {
                        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind findValueByNumber(int v) {
                            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind.valueOf(v);
                        }

                        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                        public EnumLite findValueByNumber(int v) {
                            return this.findValueByNumber(v);
                        }
                    };
                }

                private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind(int v1, int v2) {
                    this.value = v2;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
                public final int getNumber() {
                    return this.value;
                }

                public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind valueOf(int v) {
                    switch(v) {
                        case 0: {
                            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind.CLASS;
                        }
                        case 1: {
                            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind.PACKAGE;
                        }
                        case 2: {
                            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind.LOCAL;
                        }
                        default: {
                            return null;
                        }
                    }
                }
            }

            public static Parser PARSER;
            private int bitField0_;
            private static final QualifiedName defaultInstance;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind kind_;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            private int parentQualifiedName_;
            private int shortName_;
            private final ByteString unknownFields;

            static {
                QualifiedName.PARSER = new AbstractParser() {
                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                    public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                        return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                    }

                    public QualifiedName parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                        return new QualifiedName(codedInputStream0, extensionRegistryLite0, null);
                    }
                };
                QualifiedName protoBuf$QualifiedNameTable$QualifiedName0 = new QualifiedName(true);
                QualifiedName.defaultInstance = protoBuf$QualifiedNameTable$QualifiedName0;
                protoBuf$QualifiedNameTable$QualifiedName0.initFields();
            }

            private QualifiedName(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                Output byteString$Output0 = ByteString.newOutput();
                CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
                try {
                    boolean z = false;
                    while(!z) {
                        try {
                            try {
                                int v1 = codedInputStream0.readTag();
                                switch(v1) {
                                    case 0: {
                                        z = true;
                                        continue;
                                    }
                                    case 8: {
                                        this.bitField0_ |= 1;
                                        this.parentQualifiedName_ = codedInputStream0.readInt32();
                                        continue;
                                    }
                                    case 16: {
                                        this.bitField0_ |= 2;
                                        this.shortName_ = codedInputStream0.readInt32();
                                        continue;
                                    }
                                    case 24: {
                                        int v2 = codedInputStream0.readEnum();
                                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind protoBuf$QualifiedNameTable$QualifiedName$Kind0 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind.valueOf(v2);
                                        if(protoBuf$QualifiedNameTable$QualifiedName$Kind0 == null) {
                                            codedOutputStream0.writeRawVarint32(24);
                                            codedOutputStream0.writeRawVarint32(v2);
                                            continue;
                                        }
                                        this.bitField0_ |= 4;
                                        this.kind_ = protoBuf$QualifiedNameTable$QualifiedName$Kind0;
                                        continue;
                                    }
                                    default: {
                                        if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                            z = true;
                                            continue;
                                        }
                                        continue;
                                    }
                                }
                            }
                            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                            }
                            catch(IOException iOException0) {
                                throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                            }
                            throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                        }
                        catch(Throwable throwable0) {
                            try {
                                codedOutputStream0.flush();
                            }
                            catch(IOException unused_ex) {
                            }
                            throw throwable0;
                        }
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException unused_ex) {
                    }
                }
                finally {
                    this.unknownFields = byteString$Output0.toByteString();
                }
            }

            QualifiedName(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
                this(codedInputStream0, extensionRegistryLite0);
            }

            private QualifiedName(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
                super(generatedMessageLite$Builder0);
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
            }

            QualifiedName(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
                this(generatedMessageLite$Builder0);
            }

            private QualifiedName(boolean z) {
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = ByteString.EMPTY;
            }

            public static QualifiedName getDefaultInstance() {
                return QualifiedName.defaultInstance;
            }

            public QualifiedName getDefaultInstanceForType() {
                return QualifiedName.defaultInstance;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind getKind() {
                return this.kind_;
            }

            public int getParentQualifiedName() {
                return this.parentQualifiedName_;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
            public Parser getParserForType() {
                return QualifiedName.PARSER;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public int getSerializedSize() {
                int v = this.memoizedSerializedSize;
                if(v != -1) {
                    return v;
                }
                int v1 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.parentQualifiedName_) : 0;
                if((this.bitField0_ & 2) == 2) {
                    v1 += CodedOutputStream.computeInt32Size(2, this.shortName_);
                }
                if((this.bitField0_ & 4) == 4) {
                    v1 += CodedOutputStream.computeEnumSize(3, this.kind_.getNumber());
                }
                int v2 = v1 + this.unknownFields.size();
                this.memoizedSerializedSize = v2;
                return v2;
            }

            public int getShortName() {
                return this.shortName_;
            }

            public boolean hasKind() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean hasParentQualifiedName() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasShortName() {
                return (this.bitField0_ & 2) == 2;
            }

            private void initFields() {
                this.parentQualifiedName_ = -1;
                this.shortName_ = 0;
                this.kind_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind.PACKAGE;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                int v = this.memoizedIsInitialized;
                if(v == 1) {
                    return true;
                }
                if(v == 0) {
                    return false;
                }
                if(!this.hasShortName()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder newBuilder() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder.access$700();
            }

            public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder newBuilder(QualifiedName protoBuf$QualifiedNameTable$QualifiedName0) {
                return QualifiedName.newBuilder().mergeFrom(protoBuf$QualifiedNameTable$QualifiedName0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder newBuilderForType() {
                return QualifiedName.newBuilder();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
                return this.newBuilderForType();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Builder toBuilder() {
                return QualifiedName.newBuilder(this);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
                return this.toBuilder();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
                this.getSerializedSize();
                if((this.bitField0_ & 1) == 1) {
                    codedOutputStream0.writeInt32(1, this.parentQualifiedName_);
                }
                if((this.bitField0_ & 2) == 2) {
                    codedOutputStream0.writeInt32(2, this.shortName_);
                }
                if((this.bitField0_ & 4) == 4) {
                    codedOutputStream0.writeEnum(3, this.kind_.getNumber());
                }
                codedOutputStream0.writeRawBytes(this.unknownFields);
            }
        }

        public static Parser PARSER;
        private static final QualifiedNameTable defaultInstance;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List qualifiedName_;
        private final ByteString unknownFields;

        static {
            QualifiedNameTable.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public QualifiedNameTable parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new QualifiedNameTable(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            QualifiedNameTable protoBuf$QualifiedNameTable0 = new QualifiedNameTable(true);
            QualifiedNameTable.defaultInstance = protoBuf$QualifiedNameTable0;
            protoBuf$QualifiedNameTable0.initFields();
        }

        private QualifiedNameTable(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            boolean z = false;
            boolean z1 = false;
            while(!z1) {
                try {
                    try {
                        int v = codedInputStream0.readTag();
                        switch(v) {
                            case 0: {
                                z1 = true;
                                continue;
                            }
                            case 10: {
                                break;
                            }
                            default: {
                                if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v)) {
                                    z1 = true;
                                    continue;
                                }
                                continue;
                            }
                        }
                        if(!z) {
                            this.qualifiedName_ = new ArrayList();
                            z = true;
                        }
                        this.qualifiedName_.add(codedInputStream0.readMessage(QualifiedName.PARSER, extensionRegistryLite0));
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if(z) {
                        this.qualifiedName_ = Collections.unmodifiableList(this.qualifiedName_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    throw throwable0;
                }
            }
            if(z) {
                this.qualifiedName_ = Collections.unmodifiableList(this.qualifiedName_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
        }

        QualifiedNameTable(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private QualifiedNameTable(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        QualifiedNameTable(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private QualifiedNameTable(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static QualifiedNameTable getDefaultInstance() {
            return QualifiedNameTable.defaultInstance;
        }

        public QualifiedNameTable getDefaultInstanceForType() {
            return QualifiedNameTable.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return QualifiedNameTable.PARSER;
        }

        public QualifiedName getQualifiedName(int v) {
            return (QualifiedName)this.qualifiedName_.get(v);
        }

        public int getQualifiedNameCount() {
            return this.qualifiedName_.size();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v2 = 0;
            for(int v1 = 0; v1 < this.qualifiedName_.size(); ++v1) {
                v2 += CodedOutputStream.computeMessageSize(1, ((MessageLite)this.qualifiedName_.get(v1)));
            }
            int v3 = v2 + this.unknownFields.size();
            this.memoizedSerializedSize = v3;
            return v3;
        }

        private void initFields() {
            this.qualifiedName_ = Collections.EMPTY_LIST;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            for(int v1 = 0; v1 < this.getQualifiedNameCount(); ++v1) {
                if(!this.getQualifiedName(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder.access$1400();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder newBuilder(QualifiedNameTable protoBuf$QualifiedNameTable0) {
            return QualifiedNameTable.newBuilder().mergeFrom(protoBuf$QualifiedNameTable0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder newBuilderForType() {
            return QualifiedNameTable.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.Builder toBuilder() {
            return QualifiedNameTable.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            for(int v = 0; v < this.qualifiedName_.size(); ++v) {
                codedOutputStream0.writeMessage(1, ((MessageLite)this.qualifiedName_.get(v)));
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class StringTable extends GeneratedMessageLite implements ProtoBuf.StringTableOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.StringTableOrBuilder {
            private int bitField0_;
            private LazyStringList string_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder() {
                this.string_ = LazyStringArrayList.EMPTY;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder access$100() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder.create();
            }

            public StringTable build() {
                StringTable protoBuf$StringTable0 = this.buildPartial();
                if(!protoBuf$StringTable0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder.newUninitializedMessageException(protoBuf$StringTable0);
                }
                return protoBuf$StringTable0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public StringTable buildPartial() {
                StringTable protoBuf$StringTable0 = new StringTable(this, null);
                if((this.bitField0_ & 1) == 1) {
                    this.string_ = this.string_.getUnmodifiableView();
                    this.bitField0_ &= -2;
                }
                protoBuf$StringTable0.string_ = this.string_;
                return protoBuf$StringTable0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder();
            }

            private void ensureStringIsMutable() {
                if((this.bitField0_ & 1) != 1) {
                    this.string_ = new LazyStringArrayList(this.string_);
                    this.bitField0_ |= 1;
                }
            }

            public StringTable getDefaultInstanceForType() {
                return StringTable.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder mergeFrom(StringTable protoBuf$StringTable0) {
                if(protoBuf$StringTable0 == StringTable.getDefaultInstance()) {
                    return this;
                }
                if(!protoBuf$StringTable0.string_.isEmpty()) {
                    if(this.string_.isEmpty()) {
                        this.string_ = protoBuf$StringTable0.string_;
                        this.bitField0_ &= -2;
                    }
                    else {
                        this.ensureStringIsMutable();
                        this.string_.addAll(protoBuf$StringTable0.string_);
                    }
                }
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$StringTable0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                StringTable protoBuf$StringTable1;
                StringTable protoBuf$StringTable0;
                try {
                    try {
                        protoBuf$StringTable0 = null;
                        protoBuf$StringTable1 = (StringTable)StringTable.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        StringTable protoBuf$StringTable2 = (StringTable)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$StringTable0 = protoBuf$StringTable2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$StringTable0 != null) {
                    this.mergeFrom(protoBuf$StringTable0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$StringTable1 != null) {
                    this.mergeFrom(protoBuf$StringTable1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((StringTable)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }
        }

        public static Parser PARSER;
        private static final StringTable defaultInstance;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private LazyStringList string_;
        private final ByteString unknownFields;

        static {
            StringTable.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public StringTable parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new StringTable(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            StringTable protoBuf$StringTable0 = new StringTable(true);
            StringTable.defaultInstance = protoBuf$StringTable0;
            protoBuf$StringTable0.initFields();
        }

        private StringTable(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            boolean z = false;
            boolean z1 = false;
            while(!z1) {
                try {
                    try {
                        int v = codedInputStream0.readTag();
                        switch(v) {
                            case 0: {
                                z1 = true;
                                continue;
                            }
                            case 10: {
                                break;
                            }
                            default: {
                                if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v)) {
                                    z1 = true;
                                    continue;
                                }
                                continue;
                            }
                        }
                        ByteString byteString0 = codedInputStream0.readBytes();
                        if(!z) {
                            this.string_ = new LazyStringArrayList();
                            z = true;
                        }
                        this.string_.add(byteString0);
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if(z) {
                        this.string_ = this.string_.getUnmodifiableView();
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    throw throwable0;
                }
            }
            if(z) {
                this.string_ = this.string_.getUnmodifiableView();
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
        }

        StringTable(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private StringTable(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        StringTable(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private StringTable(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static StringTable getDefaultInstance() {
            return StringTable.defaultInstance;
        }

        public StringTable getDefaultInstanceForType() {
            return StringTable.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return StringTable.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v2 = 0;
            for(int v1 = 0; v1 < this.string_.size(); ++v1) {
                v2 += CodedOutputStream.computeBytesSizeNoTag(this.string_.getByteString(v1));
            }
            int v3 = v2 + this.getStringList().size() + this.unknownFields.size();
            this.memoizedSerializedSize = v3;
            return v3;
        }

        public String getString(int v) {
            return (String)this.string_.get(v);
        }

        public ProtocolStringList getStringList() {
            return this.string_;
        }

        private void initFields() {
            this.string_ = LazyStringArrayList.EMPTY;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder.access$100();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder newBuilder(StringTable protoBuf$StringTable0) {
            return StringTable.newBuilder().mergeFrom(protoBuf$StringTable0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder newBuilderForType() {
            return StringTable.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.Builder toBuilder() {
            return StringTable.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            for(int v = 0; v < this.string_.size(); ++v) {
                codedOutputStream0.writeBytes(1, this.string_.getByteString(v));
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type extends ExtendableMessage implements ProtoBuf.TypeOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument extends GeneratedMessageLite implements ProtoBuf.Type.ArgumentOrBuilder {
            public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.Type.ArgumentOrBuilder {
                private int bitField0_;
                private Projection projection_;
                private int typeId_;
                private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type type_;

                private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder() {
                    this.projection_ = Projection.INV;
                    this.type_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                }

                static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder access$5000() {
                    return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder.create();
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument build() {
                    kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument protoBuf$Type$Argument0 = this.buildPartial();
                    if(!protoBuf$Type$Argument0.isInitialized()) {
                        throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder.newUninitializedMessageException(protoBuf$Type$Argument0);
                    }
                    return protoBuf$Type$Argument0;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
                public MessageLite build() {
                    return this.build();
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument buildPartial() {
                    kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument protoBuf$Type$Argument0 = new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument(this, null);
                    int v = this.bitField0_;
                    int v1 = (v & 1) == 1 ? 1 : 0;
                    protoBuf$Type$Argument0.projection_ = this.projection_;
                    if((v & 2) == 2) {
                        v1 |= 2;
                    }
                    protoBuf$Type$Argument0.type_ = this.type_;
                    if((v & 4) == 4) {
                        v1 |= 4;
                    }
                    protoBuf$Type$Argument0.typeId_ = this.typeId_;
                    protoBuf$Type$Argument0.bitField0_ = v1;
                    return protoBuf$Type$Argument0;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public Object clone() throws CloneNotSupportedException {
                    return this.clone();
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder clone() {
                    return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder.create().mergeFrom(this.buildPartial());
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                    return this.clone();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                    return this.clone();
                }

                private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder create() {
                    return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder();
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument getDefaultInstanceForType() {
                    return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.getDefaultInstance();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public GeneratedMessageLite getDefaultInstanceForType() {
                    return this.getDefaultInstanceForType();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return this.getDefaultInstanceForType();
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getType() {
                    return this.type_;
                }

                public boolean hasType() {
                    return (this.bitField0_ & 2) == 2;
                }

                // 去混淆评级： 低(20)
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return !this.hasType() || this.getType().isInitialized();
                }

                private void maybeForceBuilderInitialization() {
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder mergeFrom(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument protoBuf$Type$Argument0) {
                    if(protoBuf$Type$Argument0 == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.getDefaultInstance()) {
                        return this;
                    }
                    if(protoBuf$Type$Argument0.hasProjection()) {
                        this.setProjection(protoBuf$Type$Argument0.getProjection());
                    }
                    if(protoBuf$Type$Argument0.hasType()) {
                        this.mergeType(protoBuf$Type$Argument0.getType());
                    }
                    if(protoBuf$Type$Argument0.hasTypeId()) {
                        this.setTypeId(protoBuf$Type$Argument0.getTypeId());
                    }
                    this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Type$Argument0.unknownFields));
                    return this;
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                    kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument protoBuf$Type$Argument1;
                    kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument protoBuf$Type$Argument0;
                    try {
                        try {
                            protoBuf$Type$Argument0 = null;
                            protoBuf$Type$Argument1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument)kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                            goto label_13;
                        }
                        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument protoBuf$Type$Argument2 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument)invalidProtocolBufferException0.getUnfinishedMessage();
                            try {
                                throw invalidProtocolBufferException0;
                            }
                            catch(Throwable throwable0) {
                                protoBuf$Type$Argument0 = protoBuf$Type$Argument2;
                            }
                        }
                    }
                    catch(Throwable throwable0) {
                    }
                    if(protoBuf$Type$Argument0 != null) {
                        this.mergeFrom(protoBuf$Type$Argument0);
                    }
                    throw throwable0;
                label_13:
                    if(protoBuf$Type$Argument1 != null) {
                        this.mergeFrom(protoBuf$Type$Argument1);
                    }
                    return this;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                    return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                    return this.mergeFrom(((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument)generatedMessageLite0));
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                    return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder mergeType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                    this.type_ = (this.bitField0_ & 2) != 2 || this.type_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.type_).mergeFrom(protoBuf$Type0).buildPartial();
                    this.bitField0_ |= 2;
                    return this;
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder setProjection(Projection protoBuf$Type$Argument$Projection0) {
                    protoBuf$Type$Argument$Projection0.getClass();
                    this.bitField0_ |= 1;
                    this.projection_ = protoBuf$Type$Argument$Projection0;
                    return this;
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder setTypeId(int v) {
                    this.bitField0_ |= 4;
                    this.typeId_ = v;
                    return this;
                }
            }

            public static enum Projection implements EnumLite {
                IN(0, 0),
                OUT(1, 1),
                INV(2, 2),
                STAR(3, 3);

                private static EnumLiteMap internalValueMap;
                private final int value;

                static {
                    Projection.internalValueMap = new EnumLiteMap() {
                        public Projection findValueByNumber(int v) {
                            return Projection.valueOf(v);
                        }

                        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                        public EnumLite findValueByNumber(int v) {
                            return this.findValueByNumber(v);
                        }
                    };
                }

                private Projection(int v1, int v2) {
                    this.value = v2;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
                public final int getNumber() {
                    return this.value;
                }

                public static Projection valueOf(int v) {
                    switch(v) {
                        case 0: {
                            return Projection.IN;
                        }
                        case 1: {
                            return Projection.OUT;
                        }
                        case 2: {
                            return Projection.INV;
                        }
                        case 3: {
                            return Projection.STAR;
                        }
                        default: {
                            return null;
                        }
                    }
                }
            }

            public static Parser PARSER;
            private int bitField0_;
            private static final kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument defaultInstance;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            private Projection projection_;
            private int typeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type type_;
            private final ByteString unknownFields;

            static {
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.PARSER = new AbstractParser() {
                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                    public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                        return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                    }

                    public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                        return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument(codedInputStream0, extensionRegistryLite0, null);
                    }
                };
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument protoBuf$Type$Argument0 = new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument(true);
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.defaultInstance = protoBuf$Type$Argument0;
                protoBuf$Type$Argument0.initFields();
            }

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                Output byteString$Output0 = ByteString.newOutput();
                CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
                try {
                    boolean z = false;
                    while(!z) {
                        try {
                            try {
                                int v1 = codedInputStream0.readTag();
                                switch(v1) {
                                    case 0: {
                                        z = true;
                                        continue;
                                    }
                                    case 8: {
                                        int v2 = codedInputStream0.readEnum();
                                        Projection protoBuf$Type$Argument$Projection0 = Projection.valueOf(v2);
                                        if(protoBuf$Type$Argument$Projection0 == null) {
                                            codedOutputStream0.writeRawVarint32(8);
                                            codedOutputStream0.writeRawVarint32(v2);
                                        }
                                        else {
                                            this.bitField0_ |= 1;
                                            this.projection_ = protoBuf$Type$Argument$Projection0;
                                        }
                                        continue;
                                    }
                                    case 18: {
                                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder protoBuf$Type$Builder0 = (this.bitField0_ & 2) == 2 ? this.type_.toBuilder() : null;
                                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                        this.type_ = protoBuf$Type0;
                                        if(protoBuf$Type$Builder0 != null) {
                                            protoBuf$Type$Builder0.mergeFrom(protoBuf$Type0);
                                            this.type_ = protoBuf$Type$Builder0.buildPartial();
                                        }
                                        this.bitField0_ |= 2;
                                        continue;
                                    }
                                    case 24: {
                                        this.bitField0_ |= 4;
                                        this.typeId_ = codedInputStream0.readInt32();
                                        continue;
                                    }
                                    default: {
                                        if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                            z = true;
                                            continue;
                                        }
                                        continue;
                                    }
                                }
                            }
                            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                            }
                            catch(IOException iOException0) {
                                throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                            }
                            throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                        }
                        catch(Throwable throwable0) {
                            try {
                                codedOutputStream0.flush();
                            }
                            catch(IOException unused_ex) {
                            }
                            throw throwable0;
                        }
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException unused_ex) {
                    }
                }
                finally {
                    this.unknownFields = byteString$Output0.toByteString();
                }
            }

            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
                this(codedInputStream0, extensionRegistryLite0);
            }

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
                super(generatedMessageLite$Builder0);
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
            }

            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
                this(generatedMessageLite$Builder0);
            }

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument(boolean z) {
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = ByteString.EMPTY;
            }

            public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument getDefaultInstance() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.defaultInstance;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument getDefaultInstanceForType() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.defaultInstance;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
            public Parser getParserForType() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.PARSER;
            }

            public Projection getProjection() {
                return this.projection_;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public int getSerializedSize() {
                int v = this.memoizedSerializedSize;
                if(v != -1) {
                    return v;
                }
                int v1 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeEnumSize(1, this.projection_.getNumber()) : 0;
                if((this.bitField0_ & 2) == 2) {
                    v1 += CodedOutputStream.computeMessageSize(2, this.type_);
                }
                if((this.bitField0_ & 4) == 4) {
                    v1 += CodedOutputStream.computeInt32Size(3, this.typeId_);
                }
                int v2 = v1 + this.unknownFields.size();
                this.memoizedSerializedSize = v2;
                return v2;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getType() {
                return this.type_;
            }

            public int getTypeId() {
                return this.typeId_;
            }

            public boolean hasProjection() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasType() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasTypeId() {
                return (this.bitField0_ & 4) == 4;
            }

            private void initFields() {
                this.projection_ = Projection.INV;
                this.type_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                this.typeId_ = 0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                int v = this.memoizedIsInitialized;
                if(v == 1) {
                    return true;
                }
                if(v == 0) {
                    return false;
                }
                if(this.hasType() && !this.getType().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder newBuilder() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder.access$5000();
            }

            public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder newBuilder(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument protoBuf$Type$Argument0) {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.newBuilder().mergeFrom(protoBuf$Type$Argument0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder newBuilderForType() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.newBuilder();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
                return this.newBuilderForType();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Builder toBuilder() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.newBuilder(this);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
                return this.toBuilder();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
                this.getSerializedSize();
                if((this.bitField0_ & 1) == 1) {
                    codedOutputStream0.writeEnum(1, this.projection_.getNumber());
                }
                if((this.bitField0_ & 2) == 2) {
                    codedOutputStream0.writeMessage(2, this.type_);
                }
                if((this.bitField0_ & 4) == 4) {
                    codedOutputStream0.writeInt32(3, this.typeId_);
                }
                codedOutputStream0.writeRawBytes(this.unknownFields);
            }
        }

        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder extends ExtendableBuilder implements ProtoBuf.TypeOrBuilder {
            private int abbreviatedTypeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type abbreviatedType_;
            private List argument_;
            private int bitField0_;
            private int className_;
            private int flags_;
            private int flexibleTypeCapabilitiesId_;
            private int flexibleUpperBoundId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type flexibleUpperBound_;
            private boolean nullable_;
            private int outerTypeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type outerType_;
            private int typeAliasName_;
            private int typeParameterName_;
            private int typeParameter_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder() {
                this.argument_ = Collections.EMPTY_LIST;
                this.flexibleUpperBound_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                this.outerType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                this.abbreviatedType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder access$5700() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder.create();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type build() {
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0 = this.buildPartial();
                if(!protoBuf$Type0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder.newUninitializedMessageException(protoBuf$Type0);
                }
                return protoBuf$Type0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type buildPartial() {
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0 = new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type(this, null);
                int v = this.bitField0_;
                int v1 = 1;
                if((v & 1) == 1) {
                    this.argument_ = Collections.unmodifiableList(this.argument_);
                    this.bitField0_ &= -2;
                }
                protoBuf$Type0.argument_ = this.argument_;
                if((v & 2) != 2) {
                    v1 = 0;
                }
                protoBuf$Type0.nullable_ = this.nullable_;
                if((v & 4) == 4) {
                    v1 |= 2;
                }
                protoBuf$Type0.flexibleTypeCapabilitiesId_ = this.flexibleTypeCapabilitiesId_;
                if((v & 8) == 8) {
                    v1 |= 4;
                }
                protoBuf$Type0.flexibleUpperBound_ = this.flexibleUpperBound_;
                if((v & 16) == 16) {
                    v1 |= 8;
                }
                protoBuf$Type0.flexibleUpperBoundId_ = this.flexibleUpperBoundId_;
                if((v & 0x20) == 0x20) {
                    v1 |= 16;
                }
                protoBuf$Type0.className_ = this.className_;
                if((v & 0x40) == 0x40) {
                    v1 |= 0x20;
                }
                protoBuf$Type0.typeParameter_ = this.typeParameter_;
                if((v & 0x80) == 0x80) {
                    v1 |= 0x40;
                }
                protoBuf$Type0.typeParameterName_ = this.typeParameterName_;
                if((v & 0x100) == 0x100) {
                    v1 |= 0x80;
                }
                protoBuf$Type0.typeAliasName_ = this.typeAliasName_;
                if((v & 0x200) == 0x200) {
                    v1 |= 0x100;
                }
                protoBuf$Type0.outerType_ = this.outerType_;
                if((v & 0x400) == 0x400) {
                    v1 |= 0x200;
                }
                protoBuf$Type0.outerTypeId_ = this.outerTypeId_;
                if((v & 0x800) == 0x800) {
                    v1 |= 0x400;
                }
                protoBuf$Type0.abbreviatedType_ = this.abbreviatedType_;
                if((v & 0x1000) == 0x1000) {
                    v1 |= 0x800;
                }
                protoBuf$Type0.abbreviatedTypeId_ = this.abbreviatedTypeId_;
                if((v & 0x2000) == 0x2000) {
                    v1 |= 0x1000;
                }
                protoBuf$Type0.flags_ = this.flags_;
                protoBuf$Type0.bitField0_ = v1;
                return protoBuf$Type0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public ExtendableBuilder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder();
            }

            private void ensureArgumentIsMutable() {
                if((this.bitField0_ & 1) != 1) {
                    this.argument_ = new ArrayList(this.argument_);
                    this.bitField0_ |= 1;
                }
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getAbbreviatedType() {
                return this.abbreviatedType_;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument getArgument(int v) {
                return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument)this.argument_.get(v);
            }

            public int getArgumentCount() {
                return this.argument_.size();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getDefaultInstanceForType() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getFlexibleUpperBound() {
                return this.flexibleUpperBound_;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getOuterType() {
                return this.outerType_;
            }

            public boolean hasAbbreviatedType() {
                return (this.bitField0_ & 0x800) == 0x800;
            }

            public boolean hasFlexibleUpperBound() {
                return (this.bitField0_ & 8) == 8;
            }

            public boolean hasOuterType() {
                return (this.bitField0_ & 0x200) == 0x200;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for(int v = 0; v < this.getArgumentCount(); ++v) {
                    if(!this.getArgument(v).isInitialized()) {
                        return false;
                    }
                }
                if(this.hasFlexibleUpperBound() && !this.getFlexibleUpperBound().isInitialized()) {
                    return false;
                }
                if(this.hasOuterType() && !this.getOuterType().isInitialized()) {
                    return false;
                }
                return !this.hasAbbreviatedType() || this.getAbbreviatedType().isInitialized() ? this.extensionsAreInitialized() : false;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder mergeAbbreviatedType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.abbreviatedType_ = (this.bitField0_ & 0x800) != 0x800 || this.abbreviatedType_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.abbreviatedType_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 0x800;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder mergeFlexibleUpperBound(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.flexibleUpperBound_ = (this.bitField0_ & 8) != 8 || this.flexibleUpperBound_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.flexibleUpperBound_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 8;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder mergeFrom(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                if(protoBuf$Type0 == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance()) {
                    return this;
                }
                if(!protoBuf$Type0.argument_.isEmpty()) {
                    if(this.argument_.isEmpty()) {
                        this.argument_ = protoBuf$Type0.argument_;
                        this.bitField0_ &= -2;
                    }
                    else {
                        this.ensureArgumentIsMutable();
                        this.argument_.addAll(protoBuf$Type0.argument_);
                    }
                }
                if(protoBuf$Type0.hasNullable()) {
                    this.setNullable(protoBuf$Type0.getNullable());
                }
                if(protoBuf$Type0.hasFlexibleTypeCapabilitiesId()) {
                    this.setFlexibleTypeCapabilitiesId(protoBuf$Type0.getFlexibleTypeCapabilitiesId());
                }
                if(protoBuf$Type0.hasFlexibleUpperBound()) {
                    this.mergeFlexibleUpperBound(protoBuf$Type0.getFlexibleUpperBound());
                }
                if(protoBuf$Type0.hasFlexibleUpperBoundId()) {
                    this.setFlexibleUpperBoundId(protoBuf$Type0.getFlexibleUpperBoundId());
                }
                if(protoBuf$Type0.hasClassName()) {
                    this.setClassName(protoBuf$Type0.getClassName());
                }
                if(protoBuf$Type0.hasTypeParameter()) {
                    this.setTypeParameter(protoBuf$Type0.getTypeParameter());
                }
                if(protoBuf$Type0.hasTypeParameterName()) {
                    this.setTypeParameterName(protoBuf$Type0.getTypeParameterName());
                }
                if(protoBuf$Type0.hasTypeAliasName()) {
                    this.setTypeAliasName(protoBuf$Type0.getTypeAliasName());
                }
                if(protoBuf$Type0.hasOuterType()) {
                    this.mergeOuterType(protoBuf$Type0.getOuterType());
                }
                if(protoBuf$Type0.hasOuterTypeId()) {
                    this.setOuterTypeId(protoBuf$Type0.getOuterTypeId());
                }
                if(protoBuf$Type0.hasAbbreviatedType()) {
                    this.mergeAbbreviatedType(protoBuf$Type0.getAbbreviatedType());
                }
                if(protoBuf$Type0.hasAbbreviatedTypeId()) {
                    this.setAbbreviatedTypeId(protoBuf$Type0.getAbbreviatedTypeId());
                }
                if(protoBuf$Type0.hasFlags()) {
                    this.setFlags(protoBuf$Type0.getFlags());
                }
                this.mergeExtensionFields(protoBuf$Type0);
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$Type0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type1;
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0;
                try {
                    try {
                        protoBuf$Type0 = null;
                        protoBuf$Type1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type2 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$Type0 = protoBuf$Type2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$Type0 != null) {
                    this.mergeFrom(protoBuf$Type0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$Type1 != null) {
                    this.mergeFrom(protoBuf$Type1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder mergeOuterType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.outerType_ = (this.bitField0_ & 0x200) != 0x200 || this.outerType_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.outerType_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 0x200;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder setAbbreviatedTypeId(int v) {
                this.bitField0_ |= 0x1000;
                this.abbreviatedTypeId_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder setClassName(int v) {
                this.bitField0_ |= 0x20;
                this.className_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder setFlags(int v) {
                this.bitField0_ |= 0x2000;
                this.flags_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder setFlexibleTypeCapabilitiesId(int v) {
                this.bitField0_ |= 4;
                this.flexibleTypeCapabilitiesId_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder setFlexibleUpperBoundId(int v) {
                this.bitField0_ |= 16;
                this.flexibleUpperBoundId_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder setNullable(boolean z) {
                this.bitField0_ |= 2;
                this.nullable_ = z;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder setOuterTypeId(int v) {
                this.bitField0_ |= 0x400;
                this.outerTypeId_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder setTypeAliasName(int v) {
                this.bitField0_ |= 0x100;
                this.typeAliasName_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder setTypeParameter(int v) {
                this.bitField0_ |= 0x40;
                this.typeParameter_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder setTypeParameterName(int v) {
                this.bitField0_ |= 0x80;
                this.typeParameterName_ = v;
                return this;
            }
        }

        public static Parser PARSER;
        private int abbreviatedTypeId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type abbreviatedType_;
        private List argument_;
        private int bitField0_;
        private int className_;
        private static final kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type defaultInstance;
        private int flags_;
        private int flexibleTypeCapabilitiesId_;
        private int flexibleUpperBoundId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type flexibleUpperBound_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private boolean nullable_;
        private int outerTypeId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type outerType_;
        private int typeAliasName_;
        private int typeParameterName_;
        private int typeParameter_;
        private final ByteString unknownFields;

        static {
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0 = new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type(true);
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.defaultInstance = protoBuf$Type0;
            protoBuf$Type0.initFields();
        }

        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            boolean z = false;
            boolean z1 = false;
            while(!z1) {
                try {
                    try {
                        int v = codedInputStream0.readTag();
                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder protoBuf$Type$Builder0 = null;
                        switch(v) {
                            case 0: {
                                z1 = true;
                                continue;
                            }
                            case 8: {
                                this.bitField0_ |= 0x1000;
                                this.flags_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 18: {
                                if(!z) {
                                    this.argument_ = new ArrayList();
                                    z = true;
                                }
                                this.argument_.add(codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 24: {
                                this.bitField0_ |= 1;
                                this.nullable_ = codedInputStream0.readBool();
                                continue;
                            }
                            case 0x20: {
                                this.bitField0_ |= 2;
                                this.flexibleTypeCapabilitiesId_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 42: {
                                if((this.bitField0_ & 4) == 4) {
                                    protoBuf$Type$Builder0 = this.flexibleUpperBound_.toBuilder();
                                }
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                this.flexibleUpperBound_ = protoBuf$Type0;
                                if(protoBuf$Type$Builder0 != null) {
                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type0);
                                    this.flexibleUpperBound_ = protoBuf$Type$Builder0.buildPartial();
                                }
                                this.bitField0_ |= 4;
                                continue;
                            }
                            case 0x30: {
                                this.bitField0_ |= 16;
                                this.className_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 56: {
                                this.bitField0_ |= 0x20;
                                this.typeParameter_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 0x40: {
                                this.bitField0_ |= 8;
                                this.flexibleUpperBoundId_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 72: {
                                this.bitField0_ |= 0x40;
                                this.typeParameterName_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 82: {
                                if((this.bitField0_ & 0x100) == 0x100) {
                                    protoBuf$Type$Builder0 = this.outerType_.toBuilder();
                                }
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                this.outerType_ = protoBuf$Type1;
                                if(protoBuf$Type$Builder0 != null) {
                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type1);
                                    this.outerType_ = protoBuf$Type$Builder0.buildPartial();
                                }
                                this.bitField0_ |= 0x100;
                                continue;
                            }
                            case 88: {
                                this.bitField0_ |= 0x200;
                                this.outerTypeId_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 0x60: {
                                this.bitField0_ |= 0x80;
                                this.typeAliasName_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 106: {
                                if((this.bitField0_ & 0x400) == 0x400) {
                                    protoBuf$Type$Builder0 = this.abbreviatedType_.toBuilder();
                                }
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type2 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                this.abbreviatedType_ = protoBuf$Type2;
                                if(protoBuf$Type$Builder0 != null) {
                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type2);
                                    this.abbreviatedType_ = protoBuf$Type$Builder0.buildPartial();
                                }
                                this.bitField0_ |= 0x400;
                                continue;
                            }
                            case 0x70: {
                                this.bitField0_ |= 0x800;
                                this.abbreviatedTypeId_ = codedInputStream0.readInt32();
                                break;
                            }
                            default: {
                                if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v)) {
                                    z1 = true;
                                    continue;
                                }
                            }
                        }
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if(z) {
                        this.argument_ = Collections.unmodifiableList(this.argument_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    this.makeExtensionsImmutable();
                    throw throwable0;
                }
            }
            if(z) {
                this.argument_ = Collections.unmodifiableList(this.argument_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
            this.makeExtensionsImmutable();
        }

        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type(ExtendableBuilder generatedMessageLite$ExtendableBuilder0) {
            super(generatedMessageLite$ExtendableBuilder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$ExtendableBuilder0.getUnknownFields();
        }

        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type(ExtendableBuilder generatedMessageLite$ExtendableBuilder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$ExtendableBuilder0);
        }

        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getAbbreviatedType() {
            return this.abbreviatedType_;
        }

        public int getAbbreviatedTypeId() {
            return this.abbreviatedTypeId_;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument getArgument(int v) {
            return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument)this.argument_.get(v);
        }

        public int getArgumentCount() {
            return this.argument_.size();
        }

        public List getArgumentList() {
            return this.argument_;
        }

        public int getClassName() {
            return this.className_;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getDefaultInstance() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.defaultInstance;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getDefaultInstanceForType() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getFlags() {
            return this.flags_;
        }

        public int getFlexibleTypeCapabilitiesId() {
            return this.flexibleTypeCapabilitiesId_;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getFlexibleUpperBound() {
            return this.flexibleUpperBound_;
        }

        public int getFlexibleUpperBoundId() {
            return this.flexibleUpperBoundId_;
        }

        public boolean getNullable() {
            return this.nullable_;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getOuterType() {
            return this.outerType_;
        }

        public int getOuterTypeId() {
            return this.outerTypeId_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v1 = this.memoizedSerializedSize;
            if(v1 != -1) {
                return v1;
            }
            int v2 = (this.bitField0_ & 0x1000) == 0x1000 ? CodedOutputStream.computeInt32Size(1, this.flags_) : 0;
            for(int v = 0; v < this.argument_.size(); ++v) {
                v2 += CodedOutputStream.computeMessageSize(2, ((MessageLite)this.argument_.get(v)));
            }
            if((this.bitField0_ & 1) == 1) {
                v2 += CodedOutputStream.computeBoolSize(3, this.nullable_);
            }
            if((this.bitField0_ & 2) == 2) {
                v2 += CodedOutputStream.computeInt32Size(4, this.flexibleTypeCapabilitiesId_);
            }
            if((this.bitField0_ & 4) == 4) {
                v2 += CodedOutputStream.computeMessageSize(5, this.flexibleUpperBound_);
            }
            if((this.bitField0_ & 16) == 16) {
                v2 += CodedOutputStream.computeInt32Size(6, this.className_);
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                v2 += CodedOutputStream.computeInt32Size(7, this.typeParameter_);
            }
            if((this.bitField0_ & 8) == 8) {
                v2 += CodedOutputStream.computeInt32Size(8, this.flexibleUpperBoundId_);
            }
            if((this.bitField0_ & 0x40) == 0x40) {
                v2 += CodedOutputStream.computeInt32Size(9, this.typeParameterName_);
            }
            if((this.bitField0_ & 0x100) == 0x100) {
                v2 += CodedOutputStream.computeMessageSize(10, this.outerType_);
            }
            if((this.bitField0_ & 0x200) == 0x200) {
                v2 += CodedOutputStream.computeInt32Size(11, this.outerTypeId_);
            }
            if((this.bitField0_ & 0x80) == 0x80) {
                v2 += CodedOutputStream.computeInt32Size(12, this.typeAliasName_);
            }
            if((this.bitField0_ & 0x400) == 0x400) {
                v2 += CodedOutputStream.computeMessageSize(13, this.abbreviatedType_);
            }
            if((this.bitField0_ & 0x800) == 0x800) {
                v2 += CodedOutputStream.computeInt32Size(14, this.abbreviatedTypeId_);
            }
            int v3 = v2 + this.extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = v3;
            return v3;
        }

        public int getTypeAliasName() {
            return this.typeAliasName_;
        }

        public int getTypeParameter() {
            return this.typeParameter_;
        }

        public int getTypeParameterName() {
            return this.typeParameterName_;
        }

        public boolean hasAbbreviatedType() {
            return (this.bitField0_ & 0x400) == 0x400;
        }

        public boolean hasAbbreviatedTypeId() {
            return (this.bitField0_ & 0x800) == 0x800;
        }

        public boolean hasClassName() {
            return (this.bitField0_ & 16) == 16;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 0x1000) == 0x1000;
        }

        public boolean hasFlexibleTypeCapabilitiesId() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasFlexibleUpperBound() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasFlexibleUpperBoundId() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasNullable() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasOuterType() {
            return (this.bitField0_ & 0x100) == 0x100;
        }

        public boolean hasOuterTypeId() {
            return (this.bitField0_ & 0x200) == 0x200;
        }

        public boolean hasTypeAliasName() {
            return (this.bitField0_ & 0x80) == 0x80;
        }

        public boolean hasTypeParameter() {
            return (this.bitField0_ & 0x20) == 0x20;
        }

        public boolean hasTypeParameterName() {
            return (this.bitField0_ & 0x40) == 0x40;
        }

        private void initFields() {
            this.argument_ = Collections.EMPTY_LIST;
            this.nullable_ = false;
            this.flexibleTypeCapabilitiesId_ = 0;
            this.flexibleUpperBound_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.flexibleUpperBoundId_ = 0;
            this.className_ = 0;
            this.typeParameter_ = 0;
            this.typeParameterName_ = 0;
            this.typeAliasName_ = 0;
            this.outerType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.outerTypeId_ = 0;
            this.abbreviatedType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.abbreviatedTypeId_ = 0;
            this.flags_ = 0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            for(int v1 = 0; v1 < this.getArgumentCount(); ++v1) {
                if(!this.getArgument(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(this.hasFlexibleUpperBound() && !this.getFlexibleUpperBound().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(this.hasOuterType() && !this.getOuterType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(this.hasAbbreviatedType() && !this.getAbbreviatedType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder.access$5700();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder newBuilder(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder().mergeFrom(protoBuf$Type0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder newBuilderForType() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder toBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            ExtensionWriter generatedMessageLite$ExtendableMessage$ExtensionWriter0 = this.newExtensionWriter();
            if((this.bitField0_ & 0x1000) == 0x1000) {
                codedOutputStream0.writeInt32(1, this.flags_);
            }
            for(int v = 0; v < this.argument_.size(); ++v) {
                codedOutputStream0.writeMessage(2, ((MessageLite)this.argument_.get(v)));
            }
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeBool(3, this.nullable_);
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeInt32(4, this.flexibleTypeCapabilitiesId_);
            }
            if((this.bitField0_ & 4) == 4) {
                codedOutputStream0.writeMessage(5, this.flexibleUpperBound_);
            }
            if((this.bitField0_ & 16) == 16) {
                codedOutputStream0.writeInt32(6, this.className_);
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                codedOutputStream0.writeInt32(7, this.typeParameter_);
            }
            if((this.bitField0_ & 8) == 8) {
                codedOutputStream0.writeInt32(8, this.flexibleUpperBoundId_);
            }
            if((this.bitField0_ & 0x40) == 0x40) {
                codedOutputStream0.writeInt32(9, this.typeParameterName_);
            }
            if((this.bitField0_ & 0x100) == 0x100) {
                codedOutputStream0.writeMessage(10, this.outerType_);
            }
            if((this.bitField0_ & 0x200) == 0x200) {
                codedOutputStream0.writeInt32(11, this.outerTypeId_);
            }
            if((this.bitField0_ & 0x80) == 0x80) {
                codedOutputStream0.writeInt32(12, this.typeAliasName_);
            }
            if((this.bitField0_ & 0x400) == 0x400) {
                codedOutputStream0.writeMessage(13, this.abbreviatedType_);
            }
            if((this.bitField0_ & 0x800) == 0x800) {
                codedOutputStream0.writeInt32(14, this.abbreviatedTypeId_);
            }
            generatedMessageLite$ExtendableMessage$ExtensionWriter0.writeUntil(200, codedOutputStream0);
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class TypeAlias extends ExtendableMessage implements ProtoBuf.TypeAliasOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder extends ExtendableBuilder implements ProtoBuf.TypeAliasOrBuilder {
            private List annotation_;
            private int bitField0_;
            private int expandedTypeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type expandedType_;
            private int flags_;
            private int name_;
            private List typeParameter_;
            private int underlyingTypeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type underlyingType_;
            private List versionRequirement_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder() {
                this.flags_ = 6;
                this.typeParameter_ = Collections.EMPTY_LIST;
                this.underlyingType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                this.expandedType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                this.annotation_ = Collections.EMPTY_LIST;
                this.versionRequirement_ = Collections.EMPTY_LIST;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder access$19000() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder.create();
            }

            public TypeAlias build() {
                TypeAlias protoBuf$TypeAlias0 = this.buildPartial();
                if(!protoBuf$TypeAlias0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder.newUninitializedMessageException(protoBuf$TypeAlias0);
                }
                return protoBuf$TypeAlias0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public TypeAlias buildPartial() {
                TypeAlias protoBuf$TypeAlias0 = new TypeAlias(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                protoBuf$TypeAlias0.flags_ = this.flags_;
                if((v & 2) == 2) {
                    v1 |= 2;
                }
                protoBuf$TypeAlias0.name_ = this.name_;
                if((this.bitField0_ & 4) == 4) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    this.bitField0_ &= -5;
                }
                protoBuf$TypeAlias0.typeParameter_ = this.typeParameter_;
                if((v & 8) == 8) {
                    v1 |= 4;
                }
                protoBuf$TypeAlias0.underlyingType_ = this.underlyingType_;
                if((v & 16) == 16) {
                    v1 |= 8;
                }
                protoBuf$TypeAlias0.underlyingTypeId_ = this.underlyingTypeId_;
                if((v & 0x20) == 0x20) {
                    v1 |= 16;
                }
                protoBuf$TypeAlias0.expandedType_ = this.expandedType_;
                if((v & 0x40) == 0x40) {
                    v1 |= 0x20;
                }
                protoBuf$TypeAlias0.expandedTypeId_ = this.expandedTypeId_;
                if((this.bitField0_ & 0x80) == 0x80) {
                    this.annotation_ = Collections.unmodifiableList(this.annotation_);
                    this.bitField0_ &= 0xFFFFFF7F;
                }
                protoBuf$TypeAlias0.annotation_ = this.annotation_;
                if((this.bitField0_ & 0x100) == 0x100) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= 0xFFFFFEFF;
                }
                protoBuf$TypeAlias0.versionRequirement_ = this.versionRequirement_;
                protoBuf$TypeAlias0.bitField0_ = v1;
                return protoBuf$TypeAlias0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public ExtendableBuilder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder();
            }

            private void ensureAnnotationIsMutable() {
                if((this.bitField0_ & 0x80) != 0x80) {
                    this.annotation_ = new ArrayList(this.annotation_);
                    this.bitField0_ |= 0x80;
                }
            }

            private void ensureTypeParameterIsMutable() {
                if((this.bitField0_ & 4) != 4) {
                    this.typeParameter_ = new ArrayList(this.typeParameter_);
                    this.bitField0_ |= 4;
                }
            }

            private void ensureVersionRequirementIsMutable() {
                if((this.bitField0_ & 0x100) != 0x100) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 0x100;
                }
            }

            public Annotation getAnnotation(int v) {
                return (Annotation)this.annotation_.get(v);
            }

            public int getAnnotationCount() {
                return this.annotation_.size();
            }

            public TypeAlias getDefaultInstanceForType() {
                return TypeAlias.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getExpandedType() {
                return this.expandedType_;
            }

            public TypeParameter getTypeParameter(int v) {
                return (TypeParameter)this.typeParameter_.get(v);
            }

            public int getTypeParameterCount() {
                return this.typeParameter_.size();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getUnderlyingType() {
                return this.underlyingType_;
            }

            public boolean hasExpandedType() {
                return (this.bitField0_ & 0x20) == 0x20;
            }

            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasUnderlyingType() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if(!this.hasName()) {
                    return false;
                }
                for(int v = 0; v < this.getTypeParameterCount(); ++v) {
                    if(!this.getTypeParameter(v).isInitialized()) {
                        return false;
                    }
                }
                if(this.hasUnderlyingType() && !this.getUnderlyingType().isInitialized()) {
                    return false;
                }
                if(this.hasExpandedType() && !this.getExpandedType().isInitialized()) {
                    return false;
                }
                for(int v1 = 0; v1 < this.getAnnotationCount(); ++v1) {
                    if(!this.getAnnotation(v1).isInitialized()) {
                        return false;
                    }
                }
                return this.extensionsAreInitialized();
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder mergeExpandedType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.expandedType_ = (this.bitField0_ & 0x20) != 0x20 || this.expandedType_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.expandedType_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 0x20;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder mergeFrom(TypeAlias protoBuf$TypeAlias0) {
                if(protoBuf$TypeAlias0 == TypeAlias.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$TypeAlias0.hasFlags()) {
                    this.setFlags(protoBuf$TypeAlias0.getFlags());
                }
                if(protoBuf$TypeAlias0.hasName()) {
                    this.setName(protoBuf$TypeAlias0.getName());
                }
                if(!protoBuf$TypeAlias0.typeParameter_.isEmpty()) {
                    if(this.typeParameter_.isEmpty()) {
                        this.typeParameter_ = protoBuf$TypeAlias0.typeParameter_;
                        this.bitField0_ &= -5;
                    }
                    else {
                        this.ensureTypeParameterIsMutable();
                        this.typeParameter_.addAll(protoBuf$TypeAlias0.typeParameter_);
                    }
                }
                if(protoBuf$TypeAlias0.hasUnderlyingType()) {
                    this.mergeUnderlyingType(protoBuf$TypeAlias0.getUnderlyingType());
                }
                if(protoBuf$TypeAlias0.hasUnderlyingTypeId()) {
                    this.setUnderlyingTypeId(protoBuf$TypeAlias0.getUnderlyingTypeId());
                }
                if(protoBuf$TypeAlias0.hasExpandedType()) {
                    this.mergeExpandedType(protoBuf$TypeAlias0.getExpandedType());
                }
                if(protoBuf$TypeAlias0.hasExpandedTypeId()) {
                    this.setExpandedTypeId(protoBuf$TypeAlias0.getExpandedTypeId());
                }
                if(!protoBuf$TypeAlias0.annotation_.isEmpty()) {
                    if(this.annotation_.isEmpty()) {
                        this.annotation_ = protoBuf$TypeAlias0.annotation_;
                        this.bitField0_ &= 0xFFFFFF7F;
                    }
                    else {
                        this.ensureAnnotationIsMutable();
                        this.annotation_.addAll(protoBuf$TypeAlias0.annotation_);
                    }
                }
                if(!protoBuf$TypeAlias0.versionRequirement_.isEmpty()) {
                    if(this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = protoBuf$TypeAlias0.versionRequirement_;
                        this.bitField0_ &= 0xFFFFFEFF;
                    }
                    else {
                        this.ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(protoBuf$TypeAlias0.versionRequirement_);
                    }
                }
                this.mergeExtensionFields(protoBuf$TypeAlias0);
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$TypeAlias0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                TypeAlias protoBuf$TypeAlias1;
                TypeAlias protoBuf$TypeAlias0;
                try {
                    try {
                        protoBuf$TypeAlias0 = null;
                        protoBuf$TypeAlias1 = (TypeAlias)TypeAlias.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        TypeAlias protoBuf$TypeAlias2 = (TypeAlias)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$TypeAlias0 = protoBuf$TypeAlias2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$TypeAlias0 != null) {
                    this.mergeFrom(protoBuf$TypeAlias0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$TypeAlias1 != null) {
                    this.mergeFrom(protoBuf$TypeAlias1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((TypeAlias)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder mergeUnderlyingType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.underlyingType_ = (this.bitField0_ & 8) != 8 || this.underlyingType_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.underlyingType_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 8;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder setExpandedTypeId(int v) {
                this.bitField0_ |= 0x40;
                this.expandedTypeId_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder setFlags(int v) {
                this.bitField0_ |= 1;
                this.flags_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder setName(int v) {
                this.bitField0_ |= 2;
                this.name_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder setUnderlyingTypeId(int v) {
                this.bitField0_ |= 16;
                this.underlyingTypeId_ = v;
                return this;
            }
        }

        public static Parser PARSER;
        private List annotation_;
        private int bitField0_;
        private static final TypeAlias defaultInstance;
        private int expandedTypeId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type expandedType_;
        private int flags_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int name_;
        private List typeParameter_;
        private int underlyingTypeId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type underlyingType_;
        private final ByteString unknownFields;
        private List versionRequirement_;

        static {
            TypeAlias.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public TypeAlias parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new TypeAlias(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            TypeAlias protoBuf$TypeAlias0 = new TypeAlias(true);
            TypeAlias.defaultInstance = protoBuf$TypeAlias0;
            protoBuf$TypeAlias0.initFields();
        }

        private TypeAlias(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            int v = 0;
            boolean z = false;
            while(!z) {
                try {
                    try {
                        int v1 = codedInputStream0.readTag();
                        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder protoBuf$Type$Builder0 = null;
                        switch(v1) {
                            case 0: {
                            label_13:
                                z = true;
                                continue;
                            }
                            case 8: {
                                this.bitField0_ |= 1;
                                this.flags_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 16: {
                                this.bitField0_ |= 2;
                                this.name_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 26: {
                                if((v & 4) != 4) {
                                    this.typeParameter_ = new ArrayList();
                                    v |= 4;
                                }
                                this.typeParameter_.add(codedInputStream0.readMessage(TypeParameter.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 34: {
                                if((this.bitField0_ & 4) == 4) {
                                    protoBuf$Type$Builder0 = this.underlyingType_.toBuilder();
                                }
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                this.underlyingType_ = protoBuf$Type0;
                                if(protoBuf$Type$Builder0 != null) {
                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type0);
                                    this.underlyingType_ = protoBuf$Type$Builder0.buildPartial();
                                }
                                this.bitField0_ |= 4;
                                continue;
                            }
                            case 40: {
                                this.bitField0_ |= 8;
                                this.underlyingTypeId_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 50: {
                                if((this.bitField0_ & 16) == 16) {
                                    protoBuf$Type$Builder0 = this.expandedType_.toBuilder();
                                }
                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                this.expandedType_ = protoBuf$Type1;
                                if(protoBuf$Type$Builder0 != null) {
                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type1);
                                    this.expandedType_ = protoBuf$Type$Builder0.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                continue;
                            }
                            case 56: {
                                this.bitField0_ |= 0x20;
                                this.expandedTypeId_ = codedInputStream0.readInt32();
                                continue;
                            }
                            case 66: {
                                if((v & 0x80) != 0x80) {
                                    this.annotation_ = new ArrayList();
                                    v |= 0x80;
                                }
                                this.annotation_.add(codedInputStream0.readMessage(Annotation.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 0xF8: {
                                if((v & 0x100) != 0x100) {
                                    this.versionRequirement_ = new ArrayList();
                                    v |= 0x100;
                                }
                                this.versionRequirement_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 0xFA: {
                                int v2 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 0x100) != 0x100 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_ = new ArrayList();
                                    v |= 0x100;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v2);
                                break;
                            }
                            default: {
                                if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                    goto label_13;
                                }
                            }
                        }
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if((v & 4) == 4) {
                        this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    }
                    if((v & 0x80) == 0x80) {
                        this.annotation_ = Collections.unmodifiableList(this.annotation_);
                    }
                    if((v & 0x100) == 0x100) {
                        this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    this.makeExtensionsImmutable();
                    throw throwable0;
                }
            }
            if((v & 4) == 4) {
                this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
            }
            if((v & 0x80) == 0x80) {
                this.annotation_ = Collections.unmodifiableList(this.annotation_);
            }
            if((v & 0x100) == 0x100) {
                this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
            this.makeExtensionsImmutable();
        }

        TypeAlias(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private TypeAlias(ExtendableBuilder generatedMessageLite$ExtendableBuilder0) {
            super(generatedMessageLite$ExtendableBuilder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$ExtendableBuilder0.getUnknownFields();
        }

        TypeAlias(ExtendableBuilder generatedMessageLite$ExtendableBuilder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$ExtendableBuilder0);
        }

        private TypeAlias(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public Annotation getAnnotation(int v) {
            return (Annotation)this.annotation_.get(v);
        }

        public int getAnnotationCount() {
            return this.annotation_.size();
        }

        public List getAnnotationList() {
            return this.annotation_;
        }

        public static TypeAlias getDefaultInstance() {
            return TypeAlias.defaultInstance;
        }

        public TypeAlias getDefaultInstanceForType() {
            return TypeAlias.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getExpandedType() {
            return this.expandedType_;
        }

        public int getExpandedTypeId() {
            return this.expandedTypeId_;
        }

        public int getFlags() {
            return this.flags_;
        }

        public int getName() {
            return this.name_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return TypeAlias.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v1 = this.memoizedSerializedSize;
            if(v1 != -1) {
                return v1;
            }
            int v2 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.flags_) : 0;
            if((this.bitField0_ & 2) == 2) {
                v2 += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            for(int v3 = 0; v3 < this.typeParameter_.size(); ++v3) {
                v2 += CodedOutputStream.computeMessageSize(3, ((MessageLite)this.typeParameter_.get(v3)));
            }
            if((this.bitField0_ & 4) == 4) {
                v2 += CodedOutputStream.computeMessageSize(4, this.underlyingType_);
            }
            if((this.bitField0_ & 8) == 8) {
                v2 += CodedOutputStream.computeInt32Size(5, this.underlyingTypeId_);
            }
            if((this.bitField0_ & 16) == 16) {
                v2 += CodedOutputStream.computeMessageSize(6, this.expandedType_);
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                v2 += CodedOutputStream.computeInt32Size(7, this.expandedTypeId_);
            }
            for(int v4 = 0; v4 < this.annotation_.size(); ++v4) {
                v2 += CodedOutputStream.computeMessageSize(8, ((MessageLite)this.annotation_.get(v4)));
            }
            int v5 = 0;
            for(int v = 0; v < this.versionRequirement_.size(); ++v) {
                v5 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.versionRequirement_.get(v)))));
            }
            int v6 = v2 + v5 + this.getVersionRequirementList().size() * 2 + this.extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = v6;
            return v6;
        }

        public TypeParameter getTypeParameter(int v) {
            return (TypeParameter)this.typeParameter_.get(v);
        }

        public int getTypeParameterCount() {
            return this.typeParameter_.size();
        }

        public List getTypeParameterList() {
            return this.typeParameter_;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getUnderlyingType() {
            return this.underlyingType_;
        }

        public int getUnderlyingTypeId() {
            return this.underlyingTypeId_;
        }

        public List getVersionRequirementList() {
            return this.versionRequirement_;
        }

        public boolean hasExpandedType() {
            return (this.bitField0_ & 16) == 16;
        }

        public boolean hasExpandedTypeId() {
            return (this.bitField0_ & 0x20) == 0x20;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasUnderlyingType() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasUnderlyingTypeId() {
            return (this.bitField0_ & 8) == 8;
        }

        private void initFields() {
            this.flags_ = 6;
            this.name_ = 0;
            this.typeParameter_ = Collections.EMPTY_LIST;
            this.underlyingType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.underlyingTypeId_ = 0;
            this.expandedType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.expandedTypeId_ = 0;
            this.annotation_ = Collections.EMPTY_LIST;
            this.versionRequirement_ = Collections.EMPTY_LIST;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            if(!this.hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for(int v1 = 0; v1 < this.getTypeParameterCount(); ++v1) {
                if(!this.getTypeParameter(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(this.hasUnderlyingType() && !this.getUnderlyingType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(this.hasExpandedType() && !this.getExpandedType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for(int v2 = 0; v2 < this.getAnnotationCount(); ++v2) {
                if(!this.getAnnotation(v2).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder.access$19000();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder newBuilder(TypeAlias protoBuf$TypeAlias0) {
            return TypeAlias.newBuilder().mergeFrom(protoBuf$TypeAlias0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder newBuilderForType() {
            return TypeAlias.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public static TypeAlias parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (TypeAlias)TypeAlias.PARSER.parseDelimitedFrom(inputStream0, extensionRegistryLite0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.Builder toBuilder() {
            return TypeAlias.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            ExtensionWriter generatedMessageLite$ExtendableMessage$ExtensionWriter0 = this.newExtensionWriter();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(1, this.flags_);
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeInt32(2, this.name_);
            }
            for(int v1 = 0; v1 < this.typeParameter_.size(); ++v1) {
                codedOutputStream0.writeMessage(3, ((MessageLite)this.typeParameter_.get(v1)));
            }
            if((this.bitField0_ & 4) == 4) {
                codedOutputStream0.writeMessage(4, this.underlyingType_);
            }
            if((this.bitField0_ & 8) == 8) {
                codedOutputStream0.writeInt32(5, this.underlyingTypeId_);
            }
            if((this.bitField0_ & 16) == 16) {
                codedOutputStream0.writeMessage(6, this.expandedType_);
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                codedOutputStream0.writeInt32(7, this.expandedTypeId_);
            }
            for(int v2 = 0; v2 < this.annotation_.size(); ++v2) {
                codedOutputStream0.writeMessage(8, ((MessageLite)this.annotation_.get(v2)));
            }
            for(int v = 0; v < this.versionRequirement_.size(); ++v) {
                codedOutputStream0.writeInt32(0x1F, ((int)(((Integer)this.versionRequirement_.get(v)))));
            }
            generatedMessageLite$ExtendableMessage$ExtensionWriter0.writeUntil(200, codedOutputStream0);
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class TypeParameter extends ExtendableMessage implements ProtoBuf.TypeParameterOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder extends ExtendableBuilder implements ProtoBuf.TypeParameterOrBuilder {
            private int bitField0_;
            private int id_;
            private int name_;
            private boolean reified_;
            private List upperBoundId_;
            private List upperBound_;
            private Variance variance_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder() {
                this.variance_ = Variance.INV;
                this.upperBound_ = Collections.EMPTY_LIST;
                this.upperBoundId_ = Collections.EMPTY_LIST;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder access$7600() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder.create();
            }

            public TypeParameter build() {
                TypeParameter protoBuf$TypeParameter0 = this.buildPartial();
                if(!protoBuf$TypeParameter0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder.newUninitializedMessageException(protoBuf$TypeParameter0);
                }
                return protoBuf$TypeParameter0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public TypeParameter buildPartial() {
                TypeParameter protoBuf$TypeParameter0 = new TypeParameter(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                protoBuf$TypeParameter0.id_ = this.id_;
                if((v & 2) == 2) {
                    v1 |= 2;
                }
                protoBuf$TypeParameter0.name_ = this.name_;
                if((v & 4) == 4) {
                    v1 |= 4;
                }
                protoBuf$TypeParameter0.reified_ = this.reified_;
                if((v & 8) == 8) {
                    v1 |= 8;
                }
                protoBuf$TypeParameter0.variance_ = this.variance_;
                if((this.bitField0_ & 16) == 16) {
                    this.upperBound_ = Collections.unmodifiableList(this.upperBound_);
                    this.bitField0_ &= -17;
                }
                protoBuf$TypeParameter0.upperBound_ = this.upperBound_;
                if((this.bitField0_ & 0x20) == 0x20) {
                    this.upperBoundId_ = Collections.unmodifiableList(this.upperBoundId_);
                    this.bitField0_ &= -33;
                }
                protoBuf$TypeParameter0.upperBoundId_ = this.upperBoundId_;
                protoBuf$TypeParameter0.bitField0_ = v1;
                return protoBuf$TypeParameter0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public ExtendableBuilder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder();
            }

            private void ensureUpperBoundIdIsMutable() {
                if((this.bitField0_ & 0x20) != 0x20) {
                    this.upperBoundId_ = new ArrayList(this.upperBoundId_);
                    this.bitField0_ |= 0x20;
                }
            }

            private void ensureUpperBoundIsMutable() {
                if((this.bitField0_ & 16) != 16) {
                    this.upperBound_ = new ArrayList(this.upperBound_);
                    this.bitField0_ |= 16;
                }
            }

            public TypeParameter getDefaultInstanceForType() {
                return TypeParameter.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getUpperBound(int v) {
                return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.upperBound_.get(v);
            }

            public int getUpperBoundCount() {
                return this.upperBound_.size();
            }

            public boolean hasId() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if(!this.hasId()) {
                    return false;
                }
                if(!this.hasName()) {
                    return false;
                }
                for(int v = 0; v < this.getUpperBoundCount(); ++v) {
                    if(!this.getUpperBound(v).isInitialized()) {
                        return false;
                    }
                }
                return this.extensionsAreInitialized();
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder mergeFrom(TypeParameter protoBuf$TypeParameter0) {
                if(protoBuf$TypeParameter0 == TypeParameter.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$TypeParameter0.hasId()) {
                    this.setId(protoBuf$TypeParameter0.getId());
                }
                if(protoBuf$TypeParameter0.hasName()) {
                    this.setName(protoBuf$TypeParameter0.getName());
                }
                if(protoBuf$TypeParameter0.hasReified()) {
                    this.setReified(protoBuf$TypeParameter0.getReified());
                }
                if(protoBuf$TypeParameter0.hasVariance()) {
                    this.setVariance(protoBuf$TypeParameter0.getVariance());
                }
                if(!protoBuf$TypeParameter0.upperBound_.isEmpty()) {
                    if(this.upperBound_.isEmpty()) {
                        this.upperBound_ = protoBuf$TypeParameter0.upperBound_;
                        this.bitField0_ &= -17;
                    }
                    else {
                        this.ensureUpperBoundIsMutable();
                        this.upperBound_.addAll(protoBuf$TypeParameter0.upperBound_);
                    }
                }
                if(!protoBuf$TypeParameter0.upperBoundId_.isEmpty()) {
                    if(this.upperBoundId_.isEmpty()) {
                        this.upperBoundId_ = protoBuf$TypeParameter0.upperBoundId_;
                        this.bitField0_ &= -33;
                    }
                    else {
                        this.ensureUpperBoundIdIsMutable();
                        this.upperBoundId_.addAll(protoBuf$TypeParameter0.upperBoundId_);
                    }
                }
                this.mergeExtensionFields(protoBuf$TypeParameter0);
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$TypeParameter0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                TypeParameter protoBuf$TypeParameter1;
                TypeParameter protoBuf$TypeParameter0;
                try {
                    try {
                        protoBuf$TypeParameter0 = null;
                        protoBuf$TypeParameter1 = (TypeParameter)TypeParameter.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        TypeParameter protoBuf$TypeParameter2 = (TypeParameter)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$TypeParameter0 = protoBuf$TypeParameter2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$TypeParameter0 != null) {
                    this.mergeFrom(protoBuf$TypeParameter0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$TypeParameter1 != null) {
                    this.mergeFrom(protoBuf$TypeParameter1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((TypeParameter)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder setId(int v) {
                this.bitField0_ |= 1;
                this.id_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder setName(int v) {
                this.bitField0_ |= 2;
                this.name_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder setReified(boolean z) {
                this.bitField0_ |= 4;
                this.reified_ = z;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder setVariance(Variance protoBuf$TypeParameter$Variance0) {
                protoBuf$TypeParameter$Variance0.getClass();
                this.bitField0_ |= 8;
                this.variance_ = protoBuf$TypeParameter$Variance0;
                return this;
            }
        }

        public static enum Variance implements EnumLite {
            IN(0, 0),
            OUT(1, 1),
            INV(2, 2);

            private static EnumLiteMap internalValueMap;
            private final int value;

            static {
                Variance.internalValueMap = new EnumLiteMap() {
                    public Variance findValueByNumber(int v) {
                        return Variance.valueOf(v);
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                    public EnumLite findValueByNumber(int v) {
                        return this.findValueByNumber(v);
                    }
                };
            }

            private Variance(int v1, int v2) {
                this.value = v2;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static Variance valueOf(int v) {
                switch(v) {
                    case 0: {
                        return Variance.IN;
                    }
                    case 1: {
                        return Variance.OUT;
                    }
                    case 2: {
                        return Variance.INV;
                    }
                    default: {
                        return null;
                    }
                }
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private static final TypeParameter defaultInstance;
        private int id_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int name_;
        private boolean reified_;
        private final ByteString unknownFields;
        private int upperBoundIdMemoizedSerializedSize;
        private List upperBoundId_;
        private List upperBound_;
        private Variance variance_;

        static {
            TypeParameter.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public TypeParameter parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new TypeParameter(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            TypeParameter protoBuf$TypeParameter0 = new TypeParameter(true);
            TypeParameter.defaultInstance = protoBuf$TypeParameter0;
            protoBuf$TypeParameter0.initFields();
        }

        private TypeParameter(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.upperBoundIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            int v = 0;
            boolean z = false;
            while(!z) {
                try {
                    try {
                        int v1 = codedInputStream0.readTag();
                        if(v1 != 0) {
                            switch(v1) {
                                case 8: {
                                    this.bitField0_ |= 1;
                                    this.id_ = codedInputStream0.readInt32();
                                    continue;
                                }
                                case 16: {
                                    this.bitField0_ |= 2;
                                    this.name_ = codedInputStream0.readInt32();
                                    continue;
                                }
                                case 24: {
                                    this.bitField0_ |= 4;
                                    this.reified_ = codedInputStream0.readBool();
                                    continue;
                                }
                                case 0x20: {
                                    int v2 = codedInputStream0.readEnum();
                                    Variance protoBuf$TypeParameter$Variance0 = Variance.valueOf(v2);
                                    if(protoBuf$TypeParameter$Variance0 == null) {
                                        codedOutputStream0.writeRawVarint32(0x20);
                                        codedOutputStream0.writeRawVarint32(v2);
                                    }
                                    else {
                                        this.bitField0_ |= 8;
                                        this.variance_ = protoBuf$TypeParameter$Variance0;
                                    }
                                    continue;
                                }
                                case 42: {
                                    if((v & 16) != 16) {
                                        this.upperBound_ = new ArrayList();
                                        v |= 16;
                                    }
                                    this.upperBound_.add(codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0));
                                    continue;
                                }
                                case 0x30: {
                                    if((v & 0x20) != 0x20) {
                                        this.upperBoundId_ = new ArrayList();
                                        v |= 0x20;
                                    }
                                    this.upperBoundId_.add(codedInputStream0.readInt32());
                                    continue;
                                }
                                case 50: {
                                    int v3 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                    if((v & 0x20) != 0x20 && codedInputStream0.getBytesUntilLimit() > 0) {
                                        this.upperBoundId_ = new ArrayList();
                                        v |= 0x20;
                                    }
                                    while(codedInputStream0.getBytesUntilLimit() > 0) {
                                        this.upperBoundId_.add(codedInputStream0.readInt32());
                                    }
                                    codedInputStream0.popLimit(v3);
                                    continue;
                                }
                                default: {
                                    if(this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                        continue;
                                    }
                                }
                            }
                        }
                        z = true;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if((v & 16) == 16) {
                        this.upperBound_ = Collections.unmodifiableList(this.upperBound_);
                    }
                    if((v & 0x20) == 0x20) {
                        this.upperBoundId_ = Collections.unmodifiableList(this.upperBoundId_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    this.makeExtensionsImmutable();
                    throw throwable0;
                }
            }
            if((v & 16) == 16) {
                this.upperBound_ = Collections.unmodifiableList(this.upperBound_);
            }
            if((v & 0x20) == 0x20) {
                this.upperBoundId_ = Collections.unmodifiableList(this.upperBoundId_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
            this.makeExtensionsImmutable();
        }

        TypeParameter(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private TypeParameter(ExtendableBuilder generatedMessageLite$ExtendableBuilder0) {
            super(generatedMessageLite$ExtendableBuilder0);
            this.upperBoundIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$ExtendableBuilder0.getUnknownFields();
        }

        TypeParameter(ExtendableBuilder generatedMessageLite$ExtendableBuilder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$ExtendableBuilder0);
        }

        private TypeParameter(boolean z) {
            this.upperBoundIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static TypeParameter getDefaultInstance() {
            return TypeParameter.defaultInstance;
        }

        public TypeParameter getDefaultInstanceForType() {
            return TypeParameter.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getId() {
            return this.id_;
        }

        public int getName() {
            return this.name_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return TypeParameter.PARSER;
        }

        public boolean getReified() {
            return this.reified_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v1 = this.memoizedSerializedSize;
            if(v1 != -1) {
                return v1;
            }
            int v2 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.id_) : 0;
            if((this.bitField0_ & 2) == 2) {
                v2 += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            if((this.bitField0_ & 4) == 4) {
                v2 += CodedOutputStream.computeBoolSize(3, this.reified_);
            }
            if((this.bitField0_ & 8) == 8) {
                v2 += CodedOutputStream.computeEnumSize(4, this.variance_.getNumber());
            }
            for(int v3 = 0; v3 < this.upperBound_.size(); ++v3) {
                v2 += CodedOutputStream.computeMessageSize(5, ((MessageLite)this.upperBound_.get(v3)));
            }
            int v4 = 0;
            for(int v = 0; v < this.upperBoundId_.size(); ++v) {
                v4 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.upperBoundId_.get(v)))));
            }
            this.upperBoundIdMemoizedSerializedSize = v4;
            int v5 = (this.getUpperBoundIdList().isEmpty() ? v2 + v4 : v2 + v4 + 1 + CodedOutputStream.computeInt32SizeNoTag(v4)) + this.extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = v5;
            return v5;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getUpperBound(int v) {
            return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.upperBound_.get(v);
        }

        public int getUpperBoundCount() {
            return this.upperBound_.size();
        }

        public List getUpperBoundIdList() {
            return this.upperBoundId_;
        }

        public List getUpperBoundList() {
            return this.upperBound_;
        }

        public Variance getVariance() {
            return this.variance_;
        }

        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasReified() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasVariance() {
            return (this.bitField0_ & 8) == 8;
        }

        private void initFields() {
            this.id_ = 0;
            this.name_ = 0;
            this.reified_ = false;
            this.variance_ = Variance.INV;
            this.upperBound_ = Collections.EMPTY_LIST;
            this.upperBoundId_ = Collections.EMPTY_LIST;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            if(!this.hasId()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(!this.hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for(int v1 = 0; v1 < this.getUpperBoundCount(); ++v1) {
                if(!this.getUpperBound(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            if(!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder.access$7600();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder newBuilder(TypeParameter protoBuf$TypeParameter0) {
            return TypeParameter.newBuilder().mergeFrom(protoBuf$TypeParameter0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder newBuilderForType() {
            return TypeParameter.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Builder toBuilder() {
            return TypeParameter.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            ExtensionWriter generatedMessageLite$ExtendableMessage$ExtensionWriter0 = this.newExtensionWriter();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(1, this.id_);
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeInt32(2, this.name_);
            }
            if((this.bitField0_ & 4) == 4) {
                codedOutputStream0.writeBool(3, this.reified_);
            }
            if((this.bitField0_ & 8) == 8) {
                codedOutputStream0.writeEnum(4, this.variance_.getNumber());
            }
            for(int v1 = 0; v1 < this.upperBound_.size(); ++v1) {
                codedOutputStream0.writeMessage(5, ((MessageLite)this.upperBound_.get(v1)));
            }
            if(this.getUpperBoundIdList().size() > 0) {
                codedOutputStream0.writeRawVarint32(50);
                codedOutputStream0.writeRawVarint32(this.upperBoundIdMemoizedSerializedSize);
            }
            for(int v = 0; v < this.upperBoundId_.size(); ++v) {
                codedOutputStream0.writeInt32NoTag(((int)(((Integer)this.upperBoundId_.get(v)))));
            }
            generatedMessageLite$ExtendableMessage$ExtensionWriter0.writeUntil(1000, codedOutputStream0);
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class TypeTable extends GeneratedMessageLite implements ProtoBuf.TypeTableOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.TypeTableOrBuilder {
            private int bitField0_;
            private int firstNullable_;
            private List type_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder() {
                this.type_ = Collections.EMPTY_LIST;
                this.firstNullable_ = -1;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder access$12600() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder.create();
            }

            public TypeTable build() {
                TypeTable protoBuf$TypeTable0 = this.buildPartial();
                if(!protoBuf$TypeTable0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder.newUninitializedMessageException(protoBuf$TypeTable0);
                }
                return protoBuf$TypeTable0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public TypeTable buildPartial() {
                TypeTable protoBuf$TypeTable0 = new TypeTable(this, null);
                int v = this.bitField0_;
                int v1 = 1;
                if((v & 1) == 1) {
                    this.type_ = Collections.unmodifiableList(this.type_);
                    this.bitField0_ &= -2;
                }
                protoBuf$TypeTable0.type_ = this.type_;
                if((v & 2) != 2) {
                    v1 = 0;
                }
                protoBuf$TypeTable0.firstNullable_ = this.firstNullable_;
                protoBuf$TypeTable0.bitField0_ = v1;
                return protoBuf$TypeTable0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder();
            }

            private void ensureTypeIsMutable() {
                if((this.bitField0_ & 1) != 1) {
                    this.type_ = new ArrayList(this.type_);
                    this.bitField0_ |= 1;
                }
            }

            public TypeTable getDefaultInstanceForType() {
                return TypeTable.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getType(int v) {
                return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.type_.get(v);
            }

            public int getTypeCount() {
                return this.type_.size();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for(int v = 0; v < this.getTypeCount(); ++v) {
                    if(!this.getType(v).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder mergeFrom(TypeTable protoBuf$TypeTable0) {
                if(protoBuf$TypeTable0 == TypeTable.getDefaultInstance()) {
                    return this;
                }
                if(!protoBuf$TypeTable0.type_.isEmpty()) {
                    if(this.type_.isEmpty()) {
                        this.type_ = protoBuf$TypeTable0.type_;
                        this.bitField0_ &= -2;
                    }
                    else {
                        this.ensureTypeIsMutable();
                        this.type_.addAll(protoBuf$TypeTable0.type_);
                    }
                }
                if(protoBuf$TypeTable0.hasFirstNullable()) {
                    this.setFirstNullable(protoBuf$TypeTable0.getFirstNullable());
                }
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$TypeTable0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                TypeTable protoBuf$TypeTable1;
                TypeTable protoBuf$TypeTable0;
                try {
                    try {
                        protoBuf$TypeTable0 = null;
                        protoBuf$TypeTable1 = (TypeTable)TypeTable.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        TypeTable protoBuf$TypeTable2 = (TypeTable)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$TypeTable0 = protoBuf$TypeTable2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$TypeTable0 != null) {
                    this.mergeFrom(protoBuf$TypeTable0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$TypeTable1 != null) {
                    this.mergeFrom(protoBuf$TypeTable1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((TypeTable)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder setFirstNullable(int v) {
                this.bitField0_ |= 2;
                this.firstNullable_ = v;
                return this;
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private static final TypeTable defaultInstance;
        private int firstNullable_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List type_;
        private final ByteString unknownFields;

        static {
            TypeTable.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public TypeTable parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new TypeTable(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            TypeTable protoBuf$TypeTable0 = new TypeTable(true);
            TypeTable.defaultInstance = protoBuf$TypeTable0;
            protoBuf$TypeTable0.initFields();
        }

        private TypeTable(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            boolean z = false;
            boolean z1 = false;
            while(!z1) {
                try {
                    try {
                        int v = codedInputStream0.readTag();
                        switch(v) {
                            case 0: {
                                z1 = true;
                                continue;
                            }
                            case 10: {
                                if(!z) {
                                    this.type_ = new ArrayList();
                                    z = true;
                                }
                                this.type_.add(codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 16: {
                                this.bitField0_ |= 1;
                                this.firstNullable_ = codedInputStream0.readInt32();
                                break;
                            }
                            default: {
                                if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v)) {
                                    z1 = true;
                                    continue;
                                }
                            }
                        }
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if(z) {
                        this.type_ = Collections.unmodifiableList(this.type_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    throw throwable0;
                }
            }
            if(z) {
                this.type_ = Collections.unmodifiableList(this.type_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
        }

        TypeTable(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private TypeTable(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        TypeTable(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private TypeTable(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static TypeTable getDefaultInstance() {
            return TypeTable.defaultInstance;
        }

        public TypeTable getDefaultInstanceForType() {
            return TypeTable.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getFirstNullable() {
            return this.firstNullable_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return TypeTable.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v2 = 0;
            for(int v1 = 0; v1 < this.type_.size(); ++v1) {
                v2 += CodedOutputStream.computeMessageSize(1, ((MessageLite)this.type_.get(v1)));
            }
            if((this.bitField0_ & 1) == 1) {
                v2 += CodedOutputStream.computeInt32Size(2, this.firstNullable_);
            }
            int v3 = v2 + this.unknownFields.size();
            this.memoizedSerializedSize = v3;
            return v3;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getType(int v) {
            return (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)this.type_.get(v);
        }

        public int getTypeCount() {
            return this.type_.size();
        }

        public List getTypeList() {
            return this.type_;
        }

        public boolean hasFirstNullable() {
            return (this.bitField0_ & 1) == 1;
        }

        private void initFields() {
            this.type_ = Collections.EMPTY_LIST;
            this.firstNullable_ = -1;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            for(int v1 = 0; v1 < this.getTypeCount(); ++v1) {
                if(!this.getType(v1).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder.access$12600();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder newBuilder(TypeTable protoBuf$TypeTable0) {
            return TypeTable.newBuilder().mergeFrom(protoBuf$TypeTable0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder newBuilderForType() {
            return TypeTable.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.Builder toBuilder() {
            return TypeTable.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            for(int v = 0; v < this.type_.size(); ++v) {
                codedOutputStream0.writeMessage(1, ((MessageLite)this.type_.get(v)));
            }
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(2, this.firstNullable_);
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class ValueParameter extends ExtendableMessage implements ProtoBuf.ValueParameterOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder extends ExtendableBuilder implements ProtoBuf.ValueParameterOrBuilder {
            private int bitField0_;
            private int flags_;
            private int name_;
            private int typeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type type_;
            private int varargElementTypeId_;
            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type varargElementType_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder() {
                this.type_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
                this.varargElementType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder access$17900() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder.create();
            }

            public ValueParameter build() {
                ValueParameter protoBuf$ValueParameter0 = this.buildPartial();
                if(!protoBuf$ValueParameter0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder.newUninitializedMessageException(protoBuf$ValueParameter0);
                }
                return protoBuf$ValueParameter0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public ValueParameter buildPartial() {
                ValueParameter protoBuf$ValueParameter0 = new ValueParameter(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                protoBuf$ValueParameter0.flags_ = this.flags_;
                if((v & 2) == 2) {
                    v1 |= 2;
                }
                protoBuf$ValueParameter0.name_ = this.name_;
                if((v & 4) == 4) {
                    v1 |= 4;
                }
                protoBuf$ValueParameter0.type_ = this.type_;
                if((v & 8) == 8) {
                    v1 |= 8;
                }
                protoBuf$ValueParameter0.typeId_ = this.typeId_;
                if((v & 16) == 16) {
                    v1 |= 16;
                }
                protoBuf$ValueParameter0.varargElementType_ = this.varargElementType_;
                if((v & 0x20) == 0x20) {
                    v1 |= 0x20;
                }
                protoBuf$ValueParameter0.varargElementTypeId_ = this.varargElementTypeId_;
                protoBuf$ValueParameter0.bitField0_ = v1;
                return protoBuf$ValueParameter0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableBuilder
            public ExtendableBuilder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder();
            }

            public ValueParameter getDefaultInstanceForType() {
                return ValueParameter.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getType() {
                return this.type_;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getVarargElementType() {
                return this.varargElementType_;
            }

            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasType() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean hasVarargElementType() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if(!this.hasName()) {
                    return false;
                }
                if(this.hasType() && !this.getType().isInitialized()) {
                    return false;
                }
                return !this.hasVarargElementType() || this.getVarargElementType().isInitialized() ? this.extensionsAreInitialized() : false;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder mergeFrom(ValueParameter protoBuf$ValueParameter0) {
                if(protoBuf$ValueParameter0 == ValueParameter.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$ValueParameter0.hasFlags()) {
                    this.setFlags(protoBuf$ValueParameter0.getFlags());
                }
                if(protoBuf$ValueParameter0.hasName()) {
                    this.setName(protoBuf$ValueParameter0.getName());
                }
                if(protoBuf$ValueParameter0.hasType()) {
                    this.mergeType(protoBuf$ValueParameter0.getType());
                }
                if(protoBuf$ValueParameter0.hasTypeId()) {
                    this.setTypeId(protoBuf$ValueParameter0.getTypeId());
                }
                if(protoBuf$ValueParameter0.hasVarargElementType()) {
                    this.mergeVarargElementType(protoBuf$ValueParameter0.getVarargElementType());
                }
                if(protoBuf$ValueParameter0.hasVarargElementTypeId()) {
                    this.setVarargElementTypeId(protoBuf$ValueParameter0.getVarargElementTypeId());
                }
                this.mergeExtensionFields(protoBuf$ValueParameter0);
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$ValueParameter0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                ValueParameter protoBuf$ValueParameter1;
                ValueParameter protoBuf$ValueParameter0;
                try {
                    try {
                        protoBuf$ValueParameter0 = null;
                        protoBuf$ValueParameter1 = (ValueParameter)ValueParameter.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        ValueParameter protoBuf$ValueParameter2 = (ValueParameter)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$ValueParameter0 = protoBuf$ValueParameter2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$ValueParameter0 != null) {
                    this.mergeFrom(protoBuf$ValueParameter0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$ValueParameter1 != null) {
                    this.mergeFrom(protoBuf$ValueParameter1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((ValueParameter)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder mergeType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.type_ = (this.bitField0_ & 4) != 4 || this.type_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.type_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 4;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder mergeVarargElementType(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0) {
                this.varargElementType_ = (this.bitField0_ & 16) != 16 || this.varargElementType_ == kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance() ? protoBuf$Type0 : kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.newBuilder(this.varargElementType_).mergeFrom(protoBuf$Type0).buildPartial();
                this.bitField0_ |= 16;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder setFlags(int v) {
                this.bitField0_ |= 1;
                this.flags_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder setName(int v) {
                this.bitField0_ |= 2;
                this.name_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder setTypeId(int v) {
                this.bitField0_ |= 8;
                this.typeId_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder setVarargElementTypeId(int v) {
                this.bitField0_ |= 0x20;
                this.varargElementTypeId_ = v;
                return this;
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private static final ValueParameter defaultInstance;
        private int flags_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int name_;
        private int typeId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type type_;
        private final ByteString unknownFields;
        private int varargElementTypeId_;
        private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type varargElementType_;

        static {
            ValueParameter.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public ValueParameter parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new ValueParameter(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            ValueParameter protoBuf$ValueParameter0 = new ValueParameter(true);
            ValueParameter.defaultInstance = protoBuf$ValueParameter0;
            protoBuf$ValueParameter0.initFields();
        }

        private ValueParameter(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            try {
                boolean z = false;
                while(!z) {
                    try {
                        try {
                            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Builder protoBuf$Type$Builder0 = null;
                            int v1 = codedInputStream0.readTag();
                            switch(v1) {
                                case 0: {
                                    z = true;
                                    continue;
                                }
                                case 8: {
                                    break;
                                }
                                default: {
                                    if(v1 == 16) {
                                        this.bitField0_ |= 2;
                                        this.name_ = codedInputStream0.readInt32();
                                        continue;
                                    }
                                    else {
                                        switch(v1) {
                                            case 26: {
                                                if((this.bitField0_ & 4) == 4) {
                                                    protoBuf$Type$Builder0 = this.type_.toBuilder();
                                                }
                                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                                this.type_ = protoBuf$Type0;
                                                if(protoBuf$Type$Builder0 != null) {
                                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type0);
                                                    this.type_ = protoBuf$Type$Builder0.buildPartial();
                                                }
                                                this.bitField0_ |= 4;
                                                continue;
                                            }
                                            case 34: {
                                                if((this.bitField0_ & 16) == 16) {
                                                    protoBuf$Type$Builder0 = this.varargElementType_.toBuilder();
                                                }
                                                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type protoBuf$Type1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type)codedInputStream0.readMessage(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.PARSER, extensionRegistryLite0);
                                                this.varargElementType_ = protoBuf$Type1;
                                                if(protoBuf$Type$Builder0 != null) {
                                                    protoBuf$Type$Builder0.mergeFrom(protoBuf$Type1);
                                                    this.varargElementType_ = protoBuf$Type$Builder0.buildPartial();
                                                }
                                                this.bitField0_ |= 16;
                                                continue;
                                            }
                                            case 40: {
                                                this.bitField0_ |= 8;
                                                this.typeId_ = codedInputStream0.readInt32();
                                                continue;
                                            }
                                            case 0x30: {
                                                this.bitField0_ |= 0x20;
                                                this.varargElementTypeId_ = codedInputStream0.readInt32();
                                                continue;
                                            }
                                            default: {
                                                if(this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                                    continue;
                                                }
                                            }
                                        }
                                    }
                                    z = true;
                                    continue;
                                }
                            }
                            this.bitField0_ |= 1;
                            this.flags_ = codedInputStream0.readInt32();
                            continue;
                        }
                        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        }
                        catch(IOException iOException0) {
                            throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                        }
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(Throwable throwable0) {
                        try {
                            codedOutputStream0.flush();
                        }
                        catch(IOException throwable1) {
                            throw throwable1;
                        }
                        catch(Throwable unused_ex) {
                        }
                        this.makeExtensionsImmutable();
                        throw throwable0;
                    }
                }
                try {
                    codedOutputStream0.flush();
                }
                catch(IOException unused_ex) {
                }
            }
            finally {
                this.unknownFields = byteString$Output0.toByteString();
            }
            this.makeExtensionsImmutable();
        }

        ValueParameter(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private ValueParameter(ExtendableBuilder generatedMessageLite$ExtendableBuilder0) {
            super(generatedMessageLite$ExtendableBuilder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$ExtendableBuilder0.getUnknownFields();
        }

        ValueParameter(ExtendableBuilder generatedMessageLite$ExtendableBuilder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$ExtendableBuilder0);
        }

        private ValueParameter(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static ValueParameter getDefaultInstance() {
            return ValueParameter.defaultInstance;
        }

        public ValueParameter getDefaultInstanceForType() {
            return ValueParameter.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getFlags() {
            return this.flags_;
        }

        public int getName() {
            return this.name_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return ValueParameter.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v1 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.flags_) : 0;
            if((this.bitField0_ & 2) == 2) {
                v1 += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            if((this.bitField0_ & 4) == 4) {
                v1 += CodedOutputStream.computeMessageSize(3, this.type_);
            }
            if((this.bitField0_ & 16) == 16) {
                v1 += CodedOutputStream.computeMessageSize(4, this.varargElementType_);
            }
            if((this.bitField0_ & 8) == 8) {
                v1 += CodedOutputStream.computeInt32Size(5, this.typeId_);
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                v1 += CodedOutputStream.computeInt32Size(6, this.varargElementTypeId_);
            }
            int v2 = v1 + this.extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = v2;
            return v2;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getType() {
            return this.type_;
        }

        public int getTypeId() {
            return this.typeId_;
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type getVarargElementType() {
            return this.varargElementType_;
        }

        public int getVarargElementTypeId() {
            return this.varargElementTypeId_;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasType() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasTypeId() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasVarargElementType() {
            return (this.bitField0_ & 16) == 16;
        }

        public boolean hasVarargElementTypeId() {
            return (this.bitField0_ & 0x20) == 0x20;
        }

        private void initFields() {
            this.flags_ = 0;
            this.name_ = 0;
            this.type_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.typeId_ = 0;
            this.varargElementType_ = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.getDefaultInstance();
            this.varargElementTypeId_ = 0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            if(!this.hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(this.hasType() && !this.getType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(this.hasVarargElementType() && !this.getVarargElementType().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if(!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder.access$17900();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder newBuilder(ValueParameter protoBuf$ValueParameter0) {
            return ValueParameter.newBuilder().mergeFrom(protoBuf$ValueParameter0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder newBuilderForType() {
            return ValueParameter.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.Builder toBuilder() {
            return ValueParameter.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            ExtensionWriter generatedMessageLite$ExtendableMessage$ExtensionWriter0 = this.newExtensionWriter();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(1, this.flags_);
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeInt32(2, this.name_);
            }
            if((this.bitField0_ & 4) == 4) {
                codedOutputStream0.writeMessage(3, this.type_);
            }
            if((this.bitField0_ & 16) == 16) {
                codedOutputStream0.writeMessage(4, this.varargElementType_);
            }
            if((this.bitField0_ & 8) == 8) {
                codedOutputStream0.writeInt32(5, this.typeId_);
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                codedOutputStream0.writeInt32(6, this.varargElementTypeId_);
            }
            generatedMessageLite$ExtendableMessage$ExtensionWriter0.writeUntil(200, codedOutputStream0);
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class VersionRequirement extends GeneratedMessageLite implements ProtoBuf.VersionRequirementOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.VersionRequirementOrBuilder {
            private int bitField0_;
            private int errorCode_;
            private Level level_;
            private int message_;
            private int versionFull_;
            private VersionKind versionKind_;
            private int version_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder() {
                this.level_ = Level.ERROR;
                this.versionKind_ = VersionKind.LANGUAGE_VERSION;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder access$21000() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder.create();
            }

            public VersionRequirement build() {
                VersionRequirement protoBuf$VersionRequirement0 = this.buildPartial();
                if(!protoBuf$VersionRequirement0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder.newUninitializedMessageException(protoBuf$VersionRequirement0);
                }
                return protoBuf$VersionRequirement0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public VersionRequirement buildPartial() {
                VersionRequirement protoBuf$VersionRequirement0 = new VersionRequirement(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                protoBuf$VersionRequirement0.version_ = this.version_;
                if((v & 2) == 2) {
                    v1 |= 2;
                }
                protoBuf$VersionRequirement0.versionFull_ = this.versionFull_;
                if((v & 4) == 4) {
                    v1 |= 4;
                }
                protoBuf$VersionRequirement0.level_ = this.level_;
                if((v & 8) == 8) {
                    v1 |= 8;
                }
                protoBuf$VersionRequirement0.errorCode_ = this.errorCode_;
                if((v & 16) == 16) {
                    v1 |= 16;
                }
                protoBuf$VersionRequirement0.message_ = this.message_;
                if((v & 0x20) == 0x20) {
                    v1 |= 0x20;
                }
                protoBuf$VersionRequirement0.versionKind_ = this.versionKind_;
                protoBuf$VersionRequirement0.bitField0_ = v1;
                return protoBuf$VersionRequirement0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder();
            }

            public VersionRequirement getDefaultInstanceForType() {
                return VersionRequirement.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder mergeFrom(VersionRequirement protoBuf$VersionRequirement0) {
                if(protoBuf$VersionRequirement0 == VersionRequirement.getDefaultInstance()) {
                    return this;
                }
                if(protoBuf$VersionRequirement0.hasVersion()) {
                    this.setVersion(protoBuf$VersionRequirement0.getVersion());
                }
                if(protoBuf$VersionRequirement0.hasVersionFull()) {
                    this.setVersionFull(protoBuf$VersionRequirement0.getVersionFull());
                }
                if(protoBuf$VersionRequirement0.hasLevel()) {
                    this.setLevel(protoBuf$VersionRequirement0.getLevel());
                }
                if(protoBuf$VersionRequirement0.hasErrorCode()) {
                    this.setErrorCode(protoBuf$VersionRequirement0.getErrorCode());
                }
                if(protoBuf$VersionRequirement0.hasMessage()) {
                    this.setMessage(protoBuf$VersionRequirement0.getMessage());
                }
                if(protoBuf$VersionRequirement0.hasVersionKind()) {
                    this.setVersionKind(protoBuf$VersionRequirement0.getVersionKind());
                }
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$VersionRequirement0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                VersionRequirement protoBuf$VersionRequirement1;
                VersionRequirement protoBuf$VersionRequirement0;
                try {
                    try {
                        protoBuf$VersionRequirement0 = null;
                        protoBuf$VersionRequirement1 = (VersionRequirement)VersionRequirement.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        VersionRequirement protoBuf$VersionRequirement2 = (VersionRequirement)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$VersionRequirement0 = protoBuf$VersionRequirement2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$VersionRequirement0 != null) {
                    this.mergeFrom(protoBuf$VersionRequirement0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$VersionRequirement1 != null) {
                    this.mergeFrom(protoBuf$VersionRequirement1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((VersionRequirement)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder setErrorCode(int v) {
                this.bitField0_ |= 8;
                this.errorCode_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder setLevel(Level protoBuf$VersionRequirement$Level0) {
                protoBuf$VersionRequirement$Level0.getClass();
                this.bitField0_ |= 4;
                this.level_ = protoBuf$VersionRequirement$Level0;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder setMessage(int v) {
                this.bitField0_ |= 16;
                this.message_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder setVersion(int v) {
                this.bitField0_ |= 1;
                this.version_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder setVersionFull(int v) {
                this.bitField0_ |= 2;
                this.versionFull_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder setVersionKind(VersionKind protoBuf$VersionRequirement$VersionKind0) {
                protoBuf$VersionRequirement$VersionKind0.getClass();
                this.bitField0_ |= 0x20;
                this.versionKind_ = protoBuf$VersionRequirement$VersionKind0;
                return this;
            }
        }

        public static enum Level implements EnumLite {
            WARNING(0, 0),
            ERROR(1, 1),
            HIDDEN(2, 2);

            private static EnumLiteMap internalValueMap;
            private final int value;

            static {
                Level.internalValueMap = new EnumLiteMap() {
                    public Level findValueByNumber(int v) {
                        return Level.valueOf(v);
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                    public EnumLite findValueByNumber(int v) {
                        return this.findValueByNumber(v);
                    }
                };
            }

            private Level(int v1, int v2) {
                this.value = v2;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static Level valueOf(int v) {
                switch(v) {
                    case 0: {
                        return Level.WARNING;
                    }
                    case 1: {
                        return Level.ERROR;
                    }
                    case 2: {
                        return Level.HIDDEN;
                    }
                    default: {
                        return null;
                    }
                }
            }
        }

        public static enum VersionKind implements EnumLite {
            LANGUAGE_VERSION(0, 0),
            COMPILER_VERSION(1, 1),
            API_VERSION(2, 2);

            private static EnumLiteMap internalValueMap;
            private final int value;

            static {
                VersionKind.internalValueMap = new EnumLiteMap() {
                    public VersionKind findValueByNumber(int v) {
                        return VersionKind.valueOf(v);
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                    public EnumLite findValueByNumber(int v) {
                        return this.findValueByNumber(v);
                    }
                };
            }

            private VersionKind(int v1, int v2) {
                this.value = v2;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static VersionKind valueOf(int v) {
                switch(v) {
                    case 0: {
                        return VersionKind.LANGUAGE_VERSION;
                    }
                    case 1: {
                        return VersionKind.COMPILER_VERSION;
                    }
                    case 2: {
                        return VersionKind.API_VERSION;
                    }
                    default: {
                        return null;
                    }
                }
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private static final VersionRequirement defaultInstance;
        private int errorCode_;
        private Level level_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int message_;
        private final ByteString unknownFields;
        private int versionFull_;
        private VersionKind versionKind_;
        private int version_;

        static {
            VersionRequirement.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public VersionRequirement parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new VersionRequirement(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            VersionRequirement protoBuf$VersionRequirement0 = new VersionRequirement(true);
            VersionRequirement.defaultInstance = protoBuf$VersionRequirement0;
            protoBuf$VersionRequirement0.initFields();
        }

        private VersionRequirement(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            try {
                boolean z = false;
                while(!z) {
                    try {
                        try {
                            int v1 = codedInputStream0.readTag();
                            switch(v1) {
                                case 0: {
                                    goto label_54;
                                }
                                case 8: {
                                    this.bitField0_ |= 1;
                                    this.version_ = codedInputStream0.readInt32();
                                    continue;
                                }
                                case 16: {
                                    this.bitField0_ |= 2;
                                    this.versionFull_ = codedInputStream0.readInt32();
                                    continue;
                                }
                                case 24: {
                                    int v3 = codedInputStream0.readEnum();
                                    Level protoBuf$VersionRequirement$Level0 = Level.valueOf(v3);
                                    if(protoBuf$VersionRequirement$Level0 == null) {
                                        codedOutputStream0.writeRawVarint32(24);
                                        codedOutputStream0.writeRawVarint32(v3);
                                    }
                                    else {
                                        this.bitField0_ |= 4;
                                        this.level_ = protoBuf$VersionRequirement$Level0;
                                    }
                                    continue;
                                }
                                case 0x20: {
                                    this.bitField0_ |= 8;
                                    this.errorCode_ = codedInputStream0.readInt32();
                                    continue;
                                }
                                case 40: {
                                    this.bitField0_ |= 16;
                                    this.message_ = codedInputStream0.readInt32();
                                    continue;
                                }
                                case 0x30: {
                                    int v2 = codedInputStream0.readEnum();
                                    VersionKind protoBuf$VersionRequirement$VersionKind0 = VersionKind.valueOf(v2);
                                    if(protoBuf$VersionRequirement$VersionKind0 == null) {
                                        codedOutputStream0.writeRawVarint32(0x30);
                                        codedOutputStream0.writeRawVarint32(v2);
                                    }
                                    else {
                                        this.bitField0_ |= 0x20;
                                        this.versionKind_ = protoBuf$VersionRequirement$VersionKind0;
                                    }
                                    continue;
                                }
                                default: {
                                    if(this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                        continue;
                                    }
                                    goto label_54;
                                }
                            }
                        }
                        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        }
                        catch(IOException iOException0) {
                            throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                        }
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(Throwable throwable0) {
                        try {
                            codedOutputStream0.flush();
                        }
                        catch(IOException unused_ex) {
                        }
                        throw throwable0;
                    }
                label_54:
                    z = true;
                }
                try {
                    codedOutputStream0.flush();
                }
                catch(IOException unused_ex) {
                }
            }
            finally {
                this.unknownFields = byteString$Output0.toByteString();
            }
        }

        VersionRequirement(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private VersionRequirement(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        VersionRequirement(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private VersionRequirement(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static VersionRequirement getDefaultInstance() {
            return VersionRequirement.defaultInstance;
        }

        public VersionRequirement getDefaultInstanceForType() {
            return VersionRequirement.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getErrorCode() {
            return this.errorCode_;
        }

        public Level getLevel() {
            return this.level_;
        }

        public int getMessage() {
            return this.message_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return VersionRequirement.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v1 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.version_) : 0;
            if((this.bitField0_ & 2) == 2) {
                v1 += CodedOutputStream.computeInt32Size(2, this.versionFull_);
            }
            if((this.bitField0_ & 4) == 4) {
                v1 += CodedOutputStream.computeEnumSize(3, this.level_.getNumber());
            }
            if((this.bitField0_ & 8) == 8) {
                v1 += CodedOutputStream.computeInt32Size(4, this.errorCode_);
            }
            if((this.bitField0_ & 16) == 16) {
                v1 += CodedOutputStream.computeInt32Size(5, this.message_);
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                v1 += CodedOutputStream.computeEnumSize(6, this.versionKind_.getNumber());
            }
            int v2 = v1 + this.unknownFields.size();
            this.memoizedSerializedSize = v2;
            return v2;
        }

        public int getVersion() {
            return this.version_;
        }

        public int getVersionFull() {
            return this.versionFull_;
        }

        public VersionKind getVersionKind() {
            return this.versionKind_;
        }

        public boolean hasErrorCode() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasLevel() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasMessage() {
            return (this.bitField0_ & 16) == 16;
        }

        public boolean hasVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasVersionFull() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasVersionKind() {
            return (this.bitField0_ & 0x20) == 0x20;
        }

        private void initFields() {
            this.version_ = 0;
            this.versionFull_ = 0;
            this.level_ = Level.ERROR;
            this.errorCode_ = 0;
            this.message_ = 0;
            this.versionKind_ = VersionKind.LANGUAGE_VERSION;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder.access$21000();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder newBuilder(VersionRequirement protoBuf$VersionRequirement0) {
            return VersionRequirement.newBuilder().mergeFrom(protoBuf$VersionRequirement0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder newBuilderForType() {
            return VersionRequirement.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Builder toBuilder() {
            return VersionRequirement.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(1, this.version_);
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeInt32(2, this.versionFull_);
            }
            if((this.bitField0_ & 4) == 4) {
                codedOutputStream0.writeEnum(3, this.level_.getNumber());
            }
            if((this.bitField0_ & 8) == 8) {
                codedOutputStream0.writeInt32(4, this.errorCode_);
            }
            if((this.bitField0_ & 16) == 16) {
                codedOutputStream0.writeInt32(5, this.message_);
            }
            if((this.bitField0_ & 0x20) == 0x20) {
                codedOutputStream0.writeEnum(6, this.versionKind_.getNumber());
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class VersionRequirementTable extends GeneratedMessageLite implements ProtoBuf.VersionRequirementTableOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements ProtoBuf.VersionRequirementTableOrBuilder {
            private int bitField0_;
            private List requirement_;

            private kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder() {
                this.requirement_ = Collections.EMPTY_LIST;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder access$22100() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder.create();
            }

            public VersionRequirementTable build() {
                VersionRequirementTable protoBuf$VersionRequirementTable0 = this.buildPartial();
                if(!protoBuf$VersionRequirementTable0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder.newUninitializedMessageException(protoBuf$VersionRequirementTable0);
                }
                return protoBuf$VersionRequirementTable0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public VersionRequirementTable buildPartial() {
                VersionRequirementTable protoBuf$VersionRequirementTable0 = new VersionRequirementTable(this, null);
                if((this.bitField0_ & 1) == 1) {
                    this.requirement_ = Collections.unmodifiableList(this.requirement_);
                    this.bitField0_ &= -2;
                }
                protoBuf$VersionRequirementTable0.requirement_ = this.requirement_;
                return protoBuf$VersionRequirementTable0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder();
            }

            private void ensureRequirementIsMutable() {
                if((this.bitField0_ & 1) != 1) {
                    this.requirement_ = new ArrayList(this.requirement_);
                    this.bitField0_ |= 1;
                }
            }

            public VersionRequirementTable getDefaultInstanceForType() {
                return VersionRequirementTable.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder mergeFrom(VersionRequirementTable protoBuf$VersionRequirementTable0) {
                if(protoBuf$VersionRequirementTable0 == VersionRequirementTable.getDefaultInstance()) {
                    return this;
                }
                if(!protoBuf$VersionRequirementTable0.requirement_.isEmpty()) {
                    if(this.requirement_.isEmpty()) {
                        this.requirement_ = protoBuf$VersionRequirementTable0.requirement_;
                        this.bitField0_ &= -2;
                    }
                    else {
                        this.ensureRequirementIsMutable();
                        this.requirement_.addAll(protoBuf$VersionRequirementTable0.requirement_);
                    }
                }
                this.setUnknownFields(this.getUnknownFields().concat(protoBuf$VersionRequirementTable0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                VersionRequirementTable protoBuf$VersionRequirementTable1;
                VersionRequirementTable protoBuf$VersionRequirementTable0;
                try {
                    try {
                        protoBuf$VersionRequirementTable0 = null;
                        protoBuf$VersionRequirementTable1 = (VersionRequirementTable)VersionRequirementTable.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        VersionRequirementTable protoBuf$VersionRequirementTable2 = (VersionRequirementTable)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            protoBuf$VersionRequirementTable0 = protoBuf$VersionRequirementTable2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(protoBuf$VersionRequirementTable0 != null) {
                    this.mergeFrom(protoBuf$VersionRequirementTable0);
                }
                throw throwable0;
            label_13:
                if(protoBuf$VersionRequirementTable1 != null) {
                    this.mergeFrom(protoBuf$VersionRequirementTable1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((VersionRequirementTable)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }
        }

        public static Parser PARSER;
        private static final VersionRequirementTable defaultInstance;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List requirement_;
        private final ByteString unknownFields;

        static {
            VersionRequirementTable.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public VersionRequirementTable parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new VersionRequirementTable(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            VersionRequirementTable protoBuf$VersionRequirementTable0 = new VersionRequirementTable(true);
            VersionRequirementTable.defaultInstance = protoBuf$VersionRequirementTable0;
            protoBuf$VersionRequirementTable0.initFields();
        }

        private VersionRequirementTable(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            boolean z = false;
            boolean z1 = false;
            while(!z1) {
                try {
                    try {
                        int v = codedInputStream0.readTag();
                        switch(v) {
                            case 0: {
                                z1 = true;
                                continue;
                            }
                            case 10: {
                                break;
                            }
                            default: {
                                if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v)) {
                                    z1 = true;
                                    continue;
                                }
                                continue;
                            }
                        }
                        if(!z) {
                            this.requirement_ = new ArrayList();
                            z = true;
                        }
                        this.requirement_.add(codedInputStream0.readMessage(VersionRequirement.PARSER, extensionRegistryLite0));
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if(z) {
                        this.requirement_ = Collections.unmodifiableList(this.requirement_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    throw throwable0;
                }
            }
            if(z) {
                this.requirement_ = Collections.unmodifiableList(this.requirement_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
        }

        VersionRequirementTable(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private VersionRequirementTable(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        VersionRequirementTable(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 protoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private VersionRequirementTable(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static VersionRequirementTable getDefaultInstance() {
            return VersionRequirementTable.defaultInstance;
        }

        public VersionRequirementTable getDefaultInstanceForType() {
            return VersionRequirementTable.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return VersionRequirementTable.PARSER;
        }

        public int getRequirementCount() {
            return this.requirement_.size();
        }

        public List getRequirementList() {
            return this.requirement_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v2 = 0;
            for(int v1 = 0; v1 < this.requirement_.size(); ++v1) {
                v2 += CodedOutputStream.computeMessageSize(1, ((MessageLite)this.requirement_.get(v1)));
            }
            int v3 = v2 + this.unknownFields.size();
            this.memoizedSerializedSize = v3;
            return v3;
        }

        private void initFields() {
            this.requirement_ = Collections.EMPTY_LIST;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder.access$22100();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder newBuilder(VersionRequirementTable protoBuf$VersionRequirementTable0) {
            return VersionRequirementTable.newBuilder().mergeFrom(protoBuf$VersionRequirementTable0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder newBuilderForType() {
            return VersionRequirementTable.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.Builder toBuilder() {
            return VersionRequirementTable.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            for(int v = 0; v < this.requirement_.size(); ++v) {
                codedOutputStream0.writeMessage(1, ((MessageLite)this.requirement_.get(v)));
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static enum Visibility implements EnumLite {
        INTERNAL(0, 0),
        PRIVATE(1, 1),
        PROTECTED(2, 2),
        PUBLIC(3, 3),
        PRIVATE_TO_THIS(4, 4),
        LOCAL(5, 5);

        private static EnumLiteMap internalValueMap;
        private final int value;

        static {
            Visibility.internalValueMap = new EnumLiteMap() {
                public Visibility findValueByNumber(int v) {
                    return Visibility.valueOf(v);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                public EnumLite findValueByNumber(int v) {
                    return this.findValueByNumber(v);
                }
            };
        }

        private Visibility(int v1, int v2) {
            this.value = v2;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static Visibility valueOf(int v) {
            switch(v) {
                case 0: {
                    return Visibility.INTERNAL;
                }
                case 1: {
                    return Visibility.PRIVATE;
                }
                case 2: {
                    return Visibility.PROTECTED;
                }
                case 3: {
                    return Visibility.PUBLIC;
                }
                case 4: {
                    return Visibility.PRIVATE_TO_THIS;
                }
                case 5: {
                    return Visibility.LOCAL;
                }
                default: {
                    return null;
                }
            }
        }
    }


    class kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.1 {
    }

}

