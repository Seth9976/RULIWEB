package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u001A\u0006\u0010\u0006\u001A\u00020\u0007\u001A\u0011\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\tH\u0080\b\u001A\u0006\u0010\u000B\u001A\u00020\u0007\u001A\u000E\u0010\u000B\u001A\u00020\u00072\u0006\u0010\f\u001A\u00020\t\u001A\u0016\u0010\u000B\u001A\u00020\u00072\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\r\u001A\u00020\t\u001A\u001E\u0010\u000B\u001A\u00020\u00072\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\r\u001A\u00020\t2\u0006\u0010\u000E\u001A\u00020\t\u001A\u0012\u0010\u000B\u001A\u00020\u00072\n\u0010\u000F\u001A\u00020\u0001\"\u00020\t\u001A\u0006\u0010\u0010\u001A\u00020\u0005\u001A\u000E\u0010\u0010\u001A\u00020\u00052\u0006\u0010\f\u001A\u00020\t\u001A\u0016\u0010\u0010\u001A\u00020\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\r\u001A\u00020\t\u001A\u001E\u0010\u0010\u001A\u00020\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\r\u001A\u00020\t2\u0006\u0010\u000E\u001A\u00020\t\u001A\u0012\u0010\u0010\u001A\u00020\u00052\n\u0010\u000F\u001A\u00020\u0001\"\u00020\t\"\u0014\u0010\u0000\u001A\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0003\"\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"EmptyIntArray", "", "getEmptyIntArray", "()[I", "EmptyIntSet", "Landroidx/collection/MutableIntSet;", "emptyIntSet", "Landroidx/collection/IntSet;", "hash", "", "k", "intSetOf", "element1", "element2", "element3", "elements", "mutableIntSetOf", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class IntSetKt {
    private static final int[] EmptyIntArray;
    private static final MutableIntSet EmptyIntSet;

    static {
        IntSetKt.EmptyIntSet = new MutableIntSet(0);
        IntSetKt.EmptyIntArray = new int[0];
    }

    public static final IntSet emptyIntSet() {
        return IntSetKt.EmptyIntSet;
    }

    public static final int[] getEmptyIntArray() {
        return IntSetKt.EmptyIntArray;
    }

    public static final int hash(int v) {
        return v * 0xCC9E2D51 ^ v * 0xCC9E2D51 << 16;
    }

    public static final IntSet intSetOf() {
        return IntSetKt.EmptyIntSet;
    }

    public static final IntSet intSetOf(int v) {
        return IntSetKt.mutableIntSetOf(v);
    }

    public static final IntSet intSetOf(int v, int v1) {
        return IntSetKt.mutableIntSetOf(v, v1);
    }

    public static final IntSet intSetOf(int v, int v1, int v2) {
        return IntSetKt.mutableIntSetOf(v, v1, v2);
    }

    public static final IntSet intSetOf(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        MutableIntSet mutableIntSet0 = new MutableIntSet(arr_v.length);
        mutableIntSet0.plusAssign(arr_v);
        return mutableIntSet0;
    }

    public static final MutableIntSet mutableIntSetOf() {
        return new MutableIntSet(0, 1, null);
    }

    public static final MutableIntSet mutableIntSetOf(int v) {
        MutableIntSet mutableIntSet0 = new MutableIntSet(1);
        mutableIntSet0.plusAssign(v);
        return mutableIntSet0;
    }

    public static final MutableIntSet mutableIntSetOf(int v, int v1) {
        MutableIntSet mutableIntSet0 = new MutableIntSet(2);
        mutableIntSet0.plusAssign(v);
        mutableIntSet0.plusAssign(v1);
        return mutableIntSet0;
    }

    public static final MutableIntSet mutableIntSetOf(int v, int v1, int v2) {
        MutableIntSet mutableIntSet0 = new MutableIntSet(3);
        mutableIntSet0.plusAssign(v);
        mutableIntSet0.plusAssign(v1);
        mutableIntSet0.plusAssign(v2);
        return mutableIntSet0;
    }

    public static final MutableIntSet mutableIntSetOf(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        MutableIntSet mutableIntSet0 = new MutableIntSet(arr_v.length);
        mutableIntSet0.plusAssign(arr_v);
        return mutableIntSet0;
    }
}

