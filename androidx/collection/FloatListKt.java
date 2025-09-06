package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0006\u0010\u0002\u001A\u00020\u0001\u001A\u0006\u0010\u0003\u001A\u00020\u0001\u001A\u000E\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u0005\u001A\u0016\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0005\u001A\u001E\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\u0005\u001A\u0012\u0010\u0003\u001A\u00020\u00012\n\u0010\b\u001A\u00020\t\"\u00020\u0005\u001A\t\u0010\n\u001A\u00020\u000BH\u0086\b\u001A\u000E\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u0005\u001A\u0016\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0005\u001A\u001E\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\u0005\u001A\u0015\u0010\n\u001A\u00020\u000B2\n\u0010\b\u001A\u00020\t\"\u00020\u0005H\u0086\b\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"EmptyFloatList", "Landroidx/collection/FloatList;", "emptyFloatList", "floatListOf", "element1", "", "element2", "element3", "elements", "", "mutableFloatListOf", "Landroidx/collection/MutableFloatList;", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class FloatListKt {
    private static final FloatList EmptyFloatList;

    static {
        FloatListKt.EmptyFloatList = new MutableFloatList(0);
    }

    public static final FloatList emptyFloatList() {
        return FloatListKt.EmptyFloatList;
    }

    public static final FloatList floatListOf() {
        return FloatListKt.EmptyFloatList;
    }

    public static final FloatList floatListOf(float f) {
        return FloatListKt.mutableFloatListOf(f);
    }

    public static final FloatList floatListOf(float f, float f1) {
        return FloatListKt.mutableFloatListOf(f, f1);
    }

    public static final FloatList floatListOf(float f, float f1, float f2) {
        return FloatListKt.mutableFloatListOf(f, f1, f2);
    }

    public static final FloatList floatListOf(float[] arr_f) {
        Intrinsics.checkNotNullParameter(arr_f, "elements");
        MutableFloatList mutableFloatList0 = new MutableFloatList(arr_f.length);
        mutableFloatList0.plusAssign(arr_f);
        return mutableFloatList0;
    }

    public static final MutableFloatList mutableFloatListOf() {
        return new MutableFloatList(0, 1, null);
    }

    public static final MutableFloatList mutableFloatListOf(float f) {
        MutableFloatList mutableFloatList0 = new MutableFloatList(1);
        mutableFloatList0.add(f);
        return mutableFloatList0;
    }

    public static final MutableFloatList mutableFloatListOf(float f, float f1) {
        MutableFloatList mutableFloatList0 = new MutableFloatList(2);
        mutableFloatList0.add(f);
        mutableFloatList0.add(f1);
        return mutableFloatList0;
    }

    public static final MutableFloatList mutableFloatListOf(float f, float f1, float f2) {
        MutableFloatList mutableFloatList0 = new MutableFloatList(3);
        mutableFloatList0.add(f);
        mutableFloatList0.add(f1);
        mutableFloatList0.add(f2);
        return mutableFloatList0;
    }

    public static final MutableFloatList mutableFloatListOf(float[] arr_f) {
        Intrinsics.checkNotNullParameter(arr_f, "elements");
        MutableFloatList mutableFloatList0 = new MutableFloatList(arr_f.length);
        mutableFloatList0.plusAssign(arr_f);
        return mutableFloatList0;
    }
}

