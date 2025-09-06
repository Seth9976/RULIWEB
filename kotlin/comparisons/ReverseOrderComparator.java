package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u001E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001j\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002`\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J$\u0010\u0006\u001A\u00020\u00072\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\"\u0010\n\u001A\u001E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001j\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002`\u0004¨\u0006\u000B"}, d2 = {"Lkotlin/comparisons/ReverseOrderComparator;", "Ljava/util/Comparator;", "", "", "Lkotlin/Comparator;", "()V", "compare", "", "a", "b", "reversed", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class ReverseOrderComparator implements Comparator {
    public static final ReverseOrderComparator INSTANCE;

    static {
        ReverseOrderComparator.INSTANCE = new ReverseOrderComparator();
    }

    public int compare(Comparable comparable0, Comparable comparable1) {
        Intrinsics.checkNotNullParameter(comparable0, "a");
        Intrinsics.checkNotNullParameter(comparable1, "b");
        return comparable1.compareTo(comparable0);
    }

    @Override
    public int compare(Object object0, Object object1) {
        return this.compare(((Comparable)object0), ((Comparable)object1));
    }

    @Override
    public final Comparator reversed() {
        return (Object object0, Object object1) -> this.compare(((Comparable)object0), ((Comparable)object1));
    }
}

