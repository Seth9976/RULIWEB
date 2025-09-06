package androidx.collection;

import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001C\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000F\u0012\b\b\u0002\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\b\u0010\u0007\u001A\u00020\bH\u0002J\u0006\u0010\t\u001A\u00020\bJ\u0010\u0010\n\u001A\u00020\u00042\u0006\u0010\u000B\u001A\u00020\u0004H\u0002J\u0015\u0010\f\u001A\u00020\u00042\u0006\u0010\r\u001A\u00028\u0000H\u0002\u00A2\u0006\u0002\u0010\u000EJ\'\u0010\u000F\u001A\u00020\u00102\u0006\u0010\r\u001A\u00028\u00002\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00100\u0012H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001A\u00020\bH\u0002J\u0010\u0010\u0015\u001A\u00020\b2\u0006\u0010\u0016\u001A\u00020\u0004H\u0002J\u0010\u0010\u0017\u001A\u00020\b2\u0006\u0010\u0003\u001A\u00020\u0004H\u0002J\u0016\u0010\u0018\u001A\u00020\b2\u0006\u0010\r\u001A\u00028\u0000H\u0086\n\u00A2\u0006\u0002\u0010\u0019J\u0017\u0010\u0018\u001A\u00020\b2\f\u0010\u001A\u001A\b\u0012\u0004\u0012\u00028\u00000\u001BH\u0086\nJ\u001E\u0010\u0018\u001A\u00020\b2\u000E\u0010\u001A\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u001CH\u0086\n\u00A2\u0006\u0002\u0010\u001DJ\u0017\u0010\u0018\u001A\u00020\b2\f\u0010\u001A\u001A\b\u0012\u0004\u0012\u00028\u00000\u001EH\u0086\nJ\u0017\u0010\u0018\u001A\u00020\b2\f\u0010\u001A\u001A\b\u0012\u0004\u0012\u00028\u00000\u001FH\u0086\nJ\u0017\u0010 \u001A\u00020\b2\f\u0010!\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086\nJ\u001B\u0010\"\u001A\u00020\b2\u0006\u0010\r\u001A\u00028\u00002\u0006\u0010#\u001A\u00020\u0010\u00A2\u0006\u0002\u0010$J#\u0010\"\u001A\u00020\u00102\u0006\u0010\r\u001A\u00028\u00002\u0006\u0010#\u001A\u00020\u00102\u0006\u0010%\u001A\u00020\u0010\u00A2\u0006\u0002\u0010&J\u0014\u0010\'\u001A\u00020\b2\f\u0010!\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002J\u0013\u0010(\u001A\u00020\b2\u0006\u0010\r\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\u0019J\u001B\u0010(\u001A\u00020)2\u0006\u0010\r\u001A\u00028\u00002\u0006\u0010#\u001A\u00020\u0010\u00A2\u0006\u0002\u0010*J\b\u0010+\u001A\u00020\bH\u0002J&\u0010,\u001A\u00020\b2\u0018\u0010-\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020)0.H\u0086\b\u00F8\u0001\u0000J\u0010\u0010/\u001A\u00020\b2\u0006\u00100\u001A\u00020\u0004H\u0001J\u0010\u00101\u001A\u00020\b2\u0006\u00102\u001A\u00020\u0004H\u0002J\u001E\u00103\u001A\u00020\b2\u0006\u0010\r\u001A\u00028\u00002\u0006\u0010#\u001A\u00020\u0010H\u0086\u0002\u00A2\u0006\u0002\u0010$J\u0006\u00104\u001A\u00020\u0004J\u0019\u00105\u001A\u00020\b2\u0006\u00100\u001A\u00020\u00042\u0006\u0010#\u001A\u00020\u0010H\u0082\bR\u000E\u0010\u0006\u001A\u00020\u0004X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u00066"}, d2 = {"Landroidx/collection/MutableObjectLongMap;", "K", "Landroidx/collection/ObjectLongMap;", "initialCapacity", "", "(I)V", "growthLimit", "adjustStorage", "", "clear", "findFirstAvailableSlot", "hash1", "findIndex", "key", "(Ljava/lang/Object;)I", "getOrPut", "", "defaultValue", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)J", "initializeGrowth", "initializeMetadata", "capacity", "initializeStorage", "minusAssign", "(Ljava/lang/Object;)V", "keys", "Landroidx/collection/ScatterSet;", "", "([Ljava/lang/Object;)V", "", "Lkotlin/sequences/Sequence;", "plusAssign", "from", "put", "value", "(Ljava/lang/Object;J)V", "default", "(Ljava/lang/Object;JJ)J", "putAll", "remove", "", "(Ljava/lang/Object;J)Z", "removeDeletedMarkers", "removeIf", "predicate", "Lkotlin/Function2;", "removeValueAt", "index", "resizeStorage", "newCapacity", "set", "trim", "writeMetadata", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class MutableObjectLongMap extends ObjectLongMap {
    private int growthLimit;

    public MutableObjectLongMap() {
        this(0, 1, null);
    }

    public MutableObjectLongMap(int v) {
        super(null);
        if(v < 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.");
        }
        this.initializeStorage(ScatterMapKt.unloadedCapacity(v));
    }

    public MutableObjectLongMap(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
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
        ArraysKt.fill(this.keys, null, 0, this._capacity);
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

    private final int findIndex(Object object0) {
        int v = object0 == null ? 0 : object0.hashCode();
        int v1 = v * 0xCC9E2D51 ^ v * 0xCC9E2D51 << 16;
        int v2 = this._capacity;
        int v3 = v1 >>> 7 & v2;
        int v4 = 0;
        while(true) {
            int v5 = (v3 & 7) << 3;
            long v6 = this.metadata[(v3 >> 3) + 1] << 0x40 - v5 & -((long)v5) >> 0x3F | this.metadata[v3 >> 3] >>> v5;
            long v7 = v6 ^ ((long)(v1 & 0x7F)) * 0x101010101010101L;
            for(long v8 = ~v7 & v7 - 0x101010101010101L & 0x8080808080808080L; v8 != 0L; v8 &= v8 - 1L) {
                int v9 = v3 + (Long.numberOfTrailingZeros(v8) >> 3) & v2;
                if(Intrinsics.areEqual(this.keys[v9], object0)) {
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

    public final long getOrPut(Object object0, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        int v = this.findKeyIndex(object0);
        if(v >= 0) {
            return this.values[v];
        }
        long v1 = ((Number)function00.invoke()).longValue();
        this.set(object0, v1);
        return v1;
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
        this.keys = new Object[v1];
        this.values = new long[v1];
    }

    public final void minusAssign(ScatterSet scatterSet0) {
        Intrinsics.checkNotNullParameter(scatterSet0, "keys");
        Object[] arr_object = scatterSet0.elements;
        long[] arr_v = scatterSet0.metadata;
        int v = arr_v.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            this.remove(arr_object[(v1 << 3) + v4]);
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

    public final void minusAssign(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "keys");
        for(Object object0: iterable0) {
            this.remove(object0);
        }
    }

    public final void minusAssign(Object object0) {
        this.remove(object0);
    }

    public final void minusAssign(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "keys");
        for(Object object0: sequence0) {
            this.remove(object0);
        }
    }

    public final void minusAssign(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "keys");
        for(int v = 0; v < arr_object.length; ++v) {
            this.remove(arr_object[v]);
        }
    }

    public final void plusAssign(ObjectLongMap objectLongMap0) {
        Intrinsics.checkNotNullParameter(objectLongMap0, "from");
        this.putAll(objectLongMap0);
    }

    public final long put(Object object0, long v, long v1) {
        int v2 = this.findIndex(object0);
        if(v2 < 0) {
            v2 = ~v2;
        }
        else {
            v1 = this.values[v2];
        }
        this.keys[v2] = object0;
        this.values[v2] = v;
        return v1;
    }

    public final void put(Object object0, long v) {
        this.set(object0, v);
    }

    public final void putAll(ObjectLongMap objectLongMap0) {
        Intrinsics.checkNotNullParameter(objectLongMap0, "from");
        Object[] arr_object = objectLongMap0.keys;
        long[] arr_v = objectLongMap0.values;
        long[] arr_v1 = objectLongMap0.metadata;
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
                            this.set(arr_object[v5], arr_v[v5]);
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

    public final void remove(Object object0) {
        int v = this.findKeyIndex(object0);
        if(v >= 0) {
            this.removeValueAt(v);
        }
    }

    public final boolean remove(Object object0, long v) {
        int v1 = this.findKeyIndex(object0);
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
                            if(((Boolean)function20.invoke(this.keys[v5], ((long)this.values[v5]))).booleanValue()) {
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
        this.keys[v] = null;
    }

    private final void resizeStorage(int v) {
        int v7;
        long[] arr_v = this.metadata;
        Object[] arr_object = this.keys;
        long[] arr_v1 = this.values;
        int v1 = this._capacity;
        this.initializeStorage(v);
        Object[] arr_object1 = this.keys;
        long[] arr_v2 = this.values;
        for(int v2 = 0; v2 < v1; v2 = v7 + 1) {
            if((arr_v[v2 >> 3] >> ((v2 & 7) << 3) & 0xFFL) < 0x80L) {
                Object object0 = arr_object[v2];
                int v3 = object0 == null ? 0 : object0.hashCode();
                int v4 = v3 * 0xCC9E2D51 ^ v3 * 0xCC9E2D51 << 16;
                int v5 = this.findFirstAvailableSlot(v4 >>> 7);
                long[] arr_v3 = this.metadata;
                int v6 = (v5 & 7) << 3;
                v7 = v2;
                arr_v3[v5 >> 3] = arr_v3[v5 >> 3] & ~(0xFFL << v6) | ((long)(v4 & 0x7F)) << v6;
                int v8 = (v5 - 7 & this._capacity) + (this._capacity & 7);
                int v9 = (v8 & 7) << 3;
                arr_v3[v8 >> 3] = arr_v3[v8 >> 3] & ~(0xFFL << v9) | ((long)(v4 & 0x7F)) << v9;
                arr_object1[v5] = object0;
                arr_v2[v5] = arr_v1[v7];
            }
            else {
                v7 = v2;
            }
        }
    }

    public final void set(Object object0, long v) {
        int v1 = this.findIndex(object0);
        if(v1 < 0) {
            v1 = ~v1;
        }
        this.keys[v1] = object0;
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

