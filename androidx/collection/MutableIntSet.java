package androidx.collection;

import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000E\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\u0003J\u000E\u0010\t\u001A\u00020\u00072\u0006\u0010\n\u001A\u00020\u0001J\u000E\u0010\t\u001A\u00020\u00072\u0006\u0010\n\u001A\u00020\u000BJ\b\u0010\f\u001A\u00020\rH\u0002J\u0006\u0010\u000E\u001A\u00020\rJ\u0010\u0010\u000F\u001A\u00020\u00032\u0006\u0010\b\u001A\u00020\u0003H\u0002J\u0010\u0010\u0010\u001A\u00020\u00032\u0006\u0010\u0011\u001A\u00020\u0003H\u0002J\b\u0010\u0012\u001A\u00020\rH\u0002J\u0010\u0010\u0013\u001A\u00020\r2\u0006\u0010\u0014\u001A\u00020\u0003H\u0002J\u0010\u0010\u0015\u001A\u00020\r2\u0006\u0010\u0002\u001A\u00020\u0003H\u0002J\u0011\u0010\u0016\u001A\u00020\r2\u0006\u0010\n\u001A\u00020\u0001H\u0086\u0002J\u0011\u0010\u0016\u001A\u00020\r2\u0006\u0010\b\u001A\u00020\u0003H\u0086\u0002J\u0011\u0010\u0016\u001A\u00020\r2\u0006\u0010\n\u001A\u00020\u000BH\u0086\u0002J\u0011\u0010\u0017\u001A\u00020\r2\u0006\u0010\n\u001A\u00020\u0001H\u0086\u0002J\u0011\u0010\u0017\u001A\u00020\r2\u0006\u0010\b\u001A\u00020\u0003H\u0086\u0002J\u0011\u0010\u0017\u001A\u00020\r2\u0006\u0010\n\u001A\u00020\u000BH\u0086\u0002J\u000E\u0010\u0018\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\u0003J\u000E\u0010\u0019\u001A\u00020\u00072\u0006\u0010\n\u001A\u00020\u0001J\u000E\u0010\u0019\u001A\u00020\u00072\u0006\u0010\n\u001A\u00020\u000BJ\b\u0010\u001A\u001A\u00020\rH\u0002J\u0010\u0010\u001B\u001A\u00020\r2\u0006\u0010\u001C\u001A\u00020\u0003H\u0002J\u0010\u0010\u001D\u001A\u00020\r2\u0006\u0010\u001E\u001A\u00020\u0003H\u0002J\b\u0010\u001F\u001A\u00020\u0003H\u0007J\u0019\u0010 \u001A\u00020\r2\u0006\u0010\u001C\u001A\u00020\u00032\u0006\u0010!\u001A\u00020\"H\u0082\bR\u000E\u0010\u0005\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/collection/MutableIntSet;", "Landroidx/collection/IntSet;", "initialCapacity", "", "(I)V", "growthLimit", "add", "", "element", "addAll", "elements", "", "adjustStorage", "", "clear", "findAbsoluteInsertIndex", "findFirstAvailableSlot", "hash1", "initializeGrowth", "initializeMetadata", "capacity", "initializeStorage", "minusAssign", "plusAssign", "remove", "removeAll", "removeDeletedMarkers", "removeElementAt", "index", "resizeStorage", "newCapacity", "trim", "writeMetadata", "value", "", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class MutableIntSet extends IntSet {
    private int growthLimit;

    public MutableIntSet() {
        this(0, 1, null);
    }

    public MutableIntSet(int v) {
        super(null);
        if(v < 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.");
        }
        this.initializeStorage(ScatterMapKt.unloadedCapacity(v));
    }

    public MutableIntSet(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 6;
        }
        this(v);
    }

    public final boolean add(int v) {
        int v1 = this._size;
        this.elements[this.findAbsoluteInsertIndex(v)] = v;
        return this._size != v1;
    }

    public final boolean addAll(IntSet intSet0) {
        Intrinsics.checkNotNullParameter(intSet0, "elements");
        int v = this._size;
        this.plusAssign(intSet0);
        return v != this._size;
    }

    public final boolean addAll(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        int v = this._size;
        this.plusAssign(arr_v);
        return v != this._size;
    }

    private final void adjustStorage() {
        if(this._capacity > 8 && UByte..ExternalSyntheticBackport0.m(((long)this._size) * 0x20L, ((long)this._capacity) * 25L) <= 0) {
            this.removeDeletedMarkers();
            return;
        }
        this.resizeStorage(ScatterMapKt.nextCapacity(this._capacity));
    }

    public final void clear() {
        this._size = 0;
        if(this.metadata != ScatterMapKt.EmptyGroup) {
            ArraysKt.fill$default(this.metadata, 0x8080808080808080L, 0, 0, 6, null);
            int v = this._capacity >> 3;
            this.metadata[v] |= 0xFFL << ((this._capacity & 7) << 3);
        }
        this.initializeGrowth();
    }

    private final int findAbsoluteInsertIndex(int v) {
        int v1 = 0xCC9E2D51 * v ^ 0xCC9E2D51 * v << 16;
        int v2 = this._capacity;
        int v3 = v1 >>> 7 & v2;
        int v4 = 0;
        while(true) {
            int v5 = (v3 & 7) << 3;
            long v6 = this.metadata[(v3 >> 3) + 1] << 0x40 - v5 & -((long)v5) >> 0x3F | this.metadata[v3 >> 3] >>> v5;
            long v7 = v6 ^ ((long)(v1 & 0x7F)) * 0x101010101010101L;
            for(long v8 = ~v7 & v7 - 0x101010101010101L & 0x8080808080808080L; v8 != 0L; v8 &= v8 - 1L) {
                int v9 = v3 + (Long.numberOfTrailingZeros(v8) >> 3) & v2;
                if(this.elements[v9] == v) {
                    return v9;
                }
            }
            if((~v6 << 6 & v6 & 0x8080808080808080L) != 0L) {
                int v10 = this.findFirstAvailableSlot(v1 >>> 7);
                if(this.growthLimit == 0 && (this.metadata[v10 >> 3] >> ((v10 & 7) << 3) & 0xFFL) != 0xFEL) {
                    this.adjustStorage();
                    v10 = this.findFirstAvailableSlot(v1 >>> 7);
                }
                ++this._size;
                int v11 = (v10 & 7) << 3;
                this.growthLimit -= ((this.metadata[v10 >> 3] >> v11 & 0xFFL) == 0x80L ? 1 : 0);
                long[] arr_v = this.metadata;
                arr_v[v10 >> 3] = arr_v[v10 >> 3] & ~(0xFFL << v11) | ((long)(v1 & 0x7F)) << v11;
                int v12 = (v10 - 7 & this._capacity) + (this._capacity & 7);
                int v13 = (v12 & 7) << 3;
                arr_v[v12 >> 3] = ~(0xFFL << v13) & arr_v[v12 >> 3] | ((long)(v1 & 0x7F)) << v13;
                return v10;
            }
            v4 += 8;
            v3 = v3 + v4 & v2;
        }
    }

    private final int findFirstAvailableSlot(int v) {
        long v6;
        int v1 = this._capacity;
        int v2 = v & v1;
        int v3 = 0;
        while(true) {
            int v4 = (v2 & 7) << 3;
            long v5 = this.metadata[(v2 >> 3) + 1] << 0x40 - v4 & -((long)v4) >> 0x3F | this.metadata[v2 >> 3] >>> v4;
            v6 = v5 & ~v5 << 7 & 0x8080808080808080L;
            if(v6 != 0L) {
                break;
            }
            v3 += 8;
            v2 = v2 + v3 & v1;
        }
        return v2 + (Long.numberOfTrailingZeros(v6) >> 3) & v1;
    }

    private final void initializeGrowth() {
        this.growthLimit = ScatterMapKt.loadedCapacity(this.getCapacity()) - this._size;
    }

    private final void initializeMetadata(int v) {
        long[] arr_v;
        if(v == 0) {
            arr_v = ScatterMapKt.EmptyGroup;
        }
        else {
            long[] arr_v1 = new long[(v + 15 & -8) >> 3];
            ArraysKt.fill$default(arr_v1, 0x8080808080808080L, 0, 0, 6, null);
            arr_v = arr_v1;
        }
        this.metadata = arr_v;
        this.metadata[v >> 3] |= 0xFFL << ((v & 7) << 3);
        this.initializeGrowth();
    }

    private final void initializeStorage(int v) {
        int v1 = v <= 0 ? 0 : Math.max(7, ScatterMapKt.normalizeCapacity(v));
        this._capacity = v1;
        this.initializeMetadata(v1);
        this.elements = new int[v1];
    }

    public final void minusAssign(int v) {
        int v10;
        int v1 = 0xCC9E2D51 * v ^ 0xCC9E2D51 * v << 16;
        int v2 = this._capacity;
        int v3 = v1 >>> 7 & v2;
        int v4 = 0;
        while(true) {
            int v5 = (v3 & 7) << 3;
            long v6 = this.metadata[(v3 >> 3) + 1] << 0x40 - v5 & -((long)v5) >> 0x3F | this.metadata[v3 >> 3] >>> v5;
            long v7 = ((long)(v1 & 0x7F)) * 0x101010101010101L ^ v6;
            long v8 = ~v7 & v7 - 0x101010101010101L & 0x8080808080808080L;
            while(v8 != 0L) {
                int v9 = (Long.numberOfTrailingZeros(v8) >> 3) + v3 & v2;
                if(this.elements[v9] == v) {
                    v10 = v9;
                    goto label_17;
                }
                v8 &= v8 - 1L;
            }
            if((v6 & ~v6 << 6 & 0x8080808080808080L) == 0L) {
                goto label_20;
            }
            else {
                v10 = -1;
            }
        label_17:
            if(v10 >= 0) {
                this.removeElementAt(v10);
            }
            return;
        label_20:
            v4 += 8;
            v3 = v3 + v4 & v2;
        }
    }

    public final void minusAssign(IntSet intSet0) {
        Intrinsics.checkNotNullParameter(intSet0, "elements");
        int[] arr_v = intSet0.elements;
        long[] arr_v1 = intSet0.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v1[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            this.minusAssign(arr_v[(v1 << 3) + v4]);
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_17;
                    }
                    break;
                }
            label_17:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
    }

    public final void minusAssign(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        for(int v = 0; v < arr_v.length; ++v) {
            this.minusAssign(arr_v[v]);
        }
    }

    public final void plusAssign(int v) {
        this.elements[this.findAbsoluteInsertIndex(v)] = v;
    }

    public final void plusAssign(IntSet intSet0) {
        Intrinsics.checkNotNullParameter(intSet0, "elements");
        int[] arr_v = intSet0.elements;
        long[] arr_v1 = intSet0.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v1[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            this.plusAssign(arr_v[(v1 << 3) + v4]);
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_17;
                    }
                    break;
                }
            label_17:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
    }

    public final void plusAssign(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        for(int v = 0; v < arr_v.length; ++v) {
            this.plusAssign(arr_v[v]);
        }
    }

    public final boolean remove(int v) {
        int v10;
        int v1 = 0xCC9E2D51 * v ^ 0xCC9E2D51 * v << 16;
        int v2 = this._capacity;
        int v3 = v1 >>> 7 & v2;
        int v4 = 0;
        while(true) {
            int v5 = (v3 & 7) << 3;
            long v6 = this.metadata[(v3 >> 3) + 1] << 0x40 - v5 & -((long)v5) >> 0x3F | this.metadata[v3 >> 3] >>> v5;
            long v7 = ((long)(v1 & 0x7F)) * 0x101010101010101L ^ v6;
            long v8 = ~v7 & v7 - 0x101010101010101L & 0x8080808080808080L;
            while(v8 != 0L) {
                int v9 = (Long.numberOfTrailingZeros(v8) >> 3) + v3 & v2;
                if(this.elements[v9] == v) {
                    v10 = v9;
                    goto label_17;
                }
                v8 &= v8 - 1L;
            }
            if((v6 & ~v6 << 6 & 0x8080808080808080L) == 0L) {
                goto label_20;
            }
            else {
                v10 = -1;
            }
        label_17:
            if(v10 >= 0) {
                this.removeElementAt(v10);
            }
            return v10 >= 0;
        label_20:
            v4 += 8;
            v3 = v3 + v4 & v2;
        }
    }

    public final boolean removeAll(IntSet intSet0) {
        Intrinsics.checkNotNullParameter(intSet0, "elements");
        int v = this._size;
        this.minusAssign(intSet0);
        return v != this._size;
    }

    public final boolean removeAll(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        int v = this._size;
        this.minusAssign(arr_v);
        return v != this._size;
    }

    private final void removeDeletedMarkers() {
        long[] arr_v = this.metadata;
        int v = this._capacity;
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            int v3 = (v1 & 7) << 3;
            if((arr_v[v1 >> 3] >> v3 & 0xFFL) == 0xFEL) {
                long[] arr_v1 = this.metadata;
                arr_v1[v1 >> 3] = 0x80L << v3 | arr_v1[v1 >> 3] & ~(0xFFL << v3);
                int v4 = (v1 - 7 & this._capacity) + (this._capacity & 7);
                int v5 = (v4 & 7) << 3;
                arr_v1[v4 >> 3] = ~(0xFFL << v5) & arr_v1[v4 >> 3] | 0x80L << v5;
                ++v2;
            }
        }
        this.growthLimit += v2;
    }

    private final void removeElementAt(int v) {
        --this._size;
        long[] arr_v = this.metadata;
        int v1 = (v & 7) << 3;
        arr_v[v >> 3] = arr_v[v >> 3] & ~(0xFFL << v1) | 0xFEL << v1;
        int v2 = (v - 7 & this._capacity) + (this._capacity & 7);
        int v3 = (v2 & 7) << 3;
        arr_v[v2 >> 3] = arr_v[v2 >> 3] & ~(0xFFL << v3) | 0xFEL << v3;
    }

    private final void resizeStorage(int v) {
        long[] arr_v = this.metadata;
        int[] arr_v1 = this.elements;
        int v1 = this._capacity;
        this.initializeStorage(v);
        int[] arr_v2 = this.elements;
        for(int v2 = 0; v2 < v1; ++v2) {
            if((arr_v[v2 >> 3] >> ((v2 & 7) << 3) & 0xFFL) < 0x80L) {
                int v3 = arr_v1[v2];
                int v4 = 0xCC9E2D51 * v3 ^ 0xCC9E2D51 * v3 << 16;
                int v5 = this.findFirstAvailableSlot(v4 >>> 7);
                long[] arr_v3 = this.metadata;
                int v6 = (v5 & 7) << 3;
                arr_v3[v5 >> 3] = ~(0xFFL << v6) & arr_v3[v5 >> 3] | ((long)(v4 & 0x7F)) << v6;
                int v7 = (v5 - 7 & this._capacity) + (this._capacity & 7);
                int v8 = (v7 & 7) << 3;
                arr_v3[v7 >> 3] = ~(0xFFL << v8) & arr_v3[v7 >> 3] | ((long)(v4 & 0x7F)) << v8;
                arr_v2[v5] = v3;
            }
        }
    }

    public final int trim() {
        int v = this._capacity;
        int v1 = ScatterMapKt.normalizeCapacity(ScatterMapKt.unloadedCapacity(this._size));
        if(v1 < v) {
            this.resizeStorage(v1);
            return v - this._capacity;
        }
        return 0;
    }

    private final void writeMetadata(int v, long v1) {
        long[] arr_v = this.metadata;
        int v2 = (v & 7) << 3;
        arr_v[v >> 3] = arr_v[v >> 3] & ~(0xFFL << v2) | v1 << v2;
        int v3 = (v - 7 & this._capacity) + (this._capacity & 7);
        int v4 = (v3 & 7) << 3;
        arr_v[v3 >> 3] = v1 << v4 | arr_v[v3 >> 3] & ~(0xFFL << v4);
    }
}

