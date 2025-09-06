package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0006\u0010\u0002\u001A\u00020\u0001\u001A\u0006\u0010\u0003\u001A\u00020\u0001\u001A\u000E\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u0005\u001A\u0016\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0005\u001A\u001E\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\u0005\u001A\u0012\u0010\u0003\u001A\u00020\u00012\n\u0010\b\u001A\u00020\t\"\u00020\u0005\u001A\t\u0010\n\u001A\u00020\u000BH\u0086\b\u001A\u000E\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u0005\u001A\u0016\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0005\u001A\u001E\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\u0005\u001A\u0015\u0010\n\u001A\u00020\u000B2\n\u0010\b\u001A\u00020\t\"\u00020\u0005H\u0086\b\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"EmptyLongList", "Landroidx/collection/LongList;", "emptyLongList", "longListOf", "element1", "", "element2", "element3", "elements", "", "mutableLongListOf", "Landroidx/collection/MutableLongList;", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class LongListKt {
    private static final LongList EmptyLongList;

    static {
        LongListKt.EmptyLongList = new MutableLongList(0);
    }

    public static final LongList emptyLongList() {
        return LongListKt.EmptyLongList;
    }

    public static final LongList longListOf() {
        return LongListKt.EmptyLongList;
    }

    public static final LongList longListOf(long v) {
        return LongListKt.mutableLongListOf(v);
    }

    public static final LongList longListOf(long v, long v1) {
        return LongListKt.mutableLongListOf(v, v1);
    }

    public static final LongList longListOf(long v, long v1, long v2) {
        return LongListKt.mutableLongListOf(v, v1, v2);
    }

    public static final LongList longListOf(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        MutableLongList mutableLongList0 = new MutableLongList(arr_v.length);
        mutableLongList0.plusAssign(arr_v);
        return mutableLongList0;
    }

    public static final MutableLongList mutableLongListOf() {
        return new MutableLongList(0, 1, null);
    }

    public static final MutableLongList mutableLongListOf(long v) {
        MutableLongList mutableLongList0 = new MutableLongList(1);
        mutableLongList0.add(v);
        return mutableLongList0;
    }

    public static final MutableLongList mutableLongListOf(long v, long v1) {
        MutableLongList mutableLongList0 = new MutableLongList(2);
        mutableLongList0.add(v);
        mutableLongList0.add(v1);
        return mutableLongList0;
    }

    public static final MutableLongList mutableLongListOf(long v, long v1, long v2) {
        MutableLongList mutableLongList0 = new MutableLongList(3);
        mutableLongList0.add(v);
        mutableLongList0.add(v1);
        mutableLongList0.add(v2);
        return mutableLongList0;
    }

    public static final MutableLongList mutableLongListOf(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        MutableLongList mutableLongList0 = new MutableLongList(arr_v.length);
        mutableLongList0.plusAssign(arr_v);
        return mutableLongList0;
    }
}

