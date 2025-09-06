package kotlin.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\u001A \u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u0001H\u0002\u001A \u0010\u0000\u001A\u00020\u00052\u0006\u0010\u0002\u001A\u00020\u00052\u0006\u0010\u0003\u001A\u00020\u00052\u0006\u0010\u0004\u001A\u00020\u0005H\u0002\u001A \u0010\u0006\u001A\u00020\u00012\u0006\u0010\u0007\u001A\u00020\u00012\u0006\u0010\b\u001A\u00020\u00012\u0006\u0010\t\u001A\u00020\u0001H\u0001\u001A \u0010\u0006\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\u00052\u0006\u0010\b\u001A\u00020\u00052\u0006\u0010\t\u001A\u00020\u0005H\u0001\u001A\u0018\u0010\n\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0001H\u0002\u001A\u0018\u0010\n\u001A\u00020\u00052\u0006\u0010\u0002\u001A\u00020\u00052\u0006\u0010\u0003\u001A\u00020\u0005H\u0002¨\u0006\u000B"}, d2 = {"differenceModulo", "", "a", "b", "c", "", "getProgressionLastElement", "start", "end", "step", "mod", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class ProgressionUtilKt {
    private static final int differenceModulo(int v, int v1, int v2) {
        return ProgressionUtilKt.mod(ProgressionUtilKt.mod(v, v2) - ProgressionUtilKt.mod(v1, v2), v2);
    }

    private static final long differenceModulo(long v, long v1, long v2) {
        return ProgressionUtilKt.mod(ProgressionUtilKt.mod(v, v2) - ProgressionUtilKt.mod(v1, v2), v2);
    }

    public static final int getProgressionLastElement(int v, int v1, int v2) {
        if(v2 > 0) {
            return v >= v1 ? v1 : v1 - ProgressionUtilKt.differenceModulo(v1, v, v2);
        }
        if(v2 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        }
        return v > v1 ? v1 + ProgressionUtilKt.differenceModulo(v, v1, -v2) : v1;
    }

    public static final long getProgressionLastElement(long v, long v1, long v2) {
        int v3 = Long.compare(v2, 0L);
        if(v3 > 0) {
            return v >= v1 ? v1 : v1 - ProgressionUtilKt.differenceModulo(v1, v, v2);
        }
        if(v3 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        }
        return v > v1 ? v1 + ProgressionUtilKt.differenceModulo(v, v1, -v2) : v1;
    }

    private static final int mod(int v, int v1) {
        int v2 = v % v1;
        return v2 < 0 ? v2 + v1 : v2;
    }

    private static final long mod(long v, long v1) {
        long v2 = v % v1;
        return v2 < 0L ? v2 + v1 : v2;
    }
}

