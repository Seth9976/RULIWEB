package kotlin.internal;

import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.UInt;
import kotlin.ULong;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001A*\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001A*\u0010\u0000\u001A\u00020\u00072\u0006\u0010\u0002\u001A\u00020\u00072\u0006\u0010\u0003\u001A\u00020\u00072\u0006\u0010\u0004\u001A\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001A*\u0010\n\u001A\u00020\u00012\u0006\u0010\u000B\u001A\u00020\u00012\u0006\u0010\f\u001A\u00020\u00012\u0006\u0010\r\u001A\u00020\u000EH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000F\u0010\u0006\u001A*\u0010\n\u001A\u00020\u00072\u0006\u0010\u000B\u001A\u00020\u00072\u0006\u0010\f\u001A\u00020\u00072\u0006\u0010\r\u001A\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class UProgressionUtilKt {
    private static final int differenceModulo-WZ9TVnA(int v, int v1, int v2) {
        int v3 = (int)((((long)v) & 0xFFFFFFFFL) % (((long)v2) & 0xFFFFFFFFL));
        int v4 = (int)((((long)v1) & 0xFFFFFFFFL) % (((long)v2) & 0xFFFFFFFFL));
        int v5 = v3 - v4;
        return UByte..ExternalSyntheticBackport0.m$2(v3, v4) < 0 ? v5 + v2 : v5;
    }

    private static final long differenceModulo-sambcqE(long v, long v1, long v2) {
        long v3 = UByte..ExternalSyntheticBackport0.m(v, v2);
        long v4 = UByte..ExternalSyntheticBackport0.m(v1, v2);
        long v5 = v3 - v4;
        return UByte..ExternalSyntheticBackport0.m(v3, v4) < 0 ? v5 + v2 : v5;
    }

    public static final long getProgressionLastElement-7ftBX0g(long v, long v1, long v2) {
        int v3 = Long.compare(v2, 0L);
        if(v3 > 0) {
            return UByte..ExternalSyntheticBackport0.m(v, v1) >= 0 ? v1 : ULong.constructor-impl(v1 - UProgressionUtilKt.differenceModulo-sambcqE(v1, v, v2));
        }
        if(v3 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        }
        return UByte..ExternalSyntheticBackport0.m(v, v1) > 0 ? ULong.constructor-impl(v1 + UProgressionUtilKt.differenceModulo-sambcqE(v, v1, -v2)) : v1;
    }

    public static final int getProgressionLastElement-Nkh28Cs(int v, int v1, int v2) {
        if(v2 > 0) {
            return UByte..ExternalSyntheticBackport0.m$2(v, v1) >= 0 ? v1 : UInt.constructor-impl(v1 - UProgressionUtilKt.differenceModulo-WZ9TVnA(v1, v, v2));
        }
        if(v2 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        }
        return UByte..ExternalSyntheticBackport0.m$2(v, v1) > 0 ? UInt.constructor-impl(v1 + UProgressionUtilKt.differenceModulo-WZ9TVnA(v, v1, -v2)) : v1;
    }
}

