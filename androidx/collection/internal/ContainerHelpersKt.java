package androidx.collection.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0007\u001A \u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u00012\u0006\u0010\u000B\u001A\u00020\t2\u0006\u0010\f\u001A\u00020\tH\u0000\u001A \u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u00032\u0006\u0010\u000B\u001A\u00020\t2\u0006\u0010\f\u001A\u00020\rH\u0000\u001A\u001C\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u00062\b\u0010\u0011\u001A\u0004\u0018\u00010\u0006H\u0000\u001A\u0010\u0010\u0012\u001A\u00020\t2\u0006\u0010\u0013\u001A\u00020\tH\u0000\u001A\u0010\u0010\u0014\u001A\u00020\t2\u0006\u0010\u0013\u001A\u00020\tH\u0000\u001A\u0010\u0010\u0015\u001A\u00020\t2\u0006\u0010\u0013\u001A\u00020\tH\u0000\"\u0010\u0010\u0000\u001A\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001A\u00020\u00038\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u001A\u0010\u0004\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0000X\u0081\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0016"}, d2 = {"EMPTY_INTS", "", "EMPTY_LONGS", "", "EMPTY_OBJECTS", "", "", "[Ljava/lang/Object;", "binarySearch", "", "array", "size", "value", "", "equal", "", "a", "b", "idealByteArraySize", "need", "idealIntArraySize", "idealLongArraySize", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ContainerHelpersKt {
    public static final int[] EMPTY_INTS;
    public static final long[] EMPTY_LONGS;
    public static final Object[] EMPTY_OBJECTS;

    static {
        ContainerHelpersKt.EMPTY_INTS = new int[0];
        ContainerHelpersKt.EMPTY_LONGS = new long[0];
        ContainerHelpersKt.EMPTY_OBJECTS = new Object[0];
    }

    public static final int binarySearch(int[] arr_v, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_v, "array");
        int v2 = v - 1;
        int v3 = 0;
        while(v3 <= v2) {
            int v4 = v3 + v2 >>> 1;
            int v5 = arr_v[v4];
            if(v5 < v1) {
                v3 = v4 + 1;
                continue;
            }
            if(v5 > v1) {
                v2 = v4 - 1;
                continue;
            }
            return v4;
        }
        return ~v3;
    }

    public static final int binarySearch(long[] arr_v, int v, long v1) {
        Intrinsics.checkNotNullParameter(arr_v, "array");
        int v2 = v - 1;
        int v3 = 0;
        while(v3 <= v2) {
            int v4 = v3 + v2 >>> 1;
            int v5 = Long.compare(arr_v[v4], v1);
            if(v5 < 0) {
                v3 = v4 + 1;
                continue;
            }
            if(v5 > 0) {
                v2 = v4 - 1;
                continue;
            }
            return v4;
        }
        return ~v3;
    }

    public static final boolean equal(Object object0, Object object1) {
        return Intrinsics.areEqual(object0, object1);
    }

    public static final int idealByteArraySize(int v) {
        for(int v1 = 4; v1 < 0x20; ++v1) {
            int v2 = (1 << v1) - 12;
            if(v <= v2) {
                return v2;
            }
        }
        return v;
    }

    public static final int idealIntArraySize(int v) {
        return ContainerHelpersKt.idealByteArraySize(v * 4) / 4;
    }

    public static final int idealLongArraySize(int v) {
        return ContainerHelpersKt.idealByteArraySize(v * 8) / 8;
    }
}

