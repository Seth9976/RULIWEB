package kotlin;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u001A2\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0002H\u00022\u0006\u0010\u0004\u001A\u0002H\u0003H\u0086\u0004¢\u0006\u0002\u0010\u0005\u001A\"\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u000E\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b0\u0001\u001A(\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u0014\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b0\t¨\u0006\n"}, d2 = {"to", "Lkotlin/Pair;", "A", "B", "that", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;", "toList", "", "T", "Lkotlin/Triple;", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class TuplesKt {
    public static final Pair to(Object object0, Object object1) [...] // Inlined contents

    public static final List toList(Pair pair0) {
        Intrinsics.checkNotNullParameter(pair0, "<this>");
        return CollectionsKt.listOf(new Object[]{pair0.getFirst(), pair0.getSecond()});
    }

    public static final List toList(Triple triple0) {
        Intrinsics.checkNotNullParameter(triple0, "<this>");
        return CollectionsKt.listOf(new Object[]{triple0.getFirst(), triple0.getSecond(), triple0.getThird()});
    }
}

