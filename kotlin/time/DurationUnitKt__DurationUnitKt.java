package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\u001A\u0018\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0001\u001A\u0010\u0010\u0006\u001A\u00020\u00012\u0006\u0010\u0007\u001A\u00020\bH\u0001\u001A\f\u0010\u0007\u001A\u00020\b*\u00020\u0001H\u0001¨\u0006\t"}, d2 = {"durationUnitByIsoChar", "Lkotlin/time/DurationUnit;", "isoChar", "", "isTimeComponent", "", "durationUnitByShortName", "shortName", "", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/time/DurationUnitKt")
class DurationUnitKt__DurationUnitKt extends DurationUnitKt__DurationUnitJvmKt {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[DurationUnit.values().length];
            try {
                arr_v[DurationUnit.NANOSECONDS.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[DurationUnit.MICROSECONDS.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[DurationUnit.MILLISECONDS.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[DurationUnit.SECONDS.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[DurationUnit.MINUTES.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[DurationUnit.HOURS.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[DurationUnit.DAYS.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final DurationUnit durationUnitByIsoChar(char c, boolean z) {
        if(!z) {
            if(c != 68) {
                throw new IllegalArgumentException("Invalid or unsupported duration ISO non-time unit: " + c);
            }
            return DurationUnit.DAYS;
        }
        switch(c) {
            case 72: {
                return DurationUnit.HOURS;
            }
            case 77: {
                return DurationUnit.MINUTES;
            }
            case 83: {
                return DurationUnit.SECONDS;
            }
            default: {
                throw new IllegalArgumentException("Invalid duration ISO time unit: " + c);
            }
        }
    }

    public static final DurationUnit durationUnitByShortName(String s) {
        Intrinsics.checkNotNullParameter(s, "shortName");
        switch(s) {
            case "d": {
                return DurationUnit.DAYS;
            }
            case "h": {
                return DurationUnit.HOURS;
            }
            case "m": {
                return DurationUnit.MINUTES;
            }
            case "ms": {
                return DurationUnit.MILLISECONDS;
            }
            case "ns": {
                return DurationUnit.NANOSECONDS;
            }
            case "s": {
                return DurationUnit.SECONDS;
            }
            case "us": {
                return DurationUnit.MICROSECONDS;
            }
            default: {
                throw new IllegalArgumentException("Unknown duration unit short name: " + s);
            }
        }
    }

    public static final String shortName(DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(durationUnit0, "<this>");
        switch(WhenMappings.$EnumSwitchMapping$0[durationUnit0.ordinal()]) {
            case 1: {
                return "ns";
            }
            case 2: {
                return "us";
            }
            case 3: {
                return "ms";
            }
            case 4: {
                return "s";
            }
            case 5: {
                return "m";
            }
            case 6: {
                return "h";
            }
            case 7: {
                return "d";
            }
            default: {
                throw new IllegalStateException(("Unknown unit: " + durationUnit0).toString());
            }
        }
    }
}

