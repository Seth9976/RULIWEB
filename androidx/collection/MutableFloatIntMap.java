package androidx.collection;

import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\b\u0010\u0006\u001A\u00020\u0007H\u0002J\u0006\u0010\b\u001A\u00020\u0007J\u0010\u0010\t\u001A\u00020\u00032\u0006\u0010\n\u001A\u00020\u0003H\u0002J\u0010\u0010\u000B\u001A\u00020\u00032\u0006\u0010\f\u001A\u00020\rH\u0002J\"\u0010\u000E\u001A\u00020\u00032\u0006\u0010\f\u001A\u00020\r2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u00030\u0010H\u0086\b\u00F8\u0001\u0000J\b\u0010\u0011\u001A\u00020\u0007H\u0002J\u0010\u0010\u0012\u001A\u00020\u00072\u0006\u0010\u0013\u001A\u00020\u0003H\u0002J\u0010\u0010\u0014\u001A\u00020\u00072\u0006\u0010\u0002\u001A\u00020\u0003H\u0002J\u0011\u0010\u0015\u001A\u00020\u00072\u0006\u0010\u0016\u001A\u00020\u0017H\u0086\nJ\u0011\u0010\u0015\u001A\u00020\u00072\u0006\u0010\u0016\u001A\u00020\u0018H\u0086\nJ\u0011\u0010\u0015\u001A\u00020\u00072\u0006\u0010\f\u001A\u00020\rH\u0086\nJ\u0011\u0010\u0015\u001A\u00020\u00072\u0006\u0010\u0016\u001A\u00020\u0019H\u0086\nJ\u0011\u0010\u001A\u001A\u00020\u00072\u0006\u0010\u001B\u001A\u00020\u0001H\u0086\nJ\u0016\u0010\u001C\u001A\u00020\u00072\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u001D\u001A\u00020\u0003J\u001E\u0010\u001C\u001A\u00020\u00032\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u001D\u001A\u00020\u00032\u0006\u0010\u001E\u001A\u00020\u0003J\u000E\u0010\u001F\u001A\u00020\u00072\u0006\u0010\u001B\u001A\u00020\u0001J\u000E\u0010 \u001A\u00020\u00072\u0006\u0010\f\u001A\u00020\rJ\u0016\u0010 \u001A\u00020!2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u001D\u001A\u00020\u0003J\b\u0010\"\u001A\u00020\u0007H\u0002J&\u0010#\u001A\u00020\u00072\u0018\u0010$\u001A\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020!0%H\u0086\b\u00F8\u0001\u0000J\u0010\u0010&\u001A\u00020\u00072\u0006\u0010\'\u001A\u00020\u0003H\u0001J\u0010\u0010(\u001A\u00020\u00072\u0006\u0010)\u001A\u00020\u0003H\u0002J\u0019\u0010*\u001A\u00020\u00072\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u001D\u001A\u00020\u0003H\u0086\u0002J\u0006\u0010+\u001A\u00020\u0003J\u0019\u0010,\u001A\u00020\u00072\u0006\u0010\'\u001A\u00020\u00032\u0006\u0010\u001D\u001A\u00020-H\u0082\bR\u000E\u0010\u0005\u001A\u00020\u0003X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006."}, d2 = {"Landroidx/collection/MutableFloatIntMap;", "Landroidx/collection/FloatIntMap;", "initialCapacity", "", "(I)V", "growthLimit", "adjustStorage", "", "clear", "findFirstAvailableSlot", "hash1", "findInsertIndex", "key", "", "getOrPut", "defaultValue", "Lkotlin/Function0;", "initializeGrowth", "initializeMetadata", "capacity", "initializeStorage", "minusAssign", "keys", "Landroidx/collection/FloatList;", "Landroidx/collection/FloatSet;", "", "plusAssign", "from", "put", "value", "default", "putAll", "remove", "", "removeDeletedMarkers", "removeIf", "predicate", "Lkotlin/Function2;", "removeValueAt", "index", "resizeStorage", "newCapacity", "set", "trim", "writeMetadata", "", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class MutableFloatIntMap extends FloatIntMap {
    private int growthLimit;

    public MutableFloatIntMap() {
        this(0, 1, null);
    }

    public MutableFloatIntMap(int v) {
        super(null);
        if(v < 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.");
        }
        this.initializeStorage(ScatterMapKt.unloadedCapacity(v));
    }

    public MutableFloatIntMap(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 6;
        }
        this(v);
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

    private final int findInsertIndex(float f) {
        int v = Float.floatToIntBits(f);
        int v1 = v * 0xCC9E2D51 ^ v * 0xCC9E2D51 << 16;
        int v2 = this._capacity;
        int v3 = v1 >>> 7 & v2;
        int v4 = 0;
        while(true) {
            int v5 = (v3 & 7) << 3;
            long v6 = this.metadata[(v3 >> 3) + 1] << 0x40 - v5 & -((long)v5) >> 0x3F | this.metadata[v3 >> 3] >>> v5;
            long v7 = v6 ^ ((long)(v1 & 0x7F)) * 0x101010101010101L;
            for(long v8 = ~v7 & v7 - 0x101010101010101L & 0x8080808080808080L; v8 != 0L; v8 &= v8 - 1L) {
                int v9 = (Long.numberOfTrailingZeros(v8) >> 3) + v3 & v2;
                if(this.keys[v9] == f) {
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
                return ~v10;
            }
            v4 += 8;
            v3 = v3 + v4 & v2;
        }
    }

    public final int getOrPut(float f, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        int v = this.findKeyIndex(f);
        if(v < 0) {
            int v1 = ((Number)function00.invoke()).intValue();
            this.put(f, v1);
            return v1;
        }
        return this.values[v];
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
        this.keys = new float[v1];
        this.values = new int[v1];
    }

    public final void minusAssign(float f) {
        this.remove(f);
    }

    public final void minusAssign(FloatList floatList0) {
        Intrinsics.checkNotNullParameter(floatList0, "keys");
        float[] arr_f = floatList0.content;
        int v = floatList0._size;
        for(int v1 = 0; v1 < v; ++v1) {
            this.remove(arr_f[v1]);
        }
    }

    public final void minusAssign(FloatSet floatSet0) {
        Intrinsics.checkNotNullParameter(floatSet0, "keys");
        float[] arr_f = floatSet0.elements;
        long[] arr_v = floatSet0.metadata;
        int v = arr_v.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            this.remove(arr_f[(v1 << 3) + v4]);
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

    public final void minusAssign(float[] arr_f) {
        Intrinsics.checkNotNullParameter(arr_f, "keys");
        for(int v = 0; v < arr_f.length; ++v) {
            this.remove(arr_f[v]);
        }
    }

    public final void plusAssign(FloatIntMap floatIntMap0) {
        Intrinsics.checkNotNullParameter(floatIntMap0, "from");
        this.putAll(floatIntMap0);
    }

    public final int put(float f, int v, int v1) {
        int v2 = this.findInsertIndex(f);
        if(v2 < 0) {
            v2 = ~v2;
        }
        else {
            v1 = this.values[v2];
        }
        this.keys[v2] = f;
        this.values[v2] = v;
        return v1;
    }

    public final void put(float f, int v) {
        this.set(f, v);
    }

    public final void putAll(FloatIntMap floatIntMap0) {
        Intrinsics.checkNotNullParameter(floatIntMap0, "from");
        float[] arr_f = floatIntMap0.keys;
        int[] arr_v = floatIntMap0.values;
        long[] arr_v1 = floatIntMap0.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v1[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            int v5 = (v1 << 3) + v4;
                            this.set(arr_f[v5], arr_v[v5]);
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_19;
                    }
                    break;
                }
            label_19:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
    }

    public final void remove(float f) {
        int v = this.findKeyIndex(f);
        if(v >= 0) {
            this.removeValueAt(v);
        }
    }

    public final boolean remove(float f, int v) {
        int v1 = this.findKeyIndex(f);
        if(v1 >= 0 && this.values[v1] == v) {
            this.removeValueAt(v1);
            return true;
        }
        return false;
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

    public final void removeIf(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "predicate");
        long[] arr_v = this.metadata;
        int v = arr_v.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            int v5 = (v1 << 3) + v4;
                            if(((Boolean)function20.invoke(((float)this.keys[v5]), ((int)this.values[v5]))).booleanValue()) {
                                this.removeValueAt(v5);
                            }
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_18;
                    }
                    break;
                }
            label_18:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
    }

    public final void removeValueAt(int v) {
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
        float[] arr_f = this.keys;
        int[] arr_v1 = this.values;
        int v1 = this._capacity;
        this.initializeStorage(v);
        float[] arr_f1 = this.keys;
        int[] arr_v2 = this.values;
        for(int v2 = 0; v2 < v1; ++v2) {
            if((arr_v[v2 >> 3] >> ((v2 & 7) << 3) & 0xFFL) < 0x80L) {
                float f = arr_f[v2];
                int v3 = Float.floatToIntBits(f);
                int v4 = v3 * 0xCC9E2D51 ^ v3 * 0xCC9E2D51 << 16;
                int v5 = this.findFirstAvailableSlot(v4 >>> 7);
                long[] arr_v3 = this.metadata;
                int v6 = (v5 & 7) << 3;
                arr_v3[v5 >> 3] = arr_v3[v5 >> 3] & ~(0xFFL << v6) | ((long)(v4 & 0x7F)) << v6;
                int v7 = (v5 - 7 & this._capacity) + (this._capacity & 7);
                int v8 = (v7 & 7) << 3;
                arr_v3[v7 >> 3] = ~(0xFFL << v8) & arr_v3[v7 >> 3] | ((long)(v4 & 0x7F)) << v8;
                arr_f1[v5] = f;
                arr_v2[v5] = arr_v1[v2];
            }
        }
    }

    public final void set(float f, int v) {
        int v1 = this.findInsertIndex(f);
        if(v1 < 0) {
            v1 = ~v1;
        }
        this.keys[v1] = f;
        this.values[v1] = v;
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

