package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.collections.BooleanIterator;
import kotlin.collections.ByteIterator;
import kotlin.collections.CharIterator;
import kotlin.collections.DoubleIterator;
import kotlin.collections.FloatIterator;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.collections.ShortIterator;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0000\u001A\u000E\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003\u001A\u000E\u0010\u0000\u001A\u00020\u00042\u0006\u0010\u0002\u001A\u00020\u0005\u001A\u000E\u0010\u0000\u001A\u00020\u00062\u0006\u0010\u0002\u001A\u00020\u0007\u001A\u000E\u0010\u0000\u001A\u00020\b2\u0006\u0010\u0002\u001A\u00020\t\u001A\u000E\u0010\u0000\u001A\u00020\n2\u0006\u0010\u0002\u001A\u00020\u000B\u001A\u000E\u0010\u0000\u001A\u00020\f2\u0006\u0010\u0002\u001A\u00020\r\u001A\u000E\u0010\u0000\u001A\u00020\u000E2\u0006\u0010\u0002\u001A\u00020\u000F\u001A\u000E\u0010\u0000\u001A\u00020\u00102\u0006\u0010\u0002\u001A\u00020\u0011¨\u0006\u0012"}, d2 = {"iterator", "Lkotlin/collections/BooleanIterator;", "array", "", "Lkotlin/collections/ByteIterator;", "", "Lkotlin/collections/CharIterator;", "", "Lkotlin/collections/DoubleIterator;", "", "Lkotlin/collections/FloatIterator;", "", "Lkotlin/collections/IntIterator;", "", "Lkotlin/collections/LongIterator;", "", "Lkotlin/collections/ShortIterator;", "", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class ArrayIteratorsKt {
    public static final BooleanIterator iterator(boolean[] arr_z) {
        Intrinsics.checkNotNullParameter(arr_z, "array");
        return new ArrayBooleanIterator(arr_z);
    }

    public static final ByteIterator iterator(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "array");
        return new ArrayByteIterator(arr_b);
    }

    public static final CharIterator iterator(char[] arr_c) {
        Intrinsics.checkNotNullParameter(arr_c, "array");
        return new ArrayCharIterator(arr_c);
    }

    public static final DoubleIterator iterator(double[] arr_f) {
        Intrinsics.checkNotNullParameter(arr_f, "array");
        return new ArrayDoubleIterator(arr_f);
    }

    public static final FloatIterator iterator(float[] arr_f) {
        Intrinsics.checkNotNullParameter(arr_f, "array");
        return new ArrayFloatIterator(arr_f);
    }

    public static final IntIterator iterator(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "array");
        return new ArrayIntIterator(arr_v);
    }

    public static final LongIterator iterator(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "array");
        return new ArrayLongIterator(arr_v);
    }

    public static final ShortIterator iterator(short[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "array");
        return new ArrayShortIterator(arr_v);
    }
}

