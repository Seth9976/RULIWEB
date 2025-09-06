package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A \u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0004H\u0001\u001A \u0010\u0000\u001A\u00020\u00062\u0006\u0010\u0002\u001A\u00020\u00062\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0004H\u0001\u001A \u0010\u0007\u001A\u00020\u00062\u0006\u0010\u0002\u001A\u00020\u00062\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0004H\u0001\u001A\f\u0010\b\u001A\u00020\u0004*\u00020\tH\u0007\u001A\f\u0010\n\u001A\u00020\t*\u00020\u0004H\u0007¨\u0006\u000B"}, d2 = {"convertDurationUnit", "", "value", "sourceUnit", "Lkotlin/time/DurationUnit;", "targetUnit", "", "convertDurationUnitOverflow", "toDurationUnit", "Ljava/util/concurrent/TimeUnit;", "toTimeUnit", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/time/DurationUnitKt")
class DurationUnitKt__DurationUnitJvmKt {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[TimeUnit.values().length];
            try {
                arr_v[TimeUnit.NANOSECONDS.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[TimeUnit.MICROSECONDS.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[TimeUnit.MILLISECONDS.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[TimeUnit.SECONDS.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[TimeUnit.MINUTES.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[TimeUnit.HOURS.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[TimeUnit.DAYS.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final double convertDurationUnit(double f, DurationUnit durationUnit0, DurationUnit durationUnit1) {
        Intrinsics.checkNotNullParameter(durationUnit0, "sourceUnit");
        Intrinsics.checkNotNullParameter(durationUnit1, "targetUnit");
        long v = durationUnit1.getTimeUnit$kotlin_stdlib().convert(1L, durationUnit0.getTimeUnit$kotlin_stdlib());
        return v <= 0L ? f / ((double)durationUnit0.getTimeUnit$kotlin_stdlib().convert(1L, durationUnit1.getTimeUnit$kotlin_stdlib())) : f * ((double)v);
    }

    public static final long convertDurationUnit(long v, DurationUnit durationUnit0, DurationUnit durationUnit1) {
        Intrinsics.checkNotNullParameter(durationUnit0, "sourceUnit");
        Intrinsics.checkNotNullParameter(durationUnit1, "targetUnit");
        return durationUnit1.getTimeUnit$kotlin_stdlib().convert(v, durationUnit0.getTimeUnit$kotlin_stdlib());
    }

    public static final long convertDurationUnitOverflow(long v, DurationUnit durationUnit0, DurationUnit durationUnit1) {
        Intrinsics.checkNotNullParameter(durationUnit0, "sourceUnit");
        Intrinsics.checkNotNullParameter(durationUnit1, "targetUnit");
        return durationUnit1.getTimeUnit$kotlin_stdlib().convert(v, durationUnit0.getTimeUnit$kotlin_stdlib());
    }

    public static final DurationUnit toDurationUnit(TimeUnit timeUnit0) {
        Intrinsics.checkNotNullParameter(timeUnit0, "<this>");
        switch(WhenMappings.$EnumSwitchMapping$0[timeUnit0.ordinal()]) {
            case 1: {
                return DurationUnit.NANOSECONDS;
            }
            case 2: {
                return DurationUnit.MICROSECONDS;
            }
            case 3: {
                return DurationUnit.MILLISECONDS;
            }
            case 4: {
                return DurationUnit.SECONDS;
            }
            case 5: {
                return DurationUnit.MINUTES;
            }
            case 6: {
                return DurationUnit.HOURS;
            }
            case 7: {
                return DurationUnit.DAYS;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    public static final TimeUnit toTimeUnit(DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(durationUnit0, "<this>");
        return durationUnit0.getTimeUnit$kotlin_stdlib();
    }
}

