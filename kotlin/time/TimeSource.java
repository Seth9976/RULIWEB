package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000 \u00042\u00020\u0001:\u0003\u0004\u0005\u0006J\b\u0010\u0002\u001A\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lkotlin/time/TimeSource;", "", "markNow", "Lkotlin/time/TimeMark;", "Companion", "Monotonic", "WithComparableMarks", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public interface TimeSource {
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lkotlin/time/TimeSource$Companion;", "", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        static final Companion $$INSTANCE;

        static {
            Companion.$$INSTANCE = new Companion();
        }
    }

    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001A\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001A\u00020\bH\u0016\u0082\u0002\b\n\u0002\b!\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/time/TimeSource$Monotonic;", "Lkotlin/time/TimeSource$WithComparableMarks;", "()V", "markNow", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "markNow-z9LOYto", "()J", "toString", "", "ValueTimeMark", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Monotonic implements WithComparableMarks {
        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b\u0014\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u0018\b\u0000\u0012\n\u0010\u0002\u001A\u00060\u0003j\u0002`\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u001B\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000BJ\u0015\u0010\f\u001A\u00020\rH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u000E\u0010\u0006J\u001A\u0010\u000F\u001A\u00020\u00102\b\u0010\t\u001A\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u000F\u0010\u0014\u001A\u00020\u0010H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000F\u0010\u0017\u001A\u00020\u0010H\u0016¢\u0006\u0004\b\u0018\u0010\u0016J\u0010\u0010\u0019\u001A\u00020\bHÖ\u0001¢\u0006\u0004\b\u001A\u0010\u001BJ\u001E\u0010\u001C\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u0001H\u0096\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001D\u0010\u001EJ\u001B\u0010\u001C\u001A\u00020\u00002\u0006\u0010\u001F\u001A\u00020\rH\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b \u0010!J\u001B\u0010\u001C\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\"\u0010!J\u001B\u0010#\u001A\u00020\u00002\u0006\u0010\u001F\u001A\u00020\rH\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b$\u0010!J\u0010\u0010%\u001A\u00020&HÖ\u0001¢\u0006\u0004\b\'\u0010(R\u0012\u0010\u0002\u001A\u00060\u0003j\u0002`\u0004X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00060\u0003j\u0002`\u0004ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006)"}, d2 = {"Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "Lkotlin/time/ComparableTimeMark;", "reading", "", "Lkotlin/time/ValueTimeMarkReading;", "constructor-impl", "(J)J", "compareTo", "", "other", "compareTo-6eNON_k", "(JJ)I", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "hasNotPassedNow", "hasNotPassedNow-impl", "(J)Z", "hasPassedNow", "hasPassedNow-impl", "hashCode", "hashCode-impl", "(J)I", "minus", "minus-UwyO8pc", "(JLkotlin/time/ComparableTimeMark;)J", "duration", "minus-LRDsOJo", "(JJ)J", "minus-6eNON_k", "plus", "plus-LRDsOJo", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        @JvmInline
        public static final class ValueTimeMark implements ComparableTimeMark {
            private final long reading;

            private ValueTimeMark(long v) {
                this.reading = v;
            }

            public static final ValueTimeMark box-impl(long v) {
                return new ValueTimeMark(v);
            }

            @Override
            public int compareTo(Object object0) {
                return this.compareTo(((ComparableTimeMark)object0));
            }

            @Override  // kotlin.time.ComparableTimeMark
            public int compareTo(ComparableTimeMark comparableTimeMark0) {
                return DefaultImpls.compareTo(this, comparableTimeMark0);
            }

            // 去混淆评级： 低(20)
            public static final int compareTo-6eNON_k(long v, long v1) {
                return Duration.compareTo-LRDsOJo(ValueTimeMark.minus-6eNON_k(v, v1), 0L);
            }

            public static int compareTo-impl(long v, ComparableTimeMark comparableTimeMark0) {
                Intrinsics.checkNotNullParameter(comparableTimeMark0, "other");
                return ValueTimeMark.box-impl(v).compareTo(comparableTimeMark0);
            }

            public static long constructor-impl(long v) [...] // Inlined contents

            public static long elapsedNow-UwyO8pc(long v) {
                return MonotonicTimeSource.INSTANCE.elapsedFrom-6eNON_k(v);
            }

            @Override  // kotlin.time.TimeMark
            public long elapsedNow-UwyO8pc() {
                return ValueTimeMark.elapsedNow-UwyO8pc(this.reading);
            }

            @Override  // kotlin.time.ComparableTimeMark
            public boolean equals(Object object0) {
                return ValueTimeMark.equals-impl(this.reading, object0);
            }

            public static boolean equals-impl(long v, Object object0) {
                return object0 instanceof ValueTimeMark ? v == ((ValueTimeMark)object0).unbox-impl() : false;
            }

            public static final boolean equals-impl0(long v, long v1) {
                return v == v1;
            }

            @Override  // kotlin.time.TimeMark
            public boolean hasNotPassedNow() {
                return ValueTimeMark.hasNotPassedNow-impl(this.reading);
            }

            public static boolean hasNotPassedNow-impl(long v) {
                return Duration.isNegative-impl(ValueTimeMark.elapsedNow-UwyO8pc(v));
            }

            @Override  // kotlin.time.TimeMark
            public boolean hasPassedNow() {
                return ValueTimeMark.hasPassedNow-impl(this.reading);
            }

            public static boolean hasPassedNow-impl(long v) {
                return !Duration.isNegative-impl(ValueTimeMark.elapsedNow-UwyO8pc(v));
            }

            @Override  // kotlin.time.ComparableTimeMark
            public int hashCode() {
                return ValueTimeMark.hashCode-impl(this.reading);
            }

            public static int hashCode-impl(long v) {
                return (int)(v ^ v >>> 0x20);
            }

            public static final long minus-6eNON_k(long v, long v1) {
                return MonotonicTimeSource.INSTANCE.differenceBetween-fRLX17w(v, v1);
            }

            public static long minus-LRDsOJo(long v, long v1) {
                long v2 = Duration.unaryMinus-UwyO8pc(v1);
                return MonotonicTimeSource.INSTANCE.adjustReading-6QKq23U(v, v2);
            }

            public long minus-LRDsOJo(long v) {
                return ValueTimeMark.minus-LRDsOJo(this.reading, v);
            }

            @Override  // kotlin.time.ComparableTimeMark
            public ComparableTimeMark minus-LRDsOJo(long v) {
                return ValueTimeMark.box-impl(this.minus-LRDsOJo(v));
            }

            @Override  // kotlin.time.TimeMark
            public TimeMark minus-LRDsOJo(long v) {
                return ValueTimeMark.box-impl(this.minus-LRDsOJo(v));
            }

            public static long minus-UwyO8pc(long v, ComparableTimeMark comparableTimeMark0) {
                Intrinsics.checkNotNullParameter(comparableTimeMark0, "other");
                if(!(comparableTimeMark0 instanceof ValueTimeMark)) {
                    throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + ValueTimeMark.toString-impl(v) + " and " + comparableTimeMark0);
                }
                return ValueTimeMark.minus-6eNON_k(v, ((ValueTimeMark)comparableTimeMark0).unbox-impl());
            }

            @Override  // kotlin.time.ComparableTimeMark
            public long minus-UwyO8pc(ComparableTimeMark comparableTimeMark0) {
                Intrinsics.checkNotNullParameter(comparableTimeMark0, "other");
                return ValueTimeMark.minus-UwyO8pc(this.reading, comparableTimeMark0);
            }

            public static long plus-LRDsOJo(long v, long v1) {
                return MonotonicTimeSource.INSTANCE.adjustReading-6QKq23U(v, v1);
            }

            public long plus-LRDsOJo(long v) {
                return ValueTimeMark.plus-LRDsOJo(this.reading, v);
            }

            @Override  // kotlin.time.ComparableTimeMark
            public ComparableTimeMark plus-LRDsOJo(long v) {
                return ValueTimeMark.box-impl(this.plus-LRDsOJo(v));
            }

            @Override  // kotlin.time.TimeMark
            public TimeMark plus-LRDsOJo(long v) {
                return ValueTimeMark.box-impl(this.plus-LRDsOJo(v));
            }

            @Override
            public String toString() {
                return ValueTimeMark.toString-impl(this.reading);
            }

            public static String toString-impl(long v) {
                return "ValueTimeMark(reading=" + v + ')';
            }

            public final long unbox-impl() {
                return this.reading;
            }
        }

        public static final Monotonic INSTANCE;

        static {
            Monotonic.INSTANCE = new Monotonic();
        }

        // 去混淆评级： 低(20)
        @Override  // kotlin.time.TimeSource$WithComparableMarks
        public ComparableTimeMark markNow() {
            return ValueTimeMark.box-impl(3513500L);
        }

        // 去混淆评级： 低(20)
        @Override  // kotlin.time.TimeSource
        public TimeMark markNow() {
            return ValueTimeMark.box-impl(3886700L);
        }

        // 去混淆评级： 低(20)
        public long markNow-z9LOYto() [...] // 潜在的解密器

        @Override
        public String toString() {
            return "TimeSource(System.nanoTime())";
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001A\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lkotlin/time/TimeSource$WithComparableMarks;", "Lkotlin/time/TimeSource;", "markNow", "Lkotlin/time/ComparableTimeMark;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public interface WithComparableMarks extends TimeSource {
        ComparableTimeMark markNow();
    }

    public static final Companion Companion;

    static {
        TimeSource.Companion = Companion.$$INSTANCE;
    }

    TimeMark markNow();
}

