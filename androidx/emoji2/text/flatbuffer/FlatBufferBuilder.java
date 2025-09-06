package androidx.emoji2.text.flatbuffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class FlatBufferBuilder {
    static class ByteBufferBackedInputStream extends InputStream {
        ByteBuffer buf;

        public ByteBufferBackedInputStream(ByteBuffer byteBuffer0) {
            this.buf = byteBuffer0;
        }

        @Override
        public int read() throws IOException {
            try {
                return this.buf.get() & 0xFF;
            }
            catch(BufferUnderflowException unused_ex) {
                return -1;
            }
        }
    }

    public static abstract class ByteBufferFactory {
        public abstract ByteBuffer newByteBuffer(int arg1);

        public void releaseByteBuffer(ByteBuffer byteBuffer0) {
        }
    }

    public static final class HeapByteBufferFactory extends ByteBufferFactory {
        public static final HeapByteBufferFactory INSTANCE;

        static {
            HeapByteBufferFactory.INSTANCE = new HeapByteBufferFactory();
        }

        @Override  // androidx.emoji2.text.flatbuffer.FlatBufferBuilder$ByteBufferFactory
        public ByteBuffer newByteBuffer(int v) {
            return ByteBuffer.allocate(v).order(ByteOrder.LITTLE_ENDIAN);
        }
    }

    static final boolean $assertionsDisabled;
    ByteBuffer bb;
    ByteBufferFactory bb_factory;
    boolean finished;
    boolean force_defaults;
    int minalign;
    boolean nested;
    int num_vtables;
    int object_start;
    int space;
    final Utf8 utf8;
    int vector_num_elems;
    int[] vtable;
    int vtable_in_use;
    int[] vtables;

    static {
    }

    public FlatBufferBuilder() {
        this(0x400);
    }

    public FlatBufferBuilder(int v) {
        Utf8 utf80 = Utf8.getDefault();
        this(v, HeapByteBufferFactory.INSTANCE, null, utf80);
    }

    public FlatBufferBuilder(int v, ByteBufferFactory flatBufferBuilder$ByteBufferFactory0) {
        this(v, flatBufferBuilder$ByteBufferFactory0, null, Utf8.getDefault());
    }

    public FlatBufferBuilder(int v, ByteBufferFactory flatBufferBuilder$ByteBufferFactory0, ByteBuffer byteBuffer0, Utf8 utf80) {
        this.minalign = 1;
        this.vtable = null;
        this.vtable_in_use = 0;
        this.nested = false;
        this.finished = false;
        this.vtables = new int[16];
        this.num_vtables = 0;
        this.vector_num_elems = 0;
        this.force_defaults = false;
        if(v <= 0) {
            v = 1;
        }
        this.bb_factory = flatBufferBuilder$ByteBufferFactory0;
        if(byteBuffer0 == null) {
            this.bb = flatBufferBuilder$ByteBufferFactory0.newByteBuffer(v);
        }
        else {
            this.bb = byteBuffer0;
            byteBuffer0.clear();
            this.bb.order(ByteOrder.LITTLE_ENDIAN);
        }
        this.utf8 = utf80;
        this.space = this.bb.capacity();
    }

    public FlatBufferBuilder(ByteBuffer byteBuffer0) {
        this(byteBuffer0, new HeapByteBufferFactory());
    }

    public FlatBufferBuilder(ByteBuffer byteBuffer0, ByteBufferFactory flatBufferBuilder$ByteBufferFactory0) {
        this(byteBuffer0.capacity(), flatBufferBuilder$ByteBufferFactory0, byteBuffer0, Utf8.getDefault());
    }

    public void Nested(int v) {
        if(v != this.offset()) {
            throw new AssertionError("FlatBuffers: struct must be serialized inline.");
        }
    }

    public void addBoolean(int v, boolean z, boolean z1) {
        if(!this.force_defaults && z == z1) {
            return;
        }
        this.addBoolean(z);
        this.slot(v);
    }

    public void addBoolean(boolean z) {
        this.prep(1, 0);
        this.putBoolean(z);
    }

    public void addByte(byte b) {
        this.prep(1, 0);
        this.putByte(b);
    }

    public void addByte(int v, byte b, int v1) {
        if(!this.force_defaults && b == v1) {
            return;
        }
        this.addByte(b);
        this.slot(v);
    }

    public void addDouble(double f) {
        this.prep(8, 0);
        this.putDouble(f);
    }

    public void addDouble(int v, double f, double f1) {
        if(!this.force_defaults && f == f1) {
            return;
        }
        this.addDouble(f);
        this.slot(v);
    }

    public void addFloat(float f) {
        this.prep(4, 0);
        this.putFloat(f);
    }

    public void addFloat(int v, float f, double f1) {
        if(!this.force_defaults && ((double)f) == f1) {
            return;
        }
        this.addFloat(f);
        this.slot(v);
    }

    public void addInt(int v) {
        this.prep(4, 0);
        this.putInt(v);
    }

    public void addInt(int v, int v1, int v2) {
        if(!this.force_defaults && v1 == v2) {
            return;
        }
        this.addInt(v1);
        this.slot(v);
    }

    public void addLong(int v, long v1, long v2) {
        if(!this.force_defaults && v1 == v2) {
            return;
        }
        this.addLong(v1);
        this.slot(v);
    }

    public void addLong(long v) {
        this.prep(8, 0);
        this.putLong(v);
    }

    public void addOffset(int v) {
        this.prep(4, 0);
        this.putInt(this.offset() - v + 4);
    }

    public void addOffset(int v, int v1, int v2) {
        if(!this.force_defaults && v1 == v2) {
            return;
        }
        this.addOffset(v1);
        this.slot(v);
    }

    public void addShort(int v, short v1, int v2) {
        if(!this.force_defaults && v1 == v2) {
            return;
        }
        this.addShort(v1);
        this.slot(v);
    }

    public void addShort(short v) {
        this.prep(2, 0);
        this.putShort(v);
    }

    public void addStruct(int v, int v1, int v2) {
        if(v1 != v2) {
            this.Nested(v1);
            this.slot(v);
        }
    }

    public void clear() {
        this.space = this.bb.capacity();
        this.bb.clear();
        this.minalign = 1;
        int v;
        while((v = this.vtable_in_use) > 0) {
            this.vtable_in_use = v - 1;
            this.vtable[v - 1] = 0;
        }
        this.vtable_in_use = 0;
        this.nested = false;
        this.finished = false;
        this.object_start = 0;
        this.num_vtables = 0;
        this.vector_num_elems = 0;
    }

    public int createByteVector(ByteBuffer byteBuffer0) {
        int v = byteBuffer0.remaining();
        this.startVector(1, v, 1);
        int v1 = this.space - v;
        this.space = v1;
        this.bb.position(v1);
        this.bb.put(byteBuffer0);
        return this.endVector();
    }

    public int createByteVector(byte[] arr_b) {
        this.startVector(1, arr_b.length, 1);
        int v = this.space - arr_b.length;
        this.space = v;
        this.bb.position(v);
        this.bb.put(arr_b);
        return this.endVector();
    }

    public int createByteVector(byte[] arr_b, int v, int v1) {
        this.startVector(1, v1, 1);
        int v2 = this.space - v1;
        this.space = v2;
        this.bb.position(v2);
        this.bb.put(arr_b, v, v1);
        return this.endVector();
    }

    public int createSortedVectorOfTables(Table table0, int[] arr_v) {
        table0.sortTables(arr_v, this.bb);
        return this.createVectorOfTables(arr_v);
    }

    public int createString(CharSequence charSequence0) {
        int v = this.utf8.encodedLength(charSequence0);
        this.addByte(0);
        this.startVector(1, v, 1);
        int v1 = this.space - v;
        this.space = v1;
        this.bb.position(v1);
        this.utf8.encodeUtf8(charSequence0, this.bb);
        return this.endVector();
    }

    public int createString(ByteBuffer byteBuffer0) {
        int v = byteBuffer0.remaining();
        this.addByte(0);
        this.startVector(1, v, 1);
        int v1 = this.space - v;
        this.space = v1;
        this.bb.position(v1);
        this.bb.put(byteBuffer0);
        return this.endVector();
    }

    public ByteBuffer createUnintializedVector(int v, int v1, int v2) {
        int v3 = v * v1;
        this.startVector(v, v1, v2);
        int v4 = this.space - v3;
        this.space = v4;
        this.bb.position(v4);
        ByteBuffer byteBuffer0 = this.bb.slice().order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer0.limit(v3);
        return byteBuffer0;
    }

    public int createVectorOfTables(int[] arr_v) {
        this.notNested();
        this.startVector(4, arr_v.length, 4);
        for(int v = arr_v.length - 1; v >= 0; --v) {
            this.addOffset(arr_v[v]);
        }
        return this.endVector();
    }

    public ByteBuffer dataBuffer() {
        this.finished();
        return this.bb;
    }

    @Deprecated
    private int dataStart() {
        this.finished();
        return this.space;
    }

    public int endTable() {
        int v5;
        if(this.vtable == null || !this.nested) {
            throw new AssertionError("FlatBuffers: endTable called without startTable");
        }
        this.addInt(0);
        int v = this.offset();
        int v1;
        for(v1 = this.vtable_in_use - 1; v1 >= 0 && this.vtable[v1] == 0; --v1) {
        }
        for(int v2 = v1; v2 >= 0; --v2) {
            int v3 = this.vtable[v2];
            this.addShort(((short)(v3 == 0 ? 0 : v - v3)));
        }
        this.addShort(((short)(v - this.object_start)));
        this.addShort(((short)((v1 + 3) * 2)));
    alab1:
        for(int v4 = 0; true; ++v4) {
            v5 = 0;
            if(v4 >= this.num_vtables) {
                break;
            }
            int v6 = this.bb.capacity() - this.vtables[v4];
            int v7 = this.space;
            int v8 = this.bb.getShort(v6);
            if(v8 == this.bb.getShort(v7)) {
                int v9 = 2;
                while(v9 < v8) {
                    if(this.bb.getShort(v6 + v9) != this.bb.getShort(v7 + v9)) {
                        continue alab1;
                    }
                    v9 += 2;
                }
                v5 = this.vtables[v4];
                break;
            }
        }
        if(v5 == 0) {
            int v11 = this.num_vtables;
            int[] arr_v = this.vtables;
            if(v11 == arr_v.length) {
                this.vtables = Arrays.copyOf(arr_v, v11 * 2);
            }
            int v12 = this.num_vtables;
            this.num_vtables = v12 + 1;
            this.vtables[v12] = this.offset();
            this.bb.putInt(this.bb.capacity() - v, this.offset() - v);
        }
        else {
            int v10 = this.bb.capacity() - v;
            this.space = v10;
            this.bb.putInt(v10, v5 - v);
        }
        this.nested = false;
        return v;
    }

    public int endVector() {
        if(!this.nested) {
            throw new AssertionError("FlatBuffers: endVector called without startVector");
        }
        this.nested = false;
        this.putInt(this.vector_num_elems);
        return this.offset();
    }

    public void finish(int v) {
        this.finish(v, false);
    }

    public void finish(int v, String s) {
        this.finish(v, s, false);
    }

    protected void finish(int v, String s, boolean z) {
        this.prep(this.minalign, (z ? 4 : 0) + 8);
        if(s.length() != 4) {
            throw new AssertionError("FlatBuffers: file identifier must be length 4");
        }
        for(int v1 = 3; v1 >= 0; --v1) {
            this.addByte(((byte)s.charAt(v1)));
        }
        this.finish(v, z);
    }

    protected void finish(int v, boolean z) {
        this.prep(this.minalign, (z ? 4 : 0) + 4);
        this.addOffset(v);
        if(z) {
            this.addInt(this.bb.capacity() - this.space);
        }
        this.bb.position(this.space);
        this.finished = true;
    }

    public void finishSizePrefixed(int v) {
        this.finish(v, true);
    }

    public void finishSizePrefixed(int v, String s) {
        this.finish(v, s, true);
    }

    public void finished() {
        if(!this.finished) {
            throw new AssertionError("FlatBuffers: you can only access the serialized buffer after it has been finished by FlatBufferBuilder.finish().");
        }
    }

    public FlatBufferBuilder forceDefaults(boolean z) {
        this.force_defaults = z;
        return this;
    }

    static ByteBuffer growByteBuffer(ByteBuffer byteBuffer0, ByteBufferFactory flatBufferBuilder$ByteBufferFactory0) {
        int v = byteBuffer0.capacity();
        if((0xC0000000 & v) != 0) {
            throw new AssertionError("FlatBuffers: cannot grow buffer beyond 2 gigabytes.");
        }
        byteBuffer0.position(0);
        ByteBuffer byteBuffer1 = flatBufferBuilder$ByteBufferFactory0.newByteBuffer((v == 0 ? 1 : v << 1));
        byteBuffer1.position(byteBuffer1.clear().capacity() - v);
        byteBuffer1.put(byteBuffer0);
        return byteBuffer1;
    }

    public FlatBufferBuilder init(ByteBuffer byteBuffer0, ByteBufferFactory flatBufferBuilder$ByteBufferFactory0) {
        this.bb_factory = flatBufferBuilder$ByteBufferFactory0;
        this.bb = byteBuffer0;
        byteBuffer0.clear();
        this.bb.order(ByteOrder.LITTLE_ENDIAN);
        this.minalign = 1;
        this.space = this.bb.capacity();
        this.vtable_in_use = 0;
        this.nested = false;
        this.finished = false;
        this.object_start = 0;
        this.num_vtables = 0;
        this.vector_num_elems = 0;
        return this;
    }

    public static boolean isFieldPresent(Table table0, int v) {
        return table0.__offset(v) != 0;
    }

    public void notNested() {
        if(this.nested) {
            throw new AssertionError("FlatBuffers: object serialization must not be nested.");
        }
    }

    public int offset() {
        return this.bb.capacity() - this.space;
    }

    public void pad(int v) {
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = this.space - 1;
            this.space = v2;
            this.bb.put(v2, 0);
        }
    }

    public void prep(int v, int v1) {
        if(v > this.minalign) {
            this.minalign = v;
        }
        int v2 = -(this.bb.capacity() - this.space + v1) & v - 1;
        while(this.space < v2 + v + v1) {
            int v3 = this.bb.capacity();
            this.bb = FlatBufferBuilder.growByteBuffer(this.bb, this.bb_factory);
            this.space += this.bb.capacity() - v3;
        }
        this.pad(v2);
    }

    public void putBoolean(boolean z) {
        int v = this.space - 1;
        this.space = v;
        this.bb.put(v, ((byte)z));
    }

    public void putByte(byte b) {
        int v = this.space - 1;
        this.space = v;
        this.bb.put(v, b);
    }

    public void putDouble(double f) {
        int v = this.space - 8;
        this.space = v;
        this.bb.putDouble(v, f);
    }

    public void putFloat(float f) {
        int v = this.space - 4;
        this.space = v;
        this.bb.putFloat(v, f);
    }

    public void putInt(int v) {
        int v1 = this.space - 4;
        this.space = v1;
        this.bb.putInt(v1, v);
    }

    public void putLong(long v) {
        int v1 = this.space - 8;
        this.space = v1;
        this.bb.putLong(v1, v);
    }

    public void putShort(short v) {
        int v1 = this.space - 2;
        this.space = v1;
        this.bb.putShort(v1, v);
    }

    public void required(int v, int v1) {
        int v2 = this.bb.capacity() - v;
        int v3 = this.bb.getInt(v2);
        if(this.bb.getShort(v2 - v3 + v1) == 0) {
            throw new AssertionError("FlatBuffers: field " + v1 + " must be set");
        }
    }

    public byte[] sizedByteArray() {
        return this.sizedByteArray(this.space, this.bb.capacity() - this.space);
    }

    public byte[] sizedByteArray(int v, int v1) {
        this.finished();
        byte[] arr_b = new byte[v1];
        this.bb.position(v);
        this.bb.get(arr_b);
        return arr_b;
    }

    public InputStream sizedInputStream() {
        this.finished();
        ByteBuffer byteBuffer0 = this.bb.duplicate();
        byteBuffer0.position(this.space);
        byteBuffer0.limit(this.bb.capacity());
        return new ByteBufferBackedInputStream(byteBuffer0);
    }

    public void slot(int v) {
        this.vtable[v] = this.offset();
    }

    public void startTable(int v) {
        this.notNested();
        if(this.vtable == null || this.vtable.length < v) {
            this.vtable = new int[v];
        }
        this.vtable_in_use = v;
        Arrays.fill(this.vtable, 0, v, 0);
        this.nested = true;
        this.object_start = this.offset();
    }

    public void startVector(int v, int v1, int v2) {
        this.notNested();
        this.vector_num_elems = v1;
        int v3 = v * v1;
        this.prep(4, v3);
        this.prep(v2, v3);
        this.nested = true;
    }
}

