package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001A*\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u0001H\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0006\u0010\u0007\u001A*\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\b2\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u0001H\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\t\u0010\n\u001A*\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u0001H\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\f\u0010\r\u001A*\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u000E2\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u0001H\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u000F\u0010\u0010\u001A*\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u0001H\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0013\u0010\u0014\u001A*\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0002\u001A\u00020\b2\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u0001H\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0015\u0010\u0016\u001A*\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0002\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u0001H\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0017\u0010\u0018\u001A*\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0002\u001A\u00020\u000E2\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u0001H\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0019\u0010\u001A\u001A*\u0010\u001B\u001A\u00020\u00122\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u001C\u001A\u00020\u00012\u0006\u0010\u001D\u001A\u00020\u0001H\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001E\u0010\u0014\u001A*\u0010\u001B\u001A\u00020\u00122\u0006\u0010\u0002\u001A\u00020\b2\u0006\u0010\u001C\u001A\u00020\u00012\u0006\u0010\u001D\u001A\u00020\u0001H\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001F\u0010\u0016\u001A*\u0010\u001B\u001A\u00020\u00122\u0006\u0010\u0002\u001A\u00020\u000B2\u0006\u0010\u001C\u001A\u00020\u00012\u0006\u0010\u001D\u001A\u00020\u0001H\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b \u0010\u0018\u001A*\u0010\u001B\u001A\u00020\u00122\u0006\u0010\u0002\u001A\u00020\u000E2\u0006\u0010\u001C\u001A\u00020\u00012\u0006\u0010\u001D\u001A\u00020\u0001H\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b!\u0010\u001A\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class UArraySortingKt {
    private static final int partition--nroSd4(long[] arr_v, int v, int v1) {
        long v2 = ULongArray.get-s-VKNKU(arr_v, (v + v1) / 2);
        while(v <= v1) {
            while(UByte..ExternalSyntheticBackport0.m(ULongArray.get-s-VKNKU(arr_v, v), v2) < 0) {
                ++v;
            }
            while(UByte..ExternalSyntheticBackport0.m(ULongArray.get-s-VKNKU(arr_v, v1), v2) > 0) {
                --v1;
            }
            if(v <= v1) {
                long v3 = ULongArray.get-s-VKNKU(arr_v, v);
                ULongArray.set-k8EXiF4(arr_v, v, ULongArray.get-s-VKNKU(arr_v, v1));
                ULongArray.set-k8EXiF4(arr_v, v1, v3);
                ++v;
                --v1;
            }
        }
        return v;
    }

    private static final int partition-4UcCI2c(byte[] arr_b, int v, int v1) {
        int v2 = UByteArray.get-w2LRezQ(arr_b, (v + v1) / 2);
        while(v <= v1) {
            while(Intrinsics.compare(UByteArray.get-w2LRezQ(arr_b, v) & 0xFF, v2 & 0xFF) < 0) {
                ++v;
            }
            while(Intrinsics.compare(UByteArray.get-w2LRezQ(arr_b, v1) & 0xFF, v2 & 0xFF) > 0) {
                --v1;
            }
            if(v <= v1) {
                int v3 = UByteArray.get-w2LRezQ(arr_b, v);
                UByteArray.set-VurrAj0(arr_b, v, UByteArray.get-w2LRezQ(arr_b, v1));
                UByteArray.set-VurrAj0(arr_b, v1, ((byte)v3));
                ++v;
                --v1;
            }
        }
        return v;
    }

    private static final int partition-Aa5vz7o(short[] arr_v, int v, int v1) {
        int v2 = UShortArray.get-Mh2AYeg(arr_v, (v + v1) / 2);
        while(v <= v1) {
            while(Intrinsics.compare(UShortArray.get-Mh2AYeg(arr_v, v) & 0xFFFF, v2 & 0xFFFF) < 0) {
                ++v;
            }
            while(Intrinsics.compare(UShortArray.get-Mh2AYeg(arr_v, v1) & 0xFFFF, v2 & 0xFFFF) > 0) {
                --v1;
            }
            if(v <= v1) {
                int v3 = UShortArray.get-Mh2AYeg(arr_v, v);
                UShortArray.set-01HTLdE(arr_v, v, UShortArray.get-Mh2AYeg(arr_v, v1));
                UShortArray.set-01HTLdE(arr_v, v1, ((short)v3));
                ++v;
                --v1;
            }
        }
        return v;
    }

    private static final int partition-oBK06Vg(int[] arr_v, int v, int v1) {
        int v2 = UIntArray.get-pVg5ArA(arr_v, (v + v1) / 2);
        while(v <= v1) {
            while(UByte..ExternalSyntheticBackport0.m$2(UIntArray.get-pVg5ArA(arr_v, v), v2) < 0) {
                ++v;
            }
            while(UByte..ExternalSyntheticBackport0.m$2(UIntArray.get-pVg5ArA(arr_v, v1), v2) > 0) {
                --v1;
            }
            if(v <= v1) {
                int v3 = UIntArray.get-pVg5ArA(arr_v, v);
                UIntArray.set-VXSXFK8(arr_v, v, UIntArray.get-pVg5ArA(arr_v, v1));
                UIntArray.set-VXSXFK8(arr_v, v1, v3);
                ++v;
                --v1;
            }
        }
        return v;
    }

    private static final void quickSort--nroSd4(long[] arr_v, int v, int v1) {
        int v2 = UArraySortingKt.partition--nroSd4(arr_v, v, v1);
        if(v < v2 - 1) {
            UArraySortingKt.quickSort--nroSd4(arr_v, v, v2 - 1);
        }
        if(v2 < v1) {
            UArraySortingKt.quickSort--nroSd4(arr_v, v2, v1);
        }
    }

    private static final void quickSort-4UcCI2c(byte[] arr_b, int v, int v1) {
        int v2 = UArraySortingKt.partition-4UcCI2c(arr_b, v, v1);
        if(v < v2 - 1) {
            UArraySortingKt.quickSort-4UcCI2c(arr_b, v, v2 - 1);
        }
        if(v2 < v1) {
            UArraySortingKt.quickSort-4UcCI2c(arr_b, v2, v1);
        }
    }

    private static final void quickSort-Aa5vz7o(short[] arr_v, int v, int v1) {
        int v2 = UArraySortingKt.partition-Aa5vz7o(arr_v, v, v1);
        if(v < v2 - 1) {
            UArraySortingKt.quickSort-Aa5vz7o(arr_v, v, v2 - 1);
        }
        if(v2 < v1) {
            UArraySortingKt.quickSort-Aa5vz7o(arr_v, v2, v1);
        }
    }

    private static final void quickSort-oBK06Vg(int[] arr_v, int v, int v1) {
        int v2 = UArraySortingKt.partition-oBK06Vg(arr_v, v, v1);
        if(v < v2 - 1) {
            UArraySortingKt.quickSort-oBK06Vg(arr_v, v, v2 - 1);
        }
        if(v2 < v1) {
            UArraySortingKt.quickSort-oBK06Vg(arr_v, v2, v1);
        }
    }

    public static final void sortArray--nroSd4(long[] arr_v, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_v, "array");
        UArraySortingKt.quickSort--nroSd4(arr_v, v, v1 - 1);
    }

    public static final void sortArray-4UcCI2c(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "array");
        UArraySortingKt.quickSort-4UcCI2c(arr_b, v, v1 - 1);
    }

    public static final void sortArray-Aa5vz7o(short[] arr_v, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_v, "array");
        UArraySortingKt.quickSort-Aa5vz7o(arr_v, v, v1 - 1);
    }

    public static final void sortArray-oBK06Vg(int[] arr_v, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_v, "array");
        UArraySortingKt.quickSort-oBK06Vg(arr_v, v, v1 - 1);
    }
}

