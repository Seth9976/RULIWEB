package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;

public final class WireFormat {
    // 部分失败：枚举糖化
    // 枚举按原样呈现，而不是糖化为Java 5枚举。
    public static class FieldType extends Enum {
        private static final FieldType[] $VALUES;
        public static final enum FieldType BOOL;
        public static final enum FieldType BYTES;
        public static final enum FieldType DOUBLE;
        public static final enum FieldType ENUM;
        public static final enum FieldType FIXED32;
        public static final enum FieldType FIXED64;
        public static final enum FieldType FLOAT;
        public static final enum FieldType GROUP;
        public static final enum FieldType INT32;
        public static final enum FieldType INT64;
        public static final enum FieldType MESSAGE;
        public static final enum FieldType SFIXED32;
        public static final enum FieldType SFIXED64;
        public static final enum FieldType SINT32;
        public static final enum FieldType SINT64;
        public static final enum FieldType STRING;
        public static final enum FieldType UINT32;
        public static final enum FieldType UINT64;
        private final JavaType javaType;
        private final int wireType;

        static {
            FieldType.DOUBLE = new FieldType("DOUBLE", 0, JavaType.DOUBLE, 1);
            FieldType.FLOAT = new FieldType("FLOAT", 1, JavaType.FLOAT, 5);
            FieldType.INT64 = new FieldType("INT64", 2, JavaType.LONG, 0);
            FieldType.UINT64 = new FieldType("UINT64", 3, JavaType.LONG, 0);
            FieldType.INT32 = new FieldType("INT32", 4, JavaType.INT, 0);
            FieldType.FIXED64 = new FieldType("FIXED64", 5, JavaType.LONG, 1);
            FieldType.FIXED32 = new FieldType("FIXED32", 6, JavaType.INT, 5);
            FieldType.BOOL = new FieldType("BOOL", 7, JavaType.BOOLEAN, 0);
            com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.1 wireFormat$FieldType$10 = new FieldType("STRING", 8, JavaType.STRING, 2) {
                @Override  // com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType
                public boolean isPackable() {
                    return false;
                }
            };
            FieldType.STRING = wireFormat$FieldType$10;
            com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.2 wireFormat$FieldType$20 = new FieldType("GROUP", 9, JavaType.MESSAGE, 3) {
                @Override  // com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType
                public boolean isPackable() {
                    return false;
                }
            };
            FieldType.GROUP = wireFormat$FieldType$20;
            com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.3 wireFormat$FieldType$30 = new FieldType("MESSAGE", 10, JavaType.MESSAGE, 2) {
                @Override  // com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType
                public boolean isPackable() {
                    return false;
                }
            };
            FieldType.MESSAGE = wireFormat$FieldType$30;
            com.google.crypto.tink.shaded.protobuf.WireFormat.FieldType.4 wireFormat$FieldType$40 = new FieldType("BYTES", 11, JavaType.BYTE_STRING, 2) {
                @Override  // com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType
                public boolean isPackable() {
                    return false;
                }
            };
            FieldType.BYTES = wireFormat$FieldType$40;
            FieldType.UINT32 = new FieldType("UINT32", 12, JavaType.INT, 0);
            FieldType.ENUM = new FieldType("ENUM", 13, JavaType.ENUM, 0);
            FieldType.SFIXED32 = new FieldType("SFIXED32", 14, JavaType.INT, 5);
            FieldType.SFIXED64 = new FieldType("SFIXED64", 15, JavaType.LONG, 1);
            FieldType.SINT32 = new FieldType("SINT32", 16, JavaType.INT, 0);
            FieldType.SINT64 = new FieldType("SINT64", 17, JavaType.LONG, 0);
            FieldType.$VALUES = new FieldType[]{FieldType.DOUBLE, FieldType.FLOAT, FieldType.INT64, FieldType.UINT64, FieldType.INT32, FieldType.FIXED64, FieldType.FIXED32, FieldType.BOOL, wireFormat$FieldType$10, wireFormat$FieldType$20, wireFormat$FieldType$30, wireFormat$FieldType$40, FieldType.UINT32, FieldType.ENUM, FieldType.SFIXED32, FieldType.SFIXED64, FieldType.SINT32, FieldType.SINT64};
        }

        private FieldType(String s, int v, JavaType wireFormat$JavaType0, int v1) {
            super(s, v);
            this.javaType = wireFormat$JavaType0;
            this.wireType = v1;
        }

        FieldType(String s, int v, JavaType wireFormat$JavaType0, int v1, com.google.crypto.tink.shaded.protobuf.WireFormat.1 wireFormat$10) {
            this(s, v, wireFormat$JavaType0, v1);
        }

        public JavaType getJavaType() {
            return this.javaType;
        }

        public int getWireType() {
            return this.wireType;
        }

        public boolean isPackable() {
            return true;
        }

        public static FieldType valueOf(String s) {
            return (FieldType)Enum.valueOf(FieldType.class, s);
        }

        public static FieldType[] values() {
            return (FieldType[])FieldType.$VALUES.clone();
        }
    }

    public static enum JavaType {
        INT(0),
        LONG(0L),
        FLOAT(0.0f),
        DOUBLE(0.0),
        BOOLEAN(Boolean.FALSE),
        STRING(""),
        BYTE_STRING(ByteString.EMPTY),
        ENUM(null),
        MESSAGE(null);

        private final Object defaultDefault;

        private JavaType(Object object0) {
            this.defaultDefault = object0;
        }

        Object getDefaultDefault() {
            return this.defaultDefault;
        }
    }

    // 部分失败：枚举糖化
    // 枚举按原样呈现，而不是糖化为Java 5枚举。
    static abstract class Utf8Validation extends Enum {
        private static final Utf8Validation[] $VALUES;
        public static final enum Utf8Validation LAZY;
        public static final enum Utf8Validation LOOSE;
        public static final enum Utf8Validation STRICT;

        static {
            com.google.crypto.tink.shaded.protobuf.WireFormat.Utf8Validation.1 wireFormat$Utf8Validation$10 = new Utf8Validation("LOOSE", 0) {
                @Override  // com.google.crypto.tink.shaded.protobuf.WireFormat$Utf8Validation
                Object readString(CodedInputStream codedInputStream0) throws IOException {
                    return codedInputStream0.readString();
                }
            };
            Utf8Validation.LOOSE = wireFormat$Utf8Validation$10;
            com.google.crypto.tink.shaded.protobuf.WireFormat.Utf8Validation.2 wireFormat$Utf8Validation$20 = new Utf8Validation("STRICT", 1) {
                @Override  // com.google.crypto.tink.shaded.protobuf.WireFormat$Utf8Validation
                Object readString(CodedInputStream codedInputStream0) throws IOException {
                    return codedInputStream0.readStringRequireUtf8();
                }
            };
            Utf8Validation.STRICT = wireFormat$Utf8Validation$20;
            com.google.crypto.tink.shaded.protobuf.WireFormat.Utf8Validation.3 wireFormat$Utf8Validation$30 = new Utf8Validation("LAZY", 2) {
                @Override  // com.google.crypto.tink.shaded.protobuf.WireFormat$Utf8Validation
                Object readString(CodedInputStream codedInputStream0) throws IOException {
                    return codedInputStream0.readBytes();
                }
            };
            Utf8Validation.LAZY = wireFormat$Utf8Validation$30;
            Utf8Validation.$VALUES = new Utf8Validation[]{wireFormat$Utf8Validation$10, wireFormat$Utf8Validation$20, wireFormat$Utf8Validation$30};
        }

        private Utf8Validation(String s, int v) {
            super(s, v);
        }

        Utf8Validation(String s, int v, com.google.crypto.tink.shaded.protobuf.WireFormat.1 wireFormat$10) {
            this(s, v);
        }

        abstract Object readString(CodedInputStream arg1) throws IOException;

        public static Utf8Validation valueOf(String s) {
            return (Utf8Validation)Enum.valueOf(Utf8Validation.class, s);
        }

        public static Utf8Validation[] values() {
            return (Utf8Validation[])Utf8Validation.$VALUES.clone();
        }
    }

    static final int FIXED32_SIZE = 4;
    static final int FIXED64_SIZE = 8;
    static final int MAX_VARINT32_SIZE = 5;
    static final int MAX_VARINT64_SIZE = 10;
    static final int MAX_VARINT_SIZE = 10;
    static final int MESSAGE_SET_ITEM = 1;
    static final int MESSAGE_SET_ITEM_END_TAG = 0;
    static final int MESSAGE_SET_ITEM_TAG = 0;
    static final int MESSAGE_SET_MESSAGE = 3;
    static final int MESSAGE_SET_MESSAGE_TAG = 0;
    static final int MESSAGE_SET_TYPE_ID = 2;
    static final int MESSAGE_SET_TYPE_ID_TAG = 0;
    static final int TAG_TYPE_BITS = 3;
    static final int TAG_TYPE_MASK = 7;
    public static final int WIRETYPE_END_GROUP = 4;
    public static final int WIRETYPE_FIXED32 = 5;
    public static final int WIRETYPE_FIXED64 = 1;
    public static final int WIRETYPE_LENGTH_DELIMITED = 2;
    public static final int WIRETYPE_START_GROUP = 3;
    public static final int WIRETYPE_VARINT;

    static {
        WireFormat.MESSAGE_SET_ITEM_TAG = 11;
        WireFormat.MESSAGE_SET_ITEM_END_TAG = 12;
        WireFormat.MESSAGE_SET_TYPE_ID_TAG = 16;
        WireFormat.MESSAGE_SET_MESSAGE_TAG = 26;
    }

    public static int getTagFieldNumber(int tag) [...] // Inlined contents

    public static int getTagWireType(int tag) [...] // Inlined contents

    static int makeTag(int fieldNumber, int wireType) [...] // Inlined contents

    static Object readPrimitiveField(CodedInputStream codedInputStream0, FieldType wireFormat$FieldType0, Utf8Validation wireFormat$Utf8Validation0) throws IOException {
        switch(com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[wireFormat$FieldType0.ordinal()]) {
            case 1: {
                return codedInputStream0.readDouble();
            }
            case 2: {
                return codedInputStream0.readFloat();
            }
            case 3: {
                return codedInputStream0.readInt64();
            }
            case 4: {
                return codedInputStream0.readUInt64();
            }
            case 5: {
                return codedInputStream0.readInt32();
            }
            case 6: {
                return codedInputStream0.readFixed64();
            }
            case 7: {
                return codedInputStream0.readFixed32();
            }
            case 8: {
                return Boolean.valueOf(codedInputStream0.readBool());
            }
            case 9: {
                return codedInputStream0.readBytes();
            }
            case 10: {
                return codedInputStream0.readUInt32();
            }
            case 11: {
                return codedInputStream0.readSFixed32();
            }
            case 12: {
                return codedInputStream0.readSFixed64();
            }
            case 13: {
                return codedInputStream0.readSInt32();
            }
            case 14: {
                return codedInputStream0.readSInt64();
            }
            case 15: {
                return wireFormat$Utf8Validation0.readString(codedInputStream0);
            }
            case 16: {
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            }
            case 17: {
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            }
            case 18: {
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            }
            default: {
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
            }
        }
    }

    class com.google.crypto.tink.shaded.protobuf.WireFormat.1 {
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] arr_v = new int[FieldType.values().length];
            com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType = arr_v;
            try {
                arr_v[FieldType.DOUBLE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FLOAT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT64.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT64.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT32.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED64.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED32.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BOOL.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BYTES.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT32.ordinal()] = 10;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED32.ordinal()] = 11;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED64.ordinal()] = 12;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT32.ordinal()] = 13;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT64.ordinal()] = 14;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.STRING.ordinal()] = 15;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.GROUP.ordinal()] = 16;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.MESSAGE.ordinal()] = 17;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.WireFormat.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.ENUM.ordinal()] = 18;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

