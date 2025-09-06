package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\u001A\u0006\u0010\u0006\u001A\u00020\u0007\u001A\u0011\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0080\b\u001A\u0006\u0010\f\u001A\u00020\u0007\u001A\u000E\u0010\f\u001A\u00020\u00072\u0006\u0010\r\u001A\u00020\u000B\u001A\u0016\u0010\f\u001A\u00020\u00072\u0006\u0010\r\u001A\u00020\u000B2\u0006\u0010\u000E\u001A\u00020\u000B\u001A\u001E\u0010\f\u001A\u00020\u00072\u0006\u0010\r\u001A\u00020\u000B2\u0006\u0010\u000E\u001A\u00020\u000B2\u0006\u0010\u000F\u001A\u00020\u000B\u001A\u0012\u0010\f\u001A\u00020\u00072\n\u0010\u0010\u001A\u00020\u0001\"\u00020\u000B\u001A\u0006\u0010\u0011\u001A\u00020\u0005\u001A\u000E\u0010\u0011\u001A\u00020\u00052\u0006\u0010\r\u001A\u00020\u000B\u001A\u0016\u0010\u0011\u001A\u00020\u00052\u0006\u0010\r\u001A\u00020\u000B2\u0006\u0010\u000E\u001A\u00020\u000B\u001A\u001E\u0010\u0011\u001A\u00020\u00052\u0006\u0010\r\u001A\u00020\u000B2\u0006\u0010\u000E\u001A\u00020\u000B2\u0006\u0010\u000F\u001A\u00020\u000B\u001A\u0012\u0010\u0011\u001A\u00020\u00052\n\u0010\u0010\u001A\u00020\u0001\"\u00020\u000B\"\u0014\u0010\u0000\u001A\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0003\"\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"EmptyLongArray", "", "getEmptyLongArray", "()[J", "EmptyLongSet", "Landroidx/collection/MutableLongSet;", "emptyLongSet", "Landroidx/collection/LongSet;", "hash", "", "k", "", "longSetOf", "element1", "element2", "element3", "elements", "mutableLongSetOf", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class LongSetKt {
    private static final long[] EmptyLongArray;
    private static final MutableLongSet EmptyLongSet;

    static {
        LongSetKt.EmptyLongSet = new MutableLongSet(0);
        LongSetKt.EmptyLongArray = new long[0];
    }

    public static final LongSet emptyLongSet() {
        return LongSetKt.EmptyLongSet;
    }

    public static final long[] getEmptyLongArray() {
        return LongSetKt.EmptyLongArray;
    }

    public static final int hash(long v) {
        int v1 = (int)(v ^ v >>> 0x20);
        return v1 * 0xCC9E2D51 ^ v1 * 0xCC9E2D51 << 16;
    }

    public static final LongSet longSetOf() {
        return LongSetKt.EmptyLongSet;
    }

    public static final LongSet longSetOf(long v) {
        return LongSetKt.mutableLongSetOf(v);
    }

    public static final LongSet longSetOf(long v, long v1) {
        return LongSetKt.mutableLongSetOf(v, v1);
    }

    public static final LongSet longSetOf(long v, long v1, long v2) {
        return LongSetKt.mutableLongSetOf(v, v1, v2);
    }

    public static final LongSet longSetOf(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        MutableLongSet mutableLongSet0 = new MutableLongSet(arr_v.length);
        mutableLongSet0.plusAssign(arr_v);
        return mutableLongSet0;
    }

    public static final MutableLongSet mutableLongSetOf() {
        return new MutableLongSet(0, 1, null);
    }

    public static final MutableLongSet mutableLongSetOf(long v) {
        MutableLongSet mutableLongSet0 = new MutableLongSet(1);
        mutableLongSet0.plusAssign(v);
        return mutableLongSet0;
    }

    public static final MutableLongSet mutableLongSetOf(long v, long v1) {
        MutableLongSet mutableLongSet0 = new MutableLongSet(2);
        mutableLongSet0.plusAssign(v);
        mutableLongSet0.plusAssign(v1);
        return mutableLongSet0;
    }

    public static final MutableLongSet mutableLongSetOf(long v, long v1, long v2) {
        MutableLongSet mutableLongSet0 = new MutableLongSet(3);
        mutableLongSet0.plusAssign(v);
        mutableLongSet0.plusAssign(v1);
        mutableLongSet0.plusAssign(v2);
        return mutableLongSet0;
    }

    public static final MutableLongSet mutableLongSetOf(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        MutableLongSet mutableLongSet0 = new MutableLongSet(arr_v.length);
        mutableLongSet0.plusAssign(arr_v);
        return mutableLongSet0;
    }
}

