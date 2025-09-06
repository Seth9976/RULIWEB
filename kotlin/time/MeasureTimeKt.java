package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A/\u0010\u0000\u001A\u00020\u00012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0005\u001A3\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001A3\u0010\u0000\u001A\u00020\u0001*\u00020\t2\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\n\u001A3\u0010\u0000\u001A\u00020\u0001*\u00020\u000B2\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\f\u001A7\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\t2\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001A7\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u00020\u000B2\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\b0\u0003H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u0082\u0002\u000B\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006\r"}, d2 = {"measureTime", "Lkotlin/time/Duration;", "block", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)J", "measureTimedValue", "Lkotlin/time/TimedValue;", "T", "Lkotlin/time/TimeSource;", "(Lkotlin/time/TimeSource;Lkotlin/jvm/functions/Function0;)J", "Lkotlin/time/TimeSource$Monotonic;", "(Lkotlin/time/TimeSource$Monotonic;Lkotlin/jvm/functions/Function0;)J", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class MeasureTimeKt {
    public static final long measureTime(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "block");
        function00.invoke();
        return ValueTimeMark.elapsedNow-UwyO8pc(3051500L);
    }

    public static final long measureTime(Monotonic timeSource$Monotonic0, Function0 function00) {
        Intrinsics.checkNotNullParameter(timeSource$Monotonic0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "block");
        function00.invoke();
        return ValueTimeMark.elapsedNow-UwyO8pc(3166700L);
    }

    public static final long measureTime(TimeSource timeSource0, Function0 function00) {
        Intrinsics.checkNotNullParameter(timeSource0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "block");
        TimeMark timeMark0 = timeSource0.markNow();
        function00.invoke();
        return timeMark0.elapsedNow-UwyO8pc();
    }

    public static final TimedValue measureTimedValue(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "block");
        return new TimedValue(function00.invoke(), ValueTimeMark.elapsedNow-UwyO8pc(3139400L), null);
    }

    public static final TimedValue measureTimedValue(Monotonic timeSource$Monotonic0, Function0 function00) {
        Intrinsics.checkNotNullParameter(timeSource$Monotonic0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "block");
        return new TimedValue(function00.invoke(), ValueTimeMark.elapsedNow-UwyO8pc(2357400L), null);
    }

    public static final TimedValue measureTimedValue(TimeSource timeSource0, Function0 function00) {
        Intrinsics.checkNotNullParameter(timeSource0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "block");
        TimeMark timeMark0 = timeSource0.markNow();
        return new TimedValue(function00.invoke(), timeMark0.elapsedNow-UwyO8pc(), null);
    }
}

