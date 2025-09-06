package kotlin.comparisons;

import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\u001A\"\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0004\u0010\u0005\u001A+\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0006\u001A\u00020\u0001H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0007\u0010\b\u001A&\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\n\u0010\t\u001A\u00020\n\"\u00020\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u000B\u0010\f\u001A\"\u0010\u0000\u001A\u00020\r2\u0006\u0010\u0002\u001A\u00020\r2\u0006\u0010\u0003\u001A\u00020\rH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u000E\u0010\u000F\u001A+\u0010\u0000\u001A\u00020\r2\u0006\u0010\u0002\u001A\u00020\r2\u0006\u0010\u0003\u001A\u00020\r2\u0006\u0010\u0006\u001A\u00020\rH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0010\u0010\u0011\u001A&\u0010\u0000\u001A\u00020\r2\u0006\u0010\u0002\u001A\u00020\r2\n\u0010\t\u001A\u00020\u0012\"\u00020\rH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0013\u0010\u0014\u001A\"\u0010\u0000\u001A\u00020\u00152\u0006\u0010\u0002\u001A\u00020\u00152\u0006\u0010\u0003\u001A\u00020\u0015H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0016\u0010\u0017\u001A+\u0010\u0000\u001A\u00020\u00152\u0006\u0010\u0002\u001A\u00020\u00152\u0006\u0010\u0003\u001A\u00020\u00152\u0006\u0010\u0006\u001A\u00020\u0015H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0018\u0010\u0019\u001A&\u0010\u0000\u001A\u00020\u00152\u0006\u0010\u0002\u001A\u00020\u00152\n\u0010\t\u001A\u00020\u001A\"\u00020\u0015H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001B\u0010\u001C\u001A\"\u0010\u0000\u001A\u00020\u001D2\u0006\u0010\u0002\u001A\u00020\u001D2\u0006\u0010\u0003\u001A\u00020\u001DH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001E\u0010\u001F\u001A+\u0010\u0000\u001A\u00020\u001D2\u0006\u0010\u0002\u001A\u00020\u001D2\u0006\u0010\u0003\u001A\u00020\u001D2\u0006\u0010\u0006\u001A\u00020\u001DH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b \u0010!\u001A&\u0010\u0000\u001A\u00020\u001D2\u0006\u0010\u0002\u001A\u00020\u001D2\n\u0010\t\u001A\u00020\"\"\u00020\u001DH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b#\u0010$\u001A\"\u0010%\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b&\u0010\u0005\u001A+\u0010%\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0006\u001A\u00020\u0001H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b\'\u0010\b\u001A&\u0010%\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\n\u0010\t\u001A\u00020\n\"\u00020\u0001H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b(\u0010\f\u001A\"\u0010%\u001A\u00020\r2\u0006\u0010\u0002\u001A\u00020\r2\u0006\u0010\u0003\u001A\u00020\rH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b)\u0010\u000F\u001A+\u0010%\u001A\u00020\r2\u0006\u0010\u0002\u001A\u00020\r2\u0006\u0010\u0003\u001A\u00020\r2\u0006\u0010\u0006\u001A\u00020\rH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b*\u0010\u0011\u001A&\u0010%\u001A\u00020\r2\u0006\u0010\u0002\u001A\u00020\r2\n\u0010\t\u001A\u00020\u0012\"\u00020\rH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b+\u0010\u0014\u001A\"\u0010%\u001A\u00020\u00152\u0006\u0010\u0002\u001A\u00020\u00152\u0006\u0010\u0003\u001A\u00020\u0015H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b,\u0010\u0017\u001A+\u0010%\u001A\u00020\u00152\u0006\u0010\u0002\u001A\u00020\u00152\u0006\u0010\u0003\u001A\u00020\u00152\u0006\u0010\u0006\u001A\u00020\u0015H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b-\u0010\u0019\u001A&\u0010%\u001A\u00020\u00152\u0006\u0010\u0002\u001A\u00020\u00152\n\u0010\t\u001A\u00020\u001A\"\u00020\u0015H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b.\u0010\u001C\u001A\"\u0010%\u001A\u00020\u001D2\u0006\u0010\u0002\u001A\u00020\u001D2\u0006\u0010\u0003\u001A\u00020\u001DH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b/\u0010\u001F\u001A+\u0010%\u001A\u00020\u001D2\u0006\u0010\u0002\u001A\u00020\u001D2\u0006\u0010\u0003\u001A\u00020\u001D2\u0006\u0010\u0006\u001A\u00020\u001DH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b0\u0010!\u001A&\u0010%\u001A\u00020\u001D2\u0006\u0010\u0002\u001A\u00020\u001D2\n\u0010\t\u001A\u00020\"\"\u00020\u001DH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b1\u0010$\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u00062"}, d2 = {"maxOf", "Lkotlin/UByte;", "a", "b", "maxOf-Kr8caGY", "(BB)B", "c", "maxOf-b33U2AM", "(BBB)B", "other", "Lkotlin/UByteArray;", "maxOf-Wr6uiD8", "(B[B)B", "Lkotlin/UInt;", "maxOf-J1ME1BU", "(II)I", "maxOf-WZ9TVnA", "(III)I", "Lkotlin/UIntArray;", "maxOf-Md2H83M", "(I[I)I", "Lkotlin/ULong;", "maxOf-eb3DHEI", "(JJ)J", "maxOf-sambcqE", "(JJJ)J", "Lkotlin/ULongArray;", "maxOf-R03FKyM", "(J[J)J", "Lkotlin/UShort;", "maxOf-5PvTz6A", "(SS)S", "maxOf-VKSA0NQ", "(SSS)S", "Lkotlin/UShortArray;", "maxOf-t1qELG4", "(S[S)S", "minOf", "minOf-Kr8caGY", "minOf-b33U2AM", "minOf-Wr6uiD8", "minOf-J1ME1BU", "minOf-WZ9TVnA", "minOf-Md2H83M", "minOf-eb3DHEI", "minOf-sambcqE", "minOf-R03FKyM", "minOf-5PvTz6A", "minOf-VKSA0NQ", "minOf-t1qELG4", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/comparisons/UComparisonsKt")
class UComparisonsKt___UComparisonsKt {
    public static final short maxOf-5PvTz6A(short v, short v1) {
        return Intrinsics.compare(v & 0xFFFF, 0xFFFF & v1) < 0 ? v1 : v;
    }

    public static final int maxOf-J1ME1BU(int v, int v1) {
        return UByte..ExternalSyntheticBackport0.m$2(v, v1) < 0 ? v1 : v;
    }

    public static final byte maxOf-Kr8caGY(byte b, byte b1) {
        return Intrinsics.compare(b & 0xFF, b1 & 0xFF) < 0 ? b1 : b;
    }

    public static final int maxOf-Md2H83M(int v, int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "other");
        int v1 = UIntArray.getSize-impl(arr_v);
        for(int v2 = 0; v2 < v1; ++v2) {
            v = UComparisonsKt.maxOf-J1ME1BU(v, UIntArray.get-pVg5ArA(arr_v, v2));
        }
        return v;
    }

    public static final long maxOf-R03FKyM(long v, long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "other");
        int v1 = ULongArray.getSize-impl(arr_v);
        for(int v2 = 0; v2 < v1; ++v2) {
            v = UComparisonsKt.maxOf-eb3DHEI(v, ULongArray.get-s-VKNKU(arr_v, v2));
        }
        return v;
    }

    private static final short maxOf-VKSA0NQ(short v, short v1, short v2) {
        return UComparisonsKt.maxOf-5PvTz6A(v, UComparisonsKt.maxOf-5PvTz6A(v1, v2));
    }

    private static final int maxOf-WZ9TVnA(int v, int v1, int v2) {
        return UComparisonsKt.maxOf-J1ME1BU(v, UComparisonsKt.maxOf-J1ME1BU(v1, v2));
    }

    public static final byte maxOf-Wr6uiD8(byte b, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "other");
        int v = UByteArray.getSize-impl(arr_b);
        for(int v1 = 0; v1 < v; ++v1) {
            b = UComparisonsKt.maxOf-Kr8caGY(b, UByteArray.get-w2LRezQ(arr_b, v1));
        }
        return b;
    }

    private static final byte maxOf-b33U2AM(byte b, byte b1, byte b2) {
        return UComparisonsKt.maxOf-Kr8caGY(b, UComparisonsKt.maxOf-Kr8caGY(b1, b2));
    }

    public static final long maxOf-eb3DHEI(long v, long v1) {
        return UByte..ExternalSyntheticBackport0.m(v, v1) < 0 ? v1 : v;
    }

    private static final long maxOf-sambcqE(long v, long v1, long v2) {
        return UComparisonsKt.maxOf-eb3DHEI(v, UComparisonsKt.maxOf-eb3DHEI(v1, v2));
    }

    public static final short maxOf-t1qELG4(short v, short[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "other");
        int v1 = UShortArray.getSize-impl(arr_v);
        for(int v2 = 0; v2 < v1; ++v2) {
            v = UComparisonsKt.maxOf-5PvTz6A(v, UShortArray.get-Mh2AYeg(arr_v, v2));
        }
        return v;
    }

    public static final short minOf-5PvTz6A(short v, short v1) {
        return Intrinsics.compare(v & 0xFFFF, 0xFFFF & v1) > 0 ? v1 : v;
    }

    public static final int minOf-J1ME1BU(int v, int v1) {
        return UByte..ExternalSyntheticBackport0.m$2(v, v1) > 0 ? v1 : v;
    }

    public static final byte minOf-Kr8caGY(byte b, byte b1) {
        return Intrinsics.compare(b & 0xFF, b1 & 0xFF) > 0 ? b1 : b;
    }

    public static final int minOf-Md2H83M(int v, int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "other");
        int v1 = UIntArray.getSize-impl(arr_v);
        for(int v2 = 0; v2 < v1; ++v2) {
            v = UComparisonsKt.minOf-J1ME1BU(v, UIntArray.get-pVg5ArA(arr_v, v2));
        }
        return v;
    }

    public static final long minOf-R03FKyM(long v, long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "other");
        int v1 = ULongArray.getSize-impl(arr_v);
        for(int v2 = 0; v2 < v1; ++v2) {
            v = UComparisonsKt.minOf-eb3DHEI(v, ULongArray.get-s-VKNKU(arr_v, v2));
        }
        return v;
    }

    private static final short minOf-VKSA0NQ(short v, short v1, short v2) {
        return UComparisonsKt.minOf-5PvTz6A(v, UComparisonsKt.minOf-5PvTz6A(v1, v2));
    }

    private static final int minOf-WZ9TVnA(int v, int v1, int v2) {
        return UComparisonsKt.minOf-J1ME1BU(v, UComparisonsKt.minOf-J1ME1BU(v1, v2));
    }

    public static final byte minOf-Wr6uiD8(byte b, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "other");
        int v = UByteArray.getSize-impl(arr_b);
        for(int v1 = 0; v1 < v; ++v1) {
            b = UComparisonsKt.minOf-Kr8caGY(b, UByteArray.get-w2LRezQ(arr_b, v1));
        }
        return b;
    }

    private static final byte minOf-b33U2AM(byte b, byte b1, byte b2) {
        return UComparisonsKt.minOf-Kr8caGY(b, UComparisonsKt.minOf-Kr8caGY(b1, b2));
    }

    public static final long minOf-eb3DHEI(long v, long v1) {
        return UByte..ExternalSyntheticBackport0.m(v, v1) > 0 ? v1 : v;
    }

    private static final long minOf-sambcqE(long v, long v1, long v2) {
        return UComparisonsKt.minOf-eb3DHEI(v, UComparisonsKt.minOf-eb3DHEI(v1, v2));
    }

    public static final short minOf-t1qELG4(short v, short[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "other");
        int v1 = UShortArray.getSize-impl(arr_v);
        for(int v2 = 0; v2 < v1; ++v2) {
            v = UComparisonsKt.minOf-5PvTz6A(v, UShortArray.get-Mh2AYeg(arr_v, v2));
        }
        return v;
    }
}

