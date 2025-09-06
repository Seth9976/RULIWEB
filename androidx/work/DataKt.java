package androidx.work;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\u001A>\u0010\u0000\u001A\u00020\u00012.\u0010\u0002\u001A\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0086\b¢\u0006\u0002\u0010\u0007\u001A!\u0010\b\u001A\u00020\t\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u0006*\u00020\u00012\u0006\u0010\u000B\u001A\u00020\u0005H\u0086\b¨\u0006\f"}, d2 = {"workDataOf", "Landroidx/work/Data;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroidx/work/Data;", "hasKeyWithValueOfType", "", "T", "key", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DataKt {
    public static final boolean hasKeyWithValueOfType(Data data0, String s) {
        Intrinsics.checkNotNullParameter(data0, "<this>");
        Intrinsics.checkNotNullParameter(s, "key");
        Intrinsics.reifiedOperationMarker(4, "T");
        return data0.hasKeyWithValueOfType(s, Object.class);
    }

    public static final Data workDataOf(Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        Builder data$Builder0 = new Builder();
        for(int v = 0; v < arr_pair.length; ++v) {
            Pair pair0 = arr_pair[v];
            data$Builder0.put(((String)pair0.getFirst()), pair0.getSecond());
        }
        return data$Builder0.build();
    }
}

