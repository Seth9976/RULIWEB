package kotlin.collections;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u001A\u001C\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001A#\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007¢\u0006\u0002\b\u0004\u001A\u001D\u0010\u0005\u001A\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001A\u00020\u0006H\u0002¢\u0006\u0002\b\b\u001A\u001D\u0010\t\u001A\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001A\u00020\u0006H\u0002¢\u0006\u0002\b\n\u001A\u001D\u0010\u000B\u001A\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001A\u00020\u0006H\u0002¢\u0006\u0002\b\f¨\u0006\r"}, d2 = {"asReversed", "", "T", "", "asReversedMutable", "reverseElementIndex", "", "index", "reverseElementIndex$CollectionsKt__ReversedViewsKt", "reverseIteratorIndex", "reverseIteratorIndex$CollectionsKt__ReversedViewsKt", "reversePositionIndex", "reversePositionIndex$CollectionsKt__ReversedViewsKt", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
class CollectionsKt__ReversedViewsKt extends CollectionsKt__MutableCollectionsKt {
    public static final List asReversed(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        return new ReversedListReadOnly(list0);
    }

    public static final List asReversedMutable(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        return new ReversedList(list0);
    }

    private static final int reverseElementIndex$CollectionsKt__ReversedViewsKt(List list0, int v) {
        if(!new IntRange(0, CollectionsKt.getLastIndex(list0)).contains(v)) {
            throw new IndexOutOfBoundsException("Element index " + v + " must be in range [" + new IntRange(0, CollectionsKt.getLastIndex(list0)) + "].");
        }
        return CollectionsKt.getLastIndex(list0) - v;
    }

    private static final int reverseIteratorIndex$CollectionsKt__ReversedViewsKt(List list0, int v) {
        return CollectionsKt.getLastIndex(list0) - v;
    }

    private static final int reversePositionIndex$CollectionsKt__ReversedViewsKt(List list0, int v) {
        if(!new IntRange(0, list0.size()).contains(v)) {
            throw new IndexOutOfBoundsException("Position index " + v + " must be in range [" + new IntRange(0, list0.size()) + "].");
        }
        return list0.size() - v;
    }
}

