package kotlin.time;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001A\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001B\u0010\u000B\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\nJ\b\u0010\r\u001A\u00020\u0004H\u0014R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000E"}, d2 = {"Lkotlin/time/TestTimeSource;", "Lkotlin/time/AbstractLongTimeSource;", "()V", "reading", "", "overflow", "", "duration", "Lkotlin/time/Duration;", "overflow-LRDsOJo", "(J)V", "plusAssign", "plusAssign-LRDsOJo", "read", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class TestTimeSource extends AbstractLongTimeSource {
    private long reading;

    public TestTimeSource() {
        super(DurationUnit.NANOSECONDS);
        this.markNow();
    }

    private final void overflow-LRDsOJo(long v) {
        throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.reading + DurationUnitKt.shortName(this.getUnit()) + " is advanced by " + Duration.toString-impl(v) + '.');
    }

    public final void plusAssign-LRDsOJo(long v) {
        long v3;
        long v1 = Duration.toLong-impl(v, this.getUnit());
        if((v1 - 1L | 1L) == 0x7FFFFFFFFFFFFFFFL) {
            long v2 = Duration.div-UwyO8pc(v, 2);
            if((1L | Duration.toLong-impl(v2, this.getUnit()) - 1L) == 0x7FFFFFFFFFFFFFFFL) {
                this.overflow-LRDsOJo(v);
                return;
            }
            try {
                v3 = this.reading;
                this.plusAssign-LRDsOJo(v2);
                this.plusAssign-LRDsOJo(Duration.minus-LRDsOJo(v, v2));
                return;
            }
            catch(IllegalStateException illegalStateException0) {
                this.reading = v3;
                throw illegalStateException0;
            }
        }
        long v4 = this.reading + v1;
        if((v1 ^ this.reading) >= 0L && (this.reading ^ v4) < 0L) {
            this.overflow-LRDsOJo(v);
        }
        this.reading = v4;
    }

    @Override  // kotlin.time.AbstractLongTimeSource
    protected long read() {
        return this.reading;
    }
}

