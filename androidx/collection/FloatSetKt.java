package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u001A\u0006\u0010\u0006\u001A\u00020\u0007\u001A\u0006\u0010\b\u001A\u00020\u0007\u001A\u000E\u0010\b\u001A\u00020\u00072\u0006\u0010\t\u001A\u00020\n\u001A\u0016\u0010\b\u001A\u00020\u00072\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\n\u001A\u001E\u0010\b\u001A\u00020\u00072\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\n2\u0006\u0010\f\u001A\u00020\n\u001A\u0012\u0010\b\u001A\u00020\u00072\n\u0010\r\u001A\u00020\u0001\"\u00020\n\u001A\u0011\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\nH\u0080\b\u001A\u0006\u0010\u0011\u001A\u00020\u0005\u001A\u000E\u0010\u0011\u001A\u00020\u00052\u0006\u0010\t\u001A\u00020\n\u001A\u0016\u0010\u0011\u001A\u00020\u00052\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\n\u001A\u001E\u0010\u0011\u001A\u00020\u00052\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\n2\u0006\u0010\f\u001A\u00020\n\u001A\u0012\u0010\u0011\u001A\u00020\u00052\n\u0010\r\u001A\u00020\u0001\"\u00020\n\"\u0014\u0010\u0000\u001A\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0003\"\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"EmptyFloatArray", "", "getEmptyFloatArray", "()[F", "EmptyFloatSet", "Landroidx/collection/MutableFloatSet;", "emptyFloatSet", "Landroidx/collection/FloatSet;", "floatSetOf", "element1", "", "element2", "element3", "elements", "hash", "", "k", "mutableFloatSetOf", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class FloatSetKt {
    private static final float[] EmptyFloatArray;
    private static final MutableFloatSet EmptyFloatSet;

    static {
        FloatSetKt.EmptyFloatSet = new MutableFloatSet(0);
        FloatSetKt.EmptyFloatArray = new float[0];
    }

    public static final FloatSet emptyFloatSet() {
        return FloatSetKt.EmptyFloatSet;
    }

    public static final FloatSet floatSetOf() {
        return FloatSetKt.EmptyFloatSet;
    }

    public static final FloatSet floatSetOf(float f) {
        return FloatSetKt.mutableFloatSetOf(f);
    }

    public static final FloatSet floatSetOf(float f, float f1) {
        return FloatSetKt.mutableFloatSetOf(f, f1);
    }

    public static final FloatSet floatSetOf(float f, float f1, float f2) {
        return FloatSetKt.mutableFloatSetOf(f, f1, f2);
    }

    public static final FloatSet floatSetOf(float[] arr_f) {
        Intrinsics.checkNotNullParameter(arr_f, "elements");
        MutableFloatSet mutableFloatSet0 = new MutableFloatSet(arr_f.length);
        mutableFloatSet0.plusAssign(arr_f);
        return mutableFloatSet0;
    }

    public static final float[] getEmptyFloatArray() {
        return FloatSetKt.EmptyFloatArray;
    }

    public static final int hash(float f) {
        int v = Float.floatToIntBits(f);
        return v * 0xCC9E2D51 ^ v * 0xCC9E2D51 << 16;
    }

    public static final MutableFloatSet mutableFloatSetOf() {
        return new MutableFloatSet(0, 1, null);
    }

    public static final MutableFloatSet mutableFloatSetOf(float f) {
        MutableFloatSet mutableFloatSet0 = new MutableFloatSet(1);
        mutableFloatSet0.plusAssign(f);
        return mutableFloatSet0;
    }

    public static final MutableFloatSet mutableFloatSetOf(float f, float f1) {
        MutableFloatSet mutableFloatSet0 = new MutableFloatSet(2);
        mutableFloatSet0.plusAssign(f);
        mutableFloatSet0.plusAssign(f1);
        return mutableFloatSet0;
    }

    public static final MutableFloatSet mutableFloatSetOf(float f, float f1, float f2) {
        MutableFloatSet mutableFloatSet0 = new MutableFloatSet(3);
        mutableFloatSet0.plusAssign(f);
        mutableFloatSet0.plusAssign(f1);
        mutableFloatSet0.plusAssign(f2);
        return mutableFloatSet0;
    }

    public static final MutableFloatSet mutableFloatSetOf(float[] arr_f) {
        Intrinsics.checkNotNullParameter(arr_f, "elements");
        MutableFloatSet mutableFloatSet0 = new MutableFloatSet(arr_f.length);
        mutableFloatSet0.plusAssign(arr_f);
        return mutableFloatSet0;
    }
}

