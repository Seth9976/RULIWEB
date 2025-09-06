package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class FlexBuffers {
    public static class Blob extends Sized {
        static final boolean $assertionsDisabled;
        static final Blob EMPTY;

        static {
            Blob.EMPTY = new Blob(FlexBuffers.EMPTY_BB, 1, 1);
        }

        Blob(ReadBuf readBuf0, int v, int v1) {
            super(readBuf0, v, v1);
        }

        public ByteBuffer data() {
            ByteBuffer byteBuffer0 = ByteBuffer.wrap(this.bb.data());
            byteBuffer0.position(this.end);
            byteBuffer0.limit(this.end + this.size());
            return byteBuffer0.asReadOnlyBuffer().slice();
        }

        public static Blob empty() {
            return Blob.EMPTY;
        }

        public byte get(int v) {
            return this.bb.get(this.end + v);
        }

        public byte[] getBytes() {
            int v = this.size();
            byte[] arr_b = new byte[v];
            for(int v1 = 0; v1 < v; ++v1) {
                arr_b[v1] = this.bb.get(this.end + v1);
            }
            return arr_b;
        }

        @Override  // androidx.emoji2.text.flatbuffer.FlexBuffers$Sized
        public int size() {
            return super.size();
        }

        @Override  // androidx.emoji2.text.flatbuffer.FlexBuffers$Object
        public String toString() {
            return this.bb.getString(this.end, this.size());
        }

        @Override  // androidx.emoji2.text.flatbuffer.FlexBuffers$Object
        public StringBuilder toString(StringBuilder stringBuilder0) {
            stringBuilder0.append('\"');
            stringBuilder0.append(this.bb.getString(this.end, this.size()));
            stringBuilder0.append('\"');
            return stringBuilder0;
        }
    }

    public static class FlexBufferException extends RuntimeException {
        FlexBufferException(String s) {
            super(s);
        }
    }

    public static class Key extends Object {
        private static final Key EMPTY;

        static {
            Key.EMPTY = new Key(FlexBuffers.EMPTY_BB, 0, 0);
        }

        Key(ReadBuf readBuf0, int v, int v1) {
            super(readBuf0, v, v1);
        }

        int compareTo(byte[] arr_b) {
            int v3;
            int v2;
            int v = this.end;
            int v1 = 0;
            do {
                v2 = this.bb.get(v);
                v3 = arr_b[v1];
                if(v2 == 0) {
                    return -v3;
                }
                ++v;
                ++v1;
                if(v1 == arr_b.length) {
                    return v2 - v3;
                }
            }
            while(v2 == v3);
            return v2 - v3;
        }

        public static Key empty() {
            return Key.EMPTY;
        }

        @Override
        public boolean equals(java.lang.Object object0) {
            return object0 instanceof Key ? ((Key)object0).end == this.end && ((Key)object0).byteWidth == this.byteWidth : false;
        }

        @Override
        public int hashCode() {
            return this.end ^ this.byteWidth;
        }

        @Override  // androidx.emoji2.text.flatbuffer.FlexBuffers$Object
        public String toString() {
            int v;
            for(v = this.end; this.bb.get(v) != 0; ++v) {
            }
            return this.bb.getString(this.end, v - this.end);
        }

        @Override  // androidx.emoji2.text.flatbuffer.FlexBuffers$Object
        public StringBuilder toString(StringBuilder stringBuilder0) {
            stringBuilder0.append(this.toString());
            return stringBuilder0;
        }
    }

    public static class KeyVector {
        private final TypedVector vec;

        KeyVector(TypedVector flexBuffers$TypedVector0) {
            this.vec = flexBuffers$TypedVector0;
        }

        public Key get(int v) {
            return v < this.size() ? new Key(this.vec.bb, FlexBuffers.indirect(this.vec.bb, this.vec.end + v * this.vec.byteWidth, this.vec.byteWidth), 1) : Key.EMPTY;
        }

        public int size() {
            return this.vec.size();
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append('[');
            for(int v = 0; v < this.vec.size(); ++v) {
                this.vec.get(v).toString(stringBuilder0);
                if(v != this.vec.size() - 1) {
                    stringBuilder0.append(", ");
                }
            }
            stringBuilder0.append("]");
            return stringBuilder0.toString();
        }
    }

    public static class Map extends Vector {
        private static final Map EMPTY_MAP;

        static {
            Map.EMPTY_MAP = new Map(FlexBuffers.EMPTY_BB, 1, 1);
        }

        Map(ReadBuf readBuf0, int v, int v1) {
            super(readBuf0, v, v1);
        }

        private int binarySearch(KeyVector flexBuffers$KeyVector0, byte[] arr_b) {
            int v = flexBuffers$KeyVector0.size() - 1;
            int v1 = 0;
            while(v1 <= v) {
                int v2 = v1 + v >>> 1;
                int v3 = flexBuffers$KeyVector0.get(v2).compareTo(arr_b);
                if(v3 < 0) {
                    v1 = v2 + 1;
                    continue;
                }
                if(v3 > 0) {
                    v = v2 - 1;
                    continue;
                }
                return v2;
            }
            return -(v1 + 1);
        }

        public static Map empty() {
            return Map.EMPTY_MAP;
        }

        public Reference get(String s) {
            return this.get(s.getBytes(StandardCharsets.UTF_8));
        }

        public Reference get(byte[] arr_b) {
            KeyVector flexBuffers$KeyVector0 = this.keys();
            int v = this.binarySearch(flexBuffers$KeyVector0, arr_b);
            return v < 0 || v >= flexBuffers$KeyVector0.size() ? Reference.NULL_REFERENCE : this.get(v);
        }

        public KeyVector keys() {
            int v = this.end - this.byteWidth * 3;
            return new KeyVector(new TypedVector(this.bb, FlexBuffers.indirect(this.bb, v, this.byteWidth), FlexBuffers.readInt(this.bb, v + this.byteWidth, this.byteWidth), 4));
        }

        @Override  // androidx.emoji2.text.flatbuffer.FlexBuffers$Vector
        public StringBuilder toString(StringBuilder stringBuilder0) {
            stringBuilder0.append("{ ");
            KeyVector flexBuffers$KeyVector0 = this.keys();
            int v = this.size();
            Vector flexBuffers$Vector0 = this.values();
            for(int v1 = 0; v1 < v; ++v1) {
                stringBuilder0.append('\"');
                stringBuilder0.append(flexBuffers$KeyVector0.get(v1).toString());
                stringBuilder0.append("\" : ");
                stringBuilder0.append(flexBuffers$Vector0.get(v1).toString());
                if(v1 != v - 1) {
                    stringBuilder0.append(", ");
                }
            }
            stringBuilder0.append(" }");
            return stringBuilder0;
        }

        public Vector values() {
            return new Vector(this.bb, this.end, this.byteWidth);
        }
    }

    static abstract class Object {
        ReadBuf bb;
        int byteWidth;
        int end;

        Object(ReadBuf readBuf0, int v, int v1) {
            this.bb = readBuf0;
            this.end = v;
            this.byteWidth = v1;
        }

        @Override
        public String toString() {
            return this.toString(new StringBuilder(0x80)).toString();
        }

        public abstract StringBuilder toString(StringBuilder arg1);
    }

    public static class Reference {
        private static final Reference NULL_REFERENCE;
        private ReadBuf bb;
        private int byteWidth;
        private int end;
        private int parentWidth;
        private int type;

        static {
            Reference.NULL_REFERENCE = new Reference(FlexBuffers.EMPTY_BB, 0, 1, 0);
        }

        Reference(ReadBuf readBuf0, int v, int v1, int v2) {
            this(readBuf0, v, v1, 1 << (v2 & 3), v2 >> 2);
        }

        Reference(ReadBuf readBuf0, int v, int v1, int v2, int v3) {
            this.bb = readBuf0;
            this.end = v;
            this.parentWidth = v1;
            this.byteWidth = v2;
            this.type = v3;
        }

        // 去混淆评级： 低(20)
        public Blob asBlob() {
            return this.isBlob() || this.isString() ? new Blob(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth) : Blob.empty();
        }

        // 去混淆评级： 低(20)
        public boolean asBoolean() {
            return this.isBoolean() ? this.bb.get(this.end) != 0 : this.asUInt() != 0L;
        }

        public double asFloat() {
            switch(this.type) {
                case 1: {
                    return (double)FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
                }
                case 3: {
                    return FlexBuffers.readDouble(this.bb, this.end, this.parentWidth);
                }
                case 5: {
                    return Double.parseDouble(this.asString());
                }
                case 6: {
                    return (double)FlexBuffers.readInt(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
                }
                case 7: {
                    return (double)FlexBuffers.readUInt(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
                }
                case 8: {
                    return FlexBuffers.readDouble(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
                }
                case 10: {
                    return (double)this.asVector().size();
                }
                case 2: 
                case 26: {
                    return (double)FlexBuffers.readUInt(this.bb, this.end, this.parentWidth);
                }
                default: {
                    return 0.0;
                }
            }
        }

        public int asInt() {
            switch(this.type) {
                case 1: {
                    return FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
                }
                case 2: {
                    return (int)FlexBuffers.readUInt(this.bb, this.end, this.parentWidth);
                }
                case 3: {
                    return (int)FlexBuffers.readDouble(this.bb, this.end, this.parentWidth);
                }
                case 5: {
                    return Integer.parseInt(this.asString());
                }
                case 6: {
                    return FlexBuffers.readInt(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
                }
                case 7: {
                    return (int)FlexBuffers.readUInt(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.parentWidth);
                }
                case 8: {
                    return (int)FlexBuffers.readDouble(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
                }
                case 10: {
                    return this.asVector().size();
                }
                case 26: {
                    return FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
                }
                default: {
                    return 0;
                }
            }
        }

        // 去混淆评级： 低(20)
        public Key asKey() {
            return this.isKey() ? new Key(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth) : Key.empty();
        }

        public long asLong() {
            switch(this.type) {
                case 1: {
                    return FlexBuffers.readLong(this.bb, this.end, this.parentWidth);
                }
                case 2: {
                    return FlexBuffers.readUInt(this.bb, this.end, this.parentWidth);
                }
                case 3: {
                    return (long)FlexBuffers.readDouble(this.bb, this.end, this.parentWidth);
                }
                case 5: {
                    try {
                        return Long.parseLong(this.asString());
                    }
                    catch(NumberFormatException unused_ex) {
                        return 0L;
                    }
                }
                case 6: {
                    return FlexBuffers.readLong(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
                }
                case 7: {
                    return FlexBuffers.readUInt(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.parentWidth);
                }
                case 8: {
                    return (long)FlexBuffers.readDouble(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
                }
                case 10: {
                    return (long)this.asVector().size();
                }
                case 26: {
                    return (long)FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
                }
                default: {
                    return 0L;
                }
            }
        }

        // 去混淆评级： 低(20)
        public Map asMap() {
            return this.isMap() ? new Map(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth) : Map.empty();
        }

        public String asString() {
            if(this.isString()) {
                int v = FlexBuffers.indirect(this.bb, this.end, this.parentWidth);
                int v1 = (int)FlexBuffers.readUInt(this.bb, v - this.byteWidth, this.byteWidth);
                return this.bb.getString(v, v1);
            }
            if(this.isKey()) {
                int v2 = FlexBuffers.indirect(this.bb, this.end, this.byteWidth);
                int v3;
                for(v3 = v2; this.bb.get(v3) != 0; ++v3) {
                }
                return this.bb.getString(v2, v3 - v2);
            }
            return "";
        }

        public long asUInt() {
            switch(this.type) {
                case 1: {
                    return FlexBuffers.readLong(this.bb, this.end, this.parentWidth);
                }
                case 2: {
                    return FlexBuffers.readUInt(this.bb, this.end, this.parentWidth);
                }
                case 3: {
                    return (long)FlexBuffers.readDouble(this.bb, this.end, this.parentWidth);
                }
                case 5: {
                    return Long.parseLong(this.asString());
                }
                case 6: {
                    return FlexBuffers.readLong(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
                }
                case 7: {
                    return FlexBuffers.readUInt(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
                }
                case 8: {
                    return (long)FlexBuffers.readDouble(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.parentWidth);
                }
                case 10: {
                    return (long)this.asVector().size();
                }
                case 26: {
                    return (long)FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
                }
                default: {
                    return 0L;
                }
            }
        }

        public Vector asVector() {
            if(this.isVector()) {
                return new Vector(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth);
            }
            int v = this.type;
            if(v == 15) {
                return new TypedVector(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth, 4);
            }
            return FlexBuffers.isTypedVector(v) ? new TypedVector(this.bb, FlexBuffers.indirect(this.bb, this.end, this.parentWidth), this.byteWidth, this.type - 10) : Vector.empty();
        }

        public int getType() {
            return this.type;
        }

        public boolean isBlob() {
            return this.type == 25;
        }

        public boolean isBoolean() {
            return this.type == 26;
        }

        public boolean isFloat() {
            return this.type == 3 || this.type == 8;
        }

        public boolean isInt() {
            return this.type == 1 || this.type == 6;
        }

        // 去混淆评级： 低(20)
        public boolean isIntOrUInt() {
            return this.isInt() || this.isUInt();
        }

        public boolean isKey() {
            return this.type == 4;
        }

        public boolean isMap() {
            return this.type == 9;
        }

        public boolean isNull() {
            return this.type == 0;
        }

        // 去混淆评级： 低(20)
        public boolean isNumeric() {
            return this.isIntOrUInt() || this.isFloat();
        }

        public boolean isString() {
            return this.type == 5;
        }

        public boolean isTypedVector() {
            return FlexBuffers.isTypedVector(this.type);
        }

        public boolean isUInt() {
            return this.type == 2 || this.type == 7;
        }

        public boolean isVector() {
            return this.type == 9 || this.type == 10;
        }

        @Override
        public String toString() {
            return this.toString(new StringBuilder(0x80)).toString();
        }

        StringBuilder toString(StringBuilder stringBuilder0) {
            int v = this.type;
            if(v != 36) {
                switch(v) {
                    case 0: {
                        stringBuilder0.append("null");
                        return stringBuilder0;
                    }
                    case 4: {
                        Key flexBuffers$Key0 = this.asKey();
                        stringBuilder0.append('\"');
                        StringBuilder stringBuilder1 = flexBuffers$Key0.toString(stringBuilder0);
                        stringBuilder1.append('\"');
                        return stringBuilder1;
                    }
                    case 5: {
                        stringBuilder0.append('\"');
                        stringBuilder0.append(this.asString());
                        stringBuilder0.append('\"');
                        return stringBuilder0;
                    }
                    case 1: 
                    case 6: {
                        stringBuilder0.append(this.asLong());
                        return stringBuilder0;
                    }
                    case 2: 
                    case 7: {
                        stringBuilder0.append(this.asUInt());
                        return stringBuilder0;
                    }
                    case 3: 
                    case 8: {
                        stringBuilder0.append(this.asFloat());
                        return stringBuilder0;
                    }
                    case 9: {
                        return this.asMap().toString(stringBuilder0);
                    }
                    case 10: {
                        return this.asVector().toString(stringBuilder0);
                    }
                    case 11: 
                    case 12: 
                    case 13: 
                    case 14: 
                    case 15: {
                        break;
                    }
                    case 16: 
                    case 17: 
                    case 18: 
                    case 19: 
                    case 20: 
                    case 21: 
                    case 22: 
                    case 23: 
                    case 24: {
                        throw new FlexBufferException("not_implemented:" + this.type);
                    }
                    case 25: {
                        return this.asBlob().toString(stringBuilder0);
                    }
                    case 26: {
                        stringBuilder0.append(this.asBoolean());
                        return stringBuilder0;
                    }
                    default: {
                        return stringBuilder0;
                    }
                }
            }
            stringBuilder0.append(this.asVector());
            return stringBuilder0;
        }
    }

    static abstract class Sized extends Object {
        protected final int size;

        Sized(ReadBuf readBuf0, int v, int v1) {
            super(readBuf0, v, v1);
            this.size = FlexBuffers.readInt(this.bb, v - v1, v1);
        }

        public int size() {
            return this.size;
        }
    }

    public static class TypedVector extends Vector {
        private static final TypedVector EMPTY_VECTOR;
        private final int elemType;

        static {
            TypedVector.EMPTY_VECTOR = new TypedVector(FlexBuffers.EMPTY_BB, 1, 1, 1);
        }

        TypedVector(ReadBuf readBuf0, int v, int v1, int v2) {
            super(readBuf0, v, v1);
            this.elemType = v2;
        }

        public static TypedVector empty() {
            return TypedVector.EMPTY_VECTOR;
        }

        @Override  // androidx.emoji2.text.flatbuffer.FlexBuffers$Vector
        public Reference get(int v) {
            return v < this.size() ? new Reference(this.bb, this.end + v * this.byteWidth, this.byteWidth, 1, this.elemType) : Reference.NULL_REFERENCE;
        }

        public int getElemType() {
            return this.elemType;
        }

        public boolean isEmptyVector() {
            return this == TypedVector.EMPTY_VECTOR;
        }
    }

    static class Unsigned {
        static int byteToUnsignedInt(byte b) [...] // Inlined contents

        static long intToUnsignedLong(int v) [...] // Inlined contents

        static int shortToUnsignedInt(short v) [...] // Inlined contents
    }

    public static class Vector extends Sized {
        private static final Vector EMPTY_VECTOR;

        static {
            Vector.EMPTY_VECTOR = new Vector(FlexBuffers.EMPTY_BB, 1, 1);
        }

        Vector(ReadBuf readBuf0, int v, int v1) {
            super(readBuf0, v, v1);
        }

        public static Vector empty() {
            return Vector.EMPTY_VECTOR;
        }

        public Reference get(int v) {
            long v1 = (long)this.size();
            if(((long)v) >= v1) {
                return Reference.NULL_REFERENCE;
            }
            int v2 = Unsigned.byteToUnsignedInt(this.bb.get(((int)(((long)this.end) + v1 * ((long)this.byteWidth) + ((long)v)))));
            return new Reference(this.bb, this.end + v * this.byteWidth, this.byteWidth, v2);
        }

        public boolean isEmpty() {
            return this == Vector.EMPTY_VECTOR;
        }

        @Override  // androidx.emoji2.text.flatbuffer.FlexBuffers$Sized
        public int size() {
            return super.size();
        }

        @Override  // androidx.emoji2.text.flatbuffer.FlexBuffers$Object
        public String toString() {
            return super.toString();
        }

        @Override  // androidx.emoji2.text.flatbuffer.FlexBuffers$Object
        public StringBuilder toString(StringBuilder stringBuilder0) {
            stringBuilder0.append("[ ");
            int v = this.size();
            for(int v1 = 0; v1 < v; ++v1) {
                this.get(v1).toString(stringBuilder0);
                if(v1 != v - 1) {
                    stringBuilder0.append(", ");
                }
            }
            stringBuilder0.append(" ]");
            return stringBuilder0;
        }
    }

    static final boolean $assertionsDisabled = false;
    private static final ReadBuf EMPTY_BB = null;
    public static final int FBT_BLOB = 25;
    public static final int FBT_BOOL = 26;
    public static final int FBT_FLOAT = 3;
    public static final int FBT_INDIRECT_FLOAT = 8;
    public static final int FBT_INDIRECT_INT = 6;
    public static final int FBT_INDIRECT_UINT = 7;
    public static final int FBT_INT = 1;
    public static final int FBT_KEY = 4;
    public static final int FBT_MAP = 9;
    public static final int FBT_NULL = 0;
    public static final int FBT_STRING = 5;
    public static final int FBT_UINT = 2;
    public static final int FBT_VECTOR = 10;
    public static final int FBT_VECTOR_BOOL = 36;
    public static final int FBT_VECTOR_FLOAT = 13;
    public static final int FBT_VECTOR_FLOAT2 = 18;
    public static final int FBT_VECTOR_FLOAT3 = 21;
    public static final int FBT_VECTOR_FLOAT4 = 24;
    public static final int FBT_VECTOR_INT = 11;
    public static final int FBT_VECTOR_INT2 = 16;
    public static final int FBT_VECTOR_INT3 = 19;
    public static final int FBT_VECTOR_INT4 = 22;
    public static final int FBT_VECTOR_KEY = 14;
    public static final int FBT_VECTOR_STRING_DEPRECATED = 15;
    public static final int FBT_VECTOR_UINT = 12;
    public static final int FBT_VECTOR_UINT2 = 17;
    public static final int FBT_VECTOR_UINT3 = 20;
    public static final int FBT_VECTOR_UINT4 = 23;

    static {
        FlexBuffers.EMPTY_BB = new ArrayReadWriteBuf(new byte[]{0}, 1);
    }

    public static Reference getRoot(ReadBuf readBuf0) {
        int v = readBuf0.limit();
        int v1 = readBuf0.get(v - 1);
        return new Reference(readBuf0, v - 2 - v1, v1, Unsigned.byteToUnsignedInt(readBuf0.get(v - 2)));
    }

    // 去混淆评级： 低(20)
    @Deprecated
    public static Reference getRoot(ByteBuffer byteBuffer0) {
        return byteBuffer0.hasArray() ? FlexBuffers.getRoot(new ArrayReadWriteBuf(byteBuffer0.array(), byteBuffer0.limit())) : FlexBuffers.getRoot(new ByteBufferReadWriteBuf(byteBuffer0));
    }

    private static int indirect(ReadBuf readBuf0, int v, int v1) {
        return (int)(((long)v) - FlexBuffers.readUInt(readBuf0, v, v1));
    }

    static boolean isTypeInline(int v) {
        return v <= 3 || v == 26;
    }

    // 去混淆评级： 低(20)
    static boolean isTypedVector(int v) {
        return v >= 11 && v <= 15 || v == 36;
    }

    // 去混淆评级： 低(20)
    static boolean isTypedVectorElementType(int v) {
        return v >= 1 && v <= 4 || v == 26;
    }

    private static double readDouble(ReadBuf readBuf0, int v, int v1) {
        switch(v1) {
            case 4: {
                return (double)readBuf0.getFloat(v);
            }
            case 8: {
                return readBuf0.getDouble(v);
            }
            default: {
                return -1.0;
            }
        }
    }

    private static int readInt(ReadBuf readBuf0, int v, int v1) {
        return (int)FlexBuffers.readLong(readBuf0, v, v1);
    }

    private static long readLong(ReadBuf readBuf0, int v, int v1) {
        switch(v1) {
            case 1: {
                return (long)readBuf0.get(v);
            }
            case 2: {
                return (long)readBuf0.getShort(v);
            }
            case 4: {
                return (long)readBuf0.getInt(v);
            }
            case 8: {
                return readBuf0.getLong(v);
            }
            default: {
                return -1L;
            }
        }
    }

    private static long readUInt(ReadBuf readBuf0, int v, int v1) {
        switch(v1) {
            case 1: {
                return (long)Unsigned.byteToUnsignedInt(readBuf0.get(v));
            }
            case 2: {
                return (long)Unsigned.shortToUnsignedInt(readBuf0.getShort(v));
            }
            case 4: {
                return Unsigned.intToUnsignedLong(readBuf0.getInt(v));
            }
            case 8: {
                return readBuf0.getLong(v);
            }
            default: {
                return -1L;
            }
        }
    }

    static int toTypedVector(int v, int v1) [...] // Inlined contents

    static int toTypedVectorElementType(int v) [...] // Inlined contents
}

