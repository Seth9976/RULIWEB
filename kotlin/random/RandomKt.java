package kotlin.random;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A\u0010\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0007\u001A\u0010\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0004H\u0007\u001A\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\bH\u0000\u001A\u0018\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0007\u001A\u00020\f2\u0006\u0010\t\u001A\u00020\fH\u0000\u001A\u0018\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0007\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\u0003H\u0000\u001A\u0018\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0007\u001A\u00020\u00042\u0006\u0010\t\u001A\u00020\u0004H\u0000\u001A\u0010\u0010\r\u001A\u00020\u00032\u0006\u0010\u000E\u001A\u00020\u0003H\u0000\u001A\u0014\u0010\u000F\u001A\u00020\u0003*\u00020\u00012\u0006\u0010\u0010\u001A\u00020\u0011H\u0007\u001A\u0014\u0010\u0012\u001A\u00020\u0004*\u00020\u00012\u0006\u0010\u0010\u001A\u00020\u0013H\u0007\u001A\u0014\u0010\u0014\u001A\u00020\u0003*\u00020\u00032\u0006\u0010\u0015\u001A\u00020\u0003H\u0000¨\u0006\u0016"}, d2 = {"Random", "Lkotlin/random/Random;", "seed", "", "", "boundsErrorMessage", "", "from", "", "until", "checkRangeBounds", "", "", "fastLog2", "value", "nextInt", "range", "Lkotlin/ranges/IntRange;", "nextLong", "Lkotlin/ranges/LongRange;", "takeUpperBits", "bitCount", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class RandomKt {
    public static final Random Random(int v) {
        return new XorWowRandom(v, v >> 0x1F);
    }

    public static final Random Random(long v) {
        return new XorWowRandom(((int)v), ((int)(v >> 0x20)));
    }

    public static final String boundsErrorMessage(Object object0, Object object1) {
        Intrinsics.checkNotNullParameter(object0, "from");
        Intrinsics.checkNotNullParameter(object1, "until");
        return "Random range is empty: [" + object0 + ", " + object1 + ").";
    }

    public static final void checkRangeBounds(double f, double f1) {
        if(f1 <= f) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(f, f1).toString());
        }
    }

    public static final void checkRangeBounds(int v, int v1) {
        if(v1 <= v) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(v, v1).toString());
        }
    }

    public static final void checkRangeBounds(long v, long v1) {
        if(v1 <= v) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(v, v1).toString());
        }
    }

    public static final int fastLog2(int v) {
        return 0x1F - Integer.numberOfLeadingZeros(v);
    }

    public static final int nextInt(Random random0, IntRange intRange0) {
        Intrinsics.checkNotNullParameter(random0, "<this>");
        Intrinsics.checkNotNullParameter(intRange0, "range");
        if(intRange0.isEmpty()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + intRange0);
        }
        if(intRange0.getLast() < 0x7FFFFFFF) {
            return random0.nextInt(intRange0.getFirst(), intRange0.getLast() + 1);
        }
        return intRange0.getFirst() <= 0x80000000 ? random0.nextInt() : random0.nextInt(intRange0.getFirst() - 1, intRange0.getLast()) + 1;
    }

    public static final long nextLong(Random random0, LongRange longRange0) {
        Intrinsics.checkNotNullParameter(random0, "<this>");
        Intrinsics.checkNotNullParameter(longRange0, "range");
        if(longRange0.isEmpty()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + longRange0);
        }
        if(longRange0.getLast() < 0x7FFFFFFFFFFFFFFFL) {
            return random0.nextLong(longRange0.getFirst(), longRange0.getLast() + 1L);
        }
        return longRange0.getFirst() <= 0x8000000000000000L ? random0.nextLong() : random0.nextLong(longRange0.getFirst() - 1L, longRange0.getLast()) + 1L;
    }

    public static final int takeUpperBits(int v, int v1) {
        return v >>> 0x20 - v1 & -v1 >> 0x1F;
    }
}

