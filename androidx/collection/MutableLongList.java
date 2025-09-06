package androidx.collection;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\u0018\u0010\b\u001A\u00020\t2\b\b\u0001\u0010\n\u001A\u00020\u00032\u0006\u0010\u000B\u001A\u00020\fJ\u000E\u0010\b\u001A\u00020\r2\u0006\u0010\u000B\u001A\u00020\fJ\u000E\u0010\u000E\u001A\u00020\r2\u0006\u0010\u000F\u001A\u00020\u0001J\u0018\u0010\u000E\u001A\u00020\r2\b\b\u0001\u0010\n\u001A\u00020\u00032\u0006\u0010\u000F\u001A\u00020\u0001J\u0018\u0010\u000E\u001A\u00020\r2\b\b\u0001\u0010\n\u001A\u00020\u00032\u0006\u0010\u000F\u001A\u00020\u0010J\u000E\u0010\u000E\u001A\u00020\r2\u0006\u0010\u000F\u001A\u00020\u0010J\u0006\u0010\u0011\u001A\u00020\tJ\u000E\u0010\u0012\u001A\u00020\t2\u0006\u0010\u0005\u001A\u00020\u0003J\u0011\u0010\u0013\u001A\u00020\t2\u0006\u0010\u000F\u001A\u00020\u0001H\u0086\u0002J\u0011\u0010\u0013\u001A\u00020\t2\u0006\u0010\u000B\u001A\u00020\fH\u0086\nJ\u0011\u0010\u0013\u001A\u00020\t2\u0006\u0010\u000F\u001A\u00020\u0010H\u0086\u0002J\u0011\u0010\u0014\u001A\u00020\t2\u0006\u0010\u000F\u001A\u00020\u0001H\u0086\u0002J\u0011\u0010\u0014\u001A\u00020\t2\u0006\u0010\u000B\u001A\u00020\fH\u0086\nJ\u0011\u0010\u0014\u001A\u00020\t2\u0006\u0010\u000F\u001A\u00020\u0010H\u0086\u0002J\u000E\u0010\u0015\u001A\u00020\r2\u0006\u0010\u000B\u001A\u00020\fJ\u000E\u0010\u0016\u001A\u00020\r2\u0006\u0010\u000F\u001A\u00020\u0001J\u000E\u0010\u0016\u001A\u00020\r2\u0006\u0010\u000F\u001A\u00020\u0010J\u0010\u0010\u0017\u001A\u00020\f2\b\b\u0001\u0010\n\u001A\u00020\u0003J\u001A\u0010\u0018\u001A\u00020\t2\b\b\u0001\u0010\u0019\u001A\u00020\u00032\b\b\u0001\u0010\u001A\u001A\u00020\u0003J\u000E\u0010\u001B\u001A\u00020\r2\u0006\u0010\u000F\u001A\u00020\u0001J\u000E\u0010\u001B\u001A\u00020\r2\u0006\u0010\u000F\u001A\u00020\u0010J\u001B\u0010\u001C\u001A\u00020\f2\b\b\u0001\u0010\n\u001A\u00020\u00032\u0006\u0010\u000B\u001A\u00020\fH\u0086\u0002J\u0006\u0010\u001D\u001A\u00020\tJ\u0006\u0010\u001E\u001A\u00020\tJ\u0010\u0010\u001F\u001A\u00020\t2\b\b\u0002\u0010 \u001A\u00020\u0003R\u0012\u0010\u0005\u001A\u00020\u00038\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007\u00A8\u0006!"}, d2 = {"Landroidx/collection/MutableLongList;", "Landroidx/collection/LongList;", "initialCapacity", "", "(I)V", "capacity", "getCapacity", "()I", "add", "", "index", "element", "", "", "addAll", "elements", "", "clear", "ensureCapacity", "minusAssign", "plusAssign", "remove", "removeAll", "removeAt", "removeRange", "start", "end", "retainAll", "set", "sort", "sortDescending", "trim", "minCapacity", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class MutableLongList extends LongList {
    public MutableLongList() {
        this(0, 1, null);
    }

    public MutableLongList(int v) {
        super(v, null);
    }

    public MutableLongList(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 16;
        }
        this(v);
    }

    public final void add(int v, long v1) {
        if(v < 0 || v > this._size) {
            throw new IndexOutOfBoundsException("Index " + v + " must be in 0.." + this._size);
        }
        this.ensureCapacity(this._size + 1);
        long[] arr_v = this.content;
        if(v != this._size) {
            ArraysKt.copyInto(arr_v, arr_v, v + 1, v, this._size);
        }
        arr_v[v] = v1;
        ++this._size;
    }

    public final boolean add(long v) {
        this.ensureCapacity(this._size + 1);
        this.content[this._size] = v;
        ++this._size;
        return true;
    }

    public final boolean addAll(int v, LongList longList0) {
        Intrinsics.checkNotNullParameter(longList0, "elements");
        if(v < 0 || v > this._size) {
            throw new IndexOutOfBoundsException("Index " + v + " must be in 0.." + this._size);
        }
        if(longList0.isEmpty()) {
            return false;
        }
        this.ensureCapacity(this._size + longList0._size);
        long[] arr_v = this.content;
        if(v != this._size) {
            ArraysKt.copyInto(arr_v, arr_v, longList0._size + v, v, this._size);
        }
        ArraysKt.copyInto(longList0.content, arr_v, v, 0, longList0._size);
        this._size += longList0._size;
        return true;
    }

    public final boolean addAll(int v, long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        if(v < 0 || v > this._size) {
            throw new IndexOutOfBoundsException("Index " + v + " must be in 0.." + this._size);
        }
        if(arr_v.length == 0) {
            return false;
        }
        this.ensureCapacity(this._size + arr_v.length);
        long[] arr_v1 = this.content;
        if(v != this._size) {
            ArraysKt.copyInto(arr_v1, arr_v1, arr_v.length + v, v, this._size);
        }
        ArraysKt.copyInto$default(arr_v, arr_v1, v, 0, 0, 12, null);
        this._size += arr_v.length;
        return true;
    }

    public final boolean addAll(LongList longList0) {
        Intrinsics.checkNotNullParameter(longList0, "elements");
        return this.addAll(this._size, longList0);
    }

    public final boolean addAll(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        return this.addAll(this._size, arr_v);
    }

    public final void clear() {
        this._size = 0;
    }

    public final void ensureCapacity(int v) {
        long[] arr_v = this.content;
        if(arr_v.length < v) {
            long[] arr_v1 = Arrays.copyOf(arr_v, Math.max(v, arr_v.length * 3 / 2));
            Intrinsics.checkNotNullExpressionValue(arr_v1, "copyOf(this, newSize)");
            this.content = arr_v1;
        }
    }

    public final int getCapacity() {
        return this.content.length;
    }

    public final void minusAssign(long v) {
        this.remove(v);
    }

    public final void minusAssign(LongList longList0) {
        Intrinsics.checkNotNullParameter(longList0, "elements");
        long[] arr_v = longList0.content;
        int v = longList0._size;
        for(int v1 = 0; v1 < v; ++v1) {
            this.remove(arr_v[v1]);
        }
    }

    public final void minusAssign(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        for(int v = 0; v < arr_v.length; ++v) {
            this.remove(arr_v[v]);
        }
    }

    public final void plusAssign(long v) {
        this.add(v);
    }

    public final void plusAssign(LongList longList0) {
        Intrinsics.checkNotNullParameter(longList0, "elements");
        this.addAll(this._size, longList0);
    }

    public final void plusAssign(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        this.addAll(this._size, arr_v);
    }

    public final boolean remove(long v) {
        int v1 = this.indexOf(v);
        if(v1 >= 0) {
            this.removeAt(v1);
            return true;
        }
        return false;
    }

    public final boolean removeAll(LongList longList0) {
        Intrinsics.checkNotNullParameter(longList0, "elements");
        int v = this._size;
        int v1 = longList0._size - 1;
        if(v1 >= 0) {
            for(int v2 = 0; true; ++v2) {
                this.remove(longList0.get(v2));
                if(v2 == v1) {
                    break;
                }
            }
        }
        return v != this._size;
    }

    public final boolean removeAll(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        int v = this._size;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            this.remove(arr_v[v1]);
        }
        return v != this._size;
    }

    public final long removeAt(int v) {
        if(v < 0 || v >= this._size) {
            throw new IndexOutOfBoundsException("Index " + v + " must be in 0.." + (this._size - 1));
        }
        long[] arr_v = this.content;
        long v1 = arr_v[v];
        if(v != this._size - 1) {
            ArraysKt.copyInto(arr_v, arr_v, v, v + 1, this._size);
        }
        --this._size;
        return v1;
    }

    public final void removeRange(int v, int v1) {
        if(v < 0 || v > this._size || v1 < 0 || v1 > this._size) {
            throw new IndexOutOfBoundsException("Start (" + v + ") and end (" + v1 + ") must be in 0.." + this._size);
        }
        if(v1 < v) {
            throw new IllegalArgumentException("Start (" + v + ") is more than end (" + v1 + ')');
        }
        if(v1 != v) {
            if(v1 < this._size) {
                ArraysKt.copyInto(this.content, this.content, v, v1, this._size);
            }
            this._size -= v1 - v;
        }
    }

    public final boolean retainAll(LongList longList0) {
        Intrinsics.checkNotNullParameter(longList0, "elements");
        int v = this._size;
        long[] arr_v = this.content;
        for(int v1 = this._size - 1; -1 < v1; --v1) {
            if(!longList0.contains(arr_v[v1])) {
                this.removeAt(v1);
            }
        }
        return v != this._size;
    }

    public final boolean retainAll(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        int v = this._size;
        long[] arr_v1 = this.content;
        for(int v1 = this._size - 1; true; --v1) {
            int v2 = -1;
            if(-1 >= v1) {
                break;
            }
            long v3 = arr_v1[v1];
            for(int v4 = 0; v4 < arr_v.length; ++v4) {
                if(arr_v[v4] == v3) {
                    v2 = v4;
                    break;
                }
            }
            if(v2 < 0) {
                this.removeAt(v1);
            }
        }
        return v != this._size;
    }

    public final long set(int v, long v1) {
        if(v < 0 || v >= this._size) {
            throw new IndexOutOfBoundsException("set index " + v + " must be between 0 .. " + (this._size - 1));
        }
        long[] arr_v = this.content;
        long v2 = arr_v[v];
        arr_v[v] = v1;
        return v2;
    }

    public final void sort() {
        ArraysKt.sort(this.content, 0, this._size);
    }

    public final void sortDescending() {
        ArraysKt.sortDescending(this.content, 0, this._size);
    }

    public final void trim(int v) {
        int v1 = Math.max(v, this._size);
        if(this.content.length > v1) {
            long[] arr_v = Arrays.copyOf(this.content, v1);
            Intrinsics.checkNotNullExpressionValue(arr_v, "copyOf(this, newSize)");
            this.content = arr_v;
        }
    }

    public static void trim$default(MutableLongList mutableLongList0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = mutableLongList0._size;
        }
        mutableLongList0.trim(v);
    }
}

