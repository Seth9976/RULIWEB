package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000F\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\u0004\bf\u0018\u0000*\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003J\u0016\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\fJ\b\u0010\r\u001A\u00020\nH\u0016R\u0012\u0010\u0004\u001A\u00028\u0000X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001A\u00028\u0000X¦\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\u0006¨\u0006\u000E"}, d2 = {"Lkotlin/ranges/ClosedRange;", "T", "", "", "endInclusive", "getEndInclusive", "()Ljava/lang/Comparable;", "start", "getStart", "contains", "", "value", "(Ljava/lang/Comparable;)Z", "isEmpty", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public interface ClosedRange {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static boolean contains(ClosedRange closedRange0, Comparable comparable0) {
            Intrinsics.checkNotNullParameter(comparable0, "value");
            return comparable0.compareTo(closedRange0.getStart()) >= 0 && comparable0.compareTo(closedRange0.getEndInclusive()) <= 0;
        }

        public static boolean isEmpty(ClosedRange closedRange0) {
            return closedRange0.getStart().compareTo(closedRange0.getEndInclusive()) > 0;
        }
    }

    boolean contains(Comparable arg1);

    Comparable getEndInclusive();

    Comparable getStart();

    boolean isEmpty();
}

