package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0081@\u0018\u0000*\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u0014\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0003ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u001A\u0010\u0010\u001A\u00020\b2\b\u0010\u0011\u001A\u0004\u0018\u00010\u0003HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001A\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001A\u00020\u0019HÖ\u0001¢\u0006\u0004\b\u001A\u0010\u001BR\u0011\u0010\u0007\u001A\u00020\b8F¢\u0006\u0006\u001A\u0004\b\t\u0010\nR\u0017\u0010\u000B\u001A\u00028\u00008F¢\u0006\f\u0012\u0004\b\f\u0010\r\u001A\u0004\b\u000E\u0010\u000FR\u0010\u0010\u0004\u001A\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0004\u0092\u0001\u0004\u0018\u00010\u0003ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001C"}, d2 = {"Lkotlinx/coroutines/internal/SegmentOrClosed;", "S", "Lkotlinx/coroutines/internal/Segment;", "", "value", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "isClosed", "", "isClosed-impl", "(Ljava/lang/Object;)Z", "segment", "getSegment$annotations", "()V", "getSegment-impl", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/Segment;", "equals", "other", "equals-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/Object;)I", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
@JvmInline
public final class SegmentOrClosed {
    private final Object value;

    private SegmentOrClosed(Object object0) {
        this.value = object0;
    }

    public static final SegmentOrClosed box-impl(Object object0) {
        return new SegmentOrClosed(object0);
    }

    public static Object constructor-impl(Object object0) [...] // Inlined contents

    @Override
    public boolean equals(Object object0) {
        return SegmentOrClosed.equals-impl(this.value, object0);
    }

    // 去混淆评级： 低(20)
    public static boolean equals-impl(Object object0, Object object1) {
        return object1 instanceof SegmentOrClosed ? Intrinsics.areEqual(object0, ((SegmentOrClosed)object1).unbox-impl()) : false;
    }

    public static final boolean equals-impl0(Object object0, Object object1) {
        return Intrinsics.areEqual(object0, object1);
    }

    public static void getSegment$annotations() {
    }

    public static final Segment getSegment-impl(Object object0) {
        if(object0 == ConcurrentLinkedListKt.CLOSED) {
            throw new IllegalStateException("Does not contain segment");
        }
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type S of kotlinx.coroutines.internal.SegmentOrClosed");
        return (Segment)object0;
    }

    @Override
    public int hashCode() {
        return SegmentOrClosed.hashCode-impl(this.value);
    }

    public static int hashCode-impl(Object object0) {
        return object0 == null ? 0 : object0.hashCode();
    }

    public static final boolean isClosed-impl(Object object0) {
        return object0 == ConcurrentLinkedListKt.CLOSED;
    }

    @Override
    public String toString() {
        return "SegmentOrClosed(value=" + this.value + ')';
    }

    public static String toString-impl(Object object0) [...] // Inlined contents

    public final Object unbox-impl() {
        return this.value;
    }
}

