package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.URandomKt;

@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000E\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\n\u001A\u001E\u0010\u0000\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0003\u0010\u0004\u001A\u001E\u0010\u0000\u001A\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001A\u00020\u0005H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0006\u0010\u0007\u001A\u001E\u0010\u0000\u001A\u00020\b*\u00020\b2\u0006\u0010\u0002\u001A\u00020\bH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\t\u0010\n\u001A\u001E\u0010\u0000\u001A\u00020\u000B*\u00020\u000B2\u0006\u0010\u0002\u001A\u00020\u000BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\f\u0010\r\u001A\u001E\u0010\u000E\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u000F\u001A\u00020\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0010\u0010\u0004\u001A\u001E\u0010\u000E\u001A\u00020\u0005*\u00020\u00052\u0006\u0010\u000F\u001A\u00020\u0005H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0011\u0010\u0007\u001A\u001E\u0010\u000E\u001A\u00020\b*\u00020\b2\u0006\u0010\u000F\u001A\u00020\bH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0012\u0010\n\u001A\u001E\u0010\u000E\u001A\u00020\u000B*\u00020\u000B2\u0006\u0010\u000F\u001A\u00020\u000BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0013\u0010\r\u001A&\u0010\u0014\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u000F\u001A\u00020\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0015\u0010\u0016\u001A&\u0010\u0014\u001A\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001A\u00020\u00052\u0006\u0010\u000F\u001A\u00020\u0005H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0017\u0010\u0018\u001A$\u0010\u0014\u001A\u00020\u0005*\u00020\u00052\f\u0010\u0019\u001A\b\u0012\u0004\u0012\u00020\u00050\u001AH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001B\u0010\u001C\u001A&\u0010\u0014\u001A\u00020\b*\u00020\b2\u0006\u0010\u0002\u001A\u00020\b2\u0006\u0010\u000F\u001A\u00020\bH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001D\u0010\u001E\u001A$\u0010\u0014\u001A\u00020\b*\u00020\b2\f\u0010\u0019\u001A\b\u0012\u0004\u0012\u00020\b0\u001AH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001F\u0010 \u001A&\u0010\u0014\u001A\u00020\u000B*\u00020\u000B2\u0006\u0010\u0002\u001A\u00020\u000B2\u0006\u0010\u000F\u001A\u00020\u000BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b!\u0010\"\u001A\u001F\u0010#\u001A\u00020$*\u00020%2\u0006\u0010&\u001A\u00020\u0001H\u0087\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b\'\u0010(\u001A\u001F\u0010#\u001A\u00020$*\u00020%2\b\u0010)\u001A\u0004\u0018\u00010\u0005H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0002\b*\u001A\u001F\u0010#\u001A\u00020$*\u00020%2\u0006\u0010&\u001A\u00020\bH\u0087\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b+\u0010,\u001A\u001F\u0010#\u001A\u00020$*\u00020%2\u0006\u0010&\u001A\u00020\u000BH\u0087\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b-\u0010.\u001A\u001F\u0010#\u001A\u00020$*\u00020/2\u0006\u0010&\u001A\u00020\u0001H\u0087\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b0\u00101\u001A\u001F\u0010#\u001A\u00020$*\u00020/2\u0006\u0010&\u001A\u00020\u0005H\u0087\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b2\u00103\u001A\u001F\u0010#\u001A\u00020$*\u00020/2\b\u0010)\u001A\u0004\u0018\u00010\bH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0002\b4\u001A\u001F\u0010#\u001A\u00020$*\u00020/2\u0006\u0010&\u001A\u00020\u000BH\u0087\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b5\u00106\u001A\u001F\u00107\u001A\u000208*\u00020\u00012\u0006\u00109\u001A\u00020\u0001H\u0087\u0004\u00F8\u0001\u0000\u00A2\u0006\u0004\b:\u0010;\u001A\u001F\u00107\u001A\u000208*\u00020\u00052\u0006\u00109\u001A\u00020\u0005H\u0087\u0004\u00F8\u0001\u0000\u00A2\u0006\u0004\b<\u0010=\u001A\u001F\u00107\u001A\u00020>*\u00020\b2\u0006\u00109\u001A\u00020\bH\u0087\u0004\u00F8\u0001\u0000\u00A2\u0006\u0004\b?\u0010@\u001A\u001F\u00107\u001A\u000208*\u00020\u000B2\u0006\u00109\u001A\u00020\u000BH\u0087\u0004\u00F8\u0001\u0000\u00A2\u0006\u0004\bA\u0010B\u001A\u0014\u0010C\u001A\u00020\u0005*\u000208H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010D\u001A\u0014\u0010C\u001A\u00020\b*\u00020>H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010E\u001A\u0011\u0010F\u001A\u0004\u0018\u00010\u0005*\u000208H\u0007\u00F8\u0001\u0000\u001A\u0011\u0010F\u001A\u0004\u0018\u00010\b*\u00020>H\u0007\u00F8\u0001\u0000\u001A\u0014\u0010G\u001A\u00020\u0005*\u000208H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010D\u001A\u0014\u0010G\u001A\u00020\b*\u00020>H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010E\u001A\u0011\u0010H\u001A\u0004\u0018\u00010\u0005*\u000208H\u0007\u00F8\u0001\u0000\u001A\u0011\u0010H\u001A\u0004\u0018\u00010\b*\u00020>H\u0007\u00F8\u0001\u0000\u001A\u0015\u0010I\u001A\u00020\u0005*\u00020%H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010J\u001A\u001C\u0010I\u001A\u00020\u0005*\u00020%2\u0006\u0010I\u001A\u00020KH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010L\u001A\u0015\u0010I\u001A\u00020\b*\u00020/H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010M\u001A\u001C\u0010I\u001A\u00020\b*\u00020/2\u0006\u0010I\u001A\u00020KH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010N\u001A\u0012\u0010O\u001A\u0004\u0018\u00010\u0005*\u00020%H\u0087\b\u00F8\u0001\u0000\u001A\u0019\u0010O\u001A\u0004\u0018\u00010\u0005*\u00020%2\u0006\u0010I\u001A\u00020KH\u0007\u00F8\u0001\u0000\u001A\u0012\u0010O\u001A\u0004\u0018\u00010\b*\u00020/H\u0087\b\u00F8\u0001\u0000\u001A\u0019\u0010O\u001A\u0004\u0018\u00010\b*\u00020/2\u0006\u0010I\u001A\u00020KH\u0007\u00F8\u0001\u0000\u001A\f\u0010P\u001A\u000208*\u000208H\u0007\u001A\f\u0010P\u001A\u00020>*\u00020>H\u0007\u001A\u0015\u0010Q\u001A\u000208*\u0002082\u0006\u0010Q\u001A\u00020RH\u0087\u0004\u001A\u0015\u0010Q\u001A\u00020>*\u00020>2\u0006\u0010Q\u001A\u00020SH\u0087\u0004\u001A\u001F\u0010T\u001A\u00020%*\u00020\u00012\u0006\u00109\u001A\u00020\u0001H\u0087\u0004\u00F8\u0001\u0000\u00A2\u0006\u0004\bU\u0010V\u001A\u001F\u0010T\u001A\u00020%*\u00020\u00052\u0006\u00109\u001A\u00020\u0005H\u0087\u0004\u00F8\u0001\u0000\u00A2\u0006\u0004\bW\u0010X\u001A\u001F\u0010T\u001A\u00020/*\u00020\b2\u0006\u00109\u001A\u00020\bH\u0087\u0004\u00F8\u0001\u0000\u00A2\u0006\u0004\bY\u0010Z\u001A\u001F\u0010T\u001A\u00020%*\u00020\u000B2\u0006\u00109\u001A\u00020\u000BH\u0087\u0004\u00F8\u0001\u0000\u00A2\u0006\u0004\b[\u0010\\\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006]"}, d2 = {"coerceAtLeast", "Lkotlin/UByte;", "minimumValue", "coerceAtLeast-Kr8caGY", "(BB)B", "Lkotlin/UInt;", "coerceAtLeast-J1ME1BU", "(II)I", "Lkotlin/ULong;", "coerceAtLeast-eb3DHEI", "(JJ)J", "Lkotlin/UShort;", "coerceAtLeast-5PvTz6A", "(SS)S", "coerceAtMost", "maximumValue", "coerceAtMost-Kr8caGY", "coerceAtMost-J1ME1BU", "coerceAtMost-eb3DHEI", "coerceAtMost-5PvTz6A", "coerceIn", "coerceIn-b33U2AM", "(BBB)B", "coerceIn-WZ9TVnA", "(III)I", "range", "Lkotlin/ranges/ClosedRange;", "coerceIn-wuiCnnA", "(ILkotlin/ranges/ClosedRange;)I", "coerceIn-sambcqE", "(JJJ)J", "coerceIn-JPwROB0", "(JLkotlin/ranges/ClosedRange;)J", "coerceIn-VKSA0NQ", "(SSS)S", "contains", "", "Lkotlin/ranges/UIntRange;", "value", "contains-68kG9v0", "(Lkotlin/ranges/UIntRange;B)Z", "element", "contains-biwQdVI", "contains-fz5IDCE", "(Lkotlin/ranges/UIntRange;J)Z", "contains-ZsK3CEQ", "(Lkotlin/ranges/UIntRange;S)Z", "Lkotlin/ranges/ULongRange;", "contains-ULb-yJY", "(Lkotlin/ranges/ULongRange;B)Z", "contains-Gab390E", "(Lkotlin/ranges/ULongRange;I)Z", "contains-GYNo2lE", "contains-uhHAxoY", "(Lkotlin/ranges/ULongRange;S)Z", "downTo", "Lkotlin/ranges/UIntProgression;", "to", "downTo-Kr8caGY", "(BB)Lkotlin/ranges/UIntProgression;", "downTo-J1ME1BU", "(II)Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ULongProgression;", "downTo-eb3DHEI", "(JJ)Lkotlin/ranges/ULongProgression;", "downTo-5PvTz6A", "(SS)Lkotlin/ranges/UIntProgression;", "first", "(Lkotlin/ranges/UIntProgression;)I", "(Lkotlin/ranges/ULongProgression;)J", "firstOrNull", "last", "lastOrNull", "random", "(Lkotlin/ranges/UIntRange;)I", "Lkotlin/random/Random;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/ULongRange;)J", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)J", "randomOrNull", "reversed", "step", "", "", "until", "until-Kr8caGY", "(BB)Lkotlin/ranges/UIntRange;", "until-J1ME1BU", "(II)Lkotlin/ranges/UIntRange;", "until-eb3DHEI", "(JJ)Lkotlin/ranges/ULongRange;", "until-5PvTz6A", "(SS)Lkotlin/ranges/UIntRange;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/ranges/URangesKt")
class URangesKt___URangesKt {
    public static final short coerceAtLeast-5PvTz6A(short v, short v1) {
        return Intrinsics.compare(v & 0xFFFF, 0xFFFF & v1) >= 0 ? v : v1;
    }

    public static final int coerceAtLeast-J1ME1BU(int v, int v1) {
        return UByte..ExternalSyntheticBackport0.m$2(v, v1) >= 0 ? v : v1;
    }

    public static final byte coerceAtLeast-Kr8caGY(byte b, byte b1) {
        return Intrinsics.compare(b & 0xFF, b1 & 0xFF) >= 0 ? b : b1;
    }

    public static final long coerceAtLeast-eb3DHEI(long v, long v1) {
        return UByte..ExternalSyntheticBackport0.m(v, v1) >= 0 ? v : v1;
    }

    public static final short coerceAtMost-5PvTz6A(short v, short v1) {
        return Intrinsics.compare(v & 0xFFFF, 0xFFFF & v1) <= 0 ? v : v1;
    }

    public static final int coerceAtMost-J1ME1BU(int v, int v1) {
        return UByte..ExternalSyntheticBackport0.m$2(v, v1) <= 0 ? v : v1;
    }

    public static final byte coerceAtMost-Kr8caGY(byte b, byte b1) {
        return Intrinsics.compare(b & 0xFF, b1 & 0xFF) <= 0 ? b : b1;
    }

    public static final long coerceAtMost-eb3DHEI(long v, long v1) {
        return UByte..ExternalSyntheticBackport0.m(v, v1) <= 0 ? v : v1;
    }

    public static final long coerceIn-JPwROB0(long v, ClosedRange closedRange0) {
        Intrinsics.checkNotNullParameter(closedRange0, "range");
        if(closedRange0 instanceof ClosedFloatingPointRange) {
            return ((ULong)RangesKt.coerceIn(ULong.box-impl(v), ((ClosedFloatingPointRange)closedRange0))).unbox-impl();
        }
        if(closedRange0.isEmpty()) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange0 + '.');
        }
        if(UByte..ExternalSyntheticBackport0.m(v, ((ULong)closedRange0.getStart()).unbox-impl()) < 0) {
            return ((ULong)closedRange0.getStart()).unbox-impl();
        }
        return UByte..ExternalSyntheticBackport0.m(v, ((ULong)closedRange0.getEndInclusive()).unbox-impl()) <= 0 ? v : ((ULong)closedRange0.getEndInclusive()).unbox-impl();
    }

    public static final short coerceIn-VKSA0NQ(short v, short v1, short v2) {
        if(Intrinsics.compare(v1 & 0xFFFF, v2 & 0xFFFF) > 0) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UShort.toString-impl(v2) + " is less than minimum " + UShort.toString-impl(v1) + '.');
        }
        if(Intrinsics.compare(0xFFFF & v, v1 & 0xFFFF) < 0) {
            return v1;
        }
        return Intrinsics.compare(0xFFFF & v, v2 & 0xFFFF) <= 0 ? v : v2;
    }

    public static final int coerceIn-WZ9TVnA(int v, int v1, int v2) {
        if(UByte..ExternalSyntheticBackport0.m$2(v1, v2) > 0) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UInt.toString-impl(v2) + " is less than minimum " + UInt.toString-impl(v1) + '.');
        }
        if(UByte..ExternalSyntheticBackport0.m$2(v, v1) < 0) {
            return v1;
        }
        return UByte..ExternalSyntheticBackport0.m$2(v, v2) <= 0 ? v : v2;
    }

    public static final byte coerceIn-b33U2AM(byte b, byte b1, byte b2) {
        if(Intrinsics.compare(b1 & 0xFF, b2 & 0xFF) > 0) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UByte.toString-impl(b2) + " is less than minimum " + UByte.toString-impl(b1) + '.');
        }
        if(Intrinsics.compare(b & 0xFF, b1 & 0xFF) < 0) {
            return b1;
        }
        return Intrinsics.compare(b & 0xFF, b2 & 0xFF) <= 0 ? b : b2;
    }

    public static final long coerceIn-sambcqE(long v, long v1, long v2) {
        if(UByte..ExternalSyntheticBackport0.m(v1, v2) > 0) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + ULong.toString-impl(v2) + " is less than minimum " + ULong.toString-impl(v1) + '.');
        }
        if(UByte..ExternalSyntheticBackport0.m(v, v1) < 0) {
            return v1;
        }
        return UByte..ExternalSyntheticBackport0.m(v, v2) <= 0 ? v : v2;
    }

    public static final int coerceIn-wuiCnnA(int v, ClosedRange closedRange0) {
        Intrinsics.checkNotNullParameter(closedRange0, "range");
        if(closedRange0 instanceof ClosedFloatingPointRange) {
            return ((UInt)RangesKt.coerceIn(UInt.box-impl(v), ((ClosedFloatingPointRange)closedRange0))).unbox-impl();
        }
        if(closedRange0.isEmpty()) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange0 + '.');
        }
        if(UByte..ExternalSyntheticBackport0.m$2(v, ((UInt)closedRange0.getStart()).unbox-impl()) < 0) {
            return ((UInt)closedRange0.getStart()).unbox-impl();
        }
        return UByte..ExternalSyntheticBackport0.m$2(v, ((UInt)closedRange0.getEndInclusive()).unbox-impl()) <= 0 ? v : ((UInt)closedRange0.getEndInclusive()).unbox-impl();
    }

    public static final boolean contains-68kG9v0(UIntRange uIntRange0, byte b) {
        Intrinsics.checkNotNullParameter(uIntRange0, "$this$contains");
        return uIntRange0.contains-WZ4Q5Ns(b & 0xFF);
    }

    private static final boolean contains-GYNo2lE(ULongRange uLongRange0, ULong uLong0) {
        Intrinsics.checkNotNullParameter(uLongRange0, "$this$contains");
        return uLong0 != null && uLongRange0.contains-VKZWuLQ(uLong0.unbox-impl());
    }

    public static final boolean contains-Gab390E(ULongRange uLongRange0, int v) {
        Intrinsics.checkNotNullParameter(uLongRange0, "$this$contains");
        return uLongRange0.contains-VKZWuLQ(((long)v) & 0xFFFFFFFFL);
    }

    public static final boolean contains-ULb-yJY(ULongRange uLongRange0, byte b) {
        Intrinsics.checkNotNullParameter(uLongRange0, "$this$contains");
        return uLongRange0.contains-VKZWuLQ(((long)b) & 0xFFL);
    }

    public static final boolean contains-ZsK3CEQ(UIntRange uIntRange0, short v) {
        Intrinsics.checkNotNullParameter(uIntRange0, "$this$contains");
        return uIntRange0.contains-WZ4Q5Ns(v & 0xFFFF);
    }

    private static final boolean contains-biwQdVI(UIntRange uIntRange0, UInt uInt0) {
        Intrinsics.checkNotNullParameter(uIntRange0, "$this$contains");
        return uInt0 != null && uIntRange0.contains-WZ4Q5Ns(uInt0.unbox-impl());
    }

    public static final boolean contains-fz5IDCE(UIntRange uIntRange0, long v) {
        Intrinsics.checkNotNullParameter(uIntRange0, "$this$contains");
        return v >>> 0x20 == 0L && uIntRange0.contains-WZ4Q5Ns(((int)v));
    }

    public static final boolean contains-uhHAxoY(ULongRange uLongRange0, short v) {
        Intrinsics.checkNotNullParameter(uLongRange0, "$this$contains");
        return uLongRange0.contains-VKZWuLQ(((long)v) & 0xFFFFL);
    }

    public static final UIntProgression downTo-5PvTz6A(short v, short v1) {
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs(v & 0xFFFF, v1 & 0xFFFF, -1);
    }

    public static final UIntProgression downTo-J1ME1BU(int v, int v1) {
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs(v, v1, -1);
    }

    public static final UIntProgression downTo-Kr8caGY(byte b, byte b1) {
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs(b & 0xFF, b1 & 0xFF, -1);
    }

    public static final ULongProgression downTo-eb3DHEI(long v, long v1) {
        return ULongProgression.Companion.fromClosedRange-7ftBX0g(v, v1, -1L);
    }

    public static final int first(UIntProgression uIntProgression0) {
        Intrinsics.checkNotNullParameter(uIntProgression0, "<this>");
        if(uIntProgression0.isEmpty()) {
            throw new NoSuchElementException("Progression " + uIntProgression0 + " is empty.");
        }
        return uIntProgression0.getFirst-pVg5ArA();
    }

    public static final long first(ULongProgression uLongProgression0) {
        Intrinsics.checkNotNullParameter(uLongProgression0, "<this>");
        if(uLongProgression0.isEmpty()) {
            throw new NoSuchElementException("Progression " + uLongProgression0 + " is empty.");
        }
        return uLongProgression0.getFirst-s-VKNKU();
    }

    public static final UInt firstOrNull(UIntProgression uIntProgression0) {
        Intrinsics.checkNotNullParameter(uIntProgression0, "<this>");
        return uIntProgression0.isEmpty() ? null : UInt.box-impl(uIntProgression0.getFirst-pVg5ArA());
    }

    public static final ULong firstOrNull(ULongProgression uLongProgression0) {
        Intrinsics.checkNotNullParameter(uLongProgression0, "<this>");
        return uLongProgression0.isEmpty() ? null : ULong.box-impl(uLongProgression0.getFirst-s-VKNKU());
    }

    public static final int last(UIntProgression uIntProgression0) {
        Intrinsics.checkNotNullParameter(uIntProgression0, "<this>");
        if(uIntProgression0.isEmpty()) {
            throw new NoSuchElementException("Progression " + uIntProgression0 + " is empty.");
        }
        return uIntProgression0.getLast-pVg5ArA();
    }

    public static final long last(ULongProgression uLongProgression0) {
        Intrinsics.checkNotNullParameter(uLongProgression0, "<this>");
        if(uLongProgression0.isEmpty()) {
            throw new NoSuchElementException("Progression " + uLongProgression0 + " is empty.");
        }
        return uLongProgression0.getLast-s-VKNKU();
    }

    public static final UInt lastOrNull(UIntProgression uIntProgression0) {
        Intrinsics.checkNotNullParameter(uIntProgression0, "<this>");
        return uIntProgression0.isEmpty() ? null : UInt.box-impl(uIntProgression0.getLast-pVg5ArA());
    }

    public static final ULong lastOrNull(ULongProgression uLongProgression0) {
        Intrinsics.checkNotNullParameter(uLongProgression0, "<this>");
        return uLongProgression0.isEmpty() ? null : ULong.box-impl(uLongProgression0.getLast-s-VKNKU());
    }

    private static final int random(UIntRange uIntRange0) {
        Intrinsics.checkNotNullParameter(uIntRange0, "<this>");
        return URangesKt.random(uIntRange0, Random.Default);
    }

    public static final int random(UIntRange uIntRange0, Random random0) {
        Intrinsics.checkNotNullParameter(uIntRange0, "<this>");
        Intrinsics.checkNotNullParameter(random0, "random");
        try {
            return URandomKt.nextUInt(random0, uIntRange0);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new NoSuchElementException(illegalArgumentException0.getMessage());
        }
    }

    private static final long random(ULongRange uLongRange0) {
        Intrinsics.checkNotNullParameter(uLongRange0, "<this>");
        return URangesKt.random(uLongRange0, Random.Default);
    }

    public static final long random(ULongRange uLongRange0, Random random0) {
        Intrinsics.checkNotNullParameter(uLongRange0, "<this>");
        Intrinsics.checkNotNullParameter(random0, "random");
        try {
            return URandomKt.nextULong(random0, uLongRange0);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new NoSuchElementException(illegalArgumentException0.getMessage());
        }
    }

    private static final UInt randomOrNull(UIntRange uIntRange0) {
        Intrinsics.checkNotNullParameter(uIntRange0, "<this>");
        return URangesKt.randomOrNull(uIntRange0, Random.Default);
    }

    public static final UInt randomOrNull(UIntRange uIntRange0, Random random0) {
        Intrinsics.checkNotNullParameter(uIntRange0, "<this>");
        Intrinsics.checkNotNullParameter(random0, "random");
        return uIntRange0.isEmpty() ? null : UInt.box-impl(URandomKt.nextUInt(random0, uIntRange0));
    }

    private static final ULong randomOrNull(ULongRange uLongRange0) {
        Intrinsics.checkNotNullParameter(uLongRange0, "<this>");
        return URangesKt.randomOrNull(uLongRange0, Random.Default);
    }

    public static final ULong randomOrNull(ULongRange uLongRange0, Random random0) {
        Intrinsics.checkNotNullParameter(uLongRange0, "<this>");
        Intrinsics.checkNotNullParameter(random0, "random");
        return uLongRange0.isEmpty() ? null : ULong.box-impl(URandomKt.nextULong(random0, uLongRange0));
    }

    public static final UIntProgression reversed(UIntProgression uIntProgression0) {
        Intrinsics.checkNotNullParameter(uIntProgression0, "<this>");
        return UIntProgression.Companion.fromClosedRange-Nkh28Cs(uIntProgression0.getLast-pVg5ArA(), uIntProgression0.getFirst-pVg5ArA(), -uIntProgression0.getStep());
    }

    public static final ULongProgression reversed(ULongProgression uLongProgression0) {
        Intrinsics.checkNotNullParameter(uLongProgression0, "<this>");
        return ULongProgression.Companion.fromClosedRange-7ftBX0g(uLongProgression0.getLast-s-VKNKU(), uLongProgression0.getFirst-s-VKNKU(), -uLongProgression0.getStep());
    }

    public static final UIntProgression step(UIntProgression uIntProgression0, int v) {
        Intrinsics.checkNotNullParameter(uIntProgression0, "<this>");
        RangesKt.checkStepIsPositive(v > 0, v);
        Companion uIntProgression$Companion0 = UIntProgression.Companion;
        int v1 = uIntProgression0.getFirst-pVg5ArA();
        int v2 = uIntProgression0.getLast-pVg5ArA();
        if(uIntProgression0.getStep() <= 0) {
            v = -v;
        }
        return uIntProgression$Companion0.fromClosedRange-Nkh28Cs(v1, v2, v);
    }

    public static final ULongProgression step(ULongProgression uLongProgression0, long v) {
        Intrinsics.checkNotNullParameter(uLongProgression0, "<this>");
        RangesKt.checkStepIsPositive(v > 0L, v);
        kotlin.ranges.ULongProgression.Companion uLongProgression$Companion0 = ULongProgression.Companion;
        long v1 = uLongProgression0.getFirst-s-VKNKU();
        long v2 = uLongProgression0.getLast-s-VKNKU();
        if(uLongProgression0.getStep() <= 0L) {
            v = -v;
        }
        return uLongProgression$Companion0.fromClosedRange-7ftBX0g(v1, v2, v);
    }

    public static final UIntRange until-5PvTz6A(short v, short v1) {
        return Intrinsics.compare(v1 & 0xFFFF, 0) > 0 ? new UIntRange(v & 0xFFFF, (v1 & 0xFFFF) - 1, null) : UIntRange.Companion.getEMPTY();
    }

    public static final UIntRange until-J1ME1BU(int v, int v1) {
        return UByte..ExternalSyntheticBackport0.m$2(v1, 0) > 0 ? new UIntRange(v, v1 - 1, null) : UIntRange.Companion.getEMPTY();
    }

    public static final UIntRange until-Kr8caGY(byte b, byte b1) {
        return Intrinsics.compare(b1 & 0xFF, 0) > 0 ? new UIntRange(b & 0xFF, (b1 & 0xFF) - 1, null) : UIntRange.Companion.getEMPTY();
    }

    public static final ULongRange until-eb3DHEI(long v, long v1) {
        return UByte..ExternalSyntheticBackport0.m(v1, 0L) > 0 ? new ULongRange(v, v1 - 1L, null) : ULongRange.Companion.getEMPTY();
    }
}

