package androidx.collection;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u000B\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\r\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0004\u00A2\u0006\u0002\u0010\u0002J&\u0010\u0015\u001A\u00020\u00162\u0018\u0010\u0017\u001A\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00160\u0018H\u0086\b\u00F8\u0001\u0000J\u0006\u0010\u001A\u001A\u00020\u0016J&\u0010\u001A\u001A\u00020\u00162\u0018\u0010\u0017\u001A\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00160\u0018H\u0086\b\u00F8\u0001\u0000J\u0011\u0010\u001B\u001A\u00020\u00162\u0006\u0010\u001C\u001A\u00020\u0004H\u0086\u0002J\u000E\u0010\u001D\u001A\u00020\u00162\u0006\u0010\u001C\u001A\u00020\u0004J\u000E\u0010\u001E\u001A\u00020\u00162\u0006\u0010\u001F\u001A\u00020\u0019J\u0006\u0010 \u001A\u00020\u0004J&\u0010 \u001A\u00020\u00042\u0018\u0010\u0017\u001A\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00160\u0018H\u0086\b\u00F8\u0001\u0000J\u0013\u0010!\u001A\u00020\u00162\b\u0010\"\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\u0010\u0010#\u001A\u00020\u00042\u0006\u0010\u001C\u001A\u00020\u0004H\u0001JD\u0010$\u001A\u00020%26\u0010&\u001A2\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\'\u0012\b\b(\u0012\u0004\b\b(\u001C\u0012\u0013\u0012\u00110\u0019\u00A2\u0006\f\b\'\u0012\b\b(\u0012\u0004\b\b(\u001F\u0012\u0004\u0012\u00020%0\u0018H\u0086\b\u00F8\u0001\u0000J/\u0010)\u001A\u00020%2!\u0010&\u001A\u001D\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\'\u0012\b\b(\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020%0*H\u0081\b\u00F8\u0001\u0000J/\u0010,\u001A\u00020%2!\u0010&\u001A\u001D\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\'\u0012\b\b(\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020%0*H\u0086\b\u00F8\u0001\u0000J/\u0010-\u001A\u00020%2!\u0010&\u001A\u001D\u0012\u0013\u0012\u00110\u0019\u00A2\u0006\f\b\'\u0012\b\b(\u0012\u0004\b\b(\u001F\u0012\u0004\u0012\u00020%0*H\u0086\b\u00F8\u0001\u0000J\u0011\u0010.\u001A\u00020\u00192\u0006\u0010\u001C\u001A\u00020\u0004H\u0086\u0002J\u0016\u0010/\u001A\u00020\u00192\u0006\u0010\u001C\u001A\u00020\u00042\u0006\u00100\u001A\u00020\u0019J\"\u00101\u001A\u00020\u00192\u0006\u0010\u001C\u001A\u00020\u00042\f\u00100\u001A\b\u0012\u0004\u0012\u00020\u001902H\u0086\b\u00F8\u0001\u0000J\b\u00103\u001A\u00020\u0004H\u0016J\u0006\u00104\u001A\u00020\u0016J\u0006\u00105\u001A\u00020\u0016J:\u00106\u001A\u0002072\b\b\u0002\u00108\u001A\u0002092\b\b\u0002\u0010:\u001A\u0002092\b\b\u0002\u0010;\u001A\u0002092\b\b\u0002\u0010<\u001A\u00020\u00042\b\b\u0002\u0010=\u001A\u000209H\u0007Jx\u00106\u001A\u0002072\b\b\u0002\u00108\u001A\u0002092\b\b\u0002\u0010:\u001A\u0002092\b\b\u0002\u0010;\u001A\u0002092\b\b\u0002\u0010<\u001A\u00020\u00042\b\b\u0002\u0010=\u001A\u00020928\b\u0004\u0010>\u001A2\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\'\u0012\b\b(\u0012\u0004\b\b(\u001C\u0012\u0013\u0012\u00110\u0019\u00A2\u0006\f\b\'\u0012\b\b(\u0012\u0004\b\b(\u001F\u0012\u0004\u0012\u0002090\u0018H\u0087\b\u00F8\u0001\u0000J\u0006\u0010?\u001A\u00020\u0016J\b\u0010@\u001A\u000207H\u0016R\u0018\u0010\u0003\u001A\u00020\u00048\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0018\u0010\u0006\u001A\u00020\u00048\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0011\u0010\b\u001A\u00020\u00048F\u00A2\u0006\u0006\u001A\u0004\b\t\u0010\nR\u0018\u0010\u000B\u001A\u00020\f8\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0002R\u0018\u0010\u000E\u001A\u00020\u000F8\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0002R\u0011\u0010\u0011\u001A\u00020\u00048F\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\nR\u0018\u0010\u0013\u001A\u00020\u000F8\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0002\u0082\u0001\u0001A\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006B"}, d2 = {"Landroidx/collection/IntLongMap;", "", "()V", "_capacity", "", "get_capacity$collection$annotations", "_size", "get_size$collection$annotations", "capacity", "getCapacity", "()I", "keys", "", "getKeys$annotations", "metadata", "", "getMetadata$annotations", "size", "getSize", "values", "getValues$annotations", "all", "", "predicate", "Lkotlin/Function2;", "", "any", "contains", "key", "containsKey", "containsValue", "value", "count", "equals", "other", "findKeyIndex", "forEach", "", "block", "Lkotlin/ParameterName;", "name", "forEachIndexed", "Lkotlin/Function1;", "index", "forEachKey", "forEachValue", "get", "getOrDefault", "defaultValue", "getOrElse", "Lkotlin/Function0;", "hashCode", "isEmpty", "isNotEmpty", "joinToString", "", "separator", "", "prefix", "postfix", "limit", "truncated", "transform", "none", "toString", "Landroidx/collection/MutableIntLongMap;", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class IntLongMap {
    public int _capacity;
    public int _size;
    public int[] keys;
    public long[] metadata;
    public long[] values;

    private IntLongMap() {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.keys = IntSetKt.getEmptyIntArray();
        this.values = LongSetKt.getEmptyLongArray();
    }

    public IntLongMap(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    public final boolean all(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "predicate");
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v = arr_v2.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v2[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            int v5 = (v1 << 3) + v4;
                            if(!((Boolean)function20.invoke(((int)arr_v[v5]), ((long)arr_v1[v5]))).booleanValue()) {
                                return false;
                            }
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_20;
                    }
                    break;
                }
            label_20:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        return true;
    }

    public final boolean any() {
        return this._size != 0;
    }

    public final boolean any(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "predicate");
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v = arr_v2.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v2[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            int v5 = (v1 << 3) + v4;
                            if(((Boolean)function20.invoke(((int)arr_v[v5]), ((long)arr_v1[v5]))).booleanValue()) {
                                return true;
                            }
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_20;
                    }
                    break;
                }
            label_20:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        return false;
    }

    public final boolean contains(int v) {
        return this.findKeyIndex(v) >= 0;
    }

    public final boolean containsKey(int v) {
        return this.findKeyIndex(v) >= 0;
    }

    public final boolean containsValue(long v) {
        long[] arr_v = this.values;
        long[] arr_v1 = this.metadata;
        int v1 = arr_v1.length - 2;
        if(v1 >= 0) {
            int v2 = 0;
            while(true) {
                long v3 = arr_v1[v2];
                if((~v3 << 7 & v3 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v4 = 8 - (~(v2 - v1) >>> 0x1F);
                    for(int v5 = 0; v5 < v4; ++v5) {
                        if((0xFFL & v3) < 0x80L && v == arr_v[(v2 << 3) + v5]) {
                            return true;
                        }
                        v3 >>= 8;
                    }
                    if(v4 == 8) {
                        goto label_16;
                    }
                    break;
                }
            label_16:
                if(v2 == v1) {
                    break;
                }
                ++v2;
            }
        }
        return false;
    }

    public final int count() {
        return this.getSize();
    }

    public final int count(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "predicate");
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v = arr_v2.length - 2;
        if(v >= 0) {
            int v2 = 0;
            for(int v1 = 0; true; ++v1) {
                long v3 = arr_v2[v1];
                if((~v3 << 7 & v3 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v4 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v5 = 0; v5 < v4; ++v5) {
                        if((0xFFL & v3) < 0x80L) {
                            int v6 = (v1 << 3) + v5;
                            if(((Boolean)function20.invoke(((int)arr_v[v6]), ((long)arr_v1[v6]))).booleanValue()) {
                                ++v2;
                            }
                        }
                        v3 >>= 8;
                    }
                    if(v4 != 8) {
                        return v2;
                    }
                }
                if(v1 == v) {
                    break;
                }
            }
            return v2;
        }
        return 0;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof IntLongMap)) {
            return false;
        }
        if(((IntLongMap)object0).getSize() != this.getSize()) {
            return false;
        }
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v = arr_v2.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v2[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            int v5 = (v1 << 3) + v4;
                            if(arr_v1[v5] != ((IntLongMap)object0).get(arr_v[v5])) {
                                return false;
                            }
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_25;
                    }
                    break;
                }
            label_25:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        return true;
    }

    public final int findKeyIndex(int v) {
        int v1 = 0xCC9E2D51 * v ^ 0xCC9E2D51 * v << 16;
        int v2 = this._capacity;
        int v3 = v1 >>> 7 & v2;
        int v4 = 0;
        while(true) {
            int v5 = (v3 & 7) << 3;
            long v6 = this.metadata[(v3 >> 3) + 1] << 0x40 - v5 & -((long)v5) >> 0x3F | this.metadata[v3 >> 3] >>> v5;
            long v7 = ((long)(v1 & 0x7F)) * 0x101010101010101L ^ v6;
            for(long v8 = ~v7 & v7 - 0x101010101010101L & 0x8080808080808080L; v8 != 0L; v8 &= v8 - 1L) {
                int v9 = (Long.numberOfTrailingZeros(v8) >> 3) + v3 & v2;
                if(this.keys[v9] == v) {
                    return v9;
                }
            }
            if((v6 & ~v6 << 6 & 0x8080808080808080L) != 0L) {
                return -1;
            }
            v4 += 8;
            v3 = v3 + v4 & v2;
        }
    }

    public final void forEach(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "block");
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v = arr_v2.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v2[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            int v5 = (v1 << 3) + v4;
                            function20.invoke(((int)arr_v[v5]), ((long)arr_v1[v5]));
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

    public final void forEachIndexed(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
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
                            function10.invoke(((int)((v1 << 3) + v4)));
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_16;
                    }
                    break;
                }
            label_16:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
    }

    public final void forEachKey(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        int[] arr_v = this.keys;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v1[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            function10.invoke(((int)arr_v[(v1 << 3) + v4]));
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

    public final void forEachValue(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        long[] arr_v = this.values;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v1[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            function10.invoke(((long)arr_v[(v1 << 3) + v4]));
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

    public final long get(int v) {
        int v1 = this.findKeyIndex(v);
        if(v1 < 0) {
            throw new NoSuchElementException("Cannot find value for key " + v);
        }
        return this.values[v1];
    }

    public final int getCapacity() {
        return this._capacity;
    }

    public static void getKeys$annotations() {
    }

    public static void getMetadata$annotations() {
    }

    public final long getOrDefault(int v, long v1) {
        int v2 = this.findKeyIndex(v);
        return v2 < 0 ? v1 : this.values[v2];
    }

    public final long getOrElse(int v, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        int v1 = this.findKeyIndex(v);
        return v1 >= 0 ? this.values[v1] : ((Number)function00.invoke()).longValue();
    }

    public final int getSize() {
        return this._size;
    }

    public static void getValues$annotations() {
    }

    public static void get_capacity$collection$annotations() {
    }

    public static void get_size$collection$annotations() {
    }

    @Override
    public int hashCode() {
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v = arr_v2.length - 2;
        if(v >= 0) {
            int v2 = 0;
            for(int v1 = 0; true; ++v1) {
                long v3 = arr_v2[v1];
                if((~v3 << 7 & v3 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v4 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v5 = 0; v5 < v4; ++v5) {
                        if((0xFFL & v3) < 0x80L) {
                            int v6 = (v1 << 3) + v5;
                            v2 += UByte..ExternalSyntheticBackport0.m(arr_v1[v6]) ^ arr_v[v6];
                        }
                        v3 >>= 8;
                    }
                    if(v4 != 8) {
                        return v2;
                    }
                }
                if(v1 == v) {
                    break;
                }
            }
            return v2;
        }
        return 0;
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    public final boolean isNotEmpty() {
        return this._size != 0;
    }

    public final String joinToString() {
        return IntLongMap.joinToString$default(this, null, null, null, 0, null, 0x1F, null);
    }

    public final String joinToString(CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        return IntLongMap.joinToString$default(this, charSequence0, null, null, 0, null, 30, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        return IntLongMap.joinToString$default(this, charSequence0, charSequence1, null, 0, null, 28, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        return IntLongMap.joinToString$default(this, charSequence0, charSequence1, charSequence2, 0, null, 24, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        return IntLongMap.joinToString$default(this, charSequence0, charSequence1, charSequence2, v, null, 16, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3) {
        long v9;
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v1 = arr_v2.length - 2;
        if(v1 >= 0) {
            int v2 = 0;
            int v3 = 0;
            while(true) {
                long v4 = arr_v2[v2];
                if((~v4 << 7 & v4 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_40;
                }
                int v5 = 8 - (~(v2 - v1) >>> 0x1F);
                int v6 = 0;
                while(v6 < v5) {
                    if((v4 & 0xFFL) < 0x80L) {
                        int v7 = (v2 << 3) + v6;
                        int v8 = arr_v[v7];
                        v9 = v4;
                        long v10 = arr_v1[v7];
                        if(v3 == v) {
                            stringBuilder0.append(charSequence3);
                            goto label_46;
                        }
                        if(v3 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        stringBuilder0.append(v8);
                        stringBuilder0.append('=');
                        stringBuilder0.append(v10);
                        ++v3;
                    }
                    else {
                        v9 = v4;
                    }
                    v4 = v9 >> 8;
                    ++v6;
                }
                if(v5 == 8) {
                label_40:
                    if(v2 != v1) {
                        ++v2;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append(charSequence2);
                }
                goto label_46;
            }
        }
        stringBuilder0.append(charSequence2);
    label_46:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, Function2 function20) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        Intrinsics.checkNotNullParameter(function20, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v1 = arr_v2.length - 2;
        if(v1 >= 0) {
            int v2 = 0;
            int v3 = 0;
            while(true) {
                long v4 = arr_v2[v2];
                int v5 = v2;
                if((~v4 << 7 & v4 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_37;
                }
                int v6 = 8 - (~(v5 - v1) >>> 0x1F);
                int v7 = 0;
                while(v7 < v6) {
                    if((v4 & 0xFFL) < 0x80L) {
                        int v8 = (v5 << 3) + v7;
                        int v9 = arr_v[v8];
                        long v10 = arr_v1[v8];
                        if(v3 == v) {
                            stringBuilder0.append(charSequence3);
                            goto label_43;
                        }
                        if(v3 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        stringBuilder0.append(((CharSequence)function20.invoke(v9, v10)));
                        ++v3;
                    }
                    v4 >>= 8;
                    ++v7;
                }
                if(v6 == 8) {
                label_37:
                    if(v5 != v1) {
                        v2 = v5 + 1;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append(charSequence2);
                }
                goto label_43;
            }
        }
        stringBuilder0.append(charSequence2);
    label_43:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, Function2 function20) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(function20, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v1 = arr_v2.length - 2;
        if(v1 >= 0) {
            int v2 = 0;
            int v3 = 0;
            while(true) {
                long v4 = arr_v2[v2];
                int v5 = v2;
                if((~v4 << 7 & v4 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_36;
                }
                int v6 = 8 - (~(v5 - v1) >>> 0x1F);
                int v7 = 0;
                while(v7 < v6) {
                    if((v4 & 0xFFL) < 0x80L) {
                        int v8 = (v5 << 3) + v7;
                        int v9 = arr_v[v8];
                        long v10 = arr_v1[v8];
                        if(v3 == v) {
                            stringBuilder0.append("...");
                            goto label_42;
                        }
                        if(v3 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        stringBuilder0.append(((CharSequence)function20.invoke(v9, v10)));
                        ++v3;
                    }
                    v4 >>= 8;
                    ++v7;
                }
                if(v6 == 8) {
                label_36:
                    if(v5 != v1) {
                        v2 = v5 + 1;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append(charSequence2);
                }
                goto label_42;
            }
        }
        stringBuilder0.append(charSequence2);
    label_42:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, Function2 function20) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(function20, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v = arr_v2.length - 2;
        if(v >= 0) {
            int v1 = 0;
            int v2 = 0;
            while(true) {
                long v3 = arr_v2[v1];
                int v4 = v1;
                if((~v3 << 7 & v3 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_36;
                }
                int v5 = 8 - (~(v4 - v) >>> 0x1F);
                int v6 = 0;
                while(v6 < v5) {
                    if((v3 & 0xFFL) < 0x80L) {
                        int v7 = (v4 << 3) + v6;
                        int v8 = arr_v[v7];
                        long v9 = arr_v1[v7];
                        if(v2 == -1) {
                            stringBuilder0.append("...");
                            goto label_42;
                        }
                        if(v2 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        stringBuilder0.append(((CharSequence)function20.invoke(v8, v9)));
                        ++v2;
                    }
                    v3 >>= 8;
                    ++v6;
                }
                if(v5 == 8) {
                label_36:
                    if(v4 != v) {
                        v1 = v4 + 1;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append(charSequence2);
                }
                goto label_42;
            }
        }
        stringBuilder0.append(charSequence2);
    label_42:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, Function2 function20) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(function20, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v = arr_v2.length - 2;
        if(v >= 0) {
            int v1 = 0;
            int v2 = 0;
            while(true) {
                long v3 = arr_v2[v1];
                int v4 = v1;
                if((~v3 << 7 & v3 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_35;
                }
                int v5 = 8 - (~(v4 - v) >>> 0x1F);
                int v6 = 0;
                while(v6 < v5) {
                    if((v3 & 0xFFL) < 0x80L) {
                        int v7 = (v4 << 3) + v6;
                        int v8 = arr_v[v7];
                        long v9 = arr_v1[v7];
                        if(v2 == -1) {
                            stringBuilder0.append("...");
                            goto label_41;
                        }
                        if(v2 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        stringBuilder0.append(((CharSequence)function20.invoke(v8, v9)));
                        ++v2;
                    }
                    v3 >>= 8;
                    ++v6;
                }
                if(v5 == 8) {
                label_35:
                    if(v4 != v) {
                        v1 = v4 + 1;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append("");
                }
                goto label_41;
            }
        }
        stringBuilder0.append("");
    label_41:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, Function2 function20) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(function20, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("");
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v = arr_v2.length - 2;
        if(v >= 0) {
            int v1 = 0;
            int v2 = 0;
            while(true) {
                long v3 = arr_v2[v1];
                int v4 = v1;
                if((~v3 << 7 & v3 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_34;
                }
                int v5 = 8 - (~(v4 - v) >>> 0x1F);
                int v6 = 0;
                while(v6 < v5) {
                    if((v3 & 0xFFL) < 0x80L) {
                        int v7 = (v4 << 3) + v6;
                        int v8 = arr_v[v7];
                        long v9 = arr_v1[v7];
                        if(v2 == -1) {
                            stringBuilder0.append("...");
                            goto label_40;
                        }
                        if(v2 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        stringBuilder0.append(((CharSequence)function20.invoke(v8, v9)));
                        ++v2;
                    }
                    v3 >>= 8;
                    ++v6;
                }
                if(v5 == 8) {
                label_34:
                    if(v4 != v) {
                        v1 = v4 + 1;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append("");
                }
                goto label_40;
            }
        }
        stringBuilder0.append("");
    label_40:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("");
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v = arr_v2.length - 2;
        if(v >= 0) {
            int v1 = 0;
            int v2 = 0;
            while(true) {
                long v3 = arr_v2[v1];
                int v4 = v1;
                if((~v3 << 7 & v3 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_33;
                }
                int v5 = 8 - (~(v4 - v) >>> 0x1F);
                int v6 = 0;
                while(v6 < v5) {
                    if((v3 & 0xFFL) < 0x80L) {
                        int v7 = (v4 << 3) + v6;
                        int v8 = arr_v[v7];
                        long v9 = arr_v1[v7];
                        if(v2 == -1) {
                            stringBuilder0.append("...");
                            goto label_39;
                        }
                        if(v2 != 0) {
                            stringBuilder0.append(", ");
                        }
                        stringBuilder0.append(((CharSequence)function20.invoke(v8, v9)));
                        ++v2;
                    }
                    v3 >>= 8;
                    ++v6;
                }
                if(v5 == 8) {
                label_33:
                    if(v4 != v) {
                        v1 = v4 + 1;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append("");
                }
                goto label_39;
            }
        }
        stringBuilder0.append("");
    label_39:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public static String joinToString$default(IntLongMap intLongMap0, CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, int v1, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        if((v1 & 1) != 0) {
            charSequence0 = ", ";
        }
        if((v1 & 2) != 0) {
            charSequence1 = "";
        }
        if((v1 & 4) != 0) {
            charSequence2 = "";
        }
        if((v1 & 8) != 0) {
            v = -1;
        }
        if((v1 & 16) != 0) {
            charSequence3 = "...";
        }
        return intLongMap0.joinToString(charSequence0, charSequence1, charSequence2, v, charSequence3);
    }

    public static String joinToString$default(IntLongMap intLongMap0, CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, Function2 function20, int v1, Object object0) {
        long[] arr_v4;
        long[] arr_v3;
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        CharSequence charSequence4 = (v1 & 1) == 0 ? charSequence0 : ", ";
        CharSequence charSequence5 = (v1 & 2) == 0 ? charSequence1 : "";
        CharSequence charSequence6 = (v1 & 4) == 0 ? charSequence2 : "";
        CharSequence charSequence7 = (v1 & 16) == 0 ? charSequence3 : "...";
        Intrinsics.checkNotNullParameter(charSequence4, "separator");
        Intrinsics.checkNotNullParameter(charSequence5, "prefix");
        Intrinsics.checkNotNullParameter(charSequence6, "postfix");
        Intrinsics.checkNotNullParameter(charSequence7, "truncated");
        Intrinsics.checkNotNullParameter(function20, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence5);
        int[] arr_v = intLongMap0.keys;
        long[] arr_v1 = intLongMap0.values;
        long[] arr_v2 = intLongMap0.metadata;
        int v2 = arr_v2.length - 2;
        if(v2 >= 0) {
            int v3 = 0;
            int v4 = 0;
            while(true) {
                long v5 = arr_v2[v3];
                int v6 = v3;
                if((~v5 << 7 & v5 & 0x8080808080808080L) == 0x8080808080808080L) {
                    arr_v4 = arr_v2;
                    goto label_60;
                }
                int v7 = 8 - (~(v6 - v2) >>> 0x1F);
                int v8 = 0;
                while(v8 < v7) {
                    if((v5 & 0xFFL) < 0x80L) {
                        int v9 = (v6 << 3) + v8;
                        int v10 = arr_v[v9];
                        long v11 = arr_v1[v9];
                        if(v4 == ((v1 & 8) == 0 ? v : -1)) {
                            stringBuilder0.append(charSequence7);
                            goto label_67;
                        }
                        if(v4 != 0) {
                            stringBuilder0.append(charSequence4);
                        }
                        arr_v3 = arr_v2;
                        stringBuilder0.append(((CharSequence)function20.invoke(v10, v11)));
                        ++v4;
                    }
                    else {
                        arr_v3 = arr_v2;
                    }
                    v5 >>= 8;
                    ++v8;
                    arr_v2 = arr_v3;
                }
                arr_v4 = arr_v2;
                if(v7 == 8) {
                label_60:
                    if(v6 != v2) {
                        v3 = v6 + 1;
                        arr_v2 = arr_v4;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append(charSequence6);
                }
                goto label_67;
            }
        }
        stringBuilder0.append(charSequence6);
    label_67:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final boolean none() {
        return this._size == 0;
    }

    @Override
    public String toString() {
        int v10;
        int v8;
        if(this.isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder0 = new StringBuilder("{");
        int[] arr_v = this.keys;
        long[] arr_v1 = this.values;
        long[] arr_v2 = this.metadata;
        int v = arr_v2.length - 2;
        if(v >= 0) {
            int v2 = 0;
            for(int v1 = 0; true; v1 = v10 + 1) {
                long v3 = arr_v2[v1];
                if((~v3 << 7 & v3 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v4 = 8 - (~(v1 - v) >>> 0x1F);
                    int v5 = 0;
                    while(v5 < v4) {
                        if((0xFFL & v3) < 0x80L) {
                            int v6 = (v1 << 3) + v5;
                            int v7 = arr_v[v6];
                            v8 = v1;
                            long v9 = arr_v1[v6];
                            stringBuilder0.append(v7);
                            stringBuilder0.append("=");
                            stringBuilder0.append(v9);
                            ++v2;
                            if(v2 < this._size) {
                                stringBuilder0.append(", ");
                            }
                        }
                        else {
                            v8 = v1;
                        }
                        v3 >>= 8;
                        ++v5;
                        v1 = v8;
                    }
                    if(v4 != 8) {
                        break;
                    }
                }
                v10 = v1;
                if(v10 == v) {
                    break;
                }
            }
        }
        stringBuilder0.append('}');
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "s.append(\'}\').toString()");
        return s;
    }
}

