package kotlin;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001A\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001A\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001A\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001A\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\t\u001A\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\nH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u000B\u001A\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\fH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000E"}, d2 = {"toULong", "Lkotlin/ULong;", "", "(B)J", "", "(D)J", "", "(F)J", "", "(I)J", "", "(J)J", "", "(S)J", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class ULongKt {
    private static final long toULong(byte b) {
        return (long)b;
    }

    private static final long toULong(double f) {
        return UnsignedKt.doubleToULong(f);
    }

    private static final long toULong(float f) {
        return UnsignedKt.doubleToULong(f);
    }

    private static final long toULong(int v) {
        return (long)v;
    }

    private static final long toULong(long v) {
        return ULong.constructor-impl(v);
    }

    private static final long toULong(short v) {
        return (long)v;
    }
}

