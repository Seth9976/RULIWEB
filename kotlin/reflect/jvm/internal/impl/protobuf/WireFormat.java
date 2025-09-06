package kotlin.reflect.jvm.internal.impl.protobuf;

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
            kotlin.reflect.jvm.internal.impl.protobuf.WireFormat.FieldType.1 wireFormat$FieldType$10 = new FieldType("STRING", 8, JavaType.STRING, 2) {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.WireFormat$FieldType
                public boolean isPackable() {
                    return false;
                }
            };
            FieldType.STRING = wireFormat$FieldType$10;
            kotlin.reflect.jvm.internal.impl.protobuf.WireFormat.FieldType.2 wireFormat$FieldType$20 = new FieldType("GROUP", 9, JavaType.MESSAGE, 3) {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.WireFormat$FieldType
                public boolean isPackable() {
                    return false;
                }
            };
            FieldType.GROUP = wireFormat$FieldType$20;
            kotlin.reflect.jvm.internal.impl.protobuf.WireFormat.FieldType.3 wireFormat$FieldType$30 = new FieldType("MESSAGE", 10, JavaType.MESSAGE, 2) {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.WireFormat$FieldType
                public boolean isPackable() {
                    return false;
                }
            };
            FieldType.MESSAGE = wireFormat$FieldType$30;
            kotlin.reflect.jvm.internal.impl.protobuf.WireFormat.FieldType.4 wireFormat$FieldType$40 = new FieldType("BYTES", 11, JavaType.BYTE_STRING, 2) {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.WireFormat$FieldType
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

        FieldType(String s, int v, JavaType wireFormat$JavaType0, int v1, kotlin.reflect.jvm.internal.impl.protobuf.WireFormat.1 wireFormat$10) {
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
    }

    static final int MESSAGE_SET_ITEM_END_TAG;
    static final int MESSAGE_SET_ITEM_TAG;
    static final int MESSAGE_SET_MESSAGE_TAG;
    static final int MESSAGE_SET_TYPE_ID_TAG;

    static {
        WireFormat.MESSAGE_SET_ITEM_TAG = 11;
        WireFormat.MESSAGE_SET_ITEM_END_TAG = 12;
        WireFormat.MESSAGE_SET_TYPE_ID_TAG = 16;
        WireFormat.MESSAGE_SET_MESSAGE_TAG = 26;
    }

    public static int getTagFieldNumber(int v) [...] // Inlined contents

    static int getTagWireType(int v) [...] // Inlined contents

    static int makeTag(int v, int v1) [...] // Inlined contents

    class kotlin.reflect.jvm.internal.impl.protobuf.WireFormat.1 {
    }

}

