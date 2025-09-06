package kotlin.collections;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001E\n\u0002\b\u0002\u001A/\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u0005H\u0000¢\u0006\u0002\u0010\u0006\u001A\u0018\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\u0004\u001A\u00020\u0005H\u0001\u001A#\u0010\n\u001A\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0001H\u0001¢\u0006\u0004\b\u000B\u0010\f\u001A,\u0010\r\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0001H\u0086\b¢\u0006\u0002\u0010\u000E\u001A\u0015\u0010\u000F\u001A\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0013H\u0087\b\u001A&\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u0015H\u0086\b¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"arrayOfNulls", "", "T", "reference", "size", "", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "copyOfRangeToIndexCheck", "", "toIndex", "contentDeepHashCodeImpl", "contentDeepHashCode", "([Ljava/lang/Object;)I", "orEmpty", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "", "", "charset", "Ljava/nio/charset/Charset;", "toTypedArray", "", "(Ljava/util/Collection;)[Ljava/lang/Object;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/ArraysKt")
class ArraysKt__ArraysJVMKt {
    public static final Object[] arrayOfNulls(Object[] arr_object, int v) {
        Intrinsics.checkNotNullParameter(arr_object, "reference");
        Object object0 = Array.newInstance(arr_object.getClass().getComponentType(), v);
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
        return (Object[])object0;
    }

    public static final int contentDeepHashCode(Object[] arr_object) {
        return Arrays.deepHashCode(arr_object);
    }

    public static final void copyOfRangeToIndexCheck(int v, int v1) {
        if(v > v1) {
            throw new IndexOutOfBoundsException("toIndex (" + v + ") is greater than size (" + v1 + ").");
        }
    }

    public static final Object[] orEmpty(Object[] arr_object) {
        if(arr_object == null) {
            Intrinsics.reifiedOperationMarker(0, "T?");
            return new Object[0];
        }
        return arr_object;
    }

    private static final String toString(byte[] arr_b, Charset charset0) {
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return new String(arr_b, charset0);
    }

    public static final Object[] toTypedArray(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.reifiedOperationMarker(0, "T?");
        return collection0.toArray(new Object[0]);
    }
}

