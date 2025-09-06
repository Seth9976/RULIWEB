package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B\u0018\u0012\u0006\u0010\u0002\u001A\u00020\u0001\u0012\u0006\u0010\u0003\u001A\u00020\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u0015\u0010\u000B\u001A\u00020\u0004H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u0007J\u001B\u0010\r\u001A\u00020\u00012\u0006\u0010\u000E\u001A\u00020\u0004H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u000F\u0010\u0010R\u0019\u0010\u0003\u001A\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001A\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001A\u00020\u0001¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0011"}, d2 = {"Lkotlin/time/AdjustedTimeMark;", "Lkotlin/time/TimeMark;", "mark", "adjustment", "Lkotlin/time/Duration;", "(Lkotlin/time/TimeMark;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAdjustment-UwyO8pc", "()J", "J", "getMark", "()Lkotlin/time/TimeMark;", "elapsedNow", "elapsedNow-UwyO8pc", "plus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/TimeMark;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class AdjustedTimeMark implements TimeMark {
    private final long adjustment;
    private final TimeMark mark;

    private AdjustedTimeMark(TimeMark timeMark0, long v) {
        Intrinsics.checkNotNullParameter(timeMark0, "mark");
        super();
        this.mark = timeMark0;
        this.adjustment = v;
    }

    public AdjustedTimeMark(TimeMark timeMark0, long v, DefaultConstructorMarker defaultConstructorMarker0) {
        this(timeMark0, v);
    }

    @Override  // kotlin.time.TimeMark
    public long elapsedNow-UwyO8pc() {
        return Duration.minus-LRDsOJo(this.mark.elapsedNow-UwyO8pc(), this.adjustment);
    }

    public final long getAdjustment-UwyO8pc() {
        return this.adjustment;
    }

    public final TimeMark getMark() {
        return this.mark;
    }

    @Override  // kotlin.time.TimeMark
    public boolean hasNotPassedNow() {
        return DefaultImpls.hasNotPassedNow(this);
    }

    @Override  // kotlin.time.TimeMark
    public boolean hasPassedNow() {
        return DefaultImpls.hasPassedNow(this);
    }

    @Override  // kotlin.time.TimeMark
    public TimeMark minus-LRDsOJo(long v) {
        return DefaultImpls.minus-LRDsOJo(this, v);
    }

    @Override  // kotlin.time.TimeMark
    public TimeMark plus-LRDsOJo(long v) {
        long v1 = Duration.plus-LRDsOJo(this.adjustment, v);
        return new AdjustedTimeMark(this.mark, v1, null);
    }
}

