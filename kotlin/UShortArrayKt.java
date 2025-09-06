package kotlin;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001A0\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0012\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u0007\u001A\u001F\u0010\b\u001A\u00020\u00012\n\u0010\t\u001A\u00020\u0001\"\u00020\u0006H\u0087\bø\u0001\u0001¢\u0006\u0004\b\n\u0010\u000B\u0082\u0002\u000B\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006\f"}, d2 = {"UShortArray", "Lkotlin/UShortArray;", "size", "", "init", "Lkotlin/Function1;", "Lkotlin/UShort;", "(ILkotlin/jvm/functions/Function1;)[S", "ushortArrayOf", "elements", "ushortArrayOf-rL5Bavg", "([S)[S", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class UShortArrayKt {
    private static final short[] UShortArray(int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "init");
        short[] arr_v = new short[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_v[v1] = ((UShort)function10.invoke(v1)).unbox-impl();
        }
        return UShortArray.constructor-impl(arr_v);
    }

    private static final short[] ushortArrayOf-rL5Bavg(short[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "elements");
        return arr_v;
    }
}

