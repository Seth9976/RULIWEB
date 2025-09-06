package androidx.collection;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A!\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0086\b\u001AO\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u0004\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00060\u0005\"\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"arrayMapOf", "Landroidx/collection/ArrayMap;", "K", "V", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Landroidx/collection/ArrayMap;", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ArrayMapKt {
    public static final ArrayMap arrayMapOf() {
        return new ArrayMap();
    }

    public static final ArrayMap arrayMapOf(Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        ArrayMap arrayMap0 = new ArrayMap(arr_pair.length);
        for(int v = 0; v < arr_pair.length; ++v) {
            Pair pair0 = arr_pair[v];
            arrayMap0.put(pair0.getFirst(), pair0.getSecond());
        }
        return arrayMap0;
    }
}

