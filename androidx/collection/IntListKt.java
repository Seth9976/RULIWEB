package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0006\u0010\u0002\u001A\u00020\u0001\u001A\u0006\u0010\u0003\u001A\u00020\u0001\u001A\u000E\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u0005\u001A\u0016\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0005\u001A\u001E\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\u0005\u001A\u0012\u0010\u0003\u001A\u00020\u00012\n\u0010\b\u001A\u00020\t\"\u00020\u0005\u001A\t\u0010\n\u001A\u00020\u000BH\u0086\b\u001A\u000E\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u0005\u001A\u0016\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0005\u001A\u001E\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\u0005\u001A\u0015\u0010\n\u001A\u00020\u000B2\n\u0010\b\u001A\u00020\t\"\u00020\u0005H\u0086\b\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"EmptyIntList", "Landroidx/collection/IntList;", "emptyIntList", "intListOf", "element1", "", "element2", "element3", "elements", "", "mutableIntListOf", "Landroidx/collection/MutableIntList;", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class IntListKt {
    private static final IntList EmptyIntList;

    static {
        IntListKt.EmptyIntList = new MutableIntList(0);
    }

    public static final IntList emptyIntList() {
        return IntListKt.EmptyIntList;
    }

    public static final IntList intListOf() {
        return IntListKt.EmptyIntList;
    }

    public static final IntList intListOf(int v) {
        return IntListKt.mutableIntListOf(v);
    }

    public static final IntList intListOf(int v, int v1) {
        return IntListKt.mutableIntListOf(v, v1);
    }

    public static final IntList intListOf(int v, int v1, int v2) {
        return IntListKt.mutableIntListOf(v, v1, v2);
    }

    public static final IntList intListOf(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        MutableIntList mutableIntList0 = new MutableIntList(arr_v.length);
        mutableIntList0.plusAssign(arr_v);
        return mutableIntList0;
    }

    public static final MutableIntList mutableIntListOf() {
        return new MutableIntList(0, 1, null);
    }

    public static final MutableIntList mutableIntListOf(int v) {
        MutableIntList mutableIntList0 = new MutableIntList(1);
        mutableIntList0.add(v);
        return mutableIntList0;
    }

    public static final MutableIntList mutableIntListOf(int v, int v1) {
        MutableIntList mutableIntList0 = new MutableIntList(2);
        mutableIntList0.add(v);
        mutableIntList0.add(v1);
        return mutableIntList0;
    }

    public static final MutableIntList mutableIntListOf(int v, int v1, int v2) {
        MutableIntList mutableIntList0 = new MutableIntList(3);
        mutableIntList0.add(v);
        mutableIntList0.add(v1);
        mutableIntList0.add(v2);
        return mutableIntList0;
    }

    public static final MutableIntList mutableIntListOf(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        MutableIntList mutableIntList0 = new MutableIntList(arr_v.length);
        mutableIntList0.plusAssign(arr_v);
        return mutableIntList0;
    }
}

