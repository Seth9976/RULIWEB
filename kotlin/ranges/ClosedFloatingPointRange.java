package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\b\bg\u0018\u0000*\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\u0016\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001A\u00020\u0005H\u0016J\u001D\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00028\u00002\u0006\u0010\u000B\u001A\u00028\u0000H&¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lkotlin/ranges/ClosedFloatingPointRange;", "T", "", "Lkotlin/ranges/ClosedRange;", "contains", "", "value", "(Ljava/lang/Comparable;)Z", "isEmpty", "lessThanOrEquals", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Z", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public interface ClosedFloatingPointRange extends ClosedRange {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static boolean contains(ClosedFloatingPointRange closedFloatingPointRange0, Comparable comparable0) {
            Intrinsics.checkNotNullParameter(comparable0, "value");
            return closedFloatingPointRange0.lessThanOrEquals(closedFloatingPointRange0.getStart(), comparable0) && closedFloatingPointRange0.lessThanOrEquals(comparable0, closedFloatingPointRange0.getEndInclusive());
        }

        public static boolean isEmpty(ClosedFloatingPointRange closedFloatingPointRange0) {
            return !closedFloatingPointRange0.lessThanOrEquals(closedFloatingPointRange0.getStart(), closedFloatingPointRange0.getEndInclusive());
        }
    }

    @Override  // kotlin.ranges.ClosedRange
    boolean contains(Comparable arg1);

    @Override  // kotlin.ranges.ClosedRange
    boolean isEmpty();

    boolean lessThanOrEquals(Comparable arg1, Comparable arg2);
}

