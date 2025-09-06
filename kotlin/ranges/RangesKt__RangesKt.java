package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001C\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000F\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\u001A\u0018\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0000\u001A@\u0010\u0006\u001A\u00020\u0003\"\b\b\u0000\u0010\u0007*\u00020\b\"\u0018\b\u0001\u0010\t*\b\u0012\u0004\u0012\u0002H\u00070\n*\b\u0012\u0004\u0012\u0002H\u00070\u000B*\u0002H\t2\b\u0010\f\u001A\u0004\u0018\u0001H\u0007H\u0087\n¢\u0006\u0002\u0010\r\u001A@\u0010\u0006\u001A\u00020\u0003\"\b\b\u0000\u0010\u0007*\u00020\b\"\u0018\b\u0001\u0010\t*\b\u0012\u0004\u0012\u0002H\u00070\u000E*\b\u0012\u0004\u0012\u0002H\u00070\u000B*\u0002H\t2\b\u0010\f\u001A\u0004\u0018\u0001H\u0007H\u0087\n¢\u0006\u0002\u0010\u000F\u001A0\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u00070\n\"\u000E\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0011*\u0002H\u00072\u0006\u0010\u0012\u001A\u0002H\u0007H\u0086\u0002¢\u0006\u0002\u0010\u0013\u001A\u001B\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00150\u0014*\u00020\u00152\u0006\u0010\u0012\u001A\u00020\u0015H\u0087\u0002\u001A\u001B\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00160\u0014*\u00020\u00162\u0006\u0010\u0012\u001A\u00020\u0016H\u0087\u0002\u001A0\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00070\u000E\"\u000E\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0011*\u0002H\u00072\u0006\u0010\u0012\u001A\u0002H\u0007H\u0087\u0002¢\u0006\u0002\u0010\u0018\u001A\u001B\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00150\u000E*\u00020\u00152\u0006\u0010\u0012\u001A\u00020\u0015H\u0087\u0002\u001A\u001B\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00160\u000E*\u00020\u00162\u0006\u0010\u0012\u001A\u00020\u0016H\u0087\u0002¨\u0006\u0019"}, d2 = {"checkStepIsPositive", "", "isPositive", "", "step", "", "contains", "T", "", "R", "Lkotlin/ranges/ClosedRange;", "", "element", "(Lkotlin/ranges/ClosedRange;Ljava/lang/Object;)Z", "Lkotlin/ranges/OpenEndRange;", "(Lkotlin/ranges/OpenEndRange;Ljava/lang/Object;)Z", "rangeTo", "", "that", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Lkotlin/ranges/ClosedRange;", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "", "rangeUntil", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Lkotlin/ranges/OpenEndRange;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/ranges/RangesKt")
class RangesKt__RangesKt {
    public static final void checkStepIsPositive(boolean z, Number number0) {
        Intrinsics.checkNotNullParameter(number0, "step");
        if(!z) {
            throw new IllegalArgumentException("Step must be positive, was: " + number0 + '.');
        }
    }

    private static final boolean contains(ClosedRange closedRange0, Object object0) {
        Intrinsics.checkNotNullParameter(closedRange0, "<this>");
        return object0 != null && closedRange0.contains(((Comparable)object0));
    }

    private static final boolean contains(OpenEndRange openEndRange0, Object object0) {
        Intrinsics.checkNotNullParameter(openEndRange0, "<this>");
        return object0 != null && openEndRange0.contains(((Comparable)object0));
    }

    public static final ClosedFloatingPointRange rangeTo(double f, double f1) {
        return new ClosedDoubleRange(f, f1);
    }

    public static final ClosedFloatingPointRange rangeTo(float f, float f1) {
        return new ClosedFloatRange(f, f1);
    }

    public static final ClosedRange rangeTo(Comparable comparable0, Comparable comparable1) {
        Intrinsics.checkNotNullParameter(comparable0, "<this>");
        Intrinsics.checkNotNullParameter(comparable1, "that");
        return new ComparableRange(comparable0, comparable1);
    }

    public static final OpenEndRange rangeUntil(double f, double f1) {
        return new OpenEndDoubleRange(f, f1);
    }

    public static final OpenEndRange rangeUntil(float f, float f1) {
        return new OpenEndFloatRange(f, f1);
    }

    public static final OpenEndRange rangeUntil(Comparable comparable0, Comparable comparable1) {
        Intrinsics.checkNotNullParameter(comparable0, "<this>");
        Intrinsics.checkNotNullParameter(comparable1, "that");
        return new ComparableOpenEndRange(comparable0, comparable1);
    }
}

