package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Comparator;

public class Table {
    protected ByteBuffer bb;
    protected int bb_pos;
    Utf8 utf8;
    private int vtable_size;
    private int vtable_start;

    public Table() {
        this.utf8 = Utf8.getDefault();
    }

    protected static boolean __has_identifier(ByteBuffer byteBuffer0, String s) {
        if(s.length() != 4) {
            throw new AssertionError("FlatBuffers: file identifier must be length 4");
        }
        for(int v = 0; v < 4; ++v) {
            if(s.charAt(v) != ((char)byteBuffer0.get(byteBuffer0.position() + 4 + v))) {
                return false;
            }
        }
        return true;
    }

    protected static int __indirect(int v, ByteBuffer byteBuffer0) {
        return v + byteBuffer0.getInt(v);
    }

    protected int __indirect(int v) {
        return v + this.bb.getInt(v);
    }

    protected static int __offset(int v, int v1, ByteBuffer byteBuffer0) {
        int v2 = byteBuffer0.capacity() - v1;
        return byteBuffer0.getShort(v + v2 - byteBuffer0.getInt(v2)) + v2;
    }

    protected int __offset(int v) {
        return v >= this.vtable_size ? 0 : this.bb.getShort(this.vtable_start + v);
    }

    public void __reset() {
        this.__reset(0, null);
    }

    protected void __reset(int v, ByteBuffer byteBuffer0) {
        this.bb = byteBuffer0;
        if(byteBuffer0 != null) {
            this.bb_pos = v;
            int v1 = v - byteBuffer0.getInt(v);
            this.vtable_start = v1;
            this.vtable_size = this.bb.getShort(v1);
            return;
        }
        this.bb_pos = 0;
        this.vtable_start = 0;
        this.vtable_size = 0;
    }

    protected static String __string(int v, ByteBuffer byteBuffer0, Utf8 utf80) {
        int v1 = v + byteBuffer0.getInt(v);
        return utf80.decodeUtf8(byteBuffer0, v1 + 4, byteBuffer0.getInt(v1));
    }

    protected String __string(int v) {
        return Table.__string(v, this.bb, this.utf8);
    }

    protected static Table __union(Table table0, int v, ByteBuffer byteBuffer0) {
        table0.__reset(Table.__indirect(v, byteBuffer0), byteBuffer0);
        return table0;
    }

    protected Table __union(Table table0, int v) {
        return Table.__union(table0, v, this.bb);
    }

    protected int __vector(int v) {
        int v1 = v + this.bb_pos;
        return v1 + this.bb.getInt(v1) + 4;
    }

    protected ByteBuffer __vector_as_bytebuffer(int v, int v1) {
        int v2 = this.__offset(v);
        if(v2 == 0) {
            return null;
        }
        ByteBuffer byteBuffer0 = this.bb.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        int v3 = this.__vector(v2);
        byteBuffer0.position(v3);
        byteBuffer0.limit(v3 + this.__vector_len(v2) * v1);
        return byteBuffer0;
    }

    protected ByteBuffer __vector_in_bytebuffer(ByteBuffer byteBuffer0, int v, int v1) {
        int v2 = this.__offset(v);
        if(v2 == 0) {
            return null;
        }
        int v3 = this.__vector(v2);
        byteBuffer0.rewind();
        byteBuffer0.limit(this.__vector_len(v2) * v1 + v3);
        byteBuffer0.position(v3);
        return byteBuffer0;
    }

    protected int __vector_len(int v) {
        int v1 = v + this.bb_pos;
        int v2 = this.bb.getInt(v1);
        return this.bb.getInt(v1 + v2);
    }

    protected static int compareStrings(int v, int v1, ByteBuffer byteBuffer0) {
        int v2 = v + byteBuffer0.getInt(v);
        int v3 = v1 + byteBuffer0.getInt(v1);
        int v4 = byteBuffer0.getInt(v2);
        int v5 = byteBuffer0.getInt(v3);
        int v6 = Math.min(v4, v5);
        for(int v7 = 0; v7 < v6; ++v7) {
            int v8 = v7 + (v2 + 4);
            int v9 = v7 + (v3 + 4);
            if(byteBuffer0.get(v8) != byteBuffer0.get(v9)) {
                return byteBuffer0.get(v8) - byteBuffer0.get(v9);
            }
        }
        return v4 - v5;
    }

    protected static int compareStrings(int v, byte[] arr_b, ByteBuffer byteBuffer0) {
        int v1 = v + byteBuffer0.getInt(v);
        int v2 = byteBuffer0.getInt(v1);
        int v3 = Math.min(v2, arr_b.length);
        for(int v4 = 0; v4 < v3; ++v4) {
            int v5 = v4 + (v1 + 4);
            if(byteBuffer0.get(v5) != arr_b[v4]) {
                return byteBuffer0.get(v5) - arr_b[v4];
            }
        }
        return v2 - arr_b.length;
    }

    public ByteBuffer getByteBuffer() {
        return this.bb;
    }

    protected int keysCompare(Integer integer0, Integer integer1, ByteBuffer byteBuffer0) [...] // Inlined contents

    protected void sortTables(int[] arr_v, ByteBuffer byteBuffer0) {
        Integer[] arr_integer = new Integer[arr_v.length];
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            arr_integer[v1] = (int)arr_v[v1];
        }
        Arrays.sort(arr_integer, new Comparator() {
            public int compare(Integer integer0, Integer integer1) {
                return 0;
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((Integer)object0), ((Integer)object1));
            }
        });
        for(int v = 0; v < arr_v.length; ++v) {
            arr_v[v] = (int)arr_integer[v];
        }
    }
}

