package kotlin.collections;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001C\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u001C\u0010\u0000\u001A\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001A\u001C\u0010\u0000\u001A\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001A\u001C\u0010\u0000\u001A\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001A\u001C\u0010\u0000\u001A\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000B\u0010\u0005\u001A\u001A\u0010\f\u001A\u00020\r*\b\u0012\u0004\u0012\u00020\u00030\u000EH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000F\u001A\u001A\u0010\u0010\u001A\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000EH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001A\u001A\u0010\u0013\u001A\u00020\u0014*\b\u0012\u0004\u0012\u00020\u00070\u000EH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001A\u001A\u0010\u0016\u001A\u00020\u0017*\b\u0012\u0004\u0012\u00020\n0\u000EH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"sum", "Lkotlin/UInt;", "", "Lkotlin/UByte;", "sumOfUByte", "(Ljava/lang/Iterable;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Ljava/lang/Iterable;)J", "Lkotlin/UShort;", "sumOfUShort", "toUByteArray", "Lkotlin/UByteArray;", "", "(Ljava/util/Collection;)[B", "toUIntArray", "Lkotlin/UIntArray;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "(Ljava/util/Collection;)[S", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/UCollectionsKt")
class UCollectionsKt___UCollectionsKt {
    public static final int sumOfUByte(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        int v = 0;
        for(Object object0: iterable0) {
            v = UInt.constructor-impl(v + UInt.constructor-impl(((UByte)object0).unbox-impl() & 0xFF));
        }
        return v;
    }

    public static final int sumOfUInt(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        int v = 0;
        for(Object object0: iterable0) {
            v = UInt.constructor-impl(v + ((UInt)object0).unbox-impl());
        }
        return v;
    }

    public static final long sumOfULong(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        long v = 0L;
        for(Object object0: iterable0) {
            v = ULong.constructor-impl(v + ((ULong)object0).unbox-impl());
        }
        return v;
    }

    public static final int sumOfUShort(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        int v = 0;
        for(Object object0: iterable0) {
            v = UInt.constructor-impl(v + UInt.constructor-impl(((UShort)object0).unbox-impl() & 0xFFFF));
        }
        return v;
    }

    public static final byte[] toUByteArray(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        byte[] arr_b = UByteArray.constructor-impl(collection0.size());
        int v = 0;
        for(Object object0: collection0) {
            UByteArray.set-VurrAj0(arr_b, v, ((UByte)object0).unbox-impl());
            ++v;
        }
        return arr_b;
    }

    public static final int[] toUIntArray(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        int[] arr_v = UIntArray.constructor-impl(collection0.size());
        int v = 0;
        for(Object object0: collection0) {
            UIntArray.set-VXSXFK8(arr_v, v, ((UInt)object0).unbox-impl());
            ++v;
        }
        return arr_v;
    }

    public static final long[] toULongArray(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        long[] arr_v = ULongArray.constructor-impl(collection0.size());
        int v = 0;
        for(Object object0: collection0) {
            ULongArray.set-k8EXiF4(arr_v, v, ((ULong)object0).unbox-impl());
            ++v;
        }
        return arr_v;
    }

    public static final short[] toUShortArray(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        short[] arr_v = UShortArray.constructor-impl(collection0.size());
        int v = 0;
        for(Object object0: collection0) {
            UShortArray.set-01HTLdE(arr_v, v, ((UShort)object0).unbox-impl());
            ++v;
        }
        return arr_v;
    }
}

