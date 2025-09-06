package androidx.emoji2.text.flatbuffer;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class FlexBuffersBuilder {
    static class Value {
        static final boolean $assertionsDisabled;
        final double dValue;
        long iValue;
        int key;
        final int minBitWidth;
        final int type;

        static {
        }

        Value(int v, int v1, int v2, double f) {
            this.key = v;
            this.type = v1;
            this.minBitWidth = v2;
            this.dValue = f;
            this.iValue = 0x8000000000000000L;
        }

        Value(int v, int v1, int v2, long v3) {
            this.key = v;
            this.type = v1;
            this.minBitWidth = v2;
            this.iValue = v3;
            this.dValue = 4.900000E-324;
        }

        static int access$100(int v, int v1) {
            return -v & v1 - 1;
        }

        static Value blob(int v, int v1, int v2, int v3) {
            return new Value(v, v2, v3, ((long)v1));
        }

        // 去混淆评级： 低(20)
        static Value bool(int v, boolean z) {
            return z ? new Value(v, 26, 0, 1L) : new Value(v, 26, 0, 0L);
        }

        private int elemWidth(int v, int v1) {
            return Value.elemWidth(this.type, this.minBitWidth, this.iValue, v, v1);
        }

        private static int elemWidth(int v, int v1, long v2, int v3, int v4) {
            if(FlexBuffers.isTypeInline(v)) {
                return v1;
            }
            for(int v5 = 1; v5 <= 0x20; v5 *= 2) {
                int v6 = FlexBuffersBuilder.widthUInBits(((int)(((long)((-v3 & v5 - 1) + v3 + v4 * v5)) - v2)));
                if(1L << v6 == ((long)v5)) {
                    return v6;
                }
            }
            return 3;
        }

        static Value float32(int v, float f) {
            return new Value(v, 3, 2, ((double)f));
        }

        static Value float64(int v, double f) {
            return new Value(v, 3, 3, f);
        }

        static Value int16(int v, int v1) {
            return new Value(v, 1, 1, ((long)v1));
        }

        static Value int32(int v, int v1) {
            return new Value(v, 1, 2, ((long)v1));
        }

        static Value int64(int v, long v1) {
            return new Value(v, 1, 3, v1);
        }

        static Value int8(int v, int v1) {
            return new Value(v, 1, 0, ((long)v1));
        }

        private static byte packedType(int v, int v1) {
            return (byte)(v | v1 << 2);
        }

        private static int paddingBytes(int v, int v1) [...] // Inlined contents

        private byte storedPackedType() {
            return this.storedPackedType(0);
        }

        private byte storedPackedType(int v) {
            return Value.packedType(this.storedWidth(v), this.type);
        }

        // 去混淆评级： 低(20)
        private int storedWidth(int v) {
            return FlexBuffers.isTypeInline(this.type) ? Math.max(this.minBitWidth, v) : this.minBitWidth;
        }

        static Value uInt16(int v, int v1) {
            return new Value(v, 2, 1, ((long)v1));
        }

        static Value uInt32(int v, int v1) {
            return new Value(v, 2, 2, ((long)v1));
        }

        static Value uInt64(int v, long v1) {
            return new Value(v, 2, 3, v1);
        }

        static Value uInt8(int v, int v1) {
            return new Value(v, 2, 0, ((long)v1));
        }
    }

    static final boolean $assertionsDisabled = false;
    public static final int BUILDER_FLAG_NONE = 0;
    public static final int BUILDER_FLAG_SHARE_ALL = 7;
    public static final int BUILDER_FLAG_SHARE_KEYS = 1;
    public static final int BUILDER_FLAG_SHARE_KEYS_AND_STRINGS = 3;
    public static final int BUILDER_FLAG_SHARE_KEY_VECTORS = 4;
    public static final int BUILDER_FLAG_SHARE_STRINGS = 2;
    private static final int WIDTH_16 = 1;
    private static final int WIDTH_32 = 2;
    private static final int WIDTH_64 = 3;
    private static final int WIDTH_8;
    private final ReadWriteBuf bb;
    private boolean finished;
    private final int flags;
    private Comparator keyComparator;
    private final HashMap keyPool;
    private final ArrayList stack;
    private final HashMap stringPool;

    static {
    }

    public FlexBuffersBuilder() {
        this(0x100);
    }

    public FlexBuffersBuilder(int v) {
        this(new ArrayReadWriteBuf(v), 1);
    }

    public FlexBuffersBuilder(ReadWriteBuf readWriteBuf0, int v) {
        this.stack = new ArrayList();
        this.keyPool = new HashMap();
        this.stringPool = new HashMap();
        this.finished = false;
        this.keyComparator = new Comparator() {
            public int compare(Value flexBuffersBuilder$Value0, Value flexBuffersBuilder$Value1) {
                int v3;
                int v2;
                int v = flexBuffersBuilder$Value0.key;
                int v1 = flexBuffersBuilder$Value1.key;
                do {
                    v2 = FlexBuffersBuilder.this.bb.get(v);
                    v3 = FlexBuffersBuilder.this.bb.get(v1);
                    if(v2 == 0) {
                        return -v3;
                    }
                    ++v;
                    ++v1;
                }
                while(v2 == v3);
                return v2 - v3;
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((Value)object0), ((Value)object1));
            }
        };
        this.bb = readWriteBuf0;
        this.flags = v;
    }

    public FlexBuffersBuilder(ByteBuffer byteBuffer0) {
        this(byteBuffer0, 1);
    }

    @Deprecated
    public FlexBuffersBuilder(ByteBuffer byteBuffer0, int v) {
        this(new ArrayReadWriteBuf(byteBuffer0.array()), v);
    }

    private int align(int v) {
        for(int v1 = Value.access$100(this.bb.writePosition(), 1 << v); v1 != 0; --v1) {
            this.bb.put(0);
        }
        return 1 << v;
    }

    private Value createKeyVector(int v, int v1) {
        int v2 = Math.max(0, FlexBuffersBuilder.widthUInBits(v1));
        for(int v3 = v; v3 < this.stack.size(); ++v3) {
            v2 = Math.max(v2, Value.elemWidth(4, 0, ((long)((Value)this.stack.get(v3)).key), this.bb.writePosition(), v3 + 1));
        }
        int v4 = this.align(v2);
        this.writeInt(((long)v1), v4);
        int v5 = this.bb.writePosition();
        while(v < this.stack.size()) {
            Value flexBuffersBuilder$Value0 = (Value)this.stack.get(v);
            this.writeOffset(((long)((Value)this.stack.get(v)).key), v4);
            ++v;
        }
        return new Value(-1, 14, v2, ((long)v5));
    }

    private Value createVector(int v, int v1, int v2, boolean z, boolean z1, Value flexBuffersBuilder$Value0) {
        int v4;
        int v3 = Math.max(0, FlexBuffersBuilder.widthUInBits(v2));
        if(flexBuffersBuilder$Value0 == null) {
            v4 = 1;
        }
        else {
            v3 = Math.max(v3, flexBuffersBuilder$Value0.elemWidth(this.bb.writePosition(), 0));
            v4 = 3;
        }
        int v5 = 4;
        int v6 = v3;
        for(int v7 = v1; v7 < this.stack.size(); ++v7) {
            v6 = Math.max(v6, ((Value)this.stack.get(v7)).elemWidth(this.bb.writePosition(), v7 + v4));
            if(z && v7 == v1) {
                v5 = ((Value)this.stack.get(v7)).type;
                if(!FlexBuffers.isTypedVectorElementType(v5)) {
                    throw new FlexBufferException("TypedVector does not support this element type");
                }
            }
        }
        int v8 = v1;
        int v9 = this.align(v6);
        if(flexBuffersBuilder$Value0 != null) {
            this.writeOffset(flexBuffersBuilder$Value0.iValue, v9);
            this.writeInt(1L << flexBuffersBuilder$Value0.minBitWidth, v9);
        }
        if(!z1) {
            this.writeInt(((long)v2), v9);
        }
        int v10 = this.bb.writePosition();
        for(int v11 = v8; v11 < this.stack.size(); ++v11) {
            this.writeAny(((Value)this.stack.get(v11)), v9);
        }
        if(!z) {
            while(v8 < this.stack.size()) {
                int v12 = ((Value)this.stack.get(v8)).storedPackedType(v6);
                this.bb.put(((byte)v12));
                ++v8;
            }
        }
        if(flexBuffersBuilder$Value0 != null) {
            return new Value(v, 9, v6, ((long)v10));
        }
        if(z) {
            if(!z1) {
                v2 = 0;
            }
            return new Value(v, FlexBuffers.toTypedVector(v5, v2), v6, ((long)v10));
        }
        return new Value(v, 10, v6, ((long)v10));
    }

    public int endMap(String s, int v) {
        int v1 = this.putKey(s);
        Collections.sort(this.stack.subList(v, this.stack.size()), this.keyComparator);
        Value flexBuffersBuilder$Value0 = this.createKeyVector(v, this.stack.size() - v);
        Value flexBuffersBuilder$Value1 = this.createVector(v1, v, this.stack.size() - v, false, false, flexBuffersBuilder$Value0);
        while(this.stack.size() > v) {
            this.stack.remove(this.stack.size() - 1);
        }
        this.stack.add(flexBuffersBuilder$Value1);
        return (int)flexBuffersBuilder$Value1.iValue;
    }

    public int endVector(String s, int v, boolean z, boolean z1) {
        Value flexBuffersBuilder$Value0 = this.createVector(this.putKey(s), v, this.stack.size() - v, z, z1, null);
        while(this.stack.size() > v) {
            this.stack.remove(this.stack.size() - 1);
        }
        this.stack.add(flexBuffersBuilder$Value0);
        return (int)flexBuffersBuilder$Value0.iValue;
    }

    public ByteBuffer finish() {
        int v = this.align(((Value)this.stack.get(0)).elemWidth(this.bb.writePosition(), 0));
        this.writeAny(((Value)this.stack.get(0)), v);
        int v1 = ((Value)this.stack.get(0)).storedPackedType();
        this.bb.put(((byte)v1));
        this.bb.put(((byte)v));
        this.finished = true;
        return ByteBuffer.wrap(this.bb.data(), 0, this.bb.writePosition());
    }

    public ReadWriteBuf getBuffer() {
        return this.bb;
    }

    public int putBlob(String s, byte[] arr_b) {
        Value flexBuffersBuilder$Value0 = this.writeBlob(this.putKey(s), arr_b, 25, false);
        this.stack.add(flexBuffersBuilder$Value0);
        return (int)flexBuffersBuilder$Value0.iValue;
    }

    public int putBlob(byte[] arr_b) {
        return this.putBlob(null, arr_b);
    }

    public void putBoolean(String s, boolean z) {
        Value flexBuffersBuilder$Value0 = Value.bool(this.putKey(s), z);
        this.stack.add(flexBuffersBuilder$Value0);
    }

    public void putBoolean(boolean z) {
        this.putBoolean(null, z);
    }

    public void putFloat(double f) {
        this.putFloat(null, f);
    }

    public void putFloat(float f) {
        this.putFloat(null, f);
    }

    public void putFloat(String s, double f) {
        Value flexBuffersBuilder$Value0 = Value.float64(this.putKey(s), f);
        this.stack.add(flexBuffersBuilder$Value0);
    }

    public void putFloat(String s, float f) {
        Value flexBuffersBuilder$Value0 = Value.float32(this.putKey(s), f);
        this.stack.add(flexBuffersBuilder$Value0);
    }

    public void putInt(int v) {
        this.putInt(null, v);
    }

    public void putInt(long v) {
        this.putInt(null, v);
    }

    public void putInt(String s, int v) {
        this.putInt(s, ((long)v));
    }

    public void putInt(String s, long v) {
        int v1 = this.putKey(s);
        if(0xFFFFFFFFFFFFFF80L <= v && v <= 0x7FL) {
            Value flexBuffersBuilder$Value0 = Value.int8(v1, ((int)v));
            this.stack.add(flexBuffersBuilder$Value0);
            return;
        }
        if(0xFFFFFFFFFFFF8000L <= v && v <= 0x7FFFL) {
            Value flexBuffersBuilder$Value1 = Value.int16(v1, ((int)v));
            this.stack.add(flexBuffersBuilder$Value1);
            return;
        }
        if(0xFFFFFFFF80000000L <= v && v <= 0x7FFFFFFFL) {
            Value flexBuffersBuilder$Value2 = Value.int32(v1, ((int)v));
            this.stack.add(flexBuffersBuilder$Value2);
            return;
        }
        Value flexBuffersBuilder$Value3 = Value.int64(v1, v);
        this.stack.add(flexBuffersBuilder$Value3);
    }

    private int putKey(String s) {
        if(s == null) {
            return -1;
        }
        int v = this.bb.writePosition();
        if((this.flags & 1) != 0) {
            Integer integer0 = (Integer)this.keyPool.get(s);
            if(integer0 == null) {
                byte[] arr_b = s.getBytes(StandardCharsets.UTF_8);
                this.bb.put(arr_b, 0, arr_b.length);
                this.bb.put(0);
                this.keyPool.put(s, v);
                return v;
            }
            return (int)integer0;
        }
        byte[] arr_b1 = s.getBytes(StandardCharsets.UTF_8);
        this.bb.put(arr_b1, 0, arr_b1.length);
        this.bb.put(0);
        this.keyPool.put(s, v);
        return v;
    }

    public int putString(String s) {
        return this.putString(null, s);
    }

    public int putString(String s, String s1) {
        int v = this.putKey(s);
        if((this.flags & 2) != 0) {
            Integer integer0 = (Integer)this.stringPool.get(s1);
            if(integer0 == null) {
                Value flexBuffersBuilder$Value0 = this.writeString(v, s1);
                this.stringPool.put(s1, ((int)flexBuffersBuilder$Value0.iValue));
                this.stack.add(flexBuffersBuilder$Value0);
                return (int)flexBuffersBuilder$Value0.iValue;
            }
            Value flexBuffersBuilder$Value1 = Value.blob(v, ((int)integer0), 5, FlexBuffersBuilder.widthUInBits(s1.length()));
            this.stack.add(flexBuffersBuilder$Value1);
            return (int)integer0;
        }
        Value flexBuffersBuilder$Value2 = this.writeString(v, s1);
        this.stack.add(flexBuffersBuilder$Value2);
        return (int)flexBuffersBuilder$Value2.iValue;
    }

    private void putUInt(String s, long v) {
        Value flexBuffersBuilder$Value0;
        int v1 = this.putKey(s);
        int v2 = FlexBuffersBuilder.widthUInBits(v);
        if(v2 == 0) {
            flexBuffersBuilder$Value0 = Value.uInt8(v1, ((int)v));
        }
        else {
            switch(v2) {
                case 1: {
                    flexBuffersBuilder$Value0 = Value.uInt16(v1, ((int)v));
                    break;
                }
                case 2: {
                    flexBuffersBuilder$Value0 = Value.uInt32(v1, ((int)v));
                    break;
                }
                default: {
                    flexBuffersBuilder$Value0 = Value.uInt64(v1, v);
                }
            }
        }
        this.stack.add(flexBuffersBuilder$Value0);
    }

    public void putUInt(int v) {
        this.putUInt(null, ((long)v));
    }

    public void putUInt(long v) {
        this.putUInt(null, v);
    }

    private void putUInt64(String s, long v) {
        Value flexBuffersBuilder$Value0 = Value.uInt64(this.putKey(s), v);
        this.stack.add(flexBuffersBuilder$Value0);
    }

    public void putUInt64(BigInteger bigInteger0) {
        this.putUInt64(null, bigInteger0.longValue());
    }

    public int startMap() {
        return this.stack.size();
    }

    public int startVector() {
        return this.stack.size();
    }

    static int widthUInBits(long v) {
        if(v <= 0xFFL) {
            return 0;
        }
        if(v <= 0xFFFFL) {
            return 1;
        }
        return v > 0xFFFFFFFFL ? 3 : 2;
    }

    private void writeAny(Value flexBuffersBuilder$Value0, int v) {
        switch(flexBuffersBuilder$Value0.type) {
            case 3: {
                this.writeDouble(flexBuffersBuilder$Value0.dValue, v);
                return;
            }
            case 0: 
            case 1: 
            case 2: 
            case 26: {
                this.writeInt(flexBuffersBuilder$Value0.iValue, v);
                return;
            }
            default: {
                this.writeOffset(flexBuffersBuilder$Value0.iValue, v);
            }
        }
    }

    private Value writeBlob(int v, byte[] arr_b, int v1, boolean z) {
        int v2 = FlexBuffersBuilder.widthUInBits(arr_b.length);
        int v3 = this.align(v2);
        this.writeInt(((long)arr_b.length), v3);
        int v4 = this.bb.writePosition();
        this.bb.put(arr_b, 0, arr_b.length);
        if(z) {
            this.bb.put(0);
        }
        return Value.blob(v, v4, v1, v2);
    }

    private void writeDouble(double f, int v) {
        switch(v) {
            case 4: {
                this.bb.putFloat(((float)f));
                return;
            }
            case 8: {
                this.bb.putDouble(f);
            }
        }
    }

    private void writeInt(long v, int v1) {
        switch(v1) {
            case 1: {
                this.bb.put(((byte)(((int)v))));
                return;
            }
            case 2: {
                this.bb.putShort(((short)(((int)v))));
                return;
            }
            case 4: {
                this.bb.putInt(((int)v));
                return;
            }
            case 8: {
                this.bb.putLong(v);
            }
        }
    }

    private void writeOffset(long v, int v1) {
        this.writeInt(((long)(((int)(((long)this.bb.writePosition()) - v)))), v1);
    }

    private Value writeString(int v, String s) {
        return this.writeBlob(v, s.getBytes(StandardCharsets.UTF_8), 5, true);
    }
}

